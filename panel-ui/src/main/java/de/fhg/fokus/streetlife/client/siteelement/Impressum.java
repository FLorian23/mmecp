package de.fhg.fokus.streetlife.client.siteelement;

import com.google.gwt.user.client.ui.HTMLPanel;

import de.fhg.fokus.streetlife.client.Res;

public class Impressum extends SiteElement {

	private static Impressum instance = null;

	private Impressum() {
		buildPanel();
	}

	private void buildPanel() {
		HTMLPanel dynContent = new HTMLPanel(Res.INSTANCE
				.streetlifePanelImprint().getText());
		dynContent.getElement().setId("imprintHTMLPanel");
		
		add(dynContent);
	}

	public static Impressum getInstance() {
		if (instance == null) {
			instance = new Impressum();
		}
		return instance;
	}
}
