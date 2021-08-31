package com.eclipsesource.uml.glsp.operations.activitydiagram;

import static org.eclipse.glsp.server.protocol.GLSPServerException.getOrThrow;

import com.eclipsesource.uml.glsp.model.UmlModelState;
import com.eclipsesource.uml.glsp.modelserver.UmlModelServerAccess;
import com.eclipsesource.uml.glsp.util.UmlConfig.Types;
import com.eclipsesource.uml.modelserver.unotation.Shape;

import com.eclipsesource.uml.glsp.operations.ModelServerAwareBasicCreateOperationHandler;
import com.google.common.collect.Lists;
import org.eclipse.glsp.graph.GPoint;
import org.eclipse.glsp.graph.util.GraphUtil;
import org.eclipse.glsp.server.model.GModelState;
import org.eclipse.glsp.server.operations.CreateNodeOperation;
import org.eclipse.glsp.server.operations.Operation;
import org.eclipse.glsp.server.protocol.GLSPServerException;
import org.eclipse.uml2.uml.*;

import java.lang.Class;
import java.util.List;

public class CreateActivityNodeOperationHandler extends ModelServerAwareBasicCreateOperationHandler<CreateNodeOperation> {

    public CreateActivityNodeOperationHandler() {
        super(handledElementTypeIds);
    }

    //TODO: Add types: e.g. TIMEEVENT, PARAMETER, CONDITION
    private static List<String> handledElementTypeIds = Lists.newArrayList(Types.ACTION);

    @Override
    public boolean handles(final Operation operation) {
        if (operation instanceof CreateNodeOperation) {
            CreateNodeOperation action = (CreateNodeOperation) operation;
            return handledElementTypeIds.contains(action.getElementTypeId());
        }
        return false;
    }

    @Override
    public void executeOperation(final CreateNodeOperation operation, final GModelState modelState, final UmlModelServerAccess modelAccess) throws Exception {
        UmlModelState umlModelState = UmlModelState.getModelState(modelState);
        String containerId = operation.getContainerId();
        Element container = getOrThrow(umlModelState.getIndex().getSemantic(containerId), Element.class,
                "No valid activity container with id " + containerId + " found!");
        String elementTypeId = operation.getElementTypeId();
        GPoint location = getPosition(umlModelState, container,operation.getLocation().orElse(GraphUtil.point(0,0)));

        if (Types.ACTION.contains(elementTypeId)) {
            Class<? extends Action> clazz = null;
            if (Types.ACTION.equals(elementTypeId)) {
                clazz = OpaqueAction.class;
            }
            modelAccess
                    .addAction(UmlModelState.getModelState(modelState), location, container, clazz)
                    .thenAccept(res -> {
                        if (!res.body()) {
                            throw new GLSPServerException("Could not execute create operation on new Action node");
                        }
                    });
        }
    }

    private GPoint getPosition(final UmlModelState modelState, final Element container, final GPoint position) {
        Shape containerShape = modelState.getIndex().getNotation(container, Shape.class).get();

        double x = position.getX();
        double y = position.getY();
        x = Math.max(0, x - containerShape.getPosition().getX());
        y = Math.max(0, y - containerShape.getPosition().getY() - 43);
        GPoint location = GraphUtil.point(x, y);

        if (container instanceof Activity) {
            return GraphUtil.point(x - 16, y - 8);
        } else if (container instanceof ActivityPartition) {
            return getPosition(modelState, container.getOwner(), location);
        }

        return GraphUtil.point(0, 0);
    }

    @Override
    public String getLabel() { return "Create uml activity"; }

}
