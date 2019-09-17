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
     * The fully-qualified name of this element.
     */
    private String fullyQualifiedName;
    
    /**
     * The {@link ParameterTypeInstance} this call will return.
     */
    private ParameterTypeInstance returnType;
    
//    /**
//     * The definition of whether this call returns a set of its {@link #returnType} (<code>true</code>) or a single
//     * element of that type (<code>false</code>, which is the default).
//     */
//    private boolean returnTypeSetDefinition;
    
    /**
     * The array of {@link ParameterTypeInstance}s this call accepts as parameters. May be <code>null</code>, if this
     * call does not require any parameters.
     */
    private ParameterTypeInstance[] parameters;
    
//    /**
//     * The definitions of whether a parameter of this call is a set of the respective {@link ParameterType}
//     * (<code>true</code>) or a single element of that type (<code>false</code>, which is the default). For each
//     * {@link ParameterType} in the array of {@link #parameters} of this call the <code>boolean</code> value at the
//     * same index in this array provides that definition. May be <code>null</code>, if this call does not require any
//     * parameters.
//     */
//    private boolean[] parametersSetDefinitions;
    
    /**
     * The {@link ParameterTypeInstance} for which this call represents a member operation. May be <code>null</code>,
     * if this call does not represent a member operation.
     */
    private ParameterTypeInstance parentParameterType;
    
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
//        returnTypeSetDefinition = false;
        parameters = null;
//        parametersSetDefinitions = null;
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
     * Completes the construction of this {@link Call} instance by setting the first given {@link ParameterTypeInstance}
     * as the return type and (optionally) the given array of {@link ParameterTypeInstance}s as the parameters and the
     * second given {@link ParameterTypeInstance} as the parent parameter type of this instance.
     * 
     * @param returnType the {@link ParameterTypeInstance} to be set as the return type of this call
     * @param parameters the array of {@link ParameterTypeInstance}s to be set as the parameters of this call; may be 
     *        <code>null</code> for calls, which do not require parameters
     * @param parentParameterType the {@link ParameterTypeInstance} to be set as the parent parameter type of this call;
     *        may be <code>null</code> for calls, which are not a member operation
     * @throws LanguageElementException if
     *         <ul>
     *         <li>the construction of this instance is already completed (this method was called without exception once
     *             before)</li>
     *         <li>the given {@link ParameterTypeInstance} for the <b>return type</b> is <code>null</code> or represents
     *             <code>void</code>, while this call represents an extractor or an analysis call</li>
     *         <li>the given array of {@link ParameterTypeInstance}s for the <b>parameters</b> contains elements, which
     *             are <code>null</code></li>
     *         <li>the given {@link ParameterTypeInstance} for the <b>parent parameter type</b> is not 
     *             <code>null</code>, while this call represents an extractor or an analysis call</li>
     *         </ul>
     */
    public void finalize(ParameterTypeInstance returnType, ParameterTypeInstance[] parameters,
            ParameterTypeInstance parentParameterType) throws LanguageElementException {
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
     * Checks whether the construction of this element is completed. For {@link Calls}s, the construction is completed,
     * if the {@link #returnType} and (optionally) the {@link #parameters} and the {@link #parentParameterType} are
     * available.
     * 
     * @return <code>true</code>, if the construction of this element is completed; <code>false</code> otherwise
     * @see #finalize(ParameterType, ParameterType[], ParameterType)
     */
    public boolean isFinal() {
        return finalized;
    }
    
    /**
     * Sets the given {@link ParameterTypeInstance} as the return type of this call.
     * 
     * @param returnType the {@link ParameterTypeInstance} to be set as the return type of this call
     * @throws LanguageElementException if the given {@link ParameterTypeInstance} is <code>null</code>, or represents
     *         <code>void</code>, while this call represents an extractor or an analysis call
     */
    private void setReturnType(ParameterTypeInstance returnType) throws LanguageElementException {
        if (returnType == null) {
            throw new LanguageElementException("The return type for this call is null");
        }
        if (returnType.getParameterType().getName().equals("void") 
                && (getElementType() == ElementType.EXTRACTOR_CALL || getElementType() == ElementType.ANALYSIS_CALL)) {
            throw new LanguageElementException("Extractor and analysis calls must have a non-void return type");
        }
        this.returnType = returnType;
    }
    
    /**
     * Sets the given array of {@link ParameterTypeInstance}s as the parameters of this call.
     * 
     * @param parameters the array of {@link ParameterTypeInstance}s to be set as the parameters of this call; may be 
     *        <code>null</code> for calls, which do not require parameters
     * @throws LanguageElementException if the given array of {@link ParameterTypeInstance}s contains elements, which
     *         are <code>null</code>
     */
    private void setParameters(ParameterTypeInstance[] parameters) throws LanguageElementException {
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
     * Sets the given {@link ParameterTypeInstance} as the parent parameter type of this call, if this call's element
     * type is {@link ElementType#OPERATION}. For all other call element types, calling this method results in an
     * exception. Further, setting this value declares this call to represent a member operation that can only be called
     * via an that {@link ParameterTypeInstance}.
     *  
     * @param parentParameterType the {@link ParameterTypeInstance} to be set as the parent parameter type of this call;
     *        may be <code>null</code> for calls, which are not a member operation
     * @throws LanguageElementException if the given {@link ParameterTypeInstance} is not <code>null</code>, while the
     *         element type of this call is not {@link ElementType#OPERATION}
     */
    private void setParentParameterType(ParameterTypeInstance parentParameterType) throws LanguageElementException {
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
     * Returns {@link ParameterTypeInstance} this call will return.
     * 
     * @return the {@link ParameterTypeInstance} this call will return or <code>null</code>, if the construction of this
     *         call is not completed yet
     * @see #isFinal()
     */
    public ParameterTypeInstance getReturnType() {
        return returnType;
    }
    
    /**
     * Returns the array of {@link ParameterTypeInstance}s, which denote the elements this call accepts as parameters.
     * 
     * @return the array of {@link ParameterTypeInstance}s, which denote the elements this call accepts as parameters,
     *         or <code>null</code>, if the construction of this call is not completed yet or this call does not accept
     *         any parameters
     * @see #isFinal()
     */
    public ParameterTypeInstance[] getParameters() {
        return parameters;
    }
    
    /**
     * Returns the parent {@link ParameterTypeInstance} for which this call represents a member operation.
     * 
     * @return the parent {@link ParameterTypeInstance} for which this call represents a member operation or
     *         <code>null</code>, if the construction of this call is not completed yet or this call is not a member
     *         operation
     */
    public ParameterTypeInstance getParentParameterType() {
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
     * Checks whether this call is a member operation of the given {@link ParameterTypeInstance} (<code>true</code>) or
     * not (<code>false</code>).
     * 
     * @param parameterTypeInstance the {@link ParameterTypeInstance} to check whether this call is a member operation
     *        of it
     * @return <code>true</code>, if this call is a member operation of the given {@link ParameterTypeInstance};
     *         <code>false</code> otherwise
     */
    public boolean isMemberOperationOf(ParameterTypeInstance parameterTypeInstance) {
        return isMemberOperation() 
                && parentParameterType.equals(parameterTypeInstance);
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
     * Checks whether {@link Call}-specific attributes of this {@link Call} and the given {@link Call} are equal. These
     * attributes are:
     * <ul>
     * <li>The {@link #sourceMethod}</li>
     * <li>The {@link #returnType} and its {@link #returnTypeSetDefinition}</li>
     * <li>The {@link #parentParameterType}</li>
     * <li>The {@link #parameters} and their {@link #parametersSetDefinitions}</li>
     * </ul>
     * 
     * @param comparableCall the {@link Call} to compare to this {@link Call}; should never be <code>null</code>
     * @return <code>true</code>, if the call-specific attributes of the given {@link Call} are equal to the attributes
     *         of this {@link Call}; <code>false</code> otherwise
     */
    private boolean hasEqualCallAttributes(Call comparableCall) {
        return sourceMethod.toGenericString().equals(comparableCall.getSourceMethod().toGenericString())
                && hasEqualReturnType(comparableCall)
                && hasEqualParentParameterType(comparableCall)
                && hasEqualParameters(comparableCall);
    }
    
    /**
     * Checks whether the return types of this {@link Call} and the given {@link Call} are equal.
     * 
     * @param comparableCall the {@link Call} to compare to this {@link Call}; should never be <code>null</code>
     * @return <code>true</code>, if the return type of the given {@link Call} is equal to the return type of this
     *         {@link Call}; <code>false</code> otherwise
     */
    private boolean hasEqualReturnType(Call comparableCall) {
        return returnType.equals(comparableCall.getReturnType());
    }
    
    /**
     * Checks whether the parent parameter types of this {@link Call} and the given {@link Call} are equal.
     * 
     * @param comparableCall the {@link Call} to compare to this {@link Call}; should never be <code>null</code>
     * @return <code>true</code>, if the parent parameter type of the given {@link Call} is equal to the parent
     *         parameter type of this {@link Call}; <code>false</code> otherwise
     */
    private boolean hasEqualParentParameterType(Call comparableCall) {
        boolean hasEqualParentParameterType = false;
        if (parentParameterType == null && comparableCall.getParentParameterType() == null) {
            hasEqualParentParameterType = true;
        } else if (parentParameterType != null && comparableCall.getParentParameterType() != null
                && parentParameterType.equals(comparableCall.getParentParameterType())) {
            hasEqualParentParameterType = true;
        }
        return hasEqualParentParameterType;
    }
    
    /**
     * Checks whether the parameters of this {@link Call} and the given {@link Call} are equal.
     * 
     * @param comparableCall the {@link Call} to compare to this {@link Call}; should never be <code>null</code>
     * @return <code>true</code>, if the parameters of the given {@link Call} are equal to the parameters of this
     *         {@link Call}; <code>false</code> otherwise
     */
    private boolean hasEqualParameters(Call comparableCall) {
        boolean hasEqualParameters = false;
        ParameterTypeInstance[] comparableParameters = comparableCall.getParameters();
        if (parameters == null && comparableCall.getParameters() == null) {
            hasEqualParameters = true;
        } else if (parameters != null && comparableParameters != null 
                && parameters.length == comparableParameters.length) {
            boolean parametersEqual = true;
            int parametersCounter = 0;
            while (parametersEqual && parametersCounter < parameters.length) {
                parametersEqual = parameters[parametersCounter].equals(comparableParameters[parametersCounter]);
                parametersCounter++;
            }
            hasEqualParameters = parametersEqual;
        }
        return hasEqualParameters;
    }
    
    /**
     * Compares the given array of {@link ParameterTypeInstance}s with the {@link #parameters} of this {@link Call} in
     * exactly their order in the arrays for equality. This is the case, if for each given parameter at a specific index
     * an equal parameter at the same index in {@link #parameters} exists. 
     *   
     * @param parameters the array of {@link ParameterTypeInstance} to compare to the {@link #parameters} of this
     *        {@link Call}
     * @return <code>true</code>, if either the given parameters and the {@link #parameters} of this {@link Call} are
     *         <code>null</code>, or for each given parameter at a specific index an equal parameter at the same index
     *         in {@link #parameters} exists; <code>false</code> otherwise
     */
    public boolean acceptsParameters(ParameterTypeInstance[] parameters) {
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

}
