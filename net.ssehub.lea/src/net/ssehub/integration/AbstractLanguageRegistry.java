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
import java.util.Iterator;
import java.util.List;

/**
 * This abstract class provides the attributes and methods for creating the specific, singleton
 * {@link LanguageRegistry}. The purpose of this class is to decouple the management of {@link LanguageElement}s, like
 * the addition of new element or checks regarding duplicated elements, from their retrieval and usage. Hence, this
 * class only contains the core attributes and methods for building the specific {@link LanguageRegistry}, while
 * the specific registry provides additional methods for using it.  
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
     * The {@link HashMap} of all available {@link ParameterType}s of the type 
     * {@link ElementType#ARTIFACT_PARAMETER_TYPE} for the definition of artifacts. Each entry in this map has a
     * particular artifact parameter type name as its key and a {@link List} of {@link ParameterType}s with that name as
     * its value.
     */
    protected HashMap<String, List<ParameterType>> artifactParameterTypes;
    
    /**
     * The {@link HashMap} of all available {@link ParameterType}s of the type 
     * {@link ElementType#FRAGMENT_PARAMETER_TYPE} for the definition of fragments. Each entry in this map has a
     * particular fragment parameter type name as its key and a {@link List} of {@link ParameterType}s with that name as
     * its value.
     */
    protected HashMap<String, List<ParameterType>> fragmentParameterTypes;
    
    /**
     * The {@link HashMap} of all available {@link ParameterType}s of the type {@link ElementType#RESULT_PARAMETER_TYPE}
     * for the definition of results. Each entry in this map has a particular result parameter type name as its key and
     * a {@link List} of {@link ParameterType}s with that name as its value.
     */
    protected HashMap<String, List<ParameterType>> resultParameterTypes;
    
    /**
     * The {@link HashMap} of all available {@link ChangeIdentifier}s for their assignment to artifacts or fragments 
     * (depending on the particular identifier). Each entry in this map has a particular change identifier name as its
     * key and a {@link List} of {@link ChangeIdentifier}s with that name as its value.
     */
    protected HashMap<String, List<ChangeIdentifier>> changeIdentifiers;
    
    /**
     * The {@link HashMap} of all available {@link Call}s of the type {@link ElementType#OPERATION} for the definition
     * of general operations. Each entry in this map has a particular operation (call) name as its key and a
     * {@link List} of {@link Call}s with that name as its value.
     */
    protected HashMap<String, List<Call>> operations;
    
    /**
     * The {@link HashMap} of all available {@link Call}s of the type {@link ElementType#EXTRACTOR_CALL} for the
     * definition of fragment extractions from artifacts. Each entry in this map has a particular extractor (call) name
     * as its key and a {@link List} of {@link Call}s with that name as its value.
     */
    protected HashMap<String, List<Call>> extractorCalls;
    
    /**
     * The {@link HashMap} of all available {@link Call}s of the type {@link ElementType#ANALYSIS_CALL} for the
     * definition of analysis results based on fragments. Each entry in this map has a particular analysis (call) name
     * as its key and a {@link List} of {@link Call}s with that name as its value.
     */
    protected HashMap<String, List<Call>> analysisCalls;
    
    /**
     * The {@link List} of {@link ChangeIdentifier}s for which {@link #isValid(ChangeIdentifier)} returned
     * <code>false</code> during their addition in {@link #addChangeIdentifier(ChangeIdentifier)}. Each element in this
     * list will be re-validated, if a new parameter type is added to this registry.
     * 
     *  @see #reValidateChangeIdentifiers()
     */
    private List<ChangeIdentifier> cachedChangeIdentifiers;
    
    /**
     * The {@link List} of {@link Call}s for which {@link #isValid(Call)} returned <code>false</code> during their 
     * addition in {@link #addCall(Call, HashMap)}. Each element in this list will be re-validated, if a new parameter
     * type is added to this registry.
     * 
     * @see #reValidateCalls()
     */
    private List<Call> cachedCalls;
    
    /**
     * Constructs a new {@link AbstractLanguageRegistry} instance.
     */
    protected AbstractLanguageRegistry() {
        languageElementCounter = 0;
        artifactParameterTypes = new HashMap<String, List<ParameterType>>();
        fragmentParameterTypes = new HashMap<String, List<ParameterType>>();
        resultParameterTypes = new HashMap<String, List<ParameterType>>();
        changeIdentifiers = new HashMap<String, List<ChangeIdentifier>>();
        operations = new HashMap<String, List<Call>>();
        extractorCalls = new HashMap<String, List<Call>>();
        analysisCalls = new HashMap<String, List<Call>>();
        cachedChangeIdentifiers = new ArrayList<ChangeIdentifier>();
        cachedCalls = new ArrayList<Call>();
    }
    
    /**
     * Adds the {@link LanguageElement}s in the given {@link List} to this {@link AbstractLanguageRegistry}. The
     * individual elements will be stored in separate {@link HashMap}s depending on their respective type. However, if
     * an element does not pass the validation, e.g., due to references to missing language elements, it will be cached
     * only. If the missing language elements are added, the cached element becomes available in this registry.
     * 
     * @param newElements the {@link List} of {@link LanguageElement}s to add to this {@link AbstractLanguageRegistry};
     *        should never be <code>null</code>, but may be <i>empty</i>
     * @return a {@link List} of {@link LanguageElement}s that were not added to this registry because of already
     *         existing duplicates; never <code>null</code>, but may be <i>empty</i>, if all given elements were added
     *         successfully, which includes their caching
     * @implNote Repetitive removal and addition of hash map elements is resource consuming. However, as these actions
     *           are performed at start-up only, the performance gain when retrieving and accessing language elements at
     *           runtime is worth doing it here like this.
     */
    public List<LanguageElement> addLanguageElements(List<LanguageElement> newElements) {
        List<LanguageElement> rejectedElements = new ArrayList<LanguageElement>();
        boolean performRevalidation = false;
        for (LanguageElement newElement : newElements) {
            switch(newElement.getElementType()) {
            case ARTIFACT_PARAMETER_TYPE:
                if (!addParameterType((ParameterType) newElement, artifactParameterTypes)) {
                    rejectedElements.add(newElement);
                } else {
                    performRevalidation = true; // New parameter types may enable the usage of cached language elements
                }
                break;
            case FRAGMENT_PARAMETER_TYPE:
                if (!addParameterType((ParameterType) newElement, fragmentParameterTypes)) {
                    rejectedElements.add(newElement);
                } else {
                    performRevalidation = true; // New parameter types may enable the usage of cached language elements
                }
                break;
            case RESULT_PARAMETER_TYPE:
                if (!addParameterType((ParameterType) newElement, resultParameterTypes)) {
                    rejectedElements.add(newElement);
                } else {
                    performRevalidation = true; // New parameter types may enable the usage of cached language elements
                }
                break;
            case CHANGE_IDENTIFIER:
                if (!addChangeIdentifier((ChangeIdentifier) newElement)) {
                    rejectedElements.add(newElement);
                }
                break;
            case OPERATION:
                if (!addCall((Call) newElement, operations)) {
                    rejectedElements.add(newElement);
                }
                break;
            case EXTRACTOR_CALL:
                if (!addCall((Call) newElement, extractorCalls)) {
                    rejectedElements.add(newElement);
                }
                break;
            case ANALYSIS_CALL:
                if (!addCall((Call) newElement, analysisCalls)) {
                    rejectedElements.add(newElement);
                }
                break;
            default:
                // Should never be reached!
                break;
            }
        }
        /*
         * If new parameter types were added, we can re-validate the cached language elements, which could not be added
         * in previous calls of this method due to their references to unknown parameter types.
         */
        if (performRevalidation) {
            revalidateCachedLanguageElements();
        }
        return rejectedElements;
    }
    
    /**
     * Adds the given {@link ParameterType} to the given {@link HashMap} by calling
     * {@link #addParameterTypeUnchecked(ParameterType, HashMap)}, if the map does not already contain such a parameter.
     * The caller of this method must ensure that the {@link ElementType} of the given {@link ParameterType} and the
     * particular {@link HashMap} match as follows:
     * <ul>
     * <li>{@link ElementType#ARTIFACT_PARAMETER_TYPE} requires {@link #artifactParameterTypes}</li>
     * <li>{@link ElementType#FRAGMENT_PARAMETER_TYPE} requires {@link #fragmentParameterTypes}</li>
     * <li>{@link ElementType#RESULT_PARAMETER_TYPE} requires {@link #resultParameterTypes}</li>
     * </ul>
     * This method does not check the above matches.
     * 
     * @param newParameterType the {@link ParameterType} to add to the given {@link HashMap}; should never be
     *        <code>null</code>
     * @param parameterMap the {@link HashMap} to which the given {@link ParameterType} should be added; should never be
     *        <code>null</code>
     * @return <code>true</code>, if the given {@link ParameterType} was added to the given {@link HashMap};
     *         <code>false</code> otherwise, e.g., if a duplicate of the given element is already available
     * @see #isDuplicate(ParameterType)
     */
    private boolean addParameterType(ParameterType newParameterType,
            HashMap<String, List<ParameterType>> parameterMap) {
        boolean parameterAdded = false;
        if (!isDuplicate(newParameterType)) {
            addParameterTypeUnchecked(newParameterType, parameterMap);
            parameterAdded = true;
        }
        return parameterAdded;
    }
    
    /**
     * Adds the given {@link ParameterType} to the given {@link HashMap} without any further checks. If checks are
     * required, use {@link #addParameterType(ParameterType, HashMap)}.
     * 
     * @param newParameterType the {@link ParameterType} to add to the given {@link HashMap}; should never be
     *        <code>null</code>
     * @param parameterMap the {@link HashMap} to which the given {@link ParameterType} should be added; should never be
     *        <code>null</code>
     */
    private void addParameterTypeUnchecked(ParameterType newParameterType,
            HashMap<String, List<ParameterType>> parameterMap) {
        String parameterTypeName = newParameterType.getName();
        List<ParameterType> availableParameterTypes = parameterMap.remove(parameterTypeName);
        if (availableParameterTypes == null) {
            availableParameterTypes = new ArrayList<ParameterType>();
        }
        availableParameterTypes.add(newParameterType);            
        parameterMap.put(parameterTypeName, availableParameterTypes);
        languageElementCounter++;
    }
    
    /**
     * Checks whether the given {@link ParameterType} is a duplicate of another {@link ParameterType} in the 
     * {@link #artifactParameterTypes}, the {@link #fragmentParameterTypes}, or the {@link #resultParameterTypes}.
     *  
     * @param parameterType the {@link ParameterType} for which a duplicate should be found; should never be
     *        <code>null</code>
     * @return <code>true</code>, if the given {@link ParameterType} is a duplicate of one of the available types in
     *         this registry; <code>false</code> otherwise
     * @see #containsDuplicate(HashMap, ParameterType)
     */
    private boolean isDuplicate(ParameterType parameterType) {
        boolean isDuplicate = false;
        if (containsDuplicate(artifactParameterTypes, parameterType) 
                || containsDuplicate(fragmentParameterTypes, parameterType) 
                || containsDuplicate(resultParameterTypes, parameterType)) {
            isDuplicate = true;
        }
        return isDuplicate;
    }
    
    /**
     * Checks whether the given {@link HashMap} contains a {@link ParameterType} that equals the given
     * {@link ParameterType}. In detail, it first searches for an entry in that map, where the key equals the name of
     * the given type, and, second, checks each type of the corresponding {@link List} (the value of the entry), if it
     * equals the given type.
     * 
     * @param parameterTypeMap the {@link HashMap} in which will be searched for a {@link ParameterType}, which equals
     *        the given {@link ParameterType}; should never be <code>null</code>, but may be <i>empty</i> 
     * @param parameterType the {@link ParameterType} for which an equal {@link ParameterType} should be found in the
     *        given map; should never be <code>null</code>
     * @return <code>true</code>, if the given {@link HashMap} contains a {@link ParameterType} that equals the given
     *         {@link ParameterType}; <code>false</code> otherwise
     * @see ParameterType#equals(LanguageElement)
     */
    private boolean containsDuplicate(HashMap<String, List<ParameterType>> parameterTypeMap,
            ParameterType parameterType) {
        boolean duplicateFound = false;
        List<ParameterType> availableParameterTypes = parameterTypeMap.get(parameterType.getName());
        if (availableParameterTypes != null && !availableParameterTypes.isEmpty()) {
            int parameterTypeCounter = 0;
            while (!duplicateFound && parameterTypeCounter < availableParameterTypes.size()) {
                duplicateFound = parameterType.equals(availableParameterTypes.get(parameterTypeCounter));
                parameterTypeCounter++;
            }
        }
        return duplicateFound;
    }
    
    /**
     * Adds the given {@link ChangeIdentifier} to the {@link #changeIdentifiers} by calling 
     * {@link #addChangeIdentifierUnchecked(ChangeIdentifier)}, if the map does not already contain such an identifier
     * and that change identifier is valid. In case of an invalid change identifier, it is added to the 
     * {@link #cachedChangeIdentifiers} for re-validation, if new {@link ParameterType}s are added.
     * 
     * @param newChangeIdentifier the {@link ChangeIdentifier} to add to the {@link #changeIdentifiers}; should never be
     *        <code>null</code>
     * @return <code>true</code>, if the given {@link ChangeIdentifier} was added to the {@link #changeIdentifiers} or
     *         cached as part of the {@link #cachedChangeIdentifiers}; <code>false</code> otherwise, e.g., if a
     *         duplicate of the given element is already available
     * @see #containsDuplicate(List, ChangeIdentifier)
     * @see #isValid(ChangeIdentifier)
     */
    private boolean addChangeIdentifier(ChangeIdentifier newChangeIdentifier) {
        boolean changeIdentifierAdded = false;
        String changeIdentifierName = newChangeIdentifier.getName();
        List<ChangeIdentifier> availableChangeIdentifiers = changeIdentifiers.get(changeIdentifierName);
        if (availableChangeIdentifiers == null
                || availableChangeIdentifiers.isEmpty()
                || (!containsDuplicate(availableChangeIdentifiers, newChangeIdentifier) 
                        && !containsDuplicate(cachedChangeIdentifiers, newChangeIdentifier))) {
            if (isValid(newChangeIdentifier)) {
                addChangeIdentifierUnchecked(newChangeIdentifier);
            } else {
                cachedChangeIdentifiers.add(newChangeIdentifier);
            }
            changeIdentifierAdded = true;
        }
        return changeIdentifierAdded;
    }
    
    /**
     * Adds the given {@link ChangeIdentifier} to the {@link #changeIdentifiers} without any further checks. If checks
     * are required, use {@link #addChangeIdentifier(ChangeIdentifier)}.
     * 
     * @param newChangeIdentifier the {@link ChangeIdentifier} to add to the {@link #changeIdentifiers}; should never be
     *        <code>null</code>
     */
    private void addChangeIdentifierUnchecked(ChangeIdentifier newChangeIdentifier) {
        String changeIdentifierName = newChangeIdentifier.getName();
        List<ChangeIdentifier> availableChangeIdentifiers = changeIdentifiers.remove(changeIdentifierName);
        if (availableChangeIdentifiers == null) {
            availableChangeIdentifiers = new ArrayList<ChangeIdentifier>();
        }
        availableChangeIdentifiers.add(newChangeIdentifier);            
        changeIdentifiers.put(changeIdentifierName, availableChangeIdentifiers);
        languageElementCounter++;
    }
    
    /**
     * Checks whether the given {@link List} of {@link ChangeIdentifier}s contains a {@link ChangeIdentifier} that 
     * equals the given {@link ChangeIdentifier}.
     * 
     * @param changeIdentifierList the {@link List} of {@link ChangeIdentifier}s in which will be searched for a
     *        {@link ChangeIdentifier}, which is equal to the given {@link ChangeIdentifier}; should never be
     *        <code>null</code>, but may be <i>empty</i>
     * @param changeIdentifier the {@link ChangeIdentifier} for which an equal {@link ChangeIdentifier} should be found
     *        in the given list; should never be <code>null</code>
     * @return <code>true</code>, if the given list of {@link ChangeIdentifier}s contains a {@link ChangeIdentifier}
     *         that equals the given {@link ChangeIdentifier}; <code>false</code> otherwise
     * @see ChangeIdentifier#equals(LanguageElement)
     */
    private boolean containsDuplicate(List<ChangeIdentifier> changeIdentifierList, ChangeIdentifier changeIdentifier) {
        boolean duplicateFound = false;
        int changeIdentifierCounter = 0;
        while (!duplicateFound && changeIdentifierCounter < changeIdentifierList.size()) {
            duplicateFound = changeIdentifier.equals(changeIdentifierList.get(changeIdentifierCounter));
            changeIdentifierCounter++;
        }
        return duplicateFound;
    }
    
    /**
     * Adds the given {@link Call} to the given {@link HashMap} by calling {@link #addCallUnchecked(Call, HashMap)},
     * if the map does not already contain such a call and that call is valid. In case of an invalid call, it is added
     * to the {@link #cachedCalls} for re-validation, if new {@link ParameterType}s are added. The caller of this 
     * method must ensure that the {@link ElementType} of the given {@link Call} and the particular {@link HashMap}
     * match as follows:
     * <ul>
     * <li>{@link ElementType#OPERATION} requires {@link #operations}</li>
     * <li>{@link ElementType#EXTRACTOR_CALL} requires {@link #extractorCalls}</li>
     * <li>{@link ElementType#ANALYSIS_CALL} requires {@link #analysisCalls}</li>
     * </ul>
     * This method does not check the above matches.
     * 
     * @param newCall the {@link Call} to add to the given {@link HashMap}; should never be <code>null</code>
     * @param callMap the {@link HashMap} to which the given {@link Call} should be added; should never be
     *        <code>null</code>, but may be <i>empty</i>
     * @return <code>true</code>, if the given {@link Call} was added to the given {@link HashMap} or cached as part of
     *         the {@link #cachedCalls}; <code>false</code> otherwise, e.g., if a duplicate of the given element is
     *         already available
     * @see #isDuplicate(Call)
     * @see #isValid(Call)
     */
    private boolean addCall(Call newCall, HashMap<String, List<Call>> callMap) {
        boolean callAdded = false;
        if (!isDuplicate(newCall)) {
            if (isValid(newCall)) {                
                addCallUnchecked(newCall, callMap);
            } else {
                cachedCalls.add(newCall);
            }
            callAdded = true;
        }
        return callAdded;
    }
    
    /**
     * Adds the given {@link Call} to the given {@link HashMap} without any further checks. If checks are required, use
     * {@link #addCall(Call, HashMap)}.
     * 
     * @param newCall the {@link Call} to add to the given {@link HashMap}; should never be <code>null</code>
     * @param callMap the {@link HashMap} to which the given {@link Call} should be added; should never be
     *        <code>null</code>
     */
    private void addCallUnchecked(Call newCall, HashMap<String, List<Call>> callMap) {
        String callName = newCall.getName();
        List<Call> availableCalls = callMap.remove(callName);
        if (availableCalls == null) {
            availableCalls = new ArrayList<Call>();
        }
        availableCalls.add(newCall);            
        callMap.put(callName, availableCalls);        
        languageElementCounter++;
    }
    
    /**
     * Checks whether the given {@link Call} is a duplicate of another {@link Call} in the {@link #operations}, the
     * {@link #extractorCalls}, the {@link #analysisCalls}, or the {@link #cachedCalls}.
     *  
     * @param call the {@link Call} for which a duplicate should be found; should never be <code>null</code>
     * @return <code>true</code>, if the given {@link Call} is a duplicate of one of the available calls in this
     *         registry; <code>false</code> otherwise
     * @see #containsDuplicate(HashMap, Call)
     * @see #containsDuplicate(List, Call)
     */
    private boolean isDuplicate(Call call) {
        boolean isDuplicate = false;
        if (containsDuplicate(operations, call) 
                || containsDuplicate(extractorCalls, call) 
                || containsDuplicate(analysisCalls, call)
                || containsDuplicate(cachedCalls, call)) {
            isDuplicate = true;
        }
        return isDuplicate;
    }
    
    /**
     * Checks whether the given {@link HashMap} contains a {@link Call} that is a duplicate of the given {@link Call}.
     * In detail, it first searches for an entry in that map, where the key equals the name of the given call, and,
     * second, checks each call of the corresponding {@link List} (the value of the entry), if it is a duplicate of the
     * given call.
     * 
     * @param callMap the {@link HashMap} in which will be searched for a duplicate of the given {@link Call}; should
     *        never be <code>null</code>, but may be <i>empty</i> 
     * @param call the {@link Call} for which a duplicate should be found in the given map; should never be
     *        <code>null</code>
     * @return <code>true</code>, if the given {@link HashMap} contains a {@link Call} that is a duplicate of the given
     *         {@link Call}; <code>false</code> otherwise
     * @see #containsDuplicate(List, Call)
     */
    private boolean containsDuplicate(HashMap<String, List<Call>> callMap, Call call) {
        return containsDuplicate(callMap.get(call.getName()), call);
    }
    
    /**
     * Checks whether the given {@link List} contains a {@link Call} that (partially) equals the given {@link Call}.
     * 
     * @param callList the {@link List} in which will be searched for a {@link Call} that (partially) equals the given
     *        {@link Call}
     * @param call the {@link Call} for which a (partially) equal {@link Call} should be found in the given list; should
     *        never be <code>null</code>
     * @return <code>true</code>, if the given {@link List} contains a {@link Call} that (partially) equals the given
     *         {@link Call}; <code>false</code> otherwise
     * @see Call#equalsIgnoreType(LanguageElement)
     */
    private boolean containsDuplicate(List<Call> callList, Call call) {
        boolean duplicateFound = false;
        if (callList != null && !callList.isEmpty()) {
            int callCounter = 0;
            while (!duplicateFound && callCounter < callList.size()) {
                duplicateFound = call.equalsIgnoreType(callList.get(callCounter));
                callCounter++;
            }
        }
        return duplicateFound;
    }
    
    /**
     * Checks whether each element in {@link ChangeIdentifier#getAssignableElements()} of the given
     * {@link ChangeIdentifier} represents a {@link ParameterType} available in this registry.
     * 
     * @param changeIdentifier the {@link ChangeIdentifier}, which should be validated regarding the presence of its
     *        assignable elements in this registry; should never be <code>null</code>
     * @return <code>true</code>, if all assignable elements are available; <code>false</code> otherwise
     * @see #areParameterTypesAvailable(String[])
     */
    private boolean isValid(ChangeIdentifier changeIdentifier) {
        return areParameterTypesAvailable(changeIdentifier.getAssignableElements());
    }
    
    /**
     * Checks whether the return type retrieved by {@link Call#getReturnType()} and each element in
     * {@link Call#getParameters()} of the given {@link Call} represents a {@link ParameterType} available in this
     * registry.
     * 
     * @param call the {@link Call}, which should be validated regarding the presence of its return type and parameters
     *        in this registry; should never be <code>null</code>
     * @return <code>true</code>, if the return type and the parameters are available; <code>false</code> otherwise
     * @see #isParameterTypeAvailable(String)
     * @see #areParameterTypesAvailable(String[])
     */
    private boolean isValid(Call call) {
        return isParameterTypeAvailable(call.getReturnType())
                && areParameterTypesAvailable(call.getParameters());
    }
    
    /**
     * Checks for each {@link String} in the given array whether a {@link ParameterType} with the same name is available
     * in this registry.
     * 
     * @param searchNames the array of {@link String}s for which the {@link ParameterType}s with the same names should
     *        be found; should never be <code>null</code>, but may be <i>empty</i>
     * @return <code>true</code>, if for each {@link String} in the given array a {@link ParameterType} with the same
     *         name is available; <code>false</code> otherwise
     * @see #isParameterTypeAvailable(String)
     */
    private boolean areParameterTypesAvailable(String[] searchNames) {
        boolean areParameterTypesAvailable = true;
        int searchNamesCounter = 0;
        while (areParameterTypesAvailable && searchNamesCounter < searchNames.length) {
            areParameterTypesAvailable = isParameterTypeAvailable(searchNames[searchNamesCounter]);
            searchNamesCounter++;
        }
        return areParameterTypesAvailable;
    }
    
    /**
     * Checks whether a {@link ParameterType} with the given search name is available in either the
     * {@link #artifactParameterTypes}, the {@link #fragmentParameterTypes}, or the {@link #resultParameterTypes}.
     *  
     * @param searchName the {@link String} defining the name of a {@link ParameterType} that should be available;
     *        should never be <code>null</code>, but may be <i>empty</i> or <i>blank</i>
     * @return <code>true</code>, if a {@link ParameterType} with the given search name is available; <code>false</code>
     *         otherwise
     */
    private boolean isParameterTypeAvailable(String searchName) {
        return artifactParameterTypes.containsKey(searchName)
                || fragmentParameterTypes.containsKey(searchName)
                || resultParameterTypes.containsKey(searchName);
    }
    
    /**
     * Re-validates each element in the {@link #cachedChangeIdentifiers} and the {@link #cachedCalls}. If an element is
     * now valid, it is added to its target {@link HashMap} and removed from the respective cache.
     * 
     * @see #isValid(ChangeIdentifier)
     * @see #addChangeIdentifierUnchecked(ChangeIdentifier)
     * @see #isValid(Call)
     * @see #addCall(Call, HashMap)
     */
    private void revalidateCachedLanguageElements() {
        /*
         * Note: duplicates are filtered out during addition, which also considers the cached elements. Hence, there is
         * no need for such a check here (again) as no duplicates of cached elements exist in this registry.
         */
        
        // Re-validate cached change identifiers
        Iterator<ChangeIdentifier> cachedChangeIdentifiersIterator = cachedChangeIdentifiers.iterator();
        ChangeIdentifier cachedChangeIdentifier;
        while (cachedChangeIdentifiersIterator.hasNext()) {
            cachedChangeIdentifier = cachedChangeIdentifiersIterator.next();
            if (isValid(cachedChangeIdentifier)) {
                addChangeIdentifierUnchecked(cachedChangeIdentifier);
                cachedChangeIdentifiersIterator.remove();
            }
        }
        // Re-validate cached calls
        Iterator<Call> cachedCallsIterator = cachedCalls.iterator();
        Call cachedCall;
        HashMap<String, List<Call>> callMap;
        while (cachedCallsIterator.hasNext()) {
            cachedCall = cachedCallsIterator.next();
            if (isValid(cachedCall)) {
                switch(cachedCall.getElementType()) {
                case OPERATION:
                    callMap = operations;
                    break;
                case EXTRACTOR_CALL:
                    callMap = extractorCalls;
                    break;
                case ANALYSIS_CALL:
                    callMap = analysisCalls;
                    break;
                default:
                    // Should never be reached
                    callMap = new HashMap<String, List<Call>>();
                    break;
                }
                addCall(cachedCall, callMap);
            }
        }
    }
    
}
