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

import net.ssehub.integration.ChangeIdentifier;
import net.ssehub.integration.ElementType;
import net.ssehub.integration.ExternalElementException;
import net.ssehub.integration.LanguageElement;
import net.ssehub.integration.LanguageElementCreator;

/**
 * This class contains unit tests for the correct creation of {@link ChangeIdentifier}s by the
 * {@link LanguageElementCreator}.
 * 
 * @author Christian Kroeher
 *
 */
public class ChangeIdentifierCreationTests extends AbstractTest {

    /**
     * This class represents a change identifier. It is annotated with the
     * {@link net.ssehub.integration.annotations.ChangeIdentifier} annotation with an <i>empty</i> array of elements to
     * which this change identifier is assignable to. Hence, creating the language element must fail.
     * 
     * @author Christian Kroeher
     *
     */
    @net.ssehub.integration.annotations.ChangeIdentifier(assignableTo = { "" })
    private class ChangeIdentifierWithEmptyAssignableTo { }
    
    /**
     * This class represents a change identifier. It is annotated with the
     * {@link net.ssehub.integration.annotations.ChangeIdentifier} annotation with an array containing a single element
     * to which this change identifier is assignable to. Hence, creating the language element must succeed.
     * 
     * @author Christian Kroeher
     *
     */
    @net.ssehub.integration.annotations.ChangeIdentifier(assignableTo = { "File" })
    private class ChangeIdentifierWithSingleAssignableElement { }
    
    /**
     * This class represents a change identifier. It is annotated with the
     * {@link net.ssehub.integration.annotations.ChangeIdentifier} annotation with an array containing a multiple
     * elements to which this change identifier is assignable to. Hence, creating the language element must succeed.
     * 
     * @author Christian Kroeher
     *
     */
    @net.ssehub.integration.annotations.ChangeIdentifier(assignableTo = { "File", "Database", "Stream" })
    private class ChangeIdentifierWithMultipleAssignableElement { }
    
    /**
     * This class represents a change identifier. It is annotated with the
     * {@link net.ssehub.integration.annotations.ChangeIdentifier} annotation with an array containing a single element
     * to which this change identifier is assignable to. Further, the annotation defines a (symbolic) name. Hence,
     * creating the language element must succeed with defining its name being the (symbolic) name defined by the 
     * annotation instead of the class name.
     * 
     * @author Christian Kroeher
     *
     */
    @net.ssehub.integration.annotations.ChangeIdentifier(name = "ChangeIdentifier", assignableTo = { "File" })
    private class ChangeIdentifierWithSingleAssignableElementAndSymbolicName { }
    
    /**
     * Tests the correct throw of an {@link ExternalElementException} due to an empty string in the array of elements to
     * which the change identifier is assignable to. This test therefore uses the 
     * {@link ChangeIdentifierWithEmptyAssignableTo} class.
     */
    @Test
    public void testChangeIdentifierWithEmptyAssignableToCreation() {
        try {
            elementCreator.createLanguageElements(ChangeIdentifierWithEmptyAssignableTo.class, sourcePlugin);
            fail("No exception thrown");
        } catch (ExternalElementException e) {
            assertEquals(e.getClass(), ExternalElementException.class, "Wrong exception thrown");
        }
    }
    
    /**
     * Tests the correct creation of a {@link ChangeIdentifier} based on the class 
     * {@link ChangeIdentifierWithSingleAssignableElement}.
     */
    @Test
    public void testChangeIdentifierWithSingleAssignableElementCreation() {
        Class<?> testClass = ChangeIdentifierWithSingleAssignableElement.class;
        try {
            List<LanguageElement> createdElements = elementCreator.createLanguageElements(testClass, sourcePlugin);
            assertEquals(1, createdElements.size(), "Wrong number of created language elements");
            
            LanguageElement createdElement = createdElements.get(0);
            assertEquals(ChangeIdentifier.class, createdElement.getClass(), "Wrong language element type");
            assertEquals(ElementType.CHANGE_IDENTIFIER, createdElement.getElementType(),
                    "Wrong language element element type");
            assertEquals(testClass.getSimpleName(), createdElement.getName(), "Wrong language element name");
            assertEquals(testClass, createdElement.getSourceClass(), "Wrong language element source class");
            assertEquals(sourcePlugin, createdElement.getSourcePlugin(), "Wrong language element source plug-in");
            
            ChangeIdentifier changeIdentifier = (ChangeIdentifier) createdElement;
            String[] assignableElements = changeIdentifier.getAssignableElements();
            assertEquals(1, assignableElements.length, "Wrong number of assignable elements");
            assertEquals("File", assignableElements[0], "Wrong assignable element");
        } catch (ExternalElementException e) {
            assertNull(e, "This test should not throw any exception");
        }
    }
    
    /**
     * Tests the correct creation of a {@link ChangeIdentifier} based on the class 
     * {@link ChangeIdentifierWithMultipleAssignableElement}.
     */
    @Test
    public void testChangeIdentifierWithMultipleAssignableElementCreation() {
        Class<?> testClass = ChangeIdentifierWithMultipleAssignableElement.class;
        try {
            List<LanguageElement> createdElements = elementCreator.createLanguageElements(testClass, sourcePlugin);
            assertEquals(1, createdElements.size(), "Wrong number of created language elements");
            
            LanguageElement createdElement = createdElements.get(0);
            assertEquals(ChangeIdentifier.class, createdElement.getClass(), "Wrong language element type");
            assertEquals(ElementType.CHANGE_IDENTIFIER, createdElement.getElementType(),
                    "Wrong language element element type");
            assertEquals(testClass.getSimpleName(), createdElement.getName(), "Wrong language element name");
            assertEquals(testClass, createdElement.getSourceClass(), "Wrong language element source class");
            assertEquals(sourcePlugin, createdElement.getSourcePlugin(), "Wrong language element source plug-in");
            
            ChangeIdentifier changeIdentifier = (ChangeIdentifier) createdElement;
            String[] assignableElements = changeIdentifier.getAssignableElements();
            assertEquals(3, assignableElements.length, "Wrong number of assignable elements");
            assertEquals("File", assignableElements[0], "Wrong assignable element");
            assertEquals("Database", assignableElements[1], "Wrong assignable element");
            assertEquals("Stream", assignableElements[2], "Wrong assignable element");
        } catch (ExternalElementException e) {
            assertNull(e, "This test should not throw any exception");
        }
    }
    
    /**
     * Tests the correct creation of a {@link ChangeIdentifier} based on the class 
     * {@link ChangeIdentifierWithSingleAssignableElementAndSymbolicName}.
     */
    @Test
    public void testChangeIdentifierWithSingleAssignableElementAndSymbolicNameCreation() {
        Class<?> testClass = ChangeIdentifierWithSingleAssignableElementAndSymbolicName.class;
        try {
            List<LanguageElement> createdElements = elementCreator.createLanguageElements(testClass, sourcePlugin);
            assertEquals(1, createdElements.size(), "Wrong number of created language elements");
            
            LanguageElement createdElement = createdElements.get(0);
            assertEquals(ChangeIdentifier.class, createdElement.getClass(), "Wrong language element type");
            assertEquals(ElementType.CHANGE_IDENTIFIER, createdElement.getElementType(),
                    "Wrong language element element type");
            assertEquals("ChangeIdentifier", createdElement.getName(), "Wrong language element name");
            assertEquals(testClass, createdElement.getSourceClass(), "Wrong language element source class");
            assertEquals(sourcePlugin, createdElement.getSourcePlugin(), "Wrong language element source plug-in");
            
            ChangeIdentifier changeIdentifier = (ChangeIdentifier) createdElement;
            String[] assignableElements = changeIdentifier.getAssignableElements();
            assertEquals(1, assignableElements.length, "Wrong number of assignable elements");
            assertEquals("File", assignableElements[0], "Wrong assignable element");
        } catch (ExternalElementException e) {
            assertNull(e, "This test should not throw any exception");
        }
    }
}
