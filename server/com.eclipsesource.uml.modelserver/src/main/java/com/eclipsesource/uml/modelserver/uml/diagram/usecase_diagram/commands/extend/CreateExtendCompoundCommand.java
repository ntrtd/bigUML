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
package com.eclipsesource.uml.modelserver.uml.diagram.usecase_diagram.commands.extend;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.uml2.uml.UseCase;

import com.eclipsesource.uml.modelserver.shared.model.ModelContext;
import com.eclipsesource.uml.modelserver.shared.notation.commands.AddEdgeNotationCommand;

public class CreateExtendCompoundCommand extends CompoundCommand {

   public CreateExtendCompoundCommand(final ModelContext context,
      final UseCase source,
      final UseCase target) {

      var command = new CreateExtendSemanticCommand(context, source, target);
      this.append(command);
      this.append(new AddEdgeNotationCommand(context, command::getSemanticElement));

   }
}
