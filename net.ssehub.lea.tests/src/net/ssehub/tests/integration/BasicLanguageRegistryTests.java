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
import java.util.ArrayList;
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
     * The {@link File} denoting the source plug-in of the {@link Class} from which a {@link LanguageElement} should be
     * created. This is just a dummy file as it has no impact on the actual creation, but is only used as a constructor
     * parameter.
     */
    private static final File SOURCE_PLUGIN = new File("./");
    
    /**
     * The expected results. Each element is again an array of exactly one specific {@link LanguageElement}.
     * 
     * @see {@link BasicLanguageRegistryTests#initializeExpectedResults()}
     */
    private static final Object[][] EXPECTED_RESULTS = initializeExpectedResults();
    
    /**
     * The expected number of {@link LanguageElement}s currently registered in {@link LanguageRegistry}. The initial
     * value is set in {@link #BasicLanguageRegistryTests(LanguageElement)} based on the value of
     * {@link LanguageRegistry#size()}.
     */
    private int expectedLanguageRegistrySize;
    
    /**
     * The {@link LanguageElement} passed to the constructor of this class for testing.
     */
    private LanguageElement testElement;
    
    /**
     * Creates a new {@link BasicLanguageRegistryTests} instance.
     * 
     * @param testElement the {@link LanguageElement} for testing
     */
    public BasicLanguageRegistryTests(LanguageElement testElement) {
        this.testElement = testElement;
        List<LanguageElement> languageElementList = new ArrayList<LanguageElement>();
        languageElementList.add(testElement);
        // Do not re-order the following two lines as the expected number is the number before the addition plus 1
        expectedLanguageRegistrySize = LanguageRegistry.INSTANCE.size() + 1; 
        LanguageRegistry.INSTANCE.addLanguageElements(languageElementList);
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
            expectedResults = new Object[][] {
                {new ParameterType(ElementType.ARTIFACT_PARAMETER_TYPE, "FileArtifact",
                        BasicLanguageRegistryTests.class, SOURCE_PLUGIN)},
                {new ParameterType(ElementType.FRAGMENT_PARAMETER_TYPE, "BlockFragment",
                        BasicLanguageRegistryTests.class, SOURCE_PLUGIN)},
                {new ParameterType(ElementType.RESULT_PARAMETER_TYPE, "AnalysisResult",
                        BasicLanguageRegistryTests.class, SOURCE_PLUGIN)},
                {new ChangeIdentifier("ChangeIdentifier", new String[] {"FileArtifact"},
                        BasicLanguageRegistryTests.class, SOURCE_PLUGIN)},
                {new Call(ElementType.OPERATION, "file", "File", new String[] {},
                        BasicLanguageRegistryTests.class, SOURCE_PLUGIN)},
                {new Call(ElementType.EXTRACTOR_CALL, "extract", "Block", new String[] {"File"},
                        BasicLanguageRegistryTests.class, SOURCE_PLUGIN)},
                {new Call(ElementType.ANALYSIS_CALL, "analyze", "DeadBlock", new String[] {"Block"},
                        BasicLanguageRegistryTests.class, SOURCE_PLUGIN)}
            };            
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
     * Tests whether the {@link #testLanguageElementsCounter} equals {@link LanguageRegistry#size()}. This ensures that
     * not more and not less elements are registered than those added as part of the unit tests in this class.
     */
    @Test
    public void testLanguageRegistrySize() {
        assertEquals(expectedLanguageRegistrySize, LanguageRegistry.INSTANCE.size(),
                "Wrong number of language elements in language registry");
    }
    
    /**
     * Tests whether the {@link LanguageRegistry} contains a {@link LanguageElement} with the same name the 
     * {@link #testElement}.
     */
    @Test
    public void testRegisteredLanguageElementName() {
        LanguageElement registeredElement = LanguageRegistry.INSTANCE.getLanguageElement(testElement.getName());
        assertEquals(testElement, registeredElement, "Language registry does not contain element with name \"" 
                + testElement.getName() + "\"");
    }
    
    /**
     * Tests whether the {@link LanguageRegistry} contains a {@link LanguageElement} of the same class as the 
     * {@link #testElement}.
     */
    @Test
    public void testRegisteredLanguageElementClass() {
        LanguageElement registeredElement = LanguageRegistry.INSTANCE.getLanguageElement(testElement.getName());
        try {            
            assertEquals(testElement.getClass(), registeredElement.getClass(), "Wrong language element class");
        } catch (NullPointerException e) {
            assertEquals(null, e, "Language registry does not contain element with name \"" + testElement.getName() 
                    + "\"");
        }
    }

    /**
     * Tests whether the {@link LanguageRegistry} contains a {@link LanguageElement} with the same {@link ElementType}
     * as the {@link #testElement}.
     */
    @Test
    public void testRegisteredLanguageElementElementType() {
        LanguageElement registeredElement = LanguageRegistry.INSTANCE.getLanguageElement(testElement.getName());
        try {            
            assertEquals(testElement.getElementType(), registeredElement.getElementType(),
                    "Wrong language element element type");
        } catch (NullPointerException e) {
            assertEquals(null, e, "Language registry does not contain element with name \"" + testElement.getName() 
                    + "\"");
        }
    }
    
    /**
     * Tests whether the {@link LanguageRegistry} contains a {@link LanguageElement} with the same source {@link Class}
     * as the {@link #testElement}.
     */
    @Test
    public void testRegisteredLanguageElementSourceClass() {
        LanguageElement registeredElement = LanguageRegistry.INSTANCE.getLanguageElement(testElement.getName());
        try {            
            assertEquals(testElement.getSourceClass(), registeredElement.getSourceClass(),
                    "Wrong language element source class");
        } catch (NullPointerException e) {
            assertEquals(null, e, "Language registry does not contain element with name \"" + testElement.getName() 
                    + "\"");
        }
    }
    
    /**
     * Tests whether the {@link LanguageRegistry} contains a {@link LanguageElement} with the same source plug-in (a 
     * {@link File}) as the {@link #testElement}.
     */
    @Test
    public void testRegisteredLanguageElementSourcePlugin() {
        LanguageElement registeredElement = LanguageRegistry.INSTANCE.getLanguageElement(testElement.getName());
        try {            
            assertEquals(testElement.getSourcePlugin(), registeredElement.getSourcePlugin(),
                    "Wrong language element source plug-in");
        } catch (NullPointerException e) {
            assertEquals(null, e, "Language registry does not contain element with name \"" + testElement.getName() 
                    + "\"");
        }
    }
}
