/**
 * Created with IntelliJ IDEA.
 * User: anirudha
 */

var LOCATE_TRUCKS_ENDPOINT = "/foodtruckmapper/locatetrucks";

var foodTruckApp = angular.module('foodTruckApp', ['ngMap']);

foodTruckApp.controller('FoodTruckMapController', ['$scope', '$http', function($scope, $http){
    $scope.trucks = [];

    $http({method: 'GET', url: LOCATE_TRUCKS_ENDPOINT}).
        success(function (data, status, headers) {
            console.log("Successfully called locate trucks endpoint. Status :: " + status);
            console.debug("Headers received :: " + headers);
            if (data.hasOwnProperty('error')) {
                // create the error label on top of the page.
            } else {
                $scope.trucks = data;
            }
        }).error(function (data, status, headers) {
            console.log("Got error when calling locate trucks endpoint.");
            console.log("Status :: " + status);
            console.log("Headers :: " + headers);
            console.log("Data :: " + data);
            // create the error label on top of the page with the error message
        });
}]);
