'use strict';

angular.module('hongjieApp')
    .controller('DictionaryDetailController', function ($scope, $rootScope, $stateParams, entity, Dictionary, Xref) {
        $scope.dictionary = entity;
        $scope.load = function (id) {
            Dictionary.get({id: id}, function(result) {
                $scope.dictionary = result;
            });
        };
        var unsubscribe = $rootScope.$on('hongjieApp:dictionaryUpdate', function(event, result) {
            $scope.dictionary = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
