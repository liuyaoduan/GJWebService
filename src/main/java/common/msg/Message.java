package common.msg;

import javax.xml.namespace.QName;
import javax.xml.soap.*;

public class Message {
    public Message() {
        SOAPMessage msg = null;
        try {
            msg = MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL).createMessage();
            msg.setProperty(SOAPMessage.CHARACTER_SET_ENCODING, "UTF-8");
            SOAPEnvelope envelope = msg.getSOAPPart().getEnvelope();

            SOAPHeader header = envelope.getHeader();
            header.addHeaderElement(new QName("clientId")).setValue("");
            header.addHeaderElement(new QName("type")).setValue("");

            SOAPBody body = envelope.getBody();
            body.add

        } catch (SOAPException e) {
            e.printStackTrace();
        }

    }
}
