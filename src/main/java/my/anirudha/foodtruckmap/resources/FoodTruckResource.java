package my.anirudha.foodtruckmap.resources;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;

/**
 * A simple resource that calls out to the Web API for fetching Food Truck data.
 *
 * @author anirudha
 */
@Path("/locatetrucks")
@Produces(MediaType.APPLICATION_JSON)
public class FoodTruckResource {

    private static final Logger logger = LoggerFactory.getLogger(FoodTruckResource.class);
    public static final String API_ENDPOINT = "http://data.sfgov.org/resource/rqzj-sfat.json";

    private final HttpClient httpClient;

    public FoodTruckResource(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @GET
    @Timed
    public String locateFoodTrucks(@QueryParam("location") Optional<String> locationPos) {
        logger.debug("Got request for fetching food trucks for SF {} ... ", locationPos);
        StringWriter stringWriter = new StringWriter();
        HttpGet httpGet = new HttpGet(API_ENDPOINT);
        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            InputStream inputStream = httpResponse.getEntity().getContent();
            IOUtils.copy(inputStream, stringWriter);
            logger.debug("Got Status Code for Response {}", httpResponse.getStatusLine());
            logger.debug("Got Response from URL {}", httpResponse);
            //logger.debug("Got response string, {}", stringWriter.toString());
        } catch (IOException e) {
            logger.error("Exception while making API call.", e);
        }
        return stringWriter.toString();
    }
}
