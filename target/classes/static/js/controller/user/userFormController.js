'use strict';

angular.module('myApp').controller('UserFormController', ['$scope' , 'UserFormService','$controller', function ($scope, UserFormService, $controller) {

	var child = this;
	child.userdto = {userName: '', firstName: ''};
	child.par = $controller('GenericFormController', {$scope: $scope});

    child.searchUsers = searchUsers;
    child.resetSearch = resetSearch;	
	
    function searchUsers() {
		console.log('searchUsers ', child.userdto);
        UserFormService.searchUsers(child.userdto)
            .then(
                function (d) {
                    child.par.objs = d;
                },
                function (errResponse) {
                    console.error('Error while searching users');
                }
            );
		resetSearch();	
    }
	
    function resetSearch() {
        child.userdto = {userName: '', firstName: ''};
		console.log('reset', child.userdto);
    }	
}]);