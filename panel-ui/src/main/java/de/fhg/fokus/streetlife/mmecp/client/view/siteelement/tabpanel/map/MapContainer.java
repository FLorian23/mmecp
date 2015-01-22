package de.fhg.fokus.streetlife.mmecp.client.view.siteelement.tabpanel.map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import de.fhg.fokus.streetlife.mmecp.client.controller.LOG;
import de.fhg.fokus.streetlife.mmecp.client.controller.SocketController;
import de.fhg.fokus.streetlife.mmecp.client.model.Data;
import de.fhg.fokus.streetlife.mmecp.client.model.IEventInfoDataMapper;
import de.fhg.fokus.streetlife.mmecp.client.service.JSONObjectService;
import de.fhg.fokus.streetlife.mmecp.client.service.JSONObjectServiceAsync;
import de.fhg.fokus.streetlife.mmecp.client.view.siteelement.sidebar.SlideBar;
import de.fhg.fokus.streetlife.mmecp.client.view.siteelement.sidebar.right.DtoToGWTElementMapper;
import de.fhg.fokus.streetlife.mmecp.share.dto.Maparea;
import de.fhg.fokus.streetlife.mmecp.share.dto.PanelObject;

import org.apache.commons.lang.ArrayUtils;
import org.gwtopenmaps.openlayers.client.LonLat;
import org.gwtopenmaps.openlayers.client.Map;
import org.gwtopenmaps.openlayers.client.MapOptions;
import org.gwtopenmaps.openlayers.client.MapWidget;
import org.gwtopenmaps.openlayers.client.Pixel;
import org.gwtopenmaps.openlayers.client.Projection;
import org.gwtopenmaps.openlayers.client.control.SelectFeatureOptions;
import org.gwtopenmaps.openlayers.client.Style;
import org.gwtopenmaps.openlayers.client.control.ArgParser;
import org.gwtopenmaps.openlayers.client.control.Attribution;
import org.gwtopenmaps.openlayers.client.control.Navigation;
import org.gwtopenmaps.openlayers.client.control.SelectFeature;
import org.gwtopenmaps.openlayers.client.event.MapClickListener;
import org.gwtopenmaps.openlayers.client.event.VectorFeatureAddedListener;
import org.gwtopenmaps.openlayers.client.event.VectorFeatureSelectedListener;
import org.gwtopenmaps.openlayers.client.feature.VectorFeature;
import org.gwtopenmaps.openlayers.client.geometry.LinearRing;
import org.gwtopenmaps.openlayers.client.geometry.Point;
import org.gwtopenmaps.openlayers.client.geometry.Polygon;
import org.gwtopenmaps.openlayers.client.layer.GoogleV3;
import org.gwtopenmaps.openlayers.client.layer.GoogleV3MapType;
import org.gwtopenmaps.openlayers.client.layer.GoogleV3Options;
import org.gwtopenmaps.openlayers.client.layer.Layer;
import org.gwtopenmaps.openlayers.client.layer.OSM;
import org.gwtopenmaps.openlayers.client.layer.Vector;

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
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.fhg.fokus.streetlife.mmecp.client.controller.Observer;
import de.fhg.fokus.streetlife.mmecp.client.controller.Subject;
import de.fhg.fokus.streetlife.mmecp.client.model.DAO;
import de.fhg.fokus.streetlife.mmecp.client.test.ExampleData;
import de.fhg.fokus.streetlife.mmecp.client.view.CSSDynamicData;
import de.fhg.fokus.streetlife.mmecp.client.view.siteelement.SiteElement;
import de.fhg.fokus.streetlife.mmecp.client.view.siteelement.sidebar.right.SlideBarRight;

import org.mortbay.log.Log;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class MapContainer extends SiteElement<VerticalPanel> implements
		ClickHandler, ChangeHandler, Observer {

	private static MapContainer instance = null;
	final Vector vectorLayer = new Vector("Vector layer");
	private Position currentPosition = null;
	private MapWidget mapWidget;
	private Map map;
	private static final Projection DEFAULT_PROJECTION = new Projection("EPSG:4326");
	private boolean isGoogleMaps = true;
	private HashMap<String, PanelObject> drawnObjects = new HashMap();
	private HashMap<String, PanelObject> myfeatures = new HashMap<String, PanelObject>();
	private VectorFeature selectedFeature = null;

	private MapContainer() {
		super(new VerticalPanel(), "mapcontainer", null);
		buildPanel();
		getPanel().setSize("100%", "100%");
		Subject.get().addToSubjectList(this);
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

	public PanelObject getMapObjectByID(String objectType, String id){
		Collection<PanelObject> values = drawnObjects.values();
		for (PanelObject a : values){
			if (a.getObjectID() == id && objectType.compareTo(a.getObjectType()) == 0){
				return a;
			}
				
		}
		return null;
	}
	
	public void buildGoogleMaps() {
		removeAllLayers();
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
		// map.addLayer(gHybrid);
		map.addLayer(gSatellite);
		// map.addLayer(gTerrain);

		// Add polygon Layer
		map.addLayer(vectorLayer);

		isGoogleMaps = true;
	}

	public void buildOpenStreetMaps() {
		removeAllLayers();
		OSM osm_1 = OSM.Mapnik("Mapnik");
		OSM osm_2 = OSM.CycleMap("CycleMap");
		osm_1.setIsBaseLayer(true);
		osm_2.setIsBaseLayer(true);
		map.addLayer(osm_1);
		map.addLayer(osm_2);

		// Add polygon Layer
		map.addLayer(vectorLayer);

		isGoogleMaps = false;
	}

	private void removeAllLayers() {
		Layer[] layers = map.getLayers();
		for (int i = 0; i < layers.length; i++) {
			map.removeLayer(layers[i]);
		}
	}


	SelectFeature clickSelectFeature = null;
	private void createSelectFeatureStuff(){

		clickSelectFeature = new SelectFeature(vectorLayer); //no options needed because no hover is needed
		clickSelectFeature.setClickOut(true);
		clickSelectFeature.setToggle(true);
		clickSelectFeature.setMultiple(false); //do not select multiple when clicked normally
		clickSelectFeature.setToggleKey("ctrlKey"); //Do toggle the selection when user holds CTRL key
		clickSelectFeature.setMultipleKey("shiftKey"); //Do select multiple features when user holds SHIFT key
		map.addControl(clickSelectFeature);
		clickSelectFeature.activate();

		vectorLayer.addVectorFeatureAddedListener(new VectorFeatureAddedListener() {
			@Override
			public void onFeatureAdded(FeatureAddedEvent eventObject) {
				LOG.logToConsole("new Feature added");
			}
		});
		vectorLayer.addVectorFeatureSelectedListener(new VectorFeatureSelectedListener() {
			@Override
			public void onFeatureSelected(FeatureSelectedEvent eventObject) {
				LOG.logToConsole("feature selected!!");
				PanelObject po =  myfeatures.get(eventObject.getVectorFeature().getFeatureId());
				//selectedFeature = eventObject.getVectorFeature();
				LOG.logToConsole("po: " + po.getObjectID());
				openSiteBar(po);
				//TODO:
				//selectedFeature.setStyle(selectedFeatureStyle);
			}
		});
	}


	public void buildPanel() {

		// create some MapOptions
		MapOptions defaultMapOptions = new MapOptions();
		defaultMapOptions.removeDefaultControls();
		defaultMapOptions.setNumZoomLevels(DAO.MapContainer_SetNumZoomLevels);

		// Create a MapWidget and add 2 OSM layers
		// MapWidget mapWidget = new MapWidget(Window.getClientWidth() + "px",
		// Window.getClientHeight() + "px", defaultMapOptions);
		mapWidget = new MapWidget("100%", "100%", defaultMapOptions);

		map = mapWidget.getMap();
		// buildGoogleMaps();
		buildOpenStreetMaps();

		// Lets add some default controls to the map
		// map.addControl(new LayerSwitcher());
		// map.addControl(new OverviewMap());
		// map.addControl(new ScaleLine());
		map.addControl(new Navigation());
		map.addControl(new ArgParser());
		map.addControl(new Attribution());

		createSelectFeatureStuff();

		// Polygon-Control
		// ****************************************
		//map.addLayer(vectorLayer);
		// ****************************************

		// Add clickhandler for map
		map.addMapClickListener(new MapClickListener() {

			public void onClick(MapClickEvent mapClickEvent) {

				/*if (mapObjectclicked(mapClickEvent)){
					PanelObject panelObject = null;
					openSiteBar(panelObject);
				}*/
/*
				Pixel pixelFromLonLat = map.getPixelFromLonLat(mapClickEvent
						.getLonLat());
				LonLat l = new LonLat(mapClickEvent.getLonLat().lon(),
						mapClickEvent.getLonLat().lat());
				l.transform(map.getProjection(),
						DEFAULT_PROJECTION.getProjectionCode());
				GuidancePopUpPanel g = new GuidancePopUpPanel(true,
						mapClickEvent.getLonLat(), l);

				int maxRightPixel = Window.getClientWidth()
						- SlideBarRight.get().getCurrentWidth();
				int maxBottomPixel = getPanel().getElement().getClientHeight();

				int xPixel = pixelFromLonLat.x()
						+ getPanel().getElement().getAbsoluteLeft();
				int yPixel = pixelFromLonLat.y()
						+ getPanel().getElement().getAbsoluteTop();

				if (xPixel + CSSDynamicData.guidancePopUpPanel_WIDTH > maxRightPixel) {
					xPixel -= CSSDynamicData.guidancePopUpPanel_WIDTH;
				}
				if (yPixel + CSSDynamicData.guidancePopUpPanel_HEIGHT > maxBottomPixel) {
					yPixel -= CSSDynamicData.guidancePopUpPanel_HEIGHT;
				}
				g.getPanel().setPopupPosition(xPixel, yPixel);
				g.getPanel().show();*/
			}
		});
		// Center and zoom to a location
		LonLat lonLat = new LonLat(DAO.BERLIN_GEO_lon, DAO.BERLIN_GEO_lat);
		lonLat.transform(DEFAULT_PROJECTION.getProjectionCode(),
				map.getProjection());
		map.setCenter(lonLat, DAO.getDefaultZoomLevelForCity(DAO.DEFAULT_CITY));

		addWidgetToPanel(mapWidget, "mapWidget", "");
		mapWidget.setHeight(RootPanel.get().getOffsetHeight() + "px");

	}

	private boolean mapObjectclicked(MapClickListener.MapClickEvent mapClickEvent){
		ArrayList<PanelObject> myList = Data.myPanelObjects;


		AsyncCallback<PanelObject> callback = new AsyncCallback<PanelObject>() {
			@Override
			public void onFailure(Throwable caught) {
				LOG.logToConsole("FAIL CALLBACK FOR getPanelObjectByCoordinate\n" + caught);
			}

			@Override
			public void onSuccess(PanelObject result) {
				LOG.logToConsole("FOUND: " + result.getObjectID());
			}
		};

		JSONObjectServiceAsync eventInfoService = GWT.create(JSONObjectService.class);
		eventInfoService.getPanelObjectByCoordinate(myList, callback);

		return false;
	}

	private void openSiteBar(PanelObject panelObject){
		VerticalPanel content = SlideBarRight.get().getContent();
		content.clear();
		// TODO: mapObject Inhalt nutzen für das seitliche Panel und zoom dort
		// hin!
		IEventInfoDataMapper eventInfo = DtoToGWTElementMapper
				.map(panelObject.getMapObject());
		eventInfo.fillContent(content);

		SlideBarRight.get().setStatus(SlideBar.STATUS.OPEN);
	}

	protected static native double[] convert(double[] code, String src, String dest)
	/*-{
        var a = $wnd.proj4(src, dest, code);
		return a;
    }-*/;

	public VectorFeature drawPolygon(LonLat[] lonLat, String color, double alpha, double borderWidth, String borderStyle, boolean isGPSPosition, String id) {
		Style s = new Style();
		s.setStrokeColor(color);
		s.setStrokeWidth(borderWidth);
		if (borderStyle.equals("solid"))
			s.setStrokeDashstyle("1,0");
		else if (borderStyle.equals("dashed"))
			s.setStrokeDashstyle("10,10");
		s.setFillColor(color);
		s.setFillOpacity(alpha);

		Point[] pointList = new Point[lonLat.length];
		for (int i = 0; i < lonLat.length; i++) {
			double[] b = null;
			if (isGPSPosition) {
				//lonLat[i].transform("EPSG:4326",
				//		map.getProjection());
				double[] a = {lonLat[i].lon(), lonLat[i].lat()};
				b = convert(a, "+proj=utm +zone=32 +ellps=WGS84 +units=m +no_defs", map.getProjection());//"EPSG:4326");
				pointList[i] = new Point(b[0], b[1]);
			} else {
				pointList[i] = new Point(lonLat[i].lon(), lonLat[i].lat());
			}
		}


		LinearRing linearRing = new LinearRing(pointList);
		VectorFeature polygonFeature = new VectorFeature(new Polygon(new LinearRing[] { linearRing }));
		polygonFeature.setFeatureId(id);
		polygonFeature.setStyle(s);
		vectorLayer.addFeature(polygonFeature);
		LOG.getLogger().info("drawPolygon_end " + vectorLayer.getFeatures().length);
		return polygonFeature;
	}

	public static void switchLocation(double lon, double lat) {
		switchLocation(lon, lat, DAO.MapContainer_DEFAULTZOOMSIZE);
	}
	
	public static void switchLocation(double lon, double lat, int zoom) {
		LonLat lonLat = new LonLat(lon, lat);
		lonLat.transform(DEFAULT_PROJECTION.getProjectionCode(),
				get().map.getProjection());
		get().map.setCenter(lonLat, zoom);
	}


	int featureCounter = 0;
	//TODO: remove feature -> hashmap
	public void drawObject(PanelObject object) {
		String key = object.getObjectType() + ":" + object.getObjectID();
		if (drawnObjects.containsKey(key)) {
			// redraw object
			LOG.getLogger().info("Redraw object " + key);
			vectorLayer.removeFeature(vectorLayer.getFeatureById(key));
		} else {
			// add and draw object
			LOG.getLogger().info("Add and draw object " + key);
			drawnObjects.put(key, object);
		}
		Maparea maparea = object.getMaparea();
		List<LonLat> coordinates = maparea.getArea().getCoordinatesLonLat().get(0);

		LonLat[] lonLats = coordinates.toArray(new LonLat[coordinates.size()]);

		VectorFeature vf = null;
		if (maparea.getBorder() != null)
			vf = drawPolygon(lonLats, maparea.getColor().getHex(), maparea.getColor().getAlpha(),
					maparea.getBorder().getWidth(), maparea.getBorder().getStyle().toString(), true, key);
		else
			vf = drawPolygon(lonLats, maparea.getColor().getHex(), maparea.getColor().getAlpha(), 1, "solid", true, key);

		vf.setFeatureId("" + featureCounter++);
		myfeatures.put(vf.getFeatureId(), object);
	}

	public static void switchLocation(DAO.CITY city) {
		double lon = 0;
		double lat = 0;

		switch (city) {
		case BERLIN:
			lon = DAO.BERLIN_GEO_lon;
			lat = DAO.BERLIN_GEO_lat;
			switchLocation(lon, lat, DAO.BERLIN_ZOOMLEVEL);
			break;
		case ROVERETO:
			//SocketController.get().requestForDemo();
			lon = DAO.ROVERETO_GEO_lon;
			lat = DAO.ROVERETO_GEO_lat;
			switchLocation(lon, lat, DAO.ROVERETO_ZOOMLEVEL);
			break;
		case TAMPERE:
			lon = DAO.TAMPERE_GEO_lon;
			lat = DAO.TAMPERE_GEO_lat;
			switchLocation(lon, lat, DAO.TAMPERE_ZOOMLEVEL);
			break;
		default:
			break;
		}
		
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

	public void update() {
		mapWidget.setHeight(RootPanel.get().getOffsetHeight() + "px");
	}
}
