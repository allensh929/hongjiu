'use strict';

angular.module('hongjieApp')
    .controller('FrontNavbarController', function ($scope, $location, $state, ENV) {
      
        $scope.$state = $state;
        $scope.inProduction = ENV === 'prod';

    });
