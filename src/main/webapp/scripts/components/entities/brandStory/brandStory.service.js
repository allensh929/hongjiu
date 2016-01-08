'use strict';

angular.module('hongjieApp')
    .factory('BrandStory', function ($resource, DateUtils) {
        return $resource('api/brandStorys/:id', {}, {
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
.factory('BrandStoryExt', function ($http) {
	console.debug('load BrandStoryExt service');
	  return {
          findAllActiveStorys: function (done) {
          	console.debug('findAllActiveStorys');
              return $http.get('api/brandStorys/active').then(function (response) {
                  return done(response.data);
              });
          }
      };
});