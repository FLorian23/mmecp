package de.fhg.fokus.streetlife.mmecp.share.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * An element type to visualize and colorize a specific geolocation.
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({ "type", "area", "color" })
public class Maparea implements Serializable {

	@JsonProperty("type")
	private Maparea.Type type;
	@JsonProperty("area")
	private Area area;
	/**
     * 
     */
	@JsonProperty("color")
	private Color color;

	/**
	 * 
	 * @return The type
	 */
	@JsonProperty("type")
	public Maparea.Type getType() {
		return type;
	}

	/**
	 * 
	 * @param type
	 *            The type
	 */
	@JsonProperty("type")
	public void setType(Maparea.Type type) {
		this.type = type;
	}

	/**
	 * 
	 * @return The area
	 */
	@JsonProperty("area")
	public Area getArea() {
		return area;
	}

	/**
	 * 
	 * @param area
	 *            The area
	 */
	@JsonProperty("area")
	public void setArea(Area area) {
		this.area = area;
	}

	/**
	 * 
	 * @return The color
	 */
	@JsonProperty("color")
	public Color getColor() {
		return color;
	}

	/**
	 * 
	 * @param color
	 *            The color
	 */
	@JsonProperty("color")
	public void setColor(Color color) {
		this.color = color;
	}

	@Generated("org.jsonschema2pojo")
	public static enum Type {

		MAPAREA("maparea");
		private final String value;
		private static Map<String, Maparea.Type> constants = new HashMap<String, Maparea.Type>();

		static {
			for (Maparea.Type c : values()) {
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
		public static Maparea.Type fromValue(String value) {
			Maparea.Type constant = constants.get(value);
			if (constant == null) {
				throw new IllegalArgumentException(value);
			} else {
				return constant;
			}
		}

	}

}
