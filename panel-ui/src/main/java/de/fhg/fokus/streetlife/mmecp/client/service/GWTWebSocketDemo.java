package de.fhg.fokus.streetlife.mmecp.client.service;

import com.google.gwt.user.client.Timer;
import com.sksamuel.gwt.websockets.Websocket;
import com.sksamuel.gwt.websockets.WebsocketListener;

public class GWTWebSocketDemo {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	Websocket socket;
	protected static native void _log(String text)
	/*-{
		console.log(text);
	}-*/;

	protected class Console {
		public void log(String text) {
			_log(text);
		}
	}

	protected Console console = new Console();

	public  GWTWebSocketDemo() {
		final Console console = new Console();

		console.log("adding websocket");

		// Establish a websocket communication channel to the atmosphere chat
		// service.
		// Websocket socket = new
		// Websocket("ws://localhost:8080/chat?X-Atmosphere-tracking-id=5ebed4c5-0b90-4166-88b2-9f273719ab75&X-Atmosphere-Framework=2.2.1-jquery&X-Atmosphere-Transport=websocket&Content-Type=application/json&X-atmo-protocol=true");
		final String url = "ws://localhost:8080/paneluisocket";
		socket = new Websocket(url);

		socket.addListener(new WebsocketListener() {

			public void onClose() {
				// do something on close
			}

			public void onMessage(String msg) {
				// a message is received
				console.log("onMessage(): " + msg);
			}

			public void onOpen() {
				// do something on open
				console.log("onOpen()");
			}
		});

		socket.open();
		console.log("websocket is open");

	}
}