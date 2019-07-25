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
package net.ssehub.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.eclipse.emf.common.util.EList;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.inject.Inject;

import net.ssehub.lea.AnalysisDefinition;
import net.ssehub.lea.ElementDeclaration;

/**
 * This abstract class contains basic tests for parsing {@link ElementDeclaration}s of the generic type <i>Artifact</i>.
 * 
 * @author Christian Kroeher
 *
 */
@RunWith(XtextRunner.class)
@InjectWith(LeaInjectorProvider.class)
public class BasicArtifactParsingTests extends AbstractTest {
    
    /**
     * This {@link ParseHelper} enables parsing of {@link AnalysisDefinition}s passed as a single string.
     */
    @Inject
    private ParseHelper<AnalysisDefinition> parseHelper;

//CHECKSTYLE:OFF

    /**
     * Tests the correct parsing of an empty file (model).
     * 
     * @throws Exception if the test data file could not be retrieved or the parser failed
     */
    @Test
    public void testEmptyFile() throws Exception {
        String model = getModelString(TestType.BASIC, "EmptyFile");
        AnalysisDefinition analysis = parseHelper.parse(model);
        
        assertSyntacticalCorrectness(analysis);
        
        assertEquals(0, analysis.getElementDeclarations().size(),
                "An empty model should not contain element declarations");
        assertEquals(0, analysis.getChangeIdentifierAssignments().size(),
                "An empty model should not contain change identifier assignments");
    }

    /**
     * Tests the correct parsing of a single artifact declaration without any initialization.
     * 
     * @throws Exception if the test data file could not be retrieved or the parser failed
     */
    @Test
    public void testArtifactDeclaration() throws Exception {
        String model = getModelString(TestType.BASIC, "ArtifactDeclaration");
        AnalysisDefinition analysis = parseHelper.parse(model);
        
        assertSyntacticalCorrectness(analysis);
        
        EList<ElementDeclaration> elementDeclarations = analysis.getElementDeclarations();
        assertEquals(1, elementDeclarations.size(), "There should only be one element declaration in the test file");
        String problemDescription = testCorrectElementDeclaration(elementDeclarations.get(0), "Artifact", false, false,
                false);
        assertNull(problemDescription, problemDescription);
    }
    
    /**
     * Tests the correct parsing of a single artifact declaration with an initialization using another element.
     * 
     * @throws Exception if the test data file could not be retrieved or the parser failed
     */
    @Test
    public void testArtifactDeclarationWithElement() throws Exception {
        String model = getModelString(TestType.BASIC, "ArtifactDeclarationWithElement");
        AnalysisDefinition analysis = parseHelper.parse(model);
        
        assertSyntacticalCorrectness(analysis);
        
        EList<ElementDeclaration> elementDeclarations = analysis.getElementDeclarations();
        assertEquals(1, elementDeclarations.size(), "There should only be one element declaration in the test file");
        String problemDescription = testCorrectElementDeclaration(elementDeclarations.get(0), "Artifact", false, false,
                true);
        assertNull(problemDescription, problemDescription);
    }
    
    /**
     * Tests the correct parsing of a single artifact declaration with an initialization using an operation.
     * 
     * @throws Exception if the test data file could not be retrieved or the parser failed
     */
    @Test
    public void testArtifactDeclarationWithOperation() throws Exception {
        String model = getModelString(TestType.BASIC, "ArtifactDeclarationWithOperation");
        AnalysisDefinition analysis = parseHelper.parse(model);
        
        assertSyntacticalCorrectness(analysis);
        
        EList<ElementDeclaration> elementDeclarations = analysis.getElementDeclarations();
        assertEquals(1, elementDeclarations.size(), "There should only be one element declaration in the test file");
        String problemDescription = testCorrectElementDeclaration(elementDeclarations.get(0), "Artifact", false, true,
                false);
        assertNull(problemDescription, problemDescription);
    }
    
    /**
     * Tests the correct parsing of a single artifact set declaration without any initialization.
     * 
     * @throws Exception if the test data file could not be retrieved or the parser failed
     */
    @Test
    public void testArtifactSetDeclaration() throws Exception {
        String model = getModelString(TestType.BASIC, "ArtifactSetDeclaration");
        AnalysisDefinition analysis = parseHelper.parse(model);
        
        assertSyntacticalCorrectness(analysis);
        
        EList<ElementDeclaration> elementDeclarations = analysis.getElementDeclarations();
        assertEquals(1, elementDeclarations.size(), "There should only be one element declaration in the test file");
        String problemDescription = testCorrectElementDeclaration(elementDeclarations.get(0), "Artifact", true, false,
                false);
        assertNull(problemDescription, problemDescription);
    }
    
    /**
     * Tests the correct parsing of a single artifact set declaration with an initialization using another element.
     * 
     * @throws Exception if the test data file could not be retrieved or the parser failed
     */
    @Test
    public void testArtifactSetDeclarationWithElement() throws Exception {
        String model = getModelString(TestType.BASIC, "ArtifactSetDeclarationWithElement");
        AnalysisDefinition analysis = parseHelper.parse(model);
        
        assertSyntacticalCorrectness(analysis);
        
        EList<ElementDeclaration> elementDeclarations = analysis.getElementDeclarations();
        assertEquals(1, elementDeclarations.size(), "There should only be one element declaration in the test file");
        String problemDescription = testCorrectElementDeclaration(elementDeclarations.get(0), "Artifact", true, false,
                true);
        assertNull(problemDescription, problemDescription);
    }
    
    /**
     * Tests the correct parsing of a single artifact set declaration with an initialization using an operation.
     * 
     * @throws Exception if the test data file could not be retrieved or the parser failed
     */
    @Test
    public void testArtifactSetDeclarationWithOperation() throws Exception {
        String model = getModelString(TestType.BASIC, "ArtifactSetDeclarationWithOperation");
        AnalysisDefinition analysis = parseHelper.parse(model);
        
        assertSyntacticalCorrectness(analysis);
        
        EList<ElementDeclaration> elementDeclarations = analysis.getElementDeclarations();
        assertEquals(1, elementDeclarations.size(), "There should only be one element declaration in the test file");
        String problemDescription = testCorrectElementDeclaration(elementDeclarations.get(0), "Artifact", true, true,
                false);
        assertNull(problemDescription, problemDescription);
    }
    
//CHECKSTYLE:ON
    
}