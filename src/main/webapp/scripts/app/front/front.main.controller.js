'use strict';

angular.module('hongjieApp')
    .controller('FrontMainController', ["$scope", "wineService", "Product", function ($scope, wineService, Product) {
      
    	console.debug('product service start');
    	
    	wineService.findAllWines(function(result){
    		$scope.products = result;
    	});
    	$scope.gogo = function () {
            alert('你点击我了');
        }
    	
//    	Product.query(function(result) {
//            $scope.products = result;
//            console.debug(result[0].name);
//        });    
        
        console.debug('product service end');
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

angular.module('hongjieApp')
.controller('FrontFooterController', ["$scope", function ($scope) {
  
}]);