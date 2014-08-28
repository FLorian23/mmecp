package de.fhg.fokus.streetlife.mmecp.client.siteelement;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.fhg.fokus.streetlife.mmecp.client.dia.ColumnChart;
import de.fhg.fokus.streetlife.mmecp.client.dia.DiagramData;
import de.fhg.fokus.streetlife.mmecp.client.dia.LineChart;
import de.fhg.fokus.streetlife.mmecp.client.dia.PieChart;

public class MathPanel extends VerticalPanel {

	private static MathPanel instance = null;

	private MathPanel() {
		add(new Label("some math..."));
		add(new LineChart(DiagramData.getExample()));
		add(new ColumnChart(DiagramData.getExample()));
		add(new PieChart(DiagramData.getExample()));
	}

	public static MathPanel get() {
		if (instance == null)
			instance = new MathPanel();
		return instance;
	}

}
