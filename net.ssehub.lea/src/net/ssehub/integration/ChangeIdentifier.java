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
public class ChangeIdentifier extends LanguageElement implements IFinalizable {
    
    /**
     * The constant {@link ElementType} of all instances of this class: {@link ElementType#CHANGE_IDENTIFIER}.
     */
    private static final ElementType ELEMENT_TYPE = ElementType.CHANGE_IDENTIFIER;
    
    /**
     * The fully-qualified name of this element.
     */
    private String fullyQualifiedName;
    
    /**
     * The array of {@link ParameterType}s to which this change identifier is assignable to.
     */
    private ParameterType[] assignableElements;
    
    /**
     * Constructs a new <b>premature</b> {@link ChangeIdentifier} with the given attributes omitting the required 
     * definition of elements to which the new change identifier is assignable to. These elements must be added using
     * {@link ChangeIdentifier#setAssignableElements(ParameterType[])} in order to {@link ChangeIdentifier#isFinal()}
     * returning <code>true</code> and, hence, enabling the addition of this change identifier to the 
     * {@link LanguageRegistry}. Further, this constructor sets the element type for the new change identifier to
     * {@link ElementType#CHANGE_IDENTIFIER} automatically.
     * 
     * @param name the name of this new element
     * @param sourceClass the {@link Class} from where this new element is created
     * @param sourcePlugin the {@link File}, which is a Java archive file, from where this new element is created
     * @throws LanguageElementException if any of the above parameters is <code>null</code>, or the name is <i>blank</i>
     */
    public ChangeIdentifier(String name, Class<?> sourceClass, File sourcePlugin) throws LanguageElementException {
        super(ELEMENT_TYPE, name, sourceClass, sourcePlugin);
        assignableElements = null;
        // Construct the fully-qualified name of this element
        String sourceClassCanonicalName = sourceClass.getCanonicalName();
        int substringEndIndex = sourceClassCanonicalName.lastIndexOf('.') + 1;
        fullyQualifiedName = sourceClassCanonicalName.substring(0, substringEndIndex) + name;
    }
    
    /**
     * Sets the given {@link ParameterType}s as elements to which this change identifier is assignable to.
     * 
     * @param assignableElements the array of {@link ParameterType}s to be set as elements to which this change
     *        identifier is assignable to
     * @throws LanguageElementException if the elements to which this change identifier is assignable to are already 
     *         defined, the given array is <code>null</code>, is <i>empty</i>, or one of the elements of that array is
     *         actually <code>null</code>
     */
    public void setAssignableElements(ParameterType[] assignableElements) throws LanguageElementException {
        if (this.assignableElements != null) {
            throw new LanguageElementException("Elements to which this change identifier is assignable to already "
                    + "defined");
        }
        if (assignableElements == null || assignableElements.length == 0) {
            throw new LanguageElementException("Missing elements to which this change identifier is assignable to");
        }
        for (int i = 0; i < assignableElements.length; i++) {
            if (assignableElements[i] == null) {
                throw new LanguageElementException("Null element to which this change identifier is assignable to");
            }
        }
        this.assignableElements = assignableElements;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String getFullyQualifiedName() {
        return fullyQualifiedName;
    }
    
    /**
     * Returns the array of {@link ParameterType}s to which this change identifier is assignable to.
     * 
     * @return the array of {@link ParameterType}s to which this change identifier is assignable to or
     *         <code>null</code>, if the construction of this change identifier is not completed yet
     * @see #isFinal() 
     */
    public ParameterType[] getAssignableElements() {
        return assignableElements;
    }

    /**
     * {@inheritDoc}
     * 
     * In addition, two {@link ChangeIdentifier}s are equal, if their construction is completed ({@link #isFinal()} 
     * returns <code>true</code>), the numbers of assignable elements are equal and each assignable element at a
     * particular index of this {@link ChangeIdentifier} is equal to the assignable element at the same index of the
     * given {@link ChangeIdentifier}. 
     */
    @Override
    public boolean equals(LanguageElement comparable) {
        boolean isEqual = false;
        if (comparable instanceof ChangeIdentifier) {
            ChangeIdentifier comparableChangeIdentifier = (ChangeIdentifier) comparable;
            if (this.isFinal() && comparableChangeIdentifier.isFinal() && super.equals(comparable)) {
                ParameterType[] comparableAssignableElements = comparableChangeIdentifier.getAssignableElements();
                if (this.assignableElements.length == comparableAssignableElements.length) {
                    boolean haveEqualAssignableElements = true;
                    int assignableElementsCounter = 0;
                    while (haveEqualAssignableElements && assignableElementsCounter < this.assignableElements.length) {
                        if (!this.assignableElements[assignableElementsCounter].equals(
                                comparableAssignableElements[assignableElementsCounter])) {
                            haveEqualAssignableElements = false;
                        }
                        assignableElementsCounter++;
                    }
                    isEqual = haveEqualAssignableElements;
                }
            }
        }
        return isEqual;
    }
    
    /**
     * Compares the given elements with {@link #assignableElements} of this {@link ChangeIdentifier} independent of
     * their order in the array for equality. This is the case, if for each given element an equal element in
     * {@link #assignableElements} exists. 
     *   
     * @param elements the elements to compare to the {@link #assignableElements} of this {@link ChangeIdentifier};
     *        should never be <code>null</code> nor <i>empty</i>
     * @return <code>true</code>, if for each given element an equal element in {@link #assignableElements} exists;
     *         <code>false</code> otherwise
     */
    public boolean assignableTo(ParameterType[] elements) {
        boolean isAssignableTo = true;
        int elementsCounter = 0;
        ParameterType element;
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

    /**
     * {@inheritDoc}<br>
     * <br>
     * For {@link ChangeIdentifier}s, the construction is completed, if the {@link #assignableElements} are available.
     */
    @Override
    public boolean isFinal() {
        return (assignableElements != null);
    }
}
