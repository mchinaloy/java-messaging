package com.jms2.openmq;

import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.JMSContext;
import javax.jms.Queue;

public class JMS2Sender {

    public static void main(String[] args) throws Exception {
        ConnectionFactory cf = new com.sun.messaging.ConnectionFactory();
        try (JMSContext jmsContext = cf.createContext()) {
            Queue queue = jmsContext.createQueue("EM_JMS2_TRADE.Q");
            jmsContext
                    .createProducer()
                    .setProperty("TraderName", "Mark")
                    .setDeliveryMode(DeliveryMode.NON_PERSISTENT)
                    .send(queue, "SELL AAPL 1500 SHARES");
            System.out.println("message sent");
        }

    }


}
