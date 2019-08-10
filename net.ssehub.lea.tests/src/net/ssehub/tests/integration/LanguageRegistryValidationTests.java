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
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
 * This class contains tests for the validation mechanisms of the {@link LanguageRegistry}. These validations only
 * exist for {@link ChangeIdentifier}s and {@link Call}s as these are the only elements, which reference other elements
 * ({@link ParameterType}s) that must be available in the registry. The tests here do not follow the fundamental
 * principle of atomic unit tests as testing the validation requires individual steps, which are accompanied by their
 * assert statements. 
 * 
 * @author Christian Kroeher
 *
 */
public class LanguageRegistryValidationTests {
    
    /**
     * The {@link File} denoting the source plug-in of the {@link Class} from which a {@link LanguageElement} should be
     * created. This is just a dummy file as it has no impact on the actual creation, but is only used as a constructor
     * parameter.
     */
    private static final File SOURCE_PLUGIN = new File("./");

    /**
     * Tests the validation of a {@link ChangeIdentifier} during its addition.
     */
    @Test
    public void testCorrectChangeIdentifierValidation() {
        try {
            // Step 1: Create the new change identifier and add it to the registry
            ChangeIdentifier changeIdentifier = new ChangeIdentifier("MyCI", new String[] {"MyFileType"},
                    LanguageRegistryValidationTests.class, SOURCE_PLUGIN);
            List<LanguageElement> newElements = new ArrayList<LanguageElement>();
            newElements.add(changeIdentifier);
            List<LanguageElement> rejectedElements = LanguageRegistry.INSTANCE.addLanguageElements(newElements);
            /*
             * Check 1: "MyFileType" as "assignable element" of the new change identifier is not available in the
             *          registry at this point. Hence, the new change identifier should be cached (being currently
             *          invalid), which should result in:
             *              1. No rejected language element
             *              2. No availability of the new change identifier in the registry
             */
            assertEquals(0, rejectedElements.size(), "No rejected language elements expected");
            assertNull(LanguageRegistry.INSTANCE.getChangeIdentifier(changeIdentifier.getName()), 
                    "No change identifier with name \"" + changeIdentifier.getName() + "\" expected");
            
            // Step 2: Clear the lists for the next step
            newElements.clear();
            rejectedElements.clear();
            
            // Step 3: Create a new parameter type as the missing "MyFileType" element and add it to the registry
            ParameterType artifactParameterType = new ParameterType(ElementType.ARTIFACT_PARAMETER_TYPE, "MyFileType",
                    LanguageRegistryValidationTests.class, SOURCE_PLUGIN);
            newElements.add(artifactParameterType);
            rejectedElements = LanguageRegistry.INSTANCE.addLanguageElements(newElements);
            /*
             * Check 2: "MyFileType" is now available in the registry and its addition should trigger a re-validation of
             *          the cached new change identifier. Hence, the expected results are:
             *              1. No rejected language element ("MyFileType" is added successfully)
             *              2. Availability of the new change identifier in the registry
             */
            assertEquals(0, rejectedElements.size(), "No rejected language elements expected");
            assertTrue(LanguageRegistry.INSTANCE.getChangeIdentifier(
                    changeIdentifier.getName()).equals(changeIdentifier), "Equal change identifier expected");
        } catch (LanguageElementException e) {
            assertNull("Unexpected exception thrown", e);
        }
    }
    
    /**
     * Tests the validation of a {@link Call} during its addition.
     */
    @Test
    public void testCorrectCallValidation() {
        try {
            // Step 1: Create the new call and add it to the registry
            Call call = new Call(ElementType.OPERATION, "getFile", "MyFileType", new String[] {"MyString"},
                    LanguageRegistryValidationTests.class.getMethods()[0], LanguageRegistryValidationTests.class,
                    SOURCE_PLUGIN);
            List<LanguageElement> newElements = new ArrayList<LanguageElement>();
            newElements.add(call);
            List<LanguageElement> rejectedElements = LanguageRegistry.INSTANCE.addLanguageElements(newElements);
            /*
             * Check 1: "MyString" as "parameter" of the new call is not available in the registry at this point. Hence,
             *          the new call should be cached (being currently invalid), which should result in:
             *              1. No rejected language element
             *              2. No availability of the new call in the registry
             */
            assertEquals(0, rejectedElements.size(), "No rejected language elements expected");
            assertNull(LanguageRegistry.INSTANCE.getOperation(call.getName()), 
                    "No change identifier with name \"" + call.getName() + "\" expected");
            
            // Step 2: Clear the lists for the next step
            newElements.clear();
            rejectedElements.clear();
            
            // Step 3: Create a new parameter type as the missing "MyString" element and add it to the registry
            ParameterType fragmentParameterType = new ParameterType(ElementType.FRAGMENT_PARAMETER_TYPE, "MyString",
                    LanguageRegistryValidationTests.class, SOURCE_PLUGIN);
            newElements.add(fragmentParameterType);
            rejectedElements = LanguageRegistry.INSTANCE.addLanguageElements(newElements);
            /*
             * Check 2: "MyString" is now available in the registry and its addition should trigger a re-validation of
             *          the cached new call. Hence, the expected results are:
             *              1. No rejected language element ("MyString" is added successfully)
             *              2. Availability of the new call in the registry
             */
            assertEquals(0, rejectedElements.size(), "No rejected language elements expected");
            assertTrue(LanguageRegistry.INSTANCE.getOperation(call.getName()).equals(call),
                    "Equal call expected");
        } catch (LanguageElementException e) {
            assertNull("Unexpected exception thrown", e);
        }
    }
}
