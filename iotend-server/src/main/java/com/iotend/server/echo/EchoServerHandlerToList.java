package com.iotend.server.echo;

import com.iotend.common.proto.MessageBody;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * @description: 业务处理
 * @author: huang
 * @create: 2020-10-14 17:13
 */

public class EchoServerHandlerToList extends SimpleChannelInboundHandler<MessageBody.login> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageBody.login mLogin) throws Exception {
        //业务处理
        System.out.println(mLogin.getID());
        System.out.println(mLogin.getContent());
    }
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        try{
            if(null != ctx){
                ctx.writeAndFlush(Unpooled.EMPTY_BUFFER);
            }
        }catch (Exception ex){}
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        //NettyChannelMap.remove(ctx);
        //出现异常时关闭channel
        cause.printStackTrace();
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
                .addListener(ChannelFutureListener.CLOSE);
    }
    @Override
    public void channelInactive(ChannelHandlerContext ctx){
        System.out.println("channelInactive");
        try {
            if (null != ctx) {
                //NettyChannelMap.remove(ctx);
                ctx.writeAndFlush(Unpooled.EMPTY_BUFFER);
                ctx.close();
            }
        }catch (Exception ex){
            //System.err.println("Zzzzz,"+ ex.toString());
        }
    }
    @Override
    public void channelActive(ChannelHandlerContext ctx){
        System.out.println("New Connection...");
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
            if(null != ctx) {
                //NettyChannelMap.remove(ctx);
                ctx.writeAndFlush(Unpooled.EMPTY_BUFFER);
                ctx.close();
            }
        }catch (Exception ex){

        }
    }

    protected void handleWriterIdle(ChannelHandlerContext ctx) {
        try {
            if(null != ctx) {
                //NettyChannelMap.remove(ctx);
                ctx.writeAndFlush(Unpooled.EMPTY_BUFFER);
                ctx.close();
            }
        }catch (Exception ex){

        }
    }

    protected void handleAllIdle(ChannelHandlerContext ctx) {
        try {
            if(null != ctx) {
                //NettyChannelMap.remove(ctx);
                ctx.writeAndFlush(Unpooled.EMPTY_BUFFER);
                ctx.close();
            }
        }catch (Exception ex){

        }
    }
}
