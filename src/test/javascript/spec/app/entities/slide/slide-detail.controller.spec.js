'use strict';

describe('Slide Detail Controller', function() {
    var $scope, $rootScope;
    var MockEntity, MockSlide, MockMenuPage;
    var createController;

    beforeEach(inject(function($injector) {
        $rootScope = $injector.get('$rootScope');
        $scope = $rootScope.$new();
        MockEntity = jasmine.createSpy('MockEntity');
        MockSlide = jasmine.createSpy('MockSlide');
        MockMenuPage = jasmine.createSpy('MockMenuPage');
        

        var locals = {
            '$scope': $scope,
            '$rootScope': $rootScope,
            'entity': MockEntity ,
            'Slide': MockSlide,
            'MenuPage': MockMenuPage
        };
        createController = function() {
            $injector.get('$controller')("SlideDetailController", locals);
        };
    }));


    describe('Root Scope Listening', function() {
        it('Unregisters root scope listener upon scope destruction', function() {
            var eventType = 'hongjieApp:slideUpdate';

            createController();
            expect($rootScope.$$listenerCount[eventType]).toEqual(1);

            $scope.$destroy();
            expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
        });
    });
});
