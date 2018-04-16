angular.module('app').controller('turmasCtrl',function($scope,$ionicPopup,$state,horarioAPI,alunoAPI){
    $scope.turmas = [];

    var usuario = localStorage.getItem("usuario");
    var jsonUsuario = angular.fromJson(usuario);
    var idAluno = jsonUsuario.id;

    alunoAPI.listTurmasAluno(idAluno).then(function(response){
        $scope.turmas = response.data;
    },function(response){
        console.log("NÃO FOI POSSÍVEL LISTAR AS TURMAS DO ALUNO");
    });
    
});