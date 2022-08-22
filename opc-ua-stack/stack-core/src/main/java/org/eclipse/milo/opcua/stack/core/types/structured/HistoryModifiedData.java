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
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v104/Core/docs/Part11/6.5.3">https://reference.opcfoundation.org/v104/Core/docs/Part11/6.5.3</a>
 */
@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder
@ToString
public class HistoryModifiedData extends HistoryData implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=11217");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=11227");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=11219");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15272");

    private final ModificationInfo[] modificationInfos;

    public HistoryModifiedData(DataValue[] dataValues, ModificationInfo[] modificationInfos) {
        super(dataValues);
        this.modificationInfos = modificationInfos;
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

    public ModificationInfo[] getModificationInfos() {
        return modificationInfos;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 11227),
            new NodeId(0, 656),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("DataValues", LocalizedText.NULL_VALUE, new NodeId(0, 23), 1, null, UInteger.valueOf(0), false),
                new StructureField("ModificationInfos", LocalizedText.NULL_VALUE, new NodeId(0, 11216), 1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<HistoryModifiedData> {
        @Override
        public Class<HistoryModifiedData> getType() {
            return HistoryModifiedData.class;
        }

        @Override
        public HistoryModifiedData decode(SerializationContext context, UaDecoder decoder) {
            DataValue[] dataValues = decoder.readDataValueArray("DataValues");
            ModificationInfo[] modificationInfos = (ModificationInfo[]) decoder.readStructArray("ModificationInfos", ModificationInfo.TYPE_ID);
            return new HistoryModifiedData(dataValues, modificationInfos);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, HistoryModifiedData value) {
            encoder.writeDataValueArray("DataValues", value.getDataValues());
            encoder.writeStructArray("ModificationInfos", value.getModificationInfos(), ModificationInfo.TYPE_ID);
        }
    }
}
