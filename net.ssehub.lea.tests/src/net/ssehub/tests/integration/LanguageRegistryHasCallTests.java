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
import net.ssehub.integration.ParameterTypeInstance;

/**
 * This class contains unit tests for the 
 * {@link LanguageRegistry#hasCall(net.ssehub.integration.ElementType, String, boolean)},
 * {@link LanguageRegistry#hasCall(net.ssehub.integration.ElementType, String, net.ssehub.integration.ParameterType,
 * boolean)}, {@link LanguageRegistry#hasCall(net.ssehub.integration.ElementType, String,
 * net.ssehub.integration.ParameterType[], boolean)}, and {@link LanguageRegistry#hasCall(
 * net.ssehub.integration.ElementType, String, net.ssehub.integration.ParameterType, 
 * net.ssehub.integration.ParameterType[], boolean)} methods of the {@link LanguageRegistry}.
 * 
 * @author Christian Kroeher
 *
 */
@RunWith(Parameterized.class)
public class LanguageRegistryHasCallTests extends AbstractLanguageRegistryTest {

    /**
     * The identifier of this class.
     */
    private static final String ID = "[LanguageRegistryHasCallTests]";
    
    /**
     * The expected results including the respective {@link LanguageElement} for testing.
     */
    private static final Object[][] EXPECTED_RESULTS = initializeExpectedResults();
    
    /**
     * The expectation of whether the {@link #currentElement} is available in the {@link LanguageRegistry} as a 
     * {@link Call} of the type {@link ElementType#OPERATION} (<code>true</code>) or not
     * (<code>false</code>), if checking that existence relies on the simple name only (non-unique).
     */
    private boolean expectedHasAnyOperationBySimpleName;
    
    /**
     * The expectation of whether the {@link #currentElement} is available in the {@link LanguageRegistry} as a 
     * {@link Call} of the type {@link ElementType#OPERATION} (<code>true</code>) or not
     * (<code>false</code>), if checking that existence relies on the fully-qualified name only (non-unique).
     */
    private boolean expectedHasAnyOperationByFullyQualifiedName;
    
    /**
     * The expectation of whether the {@link #currentElement} is available in the {@link LanguageRegistry} as a 
     * {@link Call} of the type {@link ElementType#OPERATION} (<code>true</code>) or not
     * (<code>false</code>), if checking that existence relies on the simple name only (unique).
     */
    private boolean expectedHasUniqueOperationBySimpleName;
    
    /**
     * The expectation of whether the {@link #currentElement} is available in the {@link LanguageRegistry} as a 
     * {@link Call} of the type {@link ElementType#OPERATION} (<code>true</code>) or not
     * (<code>false</code>), if checking that existence relies on the fully-qualified name only (unique).
     */
    private boolean expectedHasUniqueOperationByFullyQualifiedName;

    /**
     * The expectation of whether the {@link #currentElement} is available in the {@link LanguageRegistry} as a 
     * {@link Call} of the type {@link ElementType#EXTRACTOR_CALL} (<code>true</code>) or not
     * (<code>false</code>), if checking that existence relies on the simple name only (non-unique).
     */
    private boolean expectedHasAnyExtractorCallBySimpleName;
    
    /**
     * The expectation of whether the {@link #currentElement} is available in the {@link LanguageRegistry} as a 
     * {@link Call} of the type {@link ElementType#EXTRACTOR_CALL} (<code>true</code>) or not
     * (<code>false</code>), if checking that existence relies on the fully-qualified name only (non-unique).
     */
    private boolean expectedHasAnyExtractorCallByFullyQualifiedName;
    
    /**
     * The expectation of whether the {@link #currentElement} is available in the {@link LanguageRegistry} as a 
     * {@link Call} of the type {@link ElementType#EXTRACTOR_CALL} (<code>true</code>) or not
     * (<code>false</code>), if checking that existence relies on the simple name only (unique).
     */
    private boolean expectedHasUniqueExtractorCallBySimpleName;
    
    /**
     * The expectation of whether the {@link #currentElement} is available in the {@link LanguageRegistry} as a 
     * {@link Call} of the type {@link ElementType#EXTRACTOR_CALL} (<code>true</code>) or not
     * (<code>false</code>), if checking that existence relies on the fully-qualified name only (unique).
     */
    private boolean expectedHasUniqueExtractorCallByFullyQualifiedName;

    /**
     * The expectation of whether the {@link #currentElement} is available in the {@link LanguageRegistry} as a 
     * {@link Call} of the type {@link ElementType#ANALYSIS_CALL} (<code>true</code>) or not
     * (<code>false</code>), if checking that existence relies on the simple name only (non-unique).
     */
    private boolean expectedHasAnyAnalysisCallBySimpleName;
    
    /**
     * The expectation of whether the {@link #currentElement} is available in the {@link LanguageRegistry} as a 
     * {@link Call} of the type {@link ElementType#ANALYSIS_CALL} (<code>true</code>) or not
     * (<code>false</code>), if checking that existence relies on the fully-qualified name only (non-unique).
     */
    private boolean expectedHasAnyAnalysisCallByFullyQualifiedName;
    
    /**
     * The expectation of whether the {@link #currentElement} is available in the {@link LanguageRegistry} as a 
     * {@link Call} of the type {@link ElementType#ANALYSIS_CALL} (<code>true</code>) or not
     * (<code>false</code>), if checking that existence relies on the simple name only (unique).
     */
    private boolean expectedHasUniqueAnalysisCallBySimpleName;
    
    /**
     * The expectation of whether the {@link #currentElement} is available in the {@link LanguageRegistry} as a 
     * {@link Call} of the type {@link ElementType#ANALYSIS_CALL} (<code>true</code>) or not
     * (<code>false</code>), if checking that existence relies on the fully-qualified name only (unique).
     */
    private boolean expectedHasUniqueAnalysisCallByFullyQualifiedName;
    
    /**
     * The expectation of whether the {@link #currentElement} is available in the {@link LanguageRegistry} as a 
     * {@link Call} of the type {@link ElementType#OPERATION} (<code>true</code>) or not
     * (<code>false</code>), if checking that existence relies on the simple name and parameters only (non-unique).
     */
    private boolean expectedHasAnyOperationBySimpleNameAndParameters;
    
    /**
     * The expectation of whether the {@link #currentElement} is available in the {@link LanguageRegistry} as a 
     * {@link Call} of the type {@link ElementType#OPERATION} (<code>true</code>) or not
     * (<code>false</code>), if checking that existence relies on the fully-qualified name and parameters only (non-
     * unique).
     */
    private boolean expectedHasAnyOperationByFullyQualifiedNameAndParameters;
    
    /**
     * The expectation of whether the {@link #currentElement} is available in the {@link LanguageRegistry} as a 
     * {@link Call} of the type {@link ElementType#OPERATION} (<code>true</code>) or not
     * (<code>false</code>), if checking that existence relies on the simple name and parameters only (unique).
     */
    private boolean expectedHasUniqueOperationBySimpleNameAndParameters;
    
    /**
     * The expectation of whether the {@link #currentElement} is available in the {@link LanguageRegistry} as a 
     * {@link Call} of the type {@link ElementType#OPERATION} (<code>true</code>) or not
     * (<code>false</code>), if checking that existence relies on the fully-qualified name and parameters only (unique).
     */
    private boolean expectedHasUniqueOperationByFullyQualifiedNameAndParameters;

    /**
     * The expectation of whether the {@link #currentElement} is available in the {@link LanguageRegistry} as a 
     * {@link Call} of the type {@link ElementType#EXTRACTOR_CALL} (<code>true</code>) or not
     * (<code>false</code>), if checking that existence relies on the simple name and parameters only (non-unique).
     */
    private boolean expectedHasAnyExtractorCallBySimpleNameAndParameters;
    
    /**
     * The expectation of whether the {@link #currentElement} is available in the {@link LanguageRegistry} as a 
     * {@link Call} of the type {@link ElementType#EXTRACTOR_CALL} (<code>true</code>) or not
     * (<code>false</code>), if checking that existence relies on the fully-qualified name and parameters only (non-
     * unique).
     */
    private boolean expectedHasAnyExtractorCallByFullyQualifiedNameAndParameters;
    
    /**
     * The expectation of whether the {@link #currentElement} is available in the {@link LanguageRegistry} as a 
     * {@link Call} of the type {@link ElementType#EXTRACTOR_CALL} (<code>true</code>) or not
     * (<code>false</code>), if checking that existence relies on the simple name and parameters only (unique).
     */
    private boolean expectedHasUniqueExtractorCallBySimpleNameAndParameters;
    
    /**
     * The expectation of whether the {@link #currentElement} is available in the {@link LanguageRegistry} as a 
     * {@link Call} of the type {@link ElementType#EXTRACTOR_CALL} (<code>true</code>) or not
     * (<code>false</code>), if checking that existence relies on the fully-qualified name and parameters only (unique).
     */
    private boolean expectedHasUniqueExtractorCallByFullyQualifiedNameAndParameters;

    /**
     * The expectation of whether the {@link #currentElement} is available in the {@link LanguageRegistry} as a 
     * {@link Call} of the type {@link ElementType#ANALYSIS_CALL} (<code>true</code>) or not
     * (<code>false</code>), if checking that existence relies on the simple name and parameters only (non-unique).
     */
    private boolean expectedHasAnyAnalysisCallBySimpleNameAndParameters;
    
    /**
     * The expectation of whether the {@link #currentElement} is available in the {@link LanguageRegistry} as a 
     * {@link Call} of the type {@link ElementType#ANALYSIS_CALL} (<code>true</code>) or not
     * (<code>false</code>), if checking that existence relies on the fully-qualified name and parameters only (non-
     * unique).
     */
    private boolean expectedHasAnyAnalysisCallByFullyQualifiedNameAndParameters;
    
    /**
     * The expectation of whether the {@link #currentElement} is available in the {@link LanguageRegistry} as a 
     * {@link Call} of the type {@link ElementType#ANALYSIS_CALL} (<code>true</code>) or not
     * (<code>false</code>), if checking that existence relies on the simple name and parameters only (unique).
     */
    private boolean expectedHasUniqueAnalysisCallBySimpleNameAndParameters;
    
    /**
     * The expectation of whether the {@link #currentElement} is available in the {@link LanguageRegistry} as a 
     * {@link Call} of the type {@link ElementType#ANALYSIS_CALL} (<code>true</code>) or not
     * (<code>false</code>), if checking that existence relies on the fully-qualified name and parameters only (unique).
     */
    private boolean expectedHasUniqueAnalysisCallByFullyQualifiedNameAndParameters;
    
    /**
     * Creates a new {@link LanguageRegistryHasCallTests} instance.
     * 
     * @param testElement the {@link LanguageElement} for testing
     * @param expectedAdditionOfLanguageElement the expectation of whether the given {@link LanguageElement} is
     *        successfully added to the {@link LanguageRegistry} (<code>true</code>) or not (<code>false</code>)
     * @param expectedHasAnyOperationBySimpleName the value for {@link #expectedHasAnyOperationBySimpleName}
     * @param expectedHasAnyOperationByFullyQualifiedName the value for 
     *        {@link #expectedHasAnyOperationByFullyQualifiedName}
     * @param expectedHasUniqueOperationBySimpleName the value for {@link #expectedHasUniqueOperationBySimpleName}
     * @param expectedHasUniqueOperationByFullyQualifiedName the value for
     *        {@link #expectedHasUniqueOperationByFullyQualifiedName}
     * @param expectedHasAnyExtractorCallBySimpleName the value for {@link #expectedHasAnyExtractorCallBySimpleName}
     * @param expectedHasAnyExtractorCallByFullyQualifiedName the value for
     *        {@link #expectedHasAnyExtractorCallByFullyQualifiedName}
     * @param expectedHasUniqueExtractorCallBySimpleName the value for
     *        {@link #expectedHasUniqueExtractorCallBySimpleName}
     * @param expectedHasUniqueExtractorCallByFullyQualifiedName the value for
     *        {@link #expectedHasUniqueExtractorCallByFullyQualifiedName}
     * @param expectedHasAnyAnalysisCallBySimpleName the value for {@link #expectedHasAnyAnalysisCallBySimpleName}
     * @param expectedHasAnyAnalysisCallByFullyQualifiedName the value for
     *        {@link #expectedHasAnyAnalysisCallByFullyQualifiedName}
     * @param expectedHasUniqueAnalysisCallBySimpleName the value for {@link #expectedHasUniqueAnalysisCallBySimpleName}
     * @param expectedHasUniqueAnalysisCallByFullyQualifiedName the value for
     *        {@link #expectedHasUniqueAnalysisCallByFullyQualifiedName}
     * @param expectedHasAnyOperationBySimpleNameAndParameters the value for
     *        {@link #expectedHasAnyOperationBySimpleNameAndParameters}
     * @param expectedHasAnyOperationByFullyQualifiedNameAndParameters the value for
     *        {@link #expectedHasAnyOperationByFullyQualifiedNameAndParameters}
     * @param expectedHasUniqueOperationBySimpleNameAndParameters the value for
     *        {@link #expectedHasUniqueOperationBySimpleNameAndParameters}
     * @param expectedHasUniqueOperationByFullyQualifiedNameAndParameters the value for
     *        {@link #expectedHasUniqueOperationByFullyQualifiedNameAndParameters}
     * @param expectedHasAnyExtractorCallBySimpleNameAndParameters the value for
     *        {@link #expectedHasAnyExtractorCallBySimpleNameAndParameters}
     * @param expectedHasAnyExtractorCallByFullyQualifiedNameAndParameters the value for
     *        {@link #expectedHasAnyExtractorCallByFullyQualifiedNameAndParameters}
     * @param expectedHasUniqueExtractorCallBySimpleNameAndParameters the value for
     *        {@link #expectedHasUniqueExtractorCallBySimpleNameAndParameters}
     * @param expectedHasUniqueExtractorCallByFullyQualifiedNameAndParameters the value for
     *        {@link #expectedHasUniqueExtractorCallByFullyQualifiedNameAndParameters}
     * @param expectedHasAnyAnalysisCallBySimpleNameAndParameters the value for
     *        {@link #expectedHasAnyAnalysisCallBySimpleNameAndParameters}
     * @param expectedHasAnyAnalysisCallByFullyQualifiedNameAndParameters the value for
     *        {@link #expectedHasAnyAnalysisCallByFullyQualifiedNameAndParameters}
     * @param expectedHasUniqueAnalysisCallBySimpleNameAndParameters the value for
     *        {@link #expectedHasUniqueAnalysisCallBySimpleNameAndParameters}
     * @param expectedHasUniqueAnalysisCallByFullyQualifiedNameAndParameters the value for
     *        {@link #expectedHasUniqueAnalysisCallByFullyQualifiedNameAndParameters}
     */
// CHECKSTYLE:OFF
    public LanguageRegistryHasCallTests(LanguageElement testElement,
            boolean expectedAdditionOfLanguageElement,
            boolean expectedHasAnyOperationBySimpleName,
            boolean expectedHasAnyOperationByFullyQualifiedName,
            boolean expectedHasUniqueOperationBySimpleName,
            boolean expectedHasUniqueOperationByFullyQualifiedName,
            boolean expectedHasAnyExtractorCallBySimpleName,
            boolean expectedHasAnyExtractorCallByFullyQualifiedName,
            boolean expectedHasUniqueExtractorCallBySimpleName,
            boolean expectedHasUniqueExtractorCallByFullyQualifiedName,
            boolean expectedHasAnyAnalysisCallBySimpleName,
            boolean expectedHasAnyAnalysisCallByFullyQualifiedName,
            boolean expectedHasUniqueAnalysisCallBySimpleName,
            boolean expectedHasUniqueAnalysisCallByFullyQualifiedName,
            boolean expectedHasAnyOperationBySimpleNameAndParameters,
            boolean expectedHasAnyOperationByFullyQualifiedNameAndParameters,
            boolean expectedHasUniqueOperationBySimpleNameAndParameters,
            boolean expectedHasUniqueOperationByFullyQualifiedNameAndParameters,
            boolean expectedHasAnyExtractorCallBySimpleNameAndParameters,
            boolean expectedHasAnyExtractorCallByFullyQualifiedNameAndParameters,
            boolean expectedHasUniqueExtractorCallBySimpleNameAndParameters,
            boolean expectedHasUniqueExtractorCallByFullyQualifiedNameAndParameters,
            boolean expectedHasAnyAnalysisCallBySimpleNameAndParameters,
            boolean expectedHasAnyAnalysisCallByFullyQualifiedNameAndParameters,
            boolean expectedHasUniqueAnalysisCallBySimpleNameAndParameters,
            boolean expectedHasUniqueAnalysisCallByFullyQualifiedNameAndParameters) {
        super(ID, testElement, expectedAdditionOfLanguageElement);
        
        this.expectedHasAnyOperationBySimpleName = expectedHasAnyOperationBySimpleName; 
        this.expectedHasAnyOperationByFullyQualifiedName = expectedHasAnyOperationByFullyQualifiedName;
        this.expectedHasUniqueOperationBySimpleName = expectedHasUniqueOperationBySimpleName;
        this.expectedHasUniqueOperationByFullyQualifiedName = expectedHasUniqueOperationByFullyQualifiedName;

        this.expectedHasAnyExtractorCallBySimpleName = expectedHasAnyExtractorCallBySimpleName;
        this.expectedHasAnyExtractorCallByFullyQualifiedName = expectedHasAnyExtractorCallByFullyQualifiedName;
        this.expectedHasUniqueExtractorCallBySimpleName = expectedHasUniqueExtractorCallBySimpleName;
        this.expectedHasUniqueExtractorCallByFullyQualifiedName = expectedHasUniqueExtractorCallByFullyQualifiedName;

        this.expectedHasAnyAnalysisCallBySimpleName = expectedHasAnyAnalysisCallBySimpleName;
        this.expectedHasAnyAnalysisCallByFullyQualifiedName = expectedHasAnyAnalysisCallByFullyQualifiedName;
        this.expectedHasUniqueAnalysisCallBySimpleName = expectedHasUniqueAnalysisCallBySimpleName;
        this.expectedHasUniqueAnalysisCallByFullyQualifiedName = expectedHasUniqueAnalysisCallByFullyQualifiedName;
        
        this.expectedHasAnyOperationBySimpleNameAndParameters = expectedHasAnyOperationBySimpleNameAndParameters;
        this.expectedHasAnyOperationByFullyQualifiedNameAndParameters =
                expectedHasAnyOperationByFullyQualifiedNameAndParameters;
        this.expectedHasUniqueOperationBySimpleNameAndParameters = expectedHasUniqueOperationBySimpleNameAndParameters;
        this.expectedHasUniqueOperationByFullyQualifiedNameAndParameters =
                expectedHasUniqueOperationByFullyQualifiedNameAndParameters;

        this.expectedHasAnyExtractorCallBySimpleNameAndParameters = 
                expectedHasAnyExtractorCallBySimpleNameAndParameters;
        this.expectedHasAnyExtractorCallByFullyQualifiedNameAndParameters =
                expectedHasAnyExtractorCallByFullyQualifiedNameAndParameters;
        this.expectedHasUniqueExtractorCallBySimpleNameAndParameters =
                expectedHasUniqueExtractorCallBySimpleNameAndParameters;
        this.expectedHasUniqueExtractorCallByFullyQualifiedNameAndParameters =
                expectedHasUniqueExtractorCallByFullyQualifiedNameAndParameters;

        this.expectedHasAnyAnalysisCallBySimpleNameAndParameters = expectedHasAnyAnalysisCallBySimpleNameAndParameters;
        this.expectedHasAnyAnalysisCallByFullyQualifiedNameAndParameters =
                expectedHasAnyAnalysisCallByFullyQualifiedNameAndParameters;
        this.expectedHasUniqueAnalysisCallBySimpleNameAndParameters =
                expectedHasUniqueAnalysisCallBySimpleNameAndParameters;
        this.expectedHasUniqueAnalysisCallByFullyQualifiedNameAndParameters =
                expectedHasUniqueAnalysisCallByFullyQualifiedNameAndParameters;
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
            expectedResults = new Object[11][26];
            expectedResults[0] = new Object[] {new Call(ElementType.OPERATION, "sortUnknown", 
                    LanguageRegistryHasCallTests.class.getDeclaredMethods()[0], LanguageRegistryHasCallTests.class,
                    SOURCE_PLUGIN),
                false,
                false, false, false, false,
                false, false, false, false,
                false, false, false, false,
                false, false, false, false,
                false, false, false, false,
                false, false, false, false};
            expectedResults[1] = new Object[] {new Call(ElementType.EXTRACTOR_CALL, "extractUnkown", 
                    LanguageRegistryHasCallTests.class.getDeclaredMethods()[0], LanguageRegistryHasCallTests.class,
                    SOURCE_PLUGIN),
                false,
                false, false, false, false,
                false, false, false, false,
                false, false, false, false,
                false, false, false, false,
                false, false, false, false,
                false, false, false, false};
            expectedResults[2] = new Object[] {new Call(ElementType.ANALYSIS_CALL, "analyzeUnkown", 
                    LanguageRegistryHasCallTests.class.getDeclaredMethods()[0], LanguageRegistryHasCallTests.class,
                    SOURCE_PLUGIN),
                false,
                false, false, false, false,
                false, false, false, false,
                false, false, false, false,
                false, false, false, false,
                false, false, false, false,
                false, false, false, false};
            
            ParameterType returnType1 = new ParameterType(ElementType.ARTIFACT_PARAMETER_TYPE, "ArtifactReturnType",
                    LanguageRegistryHasCallTests.class, SOURCE_PLUGIN);
            ParameterTypeInstance returnTypeInstance1 = new ParameterTypeInstance(returnType1, false);
            if (!LanguageRegistry.INSTANCE.addParameterType(returnType1)) {
                fail(ID + "Adding required return parameter type \"" + returnType1.getFullyQualifiedName() 
                        + "\" failed");
            }
            ParameterType returnType2 = new ParameterType(ElementType.FRAGMENT_PARAMETER_TYPE, "FragmentReturnType",
                    LanguageRegistryHasCallTests.class, SOURCE_PLUGIN);
            ParameterTypeInstance returnTypeInstance2 = new ParameterTypeInstance(returnType2, false);
            if (!LanguageRegistry.INSTANCE.addParameterType(returnType2)) {
                fail(ID + "Adding required return parameter type \"" + returnType2.getFullyQualifiedName() 
                        + "\" failed");
            }
            
            Call call1 = new Call(ElementType.OPERATION, "sort",
                    LanguageRegistryHasCallTests.class.getDeclaredMethods()[0], LanguageRegistryHasCallTests.class,
                    SOURCE_PLUGIN);
            call1.finalize(returnTypeInstance1, new ParameterTypeInstance[] {returnTypeInstance1}, null);
            expectedResults[3] = new Object[] {call1,
                true,
                true, true, true, true,
                false, false, false, false,
                false, false, false, false,
                true, true, true, true,
                false, false, false, false,
                false, false, false, false};
            
            Call call2 = new Call(ElementType.OPERATION, "sort", 
                    AbstractLanguageRegistryTest.class.getDeclaredMethods()[0], AbstractLanguageRegistryTest.class,
                    SOURCE_PLUGIN);
            call2.finalize(returnTypeInstance1, new ParameterTypeInstance[] {returnTypeInstance1}, null);
            expectedResults[4] = new Object[] {call2,
                true,
                true, true, false, true,
                false, false, false, false,
                false, false, false, false,
                true, true, false, true,
                false, false, false, false,
                false, false, false, false};
            
            Call call3 = new Call(ElementType.OPERATION, "sort", 
                    AbstractLanguageRegistryTest.class.getDeclaredMethods()[0], AbstractLanguageRegistryTest.class,
                    SOURCE_PLUGIN);
            call3.finalize(returnTypeInstance2, new ParameterTypeInstance[] {returnTypeInstance1}, null);
            expectedResults[5] = new Object[] {call3,
                true,
                true, true, false, false,
                false, false, false, false,
                false, false, false, false,
                true, true, false, false,
                false, false, false, false,
                false, false, false, false};
            
            Call call4 = new Call(ElementType.OPERATION, "sort",
                    AbstractLanguageRegistryTest.class.getDeclaredMethods()[0], AbstractLanguageRegistryTest.class,
                    SOURCE_PLUGIN);
            call4.finalize(returnTypeInstance1, new ParameterTypeInstance[] {returnTypeInstance2}, null);
            expectedResults[6] = new Object[] {call4,
                true,
                true, true, false, false,
                false, false, false, false,
                false, false, false, false,
                true, true, true, true,
                false, false, false, false,
                false, false, false, false};
            
            Call call5 = new Call(ElementType.EXTRACTOR_CALL, "extract1",
                    LanguageRegistryHasCallTests.class.getDeclaredMethods()[0], LanguageRegistryHasCallTests.class,
                    SOURCE_PLUGIN);
            call5.finalize(returnTypeInstance1, new ParameterTypeInstance[] {returnTypeInstance1}, null);
            expectedResults[7] = new Object[] {call5,
                true,
                false, false, false, false,
                true, true, true, true,
                false, false, false, false,
                false, false, false, false,
                true, true, true, true,
                false, false, false, false};
            
            Call call6 = new Call(ElementType.EXTRACTOR_CALL, "extract1",
                    LanguageRegistryHasCallTests.class.getDeclaredMethods()[0], LanguageRegistryHasCallTests.class,
                    SOURCE_PLUGIN);
            call6.finalize(returnTypeInstance2, new ParameterTypeInstance[] {returnTypeInstance1}, null);
            expectedResults[8] = new Object[] {call6,
                true,
                false, false, false, false,
                true, true, false, false,
                false, false, false, false,
                false, false, false, false,
                true, true, false, false,
                false, false, false, false};
            
            Call call7 = new Call(ElementType.ANALYSIS_CALL, "analyze1",
                    LanguageRegistryHasCallTests.class.getDeclaredMethods()[0], LanguageRegistryHasCallTests.class,
                    SOURCE_PLUGIN);
            call7.finalize(returnTypeInstance1, new ParameterTypeInstance[] {returnTypeInstance1}, null);
            expectedResults[9] = new Object[] {call7,
                true,
                false, false, false, false,
                false, false, false, false,
                true, true, true, true,
                false, false, false, false,
                false, false, false, false,
                true, true, true, true};
            
            Call call8 = new Call(ElementType.ANALYSIS_CALL, "analyze1",
                    LanguageRegistryHasCallTests.class.getDeclaredMethods()[0], LanguageRegistryHasCallTests.class,
                    SOURCE_PLUGIN);
            call8.finalize(returnTypeInstance2, new ParameterTypeInstance[] {returnTypeInstance1}, null);
            expectedResults[10] = new Object[] {call8,
                true,
                false, false, false, false,
                false, false, false, false,
                true, true, false, false,
                false, false, false, false,
                false, false, false, false,
                true, true, false, false};
            
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
     * Tests whether {@link LanguageRegistry#hasCall(ElementType, String, boolean)} returns the same value as 
     * {@link #expectedHasAnyOperationBySimpleName} using {@link ElementType#OPERATION} and the simple name (non-unique)
     * of the {@link AbstractLanguageRegistryTest#currentElement}.
     */
    @Test
    public void testHasAnyOperationBySimpleName() {
        assertEquals(expectedHasAnyOperationBySimpleName, 
                languageRegistry.hasCall(ElementType.OPERATION, currentElement.getName(), false), 
                "Wrong return value of LanguageRegistry#hasCall(ElementType, String, boolean) using operation and"
                + " simple name non-unique");
    }
    
    /**
     * Tests whether {@link LanguageRegistry#hasCall(ElementType, String, boolean)} returns the same value as 
     * {@link #expectedHasAnyOperationByFullyQualifiedName} using {@link ElementType#OPERATION} and the fully-qualified
     * name (non-unique) of the {@link AbstractLanguageRegistryTest#currentElement}.
     */
    @Test
    public void testHasAnyOperationByFullyQualifiedName() {
        assertEquals(expectedHasAnyOperationByFullyQualifiedName, 
                languageRegistry.hasCall(ElementType.OPERATION, currentElement.getFullyQualifiedName(), false),
                "Wrong return value of LanguageRegistry#hasCall(ElementType, String, boolean) using operation and"
                        + " fully-qualified name non-unique");
    }
    
    /**
     * Tests whether {@link LanguageRegistry#hasCall(ElementType, String, boolean)} returns the same value as 
     * {@link #expectedHasUniqueOperationBySimpleName} using {@link ElementType#OPERATION} and the simple name (unique)
     * of the {@link AbstractLanguageRegistryTest#currentElement}.
     */
    @Test
    public void testHasUniqueOperationBySimpleName() {
        assertEquals(expectedHasUniqueOperationBySimpleName, 
                languageRegistry.hasCall(ElementType.OPERATION, currentElement.getName(), true), 
                "Wrong return value of LanguageRegistry#hasCall(ElementType, String, boolean) using operation and"
                        + " simple name unique");
    }
    
    /**
     * Tests whether {@link LanguageRegistry#hasCall(ElementType, String, boolean)} returns the same value as 
     * {@link #testHasUniqueOperationByFullyQualifiedName} using {@link ElementType#OPERATION} and the fully-qualified
     * name (unique) of the {@link AbstractLanguageRegistryTest#currentElement}.
     */
    @Test
    public void testHasUniqueOperationByFullyQualifiedName() {
        assertEquals(expectedHasUniqueOperationByFullyQualifiedName, 
                languageRegistry.hasCall(ElementType.OPERATION, currentElement.getFullyQualifiedName(), true), 
                "Wrong return value of LanguageRegistry#hasCall(ElementType, String, boolean) using operation and"
                        + " fully-qualified name unique");
    }
    
    /**
     * Tests whether {@link LanguageRegistry#hasCall(ElementType, String, boolean)} returns the same value as 
     * {@link #expectedHasAnyExtractorCallBySimpleName} using {@link ElementType#EXTRACTOR_CALL} and the simple name
     * (non-unique) of the {@link AbstractLanguageRegistryTest#currentElement}.
     */
    @Test
    public void testHasAnyExtractorCallBySimpleName() {
        assertEquals(expectedHasAnyExtractorCallBySimpleName, 
                languageRegistry.hasCall(ElementType.EXTRACTOR_CALL, currentElement.getName(), false), 
                "Wrong return value of LanguageRegistry#hasCall(ElementType, String, boolean) using extractor call and"
                + " simple name non-unique");
    }
    
    /**
     * Tests whether {@link LanguageRegistry#hasCall(ElementType, String, boolean)} returns the same value as 
     * {@link #expectedHasAnyExtractorCallByFullyQualifiedName} using {@link ElementType#EXTRACTOR_CALL} and the
     * fully-qualified name (non-unique) of the {@link AbstractLanguageRegistryTest#currentElement}.
     */
    @Test
    public void testHasAnyExtractorCallByFullyQualifiedName() {
        assertEquals(expectedHasAnyExtractorCallByFullyQualifiedName, 
                languageRegistry.hasCall(ElementType.EXTRACTOR_CALL, currentElement.getFullyQualifiedName(), false),
                "Wrong return value of LanguageRegistry#hasCall(ElementType, String, boolean) using extractor call and"
                        + " fully-qualified name non-unique");
    }
    
    /**
     * Tests whether {@link LanguageRegistry#hasCall(ElementType, String, boolean)} returns the same value as 
     * {@link #expectedHasUniqueExtractorCallBySimpleName} using {@link ElementType#EXTRACTOR_CALL} and the simple name
     * (unique) of the {@link AbstractLanguageRegistryTest#currentElement}.
     */
    @Test
    public void testHasUniqueExtractorCallBySimpleName() {
        assertEquals(expectedHasUniqueExtractorCallBySimpleName, 
                languageRegistry.hasCall(ElementType.EXTRACTOR_CALL, currentElement.getName(), true), 
                "Wrong return value of LanguageRegistry#hasCall(ElementType, String, boolean) using extractor call and"
                        + " simple name unique");
    }
    
    /**
     * Tests whether {@link LanguageRegistry#hasCall(ElementType, String, boolean)} returns the same value as 
     * {@link #expectedHasUniqueExtractorCallByFullyQualifiedName} using {@link ElementType#EXTRACTOR_CALL} and the
     * fully-qualified name (unique) of the {@link AbstractLanguageRegistryTest#currentElement}.
     */
    @Test
    public void testHasUniqueExtractorCallByFullyQualifiedName() {
        assertEquals(expectedHasUniqueExtractorCallByFullyQualifiedName, 
                languageRegistry.hasCall(ElementType.EXTRACTOR_CALL, currentElement.getFullyQualifiedName(), true), 
                "Wrong return value of LanguageRegistry#hasCall(ElementType, String, boolean) using extractor call and"
                        + " fully-qualified name unique");
    }

    /**
     * Tests whether {@link LanguageRegistry#hasCall(ElementType, String, boolean)} returns the same value as 
     * {@link #expectedHasAnyAnalysisCallBySimpleName} using {@link ElementType#ANALYSIS_CALL} and the simple name
     * (non-unique) of the {@link AbstractLanguageRegistryTest#currentElement}.
     */
    @Test
    public void testHasAnyAnalysisCallBySimpleName() {
        assertEquals(expectedHasAnyAnalysisCallBySimpleName, 
                languageRegistry.hasCall(ElementType.ANALYSIS_CALL, currentElement.getName(), false), 
                "Wrong return value of LanguageRegistry#hasCall(ElementType, String, boolean) using analysis call and"
                + " simple name non-unique");
    }
    
    /**
     * Tests whether {@link LanguageRegistry#hasCall(ElementType, String, boolean)} returns the same value as 
     * {@link #expectedHasAnyAnalysisCallByFullyQualifiedName} using {@link ElementType#ANALYSIS_CALL} and the
     * fully-qualified name (non-unique) of the {@link AbstractLanguageRegistryTest#currentElement}.
     */
    @Test
    public void testHasAnyAnalysisCallByFullyQualifiedName() {
        assertEquals(expectedHasAnyAnalysisCallByFullyQualifiedName, 
                languageRegistry.hasCall(ElementType.ANALYSIS_CALL, currentElement.getFullyQualifiedName(), false),
                "Wrong return value of LanguageRegistry#hasCall(ElementType, String, boolean) using analysis call and"
                        + " fully-qualified name non-unique");
    }
    
    /**
     * Tests whether {@link LanguageRegistry#hasCall(ElementType, String, boolean)} returns the same value as 
     * {@link #expectedHasUniqueAnalysisCallBySimpleName} using {@link ElementType#ANALYSIS_CALL} and the simple name
     * (unique) of the {@link AbstractLanguageRegistryTest#currentElement}.
     */
    @Test
    public void testHasUniqueAnalysisCallBySimpleName() {
        assertEquals(expectedHasUniqueAnalysisCallBySimpleName, 
                languageRegistry.hasCall(ElementType.ANALYSIS_CALL, currentElement.getName(), true), 
                "Wrong return value of LanguageRegistry#hasCall(ElementType, String, boolean) using analysis call and"
                        + " simple name unique");
    }
    
    /**
     * Tests whether {@link LanguageRegistry#hasCall(ElementType, String, boolean)} returns the same value as 
     * {@link #expectedHasUniqueAnalysisCallByFullyQualifiedName} using {@link ElementType#ANALYSIS_CALL} and the
     * fully-qualified name (unique) of the {@link AbstractLanguageRegistryTest#currentElement}.
     */
    @Test
    public void testHasUniqueAnalysisCallByFullyQualifiedName() {
        assertEquals(expectedHasUniqueAnalysisCallByFullyQualifiedName, 
                languageRegistry.hasCall(ElementType.ANALYSIS_CALL, currentElement.getFullyQualifiedName(), true), 
                "Wrong return value of LanguageRegistry#hasCall(ElementType, String, boolean) using analysis call and"
                        + " fully-qualified name unique");
    }

    /**
     * Tests whether {@link LanguageRegistry#hasCall(ElementType, String, ParameterType[], boolean)} returns the same
     * value as {@link #expectedHasAnyOperationBySimpleNameAndParameters} using {@link ElementType#OPERATION}, the
     * simple name, and the parameters (non-unique) of the {@link AbstractLanguageRegistryTest#currentElement}.
     */
    @Test
    public void testHasAnyOperationBySimpleNameAndParameters() {
        Call currentCall = (Call) currentElement;
        assertEquals(expectedHasAnyOperationBySimpleNameAndParameters, 
                languageRegistry.hasCall(ElementType.OPERATION, currentElement.getName(), currentCall.getParameters(),
                        false), "Wrong return value of LanguageRegistry#hasCall(ElementType, String, ParameterType[],"
                                + " boolean) using operation, simple name, and parameters non-unique");
    }
    
    /**
     * Tests whether {@link LanguageRegistry#hasCall(ElementType, String, ParameterType[], boolean)} returns the same
     * value as {@link #expectedHasAnyOperationByFullyQualifiedNameAndParameters} using {@link ElementType#OPERATION},
     * the fully-qualified name, and parameters (non-unique) of the {@link AbstractLanguageRegistryTest#currentElement}.
     */
    @Test
    public void testHasAnyOperationByFullyQualifiedNameAndParameters() {
        Call currentCall = (Call) currentElement;
        assertEquals(expectedHasAnyOperationByFullyQualifiedNameAndParameters, 
                languageRegistry.hasCall(ElementType.OPERATION, currentElement.getFullyQualifiedName(), 
                        currentCall.getParameters(), false), "Wrong return value of"
                                + " LanguageRegistry#hasCall(ElementType, String, ParameterType[], boolean) using"
                                + " operation, fully-qualified name, and parameters non-unique");
    }
    
    /**
     * Tests whether {@link LanguageRegistry#hasCall(ElementType, String, ParameterType[], boolean)} returns the same
     * value as {@link #expectedHasUniqueOperationBySimpleNameAndParameters} using {@link ElementType#OPERATION}, the 
     * the simple name, and parameters (unique) of the {@link AbstractLanguageRegistryTest#currentElement}.
     */
    @Test
    public void testHasUniqueOperationBySimpleNameAndParameters() {
        Call currentCall = (Call) currentElement;
        assertEquals(expectedHasUniqueOperationBySimpleNameAndParameters, 
                languageRegistry.hasCall(ElementType.OPERATION, currentElement.getName(), currentCall.getParameters(),
                        true), "Wrong return value of LanguageRegistry#hasCall(ElementType, String, ParameterType[],"
                                + " boolean) using operation, simple name, and paramters unique");
    }
    
    /**
     * Tests whether {@link LanguageRegistry#hasCall(ElementType, String, ParameterType[], boolean)} returns the same
     * value as {@link #expectedHasUniqueOperationByFullyQualifiedNameAndParameters} using
     * {@link ElementType#OPERATION}, the fully-qualified name, and parameters (unique) of the 
     * {@link AbstractLanguageRegistryTest#currentElement}.
     */
    @Test
    public void testHasUniqueOperationByFullyQualifiedNameAndParameters() {
        Call currentCall = (Call) currentElement;
        assertEquals(expectedHasUniqueOperationByFullyQualifiedNameAndParameters, 
                languageRegistry.hasCall(ElementType.OPERATION, currentElement.getFullyQualifiedName(),
                        currentCall.getParameters(), true), "Wrong return value of LanguageRegistry#hasCall("
                                + "ElementType, String, ParameterType[], boolean) using operation, fully-qualified"
                                + " name, and parameters unique");
    }
    
    /**
     * Tests whether {@link LanguageRegistry#hasCall(ElementType, String, ParameterType[], boolean)} returns the same
     * value as {@link #expectedHasAnyExtractorCallBySimpleNameAndParameters} using {@link ElementType#EXTRACTOR_CALL},
     * the simple name, and parameters (non-unique) of the {@link AbstractLanguageRegistryTest#currentElement}.
     */
    @Test
    public void testHasAnyExtractorCallBySimpleNameAndParameters() {
        Call currentCall = (Call) currentElement;
        assertEquals(expectedHasAnyExtractorCallBySimpleNameAndParameters, 
                languageRegistry.hasCall(ElementType.EXTRACTOR_CALL, currentElement.getName(), 
                        currentCall.getParameters(), false), "Wrong return value of"
                                + " LanguageRegistry#hasCall(ElementType, String, ParameterType[], boolean) using"
                                + " extractor call, simple name, and parameters non-unique");
    }
    
    /**
     * Tests whether {@link LanguageRegistry#hasCall(ElementType, String, ParameterType[], boolean)} returns the same
     * value as {@link #expectedHasAnyExtractorCallByFullyQualifiedNameAndParameters} using 
     * {@link ElementType#EXTRACTOR_CALL}, the fully-qualified name, and parameters (non-unique) of the 
     * {@link AbstractLanguageRegistryTest#currentElement}.
     */
    @Test
    public void testHasAnyExtractorCallByFullyQualifiedNameAndParameters() {
        Call currentCall = (Call) currentElement;
        assertEquals(expectedHasAnyExtractorCallByFullyQualifiedNameAndParameters, 
                languageRegistry.hasCall(ElementType.EXTRACTOR_CALL, currentElement.getFullyQualifiedName(), 
                        currentCall.getParameters(), false), "Wrong return value of"
                                + " LanguageRegistry#hasCall(ElementType, String, ParameterType[], boolean) using"
                                + " extractor call, fully-qualified name, and parameters non-unique");
    }
    
    /**
     * Tests whether {@link LanguageRegistry#hasCall(ElementType, String, ParameterType[], boolean)} returns the same
     * value as {@link #expectedHasUniqueExtractorCallBySimpleNameAndParameters} using
     * {@link ElementType#EXTRACTOR_CALL}, the simple name, and parameters (unique) of the
     * {@link AbstractLanguageRegistryTest#currentElement}.
     */
    @Test
    public void testHasUniqueExtractorCallBySimpleNameAndParameters() {
        Call currentCall = (Call) currentElement;
        assertEquals(expectedHasUniqueExtractorCallBySimpleNameAndParameters, 
                languageRegistry.hasCall(ElementType.EXTRACTOR_CALL, currentElement.getName(),
                        currentCall.getParameters(), true), "Wrong return value of"
                                + " LanguageRegistry#hasCall(ElementType, String, ParameterType[], boolean) using"
                                + " extractor call, simple name, and parameters unique");
    }
    
    /**
     * Tests whether {@link LanguageRegistry#hasCall(ElementType, String, ParameterType[], boolean)} returns the same
     * value as {@link #expectedHasUniqueExtractorCallByFullyQualifiedNameAndParameters} using
     * {@link ElementType#EXTRACTOR_CALL}, the fully-qualified name, and parameters (unique) of the
     * {@link AbstractLanguageRegistryTest#currentElement}.
     */
    @Test
    public void testHasUniqueExtractorCallByFullyQualifiedNameAndParameters() {
        Call currentCall = (Call) currentElement;
        assertEquals(expectedHasUniqueExtractorCallByFullyQualifiedNameAndParameters, 
                languageRegistry.hasCall(ElementType.EXTRACTOR_CALL, currentElement.getFullyQualifiedName(),
                        currentCall.getParameters(), true), "Wrong return value of"
                                + " LanguageRegistry#hasCall(ElementType, String, ParameterType[], boolean) using"
                                + " extractor call, fully-qualified name, and parameters unique");
    }

    /**
     * Tests whether {@link LanguageRegistry#hasCall(ElementType, String, ParameterType[], boolean)} returns the same
     * value as {@link #expectedHasAnyAnalysisCallBySimpleNameAndParameters} using {@link ElementType#ANALYSIS_CALL},
     * the simple name, and parameters (non-unique) of the {@link AbstractLanguageRegistryTest#currentElement}.
     */
    @Test
    public void testHasAnyAnalysisCallBySimpleNameAndParameters() {
        Call currentCall = (Call) currentElement;
        assertEquals(expectedHasAnyAnalysisCallBySimpleNameAndParameters, 
                languageRegistry.hasCall(ElementType.ANALYSIS_CALL, currentElement.getName(),
                        currentCall.getParameters(), false), "Wrong return value of"
                                + " LanguageRegistry#hasCall(ElementType, String, ParameterType[], boolean) using"
                                + " analysis call, simple name, and parameters non-unique");
    }
    
    /**
     * Tests whether {@link LanguageRegistry#hasCall(ElementType, String, ParameterType[], boolean)} returns the same
     * value as {@link #expectedHasAnyAnalysisCallByFullyQualifiedNameAndParameters} using
     * {@link ElementType#ANALYSIS_CALL}, the fully-qualified name, and parameters (non-unique) of the
     * {@link AbstractLanguageRegistryTest#currentElement}.
     */
    @Test
    public void testHasAnyAnalysisCallByFullyQualifiedNameAndParameters() {
        Call currentCall = (Call) currentElement;
        assertEquals(expectedHasAnyAnalysisCallByFullyQualifiedNameAndParameters, 
                languageRegistry.hasCall(ElementType.ANALYSIS_CALL, currentElement.getFullyQualifiedName(),
                        currentCall.getParameters(), false), "Wrong return value of"
                                + "LanguageRegistry#hasCall(ElementType, String, ParameterType[], boolean) using"
                                + " analysis call, fully-qualified name, and parameters non-unique");
    }
    
    /**
     * Tests whether {@link LanguageRegistry#hasCall(ElementType, String, ParameterType[], boolean)} returns the same
     * value as {@link #expectedHasUniqueAnalysisCallBySimpleNameAndParameters} using {@link ElementType#ANALYSIS_CALL},
     * the simple name, and parameters (unique) of the {@link AbstractLanguageRegistryTest#currentElement}.
     */
    @Test
    public void testHasUniqueAnalysisCallBySimpleNameAndParameters() {
        Call currentCall = (Call) currentElement;
        assertEquals(expectedHasUniqueAnalysisCallBySimpleNameAndParameters, 
                languageRegistry.hasCall(ElementType.ANALYSIS_CALL, currentElement.getName(),
                        currentCall.getParameters(), true), "Wrong return value of"
                                + " LanguageRegistry#hasCall(ElementType, String, ParameterType[], boolean) using"
                                + " analysis call, simple name, and parameters unique");
    }
    
    /**
     * Tests whether {@link LanguageRegistry#hasCall(ElementType, String, ParameterType[], boolean)} returns the same
     * value as {@link #expectedHasUniqueAnalysisCallByFullyQualifiedNameAndParameters} using
     * {@link ElementType#ANALYSIS_CALL}, the fully-qualified name, and parameters (unique) of the
     * {@link AbstractLanguageRegistryTest#currentElement}.
     */
    @Test
    public void testHasUniqueAnalysisCallByFullyQualifiedNameAndParameters() {
        Call currentCall = (Call) currentElement;
        assertEquals(expectedHasUniqueAnalysisCallByFullyQualifiedNameAndParameters, 
                languageRegistry.hasCall(ElementType.ANALYSIS_CALL, currentElement.getFullyQualifiedName(),
                        currentCall.getParameters(), true), "Wrong return value of"
                                + " LanguageRegistry#hasCall(ElementType, String, ParameterType[], boolean) using"
                                + " analysis call, fully-qualified name, and parameters unique");
    }
}
