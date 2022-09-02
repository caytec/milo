package org.eclipse.milo.opcua.stack.core.types.enumerated;

import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEnumeration;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.structured.EnumDefinition;
import org.eclipse.milo.opcua.stack.core.types.structured.EnumField;
import org.jetbrains.annotations.Nullable;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part14/6.3.1/#6.3.1.1.3">https://reference.opcfoundation.org/v105/Core/docs/Part14/6.3.1/#6.3.1.1.3</a>
 */
public enum DataSetOrderingType implements UaEnumeration {
    Undefined(0),

    AscendingWriterId(1),

    AscendingWriterIdSingle(2);

    private final int value;

    DataSetOrderingType(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    public static ExpandedNodeId getTypeId() {
        return ExpandedNodeId.parse("ns=0;i=20408");
    }

    public static @Nullable DataSetOrderingType from(int value) {
        switch (value) {
            case 0:
                return Undefined;
            case 1:
                return AscendingWriterId;
            case 2:
                return AscendingWriterIdSingle;
            default:
                return null;
        }
    }

    public static EnumDefinition definition() {
        return new EnumDefinition(new EnumField[]{
            new EnumField(0L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "Undefined"),
            new EnumField(1L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "AscendingWriterId"),
            new EnumField(2L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "AscendingWriterIdSingle")
        });
    }

    public static final class Codec extends GenericDataTypeCodec<DataSetOrderingType> {
        @Override
        public Class<DataSetOrderingType> getType() {
            return DataSetOrderingType.class;
        }

        @Override
        public DataSetOrderingType decode(SerializationContext context, UaDecoder decoder) {
            return decoder.readEnum(null, DataSetOrderingType.class);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, DataSetOrderingType value) {
            encoder.writeEnum(null, value);
        }
    }
}
