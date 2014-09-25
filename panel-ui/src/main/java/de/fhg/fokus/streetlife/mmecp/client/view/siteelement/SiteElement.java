package de.fhg.fokus.streetlife.mmecp.client.view.siteelement;

import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.Widget;

public class SiteElement<E extends Widget> {

	private E panel = null;

	public SiteElement(E e, String cssID, String cssClass) {
		this.panel = e;
		this.panel.getElement().setId(cssID);

		if (cssClass != null)
			this.panel.getElement().setClassName(cssClass);

		setParentID();
	}

	public void setParentPanel(Panel p) {
		p.add(this.getPanel());
		setParentID();
	}

	private void setParentID() {
		if (this.panel.getElement().getParentElement() != null) {
			this.panel.getElement().getParentElement()
					.setId(getPanel().getElement().getId() + "Wrapper");
		}
	}

	public void addWidgetToPanel(Widget w, String cssID, String cssClass) {
		if (panel instanceof Panel) {
			((Panel) panel).add(w);
		} else
			return;

		setIDsOfWidget(w, cssID, cssClass);
	}

	public void addWidgetToTabPanel(Widget w, String desc, String cssID,
			String cssClass) {
		if (panel instanceof TabPanel) {
			((TabPanel) panel).add(w, desc);
		} else
			return;

		setIDsOfWidget(w, cssID, cssClass);
	}

	private void setIDsOfWidget(Widget w, String cssID, String cssClass) {
		if (cssClass != null)
			w.getElement().setClassName(cssClass);

		if (cssID == null)
			return;
		w.getElement().setId(cssID);

		if (w.getElement().getParentElement() != null) {
			w.getElement().getParentElement().setId(cssID + "Wrapper");
		}
	}

	public E getPanel() {
		return this.panel;
	}

	public String getID() {
		return this.panel.getElement().getId();
	}

	public String getWrapperID() {
		if (this.panel.getElement().getParentElement() != null) {
			if (this.panel.getElement().getParentElement().getId()
					.compareTo("") != 0)
				return this.panel.getElement().getParentElement().getId();
		}

		return this.panel.getElement().getId() + "Wrapper";
	}
}
