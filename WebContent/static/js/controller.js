app.controller('LoginController', function($scope, $http, $log, $window) {
	$scope.login=function(){
	    $http({
	        method: 'GET',
	        url: '/springDemo/login',
	        params:{
	        	username: $scope.username,
	        	password: $scope.password
	        }
	    }).then(function successCallback(response) {
	    	window.location.href="/springDemo/main"
	      }, function errorCallback(response) {
	    	  window.location.href="/springDemo/error"
	      });
	}
});
app.controller('EmployeeController', function($scope, $http, $log, $window) {
    var self = this;     
    self.employees=[];
    $http({
        method: 'GET',
        url: '/springDemo/list',
        params:{
        	username: $scope.username,
        	password: $scope.password
        }
    }).then(function (result) {
    	self.employees=result.data;
    	console.log(result.data);
    	console.log("list");
        console.log(result);
    });
});