package org.eclipse.milo.opcua.stack.core.types.structured;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part4/5.7.2/#5.7.2.2">https://reference.opcfoundation.org/v105/Core/docs/Part4/5.7.2/#5.7.2.2</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class AddNodesResult extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=483");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=485");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=484");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15166");

    private final StatusCode statusCode;

    private final NodeId addedNodeId;

    public AddNodesResult(StatusCode statusCode, NodeId addedNodeId) {
        this.statusCode = statusCode;
        this.addedNodeId = addedNodeId;
    }

    @Override
    public ExpandedNodeId getTypeId() {
        return TYPE_ID;
    }

    @Override
    public ExpandedNodeId getBinaryEncodingId() {
        return BINARY_ENCODING_ID;
    }

    @Override
    public ExpandedNodeId getXmlEncodingId() {
        return XML_ENCODING_ID;
    }

    @Override
    public ExpandedNodeId getJsonEncodingId() {
        return JSON_ENCODING_ID;
    }

    public StatusCode getStatusCode() {
        return statusCode;
    }

    public NodeId getAddedNodeId() {
        return addedNodeId;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 485),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("StatusCode", LocalizedText.NULL_VALUE, new NodeId(0, 19), -1, null, UInteger.valueOf(0), false),
                new StructureField("AddedNodeId", LocalizedText.NULL_VALUE, new NodeId(0, 17), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<AddNodesResult> {
        @Override
        public Class<AddNodesResult> getType() {
            return AddNodesResult.class;
        }

        @Override
        public AddNodesResult decode(SerializationContext context, UaDecoder decoder) {
            StatusCode statusCode = decoder.readStatusCode("StatusCode");
            NodeId addedNodeId = decoder.readNodeId("AddedNodeId");
            return new AddNodesResult(statusCode, addedNodeId);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, AddNodesResult value) {
            encoder.writeStatusCode("StatusCode", value.getStatusCode());
            encoder.writeNodeId("AddedNodeId", value.getAddedNodeId());
        }
    }
}
