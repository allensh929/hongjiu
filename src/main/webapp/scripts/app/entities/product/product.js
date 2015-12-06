'use strict';

angular.module('hongjieApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('product', {
                parent: 'entity',
                url: '/products',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'Products'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/product/products.html',
                        controller: 'ProductController'
                    }
                },
                resolve: {
                }
            })
            .state('product.detail', {
                parent: 'entity',
                url: '/product/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'Product'
                },
                views: {
                    'front-content@': {
                        templateUrl: 'scripts/app/entities/product/product-detail.html',
                        controller: 'ProductDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'Product', function($stateParams, Product) {
                        return Product.get({id : $stateParams.id});
                    }]
                }
            })
            
            .state('product.new', {
                parent: 'product',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/product/product-dialog.html',
                        controller: 'ProductDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    number: null,
                                    name: null,
                                    price: null,
                                    produceDate: null,
                                    producer: null,
                                    image: null,
                                    favorate: null,
                                    news: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('product', null, { reload: true });
                    }, function() {
                        $state.go('product');
                    })
                }]
            })
            .state('product.edit', {
                parent: 'product',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/product/product-dialog.html',
                        controller: 'ProductDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['Product', function(Product) {
                                return Product.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('product', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('product.delete', {
                parent: 'product',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/product/product-delete-dialog.html',
                        controller: 'ProductDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['Product', function(Product) {
                                return Product.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('product', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
