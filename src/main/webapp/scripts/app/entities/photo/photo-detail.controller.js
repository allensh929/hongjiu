'use strict';

angular.module('hongjieApp')
    .controller('PhotoDetailController', function ($scope, $rootScope, $stateParams, entity, Photo) {
        $scope.photo = entity;
        $scope.load = function (id) {
            Photo.get({id: id}, function(result) {
                $scope.photo = result;
            });
        };
        var unsubscribe = $rootScope.$on('hongjieApp:photoUpdate', function(event, result) {
            $scope.photo = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
