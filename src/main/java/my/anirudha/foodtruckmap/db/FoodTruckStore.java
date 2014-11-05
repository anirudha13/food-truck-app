package my.anirudha.foodtruckmap.db;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.commons.lang.StringUtils;

import com.google.common.collect.Multimap;
import com.google.common.collect.TreeMultimap;
import my.anirudha.foodtruckmap.api.ApplicationStatusEnum;
import my.anirudha.foodtruckmap.api.FoodTruck;

/**
 * Class that stores the FoodTruck in memory to ease querying the data for display.
 *
 * @author anirudha
 */
public class FoodTruckStore {

    private Map<String, FoodTruck> foodTrucksByName = new LinkedHashMap<>();
    private Multimap<String, FoodTruck> foodTrucksByFoodItem = TreeMultimap.create();
    private Set<String> foodTruckFacilityTypes = new LinkedHashSet<>();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private Date lastUpdated;

    /**
     * Clear the internal state so that the data can be refreshed when needed.
     */
    public void clear() {
        readWriteLock.writeLock().lock();
        foodTrucksByFoodItem.clear();
        foodTrucksByName.clear();
        foodTruckFacilityTypes.clear();
        lastUpdated = null;
        readWriteLock.writeLock().unlock();
    }

    public FoodTruck findByName(String foodTruckName) {
        try {
            readWriteLock.readLock().lock();
            FoodTruck foodTruck = this.foodTrucksByName.get(foodTruckName);
            return foodTruck;
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public Collection<FoodTruck> findByFoodItem(String foodItem) {
        try {
            readWriteLock.readLock().lock();
            return this.foodTrucksByFoodItem.get(foodItem);
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public Collection<String> getAllFoodItems() {
        try {
            readWriteLock.readLock().lock();
            return this.foodTrucksByFoodItem.keySet();
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public Collection<String> getAllNames() {
        try {
            readWriteLock.readLock().lock();
            return this.foodTrucksByName.keySet();
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public Set<String> getFoodTruckFacilityTypes() {
        return this.foodTruckFacilityTypes;
    }
    
    public Collection<FoodTruck> fetchAll() {
        try {
            readWriteLock.readLock().lock();
            return this.foodTrucksByName.values();
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public Date getLastUpdated() {
        return this.lastUpdated;
    }

    public void clearAndUpdate(Collection<FoodTruck> foodTrucks) {
        try {
            readWriteLock.writeLock().lock();
            clear();
            for (FoodTruck foodTruck : foodTrucks) {
                insertSingle(foodTruck);
            }
            this.lastUpdated = new Date();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    private void insertSingle(FoodTruck foodTruck) {

        if (foodTruck.getStatus() == ApplicationStatusEnum.EXPIRED) {
            //skip all expired food trucks
            return;
        }

        // insert the food truck into the name map
        this.foodTrucksByName.put(foodTruck.getApplicant(), foodTruck);

        // insert the food truck facility type into the facility type set
        if (StringUtils.isNotEmpty(foodTruck.getFacilitytype())) {
            this.foodTruckFacilityTypes.add(foodTruck.getFacilitytype());
        }

        // insert the food truck into the food item map
        Set<String> foodItems = foodTruck.getFoodItems();
        if (foodItems != null) {
            for (String foodItem : foodItems) {
                String trimmedFoodItem = foodItem.trim();
                if (!trimmedFoodItem.isEmpty()) {
                    this.foodTrucksByFoodItem.put(trimmedFoodItem, foodTruck);
                }
            }
        }
    }

}
