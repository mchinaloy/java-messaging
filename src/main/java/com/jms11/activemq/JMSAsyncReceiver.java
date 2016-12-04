package com.jms11.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JMSAsyncReceiver implements MessageListener, Runnable {

    private static final ExecutorService singleThread = Executors.newSingleThreadExecutor();

    @Override
    public void run() {
        try {
            Connection connection = new ActiveMQConnectionFactory("tcp://localhost:61616").createConnection();
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Queue queue = session.createQueue("EM_TRADE.Q");
            MessageConsumer receiver = session.createConsumer(queue);
            receiver.setMessageListener(this);
            System.out.println("waiting for messages");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        singleThread.execute(new JMSAsyncReceiver());
    }

    @Override
    public void onMessage(Message message) {
        try {
            TextMessage msg = (TextMessage) message;
            System.out.println(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
