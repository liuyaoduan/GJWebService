package client;

import common.msg.Message;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.timeout.IdleStateHandler;



public class ClientBootstrap {

    int port;
    String host;

    String clientId;
    SocketChannel socketChannel;

    public ClientBootstrap(String host, int port) {
        this.host = host;
        this.port = port;
        start();
    }

    public ClientBootstrap setClientId(String clientId) {
        this.clientId = clientId;
        return this;
    }

    private void start() {
        EventLoopGroup loopGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.option(ChannelOption.SO_KEEPALIVE,true);
        bootstrap.group(loopGroup);
        bootstrap.remoteAddress(host,port);

        bootstrap.handler(new ChannelInitializer<SocketChannel>() {

            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                socketChannel.pipeline().addLast(new IdleStateHandler(20, 10, 0));
                socketChannel.pipeline().addLast(new ObjectEncoder());
                socketChannel.pipeline().addLast(new ObjectDecoder(ClassResolvers.cacheDisabled(null)));
                socketChannel.pipeline().addLast(new ClientHandler());
            }
        });

        bootstrap.connect(host, port).addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                if (channelFuture.isSuccess()) {
                    socketChannel = (SocketChannel) channelFuture.channel();
                    System.out.println("Client: server connected!");

                    socketChannel.writeAndFlush(Message.heartbeat(ClientBootstrap.this.clientId));
                }

            }


        });


    }

}
