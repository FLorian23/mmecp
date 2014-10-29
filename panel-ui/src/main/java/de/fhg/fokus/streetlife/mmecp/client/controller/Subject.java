package de.fhg.fokus.streetlife.mmecp.client.controller;

import java.util.ArrayList;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;

public class Subject {

	private ArrayList<Observer> myObserver = new ArrayList<Observer>();
	public static Subject instance = null;
	
	private Subject() {
		Window.addResizeHandler(new ResizeHandler() {

			public void onResize(ResizeEvent event) {
				update();
			}
		});
	}
	
	public static Subject get(){
		if (instance == null) instance = new Subject();
		return instance;
	}

	private void update() {
		for (Observer obj : myObserver) {
			obj.update();
		}
	}

	public void addToSubjectList(Observer obj) {
		myObserver.add(obj);
	}

}