angular.module('app').controller('horarioProfCtrl',function($scope,$ionicPopup,$state,horarioAPI,professorAPI){
    $scope.professores = [];
    $scope.filtroDia = "SEGUNDA-FEIRA";
    professorAPI.list().then(function(response){
        $scope.professores = response.data;
    },function(response){
        console.log("NÃO FOI POSSÍVEL LISTAR OS PROFESSORES");
    });

    $scope.buscar = function(nome){
        horarioAPI.getHorarioProfessor(nome).then(function(response){
            $scope.horario = response.data;
        },function(response){
            console.log("NÃO FOI POSSÍVEL BUSCAR OS HORÁRIOS DO PROFESSOR");
        });
    }   

    function showAlert(titulo,subtitulo){
        alertPopup = $ionicPopup.alert({
            title: titulo,
            subTitle: subtitulo
        }).then(function(res) {
              $state.go('index');
        });
    };
    
});