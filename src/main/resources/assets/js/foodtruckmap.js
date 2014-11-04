
var LOCATE_TRUCKS_ENDPOINT = "/foodtruckmapper/locatetrucks";

var myApp = angular.module('foodTruckApp', ['ngMap']);

function fetchFoodTruckData() {
    $.ajax({
        type: "GET",
        processData: false,
        contentType: "application/json; charset=UTF-8",
        url: "/foodtruckmapper/locatetrucks?location={}"
    }).done(function(data) {
            console.log("Success DATA " + data);
        }).fail(function(data) {
            console.log("Fail Data " + data);
        }).always(function(data) {
            console.log("ALways Data " + data);
        }
    );
}
