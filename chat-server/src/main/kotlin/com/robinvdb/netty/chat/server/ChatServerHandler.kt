package com.robinvdb.netty.chat.server

import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler
import io.netty.channel.group.DefaultChannelGroup
import io.netty.util.concurrent.GlobalEventExecutor

class ChatServerHandler : SimpleChannelInboundHandler<String>() {
    companion object {
        private val channels = DefaultChannelGroup(GlobalEventExecutor.INSTANCE)
    }


    override fun handlerAdded(ctx: ChannelHandlerContext) {
        val incoming = ctx.channel()
        val message = "[SERVER] ${incoming.remoteAddress()} has joined"
        broadCast(message)

        channels.add(incoming)
    }

    override fun handlerRemoved(ctx: ChannelHandlerContext) {
        val removing = ctx.channel()
        channels.remove(removing)

        val message = "[SERVER] ${removing.remoteAddress()} has leaved"
        broadCast(message)
    }

    override fun channelRead0(ctx: ChannelHandlerContext, msg: String) {
        val incoming = ctx.channel()
        val formattedMessage = "[${incoming.remoteAddress()}] $msg"
        broadCast(formattedMessage)
    }

    private fun broadCast(message: String) {
        println("Broadcasting: $message")
        for (channel in channels) {
            channel.writeAndFlush(message)
        }
    }

}
