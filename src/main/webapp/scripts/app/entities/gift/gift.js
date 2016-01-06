'use strict';

angular.module('hongjieApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('gift', {
                parent: 'entity',
                url: '/gifts',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'Gifts'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/gift/gifts.html',
                        controller: 'GiftController'
                    }
                },
                resolve: {
                }
            })
            .state('gift.detail', {
                parent: 'entity',
                url: '/gift/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'Gift'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/gift/gift-detail.html',
                        controller: 'GiftDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'Gift', function($stateParams, Gift) {
                        return Gift.get({id : $stateParams.id});
                    }]
                }
            })
            .state('gift.new', {
                parent: 'gift',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/gift/gift-dialog.html',
                        controller: 'GiftDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    name: null,
                                    title: null,
                                    price: null,
                                    image: null,
                                    description: null,
                                    detailInfo: null,
                                    active: null,
                                    orderTag: null,
                                    placeholder1: null,
                                    placeholder2: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('gift', null, { reload: true });
                    }, function() {
                        $state.go('gift');
                    })
                }]
            })
            .state('gift.edit', {
                parent: 'gift',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/gift/gift-dialog.html',
                        controller: 'GiftDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['Gift', function(Gift) {
                                return Gift.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('gift', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('gift.delete', {
                parent: 'gift',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/gift/gift-delete-dialog.html',
                        controller: 'GiftDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['Gift', function(Gift) {
                                return Gift.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('gift', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
