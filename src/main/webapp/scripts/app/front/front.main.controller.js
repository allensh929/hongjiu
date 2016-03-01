'use strict';

angular.module('hongjieApp')
    .controller('FrontMainController', ["$rootScope", "$scope", "$cookies", "$http", "wineService", "Product", function ($rootScope, $scope, $cookies, $http, wineService, Product) {
      
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
    	};
    	
    	$scope.favor = function(data){
    		console.debug('favo:'+data);
    		
    		if (!$rootScope.canDoFavor()){
    			return;
    		}
    		$http({method: 'PUT', url: '/api/products/'+data+'/favo'}).
	            then(function(response) {
	              $scope.status = response.status;
	              $scope.data = response.data;
	              $scope.refreshFavor(response.data);
	            }, function(response) {
	              $scope.data = response.data || "Request failed";
	              $scope.status = response.status;
	        });
    	};
    	
    	$scope.refreshFavor = function(data){
    		for (var i = 0; i< $scope.news.length; i++){
    			if ($scope.news[i].id == data.id ){
    				$scope.news[i].favorate = data.favorate;
    				break;
    			}
    		}
    		for (var i = 0; i< $scope.favos.length; i++){
    			if ($scope.favos[i].id == data.id ){
    				$scope.favos[i].favorate = data.favorate;
    				break;
    			}
    		}
    	};
        console.debug('FrontMainController end');
    }]);

angular.module('hongjieApp')
.controller('FrontProductController',  ["$rootScope", "$http", "$scope", "$state", "$modal","Product", function ($rootScope, $http, $scope, $state, $modal, Product) {
	
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
    $scope.favor = function(data){
		if (!$rootScope.canDoFavor()){
			return;
		}
		$http({method: 'PUT', url: '/api/products/'+data+'/favo'}).
            then(function(response) {
              $scope.status = response.status;
              $scope.data = response.data;
              $scope.refreshFavor(response.data);
            }, function(response) {
              $scope.data = response.data || "Request failed";
              $scope.status = response.status;
        });
	};
	
	$scope.refreshFavor = function(data){
		for (var i = 0; i< $scope.products.length; i++){
			if ($scope.products[i].id == data.id ){
				$scope.products[i].favorate = data.favorate;
				break;
			}
		}
	};
    console.debug('FrontProductController end');
}]);

angular.module('hongjieApp')
.controller('FrontProductSearchController', ["$rootScope", "$http", "$scope", "$state", "$stateParams","ProductExt", function ($rootScope, $http, $scope, $state, $stateParams, ProductExt)  {
	
	console.debug('FrontProductSearchController start');
    $scope.products = [];
    
    $rootScope.$emit('please-load-product-search-event');

    $rootScope.$on('load-product-search-event', function(event, data) {
    	$scope.products = data.products;
    });
    
	$scope.refresh = function () {
        $scope.loadAll();
        $scope.clear();
    };
    
    $scope.favor = function(data){
		if (!$rootScope.canDoFavor()){
			return;
		}
		$http({method: 'PUT', url: '/api/products/'+data+'/favo'}).
            then(function(response) {
              $scope.status = response.status;
              $scope.data = response.data;
              $scope.refreshFavor(response.data);
            }, function(response) {
              $scope.data = response.data || "Request failed";
              $scope.status = response.status;
        });
	};
	
	$scope.refreshFavor = function(data){
		for (var i = 0; i< $scope.products.length; i++){
			if ($scope.products[i].id == data.id ){
				$scope.products[i].favorate = data.favorate;
				break;
			}
		}
	};
    console.debug('FrontProductSearchController end');
}]);

angular.module('hongjieApp')
.controller('FrontProductByRegionsController', ["$rootScope", "$http", "$scope", "wineService", "Product", function ($rootScope, $http, $scope, wineService, Product) {
  
	console.debug('FrontProductByRegionsController start');
	
	wineService.findByRegionsWines(function(result){
		
		var regions = [[]];
		var index = 0;
		var others =[];
		$scope.showFlag = [];
		$scope.toggleShowFlag = function(index){
			$scope.showFlag[index] = !$scope.showFlag[index];
		}
		for (var key in result){
			if (key =="其他"){
				others = result[key];
				continue;
			}
			regions[index] = result[key];
			$scope.showFlag[index] = true;
			index ++;
			console.log("key" + key + ",value"+ result[key]); 
		}
		regions[index] = others;//其他放在最后显示
		if (others.length == 0){
			regions.splice(index,index);
		}
		$scope.showFlag[index] = true;
		$scope.byregionsProducts = regions;
	});
	
	$scope.favor = function(data){
		if (!$rootScope.canDoFavor()){
			return;
		}
		$http({method: 'PUT', url: '/api/products/'+data+'/favo'}).
            then(function(response) {
              $scope.status = response.status;
              $scope.data = response.data;
              $scope.refreshFavor(response.data);
            }, function(response) {
              $scope.data = response.data || "Request failed";
              $scope.status = response.status;
        });
	};
	
	$scope.refreshFavor = function(data){
		for (var i = 0; i< $scope.byregionsProducts.length; i++){
			for(var j = 0; j< $scope.byregionsProducts[i].length; j++){
				if ($scope.byregionsProducts[i][j].id == data.id ){
					$scope.byregionsProducts[i][j].favorate = data.favorate;
					break;
				}
			}
		}
	};
	
    console.debug('FrontProductByRegionsController end');
}]);

angular.module('hongjieApp')
.controller('FrontProductByVarietyController', ["$rootScope", "$http", "$scope", "wineService", "Product", function ($rootScope, $http, $scope, wineService, Product) {
  
	console.debug('FrontProductByVarietyController start');
	
	$scope.toggleShowFlag = function(index){
		$scope.showFlag[index] = !$scope.showFlag[index];
	}
	
	wineService.findByVarietyWines(function(result){
		
		var varietys = [[]];
		var index = 0;
		var others =[];
		$scope.showFlag = [];
		for (var key in result){
			if (key =="其他"){
				others = result[key];
				continue;
			}
			varietys[index] = result[key];
			$scope.showFlag[index] = true;
			index ++;
			
			console.log("key" + key + ",value"+ result[key]); 
		}
		varietys[index] = others;//其他放在最后显示
		if (others.length == 0){
			varietys.splice(index,index);
		}
		$scope.showFlag[index] = true;
		$scope.byvarietyProducts = varietys;
	});
	
	$scope.favor = function(data){
		if (!$rootScope.canDoFavor()){
			return;
		}
		$http({method: 'PUT', url: '/api/products/'+data+'/favo'}).
            then(function(response) {
              $scope.status = response.status;
              $scope.data = response.data;
              $scope.refreshFavor(response.data);
            }, function(response) {
              $scope.data = response.data || "Request failed";
              $scope.status = response.status;
        });
	};
	
	$scope.refreshFavor = function(data){
		for (var i = 0; i< $scope.byvarietyProducts.length; i++){
			for(var j = 0; j< $scope.byvarietyProducts[i].length; j++){
				if ($scope.byvarietyProducts[i][j].id == data.id ){
					$scope.byvarietyProducts[i][j].favorate = data.favorate;
					break;
				}
			}
		}
	};
	
    console.debug('FrontProductByVarietyController end');
}]);

angular.module('hongjieApp')
.controller('FrontProductByOccasionController', ["$rootScope", "$http", "$scope", "wineService", "Product", function ($rootScope, $http, $scope, wineService, Product) {
  
	console.debug('FrontProductByOccasionController start');
	
	$scope.toggleShowFlag = function(index){
		$scope.showFlag[index] = !$scope.showFlag[index];
	}
	
	wineService.findByOccasionWines(function(result){
		
		var occasions = [[]];
		var index = 0;
		var others =[];
		$scope.showFlag = [];
		for (var key in result){
			if (key =="其他"){
				others = result[key];
				continue;
			}
			occasions[index] = result[key];
			$scope.showFlag[index] = true;
			index ++;
			
			console.log("key" + key + ",value"+ result[key]); 
		}
		occasions[index] = others;//其他放在最后显示
		if (others.length == 0){
			occasions.splice(index,index);
		}
		$scope.showFlag[index] = true;
		$scope.byoccasionProducts = occasions;
	});
	
	$scope.favor = function(data){
		if (!$rootScope.canDoFavor()){
			return;
		}
		$http({method: 'PUT', url: '/api/products/'+data+'/favo'}).
            then(function(response) {
              $scope.status = response.status;
              $scope.data = response.data;
              $scope.refreshFavor(response.data);
            }, function(response) {
              $scope.data = response.data || "Request failed";
              $scope.status = response.status;
        });
	};
	
	$scope.refreshFavor = function(data){
		for (var i = 0; i< $scope.byoccasionProducts.length; i++){
			for(var j = 0; j< $scope.byoccasionProducts[i].length; j++){
				if ($scope.byoccasionProducts[i][j].id == data.id ){
					$scope.byoccasionProducts[i][j].favorate = data.favorate;
					break;
				}
			}
		}
	};
	
    console.debug('FrontProductByOccasionController end');
}]);

angular.module('hongjieApp')
.controller('FrontProductDetailController', ["$scope", "$rootScope", "$stateParams", "Product", "Xref", "wineService", function ($scope, $rootScope, $stateParams, Product, Xref, wineService) {
	
	console.debug('FrontProductDetailController start');
	$scope.images = [];
	
    Product.get({id: $stateParams.id}, function(result) {
        $scope.product = result;
        if ($scope.product != null){
        	jiathis_config.title = $scope.product.title;
        	jiathis_config.summary = $scope.product.descriptionTitle;
        	jiathis_config.pic = $rootScope.PHOTOBASEURL + $scope.product.image1;
        	if ($scope.product.image1!= null && $scope.product.image1!=''){
        		$scope.images[0] = {thumb: '/assets/images/upload/'+$scope.product.image1, img: '/assets/images/upload/'+$scope.product.image1, description: 'Image 1'};
        	}
			if ($scope.product.image2!= null && $scope.product.image2!=''){
				$scope.images[1] = {thumb: '/assets/images/upload/'+$scope.product.image2, img: '/assets/images/upload/'+$scope.product.image2, description: 'Image 2'};	
			}
			if ($scope.product.image3!= null && $scope.product.image3!=''){
				$scope.images[2] = {thumb: '/assets/images/upload/'+$scope.product.image3, img: '/assets/images/upload/'+$scope.product.image3, description: 'Image 3'};
			}
        } 
    });    
    
    wineService.getProductRelatesByProductId($stateParams.id, function (result){
    	$scope.relates = result;
    	console.debug('relates size:'+result.length);
    });
        
    console.debug('FrontProductDetailController end');
}]);

angular.module('hongjieApp')
.controller('FrontMenuPageController', ["$scope", "$rootScope", "$state", "$stateParams", function ($scope, $rootScope, $state, $stateParams) {
	
	console.debug('FrontMenuPageController start');
	for (var index in $rootScope.MENUS){
		if ($stateParams.id == $rootScope.MENUS[index].id){
			$scope.menuPage = $rootScope.MENUS[index];
			$state.current.data.pageTitle = $scope.menuPage.name;
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