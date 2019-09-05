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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This abstract class provides the attributes and methods for creating the specific, singleton
 * {@link LanguageRegistry}. The purpose of this class is to decouple the management of {@link LanguageElement}s, like
 * the addition of new element or checks regarding duplicated elements, from their retrieval and usage. Hence, this
 * class only contains the core attributes and methods for building the {@link LanguageRegistry}, while the specific
 * registry provides additional methods for using it.  
 * 
 * @author Christian Kroeher
 *
 */
public abstract class AbstractLanguageRegistry {
    
    /**
     * The number of currently registered {@link LanguageElement}s. The initial value of <code>0</code> is set in
     * {@link #AbstractLanguageRegistry()}. It is increased by <code>1</code> for each element added to this registry
     * in {@link #addLanguageElements(List)}. 
     */
    protected static int languageElementCounter;
    
    /**
     * The {@link HashMap} of all available {@link ParameterType}s. Each entry in this map has a particular parameter 
     * type (simple) name as its key and a {@link List} of {@link ParameterType}s with that (simple) name as its value.
     */
    protected HashMap<String, List<ParameterType>> parameterTypes;
    
//    /**
//     * The {@link HashMap} of all available {@link ParameterType}s of the type 
//     * {@link ElementType#ARTIFACT_PARAMETER_TYPE} for the definition of artifacts. Each entry in this map has a
//     * particular artifact parameter type name as its key and a {@link List} of {@link ParameterType}s with that name
//     * as its value.
//     */
//    protected HashMap<String, List<ParameterType>> artifactParameterTypes;
//    
//    /**
//     * The {@link HashMap} of all available {@link ParameterType}s of the type 
//     * {@link ElementType#FRAGMENT_PARAMETER_TYPE} for the definition of fragments. Each entry in this map has a
//     * particular fragment parameter type name as its key and a {@link List} of {@link ParameterType}s with that name
//     * as its value.
//     */
//    protected HashMap<String, List<ParameterType>> fragmentParameterTypes;
//    
//    /**
//     * The {@link HashMap} of all available {@link ParameterType}s of thetype{@link ElementType#RESULT_PARAMETER_TYPE}
//     * for the definition of results. Each entry in this map has a particular result parameter type name as its key
//     * and a {@link List} of {@link ParameterType}s with that name as its value.
//     */
//    protected HashMap<String, List<ParameterType>> resultParameterTypes;
    
    /**
     * The {@link HashMap} of all available {@link ChangeIdentifier}s for their assignment to artifacts or fragments 
     * (depending on the particular identifier). Each entry in this map has a particular change identifier name as its
     * key and a {@link List} of {@link ChangeIdentifier}s with that name as its value.
     */
    protected HashMap<String, List<ChangeIdentifier>> changeIdentifiers;
    
    /**
     * The {@link HashMap} of all available {@link Call}s. Each entry in this map has a particular operation (call) name
     * as its key and a {@link List} of {@link Call}s with that name as its value.
     */
    protected HashMap<String, List<Call>> calls;
    
//    /**
//     * The {@link HashMap} of all available {@link Call}s of the type {@link ElementType#OPERATION} for the definition
//     * of general operations. Each entry in this map has a particular operation (call) name as its key and a
//     * {@link List} of {@link Call}s with that name as its value.
//     */
//    protected HashMap<String, List<Call>> operations;
//    
//    /**
//     * The {@link HashMap} of all available {@link Call}s of the type {@link ElementType#OPERATION} for the definition
//     * of member operations on parameter type instances. Each entry in this map has a parameter type name as its key
//     * and a {@link List} of {@link Call}s that represent member operations for that parameter type as its value.
//     */
//    protected HashMap<String, List<Call>> memberOperations;
//    
//    /**
//     * The {@link HashMap} of all available {@link Call}s of the type {@link ElementType#EXTRACTOR_CALL} for the
//     * definition of fragment extractions from artifacts. Each entry in this map has a particular extractor (call)
//     * name as its key and a {@link List} of {@link Call}s with that name as its value.
//     */
//    protected HashMap<String, List<Call>> extractorCalls;
//    
//    /**
//     * The {@link HashMap} of all available {@link Call}s of the type {@link ElementType#ANALYSIS_CALL} for the
//     * definition of analysis results based on fragments. Each entry in this map has a particular analysis (call) name
//     * as its key and a {@link List} of {@link Call}s with that name as its value.
//     */
//    protected HashMap<String, List<Call>> analysisCalls;
    
    /**
     * Constructs a new {@link AbstractLanguageRegistry} instance.
     */
    protected AbstractLanguageRegistry() {
        languageElementCounter = 0;
        parameterTypes = new HashMap<String, List<ParameterType>>();
//        artifactParameterTypes = new HashMap<String, List<ParameterType>>();
//        fragmentParameterTypes = new HashMap<String, List<ParameterType>>();
//        resultParameterTypes = new HashMap<String, List<ParameterType>>();
        changeIdentifiers = new HashMap<String, List<ChangeIdentifier>>();
//        operations = new HashMap<String, List<Call>>();
//        memberOperations = new HashMap<String, List<Call>>();
//        extractorCalls = new HashMap<String, List<Call>>();
//        analysisCalls = new HashMap<String, List<Call>>();
        calls = new HashMap<String, List<Call>>();
    }
    
    /**
     * Removes all elements from this registry. The {@link #artifactParameterTypes}, {@link #fragmentParameterTypes},
     * {@link #resultParameterTypes}, {@link #changeIdentifiers}, {@link #operations}, {@link #memberOperations},
     * {@link #extractorCalls}, and {@link #analysisCalls} will be empty after this call returns.
     */
    public void clear() {
        languageElementCounter = 0;
        parameterTypes.clear();
//        artifactParameterTypes.clear();
//        fragmentParameterTypes.clear();
//        resultParameterTypes.clear();
        changeIdentifiers.clear();
//        operations.clear();
//        memberOperations.clear();
//        extractorCalls.clear();
//        analysisCalls.clear();
        calls.clear();
    }
    
    /**
     * TODO.
     * 
     * @param parameterType TODO the {@link ParameterType} to add to this registry as an artifact parameter type
     * @return TODO <code>true</code>, if adding the given {@link ParameterType} to this registry was successful; 
     *         <code>false</code> otherwise
     */
    public boolean addParameterType(ParameterType parameterType) {
        boolean additionSuccessful = false;
        if (parameterType != null && !isDuplicate(parameterType)) {
            String parameterTypeName = parameterType.getName();
            List<ParameterType> availableParameterTypes = parameterTypes.remove(parameterTypeName);
            if (availableParameterTypes == null) {
                availableParameterTypes = new ArrayList<ParameterType>();
            }
            availableParameterTypes.add(parameterType);            
            additionSuccessful = (parameterTypes.put(parameterTypeName, availableParameterTypes) != null);
            languageElementCounter++;
        }
        return additionSuccessful;
    }
    
//    /**
//     * Adds the given {@link ParameterType} as an artifact parameter type to this registry. Hence, the addition is
//     * only successful, if the given @{@link ParameterType} is not <code>null</code>, calling
//     * {@link ParameterType#getElementType()} returns {@link ElementType#ARTIFACT_PARAMETER_TYPE}, and the registry
//     * does not already contain such a {@link ParameterType} (a duplicate of the given one).
//     * 
//     * @param artifactParameterType the {@link ParameterType} to add to this registry as an artifact parameter type
//     * @return <code>true</code>, if adding the given {@link ParameterType} to this registry was successful; 
//     *         <code>false</code> otherwise
//     */
//    public boolean addArtifactParameterType(ParameterType artifactParameterType) {
//        boolean additionSuccessful = false;
//        if (artifactParameterType != null 
//                && artifactParameterType.getElementType() == ElementType.ARTIFACT_PARAMETER_TYPE) {
//            additionSuccessful = addParameterType(artifactParameterType, artifactParameterTypes);
//        }
//        return additionSuccessful;
//    }
//    
//    /**
//     * Adds the given {@link ParameterType} as a fragment parameter type to this registry. Hence, the addition is only
//     * successful, if the given @{@link ParameterType} is not <code>null</code>, calling
//     * {@link ParameterType#getElementType()} returns {@link ElementType#FRAGMENT_PARAMETER_TYPE}, and the registry
//     * does not already contain such a {@link ParameterType} (a duplicate of the given one).
//     * 
//     * @param fragmentParameterType the {@link ParameterType} to add to this registry as a fragment parameter type
//     * @return <code>true</code>, if adding the given {@link ParameterType} to this registry was successful; 
//     *         <code>false</code> otherwise
//     */
//    public boolean addFragmentParameterType(ParameterType fragmentParameterType) {
//        boolean additionSuccessful = false;
//        if (fragmentParameterType != null 
//                && fragmentParameterType.getElementType() == ElementType.FRAGMENT_PARAMETER_TYPE) {
//            additionSuccessful = addParameterType(fragmentParameterType, fragmentParameterTypes);
//        }
//        return additionSuccessful;
//    }
//    
//    /**
//     * Adds the given {@link ParameterType} as a result parameter type to this registry. Hence, the addition is only
//     * successful, if the given @{@link ParameterType} is not <code>null</code>, calling
//     * {@link ParameterType#getElementType()} returns {@link ElementType#RESULT_PARAMETER_TYPE}, and the registry does
//     * not already contain such a {@link ParameterType} (a duplicate of the given one).
//     * 
//     * @param resultParameterType the {@link ParameterType} to add to this registry as a result parameter type
//     * @return <code>true</code>, if adding the given {@link ParameterType} to this registry was successful; 
//     *         <code>false</code> otherwise
//     */
//    public boolean addResultParameterType(ParameterType resultParameterType) {
//        boolean additionSuccessful = false;
//        if (resultParameterType != null 
//                && resultParameterType.getElementType() == ElementType.RESULT_PARAMETER_TYPE) {
//            additionSuccessful = addParameterType(resultParameterType, resultParameterTypes);
//        }
//        return additionSuccessful;
//    }
    
    /**
     * Adds the given {@link ChangeIdentifier} to this registry. The addition is only successful, if the given 
     * {@link ChangeIdentifier} is not <code>null</code>, is completely created 
     * (checked via {@link ChangeIdentifier#isFinal()}), and and the registry does not already contain such a 
     * {@link ChangeIdentifier} (a duplicate of the given one).
     * 
     * @param changeIdentifier the {@link ChangeIdentifier} to add to this registry
     * @return <code>true</code>, if adding the given {@link ChangeIdentifier} to this registry was successful; 
     *         <code>false</code> otherwise
     */
    public boolean addChangeIdentifier(ChangeIdentifier changeIdentifier) {
        boolean additionSuccessful = false;
        if (changeIdentifier != null && changeIdentifier.isFinal()) {
            String changeIdentifierName = changeIdentifier.getName();
            List<ChangeIdentifier> availableChangeIdentifiers = changeIdentifiers.get(changeIdentifierName);
            if (!containsDuplicate(availableChangeIdentifiers, changeIdentifier)) {
                availableChangeIdentifiers = changeIdentifiers.remove(changeIdentifierName);
                if (availableChangeIdentifiers == null) {
                    availableChangeIdentifiers = new ArrayList<ChangeIdentifier>();
                }
                availableChangeIdentifiers.add(changeIdentifier);            
                changeIdentifiers.put(changeIdentifierName, availableChangeIdentifiers);
                additionSuccessful = true;
                languageElementCounter++;
            }
        }
        return additionSuccessful;
    }
    
    /**
     * TODO Adds the given {@link Call} as a (member) operation to this registry. Hence, the addition is only
     * successful, if
     * the given @{@link Call} is not <code>null</code>, is completely created (checked via {@link Call#isFinal()}), 
     * calling {@link Call#getElementType()} returns {@link ElementType#OPERATION}, and the registry does not already
     * contain such a {@link Call} (a duplicate of the given one).
     * 
     * @param call TODO the {@link Call} to add to this registry as a (member) operation
     * @return TODO <code>true</code>, if adding the given {@link Call} to this registry was successful;
     * <code>false</code>
     *         otherwise
     */
    public boolean addCall(Call call) {
        boolean additionSuccessful = false;
        if (call != null && call.isFinal() && !isDuplicate(call)) {
            String callName = call.getName();
            List<Call> availableCalls = calls.remove(callName);
            if (availableCalls == null) {
                availableCalls = new ArrayList<Call>();
            }
            availableCalls.add(call);        
            additionSuccessful = (calls.put(callName, availableCalls) != null);
            languageElementCounter++;
        }
        return additionSuccessful;
    }
    
//    /**
//     * Adds the given {@link Call} as a (member) operation to this registry. Hence, the addition is only successful,
//     * if the given @{@link Call} is not <code>null</code>, is completely created (checked via 
//     * {@link Call#isFinal()}), calling {@link Call#getElementType()} returns {@link ElementType#OPERATION}, and the
//     * registry does not already contain such a {@link Call} (a duplicate of the given one).
//     * 
//     * @param operationCall the {@link Call} to add to this registry as a (member) operation
//     * @return <code>true</code>, if adding the given {@link Call} to this registry was successful; <code>false</code>
//     *         otherwise
//     */
//    public boolean addOperation(Call operationCall) {
//        boolean additionSuccessful = false;
//        if (operationCall != null && operationCall.isFinal() 
//                && operationCall.getElementType() == ElementType.OPERATION) {
//            HashMap<String, List<Call>> targetOperationMap = operations;
//            if (operationCall.isMemberOperation()) {
//                targetOperationMap = memberOperations;
//            }
//            additionSuccessful = addCall(operationCall, targetOperationMap);
//        }
//        return additionSuccessful;
//    }
//    
//    /**
//     * Adds the given {@link Call} as an extractor call to this registry. Hence, the addition is only successful, if 
//     * the given @{@link Call} is not <code>null</code>, is completely created (checked via {@link Call#isFinal()}),
//     * calling {@link Call#getElementType()} returns {@link ElementType#EXTRACTOR_CALL}, and the registry does not
//     * already contain such a {@link Call} (a duplicate of the given one).
//     * 
//     * @param extractorCall the {@link Call} to add to this registry as an extractor call
//     * @return <code>true</code>, if adding the given {@link Call} to this registry was successful; <code>false</code>
//     *         otherwise
//     */
//    public boolean addExtractorCall(Call extractorCall) {
//        boolean additionSuccessful = false;
//        if (extractorCall != null && extractorCall.isFinal() 
//                && extractorCall.getElementType() == ElementType.EXTRACTOR_CALL) {
//            additionSuccessful = addCall(extractorCall, extractorCalls);
//        }
//        return additionSuccessful;
//    }
//    
//    /**
//     * Adds the given {@link Call} as an analysis call to this registry. Hence, the addition is only successful, if 
//     * the given @{@link Call} is not <code>null</code>, is completely created (checked via {@link Call#isFinal()}),
//     * calling {@link Call#getElementType()} returns {@link ElementType#ANALYSIS_CALL}, and the registry does not 
//     * already contain such a {@link Call} (a duplicate of the given one).
//     * 
//     * @param analysisCall the {@link Call} to add to this registry as an analysis call
//     * @return <code>true</code>, if adding the given {@link Call} to this registry was successful; <code>false</code>
//     *         otherwise
//     */
//    public boolean addAnalysisCall(Call analysisCall) {
//        boolean additionSuccessful = false;
//        if (analysisCall != null && analysisCall.isFinal() 
//                && analysisCall.getElementType() == ElementType.ANALYSIS_CALL) {
//            additionSuccessful = addCall(analysisCall, analysisCalls);
//        }
//        return additionSuccessful;
//    }
    
//    /**
//     * Adds the given {@link ParameterType} to the given {@link HashMap}, if the map does not already contain such a 
//     * parameter. The caller of this method must ensure that the {@link ElementType} of the given{@link ParameterType}
//     * and the particular {@link HashMap} match as follows:
//     * <ul>
//     * <li>{@link ElementType#ARTIFACT_PARAMETER_TYPE} requires {@link #artifactParameterTypes}</li>
//     * <li>{@link ElementType#FRAGMENT_PARAMETER_TYPE} requires {@link #fragmentParameterTypes}</li>
//     * <li>{@link ElementType#RESULT_PARAMETER_TYPE} requires {@link #resultParameterTypes}</li>
//     * </ul>
//     * This method does not check the above matches.
//     * 
//     * @param newParameterType the {@link ParameterType} to add to the given {@link HashMap}; should never be
//     *        <code>null</code>
//     * @param parameterMap the {@link HashMap} to which the given {@link ParameterType} should be added; should never
//     *        be <code>null</code>
//     * @return <code>true</code>, if the given {@link ParameterType} was added to the given {@link HashMap};
//     *         <code>false</code> otherwise, e.g., if a duplicate of the given element is already available
//     * @see #isDuplicate(ParameterType)
//     */
//    private boolean addParameterType(ParameterType newParameterType,
//            HashMap<String, List<ParameterType>> parameterMap) {
//        boolean parameterAdded = false;
//        if (!isDuplicate(newParameterType)) {
//            String parameterTypeName = newParameterType.getName();
//            List<ParameterType> availableParameterTypes = parameterMap.remove(parameterTypeName);
//            if (availableParameterTypes == null) {
//                availableParameterTypes = new ArrayList<ParameterType>();
//            }
//            availableParameterTypes.add(newParameterType);            
//            parameterMap.put(parameterTypeName, availableParameterTypes);
//            parameterAdded = true;
//            languageElementCounter++;
//        }
//        return parameterAdded;
//    }
    
    /**
     * TODO.
     *  
     * @param parameterType the {@link ParameterType} for which a duplicate should be found; should never be
     *        <code>null</code>
     * @return <code>true</code>, if the given {@link ParameterType} is a duplicate of one of the available types in
     *         this registry; <code>false</code> otherwise
     * @see #containsDuplicate(HashMap, ParameterType)
     */
    private boolean isDuplicate(ParameterType parameterType) {
        boolean isDuplicate = false;
        List<ParameterType> availableParameterTypes = parameterTypes.get(parameterType.getName());
        if (availableParameterTypes != null && !availableParameterTypes.isEmpty()) {
            int parameterTypeCounter = 0;
            while (!isDuplicate && parameterTypeCounter < availableParameterTypes.size()) {
                isDuplicate = parameterType.equals(availableParameterTypes.get(parameterTypeCounter));
                parameterTypeCounter++;
            }
        }
        return isDuplicate;
    }
    
//    /**
//     * Checks whether the given {@link ParameterType} is a duplicate of another {@link ParameterType} in the 
//     * {@link #artifactParameterTypes}, the {@link #fragmentParameterTypes}, or the {@link #resultParameterTypes}.
//     *  
//     * @param parameterType the {@link ParameterType} for which a duplicate should be found; should never be
//     *        <code>null</code>
//     * @return <code>true</code>, if the given {@link ParameterType} is a duplicate of one of the available types in
//     *         this registry; <code>false</code> otherwise
//     * @see #containsDuplicate(HashMap, ParameterType)
//     */
//    private boolean isDuplicate(ParameterType parameterType) {
//        boolean isDuplicate = false;
//        if (containsDuplicate(artifactParameterTypes, parameterType) 
//                || containsDuplicate(fragmentParameterTypes, parameterType) 
//                || containsDuplicate(resultParameterTypes, parameterType)) {
//            isDuplicate = true;
//        }
//        return isDuplicate;
//    }
    
//    /**
//     * Checks whether the given {@link HashMap} contains a {@link ParameterType} that equals the given
//     * {@link ParameterType}. In detail, it first searches for an entry in that map, where the key equals the name of
//     * the given type, and, second, checks each type of the corresponding {@link List} (the value of the entry), if it
//     * equals the given type.
//     * 
//     * @param parameterTypeMap the {@link HashMap} in which will be searched for a {@link ParameterType}, which equals
//     *        the given {@link ParameterType}; should never be <code>null</code>, but may be <i>empty</i> 
//     * @param parameterType the {@link ParameterType} for which an equal {@link ParameterType} should be found in the
//     *        given map; should never be <code>null</code>
//     * @return <code>true</code>, if the given {@link HashMap} contains a {@link ParameterType} that equals the given
//     *         {@link ParameterType}; <code>false</code> otherwise
//     * @see ParameterType#equals(LanguageElement)
//     */
//    private boolean containsDuplicate(HashMap<String, List<ParameterType>> parameterTypeMap,
//            ParameterType parameterType) {
//        boolean duplicateFound = false;
//        List<ParameterType> availableParameterTypes = parameterTypeMap.get(parameterType.getName());
//        if (availableParameterTypes != null && !availableParameterTypes.isEmpty()) {
//            int parameterTypeCounter = 0;
//            while (!duplicateFound && parameterTypeCounter < availableParameterTypes.size()) {
//                duplicateFound = parameterType.equals(availableParameterTypes.get(parameterTypeCounter));
//                parameterTypeCounter++;
//            }
//        }
//        return duplicateFound;
//    }
    
    /**
     * Checks whether the given {@link List} of {@link ChangeIdentifier}s contains a {@link ChangeIdentifier} that 
     * equals the given {@link ChangeIdentifier}.
     * 
     * @param changeIdentifierList the {@link List} of {@link ChangeIdentifier}s in which will be searched for a
     *        {@link ChangeIdentifier}, which is equal to the given {@link ChangeIdentifier}
     * @param changeIdentifier the {@link ChangeIdentifier} for which an equal {@link ChangeIdentifier} should be found
     *        in the given list; should never be <code>null</code>
     * @return <code>true</code>, if the given list of {@link ChangeIdentifier}s contains a {@link ChangeIdentifier}
     *         that equals the given {@link ChangeIdentifier}; <code>false</code> otherwise
     * @see ChangeIdentifier#equals(LanguageElement)
     */
    private boolean containsDuplicate(List<ChangeIdentifier> changeIdentifierList, ChangeIdentifier changeIdentifier) {
        boolean duplicateFound = false;
        if (changeIdentifierList != null && !changeIdentifierList.isEmpty()) {            
            int changeIdentifierCounter = 0;
            while (!duplicateFound && changeIdentifierCounter < changeIdentifierList.size()) {
                duplicateFound = changeIdentifier.equals(changeIdentifierList.get(changeIdentifierCounter));
                changeIdentifierCounter++;
            }
        }
        return duplicateFound;
    }
    
//    /**
//     * Adds the given {@link Call} to the given {@link HashMap} by calling {@link #addCallUnchecked(Call, HashMap)},
//     * if this language registry does not already contain such a call and that call is valid. In case of an invalid 
//     * call, it is added to the {@link #cachedCalls} for re-validation, if new {@link ParameterType}s are added. The
//     * caller of this method must ensure that the {@link ElementType} of the given {@link Call} and the particular 
//     * {@link HashMap} match as follows:
//     * <ul>
//     * <li>{@link ElementType#OPERATION} requires {@link #operations}, if {@link Call#isMemberOperation()} returns 
//     *     <code>false</code></li>
//     * <li>{@link ElementType#OPERATION} requires {@link #memberOperations}, if {@link Call#isMemberOperation()}
//     *     returns <code>true</code></li>    
//     * <li>{@link ElementType#OPERATION} requires {@link #operations}</li>
//     * <li>{@link ElementType#EXTRACTOR_CALL} requires {@link #extractorCalls}</li>
//     * <li>{@link ElementType#ANALYSIS_CALL} requires {@link #analysisCalls}</li>
//     * </ul>
//     * This method does not check the above matches.
//     * 
//     * @param newCall the {@link Call} to add to the given {@link HashMap}; should never be <code>null</code>
//     * @param callMap the {@link HashMap} to which the given {@link Call} should be added; should never be
//     *        <code>null</code>, but may be <i>empty</i>
//     * @return <code>true</code>, if the given {@link Call} was added to the given {@link HashMap}; <code>false</code>
//     *         otherwise, e.g., if a duplicate of the given element is already available
//     * @see #isDuplicate(Call)
//     */
//    private boolean addCall(Call newCall, HashMap<String, List<Call>> callMap) {
//        boolean callAdded = false;
//        if (!isDuplicate(newCall)) {
//            // General operations use their name as key
//            String mapKey = newCall.getName();
//            if (newCall.isMemberOperation()) {
//                // Member operations use their parent parameter type as key
//                mapKey = newCall.getParentParameterType().getName();
//            }
//            List<Call> availableCalls = callMap.remove(mapKey);
//            if (availableCalls == null) {
//                availableCalls = new ArrayList<Call>();
//            }
//            availableCalls.add(newCall);            
//            callMap.put(mapKey, availableCalls);        
//            callAdded = true;
//            languageElementCounter++;
//        }
//        return callAdded;
//    }
    
    /**
     * TODO Checks whether the given {@link Call} is a duplicate of another {@link Call} in the:
     * <ul>
     * <li>{@link #memberOperations}, if {@link Call#isMemberOperation()} returns <code>true</code></li>
     * <li>{@link #operations}, the {@link #extractorCalls}, or the {@link #analysisCalls}, if
     *     {@link Call#isMemberOperation()} returns <code>false</code></li>
     * </ul>
     * for the given {@link Call}.
     *  
     * @param call the {@link Call} for which a duplicate should be found; should never be <code>null</code>
     * @return <code>true</code>, if the given {@link Call} is a duplicate of one of the available calls in this
     *         registry; <code>false</code> otherwise
     * @see #containsDuplicate(HashMap, Call)
     */
    private boolean isDuplicate(Call call) {
        boolean isDuplicate = false;
        List<Call> availableCalls = calls.get(call.getName());
        if (availableCalls != null) {            
            int availableCallsCounter = 0;
            while (!isDuplicate && availableCallsCounter < availableCalls.size()) {
                isDuplicate = call.equalsIgnoreType(availableCalls.get(availableCallsCounter));
                availableCallsCounter++;
            }
        }
        return isDuplicate;
    }
    
//    /**
//     * Checks whether the given {@link HashMap} contains a {@link Call} that is a duplicate of the given {@link Call}.
//     * In detail, it first searches for an entry in that map, where the key equals the given search key, and, second,
//     * checks each call of the corresponding {@link List} (the value of the entry), if it is a duplicate of the
//     * given call.
//     * 
//     * @param callMap the {@link HashMap} in which will be searched for a duplicate of the given {@link Call}; should
//     *        never be <code>null</code>, but may be <i>empty</i>
//     * @param searchKey the key, which shall be used to retrieve the {@link List} of the given {@link HashMap}; should
//     *        never be <code>null</code> nor <i>blank</i>
//     * @param call the {@link Call} for which a duplicate should be found in the given map; should never be
//     *        <code>null</code>
//     * @return <code>true</code>, if the given {@link HashMap} contains a {@link Call} that is a duplicate of the
//     *         given {@link Call}; <code>false</code> otherwise
//     * @see #containsDuplicate(List, Call)
//     */
//    private boolean containsDuplicate(HashMap<String, List<Call>> callMap, String searchKey, Call call) {
//        return containsDuplicate(callMap.get(searchKey), call);
//    }
    
//    /**
//     * Checks whether the given {@link List} contains a {@link Call} that (partially) equals the given {@link Call}.
//     * 
//     * @param callList the {@link List} in which will be searched for a {@link Call} that (partially) equals the given
//     *        {@link Call}
//     * @param call the {@link Call} for which a (partially) equal {@link Call} should be found in the given list;
//     *        should never be <code>null</code>
//     * @return <code>true</code>, if the given {@link List} contains a {@link Call} that (partially) equals the given
//     *         {@link Call}; <code>false</code> otherwise
//     * @see Call#equalsIgnoreType(LanguageElement)
//     */
//    private boolean containsDuplicate(List<Call> callList, Call call) {
//        boolean duplicateFound = false;
//        if (callList != null && !callList.isEmpty()) {
//            int callCounter = 0;
//            while (!duplicateFound && callCounter < callList.size()) {
//                duplicateFound = call.equalsIgnoreType(callList.get(callCounter));
//                callCounter++;
//            }
//        }
//        return duplicateFound;
//    }
    
}
