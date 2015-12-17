'use strict';

angular.module('hongjieApp')
    .factory('wineService', function ($http) {
        return {
            findAllWines: function (done) {
            	console.debug('find all wines');
                return $http.get('api/products').then(function (response) {
                    return done(response.data);
                });
            },
            findAllNewsWines: function (done) {
            	console.debug('find all new wines');
                return $http.get('api/products/news').then(function (response) {
                    return done(response.data);
                });
            },
            
            findAllFavoWines: function (done) {
            	console.debug('find all favo wines');
                return $http.get('api/products/favo').then(function (response) {
                    return done(response.data);
                });
            },
                     
            getProductRelatesByProductId: function (product_id, done) {
            	console.debug('getProductRelatesByProductId:'+product_id);
                return $http.get('api/'+product_id+'/productRelates').then(function (response) {
                    return done(response.data);
                });
            }
        };
    });
