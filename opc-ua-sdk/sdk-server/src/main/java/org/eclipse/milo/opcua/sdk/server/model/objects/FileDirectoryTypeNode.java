package org.eclipse.milo.opcua.sdk.server.model.objects;

import java.util.Optional;

import org.eclipse.milo.opcua.sdk.core.Reference;
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

public class FileDirectoryTypeNode extends FolderTypeNode implements FileDirectoryType {
    public FileDirectoryTypeNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
                                 LocalizedText displayName, LocalizedText description, UInteger writeMask,
                                 UInteger userWriteMask, RolePermissionType[] rolePermissions,
                                 RolePermissionType[] userRolePermissions, AccessRestrictionType accessRestrictions,
                                 UByte eventNotifier) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask, rolePermissions, userRolePermissions, accessRestrictions, eventNotifier);
    }

    public FileDirectoryTypeNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
                                 LocalizedText displayName, LocalizedText description, UInteger writeMask,
                                 UInteger userWriteMask, RolePermissionType[] rolePermissions,
                                 RolePermissionType[] userRolePermissions, AccessRestrictionType accessRestrictions) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask, rolePermissions, userRolePermissions, accessRestrictions);
    }

    @Override
    public UaMethodNode getCreateDirectoryMethodNode() {
        Optional<UaNode> methodNode = findNode("http://opcfoundation.org/UA/", "CreateDirectory", node -> node instanceof UaMethodNode, Reference.HAS_COMPONENT_PREDICATE);
        return (UaMethodNode) methodNode.orElse(null);
    }

    @Override
    public UaMethodNode getCreateFileMethodNode() {
        Optional<UaNode> methodNode = findNode("http://opcfoundation.org/UA/", "CreateFile", node -> node instanceof UaMethodNode, Reference.HAS_COMPONENT_PREDICATE);
        return (UaMethodNode) methodNode.orElse(null);
    }

    @Override
    public UaMethodNode getDeleteFileSystemObjectMethodNode() {
        Optional<UaNode> methodNode = findNode("http://opcfoundation.org/UA/", "Delete", node -> node instanceof UaMethodNode, Reference.HAS_COMPONENT_PREDICATE);
        return (UaMethodNode) methodNode.orElse(null);
    }

    @Override
    public UaMethodNode getMoveOrCopyMethodNode() {
        Optional<UaNode> methodNode = findNode("http://opcfoundation.org/UA/", "MoveOrCopy", node -> node instanceof UaMethodNode, Reference.HAS_COMPONENT_PREDICATE);
        return (UaMethodNode) methodNode.orElse(null);
    }
}
