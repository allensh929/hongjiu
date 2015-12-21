'use strict';

angular.module('hongjieApp')
    .controller('XrefDetailController', function ($scope, $rootScope, $stateParams, entity, Xref, Product) {
        $scope.xref = entity;
        $scope.load = function (id) {
            Xref.get({id: id}, function(result) {
                $scope.xref = result;
            });
        };
        var unsubscribe = $rootScope.$on('hongjieApp:xrefUpdate', function(event, result) {
            $scope.xref = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
