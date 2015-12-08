package com.chesshunt.servlet;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.websocket.WebSocket;
import org.eclipse.jetty.websocket.WebSocketServlet;

/**
 * 
 * @author Anshu Sharma
 * 
 */
public class WebSocketChatServlet extends WebSocketServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5610501345675935366L;
	public static final Set<ChatWebSocket> users = new CopyOnWriteArraySet<ChatWebSocket>();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Invoked doGet of WebSocketChatServlet");
		getServletContext().getNamedDispatcher("default").forward(request,
				response);
	}

	@Override
	public WebSocket doWebSocketConnect(HttpServletRequest arg0, String arg1) {
		System.out.println("Invoked doWebSocketConnect of WebSocketChatServlet");
		return new ChatWebSocket(users);
	}

}
