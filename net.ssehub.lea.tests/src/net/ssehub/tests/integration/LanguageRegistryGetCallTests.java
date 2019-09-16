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

import net.ssehub.integration.Call;
import net.ssehub.integration.ElementType;
import net.ssehub.integration.LanguageElement;
import net.ssehub.integration.LanguageElementException;
import net.ssehub.integration.LanguageRegistry;
import net.ssehub.integration.ParameterType;

/**
 * This class contains unit tests for the {@link LanguageRegistry#getCall(net.ssehub.integration.ElementType, String)}
 * and the 
 * {@link LanguageRegistry#getCall(net.ssehub.integration.ElementType, String, net.ssehub.integration.ParameterType[])}
 * methods of the {@link LanguageRegistry}.
 * 
 * @author Christian Kroeher
 *
 */
@RunWith(Parameterized.class)
public class LanguageRegistryGetCallTests extends AbstractLanguageRegistryTest {

    /**
     * The identifier of this class.
     */
    private static final String ID = "[LanguageRegistryGetParamterTypeTests]";

    /**
     * The expected results including the respective {@link LanguageElement} for testing.
     */
    private static final Object[][] EXPECTED_RESULTS = initializeExpectedResults();
    
    /**
     * The {@link Call} instance of the {@link AbstractLanguageRegistryTest#currentElement}.
     */
    private Call currentCall;
    
    /**
     * The expectation of whether the {@link #currentElement} is available in the {@link LanguageRegistry} as a 
     * {@link Call} of the type {@link ElementType#OPERATION} (<code>true</code>) or not (<code>false</code>), if
     * returning it relies on the simple name only.
     */
    private boolean expectedHasOperationBySimpleName;
    
    /**
     * The expectation of whether the {@link #currentElement} is available in the {@link LanguageRegistry} as a 
     * {@link Call} of the type {@link ElementType#OPERATION} (<code>true</code>) or not (<code>false</code>), if
     * returning it relies on the fully-qualified name only.
     */
    private boolean expectedHasOperationByFullyQualifiedName;
    
    /**
     * The expectation of whether the {@link #currentElement} is available in the {@link LanguageRegistry} as a 
     * {@link Call} of the type {@link ElementType#EXTRACTOR_CALL} (<code>true</code>) or not (<code>false</code>), if
     * returning it relies on the simple name only.
     */
    private boolean expectedHasExtractorCallBySimpleName;
    
    /**
     * The expectation of whether the {@link #currentElement} is available in the {@link LanguageRegistry} as a 
     * {@link Call} of the type {@link ElementType#EXTRACTOR_CALL} (<code>true</code>) or not (<code>false</code>), if
     * returning it relies on the fully-qualified name only.
     */
    private boolean expectedHasExtractorCallByFullyQualifiedName;
    
    /**
     * The expectation of whether the {@link #currentElement} is available in the {@link LanguageRegistry} as a 
     * {@link Call} of the type {@link ElementType#ANALYSIS_CALL} (<code>true</code>) or not (<code>false</code>), if
     * returning it relies on the simple name only.
     */
    private boolean expectedHasAnalysisCallBySimpleName;
    
    /**
     * The expectation of whether the {@link #currentElement} is available in the {@link LanguageRegistry} as a 
     * {@link Call} of the type {@link ElementType#ANALYSIS_CALL} (<code>true</code>) or not (<code>false</code>), if
     * returning it relies on the fully-qualified name only.
     */
    private boolean expectedHasAnalysisCallByFullyQualifiedName;
    
    /**
     * The expectation of whether the {@link #currentElement} is available in the {@link LanguageRegistry} as a 
     * {@link Call} of the type {@link ElementType#OPERATION} (<code>true</code>) or not (<code>false</code>), if
     * returning it relies on the simple name and parameters only.
     */
    private boolean expectedHasOperationBySimpleNameAndParameters;
    
    /**
     * The expectation of whether the {@link #currentElement} is available in the {@link LanguageRegistry} as a 
     * {@link Call} of the type {@link ElementType#OPERATION} (<code>true</code>) or not (<code>false</code>), if
     * returning it relies on the fully-qualified name and parameters only.
     */
    private boolean expectedHasOperationByFullyQualifiedNameAndParameters;
    
    /**
     * The expectation of whether the {@link #currentElement} is available in the {@link LanguageRegistry} as a 
     * {@link Call} of the type {@link ElementType#EXTRACTOR_CALL} (<code>true</code>) or not (<code>false</code>), if
     * returning it relies on the simple name and parameters only.
     */
    private boolean expectedHasExtractorCallBySimpleNameAndParameters;
    
    /**
     * The expectation of whether the {@link #currentElement} is available in the {@link LanguageRegistry} as a 
     * {@link Call} of the type {@link ElementType#EXTRACTOR_CALL} (<code>true</code>) or not (<code>false</code>), if
     * returning it relies on the fully-qualified name and parameters only.
     */
    private boolean expectedHasExtractorCallByFullyQualifiedNameAndParameters;
    
    /**
     * The expectation of whether the {@link #currentElement} is available in the {@link LanguageRegistry} as a 
     * {@link Call} of the type {@link ElementType#ANALYSIS_CALL} (<code>true</code>) or not (<code>false</code>), if
     * returning it relies on the simple name and parameters only.
     */
    private boolean expectedHasAnalysisCallBySimpleNameAndParameters;
    
    /**
     * The expectation of whether the {@link #currentElement} is available in the {@link LanguageRegistry} as a 
     * {@link Call} of the type {@link ElementType#ANALYSIS_CALL} (<code>true</code>) or not (<code>false</code>), if
     * returning it relies on the fully-qualified name and parameters only.
     */
    private boolean expectedHasAnalysisCallByFullyQualifiedNameAndParameters;
    
    /**
     * Creates a new {@link LanguageRegistryGetCallTests} instance.
     * 
     * @param testElement the {@link LanguageElement} for testing
     * @param expectedAdditionOfLanguageElement the expectation of whether the given {@link LanguageElement} is
     *        successfully added to the {@link LanguageRegistry} (<code>true</code>) or not (<code>false</code>)
     * @param expectedHasOperationBySimpleName the value for {@link #expectedHasOperationBySimpleName}
     * @param expectedHasOperationByFullyQualifiedName the value for {@link #expectedHasOperationByFullyQualifiedName}
     * @param expectedHasExtractorCallBySimpleName the value for {@link #expectedHasExtractorCallBySimpleName}
     * @param expectedHasExtractorCallByFullyQualifiedName the value for 
     *        {@link #expectedHasExtractorCallByFullyQualifiedName}
     * @param expectedHasAnalysisCallBySimpleName the value for {@link #expectedHasAnalysisCallBySimpleName}
     * @param expectedHasAnalysisCallByFullyQualifiedName the value for 
     *        {@link #expectedHasAnalysisCallByFullyQualifiedName}
     * @param expectedHasOperationBySimpleNameAndParameters the value for
     *        {@link #expectedHasOperationBySimpleNameAndParameters}
     * @param expectedHasOperationByFullyQualifiedNameAndParameters the value for
     *        {@link #expectedHasOperationByFullyQualifiedNameAndParameters}
     * @param expectedHasExtractorCallBySimpleNameAndParameters the value for
     *        {@link #expectedHasExtractorCallBySimpleNameAndParameters}
     * @param expectedHasExtractorCallByFullyQualifiedNameAndParameters the value for
     *        {@link #expectedHasExtractorCallByFullyQualifiedNameAndParameters}
     * @param expectedHasAnalysisCallBySimpleNameAndParameters the value for
     *        {@link #expectedHasAnalysisCallBySimpleNameAndParameters}
     * @param expectedHasAnalysisCallByFullyQualifiedNameAndParameters the value for
     *        {@link #expectedHasAnalysisCallByFullyQualifiedNameAndParameters}
     */
// CHECKSTYLE:OFF
    public LanguageRegistryGetCallTests(LanguageElement testElement,
            boolean expectedAdditionOfLanguageElement,
            boolean expectedHasOperationBySimpleName,
            boolean expectedHasOperationByFullyQualifiedName,
            boolean expectedHasExtractorCallBySimpleName,
            boolean expectedHasExtractorCallByFullyQualifiedName,
            boolean expectedHasAnalysisCallBySimpleName,
            boolean expectedHasAnalysisCallByFullyQualifiedName,
            boolean expectedHasOperationBySimpleNameAndParameters,
            boolean expectedHasOperationByFullyQualifiedNameAndParameters,
            boolean expectedHasExtractorCallBySimpleNameAndParameters,
            boolean expectedHasExtractorCallByFullyQualifiedNameAndParameters,
            boolean expectedHasAnalysisCallBySimpleNameAndParameters,
            boolean expectedHasAnalysisCallByFullyQualifiedNameAndParameters) {
        super(ID, testElement, expectedAdditionOfLanguageElement);
        currentCall = (Call) currentElement;
        this.expectedHasOperationBySimpleName = expectedHasOperationBySimpleName; 
        this.expectedHasOperationByFullyQualifiedName = expectedHasOperationByFullyQualifiedName;
        this.expectedHasExtractorCallBySimpleName = expectedHasExtractorCallBySimpleName;
        this.expectedHasExtractorCallByFullyQualifiedName = expectedHasExtractorCallByFullyQualifiedName;
        this.expectedHasAnalysisCallBySimpleName = expectedHasAnalysisCallBySimpleName;
        this.expectedHasAnalysisCallByFullyQualifiedName = expectedHasAnalysisCallByFullyQualifiedName;
        
        this.expectedHasOperationBySimpleNameAndParameters = expectedHasOperationBySimpleNameAndParameters; 
        this.expectedHasOperationByFullyQualifiedNameAndParameters =
                expectedHasOperationByFullyQualifiedNameAndParameters;
        this.expectedHasExtractorCallBySimpleNameAndParameters = expectedHasExtractorCallBySimpleNameAndParameters;
        this.expectedHasExtractorCallByFullyQualifiedNameAndParameters =
                expectedHasExtractorCallByFullyQualifiedNameAndParameters;
        this.expectedHasAnalysisCallBySimpleNameAndParameters = expectedHasAnalysisCallBySimpleNameAndParameters;
        this.expectedHasAnalysisCallByFullyQualifiedNameAndParameters =
                expectedHasAnalysisCallByFullyQualifiedNameAndParameters;
    }
// CHECKSTYLE:ON
 
    /**
     * Creates the {@link #EXPECTED_RESULTS}. As testing the {@link LanguageRegistry} requires the creation of 
     * {@link LanguageElement}s, which may throw an {@link LanguageElementException}, we have to initialize the values
     * in a separate method rather then directly initializing the array.
     * 
     * @return the value for the {@link #EXPECTED_RESULTS}
     */
// CHECKSTYLE:OFF
    private static Object[][] initializeExpectedResults() {
        Object[][] expectedResults = null;
        try {
            expectedResults = new Object[11][8];
            expectedResults[0] = new Object[] {new Call(ElementType.OPERATION, "sortUnknown", 
                    LanguageRegistryGetCallTests.class.getDeclaredMethods()[0], LanguageRegistryGetCallTests.class,
                    SOURCE_PLUGIN),
                false,
                false, false,
                false, false,
                false, false,
                false, false,
                false, false,
                false, false};
            expectedResults[1] = new Object[] {new Call(ElementType.EXTRACTOR_CALL, "extractUnkown", 
                    LanguageRegistryGetCallTests.class.getDeclaredMethods()[0], LanguageRegistryGetCallTests.class,
                    SOURCE_PLUGIN),
                false,
                false, false,
                false, false,
                false, false,
                false, false,
                false, false,
                false, false};
            expectedResults[2] = new Object[] {new Call(ElementType.ANALYSIS_CALL, "analyzeUnkown", 
                    LanguageRegistryGetCallTests.class.getDeclaredMethods()[0], LanguageRegistryGetCallTests.class,
                    SOURCE_PLUGIN),
                false,
                false, false,
                false, false,
                false, false,
                false, false,
                false, false,
                false, false};
            
            ParameterType returnType1 = new ParameterType(ElementType.ARTIFACT_PARAMETER_TYPE, "ArtifactReturnType",
                    LanguageRegistryGetCallTests.class, SOURCE_PLUGIN);
            if (!LanguageRegistry.INSTANCE.addParameterType(returnType1)) {
                fail(ID + "Adding required return parameter type \"" + returnType1.getFullyQualifiedName() 
                        + "\" failed");
            }
            ParameterType returnType2 = new ParameterType(ElementType.FRAGMENT_PARAMETER_TYPE, "FragmentReturnType",
                    LanguageRegistryGetCallTests.class, SOURCE_PLUGIN);
            if (!LanguageRegistry.INSTANCE.addParameterType(returnType2)) {
                fail(ID + "Adding required return parameter type \"" + returnType2.getFullyQualifiedName() 
                        + "\" failed");
            }
            ParameterType returnType3 = new ParameterType(ElementType.RESULT_PARAMETER_TYPE, "ResultReturnType",
                    LanguageRegistryGetCallTests.class, SOURCE_PLUGIN);
            if (!LanguageRegistry.INSTANCE.addParameterType(returnType3)) {
                fail(ID + "Adding required return parameter type \"" + returnType3.getFullyQualifiedName() 
                        + "\" failed");
            }
            
            Call call1 = new Call(ElementType.OPERATION, "sort",
                    LanguageRegistryGetCallTests.class.getDeclaredMethods()[0], LanguageRegistryGetCallTests.class,
                    SOURCE_PLUGIN);
            call1.finalize(returnType1, false, new ParameterType[] {returnType1}, new boolean[] {false}, null);
            expectedResults[3] = new Object[] {call1,
                true,
                true, true,
                false, false,
                false, false,
                true, true,
                false, false,
                false, false};
            
            Call call2 = new Call(ElementType.OPERATION, "sort", 
                    AbstractLanguageRegistryTest.class.getDeclaredMethods()[0], AbstractLanguageRegistryTest.class,
                    SOURCE_PLUGIN);
            call2.finalize(returnType1, false, new ParameterType[] {returnType1}, new boolean[] {false}, null);
            expectedResults[4] = new Object[] {call2,
                true,
                false, true,
                false, false,
                false, false,
                false, true,
                false, false,
                false, false};
            
            Call call3 = new Call(ElementType.OPERATION, "sort", 
                    AbstractLanguageRegistryTest.class.getDeclaredMethods()[0], AbstractLanguageRegistryTest.class,
                    SOURCE_PLUGIN);
            call3.finalize(returnType2, false, new ParameterType[] {returnType1}, new boolean[] {false}, null);
            expectedResults[5] = new Object[] {call3,
                true,
                false, false,
                false, false,
                false, false,
                false, false,
                false, false,
                false, false};
            
            Call call4 = new Call(ElementType.OPERATION, "sort",
                    AbstractLanguageRegistryTest.class.getDeclaredMethods()[0], AbstractLanguageRegistryTest.class,
                    SOURCE_PLUGIN);
            call4.finalize(returnType1, false, new ParameterType[] {returnType2}, new boolean[] {false}, null);
            expectedResults[6] = new Object[] {call4,
                true,
                false, false,
                false, false,
                false, false,
                true, true,
                false, false,
                false, false};
            
            Call call5 = new Call(ElementType.EXTRACTOR_CALL, "extract1",
                    LanguageRegistryGetCallTests.class.getDeclaredMethods()[0], LanguageRegistryGetCallTests.class,
                    SOURCE_PLUGIN);
            call5.finalize(returnType1, false, new ParameterType[] {returnType1}, new boolean[] {false}, null);
            expectedResults[7] = new Object[] {call5,
                true,
                false, false,
                true, true,
                false, false,
                false, false,
                true, true,
                false, false};
            
            Call call6 = new Call(ElementType.EXTRACTOR_CALL, "extract1",
                    LanguageRegistryGetCallTests.class.getDeclaredMethods()[0], LanguageRegistryGetCallTests.class,
                    SOURCE_PLUGIN);
            call6.finalize(returnType2, false, new ParameterType[] {returnType1}, new boolean[] {false}, null);
            expectedResults[8] = new Object[] {call6,
                true,
                false, false,
                false, false,
                false, false,
                false, false,
                false, false,
                false, false};
            
            Call call7 = new Call(ElementType.ANALYSIS_CALL, "analyze1",
                    LanguageRegistryGetCallTests.class.getDeclaredMethods()[0], LanguageRegistryGetCallTests.class,
                    SOURCE_PLUGIN);
            call7.finalize(returnType1, false, new ParameterType[] {returnType1}, new boolean[] {false}, null);
            expectedResults[9] = new Object[] {call7,
                true,
                false, false,
                false, false,
                true, true,
                false, false,
                false, false,
                true, true};
            
            Call call8 = new Call(ElementType.ANALYSIS_CALL, "analyze1",
                    LanguageRegistryGetCallTests.class.getDeclaredMethods()[0], LanguageRegistryGetCallTests.class,
                    SOURCE_PLUGIN);
            call8.finalize(returnType2, false, new ParameterType[] {returnType1}, new boolean[] {false}, null);
            expectedResults[10] = new Object[] {call8,
                true,
                false, false,
                false, false,
                false, false,
                false, false,
                false, false,
                false, false};
            
        } catch (LanguageElementException e) {
            System.err.println(ID + " Error while initializing expected results");
            e.printStackTrace();
        }
        return expectedResults;
    }
// CHECKSTYLE:ON
    
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
     * Tests whether {@link LanguageRegistry#getCall(ElementType, String)} returns the same value as 
     * {@link #expectedHasOperationBySimpleName} using {@link ElementType#OPERATION} and the simple name of the
     * {@link AbstractLanguageRegistryTest#currentElement}.
     */
    @Test
    public void testGetOperationBySimpleName() {
        assertEquals(expectedHasOperationBySimpleName, 
                currentCall.equals(languageRegistry.getCall(ElementType.OPERATION, currentElement.getName())), 
                "Wrong return value of LanguageRegistry#getCall(ElementType, String) using operation and simple name");
    }
    
    /**
     * Tests whether {@link LanguageRegistry#getCall(ElementType, String)} returns the same value as 
     * {@link #expectedHasOperationByFullyQualifiedName} using {@link ElementType#OPERATION} and the fully-qualified
     * name of the {@link AbstractLanguageRegistryTest#currentElement}.
     */
    @Test
    public void testGetOperationByFullyQualifiedName() {
        assertEquals(expectedHasOperationByFullyQualifiedName, 
                currentCall.equals(languageRegistry.getCall(ElementType.OPERATION, 
                        currentElement.getFullyQualifiedName())), "Wrong return value of"
                                + " LanguageRegistry#getCall(ElementType, String) using operation and fully-qualified"
                                + " name");
    }
    
    /**
     * Tests whether {@link LanguageRegistry#getCall(ElementType, String)} returns the same value as 
     * {@link #expectedHasExtractorCallBySimpleName} using {@link ElementType#EXTRACTOR_CALL} and the simple name of the
     * {@link AbstractLanguageRegistryTest#currentElement}.
     */
    @Test
    public void testGetExtractorCallBySimpleName() {
        assertEquals(expectedHasExtractorCallBySimpleName, 
                currentCall.equals(languageRegistry.getCall(ElementType.EXTRACTOR_CALL, currentElement.getName())), 
                "Wrong return value of LanguageRegistry#getCall(ElementType, String) using extractor call and simple"
                + " name");
    }
    
    /**
     * Tests whether {@link LanguageRegistry#getCall(ElementType, String)} returns the same value as 
     * {@link #expectedHasExtractorCallByFullyQualifiedName} using {@link ElementType#EXTRACTOR_CALL} and the fully-
     * qualified name of the {@link AbstractLanguageRegistryTest#currentElement}.
     */
    @Test
    public void testGetExtractorCallByFullyQualifiedName() {
        assertEquals(expectedHasExtractorCallByFullyQualifiedName, 
                currentCall.equals(languageRegistry.getCall(ElementType.EXTRACTOR_CALL, 
                        currentElement.getFullyQualifiedName())), "Wrong return value of"
                                + " LanguageRegistry#getCall(ElementType, String) using extractor call and"
                                + " fully-qualified name");
    }
    
    /**
     * Tests whether {@link LanguageRegistry#getCall(ElementType, String)} returns the same value as 
     * {@link #expectedHasAnalysisCallBySimpleName} using {@link ElementType#ANALYSIS_CALL} and the simple name of the
     * {@link AbstractLanguageRegistryTest#currentElement}.
     */
    @Test
    public void testGetAnalysisCallBySimpleName() {
        assertEquals(expectedHasAnalysisCallBySimpleName, 
                currentCall.equals(languageRegistry.getCall(ElementType.ANALYSIS_CALL, currentElement.getName())), 
                "Wrong return value of LanguageRegistry#getCall(ElementType, String) using analysis call and simple"
                + " name");
    }
    
    /**
     * Tests whether {@link LanguageRegistry#getCall(ElementType, String)} returns the same value as 
     * {@link #expectedHasAnalysisCallByFullyQualifiedName} using {@link ElementType#ANALYSIS_CALL} and the fully-
     * qualified name of the {@link AbstractLanguageRegistryTest#currentElement}.
     */
    @Test
    public void testGetAnalysisCallByFullyQualifiedName() {
        assertEquals(expectedHasAnalysisCallByFullyQualifiedName, 
                currentCall.equals(languageRegistry.getCall(ElementType.ANALYSIS_CALL, 
                        currentElement.getFullyQualifiedName())), "Wrong return value of"
                                + " LanguageRegistry#getCall(ElementType, String) using analysis call and"
                                + " fully-qualified name");
    }
    
    // TODO ####################################################
    
    /**
     * Tests whether {@link LanguageRegistry#getCall(ElementType, String, ParameterType[])} returns the same value as 
     * {@link #expectedHasOperationBySimpleNameAndParameters} using {@link ElementType#OPERATION}, the simple name, and
     * parameters of the {@link AbstractLanguageRegistryTest#currentElement}.
     */
    @Test
    public void testGetOperationBySimpleNameAndParameters() {
        assertEquals(expectedHasOperationBySimpleNameAndParameters, 
                currentCall.equals(languageRegistry.getCall(ElementType.OPERATION, currentElement.getName(),
                        currentCall.getParameters())), "Wrong return value of LanguageRegistry#getCall(ElementType,"
                                + " String, ParameterType[]) using operation simple name, and parameters");
    }
    
    /**
     * Tests whether {@link LanguageRegistry#getCall(ElementType, String, ParameterType[])} returns the same value as 
     * {@link #expectedHasOperationByFullyQualifiedNameAndParameters} using {@link ElementType#OPERATION}, the fully-
     * qualified name, and parameters of the {@link AbstractLanguageRegistryTest#currentElement}.
     */
    @Test
    public void testGetOperationByFullyQualifiedNameAndParameters() {
        assertEquals(expectedHasOperationByFullyQualifiedNameAndParameters, 
                currentCall.equals(languageRegistry.getCall(ElementType.OPERATION, 
                        currentElement.getFullyQualifiedName(), currentCall.getParameters())), "Wrong return value of"
                                + " LanguageRegistry#getCall(ElementType, String, ParameterType[]) using operation,"
                                + " fully-qualified name, and parameters");
    }
    
    /**
     * Tests whether {@link LanguageRegistry#getCall(ElementType, String, ParameterType[])} returns the same value as 
     * {@link #expectedHasExtractorCallBySimpleNameAndParameters} using {@link ElementType#EXTRACTOR_CALL}, the simple
     * name, and parameters of the {@link AbstractLanguageRegistryTest#currentElement}.
     */
    @Test
    public void testGetExtractorCallBySimpleNameAndParameters() {
        assertEquals(expectedHasExtractorCallBySimpleNameAndParameters, 
                currentCall.equals(languageRegistry.getCall(ElementType.EXTRACTOR_CALL, currentElement.getName(),
                        currentCall.getParameters())), "Wrong return value of LanguageRegistry#getCall(ElementType,"
                                + " String, ParameterType[]) using extractor call, simple name, and parameters");
    }
    
    /**
     * Tests whether {@link LanguageRegistry#getCall(ElementType, String, ParameterType[])} returns the same value as 
     * {@link #expectedHasExtractorCallByFullyQualifiedNameAndParameters} using {@link ElementType#EXTRACTOR_CALL}, the
     * fully-qualified name, and parameters of the {@link AbstractLanguageRegistryTest#currentElement}.
     */
    @Test
    public void testGetExtractorCallByFullyQualifiedNameAndParameters() {
        assertEquals(expectedHasExtractorCallByFullyQualifiedNameAndParameters, 
                currentCall.equals(languageRegistry.getCall(ElementType.EXTRACTOR_CALL, 
                        currentElement.getFullyQualifiedName(), currentCall.getParameters())), "Wrong return value of"
                                + " LanguageRegistry#getCall(ElementType, String, ParameterType[]) using extractor"
                                + " call, fully-qualified name, and parameters");
    }
    
    /**
     * Tests whether {@link LanguageRegistry#getCall(ElementType, String, ParameterType[])} returns the same value as 
     * {@link #expectedHasAnalysisCallBySimpleNameAndParameters} using {@link ElementType#ANALYSIS_CALL}, the simple
     * name, and parameters of the {@link AbstractLanguageRegistryTest#currentElement}.
     */
    @Test
    public void testGetAnalysisCallBySimpleNameAndParameters() {
        assertEquals(expectedHasAnalysisCallBySimpleNameAndParameters, 
                currentCall.equals(languageRegistry.getCall(ElementType.ANALYSIS_CALL, currentElement.getName(),
                        currentCall.getParameters())), "Wrong return value of LanguageRegistry#getCall(ElementType,"
                                + " String, ParameterType[]) using analysis call, simple name, and parameters");
    }
    
    /**
     * Tests whether {@link LanguageRegistry#getCall(ElementType, String, ParameterType[])} returns the same value as 
     * {@link #expectedHasAnalysisCallByFullyQualifiedNameAndParameters} using {@link ElementType#ANALYSIS_CALL}, the
     * fully-qualified name, and parameters of the {@link AbstractLanguageRegistryTest#currentElement}.
     */
    @Test
    public void testGetAnalysisCallByFullyQualifiedNameAndParameters() {
        assertEquals(expectedHasAnalysisCallByFullyQualifiedNameAndParameters, 
                currentCall.equals(languageRegistry.getCall(ElementType.ANALYSIS_CALL, 
                        currentElement.getFullyQualifiedName(), currentCall.getParameters())), "Wrong return value of"
                                + " LanguageRegistry#getCall(ElementType, String, ParameterType[]) using analysis"
                                + " call, fully-qualified name, and parameters");
    }
}
