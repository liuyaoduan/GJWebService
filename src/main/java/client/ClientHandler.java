package client;

import common.msg.Message;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.ReferenceCountUtil;
import common.msg.MsgConstants;

import java.util.UUID;

public class ClientHandler extends SimpleChannelInboundHandler<Message> {


    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        super.userEventTriggered(ctx, evt);

        if (evt instanceof IdleStateEvent) {
            IdleStateEvent e = (IdleStateEvent) evt;
            switch (e.state()) {
                case WRITER_IDLE:
                    String clientId = UUID.randomUUID().toString();
                    Message message = Message.heartbeat(clientId);
                    ctx.writeAndFlush(message);
                    System.out.printf("Client %s: send heartbeat to server ...\n", clientId);
                    break;
                default:
                    break;
            }
        }
    }



    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Message msg) {

        switch (msg.getType()){
            case MsgConstants.HEARTBEAT:
                System.out.printf("Client %s: receive heartbeat form server ...\n", msg.getValueByTagName(MsgConstants.CLIENTID));
                break;
            case MsgConstants.ACTIVATION:

                break;

        }

        ReferenceCountUtil.release(msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);

        ctx.close();
    }
}
