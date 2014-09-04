package de.fhg.fokus.streetlife.mmecp.client.siteelement.statistic;

import java.util.ArrayList;
import java.util.Arrays;

import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ConfigurationPanelForRetrievalStatisticData extends SimplePanel {

	private static ConfigurationPanelForRetrievalStatisticData instance;
	Keys keys = new Keys();

	private ConfigurationPanelForRetrievalStatisticData() {
		buildPanel();

	}

	public static ConfigurationPanelForRetrievalStatisticData get() {
		if (ConfigurationPanelForRetrievalStatisticData.instance == null)
			ConfigurationPanelForRetrievalStatisticData.instance = new ConfigurationPanelForRetrievalStatisticData();

		return ConfigurationPanelForRetrievalStatisticData.instance;
	}

	void buildPanel() {
		// Three Columns: list_of_functions, list_of_attribute_identifiers,
		// list_of_condition_keywords
		HorizontalPanel hpanel = new HorizontalPanel();
		hpanel.getElement().setId(
				"configurationPanelForRetrievalStatisticDataPanel");
		add(hpanel);

		// list_of_functions (Checkboxen)
		VerticalPanel listOfFunctionPanel = getListOfFunctionPanel(keys.listOfFunction);
		listOfFunctionPanel.getElement().setId("listOfFunctionPanel");
		hpanel.add(listOfFunctionPanel);

		// Trennlinie
		SimplePanel sp1 = new SimplePanel();
		sp1.getElement().setClassName("dividingline");
		hpanel.add(sp1);

		// list_of_attribute_identifiers (ListBox mit Textbox)
		VerticalPanel listOfAttributeIdentifiersPanel = getListOfAttributeIdentifiersPanel();
		listOfAttributeIdentifiersPanel.getElement().setId(
				"listOfAttributeIdentifiersPanel");
		hpanel.add(listOfAttributeIdentifiersPanel);

		// Trennlinie
		SimplePanel sp2 = new SimplePanel();
		sp2.getElement().setClassName("dividingline");
		hpanel.add(sp2);

		// list_of_condition_keywords (Todo:)
		VerticalPanel listOfConditionKeywords = getListOfConditionKeywords();
		listOfConditionKeywords.getElement().setId("listOfConditionKeywords");
		hpanel.add(listOfConditionKeywords);

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
