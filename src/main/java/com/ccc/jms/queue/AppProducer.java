package com.ccc.jms.queue;


import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by ccc on 2017/7/18.
 */
public class AppProducer {

    private static final String BROKER_URL = "tcp://192.168.133.130:61616";
    private static final String QUEUE_NAME = "queue-test";

    public static void main(String[] args) throws JMSException {

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);

        Connection connection = connectionFactory.createConnection();

        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Destination destination = session.createQueue(QUEUE_NAME);

        MessageProducer producer = session.createProducer(destination);

        for (int i = 0; i < 100; i++) {
            TextMessage textMessage = session.createTextMessage("test" + i);
            producer.send(textMessage);

            System.out.println("发送消息" + textMessage.getText());

        }


        connection.close();


    }
}
