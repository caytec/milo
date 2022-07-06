/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.serialization;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Base64;
import java.util.Random;
import java.util.UUID;

import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.XmlElement;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.ULong;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;
import org.eclipse.milo.opcua.stack.core.types.enumerated.ApplicationType;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;
import org.eclipse.milo.opcua.stack.core.types.structured.RequestHeader;
import org.eclipse.milo.opcua.stack.core.util.Namespaces;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ubyte;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ushort;
import static org.junit.jupiter.api.Assertions.assertEquals;

class OpcUaJsonEncoderTest {

    @Test
    void writeBoolean() throws IOException {
        var writer = new StringWriter();
        var encoder = new OpcUaJsonEncoder(writer);

        encoder.writeBoolean(null, true);
        assertEquals("true", writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.writeBoolean(null, false);
        assertEquals("false", writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.jsonWriter.beginObject();
        encoder.writeBoolean("foo", true);
        encoder.jsonWriter.endObject();
        assertEquals("{\"foo\":true}", writer.toString());
    }

    @Test
    public void writeSByte() throws IOException {
        var writer = new StringWriter();
        var encoder = new OpcUaJsonEncoder(writer);

        encoder.writeSByte(null, (byte) 0);
        assertEquals("0", writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.writeSByte(null, Byte.MIN_VALUE);
        assertEquals(String.valueOf(Byte.MIN_VALUE), writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.writeSByte(null, Byte.MAX_VALUE);
        assertEquals(String.valueOf(Byte.MAX_VALUE), writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.jsonWriter.beginObject();
        encoder.writeSByte("foo", (byte) 0);
        encoder.jsonWriter.endObject();
        assertEquals("{\"foo\":0}", writer.toString());
    }

    @Test
    public void writeInt16() throws IOException {
        var writer = new StringWriter();
        var encoder = new OpcUaJsonEncoder(writer);

        encoder.writeInt16(null, (short) 0);
        assertEquals("0", writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.writeInt16(null, Short.MIN_VALUE);
        assertEquals(String.valueOf(Short.MIN_VALUE), writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.writeInt16(null, Short.MAX_VALUE);
        assertEquals(String.valueOf(Short.MAX_VALUE), writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.jsonWriter.beginObject();
        encoder.writeInt16("foo", (short) 0);
        encoder.jsonWriter.endObject();
        assertEquals("{\"foo\":0}", writer.toString());
    }

    @Test
    public void writeInt32() throws IOException {
        var writer = new StringWriter();
        var encoder = new OpcUaJsonEncoder(writer);

        encoder.writeInt32(null, 0);
        assertEquals("0", writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.writeInt32(null, Integer.MIN_VALUE);
        assertEquals(String.valueOf(Integer.MIN_VALUE), writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.writeInt32(null, Integer.MAX_VALUE);
        assertEquals(String.valueOf(Integer.MAX_VALUE), writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.jsonWriter.beginObject();
        encoder.writeInt32("foo", 0);
        encoder.jsonWriter.endObject();
        assertEquals("{\"foo\":0}", writer.toString());
    }

    @Test
    public void writeInt64() throws IOException {
        var writer = new StringWriter();
        var encoder = new OpcUaJsonEncoder(writer);

        // Int64 and UInt64 values shall be formatted as a decimal number
        // encoded as a JSON string.

        encoder.writeInt64(null, 0L);
        assertEquals("\"0\"", writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.writeInt64(null, Long.MIN_VALUE);
        assertEquals("\"" + Long.MIN_VALUE + "\"", writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.writeInt64(null, Long.MAX_VALUE);
        assertEquals("\"" + Long.MAX_VALUE + "\"", writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.jsonWriter.beginObject();
        encoder.writeInt64("foo", Long.MAX_VALUE);
        encoder.jsonWriter.endObject();
        assertEquals("{\"foo\":\"" + Long.MAX_VALUE + "\"}", writer.toString());
    }

    @Test
    public void writeByte() throws IOException {
        var writer = new StringWriter();
        var encoder = new OpcUaJsonEncoder(writer);

        encoder.writeByte(null, ubyte(0));
        assertEquals("0", writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.writeByte(null, UByte.MAX);
        assertEquals(String.valueOf(UByte.MAX_VALUE), writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.jsonWriter.beginObject();
        encoder.writeByte("foo", ubyte(0));
        encoder.jsonWriter.endObject();
        assertEquals("{\"foo\":0}", writer.toString());
    }

    @Test
    public void writeUInt16() throws IOException {
        var writer = new StringWriter();
        var encoder = new OpcUaJsonEncoder(writer);

        encoder.writeUInt16(null, ushort(0));
        assertEquals("0", writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.writeUInt16(null, UShort.MAX);
        assertEquals(String.valueOf(UShort.MAX_VALUE), writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.jsonWriter.beginObject();
        encoder.writeUInt16("foo", ushort(0));
        encoder.jsonWriter.endObject();
        assertEquals("{\"foo\":0}", writer.toString());
    }

    @Test
    public void writeUInt32() throws IOException {
        var writer = new StringWriter();
        var encoder = new OpcUaJsonEncoder(writer);

        encoder.writeUInt32(null, uint(0));
        assertEquals("0", writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.writeUInt32(null, UInteger.MAX);
        assertEquals(String.valueOf(UInteger.MAX_VALUE), writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.jsonWriter.beginObject();
        encoder.writeUInt32("foo", uint(0));
        encoder.jsonWriter.endObject();
        assertEquals("{\"foo\":0}", writer.toString());
    }

    @Test
    public void writeUInt64() throws IOException {
        var writer = new StringWriter();
        var encoder = new OpcUaJsonEncoder(writer);

        // Int64 and UInt64 values shall be formatted as a decimal number
        // encoded as a JSON string (See the XML encoding of 64-bit values
        // described in 5.3.1.3).

        {
            encoder.writeUInt64(null, ULong.MIN);
            assertEquals("\"" + ULong.MIN_VALUE + "\"", writer.toString());

            encoder.reset(writer = new StringWriter());
            encoder.writeUInt64(null, ULong.MAX);
            assertEquals("\"" + ULong.MAX_VALUE + "\"", writer.toString());

        }

        {
            encoder.reset(writer = new StringWriter());
            encoder.jsonWriter.beginObject();
            encoder.writeUInt64("foo", ULong.MAX);
            encoder.jsonWriter.endObject();
            assertEquals("{\"foo\":\"" + ULong.MAX_VALUE + "\"}", writer.toString());
        }
    }

    @Test
    public void writeFloat() throws IOException {
        var writer = new StringWriter();
        var encoder = new OpcUaJsonEncoder(writer);

        {
            // Normal Float and Double values shall be encoded as a JSON number.

            encoder.writeFloat(null, 0.0f);
            assertEquals("0.0", writer.toString());

            encoder.reset(writer = new StringWriter());
            encoder.writeFloat(null, Float.MIN_VALUE);
            assertEquals(String.valueOf(Float.MIN_VALUE), writer.toString());

            encoder.reset(writer = new StringWriter());
            encoder.writeFloat(null, Float.MAX_VALUE);
            assertEquals(String.valueOf(Float.MAX_VALUE), writer.toString());

            // Special floating-point numbers such as positive infinity (INF),
            // negative infinity (-INF) and not-a- number (NaN) shall be
            // represented by the values "Infinity", "-Infinity" and "NaN" encoded
            // as a JSON string.

            encoder.reset(writer = new StringWriter());
            encoder.writeFloat(null, Float.POSITIVE_INFINITY);
            assertEquals("\"Infinity\"", writer.toString());

            encoder.reset(writer = new StringWriter());
            encoder.writeFloat(null, Float.NEGATIVE_INFINITY);
            assertEquals("\"-Infinity\"", writer.toString());

            encoder.reset(writer = new StringWriter());
            encoder.writeFloat(null, Float.NaN);
            assertEquals("\"NaN\"", writer.toString());
        }

        {
            encoder.reset(writer = new StringWriter());
            encoder.jsonWriter.beginObject();
            encoder.writeFloat("foo", 0.0f);
            encoder.jsonWriter.endObject();
            assertEquals("{\"foo\":0.0}", writer.toString());
        }
    }

    @Test
    public void writeDouble() throws IOException {
        var writer = new StringWriter();
        var encoder = new OpcUaJsonEncoder(writer);

        {
            // Normal Float and Double values shall be encoded as a JSON number.

            encoder.writeDouble(null, 0.0);
            assertEquals("0.0", writer.toString());

            encoder.reset(writer = new StringWriter());
            encoder.writeDouble(null, Double.MIN_VALUE);
            assertEquals(String.valueOf(Double.MIN_VALUE), writer.toString());

            encoder.reset(writer = new StringWriter());
            encoder.writeDouble(null, Double.MAX_VALUE);
            assertEquals(String.valueOf(Double.MAX_VALUE), writer.toString());

            // Special floating-point numbers such as positive infinity (INF),
            // negative infinity (-INF) and not-a- number (NaN) shall be
            // represented by the values "Infinity", "-Infinity" and "NaN" encoded
            // as a JSON string.

            encoder.reset(writer = new StringWriter());
            encoder.writeDouble(null, Double.POSITIVE_INFINITY);
            assertEquals("\"Infinity\"", writer.toString());

            encoder.reset(writer = new StringWriter());
            encoder.writeDouble(null, Double.NEGATIVE_INFINITY);
            assertEquals("\"-Infinity\"", writer.toString());

            encoder.reset(writer = new StringWriter());
            encoder.writeDouble(null, Double.NaN);
            assertEquals("\"NaN\"", writer.toString());
        }

        {
            encoder.reset(writer = new StringWriter());
            encoder.jsonWriter.beginObject();
            encoder.writeDouble("foo", 0.0);
            encoder.jsonWriter.endObject();
            assertEquals("{\"foo\":0.0}", writer.toString());
        }
    }

    @Test
    public void writeString() throws IOException {
        var writer = new StringWriter();
        var encoder = new OpcUaJsonEncoder(writer);

        // String values shall be encoded as JSON strings.
        // Any characters which are not allowed in JSON strings are escaped
        // using the rules defined in RFC 7159.

        encoder.writeString(null, "");
        assertEquals("\"\"", writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.writeString(null, "foo");
        assertEquals("\"foo\"", writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.writeString(null, "\"quoted\"");
        assertEquals("\"\\\"quoted\\\"\"", writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.jsonWriter.beginObject();
        encoder.writeString("foo", "bar");
        encoder.jsonWriter.endObject();
        assertEquals("{\"foo\":\"bar\"}", writer.toString());
    }

    @Test
    public void writeDateTime() throws IOException {
        var writer = new StringWriter();
        var encoder = new OpcUaJsonEncoder(writer);

        {
            // DateTime values shall be formatted as specified by ISO 8601:2004
            // and encoded as a JSON string.

            encoder.writeDateTime(null, OpcUaJsonEncoder.MIN_DATE_TIME);
            assertEquals("\"" + OpcUaJsonEncoder.MIN_ISO_8601 + "\"", writer.toString());

            encoder.reset(writer = new StringWriter());
            encoder.writeDateTime(null, OpcUaJsonEncoder.MAX_DATE_TIME);
            assertEquals("\"9999-12-31T23:59:59Z\"", writer.toString());

            DateTime now = DateTime.now();
            String isoNow = OpcUaJsonEncoder.dateTimeToIso8601UtcString(now);
            encoder.reset(writer = new StringWriter());
            encoder.writeDateTime(null, now);
            assertEquals("\"" + isoNow + "\"", writer.toString());

            // DateTime values which exceed the minimum or maximum values supported
            // on a platform shall be encoded as "0001-01-01T00:00:00Z" or
            // "9999-12-31T23:59:59Z" respectively. During decoding, these values
            // shall be converted to the minimum or maximum values supported on the
            // platform.

            encoder.reset(writer = new StringWriter());
            encoder.writeDateTime(null, new DateTime(OpcUaJsonEncoder.MIN_DATE_TIME.getUtcTime() - 1));
            assertEquals("\"" + OpcUaJsonEncoder.MIN_ISO_8601 + "\"", writer.toString());

            encoder.reset(writer = new StringWriter());
            encoder.writeDateTime(null, new DateTime(OpcUaJsonEncoder.MAX_DATE_TIME.getUtcTime() + 1));
            assertEquals("\"" + OpcUaJsonEncoder.MAX_ISO_8601 + "\"", writer.toString());
        }

        {
            DateTime now = DateTime.now();
            String isoNow = OpcUaJsonEncoder.dateTimeToIso8601UtcString(now);

            encoder.reset(writer = new StringWriter());
            encoder.jsonWriter.beginObject();
            encoder.writeDateTime("foo", now);
            encoder.jsonWriter.endObject();
            assertEquals("{\"foo\":\"" + isoNow + "\"}", writer.toString());
        }
    }

    @Test
    public void writeGuid() throws IOException {
        var writer = new StringWriter();
        var encoder = new OpcUaJsonEncoder(writer);

        encoder.writeGuid(null, UUID.fromString("00000000-0000-0000-0000-000000000000"));
        assertEquals("\"00000000-0000-0000-0000-000000000000\"", writer.toString());

        UUID uuid = UUID.randomUUID();
        encoder.reset(writer = new StringWriter());
        encoder.writeGuid(null, uuid);
        assertEquals("\"" + uuid.toString().toUpperCase() + "\"", writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.jsonWriter.beginObject();
        encoder.writeGuid("foo", uuid);
        encoder.jsonWriter.endObject();
        assertEquals("{\"foo\":\"" + uuid.toString().toUpperCase() + "\"}", writer.toString());
    }

    @Test
    public void writeByteString() throws IOException {
        var writer = new StringWriter();
        var encoder = new OpcUaJsonEncoder(writer);

        // ByteString values shall be formatted as a Base64 text and encoded as
        // a JSON string.
        // Any characters which are not allowed in JSON strings are escaped
        // using the rules defined in RFC 7159.

        for (int i = 0; i < 100; i++) {
            ByteString bs = ByteString.of(randomBytes(16 * i));
            encoder.reset(writer = new StringWriter());
            encoder.writeByteString(null, bs);
            assertEquals("\"" + Base64.getEncoder().encodeToString(bs.bytes()) + "\"", writer.toString());
        }

        ByteString bs = ByteString.of(randomBytes(16));
        encoder.reset(writer = new StringWriter());
        encoder.jsonWriter.beginObject();
        encoder.writeByteString("foo", bs);
        encoder.jsonWriter.endObject();
        assertEquals("{\"foo\":\"" + Base64.getEncoder().encodeToString(bs.bytes()) + "\"}", writer.toString());
    }

    @Test
    public void writeXmlElement() throws IOException {
        var writer = new StringWriter();
        var encoder = new OpcUaJsonEncoder(writer);

        encoder.writeXmlElement(null, new XmlElement(""));
        assertEquals("\"\"", writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.writeXmlElement(null, new XmlElement("<foo>bar</foo>"));
        assertEquals("\"<foo>bar</foo>\"", writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.jsonWriter.beginObject();
        encoder.writeXmlElement("foo", new XmlElement("<foo>bar</foo>"));
        encoder.jsonWriter.endObject();
        assertEquals("{\"foo\":\"<foo>bar</foo>\"}", writer.toString());
    }

    @Test
    public void writeNodeId() throws IOException {
        var writer = new StringWriter();
        var encoder = new OpcUaJsonEncoder(writer);

        // IdType == UInt32, Namespace = 0, reversible
        encoder.writeNodeId(null, new NodeId(0, uint(0)));
        assertEquals("{\"Id\":0}", writer.toString());

        // IdType == UInt32, Namespace != 0, reversible
        encoder.reset(writer = new StringWriter());
        encoder.writeNodeId(null, new NodeId(1, uint(0)));
        assertEquals("{\"Id\":0,\"Namespace\":1}", writer.toString());

        // IdType == String, Namespace = 0, reversible
        encoder.reset(writer = new StringWriter());
        encoder.writeNodeId(null, new NodeId(0, "foo"));
        assertEquals("{\"IdType\":1,\"Id\":\"foo\"}", writer.toString());

        // IdType == String, Namespace != 0, reversible
        encoder.reset(writer = new StringWriter());
        encoder.writeNodeId(null, new NodeId(1, "foo"));
        assertEquals("{\"IdType\":1,\"Id\":\"foo\",\"Namespace\":1}", writer.toString());

        // IdType == Guid, Namespace = 0, reversible
        UUID uuid = UUID.randomUUID();
        encoder.reset(writer = new StringWriter());
        encoder.writeNodeId(null, new NodeId(0, uuid));
        assertEquals("{\"IdType\":2,\"Id\":\"" + uuid.toString().toUpperCase() + "\"}", writer.toString());

        // IdType == Guid, Namespace != 0, reversible
        encoder.reset(writer = new StringWriter());
        encoder.writeNodeId(null, new NodeId(1, uuid));
        assertEquals("{\"IdType\":2,\"Id\":\"" + uuid.toString().toUpperCase() + "\",\"Namespace\":1}", writer.toString());

        // IdType == ByteString, Namespace = 0, reversible
        ByteString bs = ByteString.of(randomBytes(16));
        encoder.reset(writer = new StringWriter());
        encoder.writeNodeId(null, new NodeId(0, bs));
        assertEquals("{\"IdType\":3,\"Id\":\"" + Base64.getEncoder().encodeToString(bs.bytesOrEmpty()) + "\"}", writer.toString());

        // IdType == ByteString, Namespace != 0, reversible
        encoder.reset(writer = new StringWriter());
        encoder.writeNodeId(null, new NodeId(1, bs));
        assertEquals("{\"IdType\":3,\"Id\":\"" + Base64.getEncoder().encodeToString(bs.bytesOrEmpty()) + "\",\"Namespace\":1}", writer.toString());

        encoder.reversible = false;
        encoder.serializationContext = new TestSerializationContext();
        encoder.serializationContext.getNamespaceTable().add("urn:eclipse:milo:test1");
        encoder.serializationContext.getNamespaceTable().add("urn:eclipse:milo:test2");

        // IdType == UInt32, Namespace = 0, non-reversible
        encoder.reset(writer = new StringWriter());
        encoder.writeNodeId(null, new NodeId(0, uint(0)));
        assertEquals("{\"Id\":0}", writer.toString());

        // IdType == UInt32, Namespace = 1, non-reversible
        encoder.reset(writer = new StringWriter());
        encoder.writeNodeId(null, new NodeId(1, uint(0)));
        assertEquals("{\"Id\":0,\"Namespace\":1}", writer.toString());

        // IdType == UInt32, Namespace > 1, non-reversible
        encoder.reset(writer = new StringWriter());
        encoder.writeNodeId(null, new NodeId(2, uint(0)));
        assertEquals("{\"Id\":0,\"Namespace\":\"urn:eclipse:milo:test2\"}", writer.toString());

        // Namespace > 1 but not in table, non-reversible
        encoder.reset(writer = new StringWriter());
        encoder.writeNodeId(null, new NodeId(99, uint(0)));
        assertEquals("{\"Id\":0,\"Namespace\":99}", writer.toString());

        // key != null
        encoder.reset(writer = new StringWriter());
        encoder.jsonWriter.beginObject();
        encoder.writeNodeId("foo", new NodeId(1, "foo"));
        encoder.jsonWriter.endObject();
        assertEquals("{\"foo\":{\"IdType\":1,\"Id\":\"foo\",\"Namespace\":1}}", writer.toString());
    }

    @Test
    public void writeExpandedNodeId() throws IOException {
        var writer = new StringWriter();
        var encoder = new OpcUaJsonEncoder(writer);

        // Two things differentiate the encoding of ExpandedNodeId from NodeId:
        // 1. if the namespace URI is specified it is encoded in the "Namespace" field
        // 2. if the ExpandedNodeId is non-local (server index > 0) it is encoded in the "ServerUri" field

        // reversible, namespace URI specified
        encoder.writeExpandedNodeId(null, new ExpandedNodeId(ushort(0), Namespaces.OPC_UA, "foo"));
        assertEquals("{\"IdType\":1,\"Id\":\"foo\",\"Namespace\":\"http://opcfoundation.org/UA/\"}", writer.toString());

        // reversible, remote server index
        encoder.reset(writer = new StringWriter());
        encoder.writeExpandedNodeId(null, new ExpandedNodeId(ushort(0), null, "foo", uint(1)));
        assertEquals("{\"IdType\":1,\"Id\":\"foo\",\"ServerUri\":1}", writer.toString());

        // non-reversible, remote server index
        encoder.reversible = false;
        encoder.serializationContext = new TestSerializationContext();
        encoder.serializationContext.getServerTable().add("urn:server:local");
        encoder.serializationContext.getServerTable().add("urn:server:remote");
        encoder.reset(writer = new StringWriter());
        encoder.writeExpandedNodeId(null, new ExpandedNodeId(ushort(0), null, "foo", uint(1)));
        assertEquals("{\"IdType\":1,\"Id\":\"foo\",\"ServerUri\":\"urn:server:remote\"}", writer.toString());

        // non-reversible, remote server index not in table
        encoder.reset(writer = new StringWriter());
        encoder.writeExpandedNodeId(null, new ExpandedNodeId(ushort(0), null, "foo", uint(2)));
        assertEquals("{\"IdType\":1,\"Id\":\"foo\",\"ServerUri\":2}", writer.toString());

        // reversible, field specified
        encoder.reversible = false;
        encoder.reset(writer = new StringWriter());
        encoder.jsonWriter.beginObject();
        encoder.writeExpandedNodeId("foo", new ExpandedNodeId(ushort(0), Namespaces.OPC_UA, "foo"));
        encoder.jsonWriter.endObject();
        assertEquals("{\"foo\":{\"IdType\":1,\"Id\":\"foo\",\"Namespace\":\"http://opcfoundation.org/UA/\"}}", writer.toString());
    }

    @Test
    public void writeStatusCode() throws IOException {
        var writer = new StringWriter();
        var encoder = new OpcUaJsonEncoder(writer);

        // reversible form
        {
            encoder.writeStatusCode(null, StatusCode.GOOD);
            assertEquals("0", writer.toString());

            encoder.reset(writer = new StringWriter());
            encoder.writeStatusCode(null, new StatusCode(StatusCodes.Uncertain_InitialValue));
            assertEquals(Long.toString(StatusCodes.Uncertain_InitialValue), writer.toString());

            encoder.reset(writer = new StringWriter());
            encoder.writeStatusCode(null, new StatusCode(StatusCodes.Bad_UnexpectedError));
            assertEquals(Long.toString(StatusCodes.Bad_UnexpectedError), writer.toString());
        }

        // non-reversible form
        {
            encoder.reversible = false;
            encoder.reset(writer = new StringWriter());
            encoder.writeStatusCode(null, StatusCode.GOOD);
            assertEquals("", writer.toString());

            encoder.reset(writer = new StringWriter());
            encoder.writeStatusCode(null, new StatusCode(StatusCodes.Uncertain_InitialValue));
            assertEquals("{\"Code\":1083310080,\"Symbol\":\"Uncertain_InitialValue\"}", writer.toString());

            encoder.reset(writer = new StringWriter());
            encoder.writeStatusCode(null, new StatusCode(StatusCodes.Bad_UnexpectedError));
            assertEquals("{\"Code\":2147549184,\"Symbol\":\"Bad_UnexpectedError\"}", writer.toString());
        }

        // reversible form with field
        {
            encoder.reversible = true;
            encoder.reset(writer = new StringWriter());
            encoder.jsonWriter.beginObject();
            encoder.writeStatusCode("foo", StatusCode.GOOD);
            encoder.jsonWriter.endObject();
            assertEquals("{\"foo\":0}", writer.toString());
        }

        // non-reversible form with field
        {
            encoder.reversible = false;
            encoder.reset(writer = new StringWriter());
            encoder.jsonWriter.beginObject();
            encoder.writeStatusCode("foo", StatusCode.GOOD);
            encoder.jsonWriter.endObject();
            assertEquals("{}", writer.toString()); // key/value omitted because code==0

            encoder.reset(writer = new StringWriter());
            encoder.jsonWriter.beginObject();
            encoder.writeStatusCode("foo", new StatusCode(StatusCodes.Uncertain_InitialValue));
            encoder.jsonWriter.endObject();
            assertEquals("{\"foo\":{\"Code\":1083310080,\"Symbol\":\"Uncertain_InitialValue\"}}", writer.toString());
        }
    }

    @Test
    public void writeQualifiedName() throws IOException {
        var writer = new StringWriter();
        var encoder = new OpcUaJsonEncoder(writer);

        encoder.writeQualifiedName(null, new QualifiedName(0, "foo"));
        assertEquals("{\"Name\":\"foo\"}", writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.writeQualifiedName(null, new QualifiedName(1, "foo"));
        assertEquals("{\"Name\":\"foo\",\"Uri\":1}", writer.toString());

        encoder.reversible = false;
        encoder.serializationContext = new TestSerializationContext();
        encoder.serializationContext.getNamespaceTable().add("urn:eclipse:milo:test1");
        encoder.serializationContext.getNamespaceTable().add("urn:eclipse:milo:test2");

        encoder.reset(writer = new StringWriter());
        encoder.writeQualifiedName(null, new QualifiedName(0, "foo"));
        assertEquals("{\"Name\":\"foo\"}", writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.writeQualifiedName(null, new QualifiedName(1, "foo"));
        assertEquals("{\"Name\":\"foo\",\"Uri\":1}", writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.writeQualifiedName(null, new QualifiedName(2, "foo"));
        assertEquals("{\"Name\":\"foo\",\"Uri\":\"urn:eclipse:milo:test2\"}", writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.writeQualifiedName(null, new QualifiedName(99, "foo"));
        assertEquals("{\"Name\":\"foo\",\"Uri\":99}", writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.jsonWriter.beginObject();
        encoder.writeQualifiedName("foo", new QualifiedName(0, "foo"));
        encoder.jsonWriter.endObject();
        assertEquals("{\"foo\":{\"Name\":\"foo\"}}", writer.toString());
    }

    @Test
    public void writeLocalizedText() throws IOException {
        var writer = new StringWriter();
        var encoder = new OpcUaJsonEncoder(writer);

        encoder.writeLocalizedText(null, LocalizedText.english("foo"));
        assertEquals("{\"Locale\":\"en\",\"Text\":\"foo\"}", writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.writeLocalizedText(null, new LocalizedText("en", null));
        assertEquals("{\"Locale\":\"en\",\"Text\":null}", writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.writeLocalizedText(null, new LocalizedText(null, "foo"));
        assertEquals("{\"Locale\":null,\"Text\":\"foo\"}", writer.toString());

        encoder.reversible = false;
        encoder.reset(writer = new StringWriter());
        encoder.writeLocalizedText(null, LocalizedText.english("foo"));
        assertEquals("\"foo\"", writer.toString());

        encoder.reversible = true;
        encoder.reset(writer = new StringWriter());
        encoder.jsonWriter.beginObject();
        encoder.writeLocalizedText("foo", LocalizedText.english("foo"));
        encoder.jsonWriter.endObject();
        assertEquals("{\"foo\":{\"Locale\":\"en\",\"Text\":\"foo\"}}", writer.toString());
    }

    @Test
    public void writeExtensionObject() {
        var writer = new StringWriter();
        var encoder = new OpcUaJsonEncoder(writer);

        var byteStringXo = new ExtensionObject(
            ByteString.of(new byte[]{0x00, 0x01, 0x02, 0x03}),
            new NodeId(2, 42)
        );

        var xmlElementXo = new ExtensionObject(
            new XmlElement("<foo>bar</foo>"),
            new NodeId(2, 42)
        );

        var jsonStringXo = new ExtensionObject(
            "{\"foo\":\"bar\",\"baz\":42}",
            new NodeId(2, 42)
        );

        encoder.writeExtensionObject(null, jsonStringXo);
        assertEquals("{\"TypeId\":{\"Id\":42,\"Namespace\":2},\"Body\":\"{\\\"foo\\\":\\\"bar\\\",\\\"baz\\\":42}\"}", writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.writeExtensionObject(null, xmlElementXo);
        assertEquals("{\"TypeId\":{\"Id\":42,\"Namespace\":2},\"Encoding\":2,\"Body\":\"<foo>bar</foo>\"}", writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.writeExtensionObject(null, byteStringXo);
        assertEquals("{\"TypeId\":{\"Id\":42,\"Namespace\":2},\"Encoding\":1,\"Body\":\"AAECAw==\"}", writer.toString());

        encoder.reversible = false;
        encoder.reset(writer = new StringWriter());
        encoder.writeExtensionObject(null, jsonStringXo);
        assertEquals("{\"foo\":\"bar\",\"baz\":42}", writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.writeExtensionObject(null, xmlElementXo);
        assertEquals("\"<foo>bar</foo>\"", writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.writeExtensionObject(null, byteStringXo);
        assertEquals("\"AAECAw==\"", writer.toString());
    }

    @Test
    public void writeDataValue() throws IOException {
        var writer = new StringWriter();
        var encoder = new OpcUaJsonEncoder(writer);

        DateTime now = DateTime.now();
        String isoNow = OpcUaJsonEncoder.dateTimeToIso8601UtcString(now);

        DataValue allFieldsValue = new DataValue(
            new Variant("foo"),
            new StatusCode(StatusCodes.Good_Overload),
            now,
            ushort(100),
            now,
            ushort(200)
        );

        encoder.reset(writer = new StringWriter());
        encoder.writeDataValue(null, allFieldsValue);
        assertEquals(String.format("{\"Value\":{\"Type\":12,\"Body\":\"foo\"},\"Status\":3080192,\"SourceTimestamp\":\"%s\",\"SourcePicoseconds\":100,\"ServerTimestamp\":\"%s\",\"ServerPicoseconds\":200}", isoNow, isoNow), writer.toString());

        // omit "Value"
        encoder.reset(writer = new StringWriter());
        encoder.writeDataValue(null, allFieldsValue.copy(b -> b.setValue(Variant.NULL_VALUE)));
        assertEquals(String.format("{\"Status\":3080192,\"SourceTimestamp\":\"%s\",\"SourcePicoseconds\":100,\"ServerTimestamp\":\"%s\",\"ServerPicoseconds\":200}", isoNow, isoNow), writer.toString());

        // omit "Status"
        encoder.reset(writer = new StringWriter());
        encoder.writeDataValue(null, allFieldsValue.copy(b -> b.setStatus(StatusCode.GOOD)));
        assertEquals(String.format("{\"Value\":{\"Type\":12,\"Body\":\"foo\"},\"SourceTimestamp\":\"%s\",\"SourcePicoseconds\":100,\"ServerTimestamp\":\"%s\",\"ServerPicoseconds\":200}", isoNow, isoNow), writer.toString());

        // omit "SourceTimestamp"
        encoder.reset(writer = new StringWriter());
        encoder.writeDataValue(null, allFieldsValue.copy(b -> b.setSourceTime(null)));
        assertEquals(String.format("{\"Value\":{\"Type\":12,\"Body\":\"foo\"},\"Status\":3080192,\"SourcePicoseconds\":100,\"ServerTimestamp\":\"%s\",\"ServerPicoseconds\":200}", isoNow), writer.toString());

        // omit "SourcePicoseconds
        encoder.reset(writer = new StringWriter());
        encoder.writeDataValue(null, allFieldsValue.copy(b -> b.setSourcePicoseconds(null)));
        assertEquals(String.format("{\"Value\":{\"Type\":12,\"Body\":\"foo\"},\"Status\":3080192,\"SourceTimestamp\":\"%s\",\"ServerTimestamp\":\"%s\",\"ServerPicoseconds\":200}", isoNow, isoNow), writer.toString());

        // omit "ServerTimestamp"
        encoder.reset(writer = new StringWriter());
        encoder.writeDataValue(null, allFieldsValue.copy(b -> b.setServerTime(null)));
        assertEquals(String.format("{\"Value\":{\"Type\":12,\"Body\":\"foo\"},\"Status\":3080192,\"SourceTimestamp\":\"%s\",\"SourcePicoseconds\":100,\"ServerPicoseconds\":200}", isoNow), writer.toString());

        // omit "ServerPicoseconds"
        encoder.reset(writer = new StringWriter());
        encoder.writeDataValue(null, allFieldsValue.copy(b -> b.setServerPicoseconds(null)));
        assertEquals(String.format("{\"Value\":{\"Type\":12,\"Body\":\"foo\"},\"Status\":3080192,\"SourceTimestamp\":\"%s\",\"SourcePicoseconds\":100,\"ServerTimestamp\":\"%s\"}", isoNow, isoNow), writer.toString());

        // omit all fields
        encoder.reset(writer = new StringWriter());
        encoder.writeDataValue(null, new DataValue(Variant.NULL_VALUE, null, null));
        assertEquals("", writer.toString());

        // omit all fields while embedded in object
        encoder.reset(writer = new StringWriter());
        encoder.jsonWriter.beginObject();
        encoder.writeDataValue("foo", new DataValue(Variant.NULL_VALUE, null, null));
        encoder.jsonWriter.endObject();
        assertEquals("{}", writer.toString());
    }

    @Test
    public void writeVariant() {
        var writer = new StringWriter();
        var encoder = new OpcUaJsonEncoder(writer);

        //region reversible
        encoder.reset(writer = new StringWriter());
        encoder.writeVariant(null, new Variant(true));
        assertEquals("{\"Type\":1,\"Body\":true}", writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.writeVariant(null, new Variant(new QualifiedName(1, "foo")));
        assertEquals("{\"Type\":20,\"Body\":{\"Name\":\"foo\",\"Uri\":1}}", writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.writeVariant(null, new Variant(new Variant[]{new Variant("foo"), new Variant("bar")}));
        assertEquals("{\"Type\":24,\"Body\":[{\"Type\":12,\"Body\":\"foo\"},{\"Type\":12,\"Body\":\"bar\"}]}", writer.toString());
        //endregion

        //region non-reversible
        encoder.reversible = false;
        encoder.reset(writer = new StringWriter());
        encoder.writeVariant(null, new Variant(true));
        assertEquals("true", writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.writeVariant(null, new Variant(new QualifiedName(1, "foo")));
        assertEquals("{\"Name\":\"foo\",\"Uri\":1}", writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.writeVariant(null, new Variant(new Variant[]{new Variant("foo"), new Variant("bar")}));
        assertEquals("[\"foo\",\"bar\"]", writer.toString());
        //endregion

        int[] value1d = {0, 1, 2, 3};
        int[][] value2d = {
            {0, 2, 3},
            {1, 3, 4}
        };
        int[][][] value3d = {
            {
                {0, 1},
                {2, 3}
            },
            {
                {4, 5},
                {6, 7},
            }
        };

        //region Arrays, reversible
        encoder.reversible = true;
        encoder.reset(writer = new StringWriter());
        encoder.writeVariant(null, new Variant(value1d));
        assertEquals("{\"Type\":6,\"Body\":[0,1,2,3]}", writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.writeVariant(null, new Variant(value2d));
        assertEquals("{\"Type\":6,\"Body\":[0,2,3,1,3,4],\"Dimensions\":[2,3]}", writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.writeVariant(null, new Variant(value3d));
        assertEquals("{\"Type\":6,\"Body\":[0,1,2,3,4,5,6,7],\"Dimensions\":[2,2,2]}", writer.toString());
        //endregion

        //region Arrays, non-reversible
        encoder.reversible = false;

        encoder.reset(writer = new StringWriter());
        encoder.writeVariant(null, new Variant(value1d));
        assertEquals("[0,1,2,3]", writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.writeVariant(null, new Variant(value2d));
        assertEquals("[[0,2,3],[1,3,4]]", writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.writeVariant(null, new Variant(value3d));
        assertEquals("[[[0,1],[2,3]],[[4,5],[6,7]]]", writer.toString());
        //endregion
    }

    @Test
    public void writeDiagnosticInfo() throws IOException {
        var writer = new StringWriter();
        var encoder = new OpcUaJsonEncoder(writer);

        var diagnosticInfo = new DiagnosticInfo(
            0,
            1,
            2,
            3,
            "foo",
            null,
            null
        );

        var nestedDiagnosticInfo = new DiagnosticInfo(
            4,
            5,
            6,
            7,
            "bar",
            StatusCode.GOOD,
            diagnosticInfo
        );

        encoder.writeDiagnosticInfo(null, diagnosticInfo);
        assertEquals("{\"SymbolicId\":1,\"NamespaceUri\":0,\"Locale\":2,\"LocalizedText\":3,\"AdditionalInfo\":\"foo\"}", writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.writeDiagnosticInfo(null, nestedDiagnosticInfo);
        assertEquals("{\"SymbolicId\":5,\"NamespaceUri\":4,\"Locale\":6,\"LocalizedText\":7,\"AdditionalInfo\":\"bar\",\"InnerStatusCode\":0,\"InnerDiagnosticInfo\":{\"SymbolicId\":1,\"NamespaceUri\":0,\"Locale\":2,\"LocalizedText\":3,\"AdditionalInfo\":\"foo\"}}", writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.jsonWriter.beginObject();
        encoder.writeDiagnosticInfo("foo", diagnosticInfo);
        encoder.jsonWriter.endObject();
        assertEquals("{\"foo\":{\"SymbolicId\":1,\"NamespaceUri\":0,\"Locale\":2,\"LocalizedText\":3,\"AdditionalInfo\":\"foo\"}}", writer.toString());
    }

    @Disabled
    @Test
    public void writeMessage() {
        var writer = new StringWriter();
        var encoder = new OpcUaJsonEncoder(writer);
        encoder.serializationContext = new TestSerializationContext();

        var message = new ReadRequest(
            new RequestHeader(
                NodeId.NULL_VALUE,
                DateTime.NULL_VALUE,
                uint(0),
                uint(0),
                "foo",
                uint(0),
                null
            ),
            0.0,
            TimestampsToReturn.Both,
            new ReadValueId[]{
                new ReadValueId(
                    new NodeId(0, 1),
                    uint(13),
                    null,
                    QualifiedName.NULL_VALUE)
            }
        );

        encoder.reset(writer = new StringWriter());
        encoder.writeMessage(null, message);
        assertEquals("TODO", writer.toString());
    }

    @Test
    public void writeEnum() throws IOException {
        var writer = new StringWriter();
        var encoder = new OpcUaJsonEncoder(writer);

        for (ApplicationType applicationType : ApplicationType.values()) {
            encoder.reset(writer = new StringWriter());
            encoder.writeEnum(null, applicationType);
            assertEquals(String.valueOf(applicationType.getValue()), writer.toString());

            encoder.reset(writer = new StringWriter());
            encoder.jsonWriter.beginObject();
            encoder.writeEnum("foo", applicationType);
            encoder.jsonWriter.endObject();
            assertEquals(String.format("{\"foo\":%d}", applicationType.getValue()), writer.toString());
        }
    }

    @Test
    @Disabled
    public void writeStruct() {
        // TODO
    }

    @Test
    public void writeBooleanArray() throws IOException {
        var writer = new StringWriter();
        var encoder = new OpcUaJsonEncoder(writer);
        encoder.serializationContext = new TestSerializationContext();

        encoder.reset(writer = new StringWriter());
        encoder.writeBooleanArray(null, null);
        assertEquals("", writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.writeBooleanArray(null, new Boolean[]{});
        assertEquals("[]", writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.writeBooleanArray(null, new Boolean[]{true, false, true});
        assertEquals("[true,false,true]", writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.writeBooleanArray(null, new Boolean[]{true, false, null});
        assertEquals("[true,false,null]", writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.jsonWriter.beginObject();
        encoder.writeBooleanArray("foo", new Boolean[]{true, false, true});
        encoder.jsonWriter.endObject();
        assertEquals("{\"foo\":[true,false,true]}", writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.jsonWriter.beginObject();
        encoder.writeBooleanArray("foo", null);
        encoder.jsonWriter.endObject();
        assertEquals("{}", writer.toString());
    }

    private static byte[] randomBytes(int length) {
        var random = new Random();
        var bs = new byte[length];
        random.nextBytes(bs);
        return bs;
    }

}