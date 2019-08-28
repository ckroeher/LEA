package net.ssehub.tests.parsing;

import java.util.Arrays;
import java.util.List;

import org.junit.runners.Parameterized.Parameters;

import net.ssehub.lea.AnalysisDefinition;
import net.ssehub.lea.ElementDeclaration;

/**
 * This class tests the correct validation results of advanced {@link ElementDeclaration}s. Hence, this class relies on
 * the unit tests defined in {@link AbstractParserTest} and does not introduce any new tests, but uses advanced models.
 * 
 * @author Christian Kroeher
 *
 */
public class AdvancedElementDeclarationParsingTests extends AbstractParserTest {
    
    /**
     * The expected results for each test data file. Each entry has the following elements:
     * <ul>
     * <li>The path and name of the desired test data file relative to the {@link #TESTDATA_DIRECTORY}</li>
     * <li>The definition of whether it is expected that the actual analysis definition is valid (<code>true</code>) or
     *     not (<code>false</code>)</li>
     * <li>The expected number of element declarations in {@link AnalysisDefinition#getElementDeclarations()}</li>
     * <li>The expected number of change identifier assignments in 
     *     {@link AnalysisDefinition#getChangeIdentifierAssignments()}</li>
     * </ul>
     */
    private static final Object[][] EXPECTED_RESULTS = new Object[][] {
        {"advanced/ArtifactDeclarationChainWithEqualTypesInitialized.lea", true, 3, 0},
        {"advanced/ArtifactDeclarationChainWithEqualTypesNotInitialized.lea", false, 3, 0},
        {"advanced/ArtifactDeclarationWithSetElement.lea", false, 2, 0},
        {"advanced/ArtifactDeclarationWithSetReturnOperation.lea", false, 1, 0},
        {"advanced/ArtifactSetDeclarationWithElement.lea", false, 2, 0},
        {"advanced/ArtifactSetDeclarationWithNoSetReturnOperation.lea", false, 1, 0}
    };

    /**
     * Creates a new {@link AdvancedElementDeclarationParsingTests} instance.
     * 
     * @param relativeTestModelFilePath the path and name of the desired test data file relative to the
     *        {@link #TESTDATA_DIRECTORY}; should never be <code>null</code>
     * @param expectedAnalysisDefinitionIsValid the definition of whether it is expected that the actual analysis
     *        definition is valid (<code>true</code>) or not (<code>false</code>)
     * @param expectedNumberOfElementDeclarations the expected number of element declarations in 
     *        {@link AnalysisDefinition#getElementDeclarations()}
     * @param expectedNumberOfChangeIdentifierAssignments the expected number of change identifier assignments in 
     *        {@link AnalysisDefinition#getChangeIdentifierAssignments()}
     */
    public AdvancedElementDeclarationParsingTests(String relativeTestModelFilePath,
            boolean expectedAnalysisDefinitionIsValid, int expectedNumberOfElementDeclarations,
            int expectedNumberOfChangeIdentifierAssignments) {
        super(relativeTestModelFilePath, expectedAnalysisDefinitionIsValid, expectedNumberOfElementDeclarations,
                expectedNumberOfChangeIdentifierAssignments);
    }
    
    /**
     * Returns the expected results as parameters for the tests defined in the super-class.
     * 
     * @return the {@link #EXPECTED_RESULTS} as an object-array list
     */
    @Parameters
    public static List<Object[]> getTestData() {
        return Arrays.asList(EXPECTED_RESULTS);
    }

}
