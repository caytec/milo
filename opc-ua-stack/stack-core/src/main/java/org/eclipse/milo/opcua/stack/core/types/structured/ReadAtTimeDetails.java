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
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v104/Core/docs/Part11/6.4.5/#6.4.5.1">https://reference.opcfoundation.org/v104/Core/docs/Part11/6.4.5/#6.4.5.1</a>
 */
@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder
@ToString
public class ReadAtTimeDetails extends HistoryReadDetails implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=653");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=655");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=654");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15269");

    private final DateTime[] reqTimes;

    private final Boolean useSimpleBounds;

    public ReadAtTimeDetails(DateTime[] reqTimes, Boolean useSimpleBounds) {
        this.reqTimes = reqTimes;
        this.useSimpleBounds = useSimpleBounds;
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

    public DateTime[] getReqTimes() {
        return reqTimes;
    }

    public Boolean getUseSimpleBounds() {
        return useSimpleBounds;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 655),
            new NodeId(0, 641),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("ReqTimes", LocalizedText.NULL_VALUE, new NodeId(0, 294), 1, null, UInteger.valueOf(0), false),
                new StructureField("UseSimpleBounds", LocalizedText.NULL_VALUE, new NodeId(0, 1), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<ReadAtTimeDetails> {
        @Override
        public Class<ReadAtTimeDetails> getType() {
            return ReadAtTimeDetails.class;
        }

        @Override
        public ReadAtTimeDetails decode(SerializationContext context, UaDecoder decoder) {
            DateTime[] reqTimes = decoder.readDateTimeArray("ReqTimes");
            Boolean useSimpleBounds = decoder.readBoolean("UseSimpleBounds");
            return new ReadAtTimeDetails(reqTimes, useSimpleBounds);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, ReadAtTimeDetails value) {
            encoder.writeDateTimeArray("ReqTimes", value.getReqTimes());
            encoder.writeBoolean("UseSimpleBounds", value.getUseSimpleBounds());
        }
    }
}
