'use strict';

angular.module('hongjieApp')
    .controller('XrefController', function ($scope, $state, $modal, Xref, ParseLinks) {
      
        $scope.xrefs = [];
        $scope.page = 0;
        $scope.loadAll = function() {
            Xref.query({page: $scope.page, size: 20}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                $scope.xrefs = result;
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
            $scope.xref = {
                name: null,
                ovalue: null,
                id: null
            };
        };
    });
