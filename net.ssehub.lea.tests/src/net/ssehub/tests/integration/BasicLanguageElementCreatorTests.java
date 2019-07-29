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

import static org.junit.jupiter.api.Assertions.assertNotNull;
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
            List<LanguageElement> createdElements = elementCreator.createLanguageElements(null, sourcePlugin);
            assertNull(createdElements, "Passing null as plug-in class should throw a null pointer exception");
        } catch (ExternalElementException e) {
            assertNull(e, "Passing null as plug-in class should not throw an external element exception");
        } catch (NullPointerException e) {
            assertNotNull(e, "Passing null as plug-in class should throw a null pointer exception");
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
            List<LanguageElement> createdElements = 
                    elementCreator.createLanguageElements(SimpleArtifactParameterType.class, null);
            assertNull(createdElements, "Passing null as source plug-in should throw a null pointer exception");
        } catch (ExternalElementException e) {
            assertNull(e, "Passing null as source plug-in should not throw an external element exception");
        } catch (NullPointerException e) {
            assertNotNull(e, "Passing null as source plug-in should throw a null pointer exception");
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
            assertNotNull(createdElements, 
                    "Passing null as source plug-in is ok, if no language elements are introduced");
        } catch (ExternalElementException e) {
            assertNull(e, "Passing null as source plug-in is ok, if no language elements are introduced");
        } catch (NullPointerException e) {
            assertNull(e, "Passing null as source plug-in is ok, if no language elements are introduced");
        }
    }
}
