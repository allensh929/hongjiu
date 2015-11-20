'use strict';

angular.module('hongjieApp')
    .controller('DictionaryController', function ($scope, $state, $modal, Dictionary, ParseLinks) {
      
        $scope.dictionarys = [];
        $scope.page = 0;
        $scope.loadAll = function() {
            Dictionary.query({page: $scope.page, size: 20}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                $scope.dictionarys = result;
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
            $scope.dictionary = {
                identifer: null,
                ovalue: null,
                description: null,
                id: null
            };
        };
    });
