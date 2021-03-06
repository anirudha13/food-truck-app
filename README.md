# food-truck-app
==============

## A Simple Food Truck Map for SF Food trucks.
This is a simple web based application that maps all the Food Trucks in San Francisco and allows user's to search them by truck type or by food items served.
The data is fetched from [DataSF](http://www.datasf.org/): [Food
Trucks](https://data.sfgov.org/Permitting/Mobile-Food-Facility-Permit/rqzj-sfat).
The application uses the following technologies,
* [Dropwizard](http://dropwizard.io/), Java based technology that allows one to write REST servers quickly.
* [Bootstrap](http://getbootstrap.com/), A CSS framework for creating nice looking web site quickly.
* [AngularJS](https://angularjs.org/), "A SuperHeroic JavaScript framework"
* [Angular UI Bootstrap](http://angular-ui.github.io/bootstrap/), Bootstrap components written in pure AngularJS by the AngularUI Team
* [Angular Google Map](http://ngmap.github.io/), GoogleMap AngularJS Directive

The application is Demo'ed [here](https://fierce-escarpment-9671.herokuapp.com/foodtruckmap/app).

### Assumptions
* The map does not center itself based on user location but is hardcoded to center on SF.
** Reason being that food truck data is not available globally so there was no point to center this on any other location.
* The Food truck data is not updated frequently since the likelihood of this changing often is very low.
** Although I have a TODO below to periodically add the ability to the backend to refresh the data by making a call to the API endpoint.
* Food trucks with EXPIRED application status are not stored in the server since these do not have location information with them.

### TODOs
- [ ] Display information of the Food Trucks marked on the Map in a List view as well
- [ ] Add functionality for the backend to update the Food Truck Store periodically. Right now the server only initializes itself with all the Food Truck data upon startup due to the latency.
