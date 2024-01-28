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
    private static int limit = 10;
    private static int offset = 0;
    private static final String LIMIT_STR = "&limit=";
    private static final String OFFSET_STR = "&offset=";
    private static final String TOKEN = "Token 1bbbbd05efe3c733efcf8f443582a09cac4ca02c";
    private static JSONObject jsonResponse;

    public static boolean nextPageExists;
    public static boolean previousPageExists;

    /**
     * Fetches data from an API based on a specified category.
     * 
     * @param dataCategory The category for API data retrieval.
     * @return JSONObject containing API response.
     * @author Müller Bady and Adizen
     * @since 1.0
     * @last_modified 2024.01.10
     */
    public static JSONObject fetchApi(String dataCategory) {
        URL url;
        try {
            url = new URL(URL + dataCategory + JSON_FORMAT + LIMIT_STR + limit + OFFSET_STR + offset);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Authorization", TOKEN);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", USER_AGENT);

            int responseCode = connection.getResponseCode();
            System.out.println("Response Code " + responseCode);

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            JSONTokener tokener = new JSONTokener(in);
            jsonResponse = new JSONObject(tokener);
            in.close();

        } catch (MalformedURLException e) {
            System.err.println("Malformed URL: " + e.getLocalizedMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("General IO Exception: " + e.getLocalizedMessage());
            e.printStackTrace();
        }
        return jsonResponse;
    }

    /**
     * Fetches data from an API based on a specified request.
     * 
     * @param dataCategory The category for API data retrieval.
     * @return JSONObject containing API response.
     * @author Müller Bady, Adizen, Victor
     * @since 1.0
     * @last_modified 2024.01.28
     */
    public static JSONObject fetchApiRequest(String request) {
        URL url;
        try {
            url = new URL(request);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Authorization", TOKEN);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", USER_AGENT);

            int responseCode = connection.getResponseCode();
            System.out.println("Response Code " + responseCode);

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            JSONTokener tokener = new JSONTokener(in);
            jsonResponse = new JSONObject(tokener);
            in.close();

        } catch (MalformedURLException e) {
            System.err.println("Malformed URL: " + e.getLocalizedMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("General IO Exception: " + e.getLocalizedMessage());
            e.printStackTrace();
        }
        return jsonResponse;
    }

    /**
     * Fetches and aggregates results from an API for a given category.
     * 
     * @param dataCategory URL subsection
     * @return JSONArray containing aggregated results.
     * @author AdiZen
     * @since 1.0
     * @last_modified 2024.01.10
     */

    public static JSONArray fetchDataPageFromCategory(String dataCategory, int pageIndex) {
        JSONArray results = new JSONArray();
        offset = pageIndex * limit;
        JSONObject apiResult = fetchApi(dataCategory);
        offset = 0;
        nextPageExists(apiResult);
        previousPageExists(apiResult);
        int c = getCountOfDataFromCategory(dataCategory);
        for (int i = 0; i < apiResult.getJSONArray("results").length(); i++) {
                results.put(apiResult.getJSONArray("results").getJSONObject(i));
        }
        return results;
    }

    /**
     * Fetches and aggregates results from an API for a given category and page index.
     * 
     * @param dataCategory URL subsection
     * @param pageIndex Index of the page to fetch
     * @return JSONArray containing aggregated results.
     * @author AdiZen
     * @since 1.0
     * @last_modified 2024.01.10
     */
    public static JSONArray fetchAllDataFromCategory(String dataCategory) {
        JSONArray results = new JSONArray();
        JSONObject apiResult = fetchApi(dataCategory);
        int c = getCountOfDataFromCategory(dataCategory);
        for (offset = 0; offset < c; offset += limit){
            for (int i = 0; i < apiResult.getJSONArray("results").length(); i++) { //
                results.put(apiResult.getJSONArray("results").getJSONObject(i));
            }
            apiResult = fetchApi(dataCategory);
        }
        offset = 0;
        return results;
    }

    /**
     * Gets the total count of data items in a given category.
     * 
     * @param dataCategory URL subsection
     * @return Total count of data items in the category.
     * @since 1.0
     * @last_modified 2024.01.10
     */
    public static int getCountOfDataFromCategory(String dataCategory){
        return fetchApi(dataCategory).getInt("count");
    }

    private static void previousPageExists (JSONObject apiResults){
        previousPageExists = !apiResults.isNull("previous");
    }

    private static void nextPageExists (JSONObject apiResults){
        nextPageExists = !apiResults.isNull("next");
    }

    public static boolean getNextPageExists(){
        return nextPageExists;
    }

    public static boolean getPreviousPageExists(){
        return previousPageExists;
    }
}

