package de.fhg.fokus.streetlife.mmecp.server.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletContext;

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

	int eventID = 0;

	public EventInfo getEventInfo() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		EventInfo eventInfo = new EventInfo();
		if (eventID % 4 == 0)
			try {
				Thread.sleep(1000 * 10);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		
		//get JSON schema
		JSONProcessor jSONProcessor = null;
		try {
			jSONProcessor = new JSONProcessor(
					getContentOfFile("/WEB-INF/schema.json"));
		} catch (IOException e) {
			e.printStackTrace();
			eventInfo.setMessage(e.getMessage());
			System.out.println(eventInfo.getMessage());
			return eventInfo;
		}

		
		//get JSON example
		String jSONExample = "";
		try {
			// Example at first
			jSONExample = getJSONExample();
		} catch (IOException e) {
			e.printStackTrace();
			eventInfo.setMessage(e.getMessage());
			System.out.println(eventInfo.getMessage());
			return eventInfo;
		}

		//validation
		if (!jSONProcessor.validate(jSONExample)) {
			eventInfo.setMessage("json file not valid");
			System.out.println(eventInfo.getMessage());
			return eventInfo;
		}

		//Parse
		try {
			eventInfo = JSONProcessor.parse(jSONExample);
		} catch (Exception e) {
			eventInfo.setMessage(e.getMessage());
			System.out.println(eventInfo.getMessage());
			return eventInfo;
		}
		eventInfo.setMessage("success");
		System.out.println(eventInfo.getMessage());
		eventInfo.setId(eventID++);
		return eventInfo;
	}

	private String getJSONExample() throws IOException {
		String filename = "/WEB-INF/example.json";
		return getContentOfFile(filename);
	}

	private String getContentOfFile(String path) throws IOException {
		String string = "";

		ServletContext context = getServletContext();
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
}
