/**
 * Created with IntelliJ IDEA.
 * User: anirudha
 */

var ALL_TRUCKS_ENDPOINT = "/foodtruckmap/trucks/all";
var FACILITY_TYPE_VALUES_ENDPOINT = "/foodtruckmap/filterValues/facilityTypes";
var FOOD_ITEMS_VALUES_ENDPOINT = "/foodtruckmap/filterValues/foodItems";

angular.module('facilityTypeModule', [])
    .filter('facilityTypeFilter', function () {
        return function (trucks, facilityType) {
            if (facilityType == null || facilityType === "") {
                console.log("No Facility Type entered for filtering " + facilityType);
                return trucks;
            }

            var filteredTrucks = [];
            var numTrucks = trucks.length;
            console.log("Facility Type :: " + facilityType);
            for (var i = 0; i < numTrucks; i++) {
                var truck = trucks[i];
                if (truck.facilitytype === facilityType) {
                    filteredTrucks.push(truck);
                }
            }
            return filteredTrucks;
        }
    });

angular.module('foodItemModule', [])
    .filter('foodItemFilter', function () {
        return function (trucks, foodItemText) {
            if (foodItemText == null || foodItemText === "") {
                console.log("No foodItem Text entered for filtering " + foodItemText);
                return trucks;
            }

            var numTrucks = trucks.length;
            console.log("Filtering on " + length + " number of trucks");
            console.log("Facility Type :: " + foodItemText);

            var filteredTrucks = [];
            for (var i= 0; i < numTrucks; i++) {
                var truck = trucks[i];
                if (!truck.hasOwnProperty('foodItems')) {
                    continue;
                }
                console.log("Truck name :: " + truck.applicant);
                var foodItems = truck.foodItems;
                console.log("Food Items :: " + foodItems);
                for (var j = 0; j < foodItems.length; j++) {
                    var fi = foodItems[j];
                    var res = fi.match(foodItemText);
                    if (res != null && res !== "") {
                        filteredTrucks.push(truck);
                    }
                }
            }
            return filteredTrucks;
        }
    });

var foodTruckApp = angular.module('foodTruckApp', ['ui.bootstrap', 'ngMap', 'foodItemModule', 'facilityTypeModule']);

foodTruckApp.controller('FoodTruckMapController', ['$scope', '$http', function($scope, $http){
    $scope.trucks = [];
    $scope.facilityTypeValues = [];
    $scope.foodItemValues = [];


    $http({method: 'GET', url: ALL_TRUCKS_ENDPOINT}).
        success(function (data, status, headers) {
            console.log("Successfully called locate trucks endpoint. Status :: " + status);
            if (data.hasOwnProperty('error')) {
                // create the error label on top of the page.
            } else {
                $scope.trucks = data;
                console.log("Recevied data of " + $scope.trucks.length + " trucks.");
            }
        }).error(function (data, status, headers) {
            console.log("Got error when calling locate trucks endpoint.");
            console.log("Status :: " + status);
            console.log("Headers :: " + headers);
            console.log("Data :: " + data);
            // create the error label on top of the page with the error message
        });

    $http({method: 'GET', url: FACILITY_TYPE_VALUES_ENDPOINT}).
        success(function (data, status, headers) {
            console.log("Successfully called locate trucks endpoint. Status :: " + status);
            if (data.hasOwnProperty('error')) {
                // create the error label on top of the page.
            } else {
                $scope.facilityTypeValues = data;
                console.log("Recevied data of " + $scope.facilityTypeValues.length + " trucks.");
            }
        }).error(function (data, status, headers) {
            console.log("Got error when calling locate trucks endpoint.");
            console.log("Status :: " + status);
            console.log("Headers :: " + headers);
            console.log("Data :: " + data);
            // create the error label on top of the page with the error message
        });

    $http({method: 'GET', url: FOOD_ITEMS_VALUES_ENDPOINT}).
        success(function (data, status, headers) {
            console.log("Successfully called locate trucks endpoint. Status :: " + status);
            if (data.hasOwnProperty('error')) {
                // create the error label on top of the page.
            } else {
                $scope.foodItemValues = data;
                console.log("Recevied data of " + $scope.foodItemValues.length + " trucks.");
            }
        }).error(function (data, status, headers) {
            console.log("Got error when calling locate trucks endpoint.");
            console.log("Status :: " + status);
            console.log("Headers :: " + headers);
            console.log("Data :: " + data);
            // create the error label on top of the page with the error message
        });

}]);
