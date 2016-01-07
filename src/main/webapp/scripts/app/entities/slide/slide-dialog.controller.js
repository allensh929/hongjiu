'use strict';

angular.module('hongjieApp').controller('SlideDialogController',
    ['$scope', '$stateParams', '$modalInstance', 'entity', 'Slide', 'MenuPage','Upload', 'Ahdin',
        function($scope, $stateParams, $modalInstance, entity, Slide, MenuPage, Upload, Ahdin) {

        $scope.slide = entity;
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
                	$scope.slide.url = data.image;
               	
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
