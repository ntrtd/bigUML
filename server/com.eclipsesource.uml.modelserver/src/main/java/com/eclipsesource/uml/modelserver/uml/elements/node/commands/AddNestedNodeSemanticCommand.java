/********************************************************************************
 * Copyright (c) 2023 EclipseSource and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0, or the MIT License which is
 * available at https://opensource.org/licenses/MIT.
 *
 * SPDX-License-Identifier: EPL-2.0 OR MIT
 ********************************************************************************/
package com.eclipsesource.uml.modelserver.uml.elements.node.commands;

import java.util.function.Function;

import org.eclipse.uml2.uml.Node;

import com.eclipsesource.uml.modelserver.shared.model.ModelContext;
import com.eclipsesource.uml.modelserver.shared.semantic.BaseCreateSemanticChildCommand;
import com.eclipsesource.uml.modelserver.uml.generator.ListNameGenerator;

public class AddNestedNodeSemanticCommand
   extends BaseCreateSemanticChildCommand<Node, Node> {

   protected final Function<Node, Node> supplier;

   public AddNestedNodeSemanticCommand(final ModelContext context,
      final Node parent, final Function<Node, Node> supplier) {
      super(context, parent);
      this.supplier = supplier;
   }

   @Override
   protected Node createSemanticElement(final Node parent) {
      var element = supplier.apply(parent);
      var nameGenerator = new ListNameGenerator(element.getClass(), parent.allNamespaces());
      element.setName(nameGenerator.newName());
      parent.getNestedNodes().add(element);
      return element;
   }

}
