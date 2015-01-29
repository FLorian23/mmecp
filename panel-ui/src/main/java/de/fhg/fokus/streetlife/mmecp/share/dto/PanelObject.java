package de.fhg.fokus.streetlife.mmecp.share.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.gson.annotations.SerializedName;
import de.fhg.fokus.streetlife.mmecp.client.controller.LOG;

/**
 * Schema for STREETLIFE MMECP panel objects.
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({ "type", "objectID", "objectType", "location",
		"description", "elements" })
public class PanelObject implements Serializable {

	// if notification, save the coresponding MapObject
	private PanelObject mapObject;

	@JsonProperty("type")
	private PanelObject.Type type;
	@JsonProperty("objectID")
	private String objectID;
	@JsonProperty("objectType")
	private String objectType;
	@JsonProperty("objectSubType")
	private String objectSubType;
	@JsonProperty("location")
	private Location location;
	@JsonProperty("description")
	private String description;
	@JsonProperty("elements")
	private List<Element> elements = new ArrayList<Element>();
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();


	public void printObject(){
		//mapObject;

		LOG.logToConsole("type: " + type);
		LOG.logToConsole("objectID: " + objectID);
		LOG.logToConsole("objectType: " + objectType);
		LOG.logToConsole("objectSubType: " + objectSubType);
		LOG.logToConsole("location: " + location);
		LOG.logToConsole("description: " + description);
		LOG.logToConsole("elements:");
		for (int i = 0;i<elements.size();i++){
			elements.get(i).printObject();
		}
	}

	/**
	 * 
	 * @return The type
	 */
	@JsonProperty("type")
	public PanelObject.Type getType() {
		return type;
	}

	/**
	 * 
	 * @param type
	 *            The type
	 */
	@JsonProperty("type")
	public void setType(PanelObject.Type type) {
		this.type = type;
	}

	/**
	 * 
	 * @return The objectID
	 */
	@JsonProperty("objectID")
	public String getObjectID() {
		return objectID;
	}

	/**
	 * 
	 * @param objectID
	 *            The objectID
	 */
	@JsonProperty("objectID")
	public void setObjectID(String objectID) {
		this.objectID = objectID;
	}

	/**
	 *
	 * @return The objectType
	 */
	@JsonProperty("objectType")
	public String getObjectType() {
		return objectType;
	}

	/**
	 *
	 * @param objectType
	 *            The objectType
	 */
	@JsonProperty("objectType")
	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	/**
	 *
	 * @return The objectSubType
	 */
	@JsonProperty("objectSubType")
	public String getObjectSubType() {
		return objectSubType;
	}

	/**
	 *
	 * @param objectType
	 *            The objectType
	 */
	@JsonProperty("objectSubType")
	public void setObjectSubType(String objectSubType) {
		this.objectSubType = objectSubType;
	}


	/**
	 * 
	 * @return The location
	 */
	@JsonProperty("location")
	public Location getLocation() {
		return location;
	}

	/**
	 * 
	 * @param location
	 *            The location
	 */
	@JsonProperty("location")
	public void setLocation(Location location) {
		this.location = location;
	}

	/**
	 * 
	 * @return The description
	 */
	@JsonProperty("description")
	public String getDescription() {
		return description;
	}

	/**
	 * 
	 * @param description
	 *            The description
	 */
	@JsonProperty("description")
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 
	 * @return The elements
	 */
	@JsonProperty("elements")
	public List<Element> getElements() {
		return elements;
	}

	/**
	 * 
	 * @param elements
	 *            The elements
	 */
	@JsonProperty("elements")
	public void setElements(List<Element> elements) {
		this.elements = elements;
	}

	public Maparea getMaparea() {
		/*
		 * Can't execute in gwt jre emulation library List<Element> result =
		 * getElements().stream() .filter(element -> element.getMaparea() !=
		 * null) .collect(Collectors.toList()); if (!result.isEmpty()) return
		 * result.get(0).getMaparea(); else return null;
		 */
		for (int i = 0; i < elements.size(); i++) {
			if (elements.get(i).getMaparea() != null)
				return elements.get(i).getMaparea();
		}
		return null;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	public PanelObject getMapObject() {
		return mapObject;
	}

	public void setMapObject(PanelObject mapObject) {
		this.mapObject = mapObject;
	}

	@Generated("org.jsonschema2pojo")
	public static enum Type {

		@SerializedName("mapobject")
		MAPOBJECT("mapobject"),

		@SerializedName("notification")
		NOTIFICATION("notification");
		private final String value;
		private static Map<String, PanelObject.Type> constants = new HashMap<String, PanelObject.Type>();

		static {
			for (PanelObject.Type c : values()) {
				constants.put(c.value, c);
			}
		}

		private Type(String value) {
			this.value = value;
		}

		@JsonValue
		@Override
		public String toString() {
			return this.value;
		}

		@JsonCreator
		public static PanelObject.Type fromValue(String value) {
			PanelObject.Type constant = constants.get(value);
			if (constant == null) {
				throw new IllegalArgumentException(value);
			} else {
				return constant;
			}
		}

	}

}
