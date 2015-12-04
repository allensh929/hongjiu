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
