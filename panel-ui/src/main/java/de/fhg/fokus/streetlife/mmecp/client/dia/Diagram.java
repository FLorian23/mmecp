package de.fhg.fokus.streetlife.mmecp.client.dia;

import com.google.gwt.user.client.ui.IsWidget;


public abstract class Diagram implements IsWidget {

	private DiagramData data;
	public Diagram(DiagramData diagramData) {
		setData(diagramData);
	}
	public DiagramData getData() {
		return data;
	}
	public void setData(DiagramData data) {
		this.data = data;
	}
}
