'use strict';

angular.module('hongjieApp')
    .controller('FrontNavbarController', function ($scope, $rootScope, $sce, $location, $state, ENV, Info, Product, ProductExt) {
      
    	console.debug("FrontNavbarController");
        $scope.$state = $state;
        $scope.inProduction = ENV === 'prod';
        $scope.search = true;
        $scope.enter = function(ev) {
        	if (ev.keyCode !== 13) return;
        	
        	if ($state.current.name == 'product.search') {
        		 $rootScope.$emit('please-load-product-search-event');
        	} else {
        		$state.go("product.search");
        	}
        	
        };

        $rootScope.$on('please-load-product-search-event', function() {
        	console.debug("searching....start");
        		if ($scope.searchInput !="" && $scope.searchInput !="undefined"){
        	        ProductExt.getSearchProducts($scope.searchInput, function(result) {
        	        	$rootScope.$emit('load-product-search-event', {products : result});
        			});
        		}else{
        			Product.query(function(result, headers) {
        				$rootScope.$emit('load-product-search-event', {products : result});
    		        });
        		}
        });
        	
        Info.query(function(result){
        	if (result.length > 0){
        		$rootScope.info = result[0];
        		if (result[0].placeholder4 != null){
        			$rootScope.dropdownBgStyle = {'background': 'url(/assets/images/upload/'+result[0].placeholder4+') center', 'background-size': 'cover', 'background-repeat': 'no-repeat'};
        			console.debug($rootScope.dropdownBgStyle);
        		}
        		
        		$scope.qrCodeHtml = $sce.trustAsHtml('扫一扫关注官方微信订阅 / 微信服务号!<br> <img src=\'/assets/images/upload/'+ $rootScope.info.wechatSubscribeCode +'\'> <img src=\'/assets/images/upload/'+ $rootScope.info.wechatServiceCode +'\'>');
        	}
        });
        
        
    });
