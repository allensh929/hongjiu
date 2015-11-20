'use strict';

angular.module('hongjieApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('dictionary', {
                parent: 'entity',
                url: '/dictionarys',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'Dictionarys'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/dictionary/dictionarys.html',
                        controller: 'DictionaryController'
                    }
                },
                resolve: {
                }
            })
            .state('dictionary.detail', {
                parent: 'entity',
                url: '/dictionary/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'Dictionary'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/dictionary/dictionary-detail.html',
                        controller: 'DictionaryDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'Dictionary', function($stateParams, Dictionary) {
                        return Dictionary.get({id : $stateParams.id});
                    }]
                }
            })
            .state('dictionary.new', {
                parent: 'dictionary',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/dictionary/dictionary-dialog.html',
                        controller: 'DictionaryDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    identifer: null,
                                    ovalue: null,
                                    description: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('dictionary', null, { reload: true });
                    }, function() {
                        $state.go('dictionary');
                    })
                }]
            })
            .state('dictionary.edit', {
                parent: 'dictionary',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/dictionary/dictionary-dialog.html',
                        controller: 'DictionaryDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['Dictionary', function(Dictionary) {
                                return Dictionary.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('dictionary', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('dictionary.delete', {
                parent: 'dictionary',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/dictionary/dictionary-delete-dialog.html',
                        controller: 'DictionaryDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['Dictionary', function(Dictionary) {
                                return Dictionary.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('dictionary', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
