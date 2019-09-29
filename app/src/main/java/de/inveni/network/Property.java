package de.inveni.network;

import java.util.List;

public class Property {
    private Long id;
    private User finderID;
    private String title;
    private long date;
    private double latitude;
    private double longitude;
    private String description;
    private String imageBase64;
    private List<Long> users;

    public Property(Long id, User finderID, String title, long date, double latitude, double longitude, String description, String imageBase64, List<Long> users) {
        this.id = id;
        this.finderID = finderID;
        this.title = title;
        this.date = date;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
        this.imageBase64 = imageBase64;
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public User getFinderID() {
        return finderID;
    }

    public String getTitle() {
        return title;
    }

    public long getDate() {
        return date;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getDescription() {
        return description;
    }

    public String getImageBase64() {
        return imageBase64;
    }

    public List<Long> getUsers() {
        return users;
    }
}
