package de.inveni.network;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Toolbox {
    public static Property jsonToProperty(JSONObject jsonRoot, boolean lazy) {
        try {
            long id = jsonRoot.getLong("id");
            String title = jsonRoot.getString("title");
            long date = jsonRoot.getLong("date");
            double latitude = jsonRoot.getDouble("latitude");
            double longitude = jsonRoot.getDouble("longitude");
            String description = jsonRoot.getString("description");
            String imageBase64 = jsonRoot.getString("imageBase64");
            JSONArray possibleOwners = jsonRoot.getJSONArray("users");
            JSONObject finder = jsonRoot.getJSONObject("finderID");

            List<User> _possibleOwners = new ArrayList<>();
            if (!lazy) {
                for (int i = 0; i < possibleOwners.length(); i++) {
                    _possibleOwners.add(Toolbox.jsonToUser(possibleOwners.getJSONObject(i), true));
                }
            }

            User _finder = Toolbox.jsonToUser(finder, false);

            return new Property(id, _finder, title, date, latitude, longitude, description, imageBase64, _possibleOwners);

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static User jsonToUser(JSONObject jsonRoot, boolean lazy) {
        try {
            long id = jsonRoot.getLong("id");
            String name = jsonRoot.getString("name");
            String givenName = jsonRoot.getString("givenName");
            String street = jsonRoot.getString("street");
            String houseNumber = jsonRoot.getString("houseNumber");
            String plz = jsonRoot.getString("plz");
            String email = jsonRoot.getString("email");
            String phone = jsonRoot.getString("phone");
            JSONObject country = jsonRoot.getJSONObject("country");
            JSONArray properties = jsonRoot.getJSONArray("properties");

            ArrayList<Property> _properties = new ArrayList<>();
            if (!lazy) {
                for (int i = 0; i < properties.length(); i++) {
                    _properties.add(Toolbox.jsonToProperty(properties.getJSONObject(i), true));
                }
            }

            Country _country = Toolbox.jsonToCountry(country);

            return new User(id, name, givenName, street, houseNumber, plz, email, phone, _country, _properties);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Country jsonToCountry(JSONObject jsonRoot) {
        try {
            long id = jsonRoot.getLong("id");
            String name = jsonRoot.getString("name");
            return new Country(id, name);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
