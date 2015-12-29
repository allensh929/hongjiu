'use strict';

angular.module('hongjieApp')
    .controller('FrontMainController', ["$scope", "wineService", "Product", function ($scope, wineService, Product) {
      
    	console.debug('FrontMainController start');
    	
    	wineService.findAllNewsWines(function(result){
    		$scope.newProducts = result;
    		if (result.length > 4){
    			$scope.news = [result[0], result[1], result[2], result[3]];
    		}else{
    			$scope.news = $scope.newProducts;
    		}
    	});
    	wineService.findAllFavoWines(function(result){
    		$scope.favoProducts = result;
    		if (result.length > 4){
    			$scope.favos = [result[0], result[1], result[2], result[3]];
    		}else{
    			$scope.favos = $scope.favoProducts;
    		}
    		
    	});
    	
        console.debug('FrontMainController end');
    }]);

angular.module('hongjieApp')
.controller('FrontProductController', function ($scope, $state, $modal, Product) {
	
	console.debug('FrontProductController start');
    $scope.products = [];
    $scope.page = 0;
    $scope.loadAll = function() {
        Product.query(function(result, headers) {
            $scope.products = result;
        });
    };
    $scope.loadPage = function(page) {
        $scope.page = page;
        $scope.loadAll();
    };
    $scope.loadAll();


    $scope.refresh = function () {
        $scope.loadAll();
        $scope.clear();
    };
    console.debug('FrontProductController end');
});

angular.module('hongjieApp')
.controller('FrontProductByRegionsController', ["$scope", "wineService", "Product", function ($scope, wineService, Product) {
  
	console.debug('FrontProductByRegionsController start');
	
	wineService.findByRegionsWines(function(result){
		
		var regions = [[]];
		var index = 0;
		for (var key in result){
			regions[index] = result[key];
			index ++;
			console.log("key" + key + ",value"+ result[key]); 
		}
		$scope.byregionsProducts = regions;
	});
	
    console.debug('FrontProductByRegionsController end');
}]);

angular.module('hongjieApp')
.controller('FrontProductDetailController', ["$scope", "$rootScope", "$stateParams", "entity", "Product", "Xref", "wineService", function ($scope, $rootScope, $stateParams, entity, Product, Xref, wineService) {
	
	console.debug('FrontProductDetailController start');
    $scope.product = entity;
    wineService.getProductRelatesByProductId($stateParams.id, function (result){
    	$scope.relates = result;
    });
        
    console.debug('FrontProductDetailController end');
}]);

angular.module('hongjieApp')
.controller('FrontMenuPageController', ["$scope", "$rootScope", "$stateParams", function ($scope, $rootScope, $stateParams) {
	
	console.debug('FrontMenuPageController start');
	for (var index in $rootScope.MENUS){
		if ($stateParams.id == $rootScope.MENUS[index].id){
			$scope.menuPage = $rootScope.MENUS[index];
			console.debug('the menu:' + $scope.menuPage.name);
			break;
		}
	}
        
    console.debug('FrontMenuPageController end');
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