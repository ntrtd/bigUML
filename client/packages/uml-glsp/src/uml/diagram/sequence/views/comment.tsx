/********************************************************************************
 * Copyright (c) 2021-2022 EclipseSource and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0, or the MIT License which is
 * available at https://opensource.org/licenses/MIT.
 *
 * SPDX-License-Identifier: EPL-2.0 OR MIT
 ********************************************************************************/
import { RectangularNodeView, RenderingContext, svg } from '@eclipse-glsp/client';
import { injectable } from 'inversify';
import { VNode } from 'snabbdom';

import { NamedElement } from '../../../elements';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
const JSX = { createElement: svg };

@injectable()
export class CommentNodeView extends RectangularNodeView {
    override render(element: NamedElement, context: RenderingContext): VNode {
        const cornerDim = 10;

        const commentNode: any = (
            <g class-selected={element.selected} class-mouseover={element.hoverFeedback}>
                <path
                    d={`M 0 0 H ${element.bounds.width - cornerDim} L ${element.bounds.width} ${cornerDim} 
                M ${element.bounds.width - cornerDim} 0 V ${cornerDim} H ${element.bounds.width} V ${element.bounds.height} H 0 L 0 0`}
                />
            </g>
        );

        return commentNode;
    }
}
