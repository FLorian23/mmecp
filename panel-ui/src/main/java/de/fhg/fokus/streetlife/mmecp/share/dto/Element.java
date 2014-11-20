package de.fhg.fokus.streetlife.mmecp.share.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({ "attribute", "chart", "icon", "maparea" })
public class Element implements Serializable {

	/**
	 * An element type to visualize a single label value pair.
	 * 
	 */
	@JsonProperty("attribute")
	private Attribute attribute;
	/**
	 * An element type to visualize a chart for a list of label value pairs.
	 * 
	 */
	@JsonProperty("chart")
	private Chart chart;
	/**
	 * An element type to visualize and place an image at the defined position.
	 * 
	 */
	@JsonProperty("icon")
	private Icon icon;
	/**
	 * An element type to visualize and colorize a specific geolocation.
	 * 
	 */
	@JsonProperty("maparea")
	private Maparea maparea;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * An element type to visualize a single label value pair.
	 * 
	 * @return The attribute
	 */
	@JsonProperty("attribute")
	public Attribute getAttribute() {
		return attribute;
	}

	/**
	 * An element type to visualize a single label value pair.
	 * 
	 * @param attribute
	 *            The attribute
	 */
	@JsonProperty("attribute")
	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
	}

	/**
	 * An element type to visualize a chart for a list of label value pairs.
	 * 
	 * @return The chart
	 */
	@JsonProperty("chart")
	public Chart getChart() {
		return chart;
	}

	/**
	 * An element type to visualize a chart for a list of label value pairs.
	 * 
	 * @param chart
	 *            The chart
	 */
	@JsonProperty("chart")
	public void setChart(Chart chart) {
		this.chart = chart;
	}

	/**
	 * An element type to visualize and place an image at the defined position.
	 * 
	 * @return The icon
	 */
	@JsonProperty("icon")
	public Icon getIcon() {
		return icon;
	}

	/**
	 * An element type to visualize and place an image at the defined position.
	 * 
	 * @param icon
	 *            The icon
	 */
	@JsonProperty("icon")
	public void setIcon(Icon icon) {
		this.icon = icon;
	}

	/**
	 * An element type to visualize and colorize a specific geolocation.
	 * 
	 * @return The maparea
	 */
	@JsonProperty("maparea")
	public Maparea getMaparea() {
		return maparea;
	}

	/**
	 * An element type to visualize and colorize a specific geolocation.
	 * 
	 * @param maparea
	 *            The maparea
	 */
	@JsonProperty("maparea")
	public void setMaparea(Maparea maparea) {
		this.maparea = maparea;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}
