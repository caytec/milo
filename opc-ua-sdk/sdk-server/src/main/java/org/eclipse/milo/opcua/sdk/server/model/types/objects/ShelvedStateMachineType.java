/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.model.types.objects;

import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.nodes.MethodNode;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part9/5.8.16/#5.8.16.1">https://reference.opcfoundation.org/v105/Core/docs/Part9/5.8.16/#5.8.16.1</a>
 */
public interface ShelvedStateMachineType extends FiniteStateMachineType {
    QualifiedProperty<Double> UNSHELVE_TIME = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "UnshelveTime",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=290"),
        -1,
        Double.class
    );

    Double getUnshelveTime();

    void setUnshelveTime(Double value);

    PropertyType getUnshelveTimeNode();

    StateType getUnshelvedNode();

    StateType getTimedShelvedNode();

    StateType getOneShotShelvedNode();

    TransitionType getUnshelvedToTimedShelvedNode();

    TransitionType getUnshelvedToOneShotShelvedNode();

    TransitionType getTimedShelvedToUnshelvedNode();

    TransitionType getTimedShelvedToOneShotShelvedNode();

    TransitionType getOneShotShelvedToUnshelvedNode();

    TransitionType getOneShotShelvedToTimedShelvedNode();

    MethodNode getTimedShelveMethodNode();

    MethodNode getTimedShelve2MethodNode();

    MethodNode getUnshelveMethodNode();

    MethodNode getUnshelve2MethodNode();

    MethodNode getOneShotShelveMethodNode();

    MethodNode getOneShotShelve2MethodNode();
}
