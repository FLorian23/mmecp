package de.fhg.fokus.streetlife.mmecp.client.view.siteelement.sidebar;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.SimplePanel;

import de.fhg.fokus.streetlife.mmecp.client.view.siteelement.SiteElement;

public abstract class SlideBar extends SiteElement<SimplePanel> implements
		ClickHandler {

	public static final int WIDTH_ROLL_IN = 40;
	public static final int WIDTH_ROLL_OUT = 300;
	private STATUS status = STATUS.HIDE;

	public SlideBar(final String domID, String wrapper) {
		super(new SimplePanel(), domID, null);
		getPanel().addDomHandler(this, ClickEvent.getType());
		checkStatus();
	}

	public enum STATUS {
		OPEN, CLOSE, HIDE
	}

	public void onClick(ClickEvent event) {
		if (getStatus() == STATUS.CLOSE) {
			opening();
		} else {
			closing();
		}
	}

	public abstract void opening();

	public abstract void closing();

	public void checkStatus() {
		switch (getStatus()) {
		case OPEN:
			opening();
			break;

		case CLOSE:
			closing();
			break;

		default:
			break;
		}
	}

	public STATUS getStatus() {
		return status;
	}

	public void setStatus(STATUS status) {
		if (this.status == status)
			return;
		this.status = status;
		checkStatus();
	}
}
