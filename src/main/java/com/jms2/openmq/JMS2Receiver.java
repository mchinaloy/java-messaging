package com.jms2.openmq;

import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Message;
import javax.jms.Queue;

public class JMS2Receiver {

    public static void main(String[] args) throws Exception {
        ConnectionFactory cf = new com.sun.messaging.ConnectionFactory();
        try (JMSContext jmsContext = cf.createContext()) {
            Queue queue = jmsContext.createQueue("EM_JMS2_TRADE.Q");
            Message msg = jmsContext.createConsumer(queue).receive();
            String body = msg.getBody(String.class);
            String trader = msg.getStringProperty("TraderName");
            System.out.println(body + "," + trader);
        }
    }

}
