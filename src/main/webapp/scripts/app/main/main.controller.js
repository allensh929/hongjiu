'use strict';

angular.module('hongjieApp')
    .controller('MainController', function ($scope, Principal) {
        Principal.identity().then(function(account) {
        	console.debug("come to main controller");
            $scope.account = account;
            $scope.isAuthenticated = Principal.isAuthenticated;
        });
    });

angular.module('hongjieApp')
.filter('trustHtml', function ($sce) {
    return function (input) {
        return $sce.trustAsHtml(input);
    }
});