package client;

import common.msg.ActivateMsg;
import common.msg.BaseMsg;
import common.msg.MSGTYPE;
import common.msg.HeartBeatMsg;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.ReferenceCountUtil;

public class ClientHandler extends SimpleChannelInboundHandler<BaseMsg> {


    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        super.userEventTriggered(ctx, evt);

        if (evt instanceof IdleStateEvent) {
            IdleStateEvent e = (IdleStateEvent) evt;
            switch (e.state()) {
                case WRITER_IDLE:
                    HeartBeatMsg heartBeatMsg =new HeartBeatMsg(HeartBeatMsg.TO.SERVER);
                    ctx.writeAndFlush(heartBeatMsg);
                    System.out.println("Client: send heartbeat to server ...");
                    break;
                default:
                    break;
            }
        }
    }



    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, BaseMsg msg) throws Exception {

        switch (msg.type){
            case HEARTBEAT:
                System.out.printf("Client %s: receive heartbeat form server ...\n", msg.clientId);
                break;
            case ACTIVATE:
                ((ActivateMsg)msg).
                break;

        }

        ReferenceCountUtil.release(msg.type);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);

        ctx.close();
    }
}
