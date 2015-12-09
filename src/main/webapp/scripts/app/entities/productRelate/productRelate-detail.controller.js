'use strict';

angular.module('hongjieApp')
    .controller('ProductRelateDetailController', function ($scope, $rootScope, $stateParams, entity, ProductRelate, Product) {
        $scope.productRelate = entity;
        $scope.load = function (id) {
            ProductRelate.get({id: id}, function(result) {
                $scope.productRelate = result;
            });
        };
        var unsubscribe = $rootScope.$on('hongjieApp:productRelateUpdate', function(event, result) {
            $scope.productRelate = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
