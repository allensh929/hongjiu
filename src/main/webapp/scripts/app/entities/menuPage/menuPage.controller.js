'use strict';

angular.module('hongjieApp')
    .controller('MenuPageController', function ($scope, $state, $modal, MenuPage, ParseLinks) {
      
        $scope.menuPages = [];
        $scope.page = 0;
        $scope.loadAll = function() {
            MenuPage.query({page: $scope.page, size: 20}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                $scope.menuPages = result;
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
            $scope.menuPage = {
                name: null,
                pageId: null,
                url: null,
                detailInfo: null,
                active: null,
                id: null
            };
        };
    });
