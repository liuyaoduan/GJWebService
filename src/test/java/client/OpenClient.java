package client;

import common.msg.ActivateMsg;

import java.util.UUID;

public class OpenClient {

    public static void main(String[] args) {
        ClientBootstrap bootstrap = new ClientBootstrap(9999, "localhost");

        ActivateMsg activateMsg = new ActivateMsg();
        activateMsg.setClientId(UUID.randomUUID().toString());
        bootstrap.socketChannel.writeAndFlush(activateMsg);


    }

}
