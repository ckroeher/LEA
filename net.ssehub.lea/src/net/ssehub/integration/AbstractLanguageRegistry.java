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
import java.util.Set;

/**
 * This abstract class provides the attributes and methods for creating the specific, singleton
 * {@link LanguageRegistry}. The purpose of this class is to decouple the management of {@link LanguageElement}s, like
 * the addition of new element or checks regarding duplicated elements, from their retrieval and usage. Hence, this
 * class only contains the core attributes and methods for building the {@link LanguageRegistry}, while the specific
 * {@link LanguageRegistry} provides additional methods for using it.  
 * 
 * @author Christian Kroeher
 *
 */
public abstract class AbstractLanguageRegistry {
    
    /**
     * The number of currently registered {@link LanguageElement}s. The initial value of <code>0</code> is set in
     * {@link #AbstractLanguageRegistry()}. It is increased by <code>1</code> for each element added to this registry
     * in {@link #addParameterType(ParameterType)}, {@link #addChangeIdentifier(ChangeIdentifier)}, or 
     * {@link #addCall(Call)}. 
     */
    protected static int languageElementCounter;
    
    /**
     * The {@link HashMap} of all available {@link ParameterType}s. Each entry in this map has a particular parameter 
     * type (simple) name as its key and a {@link List} of {@link ParameterType}s with that (simple) name as its value.
     */
    protected HashMap<String, List<ParameterType>> parameterTypes;
    
    /**
     * The {@link HashMap} of all available {@link ChangeIdentifier}s. Each entry in this map has a particular change
     * identifier (simple) name as its key and a {@link List} of {@link ChangeIdentifier}s with that (simple) name as
     * its value.
     */
    protected HashMap<String, List<ChangeIdentifier>> changeIdentifiers;
    
    /**
     * The {@link HashMap} of all available {@link Call}s. Each entry in this map has a particular call (simple) name
     * as its key and a {@link List} of {@link Call}s with that (simple) name as its value.
     */
    protected HashMap<String, List<Call>> calls;
    
    /**
     * Constructs a new {@link AbstractLanguageRegistry} instance.
     */
    protected AbstractLanguageRegistry() {
        languageElementCounter = 0;
        parameterTypes = new HashMap<String, List<ParameterType>>();
        changeIdentifiers = new HashMap<String, List<ChangeIdentifier>>();
        calls = new HashMap<String, List<Call>>();
    }
    
    /**
     * Removes all elements from this registry. The {@link #parameterTypes}, {@link #changeIdentifiers}, and 
     * {@link #calls} will be empty after this method returns.
     */
    public void clear() {
        languageElementCounter = 0;
        parameterTypes.clear();
        changeIdentifiers.clear();
        calls.clear();
    }
    
    /**
     * Adds the given {@link ParameterType} to this registry. The addition is only successful, if the given 
     * {@link ParameterType} is not <code>null</code> and the registry does not already contain such a 
     * {@link ParameterType} (a duplicate of the given one).
     * 
     * @param parameterType the {@link ParameterType} to add to this registry
     * @return <code>true</code>, if adding the given {@link ParameterType} to this registry was successful; 
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
            parameterTypes.put(parameterTypeName, availableParameterTypes);
            additionSuccessful = parameterTypes.containsKey(parameterTypeName);
            if (additionSuccessful) {                
                languageElementCounter++;
            }
        }
        return additionSuccessful;
    }
    
    /**
     * Adds the given {@link ChangeIdentifier} to this registry. The addition is only successful, if the given 
     * {@link ChangeIdentifier} is not <code>null</code>, is completely created (checked via 
     * {@link ChangeIdentifier#isFinal()}), and the registry does not already contain such a {@link ChangeIdentifier}
     * (a duplicate of the given one).
     * 
     * @param changeIdentifier the {@link ChangeIdentifier} to add to this registry
     * @return <code>true</code>, if adding the given {@link ChangeIdentifier} to this registry was successful; 
     *         <code>false</code> otherwise
     */
    public boolean addChangeIdentifier(ChangeIdentifier changeIdentifier) {
        boolean additionSuccessful = false;
        if (changeIdentifier != null && changeIdentifier.isFinal() && !isDuplicate(changeIdentifier)) {
            String changeIdentifierName = changeIdentifier.getName();
            List<ChangeIdentifier> availableChangeIdentifiers = changeIdentifiers.remove(changeIdentifierName);
            if (availableChangeIdentifiers == null) {
                availableChangeIdentifiers = new ArrayList<ChangeIdentifier>();
            }
            availableChangeIdentifiers.add(changeIdentifier);
            changeIdentifiers.put(changeIdentifierName, availableChangeIdentifiers);
            additionSuccessful = changeIdentifiers.containsKey(changeIdentifierName);
            if (additionSuccessful) {                
                languageElementCounter++;
            }
        }
        return additionSuccessful;
    }
    
    /**
     * Adds the given {@link Call} to this registry. The addition is only successful, if the given {@link Call} is not
     * <code>null</code>, is completely created (checked via {@link Call#isFinal()}), and the registry does not already
     * contain such a {@link Call} (a duplicate of the given one).
     * 
     * @param call the {@link Call} to add to this registry
     * @return <code>true</code>, if adding the given {@link Call} to this registry was successful; <code>false</code>
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
            calls.put(callName, availableCalls);
            additionSuccessful = calls.containsKey(callName);
            if (additionSuccessful) {                
                languageElementCounter++;
            }
        }
        return additionSuccessful;
    }
    
    /**
     * Checks whether the given {@link ParameterType} is a duplicate of another {@link ParameterType} in the 
     * {@link #parameterTypes}.
     *  
     * @param parameterType the {@link ParameterType}, which should be checked for being a duplicate of an already
     *        existing {@link ParameterType} in {@link #parameterTypes}; should never be <code>null</code>
     * @return <code>true</code>, if a duplicate of the given {@link ParameterType} in {@link #parameterTypes} exists;
     *         <code>false</code> otherwise
     */
    private boolean isDuplicate(ParameterType parameterType) {
        boolean isDuplicate = false;
        List<ParameterType> availableParameterTypes = parameterTypes.get(parameterType.getName());
        if (availableParameterTypes != null) {
            int parameterTypeCounter = 0;
            while (!isDuplicate && parameterTypeCounter < availableParameterTypes.size()) {
                isDuplicate = parameterType.equals(availableParameterTypes.get(parameterTypeCounter));
                parameterTypeCounter++;
            }
        }
        return isDuplicate;
    }
    
    /**
     * Checks whether the given {@link ChangeIdentifier} is a duplicate of another {@link ChangeIdentifier} in the 
     * {@link #changeIdentifiers}.
     *  
     * @param changeIdentifier the {@link ChangeIdentifier}, which should be checked for being a duplicate of an already
     *        existing {@link ChangeIdentifier} in {@link #changeIdentifiers}; should never be <code>null</code>
     * @return <code>true</code>, if a duplicate of the given {@link ChangeIdentifier} in {@link #changeIdentifiers}
     *         exists; <code>false</code> otherwise
     */
    private boolean isDuplicate(ChangeIdentifier changeIdentifier) {
        boolean isDuplicate = false;
        List<ChangeIdentifier> availableChangeIdentifiers = changeIdentifiers.get(changeIdentifier.getName());
        if (availableChangeIdentifiers != null) {            
            int changeIdentifierCounter = 0;
            while (!isDuplicate && changeIdentifierCounter < availableChangeIdentifiers.size()) {
                isDuplicate = changeIdentifier.equals(availableChangeIdentifiers.get(changeIdentifierCounter));
                changeIdentifierCounter++;
            }
        }
        return isDuplicate;
    }
    
    /**
     * Checks whether the given {@link Call} is a duplicate of another {@link Call} in the {@link #calls}.
     *  
     * @param call the {@link Call}, which should be checked for being a duplicate of an already existing {@link Call}
     *        in {@link #calls}; should never be <code>null</code>
     * @return <code>true</code>, if a duplicate of the given {@link Call} in {@link #calls} exists; <code>false</code>
     *         otherwise
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
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder registryStringBuilder = new StringBuilder();
        registryStringBuilder.append("### Parameter Types ###");
        registryStringBuilder.append("\n");
        registryStringBuilder.append(asString(parameterTypes));
        registryStringBuilder.append("### Change Identifiers ###");
        registryStringBuilder.append("\n");
        registryStringBuilder.append(asString(changeIdentifiers));
        registryStringBuilder.append("### Calls ###");
        registryStringBuilder.append("\n");
        registryStringBuilder.append(asString(calls));
        return registryStringBuilder.toString();
    }

    /**
     * .
     * @param <T> .
     * @param elementMap .
     * @return .
     */
    private <T> String asString(HashMap<String, List<T>> elementMap) {
        Set<String> elementMapKeySet = elementMap.keySet();
        StringBuilder elementMapStringBuilder = new StringBuilder();
        for (String elementMapKey : elementMapKeySet) {
            List<?> keyElementList = elementMap.get(elementMapKey);
            LanguageElement languageElement;
            for (int i = 0; i < keyElementList.size(); i++) {
                languageElement = (LanguageElement) keyElementList.get(i);
                elementMapStringBuilder.append(languageElement);
                elementMapStringBuilder.append("\n");
            }
        }
        return elementMapStringBuilder.toString();
    }

}
