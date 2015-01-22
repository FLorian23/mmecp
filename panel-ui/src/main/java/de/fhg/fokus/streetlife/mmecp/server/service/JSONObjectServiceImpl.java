package de.fhg.fokus.streetlife.mmecp.server.service;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.fhg.fokus.streetlife.mmecp.client.service.JSONObjectService;
import de.fhg.fokus.streetlife.mmecp.server.json.JSONProcessor;
import de.fhg.fokus.streetlife.mmecp.share.dto.PanelObject;
import org.gwtopenmaps.openlayers.client.LonLat;

import java.awt.*;
import java.awt.List;
import java.util.*;

public class JSONObjectServiceImpl extends RemoteServiceServlet implements
		JSONObjectService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JSONObjectServiceImpl() {

	}

	// private static int eventID = 1;
	// private static ArrayList<EventInfo> eventInfoQueue = new
	// ArrayList<EventInfo>();
	// private static JSONProcessor jSONProcessor = null;
	// private static ServletContext context = null;

	// public EventInfo getEventInfo() {
	//
	// context = getServletContext();
	// // get JSON schema
	// if (jSONProcessor == null) {
	// try {
	// jSONProcessor = new JSONProcessor(
	// getContentOfFile("/WEB-INF/schema.json"));
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }
	//
	// EventInfo eventInfo = null;
	// int counter = 0;
	// while ((eventInfoQueue.size() == 0)) {
	// try {
	// Thread.sleep(500);
	// if (counter++ == 10){
	// // System.out.println(">>zeitueberlauf");
	// EventInfo eventInfo2 = new EventInfo();
	// eventInfo2.setMessage("error: exceeded the time requirement");
	// return eventInfo2;
	// }
	// } catch (InterruptedException e1) {
	// e1.printStackTrace();
	// }
	// }
	//
	//
	// // Not consider multiple clients! TODO!
	// eventInfo = eventInfoQueue.remove(0);
	// System.out.println("Remove EventInfo [" + eventInfo.getId() +
	// "] (Message: " + eventInfo.getMessage() + ")");
	//
	// return eventInfo;
	// }

	public PanelObject getPanelObjectByCoordinate(ArrayList<PanelObject> list){
		java.awt.Polygon pol = new Polygon();
		for (int x = 0;x<list.size();x++){
			java.util.List<java.util.List<LonLat>> lonlatList =  list.get(x).getMaparea().getArea().getCoordinatesLonLat();
			for (int y = 0;y<lonlatList.size();y++){
				//pol.addPoint();
			}
		}
		return null;
	}

	public PanelObject[] getPanelObject(String jSONExample) {
		// Parse
		try {
			return JSONProcessor.parse(jSONExample, PanelObject[].class);
		} catch (Exception e) {
			System.out.println("error parsing: " + e.getMessage());
		}
		return null;
	}

	// private static String getJSONExample() throws IOException {
	// String filename = "/WEB-INF/example.json";
	// return getContentOfFile(filename);
	// }
	//
	// private static String getContentOfFile(String path) throws IOException {
	// String string = "";
	//
	// InputStream inp = context.getResourceAsStream(path);
	// if (inp != null) {
	// InputStreamReader isr = new InputStreamReader(inp);
	// BufferedReader reader = new BufferedReader(isr);
	// String text = "";
	// while ((text = reader.readLine()) != null) {
	// string += text;
	// }
	// }
	// return string;
	// }

	// public static void newEvent() {
	// if (context == null) return;
	//
	// EventInfo eventInfo = new EventInfo();
	// // get JSON example
	// String jSONExample = "";
	// try {
	// // Example at first
	// jSONExample = getJSONExample();
	// } catch (IOException e) {
	// e.printStackTrace();
	// eventInfo.setMessage(e.getMessage());
	// }
	//
	// // validation
	// if (!jSONProcessor.validate(jSONExample)) {
	// eventInfo.setMessage("json file not valid");
	// }
	//
	// // Parse
	// try {
	// eventInfo = JSONProcessor.parse(jSONExample);
	// } catch (Exception e) {
	// eventInfo.setMessage(e.getMessage());
	// System.out.println(eventInfo.getMessage());
	// }
	// eventInfo.setMessage("success");
	// eventInfo.setId(eventID++);
	// eventInfoQueue.add(eventInfo);
	//
	// System.out.println("add EventInfo [" + eventInfo.getId() + "] (Message: "
	// + eventInfo.getMessage() + ")");
	// }
}
