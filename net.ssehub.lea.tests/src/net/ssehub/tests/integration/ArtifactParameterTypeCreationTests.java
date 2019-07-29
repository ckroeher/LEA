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
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.Test;

import net.ssehub.integration.ElementType;
import net.ssehub.integration.ExternalElementException;
import net.ssehub.integration.LanguageElement;
import net.ssehub.integration.LanguageElementCreator;
import net.ssehub.integration.ParameterType;
import net.ssehub.integration.annotations.ArtifactParameterType;

/**
 * This class contains unit tests for the correct creation of {@link ParameterType}s of the type 
 * {@link ElementType#ARTIFACT_PARAMETER_TYPE} by the {@link LanguageElementCreator}.
 * 
 * @author Christian Kroeher
 *
 */
public class ArtifactParameterTypeCreationTests extends AbstractTest {

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
     * This class represents a simple (not generic) artifact parameter type. It is annotated with the 
     * {@link ArtifactParameterType} annotation including the definition of the symbolic name <i>File</i>.
     * .
     * @author Christian Kroeher
     *
     */
    @ArtifactParameterType(name = "File")
    private class SimpleArtifactParameterTypeWithSymbolicName { }
    
    /**
     * This class represents a simple (not generic) artifact parameter type. It is annotated with the 
     * {@link ArtifactParameterType} annotation including the definition of the symbolic parameter name <i>None</i>,
     * which should be ignored as this artifact is not a generic one.
     * .
     * @author Christian Kroeher
     *
     */
    @ArtifactParameterType(parameterName = "None")
    private class SimpleArtifactParameterTypeWithSymbolicParameterName { }
    
    /**
     * This class represents a simple (not generic) artifact parameter type. It is annotated with the 
     * {@link ArtifactParameterType} annotation including the definition of the the symbolic name <i>File</i> and the 
     * symbolic parameter name <i>None</i>. The latter should be ignored as this artifact is not a generic one.
     * .
     * @author Christian Kroeher
     *
     */
    @ArtifactParameterType(name = "File", parameterName = "None")
    private class SimpleArtifactParameterTypeWithSymbolicNameAndParameterName { }
    
    // TODO same combination of annotation variants with generic class.

    /**
     * Tests the correct creation of a simple {@link ArtifactParameterType} based on the class 
     * {@link SimpleArtifactParameterType}.
     */
    @Test
    public void testSimpleArtifactParameterTypeCreation() {
        try {
            List<LanguageElement> createdElements = 
                    elementCreator.createLanguageElements(SimpleArtifactParameterType.class, sourcePlugin);
            assertEquals(1, createdElements.size(), "Wrong number of created language elements");
            
            LanguageElement createdElement = createdElements.get(0);
            assertEquals(ParameterType.class, createdElement.getClass(), "Wrong language element type");
            assertEquals(ElementType.ARTIFACT_PARAMETER_TYPE, createdElement.getElementType(),
                    "Wrong language element element type");
            assertEquals(SimpleArtifactParameterType.class.getSimpleName(), createdElement.getName(),
                    "Wrong language element name");
            assertEquals(SimpleArtifactParameterType.class, createdElement.getSourceClass(),
                    "Wrong language element source class");
            assertEquals(sourcePlugin, createdElement.getSourcePlugin(), "Wrong language element source plug-in");
            
        } catch (ExternalElementException e) {
            assertNull(e, "This test should not throw any exception");
        }
    }
    
    /**
     * Tests the correct creation of a simple {@link ArtifactParameterType} based on the class 
     * {@link SimpleArtifactParameterTypeWithSymbolicName}.
     */
    @Test
    public void testSimpleArtifactParameterTypeWithSymbolicNameCreation() {
        try {
            List<LanguageElement> createdElements = 
                    elementCreator.createLanguageElements(SimpleArtifactParameterTypeWithSymbolicName.class,
                            sourcePlugin);
            assertEquals(1, createdElements.size(), "Wrong number of created language elements");
            
            LanguageElement createdElement = createdElements.get(0);
            assertEquals(ParameterType.class, createdElement.getClass(), "Wrong language element type");
            assertEquals(ElementType.ARTIFACT_PARAMETER_TYPE, createdElement.getElementType(),
                    "Wrong language element element type");
            assertEquals("File", createdElement.getName(), "Wrong language element name");
            assertEquals(SimpleArtifactParameterTypeWithSymbolicName.class, createdElement.getSourceClass(),
                    "Wrong language element source class");
            assertEquals(sourcePlugin, createdElement.getSourcePlugin(), "Wrong language element source plug-in");
            
        } catch (ExternalElementException e) {
            assertNull(e, "This test should not throw any exception");
        }
    }
    
    /**
     * Tests the correct creation of a simple {@link ArtifactParameterType} based on the class 
     * {@link SimpleArtifactParameterTypeWithSymbolicParameterName}.
     */
    @Test
    public void testSimpleArtifactParameterTypeWithSymbolicParameterNameCreation() {
        try {
            List<LanguageElement> createdElements = 
                    elementCreator.createLanguageElements(SimpleArtifactParameterTypeWithSymbolicParameterName.class,
                            sourcePlugin);
            assertEquals(1, createdElements.size(), "Wrong number of created language elements");
            
            LanguageElement createdElement = createdElements.get(0);
            assertEquals(ParameterType.class, createdElement.getClass(), "Wrong language element type");
            assertEquals(ElementType.ARTIFACT_PARAMETER_TYPE, createdElement.getElementType(),
                    "Wrong language element element type");
            assertEquals(SimpleArtifactParameterTypeWithSymbolicParameterName.class.getSimpleName(),
                    createdElement.getName(), "Wrong language element name");
            assertEquals(SimpleArtifactParameterTypeWithSymbolicParameterName.class, createdElement.getSourceClass(),
                    "Wrong language element source class");
            assertEquals(sourcePlugin, createdElement.getSourcePlugin(), "Wrong language element source plug-in");
            
        } catch (ExternalElementException e) {
            assertNull(e, "This test should not throw any exception");
        }
    }
    
    /**
     * Tests the correct creation of a simple {@link ArtifactParameterType} based on the class 
     * {@link SimpleArtifactParameterTypeWithSymbolicNameAndParameterName}.
     */
    @Test
    public void testSimpleArtifactParameterTypeWithSymbolicNameAndParameterNameCreation() {
        try {
            List<LanguageElement> createdElements = elementCreator.
                    createLanguageElements(SimpleArtifactParameterTypeWithSymbolicNameAndParameterName.class,
                            sourcePlugin);
            assertEquals(1, createdElements.size(), "Wrong number of created language elements");
            
            LanguageElement createdElement = createdElements.get(0);
            assertEquals(ParameterType.class, createdElement.getClass(), "Wrong language element type");
            assertEquals(ElementType.ARTIFACT_PARAMETER_TYPE, createdElement.getElementType(),
                    "Wrong language element element type");
            assertEquals("File", createdElement.getName(), "Wrong language element name");
            assertEquals(SimpleArtifactParameterTypeWithSymbolicNameAndParameterName.class,
                    createdElement.getSourceClass(), "Wrong language element source class");
            assertEquals(sourcePlugin, createdElement.getSourcePlugin(), "Wrong language element source plug-in");
            
        } catch (ExternalElementException e) {
            assertNull(e, "This test should not throw any exception");
        }
    }
}
