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
 * This class creates the specific {@link LanguageElement}s based on a given {@link Class}, which was loaded by the 
 * {@link LanguageElementProvider}.
 * 
 * @author Christian Kroeher
 *
 */
public class LanguageElementCreator {

    /**
     * Constructs a new {@link LanguageElementCreator}.
     * 
     * @see LanguageElementCreator#createLanguageElements(Class, File)
     */
    public LanguageElementCreator() {
        
    }
    
    /**
     * Creates {@link LanguageElement}s by scanning the given {@link Class} for the custom annotations classifying 
     * external classes, attributes, or methods to represent a language element.
     * 
     * @param pluginClass the {@link Class} in which language elements should be found; should never be
     *        <code>null</code>
     * @param sourcePlugin the {@link File} denoting a Java archive file in which the given class is located; should
     *        never be <code>null</code>
     * @return a {@link List} of {@link LanguageElement}s created from the given class; never <code>null</code>, but
     *         may be <i>empty</i>
     * @throws ExternalElementException if creating a language element failed; it is <b>not</b> thrown, if the given
     *         class does not introduce any language elements
     */
    public List<LanguageElement> createLanguageElements(Class<?> pluginClass, File sourcePlugin) 
            throws ExternalElementException {
        List<LanguageElement> createdElementsList = new ArrayList<LanguageElement>();
        LanguageElement createdElement = createLanguageElementFromClass(pluginClass, sourcePlugin);
        if (createdElement != null) {
            createdElementsList.add(createdElement);
        }
        Method[] pluginClassMethods = pluginClass.getDeclaredMethods();
        for (int i = 0; i < pluginClassMethods.length; i++) {
            createdElement = createLanguageElementFromMethod(pluginClassMethods[i], pluginClass, sourcePlugin);
            if (createdElement != null) {
                createdElementsList.add(createdElement);
            }
        }
        return createdElementsList;
    }
    
    /**
     * Creates a new {@link LanguageElement} by scanning the given {@link Class} for the custom annotations
     * classifying external classes to represent a language element. The specific language element depends on the used
     * annotations and the corresponding processing of the given class in {@link #createChangeIdentifier(Class, File)}
     * or {@link #createParameterType(Class, File)}.
     * 
     * @param pluginClass the {@link Class}, which may introduce a new language element, if it is annotated as such;
     *        should never be <code>null</code>
     * @param sourcePlugin the {@link File} denoting a Java archive file in which the given class is located; should
     *        never be <code>null</code>
     * @return a {@link LanguageElement} derived from the given class or <code>null</code>, if this class is not 
     *         annotated as introducing a new language element
     * @throws ExternalElementException if creating the language element failed; it is <b>not</b> thrown, if the given
     *         class does not introduce any language elements
     */
    private LanguageElement createLanguageElementFromClass(Class<?> pluginClass, File sourcePlugin) 
            throws ExternalElementException {
        LanguageElement createdElement = null;
        createdElement = createChangeIdentifier(pluginClass, sourcePlugin);
        if (createdElement == null) {
            createdElement = createParameterType(pluginClass, sourcePlugin);
        }
        return createdElement;
    }
    
    /**
     * Creates a new {@link net.ssehub.integration.ChangeIdentifier} by scanning the given {@link Class} for the custom
     * {@link ChangeIdentifier} annotation classifying external classes to represent this language element. All other
     * annotations will be ignored, which leads to a return value of <code>null</code>.
     * 
     * @param pluginClass the {@link Class}, which may introduce a new {@link net.ssehub.integration.ChangeIdentifier};
     *        should never be <code>null</code>
     * @param sourcePlugin the {@link File} denoting a Java archive file in which the given class is located; should
     *        never be <code>null</code>
     * @return a {@link net.ssehub.integration.ChangeIdentifier} derived from the given class or <code>null</code>, if
     *         this class is not annotated as introducing such a type of language element
     * @throws ExternalElementException if creating the {@link net.ssehub.integration.ChangeIdentifier} failed; it is
     *         <b>not</b> thrown, if the given class does not introduce any language elements
     */
    private net.ssehub.integration.ChangeIdentifier createChangeIdentifier(Class<?> pluginClass, File sourcePlugin) 
            throws ExternalElementException {
        net.ssehub.integration.ChangeIdentifier changeIndentifier = null;
        if (pluginClass.isAnnotationPresent(ChangeIdentifier.class)) {
            ChangeIdentifier customAnnotation = pluginClass.getAnnotation(ChangeIdentifier.class);
            try {
                changeIndentifier = new net.ssehub.integration.ChangeIdentifier(
                        createLanguageElementName(pluginClass, customAnnotation.name(), ""),
                        customAnnotation.assignableTo(), pluginClass, sourcePlugin);
            } catch (LanguageElementException e) {
                throw new ExternalElementException("Creating change identifier based on class \"" 
                        + pluginClass.getSimpleName() + "\" in plug-in \"" + sourcePlugin.getAbsolutePath() 
                        + "\" failed", e);
            }
        }
        return changeIndentifier;
    }
    
    /**
     * Creates a new {@link ParameterType} by scanning the given {@link Class} for the custom annotations
     * classifying external classes to represent this language element. These annotations are:
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
     * All other annotations will be ignored, which leads to a return value of <code>null</code>.
     * 
     * @param pluginClass the {@link Class}, which may introduce a new {@link ParameterType}; should never be 
     *        <code>null</code>
     * @param sourcePlugin the {@link File} denoting a Java archive file in which the given class is located; should
     *        never be <code>null</code>
     * @return a {@link ParameterType} derived from the given class or <code>null</code>, if this class is not 
     *         annotated as introducing such a type of language element
     * @throws ExternalElementException if creating the {@link ParameterType} failed; it is <b>not</b> thrown, if the
     *         given class does not introduce any language elements
     */
    private ParameterType createParameterType(Class<?> pluginClass, File sourcePlugin) 
            throws ExternalElementException {
        ParameterType parameterType = null;
        ElementType specificElementType = null;
        String symbolicName = null;
        String symbolicParameterTypeName = null;
        if (pluginClass.isAnnotationPresent(ArtifactParameterType.class)) {
            // The pluginClass declares a type T for defining artifacts, like "Artifact<T> myArtifact;"
            specificElementType = ElementType.ARTIFACT_PARAMETER_TYPE;
            ArtifactParameterType customAnnotation = pluginClass.getAnnotation(ArtifactParameterType.class);
            symbolicName = customAnnotation.name();
            symbolicParameterTypeName = customAnnotation.parameterName();
        } else if (pluginClass.isAnnotationPresent(FragmentParameterType.class)) {
            // The pluginClass declares a type T for defining fragments, like "Fragment<T> myFragment;"
            specificElementType = ElementType.FRAGMENT_PARAMETER_TYPE;
            FragmentParameterType customAnnotation = pluginClass.getAnnotation(FragmentParameterType.class);
            symbolicName = customAnnotation.name();
            symbolicParameterTypeName = customAnnotation.parameterName();
            
        } else if (pluginClass.isAnnotationPresent(ResultParameterType.class)) {
            // The pluginClass declares a type T for defining results, like "Result<T> myResult;"
            specificElementType = ElementType.RESULT_PARAMETER_TYPE;
            ResultParameterType customAnnotation = pluginClass.getAnnotation(ResultParameterType.class);
            symbolicName = customAnnotation.name();
            symbolicParameterTypeName = customAnnotation.parameterName();
        }
        // Create the language element, if the element type is known
        if (specificElementType != null) {
            try {
                parameterType = new ParameterType(specificElementType, 
                        createLanguageElementName(pluginClass, symbolicName, symbolicParameterTypeName), pluginClass,
                        sourcePlugin);
            } catch (LanguageElementException e) {
                throw new ExternalElementException("Creating parameter type based on class \"" 
                        + pluginClass.getSimpleName() + "\" in plug-in \"" + sourcePlugin.getAbsolutePath() 
                        + "\" failed", e);
            }
        }
        return parameterType;
    }
    
    /**
     * Creates a new {@link Call} by scanning the given {@link Method} for the custom annotations classifying external
     * methods to represent a language element. These annotations are:
     * <ul>
     * <li>
     * {@link Operation}, which declares the method to be a {@link Call} of the type {@link ElementType#OPERATION}.
     * </li>
     * <li>
     * {@link ExtractorCall}, which declares a method to be a {@link Call} of the type 
     * {@link ElementType#EXTRACTOR_CALL}
     * </li>
     * <li>
     * {@link AnalysisCall}, which declares a method to be a {@link Call} of the type 
     * {@link ElementType#ANALYSIS_CALL}
     * </li>
     * </ul>
     * All other annotations will be ignored, which leads to a return value of <code>null</code>.
     * 
     * @param pluginClassMethod the {@link Method}, which may introduce a new language element, if it is annotated as
     *        such; should never be <code>null</code>
     * @param sourceClass the {@link Class} in which the given method is declared; should never be <code>null</code>
     * @param sourcePlugin the {@link File} denoting a Java archive file in which the above class is located; should
     *        never be <code>null</code>
     * @return a {@link Call} derived from the given method or <code>null</code>, if this method is not annotated as
     *         introducing a new language element
     * @throws ExternalElementException if creating the {@link Call} failed; it is <b>not</b> thrown, if the given 
     *         method does not introduce any language element
     */
    private Call createLanguageElementFromMethod(Method pluginClassMethod, Class<?> sourceClass,
            File sourcePlugin) throws ExternalElementException {
        Call call = null;
        ElementType specificElementType = null;
        String callName = null;
        String returnType = null;
        String[] parameters = null;
        if (pluginClassMethod.isAnnotationPresent(Operation.class)) {
            // The pluginClassMethod declares a general operation, like "op()" or "var.op()"
            specificElementType = ElementType.OPERATION;
            callName = pluginClassMethod.getName();
            Operation customAnnotation = pluginClassMethod.getAnnotation(Operation.class);
            if (!customAnnotation.name().isBlank()) {
                callName = customAnnotation.name();
            }
            returnType = createLanguageElementName(pluginClassMethod.getGenericReturnType().getTypeName(),
                    customAnnotation.returnType());
            parameters = createCallParameters(pluginClassMethod, customAnnotation.parameters());
        } else if (pluginClassMethod.isAnnotationPresent(ExtractorCall.class)) {
            // The pluginClassMethod declares an extractor call for extracting fragments, like "Fragment<T> f = call()"
            specificElementType = ElementType.EXTRACTOR_CALL;
            callName = pluginClassMethod.getName();
            ExtractorCall customAnnotation = pluginClassMethod.getAnnotation(ExtractorCall.class);
            if (!customAnnotation.name().isBlank()) {
                callName = customAnnotation.name();
            }
            returnType = createLanguageElementName(pluginClassMethod.getGenericReturnType().getTypeName(),
                    customAnnotation.returnType());
            parameters = createCallParameters(pluginClassMethod, customAnnotation.parameters());
        } else if (pluginClassMethod.isAnnotationPresent(AnalysisCall.class)) {
            // The pluginClassMethod declares an analysis call for providing results, like "Result<T> f = call()"
            specificElementType = ElementType.ANALYSIS_CALL;
            callName = pluginClassMethod.getName();
            AnalysisCall customAnnotation = pluginClassMethod.getAnnotation(AnalysisCall.class);
            if (!customAnnotation.name().isBlank()) {
                callName = customAnnotation.name();
            }
            returnType = createLanguageElementName(pluginClassMethod.getGenericReturnType().getTypeName(),
                    customAnnotation.returnType());
            parameters = createCallParameters(pluginClassMethod, customAnnotation.parameters());
        }
        // Create the language element, if the element type is known
        if (specificElementType != null) {
            try {
                call = new Call(specificElementType, callName, returnType, parameters, sourceClass, sourcePlugin);
            } catch (LanguageElementException e) {
                throw new ExternalElementException("Creating call based on method \"" 
                        + pluginClassMethod.getName() + "\" in class \"" + sourceClass.getSimpleName() 
                        + "\" of plug-in \"" + sourcePlugin.getAbsolutePath() + "\" failed", e);
            }
        }
        return call;
    }
    
    /**
     * Creates the (symbolic) parameters for a {@link Call} as a {@link String} array based on the {@link Parameter}s of
     * the given {@link Method} or based on the given symbolic parameter names (typically defined by the annotation
     * values).
     * 
     * @param pluginClassMethod the {@link Method}, which provides the {@link Parameter}s to create the {@link Call}
     *        parameters; should never be <code>null</code>
     * @param symbolicParameters the symbolic name(s) of the {@link Call} parameters as defined by the method's
     *        annotation; the name(s) that will be returned, if the array is not empty; should never be
     *        <code>null</code> 
     * @return the (symbolic) parameters for a {@link Call} as a {@link String}
     * @throws ExternalElementException if the number of given symbolic parameters does not match the number of declared
     *         parameters of the given method 
     */
    private String[] createCallParameters(Method pluginClassMethod, String[] symbolicParameters) 
            throws ExternalElementException {
        String[] callParameters = null;
        Parameter[] methodParameters = pluginClassMethod.getParameters();
        if (symbolicParameters.length != 0) {
            if (symbolicParameters.length != methodParameters.length) {                
                throw new ExternalElementException("Mismatch of actual (" + methodParameters.length + ") and symbolic ("
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
