angular.module('app').controller('horarioTurmaCtrl',function($scope,$ionicPopup,$state,horarioAPI,salaAPI,$stateParams){
    $scope.salas = [];
    $scope.filtroDia = "SEGUNDA-FEIRA";
    var disciplina = $stateParams.disciplina;
    var professor = $stateParams.professor;
    horarioAPI.getHorarioTurma(disciplina,professor).then(function(response){
        $scope.horario = response.data;
    },function(response){
        console.log("NÃO FOI POSSÍVEL BUSCAR OS HORÁRIOS DA TURMA");
    });
    
    
});