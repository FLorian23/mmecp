package de.fhg.fokus.streetlife.client.siteelement.sidebar.right;

import java.util.List;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;

import de.fhg.fokus.streetlife.client.dia.DiagramData;
import de.fhg.fokus.streetlife.client.dia.PieDiagram;
import de.fhg.fokus.streetlife.share.dto.Datum;
import de.fhg.fokus.streetlife.share.dto.Element;
import de.fhg.fokus.streetlife.share.dto.EventInfo;

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

			switch (element.getItemtype()) {
			case BARCHART:
				w[widgetCounter++] = new Label("BarChart");// TODO: new
															// BarChart();
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
				List<Datum> data = element.getChart().getData();
				w[widgetCounter++] = new PieDiagram(
						DiagramData.fromDTOChartData(data));
				break;

			default:
				break;
			}

		}

		return eventInfo;
	}
}
