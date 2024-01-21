/********************************************************************************
 * Copyright (c) 2024 EclipseSource and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0, or the MIT License which is
 * available at https://opensource.org/licenses/MIT.
 *
 * SPDX-License-Identifier: EPL-2.0 OR MIT
 ********************************************************************************/
package com.eclipsesource.uml.glsp.uml.elements.attribute_owner;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.glsp.graph.GModelElement;
import org.eclipse.uml2.uml.AttributeOwner;
import org.eclipse.uml2.uml.Property;

import com.eclipsesource.uml.glsp.sdk.cdk.GModelContext;
import com.eclipsesource.uml.glsp.sdk.cdk.base.GCBaseObject;
import com.eclipsesource.uml.glsp.sdk.cdk.base.GCBuildable;
import com.eclipsesource.uml.glsp.sdk.cdk.base.GCBuildableCollectionProvider;
import com.eclipsesource.uml.glsp.sdk.ui.components.list.GCListItem;

public class GCAttributeOwner<TSource extends EObject & AttributeOwner> extends GCBaseObject<TSource>
   implements GCBuildableCollectionProvider<GModelElement> {

   public GCAttributeOwner(final GModelContext context, final TSource source) {
      super(context, source);
   }

   public List<Property> attributes() {
      return origin.getOwnedAttributes().stream()
         .filter(p -> p.getAssociation() == null)
         .collect(Collectors.toList());
   }

   @Override
   public boolean isVisible() { return attributes().size() > 0; }

   @Override
   public Collection<? extends GCBuildable<GModelElement>> provideAll() {
      var attributes = attributes();

      if (attributes.size() > 0) {
         return attributes.stream()
            .map(e -> context.gmodelMapHandler().handle(e))
            .map(e -> new GCListItem(context, origin, e))
            .collect(Collectors.toList());
      }

      return List.of();
   }
}
