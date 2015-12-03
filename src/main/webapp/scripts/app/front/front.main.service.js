'use strict';

angular.module('hongjieApp')
    .factory('wineService', function ($http) {
        return {
            findAllWines: function () {
                return $http.get('api/products/').then(function (response) {
                    return response.data;
                });
            }
           
        };
    });
