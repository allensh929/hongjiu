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
