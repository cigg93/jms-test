package com.ccc.jms.queue;


import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by ccc on 2017/7/18.
 */
public class AppConsumer {

    private static final String BROKER_URL = "tcp://192.168.133.130:61616";
    private static final String QUEUE_NAME = "queue-test";

    public static void main(String[] args) throws JMSException {

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);

        Connection connection = connectionFactory.createConnection();

        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Destination destination = session.createQueue(QUEUE_NAME);

        MessageConsumer consumer = session.createConsumer(destination);

        // 异步监听
        consumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println("接收消息" + textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });


//        connection.close();


    }
}
