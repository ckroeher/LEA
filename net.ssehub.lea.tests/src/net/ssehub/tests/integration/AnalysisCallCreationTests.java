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
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import org.junit.runners.Parameterized.Parameters;

import net.ssehub.integration.Call;
import net.ssehub.integration.ElementType;
import net.ssehub.integration.ExternalLanguageElementCreator;
import net.ssehub.integration.LanguageElement;
import net.ssehub.integration.LanguageElementException;
import net.ssehub.integration.LanguageRegistry;
import net.ssehub.integration.ParameterType;
import net.ssehub.integration.ParameterTypeInstance;
import net.ssehub.integration.annotations.AnalysisCall;

/**
 * This class contains unit tests for the correct creation of {@link Call}s of the type 
 * {@link ElementType#ANALYSIS_CALL} by the {@link ExternalLanguageElementCreator}. As most of the 
 * {@link OperationCreationTests} already cover common call creation properties, this class only provides a few tests
 * specific to the analysis calls.
 * 
 * @author Christian Kroeher
 *
 */
public class AnalysisCallCreationTests extends AbstractCallCreationTest {
    
    /**
     * This class defines a single method for testing the creation of a corresponding {@link Call} of the type 
     * {@link ElementType#ANALYSIS_CALL}. This method is annotated with the {@link AnalysisCall} annotation without
     * any further parameters. Hence, the {@link ExternalLanguageElementCreator} should not create any language element,
     * but throws an {@link LanguageElementException} as the method defines <code>void</code> as return type without
     * having defined a custom return type by the annotation. Analysis calls are expected to always return (a)
     * result(s).
     * 
     * @author Christian Kroeher
     *
     */
    private class ClassIntroducingVoidReturnAnalysisCall {
        
        /**
         * See {@link ClassIntroducingVoidReturnAnalysisCall}.
         */
        @AnalysisCall
        public void provideNothing() { }
    }
    
    /**
     * This class defines a single method for testing the creation of a corresponding {@link Call} of the type 
     * {@link ElementType#ANALYSIS_CALL}. This method is annotated with the {@link AnalysisCall} annotation with a 
     * custom return type. Hence the {@link ExternalLanguageElementCreator} should create a {@link Call} with the custom
     * return type and the name and parameters as defined by the method.
     * 
     * @author Christian Kroeher
     *
     */
    private class ClassIntroducingVoidReturnWithCustomReturnTypeAnalysisCall {
        
        /**
         * See {@link ClassIntroducingVoidReturnWithCustomReturnTypeAnalysisCall}.
         */
        @AnalysisCall(returnType = "HiddenResult")
        public void provideImplicit() { }
    }
    
    /**
     * The "HiddenResult" {@link ParameterType} required to create the {@link Call}s for testing.
     */
    private static ParameterType hiddenResult = createParameterType(ElementType.FRAGMENT_PARAMETER_TYPE,
            "HiddenResult");
    
    /**
     * The "HiddenResult" {@link ParameterTypeInstance} required to create the {@link Call}s for testing.
     */
    private static ParameterTypeInstance hiddenResultInstance = createParameterTypeInstance(hiddenResult, false);
    
    /**
     * The expected results for each input {@link Class} defined as inner class of this class. Each entry has the
     * following elements:
     * <ul>
     * <li>The {@link Class} used as an input to the {@link ExternalLanguageElementCreator} for creating a 
     *     {@link LanguageElement} based on the information of that class
     * </li>
     * <li>The {@link LanguageElementException} expected to be thrown during the creation of a {@link LanguageElement};
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
     * {@link LanguageElement} was created
     * </li>
     * <li>The expected {@link ParameterTypeInstance} the created {@link Call} will return</li>
     * <li>The expected array of {@link ParameterTypeInstance}s, which denote the elements the created {@link Call}
     *     accepts as parameters</li>
     * </ul>
     */
    private static final Object[][] EXPECTED_RESULTS = new Object[][] {
        {ClassIntroducingVoidReturnAnalysisCall.class, new LanguageElementException(""), false, null, null, null, null,
            null, null, null, null, null},
        
        {ClassIntroducingVoidReturnWithCustomReturnTypeAnalysisCall.class, null, true, Call.class,
            ElementType.ANALYSIS_CALL, "provideImplicit",
            "net.ssehub.tests.integration.AnalysisCallCreationTests."
                    + "ClassIntroducingVoidReturnWithCustomReturnTypeAnalysisCall.provideImplicit",
            ClassIntroducingVoidReturnWithCustomReturnTypeAnalysisCall.class, SOURCE_PLUGIN, hiddenResultInstance, null,
            ClassIntroducingVoidReturnWithCustomReturnTypeAnalysisCall.class.getMethods()[0]}
    };

    /**
     * Constructs a new {@link AnalysisCallCreationTests} instance.
     * 
     * @param testInputClass the {@link Class} used as an input to the {@link ExternalLanguageElementCreator} for
     *        creating a {@link LanguageElement} based on the information of that class
     * @param expectedException the {@link LanguageElementException} expected to be thrown during the creation of a 
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
     *        created {@link Call} accepts as parameters.
     * @param expectedSourceMethod the expected {@link Method} from where this call was created
     */
//CHECKSTYLE:OFF
    public AnalysisCallCreationTests(Class<?> testInputClass, LanguageElementException expectedException,
            boolean expectedElementsExistence, Class<?> expectedElementClass, ElementType expectedElementType,
            String expectedElementName, String expectedElementFullyQualifiedName, Class<?> expectedElementSourceClass,
            File expectedElementSourcePlugin, ParameterTypeInstance expectedReturnType,
            ParameterTypeInstance[] expectedParameters, Method expectedSourceMethod) {
        super(testInputClass, expectedException, expectedElementsExistence, expectedElementClass, expectedElementType,
                expectedElementName, expectedElementFullyQualifiedName, expectedElementSourceClass, 
                expectedElementSourcePlugin, expectedReturnType, expectedParameters, expectedSourceMethod);
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
        if (!LanguageRegistry.INSTANCE.addParameterType(hiddenResult)) {
            System.err.println("Preparation failed");
        }
    }
    
}
