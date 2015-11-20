 'use strict';

angular.module('hongjieApp')
    .factory('notificationInterceptor', function ($q, AlertService) {
        return {
            response: function(response) {
                var alertKey = response.headers('X-hongjieApp-alert');
                if (angular.isString(alertKey)) {
                    AlertService.success(alertKey, { param : response.headers('X-hongjieApp-params')});
                }
                return response;
            }
        };
    });
