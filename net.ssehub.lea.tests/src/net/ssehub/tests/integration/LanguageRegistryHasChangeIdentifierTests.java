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

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import net.ssehub.integration.ChangeIdentifier;
import net.ssehub.integration.ElementType;
import net.ssehub.integration.LanguageElement;
import net.ssehub.integration.LanguageElementException;
import net.ssehub.integration.LanguageRegistry;
import net.ssehub.integration.ParameterType;

/**
 * This class contains unit tests for the {@link LanguageRegistry#hasChangeIdentifier(String, boolean)} and the
 * {@link LanguageRegistry#hasChangeIdentifier(String, net.ssehub.integration.ParameterType[], boolean)} methods of the
 * {@link LanguageRegistry}.
 * 
 * @author Christian Kroeher
 *
 */
@RunWith(Parameterized.class)
public class LanguageRegistryHasChangeIdentifierTests extends AbstractLanguageRegistryTest {

    /**
     * The identifier of this class.
     */
    private static final String ID = "[LanguageRegistryHasChangeIdentifierTests]";
    
    /**
     * The expected results including the respective {@link LanguageElement} for testing.
     */
    private static final Object[][] EXPECTED_RESULTS = initializeExpectedResults();
    
    /**
     * The expectation of whether the {@link #currentElement} is available in the {@link LanguageRegistry} as a 
     * {@link ChangeIdentifier} (<code>true</code>) or not (<code>false</code>), if checking that existence relies on
     * the simple name only (non-unique).
     */
    private boolean expectedHasAnyChangeIdentifierBySimpleName;
    
    /**
     * The expectation of whether the {@link #currentElement} is available in the {@link LanguageRegistry} as a 
     * {@link ChangeIdentifier} (<code>true</code>) or not (<code>false</code>), if checking that existence relies on
     * the fully-qualified name only (non-unique).
     */
    private boolean expectedHasAnyChangeIdentifierByFullyQualifiedName;
    
    /**
     * The expectation of whether the {@link #currentElement} is available in the {@link LanguageRegistry} as a 
     * {@link ChangeIdentifier} (<code>true</code>) or not (<code>false</code>), if checking that existence relies on
     * the simple name only (unique).
     */
    private boolean expectedHasUniqueChangeIdentifierBySimpleName;
    
    /**
     * The expectation of whether the {@link #currentElement} is available in the {@link LanguageRegistry} as a 
     * {@link ChangeIdentifier} (<code>true</code>) or not (<code>false</code>), if checking that existence relies on
     * the fully-qualified name only (unique).
     */
    private boolean expectedHasUniqueChangeIdentifierByFullyQualifiedName;
    
    /**
     * The expectation of whether the {@link #currentElement} is available in the {@link LanguageRegistry} as a 
     * {@link ChangeIdentifier} (<code>true</code>) or not (<code>false</code>), if checking that existence relies on
     * the simple name and the assignable elements only (non-unique).
     */
    private boolean expectedHasAnyChangeIdentifierBySimpleNameAndAssignableElements;
    
    /**
     * The expectation of whether the {@link #currentElement} is available in the {@link LanguageRegistry} as a 
     * {@link ChangeIdentifier} (<code>true</code>) or not (<code>false</code>), if checking that existence relies on
     * the fully-qualified name and the assignable elements only (non-unique).
     */
    private boolean expectedHasAnyChangeIdentifierByFullyQualifiedNameAndAssignableElements;
    
    /**
     * The expectation of whether the {@link #currentElement} is available in the {@link LanguageRegistry} as a 
     * {@link ChangeIdentifier} (<code>true</code>) or not (<code>false</code>), if checking that existence relies on
     * the simple name and the assignable elements only (unique).
     */
    private boolean expectedHasUniqueChangeIdentifierBySimpleNameAndAssignableElements;
    
    /**
     * The expectation of whether the {@link #currentElement} is available in the {@link LanguageRegistry} as a 
     * {@link ChangeIdentifier} (<code>true</code>) or not (<code>false</code>), if checking that existence relies on
     * the fully-qualified name and the assignable elements only (unique).
     */
    private boolean expectedHasUniqueChangeIdentifierByFullyQualifiedNameAndAssignableElements;
    
    /**
     * Creates a new {@link LanguageRegistryHasChangeIdentifierTests} instance.
     * 
     * @param testElement the {@link LanguageElement} for testing
     * @param expectedAdditionOfLanguageElement the expectation of whether the given {@link LanguageElement} is
     *        successfully added to the {@link LanguageRegistry} (<code>true</code>) or not (<code>false</code>)
     * @param expectedHasAnyChangeIdentifierBySimpleName the value for 
     *        {@link #expectedHasAnyChangeIdentifierBySimpleName}
     * @param expectedHasAnyChangeIdentifierByFullyQualifiedName the value for
     *        {@link #expectedHasAnyChangeIdentifierByFullyQualifiedName}
     * @param expectedHasUniqueChangeIdentifierBySimpleName the value for 
     *        {@link #expectedHasUniqueChangeIdentifierBySimpleName}
     * @param expectedHasUniqueChangeIdentifierByFullyQualifiedName the value for 
     *        {@link #expectedHasUniqueChangeIdentifierByFullyQualifiedName}
     * @param expectedHasAnyChangeIdentifierBySimpleNameAndAssignableElements the value of 
     *        {@link #expectedHasAnyChangeIdentifierBySimpleNameAndAssignableElements}
     * @param expectedHasAnyChangeIdentifierByFullyQualifiedNameAndAssignableElements the value of 
     *        {@link #expectedHasAnyChangeIdentifierByFullyQualifiedNameAndAssignableElements}
     * @param expectedHasUniqueChangeIdentifierBySimpleNameAndAssignableElements the value of 
     *        {@link #expectedHasUniqueChangeIdentifierBySimpleNameAndAssignableElements}
     * @param expectedHasUniqueChangeIdentifierByFullyQualifiedNameAndAssignableElements the value of 
     *        {@link #expectedHasUniqueChangeIdentifierByFullyQualifiedNameAndAssignableElements}
     */
// CHECKSTYLE:OFF
    public LanguageRegistryHasChangeIdentifierTests(LanguageElement testElement,
            boolean expectedAdditionOfLanguageElement,
            boolean expectedHasAnyChangeIdentifierBySimpleName,
            boolean expectedHasAnyChangeIdentifierByFullyQualifiedName,
            boolean expectedHasUniqueChangeIdentifierBySimpleName,
            boolean expectedHasUniqueChangeIdentifierByFullyQualifiedName,
            boolean expectedHasAnyChangeIdentifierBySimpleNameAndAssignableElements,
            boolean expectedHasAnyChangeIdentifierByFullyQualifiedNameAndAssignableElements,
            boolean expectedHasUniqueChangeIdentifierBySimpleNameAndAssignableElements,
            boolean expectedHasUniqueChangeIdentifierByFullyQualifiedNameAndAssignableElements) {
        super(ID, testElement, expectedAdditionOfLanguageElement);
        this.expectedHasAnyChangeIdentifierBySimpleName = expectedHasAnyChangeIdentifierBySimpleName;
        this.expectedHasAnyChangeIdentifierByFullyQualifiedName = expectedHasAnyChangeIdentifierByFullyQualifiedName;
        this.expectedHasUniqueChangeIdentifierBySimpleName = expectedHasUniqueChangeIdentifierBySimpleName;
        this.expectedHasUniqueChangeIdentifierByFullyQualifiedName = 
                expectedHasUniqueChangeIdentifierByFullyQualifiedName;
        
        this.expectedHasAnyChangeIdentifierBySimpleNameAndAssignableElements = 
                expectedHasAnyChangeIdentifierBySimpleNameAndAssignableElements; 
        this.expectedHasAnyChangeIdentifierByFullyQualifiedNameAndAssignableElements = 
                expectedHasAnyChangeIdentifierByFullyQualifiedNameAndAssignableElements;
        this.expectedHasUniqueChangeIdentifierBySimpleNameAndAssignableElements = 
                expectedHasUniqueChangeIdentifierBySimpleNameAndAssignableElements;
        this.expectedHasUniqueChangeIdentifierByFullyQualifiedNameAndAssignableElements = 
                expectedHasUniqueChangeIdentifierByFullyQualifiedNameAndAssignableElements;
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
            expectedResults = new Object[4][10];
            expectedResults[0] = new Object[] {new ChangeIdentifier("ChangeIdentifier",
                    LanguageRegistryHasChangeIdentifierTests.class, SOURCE_PLUGIN),
                false,
                false, false, false, false,
                false, false, false, false};
            
            ChangeIdentifier completeChangeIdentifier1 = new ChangeIdentifier("ChangeIdentifier",
                    LanguageRegistryHasChangeIdentifierTests.class, SOURCE_PLUGIN);
            ParameterType[] assignableElements1 = {new ParameterType(ElementType.ARTIFACT_PARAMETER_TYPE,
                    "FileArtifact", LanguageRegistryHasChangeIdentifierTests.class, SOURCE_PLUGIN)};
            for (int i = 0; i < assignableElements1.length; i++) {
                if (!LanguageRegistry.INSTANCE.addParameterType(assignableElements1[i])) {
                    fail(ID + "Adding required assignable parameter type \"" 
                            + assignableElements1[i].getFullyQualifiedName() + "\" failed");
                }
            }
            completeChangeIdentifier1.finalize(assignableElements1);
            expectedResults[1] = new Object[] {completeChangeIdentifier1,
                true,
                true, true, true, true,
                true, true, true, true};
            
            ChangeIdentifier completeChangeIdentifier2 = new ChangeIdentifier("ChangeIdentifier", Object.class,
                    SOURCE_PLUGIN);
            completeChangeIdentifier2.finalize(assignableElements1);
            expectedResults[2] = new Object[] {completeChangeIdentifier2,
                true,
                true, true, false, true,
                true, true, false, true};
            
            ChangeIdentifier completeChangeIdentifier3 = new ChangeIdentifier("ChangeIdentifier",
                    LanguageRegistryHasChangeIdentifierTests.class, SOURCE_PLUGIN);
            ParameterType[] assignableElements2 = {new ParameterType(ElementType.FRAGMENT_PARAMETER_TYPE, "AFragment",
                    LanguageRegistryHasChangeIdentifierTests.class, SOURCE_PLUGIN)};
            for (int i = 0; i < assignableElements2.length; i++) {
                if (!LanguageRegistry.INSTANCE.addParameterType(assignableElements2[i])) {
                    fail(ID + "Adding required assignable parameter type \"" 
                            + assignableElements2[i].getFullyQualifiedName() + "\" failed");
                }
            }
            completeChangeIdentifier3.finalize(assignableElements2);
            expectedResults[3] = new Object[] {completeChangeIdentifier3,
                true,
                true, true, false, false,
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
     * Tests whether {@link LanguageRegistry#hasChangeIdentifier(String, boolean)} returns the same value as 
     * {@link #expectedHasAnyChangeIdentifierBySimpleName} using the simple name (non-unique) of the
     * {@link AbstractLanguageRegistryTest#currentElement}.
     */
    @Test
    public void testHasAnyChangeIdentifierBySimpleName() {
        assertEquals(expectedHasAnyChangeIdentifierBySimpleName, 
                languageRegistry.hasChangeIdentifier(currentElement.getName(), false), 
                "Wrong return value of LanguageRegistry#hasChangeIdentifier(String, boolean) using simple name"
                + " non-unique");
    }
    
    /**
     * Tests whether {@link LanguageRegistry#hasChangeIdentifier(String, boolean)} returns the same value as 
     * {@link #expectedHasAnyChangeIdentifierByFullyQualifiedName} using the fully-qualified name (non-unique) of the
     * {@link AbstractLanguageRegistryTest#currentElement}.
     */
    @Test
    public void testHasAnyChangeIdentifierByFullyQualifiedName() {
        assertEquals(expectedHasAnyChangeIdentifierByFullyQualifiedName, 
                languageRegistry.hasChangeIdentifier(currentElement.getFullyQualifiedName(), false), 
                "Wrong return value of LanguageRegistry#hasChangeIdentifier(String, boolean) using fully-qualified name"
                + " non-unique");
    }
    
    /**
     * Tests whether {@link LanguageRegistry#hasChangeIdentifier(String, boolean)} returns the same value as 
     * {@link #expectedHasUniqueChangeIdentifierBySimpleName} using the simple name (unique) of the
     * {@link AbstractLanguageRegistryTest#currentElement}.
     */
    @Test
    public void testHasUniqueChangeIdentifierBySimpleName() {
        assertEquals(expectedHasUniqueChangeIdentifierBySimpleName, 
                languageRegistry.hasChangeIdentifier(currentElement.getName(), true), 
                "Wrong return value of LanguageRegistry#hasChangeIdentifier(String, boolean) using simple name unique");
    }
    
    /**
     * Tests whether {@link LanguageRegistry#hasChangeIdentifier(String, boolean)} returns the same value as 
     * {@link #expectedHasUniqueChangeIdentifierByFullyQualifiedName} using the fully-qualified name (unique) of the
     * {@link AbstractLanguageRegistryTest#currentElement}.
     */
    @Test
    public void testHasUniqueChangeIdentifierByFullyQualifiedName() {
        assertEquals(expectedHasUniqueChangeIdentifierByFullyQualifiedName, 
                languageRegistry.hasChangeIdentifier(currentElement.getFullyQualifiedName(), true), 
                "Wrong return value of LanguageRegistry#hasChangeIdentifier(String, boolean) using fully-qualified name"
                + " unique");
    }
    
    /**
     * Tests whether {@link LanguageRegistry#hasChangeIdentifier(String, ParameterType[], boolean)} returns the same
     * value as {@link #expectedHasAnyChangeIdentifierBySimpleNameAndAssignableElements} using the simple name
     * (non-unique) and the assignable elements of the {@link AbstractLanguageRegistryTest#currentElement}.
     */
    @Test
    public void testHasAnyChangeIdentifierBySimpleNameAndAssignableElements() {
        ChangeIdentifier currentChangeIdentifier = (ChangeIdentifier) currentElement;
        assertEquals(expectedHasAnyChangeIdentifierBySimpleNameAndAssignableElements, 
                languageRegistry.hasChangeIdentifier(currentChangeIdentifier.getName(), 
                        currentChangeIdentifier.getAssignableElements(), false), 
                "Wrong return value of LanguageRegistry#hasChangeIdentifier(String, ParameterType[], boolean) using"
                + " simple name and assignable elements non-unique");
    }
    
    /**
     * Tests whether {@link LanguageRegistry#hasChangeIdentifier(String, ParameterType[], boolean)} returns the same
     * value as {@link #expectedHasAnyChangeIdentifierByFullyQualifiedNameAndAssignableElements} using the fully-
     * qualified name (non-unique) and the assignable elements of the
     * {@link AbstractLanguageRegistryTest#currentElement}.
     */
    @Test
    public void testHasAnyChangeIdentifierByFullyQualifiedNameAndAssignableElements() {
        ChangeIdentifier currentChangeIdentifier = (ChangeIdentifier) currentElement;
        assertEquals(expectedHasAnyChangeIdentifierByFullyQualifiedNameAndAssignableElements, 
                languageRegistry.hasChangeIdentifier(currentChangeIdentifier.getFullyQualifiedName(), 
                        currentChangeIdentifier.getAssignableElements(), false), 
                "Wrong return value of LanguageRegistry#hasChangeIdentifier(String, ParameterType[], boolean) using"
                + " fully-qualified name and assignable elements non-unique");
    }
    
    /**
     * Tests whether {@link LanguageRegistry#hasChangeIdentifier(String, ParameterType[], boolean)} returns the same
     * value as {@link #expectedHasUniqueChangeIdentifierBySimpleNameAndAssignableElements} using the simple name
     * (unique) and the assignable elements of the {@link AbstractLanguageRegistryTest#currentElement}.
     */
    @Test
    public void testHasUniqueChangeIdentifierBySimpleNameAndAssignableElements() {
        ChangeIdentifier currentChangeIdentifier = (ChangeIdentifier) currentElement;
        assertEquals(expectedHasUniqueChangeIdentifierBySimpleNameAndAssignableElements, 
                languageRegistry.hasChangeIdentifier(currentChangeIdentifier.getName(), 
                        currentChangeIdentifier.getAssignableElements(), true), 
                "Wrong return value of LanguageRegistry#hasChangeIdentifier(String, ParameterType[], boolean) using"
                + " simple name and assignable elements unique");
    }
    
    /**
     * Tests whether {@link LanguageRegistry#hasChangeIdentifier(String, ParameterType[], boolean)} returns the same
     * value as {@link #expectedHasUniqueChangeIdentifierByFullyQualifiedNameAndAssignableElements} using the fully-
     * qualified name (unique) and the assignable elements of the {@link AbstractLanguageRegistryTest#currentElement}.
     */
    @Test
    public void testHasUniqueChangeIdentifierByFullyQualifiedNameAndAssignableElements() {
        ChangeIdentifier currentChangeIdentifier = (ChangeIdentifier) currentElement;
        assertEquals(expectedHasUniqueChangeIdentifierByFullyQualifiedNameAndAssignableElements, 
                languageRegistry.hasChangeIdentifier(currentChangeIdentifier.getFullyQualifiedName(), 
                        currentChangeIdentifier.getAssignableElements(), true), 
                "Wrong return value of LanguageRegistry#hasChangeIdentifier(String, ParameterType[], boolean) using"
                + " fully-qualified name and assignable elements unique");
    }
    
}
