package com.robinvdb.netty.echo;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;


public class EchoClientInitializer extends ChannelInitializer<SocketChannel> {
    private final EchoClientHandler handler;

    public EchoClientInitializer(EchoClientHandler handler) {
        this.handler = handler;
    }

    @Override
    public void initChannel(SocketChannel ch) {
        ch.pipeline().addLast(handler);
    }
}
