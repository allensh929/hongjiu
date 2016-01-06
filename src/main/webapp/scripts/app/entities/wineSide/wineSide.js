'use strict';

angular.module('hongjieApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('wineSide', {
                parent: 'entity',
                url: '/wineSides',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'WineSides'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/wineSide/wineSides.html',
                        controller: 'WineSideController'
                    }
                },
                resolve: {
                }
            })
            .state('wineSide.detail', {
                parent: 'entity',
                url: '/wineSide/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'WineSide'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/wineSide/wineSide-detail.html',
                        controller: 'WineSideDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'WineSide', function($stateParams, WineSide) {
                        return WineSide.get({id : $stateParams.id});
                    }]
                }
            })
            .state('wineSide.new', {
                parent: 'wineSide',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/wineSide/wineSide-dialog.html',
                        controller: 'WineSideDialogController',
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
                        $state.go('wineSide', null, { reload: true });
                    }, function() {
                        $state.go('wineSide');
                    })
                }]
            })
            .state('wineSide.edit', {
                parent: 'wineSide',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/wineSide/wineSide-dialog.html',
                        controller: 'WineSideDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['WineSide', function(WineSide) {
                                return WineSide.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('wineSide', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('wineSide.delete', {
                parent: 'wineSide',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/wineSide/wineSide-delete-dialog.html',
                        controller: 'WineSideDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['WineSide', function(WineSide) {
                                return WineSide.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('wineSide', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
