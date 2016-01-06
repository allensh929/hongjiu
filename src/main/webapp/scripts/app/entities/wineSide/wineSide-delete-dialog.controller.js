'use strict';

angular.module('hongjieApp')
	.controller('WineSideDeleteController', function($scope, $modalInstance, entity, WineSide) {

        $scope.wineSide = entity;
        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            WineSide.delete({id: id},
                function () {
                    $modalInstance.close(true);
                });
        };

    });