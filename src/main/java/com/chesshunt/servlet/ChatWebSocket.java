package com.chesshunt.servlet;

import java.io.IOException;
import java.util.Set;

import org.eclipse.jetty.websocket.WebSocket.OnTextMessage;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chesshunt.db.dao.HibernateUtil;
import com.chesshunt.db.domain.Messages;

/**
 * 
 * @author Anshu Sharma
 * 
 */
public class ChatWebSocket implements OnTextMessage {

	private Connection connection;
	private Set<ChatWebSocket> users;
	private static Logger logger = (Logger) LoggerFactory
			.getLogger(ChatWebSocket.class);
	private static String GUEST = "guest";

	public ChatWebSocket() {

	}

	public ChatWebSocket(Set<ChatWebSocket> users) {
		this.users = users;
	}

	public void onMessage(String data) {
		logger.debug("onMessage connection:"+ connection.toString() + ", data:" + data);
		if (connection.isOpen()) {
			try {
				connection.sendMessage("echo " + data);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		Messages message = new Messages();
		message.setConnection(connection.toString());
		message.setMessage(data);
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.save(message);
		tx.commit();
	}

	@Override
	public void onOpen(Connection connection) {
		logger.debug("onOpen called, users count before:" + users.size());
		this.connection = connection;
		this.connection.setMaxIdleTime(Integer.MAX_VALUE);
		users.add(this);

	}

	@Override
	public void onClose(int closeCode, String message) {
		users.remove(this);		
	}

	public Connection getConnection() {
		return connection;
	}

}
