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
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.validation.Check;

import net.ssehub.integration.ChangeIdentifier;
import net.ssehub.integration.ElementType;
import net.ssehub.integration.LanguageElementException;
import net.ssehub.integration.LanguageRegistry;
import net.ssehub.integration.ParameterType;
import net.ssehub.integration.ParameterTypeInstance;
import net.ssehub.lea.AnalysisDefinition;
import net.ssehub.lea.Assignment;
import net.ssehub.lea.Call;
import net.ssehub.lea.ChangeIdentifierAssignment;
import net.ssehub.lea.ElementDeclaration;
import net.ssehub.lea.Iteration;
import net.ssehub.lea.LeaPackage;
import net.ssehub.lea.Operation;
import net.ssehub.lea.Parameter;
import net.ssehub.lea.ParameterList;

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
     * The reference to the {@link LanguageRegistry}.
     */
    private static final LanguageRegistry LANGUAGE_REGISTRY = LanguageRegistry.INSTANCE;
    
    /**
     * Checks the entire {@link AnalysisDefinition} for {@link ElementDeclaration}s, which have equal names. In such a
     * case, the duplicated names are marked with an error.
     * 
     * @param analysisDefinition the {@link AnalysisDefinition} in which the elements will be checked for duplicated
     *        names
     */
    @Check
    public void checkNoDuplicatedElementNames(AnalysisDefinition analysisDefinition) {
        EList<ElementDeclaration> elementDeclarations = analysisDefinition.getElementDeclarations();
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
     * Checks the given {@link ElementDeclaration} for being valid and reports invalidity by throwing errors.
     * 
     * @param elementDeclaration the {@link ElementDeclaration} to validate
     */
    @Check
    public void checkElementDeclaration(ElementDeclaration elementDeclaration) {
        ElementType genericType = getParameterTypeElementType(elementDeclaration.getGenericTyp());
        if (genericType == null) {
            error("Unsupported generic type \"" + elementDeclaration.getGenericTyp() + "\"", elementDeclaration,
                    LeaPackage.Literals.ELEMENT_DECLARATION__GENERIC_TYP);
        } else if (!LANGUAGE_REGISTRY.hasParameterType(genericType, elementDeclaration.getParameterType(), false)) {
            error("Unknown parameter type \"" + elementDeclaration.getParameterType() + "\"", elementDeclaration,
                    LeaPackage.Literals.ELEMENT_DECLARATION__PARAMETER_TYPE);
        } else if (!LANGUAGE_REGISTRY.hasParameterType(genericType, elementDeclaration.getParameterType(), true)) {
            error("Ambiguous parameter type \"" + elementDeclaration.getParameterType() + "\"", elementDeclaration,
                    LeaPackage.Literals.ELEMENT_DECLARATION__PARAMETER_TYPE);
        } else {
            Assignment initialization = elementDeclaration.getInitialization();
            if (initialization != null) {
                String errorMessage = null;
                if (initialization.getElement() != null) {
                    ElementDeclaration assignableElementDeclaration = getElementDeclaration(initialization.getElement(),
                            elementDeclaration.eResource());
                    if (assignableElementDeclaration == null) {
                        errorMessage = "Undefined element \"" + initialization.getElement() + "\"";
                    } else if (assignableElementDeclaration.getInitialization() == null) {
                        errorMessage = "Uninitialized element \"" + initialization.getElement() + "\"";
                    } else if (genericType != getParameterTypeElementType(
                            assignableElementDeclaration.getGenericTyp())) {
                        errorMessage = "Generic type mismatch: cannot assign \"" 
                                + assignableElementDeclaration.getGenericTyp() + "\" to \"" 
                                + elementDeclaration.getGenericTyp() + "\"";
                    } else if (!elementDeclaration.getParameterType().equals(
                            assignableElementDeclaration.getParameterType())) {
                        errorMessage = "Parameter type mismatch: cannot assign \"" 
                                + assignableElementDeclaration.getParameterType() + "\" to \"" 
                                + elementDeclaration.getParameterType() + "\"";
                    } else if (elementDeclaration.getSet() != null && assignableElementDeclaration.getSet() == null) {
                        errorMessage = "Set definition mismatch: cannot assign non-set element to set element";
                    }  else if (elementDeclaration.getSet() == null && assignableElementDeclaration.getSet() != null) {
                        errorMessage = "Set definition mismatch: cannot assign set element to non-set element";
                    }                    
                } else if (initialization.getOperation() != null) {
                    ParameterType declarationParameterType = LANGUAGE_REGISTRY.getParameterType(genericType,
                            elementDeclaration.getParameterType());
                    ParameterTypeInstance operationReturnType = getReturnType(initialization.getOperation());
                    if (operationReturnType == null) {
                        errorMessage = "Unknown operation";
                    } else if (!declarationParameterType.equals(operationReturnType.getParameterType())) {
                        errorMessage = "Type mismatch: cannot assign return type \"" 
                                + operationReturnType.getParameterType().getName() + "\" to parameter type \"" 
                                + elementDeclaration.getParameterType() + "\"";
                    } else if (elementDeclaration.getSet() != null && !operationReturnType.isSet()) {
                        errorMessage = "Set definition mismatch: cannot assign non-set return type to set element";
                    } else if (elementDeclaration.getSet() == null && operationReturnType.isSet()) {
                        errorMessage = "Set definition mismatch: cannot assign set return type to non-set element";
                    }
                } else {
                    errorMessage = "Incomplete element initialization";
                }
                // Show error with one of the error strings defined above for the initialization
                if (errorMessage != null) {                    
                    error(errorMessage, elementDeclaration, LeaPackage.Literals.ELEMENT_DECLARATION__INITIALIZATION);
                }
            }
        }
    }
    
    /**
     * Checks the given {@link Iteration} for being valid and reports invalidity by throwing errors.
     * 
     * @param iteration the {@link Iteration} to validate
     */
    @Check
    public void checkIteration(Iteration iteration) {
        String iterable = iteration.getIterable();
        ElementDeclaration iterableElementDeclaration = getElementDeclaration(iterable, iteration.eResource());
        if (iterableElementDeclaration == null) {
            error("Undefined element \"" + iterable + "\"", iteration, LeaPackage.Literals.ITERATION__ITERABLE);
        } else if (iterableElementDeclaration.getSet() == null) {
            error("Set definition mismatch: cannot iterate over non-set element \"" + iterable + "\"", iteration,
                    LeaPackage.Literals.ITERATION__ITERABLE);
        }
    }
    
    /**
     * Checks the given {@link ChangeIdentifierAssignment} for being valid and reports invalidity by throwing errors.
     * 
     * @param changeIdentifierAssignment the {@link ChangeIdentifierAssignment} to validate
     */
    @Check
    public void checkChangeIdentifierAssignment(ChangeIdentifierAssignment changeIdentifierAssignment) {
        if (!LANGUAGE_REGISTRY.hasChangeIdentifier(changeIdentifierAssignment.getIdentifier(), true)) {
            error("Unknown change identifier \"" + changeIdentifierAssignment.getIdentifier() + "\"",
                    changeIdentifierAssignment, LeaPackage.Literals.CHANGE_IDENTIFIER_ASSIGNMENT__IDENTIFIER);
        } else if (changeIdentifierAssignment.getElements() == null
                || changeIdentifierAssignment.getElements().isEmpty()) {
            error("Unused change identifier \"" + changeIdentifierAssignment.getIdentifier() + "\"",
                    changeIdentifierAssignment, LeaPackage.Literals.CHANGE_IDENTIFIER_ASSIGNMENT__IDENTIFIER);
        } else {
            ChangeIdentifier changeIdentifier = LANGUAGE_REGISTRY.getChangeIdentifier(
                    changeIdentifierAssignment.getIdentifier());
            EList<String> assignedElements = changeIdentifierAssignment.getElements();
            ElementDeclaration assignedElementDeclaration;
            ParameterType assignedElementParameterType;
            for (String assignedElement : assignedElements) {
                assignedElementDeclaration = getElementDeclaration(assignedElement,
                        changeIdentifierAssignment.eResource());
                if (assignedElementDeclaration == null) {
                    error("Undefined element \"" + assignedElement + "\"",
                            changeIdentifierAssignment, LeaPackage.Literals.CHANGE_IDENTIFIER_ASSIGNMENT__ELEMENTS);
                } else if (assignedElementDeclaration.getInitialization() == null) {
                    error("Uninitialized element \"" + assignedElement + "\"",
                            changeIdentifierAssignment, LeaPackage.Literals.CHANGE_IDENTIFIER_ASSIGNMENT__ELEMENTS);
                } else {
                    assignedElementParameterType = getParameterType(assignedElementDeclaration);
                    if (assignedElementParameterType != null
                            && !changeIdentifier.assignableTo(assignedElementParameterType)) {
                        error("Type mismatch: change idenifier not assignable to elements of type \"" 
                                + assignedElementParameterType.getName() + "\"", changeIdentifierAssignment,
                                LeaPackage.Literals.CHANGE_IDENTIFIER_ASSIGNMENT__ELEMENTS);
                    }
                }
            }
        }
    }
    
    /**
     * Checks the given {@link Operation} for being valid and reports invalidity by throwing errors.
     * 
     * @param operation the {@link Operation} to validate
     */
    @Check
    public void checkOperation(Operation operation) {
        ParameterTypeInstance memberParameterTypeInstance = null;
        String operationElement = operation.getElement();
        if (operationElement != null) {
            ElementDeclaration operationElementDeclaration = getElementDeclaration(operationElement,
                    operation.eResource());
            if (operationElementDeclaration == null) {
                error("Undefined element \"" + operationElement + "\"", operation,
                        LeaPackage.Literals.OPERATION__ELEMENT);
            } else {
                memberParameterTypeInstance = getParameterTypeInstance(operationElementDeclaration);
            }
        }
        EList<Call> operationCalls = operation.getCall();
        if (operationCalls != null && !operationCalls.isEmpty()) {
            boolean callsValid = true;
            Call operationCall;
            net.ssehub.integration.Call languageCall;
            int operationCallsCounter = 0;
            do {
                operationCall = operationCalls.get(operationCallsCounter);
                languageCall = getCall(operationCall);
                if (languageCall == null) {
                    callsValid = false;
                    error("Unknown call \"" + operationCall.getName() + "\"", operation, 
                            LeaPackage.Literals.OPERATION__CALL);
                } else if (memberParameterTypeInstance != null 
                        && !languageCall.isMemberOperationOf(memberParameterTypeInstance)) {
                    callsValid = false;
                    error("Undefined call \"" + operationCall.getName() + "\" for element \"" 
                        + memberParameterTypeInstance.getParameterType().getName() + "\"", operation, 
                        LeaPackage.Literals.OPERATION__CALL);
                } else {                    
                    memberParameterTypeInstance = languageCall.getReturnType();
                }
                operationCallsCounter++;
            } while (callsValid  && operationCallsCounter < operationCalls.size());
        } else {
            error("Incomplete operation", operation, LeaPackage.Literals.OPERATION__CALL);
        }
    }
    
    /**
     * Checks the given {@link Call} for being valid and reports invalidity by throwing errors.
     * 
     * @param call the {@link Call} to validate
     */
    @Check
    public void checkCall(Call call) {
        ParameterList callParameters = call.getParameters();
        ParameterTypeInstance[] languageCallParameters = null;
        boolean parametersValid = true;
        if (callParameters != null) {
            List<Parameter> callParameterList = callParameters.getParameterList();
            if (!callParameterList.isEmpty()) {                
                languageCallParameters = new ParameterTypeInstance[callParameterList.size()];
                ParameterTypeInstance callParameterTypeInstance;
                int languageCallParametersCounter = 0;
                for (Parameter callParameter : callParameterList) {
                    callParameterTypeInstance = getParameterTypeInstance(callParameter);
                    if (callParameterTypeInstance == null) {
                        error("Unknown parameter", call, LeaPackage.Literals.CALL__PARAMETERS);
                        languageCallParameters = null;
                        parametersValid = false;
                    } else {
                        languageCallParameters[languageCallParametersCounter] = callParameterTypeInstance;
                    }
                    languageCallParametersCounter++;
                }
            }
        }
        if (parametersValid) {
            if (!LANGUAGE_REGISTRY.hasCall(call.getName(), languageCallParameters, false)) {                
                error("Unknown call \"" + call.getName() + "\"", call, LeaPackage.Literals.CALL__NAME);
            } else if (!LANGUAGE_REGISTRY.hasCall(call.getName(), languageCallParameters, true)) {
                error("Ambiguous call \"" + call.getName() + "\"", call, LeaPackage.Literals.CALL__NAME);
            }
        }
    }
    
    /**
     * Returns the {@link ParameterTypeInstance} denoting the return type of the given {@link Operation}.
     * 
     * @param operation the {@link Operation} for which the return type should be returned
     * @return the {@link ParameterTypeInstance} denoting the return type of the given {@link Operation} or 
     *         <code>null</code>, if no corresponding {@link net.ssehub.integration.Call} exists in the
     *         {@link LanguageRegistry}
     */
    private ParameterTypeInstance getReturnType(Operation operation) {
        ParameterTypeInstance returnType = null;
        net.ssehub.integration.Call operationCall = getCall(operation);
        if (operationCall != null) {
            returnType = operationCall.getReturnType();
        }
        return returnType;
    }
    
    /**
     * Returns the {@link net.ssehub.integration.Call} the given {@link Operation} represents.
     * 
     * @param operation the {@link Operation} for which the corresponding {@link net.ssehub.integration.Call} should be
     *       returned
     * @return the {@link net.ssehub.integration.Call} the given {@link Operation} represents or <code>null</code>, if
     *         no such call exists in the {@link LanguageRegistry}
     */
    private net.ssehub.integration.Call getCall(Operation operation) {
        net.ssehub.integration.Call call = null;
        EList<Call> operationCalls = operation.getCall();
        if (operationCalls != null && !operationCalls.isEmpty()) {
            Call relevantOperationCall = operationCalls.get(operationCalls.size() - 1);
            ParameterTypeInstance[] languageCallParameters = getParameterTypeInstances(
                    relevantOperationCall.getParameters());
            List<net.ssehub.integration.Call> potentialLanguageCalls = LANGUAGE_REGISTRY.getCalls(
                    relevantOperationCall.getName(), languageCallParameters);
            if (potentialLanguageCalls != null) {
                ParameterTypeInstance memberParameterTypeInstance = null;
                if (operationCalls.size() > 1) {
                    // Language call must be a member operation of the return type of the call before the relevant one
                    net.ssehub.integration.Call previousCall = getCall(operationCalls.get(operationCalls.size() - 2));
                    if (previousCall != null) {                        
                        memberParameterTypeInstance = previousCall.getReturnType();
                    }
                } else if (operation.getElement() != null) {
                    // Language call must be a member operation of the operation element
                    memberParameterTypeInstance = getParameterTypeInstance(getElementDeclaration(operation.getElement(),
                            operation.eResource()));
                }
                if (memberParameterTypeInstance != null) {
                    // Filter for member operations of the memberParameterTypeInstance
                    Iterator<net.ssehub.integration.Call> potentialLanguageCallsIterator =
                            potentialLanguageCalls.iterator();
                    net.ssehub.integration.Call potentialCall;
                    while (potentialLanguageCallsIterator.hasNext()) {
                        potentialCall = potentialLanguageCallsIterator.next();
                        if (!potentialCall.isMemberOperationOf(memberParameterTypeInstance)) {
                            potentialLanguageCallsIterator.remove();
                        }
                    }
                }
                if (potentialLanguageCalls.size() == 1) {
                    call = potentialLanguageCalls.get(0);
                }
            }
        }                
        return call;
    }
    
    /**
     * Returns the {@link net.ssehub.integration.Call} the given {@link Call} represents.
     * 
     * @param call the {@link Call} for which the {@link net.ssehub.integration.Call} should be returned
     * @return the {@link net.ssehub.integration.Call} the given {@link Call} represents or <code>null</code>, if the
     *         given {@link Call} is <code>null</code> or no such (unique) call exists in the {@link LanguageRegistry}
     */
    private net.ssehub.integration.Call getCall(Call call) {
        net.ssehub.integration.Call languageCall = null;
        if (call != null) {
            ParameterTypeInstance[] languageCallParameters = getParameterTypeInstances(call.getParameters());
            languageCall = LANGUAGE_REGISTRY.getCall(call.getName(), languageCallParameters);
        }
        return languageCall;
    }
    
    /**
     * Returns the array of {@link ParameterTypeInstance}s the elements of the given {@link ParameterList} represent.
     * 
     * @param parameterContainer the {@link ParameterList} that provides the elements for which the
     *        {@link ParameterTypeInstance}s should be returned
     * @return the array of {@link ParameterTypeInstance}s the elements of the given {@link ParameterList} represent or
     *         <code>null</code>, if the {@link ParameterList} is <code>null</code> or does not contain any elements
     */
    private ParameterTypeInstance[] getParameterTypeInstances(ParameterList parameterContainer) {
        ParameterTypeInstance[] parameterTypeInstances = null;
        if (parameterContainer != null) {
            EList<Parameter> parameterList = parameterContainer.getParameterList();
            if (parameterList != null && !parameterList.isEmpty()) {
                parameterTypeInstances = new ParameterTypeInstance[parameterList.size()];
                ParameterTypeInstance parameterTypeInstance;
                int parameterCounter = 0;
                do {
                    parameterTypeInstance = getParameterTypeInstance(parameterList.get(parameterCounter));
                    if (parameterTypeInstance != null) {
                        parameterTypeInstances[parameterCounter] = parameterTypeInstance;
                    } else {
                        parameterTypeInstances = null;
                    }
                    parameterCounter++;
                } while (parameterTypeInstance != null && parameterCounter < parameterList.size());
            }
        }
        return parameterTypeInstances;
    }
    
    /**
     * Returns the {@link ParameterTypeInstance} the given {@link Parameter} represents.
     * 
     * @param parameter the {@link Parameter} for which the {@link ParameterTypeInstance} should be returned
     * @return the {@link ParameterTypeInstance} the given {@link Parameter} represents or <code>null</code>, if the
     *         {@link Parameter} is <code>null</code> or creating a corresponding {@link ParameterTypeInstance} failed
     */
    private ParameterTypeInstance getParameterTypeInstance(Parameter parameter) {
        ParameterTypeInstance parameterTypeInstance = null;
        if (parameter != null) {
            try {                
                if (parameter.getText() != null) {
                    parameterTypeInstance = new ParameterTypeInstance(LANGUAGE_REGISTRY.getParameterType("String"),
                            false);
                } else if (parameter.getElement() != null) {
                    ElementDeclaration parameterElementDeclaration = getElementDeclaration(parameter.getElement(),
                            parameter.eResource());
                    if (parameterElementDeclaration != null) {
                        boolean isSet = (parameterElementDeclaration.getSet() != null);
                        parameterTypeInstance = new ParameterTypeInstance(getParameterType(parameterElementDeclaration),
                                isSet);
                    }
                } else if (parameter.getOperation() != null) {
                    net.ssehub.integration.Call parameterCall = getCall(parameter.getOperation());
                    if (parameterCall != null) {
                        parameterTypeInstance = parameterCall.getReturnType();
                    }
                }
            } catch (LanguageElementException e) {
                // TODO Where to put such error message?
                System.err.println("Cannot create parameter type instance for parameter \"" + parameter + "\"");
                e.printStackTrace();
            }
        }
        return parameterTypeInstance;
    }
    
    /**
     * Returns the {@link ParameterTypeInstance} defined by the given {@link ElementDeclaration}.
     * 
     * @param elementDeclaration the {@link ElementDeclaration} from which the {@link ParameterTypeInstance} should be
     *        returned
     * @return the {@link ParameterTypeInstance} defined by the given {@link ElementDeclaration} or <code>null</code>,
     *         if the given {@link ElementDeclaration} is <code>null</code> or no such type exists in the
     *         {@link LanguageRegistry}
     */
    private ParameterTypeInstance getParameterTypeInstance(ElementDeclaration elementDeclaration) {
        ParameterTypeInstance parameterTypeInstance = null;
        if (elementDeclaration != null) {            
            boolean isSet = (elementDeclaration.getSet() != null);
            try {
                parameterTypeInstance = new ParameterTypeInstance(getParameterType(elementDeclaration), isSet);
            } catch (LanguageElementException e) {
                // TODO Where to put such error message?
                System.err.println("Cannot create parameter type instance for call in validator");
                e.printStackTrace();
            }
        }
        return parameterTypeInstance;
    }
    
    /**
     * Returns the {@link ParameterType} defined by the given {@link ElementDeclaration}.
     * 
     * @param elementDeclaration the {@link ElementDeclaration} from which the {@link ParameterType} should be returned
     * @return the {@link ParameterType} defined by the given {@link ElementDeclaration} or <code>null</code>, if the
     *         given {@link ElementDeclaration} is <code>null</code> or no such type exists in the
     *         {@link LanguageRegistry}
     */
    private ParameterType getParameterType(ElementDeclaration elementDeclaration) {
        ParameterType parameterType = null;
        if (elementDeclaration != null) {            
            ElementType parameterTypeElementType = getParameterTypeElementType(elementDeclaration.getGenericTyp());
            if (parameterTypeElementType != null 
                    && LANGUAGE_REGISTRY.hasParameterType(parameterTypeElementType,
                            elementDeclaration.getParameterType(), true)) {
                parameterType = LANGUAGE_REGISTRY.getParameterType(elementDeclaration.getParameterType());
            }
        }
        return parameterType;
    }
    
    
    /**
     * Returns the {@link ElementType} described by the given {@link String}. The mapping is as follows:
     * <ul>
     * <li>"Artifact" returns {@link ElementType#ARTIFACT_PARAMETER_TYPE}</li>
     * <li>"Fragment" returns {@link ElementType#FRAGMENT_PARAMETER_TYPE}</li>
     * <li>"Result" returns {@link ElementType#RESULT_PARAMETER_TYPE}</li>
     * <li>All other inputs return <code>null</code></li>
     * </ul>
     * @param elementTypeString the {@link String} describing the {@link ElementType} to return
     * @return the {@link ElementType} or <code>null</code> as described above
     */
    private ElementType getParameterTypeElementType(String elementTypeString) {
        ElementType elementType;
        switch(elementTypeString) {
        case "Artifact":
            elementType = ElementType.ARTIFACT_PARAMETER_TYPE;
            break;
        case "Fragment":
            elementType = ElementType.FRAGMENT_PARAMETER_TYPE;
            break;
        case "Result":
            elementType = ElementType.RESULT_PARAMETER_TYPE;
            break;
        default:
            elementType = null;
            break;
        }
        return elementType;
    }
    
    /**
     * Searches in the given {@link Resource} for an {@link ElementDeclaration} with the given name and returns it.
     * 
     * @param name the name of the {@link ElementDeclaration} to be found in the given {@link Resource}
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
    
}
