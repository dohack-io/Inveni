package de.inveni.backend.h2;

public class PropertyDTO {
    private long finderID;
    private String title;
    private long date;
    private double latitude;
    private double longitude;
    private String description;
    private String imageBase64;

    public PropertyDTO() {

    }

    public long getFinderID() {
        return finderID;
    }

    public void setFinderID(long finderID) {
        this.finderID = finderID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }
}
