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

import org.junit.Test;

import net.ssehub.integration.Call;
import net.ssehub.integration.ElementType;
import net.ssehub.integration.ExternalElementException;
import net.ssehub.integration.LanguageElement;
import net.ssehub.integration.LanguageElementCreator;

/**
 * This abstract class contains common attributes and unit tests for the creation of {@link Call}s by the 
 * {@link LanguageElementCreator}. The unit tests check the common properties of any {@link Call}, while
 * other classes have to extend this class to actually apply the tests as well as extending them by unit tests, that
 * check properties of specific call types, like operation, extractor, or analysis calls. 
 * 
 * @author Christian Kroeher
 *
 */
public abstract class AbstractCallCreationTest extends AbstractLanguageElementCreationTest {
    
    /**
     * The expected name of the type of element(s) the created {@link Call} will return.
     */
    private String expectedReturnType;
    
    /**
     * The expected array of names, which denote the elements the created {@link Call} accepts as parameters.
     */
    private String[] expectedParameters;
    
    /**
     * The actual name of the type of element(s) the created {@link Call} will return.
     */
    private String actualReturnType;
    
    /**
     * The actual array of names, which denote the elements the created {@link Call} accepts as parameters.
     */
    private String[] actualParameters;

    /**
     * Constructs a new {@link AbstractCallCreationTest} instance.
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
     * @param expectedReturnType the expected name of the type of element(s) the created {@link Call} will return
     * @param expectedParameters the expected array of names, which denote the elements the created {@link Call} accepts
     *        as parameters
     */
//CHECKSTYLE:OFF
    public AbstractCallCreationTest(Class<?> testInputClass, ExternalElementException expectedException,
            boolean expectedElementsExistence, Class<?> expectedElementClass, ElementType expectedElementType,
            String expectedElementName, Class<?> expectedElementSourceClass, File expectedElementSourcePlugin, 
            String expectedReturnType, String[] expectedParameters) {
        super(testInputClass, expectedException, expectedElementsExistence, expectedElementClass, expectedElementType,
                expectedElementName, expectedElementSourceClass, expectedElementSourcePlugin);
        this.expectedReturnType = expectedReturnType;
        this.expectedParameters = expectedParameters;
        
        if (createdElement != null) {            
            Call createdCall = (Call) createdElement;
            actualReturnType = createdCall.getReturnType();
            actualParameters = createdCall.getParameters();
        } else {
            actualReturnType = null;
            actualParameters = null;
        }
    }
//CHECKSTYLE:ON
    
    /**
     * Tests whether the {@link #expectedReturnType} is equal to the {@link #actualReturnType}.
     */
    @Test
    public void testCreatedCallReturnType() {
        assertEquals(expectedReturnType, actualReturnType, "Wrong return type");
    }
    
    /**
     * Tests whether the number of elements in {@link #expectedParameters} is equal to the number of elements in 
     * {@link #actualParameters}.
     */
    @Test
    public void testCreatedCallNumberOfParameters() {
        if (expectedParameters != null) {            
            assertEquals(expectedParameters.length, actualParameters.length, "Wrong number of parameters");
        } else {
            assertNull(actualParameters, "Parameters should be null");
        }
    }
    
    /**
     * Tests whether the elements in {@link #expectedParameters} are the same as the elements in 
     * {@link #actualParameters} at the same index.
     */
    @Test
    public void testCreatedCallParameters() {
        if (expectedParameters != null) {            
            for (int i = 0; i < expectedParameters.length; i++) {
                assertEquals(expectedParameters[i], actualParameters[i], "Wrong parameter");
            }
        } else {
            assertNull(actualParameters, "Parameters should be null");
        }
    }
}
