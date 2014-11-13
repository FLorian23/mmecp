package de.fhg.fokus.streetlife.mmecp.share.dto;

import javax.annotation.Generated;

import com.google.gson.annotations.Expose;
import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * An element type to visualize and place an image at the defined position.
 * 
 */
@Generated("org.jsonschema2pojo")
public class Icon implements IsSerializable {

	@Expose
	private String image;

	/**
	 * 
	 * @return The image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * 
	 * @param image
	 *            The image
	 */
	public void setImage(String image) {
		this.image = image;
	}

}
