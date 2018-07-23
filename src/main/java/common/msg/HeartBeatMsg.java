package common.msg;

public class HeartBeatMsg extends BaseMsg {

    public enum TO {
        SERVER, CLIENT;
    }

    public TO direction;

    public HeartBeatMsg(TO direction) {
        super();

        this.type = MSGTYPE.HEARTBEAT;
        this.direction = direction;
    }

    public HeartBeatMsg() {
        super();

        this.type = MSGTYPE.HEARTBEAT;
    }
}
