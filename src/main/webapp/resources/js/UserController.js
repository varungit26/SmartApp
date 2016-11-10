'use strict'

angular.module('SmartApp').controller('UserController',['$scope','$log','UserService', function ($scope,$log,UserService){
    
    var self = this;
    
    self.user = {
        id:null,
        username:'',
        firstname:'',
        lastname:'',
        address:'',
        email:''
    };
    
    self.users= [];
    
    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;
    
    fetchAllUsers();
    
    function fetchAllUsers(){
        UserService.fetchAllUsers().then(
            function(data){
                self.users = data;
            },
            function(errResponse){
                $log.error('Error while fetching users');
            }
        );
    }
    
    function createUser(user){
        UserService.createUser(user).then(
            function(errResponse){
                $log.error('Error creating user');
            },
            
            fetchAllUsers
        );
    }
    
    function updateUser(user,id){
        UserService.updateUser(user,id).then(
            fetchAllUsers,
            function(errResponse){
                $log.error('Error while updating user');
            }
        );
    }
    
    function deleteUser(){
        UserService.deleteUser(id).then(
            fetchAllUsers,
            function (errResponse){
                $log.error('Error while deleting user');
            }
        );
    }
    
    function submit(){
        if(self.user.id == null){
            $log.log('Saving New User', self.user);
            createUser(self.user);
        }else{
            updateUser(self.user,self.user.id);
            $log.log('Updating user with id',self.user.id);
        }
        reset();
    }
    
    function edit(id){
        
        $log.log('Editing id',id);
        for(var i =0;i<self.users.length;i++){
            if(self.users[i].id === id){
                self.user = angular.copy(self.users[i]);
                break;
            }
        }
    }
    
    function remove(id){
        $log.log('Deleting user id');
        if(self.user.id === id){
            reset();
        }
        deleteUser(id);
    }
    
    function reset(){
        self.user = {
            id:null,
            username:'',
            firstname:'',
            lastname:'',
            address:'',
            email:''
        };
        $scope.myForm.$setPristine();
    }
}]);