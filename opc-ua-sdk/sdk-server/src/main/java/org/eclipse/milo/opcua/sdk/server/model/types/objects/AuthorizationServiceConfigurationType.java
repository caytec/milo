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
import org.eclipse.milo.opcua.sdk.server.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;

public interface AuthorizationServiceConfigurationType extends BaseObjectType {
    QualifiedProperty<String> SERVICE_URI = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ServiceUri",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12"),
        -1,
        String.class
    );

    QualifiedProperty<ByteString> SERVICE_CERTIFICATE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ServiceCertificate",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15"),
        -1,
        ByteString.class
    );

    QualifiedProperty<String> ISSUER_ENDPOINT_URL = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "IssuerEndpointUrl",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12"),
        -1,
        String.class
    );

    String getServiceUri();

    void setServiceUri(String value);

    PropertyType getServiceUriNode();

    ByteString getServiceCertificate();

    void setServiceCertificate(ByteString value);

    PropertyType getServiceCertificateNode();

    String getIssuerEndpointUrl();

    void setIssuerEndpointUrl(String value);

    PropertyType getIssuerEndpointUrlNode();
}
