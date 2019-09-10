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

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import net.ssehub.integration.ElementType;
import net.ssehub.integration.LanguageElement;
import net.ssehub.integration.LanguageElementException;
import net.ssehub.integration.LanguageRegistry;
import net.ssehub.integration.ParameterType;

/**
 * This class contains unit tests for the {@link LanguageRegistry#hasParameterType(String, boolean)} and the
 * {@link LanguageRegistry#hasParameterType(net.ssehub.integration.ElementType, String, boolean)} methods of the
 * {@link LanguageRegistry}.
 * 
 * @author Christian Kroeher
 *
 */
@RunWith(Parameterized.class)
public class LanguageRegistryHasParameterTypeTests extends AbstractLanguageRegistryTest {
    
    /**
     * The identifier of this class.
     */
    private static final String ID = "[LanguageRegistryHasParameterTypeTests]";
    
    /**
     * The expected results including the respective {@link LanguageElement} for testing.
     */
    private static final Object[][] EXPECTED_RESULTS = initializeExpectedResults();
    
    /**
     * The expectation of whether the {@link #currentElement} is available in the {@link LanguageRegistry} as a 
     * {@link ParameterType} (<code>true</code>) or not (<code>false</code>), if checking that existence relies on the
     * simple name only (non-unique).
     */
    private boolean expectedHasAnyParameterTypeBySimpleName;
    
    /**
     * The expectation of whether the {@link #currentElement} is available in the {@link LanguageRegistry} as a 
     * {@link ParameterType} (<code>true</code>) or not (<code>false</code>), if checking that existence relies on the
     * fully-qualified name only (non-unique).
     */
    private boolean expectedHasAnyParameterTypeByFullyQualifiedName;
    
    /**
     * The expectation of whether the {@link #currentElement} is available in the {@link LanguageRegistry} as a 
     * {@link ParameterType} (<code>true</code>) or not (<code>false</code>), if checking that existence relies on the
     * simple name only (unique).
     */
    private boolean expectedHasUniqueParameterTypeBySimpleName;
    
    /**
     * The expectation of whether the {@link #currentElement} is available in the {@link LanguageRegistry} as a 
     * {@link ParameterType} (<code>true</code>) or not (<code>false</code>), if checking that existence relies on the
     * fully-qualified name only (unique).
     */
    private boolean expectedHasUniqueParameterTypeByFullyQualifiedName;
    
    /**
     * The expectation of whether the {@link #currentElement} is available in the {@link LanguageRegistry} as a 
     * {@link ParameterType} of the type {@link ElementType#ARTIFACT_PARAMETER_TYPE} (<code>true</code>) or not
     * (<code>false</code>), if checking that existence relies on the simple name only (non-unique).
     */
    private boolean expectedHasAnyArtifactParameterTypeBySimpleName;
    
    /**
     * The expectation of whether the {@link #currentElement} is available in the {@link LanguageRegistry} as a 
     * {@link ParameterType} of the type {@link ElementType#ARTIFACT_PARAMETER_TYPE} (<code>true</code>) or not
     * (<code>false</code>), if checking that existence relies on the fully-qualified name only (non-unique).
     */
    private boolean expectedHasAnyArtifactParameterTypeByFullyQualifiedName;
    
    /**
     * The expectation of whether the {@link #currentElement} is available in the {@link LanguageRegistry} as a 
     * {@link ParameterType} of the type {@link ElementType#ARTIFACT_PARAMETER_TYPE} (<code>true</code>) or not 
     * (<code>false</code>), if checking that existence relies on the simple name only (unique).
     */
    private boolean expectedHasUniqueArtifactParameterTypeBySimpleName;
    
    /**
     * The expectation of whether the {@link #currentElement} is available in the {@link LanguageRegistry} as a 
     * {@link ParameterType} of the type {@link ElementType#ARTIFACT_PARAMETER_TYPE} (<code>true</code>) or not
     * (<code>false</code>), if checking that existence relies on the fully-qualified name only (unique).
     */
    private boolean expectedHasUniqueArtifactParameterTypeByFullyQualifiedName;
    
    /**
     * The expectation of whether the {@link #currentElement} is available in the {@link LanguageRegistry} as a 
     * {@link ParameterType} of the type {@link ElementType#FRAGMENT_PARAMETER_TYPE} (<code>true</code>) or not
     * (<code>false</code>), if checking that existence relies on the simple name only (non-unique).
     */
    private boolean expectedHasAnyFragmentParameterTypeBySimpleName;
    
    /**
     * The expectation of whether the {@link #currentElement} is available in the {@link LanguageRegistry} as a 
     * {@link ParameterType} of the type {@link ElementType#FRAGMENT_PARAMETER_TYPE} (<code>true</code>) or not
     * (<code>false</code>), if checking that existence relies on the fully-qualified name only (non-unique).
     */
    private boolean expectedHasAnyFragmentParameterTypeByFullyQualifiedName;
    
    /**
     * The expectation of whether the {@link #currentElement} is available in the {@link LanguageRegistry} as a 
     * {@link ParameterType} of the type {@link ElementType#FRAGMENT_PARAMETER_TYPE} (<code>true</code>) or not 
     * (<code>false</code>), if checking that existence relies on the simple name only (unique).
     */
    private boolean expectedHasUniqueFragmentParameterTypeBySimpleName;
    
    /**
     * The expectation of whether the {@link #currentElement} is available in the {@link LanguageRegistry} as a 
     * {@link ParameterType} of the type {@link ElementType#FRAGMENT_PARAMETER_TYPE} (<code>true</code>) or not
     * (<code>false</code>), if checking that existence relies on the fully-qualified name only (unique).
     */
    private boolean expectedHasUniqueFragmentParameterTypeByFullyQualifiedName;
    
    /**
     * The expectation of whether the {@link #currentElement} is available in the {@link LanguageRegistry} as a 
     * {@link ParameterType} of the type {@link ElementType#RESULT_PARAMETER_TYPE} (<code>true</code>) or not
     * (<code>false</code>), if checking that existence relies on the simple name only (non-unique).
     */
    private boolean expectedHasAnyResultParameterTypeBySimpleName;
    
    /**
     * The expectation of whether the {@link #currentElement} is available in the {@link LanguageRegistry} as a 
     * {@link ParameterType} of the type {@link ElementType#RESULT_PARAMETER_TYPE} (<code>true</code>) or not
     * (<code>false</code>), if checking that existence relies on the fully-qualified name only (non-unique).
     */
    private boolean expectedHasAnyResultParameterTypeByFullyQualifiedName;
    
    /**
     * The expectation of whether the {@link #currentElement} is available in the {@link LanguageRegistry} as a 
     * {@link ParameterType} of the type {@link ElementType#RESULT_PARAMETER_TYPE} (<code>true</code>) or not 
     * (<code>false</code>), if checking that existence relies on the simple name only (unique).
     */
    private boolean expectedHasUniqueResultParameterTypeBySimpleName;
    
    /**
     * The expectation of whether the {@link #currentElement} is available in the {@link LanguageRegistry} as a 
     * {@link ParameterType} of the type {@link ElementType#RESULT_PARAMETER_TYPE} (<code>true</code>) or not
     * (<code>false</code>), if checking that existence relies on the fully-qualified name only (unique).
     */
    private boolean expectedHasUniqueResultParameterTypeByFullyQualifiedName;

    /**
     * Creates a new {@link LanguageRegistryHasParameterTypeTests} instance.
     * 
     * @param testElement the {@link LanguageElement} for testing
     * @param expectedAdditionOfLanguageElement the expectation of whether the given {@link LanguageElement} is
     *        successfully added to the {@link LanguageRegistry} (<code>true</code>) or not (<code>false</code>)
     * @param expectedHasAnyParameterTypeBySimpleName the value for {@link #expectedHasAnyParameterTypeBySimpleName}
     * @param expectedHasAnyParameterTypeByFullyQualifiedName the value for 
     *        {@link #expectedHasAnyParameterTypeByFullyQualifiedName}
     * @param expectedHasUniqueParameterTypeBySimpleName the value for
     *        {@link #expectedHasUniqueParameterTypeBySimpleName}
     * @param expectedHasUniqueParameterTypeByFullyQualifiedName the value for
     *        {@link #expectedHasUniqueParameterTypeByFullyQualifiedName}
     * @param expectedHasAnyArtifactParameterTypeBySimpleName the value for 
     *        {@link #expectedHasAnyArtifactParameterTypeBySimpleName}
     * @param expectedHasAnyArtifactParameterTypeByFullyQualifiedName the value for 
     *        {@link #expectedHasAnyArtifactParameterTypeByFullyQualifiedName}
     * @param expectedHasUniqueArtifactParameterTypeBySimpleName the value for 
     *        {@link #expectedHasUniqueArtifactParameterTypeBySimpleName}
     * @param expectedHasUniqueArtifactParameterTypeByFullyQualifiedName the value for 
     *        {@link #expectedHasUniqueArtifactParameterTypeByFullyQualifiedName}
     * @param expectedHasAnyFragmentParameterTypeBySimpleName the value for 
     *        {@link #expectedHasAnyFragmentParameterTypeBySimpleName}
     * @param expectedHasAnyFragmentParameterTypeByFullyQualifiedName the value for 
     *        {@link #expectedHasAnyFragmentParameterTypeByFullyQualifiedName}
     * @param expectedHasUniqueFragmentParameterTypeBySimpleName the value for 
     *        {@link #expectedHasUniqueFragmentParameterTypeBySimpleName}
     * @param expectedHasUniqueFragmentParameterTypeByFullyQualifiedName the value for 
     *        {@link #expectedHasUniqueFragmentParameterTypeByFullyQualifiedName}
     * @param expectedHasAnyResultParameterTypeBySimpleName the value for 
     *        {@link #expectedHasAnyResultParameterTypeBySimpleName}
     * @param expectedHasAnyResultParameterTypeByFullyQualifiedName the value for 
     *        {@link #expectedHasAnyResultParameterTypeByFullyQualifiedName}
     * @param expectedHasUniqueResultParameterTypeBySimpleName the value for 
     *        {@link #expectedHasUniqueResultParameterTypeBySimpleName}
     * @param expectedHasUniqueResultParameterTypeByFullyQualifiedName the value for 
     *        {@link #expectedHasUniqueResultParameterTypeByFullyQualifiedName}
     */
// CHECKSTYLE:OFF
    public LanguageRegistryHasParameterTypeTests(LanguageElement testElement,
            boolean expectedAdditionOfLanguageElement,
            boolean expectedHasAnyParameterTypeBySimpleName, 
            boolean expectedHasAnyParameterTypeByFullyQualifiedName, 
            boolean expectedHasUniqueParameterTypeBySimpleName,
            boolean expectedHasUniqueParameterTypeByFullyQualifiedName,
            boolean expectedHasAnyArtifactParameterTypeBySimpleName, 
            boolean expectedHasAnyArtifactParameterTypeByFullyQualifiedName, 
            boolean expectedHasUniqueArtifactParameterTypeBySimpleName,
            boolean expectedHasUniqueArtifactParameterTypeByFullyQualifiedName,
            boolean expectedHasAnyFragmentParameterTypeBySimpleName, 
            boolean expectedHasAnyFragmentParameterTypeByFullyQualifiedName, 
            boolean expectedHasUniqueFragmentParameterTypeBySimpleName,
            boolean expectedHasUniqueFragmentParameterTypeByFullyQualifiedName,
            boolean expectedHasAnyResultParameterTypeBySimpleName, 
            boolean expectedHasAnyResultParameterTypeByFullyQualifiedName, 
            boolean expectedHasUniqueResultParameterTypeBySimpleName,
            boolean expectedHasUniqueResultParameterTypeByFullyQualifiedName) {
        super(ID, testElement, expectedAdditionOfLanguageElement);
        this.expectedHasAnyParameterTypeBySimpleName = expectedHasAnyParameterTypeBySimpleName;
        this.expectedHasAnyParameterTypeByFullyQualifiedName = expectedHasAnyParameterTypeByFullyQualifiedName;
        this.expectedHasUniqueParameterTypeBySimpleName = expectedHasUniqueParameterTypeBySimpleName;
        this.expectedHasUniqueParameterTypeByFullyQualifiedName = expectedHasUniqueParameterTypeByFullyQualifiedName;
        
        this.expectedHasAnyArtifactParameterTypeBySimpleName = expectedHasAnyArtifactParameterTypeBySimpleName;
        this.expectedHasAnyArtifactParameterTypeByFullyQualifiedName = 
                expectedHasAnyArtifactParameterTypeByFullyQualifiedName;
        this.expectedHasUniqueArtifactParameterTypeBySimpleName = expectedHasUniqueArtifactParameterTypeBySimpleName;
        this.expectedHasUniqueArtifactParameterTypeByFullyQualifiedName = 
                expectedHasUniqueArtifactParameterTypeByFullyQualifiedName;
        
        this.expectedHasAnyFragmentParameterTypeBySimpleName = expectedHasAnyFragmentParameterTypeBySimpleName;
        this.expectedHasAnyFragmentParameterTypeByFullyQualifiedName = 
                expectedHasAnyFragmentParameterTypeByFullyQualifiedName;
        this.expectedHasUniqueFragmentParameterTypeBySimpleName = expectedHasUniqueFragmentParameterTypeBySimpleName;
        this.expectedHasUniqueFragmentParameterTypeByFullyQualifiedName = 
                expectedHasUniqueFragmentParameterTypeByFullyQualifiedName;
        
        this.expectedHasAnyResultParameterTypeBySimpleName = expectedHasAnyResultParameterTypeBySimpleName;
        this.expectedHasAnyResultParameterTypeByFullyQualifiedName = 
                expectedHasAnyResultParameterTypeByFullyQualifiedName;
        this.expectedHasUniqueResultParameterTypeBySimpleName = expectedHasUniqueResultParameterTypeBySimpleName;
        this.expectedHasUniqueResultParameterTypeByFullyQualifiedName = 
                expectedHasUniqueResultParameterTypeByFullyQualifiedName;
    }
// CHECKSTYLE:ON   
    /**
     * Creates the {@link #EXPECTED_RESULTS}. As testing the {@link LanguageRegistry} requires the creation of 
     * {@link LanguageElement}s, which may throw an {@link LanguageElementException}, we have to initialize the values
     * in a separate method rather then directly initializing the array.
     * 
     * @return the value for the {@link #EXPECTED_RESULTS}
     */
    private static Object[][] initializeExpectedResults() {
        Object[][] expectedResults = null;
        try {
            expectedResults = new Object[5][4];
            expectedResults[0] = new Object[] {new ParameterType(ElementType.ARTIFACT_PARAMETER_TYPE, "FileArtifact",
                    LanguageRegistryHasParameterTypeTests.class, SOURCE_PLUGIN),
                true,
                true, true, true, true,
                true, true, true, true,
                false, false, false, false,
                false, false, false, false};
            expectedResults[1] = new Object[] {new ParameterType(ElementType.ARTIFACT_PARAMETER_TYPE, "FileArtifact",
                    Object.class, SOURCE_PLUGIN),
                true,
                true, true, false, true,
                true, true, false, true,
                false, false, false, false,
                false, false, false, false};
            expectedResults[2] = new Object[] {new ParameterType(ElementType.FRAGMENT_PARAMETER_TYPE, "BlockFragment",
                    LanguageRegistryHasParameterTypeTests.class, SOURCE_PLUGIN),
                true,
                true, true, true, true,
                false, false, false, false,
                true, true, true, true,
                false, false, false, false};
            expectedResults[3] = new Object[] {new ParameterType(ElementType.RESULT_PARAMETER_TYPE, "AnalysisResult",
                    LanguageRegistryHasParameterTypeTests.class, SOURCE_PLUGIN),
                true,
                true, true, true, true,
                false, false, false, false,
                false, false, false, false,
                true, true, true, true};
            expectedResults[4] = new Object[] {new ParameterType(ElementType.RESULT_PARAMETER_TYPE, "FileArtifact",
                    LanguageRegistryHasParameterTypeTests.class, SOURCE_PLUGIN),
                true,
                true, true, false, false,
                true, true, false, true, /*Actually, we find one of the two first artifact parameter type above*/
                false, false, false, false,
                true, true, true, true};
        } catch (LanguageElementException e) {
            System.err.println(ID + " Error while initializing expected results");
            e.printStackTrace();
        }
        return expectedResults;
    }
    
    /**
     * Returns the expected results as parameters for the tests defined in this class.
     * 
     * @return the {@link #EXPECTED_RESULTS} as an object-array list
     */
    @Parameters
    public static List<Object[]> getTestData() {
        return Arrays.asList(EXPECTED_RESULTS);
    }
    
    /**
     * Tests whether {@link LanguageRegistry#hasParameterType(String, boolean)} returns the same value as 
     * {@link #expectedHasAnyParameterTypeBySimpleName} using the simple name (non-unique) of the
     * {@link AbstractLanguageRegistryTest#currentElement}.
     */
    @Test
    public void testHasAnyParameterTypeBySimpleName() {
        assertEquals(expectedHasAnyParameterTypeBySimpleName, 
                languageRegistry.hasParameterType(currentElement.getName(), false), 
                "Wrong return value of LanguageRegistry#hasParameterType(String, boolean) using simple name"
                + " non-unique");
    }
    
    /**
     * Tests whether {@link LanguageRegistry#hasParameterType(String, boolean)} returns the same value as 
     * {@link #expectedHasAnyParameterTypeByFullyQualifiedName} using the fully-qualified name (non-unique) of the
     * {@link AbstractLanguageRegistryTest#currentElement}.
     */
    @Test
    public void testHasAnyParameterTypeByFullyQualifiedName() {
        assertEquals(expectedHasAnyParameterTypeByFullyQualifiedName, 
                languageRegistry.hasParameterType(currentElement.getFullyQualifiedName(), false), 
                "Wrong return value of LanguageRegistry#hasParameterType(String, boolean) using fully-qualified name"
                + " non-unique");
    }
    
    /**
     * Tests whether {@link LanguageRegistry#hasParameterType(String, boolean)} returns the same value as 
     * {@link #expectedHasUniqueParameterTypeBySimpleName} using the simple name (unique) of the
     * {@link AbstractLanguageRegistryTest#currentElement}.
     */
    @Test
    public void testHasUniqueParameterTypeBySimpleName() {
        assertEquals(expectedHasUniqueParameterTypeBySimpleName, 
                languageRegistry.hasParameterType(currentElement.getName(), true), 
                "Wrong return value of LanguageRegistry#hasParameterType(String, boolean) using simple name unique");
    }
    
    /**
     * Tests whether {@link LanguageRegistry#hasParameterType(String, boolean)} returns the same value as 
     * {@link #expectedHasUniqueParameterTypeByFullyQualifiedName} using the fully-qualified name (unique) of the
     * {@link AbstractLanguageRegistryTest#currentElement}.
     */
    @Test
    public void testHasUniqueParameterTypeByFullyQualifiedName() {
        assertEquals(expectedHasUniqueParameterTypeByFullyQualifiedName, 
                languageRegistry.hasParameterType(currentElement.getFullyQualifiedName(), true), 
                "Wrong return value of LanguageRegistry#hasParameterType(String, boolean) using fully-qualified name"
                + " unique");
    }

    /**
     * Tests whether {@link LanguageRegistry#hasParameterType(ElementType, String, boolean)} returns the same value as 
     * {@link expectedHasAnyArtifactParameterTypeBySimpleName} using {@link ElementType#ARTIFACT_PARAMETER_TYPE} and the
     * simple name (non-unique) of the {@link AbstractLanguageRegistryTest#currentElement}.
     */
    @Test
    public void testHasAnyArtifactParameterTypeBySimpleName() {
        assertEquals(expectedHasAnyArtifactParameterTypeBySimpleName, 
                languageRegistry.hasParameterType(ElementType.ARTIFACT_PARAMETER_TYPE, currentElement.getName(), false),
                "Wrong return value of LanguageRegistry#hasParameterType(ElementType, String, boolean) using artifact"
                + " and simple name non-unique");
    }
    
    /**
     * Tests whether {@link LanguageRegistry#hasParameterType(ElementType, String, boolean)} returns the same value as 
     * {@link expectedHasAnyArtifactParameterTypeByFullyQualifiedName} using {@link ElementType#ARTIFACT_PARAMETER_TYPE}
     * and the fully-qualified name (non-unique) of the {@link AbstractLanguageRegistryTest#currentElement}.
     */
    @Test
    public void testHasAnyArtifactParameterTypeByFullyQualifiedName() {
        assertEquals(expectedHasAnyArtifactParameterTypeByFullyQualifiedName, 
                languageRegistry.hasParameterType(ElementType.ARTIFACT_PARAMETER_TYPE,
                        currentElement.getFullyQualifiedName(), false),
                "Wrong return value of LanguageRegistry#hasParameterType(ElementType, String, boolean) using artifact"
                + " and fully-qualified name non-unique");
    }
    
    /**
     * Tests whether {@link LanguageRegistry#hasParameterType(ElementType, String, boolean)} returns the same value as 
     * {@link expectedHasUniqueArtifactParameterTypeBySimpleName} using {@link ElementType#ARTIFACT_PARAMETER_TYPE} and
     * the simple name (unique) of the {@link AbstractLanguageRegistryTest#currentElement}.
     */
    @Test
    public void testHasUniqueArtifactParameterTypeBySimpleName() {
        assertEquals(expectedHasUniqueArtifactParameterTypeBySimpleName, 
                languageRegistry.hasParameterType(ElementType.ARTIFACT_PARAMETER_TYPE, currentElement.getName(), true),
                "Wrong return value of LanguageRegistry#hasParameterType(ElementType, String, boolean) using artifact"
                + " and simple name unique");
    }
    
    /**
     * Tests whether {@link LanguageRegistry#hasParameterType(ElementType, String, boolean)} returns the same value as 
     * {@link expectedHasUniqueArtifactParameterTypeByFullyQualifiedName} using
     * {@link ElementType#ARTIFACT_PARAMETER_TYPE} and the fully-qualified name (unique) of the
     * {@link AbstractLanguageRegistryTest#currentElement}.
     */
    @Test
    public void testHasUniqueArtifactParameterTypeByFullyQualifiedName() {
        assertEquals(expectedHasUniqueArtifactParameterTypeByFullyQualifiedName, 
                languageRegistry.hasParameterType(ElementType.ARTIFACT_PARAMETER_TYPE,
                        currentElement.getFullyQualifiedName(), true),
                "Wrong return value of LanguageRegistry#hasParameterType(ElementType, String, boolean) using artifact"
                + " and fully-qualified name unique");
    }
    
    /**
     * Tests whether {@link LanguageRegistry#hasParameterType(ElementType, String, boolean)} returns the same value as 
     * {@link expectedHasAnyFragmentParameterTypeBySimpleName} using {@link ElementType#FRAGMENT_PARAMETER_TYPE} and the
     * simple name (non-unique) of the {@link AbstractLanguageRegistryTest#currentElement}.
     */
    @Test
    public void testHasAnyFragmentParameterTypeBySimpleName() {
        assertEquals(expectedHasAnyFragmentParameterTypeBySimpleName, 
                languageRegistry.hasParameterType(ElementType.FRAGMENT_PARAMETER_TYPE, currentElement.getName(), false),
                "Wrong return value of LanguageRegistry#hasParameterType(ElementType, String, boolean) using fragment"
                + " and simple name non-unique");
    }
    
    /**
     * Tests whether {@link LanguageRegistry#hasParameterType(ElementType, String, boolean)} returns the same value as 
     * {@link expectedHasAnyFragmentParameterTypeByFullyQualifiedName} using {@link ElementType#FRAGMENT_PARAMETER_TYPE}
     * and the fully-qualified name (non-unique) of the {@link AbstractLanguageRegistryTest#currentElement}.
     */
    @Test
    public void testHasAnyFragmentParameterTypeByFullyQualifiedName() {
        assertEquals(expectedHasAnyFragmentParameterTypeByFullyQualifiedName, 
                languageRegistry.hasParameterType(ElementType.FRAGMENT_PARAMETER_TYPE,
                        currentElement.getFullyQualifiedName(), false),
                "Wrong return value of LanguageRegistry#hasParameterType(ElementType, String, boolean) using fragment"
                + " and fully-qualified name non-unique");
    }
    
    /**
     * Tests whether {@link LanguageRegistry#hasParameterType(ElementType, String, boolean)} returns the same value as 
     * {@link expectedHasUniqueFragmentParameterTypeBySimpleName} using {@link ElementType#FRAGMENT_PARAMETER_TYPE} and
     * the simple name (unique) of the {@link AbstractLanguageRegistryTest#currentElement}.
     */
    @Test
    public void testHasUniqueFragmentParameterTypeBySimpleName() {
        assertEquals(expectedHasUniqueFragmentParameterTypeBySimpleName, 
                languageRegistry.hasParameterType(ElementType.FRAGMENT_PARAMETER_TYPE, currentElement.getName(), true),
                "Wrong return value of LanguageRegistry#hasParameterType(ElementType, String, boolean) using fragment"
                + " and simple name unique");
    }
    
    /**
     * Tests whether {@link LanguageRegistry#hasParameterType(ElementType, String, boolean)} returns the same value as 
     * {@link expectedHasUniqueFragmentParameterTypeByFullyQualifiedName} using
     * {@link ElementType#FRAGMENT_PARAMETER_TYPE} and the fully-qualified name (unique) of the
     * {@link AbstractLanguageRegistryTest#currentElement}.
     */
    @Test
    public void testHasUniqueFragmentParameterTypeByFullyQualifiedName() {
        assertEquals(expectedHasUniqueFragmentParameterTypeByFullyQualifiedName, 
                languageRegistry.hasParameterType(ElementType.FRAGMENT_PARAMETER_TYPE,
                        currentElement.getFullyQualifiedName(), true),
                "Wrong return value of LanguageRegistry#hasParameterType(ElementType, String, boolean) using fragment"
                + " and fully-qualified name unique");
    }
    
    /**
     * Tests whether {@link LanguageRegistry#hasParameterType(ElementType, String, boolean)} returns the same value as 
     * {@link expectedHasAnyResultParameterTypeBySimpleName} using {@link ElementType#RESULT_PARAMETER_TYPE} and the
     * simple name (non-unique) of the {@link AbstractLanguageRegistryTest#currentElement}.
     */
    @Test
    public void testHasAnyResultParameterTypeBySimpleName() {
        assertEquals(expectedHasAnyResultParameterTypeBySimpleName, 
                languageRegistry.hasParameterType(ElementType.RESULT_PARAMETER_TYPE, currentElement.getName(), false),
                "Wrong return value of LanguageRegistry#hasParameterType(ElementType, String, boolean) using result"
                + " and simple name non-unique");
    }
    
    /**
     * Tests whether {@link LanguageRegistry#hasParameterType(ElementType, String, boolean)} returns the same value as 
     * {@link expectedHasAnyResultParameterTypeByFullyQualifiedName} using {@link ElementType#RESULT_PARAMETER_TYPE}
     * and the fully-qualified name (non-unique) of the {@link AbstractLanguageRegistryTest#currentElement}.
     */
    @Test
    public void testHasAnyResultParameterTypeByFullyQualifiedName() {
        assertEquals(expectedHasAnyResultParameterTypeByFullyQualifiedName, 
                languageRegistry.hasParameterType(ElementType.RESULT_PARAMETER_TYPE,
                        currentElement.getFullyQualifiedName(), false),
                "Wrong return value of LanguageRegistry#hasParameterType(ElementType, String, boolean) using result"
                + " and fully-qualified name non-unique");
    }
    
    /**
     * Tests whether {@link LanguageRegistry#hasParameterType(ElementType, String, boolean)} returns the same value as 
     * {@link expectedHasUniqueResultParameterTypeBySimpleName} using {@link ElementType#RESULT_PARAMETER_TYPE} and
     * the simple name (unique) of the {@link AbstractLanguageRegistryTest#currentElement}.
     */
    @Test
    public void testHasUniqueResultParameterTypeBySimpleName() {
        assertEquals(expectedHasUniqueResultParameterTypeBySimpleName, 
                languageRegistry.hasParameterType(ElementType.RESULT_PARAMETER_TYPE, currentElement.getName(), true),
                "Wrong return value of LanguageRegistry#hasParameterType(ElementType, String, boolean) using result"
                + " and simple name unique");
    }
    
    /**
     * Tests whether {@link LanguageRegistry#hasParameterType(ElementType, String, boolean)} returns the same value as 
     * {@link expectedHasUniqueResultParameterTypeByFullyQualifiedName} using
     * {@link ElementType#RESULT_PARAMETER_TYPE} and the fully-qualified name (unique) of the
     * {@link AbstractLanguageRegistryTest#currentElement}.
     */
    @Test
    public void testHasUniqueResultParameterTypeByFullyQualifiedName() {
        assertEquals(expectedHasUniqueResultParameterTypeByFullyQualifiedName, 
                languageRegistry.hasParameterType(ElementType.RESULT_PARAMETER_TYPE,
                        currentElement.getFullyQualifiedName(), true),
                "Wrong return value of LanguageRegistry#hasParameterType(ElementType, String, boolean) using result"
                + " and fully-qualified name unique");
    }

}
