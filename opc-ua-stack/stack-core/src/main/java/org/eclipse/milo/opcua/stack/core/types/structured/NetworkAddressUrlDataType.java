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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part14/6.2.7/#6.2.7.5.4">https://reference.opcfoundation.org/v105/Core/docs/Part14/6.2.7/#6.2.7.5.4</a>
 */
@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder
@ToString
public class NetworkAddressUrlDataType extends NetworkAddressDataType implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=15510");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=21152");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=21176");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=21200");

    private final String url;

    public NetworkAddressUrlDataType(String networkInterface, String url) {
        super(networkInterface);
        this.url = url;
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

    public String getUrl() {
        return url;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 21152),
            new NodeId(0, 15502),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("NetworkInterface", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false),
                new StructureField("Url", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<NetworkAddressUrlDataType> {
        @Override
        public Class<NetworkAddressUrlDataType> getType() {
            return NetworkAddressUrlDataType.class;
        }

        @Override
        public NetworkAddressUrlDataType decode(SerializationContext context, UaDecoder decoder) {
            String networkInterface = decoder.readString("NetworkInterface");
            String url = decoder.readString("Url");
            return new NetworkAddressUrlDataType(networkInterface, url);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           NetworkAddressUrlDataType value) {
            encoder.writeString("NetworkInterface", value.getNetworkInterface());
            encoder.writeString("Url", value.getUrl());
        }
    }
}