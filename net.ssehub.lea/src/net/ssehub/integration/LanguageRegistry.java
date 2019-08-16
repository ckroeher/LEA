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
            int availableChangeIdentifierCounter = 0;
            while (!hasChangeIdentifer && availableChangeIdentifierCounter < availableChangeIdentifiers.size()) {
                hasChangeIdentifer = availableChangeIdentifiers.get(availableChangeIdentifierCounter)
                        .assignableTo(assignableElements);
                availableChangeIdentifierCounter++;
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
     * Returns the {@link LanguageElement}, which has the given name. If multiple elements with the same name exist,
     * like equally named operations with different return or parameter types, the first match will be returned.
     * 
     * TODO method which returns all elements with the given name?
     *  
     * @param name the name of the {@link LanguageElement}, which should be returned
     * @return the (first found) {@link LanguageElement} with the given name or <code>null</code>, if no such element is
     *         registered
     */
    public LanguageElement getLanguageElement(String name) {
        LanguageElement foundElement = getArtifactParameterType(name);
        if (foundElement == null) {
            foundElement = getFragmentParameterType(name);
        }
        if (foundElement == null) {
            foundElement = getResultParameterType(name);
        }
        if (foundElement == null) {
            foundElement = getChangeIdentifier(name);
        }
        if (foundElement == null) {
            foundElement = getOperation(name);
        }
        if (foundElement == null) {
            foundElement = getExtractorCall(name);
        }
        if (foundElement == null) {
            foundElement = getAnalysisCall(name);
        }
        return foundElement;
    }
    
    /*
     * TODO Think about reasonable getter. This way of doing it is not the best design!
     */
    
    /**
     * Returns the {@link ParameterType} of the type {@link ElementType#ARTIFACT_PARAMETER_TYPE}, which has the given
     * name.
     *  
     * @param name the name of the artifact parameter type, which should be returned
     * @return the {@link ParameterType} of the type {@link ElementType#ARTIFACT_PARAMETER_TYPE} with the given name or
     *         <code>null</code>, if no such element is registered
     */
    public ParameterType getArtifactParameterType(String name) {
        ParameterType artifactParameterType = null;
        List<ParameterType> availableArtifactParameterTypes = artifactParameterTypes.get(name);
        if (availableArtifactParameterTypes != null && !availableArtifactParameterTypes.isEmpty()) {
            artifactParameterType = availableArtifactParameterTypes.get(0);
        }
        return artifactParameterType;
    }
    
    /**
     * Returns the {@link ParameterType} of the type {@link ElementType#FRAGMENT_PARAMETER_TYPE}, which has the given
     * name.
     *  
     * @param name the name of the fragment parameter type, which should be returned
     * @return the {@link ParameterType} of the type {@link ElementType#FRAGMENT_PARAMETER_TYPE} with the given name or
     *         <code>null</code>, if no such element is registered
     */
    public ParameterType getFragmentParameterType(String name) {
        ParameterType fragmentParameterType = null;
        List<ParameterType> availableFragmentParameterTypes = fragmentParameterTypes.get(name);
        if (availableFragmentParameterTypes != null && !availableFragmentParameterTypes.isEmpty()) {
            fragmentParameterType = availableFragmentParameterTypes.get(0);
        }
        return fragmentParameterType;
    }
    
    /**
     * Returns the {@link ParameterType} of the type {@link ElementType#RESULT_PARAMETER_TYPE}, which has the given
     * name.
     *  
     * @param name the name of the result parameter type, which should be returned
     * @return the {@link ParameterType} of the type {@link ElementType#RESULT_PARAMETER_TYPE} with the given name or
     *         <code>null</code>, if no such element is registered
     */
    public ParameterType getResultParameterType(String name) {
        ParameterType resultParameterType = null;
        List<ParameterType> availableResultParameterTypes = resultParameterTypes.get(name);
        if (availableResultParameterTypes != null && !availableResultParameterTypes.isEmpty()) {
            resultParameterType = availableResultParameterTypes.get(0);
        }
        return resultParameterType;
    }
    
    /**
     * Returns the {@link ChangeIdentifier}, which has the given name.
     *  
     * @param name the name of the change identifier, which should be returned
     * @return the {@link ChangeIdentifier} with the given name or <code>null</code>, if no such element is registered
     */
    public ChangeIdentifier getChangeIdentifier(String name) {
        ChangeIdentifier changeIdentifier = null;
        List<ChangeIdentifier> availableChangeIdentifiers = changeIdentifiers.get(name);
        if (availableChangeIdentifiers != null && !availableChangeIdentifiers.isEmpty()) {
            changeIdentifier = availableChangeIdentifiers.get(0);
        }
        return changeIdentifier;
    }
    
    /**
     * Returns the {@link Call} of the type {@link ElementType#OPERATION}, which has the given name.
     *  
     * @param name the name of the operation, which should be returned
     * @return the {@link Call} of the type {@link ElementType#OPERATION} with the given name or <code>null</code>, if
     *         no such element is registered
     */
    public Call getOperation(String name) {
        Call operation = null;
        List<Call> availableCalls = operations.get(name);
        if (availableCalls != null && !availableCalls.isEmpty()) {
            operation = availableCalls.get(0);
        }
        return operation;
    }
    
    /**
     * Returns the {@link Call} of the type {@link ElementType#EXTRACTOR_CALL}, which has the given name.
     *  
     * @param name the name of the extractor call, which should be returned
     * @return the {@link Call} of the type {@link ElementType#EXTRACTOR_CALL} with the given name or <code>null</code>,
     *         if no such element is registered
     */
    public Call getExtractorCall(String name) {
        Call extractorCall = null;
        List<Call> availableCalls = extractorCalls.get(name);
        if (availableCalls != null && !availableCalls.isEmpty()) {
            extractorCall = availableCalls.get(0);
        }
        return extractorCall;
    }
    
    /**
     * Returns the {@link Call} of the type {@link ElementType#ANALYSIS_CALL}, which has the given name.
     *  
     * @param name the name of the analysis call, which should be returned
     * @return the {@link Call} of the type {@link ElementType#ANALYSIS_CALL} with the given name or <code>null</code>,
     *         if no such element is registered
     */
    public Call getAnalysisCall(String name) {
        Call analysisCall = null;
        List<Call> availableCalls = analysisCalls.get(name);
        if (availableCalls != null && !availableCalls.isEmpty()) {
            analysisCall = availableCalls.get(0);
        }
        return analysisCall;
    }
}
