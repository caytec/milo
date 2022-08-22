package org.eclipse.milo.opcua.stack.core.types.structured;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part14/6.4.1/#6.4.1.1.3">https://reference.opcfoundation.org/v105/Core/docs/Part14/6.4.1/#6.4.1.1.3</a>
 */
@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder
@ToString
public abstract class TransmitQosDataType extends QosDataType implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=23604");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=23856");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=23924");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=23992");

    public TransmitQosDataType() {
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
}
