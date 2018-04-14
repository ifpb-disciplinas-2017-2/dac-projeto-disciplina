angular.module('app').controller('novaDuvidaCtrl',function($scope,alunoAPI,$ionicPopup,$state,duvidaAPI){
    $scope.turmas = [];
    $scope.duvida = {};

    var usuario = localStorage.getItem("usuario");
    var jsonUsuario = angular.fromJson(usuario);
    var idAluno = jsonUsuario.id;

    alunoAPI.listTurmasAluno(idAluno).then(function(response){
        $scope.turmas = response.data;
    },function(response){
        console.log("NÃO FOI POSSÍVEL LISTAR AS TURMAS DO ALUNO");
    });
    
    $scope.cadastrar = function(duvida){
        duvida.idAluno = idAluno;
        duvidaAPI.cadastrar(duvida).then(function(response){
            $state.go('home.duvidas');
        }, function(response){
            alertPopup = $ionicPopup.alert({
                title: "Erro",
                subTitle: "Não foi possível cadastrar a dúvida"
            });
        });
    };

});