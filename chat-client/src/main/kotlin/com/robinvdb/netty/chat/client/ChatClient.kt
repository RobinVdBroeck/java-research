package com.robinvdb.netty.chat.client

import io.netty.bootstrap.Bootstrap
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioSocketChannel
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.InetSocketAddress

class ChatClient(private val host: String, private val port: Int) {
    val address: InetSocketAddress
        get() = InetSocketAddress(host, port)

    fun run() {
        val eventLoopGroup = NioEventLoopGroup()

        try {
            val bootstrap = Bootstrap();
            bootstrap.apply {
                group(eventLoopGroup)
                channel(NioSocketChannel::class.java)
                handler(ChatClientInitializer())
            }

            val channelFuture = bootstrap.connect(address).sync()

            val channel = channelFuture.channel()
            val reader = BufferedReader(InputStreamReader(System.`in`))

            println("Type .quit to quit")

            reader.lineSequence()
                    .takeWhile { it != ".quit" }
                    .forEach { line -> channel.writeAndFlush(line) }

        } finally {
            eventLoopGroup.shutdownGracefully()
        }
    }
}