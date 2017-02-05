package knowhere.mx.milanopartner.pojo;

import java.io.Serializable;

/**
 * Created by Corona on 12/4/2016.
 */

public class Link implements Serializable {

    private Self self;
    private Attractions attractions;
    private Venue venue;

    public Self getSelf() {
        return self;
    }

    public void setSelf(Self self) {
        this.self = self;
    }

    public Attractions getAttractions() {
        return attractions;
    }

    public void setAttractions(Attractions attractions) {
        this.attractions = attractions;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }
}
