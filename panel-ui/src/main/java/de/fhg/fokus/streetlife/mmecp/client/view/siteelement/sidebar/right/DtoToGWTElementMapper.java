package de.fhg.fokus.streetlife.mmecp.client.view.siteelement.sidebar.right;

import java.util.List;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;

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
				
				String htmlForm = "";
				htmlForm += "<dl class=\"dl-horizontal\">";
				
//				Label l = new Label(e.getAttribute().getLabel() + " : "
//						+ e.getAttribute().getValue());
//				l.getElement().setClassName("label");
//				l.getElement().getStyle().setFontSize(17, Unit.PX);
//				l.getElement().getStyle().setColor("black");
				
				htmlForm += "<dt>" + e.getAttribute().getLabel() + "</dt>";
				htmlForm += "<dd>" + e.getAttribute().getValue() + "</dd>";
				
				htmlForm += "</dl>";

				w[i++] = new HTML(htmlForm);
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
