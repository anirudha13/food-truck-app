package my.anirudha.foodtruckmap;

import org.apache.http.client.HttpClient;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.client.HttpClientBuilder;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import my.anirudha.foodtruckmap.health.FoodTruckHealthCheck;
import my.anirudha.foodtruckmap.resources.FoodTruckResource;

public class FoodTruckApplication extends Application<FoodTruckConfiguration> {

    public static void main(final String[] args) throws Exception {
        new FoodTruckApplication().run(args);
    }

    @Override
    public String getName() {
        return "foodtruckmapper";
    }

    @Override
    public void initialize(final Bootstrap<FoodTruckConfiguration> bootstrap) {
        bootstrap.addBundle(new AssetsBundle("/assets/html", "/app", "index.htm", "static"));
        bootstrap.addBundle(new AssetsBundle("/assets/js", "/js", null, "js"));
        bootstrap.addBundle(new AssetsBundle("/assets/css", "/css", null, "css"));
        bootstrap.addBundle(new AssetsBundle("/assets/bootstrap", "/bootstrap", null, "bootstrap"));
        bootstrap.addBundle(new AssetsBundle("/assets/jquery", "/jquery", null, "jquery"));
        bootstrap.addBundle(new AssetsBundle("/assets/icons", "/icons", null, "icons"));
        bootstrap.addBundle(new AssetsBundle("/assets/marker", "/marker", null, "marker"));
        bootstrap.addBundle(new AssetsBundle("/assets/angularjs", "/angularjs", null, "angularjs"));
        bootstrap.addBundle(new AssetsBundle("/assets/ngmap", "/ngmap", null, "ngmap"));
    }

    @Override
    public void run(final FoodTruckConfiguration configuration,
                    final Environment environment) {
        final HttpClient httpClient = new HttpClientBuilder(environment).build(getName() + "client");
        final FoodTruckHealthCheck foodTruckHealthCheck = new FoodTruckHealthCheck();
        final FoodTruckResource foodTruckResource = new FoodTruckResource(httpClient);

        environment.healthChecks().register("foodTruckHealthCheck", foodTruckHealthCheck);
        environment.jersey().register(foodTruckResource);
    }

}
