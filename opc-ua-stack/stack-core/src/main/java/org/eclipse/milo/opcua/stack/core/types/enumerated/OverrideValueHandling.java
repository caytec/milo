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
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part14/6.2.10/#6.2.10.2.4">https://reference.opcfoundation.org/v105/Core/docs/Part14/6.2.10/#6.2.10.2.4</a>
 */
public enum OverrideValueHandling implements UaEnumeration {
    Disabled(0),

    LastUsableValue(1),

    OverrideValue(2);

    private final int value;

    OverrideValueHandling(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    public static ExpandedNodeId getTypeId() {
        return ExpandedNodeId.parse("ns=0;i=15874");
    }

    public static @Nullable OverrideValueHandling from(int value) {
        switch (value) {
            case 0:
                return Disabled;
            case 1:
                return LastUsableValue;
            case 2:
                return OverrideValue;
            default:
                return null;
        }
    }

    public static EnumDefinition definition() {
        return new EnumDefinition(new EnumField[]{
            new EnumField(0L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "Disabled"),
            new EnumField(1L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "LastUsableValue"),
            new EnumField(2L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "OverrideValue")
        });
    }

    public static final class Codec extends GenericDataTypeCodec<OverrideValueHandling> {
        @Override
        public Class<OverrideValueHandling> getType() {
            return OverrideValueHandling.class;
        }

        @Override
        public OverrideValueHandling decode(SerializationContext context, UaDecoder decoder) {
            return decoder.readEnum(null, OverrideValueHandling.class);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           OverrideValueHandling value) {
            encoder.writeEnum(null, value);
        }
    }
}