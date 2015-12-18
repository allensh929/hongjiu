'use strict';

angular.module('hongjieApp').controller('MenuPageDialogController',
    ['$scope', '$stateParams', '$modalInstance', 'entity', 'MenuPage', 'Slide',
        function($scope, $stateParams, $modalInstance, entity, MenuPage, Slide) {

        $scope.menuPage = entity;
        $scope.slides = Slide.query();
        $scope.load = function(id) {
            MenuPage.get({id : id}, function(result) {
                $scope.menuPage = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('hongjieApp:menuPageUpdate', result);
            $modalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.menuPage.id != null) {
                MenuPage.update($scope.menuPage, onSaveSuccess, onSaveError);
            } else {
                MenuPage.save($scope.menuPage, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };
}]);
