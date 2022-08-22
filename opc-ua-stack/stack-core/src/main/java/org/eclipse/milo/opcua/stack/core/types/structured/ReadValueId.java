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
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part4/7.29">https://reference.opcfoundation.org/v105/Core/docs/Part4/7.29</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class ReadValueId extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=626");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=628");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=627");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15256");

    private final NodeId nodeId;

    private final UInteger attributeId;

    private final String indexRange;

    private final QualifiedName dataEncoding;

    public ReadValueId(NodeId nodeId, UInteger attributeId, String indexRange,
                       QualifiedName dataEncoding) {
        this.nodeId = nodeId;
        this.attributeId = attributeId;
        this.indexRange = indexRange;
        this.dataEncoding = dataEncoding;
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

    public NodeId getNodeId() {
        return nodeId;
    }

    public UInteger getAttributeId() {
        return attributeId;
    }

    public String getIndexRange() {
        return indexRange;
    }

    public QualifiedName getDataEncoding() {
        return dataEncoding;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 628),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("NodeId", LocalizedText.NULL_VALUE, new NodeId(0, 17), -1, null, UInteger.valueOf(0), false),
                new StructureField("AttributeId", LocalizedText.NULL_VALUE, new NodeId(0, 288), -1, null, UInteger.valueOf(0), false),
                new StructureField("IndexRange", LocalizedText.NULL_VALUE, new NodeId(0, 291), -1, null, UInteger.valueOf(0), false),
                new StructureField("DataEncoding", LocalizedText.NULL_VALUE, new NodeId(0, 20), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<ReadValueId> {
        @Override
        public Class<ReadValueId> getType() {
            return ReadValueId.class;
        }

        @Override
        public ReadValueId decode(SerializationContext context, UaDecoder decoder) {
            NodeId nodeId = decoder.readNodeId("NodeId");
            UInteger attributeId = decoder.readUInt32("AttributeId");
            String indexRange = decoder.readString("IndexRange");
            QualifiedName dataEncoding = decoder.readQualifiedName("DataEncoding");
            return new ReadValueId(nodeId, attributeId, indexRange, dataEncoding);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, ReadValueId value) {
            encoder.writeNodeId("NodeId", value.getNodeId());
            encoder.writeUInt32("AttributeId", value.getAttributeId());
            encoder.writeString("IndexRange", value.getIndexRange());
            encoder.writeQualifiedName("DataEncoding", value.getDataEncoding());
        }
    }
}
