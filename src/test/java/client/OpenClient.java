package client;

public class OpenClient {

    public static void main(String[] args) {
        ClientBootstrap bootstrap = new ClientBootstrap(9999, "localhost");

        ActivateMsg activateMsg = new ActivateMsg();
        bootstrap.socketChannel.writeAndFlush(activateMsg);


    }

}
