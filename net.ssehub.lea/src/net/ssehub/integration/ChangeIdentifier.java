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
     * The array of names, which denote the elements to which this change identifier is assignable to.
     */
    private String[] assignableElements;

    /**
     * Constructs a new {@link ChangeIdentifier} with the given attributes. This constructor sets the element type 
     * for the new element to {@link ElementType#CHANGE_IDENTIFIER} automatically.
     * 
     * @param name the name of this new element
     * @param assignableElements the array of names, which denote the elements to which this change identifier is
     *        assignable to
     * @param sourceClass the {@link Class} from where this new element is created
     * @param sourcePlugin the {@link File}, which is a Java archive file, from where this new element is created
     * @throws LanguageElementException if any of the above parameters is <code>null</code>, the array of assignable
     *         elements is <i>empty</i>, or the name is <i>blank</i>
     */
    public ChangeIdentifier(String name, String[] assignableElements, Class<?> sourceClass, File sourcePlugin)
            throws LanguageElementException {
        super(ELEMENT_TYPE, name, sourceClass, sourcePlugin);
        if (assignableElements == null || assignableElements.length == 0) {
            throw new LanguageElementException("Missing elements to which this change identifier is assignable to");
        }
        // Check in addition if the elements to which this change identifier is assignable to are not blank
        for (int i = 0; i < assignableElements.length; i++) {
            if (assignableElements[i].isBlank()) {
                throw new LanguageElementException("Blank element to which this change identifier is assignable to");
            }
        }
        this.assignableElements = assignableElements;
    }
    
    /**
     * Returns the array of names, which denote the elements to which this change identifier is assignable to.
     * 
     * @return the array of names, which denote the elements to which this change identifier is assignable to
     */
    public String[] getAssignableElements() {
        return assignableElements;
    }

    /**
     * {@inheritDoc}
     * 
     * In addition, two {@link ChangeIdentifier}s are equal, if the numbers of assignable elements are equal and each
     * assignable element at a particular index in this {@link ChangeIdentifier} is equal to the assignable element at
     * the same index in the given {@link ChangeIdentifier}. 
     */
    @Override
    public boolean equals(LanguageElement comparable) {
        boolean isEqual = super.equals(comparable);
        if (isEqual) {
            ChangeIdentifier comparableChangeIdentifier = (ChangeIdentifier) comparable;
            String[] comparableAssignableElements = comparableChangeIdentifier.getAssignableElements();
            if (this.assignableElements.length == comparableAssignableElements.length) {
                int assignableElementsCounter = 0;
                while (isEqual && assignableElementsCounter < this.assignableElements.length) {
                    if (!this.assignableElements[assignableElementsCounter].equals(
                            comparableAssignableElements[assignableElementsCounter])) {
                        isEqual = false;
                    }
                    assignableElementsCounter++;
                }
            } else {
                isEqual = false;
            }
        }
        return isEqual;
    }
    
    /**
     * Compares the given elements with {@link #assignableElements} of this {@link ChangeIdentifier} independent of
     * their order in the array for equality. This is the case, if for each given element an equal element in
     * {@link #assignableElements} exists. 
     *   
     * @param elements the elements to compare to the {@link #assignableElements} of this {@link ChangeIdentifier}
     * @return <code>true</code>, if for each given element an equal element in {@link #assignableElements} exists;
     *         <code>false</code> otherwise
     */
    public boolean assignableTo(String[] elements) {
        boolean isAssignableTo = true;
        int elementsCounter = 0;
        String element;
        int assignableElementsCounter;
        boolean equalAssignableElementFound;
        while (isAssignableTo && elementsCounter < elements.length) {
            element = elements[elementsCounter];
            assignableElementsCounter = 0;
            equalAssignableElementFound = false;
            while (!equalAssignableElementFound && assignableElementsCounter < assignableElements.length) {
                equalAssignableElementFound = element.equals(assignableElements[assignableElementsCounter]);
                assignableElementsCounter++;
            }
            isAssignableTo = equalAssignableElementFound;
            elementsCounter++;
        }
        return isAssignableTo;
    }
}
