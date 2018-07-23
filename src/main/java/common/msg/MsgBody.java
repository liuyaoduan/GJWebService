package common.msg;

import com.google.gson.Gson;

import javax.xml.soap.*;
import java.io.Serializable;

public class MsgBody implements Serializable {
    /*

    head: clientid, msgtype
    body:


     */



    public String json;
    public byte[] data;

    public void set(String str) {
        Gson gson = new Gson();
        SOAPMessage msg = MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL).createMessage();
        msg.setProperty(SOAPMessage.CHARACTER_SET_ENCODING, "UTF-8");
        SOAPEnvelope envelope = msg.getSOAPPart().getEnvelope();

        SOAPBody body = envelope.getBody();
    }


}
