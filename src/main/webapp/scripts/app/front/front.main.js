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
            
            .state('product.byVariety', {
                parent: 'front-home',
                url: 'front/products/byvariety',
                data: {
                   
                    pageTitle: 'All Product'
                },
                views: {
                    'front-content@': {
                        templateUrl: 'scripts/app/front/product-byvariety.html',
                        controller: 'FrontProductByVarietyController'
                    }
                },
                resolve: {
                }
            })
            
            .state('menuPage.fdetail', {
                parent: 'front-home',
                url: 'front/menuPage/{id}',
                data: {
                   
                    pageTitle: 'Menu page'
                },
                views: {
                    'front-content@': {
                        templateUrl: 'scripts/app/front/menu-page.html',
                        controller: 'FrontMenuPageController'
                    }
                },
                resolve: {
                }
            })
            
            .state('gift.fdetail', {
                parent: 'front-home',
                url: 'front/gift/{id}',
                data: {
                   
                    pageTitle: 'Gift page'
                },
                views: {
                    'front-content@': {
                        templateUrl: 'scripts/app/front/gift-detail.html',
                        controller: 'FrontGiftController'
                    }
                },
                resolve: {
                }
            })
            
            .state('brandStory.fdetail', {
                parent: 'front-home',
                url: 'front/brandStroy/{id}',
                data: {
                   
                    pageTitle: 'Brand story page'
                },
                views: {
                    'front-content@': {
                        templateUrl: 'scripts/app/front/story-detail.html',
                        controller: 'FrontBrandStoryController'
                    }
                },
                resolve: {
                }
            })
            
            .state('wineSide.all', {
                parent: 'front-home',
                url: 'front/wineSides',
                data: {
                   
                    pageTitle: 'All wine side'
                },
                views: {
                    'front-content@': {
                        templateUrl: 'scripts/app/front/wineside-all.html',
                        controller: 'FrontWineSideController'
                    }
                },
                resolve: {
                }
            })
            
            .state('wineSide.fdetail', {
                parent: 'front-home',
                url: 'front/wineSide/{id}',
                data: {
                   
                    pageTitle: 'Wine side page'
                },
                views: {
                    'front-content@': {
                        templateUrl: 'scripts/app/front/wineside-detail.html',
                        controller: 'FrontWineSideDetailController'
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