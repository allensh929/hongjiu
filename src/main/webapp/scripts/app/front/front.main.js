'use strict';

angular.module('hongjieApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('front-home', {
                parent: 'site',
                url: '/',
                data: {
            		pageTitle: '鸿杰酒业'
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
                   
                    pageTitle: '酒品详情'
                },
                views: {
                    'front-content@': {
                        templateUrl: 'scripts/app/front/product-detail.html',
                        controller: 'FrontProductDetailController'
                    }
                },
                resolve: {
                }
            })
            .state('product.all', {
                parent: 'front-home',
                url: 'front/products',
                data: {
                   
                    pageTitle: '所有商品'
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
            .state('product.search', {
                parent: 'front-home',
                url: 'front/products/search',
                data: {
                   
                    pageTitle: '所有商品'
                },
                views: {
                    'front-content@': {
                        templateUrl: 'scripts/app/front/product-all.html',
                        controller: 'FrontProductSearchController'
                    }
                },
                resolve: {
                }
            })
            .state('product.byRegions', {
                parent: 'front-home',
                url: 'front/products/byregions',
                data: {
                   
                    pageTitle: '按地域'
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
                   
                    pageTitle: '按品种'
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
                url: 'front/menuPage/:id',
                data: {                   
                    pageTitle: '鸿杰酒业'
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
                   
                    pageTitle: '礼品'
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
                   
                    pageTitle: '品牌故事'
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
                   
                    pageTitle: '红酒周边'
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
                   
                    pageTitle: '周边产品'
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