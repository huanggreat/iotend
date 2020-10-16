package com.iotend.client.echo;

import com.iotend.common.proto.MessageBody;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * @description: Netty客户端处理
 * @author: huang
 * @create: 2020-10-15 15:31
 */
public class ClientHandler extends SimpleChannelInboundHandler<Object> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        //数据处理
        System.out.println("Server rec...");
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx){
        try{
            if(null != ctx) {
                ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
                        .addListener(ChannelFutureListener.CLOSE);
            }
        }catch (Exception ex){

        }
    }
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt){
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent e = (IdleStateEvent) evt;
            switch (e.state()) {
                case READER_IDLE:
                    handleReaderIdle(ctx);
                    break;
                case WRITER_IDLE:
                    handleWriterIdle(ctx);
                    break;
                case ALL_IDLE:
                    handleAllIdle(ctx);
                    break;
                default:
                    break;
            }
        }
    }
    protected void handleReaderIdle(ChannelHandlerContext ctx) {
        try {
            ctx.writeAndFlush(Unpooled.EMPTY_BUFFER);
            ctx.close();
        }catch (Exception ex){

        }
    }

    protected void handleWriterIdle(ChannelHandlerContext ctx) {
        try {
            ctx.writeAndFlush(Unpooled.EMPTY_BUFFER);
            ctx.close();
        }catch (Exception ex){

        }
    }

    protected void handleAllIdle(ChannelHandlerContext ctx) {
        try {
            ctx.writeAndFlush(Unpooled.EMPTY_BUFFER);
            ctx.close();
        }catch (Exception ex){

        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws InterruptedException {
        for (int i = 0; i < 9; i++) {
            System.out.println(i);
            MessageBody.login.Builder builder = MessageBody.login.newBuilder();
            builder.setID(i);
            builder.setContent("My first App.");
            ctx.writeAndFlush(builder.build());
            System.out.println("?");
            Thread.sleep(500);
        }
    }
}
