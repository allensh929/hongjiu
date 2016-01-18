'use strict';

angular.module('hongjieApp')
    .controller('FrontNavbarController', function ($scope, $rootScope, $sce, $location, $state, ENV, Info, ProductExt) {
      
    	console.debug("FrontNavbarController");
        $scope.$state = $state;
        $scope.inProduction = ENV === 'prod';
        $scope.search = true;
        $scope.searchInput = "";
        
        $scope.enter = function(ev) {
        	if (ev.keyCode !== 13) return; 
        	if ($scope.searchInput !=""){
        		$rootScope.searchInput = $scope.searchInput;
        		$state.go("product.search");
        	}
        }

        	
        Info.query(function(result){
        	if (result.length > 0){
        		$rootScope.info = result[0];
        		
        		$scope.qrCodeHtml = $sce.trustAsHtml('扫一扫关注官方微信订阅 / 微信服务号!<br> <img src=\'/assets/images/upload/'+ $rootScope.info.wechatSubscribeCode +'\'> <img src=\'/assets/images/upload/'+ $rootScope.info.wechatServiceCode +'\'>');
        	}
        });
        
        
    });
