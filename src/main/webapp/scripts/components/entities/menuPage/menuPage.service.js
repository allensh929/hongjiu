'use strict';

angular.module('hongjieApp')
    .factory('MenuPage', function ($resource, DateUtils) {
    	console.debug('load menupage service');
        return $resource('api/menuPages/:id', {}, {
	            'query': { method: 'GET', isArray: true},
	            'get': {
	                method: 'GET',
	                transformResponse: function (data) {
	                    data = angular.fromJson(data);
	                    return data;
	                }
	            },
	            'update': { method:'PUT' }
	        });
    });

angular.module('hongjieApp')
.factory('MenuPageExt', function ($http) {
	console.debug('load MenuPageExt service');
	  return {
          findAllActiveMenuPage: function (done) {
          	console.debug('findAllActiveMenuPage');
              return $http.get('api/menuPages/active').then(function (response) {
                  return done(response.data);
              });
          }
      };
});