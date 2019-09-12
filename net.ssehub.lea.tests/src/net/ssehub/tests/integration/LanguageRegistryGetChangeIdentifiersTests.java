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
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import net.ssehub.integration.ChangeIdentifier;
import net.ssehub.integration.ElementType;
import net.ssehub.integration.LanguageElement;
import net.ssehub.integration.LanguageElementException;
import net.ssehub.integration.LanguageRegistry;
import net.ssehub.integration.ParameterType;

/**
 * This class contains unit tests for the {@link LanguageRegistry#getChangeIdentifiers(String)} method of the
 * {@link LanguageRegistry}.
 * 
 * @author Christian Kroeher
 *
 */
public class LanguageRegistryGetChangeIdentifiersTests {

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
     * Tests the correct return values, if a single change identifier is added.
     */
    @Test
    public void testGetChangeIdentifiersWithSingleChangeIdentifier() {
        try {
            ChangeIdentifier changeIdentifier = new ChangeIdentifier("ChangeIdentifier",
                    LanguageRegistryGetChangeIdentifiersTests.class, SOURCE_PLUGIN);
            ParameterType assignableElement = new ParameterType(ElementType.ARTIFACT_PARAMETER_TYPE, "File",
                    LanguageRegistryGetChangeIdentifiersTests.class, SOURCE_PLUGIN);
            assertTrue(LanguageRegistry.INSTANCE.addParameterType(assignableElement),
                    "Assignable element should be added");
            changeIdentifier.finalize(new ParameterType[] {assignableElement});
            assertTrue(LanguageRegistry.INSTANCE.addChangeIdentifier(changeIdentifier),
                    "Change identifier should be added");
            
            // Test with simple name
            List<ChangeIdentifier> availableChangeIdentifiers = 
                    LanguageRegistry.INSTANCE.getChangeIdentifiers(changeIdentifier.getName());
            assertNotNull(availableChangeIdentifiers, "Missing change identifier(s)"); 
            assertEquals(changeIdentifier, availableChangeIdentifiers.get(0), "Wrong change identifier");
            
            // Test with fully-qualified name
            availableChangeIdentifiers = 
                    LanguageRegistry.INSTANCE.getChangeIdentifiers(changeIdentifier.getFullyQualifiedName());
            assertNotNull(availableChangeIdentifiers, "Missing change identifier(s)"); 
            assertEquals(changeIdentifier, availableChangeIdentifiers.get(0), "Wrong change identifier");
            
        } catch (LanguageElementException e) {
            System.out.println("[LanguageRegistryGetChangeIdentifiersTests] Creating change identifier(s) failed");
            e.printStackTrace();
        }
    }
    
    /**
     * Tests the correct return values, if two change identifiers are added.
     */
    @Test
    public void testGetChangeIdentifiersWithTwoChangeIdentifiers() {
        try {
            ChangeIdentifier changeIdentifier1 = new ChangeIdentifier("ChangeIdentifier1",
                    LanguageRegistryGetChangeIdentifiersTests.class, SOURCE_PLUGIN);
            ParameterType assignableElement = new ParameterType(ElementType.ARTIFACT_PARAMETER_TYPE, "Directory",
                    LanguageRegistryGetChangeIdentifiersTests.class, SOURCE_PLUGIN);
            assertTrue(LanguageRegistry.INSTANCE.addParameterType(assignableElement),
                    "Assignable element should be added");
            changeIdentifier1.finalize(new ParameterType[] {assignableElement});
            assertTrue(LanguageRegistry.INSTANCE.addChangeIdentifier(changeIdentifier1),
                    "Change identifier should be added");
            
            ChangeIdentifier changeIdentifier2 = new ChangeIdentifier("ChangeIdentifier1",
                    String.class, SOURCE_PLUGIN);
            changeIdentifier2.finalize(new ParameterType[] {assignableElement});
            assertTrue(LanguageRegistry.INSTANCE.addChangeIdentifier(changeIdentifier2),
                    "Change identifier should be added");
            
            // Test with simple name
            List<ChangeIdentifier> availableChangeIdentifiers = 
                    LanguageRegistry.INSTANCE.getChangeIdentifiers(changeIdentifier1.getName());
            assertNotNull(availableChangeIdentifiers, "Missing change identifier(s)");
            assertEquals(2, availableChangeIdentifiers.size(), "Wrong number of change identifier(s)");
            assertEquals(changeIdentifier1, availableChangeIdentifiers.get(0), "Wrong change identifier");
            assertEquals(changeIdentifier2, availableChangeIdentifiers.get(1), "Wrong change identifier");
            
            // Test with fully-qualified name
            availableChangeIdentifiers = 
                    LanguageRegistry.INSTANCE.getChangeIdentifiers(changeIdentifier1.getFullyQualifiedName());
            assertNotNull(availableChangeIdentifiers, "Missing change identifier(s)"); 
            assertEquals(1, availableChangeIdentifiers.size(), "Wrong number of change identifier(s)");
            assertEquals(changeIdentifier1, availableChangeIdentifiers.get(0), "Wrong change identifier");
            assertNotEquals(changeIdentifier2, availableChangeIdentifiers.get(0), "Wrong change identifier");
            
        } catch (LanguageElementException e) {
            System.out.println("[LanguageRegistryGetChangeIdentifiersTests] Creating change identifier(s) failed");
            e.printStackTrace();
        }
    }
}
