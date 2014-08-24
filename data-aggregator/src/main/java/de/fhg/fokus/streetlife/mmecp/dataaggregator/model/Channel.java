package de.fhg.fokus.streetlife.mmecp.dataaggregator.model;

import java.io.Serializable;
import java.net.URL;

/**
 * Created by benjamin on 24.08.14.
 */
public interface Channel extends Serializable {

    public String getTitle();
    public void setTitle(String title);
    public String getId();
    public void setId(String id);
    public URL getUrl();
    public void setUrl(URL url);
    public boolean isStandard();
    public void setStandard(boolean standard);
}
