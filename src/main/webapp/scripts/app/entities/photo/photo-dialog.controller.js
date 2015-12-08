'use strict';

angular.module('hongjieApp').controller('PhotoDialogController',
    ['$scope', '$stateParams', '$modalInstance', 'entity', 'Photo', 'Upload',
        function($scope, $stateParams, $modalInstance, entity, Photo, Upload) {

        $scope.photo = entity;
        $scope.load = function(id) {
            Photo.get({id : id}, function(result) {
                $scope.photo = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('hongjieApp:photoUpdate', result);
            $modalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.photo.id != null) {
                Photo.update($scope.photo, onSaveSuccess, onSaveError);
            } else {
                Photo.save($scope.photo, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };

        $scope.onFileSelect = function(uploadFile){
        	
        	Upload.upload({

                url: '/api/postPhoto',
                fields: {},
                file: uploadFile[0],
                method: 'POST'

            }).progress(function (evt) {

                var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
                console.log('progress: ' + progressPercentage + '% ');

            }).success(function (data, status, headers, config) {
           	 
            	//update the url
            	$scope.photo.url = data.image;
           	
            }).error(function (data, status, headers, config) {

                console.log('error status: ' + status);
            });
        };
}]);
