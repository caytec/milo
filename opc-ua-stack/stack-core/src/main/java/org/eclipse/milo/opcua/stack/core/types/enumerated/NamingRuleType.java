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
 * @see <a href="https://reference.opcfoundation.org/v104/Core/docs/Part3/8.29">https://reference.opcfoundation.org/v104/Core/docs/Part3/8.29</a>
 */
public enum NamingRuleType implements UaEnumeration {
    /**
     * The BrowseName must appear in all instances of the type.
     */
    Mandatory(1),

    /**
     * The BrowseName may appear in an instance of the type.
     */
    Optional(2),

    /**
     * The modelling rule defines a constraint and the BrowseName is not used in an instance of the type.
     */
    Constraint(3);

    private final int value;

    NamingRuleType(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    public static ExpandedNodeId getTypeId() {
        return ExpandedNodeId.parse("ns=0;i=120");
    }

    public static @Nullable NamingRuleType from(int value) {
        switch (value) {
            case 1:
                return Mandatory;
            case 2:
                return Optional;
            case 3:
                return Constraint;
            default:
                return null;
        }
    }

    public static EnumDefinition definition() {
        return new EnumDefinition(new EnumField[]{
            new EnumField(1L, LocalizedText.NULL_VALUE, new LocalizedText("", "The BrowseName must appear in all instances of the type."), "Mandatory"),
            new EnumField(2L, LocalizedText.NULL_VALUE, new LocalizedText("", "The BrowseName may appear in an instance of the type."), "Optional"),
            new EnumField(3L, LocalizedText.NULL_VALUE, new LocalizedText("", "The modelling rule defines a constraint and the BrowseName is not used in an instance of the type."), "Constraint")
        });
    }

    public static final class Codec extends GenericDataTypeCodec<NamingRuleType> {
        @Override
        public Class<NamingRuleType> getType() {
            return NamingRuleType.class;
        }

        @Override
        public NamingRuleType decode(SerializationContext context, UaDecoder decoder) {
            return decoder.readEnum(null, org.eclipse.milo.opcua.stack.core.types.enumerated.NamingRuleType.class);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, NamingRuleType value) {
            encoder.writeEnum(null, value);
        }
    }
}
