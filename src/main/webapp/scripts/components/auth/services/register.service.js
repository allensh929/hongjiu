'use strict';

angular.module('hongjieApp')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });


