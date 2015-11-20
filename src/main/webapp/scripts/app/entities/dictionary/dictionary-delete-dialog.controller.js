'use strict';

angular.module('hongjieApp')
	.controller('DictionaryDeleteController', function($scope, $modalInstance, entity, Dictionary) {

        $scope.dictionary = entity;
        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            Dictionary.delete({id: id},
                function () {
                    $modalInstance.close(true);
                });
        };

    });