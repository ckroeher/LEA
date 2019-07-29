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

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import net.ssehub.integration.ElementType;
import net.ssehub.integration.ParameterType;

/**
 * Indicates that the annotated class represents a {@link ParameterType} of the type 
 * {@link ElementType#ARTIFACT_PARAMETER_TYPE}.
 * 
 * @author Christian Kroeher
 *
 */
@Documented
@Retention(RUNTIME)
@Target(TYPE)
public @interface ArtifactParameterType {

    /**
     * Returns the (symbolic) name of this {@link ElementType#ARTIFACT_PARAMETER_TYPE}. If this name is defined, it is 
     * proposed as language element instead of using the name of the annotated class.
     * 
     * @return the (symbolic) name of this {@link ElementType#ARTIFACT_PARAMETER_TYPE}; the default valued is an 
     *         <i>empty</i> string
     */
    public String name() default "";
    
    /**
     * Returns the (symbolic) name of the optional parameter type of this {@link ElementType#ARTIFACT_PARAMETER_TYPE},
     * if the annotated class is a generic one. If this parameter name is defined, it is used as part of the proposal
     * of the actual language element the annotated class represents instead of using the name of the parameter type. If
     * the annotated class is not a generic one, this parameter name is ignored. 
     * 
     * @return the (symbolic) name of the optional parameter type of this {@link ElementType#ARTIFACT_PARAMETER_TYPE},
     *         if the annotated class is a generic one; the default valued is an <i>empty</i> string
     */
    public String parameterName() default "";
}
