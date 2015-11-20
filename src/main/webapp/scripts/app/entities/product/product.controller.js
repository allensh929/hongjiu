'use strict';

angular.module('hongjieApp')
    .controller('ProductController', function ($scope, $state, $modal, Product, ParseLinks) {
      
        $scope.products = [];
        $scope.page = 0;
        $scope.loadAll = function() {
            Product.query({page: $scope.page, size: 20}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                $scope.products = result;
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
            $scope.product = {
                number: null,
                name: null,
                price: null,
                produceDate: null,
                producer: null,
                image: null,
                favorate: null,
                news: null,
                id: null
            };
        };
    });
