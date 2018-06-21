package server;

import common.msg.BaseMsg;
import common.msg.MSGTYPE;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;

public class ServerHandler extends SimpleChannelInboundHandler<BaseMsg> {

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);

        ChannelMap.remove((SocketChannel) ctx.channel());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, BaseMsg msg) throws Exception {
        MSGTYPE type = msg.getType();


    }
}
