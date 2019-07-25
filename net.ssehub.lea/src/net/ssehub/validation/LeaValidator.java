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
package net.ssehub.validation;

import java.util.HashSet;

import org.eclipse.emf.common.util.EList;
import org.eclipse.xtext.validation.Check;

import net.ssehub.lea.AnalysisDefinition;
import net.ssehub.lea.ElementDeclaration;
import net.ssehub.lea.LeaPackage;

/**
 * This class contains custom validation rules.
 * 
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#validation
 * 
 * @author Christian Kroeher
 *
 */
public class LeaValidator extends AbstractLeaValidator {
    
    /**
     * Checks the entire {@link AnalysisDefinition} for {@link ElementDeclaration}s, which have equal names. In such a
     * case, the duplicated names are marked with an error.
     * 
     * @param analysis the {@link AnalysisDefinition} in which the elements will be checked for duplicated names
     */
    @Check
    public void checkNoDuplicatedElementNames(AnalysisDefinition analysis) {
        EList<ElementDeclaration> elementDeclarations = analysis.getElementDeclarations();
        HashSet<String> visitedElementNames = new HashSet<String>();
        String currentElementName;
        for (ElementDeclaration element : elementDeclarations) {
            currentElementName = element.getName();
            if (visitedElementNames.contains(currentElementName)) {
                error("Duplicated element \"" + currentElementName + "\"", element,
                        LeaPackage.Literals.ELEMENT_DECLARATION__NAME);
            } else {
                visitedElementNames.add(currentElementName);
            }
        }
    }

}
