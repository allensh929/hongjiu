'use strict';

angular.module('hongjieApp')
    .factory('Sessions', function ($resource) {
    	
    	console.debug("come here Sessions");
        return $resource('api/account/sessions/:series', {}, {
            'getAll': { method: 'GET', isArray: true}
        });
    });



