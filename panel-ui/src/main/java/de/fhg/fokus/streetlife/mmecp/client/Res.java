package de.fhg.fokus.streetlife.mmecp.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.TextResource;

public interface Res extends ClientBundle {
  public static final Res INSTANCE =  GWT.create(Res.class);

  @Source("../pictures/Streetlife.png")
  public ImageResource streetlifeImage();
  
  @Source("../pictures/Delete.png")
  public ImageResource deleteImage();
  
  @Source("../pictures/arrowLeft.png")
  public ImageResource arrowLeft();
  
  @Source("../pictures/arrowRight.png")
  public ImageResource arrowRight();
  
  @Source("../text/streetlife_panel_imprint.htm")
  TextResource streetlifePanelImprint();
}