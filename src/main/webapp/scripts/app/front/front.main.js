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
                    }
                },
                resolve: {
                    
                }
            })
            .state('product.fdetail', {
                parent: 'front-home',
                url: 'front/product/{id}',
                data: {
                   
                    pageTitle: 'Product'
                },
                views: {
                    'front-content@': {
                        templateUrl: 'scripts/app/front/product-detail.html',
                        controller: 'FrontProductDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'Product', function($stateParams, Product) {
                        return Product.get({id : $stateParams.id});
                    }]
                }
            })
            .state('product.all', {
                parent: 'front-home',
                url: 'front/products',
                data: {
                   
                    pageTitle: 'All Product'
                },
                views: {
                    'front-content@': {
                        templateUrl: 'scripts/app/front/product-all.html',
                        controller: 'FrontProductController'
                    }
                },
                resolve: {
                }
            })
            .state('product.byRegions', {
                parent: 'front-home',
                url: 'front/products/byregions',
                data: {
                   
                    pageTitle: 'All Product'
                },
                views: {
                    'front-content@': {
                        templateUrl: 'scripts/app/front/product-byregions.html',
                        controller: 'FrontProductByRegionsController'
                    }
                },
                resolve: {
                }
            })
            ;
    });
angular.module('hongjieApp')
.filter('trustHtml', function ($sce) {
    return function (input) {
        return $sce.trustAsHtml(input);
    }
});