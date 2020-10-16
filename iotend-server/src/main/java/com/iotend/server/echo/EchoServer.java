package com.iotend.server.echo;

import com.iotend.common.proto.MessageBody;
import com.iotend.utils.OsInfo;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.flush.FlushConsolidationHandler;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.concurrent.DefaultThreadFactory;
import io.netty.util.concurrent.UnorderedThreadPoolEventExecutor;

/**
 * @description: Echo Server
 * @author: huang
 * @create: 2020-10-14 17:12
 */
public class EchoServer {
    private int port;
    public EchoServer(int port) {
        this.port = port;
    }
    public void run() throws Exception {
        final UnorderedThreadPoolEventExecutor businessGroup = new UnorderedThreadPoolEventExecutor(10, new DefaultThreadFactory("business"));
        // (1)
        EventLoopGroup bossGroup;
        EventLoopGroup workerGroup;
        /**
         * 根据不同操作系统选择不同的 EventLoopGroup
         * */
        if(OsInfo.isWindows()){
            bossGroup = new NioEventLoopGroup();
            workerGroup = new NioEventLoopGroup();
        }else {
            bossGroup = new EpollEventLoopGroup();
            workerGroup = new EpollEventLoopGroup();
        }

        try {
            // (2)
            ServerBootstrap b = new ServerBootstrap();
            // (3)
            b.group(bossGroup, workerGroup)
                    .channel(OsInfo.isWindows() == true ? NioServerSocketChannel.class : EpollServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            //包分隔符
                            ch.pipeline().addLast(new IdleStateHandler(420, 0, 0));
                            ch.pipeline().addLast("decoder",new ProtobufDecoder(MessageBody.login.getDefaultInstance()));
                            ch.pipeline().addLast("encoder",new ProtobufEncoder());
                            ch.pipeline().addLast("flushEnhance", new FlushConsolidationHandler(10, true));
                            ch.pipeline().addLast(businessGroup, "handler",new EchoServerHandlerToList());  //Netty提供的业务线程
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childOption(ChannelOption.TCP_NODELAY,true);
            // 绑定端口，开始接收进来的连接
            ChannelFuture f = b.bind(port).sync();
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}
