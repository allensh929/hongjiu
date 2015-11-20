'use strict';

angular.module('hongjieApp')
	.controller('XrefDeleteController', function($scope, $modalInstance, entity, Xref) {

        $scope.xref = entity;
        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            Xref.delete({id: id},
                function () {
                    $modalInstance.close(true);
                });
        };

    });