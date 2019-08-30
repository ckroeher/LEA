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

/**
 * This class represents a parameter type <code>T</code> for the declaration of artifacts, fragments, or results, e.g.,
 * <code><b>Artifact</b>&lt;T&gt;...</code>. The particular generic type to which this type is applicable to depends on
 * the {@link ElementType} passed as a parameter to the constructor.
 * 
 * @author Christian Kroeher
 *
 */
public class ParameterType extends LanguageElement {

    /**
     * Constructs a new {@link ParameterType} with the given attributes.
     * 
     * @param elementType the {@link ElementType} of this new element, which must be
     *        {@link ElementType#ARTIFACT_PARAMETER_TYPE}, {@link ElementType#FRAGMENT_PARAMETER_TYPE},
     *        or {@link ElementType#RESULT_PARAMETER_TYPE}; any other type leads to a {@link LanguageElementException}
     * @param name the name of this new element
     * @param sourceClass the {@link Class} from where this new element is created
     * @param sourcePlugin the {@link File}, which is a Java archive file, from where this new element is created
     * @throws LanguageElementException if any of the above parameters is <code>null</code>, the element type does not
     *         match one of those defined above, or the name is <i>blank</i>
     */
    public ParameterType(ElementType elementType, String name, Class<?> sourceClass, File sourcePlugin)
            throws LanguageElementException {
        super(elementType, name, sourceClass, sourcePlugin);
        if (elementType != ElementType.ARTIFACT_PARAMETER_TYPE 
                && elementType != ElementType.FRAGMENT_PARAMETER_TYPE 
                && elementType != ElementType.RESULT_PARAMETER_TYPE) {
            throw new LanguageElementException("Type mismatch: \"" + elementType + "\" is not a valid parameter type");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getFullyQualifiedName() {
        return getSourceClass().getCanonicalName();
    }
}
