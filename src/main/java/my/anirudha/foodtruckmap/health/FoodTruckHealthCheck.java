package my.anirudha.foodtruckmap.health;

import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpHead;

import com.codahale.metrics.health.HealthCheck;

/**
 * A Simple health check for the food truck mapper.
 *
 * @author anirudha
 */
public class FoodTruckHealthCheck extends HealthCheck {

    private final HttpClient httpClient;
    private final URI serviceURI;

    public FoodTruckHealthCheck(HttpClient httpClient, String serviceURI) {
        this.httpClient = httpClient;
        this.serviceURI = URI.create(serviceURI);
    }

    @Override
    protected Result check() throws Exception {
        HttpHead method = new HttpHead(serviceURI);
        HttpResponse httpResponse = httpClient.execute(method);
        StatusLine statusLine = httpResponse.getStatusLine();
        if (statusLine.getStatusCode() != HttpStatus.SC_OK) {
            return Result.unhealthy("Service URI %s not reachable got status :: %d %s", serviceURI, statusLine.getStatusCode(), statusLine.getReasonPhrase());
        }
        return Result.healthy("Service URI is healthy got status :: %d %s", statusLine.getStatusCode(), statusLine.getReasonPhrase());
    }
}
