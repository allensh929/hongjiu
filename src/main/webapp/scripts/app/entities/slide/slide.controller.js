'use strict';

angular.module('hongjieApp')
    .controller('SlideController', function ($scope, $state, $modal, Slide, ParseLinks) {
      
        $scope.slides = [];
        $scope.page = 0;
        $scope.loadAll = function() {
            Slide.query({page: $scope.page, size: 20}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                $scope.slides = result;
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
            $scope.slide = {
                name: null,
                url: null,
                link: null,
                description: null,
                id: null
            };
        };
    });
