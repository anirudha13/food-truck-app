package my.anirudha.foodtruckmap.health;

import com.codahale.metrics.health.HealthCheck;

/**
 * Created with IntelliJ IDEA.
 * User: anirudha
 * Date: 03/11/14
 * Time: 4:27 PM
 *
 * @author anirudha
 */
public class FoodTruckHealthCheck extends HealthCheck {

    @Override
    protected Result check() throws Exception {
        return Result.healthy();
    }
}
