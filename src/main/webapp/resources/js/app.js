// MODULE
var smartApp = angular.module('smartApp', ['ngRoute', 'ngResource']);
// services
smartApp.service('cityService', function () {
    var self = this;
    self.city = '';
});
smartApp.service('weatherService', [
						'$resource'
						, function ($resource) {
        this.getWeather = function (city, days) {
            var weatherAPI = $resource("http://api.openweathermap.org/data/2.5/forecast/daily", {
                callback: "JSON_CALLBACK"
            }, {
                get: {
                    method: "JSONP"
                }
            });
            return weatherAPI.get({
                q: city
                , cnt: days
                , appid: '158fa86ca13f3cdc138ba8bf5aa1baea'
            });
        };
						}]);
// CONTROLLERS
smartApp.controller('homeController', ['$scope', '$location', '$log', '$http'
		, '$resource', 'cityService'
		, function ($scope, $location, $log, $http, $resource, cityService) {
        var self = this;
        self.location = null;

        /*function locationService() {
            $http.get("http://ip-api.com/json").success(function (data) {
                self.ipAddress = data
                    , self.location = data
                    , cityService.city = self.location.city;
            });
        }*/
       // locationService();
        $scope.submit = function () {
            $location.path("/forecast");
        };
		}]);
smartApp.controller('forecastController', [
		'$scope'
		, 'cityService'
		, '$resource'
		, '$log'
		, '$routeParams'
		, 'weatherService'
		, function ($scope, cityService, $resource, $log, $routeParams, weatherService) {
        $scope.city = cityService.city;
        $scope.days = $routeParams.days || '2';
        $scope.weatherResult = weatherService.getWeather($scope.city, $scope.days);
        $log.log($scope.weatherResult);
        $scope.convertToFahrenheit = function (degK) {
            return Math.round((1.8 * (degK - 273)) + 32);
        }
        $scope.convertToDate = function (dt) {
            return new Date(dt * 1000);
        }
		}]);
// Directives
smartApp.directive("weatherReport", function () {
    return {
        restrict: 'E'
        , templateUrl: 'resources/html/weatherReport.html'
        , replace: true
        , scope: {
            weatherDay: "=", // 2 way binding object
            convertToStandard: "&", // for a function
            convertToDate: "&"
            , dateFormat: "@" // for string
        }
    }
});