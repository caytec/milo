/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.model.variables;

import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.enumerated.AxisScaleEnumeration;
import org.eclipse.milo.opcua.stack.core.types.structured.EUInformation;
import org.eclipse.milo.opcua.stack.core.types.structured.Range;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part8/5.3.4/#5.3.4.1">https://reference.opcfoundation.org/v105/Core/docs/Part8/5.3.4/#5.3.4.1</a>
 */
public interface ArrayItemType extends DataItemType {
    QualifiedProperty<Range> INSTRUMENT_RANGE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "InstrumentRange",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=884"),
        -1,
        Range.class
    );

    QualifiedProperty<Range> EU_RANGE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "EURange",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=884"),
        -1,
        Range.class
    );

    QualifiedProperty<EUInformation> ENGINEERING_UNITS = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "EngineeringUnits",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=887"),
        -1,
        EUInformation.class
    );

    QualifiedProperty<LocalizedText> TITLE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "Title",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=21"),
        -1,
        LocalizedText.class
    );

    QualifiedProperty<AxisScaleEnumeration> AXIS_SCALE_TYPE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "AxisScaleType",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12077"),
        -1,
        AxisScaleEnumeration.class
    );

    Range getInstrumentRange();

    void setInstrumentRange(Range value);

    PropertyType getInstrumentRangeNode();

    Range getEuRange();

    void setEuRange(Range value);

    PropertyType getEuRangeNode();

    EUInformation getEngineeringUnits();

    void setEngineeringUnits(EUInformation value);

    PropertyType getEngineeringUnitsNode();

    LocalizedText getTitle();

    void setTitle(LocalizedText value);

    PropertyType getTitleNode();

    AxisScaleEnumeration getAxisScaleType();

    void setAxisScaleType(AxisScaleEnumeration value);

    PropertyType getAxisScaleTypeNode();
}