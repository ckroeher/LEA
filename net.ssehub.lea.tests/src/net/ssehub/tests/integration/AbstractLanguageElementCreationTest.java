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
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import net.ssehub.integration.ElementType;
import net.ssehub.integration.ExternalElementException;
import net.ssehub.integration.LanguageElement;
import net.ssehub.integration.LanguageElementCreator;

/**
 * This abstract class contains common attributes and unit tests for the creation of language elements by the 
 * {@link LanguageElementCreator}. The unit tests check the common properties of any {@link LanguageElement}, while
 * other classes have to extend this class to actually apply the tests as well as extending them by unit tests, that
 * check properties of specific language elements. 
 * 
 * @author Christian Kroeher
 *
 */
@RunWith(Parameterized.class)
public abstract class AbstractLanguageElementCreationTest extends AbstractCreationTest {
    
    /**
     * The expected number of created {@link LanguageElement}s by the {@link LanguageElementCreator}. This number is
     * constantly <code>1</code> as the unit tests in this class as well as in any extending class are parameterized and
     * test only one language element at a time.
     */
    private static final int EXPECTED_ELEMENTS_NUMBER = 1;
    
    /**
     * The {@link LanguageElement} created by the {@link LanguageElementCreator} based on the current test input class.
     * This attribute is only used in extending classes to define tests of specific element properties.
     */
    protected LanguageElement createdElement;
    
    /**
     * The actual number of created {@link LanguageElement}s.
     */
    private int actualNumberOfCreatedElements;
    
    /**
     * The {@link ExternalElementException} actually thrown during the creation of a {@link LanguageElement}.
     */
    private ExternalElementException actualException;
    
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
     * The actual {@link Class} from which the {@link LanguageElement} was created. 
     */
    private Class<?> actualElementSourceClass;
    
    /**
     * The actual {@link File} denoting the source plug-in of the {@link Class} from which a {@link LanguageElement} 
     * was created.
     */
    private File actualElementSourcePlugin;
    
    /**
     * The {@link ExternalElementException} expected to be thrown during the creation of a {@link LanguageElement}. A
     * value of <code>null</code> indicates that throwing an exception was not expected.
     */
    private ExternalElementException expectedException;
    
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
     * The expected {@link Class} from which the {@link LanguageElement} was created. 
     */
    private Class<?> expectedElementSourceClass;
    
    /**
     * The expected {@link File} denoting the source plug-in of the {@link Class} from which a {@link LanguageElement} 
     * was created.
     */
    private File expectedElementSourcePlugin;
    
    /**
     * Constructs a new {@link AbstractLanguageElementCreationTest} instance.
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
    public AbstractLanguageElementCreationTest(Class<?> testInputClass, ExternalElementException expectedException, 
            boolean expectedElementsExistence, Class<?> expectedElementClass, ElementType expectedElementType,
            String expectedElementName, Class<?> expectedElementSourceClass, File expectedElementSourcePlugin) {
        // Set the expected values
        this.expectedException = expectedException;
        this.expectedElementsExistence = expectedElementsExistence;
        this.expectedElementClass = expectedElementClass;
        this.expectedElementType = expectedElementType;
        this.expectedElementName = expectedElementName;
        this.expectedElementSourceClass = expectedElementSourceClass;
        this.expectedElementSourcePlugin = expectedElementSourcePlugin;
        // Create the language element from the given test input class and set the actual values
        try {
            List<LanguageElement> createdElements = elementCreator.createLanguageElements(testInputClass, sourcePlugin);
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
            actualElementSourceClass = createdElement.getSourceClass();
            actualElementSourcePlugin = createdElement.getSourcePlugin();
            actualException = null;
        } catch (ExternalElementException e) {
            createdElement = null;
            actualElementsExistence = false;
            actualElementClass = null;
            actualElementType = null;
            actualElementName = null;
            actualElementSourceClass = null;
            actualElementSourcePlugin = null;
            actualException = e;
        }
    }
//CHECKSTYLE:ON 
    
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
