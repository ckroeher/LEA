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


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This class represents the singleton language registry, which contains all (external) language elements available for
 * defining analyses.
 * 
 * @author Christian Kroeher
 *
 */
public class LanguageRegistry extends AbstractLanguageRegistry {

    /**
     * The singleton instance of this {@link LanguageRegistry}.
     */
    public static final LanguageRegistry INSTANCE = new LanguageRegistry();
    
    /**
     * Constructs the singleton instance of this {@link LanguageRegistry}.
     */
    private LanguageRegistry() {
        super();
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
     * Checks whether a(n unique) {@link ParameterType} of the type {@link ElementType#ARTIFACT_PARAMETER_TYPE} with the
     * given name is available.<br>
     * <br>
     * This method initially treats the given name as simple name and returns <code>true</code>, if a single
     * {@link ParameterType} of the type {@link ElementType#ARTIFACT_PARAMETER_TYPE} matches that simple name 
     * independent of the value of <code>isUnique</code>. If this initial check does not result in an unique match, this
     * method proceeds depending on the result as follows:
     * <ul>
     * <li>The <b>initial check does not result in any matches</b>: this method retries its check treating the given
     *     name as fully-qualified name, which must result in a single match (returns <code>true</code>) or none 
     *     (returns <code>false</code>) independent of the value of <code>isUnique</code>.</li>
     * <li>The <b>initial check results in multiple matches</b>:</li>
     *     <ul>
     *     <li>The value of <code>isUnique</code> is <code>true</code>: this method returns <code>false</code></li>
     *     <li>The value of <code>isUnique</code> is <code>false</code>: this method returns <code>true</code></li>
     *     </ul>
     * </ul>
     * 
     * @param name the name of the (unique) {@link ParameterType} of the type 
     *        {@link ElementType#ARTIFACT_PARAMETER_TYPE} to search for
     * @param isUnique must be <code>true</code> to further check whether a {@link ParameterType} matching the given
     *        name is unique; <code>false</code> otherwise 
     * @return <code>true</code>, if a(n unique) {@link ParameterType} of the type 
     *         {@link ElementType#ARTIFACT_PARAMETER_TYPE} with the given name is available; <code>false</code>
     *         otherwise
     */
    public boolean hasArtifactParameterType(String name, boolean isUnique) {
        return hasParameterType(ElementType.ARTIFACT_PARAMETER_TYPE, name, isUnique);
    }
    
    /**
     * Checks whether a(n unique) {@link ParameterType} of the type {@link ElementType#FRAGMENT_PARAMETER_TYPE} with the
     * given name is available.<br>
     * <br>
     * This method initially treats the given name as simple name and returns <code>true</code>, if a single
     * {@link ParameterType} of the type {@link ElementType#FRAGMENT_PARAMETER_TYPE} matches that simple name 
     * independent of the value of <code>isUnique</code>. If this initial check does not result in an unique match, this
     * method proceeds depending on the result as follows:
     * <ul>
     * <li>The <b>initial check does not result in any matches</b>: this method retries its check treating the given
     *     name as fully-qualified name, which must result in a single match (returns <code>true</code>) or none 
     *     (returns <code>false</code>) independent of the value of <code>isUnique</code>.</li>
     * <li>The <b>initial check results in multiple matches</b>:</li>
     *     <ul>
     *     <li>The value of <code>isUnique</code> is <code>true</code>: this method returns <code>false</code></li>
     *     <li>The value of <code>isUnique</code> is <code>false</code>: this method returns <code>true</code></li>
     *     </ul>
     * </ul>
     * 
     * @param name the name of the (unique) {@link ParameterType} of the type 
     *        {@link ElementType#FRAGMENT_PARAMETER_TYPE} to search for
     * @param isUnique must be <code>true</code> to further check whether a {@link ParameterType} matching the given
     *        name is unique; <code>false</code> otherwise 
     * @return <code>true</code>, if a(n unique) {@link ParameterType} of the type 
     *         {@link ElementType#FRAGMENT_PARAMETER_TYPE} with the given name is available; <code>false</code>
     *         otherwise
     */
    public boolean hasFragmentParameterType(String name, boolean isUnique) {
        return hasParameterType(ElementType.FRAGMENT_PARAMETER_TYPE, name, isUnique);
    }
    
    /**
     * Checks whether a(n unique) {@link ParameterType} of the type {@link ElementType#RESULT_PARAMETER_TYPE} with the
     * given name is available.<br>
     * <br>
     * This method initially treats the given name as simple name and returns <code>true</code>, if a single
     * {@link ParameterType} of the type {@link ElementType#RESULT_PARAMETER_TYPE} matches that simple name 
     * independent of the value of <code>isUnique</code>. If this initial check does not result in an unique match, this
     * method proceeds depending on the result as follows:
     * <ul>
     * <li>The <b>initial check does not result in any matches</b>: this method retries its check treating the given
     *     name as fully-qualified name, which must result in a single match (returns <code>true</code>) or none 
     *     (returns <code>false</code>) independent of the value of <code>isUnique</code>.</li>
     * <li>The <b>initial check results in multiple matches</b>:</li>
     *     <ul>
     *     <li>The value of <code>isUnique</code> is <code>true</code>: this method returns <code>false</code></li>
     *     <li>The value of <code>isUnique</code> is <code>false</code>: this method returns <code>true</code></li>
     *     </ul>
     * </ul>
     * 
     * @param name the name of the (unique) {@link ParameterType} of the type 
     *        {@link ElementType#RESULT_PARAMETER_TYPE} to search for
     * @param isUnique must be <code>true</code> to further check whether a {@link ParameterType} matching the given
     *        name is unique; <code>false</code> otherwise 
     * @return <code>true</code>, if a(n unique) {@link ParameterType} of the type 
     *         {@link ElementType#RESULT_PARAMETER_TYPE} with the given name is available; <code>false</code>
     *         otherwise
     */
    public boolean hasResultParameterType(String name, boolean isUnique) {
        return hasParameterType(ElementType.RESULT_PARAMETER_TYPE, name, isUnique);
    }
    
    /**
     * Checks whether a(n unique) {@link ParameterType} of the given {@link ElementType} with the given name is
     * available.<br>
     * <br>
     * This method initially treats the given name as simple name and returns <code>true</code>, if a single
     * {@link ParameterType} of the given {@link ElementType} matches that simple name independent of the value of
     * <code>isUnique</code>. If this initial check does not result in an unique match, this method proceeds depending
     * on the result as follows:
     * <ul>
     * <li>The <b>initial check does not result in any matches</b>: this method retries its check treating the given
     *     name as fully-qualified name, which must result in a single match (returns <code>true</code>) or none 
     *     (returns <code>false</code>) independent of the value of <code>isUnique</code>.</li>
     * <li>The <b>initial check results in multiple matches</b>:</li>
     *     <ul>
     *     <li>The value of <code>isUnique</code> is <code>true</code>: this method returns <code>false</code></li>
     *     <li>The value of <code>isUnique</code> is <code>false</code>: this method returns <code>true</code></li>
     *     </ul>
     * </ul>
     * 
     * @param elementType the {@link ElementType} of the (unique) {@link ParameterType} to search for
     * @param name the name of the (unique) {@link ParameterType} to search for
     * @param isUnique must be <code>true</code> to further check whether a {@link ParameterType} matching the given
     *        element type and name is unique; <code>false</code> otherwise 
     * @return <code>true</code>, if a(n unique) {@link ParameterType} of the given {@link ElementType} with the given
     *         name is available; <code>false</code> otherwise
     */
    private boolean hasParameterType(ElementType elementType, String name, boolean isUnique) {
        boolean hasParameterType = false;
        // Initial check treating the give name as simple name
        List<ParameterType> availableParameterTypes = parameterTypes.get(name);
        if (availableParameterTypes != null) {
            if (availableParameterTypes.size() == 1) {
                // Initial check results in single match
                if (availableParameterTypes.get(0).getElementType() == elementType) {
                    // Found a single parameter type with the given element type and name
                    hasParameterType = true;
                }
            } else {
                // Initial check results in multiple matches
                boolean doBreak = false;
                int availableParameterTypesCounter = 0;
                while (!doBreak && availableParameterTypesCounter < availableParameterTypes.size()) {
                    if (availableParameterTypes.get(availableParameterTypesCounter).getElementType() == elementType) {
                        if (!isUnique) {
                            /*
                             * Found first parameter type with the given element type and name, which must not be
                             * unique. Hence, abort search at this point with positive return value.
                             */
                            hasParameterType = true;
                            doBreak = true;
                        } else {
                            if (!hasParameterType) {
                                /*
                                 * Found first parameter type with the given element type and name, which must be
                                 * unique. Hence, continue search to ensure uniqueness.
                                 */
                                hasParameterType = true;
                            } else {
                                /*
                                 * Found second parameter type with the given element type and name, while demanding for
                                 * uniqueness. Hence, abort search at this point with negative return value.
                                 */
                                hasParameterType = false;
                                doBreak = true;
                            }
                        }
                    }
                    availableParameterTypesCounter++;
                }
            }
        } else {
            // Initial check does not result in any matches: Retry check treating the give name as fully-qualified name
            int indexOfLastDot = name.lastIndexOf(".");
            if (indexOfLastDot != -1) {
                String simpleName = name.substring(indexOfLastDot + 1);
                availableParameterTypes = parameterTypes.get(simpleName);
                if (availableParameterTypes != null) {
                    int availableParameterTypesCounter = 0;
                    while (!hasParameterType && availableParameterTypesCounter < availableParameterTypes.size()) {
                        if (availableParameterTypes.get(availableParameterTypesCounter).getElementType() == elementType
                                && availableParameterTypes.get(availableParameterTypesCounter).getFullyQualifiedName()
                                    .equals(name)) {
                            /*
                             * Found first parameter type with the given element type and fully-qualified name, which is
                             * unique by definition. Hence, abort search at this point with positive return value.
                             */
                            hasParameterType = true;
                        }
                        availableParameterTypesCounter++;
                    }
                }
            }
        }
        return hasParameterType;
    }
    
    /**
     * Checks whether a(n unique) {@link ChangeIdentifier} with the given name is available.<br>
     * <br>
     * This method initially treats the given name as simple name and returns <code>true</code>, if a single
     * {@link ChangeIdentifier} matches that simple name independent of the value of <code>isUnique</code>. If this
     * initial check does not result in an unique match, this method proceeds depending on the result as follows:
     * <ul>
     * <li>The <b>initial check does not result in any matches</b>: this method retries its check treating the given
     *     name as fully-qualified name, which must result in a single match (returns <code>true</code>) or none 
     *     (returns <code>false</code>) independent of the value of <code>isUnique</code>.</li>
     * <li>The <b>initial check results in multiple matches</b>:</li>
     *     <ul>
     *     <li>The value of <code>isUnique</code> is <code>true</code>: this method returns <code>false</code></li>
     *     <li>The value of <code>isUnique</code> is <code>false</code>: this method returns <code>true</code></li>
     *     </ul>
     * </ul>
     * 
     * @param name the name of the (unique) {@link ChangeIdentifier} to search for
     * @param isUnique must be <code>true</code> to further check whether a {@link ChangeIdentifier} matching the given
     *        name is unique; <code>false</code> otherwise 
     * @return <code>true</code>, if a(n unique) {@link ChangeIdentifier} with the given name is available;
     *         <code>false</code> otherwise
     */
    public boolean hasChangeIdentifier(String name, boolean isUnique) {
        boolean hasChangeIdentifier = false;
        // Initial check treating the give name as simple name
        List<ChangeIdentifier> availableChangeIdentifiers = changeIdentifiers.get(name);
        if (availableChangeIdentifiers != null) {
            if (availableChangeIdentifiers.size() == 1) {
                // Found a single change identifier with the given name
                hasChangeIdentifier = true;
            } else {
                // Initial check results in multiple matches
                boolean doBreak = false;
                int availableChangeIdentifiersCounter = 0;
                while (!doBreak && availableChangeIdentifiersCounter < availableChangeIdentifiers.size()) {
                    if (!isUnique) {
                        /*
                         * Found first change identifier with the given name, which must not be unique. Hence, abort
                         * search at this point with positive return value.
                         */
                        hasChangeIdentifier = true;
                        doBreak = true;
                    } else {
                        if (!hasChangeIdentifier) {
                            /*
                             * Found first change identifier with the given name, which must be unique. Hence, continue
                             * search to ensure uniqueness.
                             */
                            hasChangeIdentifier = true;
                        } else {
                            /*
                             * Found second change identifier with the given name, while demanding for uniqueness.
                             * Hence, abort search at this point with negative return value.
                             */
                            hasChangeIdentifier = false;
                            doBreak = true;
                        }
                    }
                    availableChangeIdentifiersCounter++;
                }
            }
        } else {
            // Initial check does not result in any matches: Retry check treating the give name as fully-qualified name
            int indexOfLastDot = name.lastIndexOf(".");
            if (indexOfLastDot != -1) {
                String simpleName = name.substring(indexOfLastDot + 1);
                availableChangeIdentifiers = changeIdentifiers.get(simpleName);
                if (availableChangeIdentifiers != null) {
                    int availableParameterTypesCounter = 0;
                    while (!hasChangeIdentifier && availableParameterTypesCounter < availableChangeIdentifiers.size()) {
                        if (availableChangeIdentifiers.get(availableParameterTypesCounter).getFullyQualifiedName()
                                .equals(name)) {
                            /*
                             * Found first change identifier with the given fully-qualified name, which is unique by
                             * definition. Hence, abort search at this point with positive return value.
                             */
                            hasChangeIdentifier = true;
                        }
                        availableParameterTypesCounter++;
                    }
                }
            }
        }
        return hasChangeIdentifier;
    }
    
    /**
     * Checks whether a(n unique) {@link ChangeIdentifier} with the given name is available, which is assignable to the
     * given {@link ParameterType}s<br>
     * <br>
     * This method initially treats the given name as simple name. Each {@link ChangeIdentifier} matching that simple
     * name is checked for being assignable to the given {@link ParameterType}s, which results in a return value of:
     * <ul>
     * <li><code>true</code>, if the value of <code>isUnique</code> is <code>true</code> and only a single
     *     {@link ChangeIdentifier} matches the given name and assignable elements</li>
     * <li><code>true</code>, if the value of <code>isUnique</code> is <code>false</code> and at least one 
     *     {@link ChangeIdentifier} matches the given name and assignable elements</li>
     * <li><code>false</code>, if the value of <code>isUnique</code> is <code>true</code> and more than one
     *     {@link ChangeIdentifier} matches the given name and assignable elements</li>    
     * <li><code>false</code>, if some {@link ChangeIdentifier}s match the given name, but none of them matches the 
     *     given assignable elements</li>
     * </ul>  
     * 
     * If the initial treatment of the given name as simple name results in an empty set of {@link ChangeIdentifier}s,
     * this method proceeds by treating the given name as fully-qualified name. This results in either a single
     * {@link ChangeIdentifier} (as fully-qualified names identify unique change identifiers by definition) or none.
     * Hence, the return value of this method is then only <code>true</code>, if that single {@link ChangeIdentifier}
     * matches the given fully-qualified name and assignable elements independent of the value of <code>isUnique</code>.
     * 
     * @param name the name of the (unique) {@link ChangeIdentifier} to search for
     * @param assignableElements the array of {@link ParameterType}s denoting the elements to which the 
     *        {@link ChangeIdentifier} to search for should be assignable to
     * @param isUnique must be <code>true</code> to further check whether a {@link ChangeIdentifier} matching the given
     *        name and assignable elements is unique; <code>false</code> otherwise 
     * @return <code>true</code>, if a(n unique) {@link ChangeIdentifier} with the given name is available, which is
     *         assignable to the given {@link ParameterType}s; <code>false</code> otherwise
     */
    public boolean hasChangeIdentifier(String name, ParameterType[] assignableElements, boolean isUnique) {
        boolean hasChangeIdentifier = false;
        // Initial check treating the give name as simple name
        List<ChangeIdentifier> availableChangeIdentifiers = changeIdentifiers.get(name);
        if (availableChangeIdentifiers != null) {
            /*
             * Initial check results in one or multiple matches regarding the given name, but the assignable elements
             * are still unclear. 
             */
            boolean doBreak = false;
            int availableChangeIdentifiersCounter = 0;
            while (!doBreak && availableChangeIdentifiersCounter < availableChangeIdentifiers.size()) {
                if (availableChangeIdentifiers.get(availableChangeIdentifiersCounter)
                        .assignableTo(assignableElements)) {
                    if (!isUnique) {
                        /*
                         * Found first change identifier with the given name and assignable elements, which must not be
                         * unique. Hence, abort search at this point with positive return value.
                         */
                        hasChangeIdentifier = true;
                        doBreak = true;
                    } else {
                        if (!hasChangeIdentifier) {
                            /*
                             * Found first change identifier with the given name and assignable elements, which must be
                             * unique. Hence, continue search to ensure uniqueness.
                             */
                            hasChangeIdentifier = true;
                        } else {
                            /*
                             * Found second change identifier with the given name and assignable elements, while
                             * demanding for uniqueness. Hence, abort search at this point with negative return value.
                             */
                            hasChangeIdentifier = false;
                            doBreak = true;
                        }
                    }                    
                }
                availableChangeIdentifiersCounter++;
            }
        } else {
            // Initial check does not result in any matches: Retry check treating the give name as fully-qualified name
            int indexOfLastDot = name.lastIndexOf(".");
            if (indexOfLastDot != -1) {
                String simpleName = name.substring(indexOfLastDot + 1);
                availableChangeIdentifiers = changeIdentifiers.get(simpleName);
                if (availableChangeIdentifiers != null) {
                    int availableParameterTypesCounter = 0;
                    while (!hasChangeIdentifier && availableParameterTypesCounter < availableChangeIdentifiers.size()) {
                        if (availableChangeIdentifiers.get(availableParameterTypesCounter).getFullyQualifiedName()
                                    .equals(name)
                                && availableChangeIdentifiers.get(availableParameterTypesCounter)
                                    .assignableTo(assignableElements)) {
                            /*
                             * Found first change identifier with the given fully-qualified name and assignable
                             * elements, which is unique by definition. Hence, abort search at this point with positive
                             * return value.
                             */
                            hasChangeIdentifier = true;
                        }
                        availableParameterTypesCounter++;
                    }
                }
            }
        }
        return hasChangeIdentifier;
    }
    
    /**
     * Checks whether a(n unique) {@link Call} of the type {@link ElementType#OPERATION} with the given name is
     * available.<br>
     * <br>
     * This method initially treats the given name as simple name. If no {@link Call}s match that simple name, this
     * method retries detecting available {@link Call}s treating the given name as fully-qualified name. If in any of
     * these case one or more {@link Call}s are detected, their {@link ElementType} is compared against 
     * {@link ElementType#OPERATION}. This results in the following return value:
     * <ul>
     * <li><code>true</code>, if <code>isUnique</code> is <code>true</code> and exactly one {@link Call} matches
     *     the {@link ElementType#OPERATION} and the given name</li>
     * <li><code>true</code>, if <code>isUnique</code> is <code>false</code> and at least one {@link Call} matches
     *     the {@link ElementType#OPERATION} and the given name</li>
     * <li><code>false</code>, in all other cases</li>
     * </ul>
     * 
     * @param name the name of the (unique) {@link Call} of the type {@link ElementType#OPERATION} to search for
     * @param isUnique must be <code>true</code> to further check whether a {@link Call} matching the given name is
     *        unique; <code>false</code> otherwise 
     * @return <code>true</code>, if a(n unique) {@link Call} of the type {@link ElementType#OPERATION} with the given
     *         name is available; <code>false</code> otherwise
     */
    public boolean hasOperation(String name, boolean isUnique) {
        boolean hasOperation = false;
        List<Call> availableCall = getCalls(ElementType.OPERATION, name);
        if (availableCall != null && (isUnique && availableCall.size() == 1)) {
            hasOperation = true;
        }
        return hasOperation;
    }
    
    public boolean hasOperation(String name, ParameterType returnType, boolean isUnique) {
        boolean hasOperation = false;
        List<Call> availableCall = getCalls(ElementType.OPERATION, name, returnType);
        if (availableCall != null && (isUnique && availableCall.size() == 1)) {
            hasOperation = true;
        }
        return hasOperation;
    }
    
    public boolean hasOperation(String name, ParameterType[] parameters, boolean isUnique) {
        boolean hasOperation = false;
        List<Call> availableCall = getCalls(ElementType.OPERATION, name, parameters);
        if (availableCall != null && (isUnique && availableCall.size() == 1)) {
            hasOperation = true;
        }
        return hasOperation;
    }
    
    public boolean hasOperation(String name, ParameterType returnType, ParameterType[] parameters, boolean isUnique) {
        boolean hasOperation = false;
        List<Call> availableCall = getCalls(ElementType.OPERATION, name, returnType, parameters);
        if (availableCall != null && (isUnique && availableCall.size() == 1)) {
            hasOperation = true;
        }
        return hasOperation;
    }
    
    /**
     * Checks whether a(n unique) {@link Call} of the type {@link ElementType#OPERATION} with the given name is
     * available, which is a member operation (checked via {@link Call#isMemberOperation()}.<br>
     * <br>
     * This method initially treats the given name as simple name. If no {@link Call}s match that simple name, this
     * method retries detecting available {@link Call}s treating the given name as fully-qualified name. If in any of
     * these case one or more {@link Call}s are detected, their {@link ElementType} is compared against 
     * {@link ElementType#OPERATION}. Further, each {@link Call} of the type {@link ElementType#OPERATION} is checked
     * for being a member operation. This results in the following return value:
     * <ul>
     * <li><code>true</code>, if <code>isUnique</code> is <code>true</code> and exactly one {@link Call} matches
     *     the {@link ElementType#OPERATION}, the given name, and is a member operation</li>
     * <li><code>true</code>, if <code>isUnique</code> is <code>false</code> and at least one {@link Call} matches
     *     the {@link ElementType#OPERATION}, the given name, and is a member operation</li>
     * <li><code>false</code>, in all other cases</li>
     * </ul>
     * 
     * @param name the name of the (unique) {@link Call} of the type {@link ElementType#OPERATION} to search for
     * @param isUnique must be <code>true</code> to further check whether a {@link Call} matching the given name is
     *        unique; <code>false</code> otherwise 
     * @return <code>true</code>, if a(n unique) {@link Call} of the type {@link ElementType#OPERATION} with the given
     *         name is available, which is a member operation; <code>false</code> otherwise
     */
    public boolean hasMemberOperation(String name, boolean isUnique) {
        boolean hasMemberOperation = false;
        List<Call> availableMemberOperations = getMemberOperations(name);
        if (availableMemberOperations != null && (isUnique && availableMemberOperations.size() == 1)) {
            hasMemberOperation = true;
        }
        return hasMemberOperation;
    }
    
    
    
    /**
     * Returns all available {@link Call}s of the type {@link ElementType#OPERATION} with the given name, which are a
     * member operation (checked via {@link Call#isMemberOperation()}.<br>
     * <br>
     * This method first detects all available {@link Call}s with the given name by calling 
     * {@link #getCalls(ElementType, String)}. Based on that return value, it filters those {@link Call}s, which are a
     * member operation.
     * 
     * @param name the name of the {@link Call}s to search for; should never be <code>null</code>
     * @return the {@link List} of available {@link Call}s of the type {@link ElementType#OPERATION} with the given
     *         name, which are a member operation, or <code>null</code>, if no such {@link Call}s are available 
     */
    private List<Call> getMemberOperations(String name) {
        List<Call> availableMemberOperations = null;
        List<Call> potentialMemberOperations = getCalls(ElementType.OPERATION, name);
        if (potentialMemberOperations != null) {
            for (Call potentialMemberOperation : potentialMemberOperations) {
                if (potentialMemberOperation.isMemberOperation()) {
                    if (availableMemberOperations == null) {
                        availableMemberOperations = new ArrayList<Call>();
                    }
                    availableMemberOperations.add(potentialMemberOperation);
                }
            }
        }
        return availableMemberOperations;
    }
    
    private List<Call> getCalls(ElementType elementType, String name, ParameterType returnType) {
        List<Call> availableCalls = null;
        
        return availableCalls;
    }
    
    private List<Call> getCalls(ElementType elementType, String name, ParameterType[] parameters) {
        List<Call> availableCalls = null;
        
        return availableCalls;
    }
    
    private List<Call> getCalls(ElementType elementType, String name, ParameterType returnType, 
            ParameterType[] parameters) {
        List<Call> availableCalls = null;
        
        return availableCalls;
    }
    
    /**
     * Returns all available {@link Call}s with the given {@link ElementType} and name.<br>
     * <br>
     * This method first detects all available {@link Call}s with the given name by calling {@link #getCalls(String)}.
     * Based on that return value, it filters those {@link Call}s matching the given {@link ElementType}.
     * 
     * @param elementType the {@link ElementType} of the {@link Call}s to search for; should never be <code>null</code>
     * @param name the name of the {@link Call}s to search for; should never be <code>null</code>
     * @return the {@link List} of available {@link Call}s with the given {@link ElementType} and or <code>null</code>,
     *         if no such {@link Call}s are available 
     */
    private List<Call> getCalls(ElementType elementType, String name) {
        List<Call> availableCalls = null;
        List<Call> potentialCalls = getCalls(name);
        if (potentialCalls != null) {
            for (Call potentialCall : potentialCalls) {
                if (potentialCall.getElementType() == elementType) {
                    if (availableCalls == null) {
                        availableCalls = new ArrayList<Call>();
                    }
                    availableCalls.add(potentialCall);
                }
            }
        }
        return availableCalls;
    }
    
    /**
     * Returns all available {@link Call}s with the given name independent of their {@link ElementType}.<br>
     * <br>
     * This method initially treats the given name as simple name and returns all available {@link Call}s matching that
     * simple name. If this initial treatment does not detect any {@link Call}s, this method retries detecting available
     * {@link Call}s treating the given name as fully-qualified name.
     * 
     * @param name the name of the {@link Call}s to search for; should never be <code>null</code>
     * @return the {@link List} of available {@link Call}s with the given name independent of their {@link ElementType}
     *         or <code>null</code>, if no such {@link Call}s are available 
     */
    private List<Call> getCalls(String name) {
        // Get available calls treating the given name as simple name
        List<Call> availableCalls = calls.get(name);
        if (availableCalls == null) {
            // Get available calls treating the given name as fully-qualified name
            int indexOfLastDot = name.lastIndexOf(".");
            if (indexOfLastDot != -1) {
                String simpleName = name.substring(indexOfLastDot + 1);
                List<Call> potentialCalls = calls.get(simpleName);
                int potentialCallsCounter = 0;
                while (availableCalls == null && potentialCallsCounter < potentialCalls.size()) {
                    if (potentialCalls.get(potentialCallsCounter).getFullyQualifiedName().equals(name)) {
                        availableCalls = new ArrayList<Call>();
                        availableCalls.add(potentialCalls.get(potentialCallsCounter));
                    }
                    potentialCallsCounter++;
                }
            }
        }
        return availableCalls;
    }
    
    
//    /**
//     * Checks whether a(n unique) {@link Call} of the given {@link ElementType} with the given name is available.<br>
//     * <br>
//     * This method initially treats the given name as simple name and returns <code>true</code>, if a single
//     * {@link Call} of the given {@link ElementType} matches that simple name independent of the value of
//     * <code>isUnique</code>. If this initial check does not result in an unique match, this method proceeds depending
//     * on the result as follows:
//     * <ul>
//     * <li>The <b>initial check does not result in any matches</b>: this method retries its check treating the given
//     *     name as fully-qualified name, which must result in a single match (returns <code>true</code>) or none 
//     *     (returns <code>false</code>) independent of the value of <code>isUnique</code>.</li>
//     * <li>The <b>initial check results in multiple matches</b>:</li>
//     *     <ul>
//     *     <li>The value of <code>isUnique</code> is <code>true</code>: this method returns <code>false</code></li>
//     *     <li>The value of <code>isUnique</code> is <code>false</code>: this method returns <code>true</code></li>
//     *     </ul>
//     * </ul>
//     * 
//     * @param elementType the {@link ElementType} of the (unique) {@link Call} to search for
//     * @param name the name of the (unique) {@link Call} to search for
//     * @param isUnique must be <code>true</code> to further check whether a {@link Call} matching the given element
//     *        type and name is unique; <code>false</code> otherwise 
//     * @return <code>true</code>, if a(n unique) {@link Call} of the given {@link ElementType} with the given name is
//     *         available; <code>false</code> otherwise
//     */
//    private boolean hasCall(ElementType elementType, String name, boolean isUnique) {
//        boolean hasCall = false;
//        // Initial check treating the give name as simple name
//        List<Call> availableCalls = calls.get(name);
//        if (availableCalls != null) {
//            if (availableCalls.size() == 1) {
//                // Initial check results in single match
//                if (availableCalls.get(0).getElementType() == elementType) {
//                    // Found a single call with the given element type and name
//                    hasCall = true;
//                }
//            } else {
//                // Initial check results in multiple matches
//                boolean doBreak = false;
//                int availableCallsCounter = 0;
//                while (!doBreak && availableCallsCounter < availableCalls.size()) {
//                    if (availableCalls.get(availableCallsCounter).getElementType() == elementType) {
//                        if (!isUnique) {
//                            /*
//                             * Found first call with the given element type and name, which must not be unique. Hence,
//                             * abort search at this point with positive return value.
//                             */
//                            hasCall = true;
//                            doBreak = true;
//                        } else {
//                            if (!hasCall) {
//                                /*
//                                 * Found first call with the given element type and name, which must be unique. Hence,
//                                 * continue search to ensure uniqueness.
//                                 */
//                                hasCall = true;
//                            } else {
//                                /*
//                                 * Found second call with the given element type and name, while demanding for
//                                 * uniqueness. Hence, abort search at this point with negative return value.
//                                 */
//                                hasCall = false;
//                                doBreak = true;
//                            }
//                        }
//                    }
//                    availableCallsCounter++;
//                }
//            }
//        } else {
//            // Initial check does not result in any matches: Retry check treating the give name as fully-qualifiedname
//            int indexOfLastDot = name.lastIndexOf(".");
//            if (indexOfLastDot != -1) {
//                String simpleName = name.substring(indexOfLastDot + 1);
//                availableCalls = calls.get(simpleName);
//                if (availableCalls != null) {
//                    int availableCallsCounter = 0;
//                    while (!hasCall && availableCallsCounter < availableCalls.size()) {
//                        if (availableCalls.get(availableCallsCounter).getElementType() == elementType
//                                && availableCalls.get(availableCallsCounter).getFullyQualifiedName()
//                                    .equals(name)) {
//                            /*
//                             * Found first call with the given element type and fully-qualified name, which isuniqueby
//                             * definition. Hence, abort search at this point with positive return value.
//                             */
//                            hasCall = true;
//                        }
//                        availableCallsCounter++;
//                    }
//                }
//            }
//        }
//        return hasCall;
//    }

    
//    
//    
//    /**
//     * Checks whether at least one {@link ChangeIdentifier} with the given name is available.
//     *  
//     * @param name the name of the {@link ChangeIdentifier} to search for
//     * @return <code>true</code>, if at least one {@link ChangeIdentifier} with the given name is available;
//     *         <code>false</code> otherwise
//     */
//    public boolean hasChangeIdentifier(String name) {
//        return changeIdentifiers.containsKey(name);
//    }
    
//    /**
//     * Checks whether a {@link ChangeIdentifier} is available, which has the given name and is assignable to the given
//     * elements.
//     * 
//     * @param name the name of the {@link ChangeIdentifier} to search for
//     * @param assignableElements the array of elements to which the {@link ChangeIdentifier} should be assignable to
//     * @return <code>true</code>, if a {@link ChangeIdentifier} is available, which has the given name andisassignable
//     *         to the given elements; <code>false</code> otherwise
//     */
//    public boolean hasChangeIdentifier(String name, String[] assignableElements) {
//        boolean hasChangeIdentifer = false;
//        List<ChangeIdentifier> availableChangeIdentifiers = changeIdentifiers.get(name);
//        if (availableChangeIdentifiers != null) {
//            ParameterType[] correspondingParameterTypes = new ParameterType[assignableElements.length];
//            boolean correspondingParameterTypesAvailable = true;
//            int elementsCounter = 0;
//            ParameterType correspondingParameterType;
//            while (correspondingParameterTypesAvailable && elementsCounter < assignableElements.length) {
//                correspondingParameterType = getParameterType(assignableElements[elementsCounter]);
//                if (correspondingParameterType != null) {
//                    correspondingParameterTypes[elementsCounter] = correspondingParameterType;
//                } else {
//                    correspondingParameterTypesAvailable = false;
//                }
//                elementsCounter++;
//            }
//            if (correspondingParameterTypesAvailable) {                
//                elementsCounter = 0;
//                while (!hasChangeIdentifer && elementsCounter < availableChangeIdentifiers.size()) {
//                    hasChangeIdentifer = availableChangeIdentifiers.get(elementsCounter)
//                            .assignableTo(correspondingParameterTypes);
//                    elementsCounter++;
//                }
//            }
//        }
//        return hasChangeIdentifer;
//    }
    
//    /**
//     * Checks whether at least one {@link ParameterType} of the type {@link ElementType#ARTIFACT_PARAMETER_TYPE} with
//     * the given name is available.
//     *  
//     * @param name the name of the {@link ParameterType} of the type {@link ElementType#ARTIFACT_PARAMETER_TYPE} to
//     *        search for
//     * @return <code>true</code>, if at least one {@link ParameterType} of the type
//     *         {@link ElementType#ARTIFACT_PARAMETER_TYPE} with the given name is available; <code>false</code>
//     *         otherwise
//     */
//    public boolean hasArtifactParameterType(String name) {
//        return artifactParameterTypes.containsKey(name);
//    }
//    
//    /**
//     * Checks whether at least one {@link ParameterType} of the type {@link ElementType#FRAGMENT_PARAMETER_TYPE} with
//     * the given name is available.
//     *  
//     * @param name the name of the {@link ParameterType} of the type {@link ElementType#FRAGMENT_PARAMETER_TYPE} to
//     *        search for
//     * @return <code>true</code>, if at least one {@link ParameterType} of the type
//     *         {@link ElementType#FRAGMENT_PARAMETER_TYPE} with the given name is available; <code>false</code>
//     *         otherwise
//     */
//    public boolean hasFragmentParameterType(String name) {
//        return fragmentParameterTypes.containsKey(name);
//    }
//    
//    /**
//     * Checks whether at least one {@link ParameterType} of the type {@link ElementType#RESULT_PARAMETER_TYPE} with
//     * the given name is available.
//     *  
//     * @param name the name of the {@link ParameterType} of the type {@link ElementType#RESULT_PARAMETER_TYPE} to
//     *        search for
//     * @return <code>true</code>, if at least one {@link ParameterType} of the type
//     *         {@link ElementType#RESULT_PARAMETER_TYPE} with the given name is available; <code>false</code>
//     *         otherwise
//     */
//    public boolean hasResultParameterType(String name) {
//        return resultParameterTypes.containsKey(name);
//    }
    
//    /**
//     * TODO.
//     * @param name .
//     * @return .
//     */
//    public List<String> getParameterTypeFullyQualifiedNames(String name) {
//        List<String> parameterTypefullyQualifiedNames = null;
//        String searchName = name;
//        if (searchName != null && !searchName.isBlank()) {
//            parameterTypefullyQualifiedNames = new ArrayList<String>();
//            List<ParameterType> availableParameterTypes = parameterTypes.get(searchName);
//            if (availableParameterTypes != null) {
//                // The given name is a simple one
//                for (ParameterType availableParameterType : availableParameterTypes) {
//                    parameterTypefullyQualifiedNames.add(availableParameterType.getFullyQualifiedName());
//                }
//            } else {
//                // Retry search treating the given name as a fully-qualified one
//                int indexOfLastDot = name.lastIndexOf(".");
//                if (indexOfLastDot != -1) {                    
//                    searchName = name.substring(indexOfLastDot + 1);
//                    availableParameterTypes = parameterTypes.get(searchName);
//                    if (availableParameterTypes != null) {
//                        for (ParameterType availableParameterType : availableParameterTypes) {
//                            if (availableParameterType.getFullyQualifiedName().equals(name)) {
//                                parameterTypefullyQualifiedNames.add(name);
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        return parameterTypefullyQualifiedNames;
//    }
    
    
//    /**
//     * Returns the {@link ParameterType}, which has the given fully-qualified name. 
//     * 
//     * @param name the (simple) name of the {@link ParameterType} to search for, which is used to preselect to correct
//     *        entry in the {@link HashMap} of available {@link ParameterType}s
//     * @param fullyQualifiedName the fully-qualified name of the {@link ParameterType} to search for
//     * @return the {@link ParameterType} with the given fully-qualified name or <code>null</code>, if no 
//     *         {@link ParameterType} with that fully-qualified name exists 
//     */
//    public ParameterType getParameterType(String name, String fullyQualifiedName) {
//        ParameterType parameterType = null;
//        List<ParameterType> availableParameterTypes = parameterTypes.get(name);
//        if (availableParameterTypes != null) {
//            int availableParameterTypesCounter = 0;
//            ParameterType availableParameterType;
//            while (parameterType == null && availableParameterTypesCounter < availableParameterTypes.size()) {
//                availableParameterType = availableParameterTypes.get(availableParameterTypesCounter);
//                if (availableParameterType.getFullyQualifiedName().equals(fullyQualifiedName)) {
//                    parameterType = availableParameterType;
//                }
//                availableParameterTypesCounter++;
//            }
//        }
//        return parameterType;
//    }
    
    /**
     * Returns a {@link List} of all {@link ParameterType}s of the type {@link ElementType#ARTIFACT_PARAMETER_TYPE},
     * which have the given name.
     *  
     * @param name the name of the artifact parameter type(s), which should be returned
     * @return the {@link List} of all {@link ParameterType}s of the type {@link ElementType#ARTIFACT_PARAMETER_TYPE}
     *         with the given name or <code>null</code>, if no such elements are registered
     */
    public List<ParameterType> getArtifactParameterTypes(String name) {
        return artifactParameterTypes.get(name);
    }
    
    /**
     * Returns a {@link List} of all {@link ParameterType}s of the type {@link ElementType#FRAGMENT_PARAMETER_TYPE},
     * which have the given name.
     *  
     * @param name the name of the fragment parameter type(s), which should be returned
     * @return the {@link List} of all {@link ParameterType}s of the type {@link ElementType#FRAGMENT_PARAMETER_TYPE}
     *         with the given name or <code>null</code>, if no such elements are registered
     */
    public List<ParameterType> getFragmentParameterTypes(String name) {
        return fragmentParameterTypes.get(name);
    }
    
    /**
     * Returns a {@link List} of all {@link ParameterType}s of the type {@link ElementType#RESULT_PARAMETER_TYPE},
     * which have the given name.
     *  
     * @param name the name of the result parameter type(s), which should be returned
     * @return the {@link List} of all {@link ParameterType}s of the type {@link ElementType#RESULT_PARAMETER_TYPE}
     *         with the given name or <code>null</code>, if no such elements are registered
     */
    public List<ParameterType> getResultParameterTypes(String name) {
        return resultParameterTypes.get(name);
    }
    
    /**
     * Returns a {@link List} of all {@link ChangeIdentifier}s, which have the given name.
     *  
     * @param name the name of the change identifier(s), which should be returned
     * @return the {@link List} of all {@link ChangeIdentifier}s with the given name or <code>null</code>, if no such
     *         elements are registered
     */
    public List<ChangeIdentifier> getChangeIdentifiers(String name) {
        return changeIdentifiers.get(name);
    }
    
    /**
     * Returns a {@link List} of all {@link Call}s of the type {@link ElementType#OPERATION}, which have the given name.
     *  
     * @param name the name of the operation(s), which should be returned
     * @return the {@link List} of all {@link Call}s of the type {@link ElementType#OPERATION} with the given name or
     *         <code>null</code>, if no such elements are registered
     */
    public List<Call> getOperations(String name) {
        return operations.get(name);
    }
    
    /**
     * Returns a {@link List} of all {@link Call}s of the type {@link ElementType#OPERATION}, which represent member
     * operations for the {@link ParameterType} identified by the given name.
     *  
     * @param name the name of the {@link ParameterType} for which all member operations should be returned
     * @return the {@link List} of all {@link Call}s of the type {@link ElementType#OPERATION}, which represent member
     *         operations for the {@link ParameterType} identified by the given name or <code>null</code>, if no such
     *         elements are registered
     */
    public List<Call> getMemberOperations2(String name) {
        return memberOperations.get(name);
    }
    
    /**
     * Returns a {@link List} of all {@link Call}s of the type {@link ElementType#EXTRACTOR_CALL}, which have the given
     * name.
     *  
     * @param name the name of the extractor call(s), which should be returned
     * @return the {@link List} of all {@link Call}s of the type {@link ElementType#EXTRACTOR_CALL} with the given name
     *         or <code>null</code>, if no such elements are registered
     */
    public List<Call> getExtractorCalls(String name) {
        return extractorCalls.get(name);
    }
    
    /**
     * Returns a {@link List} of all {@link Call}s of the type {@link ElementType#ANALYSIS_CALL}, which have the given
     * name.
     *  
     * @param name the name of the analysis call(s), which should be returned
     * @return the {@link List} of all {@link Call}s of the type {@link ElementType#ANALYSIS_CALL} with the given name
     *         or <code>null</code>, if no such elements are registered
     */
    public List<Call> getAnalysisCall(String name) {
        return analysisCalls.get(name);
    }
    
    /**
     * Returns the return type of the {@link Call} identified by the given name and parameter types. This method
     * searches in {@link AbstractLanguageRegistry#extractorCalls}, {@link AbstractLanguageRegistry#analysisCalls}, and
     * {@link AbstractLanguageRegistry#operations} in that order and returns the return type of the first matching call.
     * 
     * @param name the name of the {@link Call} for which the return type shall be returned; should never be 
     *        <code>null</code> nor <i>blank</i>
     * @param parameterTypes the optional array of parameters the {@link Call} should accept in the given order; can be
     *        <code>null</code> to indicate that the desired {@link Call} does not accept any parameters
     * @return the return type of the {@link Call} identified by the given name and parameter types or
     *         <code>null</code>, if no such {@link Call} is available
     * @see #getCallReturnType(HashMap, String, String[])
     */
    public String getCallReturnType(String name, String[] parameterTypes) {
        String callReturnType = getCallReturnType(extractorCalls, name, parameterTypes);
        if (callReturnType == null) {
            callReturnType = getCallReturnType(analysisCalls, name, parameterTypes);
        }
        if (callReturnType == null) {
            callReturnType = getCallReturnType(operations, name, parameterTypes);
        }
        return callReturnType;
    }
    
    /**
     * Searches in the given {@link HashMap} for a {@link Call} with the given name and parameter types and returns its
     * return type.
     * 
     * @param map the {@link HashMap} in which to search for a {@link Call} with the given attributes
     * @param name the name of the {@link Call} for which the return type shall be returned; should never be 
     *        <code>null</code> nor <i>blank</i>
     * @param parameterTypes the optional array of parameters the {@link Call} should accept in the given order; can be
     *        <code>null</code> to indicate that the desired {@link Call} does not accept any parameters
     * @return the return type of the {@link Call} identified by the given name and parameter types or
     *         <code>null</code>, if no such {@link Call} is available in the given map
     */
    private String getCallReturnType(HashMap<String, List<Call>> map, String name, String[] parameterTypes) {
        String callReturnType = null;
        List<Call> availableCalls = map.get(name);
        if (availableCalls != null) {
            // Name already equal, hence, we only need to check for equal parameters
            int availableCallsCounter = 0;
            int numberOfParameterTypes = 0;
            if (parameterTypes != null) {
                numberOfParameterTypes = parameterTypes.length;
            }
            Call availableCall;
            boolean parametersEqual;
            int parametersCounter;
            while (callReturnType == null && availableCallsCounter < availableCalls.size()) {
                availableCall = availableCalls.get(availableCallsCounter);
                if (availableCall.getParameters().length == numberOfParameterTypes) {
                    parametersEqual = true;
                    parametersCounter = 0;
                    while (parametersEqual && parametersCounter < parameterTypes.length) {
                        parametersEqual = parameterTypes[parametersCounter]
                                .equals(availableCall.getParameters()[parametersCounter].getName());
                        parametersCounter++;
                    }
                    if (parametersEqual) {
                        callReturnType = availableCall.getReturnType().getName();
                    }
                }
                availableCallsCounter++;
            }
        }
        return callReturnType;
    }
    
}
