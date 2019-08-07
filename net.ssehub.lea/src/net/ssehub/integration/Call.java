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
 * This class represents a call <code>c()</code> for operations, extractors, or analyzers, e.g.,
 * <code><b>Artifact</b>&lt;T&gt; name = c()</code>. The particular type of a call and, hence, its application depends
 * on the {@link ElementType} passed as a parameter to the constructor.
 * 
 * @author Christian Kroeher
 *
 */
public class Call extends LanguageElement {
    
    /**
     * The name of the type of element(s) this call will return.
     */
    private String returnType;
    
    /**
     * The array of names, which denote the elements this call accepts as parameters.
     */
    private String[] parameters;
    
    /**
     * The {@link Method} from where this call was created.
     */
    private Method sourceMethod;

    /**
     * Constructs a new {@link Call} with the given attributes.
     * 
     * @param elementType the {@link ElementType} of this new element, which must be {@link ElementType#OPERATION},
     *        {@link ElementType#EXTRACTOR_CALL}, or {@link ElementType#ANALYSIS_CALL}; any other type leads to a 
     *        {@link LanguageElementException}
     * @param name the name of this new element
     * @param returnType the name of the type of element(s) this call will return
     * @param parameters the array of names, which denote the elements this call accepts as parameters
     * @param sourceMethod the {@link Method} from where this new element is created
     * @param sourceClass the {@link Class} from where this new element is created
     * @param sourcePlugin the {@link File}, which is a Java archive file, from where this new element is created
     * @throws LanguageElementException if any of the above parameters is <code>null</code>, the element type does not
     *         match one of those defined above, the return type is <i>blank</i> or, the return type is 
     *         <code>void</code> and the element type is either {@link ElementType#EXTRACTOR_CALL} or 
     *         {@link ElementType#ANALYSIS_CALL}, the parameter list is <i>empty</i>, or the name is <i>blank</i>
     */
//CHECKSTYLE:OFF
    public Call(ElementType elementType, String name, String returnType, String[] parameters, Method sourceMethod,
            Class<?> sourceClass, File sourcePlugin) throws LanguageElementException {
        super(elementType, name, sourceClass, sourcePlugin);
        if (elementType != ElementType.OPERATION 
                && elementType != ElementType.EXTRACTOR_CALL 
                && elementType != ElementType.ANALYSIS_CALL) {
            throw new LanguageElementException("Type mismatch: \"" + elementType + "\" is not a valid call type");
        }
        if (returnType == null || returnType.isBlank()) {
            throw new LanguageElementException("The return type for the new language element is null or blank");
        }
        if (returnType.equals("void") && 
                (elementType == ElementType.EXTRACTOR_CALL || elementType == ElementType.ANALYSIS_CALL)) {
            throw new LanguageElementException("Extractor and analysis calls must have a non-void return type");
        }
        if (parameters == null) {
            throw new LanguageElementException("The parameter list for the new language element is null");
        }
        if (sourceMethod == null) {
            throw new LanguageElementException("The source method for the new language element is null");
        }
        this.returnType = returnType;
        this.parameters = parameters;
        this.sourceMethod = sourceMethod;
    }
//CHECKSTYLE:ON
    
    /**
     * Returns the name of the type of element(s) this call will return.
     * 
     * @return the name of the type of element(s) this call will return; never <code>null</code>
     */
    public String getReturnType() {
        return returnType;
    }
    
    /**
     * Returns the array of names, which denote the elements this call accepts as parameters.
     * 
     * @return the array of names, which denote the elements this call accepts as parameters; never <code>null</code>,
     *         but may be <i>empty</i>
     */
    public String[] getParameters() {
        return parameters;
    }
    
    /**
     * Returns the {@link Method} from where this call was created.
     * 
     * @return the {@link Method} from where this call was created; never <code>null</code>
     */
    public Method getSourceMethod() {
        return sourceMethod;
    }
    
    /**
     * {@inheritDoc}
     * 
     * In addition, two {@link Call}s are equal, if they have the same:
     * <ul>
     * <li>Return type</li>
     * <li>Source {@link Method}</li>
     * <li>
     * Number of parameters, where each parameter at a particular index in this {@link Call} is equal to the parameter
     * at the same index in the given {@link Call}
     * </li>
     * </ul>
     */
    @Override
    public boolean equals(LanguageElement comparable) {
        boolean isEqual = super.equals(comparable);
        if (isEqual) {
            Call comparableCall = (Call) comparable;
            if (this.returnType.equals(comparableCall.getReturnType()) 
                    && this.sourceMethod == comparableCall.getSourceMethod()) {
                String[] comparableParameters = comparableCall.getParameters();
                if (this.parameters.length == comparableParameters.length) {
                    int parametersCounter = 0;
                    while (isEqual && parametersCounter < this.parameters.length) {
                        if (!this.parameters[parametersCounter].equals(
                                comparableParameters[parametersCounter])) {
                            isEqual = false;
                        }
                        parametersCounter++;
                    }
                } else {
                    isEqual = false;
                }
            } else {
                isEqual = false;
            }
        }
        return isEqual;
    }
}
