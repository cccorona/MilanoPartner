package knowhere.mx.milanopartner.pojo;

import java.io.Serializable;

/**
 * Created by cacorona on 13/07/2016.
 */
public class MilanoState implements Serializable {

    public static int FIRST_RUN_TRUE = 1;
    public static int FIRST_RUN_FALSE = 0;

    private int idState;
    private int firstRun;
    private int isUpdated;
    private String version;


    public int getFirstRun() {
        return firstRun;
    }

    public void setFirstRun(int firstRun) {
        this.firstRun = firstRun;
    }

    public int getIsUpdated() {
        return isUpdated;
    }

    public void setIsUpdated(int isUpdated) {
        this.isUpdated = isUpdated;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getIdState() {
        return idState;
    }

    public void setIdState(int idState) {
        this.idState = idState;
    }
}
