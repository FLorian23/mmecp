package de.fhg.fokus.streetlife.mmecp.client.dia;

import java.util.List;

import com.google.gwt.visualization.client.AbstractDataTable;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;

import de.fhg.fokus.streetlife.mmecp.dto.Datum;

public class DiagramData {

	private String label;
	private DataField[] fields;

	public DiagramData() {
	}

	public DiagramData(String label, DataField[] fields) {
		this.label = label;
		this.fields = fields;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public DataField[] getFields() {
		return fields;
	}

	public void setFields(DataField[] fields) {
		this.fields = fields;
	}

	public static DiagramData getExample() {
		DataField[] fields = new DataField[3];
		
		fields[0] = new DataField();
		fields[0].setField("car");
		fields[0].setValue(30d);

		fields[1] = new DataField();
		fields[1].setField("walk");
		fields[1].setValue(20d);

		fields[2] = new DataField();
		fields[2].setField("bicycle");
		fields[2].setValue(50d);

		return new DiagramData("Modal Split", fields);
	}
	
	public AbstractDataTable getAbstractDataTable(){
		DataTable data = DataTable.create();
		
		data.addColumn(ColumnType.STRING, fields[0].getField());
		for (int i = 1;i<fields.length;i++){
			data.addColumn(ColumnType.NUMBER, fields[i].getField());
		}
		
		data.addRows(fields.length);
		
		for (int i = 0;i<fields.length;i++){
			data.setValue(i, 0, fields[i].getField());
			data.setValue(i, 1, fields[i].getValue());
		}
		
		return data;
	}

	public static DiagramData fromDTOChartData(List<Datum> data) {
		
		DataField[] fields = new DataField[data.size()];
		
		for (int i = 0; i<fields.length;i++){
			fields[i] = new DataField();
			fields[i].setField(data.get(i).getLabel());
			fields[i].setValue(data.get(i).getValue());
		}

		return new DiagramData("Modal Split", fields);
	}
}
