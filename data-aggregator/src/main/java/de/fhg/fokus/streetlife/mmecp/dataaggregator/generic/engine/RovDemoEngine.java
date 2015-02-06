package de.fhg.fokus.streetlife.mmecp.dataaggregator.generic.engine;

import de.fhg.fokus.streetlife.mmecp.dataaggregator.model.ResponseParseEngine;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.Color;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by bke on 05/02/15.
 */
public class RovDemoEngine implements ResponseParseEngine {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	private JsonNode exampleData = null;

	@Override
    public JsonNode parseResponse(String response) {
		JsonNode json = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			json = mapper.readTree(response);
		} catch (IOException e) {
			e.printStackTrace();
		}

		Iterator<JsonNode> ite = json.getElements();
		while (ite.hasNext()) {
			JsonNode curObject = ite.next();
			if (curObject.path("type").getTextValue().equals("mapobject")) {
				if (curObject.path("objectType").getTextValue().equals("ParkingStation") &&
						!(curObject.path("objectSubtype").getTextValue().equals("macro"))) {
					double avg = getAverageOccupancy(curObject.path("objectID").getTextValue(), Calendar.HOUR_OF_DAY, 10, 12);
					addMapObjectAttribute(curObject, "Average Occupancy (10-12)", String.format("%.2f", avg * 100) + "%");
					setMapObjectColor(curObject, avg);
					ArrayList<Double> week = getAverageOccupancyWeek(curObject.path("objectID").getTextValue());
					ArrayList<String> days = new ArrayList<>(Arrays.asList("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"));
					addMapObjectChart(curObject, "barchart", "Weekdays", "Average Occupancy", days, week);
				}
			}
		}
        return json;
    }

	private double getAverageOccupancy(String key, int field, int start, int end) {
		ArrayList<Double> occupancies = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		JsonNode dataObjects = getObjectsInExampleData(key);
		Iterator<JsonNode> ite = dataObjects.getElements();
		while (ite.hasNext()) {
			JsonNode curObject = ite.next();
			Date curDate = null;
			try {
				curDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX").parse(curObject.path("time").getTextValue());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			cal.setTime(curDate);
			if (start <= cal.get(field) && end >= cal.get(field)) {
				int occupied = curObject.path("value").path("slotsOccupiedOnFree").getIntValue() +
						curObject.path("value").path("slotsOccupiedOnPaying").getIntValue() +
						curObject.path("value").path("slotsOccupiedOnTimed").getIntValue();
				int total = curObject.path("value").path("slotsFree").getIntValue() +
						curObject.path("value").path("slotsPaying").getIntValue() +
						curObject.path("value").path("slotsTimed").getIntValue();
				occupancies.add((double) occupied / total);
			}
		}
		if (occupancies.size() > 0)
			return occupancies.stream().mapToDouble(Double::doubleValue).average().getAsDouble();
		return 0.0;
	}

	private double getAverageOccupancy(String key, int day) {
		return getAverageOccupancy(key, Calendar.DAY_OF_WEEK, day, day);
	}

	private ArrayList<Double> getAverageOccupancyWeek(String key) {
		ArrayList<Double> occupancyWeek = new ArrayList<>(7);
		for (int i = 1; i <= 7; i++)
			occupancyWeek.add(i-1, getAverageOccupancy(key, i));
		return occupancyWeek;
	}

	private void addMapObjectAttribute(JsonNode object, String label, String value) {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode newNode = mapper.createObjectNode();
		newNode.put("label", label);
		newNode.put("value", value);
		((ArrayNode) object.path("elements")).addObject().put("attribute", newNode);
	}

	private void setMapObjectColor(JsonNode object, double value) {
		Iterator<JsonNode> elements = object.path("elements").getElements();
		while (elements.hasNext()) {
			JsonNode curElement = elements.next();
			if (curElement.get("maparea") != null) {
				//TODO Exception when Color not exist
				Color color = new Color(Color.HSBtoRGB((float)((-130) * value + 130) / 360f, 1f, 0.7f));
				((ObjectNode) curElement.path("maparea").path("color")).put("red", color.getRed());
				((ObjectNode) curElement.path("maparea").path("color")).put("green", color.getGreen());
				((ObjectNode) curElement.path("maparea").path("color")).put("blue", color.getBlue());
				((ObjectNode) curElement.path("maparea").path("color")).put("alpha", 0.8);
			}
		}
	}

	private void addMapObjectChart(JsonNode object, String chartType, String xDescription, String yDescription, ArrayList<String> x, ArrayList<Double> y) {
		ObjectMapper mapper = new ObjectMapper();
		ArrayNode data = mapper.createArrayNode();
		for (int i = 0; i < y.size(); i++) {
			ObjectNode item = mapper.createObjectNode();
			item.put("label", x.get(i));
			item.put("value", y.get(i));
			data.add(item);
		}
		ObjectNode newNode = mapper.createObjectNode();
		newNode.put("type", chartType);
		newNode.put("labeldescription", xDescription);
		newNode.put("valuedescription", yDescription);
		newNode.put("data", data);
		((ArrayNode) object.path("elements")).addObject().put("chart", newNode);

	}

	public void readExampleData() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			exampleData = mapper.readTree(this.getClass().getResourceAsStream("/streetlogrovereto.json"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private JsonNode getObjectsInExampleData(String key) {
		Iterator<JsonNode> ite = exampleData.getElements();
		ObjectMapper mapper = new ObjectMapper();
		ArrayNode objects = mapper.createArrayNode();
		while (ite.hasNext()) {
			JsonNode curObject = ite.next();
			if (curObject.path("value").path("name").getTextValue().equals(key))
				objects.add(curObject);
		}
		return objects;
	}
}
