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
public class Call extends LanguageElement implements IFinalizable {
    
    /**
     * The fully-qualified name of this element.
     */
    private String fullyQualifiedName;
    
    /**
     * The {@link ParameterType} this call will return.
     */
    private ParameterType returnType;
    
    /**
     * The array of {@link ParameterType}s this call accepts as parameters.
     */
    private ParameterType[] parameters;
    
    /**
     * The {@link ParameterType} for which this call represents a member operation. May be <code>null</code>, if this
     * call does not represent a member operation.
     */
    private ParameterType parentParameterType;
    
    /**
     * The {@link Method} from where this call was created.
     */
    private Method sourceMethod;
    
    /**
     * The definition of whether the construction of this call is completed (<code>true</code>) or not
     * (<code>false</code>).
     * 
     * @see #finalize()
     */
    private boolean finalized;
    
    /**
     * Constructs a new <b>incomplete</b> {@link Call} instance with the given attributes omitting the required 
     * definition of return type as well as (optional) parameters and parent parameter type. These elements must be
     * added using {@link Call#finalize(ParameterType, ParameterType[])} in order to complete this construction. This
     * enables {@link Call#isFinal()} returning <code>true</code> and, hence, the addition of this instance to the
     * {@link LanguageRegistry}.
     * 
     * @param elementType the {@link ElementType} of this new element, which must be {@link ElementType#OPERATION},
     *        {@link ElementType#EXTRACTOR_CALL}, or {@link ElementType#ANALYSIS_CALL}; any other type leads to a 
     *        {@link LanguageElementException}
     * @param name the name of this new element
     * @param sourceMethod the {@link Method} from where this new element is created
     * @param sourceClass the {@link Class} from where this new element is created
     * @param sourcePlugin the {@link File}, which is a Java archive file, from where this new element is created
     * @throws LanguageElementException if any of the above parameters is <code>null</code>, or the element type does
     *         not match one of those defined above, or the name is <i>blank</i>
     */
    public Call(ElementType elementType, String name, Method sourceMethod, Class<?> sourceClass, File sourcePlugin)
            throws LanguageElementException {
        super(elementType, name, sourceClass, sourcePlugin);
        if (elementType != ElementType.OPERATION 
                && elementType != ElementType.EXTRACTOR_CALL 
                && elementType != ElementType.ANALYSIS_CALL) {
            throw new LanguageElementException("Type mismatch: \"" + elementType + "\" is not a valid call type");
        }
        if (sourceMethod == null) {
            throw new LanguageElementException("The source method for the new language element is null");
        }
        this.sourceMethod = sourceMethod;
        returnType = null;
        parameters = null;
        parentParameterType = null;
        finalized = false;
        // Construct the fully-qualified name of this element
        String sourceMethodGenericString = sourceMethod.toGenericString().replaceAll("(\\$|\\#)", ".");
        int substringStartIndex = sourceMethodGenericString.lastIndexOf(' ') + 1;
        if (sourceMethodGenericString.contains("(")) {
            sourceMethodGenericString = sourceMethodGenericString.substring(0, sourceMethodGenericString.indexOf('('));
        }
        int substringEndIndex = sourceMethodGenericString.lastIndexOf('.') + 1;        
        fullyQualifiedName = sourceMethodGenericString.substring(substringStartIndex, substringEndIndex) 
                + getName();
    }
    
    /**
     * Completes the construction of this {@link Call} instance by setting the first given {@link ParameterType} as the
     * return type and (optionally) the given array of {@link ParameterType}s as the parameters and the second given
     * {@link ParameterType} as the parent parameter type of this instance.
     * 
     * @param returnType the {@link ParameterType} to be set as the return type of this call
     * @param parameters the array of {@link ParameterType}s to be set as the parameters of this call; may be 
     *        <code>null</code> for calls, which do not require parameters
     * @param parentParameterType the {@link ParameterType} to be set as the parent parameter type of this call; may be 
     *        <code>null</code> for calls, which are not a member operation
     * @throws LanguageElementException if
     *         <ul>
     *         <li>the construction of this instance is already completed (this method was called without exception once
     *             before)</li>
     *         <li>the given {@link ParameterType} for the <b>return type</b> is <code>null</code> or represents
     *             <code>void</code>, while this call represents an extractor or an analysis call</li>
     *         <li>the given array of {@link ParameterType}s for the <b>parameters</b> contains elements, which are
     *             <code>null</code></li>
     *         <li>the given {@link ParameterType} for the <b>parent parameter type</b> is not <code>null</code>, while
     *             this call represents an extractor or an analysis call</li>
     *         </ul>
     */
    public void finalize(ParameterType returnType, ParameterType[] parameters, ParameterType parentParameterType)
            throws LanguageElementException {
        if (!isFinal()) {            
            setReturnType(returnType);
            setParameters(parameters);
            setParentParameterType(parentParameterType);
            finalized = true;
        } else {
            throw new LanguageElementException("The construction of this call is already completed");
        }
    }
    
    /**
     * Sets the given {@link ParameterType} as the return type of this call.
     * 
     * @param returnType the {@link ParameterType} to be set as the return type of this call
     * @throws LanguageElementException if the given {@link ParameterType} is <code>null</code>, or represents
     *         <code>void</code>, while this call represents an extractor or an analysis call
     */
    private void setReturnType(ParameterType returnType) throws LanguageElementException {
        if (returnType == null) {
            throw new LanguageElementException("The return type for this call is null");
        }
        if (returnType.getName().equals("void") 
                && (getElementType() == ElementType.EXTRACTOR_CALL || getElementType() == ElementType.ANALYSIS_CALL)) {
            throw new LanguageElementException("Extractor and analysis calls must have a non-void return type");
        }
        this.returnType = returnType;
    }
    
    /**
     * Sets the given array of {@link ParameterType}s as the parameters of this call.
     * 
     * @param parameters the array of {@link ParameterType}s to be set as the parameters of this call; may be 
     *        <code>null</code> for calls, which do not require parameters
     * @throws LanguageElementException if the given array of {@link ParameterType}s contains elements, which are 
     *         <code>null</code>
     */
    private void setParameters(ParameterType[] parameters) throws LanguageElementException {
        if (parameters != null && parameters.length > 0) {            
            for (int i = 0; i < parameters.length; i++) {
                if (parameters[i] == null) {
                    throw new LanguageElementException("A parameter is null");
                }
            }
            this.parameters = parameters;
        }
    }
    
    /**
     * Sets the given {@link ParameterType} as the parent parameter type of this call, if this call's element type is
     * {@link ElementType#OPERATION}. For all other call element types, calling this method results in an exception.
     * Further, setting this value declares this call to represent a member operation that can only be called via an
     * instance of that parent parameter type.
     *  
     * @param parentParameterType the {@link ParameterType} to be set as the parent parameter type of this call; may be 
     *        <code>null</code> for calls, which are not a member operation
     * @throws LanguageElementException if the given {@link ParameterType} is not <code>null</code>, while the element
     *         type of this call is not {@link ElementType#OPERATION}
     */
    private void setParentParameterType(ParameterType parentParameterType) throws LanguageElementException {
        if (parentParameterType != null && getElementType() != ElementType.OPERATION) {
            throw new LanguageElementException("The parent parameter type can only be set for operations");
        }
        this.parentParameterType = parentParameterType;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String getFullyQualifiedName() {
        return fullyQualifiedName;
    }
    
    /**
     * Returns {@link ParameterType} this call will return.
     * 
     * @return the {@link ParameterType} this call will return or <code>null</code>, if the construction of this call is
     *         not completed yet
     * @see #isFinal()
     */
    public ParameterType getReturnType() {
        return returnType;
    }
    
    /**
     * Returns the array of {@link ParameterType}s, which denote the elements this call accepts as parameters.
     * 
     * @return the array of {@link ParameterType}s, which denote the elements this call accepts as parameters, or 
     *         <code>null</code>, if the construction of this call is not completed yet or this call does not accept any
     *         parameters
     * @see #isFinal()
     */
    public ParameterType[] getParameters() {
        return parameters;
    }
    
    /**
     * Returns the parent {@link ParameterType} for which this call represents a member operation.
     * 
     * @return the parent {@link ParameterType} for which this call represents a member operation or <code>null</code>,
     *         if the construction of this call is not completed yet or this call is not a member operation
     */
    public ParameterType getParentParameterType() {
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
        return isMemberOperation() 
                && (parentParameterType.getName().equals(parameterTypeName) 
                        || parentParameterType.getFullyQualifiedName().equals(parameterTypeName));
    }
    
    /**
     * {@inheritDoc}
     * 
     * In addition, two {@link Call}s are equal, if their construction is completed ({@link #isFinal()} returns 
     * <code>true</code>) and they have the same:
     * <ul>
     * <li>Return type</li>
     * <li>Source {@link Method}</li>
     * <li>Number of parameters, where each parameter at a particular index of this {@link Call} is equal to the
     *     parameter at the same index of the given {@link Call}</li>
     * </ul>
     */
    @Override
    public boolean equals(LanguageElement comparable) {
        boolean isEqual = false;
        if (comparable instanceof Call) {
            Call comparableCall = (Call) comparable;
            if (this.isFinal() && comparableCall.isFinal() && super.equals(comparableCall)) {
                isEqual = hasEqualCallAttributes(comparableCall);
            }
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
        boolean isEqual = false;
        if (comparable instanceof Call) {
            Call comparableCall = (Call) comparable;
            if (this.isFinal() && comparableCall.isFinal() && super.equalsIgnoreType(comparableCall)) {
                isEqual = hasEqualCallAttributes(comparableCall);
            }
        }
        return isEqual;
    }
    
    /**
     * Checks whether the {@link #returnType}, the {@link #parameters}, the {@link #sourceMethod}, and the 
     * {@link #parentParameterType} of this {@link Call} and the given {@link Call} are equal.
     * 
     * @param comparableCall the {@link Call} to compare to this {@link Call}; should never be <code>null</code>
     * @return <code>true</code>, if the call-specific attributes of the given {@link Call} are equal to the attributes
     *         of this {@link Call}; <code>false</code> otherwise
     */
    private boolean hasEqualCallAttributes(Call comparableCall) {
        boolean hasEqualCallAttributes = false;
        if (this.sourceMethod.toGenericString().equals(comparableCall.getSourceMethod().toGenericString()) 
                && this.returnType.equals(comparableCall.getReturnType())) {
//CHECKSTYLE:OFF
            if ((this.parentParameterType == null && comparableCall.getParentParameterType() == null)
                    || (this.parentParameterType != null && comparableCall.getParentParameterType() != null
                        && this.parentParameterType.equals(comparableCall.getParentParameterType()))) {
                ParameterType[] comparableParameters = comparableCall.getParameters();
                if ((this.parameters == null && comparableParameters == null)
                        || (this.parameters != null && comparableParameters != null 
                            && this.parameters.length == comparableParameters.length)) {
//CHECKSTYLE:ON
                    boolean haveEqualParameters = true;
                    int parametersCounter = 0;
                    while (haveEqualParameters && parametersCounter < this.parameters.length) {
                        if (!this.parameters[parametersCounter].equals(comparableParameters[parametersCounter])) {
                            haveEqualParameters = false;
                        }
                        parametersCounter++;
                    }
                    hasEqualCallAttributes = haveEqualParameters;
                }
            }
        }
        return hasEqualCallAttributes;
    }
    
    /**
     * Compares the given parameters with the defined {@link #parameters} of this {@link Call} in exactly their order
     * in the arrays for equality. This is the case, if for each given parameter at a specific index an equal parameter
     * at the same index in {@link #parameters} exists. 
     *   
     * @param parameters the parameters to compare to the {@link #parameters} of this {@link Call}
     * @return <code>true</code>, if either the given parameters and the {@link #parameters} of this {@link Call} are
     *         <code>null</code>, or for each given parameter at a specific index an equal parameter at the same index
     *         in {@link #parameters} exists; <code>false</code> otherwise
     */
    public boolean acceptsParameters(ParameterType[] parameters) {
        boolean acceptsParameters = false;
        if (this.parameters == null && parameters == null) {
            acceptsParameters = true;
        } else if (this.parameters != null && parameters != null && this.parameters.length == parameters.length) {
            int parametersCounter = 0;
            boolean parametersEqual = true;
            while (parametersEqual && parametersCounter < parameters.length) {
                parametersEqual = parameters[parametersCounter].equals(this.parameters[parametersCounter]);
                parametersCounter++;
            }
            acceptsParameters = parametersEqual;
        }
        return acceptsParameters;
    }

    /**
     * {@inheritDoc}<br>
     * <br>
     * For {@link Calls}s, the construction is completed, if the {@link #returnType} and (optionally) the
     * {@link #parameters} and the {@link #parentParameterType} are available.
     * 
     * @see #finalize(ParameterType, ParameterType[], ParameterType)
     */
    @Override
    public boolean isFinal() {
        return finalized;
    }
}
