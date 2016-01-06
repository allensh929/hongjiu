'use strict';

angular.module('hongjieApp').controller('GiftDialogController',
    ['$scope', '$stateParams', '$modalInstance', 'entity', 'Gift', 'Upload', 'Ahdin',
        function($scope, $stateParams, $modalInstance, entity, Gift, Upload, Ahdin) {

        $scope.gift = entity;
        $scope.load = function(id) {
            Gift.get({id : id}, function(result) {
                $scope.gift = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('hongjieApp:giftUpdate', result);
            $modalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.gift.id != null) {
                Gift.update($scope.gift, onSaveSuccess, onSaveError);
            } else {
                Gift.save($scope.gift, onSaveSuccess, onSaveError);
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
                	$scope.gift.image = data.image;
               	                	
                }).error(function (data, status, headers, config) {

                    console.log('error status: ' + status);
                });
        	};
        	
        	
        	//TODO gif no compress
        	 Ahdin.compress({
	              sourceFile: uploadFile[0],
	              maxWidth: 1280,
	              maxHeight:800,
	              quality: 0.7
	          }).then(function(compressedBlob) {
	        	  console.log('compressed image by ahdin.');
	              uploadImageFile(compressedBlob);
	          });
	         
        };
}]);
