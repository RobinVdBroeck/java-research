package com.robinvdb.netty.time;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class TimeClient {
    public static void main(String[] args) throws Exception {
        if(args.length != 2) {
            System.out.println("Requires 2 arguments: host + port");
            return;
        }

        var host = args[0];
        var port = Integer.parseInt(args[1]);

        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            var bootstrap = new Bootstrap();
            bootstrap
                    .group(workerGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) {
                            ch.pipeline().addLast(new TimeDecoder(), new TimeClientHandler());
                        }
                    });
            var channelFuture = bootstrap.connect(host, port).sync();

            channelFuture
                    .channel()
                    .closeFuture()
                    .sync();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}
