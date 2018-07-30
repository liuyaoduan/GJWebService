package server;

import common.msg.Message;
import common.msg.MsgConstants;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;

public class ServerHandler extends SimpleChannelInboundHandler<Message> {

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);

        ChannelMap.remove((SocketChannel) ctx.channel());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message msg) throws Exception {

        switch (msg.getType()) {
            case MsgConstants.ACTIVATION:

                if (msg.getValueByTagName(MsgConstants.LOGIN_LOGOUT).equals(MsgConstants.LOGIN)) {
                    ChannelMap.add(msg.getValueByTagName(MsgConstants.CLIENTID), (SocketChannel) ctx.channel());
                } else {
                    ChannelMap.remove(msg.getValueByTagName(MsgConstants.CLIENTID));
                }

                msg.addBodyElement("")


                break;
            case MsgConstants.HEARTBEAT:
                System.out.printf("Server: receive heartbeat form client %s ...\n", msg.clientId);
                ChannelMap.get(msg.clientId).writeAndFlush(new HeartBeatMsg());
                break;

        }
    }
}
