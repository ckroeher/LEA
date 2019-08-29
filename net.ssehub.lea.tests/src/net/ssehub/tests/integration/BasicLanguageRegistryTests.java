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
     * The {@link LanguageElement} passed to the constructor of this class for testing.
     */
    private LanguageElement expectedElement;
    
    /**
     * The {@link LanguageElement} actually registered in the {@link LanguageRegistry}.
     * 
     * @see #getRegisteredElement()
     */
    private LanguageElement actualElement;
    
    /**
     * Creates a new {@link BasicLanguageRegistryTests} instance.
     * 
     * @param testElement the {@link LanguageElement} for testing
     */
    public BasicLanguageRegistryTests(LanguageElement testElement) {
        this.expectedElement = testElement;
        List<LanguageElement> languageElementList = new ArrayList<LanguageElement>();
        languageElementList.add(testElement);
        LanguageRegistry.INSTANCE.addLanguageElements(languageElementList);
        actualElement = getRegisteredElement();
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
                {new Call(ElementType.OPERATION, "file", "FileArtifact", new String[] {}, 
                        BasicLanguageRegistryTests.class.getDeclaredMethod("initializeExpectedResults"),
                        BasicLanguageRegistryTests.class, SOURCE_PLUGIN)},
                {new Call("memberOperation", "FileArtifact", new String[] {}, "FileArtifact", 
                        BasicLanguageRegistryTests.class.getDeclaredMethod("initializeExpectedResults"),
                        BasicLanguageRegistryTests.class, SOURCE_PLUGIN)},
                {new Call(ElementType.EXTRACTOR_CALL, "extract", "BlockFragment", new String[] {"FileArtifact"},
                        BasicLanguageRegistryTests.class.getDeclaredMethod("initializeExpectedResults"),
                        BasicLanguageRegistryTests.class, SOURCE_PLUGIN)},
                {new Call(ElementType.ANALYSIS_CALL, "analyze", "AnalysisResult", new String[] {"BlockFragment"},
                        BasicLanguageRegistryTests.class.getDeclaredMethod("initializeExpectedResults"),
                        BasicLanguageRegistryTests.class, SOURCE_PLUGIN)}
            };            
        } catch (LanguageElementException | NoSuchMethodException e) {
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
     * Tests whether the name of the {@link #expectedElement} equals the name of the {@link #actualElement}.
     */
    @Test
    public void testRegisteredLanguageElementName() {
        try {            
            assertEquals(expectedElement.getName(), actualElement.getName(),
                    "Language registry does not contain element with name \"" + expectedElement.getName() + "\"");
        } catch (NullPointerException e) {
            assertEquals(null, e, "Language registry does not contain element with name \"" + expectedElement.getName() 
                    + "\"");
        } 
    }
    
    /**
     * Tests whether the class of the {@link #expectedElement} equals the class of the {@link #actualElement}.
     */
    @Test
    public void testRegisteredLanguageElementClass() {
        try {            
            assertEquals(expectedElement.getClass(), actualElement.getClass(), "Wrong language element class");
        } catch (NullPointerException e) {
            assertEquals(null, e, "Language registry does not contain element with name \"" + expectedElement.getName() 
                    + "\"");
        }
    }

    /**
     * Tests whether the {@link ElementType} of the {@link #expectedElement} equals the {@link ElementType} of the 
     * {@link #actualElement}.
     */
    @Test
    public void testRegisteredLanguageElementElementType() {
        try {            
            assertEquals(expectedElement.getElementType(), actualElement.getElementType(),
                    "Wrong language element element type");
        } catch (NullPointerException e) {
            assertEquals(null, e, "Language registry does not contain element with name \"" + expectedElement.getName() 
                    + "\"");
        }
    }
    
    /**
     * Tests whether the source class of the {@link #expectedElement} equals the source class of the 
     * {@link #actualElement}.
     */
    @Test
    public void testRegisteredLanguageElementSourceClass() {
        try {            
            assertEquals(expectedElement.getSourceClass(), actualElement.getSourceClass(),
                    "Wrong language element source class");
        } catch (NullPointerException e) {
            assertEquals(null, e, "Language registry does not contain element with name \"" + expectedElement.getName() 
                    + "\"");
        }
    }
    
    /**
     * Tests whether the source plug-in of the {@link #expectedElement} equals the source plug-in of the 
     * {@link #actualElement}.
     */
    @Test
    public void testRegisteredLanguageElementSourcePlugin() {
        try {            
            assertEquals(expectedElement.getSourcePlugin(), actualElement.getSourcePlugin(),
                    "Wrong language element source plug-in");
        } catch (NullPointerException e) {
            assertEquals(null, e, "Language registry does not contain element with name \"" + expectedElement.getName() 
                    + "\"");
        }
    }
    
    /**
     * Searches in the {@link LanguageRegistry} for an {@link LanguageElement}, which has the same {@link ElementType},
     * name, source class, and source plug-in as the current {@link #expectedElement}, and returns it.
     * 
     * @return the {@link LanguageElement} in the {@link LanguageRegistry} with the same {@link ElementType}, name,
     *         source class, and source plug-in as the current {@link #expectedElement} or <code>null</code>, if no such
     *         element is registered
     */
    private LanguageElement getRegisteredElement() {
        LanguageElement registeredElement;
        switch(expectedElement.getElementType()) {
        case ARTIFACT_PARAMETER_TYPE:
            registeredElement = getRegisteredParameterType(
                    LanguageRegistry.INSTANCE.getArtifactParameterTypes(expectedElement.getName()));
            break;
        case FRAGMENT_PARAMETER_TYPE:
            registeredElement = getRegisteredParameterType(
                    LanguageRegistry.INSTANCE.getFragmentParameterTypes(expectedElement.getName()));
            break;
        case RESULT_PARAMETER_TYPE:
            registeredElement = getRegisteredParameterType(
                    LanguageRegistry.INSTANCE.getResultParameterTypes(expectedElement.getName()));
            break;
        case CHANGE_IDENTIFIER:
            registeredElement = getRegisteredChangeIdentifier(
                    LanguageRegistry.INSTANCE.getChangeIdentifiers(expectedElement.getName()));
            break;
        case OPERATION:
            Call expectedCallElement = (Call) expectedElement;
            if (expectedCallElement.isMemberOperation()) {
                registeredElement = getRegisteredCall(
                        LanguageRegistry.INSTANCE.getMemberOperations(expectedCallElement.getParentParameterType()));
            } else {                
                registeredElement = getRegisteredCall(
                        LanguageRegistry.INSTANCE.getOperations(expectedElement.getName()));
            }
            break;
        case EXTRACTOR_CALL:
            registeredElement = getRegisteredCall(
                    LanguageRegistry.INSTANCE.getExtractorCalls(expectedElement.getName()));
            break;
        case ANALYSIS_CALL:
            registeredElement = getRegisteredCall(
                    LanguageRegistry.INSTANCE.getAnalysisCall(expectedElement.getName()));
            break;
        default:
            registeredElement = null; // Should never be reached
            break;
        }
        return registeredElement;
    }
    
    /**
     * Searches in the given {@link List} of {@link ParameterType}s for an {@link LanguageElement}, which has the same 
     * source class and source plug-in as the current {@link #expectedElement}, and returns it.
     * 
     * @param searchList the {@link List} of {@link ParameterType}s to search in
     * @return the {@link LanguageElement} with the same source class and source plug-in as the current
     *         {@link #expectedElement} or <code>null</code>, if no such element is registered
     */
    private LanguageElement getRegisteredParameterType(List<ParameterType> searchList) {
        LanguageElement registeredElement = null;
        if (searchList != null) {
            int registeredParametersCounter = 0;
            ParameterType registeredParameter;
            while (registeredElement == null && registeredParametersCounter < searchList.size()) {
                registeredParameter = searchList.get(registeredParametersCounter);
                if (registeredParameter.getSourceClass() == expectedElement.getSourceClass() 
                        && registeredParameter.getSourcePlugin().getAbsolutePath().equals(
                                expectedElement.getSourcePlugin().getAbsolutePath())) {
                    registeredElement = registeredParameter;
                }
                registeredParametersCounter++;
            }
        }
        return registeredElement;
    }
    
    /**
     * Searches in the given {@link List} of {@link ChangeIdentifier}s for an {@link LanguageElement}, which has the
     * same source class and source plug-in as the current {@link #expectedElement}, and returns it.
     * 
     * @param searchList the {@link List} of {@link ChangeIdentifier}s to search in
     * @return the {@link LanguageElement} with the same source class and source plug-in as the current
     *         {@link #expectedElement} or <code>null</code>, if no such element is registered
     */
    private LanguageElement getRegisteredChangeIdentifier(List<ChangeIdentifier> searchList) {
        LanguageElement registeredElement = null;
        if (searchList != null) {
            int registeredChangeIdentifierCounter = 0;
            ChangeIdentifier registeredChangeIdentifier;
            while (registeredElement == null && registeredChangeIdentifierCounter < searchList.size()) {
                registeredChangeIdentifier = searchList.get(registeredChangeIdentifierCounter);
                if (registeredChangeIdentifier.getSourceClass() == expectedElement.getSourceClass() 
                        && registeredChangeIdentifier.getSourcePlugin().getAbsolutePath().equals(
                                expectedElement.getSourcePlugin().getAbsolutePath())) {
                    registeredElement = registeredChangeIdentifier;
                }
                registeredChangeIdentifierCounter++;
            }
        }
        return registeredElement;
    }
    
    /**
     * Searches in the given {@link List} of {@link Call}s for an {@link LanguageElement}, which has the same source
     * class and source plug-in as the current {@link #expectedElement}, and returns it.
     * 
     * @param searchList the {@link List} of {@link Call}s to search in
     * @return the {@link LanguageElement} with the same source class and source plug-in as the current
     *         {@link #expectedElement} or <code>null</code>, if no such element is registered
     */
    private LanguageElement getRegisteredCall(List<Call> searchList) {
        LanguageElement registeredElement = null;
        if (searchList != null) {
            int registeredCallsCounter = 0;
            Call registeredCall;
            while (registeredElement == null && registeredCallsCounter < searchList.size()) {
                registeredCall = searchList.get(registeredCallsCounter);
                if (registeredCall.getSourceClass() == expectedElement.getSourceClass() 
                        && registeredCall.getSourcePlugin().getAbsolutePath().equals(
                                expectedElement.getSourcePlugin().getAbsolutePath())) {
                    registeredElement = registeredCall;
                }
                registeredCallsCounter++;
            }
        }
        return registeredElement;
    }
}
