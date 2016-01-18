'use strict';

angular.module('hongjieApp')
    .controller('ProductDetailController', function ($scope, $rootScope, $stateParams, entity, Product, Xref, ProductExt) {
        $scope.product = entity;
        $scope.load = function (id) {
            Product.get({id: id}, function(result) {
                $scope.product = result;
            });
        };
        console.debug($stateParams.id);	
    	ProductExt.getRelatesByProductId($stateParams.id, function(result) {
            $scope.relates = result;
        });
        var unsubscribe = $rootScope.$on('hongjieApp:productUpdate', function(event, result) {
            $scope.product = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
