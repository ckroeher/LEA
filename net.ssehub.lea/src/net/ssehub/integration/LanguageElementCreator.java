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
 * loaded by the {@link LanguageElementProvider}. Each completely constructed {@link LanguageElement} is added to the
 * {@link LanguageRegistry}.
 * 
 * @author Christian Kroeher
 *
 */
public class LanguageElementCreator {
  
    /**
     * The {@link LanguageRegistry} to which created {@link LanguageElement}s will be added and which provides already
     * created {@link ParameterType}s for references from {@link net.ssehub.integration.ChangeIdentifier}s and
     * {@link Call}s to these types.
     */
    private LanguageRegistry languageRegistry;
    
    /**
     * The {@link List} of cached {@link net.ssehub.integration.ChangeIdentifier}s. Their construction will be completed
     * in {@link #finalizeCreations()}.
     */
    private List<net.ssehub.integration.ChangeIdentifier> cachedChangeIdentifiers;
    
    /**
     * The {@link List} of cached {@link Call}s of the type {@link ElementType#OPERATION}. Their construction will be
     * completed in {@link #finalizeCreations()}.
     */
    private List<Call> cachedOperations;
    
    /**
     * The {@link List} of cached {@link Call}s of the type {@link ElementType#EXTRACTOR_CALL}. Their construction will
     * be completed in {@link #finalizeCreations()}.
     */
    private List<Call> cachedExtractorCalls;
    
    /**
     * The {@link List} of cached {@link Call}s of the type {@link ElementType#ANALYSIS_CALL}. Their construction will
     * be completed in {@link #finalizeCreations()}.
     */
    private List<Call> cachedAnalysisCalls;

    /**
     * Constructs a new {@link LanguageElementCreator}.
     * 
     * @see #createLanguageElements(Class, File)
     * @see #finalizeCreations()
     */
    public LanguageElementCreator() {
        languageRegistry = LanguageRegistry.INSTANCE;
        cachedChangeIdentifiers = new ArrayList<net.ssehub.integration.ChangeIdentifier>();
        cachedOperations = new ArrayList<Call>();
        cachedExtractorCalls = new ArrayList<Call>();
        cachedAnalysisCalls = new ArrayList<Call>();
    }
    
    /**
     * Creates {@link LanguageElement}s by scanning the given {@link Class} for the custom annotations classifying 
     * external classes, attributes, or methods to represent a language element. Each completely constructed
     * {@link LanguageElement} is added to the {@link LanguageRegistry}.
     * 
     * @param pluginClass the {@link Class} in which language elements should be found; should never be
     *        <code>null</code>
     * @param sourcePlugin the {@link File} denoting a Java archive file in which the given class is located; should
     *        never be <code>null</code>
     * @throws ExternalElementException if creating a language element failed; it is <b>not</b> thrown, if the given
     *         class does not introduce any language elements
     */
    public void createLanguageElements(Class<?> pluginClass, File sourcePlugin) 
            throws ExternalElementException {
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
     * @throws ExternalElementException if creating the {@link ParameterType} failed; it is <b>not</b> thrown, if the
     *         given class does not introduce any language elements
     */
    private void createParameterType(Class<?> pluginClass, File sourcePlugin) 
            throws ExternalElementException {
        ParameterType parameterType = null;
        ElementType parameterTypeElementType = null;
        String parameterTypeName = null;
        if (pluginClass.isAnnotationPresent(ArtifactParameterType.class)) {
            // The pluginClass declares a type T for defining artifacts, like "Artifact<T> myArtifact;"
            parameterTypeElementType = ElementType.ARTIFACT_PARAMETER_TYPE;
            ArtifactParameterType customAnnotation = pluginClass.getAnnotation(ArtifactParameterType.class);
            parameterTypeName = createLanguageElementName(pluginClass, customAnnotation.name(),
                    customAnnotation.parameterName());
            try {
                parameterType = new ParameterType(parameterTypeElementType, parameterTypeName, pluginClass, 
                        sourcePlugin);
                if (!languageRegistry.addArtifactParameterType(parameterType)) {
                    throw new ExternalElementException("Adding artifact parameter type \"" + parameterTypeName 
                            + "\" to language registry failed");
                }
            } catch (LanguageElementException e) {
                throw new ExternalElementException("Creating artifact parameter type based on class \"" 
                        + pluginClass.getSimpleName() + "\" in plug-in \"" + sourcePlugin.getAbsolutePath() 
                        + "\" failed", e);
            }
        } else if (pluginClass.isAnnotationPresent(FragmentParameterType.class)) {
            // The pluginClass declares a type T for defining fragments, like "Fragment<T> myFragment;"
            parameterTypeElementType = ElementType.FRAGMENT_PARAMETER_TYPE;
            FragmentParameterType customAnnotation = pluginClass.getAnnotation(FragmentParameterType.class);
            parameterTypeName = createLanguageElementName(pluginClass, customAnnotation.name(),
                    customAnnotation.parameterName());
            try {
                parameterType = new ParameterType(parameterTypeElementType, parameterTypeName, pluginClass, 
                        sourcePlugin);
                if (!languageRegistry.addFragmentParameterType(parameterType)) {
                    throw new ExternalElementException("Adding fragment parameter type \"" + parameterTypeName 
                            + "\" to language registry failed");
                }
            } catch (LanguageElementException e) {
                throw new ExternalElementException("Creating fragment parameter type based on class \"" 
                        + pluginClass.getSimpleName() + "\" in plug-in \"" + sourcePlugin.getAbsolutePath() 
                        + "\" failed", e);
            }
        } else if (pluginClass.isAnnotationPresent(ResultParameterType.class)) {
            // The pluginClass declares a type T for defining results, like "Result<T> myResult;"
            parameterTypeElementType = ElementType.RESULT_PARAMETER_TYPE;
            ResultParameterType customAnnotation = pluginClass.getAnnotation(ResultParameterType.class);
            parameterTypeName = createLanguageElementName(pluginClass, customAnnotation.name(),
                    customAnnotation.parameterName());
            try {
                parameterType = new ParameterType(parameterTypeElementType, parameterTypeName, pluginClass, 
                        sourcePlugin);
                if (!languageRegistry.addResultParameterType(parameterType)) {
                    throw new ExternalElementException("Adding result parameter type \"" + parameterTypeName 
                            + "\" to language registry failed");
                }
            } catch (LanguageElementException e) {
                throw new ExternalElementException("Creating result parameter type based on class \"" 
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
     * @throws ExternalElementException if initializing the {@link net.ssehub.integration.ChangeIdentifier} failed; it
     *         is <b>not</b> thrown, if the given class does not introduce any language elements
     * @see #finalizeCreations()
     */
    private void initializeChangeIdentifier(Class<?> pluginClass, File sourcePlugin) throws ExternalElementException {
        if (pluginClass.isAnnotationPresent(ChangeIdentifier.class)) {
            ChangeIdentifier customAnnotation = pluginClass.getAnnotation(ChangeIdentifier.class);
            String changeIdentifierName = createLanguageElementName(pluginClass, customAnnotation.name(), "");
            try {
                net.ssehub.integration.ChangeIdentifier changeIndentifier = 
                        new net.ssehub.integration.ChangeIdentifier(changeIdentifierName, pluginClass, sourcePlugin);
                cachedChangeIdentifiers.add(changeIndentifier);
            } catch (LanguageElementException e) {
                throw new ExternalElementException("Creating change identifier based on class \"" 
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
     * @throws ExternalElementException if initializing the {@link Call} failed; it is <b>not</b> thrown, if the given
     *         method does not introduce any language elements
     * @see #finalizeCreations()
     */
    private void initializeCall(Method pluginClassMethod, Class<?> pluginClass, File sourcePlugin) 
            throws ExternalElementException {
        Call call = null;
        ElementType callElementType = null;
        String callName = null;
        if (pluginClassMethod.isAnnotationPresent(Operation.class)) {
            // The pluginClassMethod declares a general operation, like "op()" or "var.op()"
            callElementType = ElementType.OPERATION;
            Operation customAnnotation = pluginClassMethod.getAnnotation(Operation.class);
            callName = createLanguageElementName(pluginClassMethod.getName(), customAnnotation.name());
            try {
                call = new Call(callElementType, callName, pluginClassMethod, pluginClass, sourcePlugin);
                cachedOperations.add(call);
            } catch (LanguageElementException e) {
                throw new ExternalElementException("Creating operation based on method \"" 
                        + pluginClassMethod.getName() + "\" in class \"" + pluginClass.getSimpleName() 
                        + "\" of plug-in \"" + sourcePlugin.getAbsolutePath() + "\" failed", e);
            }
        } else if (pluginClassMethod.isAnnotationPresent(ExtractorCall.class)) {
            // The pluginClassMethod declares an extractor call for extracting fragments, like "Fragment<T> f = call()"
            callElementType = ElementType.EXTRACTOR_CALL;
            ExtractorCall customAnnotation = pluginClassMethod.getAnnotation(ExtractorCall.class);
            callName = createLanguageElementName(pluginClassMethod.getName(), customAnnotation.name());
            try {
                call = new Call(callElementType, callName, pluginClassMethod, pluginClass, sourcePlugin);
                cachedExtractorCalls.add(call);
            } catch (LanguageElementException e) {
                throw new ExternalElementException("Creating extractor call based on method \"" 
                        + pluginClassMethod.getName() + "\" in class \"" + pluginClass.getSimpleName() 
                        + "\" of plug-in \"" + sourcePlugin.getAbsolutePath() + "\" failed", e);
            }
        } else if (pluginClassMethod.isAnnotationPresent(AnalysisCall.class)) {
            // The pluginClassMethod declares an analysis call for providing results, like "Result<T> f = call()"
            callElementType = ElementType.ANALYSIS_CALL;
            AnalysisCall customAnnotation = pluginClassMethod.getAnnotation(AnalysisCall.class);
            callName = createLanguageElementName(pluginClassMethod.getName(), customAnnotation.name());
            try {
                call = new Call(callElementType, callName, pluginClassMethod, pluginClass, sourcePlugin);
                cachedAnalysisCalls.add(call);
            } catch (LanguageElementException e) {
                throw new ExternalElementException("Creating analysis call based on method \"" 
                        + pluginClassMethod.getName() + "\" in class \"" + pluginClass.getSimpleName() 
                        + "\" of plug-in \"" + sourcePlugin.getAbsolutePath() + "\" failed", e);
            }
        }
    }
    
    /**
     * Completes the construction of all {@link #cachedChangeIdentifiers}, {@link #cachedOperations}, 
     * {@link #cachedExtractorCalls}, and {@link #cachedAnalysisCalls} and adds them to the {@link LanguageRegistry}.
     * 
     * @throws ExternalElementException if completing the construction of one of the above elements failed
     * @see #finalizeChangeIdentifier(net.ssehub.integration.ChangeIdentifier)
     * @see #finalizeOperation(Call)
     * @see #finalizeExtractorCall(Call)
     * @see #finalizeAnalysisCall(Call)
     */
    public void finalizeCreations() throws ExternalElementException {
        for (net.ssehub.integration.ChangeIdentifier changeIdentifier : cachedChangeIdentifiers) {
            finalizeChangeIdentifier(changeIdentifier);
        }
        cachedChangeIdentifiers = null;
        
        for (Call call : cachedOperations) {
            finalizeOperation(call);
        }
        cachedOperations = null;
        
        for (Call call : cachedExtractorCalls) {
            finalizeExtractorCall(call);
        }
        cachedExtractorCalls = null;
        
        for (Call call : cachedAnalysisCalls) {
            finalizeAnalysisCall(call);
        }
        cachedAnalysisCalls = null;
    }
    
    /**
     * Completes the construction of the given {@link net.ssehub.integration.ChangeIdentifier} by setting the
     * {@link ParameterType}s to which that change identifier is assignable to, if these types are available in the 
     * {@link LanguageRegistry}. The completely constructed {@link net.ssehub.integration.ChangeIdentifier} is then
     * added to the {@link LanguageRegistry}.
     * 
     * @param changeIdentifier the {@link net.ssehub.integration.ChangeIdentifier} for which the construction shall be
     *                         be completed; should never be <code>null</code>
     * @throws ExternalElementException if completing the construction of the given
     *         {@link net.ssehub.integration.ChangeIdentifier} or its addition to the {@link LanguageRegistry} failed
     * @see LanguageRegistry#addChangeIdentifier(net.ssehub.integration.ChangeIdentifier)
     */
    private void finalizeChangeIdentifier(net.ssehub.integration.ChangeIdentifier changeIdentifier) 
            throws ExternalElementException {
        ChangeIdentifier changeIdentifierAnnotation = changeIdentifier.getSourceClass()
                .getAnnotation(ChangeIdentifier.class);
        String[] assignableElements = changeIdentifierAnnotation.assignableTo();
        ParameterType[] assignebleParameterTypes = new ParameterType[assignableElements.length];
        try {
            for (int i = 0; i < assignableElements.length; i++) {
                assignebleParameterTypes[i] = languageRegistry.getParameterType(assignableElements[i]);
            }
            changeIdentifier.setAssignableElements(assignebleParameterTypes);
            if (!languageRegistry.addChangeIdentifier(changeIdentifier)) {
                throw new ExternalElementException("Adding change identifier \"" + changeIdentifier.getName() 
                        + "\" to language registry failed");
            }
        } catch (LanguageElementException e) {
            throw new ExternalElementException("Setting assignable elements for change identifier \"" 
                    + changeIdentifier.getFullyQualifiedName() + "\" failed", e);
        }
    }
    
    /**
     * Completes the construction of the given {@link Call} by setting the {@link ParameterType}s, which represent the
     * return type, parameters, and (optional) parent parameter type of the given {@link Call}, if these types are
     * available in the {@link LanguageRegistry}. The completely constructed {@link Call} is then added to the
     * {@link LanguageRegistry} as an operation.
     * 
     * @param call the {@link Call} for which the construction shall be be completed; should never be <code>null</code>
     *        and must have {@link ElementType#OPERATION} as element type
     * @throws ExternalElementException if completing the construction of the given {@link Call} or its addition to the
     *         {@link LanguageRegistry} failed
     * @see #finalizeCall(Call, String, String[])
     * @see LanguageRegistry#addOperation(Call)
     */
    private void finalizeOperation(Call call) throws ExternalElementException {
        Operation operationAnnotation = call.getSourceMethod().getAnnotation(Operation.class);
        String parentParameterTypeString = operationAnnotation.isMemberOf();
        if (!parentParameterTypeString.isBlank()) {
            ParameterType parentParameterType = languageRegistry.getParameterType(parentParameterTypeString);
            try {
                call.setParentParameterType(parentParameterType);
            } catch (LanguageElementException e) {
                throw new ExternalElementException("Setting parent parameter type for operation \"" 
                        + call.getFullyQualifiedName() + "\" failed", e);
            }
        }
        String operationReturnType = createLanguageElementName(call.getSourceMethod().getGenericReturnType()
                .getTypeName(), operationAnnotation.returnType());
        String[] operationParameters = createCallParameters(call.getSourceMethod(),
                operationAnnotation.parameters());
        finalizeCall(call, operationReturnType, operationParameters);
        if (!languageRegistry.addOperation(call)) {
            throw new ExternalElementException("Adding operation \"" + call.getName() 
                    + "\" to language registry failed");
        }
    }
    
    /**
     * Completes the construction of the given {@link Call} by setting the {@link ParameterType}s, which represent the
     * return type and parameters of the given {@link Call}, if these types are available in the
     * {@link LanguageRegistry}. The completely constructed {@link Call} is then added to the {@link LanguageRegistry}
     * as an extractor call.
     * 
     * @param call the {@link Call} for which the construction shall be be completed; should never be <code>null</code>
     *        and must have {@link ElementType#EXTRACTOR_CALL} as element type
     * @throws ExternalElementException if completing the construction of the given {@link Call} or its addition to the
     *         {@link LanguageRegistry} failed
     * @see #finalizeCall(Call, String, String[])
     * @see LanguageRegistry#addExtractorCall(Call)
     */
    private void finalizeExtractorCall(Call call) throws ExternalElementException {
        ExtractorCall extractorCallAnnotation = call.getSourceMethod().getAnnotation(ExtractorCall.class);
        String extractorCallReturnType = createLanguageElementName(call.getSourceMethod().getGenericReturnType()
                .getTypeName(), extractorCallAnnotation.returnType());
        String[] extractorCallParameters = createCallParameters(call.getSourceMethod(),
                extractorCallAnnotation.parameters());
        finalizeCall(call, extractorCallReturnType, extractorCallParameters);
        if (!languageRegistry.addExtractorCall(call)) {
            throw new ExternalElementException("Adding extractor call \"" + call.getName()
                    + "\" to language registry failed");
        }
    }
    
    /**
     * Completes the construction of the given {@link Call} by setting the {@link ParameterType}s, which represent the
     * return type and parameters of the given {@link Call}, if these types are available in the
     * {@link LanguageRegistry}. The completely constructed {@link Call} is then added to the {@link LanguageRegistry}
     * as an analysis call.
     * 
     * @param call the {@link Call} for which the construction shall be be completed; should never be <code>null</code>
     *        and must have {@link ElementType#ANALYSIS_CALL} as element type
     * @throws ExternalElementException if completing the construction of the given {@link Call} or its addition to the
     *         {@link LanguageRegistry} failed
     * @see #finalizeCall(Call, String, String[])
     * @see LanguageRegistry#addAnalysisCall(Call)
     */
    private void finalizeAnalysisCall(Call call) throws ExternalElementException {
        AnalysisCall analysisCallAnnotation = call.getSourceMethod().getAnnotation(AnalysisCall.class);
        String analysisCallReturnType = createLanguageElementName(call.getSourceMethod().getGenericReturnType()
                .getTypeName(), analysisCallAnnotation.returnType());
        String[] analysisCallParameters = createCallParameters(call.getSourceMethod(),
                analysisCallAnnotation.parameters());
        finalizeCall(call, analysisCallReturnType, analysisCallParameters);
        if (!languageRegistry.addAnalysisCall(call)) {
            throw new ExternalElementException("Adding analysis call \"" + call.getName()
                    + "\" to language registry failed");
        }
    }
    
    /**
     * Completes the construction of the given {@link Call} by setting the {@link ParameterType}s, which represent the
     * return type and parameters of the given {@link Call}, if these types are available in the
     * {@link LanguageRegistry}.
     * 
     * @param call the {@link Call} for which the construction shall be be completed; should never be <code>null</code>
     * @param returnType the {@link String} representing the fully-qualified name of the return type of the given
     *        {@link Call}; should never be <code>null</code>
     * @param parameters the array of {@link String}s representing the fully-qualified names of the parameters of the
     *        given {@link Call}; should never be <code>null</code> but may be <i>empty</i>
     * @throws ExternalElementException if completing the construction of the given {@link Call} failed
     */
    private void finalizeCall(Call call, String returnType, String[] parameters) throws ExternalElementException {
        ParameterType returnTypeParameterType = languageRegistry.getParameterType(returnType);
        try {            
            call.setReturnType(returnTypeParameterType);
        } catch (LanguageElementException e) {
            throw new ExternalElementException("Setting return type for call \"" + call.getFullyQualifiedName() 
                    + "\" failed", e);
        }
        ParameterType[] parameterParameterTypes = new ParameterType[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            parameterParameterTypes[i] = languageRegistry.getParameterType(parameters[i]);
        }
        try {            
            call.setParameters(parameterParameterTypes);
        } catch (LanguageElementException e) {
            throw new ExternalElementException("Setting parameters for call \"" + call.getFullyQualifiedName() 
            + "\" failed", e);
        }
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
     * @throws ExternalElementException if the number of given symbolic parameters does not match the number of
     *         declared parameters of the given method 
     */
    private String[] createCallParameters(Method pluginClassMethod, String[] symbolicParameters) 
            throws ExternalElementException {
        String[] callParameters = null;
        Parameter[] methodParameters = pluginClassMethod.getParameters();
        if (symbolicParameters.length != 0) {
            if (symbolicParameters.length != methodParameters.length) {                
                throw new ExternalElementException("Mismatch of actual (" + methodParameters.length 
                        + ") and symbolic ("
                        + symbolicParameters.length + ") parameters while creating call based on method \"" 
                        + pluginClassMethod.toGenericString() + "\"");
            }
            callParameters = symbolicParameters;
        } else {
            callParameters = new String[methodParameters.length];
            for (int i = 0; i < methodParameters.length; i++) {
                callParameters[i] = 
                        createLanguageElementName(methodParameters[i].getParameterizedType().getTypeName(),
                        "");
            }
        }
        return callParameters;
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
     *         <code>null</code> nor <i>empty</i>
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
    
    /**
     * Creates the (symbolic) name for a language element with the given declared type name. The name is either
     * constructed by the given declared type name, which is the fully-qualified type name includes the possible generic
     * declaration and the generic parameter types, or the given symbolic type name (typically defined by the annotation
     * values).
     * 
     * @param declaredTypeName the fully-qualified, declared type name from which a name should be created
     * @param symbolicTypeName the name that will be returned, if it is not blank
     * @return name for a language element
     */
    private String createLanguageElementName(String declaredTypeName, String symbolicTypeName) {
        String elementName = symbolicTypeName;
        if (elementName.isBlank()) {            
            StringBuilder elementNameBuilder = new StringBuilder();
            int typeNameIndex = declaredTypeName.length() - 1;
            boolean ignore = false;
            do {
                if (!ignore && declaredTypeName.charAt(typeNameIndex) == '.') {
                    ignore = true;
                } else if (ignore && declaredTypeName.charAt(typeNameIndex) == '<') {
                    ignore = false;
                }
                if (!ignore) {
                    elementNameBuilder.append(declaredTypeName.charAt(typeNameIndex));
                }
                typeNameIndex--;
            } while (typeNameIndex > -1);
            elementName = elementNameBuilder.reverse().toString();
        }
        return elementName;
    }

}
