'use strict';

angular.module('hongjieApp')
    .factory('ProductRelate', function ($resource, DateUtils) {
        return $resource('api/productRelates/:id', {}, {
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
