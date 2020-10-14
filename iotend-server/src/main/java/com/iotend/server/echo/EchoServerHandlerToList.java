package com.iotend.server.echo;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @description: 业务处理
 * @author: huang
 * @create: 2020-10-14 17:13
 */
public class EchoServerHandlerToList extends SimpleChannelInboundHandler<Object> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object obj) throws Exception {
        //业务处理
    }
}
