package de.fhg.fokus.streetlife.mmecp.client.siteelement.statistic;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.TouchEndEvent;
import com.google.gwt.event.dom.client.TouchEndHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class MutableListBoxViewWithStringArray extends VerticalPanel implements
		ChangeHandler, FocusHandler, ClickHandler {

	private ListBox[] listBoxes = null;
	ArrayList<String> strings = new ArrayList<String>();
	private int numberListBoxes = 0;
	private String stringOfFocusedListBox = null;
	private int stringIndexOfFocusedListBox = -1;

	public MutableListBoxViewWithStringArray(ArrayList<String> strings) {
		this.strings = strings;
		listBoxes = new ListBox[strings.size()];
		for (int i = 0; i < listBoxes.length; i++) {
			listBoxes[i] = null;
		}

		newField();
	}

	private Widget newField() {
		if (strings.size() == 0)
			return null;

		int listBoxNumber = 0;
		for (int i = 0; i < listBoxes.length; i++) {
			if (listBoxes[i] == null) {
				listBoxNumber = i;
				break;
			}
		}

		listBoxes[listBoxNumber] = new ListBox();
		listBoxes[listBoxNumber].getElement().addClassName(
				"form-control listboxConditionKeyWord");
		listBoxes[listBoxNumber].addChangeHandler(this);
		listBoxes[listBoxNumber].addFocusHandler(this);
		listBoxes[listBoxNumber].addClickHandler(this);
		listBoxes[listBoxNumber].getElement().setId(
				"listboxConditionKeyWord_" + listBoxNumber);
		fillListBoxWithUnusedItems(listBoxes[listBoxNumber], -1);
		strings.remove(0);

		HorizontalPanel hp = new HorizontalPanel();
		hp.add(listBoxes[listBoxNumber]);
		TextBox tb = new TextBox();
		tb.getElement().addClassName("form-control textboxConditionKeyWord");
		hp.add(tb);

		add(hp);
		numberListBoxes++;
		return hp;
	}

	private void fillListBoxWithUnusedItems(ListBox lb, int selectedIndex) {
		lb.clear();

		if (strings.size() == 0)
			lb.addItem(stringOfFocusedListBox);
		for (int i = 0; i < strings.size(); i++) {
			if (i == selectedIndex) {
				lb.addItem(stringOfFocusedListBox);
			}
			lb.addItem(strings.get(i));
		}

		if (selectedIndex == -1)
			selectedIndex = 0;
		lb.setSelectedIndex(selectedIndex);
	}

	public void onChange(ChangeEvent event) {

	}

	public void onFocus(FocusEvent event) {
		String lbID = event.getRelativeElement().getId();
		String listBoxNumber = lbID.split("_")[1];
		int listBoxNumberInteger = Integer.valueOf(listBoxNumber);

		stringOfFocusedListBox = listBoxes[listBoxNumberInteger]
				.getValue(listBoxes[listBoxNumberInteger].getSelectedIndex());
		stringIndexOfFocusedListBox = listBoxes[listBoxNumberInteger]
				.getSelectedIndex();
		fillListBoxWithUnusedItems(listBoxes[listBoxNumberInteger],
				listBoxes[listBoxNumberInteger].getSelectedIndex());
	}

	int lastSelectedListBox = -1;
	public void onClick(ClickEvent event) {
		String lbID = event.getRelativeElement().getId();
		String listBoxNumber = lbID.split("_")[1];
		int listBoxNumberInteger = Integer.valueOf(listBoxNumber);
		
		//Select onother listbox
		if (lastSelectedListBox != listBoxNumberInteger){
			lastSelectedListBox = listBoxNumberInteger;
			return;
		}
		lastSelectedListBox = -1;

		strings.add(stringIndexOfFocusedListBox, stringOfFocusedListBox);
		strings.remove(listBoxes[listBoxNumberInteger]
				.getValue(listBoxes[listBoxNumberInteger].getSelectedIndex()));

		if (listBoxNumberInteger + 1 == numberListBoxes)
			newField();
	}
}
