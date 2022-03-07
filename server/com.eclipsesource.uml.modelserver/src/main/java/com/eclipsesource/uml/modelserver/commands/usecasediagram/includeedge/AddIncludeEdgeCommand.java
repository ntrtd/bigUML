package com.eclipsesource.uml.modelserver.commands.usecasediagram.includeedge;

import com.eclipsesource.uml.modelserver.commands.commons.notation.UmlNotationElementCommand;
import com.eclipsesource.uml.modelserver.commands.util.UmlNotationCommandUtil;
import com.eclipsesource.uml.modelserver.unotation.Edge;
import com.eclipsesource.uml.modelserver.unotation.SemanticProxy;
import com.eclipsesource.uml.modelserver.unotation.UnotationFactory;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Include;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.Relationship;

import java.util.function.Supplier;

public class AddIncludeEdgeCommand extends UmlNotationElementCommand {

    protected String semanticProxyUri;
    protected Supplier<Include> includeSupplier;

    private AddIncludeEdgeCommand(final EditingDomain domain, final URI modelUri) {
        super(domain, modelUri);
        this.semanticProxyUri = null;
        this.includeSupplier = null;
    }

    public AddIncludeEdgeCommand(final EditingDomain domain, final URI modelUri, final String semanticProxyUri) {
        this(domain, modelUri);
        this.semanticProxyUri = semanticProxyUri;
    }

    public AddIncludeEdgeCommand(final EditingDomain domain, final URI modelUri,
                                 final Supplier<Include> includeSupplier) {
        this(domain, modelUri);
        this.includeSupplier = includeSupplier;
    }

    @Override
    protected void doExecute() {
        Edge newEdge = UnotationFactory.eINSTANCE.createEdge();

        SemanticProxy proxy = UnotationFactory.eINSTANCE.createSemanticProxy();

        if (this.semanticProxyUri != null) {
            proxy.setUri(this.semanticProxyUri);
        } else {
            proxy.setUri(UmlNotationCommandUtil.getSemanticProxyUri(includeSupplier.get()));
        }
        newEdge.setSemanticElement(proxy);
        umlDiagram.getElements().add(newEdge);
    }
}
