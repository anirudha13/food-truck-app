package my.anirudha.foodtruckmap.resources;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.databind.ObjectMapper;
import my.anirudha.foodtruckmap.api.FoodTruck;
import my.anirudha.foodtruckmap.core.FoodTruckService;

/**
 * A simple resource that calls out to the Web API for fetching Food Truck data.
 *
 * @author anirudha
 */
@Path("/trucks")
@Produces(MediaType.APPLICATION_JSON)
public class FoodTruckResource {

    private static final Logger logger = LoggerFactory.getLogger(FoodTruckResource.class);

    private final FoodTruckService foodTruckService;

    public FoodTruckResource(FoodTruckService foodTruckService) {
        this.foodTruckService = foodTruckService;
    }

    @GET
    @Path("/all")
    @Timed
    public String allTrucks() throws IOException {
        logger.debug("Fetching ALL food trucks in SF.");
        Collection<FoodTruck> allFoodTrucks = foodTruckService.fetchAll();
        ObjectMapper objectMapper = new ObjectMapper();
        StringWriter stringWriter = new StringWriter();
        objectMapper.writeValue(stringWriter, allFoodTrucks);
        return stringWriter.toString();
    }
}
