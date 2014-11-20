package de.fhg.fokus.streetlife.mmecp.client.view.siteelement.sidebar.right;

import java.util.List;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;

import de.fhg.fokus.streetlife.mmecp.client.model.EventInfoDataMapperImpl;
import de.fhg.fokus.streetlife.mmecp.client.model.IEventInfoDataMapper;
import de.fhg.fokus.streetlife.mmecp.client.view.dia.ColumnChart;
import de.fhg.fokus.streetlife.mmecp.client.view.dia.DiagramData;
import de.fhg.fokus.streetlife.mmecp.client.view.dia.LineChart;
import de.fhg.fokus.streetlife.mmecp.client.view.dia.PieChart;
import de.fhg.fokus.streetlife.mmecp.share.dto.Datum;
import de.fhg.fokus.streetlife.mmecp.share.dto.Element;
import de.fhg.fokus.streetlife.mmecp.share.dto.PanelObject;

public class DtoToGWTElementMapper {

	// public static IEventInfoDataMapper map(Notification e) {
	// EventInfoDataMapperImpl eventInfo = new EventInfoDataMapperImpl();

	// IsWidget[] w = new IsWidget[e.getElements().size()];
	//
	// Metadata
	//
	// eventInfo.setDateTime(new Label(e.getDatetime().toString()));
	// eventInfo.setLocation(new Label(e.getLocation().getLon() + "/"
	// + e.getLocation().getLat()));
	// eventInfo.setWidgets(w);
	//
	// int widgetCounter = 0;
	// for (int i = 0; i < e.getElements().size(); i++) {
	// Element element = e.getElements().get(i);
	// List<Datum> data = null;
	//
	// switch (element.getItemtype()) {
	// case BARCHART:
	// data = element.getChart().getData();
	// w[widgetCounter++] = new ColumnChart(
	// DiagramData.fromDTOChartData(data));
	// break;
	// case CAPTION:
	// eventInfo.setCaption(new Label(element.getValue()));
	// break;
	// case DATE:
	// w[widgetCounter++] = new Label(element.getValue());
	// break;
	// case DESCRIPTION:
	// eventInfo.setDescription(new Label(element.getValue()));
	// break;
	// case LABEL:
	// w[widgetCounter++] = new Label(element.getLabel() + " "
	// + element.getValue());
	// break;
	// case PIECHART:
	// data = element.getChart().getData();
	// w[widgetCounter++] = new PieChart(
	// DiagramData.fromDTOChartData(data));
	// break;
	//
	// default:
	// break;
	// }
	//
	// }
	//
	// return eventInfo;
	// }

	public static IEventInfoDataMapper map(PanelObject panelObject) {
		EventInfoDataMapperImpl eventInfo = new EventInfoDataMapperImpl();

		List<Element> elements = panelObject.getElements();

		// Caption
		Label captionlabel = new Label(panelObject.getDescription());
		captionlabel.getElement().setClassName("page-header");
		eventInfo.setCaption(captionlabel);

		int i = 0;
		IsWidget[] w = new IsWidget[elements.size()];

		for (Element e : elements) {
			if (e.getAttribute() != null) {
				Label l = new Label(e.getAttribute().getLabel() + " : "
						+ e.getAttribute().getValue());
				l.getElement().setClassName("label");
				l.getElement().getStyle().setFontSize(17, Unit.PX);
				l.getElement().getStyle().setColor("black");
				w[i++] = l;
			}
			if (e.getChart() != null) {

				List<Datum> data = e.getChart().getData();

				switch (e.getChart().getType()) {
				case BARCHART:
					w[i++] = new ColumnChart(DiagramData.fromDTOChartData(data));
					break;
				case LINECHART:
					w[i++] = new LineChart(DiagramData.fromDTOChartData(data));
					// w[i++] = new
					// ColumnChart(DiagramData.fromDTOChartData(data));
					break;
				case PIECHART:
					w[i++] = new PieChart(DiagramData.fromDTOChartData(data));
					break;

				default:
					break;
				}

			}
			if (e.getIcon() != null) {

			}
		}

		eventInfo.setWidgets(w);
		return eventInfo;
	}
}
