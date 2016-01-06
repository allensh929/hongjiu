'use strict';

angular.module('hongjieApp')
	.controller('BrandStoryDeleteController', function($scope, $modalInstance, entity, BrandStory) {

        $scope.brandStory = entity;
        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            BrandStory.delete({id: id},
                function () {
                    $modalInstance.close(true);
                });
        };

    });