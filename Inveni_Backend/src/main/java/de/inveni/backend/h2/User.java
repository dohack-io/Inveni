package de.inveni.backend.h2;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import de.inveni.backend.util.PropertySerializer;
import de.inveni.backend.util.UserSerializer;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String givenName;
    private String street;
    private String houseNumber;
    private String plz;
    private String email;
    private String phone;
    @ManyToOne
    private Country country;
    @ManyToMany
    @JsonSerialize(using = PropertySerializer.class)
    @Basic(fetch = FetchType.LAZY)
    @JoinTable(
            name = "matching",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "property_id"))
    private List<Property> properties;

    public User() {

    }

    public User(String name, String givenName, String street, String houseNumber, String plz, String email, String phone, Country country) {
        this.name = name;
        this.givenName = givenName;
        this.street = street;
        this.houseNumber = houseNumber;
        this.plz = plz;
        this.email = email;
        this.phone = phone;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getPlz() {
        return plz;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    public void addProperty(Property property){
        if(Objects.isNull(properties)){
            properties = new ArrayList<>();
        }
        for(Property p:properties){
            if(property.equals(p)){
                return;
            }
        }
        properties.add(property);
    }

    public void removeProperty(Property property){
        if(Objects.nonNull(properties)){
            properties.remove(property);
        }
    }
}

