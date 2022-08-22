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
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part22/5.3.1/#5.3.1.3">https://reference.opcfoundation.org/v105/Core/docs/Part22/5.3.1/#5.3.1.3</a>
 */
public enum InterfaceOperStatus implements UaEnumeration {
    /**
     * Ready to pass packets.
     */
    Up(0),

    /**
     * The interface does not pass any packets.
     */
    Down(1),

    /**
     * In some test mode. No operational packets can be passed.
     */
    Testing(2),

    /**
     * Status cannot be determined for some reason.
     */
    Unknown(3),

    /**
     * Waiting for some external event.
     */
    Dormant(4),

    /**
     * Some component (typically hardware) is missing.
     */
    NotPresent(5),

    /**
     * Down due to state of lower-layer interface(s).
     */
    LowerLayerDown(6);

    private final int value;

    InterfaceOperStatus(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    public static ExpandedNodeId getTypeId() {
        return ExpandedNodeId.parse("ns=0;i=24214");
    }

    public static @Nullable InterfaceOperStatus from(int value) {
        switch (value) {
            case 0:
                return Up;
            case 1:
                return Down;
            case 2:
                return Testing;
            case 3:
                return Unknown;
            case 4:
                return Dormant;
            case 5:
                return NotPresent;
            case 6:
                return LowerLayerDown;
            default:
                return null;
        }
    }

    public static EnumDefinition definition() {
        return new EnumDefinition(new EnumField[]{
            new EnumField(0L, LocalizedText.NULL_VALUE, new LocalizedText("", "Ready to pass packets."), "Up"),
            new EnumField(1L, LocalizedText.NULL_VALUE, new LocalizedText("", "The interface does not pass any packets."), "Down"),
            new EnumField(2L, LocalizedText.NULL_VALUE, new LocalizedText("", "In some test mode. No operational packets can be passed."), "Testing"),
            new EnumField(3L, LocalizedText.NULL_VALUE, new LocalizedText("", "Status cannot be determined for some reason."), "Unknown"),
            new EnumField(4L, LocalizedText.NULL_VALUE, new LocalizedText("", "Waiting for some external event."), "Dormant"),
            new EnumField(5L, LocalizedText.NULL_VALUE, new LocalizedText("", "Some component (typically hardware) is missing."), "NotPresent"),
            new EnumField(6L, LocalizedText.NULL_VALUE, new LocalizedText("", "Down due to state of lower-layer interface(s)."), "LowerLayerDown")
        });
    }

    public static final class Codec extends GenericDataTypeCodec<InterfaceOperStatus> {
        @Override
        public Class<InterfaceOperStatus> getType() {
            return InterfaceOperStatus.class;
        }

        @Override
        public InterfaceOperStatus decode(SerializationContext context, UaDecoder decoder) {
            return decoder.readEnum(null, org.eclipse.milo.opcua.stack.core.types.enumerated.InterfaceOperStatus.class);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, InterfaceOperStatus value) {
            encoder.writeEnum(null, value);
        }
    }
}
