
package de.fhg.fokus.streetlife.mmecp.share.dto;

import com.google.gwt.user.client.rpc.IsSerializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Generated;

import org.gwtopenmaps.openlayers.client.LonLat;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "type",
    "coordinates"
})
public class Area implements IsSerializable{

    @JsonProperty("type")
    private Area.Type type;
    @JsonProperty("coordinates")
    private List<List<List<Double>>> coordinates = new ArrayList<List<List<Double>>>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The type
     */
    @JsonProperty("type")
    public Area.Type getType() {
        return type;
    }

    /**
     * 
     * @param type
     *     The type
     */
    @JsonProperty("type")
    public void setType(Area.Type type) {
        this.type = type;
    }

    /**
     * 
     * @return
     *     The coordinates
     */
    @JsonProperty("coordinates")
    public List<List<List<Double>>> getCoordinates() {
        return coordinates;
    }

    /**
     * 
     * @param coordinates
     *     The coordinates
     */
    @JsonProperty("coordinates")
    public void setCoordinates(List<List<List<Double>>> coordinates) {
        this.coordinates = coordinates;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
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

        @JsonValue
        @Override
        public String toString() {
            return this.value;
        }

        @JsonCreator
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
