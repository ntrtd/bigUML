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
package com.eclipsesource.uml.modelserver.uml.diagram.class_diagram.commands.parameter;

import java.util.Optional;

import org.eclipse.uml2.uml.ParameterDirectionKind;
import org.eclipse.uml2.uml.ParameterEffectKind;
import org.eclipse.uml2.uml.ValueSpecification;
import org.eclipse.uml2.uml.VisibilityKind;

import com.eclipsesource.uml.modelserver.shared.codec.codecs.EmbeddedCodec;

public final class UpdateParameterArgument implements EmbeddedCodec.JsonEncodable {
   private String name;
   private String label;
   private Boolean isException;
   private Boolean isStream;
   private Boolean isOrdered;
   private Boolean isUnique;
   private Integer lowerBound;
   private Integer upperBound;
   private ParameterDirectionKind directionKind;
   private ParameterEffectKind effectKind;
   private VisibilityKind visibilityKind;

   // Reference
   private String typeId;
   private ValueSpecification defaultValue;

   public Optional<String> name() {
      return Optional.ofNullable(name);
   }

   public Optional<String> label() {
      return Optional.ofNullable(label);
   }

   public Optional<Boolean> isException() { return Optional.ofNullable(isException); }

   public Optional<Boolean> isStream() { return Optional.ofNullable(isStream); }

   public Optional<Boolean> isOrdered() { return Optional.ofNullable(isOrdered); }

   public Optional<Boolean> isUnique() { return Optional.ofNullable(isUnique); }

   public Optional<ParameterDirectionKind> directionKind() {
      return Optional.ofNullable(directionKind);
   }

   public Optional<ParameterEffectKind> effectKind() {
      return Optional.ofNullable(effectKind);
   }

   public Optional<VisibilityKind> visibilityKind() {
      return Optional.ofNullable(visibilityKind);
   }

   public Optional<String> typeId() {
      return Optional.ofNullable(typeId);
   }

   public Optional<Integer> lowerBound() {
      return Optional.ofNullable(lowerBound);
   }

   public Optional<Integer> upperBound() {
      return Optional.ofNullable(upperBound);
   }

   public Optional<ValueSpecification> defaultValue() {
      return Optional.ofNullable(defaultValue);
   }

   public static final class Builder {
      private final UpdateParameterArgument argument = new UpdateParameterArgument();

      public Builder name(final String value) {
         argument.name = value;
         return this;
      }

      public Builder label(final String value) {
         argument.label = value;
         return this;
      }

      public Builder isException(final boolean value) {
         argument.isException = value;
         return this;
      }

      public Builder isOrdered(final boolean value) {
         argument.isOrdered = value;
         return this;
      }

      public Builder isStream(final boolean value) {
         argument.isStream = value;
         return this;
      }

      public Builder isUnique(final boolean value) {
         argument.isUnique = value;
         return this;
      }

      public Builder typeId(final String value) {
         argument.typeId = value;
         return this;
      }

      public Builder effectKind(final ParameterEffectKind value) {
         argument.effectKind = value;
         return this;
      }

      public Builder directionKind(final ParameterDirectionKind value) {
         argument.directionKind = value;
         return this;
      }

      public Builder visibilityKind(final VisibilityKind value) {
         argument.visibilityKind = value;
         return this;
      }

      public Builder lowerBound(final int value) {
         argument.lowerBound = value;
         return this;
      }

      public Builder upperBound(final int value) {
         argument.upperBound = value;
         return this;
      }

      public Builder defaultValue(final ValueSpecification value) {
         argument.defaultValue = value;
         return this;
      }

      public UpdateParameterArgument get() {
         return argument;
      }
   }
}
