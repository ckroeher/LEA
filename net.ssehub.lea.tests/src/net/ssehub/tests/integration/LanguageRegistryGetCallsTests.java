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
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import net.ssehub.integration.Call;
import net.ssehub.integration.ElementType;
import net.ssehub.integration.LanguageElement;
import net.ssehub.integration.LanguageElementException;
import net.ssehub.integration.LanguageRegistry;
import net.ssehub.integration.ParameterType;
import net.ssehub.integration.ParameterTypeInstance;

/**
 * This class contains unit tests for the {@link LanguageRegistry#getCalls(net.ssehub.integration.ElementType, String)}
 * and the 
 * {@link LanguageRegistry#getCalls(net.ssehub.integration.ElementType, String, net.ssehub.integration.ParameterType[])}
 * methods of the {@link LanguageRegistry}.
 * 
 * @author Christian Kroeher
 *
 */
public class LanguageRegistryGetCallsTests {

    /**
     * The {@link File} denoting the source plug-in of the {@link Class} from which a {@link LanguageElement} should be
     * created. This is just a dummy file as it has no impact on the actual creation, but is only used as a constructor
     * parameter.
     */
    private static final File SOURCE_PLUGIN = new File("./");

    /**
     * Clears the {@link LanguageRegistry} before any of the tests are executed. 
     */
    @BeforeClass
    public static void setUp() {
        LanguageRegistry.INSTANCE.clear();
    }
    
    /**
     * Clears the {@link LanguageRegistry} after all tests were executed. 
     */
    @AfterClass
    public static void tearDown() {
        LanguageRegistry.INSTANCE.clear();
    }
    
    /**
     * Tests the correct return values, if a single operation is added.
     */
    @Test
    public void testGetCallsByNameWithSingleOperation() {
        try {
            Call operation = new Call(ElementType.OPERATION, "sort", 
                    LanguageRegistryGetCallsTests.class.getDeclaredMethods()[0], LanguageRegistryGetCallsTests.class,
                    SOURCE_PLUGIN);
            ParameterType parameter = new ParameterType(ElementType.ARTIFACT_PARAMETER_TYPE, "File",
                    LanguageRegistryGetCallsTests.class, SOURCE_PLUGIN);
            ParameterTypeInstance parameterInstance = new ParameterTypeInstance(parameter, false);
            assertTrue(LanguageRegistry.INSTANCE.addParameterType(parameter), "Parameter should be added");
            operation.finalize(parameterInstance, new ParameterTypeInstance[] {parameterInstance}, null);
            assertTrue(LanguageRegistry.INSTANCE.addCall(operation), "Operation should be added");
            
            // Test with simple name
            List<Call> availableCalls = LanguageRegistry.INSTANCE.getCalls(ElementType.OPERATION, operation.getName());
            assertNotNull(availableCalls, "Missing call(s)"); 
            assertEquals(operation, availableCalls.get(0), "Wrong call");
            
            // Test with fully-qualified name
            availableCalls = LanguageRegistry.INSTANCE.getCalls(ElementType.OPERATION,
                    operation.getFullyQualifiedName());
            assertNotNull(availableCalls, "Missing call(s)"); 
            assertEquals(operation, availableCalls.get(0), "Wrong call");
            
            // Test no other calls with that simple name
            assertNull(LanguageRegistry.INSTANCE.getCalls(ElementType.EXTRACTOR_CALL, operation.getName()),
                    "Unexpected extractor call(s)");
            assertNull(LanguageRegistry.INSTANCE.getCalls(ElementType.ANALYSIS_CALL, operation.getName()),
                    "Unexpected analysis call(s)");
            
            // Test no other calls with that fully-qualified name
            assertNull(LanguageRegistry.INSTANCE.getCalls(ElementType.EXTRACTOR_CALL,
                    operation.getFullyQualifiedName()), "Unexpected extractor call(s)");
            assertNull(LanguageRegistry.INSTANCE.getCalls(ElementType.ANALYSIS_CALL,
                    operation.getFullyQualifiedName()), "Unexpected analysis call(s)");
            
        } catch (LanguageElementException e) {
            System.out.println("[LanguageRegistryGetCallsTests] Creating change identifier(s) failed");
            e.printStackTrace();
        }
    }
    
    /**
     * Tests the correct return values, if a single operation is added.
     */
    @Test
    public void testGetCallsByNameAndParametersWithSingleOperation() {
        try {
            Call operation = new Call(ElementType.OPERATION, "sort", 
                    LanguageRegistryGetCallsTests.class.getDeclaredMethods()[0], LanguageRegistryGetCallsTests.class,
                    SOURCE_PLUGIN);
            ParameterType parameter = new ParameterType(ElementType.ARTIFACT_PARAMETER_TYPE, "Directory",
                    LanguageRegistryGetCallsTests.class, SOURCE_PLUGIN);
            ParameterTypeInstance parameterInstance = new ParameterTypeInstance(parameter, false);
            assertTrue(LanguageRegistry.INSTANCE.addParameterType(parameter), "Parameter should be added");
            operation.finalize(parameterInstance, new ParameterTypeInstance[] {parameterInstance}, null);
            assertTrue(LanguageRegistry.INSTANCE.addCall(operation), "Operation should be added");
            
            // Test with simple name
            List<Call> availableCalls = LanguageRegistry.INSTANCE.getCalls(ElementType.OPERATION, operation.getName(),
                    operation.getParameters());
            assertNotNull(availableCalls, "Missing call(s)"); 
            assertEquals(operation, availableCalls.get(0), "Wrong call");
            
            // Test with fully-qualified name
            availableCalls = LanguageRegistry.INSTANCE.getCalls(ElementType.OPERATION,
                    operation.getFullyQualifiedName(), operation.getParameters());
            assertNotNull(availableCalls, "Missing call(s)"); 
            assertEquals(operation, availableCalls.get(0), "Wrong call");
            
            // Test no other calls with that simple name
            assertNull(LanguageRegistry.INSTANCE.getCalls(ElementType.EXTRACTOR_CALL, operation.getName(),
                    operation.getParameters()), "Unexpected extractor call(s)");
            assertNull(LanguageRegistry.INSTANCE.getCalls(ElementType.ANALYSIS_CALL, operation.getName(),
                    operation.getParameters()), "Unexpected analysis call(s)");
            
            // Test no other calls with that fully-qualified name
            assertNull(LanguageRegistry.INSTANCE.getCalls(ElementType.EXTRACTOR_CALL,
                    operation.getFullyQualifiedName(), operation.getParameters()), "Unexpected extractor call(s)");
            assertNull(LanguageRegistry.INSTANCE.getCalls(ElementType.ANALYSIS_CALL,
                    operation.getFullyQualifiedName(), operation.getParameters()), "Unexpected analysis call(s)");
            
        } catch (LanguageElementException e) {
            System.out.println("[LanguageRegistryGetCallsTests] Creating change identifier(s) failed");
            e.printStackTrace();
        }
    }
    
    /**
     * Tests the correct return values, if two operations are added.
     */
    @Test
    public void testGetCallsByNameWithTwoOperations() {
        try {
            Call operation1 = new Call(ElementType.OPERATION, "sort1", 
                    LanguageRegistryGetCallsTests.class.getDeclaredMethods()[0], LanguageRegistryGetCallsTests.class,
                    SOURCE_PLUGIN);
            Call operation2 = new Call(ElementType.OPERATION, "sort1", 
                    String.class.getDeclaredMethods()[0], LanguageRegistryGetCallsTests.class,
                    SOURCE_PLUGIN);
            ParameterType parameter = new ParameterType(ElementType.ARTIFACT_PARAMETER_TYPE, "Folder",
                    LanguageRegistryGetCallsTests.class, SOURCE_PLUGIN);
            ParameterTypeInstance parameterInstance = new ParameterTypeInstance(parameter, false);
            assertTrue(LanguageRegistry.INSTANCE.addParameterType(parameter), "Parameter should be added");
            operation1.finalize(parameterInstance, new ParameterTypeInstance[] {parameterInstance}, null);
            assertTrue(LanguageRegistry.INSTANCE.addCall(operation1), "Operation should be added");
            operation2.finalize(parameterInstance, new ParameterTypeInstance[] {parameterInstance}, null);
            assertTrue(LanguageRegistry.INSTANCE.addCall(operation2), "Operation should be added");
            
            // Test with simple name
            List<Call> availableCalls = LanguageRegistry.INSTANCE.getCalls(ElementType.OPERATION, operation1.getName());
            assertNotNull(availableCalls, "Missing call(s)"); 
            assertEquals(2, availableCalls.size(), "Wrong number of calls");
            assertEquals(operation1, availableCalls.get(0), "Wrong call");
            assertEquals(operation2, availableCalls.get(1), "Wrong call");
            
            // Test with fully-qualified name
            availableCalls = LanguageRegistry.INSTANCE.getCalls(ElementType.OPERATION,
                    operation1.getFullyQualifiedName());
            assertNotNull(availableCalls, "Missing call(s)"); 
            assertEquals(1, availableCalls.size(), "Wrong number of calls");
            assertEquals(operation1, availableCalls.get(0), "Wrong call");
            assertNotEquals(operation2, availableCalls.get(0), "Wrong call");

        } catch (LanguageElementException e) {
            System.out.println("[LanguageRegistryGetCallsTests] Creating change identifier(s) failed");
            e.printStackTrace();
        }
    }
    
    /**
     * Tests the correct return values, if two operations are added.
     */
    @Test
    public void testGetCallsByNameAndParametersWithTwoOperations() {
        try {
            Call operation1 = new Call(ElementType.OPERATION, "sort1", 
                    LanguageRegistryGetCallsTests.class.getDeclaredMethods()[0], LanguageRegistryGetCallsTests.class,
                    SOURCE_PLUGIN);
            Call operation2 = new Call(ElementType.OPERATION, "sort1", 
                    String.class.getDeclaredMethods()[0], LanguageRegistryGetCallsTests.class,
                    SOURCE_PLUGIN);
            ParameterType parameter = new ParameterType(ElementType.ARTIFACT_PARAMETER_TYPE, "Map",
                    LanguageRegistryGetCallsTests.class, SOURCE_PLUGIN);
            ParameterTypeInstance parameterInstance = new ParameterTypeInstance(parameter, false);
            assertTrue(LanguageRegistry.INSTANCE.addParameterType(parameter), "Parameter should be added");
            operation1.finalize(parameterInstance, new ParameterTypeInstance[] {parameterInstance}, null);
            assertTrue(LanguageRegistry.INSTANCE.addCall(operation1), "Operation should be added");
            operation2.finalize(parameterInstance, null, null);
            assertTrue(LanguageRegistry.INSTANCE.addCall(operation2), "Operation should be added");
            
            // Test with simple name
            List<Call> availableCalls = LanguageRegistry.INSTANCE.getCalls(ElementType.OPERATION, operation1.getName(),
                    operation1.getParameters());
            assertNotNull(availableCalls, "Missing call(s)"); 
            assertEquals(1, availableCalls.size(), "Wrong number of calls");
            assertEquals(operation1, availableCalls.get(0), "Wrong call");
            assertNotEquals(operation2, availableCalls.get(0), "Wrong call");
            
            // Test with fully-qualified name
            availableCalls = LanguageRegistry.INSTANCE.getCalls(ElementType.OPERATION,
                    operation1.getFullyQualifiedName(), operation1.getParameters());
            assertNotNull(availableCalls, "Missing call(s)"); 
            assertEquals(1, availableCalls.size(), "Wrong number of calls");
            assertEquals(operation1, availableCalls.get(0), "Wrong call");
            assertNotEquals(operation2, availableCalls.get(0), "Wrong call");

        } catch (LanguageElementException e) {
            System.out.println("[LanguageRegistryGetCallsTests] Creating change identifier(s) failed");
            e.printStackTrace();
        }
    }

}
