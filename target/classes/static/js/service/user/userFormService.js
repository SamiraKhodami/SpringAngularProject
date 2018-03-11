'use strict';

angular.module('myApp').factory('UserFormService', ['$http', '$q', function ($http, $q) {

    var REST_SERVICE_URI = 'http://localhost:8888/form/users/';

    var factory = {
        searchUsers: searchUsers
    };

    return factory;
	
    function searchUsers(object) {
		console.log("searchUsers service ", object);
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, object)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function (errResponse) {
                    console.error('Error while search Users');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }
}]);