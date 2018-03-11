'use strict';

angular.module('myApp').controller('GenericFormController', ['$scope' ,'$route', 'GenericFormService', function ($scope, $route, GenericFormService) {
    var self = this;
    const origObject = $route.current.$$route.object;
	self.obj = angular.copy(origObject);
    self.objs = [];

    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;

    fetchAllObjs();

    function fetchAllObjs() {
        GenericFormService.fetchAllObjs()
            .then(
                function (d) {
                    self.objs = d;
                },
                function (errResponse) {
                    console.error('Error while fetching Objects');
                }
            );
    }

    function createObj(obj) {
		console.log('Create Object', self.obj);
        GenericFormService.createObj(obj)
            .then(
                fetchAllObjs,
                function (errResponse) {
                    console.error('Error while creating Object');
                }
            );
		$scope.showModal = false;	
    }

    function updateObj(obj, id) {
        GenericFormService.updateObj(obj, id)
            .then(
                fetchAllObjs,
                function (errResponse) {
                    console.error('Error while updating Object');
                }
            );
		$scope.showModal = false;	
    }

    function deleteObj(id) {
        GenericFormService.deleteObj(id)
            .then(
                fetchAllObjs,
                function (errResponse) {
                    console.error('Error while deleting Object');
                }
            );
    }

    function submit() {
        if (self.obj.id === null) {
            console.log('Saving New Object', self.obj);
            createObj(self.obj);
        } else {
            updateObj(self.obj, self.obj.id);
            console.log('Object updated with id ', self.obj.id);
        }
        reset();
    }

    function edit(id) {
        console.log('id to be edited', id);
		console.log("bb "+$scope.showModal);
		$scope.showModal = true;
		console.log("aa "+$scope.showModal);
        for (var i = 0; i < self.objs.length; i++) {
            if (self.objs[i].id === id) {
                self.obj = angular.copy(self.objs[i]);
                break;
            }
        }
    }

    function remove(id) {
        console.log('id to be deleted', id);
        if (self.obj.id === id) {
            reset();
        }
        deleteObj(id);
    }

    function reset() {
        self.obj = angular.copy(origObject);
		console.log('reset', self.obj);
    }
}]);