'use strict';

angular.module('hongjieApp')
    .controller('WineSideDetailController', function ($scope, $rootScope, $stateParams, entity, WineSide) {
        $scope.wineSide = entity;
        $scope.load = function (id) {
            WineSide.get({id: id}, function(result) {
                $scope.wineSide = result;
            });
        };
        var unsubscribe = $rootScope.$on('hongjieApp:wineSideUpdate', function(event, result) {
            $scope.wineSide = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
