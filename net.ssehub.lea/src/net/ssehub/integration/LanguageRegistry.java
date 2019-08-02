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
    
    /*
     * TODO We need some kind of validation after all external elements are loaded. This validation should check for
     * each element, or at least for the change identifier and the operations (those elements, that somehow work on/with
     * other elements), if the elements are available. If not, we do not know what to do with the change identifier or
     * the operations later.
     * Suggestion: After the LanguageElementRegistry (or whatever the element database will be), that components has to
     * perform the validation before it is used. Problematic elements should then be removed.
     */

    /**
     * The singleton instance of this {@link LanguageRegistry}.
     */
    public static final LanguageRegistry INSTANCE = new LanguageRegistry();
    
    /**
     * The number of {@link LanguageElement}s currently registered. The initial value of <code>0</code> is set in
     * {@link #LanguageRegistry()}. It is increased by <code>1</code> for each element added to this registry in 
     * {@link #addLanguageElements(List)}. 
     */
    private static int languageElementCounter;
    
    /**
     * The {@link HashMap} of all available {@link ParameterType}s of the type 
     * {@link ElementType#ARTIFACT_PARAMETER_TYPE} for the definition of artifacts. Each entry in this set has the 
     * artifact parameter type name as its key and the corresponding {@link ParameterType} as its value.
     */
    private HashMap<String, ParameterType> artifactParameterTypes;
    
    /**
     * The {@link HashMap} of all available {@link ParameterType}s of the type 
     * {@link ElementType#FRAGMENT_PARAMETER_TYPE} for the definition of fragments. Each entry in this set has the
     * fragment parameter type name as its key and the corresponding {@link ParameterType} as its value.
     */
    private HashMap<String, ParameterType> fragmentParameterTypes;
    
    /**
     * The {@link HashMap} of all available {@link ParameterType}s of the type {@link ElementType#RESULT_PARAMETER_TYPE}
     * for the definition of results. Each entry in this set has the result parameter type name as its key and the
     * corresponding {@link ParameterType} as its value.
     */
    private HashMap<String, ParameterType> resultParameterTypes;
    
    /**
     * The {@link HashMap} of all available {@link ChangeIdentifier}s for their assignment to artifacts or fragments 
     * (depending on the particular identifier). Each entry in this set has the change identifier name as its key and
     * the corresponding {@link ChangeIdentifier} as its value.
     */
    private HashMap<String, ChangeIdentifier> changeIdentifiers;
    
    /**
     * The {@link HashMap} of all available {@link Call}s of the type {@link ElementType#OPERATION} for the definition
     * of general operations. Each entry in this set has the operation name as its key and the corresponding
     * {@link Call} as its value.
     * 
     * TODO calls may have the same name, but different return or parameter types or different numbers of parameters!
     */
    private HashMap<String, Call> operations;
    
    /**
     * The {@link HashMap} of all available {@link Call}s of the type {@link ElementType#EXTRACTOR_CALL} for the
     * definition of fragment extractions from artifacts. Each entry in this set has the extractor (call) name as its
     * key and the corresponding {@link Call} as its value.
     * 
     * TODO calls may have the same name, but different return or parameter types or different numbers of parameters!
     */
    private HashMap<String, Call> extractorCalls;
    
    /**
     * The {@link HashMap} of all available {@link Call}s of the type {@link ElementType#ANALYSIS_CALL} for the
     * definition of analysis results based on fragments. Each entry in this set has the analysis (call) name as its
     * key and the corresponding {@link Call} as its value.
     * 
     * TODO calls may have the same name, but different return or parameter types or different numbers of parameters!
     */
    private HashMap<String, Call> analysisCalls;
    
    /**
     * Constructs the singleton instance of this {@link LanguageRegistry}.
     */
    private LanguageRegistry() {
        languageElementCounter = 0;
        artifactParameterTypes = new HashMap<String, ParameterType>();
        fragmentParameterTypes = new HashMap<String, ParameterType>();
        resultParameterTypes = new HashMap<String, ParameterType>();
        changeIdentifiers = new HashMap<String, ChangeIdentifier>();
        operations = new HashMap<String, Call>();
        extractorCalls = new HashMap<String, Call>();
        analysisCalls = new HashMap<String, Call>();
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
                languageElementCounter++;
                break;
            case FRAGMENT_PARAMETER_TYPE:
                fragmentParameterTypes.put(newElement.getName(), (ParameterType) newElement);
                languageElementCounter++;
                break;
            case RESULT_PARAMETER_TYPE:
                resultParameterTypes.put(newElement.getName(), (ParameterType) newElement);
                languageElementCounter++;
                break;
            case CHANGE_IDENTIFIER:
                changeIdentifiers.put(newElement.getName(), (ChangeIdentifier) newElement);
                languageElementCounter++;
                break;
            case OPERATION:
                operations.put(newElement.getName(), (Call) newElement);
                languageElementCounter++;
                break;
            case EXTRACTOR_CALL:
                extractorCalls.put(newElement.getName(), (Call) newElement);
                languageElementCounter++;
                break;
            case ANALYSIS_CALL:
                analysisCalls.put(newElement.getName(), (Call) newElement);
                languageElementCounter++;
                break;
            default:
                // Should never be reached!
                break;
            }
        }
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
    
    /**
     * Returns the {@link ParameterType} of the type {@link ElementType#ARTIFACT_PARAMETER_TYPE}, which has the given
     * name.
     *  
     * @param name the name of the artifact parameter type, which should be returned
     * @return the {@link ParameterType} of the type {@link ElementType#ARTIFACT_PARAMETER_TYPE} with the given name or
     *         <code>null</code>, if no such element is registered
     */
    public ParameterType getArtifactParameterType(String name) {
        return artifactParameterTypes.get(name);
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
        return fragmentParameterTypes.get(name);
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
        return resultParameterTypes.get(name);
    }
    
    /**
     * Returns the {@link ChangeIdentifier}, which has the given name.
     *  
     * @param name the name of the change identifier, which should be returned
     * @return the {@link ChangeIdentifier} with the given name or <code>null</code>, if no such element is registered
     */
    public ChangeIdentifier getChangeIdentifier(String name) {
        return changeIdentifiers.get(name);
    }
    
    /**
     * Returns the {@link Call} of the type {@link ElementType#OPERATION}, which has the given name.
     *  
     * @param name the name of the operation, which should be returned
     * @return the {@link Call} of the type {@link ElementType#OPERATION} with the given name or <code>null</code>, if
     *         no such element is registered
     */
    public Call getOperation(String name) {
        return operations.get(name);
    }
    
    /**
     * Returns the {@link Call} of the type {@link ElementType#EXTRACTOR_CALL}, which has the given name.
     *  
     * @param name the name of the extractor call, which should be returned
     * @return the {@link Call} of the type {@link ElementType#EXTRACTOR_CALL} with the given name or <code>null</code>,
     *         if no such element is registered
     */
    public Call getExtractorCall(String name) {
        return extractorCalls.get(name);
    }
    
    /**
     * Returns the {@link Call} of the type {@link ElementType#ANALYSIS_CALL}, which has the given name.
     *  
     * @param name the name of the analysis call, which should be returned
     * @return the {@link Call} of the type {@link ElementType#ANALYSIS_CALL} with the given name or <code>null</code>,
     *         if no such element is registered
     */
    public Call getAnalysisCall(String name) {
        return analysisCalls.get(name);
    }
    
}
