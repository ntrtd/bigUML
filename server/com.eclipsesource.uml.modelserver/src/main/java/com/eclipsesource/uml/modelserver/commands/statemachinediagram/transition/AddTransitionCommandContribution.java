package com.eclipsesource.uml.modelserver.commands.statemachinediagram.transition;

import com.eclipsesource.uml.modelserver.commands.commons.contributions.UmlCompoundCommandContribution;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emfcloud.modelserver.command.CCommand;
import org.eclipse.emfcloud.modelserver.command.CCommandFactory;
import org.eclipse.emfcloud.modelserver.command.CCompoundCommand;
import org.eclipse.emfcloud.modelserver.common.codecs.DecodingException;

public class AddTransitionCommandContribution extends UmlCompoundCommandContribution {

    public static final String TYPE = "addTransitionContributuion";
    public static final String PARENT_SEMANTIC_URI_FRAGMENT = "parentSemanticUriFragment";
    private static final String SOURCE_URI = "sourceUri";
    private static final String TARGET_URI = "targetUri";

    public static CCompoundCommand create(final String containerRegionUriFragment, final String sourceUri,
                                          final String targetUri) {
        CCompoundCommand addTransitionCommand = CCommandFactory.eINSTANCE.createCompoundCommand();
        addTransitionCommand.setType(TYPE);
        addTransitionCommand.getProperties().put(SOURCE_URI, sourceUri);
        addTransitionCommand.getProperties().put(TARGET_URI, targetUri);
        addTransitionCommand.getProperties().put(PARENT_SEMANTIC_URI_FRAGMENT, containerRegionUriFragment);

        return addTransitionCommand;
    }

    @Override
    protected CompoundCommand toServer(final URI modelUri, final EditingDomain domain, final CCommand command)
            throws DecodingException {
        String sourceUri = command.getProperties().get(SOURCE_URI);
        String targetUri = command.getProperties().get(TARGET_URI);
        String containerRegionUriFragment = command.getProperties().get(PARENT_SEMANTIC_URI_FRAGMENT);
        return new AddTransitionCompoundCommand(domain, modelUri, containerRegionUriFragment, sourceUri, targetUri);
    }
}
