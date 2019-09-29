package de.inveni.backend.h2;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import de.inveni.backend.util.UserSerializer;

import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.List;

public class PropertyReturnDTO {
    private Long id;
    private User finder;
    private String title;
    private long date;
    private double latitude;
    private double longitude;
    private String description;
    private String imageBase64;
    private List<Long> userIDs;

    public PropertyReturnDTO(Property property){
        this.id = property.getId();
        this.finder = property.getFinderID();
        this.title = property.getTitle();
        this.date = property.getDate();
        this.latitude = property.getLatitude();
        this.longitude = property.getLongitude();
        this.description = property.getDescription();
        this.imageBase64 = property.getImageBase64();
        this.userIDs = new ArrayList<>();
        for(User u:property.getUsers()){
            userIDs.add(u.getId());
        }
    }

    public Long getId() {
        return id;
    }

    public User getFinder() {
        return finder;
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

    public List<Long> getUserIDs() {
        return userIDs;
    }
}
