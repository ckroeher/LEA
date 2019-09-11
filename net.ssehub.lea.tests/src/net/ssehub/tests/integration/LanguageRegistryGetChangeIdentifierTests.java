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
 * This class contains unit tests for the {@link LanguageRegistry#getChangeIdentifier(String)} method of the
 * {@link LanguageRegistry}.
 * 
 * @author Christian Kroeher
 *
 */
@RunWith(Parameterized.class)
public class LanguageRegistryGetChangeIdentifierTests extends AbstractLanguageRegistryTest {

    /**
     * The identifier of this class.
     */
    private static final String ID = "[LanguageRegistryGetParamterTypeTests]";

    /**
     * The expected results including the respective {@link LanguageElement} for testing.
     */
    private static final Object[][] EXPECTED_RESULTS = initializeExpectedResults();
    
    /**
     * The expected {@link ChangeIdentifier} returned by {@link LanguageRegistry#getChangeIdentifier(String)}, if the
     * simple name of the {@link AbstractLanguageRegistryTest#currentElement} is used.
     */
    private ChangeIdentifier expectedChangeIdentifierBySimpleName;
    
    /**
     * The expected {@link ChangeIdentifier} returned by {@link LanguageRegistry#getChangeIdentifier(String)}, if the
     * fully-qualified name of the {@link AbstractLanguageRegistryTest#currentElement} is used.
     */
    private ChangeIdentifier expectedChangeIdentifierByFullyQualifiedName;
    
    /**
     * Creates a new {@link LanguageRegistryGetChangeIdentifierTests} instance.
     * 
     * @param testElement the {@link LanguageElement} for testing
     * @param expectedAdditionOfLanguageElement the expectation of whether the given {@link LanguageElement} is
     *        successfully added to the {@link LanguageRegistry} (<code>true</code>) or not (<code>false</code>)
     * @param expectedChangeIdentifierBySimpleName the value for {@link #expectedChangeIdentifierBySimpleName}
     * @param expectedChangeIdentifierByFullyQualifiedName the value for 
     *        {@link #expectedChangeIdentifierByFullyQualifiedName}
     */
    public LanguageRegistryGetChangeIdentifierTests(LanguageElement testElement,
            boolean expectedAdditionOfLanguageElement,
            ChangeIdentifier expectedChangeIdentifierBySimpleName,
            ChangeIdentifier expectedChangeIdentifierByFullyQualifiedName) {
        super(ID, testElement, expectedAdditionOfLanguageElement);
        this.expectedChangeIdentifierBySimpleName = expectedChangeIdentifierBySimpleName;
        this.expectedChangeIdentifierByFullyQualifiedName = expectedChangeIdentifierByFullyQualifiedName;
    }
 
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
            expectedResults = new Object[4][4];
            expectedResults[0] = new Object[] {new ChangeIdentifier("ChangeIdentifier",
                    LanguageRegistryGetChangeIdentifierTests.class, SOURCE_PLUGIN),
                false, null, null};
            
            ChangeIdentifier completeChangeIdentifier1 = new ChangeIdentifier("ChangeIdentifier",
                    LanguageRegistryGetChangeIdentifierTests.class, SOURCE_PLUGIN);
            ParameterType[] assignableElements1 = {new ParameterType(ElementType.ARTIFACT_PARAMETER_TYPE,
                    "FileArtifact", LanguageRegistryGetChangeIdentifierTests.class, SOURCE_PLUGIN)};
            for (int i = 0; i < assignableElements1.length; i++) {
                if (!LanguageRegistry.INSTANCE.addParameterType(assignableElements1[i])) {
                    fail(ID + "Adding required assignable parameter type \"" 
                            + assignableElements1[i].getFullyQualifiedName() + "\" failed");
                }
            }
            completeChangeIdentifier1.setAssignableElements(assignableElements1);
            expectedResults[1] = new Object[] {completeChangeIdentifier1,
                true, completeChangeIdentifier1, completeChangeIdentifier1};
            
            ChangeIdentifier completeChangeIdentifier2 = new ChangeIdentifier("ChangeIdentifier", String.class,
                    SOURCE_PLUGIN);
            completeChangeIdentifier2.setAssignableElements(assignableElements1);
            expectedResults[2] = new Object[] {completeChangeIdentifier2,
                true, null, completeChangeIdentifier2};
            
            ChangeIdentifier completeChangeIdentifier3 = new ChangeIdentifier("ChangeIdentifier",
                    LanguageRegistryGetChangeIdentifierTests.class, SOURCE_PLUGIN);
            ParameterType[] assignableElements2 = {new ParameterType(ElementType.FRAGMENT_PARAMETER_TYPE, "AFragment",
                    LanguageRegistryGetChangeIdentifierTests.class, SOURCE_PLUGIN)};
            for (int i = 0; i < assignableElements2.length; i++) {
                if (!LanguageRegistry.INSTANCE.addParameterType(assignableElements2[i])) {
                    fail(ID + "Adding required assignable parameter type \"" 
                            + assignableElements2[i].getFullyQualifiedName() + "\" failed");
                }
            }
            completeChangeIdentifier3.setAssignableElements(assignableElements2);
            expectedResults[3] = new Object[] {completeChangeIdentifier3,
                true, null, null};
            
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
     * Tests whether {@link LanguageRegistry#getChangeIdentifier(String)} returns the 
     * {@link #expectedChangeIdentifierBySimpleName} using the simple name of the
     * {@link AbstractLanguageRegistryTest#currentElement}.
     */
    @Test
    public void testGetChangeIdentifierBySimpleName() {
        assertEquals(expectedChangeIdentifierBySimpleName, 
                languageRegistry.getChangeIdentifier(currentElement.getName()),
                "Wrong change identifier using simple name");
    }
    
    /**
     * Tests whether {@link LanguageRegistry#getChangeIdentifier(String)} returns the 
     * {@link #expectedChangeIdentifierByFullyQualifiedName} using the fully-qualified name of the
     * {@link AbstractLanguageRegistryTest#currentElement}.
     */
    @Test
    public void testGetChangeIdentifierByFullyQualifiedName() {
        assertEquals(expectedChangeIdentifierByFullyQualifiedName, 
                languageRegistry.getChangeIdentifier(currentElement.getFullyQualifiedName()),
                "Wrong change identifier using fully-qualified name");
    }
    
}
