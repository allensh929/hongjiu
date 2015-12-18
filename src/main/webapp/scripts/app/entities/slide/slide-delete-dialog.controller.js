'use strict';

angular.module('hongjieApp')
	.controller('SlideDeleteController', function($scope, $modalInstance, entity, Slide) {

        $scope.slide = entity;
        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            Slide.delete({id: id},
                function () {
                    $modalInstance.close(true);
                });
        };

    });