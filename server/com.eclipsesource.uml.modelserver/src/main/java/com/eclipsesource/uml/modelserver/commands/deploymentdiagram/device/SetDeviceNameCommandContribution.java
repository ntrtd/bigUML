package com.eclipsesource.uml.modelserver.commands.deploymentdiagram.device;

import com.eclipsesource.uml.modelserver.commands.commons.commands.SetNameCommand;
import com.eclipsesource.uml.modelserver.commands.commons.contributions.UmlSemanticCommandContribution;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emfcloud.modelserver.command.CCommand;
import org.eclipse.emfcloud.modelserver.command.CCommandFactory;
import org.eclipse.emfcloud.modelserver.common.codecs.DecodingException;

public class SetDeviceNameCommandContribution extends UmlSemanticCommandContribution {

    public static final String TYPE = "setDeviceName";
    public static final String NEW_NAME = "newName";

    public static CCommand create(final String semanticUri, final String newName) {
        CCommand setDeviceNameCommand = CCommandFactory.eINSTANCE.createCommand();
        setDeviceNameCommand.setType(TYPE);
        setDeviceNameCommand.getProperties().put(SEMANTIC_URI_FRAGMENT, semanticUri);
        setDeviceNameCommand.getProperties().put(NEW_NAME, newName);
        return setDeviceNameCommand;
    }

    @Override
    protected Command toServer(final URI modelUri, final EditingDomain domain, final CCommand command)
            throws DecodingException {

        String semanticUriFragment = command.getProperties().get(SEMANTIC_URI_FRAGMENT);
        String newName = command.getProperties().get(NEW_NAME);

        return new SetNameCommand(domain, modelUri, semanticUriFragment, newName);
    }
}
