/********************************************************************************
 * Copyright (c) 2022 EclipseSource and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0, or the MIT License which is
 * available at https://opensource.org/licenses/MIT.
 *
 * SPDX-License-Identifier: EPL-2.0 OR MIT
 ********************************************************************************/
package com.eclipsesource.uml.modelserver.shared.notation.commands;

import java.util.Optional;
import java.util.function.Supplier;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.glsp.graph.GDimension;
import org.eclipse.glsp.graph.GPoint;
import org.eclipse.glsp.server.emf.model.notation.NotationFactory;

import com.eclipsesource.uml.modelserver.shared.extension.SemanticElementAccessor;
import com.eclipsesource.uml.modelserver.shared.model.ModelContext;
import com.eclipsesource.uml.modelserver.shared.notation.BaseNotationElementCommand;

public class AddShapeNotationCommand extends BaseNotationElementCommand {

   protected final Optional<GPoint> shapePosition;
   protected final Optional<GDimension> shapeSize;
   protected final Supplier<? extends EObject> semanticElementSupplier;

   public AddShapeNotationCommand(final ModelContext context,
      final Supplier<? extends EObject> semanticElementSupplier) {
      super(context);
      this.semanticElementSupplier = semanticElementSupplier;
      this.shapePosition = Optional.empty();
      this.shapeSize = Optional.empty();
   }

   public AddShapeNotationCommand(final ModelContext context, final Supplier<? extends EObject> semanticElementSupplier,
      final GPoint position, final GDimension size) {
      super(context);
      this.semanticElementSupplier = semanticElementSupplier;
      this.shapePosition = Optional.of(position);
      this.shapeSize = Optional.of(size);
   }

   @Override
   protected void doExecute() {
      var newShape = NotationFactory.eINSTANCE.createShape();
      this.shapePosition.ifPresent(pos -> newShape.setPosition(pos));
      this.shapeSize.ifPresent(size -> newShape.setSize(size));

      var semanticReference = NotationFactory.eINSTANCE.createSemanticElementReference();
      semanticReference.setElementId(SemanticElementAccessor.getId(semanticElementSupplier.get()));

      newShape.setSemanticElement(semanticReference);

      diagram.getElements().add(newShape);
   }

}
