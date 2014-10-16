package de.fhg.fokus.streetlife.mmecp.client.view.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class Example extends Composite {

	@UiTemplate("Example.ui.xml")
	interface MyUiBinder extends UiBinder<Widget, Example> {
	}

	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);
	
	public Example() {
		initWidget(uiBinder.createAndBindUi(this));
	}
}