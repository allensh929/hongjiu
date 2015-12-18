'use strict';

angular.module('hongjieApp')
	.controller('MenuPageDeleteController', function($scope, $modalInstance, entity, MenuPage) {

        $scope.menuPage = entity;
        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            MenuPage.delete({id: id},
                function () {
                    $modalInstance.close(true);
                });
        };

    });