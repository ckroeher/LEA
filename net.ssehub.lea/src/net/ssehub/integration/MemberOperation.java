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

import java.io.File;
import java.lang.reflect.Method;

/**
 * This class represents a special call type, which defines {@link Call}s of the type {@link ElementType#OPERATION} to
 * be member operations of a {@link ParameterType}, e.g., <code>parameterTypeInstance.memberOperation()</code>. Hence,
 * the type of this special call must always be {@link ElementType#OPERATION} and the parent parameter type must always
 * be defined via the constructor.
 * 
 * @author Christian Kroeher
 *
 */
public class MemberOperation extends Call {
    
    /**
     * The name of the parameter type for which this call represents a member operation.
     */
    private String parentParameterType;

    /**
     * Constructs a new {@link MemberOperation} as a {@link Call} of the type {@link ElementType#OPERATION} and
     * with the given attributes.
     * 
     * @param name the name of this new element
     * @param returnType the name of the type of element(s) this call will return
     * @param parameters the array of names, which denote the elements this operation accepts as parameters
     * @param parentParameterType the name of the parameter type for which this call represents a member operation 
     * @param sourceMethod the {@link Method} from where this new element is created
     * @param sourceClass the {@link Class} from where this new element is created
     * @param sourcePlugin the {@link File}, which is a Java archive file, from where this new element is created
     * @throws LanguageElementException if any of the above parameters is <code>null</code>, the return type is 
     *         <i>blank</i>, the parent parameter type is <i>blank</i>, or the name is <i>blank</i>
     */
//CHECKSTYLE:OFF
    public MemberOperation(String name, String returnType, String[] parameters, String parentParameterType,
            Method sourceMethod, Class<?> sourceClass, File sourcePlugin) throws LanguageElementException {
        super(ElementType.OPERATION, name, returnType, parameters, sourceMethod, sourceClass, sourcePlugin);
        if (parentParameterType == null || parentParameterType.isBlank()) {
            throw new LanguageElementException("The parent parameter type for the new language element is not defined");
        }
        this.parentParameterType = parentParameterType;
    }
//CHECKSTYLE:ON
    
    /**
     * Returns the name of the parameter type for which this call represents a member operation.
     * 
     * @return the name of the parameter type for which this call represents a member operation; never <code>null</code>
     */
    public String getParentParameterType() {
        return parentParameterType;
    }

}
