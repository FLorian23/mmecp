package de.fhg.fokus.streetlife.client.dia;

import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.visualization.client.VisualizationUtils;
import com.google.gwt.visualization.client.events.SelectHandler;
import com.google.gwt.visualization.client.visualizations.PieChart;
import com.google.gwt.visualization.client.visualizations.PieChart.Options;

public class PieDiagram extends Diagram {

	public PieDiagram(DiagramData diagramData) {
		super(diagramData);
	}

	private Options createOptions() {
		Options options = Options.create();
		options.setWidth(400);
		options.setHeight(240);
		options.set3D(true);
		options.setTitle("My Daily Activities");
		return options;
	}

	private SelectHandler createSelectHandler(final PieChart chart) {
		return new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
//				String message = "";

				// May be multiple selections.
//				JsArray<Selection> selections = chart.getSelections();

//				for (int i = 0; i < selections.length(); i++) {
					// add a new line for each selection
//					message += i == 0 ? "" : "\n";

//					Selection selection = selections.get(i);

//					if (selection.isCell()) {
						// isCell() returns true if a cell has been selected.

						// getRow() returns the row number of the selected cell.
//						int row = selection.getRow();
						// getColumn() returns the column number of the selected
						// cell.
//						int column = selection.getColumn();
//						message += "cell " + row + ":" + column + " selected";
//					} else if (selection.isRow()) {
						// isRow() returns true if an entire row has been
						// selected.

						// getRow() returns the row number of the selected row.
//						int row = selection.getRow();
//						message += "row " + row + " selected";
//					} else {
						// unreachable
//						message += "Pie chart selections should be either row selections or cell selections.";
//						message += "  Other visualizations support column selections as well.";
//					}
//				}

//				Window.alert(message);
			}
		};
	}

	public Widget asWidget() {

		final Panel p = new SimplePanel();
		Runnable onLoadCallback = new Runnable() {
			public void run() {

				PieChart pie = new PieChart(getData().getAbstractDataTable(),
						createOptions());

				pie.addSelectHandler(createSelectHandler(pie));
				p.add(pie);
			}
		};

		VisualizationUtils.loadVisualizationApi(onLoadCallback,
				PieChart.PACKAGE);
		return p;
	}
}