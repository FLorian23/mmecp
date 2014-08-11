package de.fhg.fokus.streetlife.mmecp.client.siteelement.sidebar;

import static com.google.gwt.query.client.GQuery.$;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.SimplePanel;

public abstract class SlideBar extends SimplePanel {

	public static final int WIDTH_ROLL_IN = 40;
	public static final int WIDTH_ROLL_OUT = 300;
	private STATUS status = STATUS.CLOSE;

	public SlideBar(final String domID, final String wrapper) {
		getElement().setId(domID);
		addDomHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (getStatus() == STATUS.CLOSE){
					setStatus(STATUS.OPEN);
					$(wrapper).animate("width:" + SlideBar.WIDTH_ROLL_OUT, 500);	
				}else{
					setStatus(STATUS.CLOSE);
					$(wrapper).animate("width:" + SlideBar.WIDTH_ROLL_IN, 500);
				}
			
			}
		}, ClickEvent.getType());
		checkStatus();
	}
	public enum STATUS {
		OPEN, CLOSE
	}

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
		if (this.status == status) return;
		this.status = status;
		checkStatus();
	}
}
