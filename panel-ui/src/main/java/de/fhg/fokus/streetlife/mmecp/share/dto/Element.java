
package de.fhg.fokus.streetlife.mmecp.share.dto;

import javax.annotation.Generated;
import javax.validation.Valid;
import com.google.gson.annotations.Expose;

@Generated("org.jsonschema2pojo")
public class Element {

    /**
     * An element type to visualize a single label value pair.
     * 
     */
    @Expose
    @Valid
    private Attribute attribute;
    /**
     * An element type to visualize a chart for a list of label value pairs.
     * 
     */
    @Expose
    @Valid
    private Chart chart;
    /**
     * An element type to visualize and place an image at the defined position.
     * 
     */
    @Expose
    @Valid
    private Icon icon;
    /**
     * An element type to visualize and colorize a specific geolocation.
     * 
     */
    @Expose
    @Valid
    private Maparea maparea;

    /**
     * An element type to visualize a single label value pair.
     * 
     * @return
     *     The attribute
     */
    public Attribute getAttribute() {
        return attribute;
    }

    /**
     * An element type to visualize a single label value pair.
     * 
     * @param attribute
     *     The attribute
     */
    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    /**
     * An element type to visualize a chart for a list of label value pairs.
     * 
     * @return
     *     The chart
     */
    public Chart getChart() {
        return chart;
    }

    /**
     * An element type to visualize a chart for a list of label value pairs.
     * 
     * @param chart
     *     The chart
     */
    public void setChart(Chart chart) {
        this.chart = chart;
    }

    /**
     * An element type to visualize and place an image at the defined position.
     * 
     * @return
     *     The icon
     */
    public Icon getIcon() {
        return icon;
    }

    /**
     * An element type to visualize and place an image at the defined position.
     * 
     * @param icon
     *     The icon
     */
    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    /**
     * An element type to visualize and colorize a specific geolocation.
     * 
     * @return
     *     The maparea
     */
    public Maparea getMaparea() {
        return maparea;
    }

    /**
     * An element type to visualize and colorize a specific geolocation.
     * 
     * @param maparea
     *     The maparea
     */
    public void setMaparea(Maparea maparea) {
        this.maparea = maparea;
    }

}
