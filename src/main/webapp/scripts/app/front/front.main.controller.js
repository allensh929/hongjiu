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
    	
    	$scope.jump = function(link){
    		window.location.href = link;
    	}
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
		var others =[];
		for (var key in result){
			if (key =="其他"){
				others = result[key];
				continue;
			}
			regions[index] = result[key];
			index ++;
			console.log("key" + key + ",value"+ result[key]); 
		}
		regions[index] = others;//其他放在最后显示
		$scope.byregionsProducts = regions;
	});
	
    console.debug('FrontProductByRegionsController end');
}]);

angular.module('hongjieApp')
.controller('FrontProductByVarietyController', ["$scope", "wineService", "Product", function ($scope, wineService, Product) {
  
	console.debug('FrontProductByVarietyController start');
	
	wineService.findByVarietyWines(function(result){
		
		var varietys = [[]];
		var index = 0;
		var others =[];
		for (var key in result){
			if (key =="其他"){
				others = result[key];
				continue;
			}
			varietys[index] = result[key];
			index ++;
			console.log("key" + key + ",value"+ result[key]); 
		}
		varietys[index] = others;//其他放在最后显示
		$scope.byvarietyProducts = varietys;
	});
	
    console.debug('FrontProductByVarietyController end');
}]);

angular.module('hongjieApp')
.controller('FrontProductDetailController', ["$scope", "$rootScope", "$stateParams", "entity", "Product", "Xref", "wineService", function ($scope, $rootScope, $stateParams, entity, Product, Xref, wineService) {
	
	console.debug('FrontProductDetailController start');
    $scope.product = entity;
    
    if ($scope.product.image1 != null){
    	window._bd_share_config.common.bdText = $scope.product.title;
    	window._bd_share_config.common.bdDesc = $scope.product.description;
    	window._bd_share_config.common.bdPic = $rootScope.PHOTOBASEURL + $scope.product.image1;
    }
    wineService.getProductRelatesByProductId($stateParams.id, function (result){
    	$scope.relates = result;
    	console.debug('relates size:'+result.length);
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

angular.module('hongjieApp')
.controller('FrontGiftController', ["$scope", "$stateParams", "Gift", function ($scope, $stateParams, Gift) {
	
	console.debug('FrontGiftController start');
	Gift.get({id: $stateParams.id}, function(result) {
         $scope.gift = result;
     });    
        
    console.debug('FrontGiftController end');
}]);

angular.module('hongjieApp')
.controller('FrontBrandStoryController', ["$scope", "$stateParams", "BrandStory", function ($scope, $stateParams, BrandStory) {
	
	console.debug('FrontBrandStoryController start');
	BrandStory.get({id: $stateParams.id}, function(result) {
         $scope.story = result;
     });    
        
    console.debug('FrontBrandStoryController end');
}]);

angular.module('hongjieApp')
.controller('FrontWineSideController', function ($scope, $state, $modal, WineSide) {
	
	console.debug('FrontWineSideController start');
    $scope.sides = [];
    $scope.page = 0;
    $scope.loadAll = function() {
    	WineSide.query(function(result, headers) {
            $scope.sides = result;
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
    console.debug('FrontWineSideController end');
});

angular.module('hongjieApp')
.controller('FrontWineSideDetailController', ["$scope", "$stateParams", "WineSide", function ($scope, $stateParams, WineSide) {
	
	console.debug('FrontWineSideDetailController start');
	WineSide.get({id: $stateParams.id}, function(result) {
         $scope.side = result;
     });    
        
    console.debug('FrontWineSideDetailController end');
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