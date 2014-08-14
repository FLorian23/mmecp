package de.fhg.fokus.streetlife.mmecp.share.dto;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.gwt.user.client.rpc.IsSerializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({ "itemtype", "label", "value", "chart" })
public class Element implements IsSerializable{

	@JsonProperty("itemtype")
	private Element.Itemtype itemtype;
	@JsonProperty("label")
	private String label;
	@JsonProperty("value")
	private String value;
	@JsonProperty("chart")
	private Chart chart;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("itemtype")
	public Element.Itemtype getItemtype() {
		return itemtype;
	}

	@JsonProperty("itemtype")
	public void setItemtype(Element.Itemtype itemtype) {
		this.itemtype = itemtype;
	}

	@JsonProperty("label")
	public String getLabel() {
		return label;
	}

	@JsonProperty("label")
	public void setLabel(String label) {
		this.label = label;
	}

	@JsonProperty("value")
	public String getValue() {
		return value;
	}

	@JsonProperty("value")
	public void setValue(String value) {
		this.value = value;
	}

	@JsonProperty("chart")
	public Chart getChart() {
		return chart;
	}

	@JsonProperty("chart")
	public void setChart(Chart chart) {
		this.chart = chart;
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
		String ret = "Element: " + itemtype + "\n";
		switch (getItemtype()) {
		case BARCHART:
			ret += "Chart: \n" + getChart().toString();
			break;
		case CAPTION:
			ret += "Caption:" + getValue();
			break;
		case DATE:
			ret += "Date:" + getValue();
			break;
		case DESCRIPTION:
			ret += "Description:" + getValue();
			break;
		case LABEL:
			ret += getLabel() + ": " + getValue();
			break;
		case PIECHART:
			ret += "Chart: \n" + getChart().toString();
			break;

		default:
			break;
		}
		ret += "\n";
		return ret;
	}

	@Generated("org.jsonschema2pojo")
	public static enum Itemtype implements com.google.gwt.user.client.rpc.IsSerializable {

		LABEL("label"), PIECHART("piechart"), BARCHART("barchart"), CAPTION(
				"caption"), DESCRIPTION("description"), DATE("date");
		private final String value;
		private static Map<String, Element.Itemtype> constants = new HashMap<String, Element.Itemtype>();

		static {
			for (Element.Itemtype c : Element.Itemtype.values()) {
				constants.put(c.value, c);
			}
		}

		private Itemtype(String value) {
			this.value = value;
		}

		@JsonValue
		@Override
		public String toString() {
			return this.value;
		}

		@JsonCreator
		public static Element.Itemtype fromValue(String value) {
			Element.Itemtype constant = constants.get(value);
			if (constant == null) {
				throw new IllegalArgumentException(value);
			} else {
				return constant;
			}
		}

	}

}
