/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.model.variables;

import org.eclipse.milo.opcua.stack.core.types.structured.SessionSecurityDiagnosticsDataType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part5/7.15">https://reference.opcfoundation.org/v105/Core/docs/Part5/7.15</a>
 */
public interface SessionSecurityDiagnosticsArrayType extends BaseDataVariableType {
    SessionSecurityDiagnosticsType getSessionSecurityDiagnosticsNode();

    SessionSecurityDiagnosticsDataType getSessionSecurityDiagnostics();

    void setSessionSecurityDiagnostics(SessionSecurityDiagnosticsDataType value);
}