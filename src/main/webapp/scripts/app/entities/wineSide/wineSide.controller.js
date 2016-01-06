'use strict';

angular.module('hongjieApp')
    .controller('WineSideController', function ($scope, $state, $modal, WineSide, ParseLinks) {
      
        $scope.wineSides = [];
        $scope.page = 0;
        $scope.loadAll = function() {
            WineSide.query({page: $scope.page, size: 20}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                $scope.wineSides = result;
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
            $scope.wineSide = {
                name: null,
                title: null,
                price: null,
                image: null,
                description: null,
                detailInfo: null,
                active: null,
                orderTag: null,
                placeholder1: null,
                placeholder2: null,
                id: null
            };
        };
    });
