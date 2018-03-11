'use strict';

angular.module('myApp').factory('GenericFormService', ['$http', '$q', function ($http, $q) {

    var REST_SERVICE_URI = 'http://localhost:8888/form/objects/';

    var factory = {
        fetchAllObjs: fetchAllObjs,
        createObj: createObj,
        updateObj: updateObj,
        deleteObj: deleteObj
    };

    return factory;

    function fetchAllObjs() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function (errResponse) {
                    console.error('Error while fetching Objects');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function createObj(object) {
		console.log("createObj service ",object);
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, object)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function (errResponse) {
                    console.error('Error while creating Object');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function updateObj(object, id) {
        var deferred = $q.defer();
        $http.put(REST_SERVICE_URI + id, object)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function (errResponse) {
                    console.error('Error while updating Object');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function deleteObj(id) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI + id)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function (errResponse) {
                    console.error('Error while deleting Object');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

}]);