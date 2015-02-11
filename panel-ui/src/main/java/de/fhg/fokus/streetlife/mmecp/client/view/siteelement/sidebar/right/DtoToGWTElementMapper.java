package de.fhg.fokus.streetlife.mmecp.client.view.siteelement.sidebar.right;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;

import de.fhg.fokus.streetlife.mmecp.client.controller.LOG;
import de.fhg.fokus.streetlife.mmecp.client.model.EventInfoDataMapperImpl;
import de.fhg.fokus.streetlife.mmecp.client.model.IEventInfoDataMapper;
import de.fhg.fokus.streetlife.mmecp.client.view.CSSDynamicData;
import de.fhg.fokus.streetlife.mmecp.client.view.dia.*;
import de.fhg.fokus.streetlife.mmecp.client.view.siteelement.tabpanel.map.GuidancePopUpPanel;
import de.fhg.fokus.streetlife.mmecp.client.view.siteelement.tabpanel.map.MapContainer;
import de.fhg.fokus.streetlife.mmecp.share.dto.*;

public class DtoToGWTElementMapper {

	public static IEventInfoDataMapper map(PanelObject panelObject) {
		EventInfoDataMapperImpl eventInfo = new EventInfoDataMapperImpl();
		List<Element> elements = panelObject.getElements();
		// Caption
		Label captionlabel = new Label(panelObject.getDescription());
		captionlabel.getElement().setClassName("page-header");
		eventInfo.setCaption(captionlabel);
		int i = 0;
		IsWidget[] w = new IsWidget[elements.size() + 1];
		for (Element e : elements) {

			if (e.getAttribute() != null) {
				String htmlForm = "";
				htmlForm += "<dl class=\"dl-horizontal\">";

				// Label l = new Label(e.getAttribute().getLabel() + " : "
				// + e.getAttribute().getValue());
				// l.getElement().setClassName("label");
				// l.getElement().getStyle().setFontSize(17, Unit.PX);
				// l.getElement().getStyle().setColor("black");

				htmlForm += "<dt>" + e.getAttribute().getLabel() + "</dt>";
				htmlForm += "<dd>" + e.getAttribute().getValue() + "</dd>";

				htmlForm += "</dl>";

				w[i++] = new HTML(htmlForm);
			}
			if (e.getChart() != null) {
				de.fhg.fokus.streetlife.mmecp.share.dto.Chart chart = e.getChart();

				switch (e.getChart().getType()) {
				case BARCHART:
					ColumnChart columnChart = new ColumnChart(DiagramData.fromDTOChart(chart));
					w[i++] = columnChart;
					break;
				case LINECHART:
					LineChart lineChart = new LineChart(DiagramData.fromDTOChart(chart));
					w[i++] = lineChart;
					// w[i++] = new
					// ColumnChart(DiagramData.fromDTOChartData(data));
					break;
				case PIECHART:
					PieChart pieChart = new PieChart(DiagramData.fromDTOChart(chart));
					w[i++] = pieChart;
					break;

				default:
					break;
				}

			}
			if (e.getIcon() != null) {

			}
		}
		Button b = new Button("guidance");
		b.getElement().setClassName("btn btn-default");
		b.getElement().setId("guidanceButton");
		w[i++] = b;
		b.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				GuidancePopUpPanel g = new GuidancePopUpPanel(true);
				g.getPanel().setPopupPosition(Window.getClientWidth()/2 - CSSDynamicData.guidancePopUpPanel_WIDTH/2,
						(Window.getClientHeight())/2 - CSSDynamicData.guidancePopUpPanel_HEIGHT/2);
				//g.getPanel().setPopupPosition(MapContainer.get().getPanel().getOffsetWidth()/2 - CSSDynamicData.guidancePopUpPanel_WIDTH/2, MapContainer.get().getPanel().getOffsetHeight()/2 -
				//		- CSSDynamicData.guidancePopUpPanel_HEIGHT/2);
				g.getPanel().show();
			}
		});
		eventInfo.setWidgets(w);
		return eventInfo;
	}
}
