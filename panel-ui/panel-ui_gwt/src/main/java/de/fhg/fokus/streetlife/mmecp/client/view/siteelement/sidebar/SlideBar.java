package de.fhg.fokus.streetlife.mmecp.client.view.siteelement.sidebar;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.fhg.fokus.streetlife.mmecp.client.controller.LOG;
import de.fhg.fokus.streetlife.mmecp.client.controller.Subject;
import de.fhg.fokus.streetlife.mmecp.client.view.CSSDynamicData;
import de.fhg.fokus.streetlife.mmecp.client.view.siteelement.SiteElement;

public abstract class SlideBar extends SiteElement<HorizontalPanel> {

	private STATUS status = null;
	private SimplePanel picturePanel;
	boolean right;

	protected SiteElement<VerticalPanel> content;

	public SlideBar(final String domID, String wrapper,
			SiteElement<VerticalPanel> content, boolean right) {
		super(new HorizontalPanel(), domID, null);
		this.right = right;
		setPicturePanel(new SimplePanel());
		getPicturePanel().setWidth(
				CSSDynamicData.SlideBarPicturPaneleSize + "px");
		this.content = content;

		if (right) {
			super.addWidgetToPanel(getPicturePanel(), domID + "Picture", null);
			super.addWidgetToPanel(content.getPanel(), domID + "Content", null);
		} else {
			super.addWidgetToPanel(content.getPanel(), domID + "Content", null);
			super.addWidgetToPanel(getPicturePanel(), domID + "Picture", null);
		}
		setStatus(STATUS.CLOSE);
	}

	private void setPicturePanel(SimplePanel simplePanel) {
		this.picturePanel = simplePanel;
	}

	@Override
	public void addWidgetToPanel(Widget w, String cssID, String cssClass) {
		if (getPanel() instanceof Panel) {
			((Panel) getPanel()).add(w);
		} else
			return;

		setIDsOfWidget(w, cssID, cssClass);
	}

	public VerticalPanel getContentPanel() {
		return content.getPanel();
	}

	public SiteElement<VerticalPanel> getContentPanelSiteElement() {
		return content;
	}

	public enum STATUS {
		OPEN, CLOSE
	}

	protected abstract void open();

	protected abstract void close();

	public STATUS getStatus() {
		return status;
	}

	public void setStatus(STATUS status) {
		if (this.status == status)
			return;

		switch (status) {
		case OPEN:
			Subject.get().update();
			
			if (right)
				getPicturePanel().getElement().setClassName(
						"glyphicon glyphicon-chevron-right");
			else
				getPicturePanel().getElement().setClassName(
						"glyphicon glyphicon-chevron-left");
			open();
			break;

		case CLOSE:
			if (!right)
				getPicturePanel().getElement().setClassName(
						"glyphicon glyphicon-chevron-right");
			else
				getPicturePanel().getElement().setClassName(
						"glyphicon glyphicon-chevron-left");
			close();
			break;

		default:
			break;
		}

		this.status = status;
	}

	public SimplePanel getPicturePanel() {
		return picturePanel;
	}
}
