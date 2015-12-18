'use strict';

angular.module('hongjieApp')
    .controller('SlideDetailController', function ($scope, $rootScope, $stateParams, entity, Slide, MenuPage) {
        $scope.slide = entity;
        $scope.load = function (id) {
            Slide.get({id: id}, function(result) {
                $scope.slide = result;
            });
        };
        var unsubscribe = $rootScope.$on('hongjieApp:slideUpdate', function(event, result) {
            $scope.slide = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
