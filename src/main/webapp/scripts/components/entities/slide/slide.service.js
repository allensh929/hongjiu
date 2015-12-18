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
