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
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.HistoryUpdateType;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v104/Core/docs/Part11/6.5.3">https://reference.opcfoundation.org/v104/Core/docs/Part11/6.5.3</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class ModificationInfo extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=11216");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=11226");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=11218");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15271");

    private final DateTime modificationTime;

    private final HistoryUpdateType updateType;

    private final String userName;

    public ModificationInfo(DateTime modificationTime, HistoryUpdateType updateType,
                            String userName) {
        this.modificationTime = modificationTime;
        this.updateType = updateType;
        this.userName = userName;
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

    public DateTime getModificationTime() {
        return modificationTime;
    }

    public HistoryUpdateType getUpdateType() {
        return updateType;
    }

    public String getUserName() {
        return userName;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 11226),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("ModificationTime", LocalizedText.NULL_VALUE, new NodeId(0, 294), -1, null, UInteger.valueOf(0), false),
                new StructureField("UpdateType", LocalizedText.NULL_VALUE, new NodeId(0, 11234), -1, null, UInteger.valueOf(0), false),
                new StructureField("UserName", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<ModificationInfo> {
        @Override
        public Class<ModificationInfo> getType() {
            return ModificationInfo.class;
        }

        @Override
        public ModificationInfo decode(SerializationContext context, UaDecoder decoder) {
            DateTime modificationTime = decoder.readDateTime("ModificationTime");
            HistoryUpdateType updateType = (HistoryUpdateType) decoder.readEnum("UpdateType", HistoryUpdateType.class);
            String userName = decoder.readString("UserName");
            return new ModificationInfo(modificationTime, updateType, userName);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, ModificationInfo value) {
            encoder.writeDateTime("ModificationTime", value.getModificationTime());
            encoder.writeEnum("UpdateType", value.getUpdateType());
            encoder.writeString("UserName", value.getUserName());
        }
    }
}
