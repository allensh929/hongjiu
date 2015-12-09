'use strict';

angular.module('hongjieApp')
	.controller('ProductRelateDeleteController', function($scope, $modalInstance, entity, ProductRelate) {

        $scope.productRelate = entity;
        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            ProductRelate.delete({id: id},
                function () {
                    $modalInstance.close(true);
                });
        };

    });