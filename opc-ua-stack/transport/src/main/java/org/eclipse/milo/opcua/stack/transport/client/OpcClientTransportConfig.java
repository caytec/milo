/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.transport.client;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;

import io.netty.channel.EventLoopGroup;
import io.netty.util.HashedWheelTimer;
import org.eclipse.milo.opcua.stack.core.channel.EncodingLimits;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public interface OpcClientTransportConfig {

    EncodingLimits getEncodingLimits();

    UInteger getConnectTimeout();

    UInteger getRequestTimeout();

    ExecutorService getExecutor();

    ScheduledExecutorService getScheduledExecutor();

    EventLoopGroup getEventLoop();

    HashedWheelTimer getWheelTimer();

}