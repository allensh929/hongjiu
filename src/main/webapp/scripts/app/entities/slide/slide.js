'use strict';

angular.module('hongjieApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('slide', {
                parent: 'entity',
                url: '/slides',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'Slides'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/slide/slides.html',
                        controller: 'SlideController'
                    }
                },
                resolve: {
                }
            })
            .state('slide.detail', {
                parent: 'entity',
                url: '/slide/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'Slide'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/slide/slide-detail.html',
                        controller: 'SlideDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'Slide', function($stateParams, Slide) {
                        return Slide.get({id : $stateParams.id});
                    }]
                }
            })
            .state('slide.new', {
                parent: 'slide',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/slide/slide-dialog.html',
                        controller: 'SlideDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    name: null,
                                    url: null,
                                    description: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('slide', null, { reload: true });
                    }, function() {
                        $state.go('slide');
                    })
                }]
            })
            .state('slide.edit', {
                parent: 'slide',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/slide/slide-dialog.html',
                        controller: 'SlideDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['Slide', function(Slide) {
                                return Slide.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('slide', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('slide.delete', {
                parent: 'slide',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/slide/slide-delete-dialog.html',
                        controller: 'SlideDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['Slide', function(Slide) {
                                return Slide.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('slide', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
