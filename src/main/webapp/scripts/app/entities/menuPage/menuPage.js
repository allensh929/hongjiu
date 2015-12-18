'use strict';

angular.module('hongjieApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('menuPage', {
                parent: 'entity',
                url: '/menuPages',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'MenuPages'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/menuPage/menuPages.html',
                        controller: 'MenuPageController'
                    }
                },
                resolve: {
                }
            })
            .state('menuPage.detail', {
                parent: 'entity',
                url: '/menuPage/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'MenuPage'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/menuPage/menuPage-detail.html',
                        controller: 'MenuPageDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'MenuPage', function($stateParams, MenuPage) {
                        return MenuPage.get({id : $stateParams.id});
                    }]
                }
            })
            .state('menuPage.new', {
                parent: 'menuPage',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/menuPage/menuPage-dialog.html',
                        controller: 'MenuPageDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    name: null,
                                    pageId: null,
                                    url: null,
                                    detailInfo: null,
                                    active: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('menuPage', null, { reload: true });
                    }, function() {
                        $state.go('menuPage');
                    })
                }]
            })
            .state('menuPage.edit', {
                parent: 'menuPage',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/menuPage/menuPage-dialog.html',
                        controller: 'MenuPageDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['MenuPage', function(MenuPage) {
                                return MenuPage.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('menuPage', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('menuPage.delete', {
                parent: 'menuPage',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/menuPage/menuPage-delete-dialog.html',
                        controller: 'MenuPageDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['MenuPage', function(MenuPage) {
                                return MenuPage.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('menuPage', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
