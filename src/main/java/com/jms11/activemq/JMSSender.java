package com.jms11.activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

public class JMSSender {

    public static void main(String[] args) throws Exception {
        Context ctx = new InitialContext();
        Connection connection = ((ConnectionFactory) ctx.lookup("ConnectionFactory")).createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = (Queue) ctx.lookup("EM_TRADE.Q");
        MessageProducer sender = session.createProducer(queue);
        TextMessage msg = session.createTextMessage("BUY AAPL 2000 SHARES");
        msg.setStringProperty("TraderName", "Mark");
        sender.send(msg);
        System.out.println("message sent");
        connection.close();
    }

}
