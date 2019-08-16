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
package net.ssehub.tests.parsing;

import java.util.Arrays;
import java.util.List;

import org.junit.runners.Parameterized.Parameters;

import net.ssehub.lea.AnalysisDefinition;

/**
 * This class contains basic tests for parsing {@link AnalysisDefinition}s.
 * 
 * @author Christian Kroeher
 *
 */
public class BasicAnalysisDefinitionTests extends AbstractParserTest {
    
    /**
     * The expected results for each test data file. Each entry has the following elements:
     * <ul>
     * <li>The path and name of the desired test data file relative to the {@link #TESTDATA_DIRECTORY}</li>
     * <li>The definition of whether it is expected that the actual analysis definition is valid (<code>true</code>) or
     *     not (<code>false</code>)</li>
     * <li>The expected number of element declarations in {@link AnalysisDefinition#getElementDeclarations()}</li>
     * <li>The expected number of change identifier assignments in 
     *     {@link AnalysisDefinition#getChangeIdentifierAssignments()}</li>
     * </ul>
     */
    private static final Object[][] EXPECTED_RESULTS = new Object[][] {
        {"basic/EmptyFile.lea", true, 0, 0},
        {"basic/InvalidModel.lea", false, 0, 0}
    };

    /**
     * Creates a new {@link BasicAnalysisDefinitionTests} instance.
     * 
     * @param relativeTestModelFilePath the path and name of the desired test data file relative to the
     *        {@link #TESTDATA_DIRECTORY}; should never be <code>null</code>
     * @param expectedAnalysisDefinitionIsValid the definition of whether it is expected that the actual analysis
     *        definition is valid (<code>true</code>) or not (<code>false</code>)
     * @param expectedNumberOfElementDeclarations the expected number of element declarations in 
     *        {@link AnalysisDefinition#getElementDeclarations()}
     * @param expectedNumberOfChangeIdentifierAssignments the expected number of change identifier assignments in 
     *        {@link AnalysisDefinition#getChangeIdentifierAssignments()}
     */
    public BasicAnalysisDefinitionTests(String relativeTestModelFilePath, boolean expectedAnalysisDefinitionIsValid,
            int expectedNumberOfElementDeclarations, int expectedNumberOfChangeIdentifierAssignments) {
        super(relativeTestModelFilePath, expectedAnalysisDefinitionIsValid, expectedNumberOfElementDeclarations,
                expectedNumberOfChangeIdentifierAssignments);
    }

    /**
     * Returns the expected results as parameters for the tests defined in the super-class.
     * 
     * @return the {@link #EXPECTED_RESULTS} as an object-array list
     */
    @Parameters
    public static List<Object[]> getTestData() {
        return Arrays.asList(EXPECTED_RESULTS);
    }
}
