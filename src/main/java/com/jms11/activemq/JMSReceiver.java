package com.jms11.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

public class JMSReceiver {

    public static void main(String[] args) throws Exception {
        Connection connection = new ActiveMQConnectionFactory("tcp://localhost:61616").createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue("EM_TRADE.Q");
        MessageConsumer receiver = session.createConsumer(queue);
        TextMessage msg = (TextMessage) receiver.receive();
        System.out.println(msg.getText() + ", " + msg.getStringProperty("TraderName"));
    }


}
