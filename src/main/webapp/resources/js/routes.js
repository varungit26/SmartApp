//Routes
smartApp.config(function ($routeProvider) {
    
    $routeProvider
        
    .when('/', {
        templateUrl: 'resources/html/home.html',
        controller: 'homeController as home'
    })
    
    .when('/forecast', {
        templateUrl: 'resources/html/forecast.html',
        controller: 'forecastController'
    })
    
    .when('/forecast/:days', {
        templateUrl: 'resources/html/forecast.html',
        controller: 'forecastController'
    })
    
    .when('/loginSuccess', {
        templateUrl:'resources/html/mainPage1.html',
        controller:'mainFunction'
    })
    
    .when('/register', {
        templateUrl:'resources/html/register.html',
        controller:'UserController as ctrl'
    })
});