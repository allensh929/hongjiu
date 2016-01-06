'use strict';

angular.module('hongjieApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('info', {
                parent: 'entity',
                url: '/infos',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'Infos'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/info/infos.html',
                        controller: 'InfoController'
                    }
                },
                resolve: {
                }
            })
            .state('info.detail', {
                parent: 'entity',
                url: '/info/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'Info'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/info/info-detail.html',
                        controller: 'InfoDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'Info', function($stateParams, Info) {
                        return Info.get({id : $stateParams.id});
                    }]
                }
            })
            .state('info.new', {
                parent: 'info',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/info/info-dialog.html',
                        controller: 'InfoDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    qrCode: null,
                                    wechatSubscribeCode: null,
                                    wechatServiceCode: null,
                                    weiboUrl: null,
                                    qqUrl: null,
                                    placeholder1: null,
                                    placeholder2: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('info', null, { reload: true });
                    }, function() {
                        $state.go('info');
                    })
                }]
            })
            .state('info.edit', {
                parent: 'info',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/info/info-dialog.html',
                        controller: 'InfoDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['Info', function(Info) {
                                return Info.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('info', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('info.delete', {
                parent: 'info',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/info/info-delete-dialog.html',
                        controller: 'InfoDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['Info', function(Info) {
                                return Info.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('info', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
