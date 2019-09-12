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
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runners.Parameterized.Parameters;

import net.ssehub.integration.ChangeIdentifier;
import net.ssehub.integration.ElementType;
import net.ssehub.integration.ExternalElementException;
import net.ssehub.integration.LanguageElement;
import net.ssehub.integration.LanguageElementCreator;
import net.ssehub.integration.LanguageRegistry;
import net.ssehub.integration.ParameterType;

/**
 * This class contains unit tests for the correct creation of {@link ChangeIdentifier}s by the
 * {@link LanguageElementCreator}.
 * 
 * @author Christian Kroeher
 *
 */
public class ChangeIdentifierCreationTests extends AbstractLanguageElementCreationTest {

    /**
     * This class represents a change identifier. It is annotated with the
     * {@link net.ssehub.integration.annotations.ChangeIdentifier} annotation with an <i>empty</i> array of elements to
     * which this change identifier is assignable to. Hence, creating the language element must fail.
     * 
     * @author Christian Kroeher
     *
     */
    @net.ssehub.integration.annotations.ChangeIdentifier(assignableTo = { "" })
    private class ChangeIdentifierWithEmptyAssignableTo { }
    
    /**
     * This class represents a change identifier. It is annotated with the
     * {@link net.ssehub.integration.annotations.ChangeIdentifier} annotation with an array containing a single element
     * to which this change identifier is assignable to. Hence, creating the language element must succeed.
     * 
     * @author Christian Kroeher
     *
     */
    @net.ssehub.integration.annotations.ChangeIdentifier(assignableTo = { "File" })
    private class ChangeIdentifierWithSingleAssignableElement { }
    
    /**
     * This class represents a change identifier. It is annotated with the
     * {@link net.ssehub.integration.annotations.ChangeIdentifier} annotation with an array containing a multiple
     * elements to which this change identifier is assignable to. Hence, creating the language element must succeed.
     * 
     * @author Christian Kroeher
     *
     */
    @net.ssehub.integration.annotations.ChangeIdentifier(assignableTo = { "File", "Database", "Stream" })
    private class ChangeIdentifierWithMultipleAssignableElements { }
    
    /**
     * This class represents a change identifier. It is annotated with the
     * {@link net.ssehub.integration.annotations.ChangeIdentifier} annotation with an array containing a single element
     * to which this change identifier is assignable to. Further, the annotation defines a (symbolic) name. Hence,
     * creating the language element must succeed with defining its name being the (symbolic) name defined by the 
     * annotation instead of the class name.
     * 
     * @author Christian Kroeher
     *
     */
    @net.ssehub.integration.annotations.ChangeIdentifier(name = "ChangeIdentifier", assignableTo = { "File" })
    private class ChangeIdentifierWithSingleAssignableElementAndSymbolicName { }
    
    /**
     * The "File" {@link ParameterType} required to create the {@link ChangeIdentifier}s for testing.
     */
    private static ParameterType file = createParameterType(ElementType.ARTIFACT_PARAMETER_TYPE, "File");
    
    /**
     * The "Database" {@link ParameterType} required to create the {@link ChangeIdentifier}s for testing.
     */
    private static ParameterType database = createParameterType(ElementType.ARTIFACT_PARAMETER_TYPE, "Database");
    
    /**
     * The "Stream" {@link ParameterType} required to create the {@link ChangeIdentifier}s for testing.
     */
    private static ParameterType stream = createParameterType(ElementType.FRAGMENT_PARAMETER_TYPE, "Stream");
    
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
     * <li>The expected fully-qualified name of the created {@link LanguageElement}</li>
     * <li>The expected {@link Class} from which the {@link LanguageElement} was created</li>
     * <li>The expected {@link File} denoting the source plug-in of the {@link Class} from which a 
     * {@link LanguageElement} was created
     * </li>
     * <li>The array containing the expected names of the elements to which a {@link ChangeIdentifier} is assignable to
     * </li>
     * </ul>
     */
    private static final Object[][] EXPECTED_RESULTS = new Object[][] {
        {ChangeIdentifierWithEmptyAssignableTo.class, new ExternalElementException(""), false, null, null, null, null,
            null, null, null},
        
        {ChangeIdentifierWithSingleAssignableElement.class, null, true, ChangeIdentifier.class,
            ElementType.CHANGE_IDENTIFIER, ChangeIdentifierWithSingleAssignableElement.class.getSimpleName(),
            ChangeIdentifierWithSingleAssignableElement.class.getCanonicalName(),
            ChangeIdentifierWithSingleAssignableElement.class, SOURCE_PLUGIN, new ParameterType[]{file}},
        
        {ChangeIdentifierWithMultipleAssignableElements.class, null, true, ChangeIdentifier.class,
            ElementType.CHANGE_IDENTIFIER, ChangeIdentifierWithMultipleAssignableElements.class.getSimpleName(),
            ChangeIdentifierWithMultipleAssignableElements.class.getCanonicalName(),
            ChangeIdentifierWithMultipleAssignableElements.class, SOURCE_PLUGIN,
            new ParameterType[]{file, database, stream}},
        
        {ChangeIdentifierWithSingleAssignableElementAndSymbolicName.class, null, true, ChangeIdentifier.class,
            ElementType.CHANGE_IDENTIFIER, "ChangeIdentifier",
            "net.ssehub.tests.integration.ChangeIdentifierCreationTests.ChangeIdentifier",
            ChangeIdentifierWithSingleAssignableElementAndSymbolicName.class, SOURCE_PLUGIN, new ParameterType[]{file}}
    };
    
    /**
     * The array of {@link ParameterType}s representing the expected elements to which a {@link ChangeIdentifier} is
     * assignable to.
     */
    private ParameterType[] expectedAssignableElements;
    
    /**
     * The array of {@link ParameterType}s representing the actual elements to which a {@link ChangeIdentifier} is
     * assignable to.
     */
    private ParameterType[] actualAssignableElements;
    
    @Override
    protected void prepare() {
        if (!LanguageRegistry.INSTANCE.addParameterType(file)
                || !LanguageRegistry.INSTANCE.addParameterType(database)
                || !LanguageRegistry.INSTANCE.addParameterType(stream)) {
            System.err.println("Preparation failed");
        }
    }
    
    /**
     * Constructs a new {@link ChangeIdentifierCreationTests} instance.
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
     * @param expectedAssignableElements the array of {@link ParameterType}s representing the expected elements to which
     *        a {@link ChangeIdentifier} is assignable to
     */
//CHECKSTYLE:OFF
    public ChangeIdentifierCreationTests(Class<?> testInputClass, ExternalElementException expectedException,
            boolean expectedElementsExistence, Class<?> expectedElementClass, ElementType expectedElementType,
            String expectedElementName, String expectedElementFullyQualifiedName, Class<?> expectedElementSourceClass, 
            File expectedElementSourcePlugin, ParameterType[] expectedAssignableElements) {
        super(testInputClass, expectedException, expectedElementsExistence, expectedElementClass, expectedElementType,
                expectedElementName, expectedElementFullyQualifiedName, expectedElementSourceClass,
                expectedElementSourcePlugin);
        this.expectedAssignableElements = expectedAssignableElements;
        if (createdElement != null) {
            ChangeIdentifier changeIdentifier = (ChangeIdentifier) createdElement;
            actualAssignableElements = changeIdentifier.getAssignableElements();
        } else {
            actualAssignableElements = null;
        }
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
    
    /**
     * Tests whether the number of {@link #actualAssignableElements} is equal to {@link #expectedAssignableElements}. 
     */
    @Test
    public void testNumberOfCreatedAssignableElements() {
        if (expectedAssignableElements != null) {            
            assertEquals(expectedAssignableElements.length, actualAssignableElements.length, 
                    "Wrong number of assignable elements");
        } else {
            assertNull(actualAssignableElements, "Assignable elements should be null");
        }
    }
    
    /**
     * Tests whether the elements in {@link #actualAssignableElements} are the same as the elements in 
     * {@link #expectedAssignableElements} at the same index.
     */
    @Test
    public void testCreatedAssignableElements() {
        if (expectedAssignableElements != null) {            
            for (int i = 0; i < expectedAssignableElements.length; i++) {
                assertEquals(expectedAssignableElements[i], actualAssignableElements[i], "Wrong assignable element");
            }
        } else {
            assertNull(actualAssignableElements, "Assignable elements should be null");
        }
    }

}
