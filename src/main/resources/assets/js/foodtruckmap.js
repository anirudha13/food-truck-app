
var myApp = angular.module('foodTruckApp', ['ngMap']);

$(document).ready(function() {
    console.log("Document is Ready now let's make a call out to Food Truck API.");
    fetchFoodTruckData();
});

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
