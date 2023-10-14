/*
 * Copyright (c) 2023 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.types.structured;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.encoding.EncodingContext;
import org.eclipse.milo.opcua.stack.core.encoding.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.encoding.UaDecoder;
import org.eclipse.milo.opcua.stack.core.encoding.UaEncoder;
import org.eclipse.milo.opcua.stack.core.types.UaRequestMessageType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.jetbrains.annotations.Nullable;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part4/5.12.3/#5.12.3.2">https://reference.opcfoundation.org/v105/Core/docs/Part4/5.12.3/#5.12.3.2</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class ModifyMonitoredItemsRequest extends Structure implements UaRequestMessageType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=761");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=763");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=762");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15327");

    private final RequestHeader requestHeader;

    private final UInteger subscriptionId;

    private final TimestampsToReturn timestampsToReturn;

    private final MonitoredItemModifyRequest @Nullable [] itemsToModify;

    public ModifyMonitoredItemsRequest(RequestHeader requestHeader, UInteger subscriptionId,
                                       TimestampsToReturn timestampsToReturn,
                                       MonitoredItemModifyRequest @Nullable [] itemsToModify) {
        this.requestHeader = requestHeader;
        this.subscriptionId = subscriptionId;
        this.timestampsToReturn = timestampsToReturn;
        this.itemsToModify = itemsToModify;
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

    public TimestampsToReturn getTimestampsToReturn() {
        return timestampsToReturn;
    }

    public MonitoredItemModifyRequest @Nullable [] getItemsToModify() {
        return itemsToModify;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 763),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("RequestHeader", LocalizedText.NULL_VALUE, new NodeId(0, 389), -1, null, UInteger.valueOf(0), false),
                new StructureField("SubscriptionId", LocalizedText.NULL_VALUE, new NodeId(0, 288), -1, null, UInteger.valueOf(0), false),
                new StructureField("TimestampsToReturn", LocalizedText.NULL_VALUE, new NodeId(0, 625), -1, null, UInteger.valueOf(0), false),
                new StructureField("ItemsToModify", LocalizedText.NULL_VALUE, new NodeId(0, 755), 1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<ModifyMonitoredItemsRequest> {
        @Override
        public Class<ModifyMonitoredItemsRequest> getType() {
            return ModifyMonitoredItemsRequest.class;
        }

        @Override
        public ModifyMonitoredItemsRequest decodeType(EncodingContext context, UaDecoder decoder) {
            RequestHeader requestHeader = (RequestHeader) decoder.decodeStruct("RequestHeader", RequestHeader.TYPE_ID);
            UInteger subscriptionId = decoder.decodeUInt32("SubscriptionId");
            TimestampsToReturn timestampsToReturn = TimestampsToReturn.from(decoder.decodeEnum("TimestampsToReturn"));
            MonitoredItemModifyRequest[] itemsToModify = (MonitoredItemModifyRequest[]) decoder.decodeStructArray("ItemsToModify", MonitoredItemModifyRequest.TYPE_ID);
            return new ModifyMonitoredItemsRequest(requestHeader, subscriptionId, timestampsToReturn, itemsToModify);
        }

        @Override
        public void encodeType(EncodingContext context, UaEncoder encoder,
                               ModifyMonitoredItemsRequest value) {
            encoder.encodeStruct("RequestHeader", value.getRequestHeader(), RequestHeader.TYPE_ID);
            encoder.encodeUInt32("SubscriptionId", value.getSubscriptionId());
            encoder.encodeEnum("TimestampsToReturn", value.getTimestampsToReturn());
            encoder.encodeStructArray("ItemsToModify", value.getItemsToModify(), MonitoredItemModifyRequest.TYPE_ID);
        }
    }
}
