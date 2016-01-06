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
