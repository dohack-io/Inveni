package de.inveni.network;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Objects;

public class RequestManager {


    public enum ConnectionType{
        GET, POST;
    }

    private static User currentUser;

    private static String request(ConnectionType type, String address){
        HttpURLConnection connection;
        BufferedReader in;
        try {
            URL url = new URL(address);
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
        }catch(IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public static List<Property> queryProperties(){
        String response = "{\"id\":1,\"title\":\"Test\",\"date\":201909291000,\"latitude\":51.50587,\"longitude\":7.545263,\"description\":\"Test\",\"imageBase64\":\"-\",\"users\":[],\"finderID\":{\"id\":2,\"name\":\"Nocon\",\"givenName\":\"Maurice\",\"street\":\"Flughafenstraße\",\"houseNumber\":\"104d\",\"plz\":\"?\",\"email\":\"maurice.nocon@adesso.de\",\"phone\":\"?\",\"country\":{\"id\":1,\"name\":\"Deutschland\"},\"properties\":[]}}";

    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        RequestManager.currentUser = currentUser;
    }
}
