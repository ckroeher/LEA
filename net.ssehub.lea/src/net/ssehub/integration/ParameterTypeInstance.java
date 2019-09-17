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
 * This class represents a wrapper of the {@link ParameterType} class for the usage as return types or parameters of
 * {@link Call} instances. It provides additional information, like the usage of sets of these types.
 * 
 * @author Christian Kroeher
 *
 */
public class ParameterTypeInstance {

    /**
     * The {@link ParameterType} this instance represents.
     */
    private ParameterType parameterType;
    
    /**
     * The definition of whether this instance represents a set (<code>true</code>) or not (<code>false</code>).
     */
    private boolean isSet;
    
    /**
     * Constructs a new {@link ParameterTypeInstance} instance.
     * 
     * @param parameterType the {@link ParameterType} this instance represents; should never be <code>null</code>
     * @param isSet the definition of whether this instance represents a set (<code>true</code>) or not 
     *        (<code>false</code>)
     * @throws LanguageElementException if the given {@link ParameterType} is <code>null</code>
     */
    public ParameterTypeInstance(ParameterType parameterType, boolean isSet) throws LanguageElementException {
        if (parameterType == null) {
            throw new LanguageElementException("Parameter type is null");
        }
        this.parameterType = parameterType;
        this.isSet = isSet;
    }
    
    /**
     * Returns the {@link ParameterType} this instance represents.
     * 
     * @return the {@link ParameterType} this instance represents
     */
    public ParameterType getParameterType() {
        return parameterType;
    }
    
    /**
     * Returns the definition of whether this instance represents a set (<code>true</code>) or not (<code>false</code>).
     * 
     * @return <code>true</code>, if this instance represents a set; <code>false</code> otherwise
     */
    public boolean isSet() {
        return isSet;
    }
    
    /**
     * Compares this {@link ParameterTypeInstance} with the given {@link ParameterTypeInstance} for equality. This is
     * the case, if:
     * <ul>
     * <li>The {@link ParameterType}s both elements represent are equal</li>
     * <li>The set definitions of both elements are equal</li>
     * </ul>
     * @param comparable the {@link ParameterTypeInstance} to compare to this element
     * @return <code>true</code>, if this {@link ParameterTypeInstance} is equal to the given
     *         {@link ParameterTypeInstance}; <code>false</code> otherwise
     */
    public boolean equals(ParameterTypeInstance comparable) {
        boolean isEqual = false;
        if (comparable != null && parameterType.equals(comparable.getParameterType()) && isSet == comparable.isSet()) {
            isEqual = true;
        }
        return isEqual;
    }

}
