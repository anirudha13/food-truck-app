package my.anirudha.foodtruckmap;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.client.HttpClientConfiguration;

public class FoodTruckConfiguration extends Configuration {

    @Valid
    @NotNull
    @JsonProperty
    private HttpClientConfiguration httpClient = new HttpClientConfiguration();

    @Valid
    @NotNull
    @JsonProperty("serviceURI")
    private String serviceURI;

    public HttpClientConfiguration getHttpClientConfiguration() {
        return httpClient;
    }

    public String getServiceURI() {
        return this.serviceURI;
    }
}
