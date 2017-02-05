package knowhere.mx.milanopartner.pojo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Corona on 12/4/2016.
 */

public class TicketMasterResponse implements Serializable {

    private ArrayList<TicketMasterEvent> _embedded;
    private Link _links;
    private transient PageEvent page;


    public TicketMasterResponse(ArrayList<TicketMasterEvent> events) {
        this._embedded = events;
    }

    public ArrayList<TicketMasterEvent> getEvents() {
        return _embedded;
    }

    public void setEvents(ArrayList<TicketMasterEvent> events) {
        this._embedded = events;
    }

    public Link get_links() {
        return _links;
    }

    public void set_links(Link _links) {
        this._links = _links;
    }

    public PageEvent getPage() {
        return page;
    }

    public void setPage(PageEvent page) {
        this.page = page;
    }
}
