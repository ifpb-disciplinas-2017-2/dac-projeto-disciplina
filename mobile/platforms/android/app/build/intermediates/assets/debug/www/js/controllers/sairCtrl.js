angular.module('app').controller('sairCtrl',function($scope,$state){
    
    $scope.sair = function(){
        localStorage.clear();
        $state.go('index');
    };
    
});