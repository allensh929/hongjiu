'use strict';

angular.module('hongjieApp')
    .factory('errorHandlerInterceptor', function ($q, $rootScope) {
        return {
            'responseError': function (response) {
            	console.debug('come here to errorHandlerInterceptor');
                if (!(response.status == 401 && response.data.path.indexOf("/api/account") == 0 )){
	                $rootScope.$emit('hongjieApp.httpError', response);
	            }
                return $q.reject(response);
            }
        };
    });