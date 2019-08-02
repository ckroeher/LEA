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

import java.io.File;

import org.junit.BeforeClass;
import org.junit.Test;

import net.ssehub.integration.ElementType;
import net.ssehub.integration.ExternalElementException;
import net.ssehub.integration.LanguageElement;
import net.ssehub.integration.LanguageElementProvider;
import net.ssehub.integration.LanguageRegistry;

/**
 * This class contains some basic unit tests for the {@link LanguageElementProvider}.
 * 
 * @author Christian Kroeher
 *
 */
public class BasicLanguageElementProviderTests {
    
    /**
     * This {@link File} denotes the root directory in which the test data files are located.
     */
    private static final File TESTDATA_DIRECTORY = new File("./testdata/integration");

    /**
     * The {@link LanguageElementProvider} for testing the correct provision of {@link LanguageElement}s.
     */
    private static LanguageElementProvider elementProvider;
    
    /**
     * Prepares the elements commonly used by all unit tests defined in this class.
     */
    @BeforeClass
    public static void prepare() {
        elementProvider = new LanguageElementProvider(LanguageRegistry.INSTANCE);
    }
    
    /**
     * Tests the correct throw of an {@link ExternalElementException}, if the plug-in directory ({@link File}), in which
     * external plug-ins should be found, is <code>null</code>.
     */
    @Test
    public void testNullAsPluginDirectory() {
        try {
            elementProvider.detectLanguageElements(null);
            fail("No exception thrown");
        } catch (ExternalElementException e) {
            assertEquals(e.getClass(), ExternalElementException.class, "Wrong exception thrown");
        }
    }
    
    /**
     * Tests the correct throw of an {@link ExternalElementException}, if the plug-in directory ({@link File}), in which
     * external plug-ins should be found, does not exist (in the file system).
     */
    @Test
    public void testNonExistingPluginDirectory() {
        try {
            elementProvider.detectLanguageElements(new File("nonexistingdir"));
            fail("No exception thrown");
        } catch (ExternalElementException e) {
            assertEquals(e.getClass(), ExternalElementException.class, "Wrong exception thrown");
        }
    }
    
    /**
     * Tests the correct throw of an {@link ExternalElementException}, if the plug-in directory ({@link File}), in which
     * external plug-ins should be found, denotes a file (in the file system).
     */
    @Test
    public void testFileAsPluginDirectory() {
        try {
            elementProvider.detectLanguageElements(new File(TESTDATA_DIRECTORY, "/emptyPlugin/EmptyPlugin.jar"));
            fail("No exception thrown");
        } catch (ExternalElementException e) {
            assertEquals(e.getClass(), ExternalElementException.class, "Wrong exception thrown");
        }
    }
    
    /**
     * Tests the correct scan of an empty directory as the plug-in directory ({@link File}), in which
     * external plug-ins should be found. This should neither produce errors, nor any {@link LanguageElement}s in the 
     * {@link LanguageRegistry}.
     */
    @Test
    public void testEmptyDirectory() {
        try {
            elementProvider.detectLanguageElements(new File(TESTDATA_DIRECTORY, "emptyDirectory"));
            assertEquals(0, LanguageRegistry.INSTANCE.size(), 
                    "An empty plug-in directory should lead to an empty language registry");
        } catch (ExternalElementException e) {
            assertNull(e, "Exception thrown");
        }
    }
    
    /**
     * Tests the correct scan of an empty plug-in. This should neither produce errors, nor any {@link LanguageElement}s
     * in the {@link LanguageRegistry}.
     */
    @Test
    public void testEmptyPlugin() {
        try {
            elementProvider.detectLanguageElements(new File(TESTDATA_DIRECTORY, "emptyPlugin"));
            assertEquals(0, LanguageRegistry.INSTANCE.size(), 
                    "An empty plug-in should lead to an empty language registry");
        } catch (ExternalElementException e) {
            assertNull(e, "Exception thrown");
        }
    }
    
    /**
     * Tests the correct scan of a plug-in, which contains no Java classes, but files of other types. This should
     * neither produce errors, nor any {@link LanguageElement}s in the {@link LanguageRegistry}.
     */
    @Test
    public void testPluginWithoutJavaClasses() {
        try {
            elementProvider.detectLanguageElements(new File(TESTDATA_DIRECTORY, "pluginWithoutJavaClasses"));
            assertEquals(0, LanguageRegistry.INSTANCE.size(), 
                    "A plug-in without Java classes should lead to an empty language registry");
        } catch (ExternalElementException e) {
            assertNull(e, "Exception thrown");
        }
    }
    
    /**
     * Tests the correct scan of a plug-in, which contains Java classes, but they do not introduce an new elements. This
     * should neither produce errors, nor any {@link LanguageElement}s in the {@link LanguageRegistry}.
     */
    @Test
    public void testPluginWithoutNewElements() {
        try {
            elementProvider.detectLanguageElements(new File(TESTDATA_DIRECTORY, "pluginWithoutNewElements"));
            assertEquals(0, LanguageRegistry.INSTANCE.size(), 
                    "A plug-in with classes not introducing new elements should lead to an empty language registry");
        } catch (ExternalElementException e) {
            assertNull(e, "Exception thrown");
        }
    }
    
    /**
     * Tests the correct scan of a plug-in, which contains Java classes that introduce new elements. This should yield
     * corresponding {@link LanguageElement}s in the {@link LanguageRegistry}.
     */
    @Test
    public void testPluginWithNewElements() {
        String expectedRegisteredElementName = "NewElement";
        try {
            elementProvider.detectLanguageElements(new File(TESTDATA_DIRECTORY, "pluginWithNewElements"));
            assertEquals(1, LanguageRegistry.INSTANCE.size(), 
                    "A plug-in with classes introducing a new element should lead to an language registry size of 1");
            // Check if it is the expected element
            LanguageElement registeredElement = 
                    LanguageRegistry.INSTANCE.getLanguageElement(expectedRegisteredElementName);
            try {
                assertEquals(ElementType.ARTIFACT_PARAMETER_TYPE, registeredElement.getElementType(), 
                        "Wrong language element element type");
            } catch (NullPointerException e) {
                assertNull(e, "Language registry does not contain element with name \"" + expectedRegisteredElementName 
                        + "\"");
            }
        } catch (ExternalElementException e) {
            assertNull(e, "Exception thrown");
        }
    }
}
