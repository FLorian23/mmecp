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

@Generated("org.jsonschema2pojo")
public class Location implements IsSerializable {

	@Expose
	private Location.Type type;
	@Expose
	@Size(min = 2, max = 2)
	@Valid
	private List<Double> coordinates = new ArrayList<Double>();

	/**
	 * 
	 * @return The type
	 */
	public Location.Type getType() {
		return type;
	}

	/**
	 * 
	 * @param type
	 *            The type
	 */
	public void setType(Location.Type type) {
		this.type = type;
	}

	/**
	 * 
	 * @return The coordinates
	 */
	public List<Double> getCoordinates() {
		return coordinates;
	}

	/**
	 * 
	 * @param coordinates
	 *            The coordinates
	 */
	public void setCoordinates(List<Double> coordinates) {
		this.coordinates = coordinates;
	}

	@Generated("org.jsonschema2pojo")
	public static enum Type {

		POINT("Point");
		private final String value;
		private static Map<String, Location.Type> constants = new HashMap<String, Location.Type>();

		static {
			for (Location.Type c : values()) {
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

		public static Location.Type fromValue(String value) {
			Location.Type constant = constants.get(value);
			if (constant == null) {
				throw new IllegalArgumentException(value);
			} else {
				return constant;
			}
		}

	}

}
