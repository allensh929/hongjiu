'use strict';

angular.module('hongjieApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('front-home', {
                parent: 'site',
                url: '/',
                data: {
                    authorities: []
                },
                views: {
                    'front-content@': {
                        templateUrl: 'scripts/app/front/main.html',
                        controller: 'FrontMainController'
                    },
                    'front-footer@': {
                        templateUrl: 'scripts/app/front/footer.html',
                        controller: 'FrontFooterController'
                    },
                    'front-slides@': {
                        templateUrl: 'scripts/app/front/slides.html',
                        controller: 'FrontMainController'
                    }
                },
                resolve: {
                    
                }
            });
    });
