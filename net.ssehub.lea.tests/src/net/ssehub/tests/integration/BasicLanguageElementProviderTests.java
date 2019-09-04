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
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import net.ssehub.integration.Call;
import net.ssehub.integration.ExternalElementException;
import net.ssehub.integration.LanguageElement;
import net.ssehub.integration.LanguageElementProvider;
import net.ssehub.integration.LanguageRegistry;
import net.ssehub.integration.ParameterType;

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
     * Creates a new {@link LanguageElementProvider} instance for each of the unit tests in this class to avoid side-
     * effects, which cannot occur in production as the element provider is called exactly once to create all elements.
     */
    @Before
    public void prepare() {
        elementProvider = new LanguageElementProvider();
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
    
    /*
     * TODO The following two tests are currently obsolete as the LanguageElementProvider does not throw any exception
     * anymore, if a given search path does not denote a valid directory, but simply skips that path. If we have proper
     * error messages to be retrieved somewhere, we can reestablish this test. 
     */
    
    /**
     * Tests the correct throw of an {@link ExternalElementException}, if the plug-in directory ({@link File}), in which
     * external plug-ins should be found, does not exist (in the file system).
     */
    @Test
    public void testNonExistingPluginDirectory() {
        File pluginDirectory = new File("nonexistingdir");
        List<String> searchPaths = new ArrayList<String>();
        searchPaths.add(pluginDirectory.getAbsolutePath());
        try {
            elementProvider.detectLanguageElements(searchPaths);
//            fail("No exception thrown");
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
        File pluginDirectory = new File(TESTDATA_DIRECTORY, "/emptyPlugin/EmptyPlugin.jar");
        List<String> searchPaths = new ArrayList<String>();
        searchPaths.add(pluginDirectory.getAbsolutePath());
        try {
            elementProvider.detectLanguageElements(searchPaths);
//            fail("No exception thrown");
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
        File pluginDirectory = new File(TESTDATA_DIRECTORY, "emptyDirectory");
        List<String> searchPaths = new ArrayList<String>();
        searchPaths.add(pluginDirectory.getAbsolutePath());
        int expectedLanguageElementSize = LanguageRegistry.INSTANCE.size();
        try {
            elementProvider.detectLanguageElements(searchPaths);
            assertEquals(expectedLanguageElementSize, LanguageRegistry.INSTANCE.size(), 
                    "An empty plug-in directory should not change the number of elements in the language directory");
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
        File pluginDirectory = new File(TESTDATA_DIRECTORY, "emptyPlugin");
        List<String> searchPaths = new ArrayList<String>();
        searchPaths.add(pluginDirectory.getAbsolutePath());
        int expectedLanguageElementSize = LanguageRegistry.INSTANCE.size();
        try {
            elementProvider.detectLanguageElements(searchPaths);
            assertEquals(expectedLanguageElementSize, LanguageRegistry.INSTANCE.size(), 
                    "An empty plug-in should not change the number of elements in the language directory");
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
        File pluginDirectory = new File(TESTDATA_DIRECTORY, "pluginWithoutJavaClasses");
        List<String> searchPaths = new ArrayList<String>();
        searchPaths.add(pluginDirectory.getAbsolutePath());
        int expectedLanguageElementSize = LanguageRegistry.INSTANCE.size();
        try {
            elementProvider.detectLanguageElements(searchPaths);
            assertEquals(expectedLanguageElementSize, LanguageRegistry.INSTANCE.size(), 
                    "A plug-in without Java classes should not change the number of elements in the language "
                    + "directory");
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
        File pluginDirectory = new File(TESTDATA_DIRECTORY, "pluginWithoutNewElements");
        List<String> searchPaths = new ArrayList<String>();
        searchPaths.add(pluginDirectory.getAbsolutePath());
        int expectedLanguageElementSize = LanguageRegistry.INSTANCE.size();
        try {
            elementProvider.detectLanguageElements(searchPaths);
            assertEquals(expectedLanguageElementSize, LanguageRegistry.INSTANCE.size(), 
                    "A plug-in with classes not introducing new elements should not change the number of elements in "
                    + "the language directory");
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
        File pluginDirectory = new File(TESTDATA_DIRECTORY, "pluginWithNewElements");
        List<String> searchPaths = new ArrayList<String>();
        searchPaths.add(pluginDirectory.getAbsolutePath());
        int expectedLanguageElementSize = LanguageRegistry.INSTANCE.size() + 1;
        String expectedRegisteredElementName = "NewElement";
        try {
            elementProvider.detectLanguageElements(searchPaths);
            assertEquals(expectedLanguageElementSize, LanguageRegistry.INSTANCE.size(), 
                    "A plug-in with classes introducing a new element should increase the number of elements in the "
                    + "language directory by 1");
            // Check if it is the expected element
            List<ParameterType> registerdArtifactParameterTypes = 
                    LanguageRegistry.INSTANCE.getArtifactParameterTypes(expectedRegisteredElementName);
            try {
                assertEquals(expectedRegisteredElementName, registerdArtifactParameterTypes.get(0).getName(),
                        "Wrong language element");
            } catch (NullPointerException e) {
                assertNull(e, "Language registry does not contain element with name \"" + expectedRegisteredElementName 
                        + "\"");
            }
        } catch (ExternalElementException e) {
            assertNull(e, "Exception thrown");
        }
    }
    
    /**
     * Tests the correct scan of a plug-in, which contains Java classes that introduce new member operations. This 
     * should yield corresponding {@link LanguageElement}s in the {@link LanguageRegistry}.
     */
    @Test
    public void testPluginWithNewMemberOperations() {
        File pluginDirectory = new File(TESTDATA_DIRECTORY, "pluginWithNewMemberOperations");
        List<String> searchPaths = new ArrayList<String>();
        searchPaths.add(pluginDirectory.getAbsolutePath());
        int expectedLanguageElementSize = LanguageRegistry.INSTANCE.size() + 6;
        try {
            elementProvider.detectLanguageElements(searchPaths);
            assertEquals(expectedLanguageElementSize, LanguageRegistry.INSTANCE.size(), 
                    "A plug-in with classes introducing new elements should increase the number of elements in the "
                    + "language directory by 6");
            /*
             * There should be the following member operations:
             *    1. getAbsolutePath() for artifact parameter type NewFile
             *    2. getAbsolutePath() for artifact parameter type FileWithMemberOperation
             *    3. getPath() for artifact parameter type FileWithMemberOperation
             */
            try {
                ParameterType newFile = LanguageRegistry.INSTANCE.getArtifactParameterTypes("NewFile").get(0);
                // 1. getAbsolutePath() for artifact parameter type NewFile
                Call getAbsolutePathForNewFile = 
                        LanguageRegistry.INSTANCE.getMemberOperations(newFile.getName()).get(0);
                assertTrue(getAbsolutePathForNewFile.isMemberOperation(), "Call \"" 
                        + getAbsolutePathForNewFile.getFullyQualifiedName() + "\" should be a member operation"); 
                assertTrue(getAbsolutePathForNewFile.isMemberOperationOf(newFile.getName()), "Call \"" 
                        + getAbsolutePathForNewFile.getFullyQualifiedName() + "\" should be a member operation of \"" 
                        + newFile.getFullyQualifiedName() + "\"");
                assertEquals(newFile.getName(), getAbsolutePathForNewFile.getParentParameterType().getName(), "Call \"" 
                        + getAbsolutePathForNewFile.getFullyQualifiedName() + "\" should have \"" 
                        + newFile.getFullyQualifiedName() + "\" as parent parameter type");
                
                ParameterType fileWithMemberOperation = 
                        LanguageRegistry.INSTANCE.getArtifactParameterTypes("FileWithMemberOperation").get(0);
                // 2. getAbsolutePath() for artifact parameter type FileWithMemberOperation
                Call getAbsolutePathForFileWithMemberOperation = 
                        LanguageRegistry.INSTANCE.getMemberOperations(fileWithMemberOperation.getName()).get(0);
                assertTrue(getAbsolutePathForFileWithMemberOperation.isMemberOperation(), "Call \"" 
                        + getAbsolutePathForFileWithMemberOperation.getFullyQualifiedName() 
                        + "\" should be a member operation"); 
                assertTrue(getAbsolutePathForFileWithMemberOperation
                        .isMemberOperationOf(fileWithMemberOperation.getName()), "Call \"" 
                        + getAbsolutePathForFileWithMemberOperation.getFullyQualifiedName() 
                        + "\" should be a member operation of \"" + fileWithMemberOperation.getFullyQualifiedName() 
                        + "\"");
                assertEquals(fileWithMemberOperation.getName(), 
                        getAbsolutePathForFileWithMemberOperation.getParentParameterType().getName(), "Call \"" 
                                + getAbsolutePathForFileWithMemberOperation.getFullyQualifiedName() 
                                + "\" should have \"" + fileWithMemberOperation.getFullyQualifiedName() 
                                + "\" as parent parameter type");
                // 3. getPath() for artifact parameter type FileWithMemberOperation
                Call getPathForFileWithMemberOperation = 
                        LanguageRegistry.INSTANCE.getMemberOperations(fileWithMemberOperation.getName()).get(1);
                assertTrue(getPathForFileWithMemberOperation.isMemberOperation(), "Call \"" 
                        + getPathForFileWithMemberOperation.getFullyQualifiedName() 
                        + "\" should be a member operation"); 
                assertTrue(getPathForFileWithMemberOperation.isMemberOperationOf(fileWithMemberOperation.getName()), 
                        "Call \"" + getPathForFileWithMemberOperation.getFullyQualifiedName() 
                        + "\" should be a member operation of \"" + fileWithMemberOperation.getFullyQualifiedName() 
                        + "\"");
                assertEquals(fileWithMemberOperation.getName(), 
                        getPathForFileWithMemberOperation.getParentParameterType().getName(), "Call \"" 
                                + getPathForFileWithMemberOperation.getFullyQualifiedName() + "\" should have \"" 
                                + fileWithMemberOperation.getFullyQualifiedName() + "\" as parent parameter type");
            } catch (NullPointerException e) {
                assertNull(e, "Language registry does not contain required elements");
            }
        } catch (ExternalElementException e) {
            assertNull(e, "Exception thrown");
        }
    }
}
