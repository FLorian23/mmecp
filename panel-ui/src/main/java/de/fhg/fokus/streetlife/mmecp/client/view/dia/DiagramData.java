package de.fhg.fokus.streetlife.mmecp.client.view.dia;

import java.util.List;

import com.google.gwt.visualization.client.AbstractDataTable;
import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;
import com.google.gwt.visualization.client.DataTable;

import de.fhg.fokus.streetlife.mmecp.share.dto.*;

public class DiagramData {

	private String title;
	private String titleX;
	private String titleY;
	private DataField[] fields;

	public DiagramData() {
	}

	public DiagramData(String title, DataField[] fields, String titleX, String titleY) {
		this.title = title;
		this.fields = fields;
		this.titleX = titleX;
		this.titleY = titleY;
	}

	public String getTitleX() {
		return titleX;
	}

	public void setTitleX(String titleX) {
		this.titleX = titleX;
	}

	public String getTitleY() {
		return titleY;
	}

	public void setTitleY(String titleY) {
		this.titleY = titleY;
	}

	public DataField[] getFields() {
		return fields;
	}

	public void setFields(DataField[] fields) {
		this.fields = fields;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

		return new DiagramData("Modal Split", fields, "%", "modal");
	}

	public AbstractDataTable getAbstractDataTable() {
		DataTable data = DataTable.create();

		data.addColumn(ColumnType.STRING, titleX);
		//for (int i = 1; i < fields.length; i++) {
		data.addColumn(ColumnType.NUMBER, titleY);
		//}

		data.addRows(fields.length);

		for (int i = 0; i < fields.length; i++) {
			data.setValue(i, 0, fields[i].getField());
			data.setValue(i, 1, fields[i].getValue());
		}

		return data;
	}

	public static DiagramData fromDTOChart(de.fhg.fokus.streetlife.mmecp.share.dto.Chart chart) {

		List<Datum> data = chart.getData();
		DataField[] fields = new DataField[data.size()];

		for (int i = 0; i < fields.length; i++) {
			fields[i] = new DataField();
			fields[i].setField(data.get(i).getLabel());
			fields[i].setValue(data.get(i).getValue() * 100);
		}

		return new DiagramData("Modal Split", fields, chart.getLabeldescription(), chart.getValuedescription());
	}
}
