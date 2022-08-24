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
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public interface CertificateGroupType extends BaseObjectType {
    QualifiedProperty<NodeId[]> CERTIFICATE_TYPES = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "CertificateTypes",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=17"),
        1,
        NodeId[].class
    );

    NodeId[] getCertificateTypes();

    void setCertificateTypes(NodeId[] value);

    PropertyType getCertificateTypesNode();

    TrustListType getTrustListNode();

    MethodNode getGetRejectedListMethodNode();

    CertificateExpirationAlarmType getCertificateExpiredNode();

    TrustListOutOfDateAlarmType getTrustListOutOfDateNode();
}
