package my.anirudha.foodtruckmap.client;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * A service class that provides the simple interface between the resource and the External Data
 * Service that we call for the food truck data.
 *
 * @author anirudha
 */
public class FoodTruckRemoteClient {

    private static final Logger logger = LoggerFactory.getLogger(FoodTruckRemoteClient.class);
    private static final String  API_URL = "http://data.sfgov.org/resource/rqzj-sfat.json";

    private final HttpClient httpClient;

    public FoodTruckRemoteClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public JsonNode fetch() throws Exception {
        JsonNode jsonNode = null;
        HttpGet httpGet = new HttpGet(API_URL);
        try {
            HttpResponse httpResponse = this.httpClient.execute(httpGet);
            StatusLine statusLine = httpResponse.getStatusLine();
            if (statusLine.getStatusCode() != 200) {
                return errorJson(statusLine.getReasonPhrase());
            }
            InputStream inputStream = httpResponse.getEntity().getContent();
            ObjectMapper objectMapper = new ObjectMapper();
            jsonNode = objectMapper.readTree(inputStream);
        } catch (IOException e) {
            logger.error("Unable to execute the http request to the endpoint, {}", API_URL, e);
            jsonNode = errorJson(e.getMessage());
        }
        return jsonNode;
    }

    private JsonNode errorJson(String reason) {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("error", true);
        objectNode.put("reason", reason);
        return objectNode;
    }

}
