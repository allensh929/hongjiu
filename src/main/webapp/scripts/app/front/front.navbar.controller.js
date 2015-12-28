'use strict';

angular.module('hongjieApp')
    .controller('FrontNavbarController', function ($scope, $location, $state, ENV) {
      
    	console.debug("FrontNavbarController");
        $scope.$state = $state;
        $scope.inProduction = ENV === 'prod';

    });
