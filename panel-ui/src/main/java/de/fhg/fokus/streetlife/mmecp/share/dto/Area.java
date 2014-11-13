
package de.fhg.fokus.streetlife.mmecp.share.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import javax.validation.Valid;
import com.google.gson.annotations.Expose;

@Generated("org.jsonschema2pojo")
public class Area {

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
