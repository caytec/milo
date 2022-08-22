package org.eclipse.milo.opcua.stack.core.types.structured;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.eclipse.milo.opcua.stack.core.types.builtin.OptionSetUInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part5/12.2.9/#12.2.9.13">https://reference.opcfoundation.org/v105/Core/docs/Part5/12.2.9/#12.2.9.13</a>
 */
@EqualsAndHashCode(
    callSuper = true
)
@ToString
public class AccessRestrictionType extends OptionSetUInteger<AccessRestrictionType.Field> {
    public AccessRestrictionType(UShort value) {
        super(value);
    }

    public boolean getSigningRequired() {
        return get(Field.SigningRequired);
    }

    public boolean getEncryptionRequired() {
        return get(Field.EncryptionRequired);
    }

    public boolean getSessionRequired() {
        return get(Field.SessionRequired);
    }

    public boolean getApplyRestrictionsToBrowse() {
        return get(Field.ApplyRestrictionsToBrowse);
    }

    @Override
    public UShort getValue() {
        return (UShort) value;
    }

    @Override
    public Set<AccessRestrictionType.Field> toSet() {
        return Arrays.stream(Field.values())
            .filter(this::get)
            .collect(Collectors.toSet());
    }

    public static AccessRestrictionType of(AccessRestrictionType.Field... fields) {
        long bits = 0L;

        for (Field f : fields) {
            bits |= (1L << f.bitIndex);
        }

        return new AccessRestrictionType(UShort.valueOf(bits));
    }

    public enum Field implements OptionSetUInteger.BitIndex {
        SigningRequired(0),

        EncryptionRequired(1),

        SessionRequired(2),

        ApplyRestrictionsToBrowse(3);

        private final int bitIndex;

        Field(int bitIndex) {
            this.bitIndex = bitIndex;
        }

        @Override
        public int getBitIndex() {
            return bitIndex;
        }
    }
}
