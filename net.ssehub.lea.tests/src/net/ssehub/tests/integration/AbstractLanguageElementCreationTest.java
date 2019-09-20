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
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import net.ssehub.integration.Call;
import net.ssehub.integration.ChangeIdentifier;
import net.ssehub.integration.ElementType;
import net.ssehub.integration.ExternalLanguageElementCreator;
import net.ssehub.integration.LanguageElement;
import net.ssehub.integration.LanguageElementException;
import net.ssehub.integration.LanguageRegistry;
import net.ssehub.integration.ParameterType;
import net.ssehub.integration.ParameterTypeInstance;

/**
 * This abstract class contains common attributes and unit tests for the creation of language elements by the 
 * {@link ExternalLanguageElementCreator}. The unit tests check the common properties of any {@link LanguageElement},
 * while other classes have to extend this class to actually apply the tests as well as extending them by unit tests,
 * that check properties of specific language elements. 
 * 
 * @author Christian Kroeher
 *
 */
@RunWith(Parameterized.class)
public abstract class AbstractLanguageElementCreationTest extends AbstractCreationTest {
    
    /**
     * The expected number of created {@link LanguageElement}s by the {@link ExternalLanguageElementCreator}. This
     * number is constantly <code>1</code> as the unit tests in this class as well as in any extending class are
     * parameterized and test only one language element at a time.
     */
    private static final int EXPECTED_ELEMENTS_NUMBER = 1;
    
    /**
     * The {@link LanguageElement} created by the {@link ExternalLanguageElementCreator} based on the current test input
     * class. This attribute is only used in extending classes to define tests of specific element properties.
     */
    protected LanguageElement createdElement;
    
    /**
     * The actual number of created {@link LanguageElement}s.
     */
    private int actualNumberOfCreatedElements;
    
    /**
     * The {@link LanguageElementException} actually thrown during the creation of a {@link LanguageElement}.
     */
    private LanguageElementException actualException;
    
    /**
     * The actual declaration of whether the created {@link LanguageElement} is not <code>null</code> 
     * (<code>true</code>) or is <code>null</code> (<code>false</code>).
     */
    private boolean actualElementsExistence;
    
    /**
     * The actual {@link Class} of the created {@link LanguageElement}. 
     */
    private Class<?> actualElementClass;
    
    /**
     * The actual {@link ElementType} of the created {@link LanguageElement}. 
     */
    private ElementType actualElementType;
    
    /**
     * The actual name of the created {@link LanguageElement}. 
     */
    private String actualElementName;
    
    /**
     * The actual fully-qualified name of the created {@link LanguageElement}. 
     */
    private String actualElementFullyQualifiedName;
    
    /**
     * The actual {@link Class} from which the {@link LanguageElement} was created. 
     */
    private Class<?> actualElementSourceClass;
    
    /**
     * The actual {@link File} denoting the source plug-in of the {@link Class} from which a {@link LanguageElement} 
     * was created.
     */
    private File actualElementSourcePlugin;
    
    /**
     * The {@link LanguageElementException} expected to be thrown during the creation of a {@link LanguageElement}. A
     * value of <code>null</code> indicates that throwing an exception was not expected.
     */
    private LanguageElementException expectedException;
    
    /**
     * The declaration of whether it is expected that the created {@link LanguageElement} is not <code>null</code> 
     * (<code>true</code>) or should be <code>null</code> (<code>false</code>).
     */
    private boolean expectedElementsExistence;
    
    /**
     * The expected {@link Class} of the created {@link LanguageElement}. 
     */
    private Class<?> expectedElementClass;
    
    /**
     * The expected {@link ElementType} of the created {@link LanguageElement}. 
     */
    private ElementType expectedElementType;
    
    /**
     * The expected name of the created {@link LanguageElement}. 
     */
    private String expectedElementName;
    
    /**
     * The expected fully-qualified name of the created {@link LanguageElement}. 
     */
    private String expectedElementFullyQualifiedName;
    
    /**
     * The expected {@link Class} from which the {@link LanguageElement} was created. 
     */
    private Class<?> expectedElementSourceClass;
    
    /**
     * The expected {@link File} denoting the source plug-in of the {@link Class} from which a {@link LanguageElement} 
     * was created.
     */
    private File expectedElementSourcePlugin;
    
    /**
     * Creates a {@link ParameterType} of the given {@link ElementType} and with the given name.
     * 
     * @param elementType the {@link ElementType} of the {@link ParameterType} to create
     * @param name the {@link String} defining the name of the {@link ParameterType} to create
     * @return the created {@link ParameterType} or <code>null</code>, if creating it failed
     */
    protected static ParameterType createParameterType(ElementType elementType, String name) {
        ParameterType createdParameterType = null;
        try {
            createdParameterType = new ParameterType(elementType, name, ChangeIdentifierCreationTests.class,
                    SOURCE_PLUGIN);
        } catch (LanguageElementException e) {
            System.err.println("Creating required parameter type failed");
            e.printStackTrace();
        }
        return createdParameterType;
    }
    
    /**
     * Creates a {@link ParameterTypeInstance} of the given {@link ParameterType} and its given set definition.
     * 
     * @param parameterType the {@link ParameterType} for which an instance should be created
     * @param isSet the definition of whether the created {@link ParameterTypeInstance} should represent a set
     *        (<code>true</code>) or not (<code>false</code>)
     * @return the created {@link ParameterTypeInstance} or <code>null</code>, if creating it failed
     */
    protected static ParameterTypeInstance createParameterTypeInstance(ParameterType parameterType, boolean isSet) {
        ParameterTypeInstance createdParameterTypeInstance = null;
        try {
            createdParameterTypeInstance = new ParameterTypeInstance(parameterType, isSet);
        } catch (LanguageElementException e) {
            System.err.println("Creating required parameter type instance failed");
            e.printStackTrace();
        }
        return createdParameterTypeInstance;
    }
    
    /**
     * Constructs a new {@link AbstractLanguageElementCreationTest} instance.
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
     */
//CHECKSTYLE:OFF
    public AbstractLanguageElementCreationTest(Class<?> testInputClass, LanguageElementException expectedException, 
            boolean expectedElementsExistence, Class<?> expectedElementClass, ElementType expectedElementType,
            String expectedElementName, String expectedElementFullyQualifiedName, Class<?> expectedElementSourceClass,
            File expectedElementSourcePlugin) {
        super();
        // Set the expected values
        this.expectedException = expectedException;
        this.expectedElementsExistence = expectedElementsExistence;
        this.expectedElementClass = expectedElementClass;
        this.expectedElementType = expectedElementType;
        this.expectedElementName = expectedElementName;
        this.expectedElementFullyQualifiedName = expectedElementFullyQualifiedName;
        this.expectedElementSourceClass = expectedElementSourceClass;
        this.expectedElementSourcePlugin = expectedElementSourcePlugin;
        createActualValues(testInputClass, expectedElementType, expectedElementFullyQualifiedName);
    }
//CHECKSTYLE:ON
    
    /**
     * Creates the {@link LanguageElement} based on the given {@link Class} and sets the actual test values by
     * retrieving the created element from the {@link LanguageRegistry} via the given {@link ElementType} and fully-
     * qualified name.
     * 
     * @param testInputClass the {@link Class} from which the {@link LanguageElement} should be created
     * @param expectedElementType the expected {@link ElementType} of the created {@link LanguageElement} 
     * @param expectedElementFullyQualifiedName the expected fully-qualified name of the created {@link LanguageElement}
     */
    private void createActualValues(Class<?> testInputClass, ElementType expectedElementType,
            String expectedElementFullyQualifiedName) {
        LanguageRegistry.INSTANCE.clear();
        prepare();
        try {
            List<LanguageElement> createdElements = createLanguageElement(testInputClass, expectedElementType,
                    expectedElementFullyQualifiedName);
            actualNumberOfCreatedElements = createdElements.size();
            actualElementsExistence = (createdElements != null && !createdElements.isEmpty());
            int createdElementsCounter = 0;
            while (actualElementsExistence && createdElementsCounter < createdElements.size()) {
                actualElementsExistence = (createdElements.get(createdElementsCounter) != null);
                createdElementsCounter++;
            }
            createdElement = createdElements.get(0);
            actualElementClass = createdElement.getClass();
            actualElementType = createdElement.getElementType();
            actualElementName = createdElement.getName();
            actualElementFullyQualifiedName = createdElement.getFullyQualifiedName();
            actualElementSourceClass = createdElement.getSourceClass();
            actualElementSourcePlugin = createdElement.getSourcePlugin();
            actualException = null;
        } catch (LanguageElementException e) {
            createdElement = null;
            actualElementsExistence = false;
            actualElementClass = null;
            actualElementType = null;
            actualElementName = null;
            actualElementFullyQualifiedName = null;
            actualElementSourceClass = null;
            actualElementSourcePlugin = null;
            actualException = e;
        }
    }
    
    /**
     * Creates the {@link LanguageElement} based on the given {@link Class} and retrieves it from the
     * {@link LanguageRegistry} via the given {@link ElementType} and fully-qualified name.
     * 
     * @param testInputClass the {@link Class} from which the {@link LanguageElement} should be created
     * @param expectedElementType the expected {@link ElementType} of the created {@link LanguageElement} 
     * @param expectedElementFullyQualifiedName the expected fully-qualified name of the created {@link LanguageElement}
     * @return the {@link List} of created {@link LanguageElement}s or <code>null</code>, if no elements can be found
     * @throws LanguageElementException if creating a language element failed; it is not thrown, if the given class does
     *         not introduce any language elements
     */
    private List<LanguageElement> createLanguageElement(Class<?> testInputClass, ElementType expectedElementType,
            String expectedElementFullyQualifiedName) throws LanguageElementException {
        List<LanguageElement> createdElements = null;
        elementCreator.createLanguageElements(testInputClass, SOURCE_PLUGIN);
        elementCreator.finalizeCreations();
        if (expectedElementType == ElementType.ARTIFACT_PARAMETER_TYPE 
                || expectedElementType == ElementType.FRAGMENT_PARAMETER_TYPE 
                || expectedElementType == ElementType.RESULT_PARAMETER_TYPE) {
            List<ParameterType> availableParameterTypes = 
                    LanguageRegistry.INSTANCE.getParameterTypes(expectedElementType, expectedElementFullyQualifiedName);
            if (availableParameterTypes != null) {
                createdElements = new ArrayList<LanguageElement>();
                for (ParameterType availableParameterType : availableParameterTypes) {
                    createdElements.add(availableParameterType);
                }
            }
        } else if (expectedElementType == ElementType.CHANGE_IDENTIFIER) {
            List<ChangeIdentifier> availableChangeIdentifiers = 
                    LanguageRegistry.INSTANCE.getChangeIdentifiers(expectedElementFullyQualifiedName);
            if (availableChangeIdentifiers != null) {
                createdElements = new ArrayList<LanguageElement>();
                for (ChangeIdentifier availableChangeIdentifier : availableChangeIdentifiers) {
                    createdElements.add(availableChangeIdentifier);
                }
            }
        } else if (expectedElementType == ElementType.OPERATION 
                || expectedElementType == ElementType.EXTRACTOR_CALL 
                || expectedElementType == ElementType.ANALYSIS_CALL) {
            List<Call> availableCalls = LanguageRegistry.INSTANCE.getCalls(expectedElementType,
                    expectedElementFullyQualifiedName);
            if (availableCalls != null) {
                createdElements = new ArrayList<LanguageElement>();
                for (Call availableCall : availableCalls) {
                    createdElements.add(availableCall);
                }
            }
        }
        return createdElements;
    }
    
    /**
     * Prepares the required elements for testing. This method is called after {@link LanguageRegistry#clear()} and
     * before any new {@link LanguageElement} is created.
     */
    protected abstract void prepare();
    
    /**
     * Tests whether the creation of the current language element was successful. In that case the 
     * {@link #expectedException} and the {@link #actualException} must be <code>null</code>.
     */
    @Test
    public void testElementCreationSuccessful() {
        if (expectedException != null) {            
            assertEquals(expectedException.getClass(), actualException.getClass(), "Wrong exception thrown");
        } else {
            assertNull(actualException, "No exception expected");
        }
    }
    
    /**
     * Tests whether the {@link #createdElements} is <code>null</code>, <i>empty</i>, and its elements is
     *  <code>null</code> as defined by {@link #expectedElementsExistence}.
     */
    @Test
    public void testCreatedElementsExists() {
        assertEquals(expectedElementsExistence, actualElementsExistence, "Wrong element existence");
    }
    
    /**
     * Tests whether the number of {@link #createdElements} is equal to {@link #EXPECTED_ELEMENTS_NUMBER}.
     */
    public void testNumberOfCreatedElements() {
        assertEquals(EXPECTED_ELEMENTS_NUMBER, actualNumberOfCreatedElements, "Wrong number of created elements");
    }
    
    /**
     * Tests whether the first element in {@link #createdElements} has the {@link #expectedElementClass}.
     */
    @Test
    public void testCreatedElementClass() {
        assertEquals(expectedElementClass, actualElementClass, "Wrong element class");
    }
    
    /**
     * Tests whether the first element in {@link #createdElements} has the {@link #expectedElementType}.
     */
    @Test
    public void testCreatedElementType() {
        assertEquals(expectedElementType, actualElementType, "Wrong element type");
    }
    
    /**
     * Tests whether the first element in {@link #createdElements} has the {@link #expectedElementName}.
     */
    @Test
    public void testCreatedElementName() {
        assertEquals(expectedElementName, actualElementName, "Wrong element name");
    }
    
    /**
     * Tests whether the first element in {@link #createdElements} has the {@link #expectedElementFullyQualifiedName}.
     */
    @Test
    public void testCreatedElementFullyQualifiedName() {
        assertEquals(expectedElementFullyQualifiedName, actualElementFullyQualifiedName, 
                "Wrong fully-qualified element name");
    }
    
    /**
     * Tests whether the first element in {@link #createdElements} has the {@link #expectedElementSourceClass}.
     */
    @Test
    public void testCreatedElementSourceClass() {
        assertEquals(expectedElementSourceClass, actualElementSourceClass, "Wrong element source class");
    }
    
    /**
     * Tests whether the first element in {@link #createdElements} has the {@link #expectedElementSourcePlugin}.
     */
    @Test
    public void testCreatedElementSourcePlugin() {
        assertEquals(expectedElementSourcePlugin, actualElementSourcePlugin, "Wrong element source plug-in");
    }
    
}
