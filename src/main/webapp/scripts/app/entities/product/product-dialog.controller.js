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
            	alert($scope.files);
            	if ($scope.files != null && $scope.files.length > 0){
            		Upload.upload({

                        url: '/api/postImage',
                        fields: { productId: $scope.product.id },
                        file: $scope.files[0],
                        method: 'POST'

                    }).progress(function (evt) {

                        var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
                        console.log('progress: ' + progressPercentage + '% ');

                    }).success(function (data, status, headers, config) {
                   	 
                   	 $scope.product.image = data.image;
                   	 Product.update($scope.product, onSaveSuccess, onSaveError);

                    }).error(function (data, status, headers, config) {

                        console.log('error status: ' + status);
                    });
            	}else{
            		Product.update($scope.product, onSaveSuccess, onSaveError);
            	}           	 
               
            } else {            	
            	
            	if ($scope.files != null && $scope.files.length > 0){
            		Upload.upload({

                        url: '/api/postImage',
                        fields: { productId: 0 },
                        file: $scope.files[0],
                        method: 'POST'

                    }).progress(function (evt) {

                        var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
                        console.log('progress: ' + progressPercentage + '% ');

                    }).success(function (data, status, headers, config) {
                   	 
                   	 $scope.product.image = data.image;
                   	 Product.save($scope.product, onSaveSuccess, onSaveError);

                    }).error(function (data, status, headers, config) {

                        console.log('error status: ' + status);
                    });
            	}else{
            		Product.save($scope.product, onSaveSuccess, onSaveError);
            	} 
            }
        };

        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
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
