/*
 * Copyright (c) 2023 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.types.structured;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.eclipse.milo.opcua.stack.core.types.builtin.OptionSetUI16;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part14/6.2.3/#6.2.3.2.4">https://reference.opcfoundation.org/v105/Core/docs/Part14/6.2.3/#6.2.3.2.4</a>
 */
@EqualsAndHashCode(
    callSuper = true
)
@ToString
public class DataSetFieldFlags extends OptionSetUI16<DataSetFieldFlags.Field> {
    public DataSetFieldFlags(UShort value) {
        super(value);
    }

    public boolean getPromotedField() {
        return get(Field.PromotedField);
    }

    @Override
    public UShort getValue() {
        return (UShort) value;
    }

    @Override
    public Set<Field> toSet() {
        return Arrays.stream(Field.values())
            .filter(this::get)
            .collect(Collectors.toSet());
    }

    public static DataSetFieldFlags of(Field... fields) {
        long bits = 0L;

        for (Field f : fields) {
            bits |= (1L << f.bitIndex);
        }

        return new DataSetFieldFlags(UShort.valueOf(bits));
    }

    public enum Field implements BitIndex {
        PromotedField(0);

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
