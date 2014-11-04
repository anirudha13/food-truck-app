package my.anirudha.foodtruckmap.api;

import java.util.Set;

/**
 * POJO for representing the food truck information received from the remote API.
 *
 * @author anirudha
 */
public class FoodTruck implements Comparable<FoodTruck>{

    private String applicant;

    private ApplicationStatusEnum status;

    private String facilitytype;

    private String locationDescription;

    private String address;

    private String permit;

    private String latitude;

    private String longitude;

    private String scheduleUri;

    private Set<String> foodItems;

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public ApplicationStatusEnum getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatusEnum applicationStatus) {
        this.status = applicationStatus;
    }

    public String getFacilitytype() {
        return this.facilitytype;
    }

    public void setFacilityType(String facilityType) {
        this.facilitytype = facilityType;
    }

    public String getLocationDescription() {
        return locationDescription;
    }

    public void setLocationDescription(String locationDescription) {
        this.locationDescription = locationDescription;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPermit() {
        return permit;
    }

    public void setPermit(String permit) {
        this.permit = permit;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getScheduleUri() {
        return scheduleUri;
    }

    public void setScheduleUri(String scheduleUri) {
        this.scheduleUri = scheduleUri;
    }

    public Set<String> getFoodItems() {
        return foodItems;
    }

    public void setFoodItems(Set<String> foodItems) {
        this.foodItems = foodItems;
    }

    @Override
    public int compareTo(FoodTruck o) {
        return applicant.compareTo(o.getApplicant());
    }
}
