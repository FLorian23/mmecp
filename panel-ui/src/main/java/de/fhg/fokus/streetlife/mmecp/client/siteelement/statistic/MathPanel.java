package de.fhg.fokus.streetlife.mmecp.client.siteelement.statistic;

import com.google.gwt.dom.client.Style.Overflow;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.dom.client.Style.Visibility;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.fhg.fokus.streetlife.mmecp.client.dia.ColumnChart;
import de.fhg.fokus.streetlife.mmecp.client.dia.DiagramData;
import de.fhg.fokus.streetlife.mmecp.client.dia.LineChart;
import de.fhg.fokus.streetlife.mmecp.client.dia.PieChart;

public class MathPanel extends VerticalPanel {

	private static MathPanel instance = null;

	private MathPanel() {
		getElement().setId("MathPanel");
		
		add(ConfigurationPanelForRetrievalStatisticData.get());

		VerticalPanel vp = new VerticalPanel();
//		getElement().getStyle().setHeight(Window.getClientHeight(), Unit.PX);
//		getElement().getStyle().setWidth(Window.getClientWidth(), Unit.PX);
		vp.getElement().setId("vpForCharts");
		vp.add(new LineChart(DiagramData.getExample()));
		vp.add(new ColumnChart(DiagramData.getExample()));
		vp.add(new PieChart(DiagramData.getExample()));

		ScrollPanel scrollPanel = new ScrollPanel(vp);
		scrollPanel.getElement().setId("scrollpanelMath");
		add(scrollPanel);
	}

	public static MathPanel get() {
		if (instance == null)
			instance = new MathPanel();
		return instance;
	}

}
