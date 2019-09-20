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
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class creates the core {@link LanguageElement}s that should always be available in the {@link LanguageRegistry}.
 * Each completely constructed {@link LanguageElement} is added to the {@link LanguageRegistry}.
 * 
 * @author Christian Kroeher
 *
 */
public class CoreLanguageElementCreator extends AbstractLanguageElementCreator {
    
    /**
     * This class provides a set of methods for using them as {@link Call}s of the type {@link ElementType#OPERATION}.
     *  
     * @author Christian Kroeher
     *
     */
    private class CoreOperations {
        
        /*
         * TODO how and from where do we get the rootDir of an SPL where the analysis is applied?
         * something like the actual execution engine?
         */
        private File rootDir;
        
        /**
         * Returns the {@link File} denoted by the given {@link String} that represents path relative to the
         * {@link #rootDir}.
         * 
         * @param relativePath the {@link String} representing the relative path of the {@link File} to return
         * @return the {@link File} denoted by the given path relative to the {@link #rootDir} or <code>null</code>, if
         *         the given relative path is <code>null</code> or <code>blank</code>
         */
        @SuppressWarnings("unused")
        public File file(String relativePath) {
            File file = null;
            if (relativePath != null && !relativePath.isBlank()) {
                file = new File(rootDir, relativePath);
            }
            return file;
        }
        
        /**
         * Returns the {@link List} of all {@link File}s, which have an absolute file path that matches the given 
         * {@link String} regular expression. It searches recursively in all directories starting from the
         * {@link #rootDir}.
         * 
         * @param regex the {@link String} regular expression for identifying the desired {@link File}s
         * @return the {@link List} of all {@link File}s, which have an absolute file path that matches the given
         *         regular expression or <code>null</code>, if the given regular expression is <code>null</code> or
         *         <code>blank</code>
         */
        @SuppressWarnings("unused")
        public List<File> files(String regex) {
            List<File> foundFiles = null;
            if (regex != null && !regex.isBlank()) {
                foundFiles = files(regex, rootDir);
            }
            return foundFiles;
        }
        
        /**
         * Searches recursively in all directories starting from the given search directory for all {@link File}s where
         * the absolute path matches the given {@link String} regular expression and returns them.
         * 
         * @param regex the {@link String} regular expression for identifying the desired {@link File}s; should
         *        never be <code>null</code> nor <i>blank</i>
         * @param searchDir the {@link File} denoting the directory to search in for the desired {@link File}s; should
         *        never be <code>null</code> and should always denote a directory 
         * @return the {@link List} of {@link File}s where the absolute path matches the given regular expression; never
         *         <code>null</code>, but may be <i>empty</i>
         */
        private List<File> files(String regex, File searchDir) {
            List<File> foundFiles = new ArrayList<File>();
            File[] containedFiles = searchDir.listFiles();
            File containedFile;
            Pattern fileAbsolutePathPattern = Pattern.compile(regex);
            Matcher fileAbsolutePathMatcher;
            for (int i = 0; i < containedFiles.length; i++) {
                containedFile = containedFiles[i];
                if (containedFile.isDirectory()) {
                    foundFiles.addAll(files(regex, containedFile));
                } else {
                    fileAbsolutePathMatcher = fileAbsolutePathPattern.matcher(containedFile.getAbsolutePath());
                    if (fileAbsolutePathMatcher.matches()) {
                        foundFiles.add(containedFile);
                    }
                }            
            }
            return foundFiles;
        }
        
    }
    
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
        
        createCalls(JAVA_CLASSES_FOR_ARTIFACT_PARAMETER_TYPES, true);
        createCalls(JAVA_CLASSES_FOR_FRAGMENT_PARAMETER_TYPES, true);
        createCalls(CoreOperations.class, false);
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
     * @param sourceClassAsParentParameterType <code>true</code>, if the given source classes represent the parent
     *        parameter type of the calls created based on their methods such that these calls become member operations;
     *        <code>false</code> otherwise
     */
    private void createCalls(Class<?>[] sourceClasses, boolean sourceClassAsParentParameterType) {
        for (int i = 0; i < sourceClasses.length; i++) {
            createCalls(sourceClasses[i], sourceClassAsParentParameterType);
        }
    }
    
    /**
     * Creates a new {@link Call} of the type {@link ElementType#OPERATION} for each of the <b>public</b> and
     * <b>non-static</b> {@link Method}s, which <b>do not throw any exception</b>, declared by the given {@link Class}
     * and adds it to the {@link LanguageRegistry}.
     * 
     * @param sourceClass the source {@link Class} from which the {@link Call}s are extracted
     * @param sourceClassAsParentParameterType <code>true</code>, if the given source class represents the parent
     *        parameter type of the created call such that the call becomes a member operation; <code>false</code>
     *        otherwise
     */
    private void createCalls(Class<?> sourceClass, boolean sourceClassAsParentParameterType) {
        Method[] sourceMethods = sourceClass.getDeclaredMethods();
        Method sourceMethod;
        for (int i = 0; i < sourceMethods.length; i++) {
            sourceMethod = sourceMethods[i];
            if (sourceMethod.getExceptionTypes().length == 0 
                    && Modifier.isPublic(sourceMethod.getModifiers()) 
                    && !Modifier.isStatic(sourceMethod.getModifiers())) {
                createCall(sourceMethod, sourceClass, sourceClassAsParentParameterType);
            }
        }
    }
    
    /**
     * Creates a new {@link Call} of the type {@link ElementType#OPERATION} for the given {@link Method} declared by the
     * given {@link Class} and adds it to the {@link LanguageRegistry}.
     * 
     * @param sourceMethod the {@link Method} for which a corresponding {@link Call} should be created
     * @param sourceClass the source {@link Class} of the given source method
     * @param sourceClassAsParentParameterType <code>true</code>, if the given source class represents the parent
     *        parameter type of the created call such that the call becomes a member operation; <code>false</code>
     *        otherwise
     */
    private void createCall(Method sourceMethod, Class<?> sourceClass, boolean sourceClassAsParentParameterType) {
        try {
            Call call = new Call(ElementType.OPERATION, sourceMethod.getName(), sourceMethod, sourceClass,
                    SOURCE_PLUGIN);
            String returnType = createLanguageElementName(sourceMethod.getGenericReturnType().getTypeName(), "");
            Parameter[] methodParameters = sourceMethod.getParameters();
            String[] callParameters = new String[methodParameters.length];                
            for (int j = 0; j < methodParameters.length; j++) {
                callParameters[j] = createLanguageElementName(
                        methodParameters[j].getParameterizedType().getTypeName(), "");
            }
            if (sourceClassAsParentParameterType) {                
                finalizeCall(call, returnType, callParameters, sourceClass.getSimpleName());                    
            } else {
                finalizeCall(call, returnType, callParameters, null);
            }
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
