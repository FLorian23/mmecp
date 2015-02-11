package de.fhg.fokus.streetlife.mmecp.client.view.siteelement.tabpanel.math;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.fhg.fokus.streetlife.mmecp.client.Res;

public class MutableListBoxViewWithStringArray extends VerticalPanel {

	private ListBox[] listBoxes = null;
	private HorizontalPanel[] hps = null;
	// string is the amount of unused strings
	ArrayList<String> unusedStrings = new ArrayList<String>();

	/*
	 * hps and listboxes have a fixed relation if not visible, array entry is
	 * null hps listboxes 
	 * [] 	-> 	[] 
	 * []  ... 	[] 
	 * [] 		[] 
	 * [] 		[] 
	 * [] 		[] 
	 * [] 		[] 
	 * [] 		[] 
	 * [] 		[]
	 */

	// Number of visible ListBoxes
	private int numberListBoxes = 0;

	// For changing items in listboxes (consider: other listboxes have to be
	// updates, if a new listbox added)
	private String stringOfFocusedListBox = null;
	private int stringIndexOfFocusedListBox = -1;

	// For Event Handling
	ListBox focusedListBox = null;
	int counterForClickEventsOnFocusedListBox = 0;

	public MutableListBoxViewWithStringArray(ArrayList<String> strings) {
		this.unusedStrings = strings;
		listBoxes = new ListBox[strings.size()];
		hps = new HorizontalPanel[strings.size()];
		for (int i = 0; i < listBoxes.length; i++) {
			listBoxes[i] = null;
			hps[i] = null;
		}

		newField();
	}

	private void newField() {
		// No unused strings, no new listboxes!
		if (unusedStrings.size() == 0)
			return;

		// Get the next free ListBox in the ListBox Array
		int listBoxNumber = 0;
		for (int i = 0; i < listBoxes.length; i++) {
			if (listBoxes[i] == null) {
				listBoxNumber = i;
				break;
			}
		}

		// Instantiate a new List box and assign it to the right place in the
		// array
		listBoxes[listBoxNumber] = new ListBox();

		// ListBox CSS
		listBoxes[listBoxNumber].getElement().addClassName(
				"form-control listboxConditionKeyWord");
		listBoxes[listBoxNumber].getElement().setId(
				"listboxConditionKeyWord_" + listBoxNumber);

		// ListBox Handler
		listBoxes[listBoxNumber].addFocusHandler(new FocusHandler() {

			public void onFocus(FocusEvent event) {
				String lbID = event.getRelativeElement().getId();
				int id = extractID(lbID);
				updateListBox(listBoxes[id]);

				// Focus on listbox: after two clicks on the same listbox by one
				// focus
				// -> item selected
				focusedListBox = listBoxes[id];
				counterForClickEventsOnFocusedListBox = 0;

			}
		});

		listBoxes[listBoxNumber].addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				String lbID = event.getRelativeElement().getId();
				int listBoxNumberInteger = extractID(lbID);

				// Select onother listbox
				// If the last selected listbox is NOT the current selected
				// listbox?
				// Conclusion: no final change
				if (focusedListBox != listBoxes[listBoxNumberInteger]) {
					// Update the listbox
					// updateListBox(listBoxes[listBoxNumberInteger]);
					return;
				}

				// If the number of clicks is not 2, return, because no item was
				// selected
				if (++counterForClickEventsOnFocusedListBox != 2)
					return;

				// if the number of clicks is 2, then reset the counter
				counterForClickEventsOnFocusedListBox = 0;

				selectListBoxItem(listBoxNumberInteger);
			}
		});

		fillListBoxWithUnusedItems(listBoxes[listBoxNumber], true);

		HorizontalPanel hp = new HorizontalPanel();
		hp.getElement().setId("AttributeIdentifiersHP_" + listBoxNumber);
		hp.getElement().setClassName("AttributeIdentifiersHP");
		hps[listBoxNumber] = hp;

		// Delete Image
		ImageResource deleteImage = Res.INSTANCE.deleteImage();
		Image image = new Image(deleteImage.getSafeUri());
		image.getElement().setId("deleteButton_" + listBoxNumber);
		image.getElement().setClassName("deleteButton");
		image.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				String lbID = event.getRelativeElement().getId();
				String listBoxNumber = lbID.split("_")[1];
				int listBoxNumberInteger = Integer.valueOf(listBoxNumber);

				deleteField(listBoxNumberInteger);
			}
		});
		hp.add(image);

		hp.add(listBoxes[listBoxNumber]);
		TextBox tb = new TextBox();
		tb.getElement().addClassName("form-control textboxConditionKeyWord");
		hp.add(tb);

		add(hp);
		numberListBoxes++;
	}

	private void deleteField(int number) {
		if (numberListBoxes == 1) {
			return;
		}

		String value = null;
		if (listBoxes[number].getValue(0).compareTo("") == 0) {
			// ...
		} else {
			value = listBoxes[number].getValue(listBoxes[number]
					.getSelectedIndex());
			addItemToStringList(listBoxes[number].getSelectedIndex(), value);
		}

		remove(hps[number]);
		hps[number] = null;
		listBoxes[number] = null;
		numberListBoxes--;
	}

	private void fillListBoxWithUnusedItems(ListBox lb, int selectedIndex,
			String valueForSelectedIndex) {
		lb.clear();

		if (unusedStrings.size() == 0) {
			lb.addItem(valueForSelectedIndex);
			return;
		}
		for (int i = 0; i < unusedStrings.size(); i++) {

			// Have to be insert bevor addItem from the list (because i selected
			// the index later)
			if (i == selectedIndex) {
				lb.addItem(valueForSelectedIndex);
			}
			lb.addItem(unusedStrings.get(i));
		}

		if (unusedStrings.size() <= selectedIndex)
			lb.addItem(valueForSelectedIndex);

		if (selectedIndex == -1)
			selectedIndex = 0;
		else if (unusedStrings.size() <= selectedIndex)
			selectedIndex = lb.getItemCount() - 1;

		lb.setSelectedIndex(selectedIndex);
	}

	private void fillListBoxWithUnusedItems(ListBox lb, boolean newField) {
		lb.clear();

		if (unusedStrings.size() == 0) {
			return;
		}

		lb.addItem("");
		for (int i = 0; i < unusedStrings.size(); i++) {
			lb.addItem(unusedStrings.get(i));
		}
		lb.setSelectedIndex(0);
	}

	public void updateListBox(ListBox lb) {

		stringOfFocusedListBox = lb.getValue(lb.getSelectedIndex());
		// LogPanel.get().newMessage("string: " + stringOfFocusedListBox);
		stringIndexOfFocusedListBox = lb.getSelectedIndex();
		fillListBoxWithUnusedItems(lb, lb.getSelectedIndex(),
				stringOfFocusedListBox);
	}

	private void selectListBoxItem(int listBoxNumberInteger) {
		// Does the item "" is selected? (Have to be the last listbox)
		if (stringOfFocusedListBox.compareTo("") == 0) {

			// if no change
			if (listBoxes[listBoxNumberInteger].getSelectedIndex() == 0)
				return;

			// If change, but the last listbox -> only remove the item from
			// the string list (the empty string should not move in the
			// string list)
			else {
				listBoxes[listBoxNumberInteger]
						.removeItem(getIndexOfItemInListBox(
								listBoxes[listBoxNumberInteger], ""));
			}

			// not the last listbox -> add selected item to the string list
		} else
			addItemToStringList(stringIndexOfFocusedListBox,
					stringOfFocusedListBox);

		// all cases: remove the new selected item from the listbox (without the
		// case, that the selected item is "")
		unusedStrings.remove(listBoxes[listBoxNumberInteger]
				.getValue(listBoxes[listBoxNumberInteger].getSelectedIndex()));

		// if the selected item from the last listbox changed -> offer a new
		// listbox
		if (extractID(getWidget(getWidgetCount() - 1).getElement().getId()) == listBoxNumberInteger)
			newField();
	}

	private void addItemToStringList(int index, String item) {
		if (index > (unusedStrings.size() - 1))
			unusedStrings.add(item);
		else
			unusedStrings.add(index, item);
	}

	private int extractID(String id) {
		String nmb = id.split("_")[1];
		return Integer.valueOf(nmb);
	}

	private int getIndexOfItemInListBox(ListBox lb, String item) {
		for (int i = 0; i < lb.getItemCount(); i++) {
			if (lb.getItemText(i).compareTo(item) == 0)
				return i;
		}
		return -1;
	}
}
