package de.inveni.network;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
    private long id;
    private String name;
    private String givenName;
    private String street;
    private String houseNumber;
    private String plz;
    private String email;
    private String phone;
    private Country country;
    private List<Long> properties;

    public User(long id, String name, String givenName, String street, String houseNumber, String plz, String email, String phone, Country country, List<Long> properties) {
        this.id = id;
        this.name = name;
        this.givenName = givenName;
        this.street = street;
        this.houseNumber = houseNumber;
        this.plz = plz;
        this.email = email;
        this.phone = phone;
        this.country = country;
        this.properties = properties;

    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGivenName() {
        return givenName;
    }

    public String getStreet() {
        return street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getPlz() {
        return plz;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public Country getCountry() {
        return country;
    }

    public List<Long> getProperties() {
        return properties;
    }
}
