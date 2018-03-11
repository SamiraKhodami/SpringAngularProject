angular.module('myApp').directive('searchPanel', function () {
	return{
		scope: {
			headertitle: '@'
		},
		restrict: 'E',
		transclude: 'true',
		replace: 'true',
		template: '<div class="panel panel-default">'+
        '<div class="panel-heading"><span class="lead">{{headertitle}}</span></div>'+
		'<div class="searchcontainer">'+
        '<div ng-transclude></div>'+
        '</div></div>'
	};
}).directive('inputText', function () {
    return {
        scope: {
			labelvalue: '@',
			id: '@',
			required: '@?',
			minlength: '@?',
			maxlength: '@?',
			pattern: '@?',
			changefunction: '&?',
            inputvalue: '='
        },
		restrict: 'EA',
		template: '<label class="col-md-2 control-lable text-right" for="{{id}}">{{labelvalue}}:</label>'+
                  '<div class="col-md-4">'+
                  '<input type="text" ng-model="inputvalue" id="{{id}}" name="{{id}}" class="form-control input-sm" '+
				  'ng-required="{{required}}" '+
				  'ng-minlength="{{minlength}}" '+
				  'ng-maxlength="{{maxlength}}" '+
				  'ng-pattern="{{pattern}}" '+
				  'ng-change="changefunction()" />'+
                  '</div>'

    };
}).directive('inputPassword', function () {
    return {
        scope: {
			labelvalue: '@',
			id: '@',
			required: '@?',
			minlength: '@?',
            inputvalue: '='
        },
		restrict: 'EA',
		template: '<label class="col-md-2 control-lable text-right" for="{{id}}">{{labelvalue}}:</label>'+
                  '<div class="col-md-4">'+
                  '<input type="password" ng-model="inputvalue" id="{{id}}" name="{{id}}" class="form-control input-sm" '+
				  'ng-required={{required}} '+
				  'ng-minlength="{{minlength}}"/>'+
                  '</div>'

    };
}).directive('inputNumber', function () {
    return {
        scope: {
			labelvalue: '@',
			id: '@',
			required: '@?',
			minlength: '@?',
            inputvalue: '='
        },
		restrict: 'EA',
		template: '<label class="col-md-2 control-lable text-right" for="{{id}}">{{labelvalue}}:</label>'+
                  '<div class="col-md-4">'+
                  '<input type="number" ng-model="inputvalue" id="{{id}}" name="{{id}}" class="form-control input-sm" '+
				  'ng-required={{required}} ng-minlength="{{minlength}}"/>'+
                  '</div>'

    };
}).directive('submitAndResetButtonPanel', function () {
	return {
		scope: {
			submitvalue: '@',
			submitdisabled: '=',
			resetfunction: '&',
			resetdisabled: '='
		},
		restrict: 'EA',
		template: '<div class="form-actions">'+
                  '<input type="submit" value="{{submitvalue}}" class="btn btn-primary btn-sm btn-space" '+
                  ' ng-disabled="submitdisabled" >'+
                  '<button type="button" ng-click="resetfunction()" class="btn btn-warning btn-sm"'+
                  ' ng-disabled="resetdisabled">Reset Form'+
                  '</button>'+
                  '</div>'
	};
}).directive('resultPanel', function () {
	return {
		scope: {
			headertitle: '@'
		},
		restrict: 'E',
		transclude: 'true',
		replace: 'true',
		template: '<div class="panel panel-default">'+
        '<div class="panel-heading"><span class="lead">{{headertitle}}</span></div>'+
        '<div class="tablecontainer">'+
		'<div ng-transclude></div>'+
        '</div></div>'
	}
}).directive('showModal', function() {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            scope.$watch(attrs.showModal, function(value) {
                if (value) element.modal('show');
                else element.modal('hide');
            });
        }
    };
});