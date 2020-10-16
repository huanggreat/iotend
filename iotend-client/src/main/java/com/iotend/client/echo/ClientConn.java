package com.iotend.client.echo;

import com.iotend.common.proto.MessageBody;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * @description: 发送数据模拟
 * @author: huang
 * @create: 2020-10-15 15:59
 */
public class ClientConn {
    /**
     * 发送消息
     * */
    public void NettyConnention(){
        String host = "127.0.0.1";
        int port = 1234;
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            // (1)
            Bootstrap b = new Bootstrap();
            // (2)
            b.group(workerGroup);
            // (3)
            b.channel(NioSocketChannel.class);
            // (4)
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new IdleStateHandler(3, 0, 3));
                    ch.pipeline().addLast("decoder",new ProtobufDecoder(MessageBody.login.getDefaultInstance()));
                    ch.pipeline().addLast("encoder",new ProtobufEncoder());
                    ch.pipeline().addLast(new ClientHandler());
                }
            });
            // (5)
            ChannelFuture f = b.connect(host, port).sync();
            /**
             * 设置内容
             * */
/*            MessageBody.login.Builder builder = MessageBody.login.newBuilder();
            builder.setID(1234);
            builder.setContent("My first App.");
            MessageBody.login login = builder.build();

            f.channel().writeAndFlush(login).sync();*/
            // Wait until the connection is closed.
            f.channel().closeFuture().sync();
            System.out.println("Connection success.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //workerGroup.shutdownGracefully();
        }
    }
}
