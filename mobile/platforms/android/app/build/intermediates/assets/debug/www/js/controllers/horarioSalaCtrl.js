angular.module('app').controller('horarioSalaCtrl',function($scope,$ionicPopup,$state,horarioAPI,salaAPI){
    $scope.salas = [];
    $scope.filtroDia = "SEGUNDA-FEIRA";
    salaAPI.list().then(function(response){
        $scope.salas = response.data;
    },function(response){
        console.log("NÃO FOI POSSÍVEL LISTAR AS SALAS");
    });

    $scope.buscar = function(nome){
        horarioAPI.getHorarioSala(nome).then(function(response){
            $scope.horario = response.data;
        },function(response){
            console.log("NÃO FOI POSSÍVEL BUSCAR OS HORÁRIOS DA SALA");
        });
    }   

    
});