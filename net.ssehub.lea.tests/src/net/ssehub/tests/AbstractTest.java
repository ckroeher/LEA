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
package net.ssehub.tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.eclipse.xtext.testing.validation.ValidationTestHelper;

import com.google.inject.Inject;

import net.ssehub.lea.AnalysisDefinition;
import net.ssehub.lea.ChangeIdentifierAssignment;
import net.ssehub.lea.ElementDeclaration;
import net.ssehub.lea.Iteration;
import net.ssehub.lea.SetDefinition;

/**
 * This abstract class provides common attributes and methods for testing LEA.
 * 
 * @author Christian Kroeher
 *
 */
public abstract class AbstractTest {
    
    /**
     * This inner class defines a custom exception that is thrown, if a method provided by the {@link AbstractTest}
     * class fails. This enables propagating a common error to the respective unit test where it is actually caused.
     * 
     * @author Christian Kroeher
     *
     */
    protected class AbstractTestException extends Exception {
        
        /**
         * The id of this serializable class.
         */
        private static final long serialVersionUID = -7588648465766953583L;

        /**
         * Constructs a new {@link AbstractTestException} with the given message.
         * 
         * @param message the description of the error causing this exception
         */
        private AbstractTestException(String message) {
            super(message);
        }
        
        /**
         * Constructs a new {@link AbstractTestException} with the given message and cause.
         * 
         * @param message the specific description of the error causing this exception
         * @param cause the {@link Throwable} representing the actual cause of this exception
         */
        private AbstractTestException(String message, Throwable cause) {
            super(message, cause);
        }
    }
    
    /**
     * This enumeration defines the supported test types, which indicate where to find the desired test data file in the
     * {@link AbstractTest#TESTDATA_DIRECTORY}.
     * 
     * @see AbstractTest#getModelString(TestType, String)
     * 
     * @author Christian Kroeher
     *
     */
    public enum TestType { BASIC }

    /**
     * This {@link File} denotes the root directory in which the test data files are located.
     */
    private static final File TESTDATA_DIRECTORY = new File("./testdata");
    
    /**
     * This {@link ValidationTestHelper} enables checks of parser results to detect syntactical errors.
     * 
     * @see AbstractTest#assertSyntacticalCorrectness(AnalysisDefinition)
     */
    @Inject
    private ValidationTestHelper validationTestHelper;
    
    /**
     * Retrieves the content of a test data file. The file is determined by concatenating the root test data directory
     * {@link AbstractTest#TESTDATA_DIRECTORY}, the given test type as a string, and the given file name.
     * 
     * @param testType the {@link TestType} denoting the sub-directory of the root test data directory  
     * @param fileName the name of the desired test data file located in the directory denoted by the test type
     * @return the content of the test data file as a single string
     * @throws AbstractTestException if the file could not be found or reading the content failed
     */
    protected String getModelString(TestType testType, String fileName) throws AbstractTestException {
        File testFile = getTestFile(testType, fileName);
        StringBuilder modelStringBuilder = new StringBuilder();
        
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;       
        try {
            fileReader = new FileReader(testFile);
            bufferedReader = new BufferedReader(fileReader);
            String fileLine;
            while ((fileLine = bufferedReader.readLine()) != null) {
                modelStringBuilder.append(fileLine);
                modelStringBuilder.append("\n");
            }
        } catch (IOException | OutOfMemoryError e) {
            throw new AbstractTestException("Reading content from file \"" + testFile.getAbsolutePath() + "\" failed",
                    e);
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    throw new AbstractTestException("Closing file reader for \"" + testFile.getAbsolutePath() 
                            + "\" failed", e);
                }
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    throw new AbstractTestException("Closing buffered reader for \"" + testFile.getAbsolutePath() 
                            + "\" failed", e);
                }
            }
        }
        
        return modelStringBuilder.toString();
    }
    
    /**
     * Retrieves the desired test data file. The file is determined by concatenating the root test data directory
     * {@link AbstractTest#TESTDATA_DIRECTORY}, the given test type as a string, and the given file name.
     * 
     * @param testType the {@link TestType} denoting the sub-directory of the root test data directory  
     * @param fileName the name of the desired test data file located in the directory denoted by the test type
     * @return the {@link File} containing the test data
     * @throws AbstractTestException if the file does not exist or denotes a directory
     */
    private File getTestFile(TestType testType, String fileName) throws AbstractTestException {
        String relativeFilePath = testType + "/" + fileName + ".lea";
        File testFile = new File(TESTDATA_DIRECTORY, relativeFilePath);
        if (!testFile.exists()) {
            throw new AbstractTestException("The file \"" + testFile.getAbsolutePath() + "\" does not exist");
        }
        if (testFile.isDirectory()) {
            throw new AbstractTestException("The file \"" + testFile.getAbsolutePath() + "\" is not a file");
        }
        return testFile;
    }
    
    /**
     * Calls the {@link #validationTestHelper} to check the given {@link AnalysisDefinition} for syntactical
     * correctness. This check is an assertion, which causes the test runner to stop, if errors are detected. Hence,
     * this method behaves like a typical JUnit assertion call.
     *  
     * @param analysis the {@link AnalysisDefinition} to check for syntactical errors
     */
    protected void assertSyntacticalCorrectness(AnalysisDefinition analysis) {
        /* 
         * The parse result always returns without any errors, although syntactical errors may exist. It simply parses
         * as much as possible and stops, if there are no matching parsing rules. Hence, we have to perform a validation
         * test first, to ensure, that there are no errors, meaning that the model is syntactically correct.
         */
        validationTestHelper.assertNoErrors(analysis);
    }
    
    /**
     * Tests the given {@link ElementDeclaration} based on the other given parameters for correctness.
     * 
     * @param element the {@link ElementDeclaration} to check for correctness
     * @param expectedGenericType the string representation of the generic type; typically one of "Artifact",
     *        "Fragment", or "Result"
     * @param expectedSet the definition of whether the given declaration should contain a {@link SetDefinition}
     *        (<code>true</code>), or not (<code>false</code>)
     * @param expectedIteration the definition of whether the given declaration should contain a {@link SetDefinition}
     *        with an {@link Iteration} (<code>true</code>), or not (<code>false</code>)
     * @param expectedOperation the definition of whether the given declaration should contain an 
     *        {@link net.ssehub.lea.Operation} as {@link net.ssehub.lea.Assignment} (<code>true</code>), or not
     *        (<code>false</code>)
     * @param expectedElement the definition of whether the given declaration should contain another name (ID) as
     *        {@link net.ssehub.lea.Assignment} (<code>true</code>), or not (<code>false</code>)
     * @return <code>null</code>, if the declaration is correct, or a textual description of the detected problems
     */
//CHECKSTYLE:OFF
    protected String testCorrectElementDeclaration(ElementDeclaration element, String expectedGenericType,
            boolean expectedSet, boolean expectedIteration, boolean expectedOperation, boolean expectedElement) {
        String problemDescription = null;
        StringBuilder problemDescriptionBuilder = new StringBuilder();
        if (!element.getGenericTyp().equals(expectedGenericType)) {
            problemDescriptionBuilder.append("Expected generic type \"");
            problemDescriptionBuilder.append(expectedGenericType);
            problemDescriptionBuilder.append("\", but was \"");
            problemDescriptionBuilder.append(element.getGenericTyp());
            problemDescriptionBuilder.append("\"\n");
        }
        if (element.getParameterType() == null || element.getParameterType().isBlank()) {
            problemDescriptionBuilder.append("Missing parameter type \"<T>\"\n");
        }
        if (expectedSet && element.getSet() == null) {
            problemDescriptionBuilder.append("Missing set definition\n");
        }
        if (!expectedSet && element.getSet() != null) {
            problemDescriptionBuilder.append("Unexpected set definition\n");
        }
        if (expectedIteration && (element.getSet() == null || element.getSet().getIteration() == null)) {
            problemDescriptionBuilder.append("Missing iteration in set definition\n");
        }
        if (!expectedIteration && element.getSet() != null && element.getSet().getIteration() != null) {
            problemDescriptionBuilder.append("Unexpected iteration in set definition\n");
        }
        if (element.getName().isBlank()) {
            problemDescriptionBuilder.append("Missing element name\n");
        }
        if (!expectedOperation && !expectedElement && element.getInitialization() != null) {
            problemDescriptionBuilder.append("Unexpected initialization\n");
        }
        if (expectedOperation && element.getInitialization() == null) {
            problemDescriptionBuilder.append("Missing initialization via operation\n");
        }
        if (expectedOperation && element.getInitialization() != null 
                && element.getInitialization().getOperation() == null) {
            problemDescriptionBuilder.append("Initialization is not an operation\n");
        }
        if (expectedElement && element.getInitialization() == null) {
            problemDescriptionBuilder.append("Missing initialization via element\n");
        }
        if (expectedElement && element.getInitialization() != null 
                && element.getInitialization().getElement() == null) {
            problemDescriptionBuilder.append("Initialization is not an element\n");
        }
        // Build the description string, if problems were found and described
        if (problemDescriptionBuilder.length() > 0) {
            problemDescription = problemDescriptionBuilder.toString();
        }
        return problemDescription;
    }
//CHECKSTYLE:ON
    /**
     * Tests the given {@link ChangeIdentifierAssignment} based on the other given parameters for correctness.
     * 
     * @param assignment the {@link ChangeIdentifierAssignment} to check for correctness
     * @param expectedElementCount the expected number of elements to which the identifier is assigned
     * @return <code>null</code>, if the assignment is correct, or a textual description of the detected problems
     */
    protected String testCorrectChangeIdenitierAssignment(ChangeIdentifierAssignment assignment,
            int expectedElementCount) {
        String problemDescription = null;
        StringBuilder problemDescriptionBuilder = new StringBuilder();
        if (assignment.getIdentifier().isBlank()) {
            problemDescriptionBuilder.append("Missing change identifier name\n");
        }
        if (assignment.getElements().size() != expectedElementCount) {
            problemDescriptionBuilder.append("Expected elements to assign to ");
            problemDescriptionBuilder.append(expectedElementCount);
            problemDescriptionBuilder.append(", but was ");
            problemDescriptionBuilder.append(assignment.getElements().size());
        }
        // Build the description string, if problems were found and described
        if (problemDescriptionBuilder.length() > 0) {
            problemDescription = problemDescriptionBuilder.toString();
        }
        return problemDescription;
    }
}
