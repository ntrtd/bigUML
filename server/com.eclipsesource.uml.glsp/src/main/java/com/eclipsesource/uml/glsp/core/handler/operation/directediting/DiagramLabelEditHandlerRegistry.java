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
package com.eclipsesource.uml.glsp.core.handler.operation.directediting;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;

import com.eclipsesource.uml.glsp.core.common.DiagramMultiKeyRegistry;
import com.eclipsesource.uml.glsp.core.common.RepresentationKey;
import com.eclipsesource.uml.glsp.core.common.TripleKey;
import com.eclipsesource.uml.modelserver.unotation.Representation;
import com.google.inject.Inject;

public class DiagramLabelEditHandlerRegistry
   extends
   DiagramMultiKeyRegistry<TripleKey<Class<? extends EObject>, String, String>, DiagramLabelEditHandler<? extends EObject>> {

   @Inject
   public DiagramLabelEditHandlerRegistry(
      final Map<Representation, Set<DiagramLabelEditHandler<? extends EObject>>> handlers) {
      handlers.entrySet().forEach(e -> {
         var representation = e.getKey();

         e.getValue().forEach(handler -> {
            var elementType = handler.getElementType();
            var labelType = handler.getLabelType();
            var labelSuffix = handler.getLabelSuffix();
            register(RepresentationKey.of(representation, TripleKey.of(elementType, labelType, labelSuffix)), handler);
         });
      });

      // printContent();
   }

   @Override
   protected String deriveKey(final RepresentationKey<TripleKey<Class<? extends EObject>, String, String>> key) {
      var representation = key.representation;
      var tripleKey = key.key2;

      var clazz = tripleKey.key1;
      if (clazz.isInterface()) {
         return "[" + representation.getName() + "]\t\t" + clazz + "\t" + tripleKey.key2 + "\t" + tripleKey.key3;
      }

      var interfaces = List.of(clazz.getInterfaces());
      var dkeys = keys().stream().filter(k -> k.representation.equals(representation)).map(k -> k.key2)
         .collect(Collectors.toSet());

      var found = dkeys.stream().filter(k -> interfaces.contains(k.key1)).findFirst();
      if (found.isPresent()) {
         return "[" + representation.getName() + "]\t\t" + found.get().key1 + "\t" + tripleKey.key2 + "\t"
            + tripleKey.key3;
      }

      return super.deriveKey(key);
   }
}
