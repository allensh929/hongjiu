'use strict';

angular.module('hongjieApp').controller('PhotoDialogController',
    ['$scope', '$stateParams', '$modalInstance', 'entity', 'Photo', 'Upload', 'Ahdin',
        function($scope, $stateParams, $modalInstance, entity, Photo, Upload, Ahdin) {

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
        	
        	var uploadImageFile = function(compressedBlob) {
        		Upload.upload({

                    url: '/api/postPhoto',
                    fields: {},
                    file: compressedBlob,
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
        	
        	
        	//TODO gif no compress
        	 Ahdin.compress({
	              sourceFile: uploadFile[0],
	              maxWidth: 1280,
	              maxHeight:1000,
	              quality: 0.8
	          }).then(function(compressedBlob) {
	        	  console.log('compressed image by ahdin.');
	              uploadImageFile(compressedBlob);
	          });
	         
        };
}]);
