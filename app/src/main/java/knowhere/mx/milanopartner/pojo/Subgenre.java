package knowhere.mx.milanopartner.pojo;

import java.io.Serializable;

/**
 * Created by Corona on 12/4/2016.
 */

public class Subgenre implements Serializable {

    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
