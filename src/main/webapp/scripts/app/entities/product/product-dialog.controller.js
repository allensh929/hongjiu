'use strict';

angular.module('hongjieApp').controller('ProductDialogController',
    ['$scope', '$stateParams', '$modalInstance', 'entity', 'Product', 'Xref','Upload',
        function($scope, $stateParams, $modalInstance, entity, Product, Xref, Upload) {

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
        	Upload.upload({

                url: '/api/postImage',
                fields: { productId: productId },
                file: uploadFile[0],
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
//        $scope.justPostIt = function (product) {
//
//            var successCallback = function(productId) {
//
//                angular.forEach($scope.files, function(file) {
//
//                    var uploadImageFile = function(compressedBlob) {
//
//                        Upload.upload({
//
//                            url: '/api/postImage',
//                            fields: { productId: productId },
//                            file: compressedBlob,
//                            method: 'POST'
//
//                        }).progress(function (evt) {
//
//                            var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
//                            console.log('progress: ' + progressPercentage + '% ');
//
//                        }).success(function (data, status, headers, config) {
//
//                            $rootScope.$emit('image-upload-success', {productId: productId, src: data.image});
//
//                        }).error(function (data, status, headers, config) {
//
//                            console.log('error status: ' + status);
//                        });
//                    };
//
//                    //TODO gif no compress
//                    Ahdin.compress({
//                        sourceFile: file,
//                        maxWidth: 800,
//                        maxHeight:800,
//                        quality: 0.5
//                    }).then(function(compressedBlob) {
//
//                        uploadImageFile(compressedBlob);
//                    });
//
////                    $state.go('products');
//                });
//
//            };
//            Product.save($scope.product, successCallback);
//        };
}]);
