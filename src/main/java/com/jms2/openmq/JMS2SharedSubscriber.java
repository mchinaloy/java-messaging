package com.jms2.openmq;

import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Topic;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JMS2SharedSubscriber implements MessageListener, Runnable {

    private static final ExecutorService singleThread = Executors.newSingleThreadExecutor();

    public static void main(String[] args) {
        singleThread.execute(new JMS2SharedSubscriber());
    }

    @Override
    public void run() {
        try {
            ConnectionFactory cf = new com.sun.messaging.ConnectionFactory();
            JMSContext jmsContext = cf.createContext();
            Topic topic = jmsContext.createTopic("EM_JMS2_TRADE.T");
            JMSConsumer jmsConsumer = jmsContext.createSharedConsumer(topic, "sub:3e");
            jmsConsumer.setMessageListener(this);
            System.out.println("waiting for messages");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onMessage(Message message) {
        try {
            System.out.println(message.getBody(String.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
