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


import java.util.HashMap;
import java.util.List;

/**
 * This class represents the singleton language registry, which contains all (external) language elements available for
 * defining analyses.
 * 
 * @author Christian Kroeher
 *
 */
public class LanguageRegistry extends AbstractLanguageRegistry {

    /**
     * The singleton instance of this {@link LanguageRegistry}.
     */
    public static final LanguageRegistry INSTANCE = new LanguageRegistry();
    
    /**
     * Constructs the singleton instance of this {@link LanguageRegistry}.
     */
    private LanguageRegistry() {
        super();
    }
    
    /**
     * Returns the number of {@link LanguageElement}s currently registered in this {@link LanguageRegistry}.
     * 
     * @return the number of {@link LanguageElement}s currently registered in this {@link LanguageRegistry}
     */
    public int size() {
        return languageElementCounter;
    }
    
    /**
     * Checks whether at least one {@link ChangeIdentifier} with the given name is available.
     *  
     * @param name the name of the {@link ChangeIdentifier} to search for
     * @return <code>true</code>, if at least one {@link ChangeIdentifier} with the given name is available;
     *         <code>false</code> otherwise
     */
    public boolean hasChangeIdentifier(String name) {
        return changeIdentifiers.containsKey(name);
    }
    
    /**
     * Checks whether a {@link ChangeIdentifier} is available, which has the given name and is assignable to the given
     * elements.
     * 
     * @param name the name of the {@link ChangeIdentifier} to search for
     * @param assignableElements the array of elements to which the {@link ChangeIdentifier} should be assignable to
     * @return <code>true</code>, if a {@link ChangeIdentifier} is available, which has the given name and is assignable
     *         to the given elements; <code>false</code> otherwise
     */
    public boolean hasChangeIdentifier(String name, String[] assignableElements) {
        boolean hasChangeIdentifer = false;
        List<ChangeIdentifier> availableChangeIdentifiers = changeIdentifiers.get(name);
        if (availableChangeIdentifiers != null) {
            ParameterType[] correspondingParameterTypes = new ParameterType[assignableElements.length];
            boolean correspondingParameterTypesAvailable = true;
            int elementsCounter = 0;
            ParameterType correspondingParameterType;
            while (correspondingParameterTypesAvailable && elementsCounter < assignableElements.length) {
                correspondingParameterType = getParameterType(assignableElements[elementsCounter]);
                if (correspondingParameterType != null) {
                    correspondingParameterTypes[elementsCounter] = correspondingParameterType;
                } else {
                    correspondingParameterTypesAvailable = false;
                }
                elementsCounter++;
            }
            if (correspondingParameterTypesAvailable) {                
                elementsCounter = 0;
                while (!hasChangeIdentifer && elementsCounter < availableChangeIdentifiers.size()) {
                    hasChangeIdentifer = availableChangeIdentifiers.get(elementsCounter)
                            .assignableTo(correspondingParameterTypes);
                    elementsCounter++;
                }
            }
        }
        return hasChangeIdentifer;
    }
    
    /**
     * Checks whether at least one {@link ParameterType} of the type {@link ElementType#ARTIFACT_PARAMETER_TYPE} with
     * the given name is available.
     *  
     * @param name the name of the {@link ParameterType} of the type {@link ElementType#ARTIFACT_PARAMETER_TYPE} to
     *        search for
     * @return <code>true</code>, if at least one {@link ParameterType} of the type
     *         {@link ElementType#ARTIFACT_PARAMETER_TYPE} with the given name is available; <code>false</code>
     *         otherwise
     */
    public boolean hasArtifactParameterType(String name) {
        return artifactParameterTypes.containsKey(name);
    }
    
    /**
     * Checks whether at least one {@link ParameterType} of the type {@link ElementType#FRAGMENT_PARAMETER_TYPE} with
     * the given name is available.
     *  
     * @param name the name of the {@link ParameterType} of the type {@link ElementType#FRAGMENT_PARAMETER_TYPE} to
     *        search for
     * @return <code>true</code>, if at least one {@link ParameterType} of the type
     *         {@link ElementType#FRAGMENT_PARAMETER_TYPE} with the given name is available; <code>false</code>
     *         otherwise
     */
    public boolean hasFragmentParameterType(String name) {
        return fragmentParameterTypes.containsKey(name);
    }
    
    /**
     * Checks whether at least one {@link ParameterType} of the type {@link ElementType#RESULT_PARAMETER_TYPE} with
     * the given name is available.
     *  
     * @param name the name of the {@link ParameterType} of the type {@link ElementType#RESULT_PARAMETER_TYPE} to
     *        search for
     * @return <code>true</code>, if at least one {@link ParameterType} of the type
     *         {@link ElementType#RESULT_PARAMETER_TYPE} with the given name is available; <code>false</code>
     *         otherwise
     */
    public boolean hasResultParameterType(String name) {
        return resultParameterTypes.containsKey(name);
    }
    
    /**
     * Returns the {@link ParameterType}, which has the given fully-qualified name, regardless of its 
     * {@link ElementType}.
     * 
     * @param fullyQualifiedName the fully-qualified name of the {@link ParameterType} to search for; should never be
     *        <code>null</code> nor <i>blank</i>
     * @return the {@link ParameterType} with the given fully-qualified name or <code>null</code>, if no such 
     *         {@link ParameterType} is available
     */
    public ParameterType getParameterType(String fullyQualifiedName) {
        ParameterType parameterType = null;
        String simpleName = fullyQualifiedName.substring(fullyQualifiedName.lastIndexOf(".") + 1);
        List<ParameterType> availableParameterTypes = artifactParameterTypes.get(simpleName);
        if (availableParameterTypes == null) {
            availableParameterTypes = fragmentParameterTypes.get(simpleName);
            if (availableParameterTypes == null) {
                availableParameterTypes = resultParameterTypes.get(simpleName);
            }
        }
        if (availableParameterTypes != null) {
            if (availableParameterTypes.size() == 1) {
                parameterType = availableParameterTypes.get(0);
            } else {                
                int availableParameterTypesCounter = 0;
                ParameterType availableParameterType;
                while (parameterType == null && availableParameterTypesCounter < availableParameterTypes.size()) {
                    availableParameterType = availableParameterTypes.get(availableParameterTypesCounter);
                    if (availableParameterType.getFullyQualifiedName().equals(fullyQualifiedName)) {
                        parameterType = availableParameterType;
                    }
                    availableParameterTypesCounter++;
                }
            }
        }
        return parameterType;
    }
    
    /**
     * Returns the return type of the {@link Call} identified by the given name and parameter types. This method
     * searches in {@link AbstractLanguageRegistry#extractorCalls}, {@link AbstractLanguageRegistry#analysisCalls}, and
     * {@link AbstractLanguageRegistry#operations} in that order and returns the return type of the first matching call.
     * 
     * @param name the name of the {@link Call} for which the return type shall be returned; should never be 
     *        <code>null</code> nor <i>blank</i>
     * @param parameterTypes the optional array of parameters the {@link Call} should accept in the given order; can be
     *        <code>null</code> to indicate that the desired {@link Call} does not accept any parameters
     * @return the return type of the {@link Call} identified by the given name and parameter types or
     *         <code>null</code>, if no such {@link Call} is available
     * @see #getCallReturnType(HashMap, String, String[])
     */
    public String getCallReturnType(String name, String[] parameterTypes) {
        String callReturnType = getCallReturnType(extractorCalls, name, parameterTypes);
        if (callReturnType == null) {
            callReturnType = getCallReturnType(analysisCalls, name, parameterTypes);
        }
        if (callReturnType == null) {
            callReturnType = getCallReturnType(operations, name, parameterTypes);
        }
        return callReturnType;
    }
    
    /**
     * Searches in the given {@link HashMap} for a {@link Call} with the given name and parameter types and returns its
     * return type.
     * 
     * @param map the {@link HashMap} in which to search for a {@link Call} with the given attributes
     * @param name the name of the {@link Call} for which the return type shall be returned; should never be 
     *        <code>null</code> nor <i>blank</i>
     * @param parameterTypes the optional array of parameters the {@link Call} should accept in the given order; can be
     *        <code>null</code> to indicate that the desired {@link Call} does not accept any parameters
     * @return the return type of the {@link Call} identified by the given name and parameter types or
     *         <code>null</code>, if no such {@link Call} is available in the given map
     */
    private String getCallReturnType(HashMap<String, List<Call>> map, String name, String[] parameterTypes) {
        String callReturnType = null;
        List<Call> availableCalls = map.get(name);
        if (availableCalls != null) {
            // Name already equal, hence, we only need to check for equal parameters
            int availableCallsCounter = 0;
            int numberOfParameterTypes = 0;
            if (parameterTypes != null) {
                numberOfParameterTypes = parameterTypes.length;
            }
            Call availableCall;
            boolean parametersEqual;
            int parametersCounter;
            while (callReturnType == null && availableCallsCounter < availableCalls.size()) {
                availableCall = availableCalls.get(availableCallsCounter);
                if (availableCall.getParameters().length == numberOfParameterTypes) {
                    parametersEqual = true;
                    parametersCounter = 0;
                    while (parametersEqual && parametersCounter < parameterTypes.length) {
                        parametersEqual = parameterTypes[parametersCounter]
                                .equals(availableCall.getParameters()[parametersCounter].getName());
                        parametersCounter++;
                    }
                    if (parametersEqual) {
                        callReturnType = availableCall.getReturnType().getName();
                    }
                }
                availableCallsCounter++;
            }
        }
        return callReturnType;
    }
    
    /**
     * Returns a {@link List} of all {@link ParameterType}s of the type {@link ElementType#ARTIFACT_PARAMETER_TYPE},
     * which have the given name.
     *  
     * @param name the name of the artifact parameter type(s), which should be returned
     * @return the {@link List} of all {@link ParameterType}s of the type {@link ElementType#ARTIFACT_PARAMETER_TYPE}
     *         with the given name or <code>null</code>, if no such elements are registered
     */
    public List<ParameterType> getArtifactParameterTypes(String name) {
        return artifactParameterTypes.get(name);
    }
    
    /**
     * Returns a {@link List} of all {@link ParameterType}s of the type {@link ElementType#FRAGMENT_PARAMETER_TYPE},
     * which have the given name.
     *  
     * @param name the name of the fragment parameter type(s), which should be returned
     * @return the {@link List} of all {@link ParameterType}s of the type {@link ElementType#FRAGMENT_PARAMETER_TYPE}
     *         with the given name or <code>null</code>, if no such elements are registered
     */
    public List<ParameterType> getFragmentParameterTypes(String name) {
        return fragmentParameterTypes.get(name);
    }
    
    /**
     * Returns a {@link List} of all {@link ParameterType}s of the type {@link ElementType#RESULT_PARAMETER_TYPE},
     * which have the given name.
     *  
     * @param name the name of the result parameter type(s), which should be returned
     * @return the {@link List} of all {@link ParameterType}s of the type {@link ElementType#RESULT_PARAMETER_TYPE}
     *         with the given name or <code>null</code>, if no such elements are registered
     */
    public List<ParameterType> getResultParameterTypes(String name) {
        return resultParameterTypes.get(name);
    }
    
    /**
     * Returns a {@link List} of all {@link ChangeIdentifier}s, which have the given name.
     *  
     * @param name the name of the change identifier(s), which should be returned
     * @return the {@link List} of all {@link ChangeIdentifier}s with the given name or <code>null</code>, if no such
     *         elements are registered
     */
    public List<ChangeIdentifier> getChangeIdentifiers(String name) {
        return changeIdentifiers.get(name);
    }
    
    /**
     * Returns a {@link List} of all {@link Call}s of the type {@link ElementType#OPERATION}, which have the given name.
     *  
     * @param name the name of the operation(s), which should be returned
     * @return the {@link List} of all {@link Call}s of the type {@link ElementType#OPERATION} with the given name or
     *         <code>null</code>, if no such elements are registered
     */
    public List<Call> getOperations(String name) {
        return operations.get(name);
    }
    
    /**
     * Returns a {@link List} of all {@link Call}s of the type {@link ElementType#OPERATION}, which represent member
     * operations for the {@link ParameterType} identified by the given name.
     *  
     * @param name the name of the {@link ParameterType} for which all member operations should be returned
     * @return the {@link List} of all {@link Call}s of the type {@link ElementType#OPERATION}, which represent member
     *         operations for the {@link ParameterType} identified by the given name or <code>null</code>, if no such
     *         elements are registered
     */
    public List<Call> getMemberOperations(String name) {
        return memberOperations.get(name);
    }
    
    /**
     * Returns a {@link List} of all {@link Call}s of the type {@link ElementType#EXTRACTOR_CALL}, which have the given
     * name.
     *  
     * @param name the name of the extractor call(s), which should be returned
     * @return the {@link List} of all {@link Call}s of the type {@link ElementType#EXTRACTOR_CALL} with the given name
     *         or <code>null</code>, if no such elements are registered
     */
    public List<Call> getExtractorCalls(String name) {
        return extractorCalls.get(name);
    }
    
    /**
     * Returns a {@link List} of all {@link Call}s of the type {@link ElementType#ANALYSIS_CALL}, which have the given
     * name.
     *  
     * @param name the name of the analysis call(s), which should be returned
     * @return the {@link List} of all {@link Call}s of the type {@link ElementType#ANALYSIS_CALL} with the given name
     *         or <code>null</code>, if no such elements are registered
     */
    public List<Call> getAnalysisCall(String name) {
        return analysisCalls.get(name);
    }
    
}
