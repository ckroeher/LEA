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

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.Test;

import net.ssehub.integration.ExternalElementException;
import net.ssehub.integration.LanguageElement;
import net.ssehub.integration.LanguageElementCreator;
import net.ssehub.integration.annotations.ArtifactParameterType;

/**
 * This class contains some basic unit tests for the {@link LanguageElementCreator}. In particular, the tests aim at the
 * correct throw of exceptions, is some of the parameters are <code>null</code>.
 * 
 * @author Christian Kroeher
 *
 */
public class BasicLanguageElementCreatorTests extends AbstractTest {
    
    /**
     * This class represents a simple (not generic) artifact parameter type. It is annotated with the 
     * {@link ArtifactParameterType} annotation without any further parameters
     * .
     * @author Christian Kroeher
     *
     */
    @ArtifactParameterType
    private class SimpleArtifactParameterType { }
    
    /**
     * Tests the correct throw of a {@link NullPointerException}, if the passed class for creating
     * {@link LanguageElement}s is <code>null</code>.
     */
    @Test
    public void testNullAsPluginClass() {
        try {
            elementCreator.createLanguageElements(null, sourcePlugin);
            fail("Passing null as plug-in class should throw a null pointer exception");
        } catch (ExternalElementException | NullPointerException e) {
            assertEquals(e.getClass(), NullPointerException.class, "Wrong exception thrown");
        }
    }
    
    /**
     * Tests the correct throw of a {@link NullPointerException}, if the passed source plug-in for creating
     * {@link LanguageElement}s is <code>null</code> <b>and</b> the given plug-in class introduced a new language
     * element.
     */
    @Test
    public void testNullAsSourcePluginDuringElementIntroduction() {
        try {
            elementCreator.createLanguageElements(SimpleArtifactParameterType.class, null);
            fail("Passing null as source plug-in should throw a null pointer exception");
        } catch (ExternalElementException | NullPointerException e) {
            assertEquals(e.getClass(), NullPointerException.class, "Wrong exception thrown");
        }
    }
    
    /**
     * Tests the rather correct acceptance of passing <code>null</code> for the source plug-in for creating
     * {@link LanguageElement}s. Although the method for creating language elements expects not <code>null</code>, it
     * is still ok as long as no language elements are introduced.
     */
    @Test
    public void testNullAsSourcePluginDuringNoElementIntroduction() {
        try {
            List<LanguageElement> createdElements = 
                    elementCreator.createLanguageElements(BasicLanguageElementCreatorTests.class, null);
            assertEquals(0, createdElements.size(), "Language elements created although source plug-in is null");
        } catch (ExternalElementException | NullPointerException e) {
            assertNull(e, "Exception thrown");
        }
    }
}
