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
import org.eclipse.xtext.validation.Check;

import net.ssehub.integration.ChangeIdentifier;
import net.ssehub.integration.LanguageRegistry;
import net.ssehub.lea.AnalysisDefinition;
import net.ssehub.lea.ChangeIdentifierAssignment;
import net.ssehub.lea.ElementDeclaration;
import net.ssehub.lea.LeaPackage;

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
    
//    /**
//     * TODO.
//     * 
//     * @param analysis the {@link AnalysisDefinition} in which all {@link ElementDeclaration}s will be validated
//     */
//    @Check
//    public void checkValidElementDeclarations(AnalysisDefinition analysis) {
//        EList<ElementDeclaration> elementDeclarations = analysis.getElementDeclarations();
//        for (ElementDeclaration elementDeclaration : elementDeclarations) {
//            
//        }
//    }
    
    /**
     * TODO.
     * 
     * @param declaration the {@link ElementDeclaration} to validate
     */
    @Check
    public void checkValidElementDeclaration(ElementDeclaration declaration) {
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
    }

    /**
     * Checks each {@link ChangeIdentifierAssignment} in the given {@link AnalysisDefinition} for being valid. This is
     * the case, if:
     * <ul>
     * <li>The name of the change identifier defined as part of the {@link ChangeIdentifierAssignment} identifies an
     *     available {@link ChangeIdentifier} in the {@link LanguageRegistry}</li>
     * <li>The elements (names) to which the change identifier is assigned to in the {@link ChangeIdentifierAssignment}
     *     reference {@link ElementDeclaration}s of the given {@link AnalysisDefinition}</li>
     * <li>The assignable elements (names) match the supported assignable elements of the identified
     *     {@link ChangeIdentifier} above</li>
     * </ul>
     * @param analysis the {@link AnalysisDefinition} in which all {@link ChangeIdentifierAssignment}s will be validated
     * @see #checkValidChangeIdentifierAssignment(ChangeIdentifierAssignment, EList)
     */
    @Check
    public void checkValidChangeIdentifierAssignments(AnalysisDefinition analysis) {
        EList<ChangeIdentifierAssignment> changeIdentifierAssignments = analysis.getChangeIdentifierAssignments();
        EList<ElementDeclaration> elementDeclarations = analysis.getElementDeclarations();
        for (ChangeIdentifierAssignment changeIdentifierAssignment : changeIdentifierAssignments) {
            checkValidChangeIdentifierAssignment(changeIdentifierAssignment, elementDeclarations);
        }
    }
    
    /**
     * Checks the given {@link ChangeIdentifierAssignment} for being valid. This is the case, if:
     * <ul>
     * <li>The name of the change identifier defined as part of the {@link ChangeIdentifierAssignment} identifies an
     *     available {@link ChangeIdentifier} in the {@link LanguageRegistry}</li>
     * <li>The elements (names) to which the change identifier is assigned to in the {@link ChangeIdentifierAssignment}
     *     reference {@link ElementDeclaration}s of the given {@link EList}</li>
     * <li>The assignable elements (names) match the supported assignable elements of the identified
     *     {@link ChangeIdentifier} above</li>
     * </ul>
     * In case of invalidity, this method creates corresponding errors.
     *  
     * @param changeIdentifierAssignment the {@link ChangeIdentifierAssignment} to validate
     * @param elementDeclarations the {@link EList} of {@link ElementDeclaration}s to check whether the elements to
     *        which the change identifier is assigned to are defined
     */
    private void checkValidChangeIdentifierAssignment(ChangeIdentifierAssignment changeIdentifierAssignment,
            EList<ElementDeclaration> elementDeclarations) {
        boolean isValid = true; // Used to abort further checks, if invalidity already determined
        
        // 1. Change identifier(s) available in language registry?
        String changeIdentifierName = changeIdentifierAssignment.getIdentifier();
        if (changeIdentifierName == null || !LanguageRegistry.INSTANCE.hasChangeIdentifier(changeIdentifierName)) {
            isValid = false;
            error("Unknown change identifier \"" + changeIdentifierName + "\"", changeIdentifierAssignment,
                    LeaPackage.Literals.CHANGE_IDENTIFIER_ASSIGNMENT__IDENTIFIER);
        }
        
        // 2. Assignable elements available as artifacts or fragments in current analysis definition?
        EList<String> assignableElements = changeIdentifierAssignment.getElements();
        int indexCounter = 0;
        String assignableElement;
        ElementDeclaration assignableElementDeclaration;
        String[] assignableElementDeclarationParameterTypes = new String[assignableElements.size()];
        while (isValid && indexCounter < assignableElements.size()) {
            assignableElement = assignableElements.get(indexCounter);
            assignableElementDeclaration = getElementDeclaration(assignableElement, elementDeclarations);
            if (assignableElementDeclaration != null) {
                assignableElementDeclarationParameterTypes[indexCounter] = 
                        assignableElementDeclaration.getParameterType();
            } else {
                isValid = false;
                error("Undefined artifact or fragment \"" + assignableElement + "\"", changeIdentifierAssignment,
                        LeaPackage.Literals.CHANGE_IDENTIFIER_ASSIGNMENT__ELEMENTS);
            }
            indexCounter++;
        }
        
        // 3. Assignable element accepted by change identifier in the language registry?
        if (isValid && !LanguageRegistry.INSTANCE.hasChangeIdentifier(changeIdentifierName,
                assignableElementDeclarationParameterTypes)) {
            // TODO how to inform which elements are not assignable exactly?
            error("Change identifier \"" + changeIdentifierName + "\" is not assignable to these elements",
                    changeIdentifierAssignment, LeaPackage.Literals.CHANGE_IDENTIFIER_ASSIGNMENT__IDENTIFIER);
        }
    }
    
    /**
     * Searches in the given {@link EList} of {@link ElementDeclaration}s for an {@link ElementDeclaration} with the
     * given name and returns it.
     * 
     * @param name the name of the {@link ElementDeclaration} to be found in the given {@link EList} of
     *        {@link ElementDeclaration}s
     * @param searchList the {@link EList} of {@link ElementDeclaration}s in which an {@link ElementDeclaration} with
     *        the given name should be found
     * @return an {@link ElementDeclaration} with the given name or <code>null</code>, if no such declaration exists
     */
    private ElementDeclaration getElementDeclaration(String name, EList<ElementDeclaration> searchList) {
        ElementDeclaration foundElementDeclaration = null;
        if (name != null && searchList != null && !searchList.isEmpty()) {
            int searchListCounter = 0;
            while (foundElementDeclaration == null && searchListCounter < searchList.size()) {
                if (searchList.get(searchListCounter).getName().equals(name)) {
                    foundElementDeclaration = searchList.get(searchListCounter);
                }
                searchListCounter++;
            }
        }
        return foundElementDeclaration;
    }
}
