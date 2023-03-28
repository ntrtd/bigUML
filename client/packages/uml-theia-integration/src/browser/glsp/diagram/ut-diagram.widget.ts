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
import { EnableEditorPanelAction } from '@borkdominik-biguml/uml-glsp/lib/features/editor-panel';
import { DiagramServerProxy, ModelSource, TYPES } from '@eclipse-glsp/client';
import { GLSPDiagramWidget, GLSPTheiaDiagramServer } from '@eclipse-glsp/theia-integration';
import { pickBy } from 'lodash';

export class UTDiagramWidget extends GLSPDiagramWidget {
    protected override initializeSprotty(): void {
        const modelSource = this.diContainer.get<ModelSource>(TYPES.ModelSource);
        if (modelSource instanceof DiagramServerProxy) {
            modelSource.clientId = this.id;
        }
        if (modelSource instanceof GLSPTheiaDiagramServer) {
            this.connector.connect(modelSource);
        }

        this.disposed.connect(() => {
            if (modelSource instanceof GLSPTheiaDiagramServer) {
                this.connector.disconnect(modelSource);
            }
        });

        // Filter options to only contain defined primitive values
        const definedOptions: any = pickBy(this.options, v => v !== undefined && typeof v !== 'object');
        this.requestModelOptions = {
            sourceUri: this.uri.path.toString(),
            ...definedOptions
        };

        this.dispatchInitialActions();
    }

    protected override dispatchInitialActions(): void {
        super.dispatchInitialActions();

        this.actionDispatcher.dispatch(new EnableEditorPanelAction());
    }
}
