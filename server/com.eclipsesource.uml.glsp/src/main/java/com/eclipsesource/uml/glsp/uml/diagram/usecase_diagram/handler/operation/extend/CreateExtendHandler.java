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
package com.eclipsesource.uml.glsp.uml.diagram.usecase_diagram.handler.operation.extend;

import org.eclipse.emfcloud.modelserver.command.CCommand;
import org.eclipse.glsp.server.operations.CreateEdgeOperation;
import org.eclipse.uml2.uml.UseCase;

import com.eclipsesource.uml.glsp.uml.diagram.usecase_diagram.diagram.UmlUseCase_Extend;
import com.eclipsesource.uml.glsp.uml.handler.operations.create.BaseCreateEdgeHandler;
import com.eclipsesource.uml.modelserver.uml.diagram.usecase_diagram.commands.extend.CreateExtendContribution;

public class CreateExtendHandler extends BaseCreateEdgeHandler<UseCase, UseCase> {

   public CreateExtendHandler() {
      super(UmlUseCase_Extend.typeId());
   }

   @Override
   protected CCommand createCommand(final CreateEdgeOperation operation, final UseCase source,
      final UseCase target) {
      return CreateExtendContribution.create(source, target);
   }
}
