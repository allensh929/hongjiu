'use strict';

angular.module('hongjieApp', ['LocalStorageModule', 
               'ui.bootstrap', // for modal dialogs
    'ngResource', 'ui.router', 'ngCookies', 'ngAria', 'ngFileUpload', 'infinite-scroll', 'angular-loading-bar'])

    .run(function ($rootScope, $location, $window, $http, $state,  Auth, Principal, ENV, VERSION, PHOTOBASEURL) {
        
        $rootScope.ENV = ENV;
        $rootScope.VERSION = VERSION;
        $rootScope.PHOTOBASEURL = PHOTOBASEURL;
//        $rootScope.$on('$stateChangeStart', function (event, toState, toStateParams) {
//            $rootScope.toState = toState;
//            $rootScope.toStateParams = toStateParams;
//            console.debug('run auth');
//            if (Principal.isIdentityResolved()) {
//            	console.debug('run authorize');
//                Auth.authorize();
//            }
//            
//        });
        
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

    })
    .config(function ($stateProvider, $urlRouterProvider, $httpProvider, $locationProvider, AlertServiceProvider) {
        // uncomment below to make alerts look like toast
        //AlertServiceProvider.showAsToast(true);

        //enable CSRF
        $httpProvider.defaults.xsrfCookieName = 'CSRF-TOKEN';
        $httpProvider.defaults.xsrfHeaderName = 'X-CSRF-TOKEN';

        //Cache everything except rest api requests
        //httpRequestInterceptorCacheBusterProvider.setMatchlist([/.*api.*/, /.*protected.*/], true);
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
    
