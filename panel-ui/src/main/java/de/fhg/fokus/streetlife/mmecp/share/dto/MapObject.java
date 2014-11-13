package de.fhg.fokus.streetlife.mmecp.share.dto;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;
import javax.validation.Valid;
import javax.validation.constraints.Size;

import com.google.gson.annotations.Expose;
import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Schema for STREETLIFE MMECP object.
 * 
 */
@Generated("org.jsonschema2pojo")
public class MapObject implements IsSerializable {

	@Expose
	private int objectID;
	@Expose
	private String objectType;
	@Expose
	@Valid
	private Location location;
	@Expose
	@Size(max = 10)
	@Valid
	private List<Element> elements = new ArrayList<Element>();

	/**
	 * 
	 * @return The objectID
	 */
	public int getObjectID() {
		return objectID;
	}

	/**
	 * 
	 * @param objectID
	 *            The objectID
	 */
	public void setObjectID(int objectID) {
		this.objectID = objectID;
	}

	/**
	 * 
	 * @return The objectType
	 */
	public String getObjectType() {
		return objectType;
	}

	/**
	 * 
	 * @param objectType
	 *            The objectType
	 */
	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	/**
	 * 
	 * @return The location
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * 
	 * @param location
	 *            The location
	 */
	public void setLocation(Location location) {
		this.location = location;
	}

	/**
	 * 
	 * @return The elements
	 */
	public List<Element> getElements() {
		return elements;
	}

	/**
	 * 
	 * @param elements
	 *            The elements
	 */
	public void setElements(List<Element> elements) {
		this.elements = elements;
	}

}
