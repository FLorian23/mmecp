package de.fhg.fokus.streetlife.mmecp.client.model;

public class DAO {

	public enum CITY {
		BERLIN, ROVERETO, TAMPERE
	}

	public enum PARKING {
		FEE, FREE, CLOCK
	}

	public static PARKING getParkingEnumOfSubType(String subType){
		if (subType.compareTo("FREE") == 0) return PARKING.FREE;
		if (subType.compareTo("FEE") == 0) return PARKING.FEE;
		if (subType.compareTo("CLOCK") == 0) return PARKING.CLOCK;
		return null;
	}

	// Map
	public final static int MapContainer_DEFAULTZOOMSIZE = 12;
	public final static int MapContainer_SetNumZoomLevels = 19;
	public final static CITY DEFAULT_CITY = CITY.BERLIN;

	public static int getDefaultZoomLevelForCity(CITY c) {
		switch (DEFAULT_CITY) {
		case BERLIN:
			return BERLIN_ZOOMLEVEL;
		case ROVERETO:
			return ROVERETO_ZOOMLEVEL;
		case TAMPERE:
			return TAMPERE_ZOOMLEVEL;

		default:
			break;
		}
		return 0;
	}

	// Zentrum 13.385937438965227/52.513685522784805
	public static double BERLIN_GEO_lon = 13.385937438965227;
	public static double BERLIN_GEO_lat = 52.513685522784805;
	public static int BERLIN_ZOOMLEVEL = 12;

	// Zentrum 11.034870147704801/45.8898599771584
	public static double ROVERETO_GEO_lon = 11.034870147704801;
	public static double ROVERETO_GEO_lat = 45.8898599771584;
	public static int ROVERETO_ZOOMLEVEL = 15;

	// Zentrum 23.761501312254776/61.49565304058324
	public static double TAMPERE_GEO_lon = 23.761501312254776;
	public static double TAMPERE_GEO_lat = 61.49565304058324;
	public static int TAMPERE_ZOOMLEVEL = 14;

	public static String RIGHT_SIDEBAR_JSON_SCHEMA = "/schema.json";

	public static String RED = "D35400";
	public static String GREEN = "1E824C";
	public static String YELLOW = "F9BF3B";
	public static String ORANGE = "F89406";
}
