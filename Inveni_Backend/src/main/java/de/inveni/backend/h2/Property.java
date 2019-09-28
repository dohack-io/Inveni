package de.inveni.backend.h2;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import de.inveni.backend.util.UserSerializer;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User finder;
    private String title;
    private long date;
    private double latitude;
    private double longitude;
    private String description;
    private String imageBase64;
    @ManyToMany(mappedBy = "properties",fetch = FetchType.LAZY)
    @JsonSerialize(using = UserSerializer.class)
    private List<User> users;

    public Property(){

    }

    public Property(User finder, String title, long date, double latitude, double longitude, String description, String imageBase64) {
        this.finder = finder;
        this.title = title;
        this.date = date;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
        this.imageBase64 = imageBase64;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getFinderID() {
        return finder;
    }

    public void setFinderID(User finder) {
        this.finder = finder;
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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void addUser(User user){
        if(Objects.isNull(users)){
            users = new ArrayList<>();
        }
        for(User u:users){
            if(users.equals(u)){
                return;
            }
        }
        if(finder.equals(user)){
            return;
        }
        users.add(user);
    }

    public void removeUser(User user){
        if (Objects.nonNull(users)) {
            users.remove(user);
        }
    }
}
