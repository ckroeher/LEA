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
import java.util.List;

/**
 * This class represents a call <code>c()</code> for operations, extractors, or analyzers, e.g.,
 * <code><b>Artifact</b>&lt;T&gt; name = c()</code>. The particular type of a call and, hence, its application depends
 * on the {@link ElementType} passed as a parameter to the constructor.
 * 
 * @author Christian Kroeher
 *
 */
public class Call extends LanguageElement {
    
    /**
     * The name of the type of element(s) this call will return.
     */
    private String returnType;
    
    /**
     * The list of names, which denote the elements this call accepts as parameters.
     */
    private List<String> parameters;

    /**
     * Constructs a new {@link Call} with the given attributes.
     * 
     * @param elementType the {@link ElementType} of this new element, which must be {@link ElementType#OPERATION},
     *        {@link ElementType#EXTRACTOR_CALL}, or {@link ElementType#ANALYSIS_CALL}; any other type leads to a 
     *        {@link LanguageElementException}
     * @param name the name of this new element
     * @param returnType the name of the type of element(s) this call will return
     * @param parameters the list of names, which denote the elements this call accepts as parameters 
     * @param sourceClass the {@link Class} from where this new element is created
     * @param sourcePlugin the {@link File}, which is a Java archive file, from where this new element is created
     * @throws LanguageElementException if any of the above parameters is <code>null</code>, the element type does not
     *         match one of those defined above, the return type is <i>blank</i>, the parameter list is <i>empty</i>,
     *         or the name is <i>blank</i>
     */
//CHECKSTYLE:OFF
    protected Call(ElementType elementType, String name, String returnType, List<String> parameters,
            Class<?> sourceClass, File sourcePlugin) throws LanguageElementException {
        super(elementType, name, sourceClass, sourcePlugin);
        if (elementType != ElementType.OPERATION 
                && elementType != ElementType.EXTRACTOR_CALL 
                && elementType != ElementType.ANALYSIS_CALL) {
            throw new LanguageElementException("Type mismatch: \"" + elementType + "\" is not a valid call type");
        }
        if (returnType == null || returnType.isBlank()) {
            throw new LanguageElementException("The return type for the new language element is null or blank");
        }
        if (parameters == null || parameters.isEmpty()) {
            throw new LanguageElementException("The parameter list for the new language element is null or empty");
        }
        this.returnType = returnType;
        this.parameters = parameters;
    }
//CHECKSTYLE:ON
    /*
     * TODO We need some kind of validation after all external elements are loaded. This validation should check for
     * each element, or at least for the change identifier and the operations (those elements, that somehow work on/with
     * other elements), if the elements are available. If not, we do not know what to do with the change identifier or
     * the operations later.
     * Suggestion: After the LanguageElementRegistry (or whatever the element database will be), that components has to
     * perform the validation before it is used. Problematic elements should then be removed.
     */
    
    /**
     * Returns the name of the type of element(s) this call will return.
     * 
     * @return the name of the type of element(s) this call will return
     */
    public String getReturnType() {
        return returnType;
    }
    
    /**
     * Returns the list of names, which denote the elements this call accepts as parameters.
     * 
     * @return the list of names, which denote the elements this call accepts as parameters
     */
    public List<String> getParameters() {
        return parameters;
    }
}