package de.fhg.fokus.streetlife.mmecp.client.view.siteelement.statistic;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.fhg.fokus.streetlife.mmecp.client.view.dia.ColumnChart;
import de.fhg.fokus.streetlife.mmecp.client.view.dia.DiagramData;
import de.fhg.fokus.streetlife.mmecp.client.view.dia.LineChart;
import de.fhg.fokus.streetlife.mmecp.client.view.dia.PieChart;
import de.fhg.fokus.streetlife.mmecp.client.view.siteelement.SiteElement;

public class MathPanel extends SiteElement<HorizontalPanel> {

	private static MathPanel instance = null;

	private MathPanel() {
		super(new HorizontalPanel(), "MathPanel", null);

		VerticalPanel vp = new VerticalPanel();
		vp.getElement().setId("vpForCharts");
		vp.getElement().addClassName("well");
		vp.add(new LineChart(DiagramData.getExample()));
		vp.add(new ColumnChart(DiagramData.getExample()));
		vp.add(new PieChart(DiagramData.getExample()));
		
		ScrollPanel scrollPanel = new ScrollPanel(vp);

		addWidgetToPanel(ConfigurationPanelForRetrievalStatisticData.get()
				.getPanel(), null, null);
		addWidgetToPanel(scrollPanel, "scrollpanelMath", null);

	}

	public static MathPanel get() {
		if (instance == null)
			instance = new MathPanel();
		return instance;
	}

}
