package de.inveni.network;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RequestManager {

    public static final String IP = "http://10.4.1.102:8080/";

    private static User currentUser;

    private static String request(ConnectionType type, String command, String body) {
        HttpURLConnection connection;
        BufferedReader in;
        BufferedWriter out;
        try {
            URL url = new URL(IP + command);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(type.toString());
            in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            if(body != null) {
                connection.setRequestProperty("Content-Type", "application/json; utf-8");
                connection.setDoOutput(true);
                out = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
                out.write(body);
            }
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

    public static List<Property> queryProperties(long dateBefore, long dateAfter, String description, double latitude, double longitude, double radius) {
        String request = RequestManager.request(ConnectionType.GET, "fetchpropertiesbyattribsforuser?userID=" + currentUser.getId() + "&dateBefore=" + dateBefore + "&dateAfter=" + dateAfter + "&desc=" + description + "&lat=" + latitude + "&lon=" + longitude + "&radius=" + radius, null);
        try {
            List<Property> list = new ArrayList<>();
            JSONArray jsonArray = new JSONArray(request);
            for (int i = 0; i < jsonArray.length(); i++) {
                list.add(Toolbox.jsonToProperty(jsonArray.getJSONObject(i), false));
            }
            return list;
        } catch (NullPointerException | JSONException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static Property addProperty(String title, String date, String description, double latitude, double longitude, String imageBase64) {
        String request = RequestManager.request(ConnectionType.POST, "addproperty", "{\"finderID\":"+ currentUser.getId() +",\"title\":\"" + title + "\",\"date\":" + date + ",\"latitude\":" + latitude + ",\"longitude\":" + longitude + ",\"description\":\"" + description + "\",\"" + imageBase64 + "\":\"-\"}");
        try {
            return Toolbox.jsonToProperty(new JSONObject(request), false);
        } catch (NullPointerException | JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Property addPossibleOwner(long userID, long propertyID) {
        String request = RequestManager.request(ConnectionType.POST, "addpossibleowner", "{\"userID\":" + userID + ",\"propertyID\":" + propertyID + "}");
        try {
            return Toolbox.jsonToProperty(new JSONObject(request), false);
        } catch (NullPointerException | JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Property removePossibleOwner(long userID, long propertyID) {
        String request = RequestManager.request(ConnectionType.POST, "removepossibleowner", "{\"userID\":" + userID + ",\"propertyID\":" + propertyID + "}");
        try {
            return Toolbox.jsonToProperty(new JSONObject(request), false);
        } catch (NullPointerException | JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Property removeProperty(long id) {
        String request = RequestManager.request(ConnectionType.GET, "deletepropertybyid?id=" + id, null);
        try {
            return Toolbox.jsonToProperty(new JSONObject(request), false);
        } catch (NullPointerException | JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Property fetchProperty(long id) {
        String request = RequestManager.request(ConnectionType.GET, "fetchpropertybyid?id=" + id, null);
        try {
            return Toolbox.jsonToProperty(new JSONObject(request), false);
        } catch (NullPointerException | JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Property fetchUser(long id) {
        String request = RequestManager.request(ConnectionType.GET, "fetchuserbyid?id=" + id, null);
        try {
            return Toolbox.jsonToProperty(new JSONObject(request), false);
        } catch (NullPointerException | JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Property deleteUser(long id) {
        String request = RequestManager.request(ConnectionType.GET, "deleteuserbyid?id=" + id, null);
        try {
            return Toolbox.jsonToProperty(new JSONObject(request), false);
        } catch (NullPointerException | JSONException e) {
            e.printStackTrace();
            return null;
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
