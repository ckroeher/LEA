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

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import net.ssehub.integration.Call;
import net.ssehub.integration.ChangeIdentifier;
import net.ssehub.integration.LanguageElement;
import net.ssehub.integration.LanguageRegistry;
import net.ssehub.integration.ParameterType;

/**
 * This abstract class contains common attributes and methods for the specific unit tests of the 
 * {@link LanguageRegistry}. Further, it performs a single, common unit test for checking the correct addition of a 
 * {@link LanguageElement} to the {@link LanguageRegistry} defined by the respective specific language registry test
 * class. 
 * 
 * @author Christian Kroeher
 *
 */
public abstract class AbstractLanguageRegistryTest {

    /**
     * The {@link File} denoting the source plug-in of the {@link Class} from which a {@link LanguageElement} should be
     * created. This is just a dummy file as it has no impact on the actual creation, but is only used as a constructor
     * parameter.
     */
    protected static final File SOURCE_PLUGIN = new File("./");
    
    /**
     * The {@link LanguageElement} previously set as the {@link #currentElement}. This attribute is used to ensure
     * that while parameterized tests call this method multiple times for the same test element, it is only added once
     * to the language registry.
     */
    private static LanguageElement previousTestElement = null;
    
    /**
     * The fact of whether the {@link #previousTestElement} is successfully added to the {@link LanguageRegistry}
     * (<code>true</code>) or not (<code>false</code>). 
     */
    private static boolean actualAdditionOfLanguageElement = false;
    
    /**
     * The identifier (name) of the specific class instantiating this abstract class.
     */
    protected String id;
    
    /**
     * The reference of this class to the {@link LanguageRegistry}.
     */
    protected LanguageRegistry languageRegistry = LanguageRegistry.INSTANCE;
    
    /**
     * The {@link LanguageElement} passed to the constructor of this class for testing.
     */
    protected LanguageElement currentElement;
    
    /**
     * The expectation of whether the {@link #currentElement} is successfully added to the {@link LanguageRegistry}
     * (<code>true</code>) or not (<code>false</code>). 
     */
    private boolean expectedAdditionOfLanguageElement;
    
    /**
     * Creates a new {@link AbstractLanguageRegistryTest} instance.
     * 
     * @param testClassName the name of the specific test class calling this constructor
     * @param testElement the {@link LanguageElement} for testing
     * @param expectedAdditionOfLanguageElement the expectation of whether the given {@link LanguageElement} is
     *        successfully added to the {@link LanguageRegistry} (<code>true</code>) or not (<code>false</code>)
     */
    public AbstractLanguageRegistryTest(String testClassName, LanguageElement testElement,
            boolean expectedAdditionOfLanguageElement) {
        this.id = testClassName;
        this.currentElement = testElement;
        this.expectedAdditionOfLanguageElement = expectedAdditionOfLanguageElement;
    }
    
    /**
     * Adds the {@link #currentElement} to the {@link LanguageRegistry}, if it is not the {@link #previousTestElement}
     * before the actual tests are executed. This ensures that each test element is only added once to the registry.
     */
    @Before
    public void addCurrentTestElement() {
        if (previousTestElement == null || !previousTestElement.equals(currentElement)) {
            System.out.println(id + " Adding " + currentElement.getElementType() + " \"" 
                    + currentElement.getFullyQualifiedName() + "\" to language registry");
            actualAdditionOfLanguageElement = addToLanguageRegistry(currentElement);
            previousTestElement = currentElement;
        }
    }
    
    /**
     * Adds the given {@link LanguageElement} to the {@link LanguageRegistry}.
     * 
     * @param languageElement the {@link LanguageElement} to add to the {@link LanguageRegistry}
     * @return <code>true</code>, if adding the given {@link LanguageElement} to the {@link LanguageRegistry} was
     *         successful; <code>false</code> otherwise
     */
    private boolean addToLanguageRegistry(LanguageElement languageElement) {
        boolean addedSuccessfully = false;
        if (languageElement instanceof ParameterType) {
            ParameterType parameterType = (ParameterType) languageElement;
            addedSuccessfully = languageRegistry.addParameterType(parameterType);
        } else if (languageElement instanceof ChangeIdentifier) {
            ChangeIdentifier changeIdentifier = (ChangeIdentifier) languageElement;
            addedSuccessfully = languageRegistry.addChangeIdentifier(changeIdentifier);
        } else if (languageElement instanceof Call) {
            Call call = (Call) languageElement;
            addedSuccessfully = languageRegistry.addCall(call);
        } else {
            System.out.println(id + " Language element \"" + languageElement.getFullyQualifiedName() 
                    + " is of unknown instance");
        }
        return addedSuccessfully;
    }
    
    /**
     * Tests whether the {@link #expectedAdditionOfLanguageElement} matches the 
     * {@link #actualAdditionOfLanguageElement}. 
     */
    @Test
    public void testAdditionOfLanguageElement() {
        assertEquals(expectedAdditionOfLanguageElement, actualAdditionOfLanguageElement, 
                id + " Addition of language element not as expected");
    }
    
}
