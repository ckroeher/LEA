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
import net.ssehub.integration.annotations.ResultParameterType;

/**
 * This class contains unit tests for the correct creation of {@link ParameterType}s of the type 
 * {@link ElementType#RESULT_PARAMETER_TYPE} by the {@link LanguageElementCreator}.
 * 
 * @author Christian Kroeher
 *
 */
public class ResultParameterTypeCreationTests extends AbstractTest {
    
    /**
     * This class represents a simple (not generic) result parameter type. It is annotated with the 
     * {@link ResultParameterType} annotation without any further parameters. Hence, the actual class name should be
     * used for element creation.
     * 
     * @author Christian Kroeher
     *
     */
    @ResultParameterType
    private class SimpleResultParameterType { }
    
    /**
     * This class represents a simple (not generic) result parameter type. It is annotated with the 
     * {@link ResultParameterType} annotation including the definition of the symbolic name <i>DeadBlock</i>, which
     * should be used for element creation instead of the actual class name.
     * 
     * @author Christian Kroeher
     *
     */
    @ResultParameterType(name = "DeadBlock")
    private class SimpleResultParameterTypeWithSymbolicName { }
    
    /**
     * This class represents a simple (not generic) result parameter type. It is annotated with the 
     * {@link ResultParameterType} annotation including the definition of the symbolic parameter name <i>None</i>,
     * which should be ignored as this artifact is not a generic one.
     * 
     * @author Christian Kroeher
     *
     */
    @ResultParameterType(parameterName = "None")
    private class SimpleResultParameterTypeWithSymbolicParameterName { }
    
    /**
     * This class represents a simple (not generic) result parameter type. It is annotated with the 
     * {@link ResultParameterType} annotation including the definition of the the symbolic name <i>DeadBlock</i> and the
     * symbolic parameter name <i>Code</i>. While the former should be used for element creation instead of the actual
     * class name, the latter should be ignored as this artifact is not a generic one.
     * 
     * @author Christian Kroeher
     *
     */
    @ResultParameterType(name = "DeadBlock", parameterName = "Code")
    private class SimpleResultParameterTypeWithSymbolicNameAndParameterName { }

    /**
     * Tests the correct creation of a simple {@link ResultParameterType} based on the class 
     * {@link SimpleResultParameterType}.
     */
    @Test
    public void testSimpleResultParameterTypeCreation() {
        try {
            List<LanguageElement> createdElements = 
                    elementCreator.createLanguageElements(SimpleResultParameterType.class, sourcePlugin);
            assertEquals(1, createdElements.size(), "Wrong number of created language elements");
            
            LanguageElement createdElement = createdElements.get(0);
            assertEquals(ParameterType.class, createdElement.getClass(), "Wrong language element type");
            assertEquals(ElementType.RESULT_PARAMETER_TYPE, createdElement.getElementType(),
                    "Wrong language element element type");
            assertEquals(SimpleResultParameterType.class.getSimpleName(), createdElement.getName(),
                    "Wrong language element name");
            assertEquals(SimpleResultParameterType.class, createdElement.getSourceClass(),
                    "Wrong language element source class");
            assertEquals(sourcePlugin, createdElement.getSourcePlugin(), "Wrong language element source plug-in");
            
        } catch (ExternalElementException e) {
            assertNull(e, "This test should not throw any exception");
        }
    }
    
    /**
     * Tests the correct creation of a simple {@link ResultParameterType} based on the class 
     * {@link SimpleResultParameterTypeWithSymbolicName}.
     */
    @Test
    public void testSimpleResultParameterTypeWithSymbolicNameCreation() {
        try {
            List<LanguageElement> createdElements = 
                    elementCreator.createLanguageElements(SimpleResultParameterTypeWithSymbolicName.class,
                            sourcePlugin);
            assertEquals(1, createdElements.size(), "Wrong number of created language elements");
            
            LanguageElement createdElement = createdElements.get(0);
            assertEquals(ParameterType.class, createdElement.getClass(), "Wrong language element type");
            assertEquals(ElementType.RESULT_PARAMETER_TYPE, createdElement.getElementType(),
                    "Wrong language element element type");
            assertEquals("DeadBlock", createdElement.getName(), "Wrong language element name");
            assertEquals(SimpleResultParameterTypeWithSymbolicName.class, createdElement.getSourceClass(),
                    "Wrong language element source class");
            assertEquals(sourcePlugin, createdElement.getSourcePlugin(), "Wrong language element source plug-in");
            
        } catch (ExternalElementException e) {
            assertNull(e, "This test should not throw any exception");
        }
    }
    
    /**
     * Tests the correct creation of a simple {@link ResultParameterType} based on the class 
     * {@link SimpleResultParameterTypeWithSymbolicParameterName}.
     */
    @Test
    public void testSimpleResultParameterTypeWithSymbolicParameterNameCreation() {
        try {
            List<LanguageElement> createdElements = 
                    elementCreator.createLanguageElements(SimpleResultParameterTypeWithSymbolicParameterName.class,
                            sourcePlugin);
            assertEquals(1, createdElements.size(), "Wrong number of created language elements");
            
            LanguageElement createdElement = createdElements.get(0);
            assertEquals(ParameterType.class, createdElement.getClass(), "Wrong language element type");
            assertEquals(ElementType.RESULT_PARAMETER_TYPE, createdElement.getElementType(),
                    "Wrong language element element type");
            assertEquals(SimpleResultParameterTypeWithSymbolicParameterName.class.getSimpleName(),
                    createdElement.getName(), "Wrong language element name");
            assertEquals(SimpleResultParameterTypeWithSymbolicParameterName.class, createdElement.getSourceClass(),
                    "Wrong language element source class");
            assertEquals(sourcePlugin, createdElement.getSourcePlugin(), "Wrong language element source plug-in");
            
        } catch (ExternalElementException e) {
            assertNull(e, "This test should not throw any exception");
        }
    }
    
    /**
     * Tests the correct creation of a simple {@link ResultParameterType} based on the class 
     * {@link SimpleResultParameterTypeWithSymbolicNameAndParameterName}.
     */
    @Test
    public void testSimpleFragmentParameterTypeWithSymbolicNameAndParameterNameCreation() {
        try {
            List<LanguageElement> createdElements = elementCreator.
                    createLanguageElements(SimpleResultParameterTypeWithSymbolicNameAndParameterName.class,
                            sourcePlugin);
            assertEquals(1, createdElements.size(), "Wrong number of created language elements");
            
            LanguageElement createdElement = createdElements.get(0);
            assertEquals(ParameterType.class, createdElement.getClass(), "Wrong language element type");
            assertEquals(ElementType.RESULT_PARAMETER_TYPE, createdElement.getElementType(),
                    "Wrong language element element type");
            assertEquals("DeadBlock", createdElement.getName(), "Wrong language element name");
            assertEquals(SimpleResultParameterTypeWithSymbolicNameAndParameterName.class,
                    createdElement.getSourceClass(), "Wrong language element source class");
            assertEquals(sourcePlugin, createdElement.getSourcePlugin(), "Wrong language element source plug-in");
            
        } catch (ExternalElementException e) {
            assertNull(e, "This test should not throw any exception");
        }
    }
    
    /**
     * This class represents a generic result parameter type with {@link File} as parameter type. It is annotated with
     * the {@link ResultParameterType} annotation without any further parameters. Hence, the actual class name and
     * parameter type should be used for element creation.
     * 
     * @param <File> the parameter type of this generic class for testing
     * 
     * @author Christian Kroeher
     *
     */
    @ResultParameterType
    private class GenericResultParameterType<File> { }
    
    /**
     * This class represents a generic result parameter type with {@link File} as parameter type. It is annotated with
     * the {@link ResultParameterType} annotation including the definition of the symbolic name <i>DeadBlock</i>, which
     * should be used for element creation instead of the actual class name.
     * 
     * @param <File> the parameter type of this generic class for testing
     * 
     * @author Christian Kroeher
     *
     */
    @ResultParameterType(name = "DeadBlock")
    private class GenericResultParameterTypeWithSymbolicName<File> { }
    
    /**
     * This class represents a generic result parameter type with {@link File} as parameter type. It is annotated with
     * the {@link ResultParameterType} annotation including the definition of the symbolic parameter name
     * <i>Code</i>, which should be used for element creation instead of the actual parameter type.
     * 
     * @param <File> the parameter type of this generic class for testing
     * 
     * @author Christian Kroeher
     *
     */
    @ResultParameterType(parameterName = "Code")
    private class GenericResultParameterTypeWithSymbolicParameterName<File> { }
    
    /**
     * This class represents a generic result parameter type with {@link File} as parameter type. It is annotated with
     * the {@link ResultParameterType} annotation including the definition of the the symbolic name <i>DeadBlock</i> and
     * the symbolic parameter name <i>Code</i>. Hence, both symbolic names should be used for element creation instead
     * of the actual class and parameter type names.
     * 
     * @param <File> the parameter type of this generic class for testing
     * 
     * @author Christian Kroeher
     *
     */
    @ResultParameterType(name = "DeadBlock", parameterName = "Code")
    private class GenericResultParameterTypeWithSymbolicNameAndParameterName<File> { }
    
    /**
     * Tests the correct creation of a generic {@link ResultParameterType} based on the class 
     * {@link GenericResultParameterType}.
     */
    @Test
    public void testGenericResultParameterTypeCreation() {
        try {
            List<LanguageElement> createdElements = 
                    elementCreator.createLanguageElements(GenericResultParameterType.class, sourcePlugin);
            assertEquals(1, createdElements.size(), "Wrong number of created language elements");
            
            LanguageElement createdElement = createdElements.get(0);
            assertEquals(ParameterType.class, createdElement.getClass(), "Wrong language element type");
            assertEquals(ElementType.RESULT_PARAMETER_TYPE, createdElement.getElementType(),
                    "Wrong language element element type");
            assertEquals("GenericResultParameterType<File>", createdElement.getName(),
                    "Wrong language element name");
            assertEquals(GenericResultParameterType.class, createdElement.getSourceClass(),
                    "Wrong language element source class");
            assertEquals(sourcePlugin, createdElement.getSourcePlugin(), "Wrong language element source plug-in");
            
        } catch (ExternalElementException e) {
            assertNull(e, "This test should not throw any exception");
        }
    }
    
    /**
     * Tests the correct creation of a generic {@link ResultParameterType} based on the class 
     * {@link GenericResultParameterTypeWithSymbolicName}.
     */
    @Test
    public void testGenericResultParameterTypeWithSymbolicNameCreation() {
        try {
            List<LanguageElement> createdElements = 
                    elementCreator.createLanguageElements(GenericResultParameterTypeWithSymbolicName.class,
                            sourcePlugin);
            assertEquals(1, createdElements.size(), "Wrong number of created language elements");
            
            LanguageElement createdElement = createdElements.get(0);
            assertEquals(ParameterType.class, createdElement.getClass(), "Wrong language element type");
            assertEquals(ElementType.RESULT_PARAMETER_TYPE, createdElement.getElementType(),
                    "Wrong language element element type");
            assertEquals("DeadBlock<File>", createdElement.getName(), "Wrong language element name");
            assertEquals(GenericResultParameterTypeWithSymbolicName.class, createdElement.getSourceClass(),
                    "Wrong language element source class");
            assertEquals(sourcePlugin, createdElement.getSourcePlugin(), "Wrong language element source plug-in");
            
        } catch (ExternalElementException e) {
            assertNull(e, "This test should not throw any exception");
        }
    }
    
    /**
     * Tests the correct creation of a simple {@link ResultParameterType} based on the class 
     * {@link GenericResultParameterTypeWithSymbolicParameterName}.
     */
    @Test
    public void testGenericResultParameterTypeWithSymbolicParameterNameCreation() {
        try {
            List<LanguageElement> createdElements = 
                    elementCreator.createLanguageElements(GenericResultParameterTypeWithSymbolicParameterName.class,
                            sourcePlugin);
            assertEquals(1, createdElements.size(), "Wrong number of created language elements");
            
            LanguageElement createdElement = createdElements.get(0);
            assertEquals(ParameterType.class, createdElement.getClass(), "Wrong language element type");
            assertEquals(ElementType.RESULT_PARAMETER_TYPE, createdElement.getElementType(),
                    "Wrong language element element type");            
            assertEquals("GenericResultParameterTypeWithSymbolicParameterName<Code>", createdElement.getName(),
                    "Wrong language element name");
            assertEquals(GenericResultParameterTypeWithSymbolicParameterName.class, createdElement.getSourceClass(),
                    "Wrong language element source class");
            assertEquals(sourcePlugin, createdElement.getSourcePlugin(), "Wrong language element source plug-in");
            
        } catch (ExternalElementException e) {
            assertNull(e, "This test should not throw any exception");
        }
    }
    
    /**
     * Tests the correct creation of a simple {@link ResultParameterType} based on the class 
     * {@link GenericResultParameterTypeWithSymbolicNameAndParameterName}.
     */
    @Test
    public void testGenericResultParameterTypeWithSymbolicNameAndParameterNameCreation() {
        try {
            List<LanguageElement> createdElements = elementCreator.
                    createLanguageElements(GenericResultParameterTypeWithSymbolicNameAndParameterName.class,
                            sourcePlugin);
            assertEquals(1, createdElements.size(), "Wrong number of created language elements");
            
            LanguageElement createdElement = createdElements.get(0);
            assertEquals(ParameterType.class, createdElement.getClass(), "Wrong language element type");
            assertEquals(ElementType.RESULT_PARAMETER_TYPE, createdElement.getElementType(),
                    "Wrong language element element type");
            assertEquals("DeadBlock<Code>", createdElement.getName(), "Wrong language element name");
            assertEquals(GenericResultParameterTypeWithSymbolicNameAndParameterName.class,
                    createdElement.getSourceClass(), "Wrong language element source class");
            assertEquals(sourcePlugin, createdElement.getSourcePlugin(), "Wrong language element source plug-in");
            
        } catch (ExternalElementException e) {
            assertNull(e, "This test should not throw any exception");
        }
    }

}
