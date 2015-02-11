package de.fhg.fokus.streetlife.mmecp.share.dto;

import com.google.gwt.user.client.rpc.IsSerializable;
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

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({ "type", "coordinates" })
public class Location implements IsSerializable {

	@JsonProperty("type")
	private Location.Type type;
	@JsonProperty("coordinates")
	private List<Double> coordinates = new ArrayList<Double>();
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 * @return The type
	 */
	@JsonProperty("type")
	public Location.Type getType() {
		return type;
	}

	/**
	 * 
	 * @param type
	 *            The type
	 */
	@JsonProperty("type")
	public void setType(Location.Type type) {
		this.type = type;
	}

	/**
	 * 
	 * @return The coordinates
	 */
	@JsonProperty("coordinates")
	public List<Double> getCoordinates() {
		return coordinates;
	}

	/**
	 * 
	 * @param coordinates
	 *            The coordinates
	 */
	@JsonProperty("coordinates")
	public void setCoordinates(List<Double> coordinates) {
		this.coordinates = coordinates;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	@Generated("org.jsonschema2pojo")
	public static enum Type implements IsSerializable{

		POINT("Point");
		private final String value;
		private static Map<String, Location.Type> constants = new HashMap<String, Location.Type>();

		static {
			for (Location.Type c : values()) {
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
		public static Location.Type fromValue(String value) {
			Location.Type constant = constants.get(value);
			if (constant == null) {
				throw new IllegalArgumentException(value);
			} else {
				return constant;
			}
		}

	}

}
