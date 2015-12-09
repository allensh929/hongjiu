'use strict';

angular.module('hongjieApp')
    .controller('ProductRelateController', function ($scope, $state, $modal, ProductRelate, ParseLinks) {
      
        $scope.productRelates = [];
        $scope.page = 0;
        $scope.loadAll = function() {
            ProductRelate.query({page: $scope.page, size: 20}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                $scope.productRelates = result;
            });
        };
        $scope.loadPage = function(page) {
            $scope.page = page;
            $scope.loadAll();
        };
        $scope.loadAll();


        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.productRelate = {
                name: null,
                id: null
            };
        };
    });
