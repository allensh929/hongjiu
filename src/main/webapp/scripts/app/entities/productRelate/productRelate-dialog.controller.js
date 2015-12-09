'use strict';

angular.module('hongjieApp').controller('ProductRelateDialogController',
    ['$scope', '$stateParams', '$modalInstance', 'entity', 'ProductRelate', 'Product',
        function($scope, $stateParams, $modalInstance, entity, ProductRelate, Product) {

        $scope.productRelate = entity;
        $scope.products = Product.query();
        $scope.load = function(id) {
            ProductRelate.get({id : id}, function(result) {
                $scope.productRelate = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('hongjieApp:productRelateUpdate', result);
            $modalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.productRelate.id != null) {
                ProductRelate.update($scope.productRelate, onSaveSuccess, onSaveError);
            } else {
                ProductRelate.save($scope.productRelate, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };
}]);
