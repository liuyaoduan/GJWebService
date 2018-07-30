package common.msg;

import javax.xml.namespace.QName;
import javax.xml.soap.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Message implements Serializable {


    private SOAPMessage soap;

    public static Message obtain() {
        Message message = new Message();
        try {
            message.soap = MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL).createMessage();
            message.soap.setProperty(SOAPMessage.CHARACTER_SET_ENCODING, "UTF-8");
        } catch (SOAPException e) {
            e.printStackTrace();
        }
        return message;
    }

    public static Message heartbeat(String clientId) {
        Message message = Message.obtain();

        Map<String, String> head = new HashMap<String, String>();
        Map<String, String> body = new HashMap<String, String>();


        head.put("type", MsgConstants.HEARTBEAT);
        message.setHeader(head);

        body.put("clientid", clientId);
        message.setBody(body);

        return message;
    }

    public static Message activation(String clientId, String login_logout) {
        Message message = Message.obtain();

        Map<String, String> head = new HashMap<String, String>();
        Map<String, String> body = new HashMap<String, String>();


        head.put("type", MsgConstants.ACTIVATION);
        head.put("login_logout", login_logout);
        message.setHeader(head);

        body.put("clientid", clientId);
        message.setBody(body);

        return message;
    }



    public static Message createMessage(String type) {
        Message message = Message.obtain();

        Map<String, String> head = new HashMap<String, String>();
        Map<String, String> body = new HashMap<String, String>();

        switch (type) {
            case MsgConstants.ACTIVATION:
                break;
            case MsgConstants.HEARTBEAT:
                head.put("clientid", "");

                break;
        }

        return message;
    }

    public Message addHeaderElement(String key, String value) {
        try {
            this.soap.getSOAPPart().getEnvelope().getHeader().addHeaderElement(new QName(key)).setValue(value);
        } catch (SOAPException e) {
            e.printStackTrace();
        }
        return this;
    }

    public Message setHeader(Map<String, String> items) {
        try {
            SOAPHeader header = this.soap.getSOAPPart().getEnvelope().getHeader();

            for (Map.Entry<String, String> item : items.entrySet()) {
                header.addHeaderElement(new QName(item.getKey())).setValue(item.getValue());
            }

        } catch (SOAPException e) {
            e.printStackTrace();
        }

        return this;
    }

    public Message addBodyElement(String key, String value) {
        try {
            this.soap.getSOAPBody().addBodyElement(new QName(key)).setValue(value);
        } catch (SOAPException e) {
            e.printStackTrace();
        }
        return this;
    }

    public Message setBody(Map<String, String> items) {
        try {
            SOAPBody body = this.soap.getSOAPPart().getEnvelope().getBody();

            for (Map.Entry<String, String> item : items.entrySet()) {
                body.addBodyElement(new QName(item.getKey())).setValue(item.getValue());
            }

        } catch (SOAPException e) {
            e.printStackTrace();
        }

        return this;
    }

    public SOAPMessage getSoap() {
        return soap;
    }

    public String getValueByTagName(String tagName) {
        return this.soap.getSOAPPart().getElementsByTagName(tagName).item(0).getNodeValue();
    }

    public String getType() {
        return this.getValueByTagName("type");
    }
}
