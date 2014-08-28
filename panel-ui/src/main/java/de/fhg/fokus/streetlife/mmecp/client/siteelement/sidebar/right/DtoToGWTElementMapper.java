package de.fhg.fokus.streetlife.mmecp.client.siteelement.sidebar.right;

import java.util.List;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;

import de.fhg.fokus.streetlife.mmecp.client.dia.ColumnChart;
import de.fhg.fokus.streetlife.mmecp.client.dia.DiagramData;
import de.fhg.fokus.streetlife.mmecp.client.dia.PieChart;
import de.fhg.fokus.streetlife.mmecp.share.dto.Datum;
import de.fhg.fokus.streetlife.mmecp.share.dto.Element;
import de.fhg.fokus.streetlife.mmecp.share.dto.EventInfo;

public class DtoToGWTElementMapper {

	public static IEventInfoView map(EventInfo e) {
		EventInfoViewImpl eventInfo = new EventInfoViewImpl();

		IsWidget[] w = new IsWidget[e.getElements().size()];

		// Metadata

		eventInfo.setDateTime(new Label(e.getDatetime().toString()));
		eventInfo.setLocation(new Label(e.getLocation().getLon() + "/"
				+ e.getLocation().getLat()));
		eventInfo.setWidgets(w);

		int widgetCounter = 0;
		for (int i = 0; i < e.getElements().size(); i++) {
			Element element = e.getElements().get(i);
			List<Datum> data = null;
			
			switch (element.getItemtype()) {
			case BARCHART:
				data = element.getChart().getData();
				w[widgetCounter++] = new ColumnChart(
						DiagramData.fromDTOChartData(data));
				break;
			case CAPTION:
				eventInfo.setCaption(new Label(element.getValue()));
				break;
			case DATE:
				w[widgetCounter++] = new Label(element.getValue());
				break;
			case DESCRIPTION:
				eventInfo.setDescription(new Label(element.getValue()));
				break;
			case LABEL:
				w[widgetCounter++] = new Label(element.getLabel() + " "
						+ element.getValue());
				break;
			case PIECHART:
				data = element.getChart().getData();
				w[widgetCounter++] = new PieChart(
						DiagramData.fromDTOChartData(data));
				break;

			default:
				break;
			}

		}

		return eventInfo;
	}
}
