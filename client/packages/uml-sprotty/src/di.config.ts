/********************************************************************************
 * Copyright (c) 2021 EclipseSource and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0, or the MIT License which is
 * available at https://opensource.org/licenses/MIT.
 *
 * SPDX-License-Identifier: EPL-2.0 OR MIT
 ********************************************************************************/
import "@eclipse-glsp/client/css/glsp-sprotty.css";
import "sprotty/css/edit-label.css";

import {
    configureDefaultModelElements,
    configureModelElement,
    configureViewerOptions,
    ConsoleLogger,
    copyPasteContextMenuModule,
    createClientContainer,
    LogLevel,
    overrideViewerOptions,
    PolylineEdgeView,
    RectangularNode,
    saveModule,
    SCompartment,
    SCompartmentView,
    SEdge,
    SLabel,
    SLabelView,
    SRoutingHandle,
    SRoutingHandleView,
    TYPES
} from "@eclipse-glsp/client/lib";
import toolPaletteModule from "@eclipse-glsp/client/lib/features/tool-palette/di.config";
import { Container, ContainerModule } from "inversify";
import { DiamondNodeView, EditLabelUI } from "sprotty/lib";

import { EditLabelUIAutocomplete } from "./features/edit-label";
import umlToolPaletteModule from "./features/tool-palette/di.config";
import { LabelSelectionFeedback } from "./feedback";
import {
    // ConnectableEdge,
    ConnectableEditableLabel,
    ConnectionPoint,
    ControlNode,
    IconAction,
    IconActivity,
    IconActor,
    IconArtifact,
    IconClass,
    IconDeploymentNode,
    IconDeploymentSpecification,
    IconDevice,
    IconExecutionEnvironment, IconObject,
    IconPackage,
    IconState,
    IconStateMachine,
    IconUseCase,
    LabeledNode,
    SEditableLabel,
    SLabelNodeProperty
} from "./model";
import { BaseTypes, UmlTypes } from "./utils";
import {
    AcceptEventNodeView,
    AcceptTimeEventNodeView,
    ActionNodeView,
    ActivityNodeView,
    CallNodeView,
    ExceptionHandlerEdgeView,
    FinalNodeView,
    FlowEdgeView,
    FlowFinalNodeView,
    ForkOrJoinNodeView,
    InitialNodeView,
    InterruptibleRegionNodeView,
    ObjectNodeView,
    ParameterNodeView,
    PartitionNodeView,
    PinNodeView,
    PinPortView,
    SendSignalNodeView,
    ConditionNodeView
} from "./views/activitydiagram";
import {
    ClassNodeView
} from "./views/classdiagram";
import {
    ActorNodeView,
    DirectedEdgeView,
    PackageNodeView,
    UseCaseNodeView
} from "./views/usecasediagram";
import {
    IconView,
    LabelNodeView,
    CommentLinkEdgeView,
    CommentNodeView
} from "./views/commons";
import {
    FinalStateNodeView, StateActivityNodeView,
    StateMachineNodeView,
    StateNodeView,
    TransitionEdgeView,
    TransitionEffectView,
    TransitionGuardView,
    InitialStateNodeView,
    DeepHistoryNodeView,
    ShallowHistoryNodeView,
    ForkNodeView,
    JoinNodeView,
    JunctionNodeView,
    ChoiceNodeView,
    EntryPointNodeView,
    ExitPointNodeView
} from "./views/statemachinediagram";

import {
    ArtifactNodeView,
    DeploymentNodeNodeView,
    DeviceNodeView,
    ExecutionEnvironmentNodeView,
    DeploymentSpecificationNodeView,
    // DeploymentEdgeView
} from "./views/deploymentdiagram";

export default function createContainer(widgetId: string): Container {
    const classDiagramModule = new ContainerModule((bind, unbind, isBound, rebind) => {
        rebind(TYPES.ILogger).to(ConsoleLogger).inSingletonScope();
        rebind(TYPES.LogLevel).toConstantValue(LogLevel.info);
        rebind(EditLabelUI).to(EditLabelUIAutocomplete);
        // bind(TYPES.IContextMenuItemProvider).to(DeleteElementContextMenuItemProvider);

        // TODO: Add this later again when reworking the context menu
        /* bind(TYPES.IContextMenuItemProvider).to(RemoveGuardElementContextMenuItemProvider);
        bind(TYPES.IContextMenuItemProvider).to(RemoveWeightElementContextMenuItemProvider);
        bind(TYPES.IContextMenuItemProvider).to(CreateGuardElementContextMenuItemProvider);
        bind(TYPES.IContextMenuItemProvider).to(CreateWeightElementContextMenuItemProvider);*/

        const context = { bind, unbind, isBound, rebind };
        bind(TYPES.IVNodePostprocessor).to(LabelSelectionFeedback);
        // configureModelElement(context, BaseTypes.GRAPH, GLSPGraph, SGraphView);
        // configureModelElement(context, BaseTypes.HTML, HtmlRoot, HtmlRootView);
        configureDefaultModelElements(context);
        configureModelElement(context, UmlTypes.LABEL_NAME, SEditableLabel, SLabelView);
        configureModelElement(context, UmlTypes.LABEL_EDGE_NAME, SEditableLabel, SLabelView);
        configureModelElement(context, UmlTypes.LABEL_EDGE_MULTIPLICITY, SEditableLabel, SLabelView);
        configureModelElement(context, UmlTypes.LABEL_TEXT, SLabel, SLabelView);
        configureModelElement(context, UmlTypes.LABEL_ICON, SLabel, SLabelView);
        configureModelElement(context, BaseTypes.COMPARTMENT, SCompartment, SCompartmentView);
        configureModelElement(context, BaseTypes.COMPARTMENT_HEADER, SCompartment, SCompartmentView);
        configureModelElement(context, BaseTypes.ROUTING_POINT, SRoutingHandle, SRoutingHandleView);
        configureModelElement(context, BaseTypes.VOLATILE_ROUTING_POINT, SRoutingHandle, SRoutingHandleView);

        // CLASS DIAGRAM
        configureModelElement(context, UmlTypes.ICON_CLASS, IconClass, IconView);
        configureModelElement(context, UmlTypes.CLASS, LabeledNode, ClassNodeView);
        configureModelElement(context, UmlTypes.ASSOCIATION, SEdge, PolylineEdgeView);
        configureModelElement(context, UmlTypes.PROPERTY, SLabelNodeProperty, LabelNodeView);

        // OBJECT DIAGRAM
        configureModelElement(context, UmlTypes.ICON_OBJECT, IconObject, IconView);
        configureModelElement(context, UmlTypes.OBJECT, LabeledNode, ObjectNodeView);
        configureModelElement(context, UmlTypes.LINK, SEdge, PolylineEdgeView);
        configureModelElement(context, UmlTypes.ATTRIBUTE, SLabelNodeProperty, LabelNodeView);

        // ACTIVITY DIAGRAM
        configureModelElement(context, UmlTypes.ACTIVITY, LabeledNode, ActivityNodeView);
        configureModelElement(context, UmlTypes.ICON_ACTIVITY, IconActivity, IconView);
        configureModelElement(context, UmlTypes.PARTITION, LabeledNode, PartitionNodeView);
        configureModelElement(context, UmlTypes.ACTION, LabeledNode, ActionNodeView);
        configureModelElement(context, UmlTypes.ICON_ACTION, IconAction, IconView);
        configureModelElement(context, UmlTypes.ACCEPTEVENT, LabeledNode, AcceptEventNodeView);
        configureModelElement(context, UmlTypes.TIMEEVENT, LabeledNode, AcceptTimeEventNodeView);
        configureModelElement(context, UmlTypes.SENDSIGNAL, LabeledNode, SendSignalNodeView);
        configureModelElement(context, UmlTypes.CALL, LabeledNode, CallNodeView);
        configureModelElement(context, UmlTypes.CALL_REF, SEditableLabel, SLabelView);
        configureModelElement(context, UmlTypes.CONTROLFLOW, SEdge, FlowEdgeView);
        configureModelElement(context, UmlTypes.INITIALNODE, ControlNode, InitialNodeView);
        configureModelElement(context, UmlTypes.FINALNODE, ControlNode, FinalNodeView);
        configureModelElement(context, UmlTypes.FLOWFINALNODE, ControlNode, FlowFinalNodeView);
        configureModelElement(context, UmlTypes.DECISIONMERGENODE, ControlNode, DiamondNodeView);
        configureModelElement(context, UmlTypes.FORKJOINNODE, ControlNode, ForkOrJoinNodeView);
        configureModelElement(context, UmlTypes.PARAMETER, LabeledNode, ParameterNodeView);
        configureModelElement(context, UmlTypes.PIN, LabeledNode, PinNodeView);
        configureModelElement(context, UmlTypes.PIN_PORT, ControlNode, PinPortView);
        configureModelElement(context, UmlTypes.CENTRALBUFFER, LabeledNode, ObjectNodeView);
        configureModelElement(context, UmlTypes.DATASTORE, LabeledNode, ObjectNodeView);
        configureModelElement(context, UmlTypes.COMMENT, LabeledNode, CommentNodeView);
        configureModelElement(context, UmlTypes.COMMENT_LINK, SEdge, CommentLinkEdgeView);
        configureModelElement(context, UmlTypes.LABEL_FLOW_GUARD, SEditableLabel, SLabelView);
        configureModelElement(context, UmlTypes.LABEL_FLOW_WEIGHT, SEditableLabel, SLabelView);
        configureModelElement(context, UmlTypes.EXCEPTIONHANDLER, SEdge, ExceptionHandlerEdgeView);
        configureModelElement(context, UmlTypes.INTERRUPTIBLEREGION, LabeledNode, InterruptibleRegionNodeView);
        configureModelElement(context, UmlTypes.CONDITION, LabeledNode, ConditionNodeView);

        // USECASE DIAGRAM
        configureModelElement(context, UmlTypes.ICON_USECASE, IconUseCase, IconView);
        configureModelElement(context, UmlTypes.USECASE, LabeledNode, UseCaseNodeView);
        configureModelElement(context, UmlTypes.ICON_ACTOR, IconActor, IconView);
        configureModelElement(context, UmlTypes.ACTOR, LabeledNode, ActorNodeView);
        configureModelElement(context, UmlTypes.ICON_PACKAGE, IconPackage, IconView);
        configureModelElement(context, UmlTypes.PACKAGE, LabeledNode, PackageNodeView);
        configureModelElement(context, UmlTypes.COMPONENT, LabeledNode, PackageNodeView);
        configureModelElement(context, UmlTypes.EXTENSIONPOINT, ConnectableEditableLabel, SLabelView);
        configureModelElement(context, UmlTypes.EXTEND, SEdge, DirectedEdgeView);
        configureModelElement(context, UmlTypes.INCLUDE, SEdge, DirectedEdgeView);
        configureModelElement(context, UmlTypes.GENERALIZATION, SEdge, FlowEdgeView);
        configureModelElement(context, UmlTypes.CONNECTIONPOINT, ConnectionPoint, SLabelView);

        // DEPLOYMENT DIAGRAM
        configureModelElement(context, UmlTypes.ICON_ARTIFACT, IconArtifact, IconView);
        configureModelElement(context, UmlTypes.ARTIFACT, LabeledNode, ArtifactNodeView);
        configureModelElement(context, UmlTypes.ICON_DEVICE, IconDevice, IconView);
        configureModelElement(context, UmlTypes.DEVICE, LabeledNode, DeviceNodeView);
        configureModelElement(context, UmlTypes.EXECUTION_ENVIRONMENT, LabeledNode, ExecutionEnvironmentNodeView);
        configureModelElement(context, UmlTypes.ICON_EXECUTION_ENVIRONMENT, IconExecutionEnvironment, IconView);
        configureModelElement(context, UmlTypes.DEPLOYMENT_NODE, LabeledNode, DeploymentNodeNodeView);
        configureModelElement(context, UmlTypes.ICON_DEPLOYMENT_NODE, IconDeploymentNode, IconView);
        configureModelElement(context, UmlTypes.DEPLOYMENT_SPECIFICATION, LabeledNode, DeploymentSpecificationNodeView);
        configureModelElement(context, UmlTypes.ICON_DEPLOYMENT_SPECIFICATION, IconDeploymentSpecification, IconView);
        configureModelElement(context, UmlTypes.COMMUNICATION_PATH, SEdge, PolylineEdgeView);
        configureModelElement(context, UmlTypes.DEPLOYMENT, SEdge, DirectedEdgeView);

        // STATEMACHINE DIAGRAM
        configureModelElement(context, UmlTypes.ICON_STATE_MACHINE, IconStateMachine, IconView);
        configureModelElement(context, UmlTypes.STATE_MACHINE, LabeledNode, StateMachineNodeView);
        configureModelElement(context, UmlTypes.ICON_STATE, IconState, IconView);
        configureModelElement(context, UmlTypes.STATE, LabeledNode, StateNodeView);
        configureModelElement(context, UmlTypes.LABEL_VERTEX_NAME, SEditableLabel, SLabelView);
        configureModelElement(context, UmlTypes.INITIAL_STATE, LabeledNode, InitialStateNodeView);
        configureModelElement(context, UmlTypes.DEEP_HISTORY, LabeledNode, DeepHistoryNodeView);
        configureModelElement(context, UmlTypes.SHALLOW_HISTORY, LabeledNode, ShallowHistoryNodeView);
        configureModelElement(context, UmlTypes.JOIN, LabeledNode, JoinNodeView);
        configureModelElement(context, UmlTypes.FORK, RectangularNode, ForkNodeView);
        configureModelElement(context, UmlTypes.JUNCTION, RectangularNode, JunctionNodeView);
        configureModelElement(context, UmlTypes.CHOICE, LabeledNode, ChoiceNodeView);
        configureModelElement(context, UmlTypes.FINAL_STATE, LabeledNode, FinalStateNodeView);
        configureModelElement(context, UmlTypes.TRANSITION, SEdge, TransitionEdgeView);
        configureModelElement(context, UmlTypes.LABEL_TRANSITION_NAME, SEditableLabel, SLabelView);
        configureModelElement(context, UmlTypes.LABEL_TRANSITION_GUARD, SEditableLabel, TransitionGuardView);
        configureModelElement(context, UmlTypes.LABEL_TRANSITION_EFFECT, SEditableLabel, TransitionEffectView);
        configureModelElement(context, UmlTypes.LABEL_TRANSITION_TRIGGER, SEditableLabel, SLabelView);
        configureModelElement(context, UmlTypes.ENTRY_POINT, LabeledNode, EntryPointNodeView);
        configureModelElement(context, UmlTypes.EXIT_POINT, LabeledNode, ExitPointNodeView);
        configureModelElement(context, UmlTypes.STATE_ENTRY_ACTIVITY, SLabelNodeProperty, StateActivityNodeView);
        configureModelElement(context, UmlTypes.STATE_DO_ACTIVITY, SLabelNodeProperty, StateActivityNodeView);
        configureModelElement(context, UmlTypes.STATE_EXIT_ACTIVITY, SLabelNodeProperty, StateActivityNodeView);

        configureViewerOptions(context, {
            needsClientLayout: true,
            baseDiv: widgetId
        });
    });

    const container = createClientContainer(classDiagramModule, umlToolPaletteModule, saveModule, copyPasteContextMenuModule);
    container.unload(toolPaletteModule);
    overrideViewerOptions(container, {
        baseDiv: widgetId,
        hiddenDiv: widgetId + "_hidden"
    });

    return container;

}
