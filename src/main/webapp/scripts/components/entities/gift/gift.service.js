'use strict';

angular.module('hongjieApp')
    .factory('Gift', function ($resource, DateUtils) {
        return $resource('api/gifts/:id', {}, {
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
.factory('GiftExt', function ($http) {
	console.debug('load GiftExt service');
	  return {
          findAllActiveGifts: function (done) {
          	console.debug('findAllActiveGifts');
              return $http.get('api/gifts/active').then(function (response) {
                  return done(response.data);
              });
          }
      };
})