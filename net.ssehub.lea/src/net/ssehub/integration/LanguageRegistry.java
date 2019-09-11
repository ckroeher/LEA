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
     * Checks whether a(n unique) {@link ParameterType} with the given name is available.<br>
     * <br>
     * This method initially treats the given name as simple name and returns <code>true</code>, if a single
     * {@link ParameterType} matches that simple name independent of the value of <code>isUnique</code>. If this initial
     * check does not result in an unique match, this method proceeds depending on the result as follows:
     * <ul>
     * <li>The <b>initial check does not result in any matches</b>: this method retries its check treating the given
     *     name as fully-qualified name, which may result in a single match (returns <code>true</code> immediately),
     *     multiple matches (see next bullet), or none (returns <code>false</code> immediately).</li>
     * <li>The <b>initial check results in multiple matches</b>:</li>
     *     <ul>
     *     <li>The value of <code>isUnique</code> is <code>true</code>: this method returns <code>false</code></li>
     *     <li>The value of <code>isUnique</code> is <code>false</code>: this method returns <code>true</code></li>
     *     </ul>
     * </ul>
     * 
     * @param name the name of the (unique) {@link ParameterType} to search for
     * @param isUnique must be <code>true</code> to further check whether a {@link ParameterType} matching the given
     *        name is unique; <code>false</code> otherwise 
     * @return <code>true</code>, if a(n unique) {@link ParameterType} with the given name is available;
     *         <code>false</code> otherwise
     */
    public boolean hasParameterType(String name, boolean isUnique) {
        boolean hasParameterType = false;
        // Initial check treating the give name as simple name
        List<ParameterType> availableParameterTypes = parameterTypes.get(name);
        if (availableParameterTypes != null) {
            if (!isUnique || availableParameterTypes.size() == 1) {
                // Found a (single) parameter type with the given name
                hasParameterType = true;
            }
        } else {
            // Initial check does not result in any matches: Retry check treating the give name as fully-qualified name
            int indexOfLastDot = name.lastIndexOf(".");
            if (indexOfLastDot != -1) {
                String simpleName = name.substring(indexOfLastDot + 1);
                availableParameterTypes = parameterTypes.get(simpleName);
                if (availableParameterTypes != null) {
                    /*
                     * Remember that fully-qualified names of parameter types are not unique on their own, but only in
                     * combination with the element type of the parameter type. For example, the same fully-qualified
                     * name may denote an artifact and a result parameter type, of that parameter type should be
                     * available for the definitions of artifacts and results.
                     */
                    boolean doBreak = false;
                    int availableParameterTypesCounter = 0;
                    while (!doBreak && availableParameterTypesCounter < availableParameterTypes.size()) {
                        if (availableParameterTypes.get(availableParameterTypesCounter).getFullyQualifiedName()
                                .equals(name)) {
// CHECKSTYLE:OFF
                            if (!isUnique) {
                                /*
                                 * Found first parameter type with the given fully-qualified name, which must not be
                                 * unique. Hence, abort search at this point with positive return value.
                                 */
                                hasParameterType = true;
                                doBreak = true;
                            } else {
                                if (!hasParameterType) {
                                    /*
                                     * Found first parameter type with the given fully-qualified name, which must be
                                     * unique. Hence, continue search to ensure uniqueness.
                                     */
                                    hasParameterType = true;
                                } else {
                                    /*
                                     * Found second parameter type with the given fully-qualified name, while demanding
                                     * for uniqueness. Hence, abort search at this point with negative return value.
                                     */
                                    hasParameterType = false;
                                    doBreak = true;
                                }
                            }
// CHECKSTYLE:ON
                        }
                        availableParameterTypesCounter++;
                    }
                }
            }
        }
        return hasParameterType;
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
    public boolean hasParameterType(ElementType elementType, String name, boolean isUnique) {
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
                             * Found first parameter type with the given element type and fully-qualified name, which in
                             * combination is unique by definition. Hence, abort search at this point with positive
                             * return value.
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
     *     name as fully-qualified name, which may result in a single match (returns <code>true</code> immediately),
     *     multiple matches (see next bullet), or none (returns <code>false</code> immediately).</li>
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
// CHECKSTYLE:OFF
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
                    /*
                     * Remember that fully-qualified names of change identifiers are not unique on their own, but only
                     * in combination with the assignable elements of the change identifier. For example, the same
                     * fully-qualified name may denote a change identifier that is assignable to a specific artifact
                     * parameter type only and the same name is assignable to a specific result parameter type only,
                     * which may occur, if the same class is annotated twice.
                     */
                    boolean doBreak = false;
                    int availableChangeIdentifiersCounter = 0;
                    while (!doBreak && availableChangeIdentifiersCounter < availableChangeIdentifiers.size()) {
                        if (availableChangeIdentifiers.get(availableChangeIdentifiersCounter).getFullyQualifiedName()
                                .equals(name)) {
                            if (!isUnique) {
                                /*
                                 * Found first change identifier with the given fully-qualified name, which must not be
                                 * unique. Hence, abort search at this point with positive return value.
                                 */
                                hasChangeIdentifier = true;
                                doBreak = true;
                            } else {
                                if (!hasChangeIdentifier) {
                                    /*
                                     * Found first change identifier with the given fully-qualified name, which must be
                                     * unique. Hence, continue search to ensure uniqueness.
                                     */
                                    hasChangeIdentifier = true;
                                } else {
                                    /*
                                     * Found second change identifier with the given fully-qualified name, while
                                     * demanding for uniqueness. Hence, abort search at this point with negative return
                                     * value.
                                     */
                                    hasChangeIdentifier = false;
                                    doBreak = true;
                                }
                            }
                        }
                        availableChangeIdentifiersCounter++;
                    }
                }
            }
        }
        return hasChangeIdentifier;
    }
// CHECKSTYLE:ON
    
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
     * Checks whether a(n unique) {@link Call} of the given {@link ElementType} with the given name is available.<br>
     * <br>
     * This method initially treats the given name as simple name. If no {@link Call}s match that simple name, this
     * method retries detecting available {@link Call}s treating the given name as fully-qualified name. If in any of
     * these case one or more {@link Call}s are detected, their {@link ElementType} and name are compared against the
     * given ones. This results in the following return value:
     * <ul>
     * <li><code>true</code>, if <code>isUnique</code> is <code>true</code> and exactly one {@link Call} matches
     *     the given {@link ElementType} and the given name</li>
     * <li><code>true</code>, if <code>isUnique</code> is <code>false</code> and at least one {@link Call} matches
     *     the given {@link ElementType} and the given name</li>
     * <li><code>false</code>, in all other cases</li>
     * </ul>
     * 
     * @param elementType the {@link ElementType} of the (unique) {@link Call} to search for; should never be
     *        <code>null</code>
     * @param name the name of the (unique) {@link Call} to search for; should never be <code>null</code>
     * @param isUnique must be <code>true</code> to further check whether a {@link Call} matching the given
     *        {@link ElementType} name is unique; <code>false</code> otherwise 
     * @return <code>true</code>, if a(n unique) {@link Call} of the type given {@link ElementType} with the given
     *         name is available; <code>false</code> otherwise
     */
    public boolean hasCall(ElementType elementType, String name, boolean isUnique) {
        boolean hasCall = false;
        List<Call> availableCall = getCalls(elementType, name);
        if (availableCall != null && (!isUnique || availableCall.size() == 1)) {
            hasCall = true;
        }
        return hasCall;
    }
    
//    /**
//     * Checks whether a(n unique) {@link Call} of the given {@link ElementType} with the given name and 
//     * {@link ParameterType} denoting the return type is available.<br>
//     * <br>
//     * This method initially treats the given name as simple name. If no {@link Call}s match that simple name, this
//     * method retries detecting available {@link Call}s treating the given name as fully-qualified name. If in any of
//     * these case one or more {@link Call}s are detected, their {@link ElementType}, name, and return type are
//     * compared against the given ones. This results in the following return value:
//     * <ul>
//     * <li><code>true</code>, if <code>isUnique</code> is <code>true</code> and exactly one {@link Call} matches
//     *     the given {@link ElementType}, the given name, and the given return type</li>
//     * <li><code>true</code>, if <code>isUnique</code> is <code>false</code> and at least one {@link Call} matches
//     *     the given {@link ElementType} and the given name, and the given return type</li>
//     * <li><code>false</code>, in all other cases</li>
//     * </ul>
//     * 
//     * @param elementType the {@link ElementType} of the (unique) {@link Call} to search for; should never be
//     *        <code>null</code>
//     * @param name the name of the (unique) {@link Call} to search for; should never be <code>null</code>
//     * @param returnType the {@link ParameterType} denoting the return type of the {@link Call}s to search for; should
//     *        never be <code>null</code>
//     * @param isUnique must be <code>true</code> to further check whether a {@link Call} matching the given
//     *        {@link ElementType} name is unique; <code>false</code> otherwise 
//     * @return <code>true</code>, if a(n unique) {@link Call} of the type given {@link ElementType} with the given
//     *         name and return type is available; <code>false</code> otherwise
//     */
//    public boolean hasCall(ElementType elementType, String name, ParameterType returnType, boolean isUnique) {
//        boolean hasCall = false;
//        List<Call> availableCall = getCalls(elementType, name, returnType);
//        if (availableCall != null && (!isUnique || availableCall.size() == 1)) {
//            hasCall = true;
//        }
//        return hasCall;
//    }
    
    /**
     * Checks whether a(n unique) {@link Call} of the given {@link ElementType} with the given name and 
     * {@link ParameterType}s denoting the parameters is available.<br>
     * <br>
     * This method initially treats the given name as simple name. If no {@link Call}s match that simple name, this
     * method retries detecting available {@link Call}s treating the given name as fully-qualified name. If in any of
     * these case one or more {@link Call}s are detected, their {@link ElementType}, name, and parameters are compared
     * against the given ones. This results in the following return value:
     * <ul>
     * <li><code>true</code>, if <code>isUnique</code> is <code>true</code> and exactly one {@link Call} matches
     *     the given {@link ElementType}, the given name, and the given parameters</li>
     * <li><code>true</code>, if <code>isUnique</code> is <code>false</code> and at least one {@link Call} matches
     *     the given {@link ElementType} and the given name, and the given parameters</li>
     * <li><code>false</code>, in all other cases</li>
     * </ul>
     * 
     * @param elementType the {@link ElementType} of the (unique) {@link Call} to search for; should never be
     *        <code>null</code>
     * @param name the name of the (unique) {@link Call} to search for; should never be <code>null</code>
     * @param parameters the array of {@link ParameterType}s denoting the parameters of the {@link Call}s to search for;
     *        <code>null</code> or an <i>empty</i> array indicates that the {@link Calls} should not have any parameters
     * @param isUnique must be <code>true</code> to further check whether a {@link Call} matching the given
     *        {@link ElementType} name is unique; <code>false</code> otherwise 
     * @return <code>true</code>, if a(n unique) {@link Call} of the type given {@link ElementType} with the given
     *         name and parameters is available; <code>false</code> otherwise
     */
    public boolean hasCall(ElementType elementType, String name, ParameterType[] parameters, boolean isUnique) {
        boolean hasCall = false;
        List<Call> availableCall = getCalls(elementType, name, parameters);
        if (availableCall != null && (!isUnique || availableCall.size() == 1)) {
            hasCall = true;
        }
        return hasCall;
    }
    
//    /**
//     * Checks whether a(n unique) {@link Call} of the given {@link ElementType} with the given name,
//     * {@link ParameterType} denoting the return type, and {@link ParameterType}s denoting the parameters is 
//     * available.<br>
//     * <br>
//     * This method initially treats the given name as simple name. If no {@link Call}s match that simple name, this
//     * method retries detecting available {@link Call}s treating the given name as fully-qualified name. If in any of
//     * these case one or more {@link Call}s are detected, their {@link ElementType}, name, return type and parameters
//     * are compared against the given ones. This results in the following return value:
//     * <ul>
//     * <li><code>true</code>, if <code>isUnique</code> is <code>true</code> and exactly one {@link Call} matches
//     *     the given {@link ElementType}, the given name, the given return type, and the given parameters</li>
//     * <li><code>true</code>, if <code>isUnique</code> is <code>false</code> and at least one {@link Call} matches
//     *     the given {@link ElementType} and the given name, the given return type, and the given parameters</li>
//     * <li><code>false</code>, in all other cases</li>
//     * </ul>
//     * 
//     * @param elementType the {@link ElementType} of the (unique) {@link Call} to search for; should never be
//     *        <code>null</code>
//     * @param name the name of the (unique) {@link Call} to search for; should never be <code>null</code>
//     * @param returnType the {@link ParameterType} denoting the return type of the {@link Call}s to search for; should
//     *        never be <code>null</code>
//     * @param parameters the array of {@link ParameterType}s denoting the parameters of the {@link Call}s to search
//     *        for; <code>null</code> or an <i>empty</i> array indicates that the {@link Calls} should not have any
//     *        parameters
//     * @param isUnique must be <code>true</code> to further check whether a {@link Call} matching the given
//     *        {@link ElementType} name is unique; <code>false</code> otherwise 
//     * @return <code>true</code>, if a(n unique) {@link Call} of the type given {@link ElementType} with the given
//     *         name and parameters is available; <code>false</code> otherwise
//     */
//    public boolean hasCall(ElementType elementType, String name, ParameterType returnType, ParameterType[] parameters,
//            boolean isUnique) {
//        boolean hasCall = false;
//        List<Call> availableCall = getCalls(elementType, name, returnType, parameters);
//        if (availableCall != null && (!isUnique || availableCall.size() == 1)) {
//            hasCall = true;
//        }
//        return hasCall;
//    }
    
    /**
     * Returns the <b>unique</b> {@link ParameterType} with the given name.<br>
     * <br>
     * This method initially treats the given name as simple name and returns that unique {@link ParameterType}, which
     * matches that simple name. If this initial search results in multiple {@link ParameterType}s matching the simple
     * name, the return value is <code>null</code> due to ambiguity. If the initial search does not result in any
     * matches, this method retries its search treating the given name as fully-qualified name, which must result in
     * either a single match (returns that {@link ParameterType}) or none (returns <code>null</code>).
     * 
     * @param name the name of the unique {@link ParameterType} to search for 
     * @return the unique {@link ParameterType} with the given name or <code>null</code>, if the search results in
     *         multiple matches (ambiguous results) or no such {@link ParameterType} is available
     */
    public ParameterType getParameterType(String name) {
        ParameterType parameterType = null;
        // Initial search treating the give name as simple name
        List<ParameterType> availableParameterTypes = parameterTypes.get(name);
        if (availableParameterTypes != null) {
            if (availableParameterTypes.size() == 1) {
                // Found a single parameter type with the given name
                parameterType = availableParameterTypes.get(0);
            }
        } else {
            // Initial search does not result in any matches: Retry search treating the give name as fully-qualified one
            int indexOfLastDot = name.lastIndexOf(".");
            if (indexOfLastDot != -1) {
                String simpleName = name.substring(indexOfLastDot + 1);
                availableParameterTypes = parameterTypes.get(simpleName);
                if (availableParameterTypes != null) {
                    int availableParameterTypesCounter = 0;
                    while (parameterType == null && availableParameterTypesCounter < availableParameterTypes.size()) {
                        if (availableParameterTypes.get(availableParameterTypesCounter).getFullyQualifiedName()
                                    .equals(name)) {
                            /*
                             * Found first parameter type with the given fully-qualified name, which is unique by
                             * definition. Hence, abort search at this point with that parameter type as return value.
                             */
                            parameterType = availableParameterTypes.get(availableParameterTypesCounter);
                        }
                        availableParameterTypesCounter++;
                    }
                }
            }
        }
        return parameterType;
    }
    
    /**
     * Returns the <b>unique</b> {@link ParameterType} of the given {@link ElementType} with the given name.<br>
     * <br>
     * This method initially treats the given name as simple name and returns that unique {@link ParameterType}, which
     * matches that simple name and has the given {@link ElementType}. If this initial search results in multiple
     * {@link ParameterType}s matching the simple name and having the given {@link ElementType}, the return value is
     * <code>null</code> due to ambiguity. If the initial search does not result in any matches, this method retries its
     * search treating the given name as fully-qualified name, which must result in either a single match
     * (returns that {@link ParameterType}) or none (returns <code>null</code>).
     * 
     * @param elementType the {@link ElementType} of the unique {@link ParameterType} to search for
     * @param name the name of the unique {@link ParameterType} to search for 
     * @return the unique {@link ParameterType} of the given {@link ElementType} with the given name or
     *         <code>null</code>, if the search results in multiple matches (ambiguous results) or no such
     *         {@link ParameterType} is available
     */
    public ParameterType getParameterType(ElementType elementType, String name) {
        ParameterType parameterType = null;
        // Initial search treating the give name as simple name
        List<ParameterType> availableParameterTypes = parameterTypes.get(name);
        if (availableParameterTypes != null) {
            if (availableParameterTypes.size() == 1) {
                // Initial search results in single match
                if (availableParameterTypes.get(0).getElementType() == elementType) {
                    // Found a single parameter type with the given element type and name
                    parameterType = availableParameterTypes.get(0);
                }
            } else {
                // Initial search results in multiple matches
                boolean doBreak = false;
                int availableParameterTypesCounter = 0;
                while (!doBreak && availableParameterTypesCounter < availableParameterTypes.size()) {
                    if (availableParameterTypes.get(availableParameterTypesCounter).getElementType() == elementType) {
                        if (parameterType == null) {
                            /*
                             * Found first parameter type with the given element type and name, which must be
                             * unique to be returned. Hence, continue search to ensure uniqueness.
                             */
                            parameterType = availableParameterTypes.get(availableParameterTypesCounter);
                        } else {
                            /*
                             * Found second parameter type with the given element type and name, while demanding for
                             * uniqueness. Hence, abort search at this point with a return value of null as it is
                             * ambiguous which parameter type was meant.
                             */
                            parameterType = null;
                            doBreak = true;
                        }
                    }
                    availableParameterTypesCounter++;
                }
            }
        } else {
            // Initial search does not result in any matches: Retry search treating the give name as fully-qualified one
            int indexOfLastDot = name.lastIndexOf(".");
            if (indexOfLastDot != -1) {
                String simpleName = name.substring(indexOfLastDot + 1);
                availableParameterTypes = parameterTypes.get(simpleName);
                if (availableParameterTypes != null) {
                    int availableParameterTypesCounter = 0;
                    while (parameterType == null && availableParameterTypesCounter < availableParameterTypes.size()) {
                        if (availableParameterTypes.get(availableParameterTypesCounter).getElementType() == elementType
                                && availableParameterTypes.get(availableParameterTypesCounter).getFullyQualifiedName()
                                    .equals(name)) {
                            /*
                             * Found first parameter type with the given element type and fully-qualified name, which is
                             * unique by definition. Hence, abort search at this point with that parameter type as
                             * return value.
                             */
                            parameterType = availableParameterTypes.get(availableParameterTypesCounter);
                        }
                        availableParameterTypesCounter++;
                    }
                }
            }
        }
        return parameterType;
    }
    
    /**
     * Returns the {@link List} of <b>all</b> {@link ParameterType}s of the given {@link ElementType} with the given
     * name.<br>
     * <br>
     * This method initially treats the given name as simple name and returns all {@link ParameterType}s, which match
     * that simple name and have the given {@link ElementType}. If the initial search does not result in any matches,
     * this method retries its search treating the given name as fully-qualified name, which must result in either a
     * single match (returns that {@link ParameterType}) or none (returns <code>null</code>).
     * 
     * @param elementType the {@link ElementType} of the {@link ParameterType}s to search for
     * @param name the name of the {@link ParameterType}s to search for 
     * @return the {@link List} of all {@link ParameterType}s of the given {@link ElementType} with the given name or
     *         <code>null</code>, if no such {@link ParameterType}s are available
     */
    public List<ParameterType> getParameterTypes(ElementType elementType, String name) {
        List<ParameterType> parameterTypes = null;
        // Initial search treating the give name as simple name
        List<ParameterType> availableParameterTypes = this.parameterTypes.get(name);
        if (availableParameterTypes != null) {
            // Initial search results in one or multiple matches
            for (ParameterType availableParameterType : availableParameterTypes) {
                if (availableParameterType.getElementType() == elementType) {
                    if (parameterTypes == null) {
                        parameterTypes = new ArrayList<ParameterType>();
                    }
                    parameterTypes.add(availableParameterType);
                }
            }
        } else {
            // Initial search does not result in any matches: Retry search treating the give name as fully-qualified one
            int indexOfLastDot = name.lastIndexOf(".");
            if (indexOfLastDot != -1) {
                String simpleName = name.substring(indexOfLastDot + 1);
                availableParameterTypes = this.parameterTypes.get(simpleName);
                if (availableParameterTypes != null) {
                    for (ParameterType availableParameterType : availableParameterTypes) {
                        if (availableParameterType.getElementType() == elementType
                                && availableParameterType.getFullyQualifiedName().equals(name)) {
// CHECKSTYLE:OFF
                            if (parameterTypes == null) {
                                parameterTypes = new ArrayList<ParameterType>();
                            }
// CHECKSTYLE:ON
                            parameterTypes.add(availableParameterType);
                        }
                        
                    }
                }
            }
        }
        return parameterTypes;
    }
    
    /**
     * Returns the <b>unique</b> {@link ChangeIdentifier} with the given name.<br>
     * <br>
     * This method initially treats the given name as simple name and returns that unique {@link ChangeIdentifier},
     * which matches that simple name. If this initial search results in multiple {@link ChangeIdentifier}s matching the
     * simple name, the return value is <code>null</code> due to ambiguity. If the initial search does not result in any
     * matches, this method retries its search treating the given name as fully-qualified name, which must result in
     * either a single match (returns that {@link ChangeIdentifier}) or none (returns <code>null</code>).
     * 
     * @param name the name of the unique {@link ChangeIdentifier} to search for 
     * @return the unique {@link ChangeIdentifier} with the given name or <code>null</code>, if the search results in
     *         multiple matches (ambiguous results) or no such {@link ChangeIdentifier} is available
     */
    public ChangeIdentifier getChangeIdentifier(String name) {
        ChangeIdentifier changeIdentifier = null;
        // Initial search treating the give name as simple name
        List<ChangeIdentifier> availableChangeIdentifiers = changeIdentifiers.get(name);
        if (availableChangeIdentifiers != null) {
            if (availableChangeIdentifiers.size() == 1) {
                // Found a single change identifier with the given name
                changeIdentifier = availableChangeIdentifiers.get(0);
            } else {
                // Initial search results in multiple matches
                boolean doBreak = false;
                int availableChangeIdentifiersCounter = 0;
                while (!doBreak && availableChangeIdentifiersCounter < availableChangeIdentifiers.size()) {
                    if (changeIdentifier == null) {
                        /*
                         * Found first change identifier with the given name, which must be unique to be returned.
                         * Hence, continue search to ensure uniqueness.
                         */
                        changeIdentifier = availableChangeIdentifiers.get(availableChangeIdentifiersCounter);
                    } else {
                        /*
                         * Found second change identifier with the given name, while demanding for uniqueness. Hence,
                         * abort search at this point with a return value of null as it is ambiguous which change
                         * identifier was meant.
                         */
                        changeIdentifier = null;
                        doBreak = true;
                    }
                    availableChangeIdentifiersCounter++;
                }
            }
        } else {
            // Initial search does not result in any matches: Retry search treating the give name as fully-qualified one
            int indexOfLastDot = name.lastIndexOf(".");
            if (indexOfLastDot != -1) {
                String simpleName = name.substring(indexOfLastDot + 1);
                availableChangeIdentifiers = changeIdentifiers.get(simpleName);
                if (availableChangeIdentifiers != null) {
                    int availableChangeIdentifiersCounter = 0;
                    while (changeIdentifier == null 
                            && availableChangeIdentifiersCounter < availableChangeIdentifiers.size()) {
                        if (availableChangeIdentifiers.get(availableChangeIdentifiersCounter).getFullyQualifiedName()
                                    .equals(name)) {
                            /*
                             * Found first change identifier with the given fully-qualified name, which is unique by
                             * definition. Hence, abort search at this point with that change identifier as return
                             * value.
                             */
                            changeIdentifier = availableChangeIdentifiers.get(availableChangeIdentifiersCounter);
                        }
                        availableChangeIdentifiersCounter++;
                    }
                }
            }
        }
        return changeIdentifier;
    }
    
    /**
     * Returns the {@link List} of <b>all</b> {@link ChangeIdentifier}s with the given name.<br>
     * <br>
     * This method initially treats the given name as simple name and returns all {@link ChangeIdentifier}s, which match
     * that simple name. If the initial search does not result in any matches, this method retries its search treating
     * the given name as fully-qualified name, which must result in either a single match (returns that 
     * {@link ChangeIdentifier}) or none (returns <code>null</code>).
     * 
     * @param name the name of the {@link ChangeIdentifier}s to search for 
     * @return the {@link List} of all {@link ChangeIdentifier}s with the given name or <code>null</code>, if no such
     *         {@link ChangeIdentifier}s are available
     */
    public List<ChangeIdentifier> getChangeIdentifiers(String name) {
        // Initial search treating the give name as simple name
        List<ChangeIdentifier> changeIdentifiers = this.changeIdentifiers.get(name);
        if (changeIdentifiers == null) {
            // Initial search does not result in any matches: Retry search treating the give name as fully-qualified one
            int indexOfLastDot = name.lastIndexOf(".");
            if (indexOfLastDot != -1) {
                String simpleName = name.substring(indexOfLastDot + 1);
                List<ChangeIdentifier> availableChangeIdentifiers = this.changeIdentifiers.get(simpleName);
                if (availableChangeIdentifiers != null) {
                    for (ChangeIdentifier availableChangeIdentifier : availableChangeIdentifiers) {
                        if (availableChangeIdentifier.getFullyQualifiedName().equals(name)) {
// CHECKSTYLE:OFF
                            if (changeIdentifiers == null) {
                                changeIdentifiers = new ArrayList<ChangeIdentifier>();
                            }
// CHECKSTYLE:ON
                            changeIdentifiers.add(availableChangeIdentifier);
                        }
                        
                    }
                }
            }
        }
        return changeIdentifiers;
    }
    
    /**
     * Returns the <b>unique</b> {@link Call} of the given {@link ElementType} with the given name.<br>
     * <br>
     * This method initially treats the given name as simple name and returns that unique {@link Call}, which
     * matches that simple name and has the given {@link ElementType}. If this initial search results in multiple
     * {@link Call}s matching the simple name and having the given {@link ElementType}, the return value is
     * <code>null</code> due to ambiguity. If the initial search does not result in any matches, this method retries its
     * search treating the given name as fully-qualified name, which must result in either a single match
     * (returns that {@link Call}) or none (returns <code>null</code>).
     * 
     * @param elementType the {@link ElementType} of the unique {@link Call} to search for
     * @param name the name of the unique {@link Call} to search for 
     * @return the unique {@link Call} of the given {@link ElementType} with the given name or <code>null</code>, if the
     *         search results in multiple matches (ambiguous results) or no such {@link Call} is available
     */
    public Call getCall(ElementType elementType, String name) {
        Call call = null;
        List<Call> availableCalls = getCalls(elementType, name);
        if (availableCalls != null && availableCalls.size() == 1) {
            call = availableCalls.get(0);
        }
        return call;
    }
    
    /**
     * Returns the <b>unique</b> {@link Call} of the given {@link ElementType} with the given name and 
     * {@link ParameterType} denoting the return type.<br>
     * <br>
     * This method initially treats the given name as simple name and returns that unique {@link Call}, which
     * matches that simple name, the given return type, and has the given {@link ElementType}. If this initial search
     * results in multiple {@link Call}s matching the simple name, the given return type, and having the given
     * {@link ElementType}, the return value is <code>null</code> due to ambiguity. If the initial search does not
     * result in any matches, this method retries its search treating the given name as fully-qualified name, which must
     * result in either a single match (returns that {@link Call}) or none (returns <code>null</code>).
     * 
     * @param elementType the {@link ElementType} of the unique {@link Call} to search for
     * @param name the name of the unique {@link Call} to search for
     * @param returnType the {@link ParameterType} denoting the return type of the unique {@link Call} to search for
     * @return the unique {@link Call} of the given {@link ElementType} with the given name and return type or
     *         <code>null</code>, if the search results in multiple matches (ambiguous results) or no such {@link Call}
     *         is available
     */
    public Call getCall(ElementType elementType, String name, ParameterType returnType) {
        Call call = null;
        List<Call> availableCalls = getCalls(elementType, name, returnType);
        if (availableCalls != null && availableCalls.size() == 1) {
            call = availableCalls.get(0);
        }
        return call;
    }
    
    /**
     * Returns the <b>unique</b> {@link Call} of the given {@link ElementType} with the given name and 
     * {@link ParameterType}s denoting the parameters.<br>
     * <br>
     * This method initially treats the given name as simple name and returns that unique {@link Call}, which
     * matches that simple name, the given parameters, and has the given {@link ElementType}. If this initial search
     * results in multiple {@link Call}s matching the simple name, the given parameters, and having the given
     * {@link ElementType}, the return value is <code>null</code> due to ambiguity. If the initial search does not
     * result in any matches, this method retries its search treating the given name as fully-qualified name, which must
     * result in either a single match (returns that {@link Call}) or none (returns <code>null</code>).
     * 
     * @param elementType the {@link ElementType} of the unique {@link Call} to search for
     * @param name the name of the unique {@link Call} to search for
     * @param parameters the array of {@link ParameterType}s denoting the parameters of the unique {@link Call} to
     *        search for
     * @return the unique {@link Call} of the given {@link ElementType} with the given name and parameters or
     *         <code>null</code>, if the search results in multiple matches (ambiguous results) or no such {@link Call}
     *         is available
     */
    public Call getCall(ElementType elementType, String name, ParameterType[] parameters) {
        Call call = null;
        List<Call> availableCalls = getCalls(elementType, name, parameters);
        if (availableCalls != null && availableCalls.size() == 1) {
            call = availableCalls.get(0);
        }
        return call;
    }
    
    /**
     * Returns the <b>unique</b> {@link Call} of the given {@link ElementType} with the given name, 
     * {@link ParameterType} denoting the return type, and {@link ParameterType}s denoting the parameters.<br>
     * <br>
     * This method initially treats the given name as simple name and returns that unique {@link Call}, which
     * matches that simple name, the given return type, the given parameters, and has the given {@link ElementType}. If
     * this initial search results in multiple {@link Call}s matching the simple name, the given return type, the given
     * parameters, and having the given {@link ElementType}, the return value is <code>null</code> due to ambiguity. If
     * the initial search does not result in any matches, this method retries its search treating the given name as
     * fully-qualified name, which must result in either a single match (returns that {@link Call}) or none 
     * (returns <code>null</code>).
     * 
     * @param elementType the {@link ElementType} of the unique {@link Call} to search for
     * @param name the name of the unique {@link Call} to search for
     * @param returnType the {@link ParameterType} denoting the return type of the unique {@link Call} to search for
     * @param parameters the array of {@link ParameterType}s denoting the parameters of the unique {@link Call} to
     *        search for
     * @return the unique {@link Call} of the given {@link ElementType} with the given name, return type, and parameters
     *         or <code>null</code>, if the search results in multiple matches (ambiguous results) or no such
     *         {@link Call} is available
     */
    public Call getCall(ElementType elementType, String name, ParameterType returnType, ParameterType[] parameters) {
        Call call = null;
        List<Call> availableCalls = getCalls(elementType, name, returnType, parameters);
        if (availableCalls != null && availableCalls.size() == 1) {
            call = availableCalls.get(0);
        }
        return call;
    }
    
    /**
     * Returns the {@link List} of <b>all</b> {@link Call}s of the given {@link ElementType} with the given name.<br>
     * <br>
     * This method first detects all available {@link Call}s with the given name by calling {@link #getCalls(String)}.
     * Based on that return value, it filters those {@link Call}s matching the given {@link ElementType}.
     * 
     * @param elementType the {@link ElementType} of the {@link Call}s to search for; should never be <code>null</code>
     * @param name the name of the {@link Call}s to search for; should never be <code>null</code>
     * @return the {@link List} of available {@link Call}s with the given {@link ElementType} and name or
     *         <code>null</code>, if no such {@link Call}s are available 
     */
    public List<Call> getCalls(ElementType elementType, String name) {
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
     * Returns the {@link List} of <b>all</b> {@link Call}s of the given {@link ElementType} with the given name and
     * {@link ParameterType} denoting the return type.<br>
     * <br>
     * This method first detects all available {@link Call}s with the given {@link ElementType} and name by calling
     * {@link #getCalls(ElementType, String)}. Based on that return value, it filters those {@link Call}s matching the
     * given return type.
     * 
     * @param elementType the {@link ElementType} of the {@link Call}s to search for; should never be <code>null</code>
     * @param name the name of the {@link Call}s to search for; should never be <code>null</code>
     * @param returnType the {@link ParameterType} denoting the return type of the {@link Call}s to search for; should
     *        never be <code>null</code>
     * @return the {@link List} of available {@link Call}s with the given {@link ElementType}, name, and return type or
     *         <code>null</code>, if no such {@link Call}s are available 
     */
    public List<Call> getCalls(ElementType elementType, String name, ParameterType returnType) {
        List<Call> availableCalls = null;
        List<Call> potentialCalls = getCalls(elementType, name);
        if (potentialCalls != null) {
            for (Call potentialCall : potentialCalls) {
                if (potentialCall.getReturnType().equals(returnType)) {
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
     * Returns the {@link List} of <b>all</b> {@link Call}s of the given {@link ElementType} with the given name and
     * {@link ParameterType}s denoting the parameters.<br>
     * <br>
     * This method first detects all available {@link Call}s with the given {@link ElementType} and name by calling
     * {@link #getCalls(ElementType, String)}. Based on that return value, it filters those {@link Call}s matching the
     * given parameters.
     * 
     * @param elementType the {@link ElementType} of the {@link Call}s to search for; should never be <code>null</code>
     * @param name the name of the {@link Call}s to search for; should never be <code>null</code>
     * @param parameters the array of {@link ParameterType}s denoting the parameters of the {@link Call}s to search for;
     *        <code>null</code> or an <i>empty</i> array indicates that the {@link Calls} should not have any parameters
     * @return the {@link List} of available {@link Call}s with the given {@link ElementType}, name, and parameters or
     *         <code>null</code>, if no such {@link Call}s are available 
     */
    public List<Call> getCalls(ElementType elementType, String name, ParameterType[] parameters) {
        List<Call> availableCalls = null;
        List<Call> potentialCalls = getCalls(elementType, name);
        if (potentialCalls != null) {
            for (Call potentialCall : potentialCalls) {
                if ((parameters == null && potentialCall.getParameters().length == 0) 
                        || potentialCall.acceptsParameters(parameters)) {
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
     * Returns the {@link List} of <b>all</b> {@link Call}s of the given {@link ElementType} with the given name,
     * {@link ParameterType} denoting the return type, and {@link ParameterType}s denoting the parameters.<br>
     * <br>
     * This method first detects all available {@link Call}s with the given {@link ElementType}, name, and return type
     * by calling {@link #getCalls(ElementType, String, ParameterType)}. Based on that return value, it filters those
     * {@link Call}s matching the given parameters.
     * 
     * @param elementType the {@link ElementType} of the {@link Call}s to search for; should never be <code>null</code>
     * @param name the name of the {@link Call}s to search for; should never be <code>null</code>
     * @param returnType the {@link ParameterType} denoting the return type of the {@link Call}s to search for; should
     *        never be <code>null</code>
     * @param parameters the array of {@link ParameterType}s denoting the parameters of the {@link Call}s to search for;
     *        <code>null</code> or an <i>empty</i> array indicates that the {@link Calls} should not have any parameters
     * @return the {@link List} of available {@link Call}s with the given {@link ElementType}, name, return type, and
     *         parameters or <code>null</code>, if no such {@link Call}s are available 
     */
    public List<Call> getCalls(ElementType elementType, String name, ParameterType returnType, 
            ParameterType[] parameters) {
        List<Call> availableCalls = null;
        List<Call> potentialCalls = getCalls(elementType, name, returnType);
        if (potentialCalls != null) {
            for (Call potentialCall : potentialCalls) {
                if ((parameters == null && potentialCall.getParameters().length == 0) 
                        || potentialCall.acceptsParameters(parameters)) {
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
     * Returns the {@link List} of <b>all</b> {@link Call}s with the given name independent of their 
     * {@link ElementType}.<br>
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
                if (potentialCalls != null) {                    
                    for (Call potentialCall : potentialCalls) {
                        if (potentialCall.getFullyQualifiedName().equals(name)) {
// CHECKSTYLE:OFF
                            if (availableCalls == null) {
                                availableCalls = new ArrayList<Call>();
                            }
// CHECKSTYLE:ON
                            availableCalls.add(potentialCall);
                        }
                    }
                }
            }
        }
        return availableCalls;
    }
    
}
