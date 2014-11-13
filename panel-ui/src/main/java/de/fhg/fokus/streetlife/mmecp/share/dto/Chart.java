package de.fhg.fokus.streetlife.mmecp.share.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Generated;
import javax.validation.Valid;
import javax.validation.constraints.Size;

import com.google.gson.annotations.Expose;
import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * An element type to visualize a chart for a list of label value pairs.
 * 
 */
@Generated("org.jsonschema2pojo")
public class Chart implements IsSerializable {

	@Expose
	private Chart.Type type;
	@Expose
	@Size(max = 30)
	private String labeldescription;
	@Expose
	@Size(max = 30)
	private String valuedescription;
	@Expose
	@Size(min = 1)
	@Valid
	private List<Datum> data = new ArrayList<Datum>();

	/**
	 * 
	 * @return The type
	 */
	public Chart.Type getType() {
		return type;
	}

	/**
	 * 
	 * @param type
	 *            The type
	 */
	public void setType(Chart.Type type) {
		this.type = type;
	}

	/**
	 * 
	 * @return The labeldescription
	 */
	public String getLabeldescription() {
		return labeldescription;
	}

	/**
	 * 
	 * @param labeldescription
	 *            The labeldescription
	 */
	public void setLabeldescription(String labeldescription) {
		this.labeldescription = labeldescription;
	}

	/**
	 * 
	 * @return The valuedescription
	 */
	public String getValuedescription() {
		return valuedescription;
	}

	/**
	 * 
	 * @param valuedescription
	 *            The valuedescription
	 */
	public void setValuedescription(String valuedescription) {
		this.valuedescription = valuedescription;
	}

	/**
	 * 
	 * @return The data
	 */
	public List<Datum> getData() {
		return data;
	}

	/**
	 * 
	 * @param data
	 *            The data
	 */
	public void setData(List<Datum> data) {
		this.data = data;
	}

	@Generated("org.jsonschema2pojo")
	public static enum Type {

		PIECHART("piechart"), BARCHART("barchart");
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

		@Override
		public String toString() {
			return this.value;
		}

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
