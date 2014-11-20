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
 * An element type to visualize and place an image at the defined position.
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({ "type", "image" })
public class Icon implements Serializable {

	@JsonProperty("type")
	private Icon.Type type;
	@JsonProperty("image")
	private String image;

	/**
	 * 
	 * @return The type
	 */
	@JsonProperty("type")
	public Icon.Type getType() {
		return type;
	}

	/**
	 * 
	 * @param type
	 *            The type
	 */
	@JsonProperty("type")
	public void setType(Icon.Type type) {
		this.type = type;
	}

	/**
	 * 
	 * @return The image
	 */
	@JsonProperty("image")
	public String getImage() {
		return image;
	}

	/**
	 * 
	 * @param image
	 *            The image
	 */
	@JsonProperty("image")
	public void setImage(String image) {
		this.image = image;
	}

	@Generated("org.jsonschema2pojo")
	public static enum Type {

		ICON("icon");
		private final String value;
		private static Map<String, Icon.Type> constants = new HashMap<String, Icon.Type>();

		static {
			for (Icon.Type c : values()) {
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
		public static Icon.Type fromValue(String value) {
			Icon.Type constant = constants.get(value);
			if (constant == null) {
				throw new IllegalArgumentException(value);
			} else {
				return constant;
			}
		}

	}

}
