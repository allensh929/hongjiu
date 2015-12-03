'use strict';

angular.module('hongjieApp')
    .controller('FrontMainController', ["$scope", "wineService", function ($scope, wineService) {
      
    	console.debug('wine service start');
        $scope.products = wineService.findAllWines();
        console.debug('wine service end');
    }]);

angular.module('ui.bootstrap.carousel', [ 'ui.bootstrap.transition' ])
		.controller(
				'CarouselController',
				[ '$scope', '$timeout', '$transition', '$q',
						function($scope, $timeout, $transition, $q) {
						} ]).directive('carousel', [ function() {
			return {

			}
		} ]);