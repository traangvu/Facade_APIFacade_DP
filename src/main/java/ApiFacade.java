import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ApiFacade {

    public String getAttributeValueFromJson(String urlString, String attributeName) throws IllegalArgumentException, IOException {
        try {
            String json = getJsonFromApi(urlString);
            return extractAttributeFromJson(json, attributeName);
        } catch (MalformedURLException e) {
            throw new IOException("Invalid URL: " + e.getMessage(), e);
        } catch (ParseException e) {
            throw new IOException("Failed to parse JSON: " + e.getMessage(), e);
        }
    }

    private String getJsonFromApi(String apiUrl) throws IOException {
        URL url = new URL(apiUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            return content.toString();
        } finally {
            con.disconnect();
        }
    }

    private String extractAttributeFromJson(String json, String attributeName) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(json);

        if (!jsonObject.containsKey(attributeName)) {
            throw new IllegalArgumentException("Attribute not found in JSON: " + attributeName);
        }

        Object value = jsonObject.get(attributeName);
        return value != null ? value.toString() : null;
    }
}
