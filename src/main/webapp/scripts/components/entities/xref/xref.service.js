'use strict';

angular.module('hongjieApp')
    .factory('Xref', function ($resource, DateUtils) {
        return $resource('api/xrefs/:id', {}, {
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
