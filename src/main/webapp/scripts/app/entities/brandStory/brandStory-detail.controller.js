'use strict';

angular.module('hongjieApp')
    .controller('BrandStoryDetailController', function ($scope, $rootScope, $stateParams, entity, BrandStory) {
        $scope.brandStory = entity;
        $scope.load = function (id) {
            BrandStory.get({id: id}, function(result) {
                $scope.brandStory = result;
            });
        };
        var unsubscribe = $rootScope.$on('hongjieApp:brandStoryUpdate', function(event, result) {
            $scope.brandStory = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
