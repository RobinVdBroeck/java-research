package com.robinvdb.netty.chat.client

fun main(args: Array<String>) {
    if(args.size != 2) {
        System.err.println("Correct usage: ChatClient <host> <port>")
        return
    }
    
    val host = args[0]
    val port = args[1].toInt(10)
    val client = ChatClient(host, port)
    client.run()
}