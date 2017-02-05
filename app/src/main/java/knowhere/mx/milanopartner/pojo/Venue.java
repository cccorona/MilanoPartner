package knowhere.mx.milanopartner.pojo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Corona on 12/4/2016.
 */

public class Venue implements Serializable {

    private ArrayList<String> href;

    public ArrayList<String> getHref() {
        return href;
    }

    public void setHref(ArrayList<String> href) {
        this.href = href;
    }
}
