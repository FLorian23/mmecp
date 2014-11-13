package de.fhg.fokus.streetlife.mmecp.client.controller;

import java.util.logging.Logger;

public class LOG {

	private final static Logger LOG = Logger.getLogger(LOG.class.getName());

	// natic JavaScript log
	protected static native void _log(String text)
	/*-{
		console.log(text);
	}-*/;

	public static void logToConsole(String msg) {
		_log(msg);
	}

	public static Logger getLogger() {
		return LOG;
	}
}
