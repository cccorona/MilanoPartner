package knowhere.mx.milanopartner.pojo;

import java.io.Serializable;

/**
 * Created by Corona on 12/4/2016.
 */

public class Promoter implements Serializable {

    private String id;
    private String info;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
