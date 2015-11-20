'use strict';

angular.module('hongjieApp').controller('DictionaryDialogController',
    ['$scope', '$stateParams', '$modalInstance', 'entity', 'Dictionary', 'Xref',
        function($scope, $stateParams, $modalInstance, entity, Dictionary, Xref) {

        $scope.dictionary = entity;
        $scope.xrefs = Xref.query();
        $scope.load = function(id) {
            Dictionary.get({id : id}, function(result) {
                $scope.dictionary = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('hongjieApp:dictionaryUpdate', result);
            $modalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.dictionary.id != null) {
                Dictionary.update($scope.dictionary, onSaveSuccess, onSaveError);
            } else {
                Dictionary.save($scope.dictionary, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };
}]);
