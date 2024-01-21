/*********************************************************************************
 * Copyright (c) 2023 borkdominik and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the MIT License which is available at https://opensource.org/licenses/MIT.
 *
 * SPDX-License-Identifier: MIT
 *********************************************************************************/

import { UmlDiagramType } from '@borkdominik-biguml/uml-protocol';
import { configureModelElement } from '@eclipse-glsp/client';
import { DefaultTypes } from '@eclipse-glsp/protocol';
import { interfaces } from 'inversify';
import { QualifiedUtil } from '../../qualified.utils';
import { NamedElement } from '../index';
import { ActorView } from './actor.view';
import { StickFigureNode, StickFigureView } from './stick-figure.view';

export function registerActorElement(
    context: { bind: interfaces.Bind; isBound: interfaces.IsBound },
    representation: UmlDiagramType
): void {
    configureModelElement(context, QualifiedUtil.representationTypeId(representation, DefaultTypes.NODE, 'Actor'), NamedElement, ActorView);
    configureModelElement(
        context,
        QualifiedUtil.representationTemplateTypeId(representation, DefaultTypes.NODE, 'GModel', 'STICKFIGURE'),
        StickFigureNode,
        StickFigureView
    );
}
