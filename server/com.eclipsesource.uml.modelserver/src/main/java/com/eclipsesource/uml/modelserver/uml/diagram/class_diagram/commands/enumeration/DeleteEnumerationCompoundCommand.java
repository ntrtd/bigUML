package com.eclipsesource.uml.modelserver.uml.diagram.class_diagram.commands.enumeration;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.uml2.uml.Enumeration;

import com.eclipsesource.uml.modelserver.shared.model.ModelContext;
import com.eclipsesource.uml.modelserver.shared.notation.commands.DeleteNotationElementCommand;
import com.eclipsesource.uml.modelserver.uml.diagram.class_diagram.matcher.ClassDiagramCrossReferenceRemover;

public final class DeleteEnumerationCompoundCommand extends CompoundCommand {

   public DeleteEnumerationCompoundCommand(final ModelContext context, final Enumeration semanticElement) {
      this.append(new DeleteEnumerationSemanticCommand(context, semanticElement));
      this.append(new DeleteNotationElementCommand(context, semanticElement));

      new ClassDiagramCrossReferenceRemover(context).deleteCommandsFor(semanticElement)
         .forEach(this::append);
   }
}
