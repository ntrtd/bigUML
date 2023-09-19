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
package com.eclipsesource.uml.modelserver.uml.elements.activity;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.glsp.graph.GPoint;
import org.eclipse.glsp.graph.util.GraphUtil;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.UMLFactory;

import com.eclipsesource.uml.modelserver.shared.codec.ContributionDecoder;
import com.eclipsesource.uml.modelserver.shared.model.ModelContext;
import com.eclipsesource.uml.modelserver.shared.notation.commands.AddShapeNotationCommand;
import com.eclipsesource.uml.modelserver.uml.command.provider.element.NodeCommandProvider;
import com.eclipsesource.uml.modelserver.uml.elements.activity.commands.UpdateActivityArgument;
import com.eclipsesource.uml.modelserver.uml.elements.activity.commands.UpdateActivitySemanticCommand;
import com.eclipsesource.uml.modelserver.uml.elements.packageable_element.AddPackagedElementCommand;

public class ActivityCommandProvider extends NodeCommandProvider<Activity, org.eclipse.uml2.uml.Package> {

   @Override
   protected Collection<Command> createModifications(final ModelContext context,
      final org.eclipse.uml2.uml.Package parent,
      final GPoint position) {
      var semantic = new AddPackagedElementCommand(context, parent, () -> UMLFactory.eINSTANCE.createActivity());
      var notation = new AddShapeNotationCommand(
         context, semantic::getSemanticElement, position, GraphUtil.dimension(160, 50));
      return List.of(semantic, notation);
   }

   @Override
   protected Collection<Command> updateModifications(final ModelContext context, final Activity element) {
      var decoder = new ContributionDecoder(context);
      var update = decoder.embedJson(UpdateActivityArgument.class);
      return List.of(new UpdateActivitySemanticCommand(context, element, update));
   }
}
