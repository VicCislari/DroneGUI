package de.frauas.muellerbady.java.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class HelloRest {

	private static final String USER_AGENT = "MOzilla FIrefox Awesome version";
	private static final String ENDPOINT_URL = "https://dronesim.facets-labs.com/api/drones/?format=json";
	private static final String TOKEN = "Token 1bbbbd05efe3c733efcf8f443582a09cac4ca02c";
	
	public static void main(String[] args) {
		System.out.println("Test started...");

		String jsonString = "{\"name\":\"John\",\"age\":30,\"city\":\"New York\"}";

		try {
            // Create ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();

            // Parse JSON text to JsonNode
            JsonNode jsonNode = objectMapper.readTree(jsonString);

            // Access values from JsonNode
            String name = jsonNode.get("name").asText();
            int age = jsonNode.get("age").asInt();
            String city = jsonNode.get("city").asText();

            // Print parsed values
            System.out.println("Name: " + name);
            System.out.println("Age: " + age);
            System.out.println("City: " + city);
        } catch (Exception e) {
            e.printStackTrace();
        }

		/* 
        URL url;
		try {
			url = new URL(ENDPOINT_URL);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestProperty("Authorization", TOKEN);
			connection.setRequestMethod("GET");
			connection.setRequestProperty("User-Agent", USER_AGENT);
			int responseCode = connection.getResponseCode();
		
			System.out.println("Response Code " + responseCode);
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			System.out.println(response.toString()); // Process your response
			test(response.toString());
		} catch (MalformedURLException e) {
			System.err.println("Malformed URL: " + e.getLocalizedMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("General IO Exception: " + e.getLocalizedMessage());
			e.printStackTrace();
		}
		*/
	}

	public static void test(String input) {
		JSONObject wholeFile = new JSONObject(input);
		JSONArray jsonFile = wholeFile.getJSONArray("results");
		for (int i = 0; i < jsonFile.length(); i++) {
			JSONObject o = jsonFile.getJSONObject(i);
			if(o.has("carriage_type") && o.has("carriage_weight")){
				String a = o.getString("carriage_type");
				int b = o.getInt("carriage_weight");
				int id = o.getInt("id");
				System.out.println("Drone " + id + ": carriage type " + a + " (weight: " + b + "g)");
			}
		}
	}

	public static String formatJson(String input) {
		final int indentSpaces = 4;
		Object json = new JSONTokener(input).nextValue();

		if (json instanceof JSONObject) {
			JSONObject o = (JSONObject) json;
			
			return o.toString(indentSpaces);
		} else if (json instanceof JSONArray) {
			return ((JSONArray) json).toString(indentSpaces);
		} else {
			throw new IllegalArgumentException("Input string is not a valid JSON");
		}
	}
}
