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
package net.ssehub.validation;

import java.util.HashSet;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.validation.Check;

import net.ssehub.integration.ChangeIdentifier;
import net.ssehub.integration.LanguageRegistry;
import net.ssehub.lea.AnalysisDefinition;
import net.ssehub.lea.Assignment;
import net.ssehub.lea.ChangeIdentifierAssignment;
import net.ssehub.lea.ElementDeclaration;
import net.ssehub.lea.LeaPackage;
import net.ssehub.lea.Operation;

/**
 * This class contains custom validation rules.
 * 
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#validation
 * 
 * @author Christian Kroeher
 *
 */
public class LeaValidator extends AbstractLeaValidator {
    
    /**
     * Checks the entire {@link AnalysisDefinition} for {@link ElementDeclaration}s, which have equal names. In such a
     * case, the duplicated names are marked with an error.
     * 
     * @param analysis the {@link AnalysisDefinition} in which the elements will be checked for duplicated names
     */
    @Check
    public void checkNoDuplicatedElementNames(AnalysisDefinition analysis) {
        EList<ElementDeclaration> elementDeclarations = analysis.getElementDeclarations();
        HashSet<String> visitedElementNames = new HashSet<String>();
        String currentElementName;
        for (ElementDeclaration element : elementDeclarations) {
            currentElementName = element.getName();
            if (visitedElementNames.contains(currentElementName)) {
                error("Duplicated element \"" + currentElementName + "\"", element,
                        LeaPackage.Literals.ELEMENT_DECLARATION__NAME);
            } else {
                visitedElementNames.add(currentElementName);
            }
        }
    }
    
    /**
     * Checks the given {@link ElementDeclaration} for being valid. This is the case, if:
     * <ul> TODO
     * <li>The name of the change identifier defined as part of the {@link ChangeIdentifierAssignment} identifies an
     *     available {@link ChangeIdentifier} in the {@link LanguageRegistry}</li>
     * <li>The elements (names) to which the change identifier is assigned to in the {@link ChangeIdentifierAssignment}
     *     reference {@link ElementDeclaration}s in the {@link Resource} of the given
     *     {@link ChangeIdentifierAssignment}</li>
     * <li>The assignable elements (names) match the supported assignable elements of the identified
     *     {@link ChangeIdentifier} above</li>
     * </ul>
     *  
     * @param declaration the {@link ElementDeclaration} to validate
     */
    @Check
    public void checkValidElementDeclaration(ElementDeclaration declaration) {
        // 1. Element parameter type available for generic type in the language registry?
        String elementGenericType = declaration.getGenericTyp();
        String elementParameterType = declaration.getParameterType();
        switch(elementGenericType) {
        case "Artifact":
            if (!LanguageRegistry.INSTANCE.hasArtifactParameterType(elementParameterType)) {
                error("Unknown parameter type \"" + elementParameterType + "\"", declaration,
                        LeaPackage.Literals.ELEMENT_DECLARATION__PARAMETER_TYPE);
            }
            break;
        case "Fragment":
            if (!LanguageRegistry.INSTANCE.hasFragmentParameterType(elementParameterType)) {
                error("Unknown parameter type \"" + elementParameterType + "\"", declaration,
                        LeaPackage.Literals.ELEMENT_DECLARATION__PARAMETER_TYPE);
            }
            break;
        case "Result":
            if (!LanguageRegistry.INSTANCE.hasResultParameterType(elementParameterType)) {
                error("Unknown parameter type \"" + elementParameterType + "\"", declaration,
                        LeaPackage.Literals.ELEMENT_DECLARATION__PARAMETER_TYPE);
            }
            break;
        default:
            error("Unsupported \"" + elementGenericType + "\"", declaration,
                    LeaPackage.Literals.ELEMENT_DECLARATION__GENERIC_TYP);
            break;
        }
        
        // 2. Name defined correctly?
        String elementName = declaration.getName();
        if (elementName != null && !elementName.isBlank()) {
            if (Character.isDigit(elementName.charAt(0)) || Character.isUpperCase(elementName.charAt(0))) {
                warning("Element names should start with a lower case letter", declaration,
                        LeaPackage.Literals.ELEMENT_DECLARATION__NAME);
            }
        } else {
            error("Missing element name", declaration, LeaPackage.Literals.ELEMENT_DECLARATION__NAME);
        }
        
        // 3. Initialization matches defined type (including set definition)?
        Assignment elementInitialization = declaration.getInitialization();
        if (elementInitialization != null) {
            String assignedElementName = elementInitialization.getElement();
            if (assignedElementName != null) {
                ElementDeclaration assignedElement = getElementDeclaration(elementInitialization.getElement(),
                        declaration.eResource());
                if (assignedElement != null) {
                    if (!haveEqualTypes(declaration, assignedElement)) {
                        error("Type mismatch", declaration, LeaPackage.Literals.ELEMENT_DECLARATION__INITIALIZATION);
                    }
                } else {
                    error("Missing " + elementGenericType.toLowerCase() + " \"" + assignedElementName + "\"",
                            declaration, LeaPackage.Literals.ELEMENT_DECLARATION__INITIALIZATION);
                }
            } else {
                Operation assignedOperation = elementInitialization.getOperation();
                if (assignedOperation != null) {
                    if (!haveEqualTypes(declaration, assignedOperation)) {
                        error("Type mismatch", declaration, LeaPackage.Literals.ELEMENT_DECLARATION__INITIALIZATION);
                    }
                } else {
                    // If this point is reached, there is neither an assigned element nor operation -> error
                    error("Missing assignment for initialization", declaration,
                            LeaPackage.Literals.ELEMENT_DECLARATION__INITIALIZATION);
                }
            }
        }
    }
    
    /**
     * Checks the given {@link ChangeIdentifierAssignment} for being valid. This is the case, if:
     * <ul>
     * <li>The name of the change identifier defined as part of the {@link ChangeIdentifierAssignment} identifies an
     *     available {@link ChangeIdentifier} in the {@link LanguageRegistry}</li>
     * <li>The elements (names) to which the change identifier is assigned to in the {@link ChangeIdentifierAssignment}
     *     reference {@link ElementDeclaration}s in the {@link Resource} of the given
     *     {@link ChangeIdentifierAssignment}</li>
     * <li>The assignable elements (names) match the supported assignable elements of the identified
     *     {@link ChangeIdentifier} above</li>
     * </ul>
     *  
     * @param changeIdentifierAssignment the {@link ChangeIdentifierAssignment} to validate
     */
    @Check
    public void checkValidChangeIdentifierAssignment(ChangeIdentifierAssignment changeIdentifierAssignment) {
        // 1. Change identifier available in language registry?
        String changeIdentifierName = changeIdentifierAssignment.getIdentifier();
        if (changeIdentifierName == null || !LanguageRegistry.INSTANCE.hasChangeIdentifier(changeIdentifierName)) {
            error("Unknown change identifier \"" + changeIdentifierName + "\"", changeIdentifierAssignment,
                    LeaPackage.Literals.CHANGE_IDENTIFIER_ASSIGNMENT__IDENTIFIER);
        }
        
        // 2. Assignable elements available as artifacts or fragments in current analysis definition?
        EList<String> assignableElements = changeIdentifierAssignment.getElements();
        String[] assignableElementDeclarationParameterTypes = new String[assignableElements.size()];
        String assignableElement;
        ElementDeclaration assignableElementDeclaration;
        for (int i = 0; i < assignableElements.size(); i++) {
            assignableElement = assignableElements.get(i);
            assignableElementDeclaration = getElementDeclaration(assignableElement,
                    changeIdentifierAssignment.eResource());
            if (assignableElementDeclaration != null) {
                assignableElementDeclarationParameterTypes[i] = assignableElementDeclaration.getParameterType();
            } else {
                error("Undefined artifact or fragment \"" + assignableElement + "\"", changeIdentifierAssignment,
                        LeaPackage.Literals.CHANGE_IDENTIFIER_ASSIGNMENT__ELEMENTS);
            }
        }
        
        // 3. Assignable element accepted by change identifier in the language registry?
        if (!LanguageRegistry.INSTANCE.hasChangeIdentifier(changeIdentifierName,
                assignableElementDeclarationParameterTypes)) {
            error("Change identifier \"" + changeIdentifierName + "\" is not assignable to these elements",
                    changeIdentifierAssignment, LeaPackage.Literals.CHANGE_IDENTIFIER_ASSIGNMENT__IDENTIFIER);
        }
    }
    
    /**
     * Searches in the given {@link EList} of {@link ElementDeclaration}s for an {@link ElementDeclaration} with the
     * given name and returns it.
     * 
     * @param name the name of the {@link ElementDeclaration} to be found in the given {@link Resource}; should never be
     *        <code>null</code> nor <i>blank</i>
     * @param resource the {@link Resource} in which an {@link ElementDeclaration} with the given name should be found;
     *        should never be <code>null</code>
     * @return an {@link ElementDeclaration} with the given name or <code>null</code>, if no such declaration exists
     */
    private ElementDeclaration getElementDeclaration(String name, Resource resource) {
        ElementDeclaration foundElementDeclaration = null;
        if (name != null && !name.isBlank()) {
            EList<ElementDeclaration> searchList = getElementDeclarations(resource);
            if (searchList != null && !searchList.isEmpty()) {                
                int searchListCounter = 0;
                while (foundElementDeclaration == null && searchListCounter < searchList.size()) {
                    if (searchList.get(searchListCounter).getName().equals(name)) {
                        foundElementDeclaration = searchList.get(searchListCounter);
                    }
                    searchListCounter++;
                }
            }
        }
        return foundElementDeclaration;
    }
    
    /**
     * Searches in the given {@link Resource} for the {@link EList} of {@link ElementDeclaration}s and returns it.
     * 
     * @param resource the {@link Resource} in which shall be searched for the {@link EList} of 
     *        {@link ElementDeclaration}s; should never be <code>null</code>
     * @return the {@link EList} of {@link ElementDeclaration}s or <code>null</code>, if retrieving the 
     *         {@link AnalysisDefinition} from the given resource failed
     * @see #getAnalysisDefinition(EList)
     * @see Resource#getContents()
     */
    private EList<ElementDeclaration> getElementDeclarations(Resource resource) {
        EList<ElementDeclaration> elementDeclarations = null;
        AnalysisDefinition analysisDefinition = getAnalysisDefinition(resource.getContents());            
        if (analysisDefinition != null) {
            elementDeclarations = analysisDefinition.getElementDeclarations();
        }
        return elementDeclarations;
    }
    
    /**
     * Searches in the given {@link EList} of {@link EObject}s for the (currently single) {@link AnalysisDefinition} and
     * returns it.
     * 
     * @param resourceContent the {@link EList} of {@link EObject}s as returned by {@link Resource#getContents()};
     *        should never be <code>null</code>, but may be <i>empty</i>
     * @return the {@link AnalysisDefinition} found in the given list or <code>null</code>, if no such element is
     *         available
     */
    private AnalysisDefinition getAnalysisDefinition(EList<EObject> resourceContent) {
        AnalysisDefinition analysisDefinition = null;
        if (resourceContent.size() == 1) { // Currently there is only a single AnalysisDefinition per file
            EObject contentElement = resourceContent.get(0);
            if (contentElement instanceof AnalysisDefinition) {                
                analysisDefinition = (AnalysisDefinition) contentElement;
            }
        }
        return analysisDefinition;
    }
    
    /**
     * Checks whether the given {@link ElementDeclaration}s have the same type. This is the case, if:
     * <ul>
     * <li>The {@link ElementDeclaration#getGenericTyp()}s are equal</li>
     * <li>The {@link ElementDeclaration#getParameterType()}s are equal and</li>
     * <li>The {@link ElementDeclaration#getSet()} definitions are equal</li>
     * </ul>
     * 
     * @param ed1 the first {@link ElementDeclaration} to compare for equal types
     * @param ed2 the second {@link ElementDeclaration} to compare for equal types
     * @return <code>true</code>, if the given {@link ElementDeclaration}s have the same types; <code>false</code>
     *         otherwise
     */
    private boolean haveEqualTypes(ElementDeclaration ed1, ElementDeclaration ed2) {
        return ed1.getGenericTyp().equals(ed2.getGenericTyp()) 
                && ed1.getParameterType().equals(ed2.getParameterType())
                && (ed1.getSet() == null) == (ed2.getSet() == null);
    }
    
    /**
     * Checks whether the type of the given {@link ElementDeclaration} is equal to the type of the return value of the
     * given {@link Operation}. This is the case, if: TODO
     *  
     * @param elementDeclaration the {@link ElementDeclaration} to compare to the given {@link Operation} regarding
     *        element and return value type
     * @param operation the {@link Operation} to compare to the given {@link ElementDeclaration} regarding
     *        element and return value type
     * @return <code>true</code>, if the type of the given {@link ElementDeclaration} is equal to the type of the return
     *         value of the given {@link Operation}; <code>false</code> otherwise
     */
    private boolean haveEqualTypes(ElementDeclaration elementDeclaration, Operation operation) {
        return true;
    }
    
}
