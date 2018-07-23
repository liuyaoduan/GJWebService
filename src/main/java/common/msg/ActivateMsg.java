package common.msg;

public class ActivateMsg extends BaseMsg {

    public enum DO {
        LOGIN, LOGOUT
    }

    public DO what;
    public MsgBody body;

}
