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

import net.ssehub.integration.ElementType;
import net.ssehub.integration.ExternalElementException;
import net.ssehub.integration.LanguageElement;
import net.ssehub.integration.LanguageElementCreator;
import net.ssehub.integration.ParameterType;
import net.ssehub.integration.annotations.FragmentParameterType;

/**
 * This class contains unit tests for the correct creation of {@link ParameterType}s of the type 
 * {@link ElementType#FRAGMENT_PARAMETER_TYPE} by the {@link LanguageElementCreator}.
 * 
 * @author Christian Kroeher
 *
 */
public class FragmentParameterTypeCreationTests extends AbstractLanguageElementCreationTest {

    /**
     * This class represents a simple (not generic) fragment parameter type. It is annotated with the 
     * {@link FragmentParameterType} annotation without any further parameters. Hence, the actual class name should be
     * used for element creation.
     * 
     * @author Christian Kroeher
     *
     */
    @FragmentParameterType
    private class SimpleFragmentParameterType { }
    
    /**
     * This class represents a simple (not generic) fragment parameter type. It is annotated with the 
     * {@link FragmentParameterType} annotation including the definition of the symbolic name <i>Block</i>, which
     * should be used for element creation instead of the actual class name.
     * 
     * @author Christian Kroeher
     *
     */
    @FragmentParameterType(name = "Block")
    private class SimpleFragmentParameterTypeWithSymbolicName { }
    
    /**
     * This class represents a simple (not generic) fragment parameter type. It is annotated with the 
     * {@link FragmentParameterType} annotation including the definition of the symbolic parameter name <i>None</i>,
     * which should be ignored as this artifact is not a generic one.
     * 
     * @author Christian Kroeher
     *
     */
    @FragmentParameterType(parameterName = "None")
    private class SimpleFragmentParameterTypeWithSymbolicParameterName { }
    
    /**
     * This class represents a simple (not generic) fragment parameter type. It is annotated with the 
     * {@link FragmentParameterType} annotation including the definition of the the symbolic name <i>Block</i> and the 
     * symbolic parameter name <i>Code</i>. While the former should be used for element creation instead of the actual
     * class name, the latter should be ignored as this artifact is not a generic one.
     * 
     * @author Christian Kroeher
     *
     */
    @FragmentParameterType(name = "Block", parameterName = "Code")
    private class SimpleFragmentParameterTypeWithSymbolicNameAndParameterName { }
    
    /**
     * This class represents a generic fragment parameter type with {@link File} as parameter type. It is annotated with
     * the {@link FragmentParameterType} annotation without any further parameters. Hence, the actual class name and
     * parameter type should be used for element creation.
     * 
     * @param <File> the parameter type of this generic class for testing
     * 
     * @author Christian Kroeher
     *
     */
    @SuppressWarnings("hiding")
    @FragmentParameterType
    private class GenericFragmentParameterType<File> { }
    
    /**
     * This class represents a generic fragment parameter type with {@link File} as parameter type. It is annotated with
     * the {@link FragmentParameterType} annotation including the definition of the symbolic name <i>Block</i>, which
     * should be used for element creation instead of the actual class name.
     * 
     * @param <File> the parameter type of this generic class for testing
     * 
     * @author Christian Kroeher
     *
     */
    @SuppressWarnings("hiding")
    @FragmentParameterType(name = "Block")
    private class GenericFragmentParameterTypeWithSymbolicName<File> { }
    
    /**
     * This class represents a generic fragment parameter type with {@link File} as parameter type. It is annotated with
     * the {@link FragmentParameterType} annotation including the definition of the symbolic parameter name
     * <i>Code</i>, which should be used for element creation instead of the actual parameter type.
     * 
     * @param <File> the parameter type of this generic class for testing
     * 
     * @author Christian Kroeher
     *
     */
    @SuppressWarnings("hiding")
    @FragmentParameterType(parameterName = "Code")
    private class GenericFragmentParameterTypeWithSymbolicParameterName<File> { }
    
    /**
     * This class represents a generic fragment parameter type with {@link File} as parameter type. It is annotated with
     * the {@link FragmentParameterType} annotation including the definition of the the symbolic name <i>Block</i> and
     * the symbolic parameter name <i>Code</i>. Hence, both symbolic names should be used for element creation instead
     * of the actual class and parameter type names.
     * 
     * @param <File> the parameter type of this generic class for testing
     * 
     * @author Christian Kroeher
     *
     */
    @SuppressWarnings("hiding")
    @FragmentParameterType(name = "Block", parameterName = "Code")
    private class GenericFragmentParameterTypeWithSymbolicNameAndParameterName<File> { }
    
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
     * </ul>
     */
    private static final Object[][] EXPECTED_RESULTS = new Object[][] {
        {SimpleFragmentParameterType.class, null, true, ParameterType.class, ElementType.FRAGMENT_PARAMETER_TYPE, 
            SimpleFragmentParameterType.class.getSimpleName(), SimpleFragmentParameterType.class, sourcePlugin},
        
        {SimpleFragmentParameterTypeWithSymbolicName.class, null, true, ParameterType.class,
            ElementType.FRAGMENT_PARAMETER_TYPE, "Block", SimpleFragmentParameterTypeWithSymbolicName.class,
            sourcePlugin},
        
        {SimpleFragmentParameterTypeWithSymbolicParameterName.class, null, true, ParameterType.class, 
            ElementType.FRAGMENT_PARAMETER_TYPE,
            SimpleFragmentParameterTypeWithSymbolicParameterName.class.getSimpleName(),
            SimpleFragmentParameterTypeWithSymbolicParameterName.class, sourcePlugin},
        
        {SimpleFragmentParameterTypeWithSymbolicNameAndParameterName.class, null, true, ParameterType.class,
            ElementType.FRAGMENT_PARAMETER_TYPE, "Block",
            SimpleFragmentParameterTypeWithSymbolicNameAndParameterName.class, sourcePlugin},

        {GenericFragmentParameterType.class, null, true, ParameterType.class, ElementType.FRAGMENT_PARAMETER_TYPE, 
            "GenericFragmentParameterType<File>", GenericFragmentParameterType.class, sourcePlugin},
        
        {GenericFragmentParameterTypeWithSymbolicName.class, null, true, ParameterType.class,
            ElementType.FRAGMENT_PARAMETER_TYPE, "Block<File>", GenericFragmentParameterTypeWithSymbolicName.class,
            sourcePlugin},
        
        {GenericFragmentParameterTypeWithSymbolicParameterName.class, null, true, ParameterType.class,
            ElementType.FRAGMENT_PARAMETER_TYPE, "GenericFragmentParameterTypeWithSymbolicParameterName<Code>",
            GenericFragmentParameterTypeWithSymbolicParameterName.class, sourcePlugin},
        
        {GenericFragmentParameterTypeWithSymbolicNameAndParameterName.class, null, true, ParameterType.class,
            ElementType.FRAGMENT_PARAMETER_TYPE, "Block<Code>",
            GenericFragmentParameterTypeWithSymbolicNameAndParameterName.class, sourcePlugin}
    };
    
    /**
     * Constructs a new {@link FragmentParameterTypeCreationTests} instance.
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
     */
//CHECKSTYLE:OFF
    public FragmentParameterTypeCreationTests(Class<?> testInputClass, ExternalElementException expectedException,
            boolean expectedElementsExistence, Class<?> expectedElementClass, ElementType expectedElementType,
            String expectedElementName, Class<?> expectedElementSourceClass, java.io.File expectedElementSourcePlugin) {
        super(testInputClass, expectedException, expectedElementsExistence, expectedElementClass, expectedElementType,
                expectedElementName, expectedElementSourceClass, expectedElementSourcePlugin);
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
