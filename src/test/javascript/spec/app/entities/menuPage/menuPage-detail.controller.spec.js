'use strict';

describe('MenuPage Detail Controller', function() {
    var $scope, $rootScope;
    var MockEntity, MockMenuPage, MockSlide;
    var createController;

    beforeEach(inject(function($injector) {
        $rootScope = $injector.get('$rootScope');
        $scope = $rootScope.$new();
        MockEntity = jasmine.createSpy('MockEntity');
        MockMenuPage = jasmine.createSpy('MockMenuPage');
        MockSlide = jasmine.createSpy('MockSlide');
        

        var locals = {
            '$scope': $scope,
            '$rootScope': $rootScope,
            'entity': MockEntity ,
            'MenuPage': MockMenuPage,
            'Slide': MockSlide
        };
        createController = function() {
            $injector.get('$controller')("MenuPageDetailController", locals);
        };
    }));


    describe('Root Scope Listening', function() {
        it('Unregisters root scope listener upon scope destruction', function() {
            var eventType = 'hongjieApp:menuPageUpdate';

            createController();
            expect($rootScope.$$listenerCount[eventType]).toEqual(1);

            $scope.$destroy();
            expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
        });
    });
});
