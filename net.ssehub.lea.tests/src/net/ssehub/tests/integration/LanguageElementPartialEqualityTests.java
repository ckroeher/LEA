package net.ssehub.tests.integration;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.junit.Test;

import net.ssehub.integration.Call;
import net.ssehub.integration.ChangeIdentifier;
import net.ssehub.integration.ElementType;
import net.ssehub.integration.LanguageElement;
import net.ssehub.integration.LanguageElementException;
import net.ssehub.integration.ParameterType;

/**
 * This class contains unit tests for partial equality checks of {@link LanguageElement}s. These tests check whether the
 * <code>equalsIgnoreType()</code>-methods work as expected.
 * 
 * @author Christian Kroeher
 *
 */
public class LanguageElementPartialEqualityTests {

    /**
     * The {@link File} denoting the source plug-in of the {@link Class} from which a {@link LanguageElement} should be
     * created. This is just a dummy file as it has no impact on the actual creation, but is only used as a constructor
     * parameter.
     */
    private static final File SOURCE_PLUGIN = new File("./");
    
    /**
     * Test whether a {@link ParameterType} of the type {@link ElementType#ARTIFACT_PARAMETER_TYPE} and a 
     * {@link ParameterType} of the type {@link ElementType#FRAGMENT_PARAMETER_TYPE} are partially equal.
     */
    @Test
    public void testCorrectPartiallyEqualArtifactAndFragmentParameterTypes() {
        try {
            ParameterType artifactParameterType = new ParameterType(ElementType.ARTIFACT_PARAMETER_TYPE, "File",
                    LanguageElementPartialEqualityTests.class, SOURCE_PLUGIN);
            ParameterType fragmentParameterType = new ParameterType(ElementType.FRAGMENT_PARAMETER_TYPE, "File",
                    LanguageElementPartialEqualityTests.class, SOURCE_PLUGIN);
            
            assertTrue(artifactParameterType.equalsIgnoreType(fragmentParameterType),
                    "Artifact and fragment parameter types should be partially equal");
        } catch (LanguageElementException e) {
            assertNull("Unexpected exception thrown", e);
        }
    }
    
    /**
     * Test whether a {@link ParameterType} and a {@link ChangeIdentifier} are partially unequal. In this test, the 
     * elements have different names, which should be the reason for their inequality.
     */
    @Test
    public void testCorrectPartiallyUnequalParameterTypeAndChangeIdentifierWithDifferentNames() {
        try {
            ParameterType parameterType = new ParameterType(ElementType.ARTIFACT_PARAMETER_TYPE, "File",
                    LanguageElementPartialEqualityTests.class, SOURCE_PLUGIN);
            ChangeIdentifier changeIdentifier = new ChangeIdentifier("CI", new String[] {"File"},
                    LanguageElementPartialEqualityTests.class, SOURCE_PLUGIN);
            
            assertFalse(parameterType.equalsIgnoreType(changeIdentifier),
                    "Parameter type and change identifier should be partially unequal");
        } catch (LanguageElementException e) {
            assertNull("Unexpected exception thrown", e);
        }
    }
    
    /**
     * Test whether a {@link ParameterType} and a {@link ChangeIdentifier} are partially unequal. In this test, the 
     * elements have the same name. Hence, the reason for their inequality is their runtime class.
     */
    @Test
    public void testCorrectPartiallyUnequalParameterTypeAndChangeIdentifierWithEqualNames() {
        try {
            ParameterType parameterType = new ParameterType(ElementType.ARTIFACT_PARAMETER_TYPE, "File",
                    LanguageElementPartialEqualityTests.class, SOURCE_PLUGIN);
            ChangeIdentifier changeIdentifier = new ChangeIdentifier("File", new String[] {"File"},
                    LanguageElementPartialEqualityTests.class, SOURCE_PLUGIN);
            
            assertFalse(parameterType.equalsIgnoreType(changeIdentifier),
                    "Parameter type and change identifier should be partially unequal");
        } catch (LanguageElementException e) {
            assertNull("Unexpected exception thrown", e);
        }
    }
    
    /**
     * Test whether a {@link ParameterType} and a {@link Call} are partially unequal. In this test, the elements have
     * different names, which should be the reason for their inequality.
     */
    @Test
    public void testCorrectPartiallyUnequalParameterTypeAndCallWithDifferentNames() {
        try {
            ParameterType parameterType = new ParameterType(ElementType.ARTIFACT_PARAMETER_TYPE, "File",
                    LanguageElementPartialEqualityTests.class, SOURCE_PLUGIN);
            Call call = new Call(ElementType.OPERATION, "file", "File", new String[] {"String"},
                    LanguageElementPartialEqualityTests.class.getMethods()[0],
                    LanguageElementPartialEqualityTests.class, SOURCE_PLUGIN);
            
            assertFalse(parameterType.equalsIgnoreType(call), "Parameter type and call should be partially unequal");
        } catch (LanguageElementException e) {
            assertNull("Unexpected exception thrown", e);
        }
    }
    
    /**
     * Test whether a {@link ParameterType} and a {@link Call} are partially unequal. In this test, the elements have
     * the same name. Hence, the reason for their inequality is their runtime class.
     */
    @Test
    public void testCorrectPartiallyUnequalParameterTypeAndCallWithEqualNames() {
        try {
            ParameterType parameterType = new ParameterType(ElementType.ARTIFACT_PARAMETER_TYPE, "File",
                    LanguageElementPartialEqualityTests.class, SOURCE_PLUGIN);
            Call call = new Call(ElementType.OPERATION, "File", "File", new String[] {"String"},
                    LanguageElementPartialEqualityTests.class.getMethods()[0],
                    LanguageElementPartialEqualityTests.class, SOURCE_PLUGIN);
            
            assertFalse(parameterType.equalsIgnoreType(call), "Parameter type and call should be partially unequal");
        } catch (LanguageElementException e) {
            assertNull("Unexpected exception thrown", e);
        }
    }
    
    /**
     * Test whether a {@link ChangeIdentifier} and a {@link ParameterType} are partially unequal. In this test, the 
     * elements have different names, which should be the reason for their inequality.
     */
    @Test
    public void testCorrectPartiallyUnequalChangeIdentifierAndParameterTypeWithDifferentNames() {
        try {
            ChangeIdentifier changeIdentifier = new ChangeIdentifier("CI", new String[] {"File"},
                    LanguageElementPartialEqualityTests.class, SOURCE_PLUGIN);
            ParameterType parameterType = new ParameterType(ElementType.ARTIFACT_PARAMETER_TYPE, "File",
                    LanguageElementPartialEqualityTests.class, SOURCE_PLUGIN);
            
            assertFalse(changeIdentifier.equalsIgnoreType(parameterType),
                    "Change identifier and parameter type should be partially unequal");
        } catch (LanguageElementException e) {
            assertNull("Unexpected exception thrown", e);
        }
    }
    
    /**
     * Test whether a {@link ChangeIdentifier} and a {@link ParameterType} are partially unequal. In this test, the 
     * elements have the same name. Hence, the reason for their inequality is their runtime class.
     */
    @Test
    public void testCorrectPartiallyUnequalChangeIdentifierAndParameterTypeWithEqualNames() {
        try {
            ChangeIdentifier changeIdentifier = new ChangeIdentifier("File", new String[] {"File"},
                    LanguageElementPartialEqualityTests.class, SOURCE_PLUGIN);
            ParameterType parameterType = new ParameterType(ElementType.ARTIFACT_PARAMETER_TYPE, "File",
                    LanguageElementPartialEqualityTests.class, SOURCE_PLUGIN);
            
            assertFalse(changeIdentifier.equalsIgnoreType(parameterType),
                    "Change identifier and parameter type should be partially unequal");
        } catch (LanguageElementException e) {
            assertNull("Unexpected exception thrown", e);
        }
    }
    
    /**
     * Test whether a {@link ChangeIdentifier} and a {@link Call} are partially unequal. In this test, the elements have
     * different names, which should be the reason for their inequality.
     */
    @Test
    public void testCorrectPartiallyUnequalChangeIdentifierAndCallWithDifferentNames() {
        try {
            ChangeIdentifier changeIdentifier = new ChangeIdentifier("File", new String[] {"File"},
                    LanguageElementPartialEqualityTests.class, SOURCE_PLUGIN);
            Call call = new Call(ElementType.OPERATION, "file", "File", new String[] {"String"},
                    LanguageElementPartialEqualityTests.class.getMethods()[0],
                    LanguageElementPartialEqualityTests.class, SOURCE_PLUGIN);
            
            assertFalse(changeIdentifier.equalsIgnoreType(call),
                    "Change identifier and call should be partially unequal");
        } catch (LanguageElementException e) {
            assertNull("Unexpected exception thrown", e);
        }
    }
    
    /**
     * Test whether a {@link ChangeIdentifier} and a {@link Call} are partially unequal. In this test, the elements have
     * the same name. Hence, the reason for their inequality is their runtime class.
     */
    @Test
    public void testCorrectPartiallyUnequalChangeIdentifierAndCallWithEqualNames() {
        try {
            ChangeIdentifier changeIdentifier = new ChangeIdentifier("File", new String[] {"File"},
                    LanguageElementPartialEqualityTests.class, SOURCE_PLUGIN);
            Call call = new Call(ElementType.OPERATION, "File", "File", new String[] {"String"},
                    LanguageElementPartialEqualityTests.class.getMethods()[0],
                    LanguageElementPartialEqualityTests.class, SOURCE_PLUGIN);
            
            assertFalse(changeIdentifier.equalsIgnoreType(call),
                    "Change identifier and call should be partially unequal");
        } catch (LanguageElementException e) {
            assertNull("Unexpected exception thrown", e);
        }
    }
    
    /**
     * Test whether a {@link Call} of the type {@link ElementType#EXTRACTOR_CALL} and a {@link Call} of the type
     * {@link ElementType#ANALYSIS_CALL} are partially equal.
     */
    @Test
    public void testCorrectPartiallyEqualExtractorAndAnalysisCalls() {
        try {
            Call extractorCall = new Call(ElementType.EXTRACTOR_CALL, "do", "File", new String[] {"String"},
                    LanguageElementPartialEqualityTests.class.getMethods()[0],
                    LanguageElementPartialEqualityTests.class, SOURCE_PLUGIN);
            Call analysisCall = new Call(ElementType.ANALYSIS_CALL, "do", "File", new String[] {"String"},
                    LanguageElementPartialEqualityTests.class.getMethods()[0],
                    LanguageElementPartialEqualityTests.class, SOURCE_PLUGIN);
                        
            assertTrue(extractorCall.equalsIgnoreType(analysisCall),
                    "Extractor and analysis call should be partially equal");
        } catch (LanguageElementException e) {
            assertNull("Unexpected exception thrown", e);
        }
    }
    
    /**
     * Test whether a {@link Call} and a {@link ParameterType} are partially unequal. In this test, the elements have
     * different names, which should be the reason for their inequality.
     */
    @Test
    public void testCorrectPartiallyUnequalCallAndParameterTypeWithDifferentNames() {
        try {
            Call call = new Call(ElementType.OPERATION, "file", "File", new String[] {"String"},
                    LanguageElementPartialEqualityTests.class.getMethods()[0],
                    LanguageElementPartialEqualityTests.class, SOURCE_PLUGIN);
            ParameterType parameterType = new ParameterType(ElementType.ARTIFACT_PARAMETER_TYPE, "File",
                    LanguageElementPartialEqualityTests.class, SOURCE_PLUGIN);
            
            assertFalse(call.equalsIgnoreType(parameterType), "Call and parameter type should be partially unequal");
        } catch (LanguageElementException e) {
            assertNull("Unexpected exception thrown", e);
        }
    }
    
    /**
     * Test whether a {@link Call} and a {@link ParameterType} are partially unequal. In this test, the elements have
     * the same name. Hence, the reason for their inequality is their runtime class.
     */
    @Test
    public void testCorrectPartiallyUnequalCallAndParameterTypeWithEqualNames() {
        try {
            Call call = new Call(ElementType.OPERATION, "File", "File", new String[] {"String"},
                    LanguageElementPartialEqualityTests.class.getMethods()[0],
                    LanguageElementPartialEqualityTests.class, SOURCE_PLUGIN);
            ParameterType parameterType = new ParameterType(ElementType.ARTIFACT_PARAMETER_TYPE, "File",
                    LanguageElementPartialEqualityTests.class, SOURCE_PLUGIN);
            
            assertFalse(call.equalsIgnoreType(parameterType), "Call and parameter type should be partially unequal");
        } catch (LanguageElementException e) {
            assertNull("Unexpected exception thrown", e);
        }
    }
    
    /**
     * Test whether a {@link Call} and a {@link ChangeIdentifier} are partially unequal. In this test, the elements have
     * different names, which should be the reason for their inequality.
     */
    @Test
    public void testCorrectPartiallyUnequalCallAndChangeIdentifierWithDifferentNames() {
        try {
            Call call = new Call(ElementType.OPERATION, "file", "File", new String[] {"String"},
                    LanguageElementPartialEqualityTests.class.getMethods()[0],
                    LanguageElementPartialEqualityTests.class, SOURCE_PLUGIN);
            ChangeIdentifier changeIdentifier = new ChangeIdentifier("File", new String[] {"File"},
                    LanguageElementPartialEqualityTests.class, SOURCE_PLUGIN);
            
            assertFalse(call.equalsIgnoreType(changeIdentifier),
                    "Call and change identifier should be partially unequal");
        } catch (LanguageElementException e) {
            assertNull("Unexpected exception thrown", e);
        }
    }
    
    /**
     * Test whether a {@link Call} and a {@link ChangeIdentifier} are partially unequal. In this test, the elements have
     * the same name. Hence, the reason for their inequality is their runtime class.
     */
    @Test
    public void testCorrectPartiallyUnequalCallAndChangeIdentifierWithEqualNames() {
        try {
            Call call = new Call(ElementType.OPERATION, "File", "File", new String[] {"String"},
                    LanguageElementPartialEqualityTests.class.getMethods()[0],
                    LanguageElementPartialEqualityTests.class, SOURCE_PLUGIN);
            ChangeIdentifier changeIdentifier = new ChangeIdentifier("File", new String[] {"File"},
                    LanguageElementPartialEqualityTests.class, SOURCE_PLUGIN);
            
            assertFalse(call.equalsIgnoreType(changeIdentifier),
                    "Call and change identifier should be partially unequal");
        } catch (LanguageElementException e) {
            assertNull("Unexpected exception thrown", e);
        }
    }
}
