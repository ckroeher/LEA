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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.eclipse.xtext.testing.util.ParseHelper;
import org.eclipse.xtext.testing.validation.ValidationTestHelper;
import org.eclipse.xtext.validation.Issue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.google.inject.Injector;

import net.ssehub.LeaStandaloneSetup;
import net.ssehub.integration.Call;
import net.ssehub.integration.ChangeIdentifier;
import net.ssehub.integration.ElementType;
import net.ssehub.integration.LanguageElement;
import net.ssehub.integration.LanguageElementException;
import net.ssehub.integration.LanguageRegistry;
import net.ssehub.integration.ParameterType;
import net.ssehub.lea.AnalysisDefinition;
import net.ssehub.lea.ChangeIdentifierAssignment;
import net.ssehub.lea.ElementDeclaration;

/**
 * This abstract class provides common attributes and methods for testing the parsing of LEA.
 * 
 * @author Christian Kroeher
 *
 */
@RunWith(Parameterized.class)
public abstract class AbstractParserTest {
    
    /**
     * This {@link File} denotes the root directory in which the test data files are located.<br>
     * <br>
     * Path: <code>./testdata/parsing</code>
     */
    private static final File TESTDATA_DIRECTORY = new File("./testdata/parsing");
    
    /**
     * The {@link File} denoting the source plug-in of the {@link Class} from which a {@link LanguageElement} should be
     * created. This is just a dummy file as it has no impact on the actual creation, but is only used as a constructor
     * parameter.
     */
    private static final File SOURCE_PLUGIN = new File("./");
    
    /**
     * This {@link ParseHelper} enables parsing of {@link AnalysisDefinition}s passed as a single string. The actual
     * instance is defined during {@link #setup()}.
     */
    private static ParseHelper<AnalysisDefinition> parseHelper;
    
    /**
     * This {@link ValidationTestHelper} enables checks of parser results to detect syntactical and validation errors.
     * The actual instance is defined during {@link #setup()}.
     * 
     * @see AbstractTest#assertSyntacticalCorrectness(AnalysisDefinition)
     */
    private static ValidationTestHelper validationTestHelper;
    
    /**
     * The @{@link String} denoting the path and name of the desired test data file relative to the
     * {@link #TESTDATA_DIRECTORY}. The current value is set in {@link #AbstractParserTest(String)} and used to
     * {@link #parse()} the content of that file to create the {@link #actualAnalysisDefinition}.
     */
    private String relativeTestModelFilePath;
    
    /**
     * The definition of whether it is expected that the current {@link #actualAnalysisDefinition} is valid
     * (<code>true</code>) or not (<code>false</code>). The current value is set in {@link #AbstractParserTest(String)}
     */
    private boolean expectedAnalysisDefinitionIsValid;
    
    /**
     * The expected number of element declarations in {@link AnalysisDefinition#getElementDeclarations()}. The current
     * value is set in {@link #AbstractParserTest(String)}.
     */
    private int expectedNumberOfElementDeclarations;
    
    /**
     * The expected number of change identifier assignments in 
     * {@link AnalysisDefinition#getChangeIdentifierAssignments()}. The current value is set in 
     * {@link #AbstractParserTest(String)}.
     */
    private int expectedNumberOfChangeIdentifierAssignments;
    
    /**
     * The {@link AnalysisDefinition} created by the last call of {@link #parse(String)} or <code>null</code>, if an
     * error occurred during parsing.
     */
    private AnalysisDefinition actualAnalysisDefinition;
    
    /**
     * Creates a new {@link AbstractParserTest} instance.
     * 
     * @param relativeTestModelFilePath the path and name of the desired test data file relative to the
     *        {@link #TESTDATA_DIRECTORY} to be set as the current value of {@link #relativeTestModelFilePath}; should
     *        never be <code>null</code>
     * @param expectedAnalysisDefinitionIsValid the definition of whether it is expected that the current 
     *        {@link #actualAnalysisDefinition} is valid (<code>true</code>) or not (<code>false</code>)
     * @param expectedNumberOfElementDeclarations the expected number of element declarations in 
     *        {@link AnalysisDefinition#getElementDeclarations()}
     * @param expectedNumberOfChangeIdentifierAssignments the expected number of change identifier assignments in 
     *        {@link AnalysisDefinition#getChangeIdentifierAssignments()}
     */
    public AbstractParserTest(String relativeTestModelFilePath, boolean expectedAnalysisDefinitionIsValid,
            int expectedNumberOfElementDeclarations, int expectedNumberOfChangeIdentifierAssignments) {
        this.relativeTestModelFilePath = relativeTestModelFilePath;
        this.expectedAnalysisDefinitionIsValid = expectedAnalysisDefinitionIsValid;
        this.expectedNumberOfElementDeclarations = expectedNumberOfElementDeclarations;
        this.expectedNumberOfChangeIdentifierAssignments = expectedNumberOfChangeIdentifierAssignments;
    }

    /**
     * Prepares the required elements for the unit tests.
     */
    @SuppressWarnings("unchecked")
    @BeforeClass
    public static void setup() {
        /*
         * In general, it is recommended to use dependency injection to retrieve the correct instances of the parser
         * and validation test helper. However, this requires running this class with XtextRunner, which is not possible
         * due to the use of parameterized tests. Hence, we have to retrieve the instances as if we would perform stand-
         * alone tests.
         */
        Injector injector = new LeaStandaloneSetup().createInjectorAndDoEMFRegistration();
        parseHelper = (ParseHelper<AnalysisDefinition>) injector.getInstance(ParseHelper.class);
        validationTestHelper = injector.getInstance(ValidationTestHelper.class);
        
        // Add those language elements to the language registry, which are defined in the test models
        try {
            ParameterType stringParameterType = new ParameterType(ElementType.ARTIFACT_PARAMETER_TYPE, "String",
                    AbstractParserTest.class, SOURCE_PLUGIN); // TODO type not specific to artifacts -> build-in types?
            LanguageRegistry.INSTANCE.addParameterType(stringParameterType);
            ParameterType artifactParameterType = new ParameterType(ElementType.ARTIFACT_PARAMETER_TYPE, "File",
                    AbstractParserTest.class, SOURCE_PLUGIN);
            LanguageRegistry.INSTANCE.addParameterType(artifactParameterType);
            ParameterType fragmentParameterType = new ParameterType(ElementType.FRAGMENT_PARAMETER_TYPE, "CodeBlock",
                    AbstractParserTest.class, SOURCE_PLUGIN);
            LanguageRegistry.INSTANCE.addParameterType(fragmentParameterType);
            ParameterType resultParameterType = new ParameterType(ElementType.RESULT_PARAMETER_TYPE, "DeadBlock",
                    AbstractParserTest.class, SOURCE_PLUGIN);
            LanguageRegistry.INSTANCE.addParameterType(resultParameterType);
            
            ChangeIdentifier fileChangeIdentifier = new ChangeIdentifier("FileChangeIdentifier",
                    AbstractParserTest.class, SOURCE_PLUGIN);
            fileChangeIdentifier.finalize(new ParameterType[] {artifactParameterType});
            LanguageRegistry.INSTANCE.addChangeIdentifier(fileChangeIdentifier);
            ChangeIdentifier blockChangeIdentifier = new ChangeIdentifier("BlockChangeIdentifier",
                    AbstractParserTest.class, SOURCE_PLUGIN);
            blockChangeIdentifier.finalize(new ParameterType[] {fragmentParameterType});
            LanguageRegistry.INSTANCE.addChangeIdentifier(blockChangeIdentifier);
            
            Call singleFileCall = new Call(ElementType.OPERATION, "file", AbstractParserTest.class.getMethods()[0],
                    AbstractParserTest.class, SOURCE_PLUGIN);
            singleFileCall.finalize(artifactParameterType, new ParameterType[] {stringParameterType}, null);
            LanguageRegistry.INSTANCE.addCall(singleFileCall);
            Call allFilesCall = new Call(ElementType.OPERATION, "files", AbstractParserTest.class.getMethods()[0],
                    AbstractParserTest.class, SOURCE_PLUGIN); // TODO how to know that this returns a set?
            allFilesCall.finalize(artifactParameterType, new ParameterType[] {stringParameterType}, null);
            LanguageRegistry.INSTANCE.addCall(allFilesCall);
            
            Call codeExtractorCallWithtoutParameters = new Call(ElementType.EXTRACTOR_CALL, "codeExtractor",
                    AbstractParserTest.class.getMethods()[0], AbstractParserTest.class, SOURCE_PLUGIN);
            codeExtractorCallWithtoutParameters.finalize(fragmentParameterType, null, null);
            LanguageRegistry.INSTANCE.addCall(codeExtractorCallWithtoutParameters);
            Call codeExtractorCallWithParameters = new Call(ElementType.EXTRACTOR_CALL, "codeExtractor",
                    AbstractParserTest.class.getMethods()[0], AbstractParserTest.class, SOURCE_PLUGIN);
            codeExtractorCallWithParameters.finalize(fragmentParameterType, new ParameterType[] {artifactParameterType},
                    null);
            LanguageRegistry.INSTANCE.addCall(codeExtractorCallWithParameters);
            
            Call deadCodeAnaylsisCall = new Call(ElementType.ANALYSIS_CALL, "deadCodeAnalysis",
                    AbstractParserTest.class.getMethods()[0], AbstractParserTest.class, SOURCE_PLUGIN);
            deadCodeAnaylsisCall.finalize(resultParameterType, null, null);
            LanguageRegistry.INSTANCE.addCall(deadCodeAnaylsisCall);

        } catch (LanguageElementException e) {
            System.out.println("Creating required language elements during setup failed");
            e.printStackTrace();
        }        
    }
    
    /**
     * Parses the content of the test data file denoted by the current {@link #relativeTestModelFilePath} and saves the
     * resulting {@link AnalysisDefinition} to the {@link #actualAnalysisDefinition}. That definition may be incomplete
     * as the parser tries to parse as much as possible and stops parsing, e.g., if an error occurs. This method does
     * not perform any further checks.
     */
    @Before
    public void parse() {
        actualAnalysisDefinition = null;
        String model = getModelString(relativeTestModelFilePath);
        if (model != null) {            
            try {
                actualAnalysisDefinition = parseHelper.parse(model);
//CHECKSTYLE:OFF
            } catch (Exception e) {
//CHECKSTYLE:ON
                System.out.println("Parsing content of file \"" + TESTDATA_DIRECTORY.getAbsolutePath() + "/" 
                        + relativeTestModelFilePath + "\" failed");
                e.printStackTrace();
            }
        }
    }
    
    /**
     * Tests whether the current {@link #actualAnalysisDefinition} has the expected validity. Hence 
     * {@link ValidationTestHelper#validate(org.eclipse.emf.ecore.resource.Resource)} is called for the current 
     * {@link #actualAnalysisDefinition}. The returned {@link List} of {@link Issue}s is checked for being empty
     * (meaning that the definition is valid), which is compared with {@link #expectedAnalysisDefinitionIsValid}.
     */ 
    @Test
    public void testAnalysisDefinitionValidity() {       
        List<Issue> foundIssues = validationTestHelper.validate(actualAnalysisDefinition);
        StringBuilder issuesStringBuilder = new StringBuilder();
        if (!foundIssues.isEmpty()) {
            issuesStringBuilder.append(foundIssues.get(0).getMessage());
            for (int i = 1; i < foundIssues.size(); i++) {
                issuesStringBuilder.append("; ");
                issuesStringBuilder.append(foundIssues.get(i).getMessage());
            }
        }
        assertEquals(expectedAnalysisDefinitionIsValid, foundIssues.isEmpty(), 
                "Analysis definition has not expected validity (" + issuesStringBuilder.toString() + ")");
    }
    
    /**
     * Tests whether the number of {@link ElementDeclaration}s in the {@link #actualAnalysisDefinition} is equal to the
     * {@link #expectedNumberOfElementDeclarations}.
     */
    @Test
    public void testCorrectNumberOfElementDeclarations() {
        assertEquals(expectedNumberOfElementDeclarations, actualAnalysisDefinition.getElementDeclarations().size(), 
                "Analysis definition has not expected number of element declarations");
    }
    
    /**
     * Tests whether the number of {@link ChangeIdentifierAssignment}s in the {@link #actualAnalysisDefinition} is equal
     * to the {@link #expectedNumberOfChangeIdentifierAssignments}.
     */
    @Test
    public void testCorrectNumberOfChangeIdentifierAssignments() {
        assertEquals(expectedNumberOfChangeIdentifierAssignments, 
                actualAnalysisDefinition.getChangeIdentifierAssignments().size(),
                "Analysis definition has not expected number of change identifier assignments");
    }
    
    /**
     * Returns the current {@link #actualAnalysisDefinition}.
     * 
     * @return the current {@link #actualAnalysisDefinition} or <code>null</code>, if {@link #parse(String)} failed
     */
    public AnalysisDefinition getActualAnalysisDefinition() {
        return actualAnalysisDefinition;
    }
    
    /**
     * Retrieves the content of a test data file. The file is determined by concatenating the root 
     * {@link #TESTDATA_DIRECTORY} and the given relative file path.
     * 
     * @param relativeFilePath the path and name of the desired test data file relative to the
     *        {@link #TESTDATA_DIRECTORY}; should never be <code>null</code>
     * @return the content of the test data file as a single {@link String} or <code>null</code>, if the file dose not
     *         exist, denotes a directory, or reading the content of that file failed
     */
    private String getModelString(String relativeFilePath) {
        String modelString = null;
        File testDataFile = new File(TESTDATA_DIRECTORY, relativeFilePath);
        if (testDataFile.exists() && !testDataFile.isDirectory()) {
            StringBuilder modelStringBuilder = new StringBuilder();
            FileReader fileReader = null;
            BufferedReader bufferedReader = null;       
            try {
                fileReader = new FileReader(testDataFile);
                bufferedReader = new BufferedReader(fileReader);
                String fileLine;
                while ((fileLine = bufferedReader.readLine()) != null) {
                    modelStringBuilder.append(fileLine);
                    modelStringBuilder.append("\n");
                }
                modelString = modelStringBuilder.toString();
            } catch (IOException | OutOfMemoryError e) {
                System.out.println("Reading content from file \"" + testDataFile.getAbsolutePath() + "\" failed");
                e.printStackTrace();
            } finally {
                if (fileReader != null) {
                    try {
                        fileReader.close();
                    } catch (IOException e) {
                        System.out.println("Closing file reader for \"" + testDataFile.getAbsolutePath() + "\" failed");
                        e.printStackTrace();
                    }
                }
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        System.out.println("Closing buffered reader for \"" + testDataFile.getAbsolutePath()
                                + "\" failed");
                        e.printStackTrace();
                    }
                }
            }
        } else {
            System.out.println("File \"" + testDataFile.getAbsolutePath() 
                    + "\" for reading model does not exist or is not a file");
        }
        return modelString;
    }
}
