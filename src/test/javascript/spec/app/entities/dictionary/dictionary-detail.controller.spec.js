'use strict';

describe('Dictionary Detail Controller', function() {
    var $scope, $rootScope;
    var MockEntity, MockDictionary, MockXref;
    var createController;

    beforeEach(inject(function($injector) {
        $rootScope = $injector.get('$rootScope');
        $scope = $rootScope.$new();
        MockEntity = jasmine.createSpy('MockEntity');
        MockDictionary = jasmine.createSpy('MockDictionary');
        MockXref = jasmine.createSpy('MockXref');
        

        var locals = {
            '$scope': $scope,
            '$rootScope': $rootScope,
            'entity': MockEntity ,
            'Dictionary': MockDictionary,
            'Xref': MockXref
        };
        createController = function() {
            $injector.get('$controller')("DictionaryDetailController", locals);
        };
    }));


    describe('Root Scope Listening', function() {
        it('Unregisters root scope listener upon scope destruction', function() {
            var eventType = 'hongjieApp:dictionaryUpdate';

            createController();
            expect($rootScope.$$listenerCount[eventType]).toEqual(1);

            $scope.$destroy();
            expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
        });
    });
});
