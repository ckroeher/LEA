/**
 * generated by Xtext 2.18.0
 */
package net.ssehub.lea;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see net.ssehub.lea.LeaFactory
 * @model kind="package"
 * @generated
 */
public interface LeaPackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "lea";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.ssehub.net/Lea";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "lea";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  LeaPackage eINSTANCE = net.ssehub.lea.impl.LeaPackageImpl.init();

  /**
   * The meta object id for the '{@link net.ssehub.lea.impl.AnalysisDefinitionImpl <em>Analysis Definition</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see net.ssehub.lea.impl.AnalysisDefinitionImpl
   * @see net.ssehub.lea.impl.LeaPackageImpl#getAnalysisDefinition()
   * @generated
   */
  int ANALYSIS_DEFINITION = 0;

  /**
   * The feature id for the '<em><b>Element Declarations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANALYSIS_DEFINITION__ELEMENT_DECLARATIONS = 0;

  /**
   * The feature id for the '<em><b>Change Identifier Assignments</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANALYSIS_DEFINITION__CHANGE_IDENTIFIER_ASSIGNMENTS = 1;

  /**
   * The number of structural features of the '<em>Analysis Definition</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANALYSIS_DEFINITION_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link net.ssehub.lea.impl.ChangeIdentifierAssignmentImpl <em>Change Identifier Assignment</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see net.ssehub.lea.impl.ChangeIdentifierAssignmentImpl
   * @see net.ssehub.lea.impl.LeaPackageImpl#getChangeIdentifierAssignment()
   * @generated
   */
  int CHANGE_IDENTIFIER_ASSIGNMENT = 1;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CHANGE_IDENTIFIER_ASSIGNMENT__IDENTIFIER = 0;

  /**
   * The feature id for the '<em><b>Elements</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CHANGE_IDENTIFIER_ASSIGNMENT__ELEMENTS = 1;

  /**
   * The number of structural features of the '<em>Change Identifier Assignment</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CHANGE_IDENTIFIER_ASSIGNMENT_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link net.ssehub.lea.impl.ElementDeclarationImpl <em>Element Declaration</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see net.ssehub.lea.impl.ElementDeclarationImpl
   * @see net.ssehub.lea.impl.LeaPackageImpl#getElementDeclaration()
   * @generated
   */
  int ELEMENT_DECLARATION = 2;

  /**
   * The feature id for the '<em><b>Generic Typ</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELEMENT_DECLARATION__GENERIC_TYP = 0;

  /**
   * The feature id for the '<em><b>Parameter Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELEMENT_DECLARATION__PARAMETER_TYPE = 1;

  /**
   * The feature id for the '<em><b>Set</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELEMENT_DECLARATION__SET = 2;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELEMENT_DECLARATION__NAME = 3;

  /**
   * The feature id for the '<em><b>Initialization</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELEMENT_DECLARATION__INITIALIZATION = 4;

  /**
   * The number of structural features of the '<em>Element Declaration</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELEMENT_DECLARATION_FEATURE_COUNT = 5;

  /**
   * The meta object id for the '{@link net.ssehub.lea.impl.SetDefinitionImpl <em>Set Definition</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see net.ssehub.lea.impl.SetDefinitionImpl
   * @see net.ssehub.lea.impl.LeaPackageImpl#getSetDefinition()
   * @generated
   */
  int SET_DEFINITION = 3;

  /**
   * The feature id for the '<em><b>Iteration</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SET_DEFINITION__ITERATION = 0;

  /**
   * The number of structural features of the '<em>Set Definition</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SET_DEFINITION_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link net.ssehub.lea.impl.IterationImpl <em>Iteration</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see net.ssehub.lea.impl.IterationImpl
   * @see net.ssehub.lea.impl.LeaPackageImpl#getIteration()
   * @generated
   */
  int ITERATION = 4;

  /**
   * The feature id for the '<em><b>Iterator</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ITERATION__ITERATOR = 0;

  /**
   * The feature id for the '<em><b>Iterable</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ITERATION__ITERABLE = 1;

  /**
   * The number of structural features of the '<em>Iteration</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ITERATION_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link net.ssehub.lea.impl.AssignmentImpl <em>Assignment</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see net.ssehub.lea.impl.AssignmentImpl
   * @see net.ssehub.lea.impl.LeaPackageImpl#getAssignment()
   * @generated
   */
  int ASSIGNMENT = 5;

  /**
   * The feature id for the '<em><b>Element</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ASSIGNMENT__ELEMENT = 0;

  /**
   * The feature id for the '<em><b>Operation</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ASSIGNMENT__OPERATION = 1;

  /**
   * The number of structural features of the '<em>Assignment</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ASSIGNMENT_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link net.ssehub.lea.impl.OperationImpl <em>Operation</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see net.ssehub.lea.impl.OperationImpl
   * @see net.ssehub.lea.impl.LeaPackageImpl#getOperation()
   * @generated
   */
  int OPERATION = 6;

  /**
   * The feature id for the '<em><b>Element</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OPERATION__ELEMENT = 0;

  /**
   * The feature id for the '<em><b>Call</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OPERATION__CALL = 1;

  /**
   * The number of structural features of the '<em>Operation</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OPERATION_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link net.ssehub.lea.impl.CallImpl <em>Call</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see net.ssehub.lea.impl.CallImpl
   * @see net.ssehub.lea.impl.LeaPackageImpl#getCall()
   * @generated
   */
  int CALL = 7;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CALL__NAME = 0;

  /**
   * The feature id for the '<em><b>Parameters</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CALL__PARAMETERS = 1;

  /**
   * The number of structural features of the '<em>Call</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CALL_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link net.ssehub.lea.impl.ParameterListImpl <em>Parameter List</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see net.ssehub.lea.impl.ParameterListImpl
   * @see net.ssehub.lea.impl.LeaPackageImpl#getParameterList()
   * @generated
   */
  int PARAMETER_LIST = 8;

  /**
   * The feature id for the '<em><b>Parameter List</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAMETER_LIST__PARAMETER_LIST = 0;

  /**
   * The number of structural features of the '<em>Parameter List</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAMETER_LIST_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link net.ssehub.lea.impl.ParameterImpl <em>Parameter</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see net.ssehub.lea.impl.ParameterImpl
   * @see net.ssehub.lea.impl.LeaPackageImpl#getParameter()
   * @generated
   */
  int PARAMETER = 9;

  /**
   * The feature id for the '<em><b>Text</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAMETER__TEXT = 0;

  /**
   * The feature id for the '<em><b>Element</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAMETER__ELEMENT = 1;

  /**
   * The feature id for the '<em><b>Operation</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAMETER__OPERATION = 2;

  /**
   * The number of structural features of the '<em>Parameter</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAMETER_FEATURE_COUNT = 3;


  /**
   * Returns the meta object for class '{@link net.ssehub.lea.AnalysisDefinition <em>Analysis Definition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Analysis Definition</em>'.
   * @see net.ssehub.lea.AnalysisDefinition
   * @generated
   */
  EClass getAnalysisDefinition();

  /**
   * Returns the meta object for the containment reference list '{@link net.ssehub.lea.AnalysisDefinition#getElementDeclarations <em>Element Declarations</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Element Declarations</em>'.
   * @see net.ssehub.lea.AnalysisDefinition#getElementDeclarations()
   * @see #getAnalysisDefinition()
   * @generated
   */
  EReference getAnalysisDefinition_ElementDeclarations();

  /**
   * Returns the meta object for the containment reference list '{@link net.ssehub.lea.AnalysisDefinition#getChangeIdentifierAssignments <em>Change Identifier Assignments</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Change Identifier Assignments</em>'.
   * @see net.ssehub.lea.AnalysisDefinition#getChangeIdentifierAssignments()
   * @see #getAnalysisDefinition()
   * @generated
   */
  EReference getAnalysisDefinition_ChangeIdentifierAssignments();

  /**
   * Returns the meta object for class '{@link net.ssehub.lea.ChangeIdentifierAssignment <em>Change Identifier Assignment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Change Identifier Assignment</em>'.
   * @see net.ssehub.lea.ChangeIdentifierAssignment
   * @generated
   */
  EClass getChangeIdentifierAssignment();

  /**
   * Returns the meta object for the attribute '{@link net.ssehub.lea.ChangeIdentifierAssignment#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identifier</em>'.
   * @see net.ssehub.lea.ChangeIdentifierAssignment#getIdentifier()
   * @see #getChangeIdentifierAssignment()
   * @generated
   */
  EAttribute getChangeIdentifierAssignment_Identifier();

  /**
   * Returns the meta object for the attribute list '{@link net.ssehub.lea.ChangeIdentifierAssignment#getElements <em>Elements</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Elements</em>'.
   * @see net.ssehub.lea.ChangeIdentifierAssignment#getElements()
   * @see #getChangeIdentifierAssignment()
   * @generated
   */
  EAttribute getChangeIdentifierAssignment_Elements();

  /**
   * Returns the meta object for class '{@link net.ssehub.lea.ElementDeclaration <em>Element Declaration</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Element Declaration</em>'.
   * @see net.ssehub.lea.ElementDeclaration
   * @generated
   */
  EClass getElementDeclaration();

  /**
   * Returns the meta object for the attribute '{@link net.ssehub.lea.ElementDeclaration#getGenericTyp <em>Generic Typ</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Generic Typ</em>'.
   * @see net.ssehub.lea.ElementDeclaration#getGenericTyp()
   * @see #getElementDeclaration()
   * @generated
   */
  EAttribute getElementDeclaration_GenericTyp();

  /**
   * Returns the meta object for the attribute '{@link net.ssehub.lea.ElementDeclaration#getParameterType <em>Parameter Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Parameter Type</em>'.
   * @see net.ssehub.lea.ElementDeclaration#getParameterType()
   * @see #getElementDeclaration()
   * @generated
   */
  EAttribute getElementDeclaration_ParameterType();

  /**
   * Returns the meta object for the containment reference '{@link net.ssehub.lea.ElementDeclaration#getSet <em>Set</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Set</em>'.
   * @see net.ssehub.lea.ElementDeclaration#getSet()
   * @see #getElementDeclaration()
   * @generated
   */
  EReference getElementDeclaration_Set();

  /**
   * Returns the meta object for the attribute '{@link net.ssehub.lea.ElementDeclaration#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see net.ssehub.lea.ElementDeclaration#getName()
   * @see #getElementDeclaration()
   * @generated
   */
  EAttribute getElementDeclaration_Name();

  /**
   * Returns the meta object for the containment reference '{@link net.ssehub.lea.ElementDeclaration#getInitialization <em>Initialization</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Initialization</em>'.
   * @see net.ssehub.lea.ElementDeclaration#getInitialization()
   * @see #getElementDeclaration()
   * @generated
   */
  EReference getElementDeclaration_Initialization();

  /**
   * Returns the meta object for class '{@link net.ssehub.lea.SetDefinition <em>Set Definition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Set Definition</em>'.
   * @see net.ssehub.lea.SetDefinition
   * @generated
   */
  EClass getSetDefinition();

  /**
   * Returns the meta object for the containment reference '{@link net.ssehub.lea.SetDefinition#getIteration <em>Iteration</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Iteration</em>'.
   * @see net.ssehub.lea.SetDefinition#getIteration()
   * @see #getSetDefinition()
   * @generated
   */
  EReference getSetDefinition_Iteration();

  /**
   * Returns the meta object for class '{@link net.ssehub.lea.Iteration <em>Iteration</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Iteration</em>'.
   * @see net.ssehub.lea.Iteration
   * @generated
   */
  EClass getIteration();

  /**
   * Returns the meta object for the attribute '{@link net.ssehub.lea.Iteration#getIterator <em>Iterator</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Iterator</em>'.
   * @see net.ssehub.lea.Iteration#getIterator()
   * @see #getIteration()
   * @generated
   */
  EAttribute getIteration_Iterator();

  /**
   * Returns the meta object for the attribute '{@link net.ssehub.lea.Iteration#getIterable <em>Iterable</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Iterable</em>'.
   * @see net.ssehub.lea.Iteration#getIterable()
   * @see #getIteration()
   * @generated
   */
  EAttribute getIteration_Iterable();

  /**
   * Returns the meta object for class '{@link net.ssehub.lea.Assignment <em>Assignment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Assignment</em>'.
   * @see net.ssehub.lea.Assignment
   * @generated
   */
  EClass getAssignment();

  /**
   * Returns the meta object for the attribute '{@link net.ssehub.lea.Assignment#getElement <em>Element</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Element</em>'.
   * @see net.ssehub.lea.Assignment#getElement()
   * @see #getAssignment()
   * @generated
   */
  EAttribute getAssignment_Element();

  /**
   * Returns the meta object for the containment reference '{@link net.ssehub.lea.Assignment#getOperation <em>Operation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Operation</em>'.
   * @see net.ssehub.lea.Assignment#getOperation()
   * @see #getAssignment()
   * @generated
   */
  EReference getAssignment_Operation();

  /**
   * Returns the meta object for class '{@link net.ssehub.lea.Operation <em>Operation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Operation</em>'.
   * @see net.ssehub.lea.Operation
   * @generated
   */
  EClass getOperation();

  /**
   * Returns the meta object for the attribute '{@link net.ssehub.lea.Operation#getElement <em>Element</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Element</em>'.
   * @see net.ssehub.lea.Operation#getElement()
   * @see #getOperation()
   * @generated
   */
  EAttribute getOperation_Element();

  /**
   * Returns the meta object for the containment reference list '{@link net.ssehub.lea.Operation#getCall <em>Call</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Call</em>'.
   * @see net.ssehub.lea.Operation#getCall()
   * @see #getOperation()
   * @generated
   */
  EReference getOperation_Call();

  /**
   * Returns the meta object for class '{@link net.ssehub.lea.Call <em>Call</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Call</em>'.
   * @see net.ssehub.lea.Call
   * @generated
   */
  EClass getCall();

  /**
   * Returns the meta object for the attribute '{@link net.ssehub.lea.Call#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see net.ssehub.lea.Call#getName()
   * @see #getCall()
   * @generated
   */
  EAttribute getCall_Name();

  /**
   * Returns the meta object for the containment reference '{@link net.ssehub.lea.Call#getParameters <em>Parameters</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Parameters</em>'.
   * @see net.ssehub.lea.Call#getParameters()
   * @see #getCall()
   * @generated
   */
  EReference getCall_Parameters();

  /**
   * Returns the meta object for class '{@link net.ssehub.lea.ParameterList <em>Parameter List</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Parameter List</em>'.
   * @see net.ssehub.lea.ParameterList
   * @generated
   */
  EClass getParameterList();

  /**
   * Returns the meta object for the containment reference list '{@link net.ssehub.lea.ParameterList#getParameterList <em>Parameter List</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Parameter List</em>'.
   * @see net.ssehub.lea.ParameterList#getParameterList()
   * @see #getParameterList()
   * @generated
   */
  EReference getParameterList_ParameterList();

  /**
   * Returns the meta object for class '{@link net.ssehub.lea.Parameter <em>Parameter</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Parameter</em>'.
   * @see net.ssehub.lea.Parameter
   * @generated
   */
  EClass getParameter();

  /**
   * Returns the meta object for the attribute '{@link net.ssehub.lea.Parameter#getText <em>Text</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Text</em>'.
   * @see net.ssehub.lea.Parameter#getText()
   * @see #getParameter()
   * @generated
   */
  EAttribute getParameter_Text();

  /**
   * Returns the meta object for the attribute '{@link net.ssehub.lea.Parameter#getElement <em>Element</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Element</em>'.
   * @see net.ssehub.lea.Parameter#getElement()
   * @see #getParameter()
   * @generated
   */
  EAttribute getParameter_Element();

  /**
   * Returns the meta object for the containment reference '{@link net.ssehub.lea.Parameter#getOperation <em>Operation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Operation</em>'.
   * @see net.ssehub.lea.Parameter#getOperation()
   * @see #getParameter()
   * @generated
   */
  EReference getParameter_Operation();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  LeaFactory getLeaFactory();

  /**
   * <!-- begin-user-doc -->
   * Defines literals for the meta objects that represent
   * <ul>
   *   <li>each class,</li>
   *   <li>each feature of each class,</li>
   *   <li>each enum,</li>
   *   <li>and each data type</li>
   * </ul>
   * <!-- end-user-doc -->
   * @generated
   */
  interface Literals
  {
    /**
     * The meta object literal for the '{@link net.ssehub.lea.impl.AnalysisDefinitionImpl <em>Analysis Definition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.ssehub.lea.impl.AnalysisDefinitionImpl
     * @see net.ssehub.lea.impl.LeaPackageImpl#getAnalysisDefinition()
     * @generated
     */
    EClass ANALYSIS_DEFINITION = eINSTANCE.getAnalysisDefinition();

    /**
     * The meta object literal for the '<em><b>Element Declarations</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ANALYSIS_DEFINITION__ELEMENT_DECLARATIONS = eINSTANCE.getAnalysisDefinition_ElementDeclarations();

    /**
     * The meta object literal for the '<em><b>Change Identifier Assignments</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ANALYSIS_DEFINITION__CHANGE_IDENTIFIER_ASSIGNMENTS = eINSTANCE.getAnalysisDefinition_ChangeIdentifierAssignments();

    /**
     * The meta object literal for the '{@link net.ssehub.lea.impl.ChangeIdentifierAssignmentImpl <em>Change Identifier Assignment</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.ssehub.lea.impl.ChangeIdentifierAssignmentImpl
     * @see net.ssehub.lea.impl.LeaPackageImpl#getChangeIdentifierAssignment()
     * @generated
     */
    EClass CHANGE_IDENTIFIER_ASSIGNMENT = eINSTANCE.getChangeIdentifierAssignment();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute CHANGE_IDENTIFIER_ASSIGNMENT__IDENTIFIER = eINSTANCE.getChangeIdentifierAssignment_Identifier();

    /**
     * The meta object literal for the '<em><b>Elements</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute CHANGE_IDENTIFIER_ASSIGNMENT__ELEMENTS = eINSTANCE.getChangeIdentifierAssignment_Elements();

    /**
     * The meta object literal for the '{@link net.ssehub.lea.impl.ElementDeclarationImpl <em>Element Declaration</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.ssehub.lea.impl.ElementDeclarationImpl
     * @see net.ssehub.lea.impl.LeaPackageImpl#getElementDeclaration()
     * @generated
     */
    EClass ELEMENT_DECLARATION = eINSTANCE.getElementDeclaration();

    /**
     * The meta object literal for the '<em><b>Generic Typ</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ELEMENT_DECLARATION__GENERIC_TYP = eINSTANCE.getElementDeclaration_GenericTyp();

    /**
     * The meta object literal for the '<em><b>Parameter Type</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ELEMENT_DECLARATION__PARAMETER_TYPE = eINSTANCE.getElementDeclaration_ParameterType();

    /**
     * The meta object literal for the '<em><b>Set</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ELEMENT_DECLARATION__SET = eINSTANCE.getElementDeclaration_Set();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ELEMENT_DECLARATION__NAME = eINSTANCE.getElementDeclaration_Name();

    /**
     * The meta object literal for the '<em><b>Initialization</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ELEMENT_DECLARATION__INITIALIZATION = eINSTANCE.getElementDeclaration_Initialization();

    /**
     * The meta object literal for the '{@link net.ssehub.lea.impl.SetDefinitionImpl <em>Set Definition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.ssehub.lea.impl.SetDefinitionImpl
     * @see net.ssehub.lea.impl.LeaPackageImpl#getSetDefinition()
     * @generated
     */
    EClass SET_DEFINITION = eINSTANCE.getSetDefinition();

    /**
     * The meta object literal for the '<em><b>Iteration</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SET_DEFINITION__ITERATION = eINSTANCE.getSetDefinition_Iteration();

    /**
     * The meta object literal for the '{@link net.ssehub.lea.impl.IterationImpl <em>Iteration</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.ssehub.lea.impl.IterationImpl
     * @see net.ssehub.lea.impl.LeaPackageImpl#getIteration()
     * @generated
     */
    EClass ITERATION = eINSTANCE.getIteration();

    /**
     * The meta object literal for the '<em><b>Iterator</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ITERATION__ITERATOR = eINSTANCE.getIteration_Iterator();

    /**
     * The meta object literal for the '<em><b>Iterable</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ITERATION__ITERABLE = eINSTANCE.getIteration_Iterable();

    /**
     * The meta object literal for the '{@link net.ssehub.lea.impl.AssignmentImpl <em>Assignment</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.ssehub.lea.impl.AssignmentImpl
     * @see net.ssehub.lea.impl.LeaPackageImpl#getAssignment()
     * @generated
     */
    EClass ASSIGNMENT = eINSTANCE.getAssignment();

    /**
     * The meta object literal for the '<em><b>Element</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ASSIGNMENT__ELEMENT = eINSTANCE.getAssignment_Element();

    /**
     * The meta object literal for the '<em><b>Operation</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ASSIGNMENT__OPERATION = eINSTANCE.getAssignment_Operation();

    /**
     * The meta object literal for the '{@link net.ssehub.lea.impl.OperationImpl <em>Operation</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.ssehub.lea.impl.OperationImpl
     * @see net.ssehub.lea.impl.LeaPackageImpl#getOperation()
     * @generated
     */
    EClass OPERATION = eINSTANCE.getOperation();

    /**
     * The meta object literal for the '<em><b>Element</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute OPERATION__ELEMENT = eINSTANCE.getOperation_Element();

    /**
     * The meta object literal for the '<em><b>Call</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference OPERATION__CALL = eINSTANCE.getOperation_Call();

    /**
     * The meta object literal for the '{@link net.ssehub.lea.impl.CallImpl <em>Call</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.ssehub.lea.impl.CallImpl
     * @see net.ssehub.lea.impl.LeaPackageImpl#getCall()
     * @generated
     */
    EClass CALL = eINSTANCE.getCall();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute CALL__NAME = eINSTANCE.getCall_Name();

    /**
     * The meta object literal for the '<em><b>Parameters</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CALL__PARAMETERS = eINSTANCE.getCall_Parameters();

    /**
     * The meta object literal for the '{@link net.ssehub.lea.impl.ParameterListImpl <em>Parameter List</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.ssehub.lea.impl.ParameterListImpl
     * @see net.ssehub.lea.impl.LeaPackageImpl#getParameterList()
     * @generated
     */
    EClass PARAMETER_LIST = eINSTANCE.getParameterList();

    /**
     * The meta object literal for the '<em><b>Parameter List</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PARAMETER_LIST__PARAMETER_LIST = eINSTANCE.getParameterList_ParameterList();

    /**
     * The meta object literal for the '{@link net.ssehub.lea.impl.ParameterImpl <em>Parameter</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.ssehub.lea.impl.ParameterImpl
     * @see net.ssehub.lea.impl.LeaPackageImpl#getParameter()
     * @generated
     */
    EClass PARAMETER = eINSTANCE.getParameter();

    /**
     * The meta object literal for the '<em><b>Text</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PARAMETER__TEXT = eINSTANCE.getParameter_Text();

    /**
     * The meta object literal for the '<em><b>Element</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PARAMETER__ELEMENT = eINSTANCE.getParameter_Element();

    /**
     * The meta object literal for the '<em><b>Operation</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PARAMETER__OPERATION = eINSTANCE.getParameter_Operation();

  }

} //LeaPackage
