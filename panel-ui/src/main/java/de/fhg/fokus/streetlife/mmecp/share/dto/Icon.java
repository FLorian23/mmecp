
package de.fhg.fokus.streetlife.mmecp.share.dto;

import java.net.URI;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;


/**
 * An element type to visualize and place an image at the defined position.
 * 
 */
@Generated("org.jsonschema2pojo")
public class Icon {

    @Expose
    private URI image;

    /**
     * 
     * @return
     *     The image
     */
    public URI getImage() {
        return image;
    }

    /**
     * 
     * @param image
     *     The image
     */
    public void setImage(URI image) {
        this.image = image;
    }

}
