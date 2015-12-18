'use strict';

angular.module('hongjieApp')
    .controller('MenuPageDetailController', function ($scope, $rootScope, $stateParams, entity, MenuPage, Slide) {
        $scope.menuPage = entity;
        $scope.load = function (id) {
            MenuPage.get({id: id}, function(result) {
                $scope.menuPage = result;
            });
        };
        var unsubscribe = $rootScope.$on('hongjieApp:menuPageUpdate', function(event, result) {
            $scope.menuPage = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
