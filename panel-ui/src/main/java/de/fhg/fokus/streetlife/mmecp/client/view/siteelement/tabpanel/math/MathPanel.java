package de.fhg.fokus.streetlife.mmecp.client.view.siteelement.tabpanel.math;

import java.util.ArrayList;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ScrollPanel;

import de.fhg.fokus.streetlife.mmecp.client.controller.Subject;
import de.fhg.fokus.streetlife.mmecp.client.controller.Observer;
import de.fhg.fokus.streetlife.mmecp.client.view.CSSDynamicData;
import de.fhg.fokus.streetlife.mmecp.client.view.dia.Chart;
import de.fhg.fokus.streetlife.mmecp.client.view.dia.ColumnChart;
import de.fhg.fokus.streetlife.mmecp.client.view.dia.DiagramData;
import de.fhg.fokus.streetlife.mmecp.client.view.dia.LineChart;
import de.fhg.fokus.streetlife.mmecp.client.view.dia.PieChart;
import de.fhg.fokus.streetlife.mmecp.client.view.siteelement.SiteElement;

public class MathPanel extends SiteElement<HorizontalPanel> implements
		Observer {

	private static MathPanel instance = null;
	public static final String cssID = "Statistik";

	ScrollPanel scrollPanel;
	Grid gridForCharts;

	private enum RESPONSABLE {
		VERTICAL, HORIZONTAL
	};

	private RESPONSABLE responsableStatus = RESPONSABLE.HORIZONTAL;
	ArrayList<Chart> myCharts = new ArrayList<Chart>();

	private MathPanel() {
		super(new HorizontalPanel(), "MathPanel", null);

		scrollPanel = new ScrollPanel();

		addWidgetToPanel(ConfigurationPanelForRetrievalStatisticData.get()
				.getPanel(),
				"configurationPanelForRetrievalStatisticDataPanel", null);
		addWidgetToPanel(scrollPanel, "scrollpanelMath", null);

		gridForCharts = new Grid();
		gridForCharts.getElement().setId("vpForCharts");
		gridForCharts.getElement().addClassName("");

		scrollPanel.add(gridForCharts);
		Subject.get().addToSubjectList(this);
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
		int clientWidth = Window.getClientWidth()
				- ConfigurationPanelForRetrievalStatisticData.get().getPanel()
						.getOffsetWidth();
		int colums = (clientWidth / (CSSDynamicData.chartWidth + CSSDynamicData.chartWidthMargin)) + 1;

		// Vertical View
		if (colums == 1) {
			switch (responsableStatus) {
			case VERTICAL:
				return;
			case HORIZONTAL:
				scrollPanel.remove(gridForCharts);
				ConfigurationPanelForRetrievalStatisticData.get()
						.addWidgetToPanel(gridForCharts,
								"gridPanelUnderConfig", null);
			default:
				break;
			}

			responsableStatus = RESPONSABLE.VERTICAL;

			// Horizontal View
		} else if (colums > 1) {
			switch (responsableStatus) {
			case HORIZONTAL:
				if (gridForCharts.getColumnCount() == colums - 1)
					return;
				else
					colums--;
				break;
			case VERTICAL:
				ConfigurationPanelForRetrievalStatisticData.get().getPanel()
						.remove(gridForCharts);
				scrollPanel.add(gridForCharts);
				break;
			default:
				break;
			}

			responsableStatus = RESPONSABLE.HORIZONTAL;
		}

		gridForCharts.resize(myCharts.size() / colums + 1, colums);
		fillTable();
	}

	public void update() {
		resize();
	}
}
