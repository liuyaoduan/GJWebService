package client;

import common.msg.Message;
import common.msg.MsgConstants;

import java.util.UUID;

public class OpenClient {

    public static void main(String[] args) {
        String clientId = UUID.randomUUID().toString();

        ClientBootstrap bootstrap = new ClientBootstrap("localhost", 9999).setClientId(clientId);
        bootstrap.socketChannel.writeAndFlush(Message.activation(clientId, MsgConstants.LOGIN));


    }

}
