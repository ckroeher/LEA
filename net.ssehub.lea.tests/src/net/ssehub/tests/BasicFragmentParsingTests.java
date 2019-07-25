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
 * This abstract class contains basic tests for parsing {@link ElementDeclaration}s of the parameter type
 * <i>Fragment</i>.
 * 
 * @author Christian Kroeher
 *
 */
@RunWith(XtextRunner.class)
@InjectWith(LeaInjectorProvider.class)
public class BasicFragmentParsingTests extends AbstractTest {
    
    /**
     * This {@link ParseHelper} enables parsing of {@link AnalysisDefinition}s passed as a single string.
     */
    @Inject
    private ParseHelper<AnalysisDefinition> parseHelper;
//CHECKSTYLE:OFF
    /**
     * Tests the correct parsing of a single fragment declaration without any initialization.
     * 
     * @throws Exception if the test data file could not be retrieved or the parser failed
     */
    @Test
    public void testFragmentDeclaration() throws Exception {
        String model = getModelString(TestType.BASIC, "FragmentDeclaration");
        AnalysisDefinition analysis = parseHelper.parse(model);
        
        assertSyntacticalCorrectness(analysis);
        
        EList<ElementDeclaration> elementDeclarations = analysis.getElementDeclarations();
        assertEquals(1, elementDeclarations.size(), "There should only be one element declaration in the test file");
        String problemDescription = testCorrectElementDeclaration(elementDeclarations.get(0), "Fragment", false, false,
                false, false);
        assertNull(problemDescription, problemDescription);
    }
    
    /**
     * Tests the correct parsing of a single fragment declaration with an initialization using another element.
     * 
     * @throws Exception if the test data file could not be retrieved or the parser failed
     */
    @Test
    public void testFragmentDeclarationWithElement() throws Exception {
        String model = getModelString(TestType.BASIC, "FragmentDeclarationWithElement");
        AnalysisDefinition analysis = parseHelper.parse(model);
        
        assertSyntacticalCorrectness(analysis);
        
        EList<ElementDeclaration> elementDeclarations = analysis.getElementDeclarations();
        assertEquals(1, elementDeclarations.size(), "There should only be one element declaration in the test file");
        String problemDescription = testCorrectElementDeclaration(elementDeclarations.get(0), "Fragment", false, false,
                false, true);
        assertNull(problemDescription, problemDescription);
    }
    
    /**
     * Tests the correct parsing of a single fragment declaration with an initialization using an operation.
     * 
     * @throws Exception if the test data file could not be retrieved or the parser failed
     */
    @Test
    public void testFragmentDeclarationWithOperation() throws Exception {
        String model = getModelString(TestType.BASIC, "FragmentDeclarationWithOperation");
        AnalysisDefinition analysis = parseHelper.parse(model);
        
        assertSyntacticalCorrectness(analysis);
        
        EList<ElementDeclaration> elementDeclarations = analysis.getElementDeclarations();
        assertEquals(1, elementDeclarations.size(), "There should only be one element declaration in the test file");
        String problemDescription = testCorrectElementDeclaration(elementDeclarations.get(0), "Fragment", false, false,
                true, false);
        assertNull(problemDescription, problemDescription);
    }
    
    /**
     * Tests the correct parsing of a single fragment set declaration without any initialization.
     * 
     * @throws Exception if the test data file could not be retrieved or the parser failed
     */
    @Test
    public void testFragmentSetDeclaration() throws Exception {
        String model = getModelString(TestType.BASIC, "FragmentSetDeclaration");
        AnalysisDefinition analysis = parseHelper.parse(model);
        
        assertSyntacticalCorrectness(analysis);
        
        EList<ElementDeclaration> elementDeclarations = analysis.getElementDeclarations();
        assertEquals(1, elementDeclarations.size(), "There should only be one element declaration in the test file");
        String problemDescription = testCorrectElementDeclaration(elementDeclarations.get(0), "Fragment", true, false,
                false, false);
        assertNull(problemDescription, problemDescription);
    }
    
    /**
     * Tests the correct parsing of a single fragment set declaration with an initialization using another element.
     * 
     * @throws Exception if the test data file could not be retrieved or the parser failed
     */
    @Test
    public void testFragmentSetDeclarationWithElement() throws Exception {
        String model = getModelString(TestType.BASIC, "FragmentSetDeclarationWithElement");
        AnalysisDefinition analysis = parseHelper.parse(model);
        
        assertSyntacticalCorrectness(analysis);
        
        EList<ElementDeclaration> elementDeclarations = analysis.getElementDeclarations();
        assertEquals(1, elementDeclarations.size(), "There should only be one element declaration in the test file");
        String problemDescription = testCorrectElementDeclaration(elementDeclarations.get(0), "Fragment", true, false,
                false, true);
        assertNull(problemDescription, problemDescription);
    }
    
    /**
     * Tests the correct parsing of a single fragment set declaration with an initialization using an operation.
     * 
     * @throws Exception if the test data file could not be retrieved or the parser failed
     */
    @Test
    public void testFragmentSetDeclarationWithOperation() throws Exception {
        String model = getModelString(TestType.BASIC, "FragmentSetDeclarationWithOperation");
        AnalysisDefinition analysis = parseHelper.parse(model);
        
        assertSyntacticalCorrectness(analysis);
        
        EList<ElementDeclaration> elementDeclarations = analysis.getElementDeclarations();
        assertEquals(1, elementDeclarations.size(), "There should only be one element declaration in the test file");
        String problemDescription = testCorrectElementDeclaration(elementDeclarations.get(0), "Fragment", true, false,
                true, false);
        assertNull(problemDescription, problemDescription);
    }
    
    /**
     * Tests the correct parsing of a single fragment set declaration with a set iteration.
     * 
     * @throws Exception if the test data file could not be retrieved or the parser failed
     */
    @Test
    public void testFragmentSetIterationDeclaration() throws Exception {
        String model = getModelString(TestType.BASIC, "FragmentSetIterationDeclaration");
        AnalysisDefinition analysis = parseHelper.parse(model);
        
        assertSyntacticalCorrectness(analysis);
        
        EList<ElementDeclaration> elementDeclarations = analysis.getElementDeclarations();
        assertEquals(1, elementDeclarations.size(), "There should only be one element declaration in the test file");
        String problemDescription = testCorrectElementDeclaration(elementDeclarations.get(0), "Fragment", true, true,
                false, false);
        assertNull(problemDescription, problemDescription);
    }
//CHECKSTYLE:ON
}
