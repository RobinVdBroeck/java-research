package com.robinvdb.netty.echo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.net.InetSocketAddress;

public class EchoServer {
    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws Exception {
        if(args.length != 1) {
            System.err.println("Usage: " + EchoServer.class.getSimpleName() + " <port>");
        }
        var port = Integer.parseInt(args[0]);
        new EchoServer(port)
                .start();
    }

    public void start() throws Exception {
        final var serverHandler = new EchoServerHandler();
        final var group = new NioEventLoopGroup();

        try {
            var b = new ServerBootstrap();
            b.group(group)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port))
                    .childHandler(new EchoServerInitializer(serverHandler));

            final var channelFuture = b.bind()
                    .sync();

            System.out.println("Running echo server on port " + port);

            channelFuture.channel()
                    .closeFuture()
                    .sync();
        } finally {
            group.shutdownGracefully();
        }
    }
}
