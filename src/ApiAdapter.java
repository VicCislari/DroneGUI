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
    private static int lastCount;
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
     * Gets the total count of data items in a given category.
     * 
     * @param dataCategory URL subsection
     * @return Total count of data items in the category.
     * @since 1.0
     * @last_modified 2024.01.10
     */
    public static int getCountOfDataFromCategory(String dataCategory){return fetchApi(dataCategory).getInt("count");}

    private static void setPreviousPageExists (JSONObject apiResults){previousPageExists = !apiResults.isNull("previous");}

    private static void setNextPageExists (JSONObject apiResults){
        nextPageExists = !apiResults.isNull("next");
    }

    public static boolean getNextPageExists(){
        return nextPageExists;
    }

    public static boolean getPreviousPageExists(){
        return previousPageExists;
    }


    public static JSONArray fetchDataFromCategory(String dataCategory, int amount, int pageNr) {
        JSONArray results = new JSONArray();
        JSONObject apiResult;
        lastCount = getCountOfDataFromCategory(dataCategory);
        if(amount == 0 && pageNr == 0){ //fetch all elements inside category
            for (offset = 0; offset < lastCount; offset += limit){
                apiResult = fetchApi(dataCategory);
                results.putAll(addFetchedResultIntoNewList(apiResult));
            }
            offset = 0;
        } else if (amount > 0 && pageNr != 0){ //fetch page with amount of elements in it
            limit = amount;
            results = fetchDataFromCategoryPagewise(dataCategory, amount, pageNr);
        } else if (amount > 0){ //fetch elements with default limit = 10
            results = fetchDataFromCategoryPagewise(dataCategory, amount, pageNr);
        } else {
            System.out.println("Amount can't be negative!");
        }
        return results;
    }
    private static JSONArray addFetchedResultIntoNewList(JSONObject apiResult){
        JSONArray results = new JSONArray();
        for (int i = 0; i < apiResult.getJSONArray("results").length(); i++) {
            results.put(apiResult.getJSONArray("results").getJSONObject(i));
        }
        return results;
    }

    public static int getLastCount(){return lastCount;}

    private static JSONArray fetchDataFromCategoryPagewise(String dataCategory, int amount, int pageNr){
        JSONObject apiResult;
        JSONArray results;
        if (pageNr > 0){pageNr -= 1;}
        offset= (lastCount+(pageNr)*amount)%lastCount;
        apiResult = fetchApi(dataCategory);
        setNextPageExists(apiResult);
        setPreviousPageExists(apiResult);
        results = addFetchedResultIntoNewList(apiResult);
        offset = 0;
        limit = 10;
        return results;
    }

    public static JSONArray fetchDataFromCategoryOffsetwise(String dataCategory, int startIndex, int amount){
        JSONObject apiResult;
        JSONArray results;
        offset = startIndex;
        limit = amount;
        apiResult = fetchApi(dataCategory);
        setNextPageExists(apiResult);
        setPreviousPageExists(apiResult);
        results = addFetchedResultIntoNewList(apiResult);
        offset = 0;
        limit = 10;
        return results;
    }
}

