package de.fhg.fokus.streetlife.mmecp.client.view.siteelement.tabpanel.map;

import java.util.ResourceBundle.Control;

import org.gwtopenmaps.openlayers.client.LonLat;
import org.gwtopenmaps.openlayers.client.Map;
import org.gwtopenmaps.openlayers.client.MapOptions;
import org.gwtopenmaps.openlayers.client.MapWidget;
import org.gwtopenmaps.openlayers.client.Pixel;
import org.gwtopenmaps.openlayers.client.Projection;
import org.gwtopenmaps.openlayers.client.control.ArgParser;
import org.gwtopenmaps.openlayers.client.control.Attribution;
import org.gwtopenmaps.openlayers.client.control.LayerSwitcher;
import org.gwtopenmaps.openlayers.client.control.Navigation;
import org.gwtopenmaps.openlayers.client.control.ScaleLine;
import org.gwtopenmaps.openlayers.client.event.MapClickListener;
import org.gwtopenmaps.openlayers.client.layer.GoogleV3;
import org.gwtopenmaps.openlayers.client.layer.GoogleV3MapType;
import org.gwtopenmaps.openlayers.client.layer.GoogleV3Options;
import org.gwtopenmaps.openlayers.client.layer.Layer;
import org.gwtopenmaps.openlayers.client.layer.OSM;

import com.google.gwt.core.client.Callback;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.geolocation.client.Geolocation;
import com.google.gwt.geolocation.client.Position;
import com.google.gwt.geolocation.client.PositionError;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.fhg.fokus.streetlife.mmecp.client.model.DAO;
import de.fhg.fokus.streetlife.mmecp.client.view.CSSDynamicData;
import de.fhg.fokus.streetlife.mmecp.client.view.siteelement.SiteElement;
import de.fhg.fokus.streetlife.mmecp.client.view.siteelement.sidebar.right.SlideBarRight;

public class MapContainer extends SiteElement<VerticalPanel> implements
		ClickHandler, ChangeHandler {

	private static MapContainer instance = null;

	private Position currentPosition = null;

	private MapContainer() {
		super(new VerticalPanel(), "mapcontainer", null);
		buildPanel();

		// TODP:
		getPanel().setSize("100%", "100%");
	}

	public static MapContainer get() {
		if (instance == null) {
			instance = new MapContainer();
		}
		return instance;
	}

	public static ClickHandler getHandler() {
		return (ClickHandler) get();
	}

	private Map map;

	private static final Projection DEFAULT_PROJECTION = new Projection(
			"EPSG:4326");
	private boolean isGoogleMaps = true;

	public void buildGoogleMaps() {
		GoogleV3Options gHybridOptions = new GoogleV3Options();
		gHybridOptions.setIsBaseLayer(true);
		gHybridOptions.setType(GoogleV3MapType.G_HYBRID_MAP);
		GoogleV3 gHybrid = new GoogleV3("Google Hybrid", gHybridOptions);

		GoogleV3Options gNormalOptions = new GoogleV3Options();
		gNormalOptions.setIsBaseLayer(true);
		gNormalOptions.setType(GoogleV3MapType.G_NORMAL_MAP);
		GoogleV3 gNormal = new GoogleV3("Google Normal", gNormalOptions);

		GoogleV3Options gSatelliteOptions = new GoogleV3Options();
		gSatelliteOptions.setIsBaseLayer(true);
		gSatelliteOptions.setType(GoogleV3MapType.G_SATELLITE_MAP);
		GoogleV3 gSatellite = new GoogleV3("Google Satellite",
				gSatelliteOptions);

		GoogleV3Options gTerrainOptions = new GoogleV3Options();
		gTerrainOptions.setIsBaseLayer(true);
		gTerrainOptions.setType(GoogleV3MapType.G_TERRAIN_MAP);
		GoogleV3 gTerrain = new GoogleV3("Google Terrain", gTerrainOptions);

		map.addLayer(gNormal);
		map.addLayer(gHybrid);
		map.addLayer(gSatellite);
		map.addLayer(gTerrain);
		isGoogleMaps = true;
	}

	public void buildOpenStreetMaps() {
		OSM osm_1 = OSM.Mapnik("Mapnik");
		OSM osm_2 = OSM.CycleMap("CycleMap");
		osm_1.setIsBaseLayer(true);
		osm_2.setIsBaseLayer(true);
		map.addLayer(osm_1);
		map.addLayer(osm_2);
		isGoogleMaps = false;
	}

	public void buildPanel() {

		// create some MapOptions
		MapOptions defaultMapOptions = new MapOptions();
		defaultMapOptions.removeDefaultControls();
		defaultMapOptions.setNumZoomLevels(CSSDynamicData.MapContainer_SetNumZoomLevels);

		// Create a MapWidget and add 2 OSM layers
//		MapWidget mapWidget = new MapWidget(Window.getClientWidth() + "px", Window.getClientHeight() + "px", defaultMapOptions);
		MapWidget mapWidget = new MapWidget("100%", "100%", defaultMapOptions);

		map = mapWidget.getMap();
		buildGoogleMaps();
		
		// Lets add some default controls to the map
		// map.addControl(new LayerSwitcher());
//		 map.addControl(new OverviewMap());
//		map.addControl(new ScaleLine());
		map.addControl(new Navigation());
		map.addControl(new ArgParser());
		map.addControl(new Attribution());
		
		// Add clickhandler for map
		map.addMapClickListener(new MapClickListener() {

			public void onClick(MapClickEvent mapClickEvent) {
				Pixel pixelFromLonLat = map.getPixelFromLonLat(mapClickEvent
						.getLonLat());
				GuidancePopUpPanel g = new GuidancePopUpPanel(true);
				
				int maxRightPixel = Window.getClientWidth() - SlideBarRight.get().getCurrentWidth();
				int maxBottomPixel = getPanel().getElement().getClientHeight();
				
				int xPixel = pixelFromLonLat.x() + getPanel().getElement().getAbsoluteLeft();
				int yPixel = pixelFromLonLat.y() + getPanel().getElement().getAbsoluteTop();

				if (xPixel + CSSDynamicData.guidancePopUpPanel_WIDTH > maxRightPixel){
					xPixel -= CSSDynamicData.guidancePopUpPanel_WIDTH ;
				}
				if (yPixel + CSSDynamicData.guidancePopUpPanel_HEIGHT  > maxBottomPixel){
					yPixel -= CSSDynamicData.guidancePopUpPanel_HEIGHT ;
				}
				g.getPanel().setPopupPosition(xPixel, yPixel);
				g.getPanel().show();
			}
		});
		// Center and zoom to a location
		LonLat lonLat = new LonLat(DAO.BERLIN_GEO_lon, DAO.BERLIN_GEO_lat);
		lonLat.transform(DEFAULT_PROJECTION.getProjectionCode(),
				map.getProjection());
		map.setCenter(lonLat, CSSDynamicData.MapContainer_DEFAULTZOOMSIZE);

		addWidgetToPanel(mapWidget, "mapWidget", "");

		mapWidget.getElement().getFirstChildElement().getStyle().setZIndex(0);
	}

	public static void switchLocation(double lon, double lat) {
		LonLat lonLat = new LonLat(lon, lat);
		lonLat.transform(DEFAULT_PROJECTION.getProjectionCode(),
				get().map.getProjection());
		get().map.setCenter(lonLat, CSSDynamicData.MapContainer_DEFAULTZOOMSIZE);
	}

	public static void switchLocation(DAO.CITY city) {
		double lon = 0;
		double lat = 0;

		switch (city) {
		case BERLIN:

			lon = DAO.BERLIN_GEO_lon;
			lat = DAO.BERLIN_GEO_lat;
			break;
		case ROVERETO:
			lon = DAO.ROVERETO_GEO_lon;
			lat = DAO.ROVERETO_GEO_lat;
			break;
		case TAMPERE:
			lon = DAO.TAMPERE_GEO_lon;
			lat = DAO.TAMPERE_GEO_lat;

			break;

		default:
			break;
		}
		switchLocation(lon, lat);
	}

	public void onClick(ClickEvent event) {

		String text = ((Button) event.getSource()).getText();
		if (text.equals("Switch Map")) {
			Layer[] layers = map.getLayers();
			for (int i = 0; i < layers.length; i++) {
				map.removeLayer(layers[i]);
			}

			if (isGoogleMaps)
				buildOpenStreetMaps();
			else
				buildGoogleMaps();
		} else if (text.equals("Locate Me")) {
			Geolocation geoLocation = Geolocation.getIfSupported();
			if (geoLocation == null) {
				Window.alert("No GeoLocation supprt available in this browser :-(");
			} else {
				Geolocation.PositionOptions geoOptions = new Geolocation.PositionOptions();
				geoOptions.setHighAccuracyEnabled(true);
				if (currentPosition == null)
					geoLocation.watchPosition(
							new Callback<Position, PositionError>() {

								public void onFailure(PositionError reason) {
									Window.alert("Something went wrong fetching the geolocation:\n"
											+ reason);
								}

								public void onSuccess(Position result) {
									currentPosition = result;
									switchLocation(result.getCoordinates()
											.getLongitude(), result
											.getCoordinates().getLatitude());

								}
							}, geoOptions);
				else {
					switchLocation(currentPosition.getCoordinates()
							.getLongitude(), currentPosition.getCoordinates()
							.getLatitude());
				}
			}

		}
	}

	public static ChangeHandler getChangeHandler() {
		return (ChangeHandler) get();
	}

	public void onChange(ChangeEvent event) {
		ListBox ListBox = (ListBox) event.getSource();
		String value = ListBox.getValue(ListBox.getSelectedIndex());
		switchLocation(DAO.CITY.valueOf(value));
	}
}

// How to set Markers!
// Markers layer = new Markers("Example Marker");
// map.addLayer(layer);
//
// Icon icon = new
// Icon("http://icongal.com/gallery/image/98728/map_pin_location_push_pin_gps_pushpin.png",
// new Size(32, 32));
// final Marker marker = new Marker(mapClickEvent.getLonLat(), icon);
// layer.addMarker(marker);
// map.addLayer(layer);
