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

import net.ssehub.integration.ElementType;
import net.ssehub.integration.LanguageElement;
import net.ssehub.integration.LanguageElementException;
import net.ssehub.integration.LanguageRegistry;
import net.ssehub.integration.ParameterType;

/**
 * This class contains unit tests for the 
 * {@link LanguageRegistry#getParameterTypes(net.ssehub.integration.ElementType, String)} method of the
 * {@link LanguageRegistry}.
 * 
 * @author Christian Kroeher
 *
 */
public class LanguageRegistryGetParameterTypesTests {
    
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
     * Tests the correct return values, if a single artifact parameter type is added.
     */
    @Test
    public void testGetParameterTypesWithSingleArtifactParameterType() {
        try {
            ParameterType artifactParameterType = new ParameterType(ElementType.ARTIFACT_PARAMETER_TYPE, "File",
                    LanguageRegistryGetParameterTypesTests.class, SOURCE_PLUGIN);
            assertTrue(LanguageRegistry.INSTANCE.addParameterType(artifactParameterType),
                    "Artifact parameter type should be added");
            
            // Test with simple name
            List<ParameterType> availableParameterTypes = LanguageRegistry.INSTANCE.getParameterTypes(
                    ElementType.ARTIFACT_PARAMETER_TYPE, artifactParameterType.getName());
            assertNotNull(availableParameterTypes, "Missing artifact parameter type(s)"); 
            assertEquals(artifactParameterType, availableParameterTypes.get(0), "Wrong artifact parameter type");
            
            // Test with fully-qualified name
            availableParameterTypes = LanguageRegistry.INSTANCE.getParameterTypes(
                    ElementType.ARTIFACT_PARAMETER_TYPE, artifactParameterType.getFullyQualifiedName());
            assertNotNull(availableParameterTypes, "Missing artifact parameter type(s)");
            assertEquals(artifactParameterType, availableParameterTypes.get(0), "Wrong artifact parameter type");
            
            // Test no other parameter types with that simple name
            assertNull(LanguageRegistry.INSTANCE.getParameterTypes(ElementType.FRAGMENT_PARAMETER_TYPE,
                    artifactParameterType.getName()), "Unexpected fragment parameter type(s)");
            assertNull(LanguageRegistry.INSTANCE.getParameterTypes(ElementType.RESULT_PARAMETER_TYPE,
                    artifactParameterType.getName()), "Unexpected result parameter type(s)");
            
            // Test no other parameter types with that fully-qualified name
            assertNull(LanguageRegistry.INSTANCE.getParameterTypes(ElementType.FRAGMENT_PARAMETER_TYPE,
                    artifactParameterType.getFullyQualifiedName()), "Unexpected fragment parameter type(s)");
            assertNull(LanguageRegistry.INSTANCE.getParameterTypes(ElementType.RESULT_PARAMETER_TYPE,
                    artifactParameterType.getFullyQualifiedName()), "Unexpected result parameter type(s)");
            
        } catch (LanguageElementException e) {
            System.out.println("[LanguageRegistryGetParameterTypesTests] Creating parameter type(s) failed");
            e.printStackTrace();
        }
    }
    
    /**
     * Tests the correct return values, if two artifact parameter types with the same name are added.
     */
    @Test
    public void testGetParameterTypesWithTwoArtifactParameterTypesSameName() {
        try {
            ParameterType artifactParameterType1 = new ParameterType(ElementType.ARTIFACT_PARAMETER_TYPE, "File1",
                    LanguageRegistryGetParameterTypesTests.class, SOURCE_PLUGIN);
            assertTrue(LanguageRegistry.INSTANCE.addParameterType(artifactParameterType1),
                    "Artifact parameter type should be added");
            ParameterType artifactParameterType2 = new ParameterType(ElementType.ARTIFACT_PARAMETER_TYPE, "File1",
                    String.class, SOURCE_PLUGIN);
            assertTrue(LanguageRegistry.INSTANCE.addParameterType(artifactParameterType2),
                    "Artifact parameter type should be added");
            
            // Test with simple name
            List<ParameterType> availableParameterTypes = LanguageRegistry.INSTANCE.getParameterTypes(
                    ElementType.ARTIFACT_PARAMETER_TYPE, artifactParameterType1.getName());
            assertNotNull(availableParameterTypes, "Missing artifact parameter type(s)");
            assertEquals(2, availableParameterTypes.size(), "Wrong number of artifact parameter types");
            assertEquals(artifactParameterType1, availableParameterTypes.get(0), "Wrong artifact parameter type");
            assertEquals(artifactParameterType2, availableParameterTypes.get(1), "Wrong artifact parameter type");
            
            // Test with fully-qualified name
            availableParameterTypes = LanguageRegistry.INSTANCE.getParameterTypes(
                    ElementType.ARTIFACT_PARAMETER_TYPE, artifactParameterType1.getFullyQualifiedName());
            assertNotNull(availableParameterTypes, "Missing artifact parameter type(s)");
            assertEquals(1, availableParameterTypes.size(), "Wrong number of artifact parameter types");
            assertEquals(artifactParameterType1, availableParameterTypes.get(0), "Wrong artifact parameter type");
            assertNotEquals(artifactParameterType2, availableParameterTypes.get(0), "Wrong artifact parameter type");
            
            // Test no other parameter types with that simple name
            assertNull(LanguageRegistry.INSTANCE.getParameterTypes(ElementType.FRAGMENT_PARAMETER_TYPE,
                    artifactParameterType1.getName()), "Unexpected fragment parameter type(s)");
            assertNull(LanguageRegistry.INSTANCE.getParameterTypes(ElementType.RESULT_PARAMETER_TYPE,
                    artifactParameterType1.getName()), "Unexpected result parameter type(s)");
            
            // Test no other parameter types with that fully-qualified name
            assertNull(LanguageRegistry.INSTANCE.getParameterTypes(ElementType.FRAGMENT_PARAMETER_TYPE,
                    artifactParameterType1.getFullyQualifiedName()), "Unexpected fragment parameter type(s)");
            assertNull(LanguageRegistry.INSTANCE.getParameterTypes(ElementType.RESULT_PARAMETER_TYPE,
                    artifactParameterType1.getFullyQualifiedName()), "Unexpected result parameter type(s)");
            
        } catch (LanguageElementException e) {
            System.out.println("[LanguageRegistryGetParameterTypesTests] Creating parameter type(s) failed");
            e.printStackTrace();
        }
    }
}
