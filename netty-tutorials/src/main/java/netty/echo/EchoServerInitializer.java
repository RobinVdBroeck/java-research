package netty.echo;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

public class EchoServerInitializer extends ChannelInitializer<SocketChannel> {
        private final EchoServerHandler serverHandler;

        public EchoServerInitializer(EchoServerHandler serverHandler) {
            this.serverHandler = serverHandler;
        }

        @Override
        protected void initChannel(SocketChannel ch) {
        ch.pipeline()
            .addLast(serverHandler);
        }
}
