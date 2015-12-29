'use strict';

angular.module('hongjieApp')
    .factory('Slide', function ($resource, DateUtils) {
        return $resource('api/slides/:id', {}, {
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
.factory('SlideExt', function ($http) {
	console.debug('load SlideExt service');
	  return {
          findAllSlideByPageId: function (page_id, done) {
          	console.debug('findAllSlideByPageId:'+page_id);
              return $http.get('api/slides/bypageid/'+ page_id).then(function (response) {
                  return done(response.data);
              });
          }
      };
});