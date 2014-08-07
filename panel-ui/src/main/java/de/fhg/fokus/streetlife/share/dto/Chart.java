package de.fhg.fokus.streetlife.share.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.gwt.user.client.rpc.IsSerializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({ "labeldescription", "valuedescription", "data" })
public class Chart implements IsSerializable{

	@JsonProperty("labeldescription")
	private String labeldescription;
	@JsonProperty("valuedescription")
	private String valuedescription;
	/**
     * 
     */
	@JsonProperty("data")
	private List<Datum> data = new ArrayList<Datum>();
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("labeldescription")
	public String getLabeldescription() {
		return labeldescription;
	}

	@JsonProperty("labeldescription")
	public void setLabeldescription(String labeldescription) {
		this.labeldescription = labeldescription;
	}

	@JsonProperty("valuedescription")
	public String getValuedescription() {
		return valuedescription;
	}

	@JsonProperty("valuedescription")
	public void setValuedescription(String valuedescription) {
		this.valuedescription = valuedescription;
	}

	/**
     * 
     */
	@JsonProperty("data")
	public List<Datum> getData() {
		return data;
	}

	/**
     * 
     */
	@JsonProperty("data")
	public void setData(List<Datum> data) {
		this.data = data;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	@Override
	public String toString() {
		String ret = "";
		ret += "Labeldescription: " + getLabeldescription() + "\n";
		ret += "Valuedescription: " + getValuedescription() + "\n";

		ret += "Daten:\n";
		for (Datum d : getData()) {
			ret += d.toString();
		}

		ret += "\n";
		return ret;
	}

}
