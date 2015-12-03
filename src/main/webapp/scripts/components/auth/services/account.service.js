'use strict';

angular.module('hongjieApp')
    .factory('Account', function Account($resource) {
    	console.debug('come here to account service');
        return $resource('api/account', {}, {
            'get': { method: 'GET', params: {}, isArray: false,
                interceptor: {
                    response: function(response) {
                        // expose response
                        return response;
                    }
                }
            }
        });
    });
