'use strict';

angular.module('hongjieApp')
	.controller('InfoDeleteController', function($scope, $modalInstance, entity, Info) {

        $scope.info = entity;
        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            Info.delete({id: id},
                function () {
                    $modalInstance.close(true);
                });
        };

    });