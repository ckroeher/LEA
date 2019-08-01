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

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.junit.runners.Parameterized.Parameters;

import net.ssehub.integration.Call;
import net.ssehub.integration.ElementType;
import net.ssehub.integration.ExternalElementException;
import net.ssehub.integration.LanguageElement;
import net.ssehub.integration.LanguageElementCreator;
import net.ssehub.integration.annotations.Operation;

/**
 * This class contains unit tests for the correct creation of {@link Call}s of the type {@link ElementType#OPERATION} by
 * the {@link LanguageElementCreator}.
 * 
 * @author Christian Kroeher
 *
 */
public class OperationCreationTests extends AbstractCallCreationTest {
    
    /**
     * This class defines a single method for testing the creation of a corresponding {@link Call} of the type 
     * {@link ElementType#OPERATION}. This method is annotated with the {@link Operation} annotation without any further
     * parameters. Hence the {@link LanguageElementCreator} should create a {@link Call} with the name, return type, and
     * parameters as defined by the method. However, the return type of this method is <code>void</code>.
     * 
     * @author Christian Kroeher
     *
     */
    private class ClassIntroducingVoidReturnOperation {
        
        /**
         * See {@link ClassIntroducingVoidReturnOperation}.
         */
        @Operation
        public void noReturn() { }
    }
    
    /**
     * This class defines a single method for testing the creation of a corresponding {@link Call} of the type 
     * {@link ElementType#OPERATION}. This method is annotated with the {@link Operation} annotation without any further
     * parameters. Hence, the {@link LanguageElementCreator} should create a {@link Call} with the name, return type,
     * and parameters as defined by the method.
     * 
     * @author Christian Kroeher
     *
     */
    private class ClassIntroducingFileReturnOperation {
       
        /**
         * See {@link ClassIntroducingFileReturnOperation}.
         * 
         * @return <code>null</code> as this is out of scope of the language
         */
        @Operation
        public File getFile() {
            return null;
        }
    }
    
    /**
     * This class defines a single method for testing the creation of a corresponding {@link Call} of the type 
     * {@link ElementType#OPERATION}. This method is annotated with the {@link Operation} annotation without any further
     * parameters. Hence, the {@link LanguageElementCreator} should create a {@link Call} with the name, return type,
     * and parameters as defined by the method.
     * 
     * @author Christian Kroeher
     *
     */
    private class ClassIntroducingFileReturnAndSingleSimpleParameterOperation {
       
        /**
         * See {@link ClassIntroducingFileReturnAndSingleSimpleParameterOperation}.
         * 
         * @param dir the parameter, which also the {@link Call} should accept
         * @return <code>null</code> as this is out of scope of the language
         */
        @Operation
        public File getFile(File dir) {
            return null;
        }
    }
    
    /**
     * This class defines a single method for testing the creation of a corresponding {@link Call} of the type 
     * {@link ElementType#OPERATION}. This method is annotated with the {@link Operation} annotation without any further
     * parameters. Hence, the {@link LanguageElementCreator} should create a {@link Call} with the name, return type,
     * and parameters as defined by the method.
     * 
     * @author Christian Kroeher
     *
     */
    private class ClassIntroducingFileReturnAndMultipleSimpleParametersOperation {
       
        /**
         * See {@link ClassIntroducingFileReturnAndMultipleSimpleParametersOperation}.
         * 
         * @param dir the parameter, which also the {@link Call} should accept
         * @param prefix the parameter, which also the {@link Call} should accept
         * @return <code>null</code> as this is out of scope of the language
         */
        @Operation
        public File getFile(File dir, String prefix) {
            return null;
        }
    }
    
    /**
     * This class defines a single method for testing the creation of a corresponding {@link Call} of the type 
     * {@link ElementType#OPERATION}. This method is annotated with the {@link Operation} annotation without any further
     * parameters. Hence, the {@link LanguageElementCreator} should create a {@link Call} with the name, return type,
     * and parameters as defined by the method.
     * 
     * @author Christian Kroeher
     *
     */
    private class ClassIntroducingFileReturnAndSingleArrayParameterOperation {
       
        /**
         * See {@link ClassIntroducingFileReturnAndSingleArrayParameterOperation}.
         * 
         * @param dirs the parameter, which also the {@link Call} should accept
         * @return <code>null</code> as this is out of scope of the language
         */
        @Operation
        public File getFile(File[] dirs) {
            return null;
        }
    }
    
    /**
     * This class defines a single method for testing the creation of a corresponding {@link Call} of the type 
     * {@link ElementType#OPERATION}. This method is annotated with the {@link Operation} annotation without any further
     * parameters. Hence, the {@link LanguageElementCreator} should create a {@link Call} with the name, return type,
     * and parameters as defined by the method.
     * 
     * @author Christian Kroeher
     *
     */
    private class ClassIntroducingFileReturnAndSingleListParameterOperation {
       
        /**
         * See {@link ClassIntroducingFileReturnAndSingleListParameterOperation}.
         * 
         * @param dirs the parameter, which also the {@link Call} should accept
         * @return <code>null</code> as this is out of scope of the language
         */
        @Operation
        public File getFile(List<File> dirs) {
            return null;
        }
    }
    
    /**
     * This class defines a single method for testing the creation of a corresponding {@link Call} of the type 
     * {@link ElementType#OPERATION}. This method is annotated with the {@link Operation} annotation without any further
     * parameters. Hence, the {@link LanguageElementCreator} should create a {@link Call} with the name, return type,
     * and parameters as defined by the method.
     * 
     * @author Christian Kroeher
     *
     */
    private class ClassIntroducingFileReturnAndMultipleComplexParameterOperation {
       
        /**
         * See {@link ClassIntroducingFileReturnAndMultipleComplexParameterOperation}.
         * 
         * @param dirs the parameter, which also the {@link Call} should accept
         * @param names dirs the parameter, which also the {@link Call} should accept
         * @return <code>null</code> as this is out of scope of the language
         */
        @Operation
        public File getFile(List<File> dirs, String[] names) {
            return null;
        }
    }
    

    
    /**
     * This class defines a single method for testing the creation of a corresponding {@link Call} of the type 
     * {@link ElementType#OPERATION}. This method is annotated with the {@link Operation} annotation without any further
     * parameters. Hence, the {@link LanguageElementCreator} should create a {@link Call} with the name, return type,
     * and parameters as defined by the method.
     * 
     * @author Christian Kroeher
     *
     */
    private class ClassIntroducingFileArrayReturnOperation {
       
        /**
         * See {@link ClassIntroducingFileArrayReturnOperation}.
         * 
         * @return <code>null</code> as this is out of scope of the language
         */
        @Operation
        public File[] getFile() {
            return null;
        }
    }
    
    /**
     * This class defines a single method for testing the creation of a corresponding {@link Call} of the type 
     * {@link ElementType#OPERATION}. This method is annotated with the {@link Operation} annotation without any further
     * parameters. Hence, the {@link LanguageElementCreator} should create a {@link Call} with the name, return type,
     * and parameters as defined by the method.
     * 
     * @author Christian Kroeher
     *
     */
    private class ClassIntroducingFileListReturnOperation {
       
        /**
         * See {@link ClassIntroducingFileListReturnOperation}.
         * 
         * @return <code>null</code> as this is out of scope of the language
         */
        @Operation
        public List<File> getFile() {
            return null;
        }
    }
    
    /**
     * This class defines a single method for testing the creation of a corresponding {@link Call} of the type 
     * {@link ElementType#OPERATION}. This method is annotated with the {@link Operation} annotation with a custom name.
     * Hence, the {@link LanguageElementCreator} should create a {@link Call} with the custom name, but uses the return
     * type and parameters as defined by the method.
     * 
     * @author Christian Kroeher
     *
     */
    private class ClassIntroducingFileReturnWithCustomNameOperation {
       
        /**
         * See {@link ClassIntroducingFileReturnWithCustomNameOperation}.
         * 
         * @return <code>null</code> as this is out of scope of the language
         */
        @Operation(name = "parseFile")
        public File getFile() {
            return null;
        }
    }
    
    /**
     * This class defines a single method for testing the creation of a corresponding {@link Call} of the type 
     * {@link ElementType#OPERATION}. This method is annotated with the {@link Operation} annotation with a custom
     * return type. Hence, the {@link LanguageElementCreator} should create a {@link Call} with the custom return type,
     * but uses the name and parameters as defined by the method.
     * 
     * @author Christian Kroeher
     *
     */
    private class ClassIntroducingFileReturnWithCustomReturnTypeOperation {
       
        /**
         * See {@link ClassIntroducingFileReturnWithCustomReturnTypeOperation}.
         * 
         * @return <code>null</code> as this is out of scope of the language
         */
        @Operation(returnType = "FileObject")
        public File getFile() {
            return null;
        }
    }
    
    /**
     * This class defines a single method for testing the creation of a corresponding {@link Call} of the type 
     * {@link ElementType#OPERATION}. This method is annotated with the {@link Operation} annotation with a custom name
     * and return type. Hence, the {@link LanguageElementCreator} should create a {@link Call} with the custom name and
     * return type, but uses the parameters as defined by the method.
     * 
     * @author Christian Kroeher
     *
     */
    private class ClassIntroducingFileReturnWithCustomNameAndReturnTypeOperation {
       
        /**
         * See {@link ClassIntroducingFileReturnWithCustomNameAndReturnTypeOperation}.
         * 
         * @return <code>null</code> as this is out of scope of the language
         */
        @Operation(name = "getFileObject", returnType = "FileObject")
        public File getFile() {
            return null;
        }
    }
    
    /**
     * This class defines a single method for testing the creation of a corresponding {@link Call} of the type 
     * {@link ElementType#OPERATION}. This method is annotated with the {@link Operation} annotation with custom
     * parameters. Hence, the {@link LanguageElementCreator} should not create any language element, but throws an 
     * {@link ExternalElementException} as the method does not define any parameters.
     * 
     * @author Christian Kroeher
     *
     */
    private class ClassIntroducingFileReturnWithIncorrectCustomParametersOperation {
       
        /**
         * See {@link ClassIntroducingFileReturnWithIncorrectCustomParametersOperation}.
         * 
         * @return <code>null</code> as this is out of scope of the language
         */
        @Operation(parameters = "File")
        public File getFile() {
            return null;
        }
    }
    
    /**
     * This class defines a single method for testing the creation of a corresponding {@link Call} of the type 
     * {@link ElementType#OPERATION}. This method is annotated with the {@link Operation} annotation with custom
     * parameters. Hence, the {@link LanguageElementCreator} should create a {@link Call} with the custom parameters,
     * but uses the name and return type as defined by the method.
     * 
     * @author Christian Kroeher
     *
     */
    private class ClassIntroducingFileReturnWithSingleCustomParameterOperation {
       
        /**
         * See {@link ClassIntroducingFileReturnWithSingleCustomParameterOperation}.
         * 
         * @param dir the parameter, which also the {@link Call} should accept
         * @return <code>null</code> as this is out of scope of the language
         */
        @Operation(parameters = "FileObject")
        public File getFile(File dir) {
            return null;
        }
    }
    
    /**
     * This class defines a single method for testing the creation of a corresponding {@link Call} of the type 
     * {@link ElementType#OPERATION}. This method is annotated with the {@link Operation} annotation with custom
     * parameters. Hence, the {@link LanguageElementCreator} should not create any language element, but throws an 
     * {@link ExternalElementException} as the method does not define as much parameters as the custom ones.
     * 
     * @author Christian Kroeher
     *
     */
    private class ClassIntroducingFileReturnWithTooManyCustomParametersOperation {
       
        /**
         * See {@link ClassIntroducingFileReturnWithSingleCustomParameterOperation}.
         * 
         * @param dir the parameter, which also the {@link Call} should accept
         * @return <code>null</code> as this is out of scope of the language
         */
        @Operation(parameters = {"FileObject", "TooMuch"})
        public File getFile(File dir) {
            return null;
        }
    }
    
    /**
     * This class defines a single method for testing the creation of a corresponding {@link Call} of the type 
     * {@link ElementType#OPERATION}. This method is annotated with the {@link Operation} annotation with custom
     * parameters. Hence, the {@link LanguageElementCreator} should create a {@link Call} with the custom parameters,
     * but uses the name and return type as defined by the method.
     * 
     * @author Christian Kroeher
     *
     */
    private class ClassIntroducingFileReturnWithMultipleCustomParametersOperation {
       
        /**
         * See {@link ClassIntroducingFileReturnWithMultipleCustomParametersOperation}.
         * 
         * @param dir the parameter, which also the {@link Call} should accept
         * @param prefix the parameter, which also the {@link Call} should accept
         * @return <code>null</code> as this is out of scope of the language
         */
        @Operation(parameters = {"FileObject", "Prefix"})
        public File getFile(File dir, String prefix) {
            return null;
        }
    }
    
    /**
     * This class defines a single method for testing the creation of a corresponding {@link Call} of the type 
     * {@link ElementType#OPERATION}. This method is annotated with the {@link Operation} annotation with custom
     * parameters. Hence, the {@link LanguageElementCreator} should not create any language element, but throws an 
     * {@link ExternalElementException} as the method defines more parameters as the custom ones.
     * 
     * @author Christian Kroeher
     *
     */
    private class ClassIntroducingFileReturnWithTooFewCustomParametersOperation {
       
        /**
         * See {@link ClassIntroducingFileReturnWithTooFewCustomParametersOperation}.
         * 
         * @param dir the parameter, which also the {@link Call} should accept
         * @param prefix the parameter, which also the {@link Call} should accept
         * @return <code>null</code> as this is out of scope of the language
         */
        @Operation(parameters = {"FileObject"})
        public File getFile(File dir, String prefix) {
            return null;
        }
    }
    
    /**
     * The expected results for each input {@link Class} defined as inner class of this class. Each entry has the
     * following elements:
     * <ul>
     * <li>The {@link Class} used as an input to the {@link LanguageElementCreator} for creating a 
     * {@link LanguageElement} based on the information of that class
     * </li>
     * <li>The {@link ExternalElementException} expected to be thrown during the creation of a {@link LanguageElement};
     * a value of <code>null</code> indicates that throwing an exception was not expected
     * </li>
     * <li>The declaration of whether it is expected that the created {@link LanguageElement} is not <code>null</code>
     * (<code>true</code>) or should be <code>null</code> (<code>false</code>)
     * </li>
     * <li>The expected {@link Class} of the created {@link LanguageElement}</li>
     * <li>The expected {@link ElementType} of the created {@link LanguageElement}</li>
     * <li>The expected name of the created {@link LanguageElement}</li>
     * <li>The expected {@link Class} from which the {@link LanguageElement} was created</li>
     * <li>The expected {@link File} denoting the source plug-in of the {@link Class} from which a 
     * {@link LanguageElement} was created
     * </li>
     * <li>The expected name of the type of element(s) the created call will return</li>
     * <li>The expected array of names, which denote the elements the created call accepts as parameters</li>
     * </ul>
     */
    private static final Object[][] EXPECTED_RESULTS = new Object[][] {
        {ClassIntroducingVoidReturnOperation.class, null, true, Call.class, ElementType.OPERATION, 
            "noReturn", ClassIntroducingVoidReturnOperation.class, sourcePlugin, "void", new String[] {}},
        
        {ClassIntroducingFileReturnOperation.class, null, true, Call.class, ElementType.OPERATION, 
            "getFile", ClassIntroducingFileReturnOperation.class, sourcePlugin, "File", new String[] {}},
        
        {ClassIntroducingFileReturnAndSingleSimpleParameterOperation.class, null, true, Call.class,
            ElementType.OPERATION, "getFile", ClassIntroducingFileReturnAndSingleSimpleParameterOperation.class,
            sourcePlugin, "File", new String[] {"File"}},
        
        {ClassIntroducingFileReturnAndMultipleSimpleParametersOperation.class, null, true, Call.class,
            ElementType.OPERATION, "getFile", ClassIntroducingFileReturnAndMultipleSimpleParametersOperation.class,
            sourcePlugin, "File", new String[] {"File", "String"}},
        
        {ClassIntroducingFileReturnAndSingleArrayParameterOperation.class, null, true, Call.class,
            ElementType.OPERATION, "getFile", ClassIntroducingFileReturnAndSingleArrayParameterOperation.class,
            sourcePlugin, "File", new String[] {"File[]"}},
        
        {ClassIntroducingFileReturnAndSingleListParameterOperation.class, null, true, Call.class,
            ElementType.OPERATION, "getFile", ClassIntroducingFileReturnAndSingleListParameterOperation.class,
            sourcePlugin, "File", new String[] {"List<File>"}},
        
        {ClassIntroducingFileReturnAndMultipleComplexParameterOperation.class, null, true, Call.class,
            ElementType.OPERATION, "getFile", ClassIntroducingFileReturnAndMultipleComplexParameterOperation.class,
            sourcePlugin, "File", new String[] {"List<File>", "String[]"}},

        {ClassIntroducingFileArrayReturnOperation.class, null, true, Call.class, ElementType.OPERATION, 
            "getFile", ClassIntroducingFileArrayReturnOperation.class, sourcePlugin, "File[]", new String[] {}},
        
        {ClassIntroducingFileListReturnOperation.class, null, true, Call.class, ElementType.OPERATION, 
            "getFile", ClassIntroducingFileListReturnOperation.class, sourcePlugin, "List<File>", new String[] {}},
        
        {ClassIntroducingFileReturnWithCustomNameOperation.class, null, true, Call.class, ElementType.OPERATION, 
            "parseFile", ClassIntroducingFileReturnWithCustomNameOperation.class, sourcePlugin, "File",
            new String[] {}},
        
        {ClassIntroducingFileReturnWithCustomReturnTypeOperation.class, null, true, Call.class, ElementType.OPERATION, 
            "getFile", ClassIntroducingFileReturnWithCustomReturnTypeOperation.class, sourcePlugin, "FileObject",
            new String[] {}},
        
        {ClassIntroducingFileReturnWithCustomNameAndReturnTypeOperation.class, null, true, Call.class,
            ElementType.OPERATION, "getFileObject",
            ClassIntroducingFileReturnWithCustomNameAndReturnTypeOperation.class, sourcePlugin, "FileObject",
            new String[] {}},
        
        {ClassIntroducingFileReturnWithIncorrectCustomParametersOperation.class, new ExternalElementException(""),
            false, null, null, null, null, null, null, null},
        
        {ClassIntroducingFileReturnWithSingleCustomParameterOperation.class, null, true, Call.class,
            ElementType.OPERATION, "getFile", ClassIntroducingFileReturnWithSingleCustomParameterOperation.class,
            sourcePlugin, "File", new String[] {"FileObject"}},
        
        {ClassIntroducingFileReturnWithTooManyCustomParametersOperation.class, new ExternalElementException(""),
            false, null, null, null, null, null, null, null},
        
        {ClassIntroducingFileReturnWithMultipleCustomParametersOperation.class, null, true, Call.class,
            ElementType.OPERATION, "getFile", ClassIntroducingFileReturnWithMultipleCustomParametersOperation.class,
            sourcePlugin, "File", new String[] {"FileObject", "Prefix"}},
        
        {ClassIntroducingFileReturnWithTooFewCustomParametersOperation.class, new ExternalElementException(""),
            false, null, null, null, null, null, null, null}
    };

    /**
     * Constructs a new {@link OperationCreationTests} instance.
     * 
     * @param testInputClass the {@link Class} used as an input to the {@link LanguageElementCreator} for creating a
     *        {@link LanguageElement} based on the information of that class
     * @param expectedException the {@link ExternalElementException} expected to be thrown during the creation of a 
     *        {@link LanguageElement}; a value of <code>null</code> indicates that throwing an exception was not 
     *        expected
     * @param expectedElementsExistence the declaration of whether it is expected that the created
     *        {@link LanguageElement} is not <code>null</code> (<code>true</code>) or should be <code>null</code>
     *        (<code>false</code>)
     * @param expectedElementClass the expected {@link Class} of the created {@link LanguageElement}
     * @param expectedElementType the expected {@link ElementType} of the created {@link LanguageElement}
     * @param expectedElementName the expected name of the created {@link LanguageElement}
     * @param expectedElementSourceClass the expected {@link Class} from which the {@link LanguageElement} was created
     * @param expectedElementSourcePlugin the expected {@link File} denoting the source plug-in of the {@link Class}
     *        from which a {@link LanguageElement} was created
     * @param expectedReturnType the expected name of the type of element(s) the created operation will return
     * @param expectedParameters the expected array of names, which denote the elements the created operation accepts as
     *        parameters
     */
//CHECKSTYLE:OFF
    public OperationCreationTests(Class<?> testInputClass, ExternalElementException expectedException,
            boolean expectedElementsExistence, Class<?> expectedElementClass, ElementType expectedElementType,
            String expectedElementName, Class<?> expectedElementSourceClass, File expectedElementSourcePlugin,
            String expectedReturnType, String[] expectedParameters) {
        super(testInputClass, expectedException, expectedElementsExistence, expectedElementClass, expectedElementType,
                expectedElementName, expectedElementSourceClass, expectedElementSourcePlugin, expectedReturnType,
                expectedParameters);
    }
//CHECKSTYLE:ON
    
    /**
     * Returns the expected results as parameters for the tests defined in this and the super-class.
     * 
     * @return the {@link #EXPECTED_RESULTS} as an object-array list
     */
    @Parameters
    public static List<Object[]> getTestData() {
        return Arrays.asList(EXPECTED_RESULTS);
    }

}
