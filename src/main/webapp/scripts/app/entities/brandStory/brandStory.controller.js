'use strict';

angular.module('hongjieApp')
    .controller('BrandStoryController', function ($scope, $state, $modal, BrandStory, ParseLinks) {
      
        $scope.brandStorys = [];
        $scope.page = 0;
        $scope.loadAll = function() {
            BrandStory.query({page: $scope.page, size: 20}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                $scope.brandStorys = result;
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
            $scope.brandStory = {
                name: null,
                title: null,
                slide1: null,
                slide2: null,
                slide3: null,
                detailInfo: null,
                active: null,
                orderTag: null,
                placeholder1: null,
                placeholder2: null,
                id: null
            };
        };
    });
