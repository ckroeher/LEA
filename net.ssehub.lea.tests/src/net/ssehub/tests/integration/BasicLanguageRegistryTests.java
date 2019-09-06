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

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import net.ssehub.integration.Call;
import net.ssehub.integration.ChangeIdentifier;
import net.ssehub.integration.ElementType;
import net.ssehub.integration.LanguageElement;
import net.ssehub.integration.LanguageElementException;
import net.ssehub.integration.LanguageRegistry;
import net.ssehub.integration.ParameterType;

/**
 * This class contains unit tests for the {@link LanguageRegistry}. Hence, these tests only check the core behavior of
 * that class. Tests regarding the loading of external plug-ins are defined in the 
 * {@link BasicLanguageElementProviderTests} and tests for the creation of language elements based on external classes
 * of such plug-ins are defined in the {@link BasicLanguageElementCreatorTests} as well as the respective
 * <code>*CreationTests.java</code> classes in this package.
 * 
 * @author Christian Kroeher
 *
 */
@RunWith(Parameterized.class)
public class BasicLanguageRegistryTests {
    
    /**
     * The identifier of this class.
     */
    private static final String ID = "[BasicLanguageRegistryTests]";
    
    /**
     * The {@link File} denoting the source plug-in of the {@link Class} from which a {@link LanguageElement} should be
     * created. This is just a dummy file as it has no impact on the actual creation, but is only used as a constructor
     * parameter.
     */
    private static final File SOURCE_PLUGIN = new File("./");
    
    /**
     * The expected results including the respective test {@link LanguageElement}.
     * 
     * @see {@link BasicLanguageRegistryTests#initializeExpectedResults()}
     */
    private static final Object[][] EXPECTED_RESULTS = initializeExpectedResults();
    
    /**
     * The reference of this class to the {@link LanguageRegistry}.
     */
    private LanguageRegistry languageRegistry = LanguageRegistry.INSTANCE;
    
    /**
     * The {@link LanguageElement} passed to the constructor of this class for testing.
     */
    private LanguageElement expectedElement;
    
    /**
     * The expectation of whether the {@link #expectedElement} is successfully added to the {@link LanguageRegistry}
     * (<code>true</code>) or not (<code>false</code>). 
     */
    private boolean expectedAdditionOfLanguageElement;
    
    /**
     * The expectation of whether the {@link #expectedElement} is available in the {@link LanguageRegistry}
     * (<code>true</code>) or not (<code>false</code>), if checking that existence relies on the name only (non-unique).
     */
    private boolean expectedHasAnyLanguageElementBySimpleName;
    
    /**
     * The expectation of whether the {@link #expectedElement} is available in the {@link LanguageRegistry}
     * (<code>true</code>) or not (<code>false</code>), if checking that existence relies on the fully-qualified name
     * only (non-unique).
     */
    private boolean expectedHasAnyLanguageElementByFullyQualifiedName;
    
    /**
     * The expectation of whether the {@link #expectedElement} is available in the {@link LanguageRegistry}
     * (<code>true</code>) or not (<code>false</code>), if checking that existence relies on the name only (unique).
     */
    private boolean expectedHasUniqueLanguageElementBySimpleName;
    
    /**
     * The {@link LanguageElement} actually registered in the {@link LanguageRegistry}.
     * 
     * @see #getRegisteredElement()
     */
    private LanguageElement actualElement;
    
    /**
     * The fact of whether the {@link #expectedElement} is successfully added to the {@link LanguageRegistry}
     * (<code>true</code>) or not (<code>false</code>). 
     */
    private boolean actualAdditionOfLanguageElement;
    
    /**
     * Creates a new {@link BasicLanguageRegistryTests} instance.
     * 
     * @param testElement the {@link LanguageElement} for testing
     * @param expectedAdditionOfLanguageElement the expectation of whether the given {@link LanguageElement} is
     *        successfully added to the {@link LanguageRegistry} (<code>true</code>) or not (<code>false</code>)
     * @param expectedHasAnyLanguageElementBySimpleName the expectation of whether the {@link #expectedElement} is
     *        available in the {@link LanguageRegistry} (<code>true</code>) or not (<code>false</code>), if checking
     *        that existence relies on the name only (non-unique)
     * @param expectedHasAnyLanguageElementByFullyQualifiedName the expectation of whether the {@link #expectedElement}
     *        is available in the {@link LanguageRegistry} (<code>true</code>) or not (<code>false</code>), if checking
     *        that existence relies on the fully-qualified name only (non-unique)
     * @param expectedHasUniqueLanguageElementBySimpleName the expectation of whether the {@link #expectedElement} is
     *        available in the {@link LanguageRegistry} (<code>true</code>) or not (<code>false</code>), if checking
     *        that existence relies on the name only (unique)
     */
    public BasicLanguageRegistryTests(LanguageElement testElement, boolean expectedAdditionOfLanguageElement, 
            boolean expectedHasAnyLanguageElementBySimpleName, 
            boolean expectedHasAnyLanguageElementByFullyQualifiedName, 
            boolean expectedHasUniqueLanguageElementBySimpleName) {
        this.expectedElement = testElement;
        this.expectedAdditionOfLanguageElement = expectedAdditionOfLanguageElement;
        this.expectedHasAnyLanguageElementBySimpleName = expectedHasAnyLanguageElementBySimpleName;
        this.expectedHasAnyLanguageElementByFullyQualifiedName = expectedHasAnyLanguageElementByFullyQualifiedName;
        this.expectedHasUniqueLanguageElementBySimpleName = expectedHasUniqueLanguageElementBySimpleName;
        
        actualAdditionOfLanguageElement = addToLanguageRegistry(testElement);
    }
    
    /**
     * Creates the {@link #EXPECTED_RESULTS}. As testing the {@link LanguageRegistry} requires the creation of 
     * {@link LanguageElement}s, which may throw an {@link LanguageElementException}, we have to initialize the values
     * in a separate method rather then directly initializing the array.
     * 
     * @return the {@link #EXPECTED_RESULTS}
     */
    private static Object[][] initializeExpectedResults() {
        Object[][] expectedResults = null;
        try {
            expectedResults = new Object[8][4];
            ParameterType artifactParameterType = new ParameterType(ElementType.ARTIFACT_PARAMETER_TYPE, "FileArtifact",
                    BasicLanguageRegistryTests.class, SOURCE_PLUGIN);
            expectedResults[0] = new Object[] {artifactParameterType, true, true, true, true};
            expectedResults[1] = new Object[] {new ParameterType(ElementType.ARTIFACT_PARAMETER_TYPE, "FileArtifact",
                    Object.class, SOURCE_PLUGIN), true, true, true, false};
            expectedResults[2] = new Object[] {new ParameterType(ElementType.FRAGMENT_PARAMETER_TYPE, "BlockFragment",
                    BasicLanguageRegistryTests.class, SOURCE_PLUGIN), true, true, true, true};
            expectedResults[3] = new Object[] {new ParameterType(ElementType.RESULT_PARAMETER_TYPE, "AnalysisResult",
                    BasicLanguageRegistryTests.class, SOURCE_PLUGIN), true, true, true, true};
            
            expectedResults[4] = new Object[] {new ChangeIdentifier("ChangeIdentifier",
                    BasicLanguageRegistryTests.class, SOURCE_PLUGIN), false, false, false, false};
            ChangeIdentifier completeChangeIdentifier = new ChangeIdentifier("ChangeIdentifier",
                    BasicLanguageRegistryTests.class, SOURCE_PLUGIN);
            completeChangeIdentifier.setAssignableElements(new ParameterType[] {artifactParameterType});
            expectedResults[5] = new Object[] {completeChangeIdentifier, true, true, true, true};
            
            expectedResults[6] = new Object[] {new Call(ElementType.OPERATION, "operate",
                    BasicLanguageRegistryTests.class.getDeclaredMethods()[0], BasicLanguageRegistryTests.class,
                    SOURCE_PLUGIN), false, false, false, false};
            Call completeCall = new Call(ElementType.OPERATION, "operate",
                    BasicLanguageRegistryTests.class.getDeclaredMethods()[0], BasicLanguageRegistryTests.class,
                    SOURCE_PLUGIN);
            completeCall.setReturnType(artifactParameterType);
            completeCall.setParameters(new ParameterType[] {artifactParameterType});
            expectedResults[7] = new Object[] {completeCall, true, false, false, false};
        } catch (LanguageElementException e) {
            System.err.println("Error while initializing expected results");
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
     * Adds the given {@link LanguageElement} to the {@link LanguageRegistry}.
     * 
     * @param languageElement the {@link LanguageElement} to add to the {@link LanguageRegistry}
     * @return <code>true</code>, if adding the given {@link LanguageElement} to the {@link LanguageRegistry} was
     *         successful; <code>false</code> otherwise
     */
    private boolean addToLanguageRegistry(LanguageElement languageElement) {
        boolean addedSuccessfully = false;
        if (languageElement instanceof ParameterType) {
            ParameterType parameterType = (ParameterType) languageElement;
            addedSuccessfully = languageRegistry.addParameterType(parameterType);
        } else if (languageElement instanceof ChangeIdentifier) {
            ChangeIdentifier changeIdentifier = (ChangeIdentifier) languageElement;
            addedSuccessfully = languageRegistry.addChangeIdentifier(changeIdentifier);
        } else if (languageElement instanceof Call) {
            Call call = (Call) languageElement;
            addedSuccessfully = languageRegistry.addCall(call);
        } else {
            System.out.println(ID + " Language element \"" + languageElement.getFullyQualifiedName() 
                    + " is of unknown instance");
        }
        return addedSuccessfully;
    }
    
    /**
     * Tests whether the {@link #expectedAdditionOfLanguageElement} matches the 
     * {@link #actualAdditionOfLanguageElement}. 
     */
    @Test
    public void testAdditionOfLanguageElement() {
        assertEquals(expectedAdditionOfLanguageElement, actualAdditionOfLanguageElement, 
                "Addition of language element not as expected");
    }
    
    /**
     * Tests whether the {@link #expectedElement} is available in the {@link LanguageRegistry}, if checking the 
     * existence relies on the simple name only (non-unique).
     */
    @Test
    public void testHasAnyLanguageElementBySimpleName() {
        boolean actualHasLanguageElement = false;
        if (expectedElement instanceof ParameterType) {
            actualHasLanguageElement = languageRegistry.hasParameterType(expectedElement.getName(), false);
        } else if (expectedElement instanceof ChangeIdentifier) {
            actualHasLanguageElement = languageRegistry.hasChangeIdentifier(expectedElement.getName(), false);
        } else if (expectedElement instanceof Call) {
            System.out.println(ID + " Skipping \"" + expectedElement.getFullyQualifiedName() + "\" as a(n) " 
                    + expectedElement.getElementType() + " cannot be found by name only");
        }
        assertEquals(expectedHasAnyLanguageElementBySimpleName, actualHasLanguageElement, 
                "Language registry does not have any lanuage element by name only as expected");
    }
    
    /**
     * Tests whether the {@link #expectedElement} is available in the {@link LanguageRegistry}, if checking the 
     * existence relies on the fully-qualified name only (non-unique).
     */
    @Test
    public void testHasAnyLanguageElementByFullyQualifiedName() {
        boolean actualHasLanguageElement = false;
        if (expectedElement instanceof ParameterType) {
            actualHasLanguageElement = languageRegistry.hasParameterType(expectedElement.getFullyQualifiedName(),
                    false);
        } else if (expectedElement instanceof ChangeIdentifier) {
            actualHasLanguageElement = languageRegistry.hasChangeIdentifier(expectedElement.getFullyQualifiedName(),
                    false);
        } else if (expectedElement instanceof Call) {
            System.out.println(ID + " Skipping \"" + expectedElement.getFullyQualifiedName() + "\" as a(n) " 
                    + expectedElement.getElementType() + " cannot be found by fully-qualified name only");
        }
        assertEquals(expectedHasAnyLanguageElementByFullyQualifiedName, actualHasLanguageElement, 
                "Language registry does not have any lanuage element by fully-qualified name only as expected");
    }
    
    /**
     * Tests whether the {@link #expectedElement} is available in the {@link LanguageRegistry}, if checking the 
     * existence relies on the simple name only (unique).
     */
    @Test
    public void testHasUniqueLanguageElementBySimpleName() {
        boolean actualHasLanguageElement = false;
        if (expectedElement instanceof ParameterType) {
            actualHasLanguageElement = languageRegistry.hasParameterType(expectedElement.getName(), true);
        } else if (expectedElement instanceof ChangeIdentifier) {
            actualHasLanguageElement = languageRegistry.hasChangeIdentifier(expectedElement.getName(), true);
        } else if (expectedElement instanceof Call) {
            System.out.println(ID + " Skipping \"" + expectedElement.getFullyQualifiedName() + "\" as a(n) " 
                    + expectedElement.getElementType() + " cannot be found by name only");
        }
        assertEquals(expectedHasUniqueLanguageElementBySimpleName, actualHasLanguageElement, 
                "Language registry does not have unique lanuage element by name only as expected");
    }
}
