package com.eclipsesource.uml.modelserver.commands.deploymentdiagram.executionenvironment;

import com.eclipsesource.uml.modelserver.commands.commons.contributions.UmlCompoundCommandContribution;
import com.eclipsesource.uml.modelserver.commands.commons.contributions.UmlSemanticCommandContribution;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emfcloud.modelserver.command.CCommand;
import org.eclipse.emfcloud.modelserver.command.CCommandFactory;
import org.eclipse.emfcloud.modelserver.command.CCompoundCommand;
import org.eclipse.emfcloud.modelserver.common.codecs.DecodingException;

public class RemoveExecutionEnvironmentCommandContribution extends UmlCompoundCommandContribution {

    public static final String TYPE = "removeExecutionEnvironment";

    public static CCompoundCommand create(final String semanticUri, final String parentSemanticUri) {
        CCompoundCommand removeExecutionEnvironmentCommand = CCommandFactory.eINSTANCE.createCompoundCommand();
        removeExecutionEnvironmentCommand.setType(TYPE);
        removeExecutionEnvironmentCommand.getProperties().put(SEMANTIC_URI_FRAGMENT, semanticUri);
        removeExecutionEnvironmentCommand.getProperties().put(UmlSemanticCommandContribution.PARENT_SEMANTIC_URI_FRAGMENT,
                parentSemanticUri);
        return removeExecutionEnvironmentCommand;
    }

    @Override
    protected CompoundCommand toServer(final URI modelUri, final EditingDomain domain, final CCommand command)
            throws DecodingException {
        String semanticUriFragment = command.getProperties().get(SEMANTIC_URI_FRAGMENT);
        String parentSemanticUriFragment = command.getProperties()
                .get(UmlSemanticCommandContribution.PARENT_SEMANTIC_URI_FRAGMENT);

        return new RemoveExecutionEnvironmentCompoundCommand(domain, modelUri, semanticUriFragment,
                parentSemanticUriFragment);
    }
}
