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
 * This class represents a change identifier <code>CI</code> for the assignment of such an element to artifacts or
 * fragments, e.g., <code><b>apply</b> CI <b>to</b> ...</code>. 
 * 
 * @author Christian Kroeher
 *
 */
public class ChangeIdentifier extends LanguageElement {
    
    /**
     * The constant {@link ElementType} of all instances of this class: {@link ElementType#CHANGE_IDENTIFIER}.
     */
    private static final ElementType ELEMENT_TYPE = ElementType.CHANGE_IDENTIFIER;
    
    /**
     * The list of names, which denote the elements to which this change identifier is assignable to.
     */
    private List<String> assignableElements;

    /**
     * Constructs a new {@link ChangeIdentifier} with the given attributes. This constructor sets the element type 
     * for the new element to {@link ElementType#CHANGE_IDENTIFIER} automatically.
     * 
     * @param name the name of this new element
     * @param assignableElements the list of names, which denote the elements to which this change identifier is
     *        assignable to
     * @param sourceClass the {@link Class} from where this new element is created
     * @param sourcePlugin the {@link File}, which is a Java archive file, from where this new element is created
     * @throws LanguageElementException if any of the above parameters is <code>null</code>, the list of assignable
     *         elements is <i>empty</i>, or the name is <i>blank</i>
     */
    protected ChangeIdentifier(String name, List<String> assignableElements, Class<?> sourceClass, File sourcePlugin)
            throws LanguageElementException {
        super(ELEMENT_TYPE, name, sourceClass, sourcePlugin);
        if (assignableElements.isEmpty()) {
            throw new LanguageElementException("Missing elements to which this change identifier is assignable");
        }
        this.assignableElements = assignableElements;
    }
    
    /*
     * TODO We need some kind of validation after all external elements are loaded. This validation should check for
     * each element, or at least for the change identifier and the operations (those elements, that somehow work on/with
     * other elements), if the elements are available. If not, we do not know what to do with the change identifier or
     * the operations later.
     * Suggestion: After the LanguageElementRegistry (or whatever the element database will be), that components has to
     * perform the validation before it is used. Problematic elements should then be removed.
     */
    
    /**
     * Returns the list of names, which denote the elements to which this change identifier is assignable to.
     * 
     * @return the list of names, which denote the elements to which this change identifier is assignable to
     */
    public List<String> getAssignableElements() {
        return assignableElements;
    }

}
