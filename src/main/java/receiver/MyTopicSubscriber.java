package receiver;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.jms.*;

public class MyTopicSubscriber {

    public static void main(String args[]) {
        try{
            ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContextJMS.xml");
            TopicConnectionFactory factory = (TopicConnectionFactory) applicationContext.getBean("connectionFactory");

            Topic topic = (Topic) applicationContext.getBean("topic");

            // Create a connection. See https://docs.oracle.com/javaee/7/api/javax/jms/package-summary.html
            TopicConnection connection = factory.createTopicConnection();

            // Open a session without transaction and acknowledge automatic
            TopicSession session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

            // Start the connection
            connection.start();

            // Create a subscriber
            TopicSubscriber topicSubscriber = session.createSubscriber(topic);

            // Receive the message
            Message message = null;
            message = topicSubscriber.receive(10000);

            //Print message
            System.out.println(message);

            // Session close
            session.close();

            // Connection close
            connection.close();

        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
