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

public enum BrowseResultMask implements UaEnumeration {
    None(0),

    ReferenceTypeId(1),

    IsForward(2),

    NodeClass(4),

    BrowseName(8),

    DisplayName(16),

    TypeDefinition(32),

    All(63),

    ReferenceTypeInfo(3),

    TargetInfo(60);

    private final int value;

    BrowseResultMask(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    public static ExpandedNodeId getTypeId() {
        return ExpandedNodeId.parse("ns=0;i=517");
    }

    public static @Nullable BrowseResultMask from(int value) {
        switch (value) {
            case 0:
                return None;
            case 1:
                return ReferenceTypeId;
            case 2:
                return IsForward;
            case 4:
                return NodeClass;
            case 8:
                return BrowseName;
            case 16:
                return DisplayName;
            case 32:
                return TypeDefinition;
            case 63:
                return All;
            case 3:
                return ReferenceTypeInfo;
            case 60:
                return TargetInfo;
            default:
                return null;
        }
    }

    public static EnumDefinition definition() {
        return new EnumDefinition(new EnumField[]{
            new EnumField(0L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "None"),
            new EnumField(1L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "ReferenceTypeId"),
            new EnumField(2L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "IsForward"),
            new EnumField(4L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "NodeClass"),
            new EnumField(8L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "BrowseName"),
            new EnumField(16L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "DisplayName"),
            new EnumField(32L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "TypeDefinition"),
            new EnumField(63L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "All"),
            new EnumField(3L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "ReferenceTypeInfo"),
            new EnumField(60L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "TargetInfo")
        });
    }

    public static final class Codec extends GenericDataTypeCodec<BrowseResultMask> {
        @Override
        public Class<BrowseResultMask> getType() {
            return BrowseResultMask.class;
        }

        @Override
        public BrowseResultMask decode(SerializationContext context, UaDecoder decoder) {
            return decoder.readEnum(null, org.eclipse.milo.opcua.stack.core.types.enumerated.BrowseResultMask.class);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, BrowseResultMask value) {
            encoder.writeEnum(null, value);
        }
    }
}
