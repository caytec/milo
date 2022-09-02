package org.eclipse.milo.opcua.stack.core.types.structured;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaRequestMessage;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
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
public class AddNodesRequest extends Structure implements UaRequestMessage {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=486");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=488");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=487");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15167");

    private final RequestHeader requestHeader;

    private final AddNodesItem[] nodesToAdd;

    public AddNodesRequest(RequestHeader requestHeader, AddNodesItem[] nodesToAdd) {
        this.requestHeader = requestHeader;
        this.nodesToAdd = nodesToAdd;
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

    public RequestHeader getRequestHeader() {
        return requestHeader;
    }

    public AddNodesItem[] getNodesToAdd() {
        return nodesToAdd;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 488),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("RequestHeader", LocalizedText.NULL_VALUE, new NodeId(0, 389), -1, null, UInteger.valueOf(0), false),
                new StructureField("NodesToAdd", LocalizedText.NULL_VALUE, new NodeId(0, 376), 1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<AddNodesRequest> {
        @Override
        public Class<AddNodesRequest> getType() {
            return AddNodesRequest.class;
        }

        @Override
        public AddNodesRequest decode(SerializationContext context, UaDecoder decoder) {
            RequestHeader requestHeader = (RequestHeader) decoder.readStruct("RequestHeader", RequestHeader.TYPE_ID);
            AddNodesItem[] nodesToAdd = (AddNodesItem[]) decoder.readStructArray("NodesToAdd", AddNodesItem.TYPE_ID);
            return new AddNodesRequest(requestHeader, nodesToAdd);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, AddNodesRequest value) {
            encoder.writeStruct("RequestHeader", value.getRequestHeader(), RequestHeader.TYPE_ID);
            encoder.writeStructArray("NodesToAdd", value.getNodesToAdd(), AddNodesItem.TYPE_ID);
        }
    }
}
