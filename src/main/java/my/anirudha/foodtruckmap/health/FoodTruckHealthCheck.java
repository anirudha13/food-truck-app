package my.anirudha.foodtruckmap.health;

import com.codahale.metrics.health.HealthCheck;

/**
 * A Simple health check for the food truck mapper.
 *
 * @author anirudha
 */
public class FoodTruckHealthCheck extends HealthCheck {

    @Override
    protected Result check() throws Exception {
        return Result.healthy();
    }
}
