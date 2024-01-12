import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
TODO:
1. alle Dronen bekommen und in Dronen Objekte konvertieren
    a) die Links erstellen für alle Dronen
        Prio 1: https://dronesim.facets-labs.com/api/drones/?format=json&limit=30&offset=10
        Prio 3: additionally: zukunftsorienteirt machen. Was ist wenn mehr Dronen hinzugefügt werden
        -> extra Tools.java Datei, Funktion  - String getAll() - gibt ein String zurück in JSON Format
        -> Json String muss bearbeitet werden, extra Funktion, 
        -> schauen ob ApiAdapter das eigentlich schon erfüllt.
2. Globalising the main variables
3. Frontend... you are welcome to add some comments
4. Backend... same here
5. Diagrams
    a) Flowchart
    b) Data Chart
    c) Class diagram
*/

public class Main {
    private static final String USER_AGENT = "MOzilla FIrefox Awesome version";
    //private static final String ENDPOINT_URL = "https://dronesim.facets-labs.com/api/drones/?format=json";
    private static final String ENDPOINT_URL = "https://dronesim.facets-labs.com/api/drones/?format=json&limit=10&offset=10";
    //private static final String ENDPOINT_URL = "http://dronesim.facets-labs.com/api/drones/?format=json&limit=20&offset=40";
    private static final String TOKEN = "Token 1bbbbd05efe3c733efcf8f443582a09cac4ca02c";

    /*
     * {
    "drones": "http://dronesim.facets-labs.com/api/drones/",
    "dronedynamics": "http://dronesim.facets-labs.com/api/dronedynamics/",
    "dronetypes": "http://dronesim.facets-labs.com/api/dronetypes/"
    }
    */

    public static void main(String[] args) {
      ApiAdapter.api_results("drones");
      //runTest();
      //DroneList.rundummy();
    }

    /**
     * Runs a test by fetching data from an API and processing the response.
     * @author Mueller-Bady
     * @since 1.0
     * @last_modified original
     * Note:
     * - this function's code is extracted from HelloRest.java
     * - Link: https://campuas.frankfurt-university.de/pluginfile.php/422675/mod_resource/content/1/HelloRest.java
     */
    public static void runTest(){
        System.out.println("Test started. -------------");
        System.out.println("hallo");

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
    }

    /** Outputs a JSON Object from a JSON String.
     * @param input A String in JSON Format;
     * @author Mueller-Bady
     * @since 1.0
     * @last_modified original
     */
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

    /** Formats a JSON string with specified indentation.
     * @param input A string in JSON format.
     * @return Formatted JSON string.
     * @author Mueller-Bady
     * @since original
     * @last_modified original
     */
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