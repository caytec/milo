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
import org.eclipse.milo.opcua.sdk.server.model.types.variables.TwoStateVariableType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part9/5.6.2">https://reference.opcfoundation.org/v105/Core/docs/Part9/5.6.2</a>
 */
public interface DialogConditionType extends ConditionType {
    QualifiedProperty<LocalizedText> PROMPT = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "Prompt",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=21"),
        -1,
        LocalizedText.class
    );

    QualifiedProperty<LocalizedText[]> RESPONSE_OPTION_SET = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ResponseOptionSet",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=21"),
        1,
        LocalizedText[].class
    );

    QualifiedProperty<Integer> DEFAULT_RESPONSE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "DefaultResponse",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=6"),
        -1,
        Integer.class
    );

    QualifiedProperty<Integer> OK_RESPONSE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "OkResponse",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=6"),
        -1,
        Integer.class
    );

    QualifiedProperty<Integer> CANCEL_RESPONSE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "CancelResponse",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=6"),
        -1,
        Integer.class
    );

    QualifiedProperty<Integer> LAST_RESPONSE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "LastResponse",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=6"),
        -1,
        Integer.class
    );

    LocalizedText getPrompt();

    void setPrompt(LocalizedText value);

    PropertyType getPromptNode();

    LocalizedText[] getResponseOptionSet();

    void setResponseOptionSet(LocalizedText[] value);

    PropertyType getResponseOptionSetNode();

    Integer getDefaultResponse();

    void setDefaultResponse(Integer value);

    PropertyType getDefaultResponseNode();

    Integer getOkResponse();

    void setOkResponse(Integer value);

    PropertyType getOkResponseNode();

    Integer getCancelResponse();

    void setCancelResponse(Integer value);

    PropertyType getCancelResponseNode();

    Integer getLastResponse();

    void setLastResponse(Integer value);

    PropertyType getLastResponseNode();

    TwoStateVariableType getEnabledStateNode();

    LocalizedText getEnabledState();

    void setEnabledState(LocalizedText value);

    TwoStateVariableType getDialogStateNode();

    LocalizedText getDialogState();

    void setDialogState(LocalizedText value);

    MethodNode getRespondMethodNode();

    MethodNode getRespond2MethodNode();
}
