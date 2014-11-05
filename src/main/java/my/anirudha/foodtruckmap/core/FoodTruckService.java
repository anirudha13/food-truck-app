package my.anirudha.foodtruckmap.core;

import java.io.StringWriter;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.http.client.HttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import my.anirudha.foodtruckmap.api.FoodTruck;
import my.anirudha.foodtruckmap.client.FoodTruckRemoteClient;
import my.anirudha.foodtruckmap.db.FoodTruckStore;

/**
 * Class that is the single interface for fetching the FoodTruck data from the remote data sources and storing it locally.
 * This class decides whether to call the remote end point to refresh the data or to serve it from its local copy.
 *
 * @author anirudha
 */
public class FoodTruckService {

    private static final Logger logger = LoggerFactory.getLogger(FoodTruckService.class);

    private final FoodTruckStore foodTruckStore = new FoodTruckStore();
    private final FoodTruckRemoteClient foodTruckRemoteClient;

    public FoodTruckService(HttpClient httpClient) {
        this.foodTruckRemoteClient = new FoodTruckRemoteClient(httpClient);
    }

    public void init() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // fetch the data
        JsonNode rootNode = foodTruckRemoteClient.fetch();
        if (!rootNode.isArray()) {
            StringWriter writer = new StringWriter();
            objectMapper.writeValue(writer, rootNode);
            throw new Exception("Incorrect object received from remote client, " + writer.toString());
        }

        // parse each node
        ArrayNode arrayNode = (ArrayNode) rootNode;
        Iterator<JsonNode> elementItr = arrayNode.elements();
        Set<FoodTruck> foodTrucks = new LinkedHashSet<>();
        while (elementItr.hasNext()) {
            JsonNode node = elementItr.next();
            if (!node.isObject()) {
                logger.warn("Skipping json node of type {} expecting Object.", node.getNodeType());
                continue;
            }
            ObjectNode objectNode = (ObjectNode) node;
            FoodTruck foodTruck = objectMapper.convertValue(objectNode, FoodTruck.class);

            if (objectNode.has("fooditems")) {
                String foodItemStr = objectNode.get("fooditems").asText();
                String[] foodItems = StringUtils.splitByWholeSeparatorPreserveAllTokens(foodItemStr, ":");
                Set<String> foodItemVal = new LinkedHashSet<>();
                for (String fi : foodItems) {
                    fi = fi.trim();
                    foodItemVal.add(fi);
                }
                foodTruck.setFoodItems(foodItemVal);
            }

            foodTrucks.add(foodTruck);
        }

        // insert it into the store
        this.foodTruckStore.clearAndUpdate(foodTrucks);
    }

    public Collection<FoodTruck> fetchAll() {
        return this.foodTruckStore.fetchAll();
    }

    public Collection<String> getFacilityTypes() {
        return this.foodTruckStore.getFoodTruckFacilityTypes();
    }

    public Collection<String> getFoodItems() {
        return this.foodTruckStore.getAllFoodItems();
    }
}
