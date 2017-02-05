package knowhere.mx.milanopartner.pojo;

import java.io.Serializable;

/**
 * Created by Corona on 12/4/2016.
 */

public class ImageEvent implements Serializable {

    private String ratio;
    private String url;
    private int width;
    private int height;
    private boolean fallback;


    public String getRatio() {
        return ratio;
    }

    public void setRatio(String ratio) {
        this.ratio = ratio;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isFallback() {
        return fallback;
    }

    public void setFallback(boolean fallback) {
        this.fallback = fallback;
    }
}
