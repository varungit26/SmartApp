'use strict'

angular.module('SmartApp').factory('UserService',['$http','$q','$log',function($http,$q,$log){
    
    var USER_SERVICE_URI = 'http://localhost:8080/SmartApp/user/';
    
    var factory = {
        fetchAllUsers : fetchAllUsers,
        createUser: createUser,
        updateUser: updateUser,
        deleteUser:deleteUser
    };
    
    return factory;
    
    function fetchAllUsers(){
        
        var deferred = $q.defer();
        $http.get(USER_SERVICE_URI)
            .then(
            function(response){
                deferred.resolve(response.data);
            },
            function(errResponse){
                $log.error('Error while fetching Users');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function createUser(user){
        
        var deferred = $q.defer();
        $http.post(USER_SERVICE_URI,user)
        .then(
            function(response){
              deferred.resolve(response.data);  
            },
            function(errResponse){
                $log.error('Error while creating user');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function updateUser(user,id){
        var deferred = $q.defer();
        
        $http.put(USER_SERVICE_URI).then(
            function(response){
                deferred.resolve(response.data);
            },
            function(errResponse){
                $log.error('Error while updating user');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function deleteUser(id){
        var deferred = $q.defer();
        
        $http.delete(id).then(
            function(response){
                deferred.resolve(response.data);
            },
            function(errResponse){
                $log.error('Error while deleting user');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
}]);