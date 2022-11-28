/**
 * Copyright (c) 2021 EclipseSource and others.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0, or the MIT License which is
 * available at https://opensource.org/licenses/MIT.
 * 
 * SPDX-License-Identifier: EPL-2.0 OR MIT
 */
package com.eclipsesource.uml.modelserver.unotation;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.glsp.server.emf.model.notation.NotationPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see com.eclipsesource.uml.modelserver.unotation.UnotationFactory
 * @model kind="package"
 * @generated
 */
public interface UnotationPackage extends EPackage {
   /**
    * The package name.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String eNAME = "unotation";

   /**
    * The package namespace URI.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String eNS_URI = "http://www.eclipsesource.com/glsp/uml/unotation";

   /**
    * The package namespace name.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String eNS_PREFIX = "unotation";

   /**
    * The singleton instance of the package.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   UnotationPackage eINSTANCE = com.eclipsesource.uml.modelserver.unotation.impl.UnotationPackageImpl.init();

   /**
    * The meta object id for the '{@link com.eclipsesource.uml.modelserver.unotation.impl.UmlDiagramImpl <em>Uml Diagram</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see com.eclipsesource.uml.modelserver.unotation.impl.UmlDiagramImpl
    * @see com.eclipsesource.uml.modelserver.unotation.impl.UnotationPackageImpl#getUmlDiagram()
    * @generated
    */
   int UML_DIAGRAM = 0;

   /**
    * The feature id for the '<em><b>Semantic Element</b></em>' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int UML_DIAGRAM__SEMANTIC_ELEMENT = NotationPackage.DIAGRAM__SEMANTIC_ELEMENT;

   /**
    * The feature id for the '<em><b>Type</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int UML_DIAGRAM__TYPE = NotationPackage.DIAGRAM__TYPE;

   /**
    * The feature id for the '<em><b>Elements</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int UML_DIAGRAM__ELEMENTS = NotationPackage.DIAGRAM__ELEMENTS;

   /**
    * The feature id for the '<em><b>Diagram Type</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int UML_DIAGRAM__DIAGRAM_TYPE = NotationPackage.DIAGRAM__DIAGRAM_TYPE;

   /**
    * The feature id for the '<em><b>Representation</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int UML_DIAGRAM__REPRESENTATION = NotationPackage.DIAGRAM_FEATURE_COUNT + 0;

   /**
    * The number of structural features of the '<em>Uml Diagram</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int UML_DIAGRAM_FEATURE_COUNT = NotationPackage.DIAGRAM_FEATURE_COUNT + 1;

   /**
    * The number of operations of the '<em>Uml Diagram</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int UML_DIAGRAM_OPERATION_COUNT = NotationPackage.DIAGRAM_OPERATION_COUNT + 0;

   /**
    * The meta object id for the '{@link com.eclipsesource.uml.modelserver.unotation.Representation <em>Representation</em>}' enum.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see com.eclipsesource.uml.modelserver.unotation.Representation
    * @see com.eclipsesource.uml.modelserver.unotation.impl.UnotationPackageImpl#getRepresentation()
    * @generated
    */
   int REPRESENTATION = 1;


   /**
    * Returns the meta object for class '{@link com.eclipsesource.uml.modelserver.unotation.UmlDiagram <em>Uml Diagram</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>Uml Diagram</em>'.
    * @see com.eclipsesource.uml.modelserver.unotation.UmlDiagram
    * @generated
    */
   EClass getUmlDiagram();

   /**
    * Returns the meta object for the attribute '{@link com.eclipsesource.uml.modelserver.unotation.UmlDiagram#getRepresentation <em>Representation</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Representation</em>'.
    * @see com.eclipsesource.uml.modelserver.unotation.UmlDiagram#getRepresentation()
    * @see #getUmlDiagram()
    * @generated
    */
   EAttribute getUmlDiagram_Representation();

   /**
    * Returns the meta object for enum '{@link com.eclipsesource.uml.modelserver.unotation.Representation <em>Representation</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for enum '<em>Representation</em>'.
    * @see com.eclipsesource.uml.modelserver.unotation.Representation
    * @generated
    */
   EEnum getRepresentation();

   /**
    * Returns the factory that creates the instances of the model.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the factory that creates the instances of the model.
    * @generated
    */
   UnotationFactory getUnotationFactory();

   /**
    * <!-- begin-user-doc -->
    * Defines literals for the meta objects that represent
    * <ul>
    *   <li>each class,</li>
    *   <li>each feature of each class,</li>
    *   <li>each operation of each class,</li>
    *   <li>each enum,</li>
    *   <li>and each data type</li>
    * </ul>
    * <!-- end-user-doc -->
    * @generated
    */
   interface Literals {
      /**
       * The meta object literal for the '{@link com.eclipsesource.uml.modelserver.unotation.impl.UmlDiagramImpl <em>Uml Diagram</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see com.eclipsesource.uml.modelserver.unotation.impl.UmlDiagramImpl
       * @see com.eclipsesource.uml.modelserver.unotation.impl.UnotationPackageImpl#getUmlDiagram()
       * @generated
       */
      EClass UML_DIAGRAM = eINSTANCE.getUmlDiagram();

      /**
       * The meta object literal for the '<em><b>Representation</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute UML_DIAGRAM__REPRESENTATION = eINSTANCE.getUmlDiagram_Representation();

      /**
       * The meta object literal for the '{@link com.eclipsesource.uml.modelserver.unotation.Representation <em>Representation</em>}' enum.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see com.eclipsesource.uml.modelserver.unotation.Representation
       * @see com.eclipsesource.uml.modelserver.unotation.impl.UnotationPackageImpl#getRepresentation()
       * @generated
       */
      EEnum REPRESENTATION = eINSTANCE.getRepresentation();

   }

} //UnotationPackage
