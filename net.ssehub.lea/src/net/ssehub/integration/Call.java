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
     * Constructs a new <b>premature</b> {@link Call} with the given attributes omitting the required 
     * definition of return type and parameters as well as the optional parent parameter type. These elements must be
     * added using {@link Call#setReturnType(ParameterType)}, {@link Call#setParameters(ParameterType[])}, and 
     * (optionally) {@link Call#setParentParameterType(ParameterType)} in order to {@link Call#isFinal()} returning 
     * <code>true</code> and, hence, enabling the addition of this call to the {@link LanguageRegistry}.
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
        fullyQualifiedName = null;
        returnType = null;
        parameters = null;
        parentParameterType = null;
    }
    
    /**
     * Sets the given {@link ParameterType} as the return type of this call.
     * 
     * @param returnType the {@link ParameterType} to be set as the return type of this call
     * @throws LanguageElementException if the return type of this call is already defined, the given
     *         {@link ParameterType} is <code>null</code>, or represents <code>void</code> while this call represents an
     *         extractor or an analysis call
     */
    public void setReturnType(ParameterType returnType) throws LanguageElementException {
        if (this.returnType != null) {
            throw new LanguageElementException("The return type for this call is already defined");
        }
        if (returnType == null) {
            throw new LanguageElementException("The return type for this call is null");
        }
        if (returnType.getName().equals("void") 
                && (getElementType() == ElementType.EXTRACTOR_CALL || getElementType() == ElementType.ANALYSIS_CALL)) {
            throw new LanguageElementException("Extractor and analysis calls must have a non-void return type");
        }
        this.returnType = returnType;
        // Try to construct the fully-qualified name of this element, if also the parameters are available
        constructFullyQualifiedName();
    }
    
    /**
     * Sets the given {@link ParameterType}s as the parameters of this call.
     * 
     * @param parameters the array of {@link ParameterType}s to be set as the parameters of this call
     * @throws LanguageElementException if the parameters for this call are already defined, the given array is 
     *         <code>null</code>, or one of the elements of that array is actually <code>null</code>
     */
    public void setParameters(ParameterType[] parameters) throws LanguageElementException {
        if (this.parameters != null) {
            throw new LanguageElementException("The parameters for this call are already defined");
        }
        if (parameters == null) {
            throw new LanguageElementException("Missing parameters for this call");
        }
        for (int i = 0; i < parameters.length; i++) {
            if (parameters[i] == null) {
                throw new LanguageElementException("Null element as call parameter");
            }
        }
        this.parameters = parameters;
        // Try to construct the fully-qualified name of this element, if also the return type is available
        constructFullyQualifiedName();
    }
    
    /**
     * Sets the given {@link ParameterType} as the parent parameter type of this call, if this call's element type is
     * {@link ElementType#OPERATION}. For all other call element types, calling this method results in an exception.
     * Further, setting this value declares this call to represent a member operation that can only be called via an
     * instance of that parent parameter type.
     *  
     * @param parentParameterType the {@link ParameterType} to be set as the parent parameter type of this call
     * @throws LanguageElementException if the element type of this call is not {@link ElementType#OPERATION}, the 
     *         parent parameter type of this call is already defined, or the given {@link ParameterType} is
     *         <code>null</code>
     */
    public void setParentParameterType(ParameterType parentParameterType) throws LanguageElementException {
        if (getElementType() == ElementType.EXTRACTOR_CALL || getElementType() == ElementType.ANALYSIS_CALL) {
            throw new LanguageElementException("The parent parameter type can only be set for operation calls");
        }
        if (this.parentParameterType != null) {
            throw new LanguageElementException("The parent parameter type for this call is already defined");
        }
        if (parentParameterType == null) {
            throw new LanguageElementException("The parent parameter type for this call is null");
        }
        this.parentParameterType = parentParameterType;
    }
    
    /**
     * Constructs the {@link #fullyQualifiedName} of this call, if and only if the {@link #returnType} and the 
     * {@link #parameters} are available and, hence, {@link #isFinal()} returns <code>true</code>.
     */
    private void constructFullyQualifiedName() {
        // TODO this is not finished yet!
        if (isFinal()) {
            String sourceMethodGenericString = sourceMethod.toGenericString();
            int indexOfLastWhitespace = sourceMethodGenericString.lastIndexOf(' ');
            fullyQualifiedName = sourceMethodGenericString.substring(indexOfLastWhitespace + 1)
                    .replaceAll("\\$|\\#", ".");
            System.out.println("Call: " + fullyQualifiedName);
        }
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
     * @return the array of {@link ParameterType}, which denote the elements this call accepts as parameters, or 
     *         <code>null</code>, if the construction of this call is not completed yet
     * @see #isFinal()
     */
    public ParameterType[] getParameters() {
        return parameters;
    }
    
    /**
     * Returns the parent {@link ParameterType} for which this call represents a member operation.
     * 
     * @return the parent {@link ParameterType} for which this call represents a member operation or <code>null</code>,
     *         if this call is not a member operation TODO make this special call an own class!
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
        return parentParameterType.getName().equals(parameterTypeName);
    }
    
    /**
     * {@inheritDoc}
     * 
     * In addition, two {@link Call}s are equal, if their construction is completed and they have the same:
     * <ul>
     * <li>Return type</li>
     * <li>Source {@link Method}</li>
     * <li>Number of parameters, where each parameter at a particular index in this {@link Call} is equal to the
     *     parameter at the same index in the given {@link Call}</li>
     * </ul>
     */
    @Override
    public boolean equals(LanguageElement comparable) {
        boolean isEqual = false;
        if (isFinal() && comparable instanceof Call) {
            Call comparableCall = (Call) comparable;
            if (comparableCall.isFinal()) {                
                isEqual = super.equals(comparableCall);
                if (isEqual) {
                    isEqual = hasEqualCallAttributes(comparableCall);
                }
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
        boolean isEqual = super.equalsIgnoreType(comparable);
        if (isFinal() && comparable instanceof Call) {
            Call comparableCall = (Call) comparable;
            if (comparableCall.isFinal()) {
                isEqual = super.equals(comparableCall);
                if (isEqual) {
                    isEqual = hasEqualCallAttributes(comparableCall);
                }
            }
        }
        return isEqual;
    }
    
    /**
     * Checks whether the {@link #returnType}, the {@link #parameters}, the {@link #sourceMethod}, and the 
     * {@link #parentParameterType} of this {@link Call} and the given {@link Call} are equal.
     * 
     * @param comparableCall the {@link Call} to compare to this {@link Call};
     *        should never be <code>null</code>
     * @return <code>true</code>, if the call-specific attributes of the given {@link Call} are equal to the attributes
     *         of this {@link Call}; <code>false</code> otherwise
     */
    private boolean hasEqualCallAttributes(Call comparableCall) {
        boolean hasEqualCallAttributes = true;
        if (this.returnType.equals(comparableCall.getReturnType()) 
                && this.sourceMethod.toGenericString().equals(comparableCall.getSourceMethod().toGenericString())) {
//CHECKSTYLE:OFF
            if ((this.parentParameterType == null && comparableCall.getParentParameterType() == null)
                    || (this.parentParameterType != null && comparableCall.getParentParameterType() != null
                        && this.parentParameterType.equals(comparableCall.getParentParameterType()))) {
//CHECKSTYLE:ON
                ParameterType[] comparableParameters = comparableCall.getParameters();
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
        } else {
            hasEqualCallAttributes = false;
        }
        return hasEqualCallAttributes;
    }
    
    /**
     * Compares the given parameters with the defined {@link #parameters} of this {@link Call} in exactly their order
     * in the array for equality. This is the case, if for each given parameter at a specific index an equal parameter
     * at the same index in {@link #parameters} exists. 
     *   
     * @param parameters the parameters to compare to the {@link #assignableElements} of this {@link Call}; should never
     *        be <code>null</code> nor <i>empty</i>
     * @return <code>true</code>, if for each given parameter at a specific index an equal parameter at the same index
     *         in {@link #parameters} exists; <code>false</code> otherwise
     */
    public boolean acceptsParameters(ParameterType[] parameters) {
        boolean acceptsParameters = true;
        int parametersCounter = 0;
        while (acceptsParameters && parametersCounter < parameters.length) {
            acceptsParameters = parameters[parametersCounter].equals(this.parameters[parametersCounter]);
            parametersCounter++;
        }
        return acceptsParameters;
    }

    /**
     * {@inheritDoc}<br>
     * <br>
     * For {@link Calls}s, the construction is completed, if the {@link #returnType} and the {@link #parameters} are
     * available.
     */
    @Override
    public boolean isFinal() {
        return (returnType != null && parameters != null);
    }
}
