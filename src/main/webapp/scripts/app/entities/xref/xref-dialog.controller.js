'use strict';

angular.module('hongjieApp').controller('XrefDialogController',
    ['$scope', '$stateParams', '$modalInstance', '$q', 'entity', 'Xref', 'Dictionary', 'Product',
        function($scope, $stateParams, $modalInstance, $q, entity, Xref, Dictionary, Product) {

        $scope.xref = entity;
        $scope.dictionarys = Dictionary.query({filter: 'xref-is-null'});
        $q.all([$scope.xref.$promise, $scope.dictionarys.$promise]).then(function() {
            if (!$scope.xref.id || !$scope.xref.dictionary.id) {
                return $q.reject();
            }
            return Dictionary.get({id : $scope.xref.dictionary.id}).$promise;
        }).then(function(dictionary) {
            $scope.dictionarys.push(dictionary);
        });
        $scope.products = Product.query();
        $scope.load = function(id) {
            Xref.get({id : id}, function(result) {
                $scope.xref = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('hongjieApp:xrefUpdate', result);
            $modalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.xref.id != null) {
                Xref.update($scope.xref, onSaveSuccess, onSaveError);
            } else {
                Xref.save($scope.xref, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };
}]);
