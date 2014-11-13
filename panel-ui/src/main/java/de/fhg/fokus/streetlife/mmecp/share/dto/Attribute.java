package de.fhg.fokus.streetlife.mmecp.share.dto;

import javax.annotation.Generated;
import javax.validation.constraints.Size;

import com.google.gson.annotations.Expose;
import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * An element type to visualize a single label value pair.
 * 
 */
@Generated("org.jsonschema2pojo")
public class Attribute implements IsSerializable {

	@Expose
	@Size(max = 30)
	private String label;
	@Expose
	@Size(max = 50)
	private String value;

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
	public String getValue() {
		return value;
	}

	/**
	 * 
	 * @param value
	 *            The value
	 */
	public void setValue(String value) {
		this.value = value;
	}

}
