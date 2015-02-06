package de.fhg.fokus.streetlife.mmecp.share.dto;

import com.google.gwt.user.client.rpc.IsSerializable;
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
@JsonPropertyOrder({ "red", "green", "blue", "alpha" })
public class Color implements IsSerializable {

	@JsonProperty("red")
	private Double red;
	@JsonProperty("green")
	private Double green;
	@JsonProperty("blue")
	private Double blue;
	@JsonProperty("alpha")
	private Double alpha;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 * @return The red
	 */
	@JsonProperty("red")
	public Double getRed() {
		return red;
	}

	/**
	 * 
	 * @param red
	 *            The red
	 */
	@JsonProperty("red")
	public void setRed(Double red) {
		this.red = red;
	}

	/**
	 * 
	 * @return The green
	 */
	@JsonProperty("green")
	public Double getGreen() {
		return green;
	}

	/**
	 * 
	 * @param green
	 *            The green
	 */
	@JsonProperty("green")
	public void setGreen(Double green) {
		this.green = green;
	}

	/**
	 * 
	 * @return The blue
	 */
	@JsonProperty("blue")
	public Double getBlue() {
		return blue;
	}

	/**
	 * 
	 * @param blue
	 *            The blue
	 */
	@JsonProperty("blue")
	public void setBlue(Double blue) {
		this.blue = blue;
	}

	/**
	 * 
	 * @return The alpha
	 */
	@JsonProperty("alpha")
	public Double getAlpha() {
		return alpha;
	}

	public String getHex() {
		return ("#" + Integer.toHexString(0x100 | red.intValue()).substring(1)
				+ Integer.toHexString(0x100 | green.intValue()).substring(1) + Integer
				.toHexString(0x100 | blue.intValue()).substring(1));
	}

	/**
	 * 
	 * @param alpha
	 *            The alpha
	 */
	@JsonProperty("alpha")
	public void setAlpha(Double alpha) {
		this.alpha = alpha;
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
