package de.fhg.fokus.streetlife.mmecp.share.dto;

import javax.annotation.Generated;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

import com.google.gson.annotations.Expose;
import com.google.gwt.user.client.rpc.IsSerializable;

@Generated("org.jsonschema2pojo")
public class Color implements IsSerializable {

	@Expose
	@DecimalMin("0")
	@DecimalMax("255")
	private double red;
	@Expose
	@DecimalMin("0")
	@DecimalMax("255")
	private double green;
	@Expose
	@DecimalMin("0")
	@DecimalMax("255")
	private double blue;
	@Expose
	@DecimalMin("0.0")
	@DecimalMax("1.0")
	private double alpha;

	/**
	 * 
	 * @return The red
	 */
	public double getRed() {
		return red;
	}

	/**
	 * 
	 * @param red
	 *            The red
	 */
	public void setRed(double red) {
		this.red = red;
	}

	/**
	 * 
	 * @return The green
	 */
	public double getGreen() {
		return green;
	}

	/**
	 * 
	 * @param green
	 *            The green
	 */
	public void setGreen(double green) {
		this.green = green;
	}

	/**
	 * 
	 * @return The blue
	 */
	public double getBlue() {
		return blue;
	}

	/**
	 * 
	 * @param blue
	 *            The blue
	 */
	public void setBlue(double blue) {
		this.blue = blue;
	}

	public String getHex() {
		return ("#" +
				Integer.toHexString(0x100 | (int) red).substring(1) +
				Integer.toHexString(0x100 | (int) green).substring(1) +
				Integer.toHexString(0x100 | (int) blue).substring(1));
	}

	/**
	 * 
	 * @return The alpha
	 */
	public double getAlpha() {
		return alpha;
	}

	/**
	 * 
	 * @param alpha
	 *            The alpha
	 */
	public void setAlpha(double alpha) {
		this.alpha = alpha;
	}

}
