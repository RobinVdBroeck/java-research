package com.robinvdb.netty.time;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class TimeEncoder extends MessageToByteEncoder<UnixTime> {
    @Override
    public void encode(ChannelHandlerContext ctx, UnixTime msg, ByteBuf out) {
        System.out.println("Encoding time");
        out.writeInt((int)msg.getValue());
    }
}
