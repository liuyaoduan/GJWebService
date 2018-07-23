package server;

import common.msg.ActivateMsg;
import common.msg.BaseMsg;
import common.msg.HeartBeatMsg;
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

        switch (msg.type) {
            case ACTIVATE:
                ActivateMsg activateMsg = (ActivateMsg) msg;
                if (activateMsg.what == ActivateMsg.DO.LOGIN) {
                    // TODO
                    ChannelMap.add(msg.clientId, (SocketChannel) ctx.channel());
                } else if (activateMsg.what == ActivateMsg.DO.LOGOUT) {
                    ChannelMap.remove(msg.clientId);
                }

                activateMsg.body
                break;
            case HEARTBEAT:
                System.out.printf("Server: receive heartbeat form client %s ...\n", msg.clientId);
                ChannelMap.get(msg.clientId).writeAndFlush(new HeartBeatMsg());
                break;

        }
    }
}
