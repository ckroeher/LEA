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
public class LanguageRegistry {

    /**
     * The singleton instance of this {@link LanguageRegistry}.
     */
    public static final LanguageRegistry INSTANCE = new LanguageRegistry();
    
    /**
     * The {@link HashMap} of all available parameter types for the definition of artifacts. Each entry in this set has
     * the artifact parameter type name as its key and the corresponding {@link ParameterType} as its value.
     */
    private HashMap<String, ParameterType> artifactParameterTypes;
    
    /**
     * The {@link HashMap} of all available parameter types for the definition of fragments. Each entry in this set has
     * the fragment parameter type name as its key and the corresponding {@link ParameterType} as its value.
     */
    private HashMap<String, ParameterType> fragmentParameterTypes;
    
    /**
     * The {@link HashMap} of all available parameter types for the definition of results. Each entry in this set has
     * the result parameter type name as its key and the corresponding {@link ParameterType} as its value.
     */
    private HashMap<String, ParameterType> resultParameterTypes;
    
    /**
     * Constructs the singleton instance of this {@link LanguageRegistry}.
     */
    private LanguageRegistry() {
        artifactParameterTypes = new HashMap<String, ParameterType>();
        fragmentParameterTypes = new HashMap<String, ParameterType>();
        resultParameterTypes = new HashMap<String, ParameterType>();
    }
    
    /**
     * Adds the {@link LanguageElement}s in the given {@link List} to this {@link LanguageRegistry}. The individual
     * elements will be stored in separate {@link HashMap}s depending on their respective type.
     * 
     * @param newElements the {@link List} of {@link LanguageElement}s to be added to this {@link LanguageRegistry};
     *        should never be <code>null</code>, but may be <i>empty</i>
     */
    public void addLanguageElements(List<LanguageElement> newElements) {
        for (LanguageElement newElement : newElements) {
            switch(newElement.getElementType()) {
            case ARTIFACT_PARAMETER_TYPE:
                artifactParameterTypes.put(newElement.getName(), (ParameterType) newElement);
                break;
            case FRAGMENT_PARAMETER_TYPE:
                fragmentParameterTypes.put(newElement.getName(), (ParameterType) newElement);
                break;
            case RESULT_PARAMETER_TYPE:
                resultParameterTypes.put(newElement.getName(), (ParameterType) newElement);
                break;
            default:
                // Should never be reached!
                break;
            }
        }
    }
    
    /**
     * Checks whether this registry contains no {@link LanguageElement}s.
     * 
     * @return <code>true</code> if this registry contains no {@link LanguageElement}s; <code>false</code> otherwise
     */
    public boolean isEmpty() {
        return artifactParameterTypes.isEmpty() && fragmentParameterTypes.isEmpty() && resultParameterTypes.isEmpty();
    }
    
    /**
     * Checks whether the given search name is an available artifact parameter type (name).
     * 
     * @param searchName the name that should denote an available artifact parameter type
     * @return <code>true</code>, if the given search name is an available artifact parameter type (name);
     *         <code>false</code> otherwise
     */
    public boolean hasArtifactParameterType(String searchName) {
        return artifactParameterTypes.containsKey(searchName);
    }
    
    /**
     * Checks whether the given search name is an available fragment parameter type (name).
     * 
     * @param searchName the name that should denote an available fragment parameter type
     * @return <code>true</code>, if the given search name is an available fragment parameter type (name);
     *         <code>false</code> otherwise
     */
    public boolean hasFragmentParameterType(String searchName) {
        return fragmentParameterTypes.containsKey(searchName);
    }
    
    /**
     * Checks whether the given search name is an available result parameter type (name).
     * 
     * @param searchName the name that should denote an available result parameter type
     * @return <code>true</code>, if the given search name is an available result parameter type (name);
     *         <code>false</code> otherwise
     */
    public boolean hasResultParameterType(String searchName) {
        return resultParameterTypes.containsKey(searchName);
    }
}
