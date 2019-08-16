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
import net.ssehub.lea.ChangeIdentifierAssignment;

/**
 * This class contains basic tests for parsing {@link ChangeIdentifierAssignment}s.
 * 
 * @author Christian Kroeher
 *
 */
public class BasicChangeIdentifierAssignmentTests extends AbstractParserTest {
    
    /**
     * The expected results for each test data file. Each entry has the following elements:
     * <ul>
     * <li>The path and name of the desired test data file relative to the {@link #TESTDATA_DIRECTORY}</li>
     * <li>The definition of whether it is expected that the actual analysis definition is valid (<code>true</code>) or
     *     not (<code>false</code>)</li>
     * <li>The expected number of element declarations in {@link AnalysisDefinition#getElementDeclarations()}</li>
     * <li>The expected number of change identifier assignments in 
     *     {@link AnalysisDefinition#getChangeIdentifierAssignments()}</li>
     * <li>The expected name of the change identifier</li>
     * <li>The expected number of elements to which the changed identifier is assigned to</li>
     * <li>The expected elements (names) to which the changed identifier is assigned to</li>
     * </ul>
     */
    private static final Object[][] EXPECTED_RESULTS = new Object[][] {
        {"basic/ChangeIdentifierAssignmentToSingleElement.lea", true, 1, 1, "FileChangeIdentifier", 1, 
            new String[] {"codeFiles"}},
        {"basic/ChangeIdentifierAssignmentToMultipleElements.lea", true, 3, 1, "FileChangeIdentifier", 3, 
            new String[] {"codeFiles", "buildFiles", "vmFiles"}}
    };
    
    /**
     * The expected name of the change identifier.
     */
    private String expectedChangeIdentifierName;
    
    /**
     * The expected number of elements to which the changed identifier is assigned to.
     */
    private int expectedNumberOfAssignedElements;
    
    /**
     * The expected elements (names) to which the changed identifier is assigned to.
     */
    private String[] expectedAssignedElement;

    /**
     * Creates a new {@link BasicChangeIdentifierAssignmentTests} instance.
     * 
     * @param relativeTestModelFilePath the path and name of the desired test data file relative to the
     *        {@link #TESTDATA_DIRECTORY}; should never be <code>null</code>
     * @param expectedAnalysisDefinitionIsValid the definition of whether it is expected that the actual analysis
     *        definition is valid (<code>true</code>) or not (<code>false</code>)
     * @param expectedNumberOfElementDeclarations the expected number of element declarations in 
     *        {@link AnalysisDefinition#getElementDeclarations()}
     * @param expectedNumberOfChangeIdentifierAssignments the expected number of change identifier assignments in 
     *        {@link AnalysisDefinition#getChangeIdentifierAssignments()}
     * @param expectedChangeIdentifierName the expected name of the change identifier
     * @param expectedNumberOfAssignedElements the expected number of elements to which the changed identifier is
     *        assigned to
     * @param expectedAssignedElements the expected elements (names) to which the changed identifier is assigned to
     */
//CHECKSTYLE:OFF
    public BasicChangeIdentifierAssignmentTests(String relativeTestModelFilePath,
            boolean expectedAnalysisDefinitionIsValid, int expectedNumberOfElementDeclarations,
            int expectedNumberOfChangeIdentifierAssignments, String expectedChangeIdentifierName, 
            int expectedNumberOfAssignedElements, String[] expectedAssignedElements) {
        super(relativeTestModelFilePath, expectedAnalysisDefinitionIsValid, expectedNumberOfElementDeclarations,
                expectedNumberOfChangeIdentifierAssignments);
        this.expectedChangeIdentifierName = expectedChangeIdentifierName;
        this.expectedNumberOfAssignedElements = expectedNumberOfAssignedElements;
        this.expectedAssignedElement = expectedAssignedElements;
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
     * Tests whether the change identifier name equals the {@link #expectedChangeIdentifierName}.
     */
    @Test
    public void testCorrectChangeIdentifierName() {
        ChangeIdentifierAssignment assignment = getActualAnalysisDefinition().getChangeIdentifierAssignments().get(0);
        assertEquals(expectedChangeIdentifierName, assignment.getIdentifier(), "Unexpected change identifier name");
    }
    
    /**
     * Tests whether the number of elements to which the change identifier is assigned to equals the 
     * {@link #expectedNumberOfAssignedElements}.
     */
    @Test
    public void testCorrectNumberOfAssignedElements() {
        ChangeIdentifierAssignment assignment = getActualAnalysisDefinition().getChangeIdentifierAssignments().get(0);
        assertEquals(expectedNumberOfAssignedElements, assignment.getElements().size(), 
                "Unexpected number of elements to which the change identifier is assigned to");
    }
    
    /**
     * Tests whether the elements to which the change identifier is assigned to equal the
     * {@link #expectedAssignedElement}.
     */
    @Test
    public void testCorrectAssignedElements() {
        ChangeIdentifierAssignment assignment = getActualAnalysisDefinition().getChangeIdentifierAssignments().get(0);
        List<String> assignedElements = assignment.getElements();
        for (int i = 0; i < assignedElements.size(); i++) {
            assertEquals(expectedAssignedElement[i], assignedElements.get(i), 
                    "Unexpected element \"" +  assignedElements.get(i) 
                    + "\" to which the change identifier is assigned to");
        }
    }

}
