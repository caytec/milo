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
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part5/12.26">https://reference.opcfoundation.org/v105/Core/docs/Part5/12.26</a>
 */
@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder
@ToString
public class ThreeDCartesianCoordinates extends CartesianCoordinates implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=18810");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=18819");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=18855");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=19068");

    private final Double x;

    private final Double y;

    private final Double z;

    public ThreeDCartesianCoordinates(Double x, Double y, Double z) {
        this.x = x;
        this.y = y;
        this.z = z;
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

    public Double getX() {
        return x;
    }

    public Double getY() {
        return y;
    }

    public Double getZ() {
        return z;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 18819),
            new NodeId(0, 18809),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("X", LocalizedText.NULL_VALUE, new NodeId(0, 11), -1, null, UInteger.valueOf(0), false),
                new StructureField("Y", LocalizedText.NULL_VALUE, new NodeId(0, 11), -1, null, UInteger.valueOf(0), false),
                new StructureField("Z", LocalizedText.NULL_VALUE, new NodeId(0, 11), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<ThreeDCartesianCoordinates> {
        @Override
        public Class<ThreeDCartesianCoordinates> getType() {
            return ThreeDCartesianCoordinates.class;
        }

        @Override
        public ThreeDCartesianCoordinates decode(SerializationContext context, UaDecoder decoder) {
            Double x = decoder.readDouble("X");
            Double y = decoder.readDouble("Y");
            Double z = decoder.readDouble("Z");
            return new ThreeDCartesianCoordinates(x, y, z);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           ThreeDCartesianCoordinates value) {
            encoder.writeDouble("X", value.getX());
            encoder.writeDouble("Y", value.getY());
            encoder.writeDouble("Z", value.getZ());
        }
    }
}