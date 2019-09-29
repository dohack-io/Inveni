package de.inveni.network;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Toolbox {
    public static Property jsonToProperty(JSONObject jsonRoot) {
        try {
            long id = jsonRoot.getLong("id");
            String title = jsonRoot.getString("title");
            long date = jsonRoot.getLong("date");
            double latitude = jsonRoot.getDouble("latitude");
            double longitude = jsonRoot.getDouble("longitude");
            String description = jsonRoot.getString("description");
            String imageBase64 = jsonRoot.getString("imageBase64");
            JSONArray possibleOwners = jsonRoot.getJSONArray("users");
            JSONObject finder = jsonRoot.getJSONObject("finder");

            List<User> _possibleOwners = new ArrayList<>();
            for(int i = 0; i < possibleOwners.length(); i++){
                _possibleOwners.add(Toolbox.jsonToUser(possibleOwners.getJSONObject(i)));
            }

            User _finder = Toolbox.jsonToUser(finder);

            return new Property(id, _finder,title,date,latitude,longitude,description,imageBase64, _possibleOwners);

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static User jsonToUser(JSONObject jsonRoot){

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
