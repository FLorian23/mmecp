package de.fhg.fokus.streetlife.mmecp.client.siteelement;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class MathPanel extends VerticalPanel {

	private static MathPanel instance = null;

	private MathPanel() {
		add(new Label("some math..."));
	}

	public static MathPanel get() {
		if (instance == null)
			instance = new MathPanel();
		return instance;
	}

}
