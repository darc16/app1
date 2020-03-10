(function(){
    angular.module('app.features')
        .controller('addIndicator', ['$timeout', '$stateParams', 'ReportFactory', '$scope', '$window', Callback])

    function Callback($timeout, $stateParams, ReportFactory, $scope, $window) {

        let addIndicatorVM = this;

        addIndicatorVM.save = save
        addIndicatorVM.reset = reset;
        addIndicatorVM.validate = validate;
        // addIndicatorVM.lookupData = lookupMap;

        addIndicatorVM.indicatorTypes = [
            {name:"IP", id:2},
            {name:"Domain", id:1},
            {name:"Email", id:3}
        ]

        addIndicatorVM.classifications= [
            {name:"U", id:2},
            {name:"FOUO", id:1},
            {name:"S", id:3}
        ]


        function save() {

            // Create a map that will hold the new report info
            let addReportDTO = {
                'name':     addIndicatorVM.new.display_name,
                'priority': addIndicatorVM.new.priority
            }

            ReportFactory.addReport(addReportDTO).then(function (res) {
                // The REST worked  (it returned a status between 200-299)
                console.log('REST call succeeded.  returned info is res=', res);

            })
                .catch(function (res) {
                    // The REST failed  (it returned a status code outside of 200-299)
                    console.log('REST call failed.  returned info is res=', res);

                })
                .finally(function () {
                    // This method is always called
                    console.log('REST call finally() was reached.');

                });
        }

        function reset(){
            $scope.myForm.$setPristine();
            $scope.myForm.$setUntouched();
        }

        function validate() {
            if ($scope.myForm.customName.$viewValue.toLowerCase() === 'good') {
                $scope.myForm.customName.$setValidity("good", true);
            } else {
                $scope.myForm.customName.$setValidity("good", false);
            }
        }

        window.document.title = "Add Report | APP1";

        addIndicatorVM.$onInit = function() {
        };

        console.log('addReport controller finished.');
    }
})();