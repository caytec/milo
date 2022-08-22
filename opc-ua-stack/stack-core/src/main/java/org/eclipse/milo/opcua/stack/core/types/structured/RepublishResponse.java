package org.eclipse.milo.opcua.stack.core.types.structured;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaResponseMessage;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part4/5.13.6/#5.13.6.2">https://reference.opcfoundation.org/v105/Core/docs/Part4/5.13.6/#5.13.6.2</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class RepublishResponse extends Structure implements UaResponseMessage {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=833");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=835");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=834");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15355");

    private final ResponseHeader responseHeader;

    private final NotificationMessage notificationMessage;

    public RepublishResponse(ResponseHeader responseHeader, NotificationMessage notificationMessage) {
        this.responseHeader = responseHeader;
        this.notificationMessage = notificationMessage;
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

    public ResponseHeader getResponseHeader() {
        return responseHeader;
    }

    public NotificationMessage getNotificationMessage() {
        return notificationMessage;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 835),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("ResponseHeader", LocalizedText.NULL_VALUE, new NodeId(0, 392), -1, null, UInteger.valueOf(0), false),
                new StructureField("NotificationMessage", LocalizedText.NULL_VALUE, new NodeId(0, 803), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<RepublishResponse> {
        @Override
        public Class<RepublishResponse> getType() {
            return RepublishResponse.class;
        }

        @Override
        public RepublishResponse decode(SerializationContext context, UaDecoder decoder) {
            ResponseHeader responseHeader = (ResponseHeader) decoder.readStruct("ResponseHeader", ResponseHeader.TYPE_ID);
            NotificationMessage notificationMessage = (NotificationMessage) decoder.readStruct("NotificationMessage", NotificationMessage.TYPE_ID);
            return new RepublishResponse(responseHeader, notificationMessage);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, RepublishResponse value) {
            encoder.writeStruct("ResponseHeader", value.getResponseHeader(), ResponseHeader.TYPE_ID);
            encoder.writeStruct("NotificationMessage", value.getNotificationMessage(), NotificationMessage.TYPE_ID);
        }
    }
}
