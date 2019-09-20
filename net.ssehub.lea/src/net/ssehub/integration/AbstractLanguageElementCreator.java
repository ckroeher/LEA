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
 * This abstract class provides the common attributes and methods of the {@link ExternalLanguageElementCreator} and the
 * {@link CoreLanguageElementCreator}.
 * 
 * @author Christian Kroeher
 *
 */
public abstract class AbstractLanguageElementCreator {
    
    /**
     * The constant array of Java container type prefixes.
     */
    private static final String[] JAVA_CONTAINER_TYPE_PREFIXES = {"ArrayList<", "HashSet<", "LinkedList<", "List<",
        "Set<", "Vector<"};

    /**
     * The {@link LanguageRegistry} instance to which completely constructed {@link LanguageElement}s will be added and
     * which provides already constructed {@link ParameterType}s for referencing them during the construction of
     * {@link ChangeIdentifier}s and {@link Call}s.
     */
    protected LanguageRegistry languageRegistry;
    
    /**
     * Constructs a new {@link AbstractLanguageElementCreator} instance.
     */
    protected AbstractLanguageElementCreator() {
        languageRegistry = LanguageRegistry.INSTANCE;
    }
    
    /**
     * Creates a new {@link ParameterType} with the given attributes and adds it to the {@link LanguageRegistry}.
     * 
     * @param elementType the {@link ElementType} of the {@link ParameterType} to create
     * @param name the {@link String} defining the name of the {@link ParameterType} to create
     * @param sourceClass the source {@link Class} from which the {@link ParameterType} is extracted
     * @param sourcePlugin the {@link File} denoting the source plug-in where the source class is located 
     * @throws LanguageElementException if creating the {@link ParameterType} or adding it to the
     *         {@link LanguageRegistry} failed
     */
    protected void createParameterType(ElementType elementType, String name, Class<?> sourceClass, File sourcePlugin)
            throws LanguageElementException {
        ParameterType parameterType = new ParameterType(elementType, name,
                sourceClass, sourcePlugin);
        if (!languageRegistry.addParameterType(parameterType)) {
            throw new LanguageElementException("Adding " + elementType + " \"" + name
                    + "\" to language registry failed"); 
        }
    }
    
    /**
     * Completes the construction of the given {@link Call} by setting the {@link ParameterTypeInstance}s, which
     * represent the return type and (optionally) parameters and the parent parameter type of the given {@link Call}, if
     * the respective {@link ParameterType}s are available in the {@link LanguageRegistry}. The completely constructed
     * {@link Call} is then added to the {@link LanguageRegistry}.
     * 
     * @param call the {@link Call} for which the construction shall be be completed; should never be <code>null</code>
     * @param returnType the {@link String} representing the fully-qualified name of the return type of the given
     *        {@link Call}; should never be <code>null</code>
     * @param parameters the array of {@link String}s representing the fully-qualified names of the parameters of the
     *        given {@link Call}; may be <code>null</code> or <i>empty</i>, if the given {@link Call} does not require
     *        any parameters
     * @param parentParameterType the {@link String} representing the optional parent parameter type of the given
     *        {@link Call}; may be <code>null</code> to indicate that the given {@link Call} does not have a parent
     *        parameter type
     * @throws LanguageElementException if completing the construction of the given {@link Call} or its addition to the
     *         {@link LanguageRegistry} failed
     * @see LanguageRegistry#addCall(Call)
     */
    protected void finalizeCall(Call call, String returnType, String[] parameters, String parentParameterType) 
            throws LanguageElementException {
        try {
            // Get the return type
            ParameterTypeInstance callReturnType = createParameterTypeInstance(returnType);
            // Get the parameters (optional)
            ParameterTypeInstance[] callParameters = null;
            if (parameters != null) {            
                callParameters = new ParameterTypeInstance[parameters.length];
                for (int i = 0; i < parameters.length; i++) {
                    callParameters[i] = createParameterTypeInstance(parameters[i]);
                }
            }
            // Get the parent parameter type (optional)
            ParameterTypeInstance callParentParameterType = null;
            if (parentParameterType != null) {
                callParentParameterType = createParameterTypeInstance(parentParameterType);
            }
            // Finalize call and add it to the registry
            call.finalize(callReturnType, callParameters, callParentParameterType);
            if (!languageRegistry.addCall(call)) {
                throw new LanguageElementException("Adding " + call.getElementType() + " \"" 
                        + call.getFullyQualifiedName() + "\" to language registry failed");
            }
        } catch (LanguageElementException e) {
            throw new LanguageElementException("Completing the construction of call \"" + call.getFullyQualifiedName() 
                    + "\" failed", e);
        }
    }
    
    /**
     * Creates the {@link ParameterTypeInstance} for {@link Call} elements based in the given {@link String} denoting a
     * method return type or parameter type.
     * 
     * @param type the {@link String} denoting a method return type or parameter type for which the
     *        {@link ParameterTypeInstance} should be created
     * @return the {@link ParameterTypeInstance} or <code>null</code>, if the given {@link String} is <code>null</code>
     *         or <i>blank</i>
     * @throws LanguageElementException if the {@link LanguageRegistry} does not contain a {@link ParameterType} to
     *         create an instance from
     */
    private ParameterTypeInstance createParameterTypeInstance(String type) throws LanguageElementException {
        ParameterTypeInstance parameterTypeInstance = null;
        if (type != null && !type.isBlank()) {            
            String coreType = getCoreType(type);
            boolean isSet = !type.equals(coreType);
            ParameterType parameterType = languageRegistry.getParameterType(coreType);
            parameterTypeInstance = new ParameterTypeInstance(parameterType, isSet);
        }
        return parameterTypeInstance;
    }
    
    /**
     * Returns the core (non-array and non-container) type of the given type. 
     * 
     * @param type the type for which the core type should be returned; should never be <code>null</code> nor
     *        <i>blank</i>
     * @return the core (non-set and non-container) type of the given type or the given type, if it does not denote an
     *         array or a container type
     */
    private String getCoreType(String type) {
        String coreType = type;
        if (type.charAt(type.length() - 1) == ']') {
            coreType = type.substring(0, type.length() - 2);
        } else {
            boolean containerTypeFound = false;
            int containerTypeCounter = 0;
            while (!containerTypeFound && containerTypeCounter < JAVA_CONTAINER_TYPE_PREFIXES.length) {
                if (type.startsWith(JAVA_CONTAINER_TYPE_PREFIXES[containerTypeCounter])) {
                    containerTypeFound = true;
                    coreType = type.substring(type.indexOf('<') + 1, type.lastIndexOf('>'));
                }
                containerTypeCounter++;
            }
        }
        return coreType;
    }
    
    /**
     * Creates the (symbolic) name for a language element with the given declared type name. The name is either
     * constructed by the given declared type name, which is the fully-qualified type name including the possible
     * generic declaration and the generic parameter types, or the given symbolic type name (typically defined by the
     * annotation values).
     * 
     * @param declaredTypeName the fully-qualified, declared type name from which a name should be created; should never
     *        be <code>null</code> nor <i>blank</i>
     * @param symbolicTypeName the name that will be returned, if it is not <i>blank</i>; should never be
     *        <code>null</code>
     * @return the (symbolic) name for a language element; never <code>null</code> nor <i>blank</i>
     */
    protected String createLanguageElementName(String declaredTypeName, String symbolicTypeName) {
        String elementName = symbolicTypeName;
        if (elementName.isBlank()) {            
            StringBuilder elementNameBuilder = new StringBuilder();
            int typeNameIndex = declaredTypeName.length() - 1;
            boolean ignore = false;
            do {
                if (!ignore && declaredTypeName.charAt(typeNameIndex) == '.') {
                    ignore = true;
                } else if (ignore && declaredTypeName.charAt(typeNameIndex) == '<') {
                    ignore = false;
                }
                if (!ignore) {
                    elementNameBuilder.append(declaredTypeName.charAt(typeNameIndex));
                }
                typeNameIndex--;
            } while (typeNameIndex > -1);
            elementName = elementNameBuilder.reverse().toString();
        }
        return elementName;
    }
}
