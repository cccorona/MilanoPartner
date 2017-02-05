package knowhere.mx.milanopartner.pojo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Corona on 12/4/2016.
 */

public class TicketMasterEvent implements Serializable {

    private String name;
    private String type;
    private String id;
    private String test;
    private String locale;
    private String url;
    private String pleaseNote;
    private ArrayList<PriceRange> priceRanges;
    private Promoter promoter;
    private String info;
    private ArrayList<ImageEvent> images;
    private Sale sales;
    private DateEvent dates;
    private ArrayList<Classification> classifications;
    private Link _links;
    private Embedded  _embedded;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPleaseNote() {
        return pleaseNote;
    }

    public void setPleaseNote(String pleaseNote) {
        this.pleaseNote = pleaseNote;
    }

    public ArrayList<PriceRange> getPriceRanges() {
        return priceRanges;
    }

    public void setPriceRanges(ArrayList<PriceRange> priceRanges) {
        this.priceRanges = priceRanges;
    }

    public Promoter getPromoter() {
        return promoter;
    }

    public void setPromoter(Promoter promoter) {
        this.promoter = promoter;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public ArrayList<ImageEvent> getImages() {
        return images;
    }

    public void setImages(ArrayList<ImageEvent> images) {
        this.images = images;
    }

    public Sale getSales() {
        return sales;
    }

    public void setSales(Sale sales) {
        this.sales = sales;
    }

    public DateEvent getDates() {
        return dates;
    }

    public void setDates(DateEvent dates) {
        this.dates = dates;
    }

    public ArrayList<Classification> getClassifications() {
        return classifications;
    }

    public void setClassifications(ArrayList<Classification> classifications) {
        this.classifications = classifications;
    }

    public Link get_links() {
        return _links;
    }

    public void set_links(Link _links) {
        this._links = _links;
    }

    public Embedded get_embedded() {
        return _embedded;
    }

    public void set_embedded(Embedded _embedded) {
        this._embedded = _embedded;
    }
}
