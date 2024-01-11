//package de.frauas.muellerbady.java.test;
//author: Adizen

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class ApiAdapter {

    private static final String USER_AGENT = "Group 26";
    private static final String URL = "https://dronesim.facets-labs.com/api/";
    private static final String JSON_FORMAT = "/?format=json";
    private static int offset;
    private static final String QUERY = "&limit=10&offset=";
    private static final String TOKEN = "Token 1bbbbd05efe3c733efcf8f443582a09cac4ca02c";
    private static JSONObject jsonResponse;

    public static JSONObject api_fetch(String category) {
        URL url;
        try {
            url = new URL(URL + category + JSON_FORMAT + QUERY + offset);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Authorization", TOKEN);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", USER_AGENT);

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            JSONTokener tokener = new JSONTokener(in);
            jsonResponse = new JSONObject(tokener);
            in.close();

        } catch (MalformedURLException e) {
            System.err.println("Malformed URL: " + e.getLocalizedMessage());
            e.printStackTrace();
        }
        catch (IOException e) {
            System.out.println("General IO Exception: " + e.getLocalizedMessage());
            e.printStackTrace();
        }
        return jsonResponse;
    }

    /**
     * @functionality
     * Example: [This method takes two integers as input and returns their sum.
     * It performs addition and returns the result]
     *
     * @param category ::::.
     * @return :::::.
     * @author AdiZen
     * @version 1.0
     * @last_modified 2024.01.11
     */
    public static JSONArray api_results(String category){
        JSONArray results = new JSONArray();
        JSONObject fetch = api_fetch(category);
        int j=0;
        while(fetch.getInt("count") > j){ // while next block is not null
            for (int i=0; i < fetch.getJSONArray("results").length(); i++) {
                results.put(fetch.getJSONArray("results").getJSONObject(i));
                j++;
            }
            offset = offset + 10;
            fetch = api_fetch(category);
        }
        System.out.println(results);
        System.out.println("-----------------");
        System.out.println(fetch);
        return results;
    }
}
