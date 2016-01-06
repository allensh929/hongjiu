'use strict';

angular.module('hongjieApp')
	.controller('GiftDeleteController', function($scope, $modalInstance, entity, Gift) {

        $scope.gift = entity;
        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            Gift.delete({id: id},
                function () {
                    $modalInstance.close(true);
                });
        };

    });