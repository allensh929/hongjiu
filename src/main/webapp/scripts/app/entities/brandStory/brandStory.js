'use strict';

angular.module('hongjieApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('brandStory', {
                parent: 'entity',
                url: '/brandStorys',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'BrandStorys'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/brandStory/brandStorys.html',
                        controller: 'BrandStoryController'
                    }
                },
                resolve: {
                }
            })
            .state('brandStory.detail', {
                parent: 'entity',
                url: '/brandStory/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'BrandStory'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/brandStory/brandStory-detail.html',
                        controller: 'BrandStoryDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'BrandStory', function($stateParams, BrandStory) {
                        return BrandStory.get({id : $stateParams.id});
                    }]
                }
            })
            .state('brandStory.new', {
                parent: 'brandStory',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/brandStory/brandStory-dialog.html',
                        controller: 'BrandStoryDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    name: null,
                                    title: null,
                                    slide1: null,
                                    slide2: null,
                                    slide3: null,
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
                        $state.go('brandStory', null, { reload: true });
                    }, function() {
                        $state.go('brandStory');
                    })
                }]
            })
            .state('brandStory.edit', {
                parent: 'brandStory',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/brandStory/brandStory-dialog.html',
                        controller: 'BrandStoryDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['BrandStory', function(BrandStory) {
                                return BrandStory.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('brandStory', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('brandStory.delete', {
                parent: 'brandStory',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/brandStory/brandStory-delete-dialog.html',
                        controller: 'BrandStoryDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['BrandStory', function(BrandStory) {
                                return BrandStory.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('brandStory', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
