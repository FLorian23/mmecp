package de.fhg.fokus.streetlife.mmecp.client.view.dia;

import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.visualization.client.LegendPosition;
import com.google.gwt.visualization.client.VisualizationUtils;
import com.google.gwt.visualization.client.visualizations.LineChart.Options;

import de.fhg.fokus.streetlife.mmecp.client.view.CSSDynamicData;

public class LineChart extends Chart {

	public LineChart(DiagramData diagramData) {
		super(diagramData);
	}

	private Options createOptions() {
		Options options = Options.create();
		options.setWidth(CSSDynamicData.chartWidth);
		options.setHeight(CSSDynamicData.chartHeight);
		// options.set3D(true);
		options.setLegend(LegendPosition.NONE);
		options.setTitle(getData().getTitle());
		options.setTitleX(getData().getTitleX());
		options.setTitleY(getData().getTitleY());
		return options;
	}

	public Widget asWidget() {

		final Panel p = new SimplePanel();
		p.getElement().addClassName("well well-sm diagram");
		Runnable onLoadCallback = new Runnable() {
			public void run() {

				com.google.gwt.visualization.client.visualizations.LineChart lin = new com.google.gwt.visualization.client.visualizations.LineChart(
						getData().getAbstractDataTable(), createOptions());

				p.add(lin);
			}
		};

		VisualizationUtils
				.loadVisualizationApi(
						onLoadCallback,
						com.google.gwt.visualization.client.visualizations.PieChart.PACKAGE);
		return p;
	}

}
