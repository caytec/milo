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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part4/5.13.3/#5.13.3.2">https://reference.opcfoundation.org/v105/Core/docs/Part4/5.13.3/#5.13.3.2</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class ModifySubscriptionRequest extends Structure implements UaRequestMessage {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=791");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=793");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=792");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15339");

    private final RequestHeader requestHeader;

    private final UInteger subscriptionId;

    private final Double requestedPublishingInterval;

    private final UInteger requestedLifetimeCount;

    private final UInteger requestedMaxKeepAliveCount;

    private final UInteger maxNotificationsPerPublish;

    private final UByte priority;

    public ModifySubscriptionRequest(RequestHeader requestHeader, UInteger subscriptionId,
                                     Double requestedPublishingInterval, UInteger requestedLifetimeCount,
                                     UInteger requestedMaxKeepAliveCount, UInteger maxNotificationsPerPublish, UByte priority) {
        this.requestHeader = requestHeader;
        this.subscriptionId = subscriptionId;
        this.requestedPublishingInterval = requestedPublishingInterval;
        this.requestedLifetimeCount = requestedLifetimeCount;
        this.requestedMaxKeepAliveCount = requestedMaxKeepAliveCount;
        this.maxNotificationsPerPublish = maxNotificationsPerPublish;
        this.priority = priority;
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

    public UInteger getSubscriptionId() {
        return subscriptionId;
    }

    public Double getRequestedPublishingInterval() {
        return requestedPublishingInterval;
    }

    public UInteger getRequestedLifetimeCount() {
        return requestedLifetimeCount;
    }

    public UInteger getRequestedMaxKeepAliveCount() {
        return requestedMaxKeepAliveCount;
    }

    public UInteger getMaxNotificationsPerPublish() {
        return maxNotificationsPerPublish;
    }

    public UByte getPriority() {
        return priority;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 793),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("RequestHeader", LocalizedText.NULL_VALUE, new NodeId(0, 389), -1, null, UInteger.valueOf(0), false),
                new StructureField("SubscriptionId", LocalizedText.NULL_VALUE, new NodeId(0, 288), -1, null, UInteger.valueOf(0), false),
                new StructureField("RequestedPublishingInterval", LocalizedText.NULL_VALUE, new NodeId(0, 290), -1, null, UInteger.valueOf(0), false),
                new StructureField("RequestedLifetimeCount", LocalizedText.NULL_VALUE, new NodeId(0, 289), -1, null, UInteger.valueOf(0), false),
                new StructureField("RequestedMaxKeepAliveCount", LocalizedText.NULL_VALUE, new NodeId(0, 289), -1, null, UInteger.valueOf(0), false),
                new StructureField("MaxNotificationsPerPublish", LocalizedText.NULL_VALUE, new NodeId(0, 289), -1, null, UInteger.valueOf(0), false),
                new StructureField("Priority", LocalizedText.NULL_VALUE, new NodeId(0, 3), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<ModifySubscriptionRequest> {
        @Override
        public Class<ModifySubscriptionRequest> getType() {
            return ModifySubscriptionRequest.class;
        }

        @Override
        public ModifySubscriptionRequest decode(SerializationContext context, UaDecoder decoder) {
            RequestHeader requestHeader = (RequestHeader) decoder.readStruct("RequestHeader", RequestHeader.TYPE_ID);
            UInteger subscriptionId = decoder.readUInt32("SubscriptionId");
            Double requestedPublishingInterval = decoder.readDouble("RequestedPublishingInterval");
            UInteger requestedLifetimeCount = decoder.readUInt32("RequestedLifetimeCount");
            UInteger requestedMaxKeepAliveCount = decoder.readUInt32("RequestedMaxKeepAliveCount");
            UInteger maxNotificationsPerPublish = decoder.readUInt32("MaxNotificationsPerPublish");
            UByte priority = decoder.readByte("Priority");
            return new ModifySubscriptionRequest(requestHeader, subscriptionId, requestedPublishingInterval, requestedLifetimeCount, requestedMaxKeepAliveCount, maxNotificationsPerPublish, priority);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           ModifySubscriptionRequest value) {
            encoder.writeStruct("RequestHeader", value.getRequestHeader(), RequestHeader.TYPE_ID);
            encoder.writeUInt32("SubscriptionId", value.getSubscriptionId());
            encoder.writeDouble("RequestedPublishingInterval", value.getRequestedPublishingInterval());
            encoder.writeUInt32("RequestedLifetimeCount", value.getRequestedLifetimeCount());
            encoder.writeUInt32("RequestedMaxKeepAliveCount", value.getRequestedMaxKeepAliveCount());
            encoder.writeUInt32("MaxNotificationsPerPublish", value.getMaxNotificationsPerPublish());
            encoder.writeByte("Priority", value.getPriority());
        }
    }
}
