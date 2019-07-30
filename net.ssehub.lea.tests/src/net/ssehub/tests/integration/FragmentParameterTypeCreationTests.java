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
package net.ssehub.tests.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.Test;

import net.ssehub.integration.ElementType;
import net.ssehub.integration.ExternalElementException;
import net.ssehub.integration.LanguageElement;
import net.ssehub.integration.LanguageElementCreator;
import net.ssehub.integration.ParameterType;
import net.ssehub.integration.annotations.FragmentParameterType;

/**
 * This class contains unit tests for the correct creation of {@link ParameterType}s of the type 
 * {@link ElementType#FRAGMENT_PARAMETER_TYPE} by the {@link LanguageElementCreator}.
 * 
 * @author Christian Kroeher
 *
 */
public class FragmentParameterTypeCreationTests extends AbstractTest {

    /**
     * This class represents a simple (not generic) fragment parameter type. It is annotated with the 
     * {@link FragmentParameterType} annotation without any further parameters. Hence, the actual class name should be
     * used for element creation.
     * 
     * @author Christian Kroeher
     *
     */
    @FragmentParameterType
    private class SimpleFragmentParameterType { }
    
    /**
     * This class represents a simple (not generic) fragment parameter type. It is annotated with the 
     * {@link FragmentParameterType} annotation including the definition of the symbolic name <i>Block</i>, which
     * should be used for element creation instead of the actual class name.
     * 
     * @author Christian Kroeher
     *
     */
    @FragmentParameterType(name = "Block")
    private class SimpleFragmentParameterTypeWithSymbolicName { }
    
    /**
     * This class represents a simple (not generic) fragment parameter type. It is annotated with the 
     * {@link FragmentParameterType} annotation including the definition of the symbolic parameter name <i>None</i>,
     * which should be ignored as this artifact is not a generic one.
     * 
     * @author Christian Kroeher
     *
     */
    @FragmentParameterType(parameterName = "None")
    private class SimpleFragmentParameterTypeWithSymbolicParameterName { }
    
    /**
     * This class represents a simple (not generic) fragment parameter type. It is annotated with the 
     * {@link FragmentParameterType} annotation including the definition of the the symbolic name <i>Block</i> and the 
     * symbolic parameter name <i>Code</i>. While the former should be used for element creation instead of the actual
     * class name, the latter should be ignored as this artifact is not a generic one.
     * 
     * @author Christian Kroeher
     *
     */
    @FragmentParameterType(name = "Block", parameterName = "Code")
    private class SimpleFragmentParameterTypeWithSymbolicNameAndParameterName { }

    /**
     * Tests the correct creation of a simple {@link FragmentParameterType} based on the class 
     * {@link SimpleFragmentParameterType}.
     */
    @Test
    public void testSimpleFragmentParameterTypeCreation() {
        try {
            List<LanguageElement> createdElements = 
                    elementCreator.createLanguageElements(SimpleFragmentParameterType.class, sourcePlugin);
            assertEquals(1, createdElements.size(), "Wrong number of created language elements");
            
            LanguageElement createdElement = createdElements.get(0);
            assertEquals(ParameterType.class, createdElement.getClass(), "Wrong language element type");
            assertEquals(ElementType.FRAGMENT_PARAMETER_TYPE, createdElement.getElementType(),
                    "Wrong language element element type");
            assertEquals(SimpleFragmentParameterType.class.getSimpleName(), createdElement.getName(),
                    "Wrong language element name");
            assertEquals(SimpleFragmentParameterType.class, createdElement.getSourceClass(),
                    "Wrong language element source class");
            assertEquals(sourcePlugin, createdElement.getSourcePlugin(), "Wrong language element source plug-in");
            
        } catch (ExternalElementException e) {
            assertNull(e, "This test should not throw any exception");
        }
    }
    
    /**
     * Tests the correct creation of a simple {@link FragmentParameterType} based on the class 
     * {@link SimpleFragmentParameterTypeWithSymbolicName}.
     */
    @Test
    public void testSimpleFragmentParameterTypeWithSymbolicNameCreation() {
        try {
            List<LanguageElement> createdElements = 
                    elementCreator.createLanguageElements(SimpleFragmentParameterTypeWithSymbolicName.class,
                            sourcePlugin);
            assertEquals(1, createdElements.size(), "Wrong number of created language elements");
            
            LanguageElement createdElement = createdElements.get(0);
            assertEquals(ParameterType.class, createdElement.getClass(), "Wrong language element type");
            assertEquals(ElementType.FRAGMENT_PARAMETER_TYPE, createdElement.getElementType(),
                    "Wrong language element element type");
            assertEquals("Block", createdElement.getName(), "Wrong language element name");
            assertEquals(SimpleFragmentParameterTypeWithSymbolicName.class, createdElement.getSourceClass(),
                    "Wrong language element source class");
            assertEquals(sourcePlugin, createdElement.getSourcePlugin(), "Wrong language element source plug-in");
            
        } catch (ExternalElementException e) {
            assertNull(e, "This test should not throw any exception");
        }
    }
    
    /**
     * Tests the correct creation of a simple {@link FragmentParameterType} based on the class 
     * {@link SimpleFragmentParameterTypeWithSymbolicParameterName}.
     */
    @Test
    public void testSimpleFragmentParameterTypeWithSymbolicParameterNameCreation() {
        try {
            List<LanguageElement> createdElements = 
                    elementCreator.createLanguageElements(SimpleFragmentParameterTypeWithSymbolicParameterName.class,
                            sourcePlugin);
            assertEquals(1, createdElements.size(), "Wrong number of created language elements");
            
            LanguageElement createdElement = createdElements.get(0);
            assertEquals(ParameterType.class, createdElement.getClass(), "Wrong language element type");
            assertEquals(ElementType.FRAGMENT_PARAMETER_TYPE, createdElement.getElementType(),
                    "Wrong language element element type");
            assertEquals(SimpleFragmentParameterTypeWithSymbolicParameterName.class.getSimpleName(),
                    createdElement.getName(), "Wrong language element name");
            assertEquals(SimpleFragmentParameterTypeWithSymbolicParameterName.class, createdElement.getSourceClass(),
                    "Wrong language element source class");
            assertEquals(sourcePlugin, createdElement.getSourcePlugin(), "Wrong language element source plug-in");
            
        } catch (ExternalElementException e) {
            assertNull(e, "This test should not throw any exception");
        }
    }
    
    /**
     * Tests the correct creation of a simple {@link FragmentParameterType} based on the class 
     * {@link SimpleFragmentParameterTypeWithSymbolicNameAndParameterName}.
     */
    @Test
    public void testSimpleFragmentParameterTypeWithSymbolicNameAndParameterNameCreation() {
        try {
            List<LanguageElement> createdElements = elementCreator.
                    createLanguageElements(SimpleFragmentParameterTypeWithSymbolicNameAndParameterName.class,
                            sourcePlugin);
            assertEquals(1, createdElements.size(), "Wrong number of created language elements");
            
            LanguageElement createdElement = createdElements.get(0);
            assertEquals(ParameterType.class, createdElement.getClass(), "Wrong language element type");
            assertEquals(ElementType.FRAGMENT_PARAMETER_TYPE, createdElement.getElementType(),
                    "Wrong language element element type");
            assertEquals("Block", createdElement.getName(), "Wrong language element name");
            assertEquals(SimpleFragmentParameterTypeWithSymbolicNameAndParameterName.class,
                    createdElement.getSourceClass(), "Wrong language element source class");
            assertEquals(sourcePlugin, createdElement.getSourcePlugin(), "Wrong language element source plug-in");
            
        } catch (ExternalElementException e) {
            assertNull(e, "This test should not throw any exception");
        }
    }
    
    /**
     * This class represents a generic fragment parameter type with {@link File} as parameter type. It is annotated with
     * the {@link FragmentParameterType} annotation without any further parameters. Hence, the actual class name and
     * parameter type should be used for element creation.
     * 
     * @param <File> the parameter type of this generic class for testing
     * 
     * @author Christian Kroeher
     *
     */
    @FragmentParameterType
    private class GenericFragmentParameterType<File> { }
    
    /**
     * This class represents a generic fragment parameter type with {@link File} as parameter type. It is annotated with
     * the {@link FragmentParameterType} annotation including the definition of the symbolic name <i>Block</i>, which
     * should be used for element creation instead of the actual class name.
     * 
     * @param <File> the parameter type of this generic class for testing
     * 
     * @author Christian Kroeher
     *
     */
    @FragmentParameterType(name = "Block")
    private class GenericFragmentParameterTypeWithSymbolicName<File> { }
    
    /**
     * This class represents a generic fragment parameter type with {@link File} as parameter type. It is annotated with
     * the {@link FragmentParameterType} annotation including the definition of the symbolic parameter name
     * <i>Code</i>, which should be used for element creation instead of the actual parameter type.
     * 
     * @param <File> the parameter type of this generic class for testing
     * 
     * @author Christian Kroeher
     *
     */
    @FragmentParameterType(parameterName = "Code")
    private class GenericFragmentParameterTypeWithSymbolicParameterName<File> { }
    
    /**
     * This class represents a generic fragment parameter type with {@link File} as parameter type. It is annotated with
     * the {@link FragmentParameterType} annotation including the definition of the the symbolic name <i>Block</i> and
     * the symbolic parameter name <i>Code</i>. Hence, both symbolic names should be used for element creation instead
     * of the actual class and parameter type names.
     * 
     * @param <File> the parameter type of this generic class for testing
     * 
     * @author Christian Kroeher
     *
     */
    @FragmentParameterType(name = "Block", parameterName = "Code")
    private class GenericFragmentParameterTypeWithSymbolicNameAndParameterName<File> { }
    
    /**
     * Tests the correct creation of a generic {@link FragmentParameterType} based on the class 
     * {@link GenericFragmentParameterType}.
     */
    @Test
    public void testGenericFragmentParameterTypeCreation() {
        try {
            List<LanguageElement> createdElements = 
                    elementCreator.createLanguageElements(GenericFragmentParameterType.class, sourcePlugin);
            assertEquals(1, createdElements.size(), "Wrong number of created language elements");
            
            LanguageElement createdElement = createdElements.get(0);
            assertEquals(ParameterType.class, createdElement.getClass(), "Wrong language element type");
            assertEquals(ElementType.FRAGMENT_PARAMETER_TYPE, createdElement.getElementType(),
                    "Wrong language element element type");
            assertEquals("GenericFragmentParameterType<File>", createdElement.getName(),
                    "Wrong language element name");
            assertEquals(GenericFragmentParameterType.class, createdElement.getSourceClass(),
                    "Wrong language element source class");
            assertEquals(sourcePlugin, createdElement.getSourcePlugin(), "Wrong language element source plug-in");
            
        } catch (ExternalElementException e) {
            assertNull(e, "This test should not throw any exception");
        }
    }
    
    /**
     * Tests the correct creation of a generic {@link FragmentParameterType} based on the class 
     * {@link GenericFragmentParameterTypeWithSymbolicName}.
     */
    @Test
    public void testGenericFragmentParameterTypeWithSymbolicNameCreation() {
        try {
            List<LanguageElement> createdElements = 
                    elementCreator.createLanguageElements(GenericFragmentParameterTypeWithSymbolicName.class,
                            sourcePlugin);
            assertEquals(1, createdElements.size(), "Wrong number of created language elements");
            
            LanguageElement createdElement = createdElements.get(0);
            assertEquals(ParameterType.class, createdElement.getClass(), "Wrong language element type");
            assertEquals(ElementType.FRAGMENT_PARAMETER_TYPE, createdElement.getElementType(),
                    "Wrong language element element type");
            assertEquals("Block<File>", createdElement.getName(), "Wrong language element name");
            assertEquals(GenericFragmentParameterTypeWithSymbolicName.class, createdElement.getSourceClass(),
                    "Wrong language element source class");
            assertEquals(sourcePlugin, createdElement.getSourcePlugin(), "Wrong language element source plug-in");
            
        } catch (ExternalElementException e) {
            assertNull(e, "This test should not throw any exception");
        }
    }
    
    /**
     * Tests the correct creation of a simple {@link FragmentParameterType} based on the class 
     * {@link GenericFragmentParameterTypeWithSymbolicParameterName}.
     */
    @Test
    public void testGenericFragmentParameterTypeWithSymbolicParameterNameCreation() {
        try {
            List<LanguageElement> createdElements = 
                    elementCreator.createLanguageElements(GenericFragmentParameterTypeWithSymbolicParameterName.class,
                            sourcePlugin);
            assertEquals(1, createdElements.size(), "Wrong number of created language elements");
            
            LanguageElement createdElement = createdElements.get(0);
            assertEquals(ParameterType.class, createdElement.getClass(), "Wrong language element type");
            assertEquals(ElementType.FRAGMENT_PARAMETER_TYPE, createdElement.getElementType(),
                    "Wrong language element element type");            
            assertEquals("GenericFragmentParameterTypeWithSymbolicParameterName<Code>", createdElement.getName(),
                    "Wrong language element name");
            assertEquals(GenericFragmentParameterTypeWithSymbolicParameterName.class, createdElement.getSourceClass(),
                    "Wrong language element source class");
            assertEquals(sourcePlugin, createdElement.getSourcePlugin(), "Wrong language element source plug-in");
            
        } catch (ExternalElementException e) {
            assertNull(e, "This test should not throw any exception");
        }
    }
    
    /**
     * Tests the correct creation of a simple {@link FragmentParameterType} based on the class 
     * {@link GenericFragmentParameterTypeWithSymbolicNameAndParameterName}.
     */
    @Test
    public void testGenericFragmentParameterTypeWithSymbolicNameAndParameterNameCreation() {
        try {
            List<LanguageElement> createdElements = elementCreator.
                    createLanguageElements(GenericFragmentParameterTypeWithSymbolicNameAndParameterName.class,
                            sourcePlugin);
            assertEquals(1, createdElements.size(), "Wrong number of created language elements");
            
            LanguageElement createdElement = createdElements.get(0);
            assertEquals(ParameterType.class, createdElement.getClass(), "Wrong language element type");
            assertEquals(ElementType.FRAGMENT_PARAMETER_TYPE, createdElement.getElementType(),
                    "Wrong language element element type");
            assertEquals("Block<Code>", createdElement.getName(), "Wrong language element name");
            assertEquals(GenericFragmentParameterTypeWithSymbolicNameAndParameterName.class,
                    createdElement.getSourceClass(), "Wrong language element source class");
            assertEquals(sourcePlugin, createdElement.getSourcePlugin(), "Wrong language element source plug-in");
            
        } catch (ExternalElementException e) {
            assertNull(e, "This test should not throw any exception");
        }
    }
    
}
