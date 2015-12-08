'use strict';

angular.module('hongjieApp')
    .controller('PhotoController', function ($scope, $state, $modal, Photo, ParseLinks,$rootScope) {
      
        $scope.photos = [];
        $scope.page = 0;
        $scope.loadAll = function() {
            Photo.query({page: $scope.page, size: 20}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                $scope.photos = result;
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
            $scope.photo = {
                name: null,
                url: null,
                note: null,
                id: null
            };
        };
        $scope.photoBaseUrl = $rootScope.PHOTOBASEURL;
    });
