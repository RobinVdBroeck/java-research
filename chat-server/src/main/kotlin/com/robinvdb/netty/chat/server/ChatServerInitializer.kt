package com.robinvdb.netty.chat.server

import io.netty.channel.ChannelInitializer
import io.netty.channel.socket.SocketChannel
import io.netty.handler.codec.string.StringDecoder
import io.netty.handler.codec.string.StringEncoder


class ChatServerInitializer : ChannelInitializer<SocketChannel>() {
    override fun initChannel(socketChannel: SocketChannel) {
        val pipeline = socketChannel.pipeline()


        pipeline.apply {
            addLast("decoder", StringDecoder())
            addLast("encoder", StringEncoder())
            addLast("handler", ChatServerHandler())
        }

    }
}