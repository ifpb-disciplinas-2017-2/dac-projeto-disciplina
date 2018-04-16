angular.module('app').controller('horarioLabCtrl',function($scope,$ionicPopup,$state,horarioAPI,laboratorioAPI){
    $scope.laboratorios = [];
    $scope.filtroDia = "SEGUNDA-FEIRA";
    laboratorioAPI.list().then(function(response){
        $scope.laboratorios = response.data;
    },function(response){
        console.log("NÃO FOI POSSÍVEL LISTAR OS LABORATÓRIOS");
    });

    $scope.buscar = function(nome){
        horarioAPI.getHorarioLaboratorio(nome).then(function(response){
            $scope.horario = response.data;
        },function(response){
            console.log("NÃO FOI POSSÍVEL BUSCAR OS HORÁRIOS DO LABORATÓRIO");
        });
    }   
    
});