
package de.fhg.fokus.streetlife.mmecp.share.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Generated;
import javax.validation.Valid;

import com.google.gson.annotations.Expose;
import com.google.gwt.user.client.rpc.IsSerializable;
import org.gwtopenmaps.openlayers.client.LonLat;

@Generated("org.jsonschema2pojo")
public class Area implements IsSerializable{

    @Expose
    private Area.Type type;
    @Expose
    @Valid
    private List<List<List<Double>>> coordinates = new ArrayList<List<List<Double>>>();

    /**
     * 
     * @return
     *     The type
     */
    public Area.Type getType() {
        return type;
    }

    /**
     * 
     * @param type
     *     The type
     */
    public void setType(Area.Type type) {
        this.type = type;
    }

    /**
     * 
     * @return
     *     The coordinates
     */
    public List<List<List<Double>>> getCoordinates() {
        return coordinates;
    }

    public List<List<LonLat>> getCoordinatesLonLat() {
        List<List<LonLat>> result = new ArrayList<List<LonLat>>();
        for (int j = 0; j < coordinates.size(); j++) {
            List<LonLat> lonLatArray = new ArrayList<LonLat>();
            for (int i = 0; i < coordinates.get(j).size(); i++) {
                lonLatArray.add(new LonLat(
                        coordinates.get(j).get(i).get(0),
                        coordinates.get(j).get(i).get(1)
                ));
            }
            result.add(lonLatArray);
        }
        return result;
    }

    /**
     * 
     * @param coordinates
     *     The coordinates
     */
    public void setCoordinates(List<List<List<Double>>> coordinates) {
        this.coordinates = coordinates;
    }

    @Generated("org.jsonschema2pojo")
    public static enum Type {

        POLYGON("Polygon");
        private final String value;
        private static Map<String, Area.Type> constants = new HashMap<String, Area.Type>();

        static {
            for (Area.Type c: values()) {
                constants.put(c.value, c);
            }
        }

        private Type(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        public static Area.Type fromValue(String value) {
            Area.Type constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
