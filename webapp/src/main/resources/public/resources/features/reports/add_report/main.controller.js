(function(){
    angular.module('app.features')
        .controller('addReport', ['$timeout', '$stateParams', 'ReportFactory', '$scope', '$window', 'lookupMap', Callback])

    function Callback($timeout, $stateParams, ReportFactory, $scope, $window, lookupMap) {

        let addReportVM = this;

        addReportVM.save = save
        addReportVM.reset = reset;
        addReportVM.validate = validate;
        addReportVM.lookupData = lookupMap;



        function save() {

            // Create a map that will hold the new report info
            let addReportDTO = {
                'name':     addReportVM.new.display_name,
                'priority': addReportVM.new.priority
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

        addReportVM.$onInit = function() {
        };

        console.log('addReport controller finished.');
    }
})();