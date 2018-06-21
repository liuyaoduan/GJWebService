package common.msg;

import java.io.Serializable;

public class BaseMsg implements Serializable {

    public MSGTYPE type;
    public String clientId;

}
