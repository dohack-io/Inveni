package de.inveni.backend.h2;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import de.inveni.backend.util.PropertySerializer;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class UserReturnDTO {
    private Long id;
    private String name;
    private String givenName;
    private String street;
    private String houseNumber;
    private String plz;
    private String email;
    private String phone;
    private Country country;
    private List<Long> propertyIDs;

    public UserReturnDTO(User user){
        this.id = user.getId();
        this.name = user.getName();
        this.givenName = user.getGivenName();
        this.street = user.getStreet();
        this.houseNumber = user.getHouseNumber();
        this.plz = user.getPlz();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.country = user.getCountry();
        propertyIDs = new ArrayList<>();
        for(Property p:user.getProperties()){
            propertyIDs.add(p.getId());
        }
    }

    public Long getId() {
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

    public List<Long> getPropertyIDs() {
        return propertyIDs;
    }
}
