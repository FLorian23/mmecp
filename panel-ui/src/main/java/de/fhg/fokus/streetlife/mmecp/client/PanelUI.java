package de.fhg.fokus.streetlife.mmecp.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

import de.fhg.fokus.streetlife.mmecp.client.siteelement.ContentController;
import de.fhg.fokus.streetlife.mmecp.client.siteelement.Header;

public class PanelUI implements EntryPoint {

	public void onModuleLoad() {
		buildPanel();
	}

	public void buildPanel() {
		RootPanel.get().add(new Header());
		RootPanel.get().add(ContentController.getInstance().getVerticalPanel());
		
		
		init();
	}

	private void init() {
		ContentController.getInstance().show(ContentController.Kind.MAP);
	}
}