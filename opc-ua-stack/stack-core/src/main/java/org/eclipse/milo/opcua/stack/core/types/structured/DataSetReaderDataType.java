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
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MessageSecurityMode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part14/6.2.9/#6.2.9.13.1">https://reference.opcfoundation.org/v105/Core/docs/Part14/6.2.9/#6.2.9.13.1</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class DataSetReaderDataType extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=15623");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=15703");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=16007");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=16286");

    private final String name;

    private final Boolean enabled;

    private final Variant publisherId;

    private final UShort writerGroupId;

    private final UShort dataSetWriterId;

    private final DataSetMetaDataType dataSetMetaData;

    private final DataSetFieldContentMask dataSetFieldContentMask;

    private final Double messageReceiveTimeout;

    private final UInteger keyFrameCount;

    private final String headerLayoutUri;

    private final MessageSecurityMode securityMode;

    private final String securityGroupId;

    private final EndpointDescription[] securityKeyServices;

    private final KeyValuePair[] dataSetReaderProperties;

    private final DataSetReaderTransportDataType transportSettings;

    private final DataSetReaderMessageDataType messageSettings;

    private final SubscribedDataSetDataType subscribedDataSet;

    public DataSetReaderDataType(String name, Boolean enabled, Variant publisherId,
                                 UShort writerGroupId, UShort dataSetWriterId, DataSetMetaDataType dataSetMetaData,
                                 DataSetFieldContentMask dataSetFieldContentMask, Double messageReceiveTimeout,
                                 UInteger keyFrameCount, String headerLayoutUri, MessageSecurityMode securityMode,
                                 String securityGroupId, EndpointDescription[] securityKeyServices,
                                 KeyValuePair[] dataSetReaderProperties, DataSetReaderTransportDataType transportSettings,
                                 DataSetReaderMessageDataType messageSettings, SubscribedDataSetDataType subscribedDataSet) {
        this.name = name;
        this.enabled = enabled;
        this.publisherId = publisherId;
        this.writerGroupId = writerGroupId;
        this.dataSetWriterId = dataSetWriterId;
        this.dataSetMetaData = dataSetMetaData;
        this.dataSetFieldContentMask = dataSetFieldContentMask;
        this.messageReceiveTimeout = messageReceiveTimeout;
        this.keyFrameCount = keyFrameCount;
        this.headerLayoutUri = headerLayoutUri;
        this.securityMode = securityMode;
        this.securityGroupId = securityGroupId;
        this.securityKeyServices = securityKeyServices;
        this.dataSetReaderProperties = dataSetReaderProperties;
        this.transportSettings = transportSettings;
        this.messageSettings = messageSettings;
        this.subscribedDataSet = subscribedDataSet;
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

    public String getName() {
        return name;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public Variant getPublisherId() {
        return publisherId;
    }

    public UShort getWriterGroupId() {
        return writerGroupId;
    }

    public UShort getDataSetWriterId() {
        return dataSetWriterId;
    }

    public DataSetMetaDataType getDataSetMetaData() {
        return dataSetMetaData;
    }

    public DataSetFieldContentMask getDataSetFieldContentMask() {
        return dataSetFieldContentMask;
    }

    public Double getMessageReceiveTimeout() {
        return messageReceiveTimeout;
    }

    public UInteger getKeyFrameCount() {
        return keyFrameCount;
    }

    public String getHeaderLayoutUri() {
        return headerLayoutUri;
    }

    public MessageSecurityMode getSecurityMode() {
        return securityMode;
    }

    public String getSecurityGroupId() {
        return securityGroupId;
    }

    public EndpointDescription[] getSecurityKeyServices() {
        return securityKeyServices;
    }

    public KeyValuePair[] getDataSetReaderProperties() {
        return dataSetReaderProperties;
    }

    public DataSetReaderTransportDataType getTransportSettings() {
        return transportSettings;
    }

    public DataSetReaderMessageDataType getMessageSettings() {
        return messageSettings;
    }

    public SubscribedDataSetDataType getSubscribedDataSet() {
        return subscribedDataSet;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 15703),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("Name", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false),
                new StructureField("Enabled", LocalizedText.NULL_VALUE, new NodeId(0, 1), -1, null, UInteger.valueOf(0), false),
                new StructureField("PublisherId", LocalizedText.NULL_VALUE, new NodeId(0, 24), -1, null, UInteger.valueOf(0), false),
                new StructureField("WriterGroupId", LocalizedText.NULL_VALUE, new NodeId(0, 5), -1, null, UInteger.valueOf(0), false),
                new StructureField("DataSetWriterId", LocalizedText.NULL_VALUE, new NodeId(0, 5), -1, null, UInteger.valueOf(0), false),
                new StructureField("DataSetMetaData", LocalizedText.NULL_VALUE, new NodeId(0, 14523), -1, null, UInteger.valueOf(0), false),
                new StructureField("DataSetFieldContentMask", LocalizedText.NULL_VALUE, new NodeId(0, 15583), -1, null, UInteger.valueOf(0), false),
                new StructureField("MessageReceiveTimeout", LocalizedText.NULL_VALUE, new NodeId(0, 290), -1, null, UInteger.valueOf(0), false),
                new StructureField("KeyFrameCount", LocalizedText.NULL_VALUE, new NodeId(0, 7), -1, null, UInteger.valueOf(0), false),
                new StructureField("HeaderLayoutUri", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false),
                new StructureField("SecurityMode", LocalizedText.NULL_VALUE, new NodeId(0, 302), -1, null, UInteger.valueOf(0), false),
                new StructureField("SecurityGroupId", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false),
                new StructureField("SecurityKeyServices", LocalizedText.NULL_VALUE, new NodeId(0, 312), 1, null, UInteger.valueOf(0), false),
                new StructureField("DataSetReaderProperties", LocalizedText.NULL_VALUE, new NodeId(0, 14533), 1, null, UInteger.valueOf(0), false),
                new StructureField("TransportSettings", LocalizedText.NULL_VALUE, new NodeId(0, 15628), -1, null, UInteger.valueOf(0), false),
                new StructureField("MessageSettings", LocalizedText.NULL_VALUE, new NodeId(0, 15629), -1, null, UInteger.valueOf(0), false),
                new StructureField("SubscribedDataSet", LocalizedText.NULL_VALUE, new NodeId(0, 15630), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<DataSetReaderDataType> {
        @Override
        public Class<DataSetReaderDataType> getType() {
            return DataSetReaderDataType.class;
        }

        @Override
        public DataSetReaderDataType decode(SerializationContext context, UaDecoder decoder) {
            String name = decoder.readString("Name");
            Boolean enabled = decoder.readBoolean("Enabled");
            Variant publisherId = decoder.readVariant("PublisherId");
            UShort writerGroupId = decoder.readUInt16("WriterGroupId");
            UShort dataSetWriterId = decoder.readUInt16("DataSetWriterId");
            DataSetMetaDataType dataSetMetaData = (DataSetMetaDataType) decoder.readStruct("DataSetMetaData", DataSetMetaDataType.TYPE_ID);
            DataSetFieldContentMask dataSetFieldContentMask = new DataSetFieldContentMask(decoder.readUInt32("DataSetFieldContentMask"));
            Double messageReceiveTimeout = decoder.readDouble("MessageReceiveTimeout");
            UInteger keyFrameCount = decoder.readUInt32("KeyFrameCount");
            String headerLayoutUri = decoder.readString("HeaderLayoutUri");
            MessageSecurityMode securityMode = (MessageSecurityMode) decoder.readEnum("SecurityMode", MessageSecurityMode.class);
            String securityGroupId = decoder.readString("SecurityGroupId");
            EndpointDescription[] securityKeyServices = (EndpointDescription[]) decoder.readStructArray("SecurityKeyServices", EndpointDescription.TYPE_ID);
            KeyValuePair[] dataSetReaderProperties = (KeyValuePair[]) decoder.readStructArray("DataSetReaderProperties", KeyValuePair.TYPE_ID);
            DataSetReaderTransportDataType transportSettings = (DataSetReaderTransportDataType) decoder.readStruct("TransportSettings", DataSetReaderTransportDataType.TYPE_ID);
            DataSetReaderMessageDataType messageSettings = (DataSetReaderMessageDataType) decoder.readStruct("MessageSettings", DataSetReaderMessageDataType.TYPE_ID);
            SubscribedDataSetDataType subscribedDataSet = (SubscribedDataSetDataType) decoder.readStruct("SubscribedDataSet", SubscribedDataSetDataType.TYPE_ID);
            return new DataSetReaderDataType(name, enabled, publisherId, writerGroupId, dataSetWriterId, dataSetMetaData, dataSetFieldContentMask, messageReceiveTimeout, keyFrameCount, headerLayoutUri, securityMode, securityGroupId, securityKeyServices, dataSetReaderProperties, transportSettings, messageSettings, subscribedDataSet);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           DataSetReaderDataType value) {
            encoder.writeString("Name", value.getName());
            encoder.writeBoolean("Enabled", value.getEnabled());
            encoder.writeVariant("PublisherId", value.getPublisherId());
            encoder.writeUInt16("WriterGroupId", value.getWriterGroupId());
            encoder.writeUInt16("DataSetWriterId", value.getDataSetWriterId());
            encoder.writeStruct("DataSetMetaData", value.getDataSetMetaData(), DataSetMetaDataType.TYPE_ID);
            encoder.writeUInt32("DataSetFieldContentMask", value.getDataSetFieldContentMask().getValue());
            encoder.writeDouble("MessageReceiveTimeout", value.getMessageReceiveTimeout());
            encoder.writeUInt32("KeyFrameCount", value.getKeyFrameCount());
            encoder.writeString("HeaderLayoutUri", value.getHeaderLayoutUri());
            encoder.writeEnum("SecurityMode", value.getSecurityMode());
            encoder.writeString("SecurityGroupId", value.getSecurityGroupId());
            encoder.writeStructArray("SecurityKeyServices", value.getSecurityKeyServices(), EndpointDescription.TYPE_ID);
            encoder.writeStructArray("DataSetReaderProperties", value.getDataSetReaderProperties(), KeyValuePair.TYPE_ID);
            encoder.writeStruct("TransportSettings", value.getTransportSettings(), DataSetReaderTransportDataType.TYPE_ID);
            encoder.writeStruct("MessageSettings", value.getMessageSettings(), DataSetReaderMessageDataType.TYPE_ID);
            encoder.writeStruct("SubscribedDataSet", value.getSubscribedDataSet(), SubscribedDataSetDataType.TYPE_ID);
        }
    }
}
