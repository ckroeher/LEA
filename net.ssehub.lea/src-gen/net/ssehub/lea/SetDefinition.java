/**
 * generated by Xtext 2.18.0
 */
package net.ssehub.lea;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Set Definition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link net.ssehub.lea.SetDefinition#getIteration <em>Iteration</em>}</li>
 * </ul>
 *
 * @see net.ssehub.lea.LeaPackage#getSetDefinition()
 * @model
 * @generated
 */
public interface SetDefinition extends EObject
{
  /**
   * Returns the value of the '<em><b>Iteration</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Iteration</em>' containment reference.
   * @see #setIteration(Iteration)
   * @see net.ssehub.lea.LeaPackage#getSetDefinition_Iteration()
   * @model containment="true"
   * @generated
   */
  Iteration getIteration();

  /**
   * Sets the value of the '{@link net.ssehub.lea.SetDefinition#getIteration <em>Iteration</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Iteration</em>' containment reference.
   * @see #getIteration()
   * @generated
   */
  void setIteration(Iteration value);

} // SetDefinition
