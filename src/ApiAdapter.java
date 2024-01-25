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

    /**
     * Fetches data from an API based on a specified category.
     * @param category The category for API data retrieval.
     * @return JSONObject containing API response.
     * @author Müller Bady and Adizen
     * @since 1.0
     * @last_modified 2024.01.10
     */
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
     * Fetches and aggregates results from an API for a given category.
     * @param category The category for API data retrieval.
     * @return JSONArray containing aggregated results.
     * @author AdiZen
     * @since 1.0
     * @last_modified 2024.01.10
     */
    public static JSONArray api_results(String category){
=======
    public static JSONArray apiResults(String category){
>>>>>>> 23fa67e7811582e2e4d167715cdadc1a7241a503
        JSONArray results = new JSONArray();
        JSONObject fetch = apiFetch(category);
        int j=0;
        while(fetch.getInt("count") > j){ // while next block is not null
            for (int i=0; i < fetch.getJSONArray("results").length(); i++) {
                results.put(fetch.getJSONArray("results").getJSONObject(i));
                j++;
            }
            offset = offset + 10;
            fetch = apiFetch(category);
        }
        System.out.println(results);
        System.out.println("-----------------");
        System.out.println(fetch);
        return results;
    }
}
