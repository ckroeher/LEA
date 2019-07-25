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

@RunWith(XtextRunner.class)
@InjectWith(LeaInjectorProvider.class)
public class BasicArtifactParsingTests extends AbstractTest {
    
    @Inject
    ParseHelper<AnalysisDefinition> parseHelper;

    @Test
    public void testEmptyFile() throws Exception {
        String model = getModelString(TestType.BASIC, "EmptyFile");
        AnalysisDefinition analysis = parseHelper.parse(model);
        
        assertSyntacticalCorrectness(analysis);
        
        assertEquals(0, analysis.getElementDeclarations().size(), "An empty model should not contain element declarations");
        assertEquals(0, analysis.getChangeIdentifierAssignments().size(), "An empty model should not contain change identifier assignments");
    }
    
    @Test
    public void testArtifactDeclaration() throws Exception {
        String model = getModelString(TestType.BASIC, "ArtifactDeclaration");
        AnalysisDefinition analysis = parseHelper.parse(model);
        
        assertSyntacticalCorrectness(analysis);
        
        EList<ElementDeclaration> elementDeclarations = analysis.getElementDeclarations();
        assertEquals(1, elementDeclarations.size(), "There should only be one element declaration in the test file");
        String problemDescription = testCorrectElementDeclaration(elementDeclarations.get(0), "Artifact", false, false, false);
        assertNull(problemDescription, problemDescription);
    }
    
    @Test
    public void testArtifactDeclarationWithElement() throws Exception {
        String model = getModelString(TestType.BASIC, "ArtifactDeclarationWithElement");
        AnalysisDefinition analysis = parseHelper.parse(model);
        
        assertSyntacticalCorrectness(analysis);
        
        EList<ElementDeclaration> elementDeclarations = analysis.getElementDeclarations();
        assertEquals(1, elementDeclarations.size(), "There should only be one element declaration in the test file");
        String problemDescription = testCorrectElementDeclaration(elementDeclarations.get(0), "Artifact", false, false, true);
        assertNull(problemDescription, problemDescription);
    }
    
    @Test
    public void testArtifactDeclarationWithOperation() throws Exception {
        String model = getModelString(TestType.BASIC, "ArtifactDeclarationWithOperation");
        AnalysisDefinition analysis = parseHelper.parse(model);
        
        assertSyntacticalCorrectness(analysis);
        
        EList<ElementDeclaration> elementDeclarations = analysis.getElementDeclarations();
        assertEquals(1, elementDeclarations.size(), "There should only be one element declaration in the test file");
        String problemDescription = testCorrectElementDeclaration(elementDeclarations.get(0), "Artifact", false, true, false);
        assertNull(problemDescription, problemDescription);
    }
    
    @Test
    public void testArtifactSetDeclaration() throws Exception {
        String model = getModelString(TestType.BASIC, "ArtifactSetDeclaration");
        AnalysisDefinition analysis = parseHelper.parse(model);
        
        assertSyntacticalCorrectness(analysis);
        
        EList<ElementDeclaration> elementDeclarations = analysis.getElementDeclarations();
        assertEquals(1, elementDeclarations.size(), "There should only be one element declaration in the test file");
        String problemDescription = testCorrectElementDeclaration(elementDeclarations.get(0), "Artifact", true, false, false);
        assertNull(problemDescription, problemDescription);
    }
    
    @Test
    public void testArtifactSetDeclarationWithElement() throws Exception {
        String model = getModelString(TestType.BASIC, "ArtifactSetDeclarationWithElement");
        AnalysisDefinition analysis = parseHelper.parse(model);
        
        assertSyntacticalCorrectness(analysis);
        
        EList<ElementDeclaration> elementDeclarations = analysis.getElementDeclarations();
        assertEquals(1, elementDeclarations.size(), "There should only be one element declaration in the test file");
        String problemDescription = testCorrectElementDeclaration(elementDeclarations.get(0), "Artifact", true, false, true);
        assertNull(problemDescription, problemDescription);
    }
    
    @Test
    public void testArtifactSetDeclarationWithOperation() throws Exception {
        String model = getModelString(TestType.BASIC, "ArtifactSetDeclarationWithOperation");
        AnalysisDefinition analysis = parseHelper.parse(model);
        
        assertSyntacticalCorrectness(analysis);
        
        EList<ElementDeclaration> elementDeclarations = analysis.getElementDeclarations();
        assertEquals(1, elementDeclarations.size(), "There should only be one element declaration in the test file");
        String problemDescription = testCorrectElementDeclaration(elementDeclarations.get(0), "Artifact", true, true, false);
        assertNull(problemDescription, problemDescription);
    }
    
}
