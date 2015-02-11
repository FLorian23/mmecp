package de.fhg.fokus.streetlife.mmecp.client.view.siteelement.tabpanel.map;

import de.fhg.fokus.streetlife.mmecp.client.controller.LOG;
import de.fhg.fokus.streetlife.mmecp.client.controller.SocketController;
import de.fhg.fokus.streetlife.mmecp.client.model.IEventInfoDataMapper;
import de.fhg.fokus.streetlife.mmecp.client.view.siteelement.sidebar.SlideBar;
import de.fhg.fokus.streetlife.mmecp.client.view.siteelement.sidebar.right.DtoToGWTElementMapper;
import de.fhg.fokus.streetlife.mmecp.client.view.siteelement.tabpanel.TabPanelManager;
import de.fhg.fokus.streetlife.mmecp.share.dto.Maparea;
import de.fhg.fokus.streetlife.mmecp.share.dto.PanelObject;

import org.gwtopenmaps.openlayers.client.LonLat;
import org.gwtopenmaps.openlayers.client.Map;
import org.gwtopenmaps.openlayers.client.MapOptions;
import org.gwtopenmaps.openlayers.client.MapWidget;
import org.gwtopenmaps.openlayers.client.Projection;
import org.gwtopenmaps.openlayers.client.Style;
import org.gwtopenmaps.openlayers.client.control.*;
import org.gwtopenmaps.openlayers.client.event.*;
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
import de.fhg.fokus.streetlife.mmecp.client.view.siteelement.SiteElement;
import de.fhg.fokus.streetlife.mmecp.client.view.siteelement.sidebar.right.SlideBarRight;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import static com.google.gwt.query.client.GQuery.$;

public class MapContainer extends SiteElement<VerticalPanel> implements ClickHandler, ChangeHandler, Observer {

	private static MapContainer instance = null;

	final Vector vectorLayerParkingMacro = new Vector("ParkingMacro");
	final Vector vectorLayerParkingFree = new Vector("ParkingFree");
	final Vector vectorLayerParkingFee = new Vector("ParkingFee");
	final Vector vectorLayerParkingClock = new Vector("ParkingClock");

	private Layer[] layerGM = new Layer[2];
	private Layer[] layerOSM = new Layer[2];


	private Position currentPosition = null;
	private MapWidget mapWidget;
	private Map map;
	private static final Projection DEFAULT_PROJECTION = new Projection("EPSG:4326");
	private boolean isGoogleMaps = true;
	private HashMap<String, PanelObject> drawnObjects = new HashMap();

	private MapContainer() {
		super(new VerticalPanel(), "mapcontainer", null);
		buildPanel();
		getPanel().setSize("100%", "100%");
		Subject.get().addToSubjectList(this);
	}

	public static MapContainer get() {
		if (instance == null)
			instance = new MapContainer();
		return instance;
	}

	public static ClickHandler getHandler() {
		return (ClickHandler) get();
	}

	public PanelObject getMapObjectByID(String objectType, String id){
		Collection<PanelObject> values = drawnObjects.values();
		for (PanelObject a : values){
			if (a.getObjectID() == id && objectType.compareTo(a.getObjectType()) == 0)
				return a;
		}
		return null;
	}
	
	public void buildGoogleMaps() {
		//removeAllLayers();
		removeOSLayers();

		map.addLayer(layerGM[0]);
		map.addLayer(layerGM[1]);
		// map.addLayer(gHybrid);
		// map.addLayer(gTerrain);

		//buildLayer();
		isGoogleMaps = true;
	}

	private void removeOSLayers() {
		for (int i = 0;i<layerOSM.length;i++){
			if (map.getNumLayers() == 0) return;
			map.removeLayer(layerOSM[i]);
		}
	}
	private void removeGMLayers() {
		for (int i = 0;i<layerGM.length;i++){
			if (map.getNumLayers() == 0) return;
			map.removeLayer(layerGM[i]);
		}
	}

	private void buildLayer() {
		map.addLayer(vectorLayerParkingMacro);
		map.addLayer(vectorLayerParkingClock);
		map.addLayer(vectorLayerParkingFee);
		map.addLayer(vectorLayerParkingFree);

		vectorLayerParkingFree.setIsVisible(false);
		vectorLayerParkingMacro.setIsVisible(false);
		vectorLayerParkingFee.setIsVisible(false);
		vectorLayerParkingClock.setIsVisible(false);
	}

	public void buildOpenStreetMaps() {
		//removeAllLayers();
		removeGMLayers();

		map.addLayer(layerOSM[0]);
		map.addLayer(layerOSM[1]);

		// Add polygon Layer
		//buildLayer();

		isGoogleMaps = false;
	}

	SelectFeature clickSelectFeature = null;
	VectorFeature lastSelected = null;
	private void createSelectFeatureStuff(){

		//map.addControl(new LayerSwitcher());

		Vector[] myVectors = new Vector[4];
		myVectors[0] = vectorLayerParkingMacro;
		myVectors[1] = vectorLayerParkingFree;
		myVectors[2] = vectorLayerParkingClock;
		myVectors[3] = vectorLayerParkingFee;

		SelectFeature selectFeature = new SelectFeature(myVectors);
		map.addControl(selectFeature);

		selectFeature.activate();
		createSelectFeatureForLayer(vectorLayerParkingMacro);
		createSelectFeatureForLayer(vectorLayerParkingFree);
		createSelectFeatureForLayer(vectorLayerParkingClock);
		createSelectFeatureForLayer(vectorLayerParkingFee);

		VectorFeatureAddedListener vectorFeatureAddedListener = new VectorFeatureAddedListener() {
			@Override
			public void onFeatureAdded(FeatureAddedEvent eventObject) {
				//LOG.logToConsole("new Feature added");
			}
		};
		vectorLayerParkingClock.addVectorFeatureAddedListener(vectorFeatureAddedListener);
		vectorLayerParkingFee.addVectorFeatureAddedListener(vectorFeatureAddedListener);
		vectorLayerParkingFree.addVectorFeatureAddedListener(vectorFeatureAddedListener);
		vectorLayerParkingMacro.addVectorFeatureAddedListener(vectorFeatureAddedListener);

		VectorFeatureUnselectedListener vectorFeatureUnselectedListener = new VectorFeatureUnselectedListener() {
			@Override
			public void onFeatureUnselected(FeatureUnselectedEvent eventObject) {
				Style s = eventObject.getVectorFeature().getStyle();

				PanelObject po = drawnObjects.get(eventObject.getVectorFeature().getFeatureId());

				s.setFillOpacity(po.getMaparea().getColor().getAlpha());
				s.setStroke(true);
				s.setStrokeWidth(1);
				eventObject.getVectorFeature().setStyle(s);
				eventObject.getVectorFeature().redrawParent();
				map.updateSize();

				SlideBarRight.get().setStatus(SlideBar.STATUS.CLOSE);
			}
		};
		vectorLayerParkingClock.addVectorFeatureUnselectedListener(vectorFeatureUnselectedListener);
		vectorLayerParkingFee.addVectorFeatureUnselectedListener(vectorFeatureUnselectedListener);
		vectorLayerParkingFree.addVectorFeatureUnselectedListener(vectorFeatureUnselectedListener);
		vectorLayerParkingMacro.addVectorFeatureUnselectedListener(vectorFeatureUnselectedListener);

		VectorFeatureSelectedListener vectorFeatureSelectedListener = new VectorFeatureSelectedListener() {
			@Override
			public void onFeatureSelected(FeatureSelectedEvent eventObject) {
				if (lastSelected != null) {
					clickSelectFeature.unSelect(lastSelected);
				}
				lastSelected = eventObject.getVectorFeature();
				Style s = eventObject.getVectorFeature().getStyle();
				s.setFillOpacity(0.9); //default 0.5
				s.setStroke(true);
				LOG.logToConsole(eventObject.getVectorFeature().getLayer().getId());
				s.setStrokeWidth(5);
				eventObject.getVectorFeature().setStyle(s);
				eventObject.getVectorFeature().redrawParent();
				map.updateSize();
				PanelObject po = drawnObjects.get(eventObject.getVectorFeature().getFeatureId());
				openSiteBar(po);
			}
		};
		vectorLayerParkingClock.addVectorFeatureSelectedListener(vectorFeatureSelectedListener);
		vectorLayerParkingFee.addVectorFeatureSelectedListener(vectorFeatureSelectedListener);
		vectorLayerParkingFree.addVectorFeatureSelectedListener(vectorFeatureSelectedListener);
		vectorLayerParkingMacro.addVectorFeatureSelectedListener(vectorFeatureSelectedListener);
	}

	private void createSelectFeatureForLayer(Vector vectorLayerParkingFree) {
		clickSelectFeature = new SelectFeature(vectorLayerParkingFree); //no options needed because no hover is needed
		clickSelectFeature.setClickOut(true);
		clickSelectFeature.setToggle(true);
		clickSelectFeature.setMultiple(false); //do not select multiple when clicked normally
		clickSelectFeature.setToggleKey("ctrlKey"); //Do toggle the selection when user holds CTRL key
		clickSelectFeature.setMultipleKey("shiftKey"); //Do select multiple features when user holds SHIFT key
		map.addControl(clickSelectFeature);
		clickSelectFeature.activate();
	}


	public void buildPanel() {

		// create some MapOptions
		MapOptions defaultMapOptions = new MapOptions();
		defaultMapOptions.removeDefaultControls();
		defaultMapOptions.setNumZoomLevels(DAO.MapContainer_SetNumZoomLevels);

		// Create a MapWidget and add 2 OSM layers
		mapWidget = new MapWidget("100%", "100%", defaultMapOptions);
		map = mapWidget.getMap();


		//build OpenStreet Layer
		OSM osm_1 = OSM.Mapnik("Mapnik");
		OSM osm_2 = OSM.CycleMap("CycleMap");
		osm_1.setIsBaseLayer(true);
		osm_2.setIsBaseLayer(true);

		layerOSM[0] = osm_1;
		layerOSM[1] = osm_2;


		//build Google Maps Layer
		GoogleV3Options gNormalOptions = new GoogleV3Options();
		gNormalOptions.setIsBaseLayer(true);
		gNormalOptions.setType(GoogleV3MapType.G_NORMAL_MAP);
		GoogleV3 gNormal = new GoogleV3("Google Normal", gNormalOptions);

		GoogleV3Options gSatelliteOptions = new GoogleV3Options();
		gSatelliteOptions.setIsBaseLayer(true);
		gSatelliteOptions.setType(GoogleV3MapType.G_SATELLITE_MAP);
		GoogleV3 gSatellite = new GoogleV3("Google Satellite", gSatelliteOptions);

		layerGM[0] = gNormal;
		layerGM[1] = gSatellite;


		// buildGoogleMaps();
		buildOpenStreetMaps();


		buildLayer();

		// Lets add some default controls to the map
		// map.addControl(new LayerSwitcher());
		// map.addControl(new OverviewMap());
		// map.addControl(new ScaleLine());
		map.addControl(new Navigation());
		map.addControl(new ArgParser());
		map.addControl(new Attribution());

		createSelectFeatureStuff();

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
		LonLat lonLat = new LonLat(DAO.ROVERETO_GEO_lon, DAO.ROVERETO_GEO_lat);
		lonLat.transform(DEFAULT_PROJECTION.getProjectionCode(), map.getProjection());
		map.setCenter(lonLat, DAO.ROVERETO_ZOOMLEVEL);

		addWidgetToPanel(mapWidget, "mapWidget", "");
		mapWidget.setHeight(RootPanel.get().getOffsetHeight() + "px");

	}


	private void openSiteBar(PanelObject panelObject){
		VerticalPanel content = SlideBarRight.get().getContent();
		content.clear();
		// TODO: mapObject Inhalt nutzen für das seitliche Panel und zoom dort
		IEventInfoDataMapper eventInfo = DtoToGWTElementMapper
				.map(panelObject);
		eventInfo.fillContent(content);
		SlideBarRight.get().setStatus(SlideBar.STATUS.OPEN);
	}

	protected static native double[] convert(double[] code, String src, String dest)
	/*-{
        var a = $wnd.proj4(src, dest, code);
		return a;
    }-*/;

	public VectorFeature drawPolygon(LonLat[] lonLat, String color, double alpha, double borderWidth, String borderStyle, boolean isUTM, String id, DAO.PARKING parkingCase) {
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
			double[] b;
			if (isUTM) {
				double[] a = {lonLat[i].lon(), lonLat[i].lat()};
				b = convert(a, "+proj=utm +zone=32 +ellps=WGS84 +units=m +no_defs", map.getProjection());//"EPSG:4326");
				pointList[i] = new Point(b[0], b[1]);
			} else {
				lonLat[i].transform(DEFAULT_PROJECTION.getProjectionCode(), map.getProjection());
				pointList[i] = new Point(lonLat[i].lon(), lonLat[i].lat());
			}
		}

		LinearRing linearRing = new LinearRing(pointList);
		VectorFeature polygonFeature = new VectorFeature(new Polygon(new LinearRing[] { linearRing }));
		polygonFeature.setFeatureId(id);
		polygonFeature.setStyle(s);

		switch (parkingCase) {
			case FREE:
				vectorLayerParkingFree.addFeature(polygonFeature);
				break;
			case FEE:
				vectorLayerParkingFee.addFeature(polygonFeature);
				break;
			case CLOCK:
				vectorLayerParkingClock.addFeature(polygonFeature);
				break;
			case MACRO:
				vectorLayerParkingMacro.addFeature(polygonFeature);
				break;
			default:
				LOG.logToConsole("ERROR in matching ParkingCase in drawPolygon");
		}
		return polygonFeature;
	}

	public static void switchLocation(double lon, double lat) {
		switchLocation(lon, lat, DAO.MapContainer_DEFAULTZOOMSIZE);
	}
	
	public static void switchLocation(double lon, double lat, int zoom) {
		LonLat lonLat = new LonLat(lon, lat);
		lonLat.transform(DEFAULT_PROJECTION.getProjectionCode(), get().map.getProjection());
		get().map.setCenter(lonLat, zoom);
	}

	public void drawObject(PanelObject object) {
		String key = object.getObjectType() + ":" + object.getObjectID() + ":" + object.getObjectSubtype();
		if (drawnObjects.containsKey(key)) {

			LOG.getLogger().info("replace object " + key);
			drawnObjects.remove(key);
			drawnObjects.put(key, object);

			LOG.getLogger().info("Redraw object " + key);
			switch (DAO.getParkingEnumOfSubType(object.getObjectSubtype())) {
				case CLOCK:
					vectorLayerParkingClock.removeFeature(vectorLayerParkingClock.getFeatureById(key));
					break;
				case FEE:
					vectorLayerParkingFee.removeFeature(vectorLayerParkingFee.getFeatureById(key));
					break;
				case FREE:
					vectorLayerParkingFree.removeFeature(vectorLayerParkingFree.getFeatureById(key));
					break;
				case MACRO:
					vectorLayerParkingMacro.removeFeature(vectorLayerParkingMacro.getFeatureById(key));
					break;
				default:
					LOG.logToConsole("ERROR in matching ParkingCase in drawObject");
			}
		} else {
			// add and draw object
			drawnObjects.put(key, object);
		}
		Maparea maparea = object.getMaparea();
		List<LonLat> coordinates = maparea.getArea().getCoordinatesLonLat().get(0);
		LonLat[] lonLats = coordinates.toArray(new LonLat[coordinates.size()]);

		VectorFeature vf = null;
		if (maparea.getBorder() != null)
			vf = drawPolygon(lonLats, maparea.getColor().getHex(), maparea.getColor().getAlpha(),
					maparea.getBorder().getWidth(), maparea.getBorder().getStyle().toString(), false, key, DAO.getParkingEnumOfSubType(object.getObjectSubtype()));
		else
			vf = drawPolygon(lonLats, maparea.getColor().getHex(), maparea.getColor().getAlpha(), 1, "solid", false, key, DAO.getParkingEnumOfSubType(object.getObjectSubtype()));

		vf.setFeatureId(key);
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
		mapWidget.setHeight(RootPanel.get().getOffsetHeight() - 76 - 24 + "px");
		TabPanelManager.get().getElement().getStyle().setHeight(RootPanel.get().getOffsetHeight() - 76 - 24, com.google.gwt.dom.client.Style.Unit.PX);
		$("#slideRightPanelContentWrapper").css("height", RootPanel.get().getOffsetHeight() - 76 - 60 + "px");
	}

	public void visibleLayer(DAO.PARKING parking, boolean visible) {

		switch (parking) {
			case CLOCK:
				if (visible)
					SocketController.get().sendMessage("getObjectsOfType:ParkingStationsClock", 5);
				vectorLayerParkingClock.setIsVisible(visible);
				break;
			case FEE:
				if (visible)
					SocketController.get().sendMessage("getObjectsOfType:ParkingStationsFee", 5);
				vectorLayerParkingFee.setIsVisible(visible);
				break;
			case FREE:
				if (visible)
					SocketController.get().sendMessage("getObjectsOfType:ParkingStationsFree", 5);
				vectorLayerParkingFree.setIsVisible(visible);
				break;
			case MACRO:
				if (visible)
					SocketController.get().sendMessage("getObjectsOfType:ParkingAreas", 5);
				vectorLayerParkingMacro.setIsVisible(visible);
				break;
			default:
				LOG.logToConsole("ERROR in matching ParkingCase in drawObject");
		}
	}

}
