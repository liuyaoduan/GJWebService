package server;


import io.netty.channel.socket.SocketChannel;

public class OpenServer {

    public static void main(String[] args) {
        ServerBootstrap bootstrap = new ServerBootstrap(9999);

        SocketChannel channel = ChannelMap.get("1");
        if (channel != null) {

        }
    }
}
