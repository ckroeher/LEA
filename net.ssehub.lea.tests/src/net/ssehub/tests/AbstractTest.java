package net.ssehub.tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.eclipse.xtext.testing.validation.ValidationTestHelper;

import com.google.inject.Inject;

import net.ssehub.lea.AnalysisDefinition;
import net.ssehub.lea.ElementDeclaration;

public abstract class AbstractTest {
    
    protected class AbstractTestException extends Exception {
        
        private static final long serialVersionUID = -7588648465766953583L;

        private AbstractTestException(String message) {
            super(message);
        }
        
        private AbstractTestException(String message, Throwable cause) {
            super(message, cause);
        }
    }
    
    public enum TestType {BASIC}    

    private static final File TESTDATA_DIRECTORY = new File("./testdata");
    
    @Inject
    ValidationTestHelper validationTestHelper;
    
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
            throw new AbstractTestException("Reading content from file \"" + testFile.getAbsolutePath() + "\" failed", e);
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    throw new AbstractTestException("Closing file reader for \"" + testFile.getAbsolutePath() + "\" failed", e);
                }
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    throw new AbstractTestException("Closing buffered reader for \"" + testFile.getAbsolutePath() + "\" failed", e);
                }
            }
        }
        
        return modelStringBuilder.toString();
    }
    
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
    
    protected void assertSyntacticalCorrectness(AnalysisDefinition analysis) {
        /* 
         * The parse result always returns without any errors, although syntactical errors may exist. It simply parses
         * as much as possible and stops, if there are no matching parsing rules. Hence, we have to perform a validation
         * test first, to ensure, that there are no errors, meaning that the model is syntactically correct.
         */
        validationTestHelper.assertNoErrors(analysis);
    }
    
//  Artifact<T> name = call();
    protected String testCorrectElementDeclaration(ElementDeclaration element, String expectedGenericType, boolean expectedSet, boolean expectedOperation, boolean expectedElement) {
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
        if (element.getName().isBlank()) {
            problemDescriptionBuilder.append("Missing element name\n");
        }
        if (!expectedOperation && !expectedElement && element.getInitialization() != null) {
            problemDescriptionBuilder.append("Unexpected initialization\n");
        }
        if (expectedOperation && element.getInitialization() == null) {
            problemDescriptionBuilder.append("Missing initialization via operation\n");
        }
        if (expectedOperation && element.getInitialization() != null && element.getInitialization().getOperation() == null) {
            problemDescriptionBuilder.append("Initialization is not an operation\n");
        }
        if (expectedElement && element.getInitialization() == null) {
            problemDescriptionBuilder.append("Missing initialization via element\n");
        }
        if (expectedElement && element.getInitialization() != null && element.getInitialization().getElement() == null) {
            problemDescriptionBuilder.append("Initialization is not an element\n");
        }
        
        if (problemDescriptionBuilder.length() > 0) {
            problemDescription = problemDescriptionBuilder.toString();
        }
        
        return problemDescription;
    }
}
