'use strict';

angular.module('hongjieApp')
    .factory('Product', function ($resource, DateUtils) {
    	console.debug('come to product service');
        return $resource('api/products/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    data.produceDate = DateUtils.convertLocaleDateFromServer(data.produceDate);
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
//                    data.produceDate = DateUtils.convertLocaleDateToServer(data.produceDate);
                    return angular.toJson(data);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    data.produceDate = DateUtils.convertLocaleDateToServer(data.produceDate);
                    return angular.toJson(data);
                }
            }
        });
    });

angular.module('hongjieApp')
.factory('ProductExt', function ($http) {
	console.debug('load ProductExt service');
	  return {
		  getRelatesByProductId: function (product_id, done) {
        	  console.debug('getRelatesByProductId:'+product_id);
              return $http.get('api/'+product_id+'/productRelates').then(function (response) {
                  return done(response.data);
              });
          },
          getSearchProducts: function(search, done){
        	  console.debug('getSearchProducts:'+ search);
              return $http.get('api/products/search/'+search).then(function (response) {
                  return done(response.data);
              });
          }
      };
});