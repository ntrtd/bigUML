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
import { Action, generateRequestId, RequestAction, ResponseAction } from "@eclipse-glsp/client";

export class GetTypesAction implements RequestAction<ReturnTypesAction> {
    static readonly KIND = "getTypes";
    kind = GetTypesAction.KIND;
    constructor(public readonly requestId: string = generateRequestId()) { }
}

export class ReturnTypesAction implements ResponseAction {
    static readonly KIND = "returnTypes";
    kind = ReturnTypesAction.KIND;
    types: string[];
    constructor(public readonly actions: string[], public readonly responseId: string = "") {
        this.types = actions;
    }
}

export class GetBehaviorsAction implements RequestAction<CallBehaviorsAction> {
    static readonly KIND = "getBehaviors";
    kind = GetBehaviorsAction.KIND;
    constructor(public readonly requestId: string = generateRequestId()) { }
}

export class CallBehaviorsAction implements ResponseAction {
    static readonly KIND = "callBehaviors";
    kind = CallBehaviorsAction.KIND;
    constructor(public readonly behaviors: string[], public readonly responseId: string = "") {

    }
}

export class CreateGuardAction implements Action {
    static readonly KIND = "createGuard";
    kind = CreateGuardAction.KIND;
    constructor(public elementTypeId: string) { }
}

export class CreateWeightAction implements Action {
    static readonly KIND = "createWeight";
    kind = CreateWeightAction.KIND;
    constructor(public elementTypeId: string) { }
}
