package de.inveni.network;

import java.io.Serializable;

public class Country implements Serializable {
    private long id;
    private String name;

    public Country(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
