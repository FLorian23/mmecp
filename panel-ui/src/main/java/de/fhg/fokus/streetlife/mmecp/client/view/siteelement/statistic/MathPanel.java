package de.fhg.fokus.streetlife.mmecp.client.view.siteelement.statistic;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.fhg.fokus.streetlife.mmecp.client.view.dia.ColumnChart;
import de.fhg.fokus.streetlife.mmecp.client.view.dia.DiagramData;
import de.fhg.fokus.streetlife.mmecp.client.view.dia.LineChart;
import de.fhg.fokus.streetlife.mmecp.client.view.dia.PieChart;
import de.fhg.fokus.streetlife.mmecp.client.view.siteelement.SiteElement;

public class MathPanel extends SiteElement<HorizontalPanel> {

	private static MathPanel instance = null;
	
	ScrollPanel scrollPanel;
	
	private MathPanel() {
		super(new HorizontalPanel(), "MathPanel", null);

//		VerticalPanel vp = new VerticalPanel();
//		Grid g = new Grid(1,2);
//		g.getElement().setId("vpForCharts");
//		g.getElement().addClassName("well");

		
		scrollPanel = new ScrollPanel();

		addWidgetToPanel(ConfigurationPanelForRetrievalStatisticData.get()
				.getPanel(), null, null);
		addWidgetToPanel(scrollPanel, "scrollpanelMath", null);

	}

	public static MathPanel get() {
		if (instance == null)
			instance = new MathPanel();
		return instance;
	}

	public void buildTable() {

		Grid g = new Grid(1,2);
		g.getElement().setId("vpForCharts");
		g.getElement().addClassName("well");

		g.resize(4, 2);
		
		g.setWidget(0, 0, new LineChart(DiagramData.getExample()));
		g.setWidget(0, 1, new ColumnChart(DiagramData.getExample()));
		g.setWidget(1, 0, new PieChart(DiagramData.getExample()));
		g.setWidget(1, 1, new ColumnChart(DiagramData.getExample()));
		g.setWidget(2, 0, new PieChart(DiagramData.getExample()));
		g.setWidget(2, 1, new ColumnChart(DiagramData.getExample()));
		g.setWidget(3, 0, new PieChart(DiagramData.getExample()));
		
		scrollPanel.add(g);
	}
}
