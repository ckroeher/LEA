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
import net.ssehub.integration.ParameterType;

/**
 * Indicates that the annotated method represents a {@link Call} of the type {@link ElementType#OPERATION}.
 * 
 * @author Christian Kroeher
 *
 */
@Documented
@Retention(RUNTIME)
@Target(METHOD)
public @interface Operation {

    /**
     * Returns the (symbolic) name of this {@link ElementType#OPERATION}. If this name is defined, it is proposed as
     * language element instead of using the name of the annotated method.
     * 
     * @return the (symbolic) name of this {@link ElementType#OPERATION}; the default value is an <i>empty</i> string
     */
    public String name() default "";
    
    /**
     * Returns the (symbolic) name of the return type of this {@link ElementType#OPERATION}. If this return type name is
     * defined, it is used as part of the proposal of the actual operation the annotated method represents instead of
     * using the name of the actual return type of that method.
     * 
     * @return the (symbolic) name of the return type of this {@link ElementType#OPERATION}; the default value is an
     *         <i>empty</i> string
     */
    public String returnType() default "";
    
    /**
     * Returns an array of (symbolic) names representing the parameters of this {@link ElementType#OPERATION}. If this 
     * array is defined, its elements are used as part of the proposal of the actual operation the annotated method
     * represents instead of using the names of the actual parameters of that method.
     * 
     * @return the array of (symbolic) names representing the parameters of this {@link ElementType#OPERATION}; the
     *         default value is an <i>empty</i> array
     */
    public String[] parameters() default { };
    
    /**
     * Returns the name of the {@link ParameterType} for which this {@link ElementType#OPERATION} represents a member
     * operation. If this name is defined, the annotated method is interpreted as a member method of the defined 
     * {@link ParameterType} and, hence, can only be called like <code>parameterTypeInstance.method()</code>. If this 
     * name is not defined, the annotated method is interpreted as global method and, hence, can only be called like 
     * <code>method()</code>.
     * 
     * @return the name of the {@link ParameterType} for which this {@link ElementType#OPERATION} represents a member
     *         operation; the default value is an <i>empty</i> string, which results in interpreting the annotated 
     *         method as being global
     */
    public String isMemberOf() default "";
}
