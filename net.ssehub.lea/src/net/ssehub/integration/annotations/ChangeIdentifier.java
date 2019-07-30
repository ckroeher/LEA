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

/**
 * Indicates that the annotated class represents a {@link net.ssehub.integration.ChangeIdentifier}.
 * 
 * @author Christian Kroeher
 *
 */
@Documented
@Retention(RUNTIME)
@Target(TYPE)
public @interface ChangeIdentifier {

    /**
     * Returns the (symbolic) name of this {@link net.ssehub.integration.ChangeIdentifier}. If this name is defined, it
     * is proposed as language element instead of using the name of the annotated class.
     * 
     * @return the (symbolic) name of this {@link net.ssehub.integration.ChangeIdentifier}; the default value is an 
     *         <i>empty</i> string
     */
    public String name() default "";
    
    /**
     * Returns an array of (symbolic) names of those artifact or fragment types to which this
     * {@link net.ssehub.integration.ChangeIdentifier} is assignable to. This value is mandatory.
     * 
     * @return an array of (symbolic) names of those artifact or fragment types to which this
     *         {@link net.ssehub.integration.ChangeIdentifier} is assignable to
     */
    public String[] assignableTo();
}
