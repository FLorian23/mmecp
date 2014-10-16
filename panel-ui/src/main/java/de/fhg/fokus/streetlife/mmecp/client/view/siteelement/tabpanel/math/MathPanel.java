package de.fhg.fokus.streetlife.mmecp.client.view.siteelement.tabpanel.math;

import java.util.ArrayList;

import com.github.gwtbootstrap.client.ui.DataGrid;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ScrollPanel;

import de.fhg.fokus.streetlife.mmecp.client.test.LogPanel;
import de.fhg.fokus.streetlife.mmecp.client.view.CSSDynamicData;
import de.fhg.fokus.streetlife.mmecp.client.view.dia.Chart;
import de.fhg.fokus.streetlife.mmecp.client.view.dia.ColumnChart;
import de.fhg.fokus.streetlife.mmecp.client.view.dia.DiagramData;
import de.fhg.fokus.streetlife.mmecp.client.view.dia.LineChart;
import de.fhg.fokus.streetlife.mmecp.client.view.dia.PieChart;
import de.fhg.fokus.streetlife.mmecp.client.view.siteelement.SiteElement;

public class MathPanel extends SiteElement<HorizontalPanel> {

	private static MathPanel instance = null;
	public static final String cssID = "Statistik";

	ScrollPanel scrollPanel;
	Grid gridForCharts;
	int previousColums = -1;

	ArrayList<Chart> myCharts = new ArrayList<Chart>();

	private MathPanel() {
		super(new HorizontalPanel(), "MathPanel", null);

		scrollPanel = new ScrollPanel();
		com.github.gwtbootstrap.client.ui.DataGrid<Chart> a = new DataGrid<Chart>();

		addWidgetToPanel(ConfigurationPanelForRetrievalStatisticData.get()
				.getPanel(),
				"configurationPanelForRetrievalStatisticDataPanel", null);
		addWidgetToPanel(scrollPanel, "scrollpanelMath", null);

		gridForCharts = new Grid();
		gridForCharts.getElement().setId("vpForCharts");
		gridForCharts.getElement().addClassName("");

		scrollPanel.add(gridForCharts);

		addWidgetToPanel(LogPanel.get(), "logpanel", null);
	}

	public static MathPanel get() {
		if (instance == null)
			instance = new MathPanel();
		return instance;
	}

	public void buildTable() {
		myCharts.clear();

		myCharts.add(new LineChart(DiagramData.getExample()));
		myCharts.add(new ColumnChart(DiagramData.getExample()));
		myCharts.add(new PieChart(DiagramData.getExample()));
		myCharts.add(new ColumnChart(DiagramData.getExample()));
		myCharts.add(new PieChart(DiagramData.getExample()));
		myCharts.add(new LineChart(DiagramData.getExample()));
		myCharts.add(new PieChart(DiagramData.getExample()));
		resize();
	}

	private void fillTable() {
		gridForCharts.clear(true);

		for (int x = 0; x < gridForCharts.getRowCount(); x++) {
			for (int y = 0; y < gridForCharts.getColumnCount(); y++) {
				if ((x * gridForCharts.getColumnCount() + y) == myCharts.size()) {
					break;
				}
				gridForCharts.setWidget(x, y,
						myCharts.get(x * gridForCharts.getColumnCount() + y));
			}
		}
	}

	public void resize() {
		int clientWidth = Window.getClientWidth() - 424;
		int colums = clientWidth / (CSSDynamicData.chartWidth + 20);

		if (previousColums == -1)
			previousColums = colums;
		else if (previousColums == colums)
			return;
		if (colums == 0){
			ConfigurationPanelForRetrievalStatisticData.get().addWidgetToPanel(gridForCharts, "gridPanelUnderConfig", null);
			gridForCharts.resize(myCharts.size(), 1);
			fillTable();
			previousColums = colums;
			return;
		}else if (previousColums == 0){
			ConfigurationPanelForRetrievalStatisticData.get().getPanel().remove(gridForCharts);
			scrollPanel.add(gridForCharts);
		}
		gridForCharts.resize(myCharts.size() / colums + 1, colums);
		fillTable();
		previousColums = colums;
	}
}
