'use strict';

angular.module('hongjieApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('productRelate', {
                parent: 'entity',
                url: '/productRelates',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'ProductRelates'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/productRelate/productRelates.html',
                        controller: 'ProductRelateController'
                    }
                },
                resolve: {
                }
            })
            .state('productRelate.detail', {
                parent: 'entity',
                url: '/productRelate/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'ProductRelate'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/productRelate/productRelate-detail.html',
                        controller: 'ProductRelateDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'ProductRelate', function($stateParams, ProductRelate) {
                        return ProductRelate.get({id : $stateParams.id});
                    }]
                }
            })
            .state('productRelate.new', {
                parent: 'productRelate',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/productRelate/productRelate-dialog.html',
                        controller: 'ProductRelateDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    name: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('productRelate', null, { reload: true });
                    }, function() {
                        $state.go('productRelate');
                    })
                }]
            })
            .state('productRelate.edit', {
                parent: 'productRelate',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/productRelate/productRelate-dialog.html',
                        controller: 'ProductRelateDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['ProductRelate', function(ProductRelate) {
                                return ProductRelate.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('productRelate', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('productRelate.delete', {
                parent: 'productRelate',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/productRelate/productRelate-delete-dialog.html',
                        controller: 'ProductRelateDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['ProductRelate', function(ProductRelate) {
                                return ProductRelate.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('productRelate', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
