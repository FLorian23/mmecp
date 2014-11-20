package de.fhg.fokus.streetlife.mmecp.share.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.gson.annotations.SerializedName;

/**
 * An element type to visualize a chart for a list of label value pairs.
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({ "type", "labeldescription", "valuedescription", "data" })
public class Chart implements Serializable {

	@JsonProperty("type")
	private Chart.Type type;
	@JsonProperty("labeldescription")
	private String labeldescription;
	@JsonProperty("valuedescription")
	private String valuedescription;
	@JsonProperty("data")
	private List<Datum> data = new ArrayList<Datum>();

	/**
	 * 
	 * @return The type
	 */
	@JsonProperty("type")
	public Chart.Type getType() {
		return type;
	}

	/**
	 * 
	 * @param type
	 *            The type
	 */
	@JsonProperty("type")
	public void setType(Chart.Type type) {
		this.type = type;
	}

	/**
	 * 
	 * @return The labeldescription
	 */
	@JsonProperty("labeldescription")
	public String getLabeldescription() {
		return labeldescription;
	}

	/**
	 * 
	 * @param labeldescription
	 *            The labeldescription
	 */
	@JsonProperty("labeldescription")
	public void setLabeldescription(String labeldescription) {
		this.labeldescription = labeldescription;
	}

	/**
	 * 
	 * @return The valuedescription
	 */
	@JsonProperty("valuedescription")
	public String getValuedescription() {
		return valuedescription;
	}

	/**
	 * 
	 * @param valuedescription
	 *            The valuedescription
	 */
	@JsonProperty("valuedescription")
	public void setValuedescription(String valuedescription) {
		this.valuedescription = valuedescription;
	}

	/**
	 * 
	 * @return The data
	 */
	@JsonProperty("data")
	public List<Datum> getData() {
		return data;
	}

	/**
	 * 
	 * @param data
	 *            The data
	 */
	@JsonProperty("data")
	public void setData(List<Datum> data) {
		this.data = data;
	}

	@Generated("org.jsonschema2pojo")
	public static enum Type {

		@SerializedName("piechart")
		PIECHART("piechart"),
		
		@SerializedName("barchart")
		BARCHART("barchart"),
		
		@SerializedName("linechart")
		LINECHART("linechart");
		private final String value;
		private static Map<String, Chart.Type> constants = new HashMap<String, Chart.Type>();

		static {
			for (Chart.Type c : values()) {
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
		public static Chart.Type fromValue(String value) {
			Chart.Type constant = constants.get(value);
			if (constant == null) {
				throw new IllegalArgumentException(value);
			} else {
				return constant;
			}
		}

	}

}