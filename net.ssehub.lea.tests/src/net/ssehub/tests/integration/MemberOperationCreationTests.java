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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runners.Parameterized.Parameters;

import net.ssehub.integration.Call;
import net.ssehub.integration.ElementType;
import net.ssehub.integration.ExternalElementException;
import net.ssehub.integration.LanguageElement;
import net.ssehub.integration.LanguageElementCreator;
import net.ssehub.integration.LanguageRegistry;
import net.ssehub.integration.ParameterType;
import net.ssehub.integration.ParameterTypeInstance;
import net.ssehub.integration.annotations.Operation;

/**
 * This class contains unit tests for the correct creation of {@link Call}s of the type {@link ElementType#OPERATION} as
 * member operations by the {@link LanguageElementCreator}. As {@link OperationCreationTests} already cover the generic
 * operation properties, tests in this class as well as corresponding test data focuses on member operation properties
 * only.
 * 
 * @author Christian Kroeher
 *
 */
public class MemberOperationCreationTests extends AbstractCallCreationTest {

    /**
     * This class defines a single method for testing the creation of a corresponding {@link Call} of the type 
     * {@link ElementType#OPERATION} as a member operation. This method is annotated with the {@link Operation} 
     * annotation with a <code>isMemberOf</code> declaration. Hence, the {@link LanguageElementCreator} should create a 
     * {@link Call} that is a member operation for the {@link ParameterType} defined by that declaration.
     * 
     * @author Christian Kroeher
     *
     */
    private class ClassIntroducingSimpleMemberOperation {
       
        /**
         * See {@link ClassIntroducingSimpleMemberOperation}.
         * 
         * @return <code>null</code> as this is out of scope of the language
         */
        @Operation(isMemberOf = "Path")
        public File getFile() {
            return null;
        }
    }
    
    /**
     * This class defines a single method for testing the creation of a corresponding {@link Call} of the type 
     * {@link ElementType#OPERATION}, but not as a member operation. This method is annotated with the {@link Operation}
     * annotation without a <code>isMemberOf</code> declaration. Hence, the {@link LanguageElementCreator} should create
     * a general {@link Call} that is not a member operation.
     * 
     * @author Christian Kroeher
     *
     */
    private class ClassIntroducingNoMemberOperation {
       
        /**
         * See {@link ClassIntroducingNoMemberOperation}.
         * 
         * @return <code>null</code> as this is out of scope of the language
         */
        @Operation()
        public File getFile() {
            return null;
        }
    }
    
    /**
     * The "File" {@link ParameterType} required to create the {@link Call}s for testing.
     */
    private static ParameterType file = createParameterType(ElementType.ARTIFACT_PARAMETER_TYPE, "File");
    
    /**
     * The "File" {@link ParameterTypeInstance} required to create the {@link Call}s for testing.
     */
    private static ParameterTypeInstance fileInstance = createParameterTypeInstance(file, false);
    
    /**
     * The "Path" {@link ParameterType} required to create the {@link Call}s for testing.
     */
    private static ParameterType path = createParameterType(ElementType.ARTIFACT_PARAMETER_TYPE, "Path");
    
    /**
     * The "Path" {@link ParameterTypeInstance} required to create the {@link Call}s for testing.
     */
    private static ParameterTypeInstance pathInstance = createParameterTypeInstance(path, false);
    
    /**
     * The expected results for each input {@link Class} defined as inner class of this class. Each entry has the
     * following elements:
     * <ul>
     * <li>The {@link Class} used as an input to the {@link LanguageElementCreator} for creating a 
     *     {@link LanguageElement} based on the information of that class
     * </li>
     * <li>The {@link ExternalElementException} expected to be thrown during the creation of a {@link LanguageElement};
     *     a value of <code>null</code> indicates that throwing an exception was not expected
     * </li>
     * <li>The declaration of whether it is expected that the created {@link LanguageElement} is not <code>null</code>
     *     (<code>true</code>) or should be <code>null</code> (<code>false</code>)
     * </li>
     * <li>The expected {@link Class} of the created {@link LanguageElement}</li>
     * <li>The expected {@link ElementType} of the created {@link LanguageElement}</li>
     * <li>The expected name of the created {@link LanguageElement}</li>
     * <li>The expected fully-qualified name of the created {@link LanguageElement}</li>
     * <li>The expected {@link Class} from which the {@link LanguageElement} was created</li>
     * <li>The expected {@link File} denoting the source plug-in of the {@link Class} from which a 
     *     {@link LanguageElement} was created
     * </li>
     * <li>The expected {@link ParameterTypeInstance} the created {@link Call} will return</li>
     * <li>The expected array of {@link ParameterTypeInstance}s, which denote the elements the created {@link Call}
     *     accepts as parameters</li>
     * <li>The expected source {@link Method} from which the {@link LanguageElement} was created</li>
     * <li>The declaration of whether it is expected that the created {@link Call} is a member operation
     *     (<code>true</code>) or not (<code>false</code>)</li>
     * <li>The declaration of whether it is expected that the created {@link Call} is a member operation of the declared
     *     parent parameter type (<code>true</code>) or not (<code>false</code>)</li>
     * <li>The expected {@link ParameterTypeInstance} for which the created {@link Call} is a member operation</li>
     * </ul>
     */
    private static final Object[][] EXPECTED_RESULTS = new Object[][] {
        {ClassIntroducingSimpleMemberOperation.class, null, true, Call.class, ElementType.OPERATION, 
            "getFile", "net.ssehub.tests.integration.MemberOperationCreationTests."
                    + "ClassIntroducingSimpleMemberOperation.getFile",
            ClassIntroducingSimpleMemberOperation.class, SOURCE_PLUGIN, fileInstance, null,
            ClassIntroducingSimpleMemberOperation.class.getMethods()[0], true, true, pathInstance},
        {ClassIntroducingNoMemberOperation.class, null, true, Call.class, ElementType.OPERATION, 
            "getFile", "net.ssehub.tests.integration.MemberOperationCreationTests."
                    + "ClassIntroducingNoMemberOperation.getFile",
            ClassIntroducingNoMemberOperation.class, SOURCE_PLUGIN, fileInstance, null,
            ClassIntroducingNoMemberOperation.class.getMethods()[0], false, false, null}
    };
    
    /**
     * The definition of whether it is expected that the created {@link Call} is a member operation (<code>true</code>)
     * or not (<code>false</code>).
     */
    private boolean expectedMemberOperation;
    
    /**
     * The definition of whether it is expected that the created {@link Call} is a member operation of the declared
     * parent parameter type (<code>true</code>) or not (<code>false</code>).
     */
    private boolean expectedMemberOperationOf;
    
    /**
     * The expected {@link ParameterTypeInstance} for which the created {@link Call} is a member operation.
     */
    private ParameterTypeInstance expectedParentParameterType;
    
    /**
     * The definition of whether the created {@link Call} is actually a member operation (<code>true</code>) or not 
     * (<code>false</code>).
     */
    private boolean actualMemberOperation;
    
    /**
     * The definition of whether the created {@link Call} is actually a member operation of the declared parent
     * parameter type (<code>true</code>) or not (<code>false</code>).
     */
    private boolean actualMemberOperationOf;
    
    /**
     * The actual {@link ParameterTypeInstance} for which the created {@link Call} is a member operation.
     */
    private ParameterTypeInstance actualParentParameterType;
    
    /**
     * Constructs a new {@link MemberOperationCreationTests} instance.
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
     * @param expectedElementFullyQualifiedName the expected fully-qualified name of the created {@link LanguageElement}
     * @param expectedElementSourceClass the expected {@link Class} from which the {@link LanguageElement} was created
     * @param expectedElementSourcePlugin the expected {@link File} denoting the source plug-in of the {@link Class}
     *        from which a {@link LanguageElement} was created
     * @param expectedReturnType the expected {@link ParameterTypeInstance} the created {@link Call} will return
     * @param expectedParameters the expected array of {@link ParameterTypeInstance}s, which denote the elements the
     *        created {@link Call} accepts as parameters
     * @param expectedSourceMethod the expected {@link Method} from where this call was created
     * @param expectedMemberOperation the declaration of whether it is expected that the created {@link Call} is a
     *        member operation (<code>true</code>) or not (<code>false</code>)
     * @param expectedMemberOperationOf the definition of whether it is expected that the created {@link Call} is a
     *        member operation of the declared parent parameter type (<code>true</code>) or not (<code>false</code>)
     * @param expectedParentParameterType the expected {@link ParameterTypeInstance} for which the created {@link Call}
     *        is a member operation
     */
//CHECKSTYLE:OFF
    public MemberOperationCreationTests(Class<?> testInputClass, ExternalElementException expectedException,
            boolean expectedElementsExistence, Class<?> expectedElementClass, ElementType expectedElementType,
            String expectedElementName, String expectedElementFullyQualifiedName, Class<?> expectedElementSourceClass,
            File expectedElementSourcePlugin, ParameterTypeInstance expectedReturnType,
            ParameterTypeInstance[] expectedParameters, Method expectedSourceMethod, boolean expectedMemberOperation,
            boolean expectedMemberOperationOf, ParameterTypeInstance expectedParentParameterType) {
        super(testInputClass, expectedException, expectedElementsExistence, expectedElementClass, expectedElementType,
                expectedElementName, expectedElementFullyQualifiedName, expectedElementSourceClass, 
                expectedElementSourcePlugin, expectedReturnType, expectedParameters, expectedSourceMethod);
        this.expectedMemberOperation = expectedMemberOperation;
        this.expectedMemberOperationOf = expectedMemberOperationOf;
        this.expectedParentParameterType = expectedParentParameterType;
        
        Call createdCall = (Call) createdElement;
        this.actualMemberOperation = createdCall.isMemberOperation();
        if (expectedParentParameterType == null) {
            this.actualParentParameterType = null;
        } else {            
            this.actualMemberOperationOf = createdCall.isMemberOperationOf(expectedParentParameterType);
        }
        this.actualParentParameterType = createdCall.getParentParameterType();
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
    
    @Override
    protected void prepare() {
        if (!LanguageRegistry.INSTANCE.addParameterType(file)
                || !LanguageRegistry.INSTANCE.addParameterType(path)) {
            System.err.println("Preparation failed");
        }
    }

    /**
     * Tests whether the {@link #expectedMemberOperation} is equal to the {@link #actualMemberOperation}.
     */
    @Test
    public void testIsMemberOperation() {
        assertEquals(expectedMemberOperation, actualMemberOperation, "Wrong member operation declaration");
    }
    
    /**
     * Tests whether the {@link #expectedMemberOperationOf} is equal to the {@link #actualMemberOperationOf}.
     */
    @Test
    public void testIsMemberOperationOf() {
        assertEquals(expectedMemberOperationOf, actualMemberOperationOf, 
                "Unexpected unsupported parent parameter type");
    }
    
    /**
     * Tests whether the {@link #expectedParentParameterType} is equal to the {@link #actualParentParameterType}.
     */
    @Test
    public void testGetParentParameterType() {
        boolean expected = false;
        if (expectedParentParameterType == null) {
            expected = (actualParentParameterType == null);
        } else {
            expected = expectedParentParameterType.equals(actualParentParameterType);
        }
        assertTrue(expected, "Wrong parent parameter type");
    }
}
