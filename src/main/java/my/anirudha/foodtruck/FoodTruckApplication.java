package my.anirudha.foodtruck;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class FoodTruckApplication extends Application<FoodTruckConfiguration> {

    public static void main(final String[] args) throws Exception {
        new FoodTruckApplication().run(args);
    }

    @Override
    public String getName() {
        return "foodtruck";
    }

    @Override
    public void initialize(final Bootstrap<FoodTruckConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final FoodTruckConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
    }

}
