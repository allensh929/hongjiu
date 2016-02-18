'use strict';

angular.module('hongjieApp').controller('InfoDialogController',
    ['$scope', '$stateParams', '$modalInstance', 'entity', 'Info', 'Upload', 'Ahdin',
        function($scope, $stateParams, $modalInstance, entity, Info, Upload, Ahdin) {

        $scope.info = entity;
        $scope.load = function(id) {
            Info.get({id : id}, function(result) {
                $scope.info = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('hongjieApp:infoUpdate', result);
            $modalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.info.id != null) {
                Info.update($scope.info, onSaveSuccess, onSaveError);
            } else {
                Info.save($scope.info, onSaveSuccess, onSaveError);
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
                	
               	 	if (name == "qrCode"){
               	 		$scope.info.qrCode = data.image;
                	 }
                	 if (name == "subscribeCode"){
                		$scope.info.wechatSubscribeCode = data.image;
                	 }
                	 if (name == "serviceCode"){
                		$scope.info.wechatServiceCode = data.image;
                	 }
                	 if (name == "logo"){
                 		$scope.info.placeholder2 = data.image;
                 	 }
                	 if (name == "logo2"){
                  		$scope.info.placeholder3 = data.image;
                  	 }
                	 if (name == "bg"){
                   		$scope.info.placeholder4 = data.image;
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
