package de.fhg.fokus.streetlife.mmecp.share.dto;

import javax.annotation.Generated;
import javax.validation.Valid;

import com.google.gson.annotations.Expose;
import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * An element type to visualize and colorize a specific geolocation.
 * 
 */
@Generated("org.jsonschema2pojo")
public class Maparea implements IsSerializable {

	@Expose
	@Valid
	private Area area;
	/**
     * 
     */
	@Expose
	@Valid
	private Color color;

	/**
	 * 
	 * @return The area
	 */
	public Area getArea() {
		return area;
	}

	/**
	 * 
	 * @param area
	 *            The area
	 */
	public void setArea(Area area) {
		this.area = area;
	}

	/**
	 * 
	 * @return The color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * 
	 * @param color
	 *            The color
	 */
	public void setColor(Color color) {
		this.color = color;
	}

}
