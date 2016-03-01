'use strict';

angular.module('hongjieApp', ['LocalStorageModule', 
               'ui.bootstrap', // for modal dialogs
    'ngResource', 'ui.router', 'ngCookies', 'ngAria', 'ngCacheBuster', 'infinite-scroll', 'angular-loading-bar', 'jkuri.gallery'])

    .run(function ($rootScope, $location, $window, $http, $state,  Auth, Principal, ENV, VERSION, PHOTOBASEURL, MenuPageExt, Slide, GiftExt, BrandStoryExt, Info, $cookies) {
        
    	console.debug('run');
        $rootScope.ENV = ENV;
        $rootScope.VERSION = VERSION;
        $rootScope.PHOTOBASEURL = PHOTOBASEURL;
        $rootScope.dropdownBgStyle = '';
        $rootScope.slideStyle1 = '';
        $rootScope.slideStyle2 = '';
        $rootScope.slideStyle3 = '';
        $rootScope.slideSrc1 = '';
        $rootScope.slideSrc2 = '';
        $rootScope.slideSrc3 = '';
        $rootScope.slideLink1 = '#';
        $rootScope.slideLink2 = '#';
        $rootScope.slideLink3 = '#';
        $rootScope.searchInput = "";
        
        MenuPageExt.findAllActiveMenuPage(function(result){
        	
        	$rootScope.MENUS = result;
        	console.debug('menus:' + $rootScope.MENUS.length);
    	});
        
        
        Slide.query(function(result){
	    	$rootScope.HOME_SLIDES = result;
	    	if (result.length > 0){
	    		$rootScope.slideStyle1= {'background': 'url(/assets/images/upload/'+result[0].url+') center'};
	    		if (result[0].link != null){
	    			$rootScope.slideLink1 = result[0].link;
	    		}
	    		if (result[0].url != null){
	    			$rootScope.slideSrc1 = result[0].url;
	    		}
	    		
	    	}
	    	if (result.length > 1){
	    		$rootScope.slideStyle2= {'background': 'url(/assets/images/upload/'+result[1].url+') center'};
	    		if (result[1].link != null){
	    			$rootScope.slideLink2 = result[1].link;
	    		}
	    		if (result[1].url != null){
	    			$rootScope.slideSrc2 = result[1].url;
	    		}
	    	}
	    	if (result.length > 2){
	    		
	    		$rootScope.slideStyle3= {'background': 'url(/assets/images/upload/'+result[2].url+') center'};
	    		if (result[2].link != null){
	    			$rootScope.slideLink3 = result[2].link;
	    		}
	    		if (result[2].url != null){
	    			$rootScope.slideSrc3 = result[2].url;
	    		}
	    	}
	    	
	    	console.debug('home slides:' + $rootScope.HOME_SLIDES.length);
	    });
        
        GiftExt.findAllActiveGifts(function(result){
        	$rootScope.GIFTS = result;
        	console.debug('gifts:' + $rootScope.GIFTS.length);
        });
        
        BrandStoryExt.findAllActiveStorys(function(result){
        	
        	$rootScope.STORYS = result;
        	console.debug('storys:' + $rootScope.STORYS.length);
    	});
        $rootScope.$on('$stateChangeStart', function (event, toState, toStateParams) {
            $rootScope.toState = toState;
            $rootScope.toStateParams = toStateParams;
            console.debug('run auth');
            if (Principal.isIdentityResolved()) {
            	console.debug('run authorize');
                Auth.authorize();
            }
            
        });
        
        $rootScope.$on('$stateChangeSuccess',  function(event, toState, toParams, fromState, fromParams) {
            var titleKey = 'hongjie' ;

            // Remember previous state unless we've been redirected to login or we've just
            // reset the state memory after logout. If we're redirected to login, our
            // previousState is already set in the authExpiredInterceptor. If we're going
            // to login directly, we don't want to be sent to some previous state anyway
            if (toState.name != 'login' && $rootScope.previousStateName) {
              $rootScope.previousStateName = fromState.name;
              $rootScope.previousStateParams = fromParams;
            }

            // Set the page title key to the one configured in state or use default one
            if (toState.data.pageTitle) {
                titleKey = toState.data.pageTitle;
            }
            $window.document.title = titleKey;
        });
        
        $rootScope.back = function() {
            // If previous state is 'activate' or do not exist go to 'home'
            if ($rootScope.previousStateName === 'activate' || $state.get($rootScope.previousStateName) === null) {
                $state.go('home');
            } else {
                $state.go($rootScope.previousStateName, $rootScope.previousStateParams);
            }
        };

        $rootScope.canDoFavor = function(){
        	var favoriteCookie = $cookies.get('myFavorite');
    		if (isNaN(favoriteCookie)){
    			favoriteCookie = 1;
    			$cookies.put('myFavorite', favoriteCookie);
    		}else{
    			favoriteCookie = Number(favoriteCookie) + 1;
    			$cookies.put('myFavorite', favoriteCookie);
    		}
    		return Number(favoriteCookie) <= 5;
        };
        
    })
    .config(function ($stateProvider, $urlRouterProvider, $httpProvider, $locationProvider, httpRequestInterceptorCacheBusterProvider, AlertServiceProvider) {
        // uncomment below to make alerts look like toast
        //AlertServiceProvider.showAsToast(true);
    	
        //enable CSRF
        $httpProvider.defaults.xsrfCookieName = 'CSRF-TOKEN';
        $httpProvider.defaults.xsrfHeaderName = 'X-CSRF-TOKEN';
        console.debug('config state');
        //Cache everything except rest api requests
        httpRequestInterceptorCacheBusterProvider.setMatchlist([/.*api.*/, /.*protected.*/], true);
        $urlRouterProvider.otherwise('/');
        $stateProvider.state('site', {
            'abstract': true,
            views: {
                'front-navbar@': {
                    templateUrl: 'scripts/app/front/navbar.html',
                    controller: 'FrontNavbarController'
                }
            },
            resolve: {
            
            }
        });

        $httpProvider.interceptors.push('errorHandlerInterceptor');
        $httpProvider.interceptors.push('authExpiredInterceptor');
        $httpProvider.interceptors.push('notificationInterceptor');
        
    })
    .config(['$urlMatcherFactoryProvider', function($urlMatcherFactory) {
        $urlMatcherFactory.type('boolean', {
            name : 'boolean',
            decode: function(val) { return val == true ? true : val == "true" ? true : false },
            encode: function(val) { return val ? 1 : 0; },
            equals: function(a, b) { return this.is(a) && a === b; },
            is: function(val) { return [true,false,0,1].indexOf(val) >= 0 },
            pattern: /bool|true|0|1/
        });
    }]);;
    
