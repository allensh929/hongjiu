'use strict';

angular.module('hongjieApp').controller('ProductDialogController',
    ['$scope', '$stateParams', '$modalInstance', 'entity', 'Product', 'Xref','Upload', 'Ahdin',
        function($scope, $stateParams, $modalInstance, entity, Product, Xref, Upload, Ahdin) {

        $scope.product = entity;
        $scope.xrefs = Xref.query();
        $scope.load = function(id) {
            Product.get({id : id}, function(result) {
                $scope.product = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('hongjieApp:productUpdate', result);
            $modalInstance.close(result);
            $scope.isSaving = false;
        };
        
        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.product.id != null) {
            	Product.update($scope.product, onSaveSuccess, onSaveError);
               
            } else {            	
            	Product.save($scope.product, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };
        
        $scope.onFileSelect = function(uploadFile, name){
        	
        	var productId = 0;
        	if ($scope.product.id != null){
        		productId = $scope.product.id;
        	}
        	var uploadImageFile = function(compressedBlob) {
        		Upload.upload({

                    url: '/api/postImage',
                    fields: { productId: productId },
                    file: compressedBlob,
                    method: 'POST'

                }).progress(function (evt) {

                    var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
                    console.log('progress: ' + progressPercentage + '% ');

                }).success(function (data, status, headers, config) {
               	 
               	 if (name == "image"){
               		$scope.product.image = data.image;
               	 }
               	 if (name == "imageDetail"){
               		$scope.product.image1 = data.image;
               	 }
               	 if (name == "award1"){
               		$scope.product.award1 = data.image;
               	 }
               	if (name == "award2"){
               		$scope.product.award2 = data.image;
               	 }
               	if (name == "award3"){
               		$scope.product.award3 = data.image;
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
        
        $scope.onFileDelete = function(name){
        	if (name == "image"){
           		$scope.product.image = "";
           	 }
           	 if (name == "imageDetail"){
           		$scope.product.image1 = "";
           	 }
           	 if (name == "award1"){
           		$scope.product.award1 = "";
           	 }
           	if (name == "award2"){
           		$scope.product.award2 = "";
           	 }
           	if (name == "award3"){
           		$scope.product.award3 = "";
           	 }
        };
        
}]);
