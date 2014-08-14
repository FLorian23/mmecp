package de.fhg.fokus.streetlife.mmecp.client.siteelement.sidebar;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.SimplePanel;

public abstract class SlideBar extends SimplePanel implements ClickHandler {

	public static final int WIDTH_ROLL_IN = 40;
	public static final int WIDTH_ROLL_OUT = 300;
	private STATUS status = STATUS.CLOSE;
	private String wrapper = "";

	public SlideBar(final String domID, String wrapper) {
		this.setWrapper(wrapper);
		getElement().setId(domID);
		addDomHandler(this, ClickEvent.getType());
		checkStatus();
	}

	public enum STATUS {
		OPEN, CLOSE
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
			open();
			break;

		case CLOSE:
			close();
			break;

		default:
			break;
		}
	}

	public abstract void open();

	public abstract void close();

	public STATUS getStatus() {
		return status;
	}

	public void setStatus(STATUS status) {
		if (this.status == status)
			return;
		this.status = status;
		checkStatus();
	}

	public String getWrapper() {
		return wrapper;
	}

	public void setWrapper(String wrapper) {
		this.wrapper = wrapper;
	}
}
