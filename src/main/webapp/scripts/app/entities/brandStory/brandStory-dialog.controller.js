'use strict';

angular.module('hongjieApp').controller('BrandStoryDialogController',
    ['$scope', '$stateParams', '$modalInstance', 'entity', 'BrandStory','Upload', 'Ahdin',
        function($scope, $stateParams, $modalInstance, entity, BrandStory, Upload, Ahdin) {

        $scope.brandStory = entity;
        $scope.load = function(id) {
            BrandStory.get({id : id}, function(result) {
                $scope.brandStory = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('hongjieApp:brandStoryUpdate', result);
            $modalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.brandStory.id != null) {
                BrandStory.update($scope.brandStory, onSaveSuccess, onSaveError);
            } else {
                BrandStory.save($scope.brandStory, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };
        
        $scope.onFileSelect = function(uploadFile, name){
        	
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
                	
               	 	if (name == "slide1"){
               	 		$scope.brandStory.slide1 = data.image;
                	 }
                	 if (name == "slide2"){
                		$scope.brandStory.slide2 = data.image;
                	 }
                	 if (name == "slide3"){
                		$scope.brandStory.slide3 = data.image;
                	 }
               	
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
