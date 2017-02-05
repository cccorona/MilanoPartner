package knowhere.mx.milanopartner.pojo;

import java.io.Serializable;

/**
 * Created by Corona on 12/4/2016.
 */

public class Access implements Serializable {

    private String startDateTime;
    private boolean startApproximate;
    private String endDateTime;
    private boolean endApproximate;

    public String getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    public boolean isStartApproximate() {
        return startApproximate;
    }

    public void setStartApproximate(boolean startApproximate) {
        this.startApproximate = startApproximate;
    }

    public String getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }

    public boolean isEndApproximate() {
        return endApproximate;
    }

    public void setEndApproximate(boolean endApproximate) {
        this.endApproximate = endApproximate;
    }
}
