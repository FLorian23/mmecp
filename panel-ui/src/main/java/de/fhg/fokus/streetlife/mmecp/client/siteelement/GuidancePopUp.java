package de.fhg.fokus.streetlife.mmecp.client.siteelement;

import org.gwtopenmaps.openlayers.client.LonLat;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.fhg.fokus.streetlife.mmecp.client.siteelement.sidebar.right.SlideBarRight;
import static com.google.gwt.query.client.GQuery.$;

public class GuidancePopUp extends SimplePanel implements ClickHandler {

	LonLat position;
	private static GuidancePopUp instance = null;
	VerticalPanel content = new VerticalPanel();
	private static Element wrapperElement = null;
	LayoutPanel lp = null;
	public final static int guidancePopUp_WIDTH = 250;
	public final static int guidancePopUp_HEIGHT = 100;
	private int currentX = 0;
	private int currentY = 0;

	public static GuidancePopUp get() {
		if (instance == null)
			instance = new GuidancePopUp();
		return instance;
	}

	public static void setWrapperElement(Element e) {
		wrapperElement = e;

		wrapperElement.getStyle().setPosition(
				com.google.gwt.dom.client.Style.Position.ABSOLUTE);
		wrapperElement.getStyle().setZIndex(999);
		wrapperElement.getStyle().setBackgroundColor("WHITE");
		wrapperElement.getStyle().setHeight(guidancePopUp_HEIGHT, Unit.PX);
		wrapperElement.getStyle().setWidth(guidancePopUp_WIDTH, Unit.PX);
		wrapperElement.setId("wrapperguidancepopup");
	}

	private GuidancePopUp() {
		this.setVisible(false);
		add(content);
		this.getElement().getStyle()
				.setPosition(com.google.gwt.dom.client.Style.Position.ABSOLUTE);
		this.getElement().getStyle().setZIndex(999);
		this.getElement().getStyle().setLeft(0, Unit.PX);
		this.getElement().getStyle().setTop(0, Unit.PX);

		this.setSize(guidancePopUp_WIDTH + "px", guidancePopUp_HEIGHT + "px");

		Button button = new Button("OK");
		button.addClickHandler(this);

		content.add(new HTML("some guidance options..."));
		content.add(button);
	}

	public void onClick(ClickEvent event) {
		hide();
	}

	public void hide() {
		this.setVisible(false);
		lp.remove(this);
	}

	public void setTo(int x, int y) {
		if (lp.getElement().getClientWidth() < x + guidancePopUp_WIDTH
				+ SlideBarRight.get().getElement().getClientWidth())
			x = x - guidancePopUp_WIDTH;
		if (lp.getElement().getClientHeight() < y + guidancePopUp_HEIGHT + 100)
			y = y - guidancePopUp_HEIGHT;
		if (!this.isVisible()) {
			this.setVisible(true);
			lp.add(this);
			Element widgetContainerElement = lp
					.getWidgetContainerElement(GuidancePopUp.get());
			widgetContainerElement.getStyle().setLeft(x, Unit.PX);
			widgetContainerElement.getStyle().setTop(y, Unit.PX);
			lp.setWidgetLeftWidth(this, x, Unit.PX, guidancePopUp_WIDTH,
					Unit.PX);
			lp.setWidgetTopHeight(this, y, Unit.PX, guidancePopUp_HEIGHT,
					Unit.PX);
			setWrapperElement(widgetContainerElement);
		}

		// if already visible...
		$("#wrapperguidancepopup").css("left", x + "px");
		$("#wrapperguidancepopup").css("top", y + "px");
		
		setCurrentX(x);
		setCurrentY(y);
	}

	public void setLayoutPanel(LayoutPanel lp) {
		this.lp = lp;
	}
	
	public LayoutPanel getLayoutPanel() {
		return this.lp;
	}

	public int getCurrentX() {
		return currentX;
	}

	public void setCurrentX(int currentX) {
		this.currentX = currentX;
	}

	public int getCurrentY() {
		return currentY;
	}

	public void setCurrentY(int currentY) {
		this.currentY = currentY;
	}
}
