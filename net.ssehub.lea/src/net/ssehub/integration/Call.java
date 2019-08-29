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
     * The name of the parameter type for which this call represents a member operation. May be <code>null</code>, if
     * this call does not represent a member operation.
     */
    private String parentParameterType;
    
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
     *         match one of those defined above, the return type is <i>blank</i>, or the return type is 
     *         <code>void</code> and the element type is either {@link ElementType#EXTRACTOR_CALL} or 
     *         {@link ElementType#ANALYSIS_CALL}, or the name is <i>blank</i>
     */
//CHECKSTYLE:OFF
    public Call(ElementType elementType, String name, String returnType, String[] parameters, Method sourceMethod,
            Class<?> sourceClass, File sourcePlugin) throws LanguageElementException {
        super(elementType, name, sourceClass, sourcePlugin);
        initializeCall(elementType, returnType, parameters, sourceMethod);
        this.parentParameterType = null;
    }
//CHECKSTYLE:ON
    
    /**
     * Constructs a new {@link Call} of the type {@link ElementType#OPERATION} and with the given attributes, which will
     * represent a member operations of a {@link ParameterType}, e.g., 
     * <code>parameterTypeInstance.memberOperation()</code>. Hence, this constructor requires a parent parameter type as
     * an additional parameter. For general {@link Call}s use 
     * {@link #Call(ElementType, String, String, String[], Method, Class, File)}.
     * 
     * @param name the name of this new element
     * @param returnType the name of the type of element(s) this call will return
     * @param parameters the array of names, which denote the elements this call accepts as parameters
     * @param parentParameterType the name of the parameter type for which this call represents a member operation
     * @param sourceMethod the {@link Method} from where this new element is created
     * @param sourceClass the {@link Class} from where this new element is created
     * @param sourcePlugin the {@link File}, which is a Java archive file, from where this new element is created
     * @throws LanguageElementException if any of the above parameters is <code>null</code>, the return type is 
     *         <i>blank</i>, the parent parameter type is <i>blank</i>, or the name is <i>blank</i>
     */
//CHECKSTYLE:OFF
    public Call(String name, String returnType, String[] parameters, String parentParameterType,
            Method sourceMethod, Class<?> sourceClass, File sourcePlugin) throws LanguageElementException {
        super(ElementType.OPERATION, name, sourceClass, sourcePlugin);
        initializeCall(ElementType.OPERATION, returnType, parameters, sourceMethod);
        if (parentParameterType == null || parentParameterType.isBlank()) {
            throw new LanguageElementException("The parent parameter type for the new language element is not defined");
        }
        this.parentParameterType = parentParameterType;
    }
//CHECKSTYLE:ON
    
    /**
     * Initializes the attributes of this {@link Call} with the given parameter values.
     * 
     * 
     * @param elementType the {@link ElementType} of this new element, which must be {@link ElementType#OPERATION},
     *        {@link ElementType#EXTRACTOR_CALL}, or {@link ElementType#ANALYSIS_CALL}; any other type leads to a 
     *        {@link LanguageElementException}
     * 
     * @param returnType the name of the type of element(s) this call will return
     * @param parameters the array of names, which denote the elements this call accepts as parameters
     * @param sourceMethod the {@link Method} from where this new element is created
     * @throws LanguageElementException if return type, parameters, or source method are <code>null</code>, the element
     *         type does not  match one of those defined above, the return type is <i>blank</i>, or the return type is 
     *         <code>void</code> and the element type is either {@link ElementType#EXTRACTOR_CALL} or 
     *         {@link ElementType#ANALYSIS_CALL}, or the name is <i>blank</i>
     */
    private void initializeCall(ElementType elementType, String returnType, String[] parameters, Method sourceMethod) 
            throws LanguageElementException {
        if (elementType != ElementType.OPERATION 
                && elementType != ElementType.EXTRACTOR_CALL 
                && elementType != ElementType.ANALYSIS_CALL) {
            throw new LanguageElementException("Type mismatch: \"" + elementType + "\" is not a valid call type");
        }
        if (returnType == null || returnType.isBlank()) {
            throw new LanguageElementException("The return type for the new language element is null or blank");
        }
        if (returnType.equals("void") 
                && (elementType == ElementType.EXTRACTOR_CALL || elementType == ElementType.ANALYSIS_CALL)) {
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
     * Checks whether this call is a member operation (<code>true</code>) or not (<code>false</code>).
     * 
     * @return <code>true</code>, if this call is a member operation; <code>false</code> otherwise
     * @see #isMemberOperationOf(String)
     */
    public boolean isMemberOperation() {
        return parentParameterType != null;
    }
    
    /**
     * Checks whether this call is a member operation of the parameter type identified by the given name
     * (<code>true</code>) or not (<code>false</code>).
     * 
     * @param parameterTypeName the name of the parameter type to check whether this call is a member operation of it;
     *        should never be <code>null</code>
     * @return <code>true</code>, if this call is a member operation of the parameter type identified by the given name;
     *         <code>false</code> otherwise
     */
    public boolean isMemberOperationOf(String parameterTypeName) {
        return parentParameterType.equals(parameterTypeName);
    }
    
    /**
     * Returns the name of the parameter type for which this call represents a member operation.
     * 
     * @return the name of the parameter type for which this call represents a member operation or never 
     *         <code>null</code>, if this call is not a member operation
     * @see #Call(String, String, String[], String, Method, Class, File)
     */
    public String getParentParameterType() {
        return parentParameterType;
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
            isEqual = hasEqualCallAttributes(comparable);
        }
        return isEqual;
    }
    
    /**
     * Performs the same equality check as {@link #equals(LanguageElement)}, but without consideration of the 
     * {@link ElementType} of this and the given {@link LanguageElement}.
     * 
     * @param comparable the {@link LanguageElement} to compare to this element, while ignoring the {@link ElementType};
     *        should never be <code>null</code>
     * @return <code>true</code>, if all attributes except for the {@link ElementType} of this {@link LanguageElement}
     *         are equal to the attributes of the given {@link LanguageElement}; <code>false</code> otherwise
     */
    @Override
    public boolean equalsIgnoreType(LanguageElement comparable) {
        boolean isEqual = super.equalsIgnoreType(comparable);
        if (isEqual) {
            isEqual = hasEqualCallAttributes(comparable);
        }
        return isEqual;
    }
    
    /**
     * Checks whether the {@link #returnType}, the {@link #parameters}, and the {@link #sourceMethod} of this
     * {@link Call} and the given {@link LanguageElement} are equal. Hence, this method will cast the given element into
     * a {@link Call} object without further checks.
     * 
     * @param comparable the {@link LanguageElement} of runtime class {@link Call} to compare to this {@link Call};
     *        should never be <code>null</code>
     * @return <code>true</code>, if the given {@link LanguageElement} is a {@link Call} and its call-specific
     *         attributes are equal to the attributes of this {@link Call}; <code>false</code> otherwise
     */
    private boolean hasEqualCallAttributes(LanguageElement comparable) {
        boolean hasEqualCallAttributes = true;
        Call comparableCall = (Call) comparable; // Callers ensure equality of this and the given elements runtime class
        if (this.returnType.equals(comparableCall.getReturnType()) 
                && this.sourceMethod.toGenericString().equals(comparableCall.getSourceMethod().toGenericString())) {
            String[] comparableParameters = comparableCall.getParameters();
            if (this.parameters.length == comparableParameters.length) {
                int parametersCounter = 0;
                while (hasEqualCallAttributes && parametersCounter < this.parameters.length) {
                    if (!this.parameters[parametersCounter].equals(
                            comparableParameters[parametersCounter])) {
                        hasEqualCallAttributes = false;
                    }
                    parametersCounter++;
                }
            } else {
                hasEqualCallAttributes = false;
            }
        } else {
            hasEqualCallAttributes = false;
        }
        return hasEqualCallAttributes;
    }
}
