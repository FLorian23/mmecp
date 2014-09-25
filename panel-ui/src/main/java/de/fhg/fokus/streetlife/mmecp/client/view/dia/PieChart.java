package de.fhg.fokus.streetlife.mmecp.client.view.dia;

import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.visualization.client.VisualizationUtils;
import com.google.gwt.visualization.client.visualizations.PieChart.Options;

public class PieChart extends Chart {

	public PieChart(DiagramData diagramData) {
		super(diagramData);
	}

	private Options createOptions() {
		Options options = Options.create();
		options.setWidth(400);
		options.setHeight(240);
		options.set3D(true);
		options.setTitle(getData().getTitle());
		return options;
	}

	public Widget asWidget() {

		final Panel p = new SimplePanel();
		p.getElement().addClassName("well well-sm diagram");
		Runnable onLoadCallback = new Runnable() {
			public void run() {

				com.google.gwt.visualization.client.visualizations.PieChart pie = new com.google.gwt.visualization.client.visualizations.PieChart(
						getData().getAbstractDataTable(), createOptions());

				p.add(pie);
			}
		};

		VisualizationUtils
				.loadVisualizationApi(
						onLoadCallback,
						com.google.gwt.visualization.client.visualizations.PieChart.PACKAGE);
		return p;
	}
}