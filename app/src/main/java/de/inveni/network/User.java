package de.inveni.network;

public class User {
    private long id;
    private String name;
    private String givenName;
    private String street;
    private String houseNumber;
    private String plz;
    private String email;
    private String phone;
    private Country country;

    public User(long id, String name, String givenName, String street, String houseNumber, String plz, String email, String phone, Country country) {
        this.id = id;
        this.name = name;
        this.givenName = givenName;
        this.street = street;
        this.houseNumber = houseNumber;
        this.plz = plz;
        this.email = email;
        this.phone = phone;
        this.country = country;
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
}
