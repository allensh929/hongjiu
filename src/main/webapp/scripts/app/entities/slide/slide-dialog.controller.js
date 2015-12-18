'use strict';

angular.module('hongjieApp').controller('SlideDialogController',
    ['$scope', '$stateParams', '$modalInstance', 'entity', 'Slide', 'MenuPage',
        function($scope, $stateParams, $modalInstance, entity, Slide, MenuPage) {

        $scope.slide = entity;
        $scope.menupages = MenuPage.query();
        $scope.load = function(id) {
            Slide.get({id : id}, function(result) {
                $scope.slide = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('hongjieApp:slideUpdate', result);
            $modalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.slide.id != null) {
                Slide.update($scope.slide, onSaveSuccess, onSaveError);
            } else {
                Slide.save($scope.slide, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };
}]);
