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
/** @jsx svg */
/* eslint-disable react/jsx-key */
import { injectable } from "inversify";
import { svg } from "snabbdom-jsx";
import { VNode } from "snabbdom/vnode";
import {
    RectangularNodeView,
    RenderingContext
} from "sprotty/lib";

import { LabeledNode } from "../model";

@injectable()
export class ActivityNodeView extends RectangularNodeView {
    render(node: LabeledNode, context: RenderingContext): VNode {
        return <g class-node={true} class-selected={node.selected} class-mouseover={node.hoverFeedback}>
            <defs>
                <filter id="dropShadow">
                    <feDropShadow dx="1.5" dy="1.5" stdDeviation="0.5" style-flood-color="var(--uml-drop-shadow)" style-flood-opacity="0.5" />
                </filter>
            </defs>

            <rect x={0} y={0} rx={10} ry={10} width={Math.max(0, node.bounds.width)} height={Math.max(0, node.bounds.height)} />
            {context.renderChildren(node)}
        </g>;
    }
}

@injectable()
export class ActionNodeView extends RectangularNodeView {
    render(node: LabeledNode,  context: RenderingContext): VNode {
        return <g class-node={true} class-selected={node.selected} class-mouseover={node.hoverFeedback}>
            <defs>
                <filter id="dropShadow">
                    <feDropShadow dx="1.5" dy="1.5" stdDeviation="0.5" style-flood-color="var(--uml-drop-shadow)" style-flood-opacity="0.5" />
                </filter>
            </defs>

            <rect x={0} y={0} rx={10} ry={10} width={Math.max(0, node.bounds.width)} height={Math.max(0, node.bounds.height)} />
            {context.renderChildren(node)}
        </g>;
    }
}
