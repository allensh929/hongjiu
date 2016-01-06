'use strict';

angular.module('hongjieApp')
    .controller('GiftDetailController', function ($scope, $rootScope, $stateParams, entity, Gift) {
        $scope.gift = entity;
        $scope.load = function (id) {
            Gift.get({id: id}, function(result) {
                $scope.gift = result;
            });
        };
        var unsubscribe = $rootScope.$on('hongjieApp:giftUpdate', function(event, result) {
            $scope.gift = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
