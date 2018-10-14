package com.robinvdb.netty.chat.server

fun main(args: Array<String>) {
    if (args.size != 1) {
        System.err.println("Correct usage: ChatServer <port>")
        System.err.println("Got: " + args.joinToString(" "))
        return
    }
    val port = args[0].toInt(10)

    ChatServer(port)
            .run()
}