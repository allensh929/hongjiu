'use strict';

angular.module('hongjieApp')
    .controller('FrontNavbarController', function ($scope, $rootScope, $sce, $location, $state, ENV, Info) {
      
    	console.debug("FrontNavbarController");
        $scope.$state = $state;
        $scope.inProduction = ENV === 'prod';
        
        Info.query(function(result){
        	if (result.length > 0){
        		$rootScope.info = result[0];
        		console.debug('SINAURL:' + $rootScope.info.weiboUrl);
        		$scope.qrCodeHtml = $sce.trustAsHtml('扫一扫关注官方微信订阅 / 微信服务号!<br> <img src=\'/assets/images/upload/'+ $rootScope.info.wechatSubscribeCode +'\'> <img src=\'/assets/images/upload/'+ $rootScope.info.wechatServiceCode +'\'>');
        	}
        });
        
        
    });
