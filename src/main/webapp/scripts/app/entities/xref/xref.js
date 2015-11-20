'use strict';

angular.module('hongjieApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('xref', {
                parent: 'entity',
                url: '/xrefs',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'Xrefs'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/xref/xrefs.html',
                        controller: 'XrefController'
                    }
                },
                resolve: {
                }
            })
            .state('xref.detail', {
                parent: 'entity',
                url: '/xref/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'Xref'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/xref/xref-detail.html',
                        controller: 'XrefDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'Xref', function($stateParams, Xref) {
                        return Xref.get({id : $stateParams.id});
                    }]
                }
            })
            .state('xref.new', {
                parent: 'xref',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/xref/xref-dialog.html',
                        controller: 'XrefDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    name: null,
                                    ovalue: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('xref', null, { reload: true });
                    }, function() {
                        $state.go('xref');
                    })
                }]
            })
            .state('xref.edit', {
                parent: 'xref',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/xref/xref-dialog.html',
                        controller: 'XrefDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['Xref', function(Xref) {
                                return Xref.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('xref', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('xref.delete', {
                parent: 'xref',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/xref/xref-delete-dialog.html',
                        controller: 'XrefDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['Xref', function(Xref) {
                                return Xref.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('xref', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
