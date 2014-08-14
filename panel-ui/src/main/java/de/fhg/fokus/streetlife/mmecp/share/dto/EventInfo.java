package de.fhg.fokus.streetlife.mmecp.share.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.gwt.user.client.rpc.IsSerializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({ "location", "datetime", "elements" })
public class EventInfo implements IsSerializable {

	/**
     * 
     */
	private int id;
	@JsonProperty("location")
	private Location location;
	@JsonProperty("datetime")
	private Date datetime;
	@JsonProperty("elements")
	private List<Element> elements = new ArrayList<Element>();
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	private String message;

	/**
     * 
     */
	@JsonProperty("location")
	public Location getLocation() {
		return location;
	}

	/**
     * 
     */
	@JsonProperty("location")
	public void setLocation(Location location) {
		this.location = location;
	}

	@JsonProperty("datetime")
	public Date getDatetime() {
		return datetime;
	}

	@JsonProperty("datetime")
	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	@JsonProperty("elements")
	public List<Element> getElements() {
		return elements;
	}

	@JsonProperty("elements")
	public void setElements(List<Element> elements) {
		this.elements = elements;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	@Override
	public String toString() {
		String ret = "";
		ret += "EventInfo:\n==========\n";
		ret += "Location: " + getLocation().toString() + "\n";
		ret += "DateTime: " + getDatetime().toString() + "\n\n";
		ret += "Elemente:\n";
		for (int i = 0; i < getElements().size(); i++) {
			ret += i + ".";
			ret += getElements().get(i).toString() + "\n";
		}
		return ret;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
