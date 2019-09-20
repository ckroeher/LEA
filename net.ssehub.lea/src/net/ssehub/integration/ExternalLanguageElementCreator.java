/*
 * Copyright 2019 University of Hildesheim, Software Systems Engineering
 * 
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements. See the NOTICE
 * file distributed with this work for additional information regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package net.ssehub.integration;

import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;

import net.ssehub.integration.annotations.AnalysisCall;
import net.ssehub.integration.annotations.ArtifactParameterType;
import net.ssehub.integration.annotations.ChangeIdentifier;
import net.ssehub.integration.annotations.ExtractorCall;
import net.ssehub.integration.annotations.FragmentParameterType;
import net.ssehub.integration.annotations.Operation;
import net.ssehub.integration.annotations.ResultParameterType;

/**
 * This class creates the specific {@link LanguageElement}s based on a given {@link Class}, which was extracted and 
 * loaded from external plug-ins by the {@link LanguageElementProvider}. Each completely constructed
 * {@link LanguageElement} is added to the {@link LanguageRegistry}.
 * 
 * @author Christian Kroeher
 *
 */
public class ExternalLanguageElementCreator extends AbstractLanguageElementCreator {
    
    /**
     * The {@link List} of cached {@link net.ssehub.integration.ChangeIdentifier}s. Their construction will be completed
     * in {@link #finalizeCreations()}.
     */
    private List<net.ssehub.integration.ChangeIdentifier> cachedChangeIdentifiers;
    
    /**
     * The {@link List} of cached {@link Call}s. Their construction will be completed in {@link #finalizeCreations()}.
     */
    private List<Call> cachedCalls;

    /**
     * Constructs a new {@link ExternalLanguageElementCreator}.
     * 
     * @see #createLanguageElements(Class, File)
     * @see #finalizeCreations()
     */
    public ExternalLanguageElementCreator() {
        cachedChangeIdentifiers = new ArrayList<net.ssehub.integration.ChangeIdentifier>();
        cachedCalls = new ArrayList<Call>();
    }
    
    /**
     * Creates {@link LanguageElement}s by scanning the given {@link Class} for the custom annotations classifying 
     * external classes, attributes, or methods to represent a language element. Each completely constructed
     * {@link LanguageElement} is added to the {@link LanguageRegistry}. However, the construction of
     * {@link net.ssehub.integration.ChangeIdentifier}s and {@link Call}s can only be completed, if all
     * {@link ParameterType}s are available. Hence, the caller of this method must ensure, that all relevant
     * {@link Class}es were passed once to this method, before {@link #finalizeCreations()} is called. That call is
     * the mandatory last step in creating (external) {@link LanguageElement}s.
     * 
     * @param pluginClass the {@link Class} in which language elements should be found; should never be
     *        <code>null</code>
     * @param sourcePlugin the {@link File} denoting a Java archive file in which the given class is located; should
     *        never be <code>null</code>
     * @throws LanguageElementException if creating a language element failed; it is <b>not</b> thrown, if the given
     *         class does not introduce any language elements
     */
    public void createLanguageElements(Class<?> pluginClass, File sourcePlugin) 
            throws LanguageElementException {
        createParameterType(pluginClass, sourcePlugin);
        
        initializeChangeIdentifier(pluginClass, sourcePlugin);
        
        Method[] pluginClassMethods = pluginClass.getDeclaredMethods();
        Method pluginClassMethod;
        for (int i = 0; i < pluginClassMethods.length; i++) {
            pluginClassMethod = pluginClassMethods[i];
            initializeCall(pluginClassMethod, pluginClass, sourcePlugin);
        }
    }
    
    /**
     * Creates a new {@link ParameterType} by scanning the given {@link Class} for the custom annotations
     * classifying external classes to represent this language element and adds it to the {@link LanguageRegistry}.
     * These annotations are:
     * <ul>
     * <li>{@link ArtifactParameterType}, which declares a type <code>T</code> for defining artifacts, like
     *     <code><b>Artifact</b>&lt;T&gt; myArtifact;</code>
     * </li>
     * <li>{@link FragmentParameterType}, which declares a type <code>T</code> for defining fragments, like
     *     <code><b>Fragment</b>&lt;T&gt; myFragment;</code>
     * </li>
     * <li>{@link ResultParameterType}, which declares a type <code>T</code> for defining results, like
     *     <code><b>Result</b>&lt;T&gt; myResult;</code>
     * </li>
     * </ul>
     * All other annotations will be ignored.
     * 
     * @param pluginClass the {@link Class}, which may introduce a new {@link ParameterType}; should never be 
     *        <code>null</code>
     * @param sourcePlugin the {@link File} denoting a Java archive file in which the given class is located; should
     *        never be <code>null</code>
     * @throws LanguageElementException if creating the {@link ParameterType} failed; it is <b>not</b> thrown, if the
     *         given class does not introduce any language elements
     */
    private void createParameterType(Class<?> pluginClass, File sourcePlugin) 
            throws LanguageElementException {
        ElementType parameterTypeElementType = getElementType(pluginClass);
        if (parameterTypeElementType != null) {            
            String parameterTypeName = getParameterTypeName(pluginClass);
            try {
                createParameterType(parameterTypeElementType, parameterTypeName, pluginClass, sourcePlugin);
            } catch (LanguageElementException e) {
                throw new LanguageElementException("Creating " + parameterTypeElementType + " based on class \"" 
                        + pluginClass.getSimpleName() + "\" in plug-in \"" + sourcePlugin.getAbsolutePath() 
                        + "\" failed", e);
            }
        }
    }
    
    /**
     * Creates a <b>premature</b> {@link net.ssehub.integration.ChangeIdentifier} by scanning the given {@link Class}
     * for the {@link ChangeIdentifier} annotation classifying external classes to represent this language element. The
     * construction of a {@link net.ssehub.integration.ChangeIdentifier} can only be completed, if all 
     * {@link ParameterType}s, to which a change identifier can be potentially assigned to, are available in the
     * {@link LanguageRegistry}. Hence, the caller of {@link #createLanguageElements(Class, File)} must guarantee that
     * {@link #finalizeCreations()} will be called after all plug-in classes were passed once to this creator.
     * Otherwise the initialized change identifier will last as a cached element.
     * 
     * @param pluginClass the {@link Class}, which may introduce a new {@link net.ssehub.integration.ChangeIdentifier};
     *        should never be <code>null</code>
     * @param sourcePlugin the {@link File} denoting a Java archive file in which the given class is located; should
     *        never be <code>null</code>
     * @throws LanguageElementException if initializing the {@link net.ssehub.integration.ChangeIdentifier} failed; it
     *         is <b>not</b> thrown, if the given class does not introduce any language elements
     * @see #finalizeCreations()
     */
    private void initializeChangeIdentifier(Class<?> pluginClass, File sourcePlugin) throws LanguageElementException {
        if (pluginClass.isAnnotationPresent(ChangeIdentifier.class)) {
            ChangeIdentifier customAnnotation = pluginClass.getAnnotation(ChangeIdentifier.class);
            String changeIdentifierName = createLanguageElementName(pluginClass, customAnnotation.name(), "");
            try {
                net.ssehub.integration.ChangeIdentifier changeIndentifier = 
                        new net.ssehub.integration.ChangeIdentifier(changeIdentifierName, pluginClass, sourcePlugin);
                cachedChangeIdentifiers.add(changeIndentifier);
            } catch (LanguageElementException e) {
                throw new LanguageElementException("Creating change identifier based on class \"" 
                        + pluginClass.getSimpleName() + "\" in plug-in \"" + sourcePlugin.getAbsolutePath() 
                        + "\" failed", e);
            }
        }
    }
    
    /**
     * Creates a <b>premature</b> {@link Call} by scanning the given {@link Method} for the custom annotations
     * classifying external methods to represent this language element. These annotations are:
     * <ul>
     * <li>{@link Operation}, which declares the method to be a {@link Call} of the type {@link ElementType#OPERATION}.
     *     </li>
     * <li>{@link ExtractorCall}, which declares a method to be a {@link Call} of the type
     *     {@link ElementType#EXTRACTOR_CALL}</li>
     * <li>{@link AnalysisCall}, which declares a method to be a {@link Call} of the type
     *     {@link ElementType#ANALYSIS_CALL}</li>
     * </ul>
     * All other annotations will be ignored. The construction of a {@link Call} can only be completed, if all 
     * {@link ParameterType}s, which may potentially be return types or parameters of a call, are available in the
     * {@link LanguageRegistry}. Hence, the caller of {@link #createLanguageElements(Class, File)} must guarantee that
     * {@link #finalizeCreations()} will be called after all plug-in classes were passed once to this creator.
     * Otherwise the initialized call will last as a cached element.
     * 
     * @param pluginClassMethod the {@link Method}, which may introduce a new {@link Call}; should never be
     *        <code>null</code>
     * @param pluginClass the {@link Class} in which the given method is defined; should never be <code>null</code>
     * @param sourcePlugin the {@link File} denoting a Java archive file in which the given class is located; should
     *        never be <code>null</code>
     * @throws LanguageElementException if initializing the {@link Call} failed; it is <b>not</b> thrown, if the given
     *         method does not introduce any language elements
     * @see #finalizeCreations()
     */
    private void initializeCall(Method pluginClassMethod, Class<?> pluginClass, File sourcePlugin) 
            throws LanguageElementException {
        ElementType callElementType = getElementType(pluginClassMethod);
        if (callElementType != null) {            
            String callName = getCallName(pluginClassMethod);
            try {
                Call call = new Call(callElementType, callName, pluginClassMethod, pluginClass, sourcePlugin);
                cachedCalls.add(call);
            } catch (LanguageElementException e) {
                throw new LanguageElementException("Creating " + callElementType + " based on method \"" 
                        + pluginClassMethod.getName() + "\" in class \"" + pluginClass.getSimpleName() 
                        + "\" of plug-in \"" + sourcePlugin.getAbsolutePath() + "\" failed", e);
            }
        }
    }
    
    /**
     * Completes the construction of all {@link #cachedChangeIdentifiers} and {@link #cachedCalls} and adds them to the 
     * {@link LanguageRegistry}.
     * 
     * @throws LanguageElementException if completing the construction of one of the above elements failed
     * @see #finalizeChangeIdentifier(net.ssehub.integration.ChangeIdentifier)
     * @see #finalizeCall(Call, String, String[], String)
     */
    public void finalizeCreations() throws LanguageElementException {
        for (net.ssehub.integration.ChangeIdentifier changeIdentifier : cachedChangeIdentifiers) {
            finalizeChangeIdentifier(changeIdentifier);
        }
        cachedChangeIdentifiers = null;
        
        Method sourceMethod;
        for (Call call : cachedCalls) {
            sourceMethod = call.getSourceMethod();
            finalizeCall(call, getReturnType(sourceMethod), getParameters(sourceMethod), getParentParameterType(call));
        }
        cachedCalls = null;
    }
    
    /**
     * Completes the construction of the given {@link net.ssehub.integration.ChangeIdentifier} by setting the
     * {@link ParameterType}s to which that change identifier is assignable to, if these types are available in the 
     * {@link LanguageRegistry}. The completely constructed {@link net.ssehub.integration.ChangeIdentifier} is then
     * added to the {@link LanguageRegistry}.
     * 
     * @param changeIdentifier the {@link net.ssehub.integration.ChangeIdentifier} for which the construction shall be
     *                         be completed; should never be <code>null</code>
     * @throws LanguageElementException if completing the construction of the given
     *         {@link net.ssehub.integration.ChangeIdentifier} or its addition to the {@link LanguageRegistry} failed
     * @see LanguageRegistry#addChangeIdentifier(net.ssehub.integration.ChangeIdentifier)
     */
    private void finalizeChangeIdentifier(net.ssehub.integration.ChangeIdentifier changeIdentifier) 
            throws LanguageElementException {
        ChangeIdentifier changeIdentifierAnnotation = changeIdentifier.getSourceClass()
                .getAnnotation(ChangeIdentifier.class);
        String[] assignableElements = changeIdentifierAnnotation.assignableTo();
        ParameterType[] assignebleParameterTypes = new ParameterType[assignableElements.length];
        for (int i = 0; i < assignableElements.length; i++) {
            assignebleParameterTypes[i] = languageRegistry.getParameterType(assignableElements[i]);
        }
        try {
            changeIdentifier.finalize(assignebleParameterTypes);
            if (!languageRegistry.addChangeIdentifier(changeIdentifier)) {
                throw new LanguageElementException("Adding change identifier \"" + changeIdentifier.getName() 
                        + "\" to language registry failed");
            }
        } catch (LanguageElementException e) {
            throw new LanguageElementException("Completing the construction of change identifier \"" 
                    + changeIdentifier.getFullyQualifiedName() + "\" failed", e);
        }
    }
    
    /**
     * Returns the return type for a {@link Call} based on the custom annotations the given {@link Method} is annotated
     * with.
     * 
     * @param sourceMethod the source {@link Method} of the {@link Call} for which the return type shall be returned;
     *        should never be <code>null</code>
     * @return the {@link String} representing the return type of the {@link Call} of the given source {@link Method};
     *         never <code>null</code> nor <i>blank</i>
     */
    private String getReturnType(Method sourceMethod) {
        String returnType = null;
        String symbolicReturnType = null;
        if (sourceMethod.isAnnotationPresent(Operation.class)) {
            Operation annotation = sourceMethod.getAnnotation(Operation.class);
            symbolicReturnType = annotation.returnType();
        } else if (sourceMethod.isAnnotationPresent(ExtractorCall.class)) {
            ExtractorCall annotation = sourceMethod.getAnnotation(ExtractorCall.class);
            symbolicReturnType = annotation.returnType();
        } else if (sourceMethod.isAnnotationPresent(AnalysisCall.class)) {
            AnalysisCall annotation = sourceMethod.getAnnotation(AnalysisCall.class);
            symbolicReturnType = annotation.returnType();
        }
        returnType = createLanguageElementName(sourceMethod.getGenericReturnType().getTypeName(), symbolicReturnType);
        return returnType;
    }
    
    /**
     * Returns the parameter for a {@link Call} based on the custom annotations the given {@link Method} is annotated
     * with.
     * 
     * @param sourceMethod the source {@link Method} of the {@link Call} for which the parameters shall be returned;
     *        should never be <code>null</code>
     * @return the array of {@link String}s representing the parameters of the {@link Call} of the given source
     *         {@link Method}; never <code>null</code> nor <i>blank</i>
     * @throws LanguageElementException if the number of given symbolic parameters does not match the number of
     *         declared parameters of the given method
     */
    private String[] getParameters(Method sourceMethod) throws LanguageElementException {
        String[] parameters = null;
        String[] symbolicParameters = null;
        if (sourceMethod.isAnnotationPresent(Operation.class)) {
            Operation annotation = sourceMethod.getAnnotation(Operation.class);
            symbolicParameters = annotation.parameters();
        } else if (sourceMethod.isAnnotationPresent(ExtractorCall.class)) {
            ExtractorCall annotation = sourceMethod.getAnnotation(ExtractorCall.class);
            symbolicParameters = annotation.parameters();
        } else if (sourceMethod.isAnnotationPresent(AnalysisCall.class)) {
            AnalysisCall annotation = sourceMethod.getAnnotation(AnalysisCall.class);
            symbolicParameters = annotation.parameters();
        }
        parameters = createCallParameters(sourceMethod, symbolicParameters);
        return parameters;
    }
    
    /**
     * Returns the (optional) parent parameter type of the given {@link Call} of the type {@link ElementType#OPERATION}
     * based on the custom annotation the given {@link Call}'s source method is annotated with.
     * 
     * @param call the {@link Call} for which the parent parameter type shall be returned; should never be 
     *        <code>null</code>
     * @return the {@link String} representing the parent parameter type of the given {@link Call} or <code>null</code>,
     *         if the given {@link Call} is not of the type {@link ElementType#OPERATION} or no parent parameter type is
     *         defined; never <i>blank</i>
     */
    private String getParentParameterType(Call call) {
        String parentParameterType = null;
        if (call.getElementType() == ElementType.OPERATION) {
            Operation operationAnnotation = call.getSourceMethod().getAnnotation(Operation.class);
            parentParameterType = operationAnnotation.isMemberOf();
            if (parentParameterType.isBlank()) {
                parentParameterType = null;
            }
        }
        return parentParameterType;
    }
    
    /**
     * Creates the (symbolic) parameters for a {@link Call} as a {@link String} array based on the {@link Parameter}s
     * of the given {@link Method} or based on the given symbolic parameter names (typically defined by the annotation
     * values).
     * 
     * @param pluginClassMethod the {@link Method}, which provides the {@link Parameter}s to create the {@link Call}
     *        parameters; should never be <code>null</code>
     * @param symbolicParameters the symbolic name(s) of the {@link Call} parameters as defined by the method's
     *        annotation; the name(s) that will be returned, if the array is not empty; should never be
     *        <code>null</code> 
     * @return the (symbolic) parameters for a {@link Call} as a {@link String}
     * @throws LanguageElementException if the number of given symbolic parameters does not match the number of
     *         declared parameters of the given method 
     */
    private String[] createCallParameters(Method pluginClassMethod, String[] symbolicParameters) 
            throws LanguageElementException {
        String[] callParameters = null;
        Parameter[] methodParameters = pluginClassMethod.getParameters();
        if (symbolicParameters.length != 0) {
            if (symbolicParameters.length != methodParameters.length) {                
                throw new LanguageElementException("Mismatch of actual (" + methodParameters.length 
                        + ") and symbolic ("
                        + symbolicParameters.length + ") parameters while creating call based on method \"" 
                        + pluginClassMethod.toGenericString() + "\"");
            }
            callParameters = symbolicParameters;
        } else {
            callParameters = new String[methodParameters.length];
            for (int i = 0; i < methodParameters.length; i++) {
                callParameters[i] = createLanguageElementName(methodParameters[i].getParameterizedType().getTypeName(),
                        "");
            }
        }
        return callParameters;
    }
    
    /**
     * Returns the {@link ElementType} for a {@link ParameterType} based on the custom annotations the given 
     * {@link Class} is annotated with. That mapping is as follows:
     * <ul>
     * <li>{@link ArtifactParameterType} returns {@link ElementType#ARTIFACT_PARAMETER_TYPE}</li>
     * <li>{@link FragmentParameterType} returns {@link ElementType#FRAGMENT_PARAMETER_TYPE}</li>
     * <li>{@link ResultParameterType} returns {@link ElementType#RESULT_PARAMETER_TYPE}</li>
     * </ul>
     * 
     * @param pluginClass the {@link Class} from which the {@link ParameterType} will be created; should never be
     *        <code>null</code>
     * @return the {@link ElementType} of the {@link ParameterType} created from the given {@link Class} as defined
     *         above or <code>null</code> if none of the above annotations could be found
     */
    private ElementType getElementType(Class<?> pluginClass) {
        ElementType elementType = null;
        if (pluginClass.isAnnotationPresent(ArtifactParameterType.class)) {
            elementType = ElementType.ARTIFACT_PARAMETER_TYPE;
        } else if (pluginClass.isAnnotationPresent(FragmentParameterType.class)) {
            elementType = ElementType.FRAGMENT_PARAMETER_TYPE;
        } else if (pluginClass.isAnnotationPresent(ResultParameterType.class)) {
            elementType = ElementType.RESULT_PARAMETER_TYPE;
        }
        return elementType;
    }
    
    /**
     * Returns the {@link ElementType} for a {@link Call} based on the custom annotations the given 
     * {@link Method} is annotated with. That mapping is as follows:
     * <ul>
     * <li>{@link Operation} returns {@link ElementType#OPERATION}</li>
     * <li>{@link ExtractorCall} returns {@link ElementType#EXTRACTOR_CALL}</li>
     * <li>{@link AnalysisCall} returns {@link ElementType#ANALYSIS_CALL}</li>
     * </ul>
     * 
     * @param pluginClassMethod the {@link Method} from which the {@link Call} will be created; should never be
     *        <code>null</code>
     * @return the {@link ElementType} of the {@link Call} created from the given {@link Method} as defined
     *         above or <code>null</code> if none of the above annotations could be found
     */
    private ElementType getElementType(Method pluginClassMethod) {
        ElementType elementType = null;
        if (pluginClassMethod.isAnnotationPresent(Operation.class)) {
            elementType = ElementType.OPERATION;
        } else if (pluginClassMethod.isAnnotationPresent(ExtractorCall.class)) {
            elementType = ElementType.EXTRACTOR_CALL;
        } else if (pluginClassMethod.isAnnotationPresent(AnalysisCall.class)) {
            elementType = ElementType.ANALYSIS_CALL;
        }
        return elementType;
    }
    
    /**
     * Returns the name for a {@link ParameterType} created by either the name of the given {@link Class} or the custom
     * annotations.
     * 
     * @param pluginClass the {@link Class} from which the {@link ParameterType} will be created; should never be
     *        <code>null</code>
     * @return the {@link String} representing the name of the {@link ParameterType} created from the given
     *         {@link Class}; never <code>null</code> nor <i>blank</i>
     * @see #createLanguageElementName(Class, String, String)
     */
    private String getParameterTypeName(Class<?> pluginClass) {
        String parameterTypeName = null;
        String symbolicName = null;
        String symbolicParameterName = null;
        if (pluginClass.isAnnotationPresent(ArtifactParameterType.class)) {
            ArtifactParameterType annotation = pluginClass.getAnnotation(ArtifactParameterType.class);
            symbolicName = annotation.name();
            symbolicParameterName = annotation.parameterName();
        } else if (pluginClass.isAnnotationPresent(FragmentParameterType.class)) {
            FragmentParameterType annotation = pluginClass.getAnnotation(FragmentParameterType.class);
            symbolicName = annotation.name();
            symbolicParameterName = annotation.parameterName();
        } else if (pluginClass.isAnnotationPresent(ResultParameterType.class)) {
            ResultParameterType annotation = pluginClass.getAnnotation(ResultParameterType.class);
            symbolicName = annotation.name();
            symbolicParameterName = annotation.parameterName();
        }
        parameterTypeName = createLanguageElementName(pluginClass, symbolicName, symbolicParameterName);
        return parameterTypeName;
    }
    
    /**
     * Returns the name for a {@link Call} created by either the name of the given {@link Method} or the custom
     * annotations.
     * 
     * @param pluginClassMethod the {@link Method} from which the {@link Call} will be created; should never be
     *        <code>null</code>
     * @return the {@link String} representing the name of the {@link Call} created from the given
     *         {@link Method}; never <code>null</code> nor <i>blank</i>
     * @see #createLanguageElementName(String, String)
     */
    private String getCallName(Method pluginClassMethod) {
        String callName = null;
        String symbolicName = null;
        if (pluginClassMethod.isAnnotationPresent(Operation.class)) {
            Operation annotation = pluginClassMethod.getAnnotation(Operation.class);
            symbolicName = annotation.name();
        } else if (pluginClassMethod.isAnnotationPresent(ExtractorCall.class)) {
            ExtractorCall annotation = pluginClassMethod.getAnnotation(ExtractorCall.class);
            symbolicName = annotation.name();
        } else if (pluginClassMethod.isAnnotationPresent(AnalysisCall.class)) {
            AnalysisCall annotation = pluginClassMethod.getAnnotation(AnalysisCall.class);
            symbolicName = annotation.name();
        }
        callName = createLanguageElementName(pluginClassMethod.getName(), symbolicName);
        return callName;
    }
    
    /**
     * Creates the (symbolic) name for the language element represented by the given {@link Class}. This name is either
     * constructed by the given symbolic names (typically defined by the annotation values) or by the elements of the
     * class declaration.
     * 
     * @param pluginClass the {@link Class} for which the (symbolic) language element name shall be created; should
     *        never be <code>null</code>
     * @param symbolicName the symbolic name for the language element represented by the given {@link Class} as defined
     *        by its annotation; the name that will be returned, if it is not blank; should never be <code>null</code>
     * @param symbolicParameterName the symbolic name of the parameter type as defined by the class annotation, if the
     *        given {@link Class} is a generic one; the name that will be returned, if it is not blank; should never be
     *        <code>null</code>
     * @return the (symbolic) name for the language element represented by the given {@link Class}; never 
     *         <code>null</code> nor <i>blank</i>
     */
    private String createLanguageElementName(Class<?> pluginClass, String symbolicName, String symbolicParameterName) {
        StringBuilder elementNameBuilder = new StringBuilder();
        elementNameBuilder.append(pluginClass.getSimpleName());
        if (!symbolicName.isBlank()) {
            // Use the name as defined in the annotation instead of the actual class name
            elementNameBuilder.setLength(0);
            elementNameBuilder.append(symbolicName);
        }
        TypeVariable<?>[] typeParameters = pluginClass.getTypeParameters();
        if (typeParameters.length > 0) {
            // The plug-in class is a generic. Hence, add the parameter type to the element name
            elementNameBuilder.append("<");
            if (!symbolicParameterName.isBlank()) {
                // Use the parameter type name as defined in the annotation instead of the actual parameter type name
                elementNameBuilder.append(symbolicParameterName);
            } else {
                elementNameBuilder.append(typeParameters[0].getName());
                int typeParameterCounter = 1;
                while (typeParameterCounter < typeParameters.length) {
                    elementNameBuilder.append(", ");
                    elementNameBuilder.append(typeParameters[typeParameterCounter]);
                    typeParameterCounter++;
                }
                 
            }
            elementNameBuilder.append(">");
        }
        return elementNameBuilder.toString();
    }

}
