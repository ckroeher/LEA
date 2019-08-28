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
import net.ssehub.lea.Call;
import net.ssehub.lea.ChangeIdentifierAssignment;
import net.ssehub.lea.ElementDeclaration;
import net.ssehub.lea.LeaPackage;
import net.ssehub.lea.Operation;
import net.ssehub.lea.Parameter;

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
     * <ul>
     * <li>The parameter type used for the {@link ElementDeclaration} identifies an available parameter type in the
     *     {@link LanguageRegistry} for the generic type used for the {@link ElementDeclaration}</li>
     * <li>The name is defined for the {@link ElementDeclaration}</li>
     * <li>If that {@link ElementDeclaration} includes an initialization, the assigned element or operation determining
     *     the initial value has the same type as the one used for the {@link ElementDeclaration}</li>
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
            error("Unsupported generic type \"" + elementGenericType + "\"", declaration,
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
                // Element initialization with other artifact, fragment, or result element (declaration)
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
                    // Element initialization with operation
                    if (!haveEqualTypes(declaration, assignedOperation)) {
                        error("Type mismatch", declaration, LeaPackage.Literals.ELEMENT_DECLARATION__INITIALIZATION);
                    }
                } else {
                    // Element initialization with neither an assigned element nor operation -> should not be possible
                    error("Missing assignment for initialization", declaration,
                            LeaPackage.Literals.ELEMENT_DECLARATION__INITIALIZATION);
                }
            }
        }
    }
    
    /**
     * Checks whether the initialization (assignment) of the given {@link ElementDeclaration} is itself initialized, if
     * the given {@link ElementDeclaration} is initialized with another artifact, fragment, or result element.
     * 
     * @param elementDeclaration the {@link ElementDeclaration} to check for correct initialization
     */
    @Check
    public void checkInitializationInitialized(ElementDeclaration elementDeclaration) {
        Assignment initializationAssignment = elementDeclaration.getInitialization();
        if (initializationAssignment != null && !isAssignmentInitialized(initializationAssignment)) {
            error("Element \"" + initializationAssignment.getElement() + "\" may not have been initialized", 
                    elementDeclaration, LeaPackage.Literals.ELEMENT_DECLARATION__INITIALIZATION);
        }
    }
    
    /**
     * Checks whether the given (initialization) {@link Assignment} is itself initialized, if the given 
     * {@link Assignment} references another artifact, fragment, or result element. Hence, this method recursively calls
     * itself every time an assignment references another element. In case that the {@link Assignment}
     * is an operation call, this method assumes that the operation is a correct initialization.
     * 
     * @param initializationAssignment the {@link Assignment} to check for correct initialization 
     * @return <code>true</code>, if the assignment represents a correct initialization; <code>false</code> otherwise
     */
    private boolean isAssignmentInitialized(Assignment initializationAssignment) {
        boolean isAssignmentInitialized = false;
        String assignedElementName = initializationAssignment.getElement();
        if (assignedElementName != null) {
            ElementDeclaration assignedElementDeclaration = 
                    getElementDeclaration(assignedElementName, initializationAssignment.eResource());
            /*
             * No further checks or errors messages, if the assigned element is not declared; this is done by
             * checkValidElementDeclaration(ElementDeclaration declaration). 
             */
            if (assignedElementDeclaration != null) {
                Assignment assignedElementDeclarationInitialization = assignedElementDeclaration.getInitialization();
                if (assignedElementDeclarationInitialization != null) {
                    isAssignmentInitialized = isAssignmentInitialized(assignedElementDeclarationInitialization);
                }
            }
        } else {
            /*
             * There is no element assigned, but an operation is called. Hence, we assume that the initialization is
             * done by that operation correctly. All other checks regarding type safety, etc. are done by 
             * checkValidElementDeclaration(ElementDeclaration declaration)
             */
            isAssignmentInitialized = true;
        }
        return isAssignmentInitialized;
    } // TODO similar checks for elements a change identifier is assigned to and the parameters of a call (and tests!)
    
    
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
     * Searches in the given {@link Resource} for an {@link ElementDeclaration} with the given name and returns it.
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
     * @param ed1 the first {@link ElementDeclaration} to compare for equal types; should never be <code>null</code>
     * @param ed2 the second {@link ElementDeclaration} to compare for equal types; should never be <code>null</code>
     * @return <code>true</code>, if the given {@link ElementDeclaration}s have the same types; <code>false</code>
     *         otherwise
     */
    private boolean haveEqualTypes(ElementDeclaration ed1, ElementDeclaration ed2) {
        return ed1.getGenericTyp().equals(ed2.getGenericTyp()) 
                && ed1.getParameterType().equals(ed2.getParameterType())
                && ((ed1.getSet() == null) == (ed2.getSet() == null));
    }
    
    /**
     * Checks whether the type of the given {@link ElementDeclaration} is equal to the type of the return value of the
     * given {@link Operation}. This is the case, if {@link ElementDeclaration#getParameterType()} equals the resolved 
     * type of the operation.
     *  
     * @param elementDeclaration the {@link ElementDeclaration} to compare to the given {@link Operation} regarding
     *        element and return value type; should never be <code>null</code>
     * @param operation the {@link Operation} to compare to the given {@link ElementDeclaration} regarding
     *        element and return value type; should never be <code>null</code>
     * @return <code>true</code>, if the type of the given {@link ElementDeclaration} is equal to the type of the return
     *         value of the given {@link Operation}; <code>false</code> otherwise
     * @see #resolveToType(Operation)
     */
    private boolean haveEqualTypes(ElementDeclaration elementDeclaration, Operation operation) {
        // TODO it is not only the operation return type, but also, if that operation returns a single element or a set
        return elementDeclaration.getParameterType().equals(resolveToType(operation));
    }
    
    /**
     * Resolves the given {@link Operation} to its (return) type by resolving its inherent elements and {@link Call}s.
     * 
     * @param operation the {@link Operation} to resolve to its type; should never be <code>null</code>
     * @return the return type of the given {@link Operation} or <code>null</code>, if resolving that type failed, e.g.,
     *         due to unavailable elements in the {@link LanguageRegistry} or undefined language elements in the 
     *         {@link AnalysisDefinition} used as operation parameters
     * @see #resolveToType(EList)
     */
    private String resolveToType(Operation operation) {
        String resolvedType = null;
        
        /*
         * TODO handling element.call() via operation.getElement() currently missing as this is anyway not supported as
         * supposed. If such calls are realized, implement corresponding validation here.
         */
        
        resolvedType = resolveToType(operation.getCall());
        
        return resolvedType;
    }
    
    /**
     * Resolves the concatenated {@link Call}s in the given {@link EList} to the final (return) type and returns it. The
     * concatenation is interpreted in the order of the {@link Calls} in the {@link EList}. Hence, the dependencies of
     * the {@link Call}s within the {@link EList} are considered in that order during their individual resolution 
     * regarding their (return) types.
     *  
     * @param concatenatedCalls the {@link EList} of concatenated {@link Call}s for which the type shall be returned;
     *        should never be <code>null</code> nor <i>empty</i>
     * @return the (return) type of the concatenated {@link Call}s in the given {@link EList} as a {@link String} or
     *         <code>null</code>, if resolving the individual types or those of the calls parameters failed
     * @see #resolveToType(Call) 
     */
    private String resolveToType(EList<Call> concatenatedCalls) {
        String resolvedType = null;
        
        /*
         * TODO handling concatenated calls, like "a().b()", currently missing as this is anyway not supported as
         * supposed. If such calls are realized, implement corresponding validation here.
         */
        
        resolvedType = resolveToType(concatenatedCalls.get(0));
        
        return resolvedType;
    }
    
    /**
     * Resolves the given {@link Call} to its (return) type using its name and optional parameters and returns it.
     * 
     * @param call the {@link Call} for which the type shall be returned; should never be <code>null</code>
     * @return the (return) type of the given {@link Call} as a {@link String} or <code>null</code>, if resolving its
     *         type or those of its parameters failed
     * @see #resolveToTypes(EList)
     * @see LanguageRegistry#getCallReturnType(String, String[])
     */
    private String resolveToType(Call call) {
        String[] resolvedParameterTypes = null;
        if (call.getParameters() != null) {
            resolvedParameterTypes = resolveToTypes(call.getParameters().getParameterList());
        }
        return LanguageRegistry.INSTANCE.getCallReturnType(call.getName(), resolvedParameterTypes);
    }
    
    /**
     * Resolves each {@link Parameter} in the given {@link EList} to its type and returns them as a single array, which
     * contains the types in the order of the {@link Parameter}s in the {@link EList}.
     * 
     * @param parameterList the {@link EList} of {@link Parameter} for which the types shall be returned; should never
     *        be <code>null</code> nor <i>empty</i>
     * @return an array of {@link Strings}, which represent the types of the given {@link Parameter}s or 
     *         <code>null</code>, if resolving one of the parameters failed
     */
    private String[] resolveToTypes(EList<Parameter> parameterList) {
        String[] resolvedTypes = new String[parameterList.size()];
        boolean unresolvableTypeFound = false;
        int parameterCounter = 0;
        Parameter parameter;
        while (!unresolvableTypeFound && parameterCounter < parameterList.size()) {
            parameter = parameterList.get(parameterCounter);
            if (parameter.getText() != null && !parameter.getText().isBlank()) {
                // Parameter is a string with quotation marks
                resolvedTypes[parameterCounter] = "String";
            } else if (parameter.getElement() != null) {
                ElementDeclaration parameterElementDeclaration = getElementDeclaration(parameter.getElement(),
                        parameter.eResource());
                if (parameterElementDeclaration != null) {
                    // Parameter is another artifact, fragment, or result element
                    resolvedTypes[parameterCounter] = parameterElementDeclaration.getParameterType();
                } else {
                    // Parameter could not be resolved to a type due to missing element declaration
                    unresolvableTypeFound = true;
                    resolvedTypes = null;
                }
            } else if (parameter.getOperation() != null) {
                String resolvedOperationType = resolveToType(parameter.getOperation());
                if (resolvedOperationType != null) {
                    resolvedTypes[parameterCounter] = resolvedOperationType;
                } else {
                    // Parameter could not be resolved to a type due not resolvable operation
                    unresolvableTypeFound = true;
                    resolvedTypes = null;
                }
            } else {
                // Parameter could not be resolved to a type due to missing, incomplete, or unknown parameter definition
                unresolvableTypeFound = true;
                resolvedTypes = null;
            }
            parameterCounter++;
        }
        return resolvedTypes;
    }
    
}
