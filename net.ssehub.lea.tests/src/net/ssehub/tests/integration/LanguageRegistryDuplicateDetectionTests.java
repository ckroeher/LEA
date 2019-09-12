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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

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
 * <br>
 * {@link ChangeIdentifier}: For this type of {@link LanguageElement} it is sufficient to check for complete equality.
 * <br><br>
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
            assertTrue("First parameter should be added", LanguageRegistry.INSTANCE.addParameterType(parameterType));
            assertFalse("Second parameter should be rejected", 
                    LanguageRegistry.INSTANCE.addParameterType(parameterType));
        } catch (LanguageElementException e) {
            assertNull("Unexpected exception thrown", e);
        }
    }
    
    /**
     * Test whether the {@link LanguageRegistry} rejects the addition of two equal {@link ChangeIdentifier}s. This test
     * uses the same {@link ChangeIdentifier} instance for the addition.
     */
    @Test
    public void testCorrectRejectionOfSameChangeIdentifierInstances() {
        try {
            ParameterType parameterType = new ParameterType(ElementType.ARTIFACT_PARAMETER_TYPE, "CIAE",
                    LanguageRegistryDuplicateDetectionTests.class, SOURCE_PLUGIN);
            
            ChangeIdentifier changeIdentifier = new ChangeIdentifier("CI",
                    LanguageRegistryDuplicateDetectionTests.class, SOURCE_PLUGIN);
            changeIdentifier.finalize(new ParameterType[] {parameterType});
            
            assertTrue("First change identifier should be added", 
                    LanguageRegistry.INSTANCE.addChangeIdentifier(changeIdentifier));
            assertFalse("Second change identifier should be rejected", 
                    LanguageRegistry.INSTANCE.addChangeIdentifier(changeIdentifier));
        } catch (LanguageElementException e) {
            assertNull("Unexpected exception thrown", e);
        }
    }
    
    /**
     * Test whether the {@link LanguageRegistry} rejects the addition of two equal {@link Call}s. This test uses the
     * same {@link Call} instance for the addition.
     */
    @Test
    public void testCorrectRejectionOfSameCallInstances() {
        try {
            // A parameter type required to not cache the first call due to unavailable return type
            ParameterType requiredReturnType = new ParameterType(ElementType.ARTIFACT_PARAMETER_TYPE, "CRT",
                    LanguageRegistryDuplicateDetectionTests.class, SOURCE_PLUGIN);
            // A parameter type required to not cache the first call due to unavailable parameter type
            ParameterType requiredParameterType = new ParameterType(ElementType.ARTIFACT_PARAMETER_TYPE, "CPT",
                    LanguageRegistryDuplicateDetectionTests.class, SOURCE_PLUGIN);
            
            Call call = new Call(ElementType.OPERATION, "someStupidCallName",
                    LanguageRegistryDuplicateDetectionTests.class.getMethods()[0],
                    LanguageRegistryDuplicateDetectionTests.class, SOURCE_PLUGIN);
            call.finalize(requiredReturnType, new ParameterType[] {requiredParameterType}, null);
            
            assertTrue("First call should be added", LanguageRegistry.INSTANCE.addCall(call));
            assertFalse("Second call should be rejected", LanguageRegistry.INSTANCE.addCall(call));
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
            ParameterType requiredReturnType = new ParameterType(ElementType.ARTIFACT_PARAMETER_TYPE, "CRT2",
                    LanguageRegistryDuplicateDetectionTests.class, SOURCE_PLUGIN);
            ParameterType requiredParameterType = new ParameterType(ElementType.ARTIFACT_PARAMETER_TYPE, "CPT2",
                    LanguageRegistryDuplicateDetectionTests.class, SOURCE_PLUGIN);
            
            Call operation = new Call(ElementType.OPERATION, "someStupidCallName", 
                    LanguageRegistryDuplicateDetectionTests.class.getMethods()[0],
                    LanguageRegistryDuplicateDetectionTests.class, SOURCE_PLUGIN);
            operation.finalize(requiredReturnType, new ParameterType[] {requiredParameterType}, null);
            Call extractorCall = new Call(ElementType.EXTRACTOR_CALL, "someStupidCallName",
                    LanguageRegistryDuplicateDetectionTests.class.getMethods()[0],
                    LanguageRegistryDuplicateDetectionTests.class, SOURCE_PLUGIN);
            extractorCall.finalize(requiredReturnType, new ParameterType[] {requiredParameterType}, null);
            
            assertTrue("First call should be added", LanguageRegistry.INSTANCE.addCall(operation));
            assertFalse("Second call should be rejected", LanguageRegistry.INSTANCE.addCall(extractorCall));
        } catch (LanguageElementException e) {
            assertNull("Unexpected exception thrown", e);
        }
    }
    
    /**
     * Test whether the {@link LanguageRegistry} rejects the addition of two equal {@link Call}s representing member
     * operations. This test uses the same {@link Call} instance for the addition.
     */
    @Test
    public void testCorrectRejectionOfSameMemberOperationInstances() {
        try {
            ParameterType requiredReturnType = new ParameterType(ElementType.ARTIFACT_PARAMETER_TYPE, "CRT3",
                    LanguageRegistryDuplicateDetectionTests.class, SOURCE_PLUGIN);
            ParameterType requiredParameterType = new ParameterType(ElementType.ARTIFACT_PARAMETER_TYPE, "CPT3",
                    LanguageRegistryDuplicateDetectionTests.class, SOURCE_PLUGIN);
            ParameterType requiredParentParameterType = new ParameterType(ElementType.ARTIFACT_PARAMETER_TYPE, "MOPPT",
                    LanguageRegistryDuplicateDetectionTests.class, SOURCE_PLUGIN);
            
            Call memberOperation = new Call(ElementType.OPERATION, "someStupidMemberOperationName",
                    LanguageRegistryDuplicateDetectionTests.class.getMethods()[0],
                    LanguageRegistryDuplicateDetectionTests.class, SOURCE_PLUGIN);
            memberOperation.finalize(requiredReturnType, new ParameterType[] {requiredParameterType},
                    requiredParentParameterType);
            
            assertTrue("First call should be added", LanguageRegistry.INSTANCE.addCall(memberOperation));
            assertFalse("Second call should be rejected", LanguageRegistry.INSTANCE.addCall(memberOperation));
        } catch (LanguageElementException e) {
            assertNull("Unexpected exception thrown", e);
        }
    }
    
    /**
     * Test whether the {@link LanguageRegistry} rejects the addition of two equal {@link Call}s representing member
     * operations. This test uses two distinct {@link Call} instances.
     */
    @Test
    public void testCorrectRejectionOfEqualMemberOperations() {
        try {
            ParameterType requiredReturnType = new ParameterType(ElementType.ARTIFACT_PARAMETER_TYPE, "CRT4",
                    LanguageRegistryDuplicateDetectionTests.class, SOURCE_PLUGIN);
            ParameterType requiredParameterType = new ParameterType(ElementType.ARTIFACT_PARAMETER_TYPE, "CPT4",
                    LanguageRegistryDuplicateDetectionTests.class, SOURCE_PLUGIN);
            ParameterType requiredParentParameterType = new ParameterType(ElementType.ARTIFACT_PARAMETER_TYPE, "MOPPT2",
                    LanguageRegistryDuplicateDetectionTests.class, SOURCE_PLUGIN);
            
            Call memberOperation1 = new Call(ElementType.OPERATION, "someStupidMemberOperationName",
                    LanguageRegistryDuplicateDetectionTests.class.getMethods()[0],
                    LanguageRegistryDuplicateDetectionTests.class, SOURCE_PLUGIN);
            memberOperation1.finalize(requiredReturnType, new ParameterType[] {requiredParameterType},
                    requiredParentParameterType);
            Call memberOperation2 = new Call(ElementType.OPERATION, "someStupidMemberOperationName",
                    LanguageRegistryDuplicateDetectionTests.class.getMethods()[0],
                    LanguageRegistryDuplicateDetectionTests.class, SOURCE_PLUGIN);
            memberOperation2.finalize(requiredReturnType, new ParameterType[] {requiredParameterType},
                    requiredParentParameterType);
            
            assertTrue("First call should be added", LanguageRegistry.INSTANCE.addCall(memberOperation1));
            assertFalse("Second call should be rejected", LanguageRegistry.INSTANCE.addCall(memberOperation2));
        } catch (LanguageElementException e) {
            assertNull("Unexpected exception thrown", e);
        }
    }
}
