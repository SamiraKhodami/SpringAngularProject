'use strict';
angular.module('myApp', ['ngRoute']).
  config(['$locationProvider', function($locationProvider) {
    $locationProvider.html5Mode(true);
    $locationProvider.hashPrefix('!');
  }]).
  config(['$httpProvider', function($httpProvider) {
    // Accept: application/json
    $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
  }]).
  config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/menus/', {templateUrl: 'views/index.html'});
    $routeProvider.when('/menus/users', {templateUrl: 'views/user/addUsers.html',object:{id: null, userName: '', password: '' , firstName: '' , surname: ''}});
    $routeProvider.otherwise({redirectTo: 'menus/'});
  }]);
