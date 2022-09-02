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
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part14/6.4.2/#6.4.2.2.3">https://reference.opcfoundation.org/v105/Core/docs/Part14/6.4.2/#6.4.2.2.3</a>
 */
@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder
@ToString
public class BrokerConnectionTransportDataType extends ConnectionTransportDataType implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=15007");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=15479");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=15579");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15726");

    private final String resourceUri;

    private final String authenticationProfileUri;

    public BrokerConnectionTransportDataType(String resourceUri, String authenticationProfileUri) {
        this.resourceUri = resourceUri;
        this.authenticationProfileUri = authenticationProfileUri;
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

    public String getResourceUri() {
        return resourceUri;
    }

    public String getAuthenticationProfileUri() {
        return authenticationProfileUri;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 15479),
            new NodeId(0, 15618),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("ResourceUri", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false),
                new StructureField("AuthenticationProfileUri", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<BrokerConnectionTransportDataType> {
        @Override
        public Class<BrokerConnectionTransportDataType> getType() {
            return BrokerConnectionTransportDataType.class;
        }

        @Override
        public BrokerConnectionTransportDataType decode(SerializationContext context,
                                                        UaDecoder decoder) {
            String resourceUri = decoder.readString("ResourceUri");
            String authenticationProfileUri = decoder.readString("AuthenticationProfileUri");
            return new BrokerConnectionTransportDataType(resourceUri, authenticationProfileUri);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           BrokerConnectionTransportDataType value) {
            encoder.writeString("ResourceUri", value.getResourceUri());
            encoder.writeString("AuthenticationProfileUri", value.getAuthenticationProfileUri());
        }
    }
}
