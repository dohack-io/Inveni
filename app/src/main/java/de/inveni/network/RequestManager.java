package de.inveni.network;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RequestManager {

    public static final String IP = "http://10.9.80.33:8080/";

    private static User currentUser;

    private static String request(ConnectionType type, String command) {
        HttpURLConnection connection;
        BufferedReader in;
        try {
            URL url = new URL(IP + command);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(type.toString());
            connection.setDoOutput(true);
            in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            connection.disconnect();
            return content.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Property> queryProperties(long dateBefore, long dateAfter, String description, double latitude, double longitude, double radius){
        String request = RequestManager.request(ConnectionType.GET, "fetchpropertiesbyattribsforuser?userID=" + currentUser.getId() + "&dateBefore=" + dateBefore + "&dateAfter=" + dateAfter + "&desc=" + description + "&lat=" + latitude + "&lon=" + longitude + "&radius=" + radius);
        try {
            List<Property> list = new ArrayList<>();
            JSONArray jsonArray = new JSONArray(request);
            for(int i = 0; i < jsonArray.length(); i++){
                list.add(Toolbox.jsonToProperty(jsonArray.getJSONObject(i), false));
            }
            return list;
        }catch(NullPointerException | JSONException e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        RequestManager.currentUser = currentUser;
    }

    public enum ConnectionType {
        GET, POST;
    }
}
