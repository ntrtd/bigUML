/********************************************************************************
 * Copyright (c) 2021-2022 EclipseSource and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0, or the MIT License which is
 * available at https://opensource.org/licenses/MIT.
 *
 * SPDX-License-Identifier: EPL-2.0 OR MIT
 ********************************************************************************/
package com.eclipsesource.uml.glsp.uml.diagram.class_diagram.gmodel;

import java.util.stream.Collectors;

import org.eclipse.glsp.graph.GCompartment;
import org.eclipse.glsp.graph.GNode;
import org.eclipse.glsp.graph.builder.impl.GNodeBuilder;
import org.eclipse.glsp.graph.util.GConstants;
import org.eclipse.uml2.uml.Enumeration;

import com.eclipsesource.uml.glsp.core.constants.CoreCSS;
import com.eclipsesource.uml.glsp.uml.diagram.class_diagram.diagram.UmlClass_Enumeration;
import com.eclipsesource.uml.glsp.uml.gmodel.BaseGNodeMapper;
import com.eclipsesource.uml.glsp.uml.gmodel.element.NamedElementGBuilder;

public final class EnumerationNodeMapper extends BaseGNodeMapper<Enumeration, GNode>
   implements NamedElementGBuilder<Enumeration> {

   @Override
   public GNode map(final Enumeration source) {
      var builder = new GNodeBuilder(UmlClass_Enumeration.typeId())
         .id(idGenerator.getOrCreateId(source))
         .layout(GConstants.Layout.VBOX)
         .addCssClass(CoreCSS.NODE)
         .add(buildHeader(source))
         .add(buildLiterals(source));

      applyShapeNotation(source, builder);

      return builder.build();
   }

   protected GCompartment buildHeader(final Enumeration source) {
      var header = compartmentHeaderBuilder(source)
         .layout(GConstants.Layout.VBOX);

      header.add(textBuilder(source, "<<Enumeration>>").build());
      header.add(buildIconVisibilityName(source, "--uml-enumeration-icon"));

      return header.build();
   }

   protected GCompartment buildLiterals(final Enumeration source) {
      var compartment = fixedChildrenCompartmentBuilder(source);

      var literalElements = source.getOwnedLiterals().stream()
         .map(mapHandler::handle)
         .collect(Collectors.toList());
      compartment.addAll(literalElements);

      return compartment.build();

   }
}
