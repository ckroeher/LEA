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
import net.ssehub.integration.annotations.ArtifactParameterType;

/**
 * This class contains unit tests for the correct creation of {@link ParameterType}s of the type 
 * {@link ElementType#ARTIFACT_PARAMETER_TYPE} by the {@link LanguageElementCreator}.
 * 
 * @author Christian Kroeher
 *
 */
public class ArtifactParameterTypeCreationTests extends AbstractLanguageElementCreationTest {

    /**
     * This class represents a simple (not generic) artifact parameter type. It is annotated with the 
     * {@link ArtifactParameterType} annotation without any further parameters. Hence, the actual class name should be
     * used for element creation.
     * 
     * @author Christian Kroeher
     *
     */
    @ArtifactParameterType
    private class SimpleArtifactParameterType { }
    
    /**
     * This class represents a simple (not generic) artifact parameter type. It is annotated with the 
     * {@link ArtifactParameterType} annotation including the definition of the symbolic name <i>File</i>, which
     * should be used for element creation instead of the actual class name.
     * 
     * @author Christian Kroeher
     *
     */
    @ArtifactParameterType(name = "File")
    private class SimpleArtifactParameterTypeWithSymbolicName { }
    
    /**
     * This class represents a simple (not generic) artifact parameter type. It is annotated with the 
     * {@link ArtifactParameterType} annotation including the definition of the symbolic parameter name <i>None</i>,
     * which should be ignored as this artifact is not a generic one.
     * 
     * @author Christian Kroeher
     *
     */
    @ArtifactParameterType(parameterName = "None")
    private class SimpleArtifactParameterTypeWithSymbolicParameterName { }
    
    /**
     * This class represents a simple (not generic) artifact parameter type. It is annotated with the 
     * {@link ArtifactParameterType} annotation including the definition of the the symbolic name <i>File</i> and the 
     * symbolic parameter name <i>None</i>. While the former should be used for element creation instead of the actual
     * class name, the latter should be ignored as this artifact is not a generic one.
     * 
     * @author Christian Kroeher
     *
     */
    @ArtifactParameterType(name = "File", parameterName = "None")
    private class SimpleArtifactParameterTypeWithSymbolicNameAndParameterName { }
    
    /**
     * This class represents a generic artifact parameter type with {@link File} as parameter type. It is annotated with
     * the {@link ArtifactParameterType} annotation without any further parameters. Hence, the actual class name and
     * parameter type should be used for element creation.
     * 
     * @param <File> the parameter type of this generic class for testing
     * 
     * @author Christian Kroeher
     *
     */
    @SuppressWarnings("hiding")
    @ArtifactParameterType
    private class GenericArtifactParameterType<File> { }
    
    /**
     * This class represents a generic artifact parameter type with {@link File} as parameter type. It is annotated with
     * the {@link ArtifactParameterType} annotation including the definition of the symbolic name <i>File</i>, which
     * should be used for element creation instead of the actual class name.
     * 
     * @param <File> the parameter type of this generic class for testing
     * 
     * @author Christian Kroeher
     *
     */
    @SuppressWarnings("hiding")
    @ArtifactParameterType(name = "File")
    private class GenericArtifactParameterTypeWithSymbolicName<File> { }
    
    /**
     * This class represents a generic artifact parameter type with {@link File} as parameter type. It is annotated with
     * the {@link ArtifactParameterType} annotation including the definition of the symbolic parameter name
     * <i>MyFile</i>, which should be used for element creation instead of the actual parameter type.
     * 
     * @param <File> the parameter type of this generic class for testing
     * 
     * @author Christian Kroeher
     *
     */
    @SuppressWarnings("hiding")
    @ArtifactParameterType(parameterName = "MyFile")
    private class GenericArtifactParameterTypeWithSymbolicParameterName<File> { }
    
    /**
     * This class represents a generic artifact parameter type with {@link File} as parameter type. It is annotated with
     * the {@link ArtifactParameterType} annotation including the definition of the the symbolic name <i>File</i> and
     * the symbolic parameter name <i>MyFile</i>. Hence, both symbolic names should be used for element creation instead
     * of the actual class and parameter type names.
     * 
     * @param <File> the parameter type of this generic class for testing
     * 
     * @author Christian Kroeher
     *
     */
    @SuppressWarnings("hiding")
    @ArtifactParameterType(name = "File", parameterName = "MyFile")
    private class GenericArtifactParameterTypeWithSymbolicNameAndParameterName<File> { }
    
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
        {SimpleArtifactParameterType.class, null, true, ParameterType.class, ElementType.ARTIFACT_PARAMETER_TYPE, 
            SimpleArtifactParameterType.class.getSimpleName(), SimpleArtifactParameterType.class, sourcePlugin},
        
        {SimpleArtifactParameterTypeWithSymbolicName.class, null, true, ParameterType.class, 
            ElementType.ARTIFACT_PARAMETER_TYPE, "File", SimpleArtifactParameterTypeWithSymbolicName.class,
            sourcePlugin},
        
        {SimpleArtifactParameterTypeWithSymbolicParameterName.class, null, true, ParameterType.class,
            ElementType.ARTIFACT_PARAMETER_TYPE,
            SimpleArtifactParameterTypeWithSymbolicParameterName.class.getSimpleName(),
            SimpleArtifactParameterTypeWithSymbolicParameterName.class, sourcePlugin},
            
        {SimpleArtifactParameterTypeWithSymbolicNameAndParameterName.class, null, true, ParameterType.class,
            ElementType.ARTIFACT_PARAMETER_TYPE, 
            "File", SimpleArtifactParameterTypeWithSymbolicNameAndParameterName.class, sourcePlugin},
        
        {GenericArtifactParameterType.class, null, true, ParameterType.class, ElementType.ARTIFACT_PARAMETER_TYPE, 
            "GenericArtifactParameterType<File>", GenericArtifactParameterType.class, sourcePlugin},
        
        {GenericArtifactParameterTypeWithSymbolicName.class, null, true, ParameterType.class,
            ElementType.ARTIFACT_PARAMETER_TYPE, "File<File>", GenericArtifactParameterTypeWithSymbolicName.class,
            sourcePlugin},
        
        {GenericArtifactParameterTypeWithSymbolicParameterName.class, null, true, ParameterType.class,
            ElementType.ARTIFACT_PARAMETER_TYPE, "GenericArtifactParameterTypeWithSymbolicParameterName<MyFile>",
            GenericArtifactParameterTypeWithSymbolicParameterName.class, sourcePlugin},
        
        {GenericArtifactParameterTypeWithSymbolicNameAndParameterName.class, null, true, ParameterType.class,
            ElementType.ARTIFACT_PARAMETER_TYPE, "File<MyFile>",
            GenericArtifactParameterTypeWithSymbolicNameAndParameterName.class, sourcePlugin}
    };
    
    /**
     * Constructs a new {@link ArtifactParameterTypeCreationTests} instance.
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
    public ArtifactParameterTypeCreationTests(Class<?> testInputClass, ExternalElementException expectedException,
            boolean expectedElementsExistence, Class<?> expectedElementClass, ElementType expectedElementType,
            String expectedElementName, Class<?> expectedElementSourceClass, File expectedElementSourcePlugin) {
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
