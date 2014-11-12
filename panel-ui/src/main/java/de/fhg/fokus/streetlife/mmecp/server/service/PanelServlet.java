package de.fhg.fokus.streetlife.mmecp.server.service;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.glassfish.tyrus.client.ClientManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.websocket.Session;

import de.fhg.fokus.streetlife.mmecp.server.websocket.PanelEndpoint;

/**
 * Servlet implementation class ExampleData
 */
public class PanelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	Session session = null;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PanelServlet() {
        super();
        try {
        	LOG.info("instanziere...");
			connectToBackEnd();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().print("Hello World");
		
		try {
			LOG.info("send Message0");
			session.getBasicRemote().sendText("Hello from Panel!");
			LOG.info("send Message1");
		} catch (IOException e) {
			LOG.error(e.getMessage());
		}
	}

	/**	
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	public void connectToBackEnd() throws Exception {
		ClientManager client = ClientManager.createClient();
		String uri = "ws://193.175.133.251:8080/api-websocket/objects";
		session = client.connectToServer(PanelEndpoint.class, URI.create(uri));
	}
	
}
