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

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import net.ssehub.integration.Call;
import net.ssehub.integration.ChangeIdentifier;
import net.ssehub.integration.ElementType;
import net.ssehub.integration.LanguageElement;
import net.ssehub.integration.LanguageElementException;
import net.ssehub.integration.LanguageRegistry;
import net.ssehub.integration.ParameterType;

/**
 * This class contains unit tests for the {@link LanguageRegistry}, which check the correct rejection of 
 * {@link LanguageElement}s during their addition to the registry, if an equal element already exists in that registry.
 * Tests for the correct acceptance of a single {@link LanguageElement} are defined in the
 * {@link BasicLanguageRegistryTests}.<br>
 * <br>
 * <b>Note:</b> For now, the {@link LanguageRegistry} accepts {@link ParameterType}s, which are only partially equal.
 * This enables the addition of the same type as, e.g., artifacts and fragment parameter type at the same time. While
 * this was not planned initially, there might be situations that require such definitions. Hence, the respective tests
 * that would check for rejections of such types are currently commented out.<br>
 * {@link ChangeIdentifier}: For this type of {@link LanguageElement} it is sufficient to check for complete equality.
 * {@link Call}: Here, the declaration of a partially equal call may lead to a rejection. That is, if all properties are
 * equal except for the {@link ElementType}, which would mean that the same source method is, e.g., an extractor and an
 * analysis call at the same time.
 * 
 * @author Christian Kroeher
 *
 */
public class LanguageRegistryDuplicateDetectionTests {

    /**
     * The {@link File} denoting the source plug-in of the {@link Class} from which a {@link LanguageElement} should be
     * created. This is just a dummy file as it has no impact on the actual creation, but is only used as a constructor
     * parameter.
     */
    private static final File SOURCE_PLUGIN = new File("./");
    
    /**
     * Test whether the {@link LanguageRegistry} rejects the addition of two equal {@link ParameterType}s. This test
     * uses the same {@link ParameterType} instance for the addition.
     */
    @Test
    public void testCorrectRejectionOfSameParameterTypeInstances() {
        try {
            ParameterType parameterType = new ParameterType(ElementType.ARTIFACT_PARAMETER_TYPE, "File",
                    LanguageRegistryDuplicateDetectionTests.class, SOURCE_PLUGIN);
            List<LanguageElement> newElements = new ArrayList<LanguageElement>();
            newElements.add(parameterType);
            newElements.add(parameterType);
            List<LanguageElement> rejectedElements = LanguageRegistry.INSTANCE.addLanguageElements(newElements);
            
            assertEquals(1, rejectedElements.size(), "Wrong number of rejected new language elements");
            assertEquals(newElements.get(1), rejectedElements.get(0), "Wrong rejected new language element");
        } catch (LanguageElementException e) {
            assertNull("Unexpected exception thrown", e);
        }
    }
    
//    /**
//     * Test whether the {@link LanguageRegistry} rejects the addition of two equal {@link ParameterType}s. This test
//     * uses two distinct {@link ParameterType} instances.
//     */
//    @Test
//    public void testCorrectRejectionOfEqualParameterTypes() {
//        try {
//            ParameterType artifactParameterType = new ParameterType(ElementType.ARTIFACT_PARAMETER_TYPE, "DB",
//                    LanguageRegistryDuplicateDetectionTests.class, SOURCE_PLUGIN);
//            ParameterType fragmentParameterType = new ParameterType(ElementType.FRAGMENT_PARAMETER_TYPE, "DB",
//                    LanguageRegistryDuplicateDetectionTests.class, SOURCE_PLUGIN);
//            List<LanguageElement> newElements = new ArrayList<LanguageElement>();
//            newElements.add(artifactParameterType);
//            newElements.add(fragmentParameterType);
//            List<LanguageElement> rejectedElements = LanguageRegistry.INSTANCE.addLanguageElements(newElements);
//            
//            assertEquals(1, rejectedElements.size(), "Wrong number of rejected new language elements");
//            assertEquals(newElements.get(1), rejectedElements.get(0), "Wrong rejected new language element");
//        } catch (LanguageElementException e) {
//            assertNull("Unexpected exception thrown", e);
//        }
//    }
    
    /**
     * Test whether the {@link LanguageRegistry} rejects the addition of two equal {@link ChangeIdentifier}s. This test
     * uses the same {@link ChangeIdentifier} instance for the addition.
     */
    @Test
    public void testCorrectRejectionOfSameChangeIdentifierInstances() {
        try {
            ChangeIdentifier changeIdentifier = new ChangeIdentifier("CI", new String[] {"File"},
                    LanguageRegistryDuplicateDetectionTests.class, SOURCE_PLUGIN);
            List<LanguageElement> newElements = new ArrayList<LanguageElement>();
            newElements.add(changeIdentifier);
            newElements.add(changeIdentifier);
            List<LanguageElement> rejectedElements = LanguageRegistry.INSTANCE.addLanguageElements(newElements);
            
            assertEquals(1, rejectedElements.size(), "Wrong number of rejected new language elements");
            assertEquals(newElements.get(1), rejectedElements.get(0), "Wrong rejected new language element");
        } catch (LanguageElementException e) {
            assertNull("Unexpected exception thrown", e);
        }
    }
    
//    /**
//     * Test whether the {@link LanguageRegistry} rejects the addition of two equal {@link ChangeIdentifier}s. This
//     * test uses two distinct {@link ChangeIdentifier} instances.
//     */
//    @Test
//    public void testCorrectRejectionOfEqualChangeIdentifiers() {
//        try {
//            ChangeIdentifier changeIdentifier1 = new ChangeIdentifier("ChId", new String[] {"File"},
//                    LanguageRegistryDuplicateDetectionTests.class, SOURCE_PLUGIN);
//            ChangeIdentifier changeIdentifier2 = new ChangeIdentifier("ChId2", new String[] {"File"},
//                    LanguageRegistryDuplicateDetectionTests.class, SOURCE_PLUGIN);
//            List<LanguageElement> newElements = new ArrayList<LanguageElement>();
//            newElements.add(changeIdentifier1);
//            newElements.add(changeIdentifier2);
//            List<LanguageElement> rejectedElements = LanguageRegistry.INSTANCE.addLanguageElements(newElements);
//            
//            assertEquals(1, rejectedElements.size(), "Wrong number of rejected new language elements");
//            assertEquals(newElements.get(1), rejectedElements.get(0), "Wrong rejected new language element");
//        } catch (LanguageElementException e) {
//            assertNull("Unexpected exception thrown", e);
//        }
//    }
    
    /**
     * Test whether the {@link LanguageRegistry} rejects the addition of two equal {@link Call}s. This test uses the
     * same {@link Call} instance for the addition.
     */
    @Test
    public void testCorrectRejectionOfSameCallInstances() {
        try {
            Call call = new Call(ElementType.OPERATION, "file", "File", new String[] {"path"}, 
                    LanguageRegistryDuplicateDetectionTests.class.getMethods()[0],
                    LanguageRegistryDuplicateDetectionTests.class, SOURCE_PLUGIN);
            List<LanguageElement> newElements = new ArrayList<LanguageElement>();
            newElements.add(call);
            newElements.add(call);
            List<LanguageElement> rejectedElements = LanguageRegistry.INSTANCE.addLanguageElements(newElements);
            
            assertEquals(1, rejectedElements.size(), "Wrong number of rejected new language elements");
            assertEquals(newElements.get(1), rejectedElements.get(0), "Wrong rejected new language element");
        } catch (LanguageElementException e) {
            assertNull("Unexpected exception thrown", e);
        }
    }
    
    /**
     * Test whether the {@link LanguageRegistry} rejects the addition of two equal {@link Call}s. This test uses two
     * distinct {@link Call} instances.
     */
    @Test
    public void testCorrectRejectionOfEqualCalls() {
        try {
            Call operation = new Call(ElementType.OPERATION, "files", "File[]", new String[] {"abspath"}, 
                    LanguageRegistryDuplicateDetectionTests.class.getMethods()[0],
                    LanguageRegistryDuplicateDetectionTests.class, SOURCE_PLUGIN);
            Call extractorCall = new Call(ElementType.EXTRACTOR_CALL, "files", "File[]", new String[] {"abspath"}, 
                    LanguageRegistryDuplicateDetectionTests.class.getMethods()[0],
                    LanguageRegistryDuplicateDetectionTests.class, SOURCE_PLUGIN);
            List<LanguageElement> newElements = new ArrayList<LanguageElement>();
            newElements.add(operation);
            newElements.add(extractorCall);
            List<LanguageElement> rejectedElements = LanguageRegistry.INSTANCE.addLanguageElements(newElements);
            
            assertEquals(1, rejectedElements.size(), "Wrong number of rejected new language elements");
            assertEquals(newElements.get(1), rejectedElements.get(0), "Wrong rejected new language element");
        } catch (LanguageElementException e) {
            assertNull("Unexpected exception thrown", e);
        }
    }
}
