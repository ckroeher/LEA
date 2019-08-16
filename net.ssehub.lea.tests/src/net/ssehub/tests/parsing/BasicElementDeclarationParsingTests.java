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

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runners.Parameterized.Parameters;

import net.ssehub.lea.AnalysisDefinition;
import net.ssehub.lea.ElementDeclaration;

/**
 * This class contains basic tests for parsing {@link ElementDeclaration}s.
 * 
 * @author Christian Kroeher
 *
 */
public class BasicElementDeclarationParsingTests extends AbstractParserTest {
    
    /**
     * The expected results for each test data file. Each entry has the following elements:
     * <ul>
     * <li>The path and name of the desired test data file relative to the {@link #TESTDATA_DIRECTORY}</li>
     * <li>The definition of whether it is expected that the actual analysis definition is valid (<code>true</code>) or
     *     not (<code>false</code>)</li>
     * <li>The expected number of element declarations in {@link AnalysisDefinition#getElementDeclarations()}</li>
     * <li>The expected number of change identifier assignments in 
     *     {@link AnalysisDefinition#getChangeIdentifierAssignments()}</li>
     * <li>The expected generic type of the {@link ElementDeclaration}</li>
     * <li>The expected parameter type of the {@link ElementDeclaration}</li>
     * <li>The definition of whether it is expected that the {@link ElementDeclaration} is defined as a set
     *     (<code>true</code>) or not (<code>false</code>).</li>
     * <li>The expected name of the {@link ElementDeclaration}</li>
     * </ul>
     */
    private static final Object[][] EXPECTED_RESULTS = new Object[][] {
        // Artifact declarations
        {"basic/ArtifactDeclaration.lea", true, 1, 0, "Artifact", "File", false, "name"},
        {"basic/ArtifactDeclarationWithElement.lea", true, 1, 0, "Artifact", "File", false, "name"},
        {"basic/ArtifactDeclarationWithOperation.lea", true, 1, 0, "Artifact", "File", false, "name"},
        {"basic/ArtifactSetDeclaration.lea", true, 1, 0, "Artifact", "File", true, "name"},
        {"basic/ArtifactSetDeclarationWithElement.lea", true, 1, 0, "Artifact", "File", true, "name"},
        {"basic/ArtifactSetDeclarationWithOperation.lea", true, 1, 0, "Artifact", "File", true, "name"},
        {"basic/ArtifactSetIterationDeclaration.lea", true, 1, 0, "Artifact", "File", true, "name"},
        // Fragment declarations
        {"basic/FragmentDeclaration.lea", true, 1, 0, "Fragment", "CodeBlock", false, "name"},
        {"basic/FragmentDeclarationWithElement.lea", true, 1, 0, "Fragment", "CodeBlock", false, "name"},
        {"basic/FragmentDeclarationWithOperation.lea", true, 1, 0, "Fragment", "CodeBlock", false, "name"},
        {"basic/FragmentSetDeclaration.lea", true, 1, 0, "Fragment", "CodeBlock", true, "name"},
        {"basic/FragmentSetDeclarationWithElement.lea", true, 1, 0, "Fragment", "CodeBlock", true, "name"},
        {"basic/FragmentSetDeclarationWithOperation.lea", true, 1, 0, "Fragment", "CodeBlock", true, "name"},
        {"basic/FragmentSetIterationDeclaration.lea", true, 1, 0, "Fragment", "CodeBlock", true, "name"},
        // Result declarations
        {"basic/ResultDeclaration.lea", true, 1, 0, "Result", "DeadBlock", false, "name"},
        {"basic/ResultDeclarationWithElement.lea", true, 1, 0, "Result", "DeadBlock", false, "name"},
        {"basic/ResultDeclarationWithOperation.lea", true, 1, 0, "Result", "DeadBlock", false, "name"},
        {"basic/ResultSetDeclaration.lea", true, 1, 0, "Result", "DeadBlock", true, "name"},
        {"basic/ResultSetDeclarationWithElement.lea", true, 1, 0, "Result", "DeadBlock", true, "name"},
        {"basic/ResultSetDeclarationWithOperation.lea", true, 1, 0, "Result", "DeadBlock", true, "name"},
        {"basic/ResultSetIterationDeclaration.lea", true, 1, 0, "Result", "DeadBlock", true, "name"}
    };
    
    /**
     * The expected generic type of the {@link ElementDeclaration}.
     */
    private String expectedGenericType;
    
    /**
     * The expected parameter type of the {@link ElementDeclaration}.
     */
    private String expectedParameterType;
    
    /**
     * The definition of whether it is expected that the {@link ElementDeclaration} is defined as a set
     * (<code>true</code>) or not (<code>false</code>).
     */
    private boolean expectedSetDefinition;
    
    /**
     * The expected name of the {@link ElementDeclaration}.
     */
    private String expectedName;
    
    // TODO setIteration? initialization=Assignment?
    
    /**
     * Creates a new {@link BasicElementDeclarationParsingTests} instance.
     * 
     * @param relativeTestModelFilePath the path and name of the desired test data file relative to the
     *        {@link #TESTDATA_DIRECTORY}; should never be <code>null</code>
     * @param expectedAnalysisDefinitionIsValid the definition of whether it is expected that the actual analysis
     *        definition is valid (<code>true</code>) or not (<code>false</code>)
     * @param expectedNumberOfElementDeclarations the expected number of element declarations in 
     *        {@link AnalysisDefinition#getElementDeclarations()}
     * @param expectedNumberOfChangeIdentifierAssignments the expected number of change identifier assignments in 
     *        {@link AnalysisDefinition#getChangeIdentifierAssignments()}
     * @param expectedGenericType the expected generic type of the {@link ElementDeclaration}
     * @param expectedParameterType the expected parameter type of the {@link ElementDeclaration}
     * @param expectedSetDefinition the definition of whether it is expected that the {@link ElementDeclaration} is
     *        defined as a set (<code>true</code>) or not (<code>false</code>)
     * @param expectedName the expected name of the {@link ElementDeclaration}
     */
//CHECKSTYLE:OFF
    public BasicElementDeclarationParsingTests(String relativeTestModelFilePath, boolean expectedAnalysisDefinitionIsValid,
            int expectedNumberOfElementDeclarations, int expectedNumberOfChangeIdentifierAssignments,
            String expectedGenericType, String expectedParameterType, boolean expectedSetDefinition,
            String expectedName) {
        super(relativeTestModelFilePath, expectedAnalysisDefinitionIsValid, expectedNumberOfElementDeclarations,
                expectedNumberOfChangeIdentifierAssignments);
        this.expectedGenericType = expectedGenericType;
        this.expectedParameterType = expectedParameterType;
        this.expectedSetDefinition = expectedSetDefinition;
        this.expectedName = expectedName;
    }
//CHECKSTYLE:ON
    /**
     * Returns the expected results as parameters for the tests defined in this class and the super-class.
     * 
     * @return the {@link #EXPECTED_RESULTS} as an object-array list
     */
    @Parameters
    public static List<Object[]> getTestData() {
        return Arrays.asList(EXPECTED_RESULTS);
    }
    
    /**
     * Tests whether the {@link ElementDeclaration#getGenericTyp()} equals the {@link #expectedGenericType}.
     */
    @Test
    public void testCorrectGenericType() {
        ElementDeclaration elementDeclaration = getActualAnalysisDefinition().getElementDeclarations().get(0);
        assertEquals(expectedGenericType, elementDeclaration.getGenericTyp(), "Unexpected generic type");
    }
    
    /**
     * Tests whether the {@link ElementDeclaration#getParameterType()} equals the {@link #expectedParameterType}.
     */
    @Test
    public void testCorrectParameterType() {
        ElementDeclaration elementDeclaration = getActualAnalysisDefinition().getElementDeclarations().get(0);
        assertEquals(expectedParameterType, elementDeclaration.getParameterType(), "Unexpected parameter type");
    }
    
    /**
     * Tests whether the {@link ElementDeclaration#getSet()} is available as defined by {@link #expectedSetDefinition}.
     */
    @Test
    public void testCorrectSetDefinition() {
        ElementDeclaration elementDeclaration = getActualAnalysisDefinition().getElementDeclarations().get(0);
        assertEquals(expectedSetDefinition, (elementDeclaration.getSet() != null), 
                "Unexpected (un)availability of set definition");
    }
    
    /**
     * Tests whether the {@link ElementDeclaration#getName()} equals the {@link #expectedName}.
     */
    @Test
    public void testCorrectName() {
        ElementDeclaration elementDeclaration = getActualAnalysisDefinition().getElementDeclarations().get(0);
        assertEquals(expectedName, elementDeclaration.getName(), "Unexpected name");
    }

}