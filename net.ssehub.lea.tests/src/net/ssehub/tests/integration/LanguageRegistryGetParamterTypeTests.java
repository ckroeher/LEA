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
 * This class contains unit tests for the {@link LanguageRegistry#getParameterType(String)} and the
 * {@link LanguageRegistry#getParameterType(net.ssehub.integration.ElementType, String)} methods of the
 * {@link LanguageRegistry}.
 * 
 * @author Christian Kroeher
 *
 */
@RunWith(Parameterized.class)
public class LanguageRegistryGetParamterTypeTests extends AbstractLanguageRegistryTest {
    
    /**
     * The identifier of this class.
     */
    private static final String ID = "[LanguageRegistryGetParamterTypeTests]";

    /**
     * The expected results including the respective {@link LanguageElement} for testing.
     */
    private static final Object[][] EXPECTED_RESULTS = initializeExpectedResults();
    
    /**
     * The expectation of whether the {@link #currentElement} is available in the {@link LanguageRegistry} as a 
     * {@link ParameterType} (<code>true</code>) or not (<code>false</code>), if returning it relies on the simple name
     * only.
     */
    private boolean expectedHasParameterTypeBySimpleName;
    
    /**
     * The expectation of whether the {@link #currentElement} is available in the {@link LanguageRegistry} as a 
     * {@link ParameterType} (<code>true</code>) or not (<code>false</code>), if returning it relies on the fully-
     * qualified name only.
     */
    private boolean expectedHasParameterTypeByFullyQualifiedName;
    
    /**
     * The expectation of whether the {@link #currentElement} is available in the {@link LanguageRegistry} as a 
     * {@link ParameterType} of the type {@link ElementType#ARTIFACT_PARAMETER_TYPE} (<code>true</code>) or not
     * (<code>false</code>), if returning it relies on the simple name only.
     */
    private boolean expectedHasArtifactParameterTypeBySimpleName;
    
    /**
     * The expectation of whether the {@link #currentElement} is available in the {@link LanguageRegistry} as a 
     * {@link ParameterType} of the type {@link ElementType#ARTIFACT_PARAMETER_TYPE} (<code>true</code>) or not
     * (<code>false</code>), if returning it relies on the fully-qualified name only.
     */
    private boolean expectedHasArtifactParameterTypeByFullyQualifiedName;
    
    /**
     * The expectation of whether the {@link #currentElement} is available in the {@link LanguageRegistry} as a 
     * {@link ParameterType} of the type {@link ElementType#FRAGMENT_PARAMETER_TYPE} (<code>true</code>) or not
     * (<code>false</code>), if returning it relies on the simple name only.
     */
    private boolean expectedHasFragmentParameterTypeBySimpleName;
    
    /**
     * The expectation of whether the {@link #currentElement} is available in the {@link LanguageRegistry} as a 
     * {@link ParameterType} of the type {@link ElementType#FRAGMENT_PARAMETER_TYPE} (<code>true</code>) or not
     * (<code>false</code>), if returning it relies on the fully-qualified name only.
     */
    private boolean expectedHasFragmentParameterTypeByFullyQualifiedName;
    
    /**
     * The expectation of whether the {@link #currentElement} is available in the {@link LanguageRegistry} as a 
     * {@link ParameterType} of the type {@link ElementType#RESULT_PARAMETER_TYPE} (<code>true</code>) or not
     * (<code>false</code>), if returning it relies on the simple name only.
     */
    private boolean expectedHasResultParameterTypeBySimpleName;
    
    /**
     * The expectation of whether the {@link #currentElement} is available in the {@link LanguageRegistry} as a 
     * {@link ParameterType} of the type {@link ElementType#RESULT_PARAMETER_TYPE} (<code>true</code>) or not
     * (<code>false</code>), if returning it relies on the fully-qualified name only.
     */
    private boolean expectedHasResultParameterTypeByFullyQualifiedName;
    
    /**
     * Creates a new {@link LanguageRegistryGetParamterTypeTests} instance.
     * 
     * @param testElement the {@link LanguageElement} for testing
     * @param expectedAdditionOfLanguageElement the expectation of whether the given {@link LanguageElement} is
     *        successfully added to the {@link LanguageRegistry} (<code>true</code>) or not (<code>false</code>)
     * @param expectedHasParameterTypeBySimpleName the value for {@link #expectedHasParameterTypeBySimpleName}
     * @param expectedHasParameterTypeByFullyQualifiedName the value for
     *        {@link #expectedHasParameterTypeByFullyQualifiedName}
     * @param expectedHasArtifactParameterTypeBySimpleName the value for
     *        {@link #expectedHasArtifactParameterTypeBySimpleName}
     * @param expectedHasArtifactParameterTypeByFullyQualifiedName the value for
     *        {@link #expectedHasArtifactParameterTypeByFullyQualifiedName}
     * @param expectedHasFragmentParameterTypeBySimpleName the value for
     *        {@link #expectedHasFragmentParameterTypeBySimpleName}
     * @param expectedHasFragmentParameterTypeByFullyQualifiedName the value for
     *        {@link #expectedHasFragmentParameterTypeByFullyQualifiedName}
     * @param expectedHasResultParameterTypeBySimpleName the value for
     *        {@link #expectedHasResultParameterTypeBySimpleName}
     * @param expectedHasResultParameterTypeByFullyQualifiedName the value for
     *        {@link #expectedHasResultParameterTypeByFullyQualifiedName}
     */
// CHECKSTYLE:OFF
    public LanguageRegistryGetParamterTypeTests(LanguageElement testElement,
            boolean expectedAdditionOfLanguageElement,
            boolean expectedHasParameterTypeBySimpleName,
            boolean expectedHasParameterTypeByFullyQualifiedName,
            boolean expectedHasArtifactParameterTypeBySimpleName,
            boolean expectedHasArtifactParameterTypeByFullyQualifiedName,
            boolean expectedHasFragmentParameterTypeBySimpleName,
            boolean expectedHasFragmentParameterTypeByFullyQualifiedName,
            boolean expectedHasResultParameterTypeBySimpleName,
            boolean expectedHasResultParameterTypeByFullyQualifiedName) {
        super(ID, testElement, expectedAdditionOfLanguageElement);
        this.expectedHasParameterTypeBySimpleName = expectedHasParameterTypeBySimpleName; 
        this.expectedHasParameterTypeByFullyQualifiedName = expectedHasParameterTypeByFullyQualifiedName;
        this.expectedHasArtifactParameterTypeBySimpleName = expectedHasArtifactParameterTypeBySimpleName;
        this.expectedHasArtifactParameterTypeByFullyQualifiedName = 
                expectedHasArtifactParameterTypeByFullyQualifiedName;
        this.expectedHasFragmentParameterTypeBySimpleName = expectedHasFragmentParameterTypeBySimpleName;
        this.expectedHasFragmentParameterTypeByFullyQualifiedName = 
                expectedHasFragmentParameterTypeByFullyQualifiedName;
        this.expectedHasResultParameterTypeBySimpleName = expectedHasResultParameterTypeBySimpleName;
        this.expectedHasResultParameterTypeByFullyQualifiedName = expectedHasResultParameterTypeByFullyQualifiedName;
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
            expectedResults = new Object[5][9];
            expectedResults[0] = new Object[] {new ParameterType(ElementType.ARTIFACT_PARAMETER_TYPE, "FA",
                    LanguageRegistryGetParamterTypeTests.class, SOURCE_PLUGIN),
                true,
                true, true,
                true, true,
                false, false,
                false, false};
            expectedResults[1] = new Object[] {new ParameterType(ElementType.ARTIFACT_PARAMETER_TYPE, "FA",
                    String.class, SOURCE_PLUGIN),
                true,
                false, true,
                false, true,
                false, false,
                false, false};
            expectedResults[2] = new Object[] {new ParameterType(ElementType.FRAGMENT_PARAMETER_TYPE, "BF",
                    LanguageRegistryGetParamterTypeTests.class, SOURCE_PLUGIN),
                true,
                true, true,
                false, false,
                true, true,
                false, false};
            expectedResults[3] = new Object[] {new ParameterType(ElementType.RESULT_PARAMETER_TYPE, "AR",
                    LanguageRegistryGetParamterTypeTests.class, SOURCE_PLUGIN),
                true,
                true, true,
                false, false,
                false, false,
                true, true};
            expectedResults[4] = new Object[] {new ParameterType(ElementType.RESULT_PARAMETER_TYPE, "FA",
                    LanguageRegistryGetParamterTypeTests.class, SOURCE_PLUGIN),
                true,
                false, false,
                false, false,
                false, false,
                true, true};
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
     * Tests whether {@link LanguageRegistry#getParameterType(String)} returns the same value as 
     * {@link #expectedHasParameterTypeBySimpleName} using the simple name of the
     * {@link AbstractLanguageRegistryTest#currentElement}.
     */
    @Test
    public void testGetParameterTypeBySimpleName() {
        assertEquals(expectedHasParameterTypeBySimpleName, 
                currentElement.equals(languageRegistry.getParameterType(currentElement.getName())), 
                "Wrong return value of LanguageRegistry#getParameterType(String) using simple name");
    }
    
    /**
     * Tests whether {@link LanguageRegistry#getParameterType(String)} returns the same value as 
     * {@link #expectedHasParameterTypeByFullyQualifiedName} using the fully-qualified name of the
     * {@link AbstractLanguageRegistryTest#currentElement}.
     */
    @Test
    public void testGetParameterTypeByFullyQualifiedName() {
        assertEquals(expectedHasParameterTypeByFullyQualifiedName, 
                currentElement.equals(languageRegistry.getParameterType(currentElement.getFullyQualifiedName())), 
                "Wrong return value of LanguageRegistry#getParameterType(String) using fully-qualified name");
    }
    
    /**
     * Tests whether {@link LanguageRegistry#getParameterType(ElementType, String)} returns the same value as 
     * {@link #expectedHasArtifactParameterTypeBySimpleName} using {@link ElementType#ARTIFACT_PARAMETER_TYPE} and the
     * simple name of the {@link AbstractLanguageRegistryTest#currentElement}.
     */
    @Test
    public void testGetArtifactParameterTypeBySimpleName() {
        assertEquals(expectedHasArtifactParameterTypeBySimpleName, 
                currentElement.equals(languageRegistry.getParameterType(ElementType.ARTIFACT_PARAMETER_TYPE,
                        currentElement.getName())), 
                "Wrong return value of LanguageRegistry#getParameterType(String) using artifact and simple name");
    }
    
    /**
     * Tests whether {@link LanguageRegistry#getParameterType(ElementType, String)} returns the same value as 
     * {@link #expectedHasArtifactParameterTypeByFullyQualifiedName} using {@link ElementType#ARTIFACT_PARAMETER_TYPE}
     * and the fully-qualified name of the {@link AbstractLanguageRegistryTest#currentElement}.
     */
    @Test
    public void testGetArtifactParameterTypeByFullyQualifiedName() {
        assertEquals(expectedHasArtifactParameterTypeByFullyQualifiedName, 
                currentElement.equals(languageRegistry.getParameterType(ElementType.ARTIFACT_PARAMETER_TYPE, 
                        currentElement.getFullyQualifiedName())), 
                "Wrong return value of LanguageRegistry#getParameterType(String) using artifact and fully-qualified"
                + " name");
    }
    
    /**
     * Tests whether {@link LanguageRegistry#getParameterType(ElementType, String)} returns the same value as 
     * {@link #expectedHasFragmentParameterTypeBySimpleName} using {@link ElementType#FRAGMENT_PARAMETER_TYPE} and the
     * simple name of the {@link AbstractLanguageRegistryTest#currentElement}.
     */
    @Test
    public void testGetFragmentParameterTypeBySimpleName() {
        assertEquals(expectedHasFragmentParameterTypeBySimpleName, 
                currentElement.equals(languageRegistry.getParameterType(ElementType.FRAGMENT_PARAMETER_TYPE,
                        currentElement.getName())), 
                "Wrong return value of LanguageRegistry#getParameterType(String) using fragment and simple name");
    }
    
    /**
     * Tests whether {@link LanguageRegistry#getParameterType(ElementType, String)} returns the same value as 
     * {@link #expectedHasFragmentParameterTypeByFullyQualifiedName} using {@link ElementType#FRAGMENT_PARAMETER_TYPE}
     * and the fully-qualified name of the {@link AbstractLanguageRegistryTest#currentElement}.
     */
    @Test
    public void testGetFragmentParameterTypeByFullyQualifiedName() {
        assertEquals(expectedHasFragmentParameterTypeByFullyQualifiedName, 
                currentElement.equals(languageRegistry.getParameterType(ElementType.FRAGMENT_PARAMETER_TYPE, 
                        currentElement.getFullyQualifiedName())), 
                "Wrong return value of LanguageRegistry#getParameterType(String) using fragment and fully-qualified"
                + " name");
    }    
    
    /**
     * Tests whether {@link LanguageRegistry#getParameterType(ElementType, String)} returns the same value as 
     * {@link #expectedHasResultParameterTypeBySimpleName} using {@link ElementType#RESULT_PARAMETER_TYPE} and the
     * simple name of the {@link AbstractLanguageRegistryTest#currentElement}.
     */
    @Test
    public void testGetResultParameterTypeBySimpleName() {
        assertEquals(expectedHasResultParameterTypeBySimpleName, 
                currentElement.equals(languageRegistry.getParameterType(ElementType.RESULT_PARAMETER_TYPE,
                        currentElement.getName())), 
                "Wrong return value of LanguageRegistry#getParameterType(String) using result and simple name");
    }
    
    /**
     * Tests whether {@link LanguageRegistry#getParameterType(ElementType, String)} returns the same value as 
     * {@link #expectedHasResultParameterTypeByFullyQualifiedName} using {@link ElementType#RESULT_PARAMETER_TYPE}
     * and the fully-qualified name of the {@link AbstractLanguageRegistryTest#currentElement}.
     */
    @Test
    public void testGetResultParameterTypeByFullyQualifiedName() {
        assertEquals(expectedHasResultParameterTypeByFullyQualifiedName, 
                currentElement.equals(languageRegistry.getParameterType(ElementType.RESULT_PARAMETER_TYPE, 
                        currentElement.getFullyQualifiedName())), 
                "Wrong return value of LanguageRegistry#getParameterType(String) using result and fully-qualified"
                + " name");
    }

}
