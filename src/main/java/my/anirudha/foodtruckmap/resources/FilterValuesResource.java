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
import my.anirudha.foodtruckmap.core.FoodTruckService;

/**
 * Resource that returns the parameters that can be used to filter the trucks by.
 *
 * @author anirudha
 */
@Path("/filterValues")
@Produces(MediaType.APPLICATION_JSON)
public class FilterValuesResource {

    private static final Logger logger = LoggerFactory.getLogger(FilterValuesResource.class);

    private final FoodTruckService foodTruckService;

    public FilterValuesResource(FoodTruckService foodTruckService) {
        this.foodTruckService = foodTruckService;
    }

    @GET
    @Path("/facilityTypes")
    @Timed
    public String facilityTypeValues() throws IOException {
        logger.debug("Fetching the supported facility types for Food Trucks");
        Collection<String> facilityTypes = this.foodTruckService.getFacilityTypes();

        ObjectMapper objectMapper = new ObjectMapper();
        StringWriter stringWriter = new StringWriter();
        objectMapper.writeValue(stringWriter, facilityTypes);
        logger.debug("Returning response :: {}", stringWriter.toString());
        return stringWriter.toString();
    }

    @GET
    @Path("/foodItems")
    @Timed
    public String foodItemValues() throws IOException {
        logger.debug("Fetching the supported facility types for Food Trucks");
        Collection<String> foodItems = this.foodTruckService.getFoodItems();

        ObjectMapper objectMapper = new ObjectMapper();
        StringWriter stringWriter = new StringWriter();
        objectMapper.writeValue(stringWriter, foodItems);
        logger.debug("Returning response :: {}", stringWriter.toString());
        return stringWriter.toString();
    }


}
