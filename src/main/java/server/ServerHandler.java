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
    protected void channelRead0(ChannelHandlerContext ctx, Message msg) {

        String clientId = msg.getValueByTagName(MsgConstants.CLIENTID);

        switch (msg.getType()) {
            case MsgConstants.ACTIVATION:

                if (msg.getValueByTagName(MsgConstants.LOGIN_LOGOUT).equals(MsgConstants.LOGIN)) {
                    ChannelMap.add(clientId, (SocketChannel) ctx.channel());
                } else {
                    ChannelMap.remove(clientId);
                }

                msg.addBodyElement(MsgConstants.RESULT, MsgConstants.OK);

                break;
            case MsgConstants.HEARTBEAT:
                System.out.printf("Server: receive heartbeat form client %s ...\n", clientId);
                ChannelMap.get(clientId).writeAndFlush(Message.heartbeat(clientId));
                break;

        }
    }
}
