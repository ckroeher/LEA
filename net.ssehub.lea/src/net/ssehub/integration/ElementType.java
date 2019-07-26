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

/**
 * This enumeration defines the types of external language elements, which are used to differentiate the particular 
 * {@link LanguageElement}. These types are:
 * <ul>
 * <li>{@link ElementType#ARTIFACT_PARAMETER_TYPE}</li>
 * <li>{@link ElementType#FRAGMENT_PARAMETER_TYPE}</li>
 * <li>{@link ElementType#RESULT_PARAMETER_TYPE}</li>
 * <li>{@link ElementType#CHANGE_IDENTIFIER}</li>
 * <li>{@link ElementType#OPERATION}</li>
 * <li>{@link ElementType#EXTRACTOR_CALL}</li>
 * <li>{@link ElementType#ANALYSIS_CALL}</li>
 * </ul>
 * 
 * @author Christian Kroeher
 *
 */
public enum ElementType {

    /**
     * Defines the element to be an artifact parameter type <code>T</code>. It can be used as follows: 
     * <code><b>Artifact</b>&lt;T&gt; ...</code>
     */
    ARTIFACT_PARAMETER_TYPE,
    
    /**
     * Defines the element to be a fragment parameter type <code>T</code>. It can be used as follows: 
     * <code><b>Fragment</b>&lt;T&gt; ...</code>
     */
    FRAGMENT_PARAMETER_TYPE,
    
    /**
     * Defines the element to be a result parameter type <code>T</code>. It can be used as follows: 
     * <code><b>Result</b>&lt;T&gt; ...</code>
     */
    RESULT_PARAMETER_TYPE,
    
    /**
     * Defines the element to be a change identifier <code>CI</code>. It can be used as follows: 
     * <code><b>apply</b> CI <b>to</b> ...</code> 
     */
    CHANGE_IDENTIFIER,
    
    /**
     * Defines the element to be an operation <code>op()</code>. The way it can be used depends on the specific type of
     * operation, e.g., <code>element.op()</code>, or <code>op()</code>, etc.
     */
    OPERATION,
    
    /**
     * Defines the element to be an extractor call <code>ex()</code>. The way it can be used depends on the specific
     * extractor, e.g., <code>Fragment&lt;T&gt; name = ex()</code>, etc.
     */
    EXTRACTOR_CALL,
    
    /**
     * Defines the element to be an analysis call <code>an()</code>. The way it can be used depends on the specific
     * analyzer, e.g., <code>Result&lt;T&gt; name = an()</code>, etc.
     */
    ANALYSIS_CALL
}
