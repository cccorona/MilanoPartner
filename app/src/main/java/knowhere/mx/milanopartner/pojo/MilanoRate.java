package knowhere.mx.milanopartner.pojo;

import java.io.Serializable;

/**
 * Created by cacorona on 13/07/2016.
 */
public class MilanoRate implements Serializable {

    private double rateValue;
    private int servicesNumber;
    private int acceptedServices;
    private int fiveStarServices;


    public double getRateValue() {
        return rateValue;
    }

    public void setRateValue(double rateValue) {
        this.rateValue = rateValue;
    }

    public int getServicesNumber() {
        return servicesNumber;
    }

    public void setServicesNumber(int servicesNumber) {
        this.servicesNumber = servicesNumber;
    }

    public int getAcceptedServices() {
        return acceptedServices;
    }

    public void setAcceptedServices(int acceptedServices) {
        this.acceptedServices = acceptedServices;
    }

    public int getFiveStarServices() {
        return fiveStarServices;
    }

    public void setFiveStarServices(int fiveStarServices) {
        this.fiveStarServices = fiveStarServices;
    }
}
