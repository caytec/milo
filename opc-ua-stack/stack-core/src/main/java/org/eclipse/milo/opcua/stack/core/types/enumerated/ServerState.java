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
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part5/12.6">https://reference.opcfoundation.org/v105/Core/docs/Part5/12.6</a>
 */
public enum ServerState implements UaEnumeration {
    Running(0),

    Failed(1),

    NoConfiguration(2),

    Suspended(3),

    Shutdown(4),

    Test(5),

    CommunicationFault(6),

    Unknown(7);

    private final int value;

    ServerState(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    public static ExpandedNodeId getTypeId() {
        return ExpandedNodeId.parse("ns=0;i=852");
    }

    public static @Nullable ServerState from(int value) {
        switch (value) {
            case 0:
                return Running;
            case 1:
                return Failed;
            case 2:
                return NoConfiguration;
            case 3:
                return Suspended;
            case 4:
                return Shutdown;
            case 5:
                return Test;
            case 6:
                return CommunicationFault;
            case 7:
                return Unknown;
            default:
                return null;
        }
    }

    public static EnumDefinition definition() {
        return new EnumDefinition(new EnumField[]{
            new EnumField(0L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "Running"),
            new EnumField(1L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "Failed"),
            new EnumField(2L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "NoConfiguration"),
            new EnumField(3L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "Suspended"),
            new EnumField(4L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "Shutdown"),
            new EnumField(5L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "Test"),
            new EnumField(6L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "CommunicationFault"),
            new EnumField(7L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "Unknown")
        });
    }

    public static final class Codec extends GenericDataTypeCodec<ServerState> {
        @Override
        public Class<ServerState> getType() {
            return ServerState.class;
        }

        @Override
        public ServerState decode(SerializationContext context, UaDecoder decoder) {
            return decoder.readEnum(null, ServerState.class);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, ServerState value) {
            encoder.writeEnum(null, value);
        }
    }
}
