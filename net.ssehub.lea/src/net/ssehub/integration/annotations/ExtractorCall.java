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
package net.ssehub.integration.annotations;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import net.ssehub.integration.Call;
import net.ssehub.integration.ElementType;
import net.ssehub.integration.LanguageElementException;

/**
 * Indicates that the annotated method represents a {@link Call} of the type {@link ElementType#EXTRACTOR_CALL}.
 * 
 * @author Christian Kroeher
 *
 */
@Documented
@Retention(RUNTIME)
@Target(METHOD)
/**
 * @author Christian Kroeher
 *
 */
public @interface ExtractorCall {

    /**
     * Returns the (symbolic) name of this {@link ElementType#EXTRACTOR_CALL}. If this name is defined, it is proposed
     * as language element instead of using the name of the annotated method.
     * 
     * @return the (symbolic) name of this {@link ElementType#EXTRACTOR_CALL}; the default value is an <i>empty</i>
     *         string
     */
    public String name() default "";
    
    /**
     * Returns the (symbolic) name of the return type of this {@link ElementType#EXTRACTOR_CALL}. If this return type
     * name is defined, it is used as part of the proposal of the actual call the annotated method represents
     * instead of using the name of the actual return type of that method.<br>
     * <br>
     * <b>Important:</b> If the annotated method has no return type (<code>void</code>), this parameter must be defined
     * as an extractor call is solely used for extracting fragments and, hence, requires a return type; otherwise the
     * {@link Call} constructor will throw an {@link LanguageElementException} and the extractor call will not be
     * available as part of the language. 
     * 
     * @return the (symbolic) name of the return type of this {@link ElementType#EXTRACTOR_CALL}; the default value is
     *         an <i>empty</i> string
     */
    public String returnType() default "";
    
    /**
     * Returns an array of (symbolic) names representing the parameters of this {@link ElementType#EXTRACTOR_CALL}. If
     * this array is defined, its elements are used as part of the proposal of the actual call the annotated method
     * represents instead of using the names of the actual parameters of that method.
     * 
     * @return the array of (symbolic) names representing the parameters of this {@link ElementType#EXTRACTOR_CALL}; the
     *         default value is an <i>empty</i> array
     */
    public String[] parameters() default { };
}
