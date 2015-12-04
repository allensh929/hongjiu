'use strict';

angular.module('hongjieApp')
    .factory('wineService', function ($http) {
        return {
            findAllWines: function (done) {
            	console.debug('find all wines');
                return $http.get('api/products').then(function (response) {
                    return done(response.data);
                });
            }
           
        };
    });
