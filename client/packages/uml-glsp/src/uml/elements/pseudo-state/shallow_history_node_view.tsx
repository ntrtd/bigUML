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
import { CircularNode, CircularNodeView, RenderingContext, svg } from '@eclipse-glsp/client';
import { injectable } from 'inversify';
import { VNode } from 'snabbdom';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
const JSX = { createElement: svg };

@injectable()
export class ShallowHistoryNodeView extends CircularNodeView {
    override render(node: CircularNode, context: RenderingContext): VNode {
        const radius = this.getRadius(node);
        const shallowHistoryNode: any = (
            <g>
                <circle
                    stroke='#4E81B4'
                    class-node={true}
                    class-mouseover={node.hoverFeedback}
                    class-selected={node.selected}
                    r={radius}
                    cx={radius}
                    cy={radius}
                />
                <line x1="0" y1={-(radius/2)} x2="0" y2={radius / 2} transform={`translate(${radius / 2} ${radius})`} fill="none" stroke="#ffffff" stroke-width="0.125em"/>
                <line x1="0" y1={-(radius/2)} x2="0" y2={radius / 2} transform={`translate(${radius / 2 + radius} ${radius})`} fill="none" stroke="#ffffff" stroke-width="0.125em"/>
                <line x1="0" y1="0" x2={radius} y2="0" transform={`translate(${radius / 2} ${radius})`} fill="none" stroke="#ffffff" stroke-width="0.125em"/>
                {context.renderChildren(node)}
            </g>
        );
        return shallowHistoryNode;
    }
}
