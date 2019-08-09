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

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.lang.reflect.Method;

import org.junit.Test;

import net.ssehub.integration.Call;
import net.ssehub.integration.ChangeIdentifier;
import net.ssehub.integration.ElementType;
import net.ssehub.integration.LanguageElement;
import net.ssehub.integration.LanguageElementException;
import net.ssehub.integration.ParameterType;

/**
 * This class contains unit tests for equality checks of {@link LanguageElement}s. These tests check whether the 
 * <code>equals()</code>-methods work as expected.
 * 
 * @author Christian Kroeher
 *
 */
public class LanguageElementEqualityTests {
    
    /**
     * The {@link File} denoting the source plug-in of the {@link Class} from which a {@link LanguageElement} should be
     * created. This is just a dummy file as it has no impact on the actual creation, but is only used as a constructor
     * parameter.
     */
    private static final File SOURCE_PLUGIN = new File("./");

    /**
     * Test whether a single {@link ParameterType} instance is equal to itself.
     */
    @Test
    public void testCorrectEqualParameterTypesWithSingleInstance() {
        try {
            ParameterType parameterType = new ParameterType(ElementType.ARTIFACT_PARAMETER_TYPE, "File",
                    LanguageElementEqualityTests.class, SOURCE_PLUGIN);
            
            assertTrue(parameterType.equals(parameterType), "Parameter types should be equal");
        } catch (LanguageElementException e) {
            assertNull("Unexpected exception thrown", e);
        }
    }
    
    /**
     * Test whether two {@link ParameterType} instances with equal attributes are equal.
     */
    @Test
    public void testCorrectEqualParameterTypesWithTwoInstance() {
        try {
            ParameterType parameterType1 = new ParameterType(ElementType.ARTIFACT_PARAMETER_TYPE, "File",
                    LanguageElementEqualityTests.class, SOURCE_PLUGIN);
            ParameterType parameterType2 = new ParameterType(ElementType.ARTIFACT_PARAMETER_TYPE, "File",
                    LanguageElementEqualityTests.class, SOURCE_PLUGIN);
            
            assertTrue(parameterType1.equals(parameterType2), "Parameter types should be equal");
        } catch (LanguageElementException e) {
            assertNull("Unexpected exception thrown", e);
        }
    }
    
    /**
     * Test whether two {@link ParameterType} instances with different {@link ElementType}s but equal remaining
     * attributes are unequal.
     */
    @Test
    public void testCorrectUnequalParameterTypesWithUnequalElementTypes() {
        try {
            ParameterType parameterType1 = new ParameterType(ElementType.ARTIFACT_PARAMETER_TYPE, "File",
                    LanguageElementEqualityTests.class, SOURCE_PLUGIN);
            ParameterType parameterType2 = new ParameterType(ElementType.FRAGMENT_PARAMETER_TYPE, "File",
                    LanguageElementEqualityTests.class, SOURCE_PLUGIN);
            
            assertFalse(parameterType1.equals(parameterType2), "Parameter types should be unequal");
        } catch (LanguageElementException e) {
            assertNull("Unexpected exception thrown", e);
        }
    }
    
    /**
     * Test whether two {@link ParameterType} instances with different names but equal remaining attributes are unequal.
     */
    @Test
    public void testCorrectUnequalParameterTypesWithUnequalNames() {
        try {
            ParameterType parameterType1 = new ParameterType(ElementType.ARTIFACT_PARAMETER_TYPE, "File",
                    LanguageElementEqualityTests.class, SOURCE_PLUGIN);
            ParameterType parameterType2 = new ParameterType(ElementType.ARTIFACT_PARAMETER_TYPE, "Database",
                    LanguageElementEqualityTests.class, SOURCE_PLUGIN);
            
            assertFalse(parameterType1.equals(parameterType2), "Parameter types should be unequal");
        } catch (LanguageElementException e) {
            assertNull("Unexpected exception thrown", e);
        }
    }
    
    /**
     * Test whether two {@link ParameterType} instances with different source {@link Class}es but equal remaining
     * attributes are unequal.
     */
    @Test
    public void testCorrectUnequalParameterTypesWithUnequalSourceClasses() {
        try {
            ParameterType parameterType1 = new ParameterType(ElementType.ARTIFACT_PARAMETER_TYPE, "File",
                    LanguageElementEqualityTests.class, SOURCE_PLUGIN);
            ParameterType parameterType2 = new ParameterType(ElementType.ARTIFACT_PARAMETER_TYPE, "File",
                    AbstractCreationTest.class, SOURCE_PLUGIN);
            
            assertFalse(parameterType1.equals(parameterType2), "Parameter types should be unequal");
        } catch (LanguageElementException e) {
            assertNull("Unexpected exception thrown", e);
        }
    }
    
    /**
     * Test whether two {@link ParameterType} instances with different source plug-ins ({@link File}s) but equal
     * remaining attributes are unequal.
     */
    @Test
    public void testCorrectUnequalParameterTypesWithUnequalSourcePlugins() {
        try {
            ParameterType parameterType1 = new ParameterType(ElementType.ARTIFACT_PARAMETER_TYPE, "File",
                    LanguageElementEqualityTests.class, SOURCE_PLUGIN);
            ParameterType parameterType2 = new ParameterType(ElementType.ARTIFACT_PARAMETER_TYPE, "File",
                    LanguageElementEqualityTests.class, new File(""));
            
            assertFalse(parameterType1.equals(parameterType2), "Parameter types should be unequal");
        } catch (LanguageElementException e) {
            assertNull("Unexpected exception thrown", e);
        }
    }
    
    /**
     * Tests whether a {@link ParameterType} is unequal to a {@link ChangeIdentifier}.
     */
    @Test
    public void testCorrectInequalityBetweenParameterTypeAndChangeIdentifier() {
        try {
            ParameterType parameterType = new ParameterType(ElementType.ARTIFACT_PARAMETER_TYPE, "File",
                    LanguageElementEqualityTests.class, SOURCE_PLUGIN);
            ChangeIdentifier changeIdentifier = new ChangeIdentifier("CI", new String[] {"File"},
                    LanguageElementEqualityTests.class, SOURCE_PLUGIN);
            
            assertFalse(parameterType.equals(changeIdentifier),
                    "Parameter type should be unequal to change identifier");
        } catch (LanguageElementException e) {
            assertNull("Unexpected exception thrown", e);
        }
    }
    
    /**
     * Tests whether a {@link ParameterType} is unequal to a {@link Call}.
     */
    @Test
    public void testCorrectInequalityBetweenParameterTypeAndCall() {
        try {
            ParameterType parameterType = new ParameterType(ElementType.ARTIFACT_PARAMETER_TYPE, "File",
                    LanguageElementEqualityTests.class, SOURCE_PLUGIN);
            Call call = new Call(ElementType.OPERATION, "file", "File", new String[] {"String"},
                    LanguageElementEqualityTests.class.getMethods()[0], LanguageElementEqualityTests.class,
                    SOURCE_PLUGIN);
            
            assertFalse(parameterType.equals(call), "Parameter type should be unequal to call");
        } catch (LanguageElementException e) {
            assertNull("Unexpected exception thrown", e);
        }
    }
    
    /**
     * Test whether a single {@link ChangeIdentifier} instance is equal to itself.
     */
    @Test
    public void testCorrectEqualChangeIdentifiersWithSingleInstance() {
        try {
            ChangeIdentifier changeIdentifier = new ChangeIdentifier("CI", new String[] {"File"},
                    LanguageElementEqualityTests.class, SOURCE_PLUGIN);
            
            assertTrue(changeIdentifier.equals(changeIdentifier), "Change identifiers should be equal");
        } catch (LanguageElementException e) {
            assertNull("Unexpected exception thrown", e);
        }
    }
    
    /**
     * Test whether two {@link ChangeIdentifier} instances with equal attributes are equal.
     */
    @Test
    public void testCorrectEqualChangeIdentifiersWithTwoInstance() {
        try {
            ChangeIdentifier changeIdentifier1 = new ChangeIdentifier("CI", new String[] {"File"},
                    LanguageElementEqualityTests.class, SOURCE_PLUGIN);
            ChangeIdentifier changeIdentifier2 = new ChangeIdentifier("CI", new String[] {"File"},
                    LanguageElementEqualityTests.class, SOURCE_PLUGIN);
            
            assertTrue(changeIdentifier1.equals(changeIdentifier2), "Change identifiers should be equal");
        } catch (LanguageElementException e) {
            assertNull("Unexpected exception thrown", e);
        }
    }
    
    /**
     * Test whether a {@link ChangeIdentifier} instance is unequal to a different {@link LanguageElement}.
     */
    @Test
    public void testCorrectUnequalChangeIdentifierToElementWithUnequalElementType() {
        try {
            ChangeIdentifier changeIdentifier = new ChangeIdentifier("CI", new String[] {"File"},
                    LanguageElementEqualityTests.class, SOURCE_PLUGIN);
            ParameterType parameterType = new ParameterType(ElementType.ARTIFACT_PARAMETER_TYPE, "File",
                    LanguageElementEqualityTests.class, SOURCE_PLUGIN);
            
            assertFalse(changeIdentifier.equals(parameterType), "Different language elements should be unequal");
        } catch (LanguageElementException e) {
            assertNull("Unexpected exception thrown", e);
        }
    }
    
    /**
     * Test whether two {@link ChangeIdentifier} instances with different names but equal remaining attributes are
     * unequal.
     */
    @Test
    public void testCorrectUnequalChangeIdentifiersWithUnequalNames() {
        try {
            ChangeIdentifier changeIdentifier1 = new ChangeIdentifier("CI", new String[] {"File"},
                    LanguageElementEqualityTests.class, SOURCE_PLUGIN);
            ChangeIdentifier changeIdentifier2 = new ChangeIdentifier("Identifier", new String[] {"File"},
                    LanguageElementEqualityTests.class, SOURCE_PLUGIN);
            
            assertFalse(changeIdentifier1.equals(changeIdentifier2), "Change Identifiers should be unequal");
        } catch (LanguageElementException e) {
            assertNull("Unexpected exception thrown", e);
        }
    }
    
    /**
     * Test whether two {@link ChangeIdentifier} instances with different assignable elements but the same number of
     * assignable elements and equal remaining attributes are unequal.
     */
    @Test
    public void testCorrectUnequalChangeIdentifiersWithUnequalAssignableElements() {
        try {
            ChangeIdentifier changeIdentifier1 = new ChangeIdentifier("CI", new String[] {"File"},
                    LanguageElementEqualityTests.class, SOURCE_PLUGIN);
            ChangeIdentifier changeIdentifier2 = new ChangeIdentifier("Identifier", new String[] {"DB"},
                    LanguageElementEqualityTests.class, SOURCE_PLUGIN);
            
            assertFalse(changeIdentifier1.equals(changeIdentifier2), "Change Identifiers should be unequal");
        } catch (LanguageElementException e) {
            assertNull("Unexpected exception thrown", e);
        }
    }
    
    /**
     * Test whether two {@link ChangeIdentifier} instances with different number of assignable elements but equal
     * remaining attributes are unequal.
     */
    @Test
    public void testCorrectUnequalChangeIdentifiersWithUnequalAssignableElementsNumber() {
        try {
            ChangeIdentifier changeIdentifier1 = new ChangeIdentifier("CI", new String[] {"File"},
                    LanguageElementEqualityTests.class, SOURCE_PLUGIN);
            ChangeIdentifier changeIdentifier2 = new ChangeIdentifier("Identifier", new String[] {"File", "DB"},
                    LanguageElementEqualityTests.class, SOURCE_PLUGIN);
            
            assertFalse(changeIdentifier1.equals(changeIdentifier2), "Change Identifiers should be unequal");
        } catch (LanguageElementException e) {
            assertNull("Unexpected exception thrown", e);
        }
    }
    
    /**
     * Test whether two {@link ChangeIdentifier} instances with different source {@link Class}es but equal remaining
     * attributes are unequal.
     */
    @Test
    public void testCorrectUnequalChangeIdentifierWithUnequalSourceClasses() {
        try {
            ChangeIdentifier changeIdentifier1 = new ChangeIdentifier("CI", new String[] {"File"},
                    LanguageElementEqualityTests.class, SOURCE_PLUGIN);
            ChangeIdentifier changeIdentifier2 = new ChangeIdentifier("CI", new String[] {"File"},
                    AbstractCreationTest.class, SOURCE_PLUGIN);
            
            assertFalse(changeIdentifier1.equals(changeIdentifier2), "Change Identifiers should be unequal");
        } catch (LanguageElementException e) {
            assertNull("Unexpected exception thrown", e);
        }
    }
    
    /**
     * Test whether two {@link ChangeIdentifier} instances with different source plug-ins ({@link File}s) but equal
     * remaining attributes are unequal.
     */
    @Test
    public void testCorrectUnequalChangeIdentifierWithUnequalSourcePlugins() {
        try {
            ChangeIdentifier changeIdentifier1 = new ChangeIdentifier("CI", new String[] {"File"},
                    LanguageElementEqualityTests.class, SOURCE_PLUGIN);
            ChangeIdentifier changeIdentifier2 = new ChangeIdentifier("CI", new String[] {"File"},
                    LanguageElementEqualityTests.class, new File(""));
            
            assertFalse(changeIdentifier1.equals(changeIdentifier2), "Change Identifiers should be unequal");
        } catch (LanguageElementException e) {
            assertNull("Unexpected exception thrown", e);
        }
    }
    
    /**
     * Tests whether a {@link ChangeIdentifier} is unequal to a {@link ParameterType}.
     */
    @Test
    public void testCorrectInequalityBetweenChangeIdentifierAndParameterType() {
        try {
            ChangeIdentifier changeIdentifier = new ChangeIdentifier("CI", new String[] {"File"},
                    LanguageElementEqualityTests.class, SOURCE_PLUGIN);
            ParameterType parameterType = new ParameterType(ElementType.ARTIFACT_PARAMETER_TYPE, "File",
                    LanguageElementEqualityTests.class, SOURCE_PLUGIN);
            
            assertFalse(changeIdentifier.equals(parameterType),
                    "Change identifier should be unequal to parameter type");
        } catch (LanguageElementException e) {
            assertNull("Unexpected exception thrown", e);
        }
    }
    
    /**
     * Tests whether a {@link ChangeIdentifier} is unequal to a {@link Call}.
     */
    @Test
    public void testCorrectInequalityBetweenChangeIdentifierAndCall() {
        try {
            ChangeIdentifier changeIdentifier = new ChangeIdentifier("CI", new String[] {"File"},
                    LanguageElementEqualityTests.class, SOURCE_PLUGIN);
            Call call = new Call(ElementType.OPERATION, "file", "File", new String[] {"String"},
                    LanguageElementEqualityTests.class.getMethods()[0], LanguageElementEqualityTests.class,
                    SOURCE_PLUGIN);
            
            assertFalse(changeIdentifier.equals(call), "Change identifier should be unequal to call");
        } catch (LanguageElementException e) {
            assertNull("Unexpected exception thrown", e);
        }
    }
    
    /**
     * Test whether a single {@link Call} instance is equal to itself.
     */
    @Test
    public void testCorrectEqualCallsWithSingleInstance() {
        try {
            Call call = new Call(ElementType.OPERATION, "file", "File", new String[] {"String"},
                    LanguageElementEqualityTests.class.getMethods()[0], LanguageElementEqualityTests.class,
                    SOURCE_PLUGIN);
            
            assertTrue(call.equals(call), "Calls should be equal");
        } catch (LanguageElementException e) {
            assertNull("Unexpected exception thrown", e);
        }
    }
    
    /**
     * Test whether two {@link Call} instances with equal attributes are equal.
     */
    @Test
    public void testCorrectEqualCallsWithTwoInstance() {
        try {
            Call call1 = new Call(ElementType.OPERATION, "file", "File", new String[] {"String"},
                    LanguageElementEqualityTests.class.getMethods()[0], LanguageElementEqualityTests.class,
                    SOURCE_PLUGIN);
            Call call2 = new Call(ElementType.OPERATION, "file", "File", new String[] {"String"},
                    LanguageElementEqualityTests.class.getMethods()[0], LanguageElementEqualityTests.class,
                    SOURCE_PLUGIN);
            
            assertTrue(call1.equals(call2), "Calls should be equal");
        } catch (LanguageElementException e) {
            assertNull("Unexpected exception thrown", e);
        }
    }
    
    /**
     * Test whether two {@link Call} instances with different {@link ElementType}s but equal remaining attributes are
     * unequal.
     */
    @Test
    public void testCorrectUnequalCallsWithUnequalElementTypes() {
        try {
            Call call1 = new Call(ElementType.OPERATION, "file", "File", new String[] {"String"},
                    LanguageElementEqualityTests.class.getMethods()[0], LanguageElementEqualityTests.class,
                    SOURCE_PLUGIN);
            Call call2 = new Call(ElementType.EXTRACTOR_CALL, "file", "File", new String[] {"String"},
                    LanguageElementEqualityTests.class.getMethods()[0], LanguageElementEqualityTests.class,
                    SOURCE_PLUGIN);
            
            assertFalse(call1.equals(call2), "Calls should be unequal");
        } catch (LanguageElementException e) {
            assertNull("Unexpected exception thrown", e);
        }
    }
    
    /**
     * Test whether two {@link Call} instances with different names but equal remaining attributes are unequal.
     */
    @Test
    public void testCorrectUnequalCallsWithUnequalNames() {
        try {
            Call call1 = new Call(ElementType.OPERATION, "file", "File", new String[] {"String"},
                    LanguageElementEqualityTests.class.getMethods()[0], LanguageElementEqualityTests.class,
                    SOURCE_PLUGIN);
            Call call2 = new Call(ElementType.OPERATION, "getfile", "File", new String[] {"String"},
                    LanguageElementEqualityTests.class.getMethods()[0], LanguageElementEqualityTests.class,
                    SOURCE_PLUGIN);
            
            assertFalse(call1.equals(call2), "Calls should be unequal");
        } catch (LanguageElementException e) {
            assertNull("Unexpected exception thrown", e);
        }
    }
    
    /**
     * Test whether two {@link Call} instances with different return types but equal remaining attributes are unequal.
     */
    @Test
    public void testCorrectUnequalCallsWithUnequalReturnTypes() {
        try {
            Call call1 = new Call(ElementType.OPERATION, "file", "File", new String[] {"String"},
                    LanguageElementEqualityTests.class.getMethods()[0], LanguageElementEqualityTests.class,
                    SOURCE_PLUGIN);
            Call call2 = new Call(ElementType.OPERATION, "file", "Datei", new String[] {"String"},
                    LanguageElementEqualityTests.class.getMethods()[0], LanguageElementEqualityTests.class,
                    SOURCE_PLUGIN);
            
            assertFalse(call1.equals(call2), "Calls should be unequal");
        } catch (LanguageElementException e) {
            assertNull("Unexpected exception thrown", e);
        }
    }
    
    /**
     * Test whether two {@link Call} instances with different parameters but the same number of parameters and equal
     * remaining attributes are unequal.
     */
    @Test
    public void testCorrectUnequalCallsWithUnequalParameters() {
        try {
            Call call1 = new Call(ElementType.OPERATION, "file", "File", new String[] {"String"},
                    LanguageElementEqualityTests.class.getMethods()[0], LanguageElementEqualityTests.class,
                    SOURCE_PLUGIN);
            Call call2 = new Call(ElementType.OPERATION, "file", "File", new String[] {"Char[]"},
                    LanguageElementEqualityTests.class.getMethods()[0], LanguageElementEqualityTests.class,
                    SOURCE_PLUGIN);
            
            assertFalse(call1.equals(call2), "Calls should be unequal");
        } catch (LanguageElementException e) {
            assertNull("Unexpected exception thrown", e);
        }
    }
    
    /**
     * Test whether two {@link Call} instances with different number of parameters but equal remaining attributes are
     * unequal.
     */
    @Test
    public void testCorrectUnequalCallsWithUnequalParametersNumber() {
        try {
            Call call1 = new Call(ElementType.OPERATION, "file", "File", new String[] {"String"},
                    LanguageElementEqualityTests.class.getMethods()[0], LanguageElementEqualityTests.class,
                    SOURCE_PLUGIN);
            Call call2 = new Call(ElementType.OPERATION, "file", "File", new String[] {"String", "String"},
                    LanguageElementEqualityTests.class.getMethods()[0], LanguageElementEqualityTests.class,
                    SOURCE_PLUGIN);
            
            assertFalse(call1.equals(call2), "Calls should be unequal");
        } catch (LanguageElementException e) {
            assertNull("Unexpected exception thrown", e);
        }
    }
    
    /**
     * Test whether two {@link Call} instances with different source {@link Method}s but equal remaining attributes are
     * unequal.
     */
    @Test
    public void testCorrectUnequalCallsWithUnequalSourceMethods() {
        try {
            Call call1 = new Call(ElementType.OPERATION, "file", "File", new String[] {"String"},
                    LanguageElementEqualityTests.class.getMethods()[0], LanguageElementEqualityTests.class,
                    SOURCE_PLUGIN);
            Call call2 = new Call(ElementType.OPERATION, "file", "File", new String[] {"String", "String"},
                    LanguageElementEqualityTests.class.getMethods()[1], LanguageElementEqualityTests.class,
                    SOURCE_PLUGIN);
            
            assertFalse(call1.equals(call2), "Calls should be unequal");
        } catch (LanguageElementException e) {
            assertNull("Unexpected exception thrown", e);
        }
    }
    
    /**
     * Test whether two {@link Call} instances with different source {@link Class}es but equal remaining attributes are
     * unequal.
     */
    @Test
    public void testCorrectUnequalCallsWithUnequalSourceClasses() {
        try {
            Call call1 = new Call(ElementType.OPERATION, "file", "File", new String[] {"String"},
                    LanguageElementEqualityTests.class.getMethods()[0], LanguageElementEqualityTests.class,
                    SOURCE_PLUGIN);
            Call call2 = new Call(ElementType.OPERATION, "file", "File", new String[] {"String"},
                    LanguageElementEqualityTests.class.getMethods()[0], AbstractCreationTest.class,
                    SOURCE_PLUGIN);
            
            assertFalse(call1.equals(call2), "Calls should be unequal");
        } catch (LanguageElementException e) {
            assertNull("Unexpected exception thrown", e);
        }
    }
    
    /**
     * Test whether two {@link Call} instances with different source plug-ins ({@link File}s) but equal remaining
     * attributes are unequal.
     */
    @Test
    public void testCorrectUnequalCallsWithUnequalSourcePlugins() {
        try {
            Call call1 = new Call(ElementType.OPERATION, "file", "File", new String[] {"String"},
                    LanguageElementEqualityTests.class.getMethods()[0], LanguageElementEqualityTests.class,
                    SOURCE_PLUGIN);
            Call call2 = new Call(ElementType.OPERATION, "file", "File", new String[] {"String"},
                    LanguageElementEqualityTests.class.getMethods()[0], LanguageElementEqualityTests.class,
                    new File(""));
            
            assertFalse(call1.equals(call2), "Calls should be unequal");
        } catch (LanguageElementException e) {
            assertNull("Unexpected exception thrown", e);
        }
    }
    
    /**
     * Tests whether a {@link Call} is unequal to a {@link ParameterType}.
     */
    @Test
    public void testCorrectInequalityBetweenCallAndParameterType() {
        try {
            Call call = new Call(ElementType.OPERATION, "file", "File", new String[] {"String"},
                    LanguageElementEqualityTests.class.getMethods()[0], LanguageElementEqualityTests.class,
                    SOURCE_PLUGIN);
            ParameterType parameterType = new ParameterType(ElementType.ARTIFACT_PARAMETER_TYPE, "File",
                    LanguageElementEqualityTests.class, SOURCE_PLUGIN);
            
            assertFalse(call.equals(parameterType), "Call should be unequal to parameter type");
        } catch (LanguageElementException e) {
            assertNull("Unexpected exception thrown", e);
        }
    }
    
    /**
     * Tests whether a {@link Call} is unequal to a {@link ChangeIdentifier}.
     */
    @Test
    public void testCorrectInequalityBetweenCallAndChangeIdentifier() {
        try {
            Call call = new Call(ElementType.OPERATION, "file", "File", new String[] {"String"},
                    LanguageElementEqualityTests.class.getMethods()[0], LanguageElementEqualityTests.class,
                    SOURCE_PLUGIN);
            ChangeIdentifier changeIdentifier = new ChangeIdentifier("CI", new String[] {"File"},
                    LanguageElementEqualityTests.class, SOURCE_PLUGIN);
            
            assertFalse(call.equals(changeIdentifier), "Call should be unequal to change identifier");
        } catch (LanguageElementException e) {
            assertNull("Unexpected exception thrown", e);
        }
    }
}
