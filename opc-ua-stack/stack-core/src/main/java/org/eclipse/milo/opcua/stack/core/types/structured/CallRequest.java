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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part4/5.11.2/#5.11.2.2">https://reference.opcfoundation.org/v105/Core/docs/Part4/5.11.2/#5.11.2.2</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class CallRequest extends Structure implements UaRequestMessage {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=710");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=712");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=711");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15291");

    private final RequestHeader requestHeader;

    private final CallMethodRequest[] methodsToCall;

    public CallRequest(RequestHeader requestHeader, CallMethodRequest[] methodsToCall) {
        this.requestHeader = requestHeader;
        this.methodsToCall = methodsToCall;
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

    public CallMethodRequest[] getMethodsToCall() {
        return methodsToCall;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 712),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("RequestHeader", LocalizedText.NULL_VALUE, new NodeId(0, 389), -1, null, UInteger.valueOf(0), false),
                new StructureField("MethodsToCall", LocalizedText.NULL_VALUE, new NodeId(0, 704), 1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<CallRequest> {
        @Override
        public Class<CallRequest> getType() {
            return CallRequest.class;
        }

        @Override
        public CallRequest decode(SerializationContext context, UaDecoder decoder) {
            RequestHeader requestHeader = (RequestHeader) decoder.readStruct("RequestHeader", RequestHeader.TYPE_ID);
            CallMethodRequest[] methodsToCall = (CallMethodRequest[]) decoder.readStructArray("MethodsToCall", CallMethodRequest.TYPE_ID);
            return new CallRequest(requestHeader, methodsToCall);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, CallRequest value) {
            encoder.writeStruct("RequestHeader", value.getRequestHeader(), RequestHeader.TYPE_ID);
            encoder.writeStructArray("MethodsToCall", value.getMethodsToCall(), CallMethodRequest.TYPE_ID);
        }
    }
}
