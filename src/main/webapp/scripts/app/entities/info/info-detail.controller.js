'use strict';

angular.module('hongjieApp')
    .controller('InfoDetailController', function ($scope, $rootScope, $stateParams, entity, Info) {
        $scope.info = entity;
        $scope.load = function (id) {
            Info.get({id: id}, function(result) {
                $scope.info = result;
            });
        };
        var unsubscribe = $rootScope.$on('hongjieApp:infoUpdate', function(event, result) {
            $scope.info = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
