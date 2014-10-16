package de.fhg.fokus.streetlife.mmecp.resources;

import com.github.gwtbootstrap.client.ui.config.Configurator;
import com.github.gwtbootstrap.client.ui.resources.Resources;
import com.google.gwt.core.client.GWT;

public class MyConfigurator implements Configurator {
	public Resources getResources() {
		return GWT.create(MyResources.class);
	}

	public boolean hasResponsiveDesign() {
		return false;
	}
}