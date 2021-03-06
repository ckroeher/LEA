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

import org.eclipse.emf.common.util.EList;
import org.junit.Before;
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
        {"basic/ArtifactDeclarationWithElement.lea", true, 2, 0, "Artifact", "File", false, "name"},
        {"basic/ArtifactDeclarationWithOperation.lea", true, 1, 0, "Artifact", "File", false, "name"},
        {"basic/ArtifactSetDeclaration.lea", true, 1, 0, "Artifact", "File", true, "name"},
        {"basic/ArtifactSetDeclarationWithElement.lea", true, 2, 0, "Artifact", "File", true, "name"},
        {"basic/ArtifactSetDeclarationWithOperation.lea", true, 1, 0, "Artifact", "File", true, "name"},
        {"basic/ArtifactSetIterationDeclaration.lea", true, 2, 0, "Artifact", "File", true, "name"},
        // Fragment declarations
        {"basic/FragmentDeclaration.lea", true, 1, 0, "Fragment", "CodeBlock", false, "name"},
        {"basic/FragmentDeclarationWithElement.lea", false, 2, 0, "Fragment", "CodeBlock", false, "name"},
        {"basic/FragmentDeclarationWithOperation.lea", false, 1, 0, "Fragment", "CodeBlock", false, "name"},
        {"basic/FragmentSetDeclaration.lea", true, 1, 0, "Fragment", "CodeBlock", true, "name"},
        {"basic/FragmentSetDeclarationWithElement.lea", true, 2, 0, "Fragment", "CodeBlock", true, "name"},
        {"basic/FragmentSetDeclarationWithOperation.lea", true, 1, 0, "Fragment", "CodeBlock", true, "name"},
        {"basic/FragmentSetIterationDeclaration.lea", true, 2, 0, "Fragment", "CodeBlock", true, "name"},
        // Result declarations
        {"basic/ResultDeclaration.lea", true, 1, 0, "Result", "DeadBlock", false, "name"},
        {"basic/ResultDeclarationWithElement.lea", false, 2, 0, "Result", "DeadBlock", false, "name"},
        {"basic/ResultDeclarationWithOperation.lea", false, 1, 0, "Result", "DeadBlock", false, "name"},
        {"basic/ResultSetDeclaration.lea", true, 1, 0, "Result", "DeadBlock", true, "name"},
        {"basic/ResultSetDeclarationWithElement.lea", true, 2, 0, "Result", "DeadBlock", true, "name"},
        {"basic/ResultSetDeclarationWithOperation.lea", true, 1, 0, "Result", "DeadBlock", true, "name"},
        {"basic/ResultSetIterationDeclaration.lea", true, 2, 0, "Result", "DeadBlock", true, "name"}
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
     * The actual {@link ElementDeclaration}. This value is set in {@link #setActualElementDeclaration()}.
     */
    private ElementDeclaration actualElementDeclaration;
    
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
     * Sets the value of {@link #actualElementDeclaration} based on the current {@link AnalysisDefinition} from
     * {@link #getActualAnalysisDefinition()}.
     */
    @Before
    public void setActualElementDeclaration() {
        // As element names must be unique, the element declaration with the expected name is the one to test
        AnalysisDefinition actualAnalysisDefinition = getActualAnalysisDefinition();
        if (actualAnalysisDefinition != null) {            
            EList<ElementDeclaration> actualElementDeclarations = actualAnalysisDefinition.getElementDeclarations();
            int actualElementDeclarationCounter = 0;
            while (actualElementDeclaration == null 
                    && actualElementDeclarationCounter < actualElementDeclarations.size()) {
                if (actualElementDeclarations.get(actualElementDeclarationCounter).getName().equals(expectedName)) {
                    actualElementDeclaration = actualElementDeclarations.get(actualElementDeclarationCounter);
                }
                actualElementDeclarationCounter++;
            }
        } else {
            System.err.println("BasicElementDeclarationParsingTests failed due to missing actual analysis definition");
        }
    }
    
    /**
     * Tests whether the generic type of the {@link #actualElementDeclaration} equals the {@link #expectedGenericType}.
     */
    @Test
    public void testCorrectGenericType() {
        assertEquals(expectedGenericType, actualElementDeclaration.getGenericTyp(), "Unexpected generic type");
    }
    
    /**
     * Tests whether the parameter type of the {@link #actualElementDeclaration} equals the 
     * {@link #expectedParameterType}.
     */
    @Test
    public void testCorrectParameterType() {
        assertEquals(expectedParameterType, actualElementDeclaration.getParameterType(), "Unexpected parameter type");
    }
    
    /**
     * Tests whether the set definition of the {@link #actualElementDeclaration} is available as defined by 
     * {@link #expectedSetDefinition}.
     */
    @Test
    public void testCorrectSetDefinition() {
        assertEquals(expectedSetDefinition, (actualElementDeclaration.getSet() != null), 
                "Unexpected (un)availability of set definition");
    }
    
    /**
     * Tests whether the name of the {@link #actualElementDeclaration} equals the {@link #expectedName}.
     */
    @Test
    public void testCorrectName() {
        assertEquals(expectedName, actualElementDeclaration.getName(), "Unexpected name");
    }

}