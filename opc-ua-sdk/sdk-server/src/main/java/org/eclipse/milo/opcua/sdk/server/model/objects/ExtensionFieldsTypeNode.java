package org.eclipse.milo.opcua.sdk.server.model.objects;

import java.util.Optional;

import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.core.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.model.variables.PropertyTypeNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaMethodNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.AccessRestrictionType;
import org.eclipse.milo.opcua.stack.core.types.structured.RolePermissionType;

public class ExtensionFieldsTypeNode extends BaseObjectTypeNode implements ExtensionFieldsType {
    public ExtensionFieldsTypeNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
                                   LocalizedText displayName, LocalizedText description, UInteger writeMask,
                                   UInteger userWriteMask, RolePermissionType[] rolePermissions,
                                   RolePermissionType[] userRolePermissions, AccessRestrictionType accessRestrictions,
                                   UByte eventNotifier) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask, rolePermissions, userRolePermissions, accessRestrictions, eventNotifier);
    }

    public ExtensionFieldsTypeNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
                                   LocalizedText displayName, LocalizedText description, UInteger writeMask,
                                   UInteger userWriteMask, RolePermissionType[] rolePermissions,
                                   RolePermissionType[] userRolePermissions, AccessRestrictionType accessRestrictions) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask, rolePermissions, userRolePermissions, accessRestrictions);
    }

    @Override
    public PropertyTypeNode getExtensionFieldNamePlaceholderNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ExtensionFieldsType.EXTENSION_FIELD_NAME_PLACEHOLDER);
        return (PropertyTypeNode) propertyNode.orElse(null);
    }

    @Override
    public Object getExtensionFieldNamePlaceholder() {
        return getProperty(ExtensionFieldsType.EXTENSION_FIELD_NAME_PLACEHOLDER).orElse(null);
    }

    @Override
    public void setExtensionFieldNamePlaceholder(Object value) {
        setProperty(ExtensionFieldsType.EXTENSION_FIELD_NAME_PLACEHOLDER, value);
    }

    @Override
    public UaMethodNode getAddExtensionFieldMethodNode() {
        Optional<UaNode> methodNode = findNode("http://opcfoundation.org/UA/", "AddExtensionField", node -> node instanceof UaMethodNode, Reference.HAS_COMPONENT_PREDICATE);
        return (UaMethodNode) methodNode.orElse(null);
    }

    @Override
    public UaMethodNode getRemoveExtensionFieldMethodNode() {
        Optional<UaNode> methodNode = findNode("http://opcfoundation.org/UA/", "RemoveExtensionField", node -> node instanceof UaMethodNode, Reference.HAS_COMPONENT_PREDICATE);
        return (UaMethodNode) methodNode.orElse(null);
    }
}
