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
 * @see <a href="https://reference.opcfoundation.org/v104/Core/docs/Part11/6.7">https://reference.opcfoundation.org/v104/Core/docs/Part11/6.7</a>
 */
public enum PerformUpdateType implements UaEnumeration {
    Insert(1),

    Replace(2),

    Update(3),

    Remove(4);

    private final int value;

    PerformUpdateType(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    public static ExpandedNodeId getTypeId() {
        return ExpandedNodeId.parse("ns=0;i=11293");
    }

    public static @Nullable PerformUpdateType from(int value) {
        switch (value) {
            case 1:
                return Insert;
            case 2:
                return Replace;
            case 3:
                return Update;
            case 4:
                return Remove;
            default:
                return null;
        }
    }

    public static EnumDefinition definition() {
        return new EnumDefinition(new EnumField[]{
            new EnumField(1L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "Insert"),
            new EnumField(2L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "Replace"),
            new EnumField(3L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "Update"),
            new EnumField(4L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "Remove")
        });
    }

    public static final class Codec extends GenericDataTypeCodec<PerformUpdateType> {
        @Override
        public Class<PerformUpdateType> getType() {
            return PerformUpdateType.class;
        }

        @Override
        public PerformUpdateType decode(SerializationContext context, UaDecoder decoder) {
            return decoder.readEnum(null, org.eclipse.milo.opcua.stack.core.types.enumerated.PerformUpdateType.class);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, PerformUpdateType value) {
            encoder.writeEnum(null, value);
        }
    }
}
