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
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part5/12.3.4">https://reference.opcfoundation.org/v105/Core/docs/Part5/12.3.4</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class ContentFilter extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=586");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=588");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=587");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15205");

    private final ContentFilterElement[] elements;

    public ContentFilter(ContentFilterElement[] elements) {
        this.elements = elements;
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

    public ContentFilterElement[] getElements() {
        return elements;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 588),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("Elements", LocalizedText.NULL_VALUE, new NodeId(0, 583), 1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<ContentFilter> {
        @Override
        public Class<ContentFilter> getType() {
            return ContentFilter.class;
        }

        @Override
        public ContentFilter decode(SerializationContext context, UaDecoder decoder) {
            ContentFilterElement[] elements = (ContentFilterElement[]) decoder.readStructArray("Elements", ContentFilterElement.TYPE_ID);
            return new ContentFilter(elements);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, ContentFilter value) {
            encoder.writeStructArray("Elements", value.getElements(), ContentFilterElement.TYPE_ID);
        }
    }
}
