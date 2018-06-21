package server;

import java.nio.channels.SocketChannel;

public class OpenServer {

    public static void main(String[] args) {
        ServerBootstrap bootstrap = new ServerBootstrap(9999);

        SocketChannel channel = ChannelMap.get();
        if (channel != null) {

        }
    }
}
