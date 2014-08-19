package de.fhg.fokus.streetlife.mmecp.server.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.fhg.fokus.streetlife.mmecp.client.service.EventInfoService;
import de.fhg.fokus.streetlife.mmecp.server.json.JSONProcessor;
import de.fhg.fokus.streetlife.mmecp.share.dto.EventInfo;

public class EventInfoServiceImpl extends RemoteServiceServlet implements
		EventInfoService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EventInfoServiceImpl() {

	}

	private static int eventID = 1;
	private static ArrayList<EventInfo> eventInfoQueue = new ArrayList<EventInfo>();
	private static JSONProcessor jSONProcessor = null;
	private static ServletContext context = null;

	public EventInfo getEventInfo() {

		context = getServletContext();
		// get JSON schema
		if (jSONProcessor == null) {
			try {
				jSONProcessor = new JSONProcessor(
						getContentOfFile("/WEB-INF/schema.json"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		EventInfo eventInfo = null;
		while ((eventInfoQueue.size() == 0)) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
		

		// Not consider multiple clients! TODO!
		eventInfo = eventInfoQueue.remove(eventInfoQueue.size() - 1);

		return eventInfo;
	}

	private static String getJSONExample() throws IOException {
		String filename = "/WEB-INF/example.json";
		return getContentOfFile(filename);
	}

	private static String getContentOfFile(String path) throws IOException {
		String string = "";

		InputStream inp = context.getResourceAsStream(path);
		if (inp != null) {
			InputStreamReader isr = new InputStreamReader(inp);
			BufferedReader reader = new BufferedReader(isr);
			String text = "";
			while ((text = reader.readLine()) != null) {
				string += text;
			}
		}
		return string;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		if (req.getServletPath().compareTo("/exampleData") == 0) {
			System.out.println("doGET!");
			eventID = 1;
		}
	}

	public static void newEvent() {
		if (context == null) return;
		
		EventInfo eventInfo = new EventInfo();
		// get JSON example
		String jSONExample = "";
		try {
			// Example at first
			jSONExample = getJSONExample();
		} catch (IOException e) {
			e.printStackTrace();
			eventInfo.setMessage(e.getMessage());
			System.out.println(eventInfo.getMessage());
		}

		// validation
		if (!jSONProcessor.validate(jSONExample)) {
			eventInfo.setMessage("json file not valid");
			System.out.println(eventInfo.getMessage());
		}

		// Parse
		try {
			eventInfo = JSONProcessor.parse(jSONExample);
		} catch (Exception e) {
			eventInfo.setMessage(e.getMessage());
			System.out.println(eventInfo.getMessage());
		}
		eventInfo.setMessage("success");
		System.out.println(eventInfo.getMessage());
		eventInfo.setId(eventID++);

		eventInfoQueue.add(eventInfo);
	}
}
