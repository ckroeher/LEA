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
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import net.ssehub.integration.Call;
import net.ssehub.integration.ElementType;
import net.ssehub.integration.LanguageElement;
import net.ssehub.integration.LanguageElementException;
import net.ssehub.integration.LanguageElementProvider;
import net.ssehub.integration.LanguageRegistry;
import net.ssehub.integration.ParameterType;
import net.ssehub.integration.ParameterTypeInstance;

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
     * The number of core {@link LanguageElement}s, which are always available in the {@link LanguageRegistry}.
     */
    private static final int NUMBER_OF_CORE_LANGUAGE_ELEMENTS = 91;

    /**
     * The {@link LanguageElementProvider} for testing the correct provision of {@link LanguageElement}s.
     */
    private LanguageElementProvider elementProvider;
    
    /**
     * Creates a new {@link LanguageElementProvider} instance for each of the unit tests in this class to avoid side-
     * effects, which cannot occur in production as the element provider is called exactly once to create all elements.
     * Further, it clears the {@link LanguageRegistry} to avoid duplicated elements or false-positive detections of
     * expected elements.
     */
    @Before
    public void prepare() {
        LanguageRegistry.INSTANCE.clear();
        elementProvider = new LanguageElementProvider();
    }
    
    /**
     * Tests the correct throw of an {@link LanguageElementException}, if the plug-in directory ({@link File}), in which
     * external plug-ins should be found, is <code>null</code>.
     */
    @Test
    public void testNullAsPluginDirectory() {
        try {
            elementProvider.provideLanguageElements(null);
            fail("No exception thrown");
        } catch (LanguageElementException e) {
            assertEquals(e.getClass(), LanguageElementException.class, "Wrong exception thrown");
        }
    }
    
    /**
     * Tests the correct acceptance of a plug-in directory ({@link File}), in which external plug-ins should be found,
     * that does not exist (in the file system) without any throw of an {@link LanguageElementException}.
     */
    @Test
    public void testNonExistingPluginDirectory() {
        File pluginDirectory = new File("nonexistingdir");
        List<String> searchPaths = new ArrayList<String>();
        searchPaths.add(pluginDirectory.getAbsolutePath());
        try {
            elementProvider.provideLanguageElements(searchPaths);
        } catch (LanguageElementException e) {
            fail("Exception thrown");
        }
    }
    
    /**
     * Tests the correct acceptance of a plug-in directory ({@link File}), in which external plug-ins should be found,
     * that denotes a file (in the file system) without any throw of an {@link LanguageElementException}.
     */
    @Test
    public void testFileAsPluginDirectory() {
        File pluginDirectory = new File(TESTDATA_DIRECTORY, "/emptyPlugin/EmptyPlugin.jar");
        List<String> searchPaths = new ArrayList<String>();
        searchPaths.add(pluginDirectory.getAbsolutePath());
        try {
            elementProvider.provideLanguageElements(searchPaths);
        } catch (LanguageElementException e) {
            fail("Exception thrown");
        }
    }
    
    /**
     * Tests the correct scan of an empty directory as the plug-in directory ({@link File}), in which
     * external plug-ins should be found. This should neither produce errors nor any {@link LanguageElement}s in the 
     * {@link LanguageRegistry}.
     */
    @Test
    public void testEmptyDirectory() {
        File pluginDirectory = new File(TESTDATA_DIRECTORY, "emptyDirectory");
        List<String> searchPaths = new ArrayList<String>();
        searchPaths.add(pluginDirectory.getAbsolutePath());
        try {
            elementProvider.provideLanguageElements(searchPaths);
            assertEquals(NUMBER_OF_CORE_LANGUAGE_ELEMENTS, LanguageRegistry.INSTANCE.size(), 
                    "An empty plug-in directory should not introduce new language elements");
        } catch (LanguageElementException e) {
            fail("Exception thrown");
        }
    }
    
    /**
     * Tests the correct scan of an empty plug-in. This should neither produce errors nor any {@link LanguageElement}s
     * in the {@link LanguageRegistry}.
     */
    @Test
    public void testEmptyPlugin() {
        File pluginDirectory = new File(TESTDATA_DIRECTORY, "emptyPlugin");
        List<String> searchPaths = new ArrayList<String>();
        searchPaths.add(pluginDirectory.getAbsolutePath());
        try {
            elementProvider.provideLanguageElements(searchPaths);
            assertEquals(NUMBER_OF_CORE_LANGUAGE_ELEMENTS, LanguageRegistry.INSTANCE.size(), 
                    "An empty plug-in should not introduce new language elements");
        } catch (LanguageElementException e) {
            fail("Exception thrown");
        }
    }
    
    /**
     * Tests the correct scan of a plug-in, which contains no Java classes, but files of other types. This should
     * neither produce errors nor any {@link LanguageElement}s in the {@link LanguageRegistry}.
     */
    @Test
    public void testPluginWithoutJavaClasses() {
        File pluginDirectory = new File(TESTDATA_DIRECTORY, "pluginWithoutJavaClasses");
        List<String> searchPaths = new ArrayList<String>();
        searchPaths.add(pluginDirectory.getAbsolutePath());
        try {
            elementProvider.provideLanguageElements(searchPaths);
            assertEquals(NUMBER_OF_CORE_LANGUAGE_ELEMENTS, LanguageRegistry.INSTANCE.size(), 
                    "A plug-in without Java classes should not introduce new language elements");
        } catch (LanguageElementException e) {
            fail("Exception thrown");
        }
    }
    
    /**
     * Tests the correct scan of a plug-in, which contains Java classes, but they do not introduce new elements. This
     * should neither produce errors nor any {@link LanguageElement}s in the {@link LanguageRegistry}.
     */
    @Test
    public void testPluginWithoutNewElements() {
        File pluginDirectory = new File(TESTDATA_DIRECTORY, "pluginWithoutNewElements");
        List<String> searchPaths = new ArrayList<String>();
        searchPaths.add(pluginDirectory.getAbsolutePath());
        try {
            elementProvider.provideLanguageElements(searchPaths);
            assertEquals(NUMBER_OF_CORE_LANGUAGE_ELEMENTS, LanguageRegistry.INSTANCE.size(), 
                    "A plug-in with classes not introducing new elements should not introduce new language elements");
        } catch (LanguageElementException e) {
            fail("Exception thrown");
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
        int expectedLanguageElementSize = NUMBER_OF_CORE_LANGUAGE_ELEMENTS + 1;
        String expectedRegisteredElementName = "NewElement";
        try {
            elementProvider.provideLanguageElements(searchPaths);
            assertEquals(expectedLanguageElementSize, LanguageRegistry.INSTANCE.size(), 
                    "A plug-in with classes introducing a new element should introduce a new language elements");
            // Check if it is the expected element
            List<ParameterType> registerdArtifactParameterTypes = 
                    LanguageRegistry.INSTANCE.getParameterTypes(ElementType.ARTIFACT_PARAMETER_TYPE,
                            expectedRegisteredElementName);
            try {
                assertEquals(expectedRegisteredElementName, registerdArtifactParameterTypes.get(0).getName(),
                        "Wrong language element");
            } catch (NullPointerException e) {
                fail("Language registry does not contain element with name \"" + expectedRegisteredElementName + "\"");
            }
        } catch (LanguageElementException e) {
            fail("Exception thrown");
        }
    }
    
    /**
     * Tests the correct scan of a plug-in, which contains Java classes that introduce new member operations. This 
     * should yield corresponding {@link LanguageElement}s in the {@link LanguageRegistry}.
     */
// CHECKSTYLE:OFF
    @Test
    public void testPluginWithNewMemberOperations() {
        File pluginDirectory = new File(TESTDATA_DIRECTORY, "pluginWithNewMemberOperations");
        List<String> searchPaths = new ArrayList<String>();
        searchPaths.add(pluginDirectory.getAbsolutePath());
        int expectedLanguageElementSize = NUMBER_OF_CORE_LANGUAGE_ELEMENTS + 6;
        try {
            elementProvider.provideLanguageElements(searchPaths);
            assertEquals(expectedLanguageElementSize, LanguageRegistry.INSTANCE.size(), 
                    "A plug-in with classes introducing 6 new elements should introduce 6 new language elements");
            /*
             * There should be 3 member operations:
             *    1. getAbsolutePath() for artifact parameter type NewFile
             *    2. getAbsolutePath() for artifact parameter type FileWithMemberOperation
             *    3. getPath() for artifact parameter type FileWithMemberOperation
             */
            try {
                ParameterType newFile = LanguageRegistry.INSTANCE.getParameterType(ElementType.ARTIFACT_PARAMETER_TYPE,
                        "NewFile");
                ParameterTypeInstance newFileInstance = new ParameterTypeInstance(newFile, false);
                // 1. getAbsolutePath() for artifact parameter type NewFile
                Call getAbsolutePathForNewFile = getMemberOperation(newFileInstance, "getAbsolutePath");
                assertTrue(getAbsolutePathForNewFile.isMemberOperation(), "Call \"" 
                        + getAbsolutePathForNewFile.getFullyQualifiedName() + "\" should be a member operation"); 
                assertTrue(getAbsolutePathForNewFile.isMemberOperationOf(newFileInstance), "Call \"" 
                        + getAbsolutePathForNewFile.getFullyQualifiedName() + "\" should be a member operation of \"" 
                        + newFile.getFullyQualifiedName() + "\"");
                assertEquals(newFile.getName(),
                        getAbsolutePathForNewFile.getParentParameterType().getParameterType().getName(), "Call \"" 
                        + getAbsolutePathForNewFile.getFullyQualifiedName() + "\" should have \"" 
                        + newFile.getFullyQualifiedName() + "\" as parent parameter type");
                
                ParameterType fileWithMemberOperation = 
                        LanguageRegistry.INSTANCE.getParameterType(ElementType.ARTIFACT_PARAMETER_TYPE,
                                "FileWithMemberOperation");
                ParameterTypeInstance fileWithMemberOperationInstance = 
                        new ParameterTypeInstance(fileWithMemberOperation, false);
                // 2. getAbsolutePath() for artifact parameter type FileWithMemberOperation
                Call getAbsolutePathForFileWithMemberOperation = getMemberOperation(fileWithMemberOperationInstance,
                        "getAbsolutePath");
                assertTrue(getAbsolutePathForFileWithMemberOperation.isMemberOperation(), "Call \"" 
                        + getAbsolutePathForFileWithMemberOperation.getFullyQualifiedName() 
                        + "\" should be a member operation"); 
                assertTrue(getAbsolutePathForFileWithMemberOperation
                        .isMemberOperationOf(fileWithMemberOperationInstance), "Call \"" 
                        + getAbsolutePathForFileWithMemberOperation.getFullyQualifiedName() 
                        + "\" should be a member operation of \"" + fileWithMemberOperation.getFullyQualifiedName() 
                        + "\"");
                assertEquals(fileWithMemberOperation.getName(), 
                        getAbsolutePathForFileWithMemberOperation.getParentParameterType().getParameterType().getName(),
                            "Call \"" + getAbsolutePathForFileWithMemberOperation.getFullyQualifiedName() 
                                + "\" should have \"" + fileWithMemberOperation.getFullyQualifiedName() 
                                + "\" as parent parameter type");
                // 3. getPath() for artifact parameter type FileWithMemberOperation
                Call getPathForFileWithMemberOperation = getMemberOperation(fileWithMemberOperationInstance, "getPath");
                assertTrue(getPathForFileWithMemberOperation.isMemberOperation(), "Call \"" 
                        + getPathForFileWithMemberOperation.getFullyQualifiedName() 
                        + "\" should be a member operation"); 
                assertTrue(getPathForFileWithMemberOperation.isMemberOperationOf(fileWithMemberOperationInstance), 
                        "Call \"" + getPathForFileWithMemberOperation.getFullyQualifiedName() 
                        + "\" should be a member operation of \"" + fileWithMemberOperation.getFullyQualifiedName() 
                        + "\"");
                assertEquals(fileWithMemberOperation.getName(), 
                        getPathForFileWithMemberOperation.getParentParameterType().getParameterType().getName(),
                            "Call \"" + getPathForFileWithMemberOperation.getFullyQualifiedName() + "\" should have \"" 
                                + fileWithMemberOperation.getFullyQualifiedName() + "\" as parent parameter type");
            } catch (NullPointerException e) {
                fail("Language registry does not contain required elements");
            }
        } catch (LanguageElementException e) {
            fail("Exception thrown");
        }
    }
// CHECKSTYLE:ON
    
    /**
     * Return the {@link Call} with the given name, which is a member operation of the given
     * {@link ParameterTypeInstance}.
     * 
     * @param target the {@link ParameterTypeInstance} for which the member operation with the given name should be
     *        returned
     * @param name the {@link String} denoting the name of the member operation, which should be returned
     * @return the {@link Call} with the given name, which is a member operation of the given
     *         {@link ParameterTypeInstance}, or <code>null</code>, if no such {@link Call} is available
     */
    private Call getMemberOperation(ParameterTypeInstance target, String name) {
        Call memberOperation = null;
        List<Call> potentialOperations = LanguageRegistry.INSTANCE.getCalls(ElementType.OPERATION, name);
        int potentialOperationsCounter = 0;
        while (memberOperation == null && potentialOperationsCounter < potentialOperations.size()) {
            if (potentialOperations.get(potentialOperationsCounter).isMemberOperationOf(target)) {
                memberOperation = potentialOperations.get(potentialOperationsCounter);
            }
            potentialOperationsCounter++;
        }
        return memberOperation;
    }
}
