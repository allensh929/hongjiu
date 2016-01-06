'use strict';

angular.module('hongjieApp')
    .controller('InfoController', function ($scope, $state, $modal, Info, ParseLinks) {
      
        $scope.infos = [];
        $scope.page = 0;
        $scope.loadAll = function() {
            Info.query({page: $scope.page, size: 20}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                $scope.infos = result;
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
            $scope.info = {
                qrCode: null,
                wechatSubscribeCode: null,
                wechatServiceCode: null,
                weiboUrl: null,
                qqUrl: null,
                placeholder1: null,
                placeholder2: null,
                id: null
            };
        };
    });
