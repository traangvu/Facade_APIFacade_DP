import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ApiFacade {

    public String getAttributeValueFromJson(String urlString, String attributePath) throws IllegalArgumentException, IOException {
        try {
            String json = getJsonFromApi(urlString);
            return extractNestedAttribute(json, attributePath);
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

    private String extractNestedAttribute(String json, String path) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(json);

        String[] keys = path.split("\\.");
        Object current = jsonObject;

        for (String key : keys) {
            if (!(current instanceof JSONObject)) {
                throw new IllegalArgumentException("Invalid path: " + path);
            }
            JSONObject obj = (JSONObject) current;
            if (!obj.containsKey(key)) {
                throw new IllegalArgumentException("Attribute not found: " + key);
            }
            current = obj.get(key);
        }

        return current != null ? current.toString() : null;
    }
}
