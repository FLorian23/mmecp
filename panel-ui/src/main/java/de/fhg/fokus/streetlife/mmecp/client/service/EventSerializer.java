package de.fhg.fokus.streetlife.mmecp.client.service;

import org.atmosphere.gwt.client.AtmosphereGWTSerializer;
import org.atmosphere.gwt.client.SerialTypes;

@SerialTypes(value = {Event.class})
public abstract class EventSerializer extends AtmosphereGWTSerializer {
}