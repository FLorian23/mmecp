package de.fhg.fokus.streetlife.mmecp;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import de.fhg.fokus.streetlife.mmecp.services.SubscriptionApi;

public class MmecpApplication extends Application {

	private Set<Object> singletons = new HashSet<Object>();

	public MmecpApplication() {
		singletons.add(new SubscriptionApi());
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}
}
