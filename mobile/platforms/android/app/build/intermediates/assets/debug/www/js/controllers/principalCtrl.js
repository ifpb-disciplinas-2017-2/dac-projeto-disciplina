angular.module('app').controller('principalCtrl',function($scope){
    
    var usuario = localStorage.getItem("usuario");
    $scope.aluno = angular.fromJson(usuario);
    

});