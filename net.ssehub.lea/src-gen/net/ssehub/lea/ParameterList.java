/**
 * generated by Xtext 2.18.0
 */
package net.ssehub.lea;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Parameter List</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link net.ssehub.lea.ParameterList#getParameterList <em>Parameter List</em>}</li>
 * </ul>
 *
 * @see net.ssehub.lea.LeaPackage#getParameterList()
 * @model
 * @generated
 */
public interface ParameterList extends EObject
{
  /**
   * Returns the value of the '<em><b>Parameter List</b></em>' containment reference list.
   * The list contents are of type {@link net.ssehub.lea.Parameter}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Parameter List</em>' containment reference list.
   * @see net.ssehub.lea.LeaPackage#getParameterList_ParameterList()
   * @model containment="true"
   * @generated
   */
  EList<Parameter> getParameterList();

} // ParameterList
