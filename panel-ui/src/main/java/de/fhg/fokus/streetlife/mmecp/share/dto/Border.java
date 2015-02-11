package de.fhg.fokus.streetlife.mmecp.share.dto;

import com.google.gwt.user.client.rpc.IsSerializable;
import java.util.HashMap;
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

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
		"width",
		"style"
})
public class Border implements IsSerializable {

	@JsonProperty("width")
	private Double width;
	@JsonProperty("style")
	private Border.Style style;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 *
	 * @return
	 * The width
	 */
	@JsonProperty("width")
	public Double getWidth() {
		return width;
	}

	/**
	 *
	 * @param width
	 * The width
	 */
	@JsonProperty("width")
	public void setWidth(Double width) {
		this.width = width;
	}

	/**
	 *
	 * @return
	 * The style
	 */
	@JsonProperty("style")
	public Border.Style getStyle() {
		return style;
	}

	/**
	 *
	 * @param style
	 * The style
	 */
	@JsonProperty("style")
	public void setStyle(Border.Style style) {
		this.style = style;
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
	public static enum Style {

		@SerializedName("solid")
		SOLID("solid"),
		@SerializedName("dashed")
		DASHED("dashed");
		private final String value;
		private static Map<String, Border.Style> constants = new HashMap<String, Border.Style>();

		static {
			for (Border.Style c: values()) {
				constants.put(c.value, c);
			}
		}

		private Style(String value) {
			this.value = value;
		}

		@JsonValue
		@Override
		public String toString() {
			return this.value;
		}

		@JsonCreator
		public static Border.Style fromValue(String value) {
			Border.Style constant = constants.get(value);
			if (constant == null) {
				throw new IllegalArgumentException(value);
			} else {
				return constant;
			}
		}

	}

}