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
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part5/12.20">https://reference.opcfoundation.org/v105/Core/docs/Part5/12.20</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class EndpointUrlListDataType extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=11943");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=11957");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=11949");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15363");

    private final String[] endpointUrlList;

    public EndpointUrlListDataType(String[] endpointUrlList) {
        this.endpointUrlList = endpointUrlList;
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

    public String[] getEndpointUrlList() {
        return endpointUrlList;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 11957),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("EndpointUrlList", LocalizedText.NULL_VALUE, new NodeId(0, 12), 1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<EndpointUrlListDataType> {
        @Override
        public Class<EndpointUrlListDataType> getType() {
            return EndpointUrlListDataType.class;
        }

        @Override
        public EndpointUrlListDataType decode(SerializationContext context, UaDecoder decoder) {
            String[] endpointUrlList = decoder.readStringArray("EndpointUrlList");
            return new EndpointUrlListDataType(endpointUrlList);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           EndpointUrlListDataType value) {
            encoder.writeStringArray("EndpointUrlList", value.getEndpointUrlList());
        }
    }
}
