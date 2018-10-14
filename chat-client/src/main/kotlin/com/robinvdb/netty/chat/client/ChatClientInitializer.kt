package com.robinvdb.netty.chat.client

import io.netty.channel.ChannelInitializer
import io.netty.channel.socket.SocketChannel
import io.netty.handler.codec.string.StringDecoder
import io.netty.handler.codec.string.StringEncoder


class ChatClientInitializer : ChannelInitializer<SocketChannel>() {
    override fun initChannel(socketChannel: SocketChannel) {
        val pipeline = socketChannel.pipeline()


        pipeline.apply {
            addLast(StringDecoder())
            addLast(StringEncoder())

            addLast("handler", ChatClientHandler())
        }
    }
}