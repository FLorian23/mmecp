package de.fhg.fokus.streetlife.mmecp.client.dia;

public class DataField{
	String field;
	Double value;
	
	public DataField() {
	}
	public DataField(String field, Double value) {
		super();
		this.field = field;
		this.value = value;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	
}