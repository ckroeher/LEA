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
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;

/**
 * This class creates the core {@link LanguageElement}s that should always be available in the {@link LanguageRegistry}.
 * Each completely constructed {@link LanguageElement} is added to the {@link LanguageRegistry}.
 * 
 * @author Christian Kroeher
 *
 */
public class CoreLanguageElementCreator extends AbstractLanguageElementCreator {
    
    /**
     * The {@link File} denoting the source plug-in of all core {@link LanguageElement}s created by instances of this
     * class. This source plug-in is always this project/plug-in.
     */
    private static final File SOURCE_PLUGIN = new File(".");
    
    /**
     * The array of Java {@link Class}es used to create corresponding {@link LanguageElement}s of the type
     * {@link ElementType#ARTIFACT_PARAMETER_TYPE} by instances of this class.
     */
    private static final Class<?>[] JAVA_CLASSES_FOR_ARTIFACT_PARAMETER_TYPES = {
        File.class
    };
    
    /**
     * The array of Java {@link Class}es used to create corresponding {@link LanguageElement}s of the type
     * {@link ElementType#FRAGMENT_PARAMETER_TYPE} by instances of this class.
     */
    private static final Class<?>[] JAVA_CLASSES_FOR_FRAGMENT_PARAMETER_TYPES = {
        boolean.class,
        char.class,
        byte.class,
        short.class,
        int.class,
        long.class,
        float.class,
        double.class,
        String.class,
    };

    /**
     * Constructs a new {@link CoreLanguageElementCreator} instance.
     */
    public CoreLanguageElementCreator() { }
    
    /**
     * Creates the core {@link LanguageElement}s based on the {@link #JAVA_CLASSES_FOR_ARTIFACT_PARAMETER_TYPES} and the
     * {@link #JAVA_CLASSES_FOR_FRAGMENT_PARAMETER_TYPES} defined in this class. As these core {@link LanguageElement}s
     * do not depend on any other (external) elements, there is no need for finalizing their creation. This method
     * manages to correct order of their creation and addition to the {@link LanguageRegistry}.
     * 
     * @throws LanguageElementException if creating a core {@link LanguageElement}  or adding it to the
     *         {@link LanguageRegistry} failed
     */
    public void createLanguageElements() throws LanguageElementException {
        // First create all parameter types for potential referencing by created calls below
        createParameterTypes(ElementType.ARTIFACT_PARAMETER_TYPE, JAVA_CLASSES_FOR_ARTIFACT_PARAMETER_TYPES);
        createParameterTypes(ElementType.FRAGMENT_PARAMETER_TYPE, JAVA_CLASSES_FOR_FRAGMENT_PARAMETER_TYPES);
        
        createCalls(JAVA_CLASSES_FOR_ARTIFACT_PARAMETER_TYPES);
        createCalls(JAVA_CLASSES_FOR_FRAGMENT_PARAMETER_TYPES);
    }
    
    /**
     * Creates a new {@link ParameterType} of the given {@link ElementType} for each of the {@link Class}es in the given
     * array and adds it to the {@link LanguageRegistry}.
     * 
     * @param elementType the {@link ElementType} of the {@link ParameterType}s to create
     * @param sourceClasses the array of source {@link Class}es from which the {@link ParameterType}s are extracted
     * @throws LanguageElementException if creating a {@link ParameterType} or adding it to the
     *         {@link LanguageRegistry} failed
     */
    private void createParameterTypes(ElementType elementType, Class<?>[] sourceClasses) 
            throws LanguageElementException {
        for (int i = 0; i < sourceClasses.length; i++) {
            createParameterType(elementType, sourceClasses[i].getSimpleName(), sourceClasses[i], SOURCE_PLUGIN);
        }
    }
    
    /**
     * Creates a new {@link Call} of the type {@link ElementType#OPERATION} for each of the <b>public</b> and
     * <b>non-static</b> {@link Method}s, which <b>do not throw any exception</b>, declared by the {@link Class}es in
     * the given array and adds it to the {@link LanguageRegistry}.
     * 
     * @param sourceClasses the array of source {@link Class}es from which the {@link Call}s are extracted
     */
    private void createCalls(Class<?>[] sourceClasses) {
        for (int i = 0; i < sourceClasses.length; i++) {
            createCalls(sourceClasses[i]);
        }
    }
    
    /**
     * Creates a new {@link Call} of the type {@link ElementType#OPERATION} for each of the <b>public</b> and
     * <b>non-static</b> {@link Method}s, which <b>do not throw any exception</b>, declared by the given {@link Class}
     * and adds it to the {@link LanguageRegistry}.
     * 
     * @param sourceClass the source {@link Class} from which the {@link Call}s are extracted
     */
    private void createCalls(Class<?> sourceClass) {
        Method[] sourceMethods = sourceClass.getDeclaredMethods();
        Method sourceMethod;
        Parameter[] methodParameters;
        String[] callParameters = null;
        for (int i = 0; i < sourceMethods.length; i++) {
            sourceMethod = sourceMethods[i];
            if (sourceMethod.getExceptionTypes().length == 0 
                    && Modifier.isPublic(sourceMethod.getModifiers()) 
                    && !Modifier.isStatic(sourceMethod.getModifiers())) {
                try {
                    Call call = new Call(ElementType.OPERATION, sourceMethod.getName(), sourceMethod, sourceClass,
                            SOURCE_PLUGIN);
                    methodParameters = sourceMethod.getParameters();
                    callParameters = new String[methodParameters.length];                
                    for (int j = 0; j < methodParameters.length; j++) {
                        callParameters[j] = createLanguageElementName(
                                methodParameters[j].getParameterizedType().getTypeName(), "");
                    }
                    finalizeCall(call, sourceMethod.getGenericReturnType().getTypeName(), callParameters,
                            sourceClass.getSimpleName());                    
                } catch (LanguageElementException e) {
                    /*
                     * There may be methods that return or use types, which are not available as parameter types in this
                     * language. In such a case, the call creation fails, which is not a hard error, but accepted.
                     * Hence, only inform about such methods, but do not abort the entire process
                     */
                    // TODO how to report?
                    System.err.println("Creating " + ElementType.OPERATION + " based on method \"" 
                            + sourceMethod.getName() + "\" in class \"" + sourceClass.getSimpleName() 
                            + "\" of plug-in \"" + SOURCE_PLUGIN.getAbsolutePath() + "\" failed: " 
                            + e.getLocalizedMessage());
                }
            }
        }
    }
    
}
