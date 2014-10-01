package de.fhg.fokus.streetlife.mmecp.client.view.siteelement.statistic;

import java.util.ArrayList;
import java.util.Arrays;

import com.google.gwt.dom.client.Style.VerticalAlign;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.fhg.fokus.streetlife.mmecp.client.model.Keys;
import de.fhg.fokus.streetlife.mmecp.client.view.siteelement.SiteElement;

public class ConfigurationPanelForRetrievalStatisticData extends
		SiteElement<VerticalPanel> {

	private static ConfigurationPanelForRetrievalStatisticData instance;
	Keys keys = new Keys();

	private ConfigurationPanelForRetrievalStatisticData() {
		super(new VerticalPanel(),
				"configurationPanelForRetrievalStatisticDataPanel", "well");
		buildPanel();

	}

	public static ConfigurationPanelForRetrievalStatisticData get() {
		if (ConfigurationPanelForRetrievalStatisticData.instance == null)
			ConfigurationPanelForRetrievalStatisticData.instance = new ConfigurationPanelForRetrievalStatisticData();

		return ConfigurationPanelForRetrievalStatisticData.instance;
	}

	void buildPanel() {
		// list_of_functions (Checkboxen)
		VerticalPanel vp0 = new VerticalPanel();
		VerticalPanel listOfFunctionPanel = getListOfFunctionPanel(keys.listOfFunction);
		listOfFunctionPanel.getElement().setId("listOfFunctionPanel");
		Label label0 = new Label("Function");
		label0.getElement().setClassName(
				"configurationPanelForRetrievalStatisticDataPanelHeadLine");
		vp0.add(label0);
		vp0.add(listOfFunctionPanel);
		addWidgetToPanel(vp0, "listOfFunctionPanelWrapperID",
				"listOfFunctionPanelWrapper");

		// list_of_attribute_identifiers (ListBox mit Textbox)
		VerticalPanel vp1 = new VerticalPanel();
		VerticalPanel listOfAttributeIdentifiersPanel = getListOfAttributeIdentifiersPanel();
		listOfAttributeIdentifiersPanel.getElement().setId(
				"listOfAttributeIdentifiersPanel");
		Label label1 = new Label("Attribute identifier");
		label1.getElement().setClassName(
				"configurationPanelForRetrievalStatisticDataPanelHeadLine");
		vp1.add(label1);
		vp1.add(listOfAttributeIdentifiersPanel);
		addWidgetToPanel(vp1, "listOfAttributeIdentifiersPanelWrapperID",
				"listOfAttributeIdentifiersPanelWrapper");

		// list_of_condition_keywords (Todo:)
		VerticalPanel vp2 = new VerticalPanel();
		VerticalPanel listOfConditionKeywords = getListOfConditionKeywords();
		addWidgetToPanel(listOfConditionKeywords, "listOfConditionKeywords",
				null);
		Label label2 = new Label("Condition");
		label2.getElement().setClassName(
				"configurationPanelForRetrievalStatisticDataPanelHeadLine");
		vp2.add(label2);
		vp2.add(listOfConditionKeywords);
		addWidgetToPanel(vp2, "listOfConditionKeywordsWrapperID",
				"listOfConditionKeywordsWrapper");

		// Send Request Button
		Button sendButton = new Button("Get Data");
		sendButton.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				sendRequest();
			}
		});
		addWidgetToPanel(sendButton,
				"sendConfigurationForRetrievalStatisticDataButton",
				"btn btn-primary");
		sendButton.getElement().getParentElement().getStyle()
				.setVerticalAlign(VerticalAlign.BOTTOM);
	}

	private void sendRequest() {
		MathPanel.get().buildTable();
	}

	VerticalPanel getListOfAttributeIdentifiersPanel() {
		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.add(new Label("coming soon..."));
		return verticalPanel;
	}

	VerticalPanel getListOfFunctionPanel(String[] listOfFunction) {
		VerticalPanel verticalPanel = new VerticalPanel();

		CheckBox[] checkBox = new CheckBox[listOfFunction.length];
		for (int i = 0; i < listOfFunction.length; i++) {
			checkBox[i] = new CheckBox(listOfFunction[i]);
			checkBox[i].setValue(false);
			verticalPanel.add(checkBox[i]);
		}

		return verticalPanel;
	}

	VerticalPanel getListOfConditionKeywords() {
		return new MutableListBoxViewWithStringArray(new ArrayList<String>(
				Arrays.asList(keys.listOfConditionKeywords)));
	}

	private Keys getKeys() {
		return keys;
	}
}
