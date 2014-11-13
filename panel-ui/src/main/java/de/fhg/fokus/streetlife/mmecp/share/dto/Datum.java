package de.fhg.fokus.streetlife.mmecp.share.dto;

import javax.annotation.Generated;

import com.google.gson.annotations.Expose;
import com.google.gwt.user.client.rpc.IsSerializable;

@Generated("org.jsonschema2pojo")
public class Datum implements IsSerializable {

	@Expose
	private String label;
	@Expose
	private double value;

	/**
	 * 
	 * @return The label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * 
	 * @param label
	 *            The label
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * 
	 * @return The value
	 */
	public double getValue() {
		return value;
	}

	/**
	 * 
	 * @param value
	 *            The value
	 */
	public void setValue(double value) {
		this.value = value;
	}

}
