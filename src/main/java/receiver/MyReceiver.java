package receiver;

import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;

import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.Clock;

public class MyReceiver {

	public static void main(String[] args) {
		try{
			
			ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContextJMS.xml");
			QueueConnectionFactory factory = (QueueConnectionFactory) applicationContext.getBean("connectionFactory");
			
			Queue queue = (Queue) applicationContext.getBean("queue");

			// Create a connection. See https://docs.oracle.com/javaee/7/api/javax/jms/package-summary.html
			QueueConnection connection = factory.createQueueConnection();

			// Open a session
			QueueSession session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

			// start the connection
			connection.start();

			// Create a receiver
			QueueReceiver receiver = session.createReceiver(queue);

			// Receive the message
			Message message = null;
			message = receiver.receive(10000);

			//Print message
			System.out.println(message);
			session.close();
			connection.close();

		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
