angular.module('app').controller('horarioCursoCtrl',function($scope,$ionicPopup,$state,horarioAPI,cursoAPI){
    $scope.cursos = [];
    $scope.disciplinas = [];
    $scope.filtroDia = "SEGUNDA-FEIRA";

    cursoAPI.list().then(function(response){
        $scope.cursos = response.data;
    },function(response){
        console.log("NÃO FOI POSSÍVEL LISTAR OS CURSOS");
    });

    $scope.carregarDisciplinas = function(curso){
        cursoAPI.listDisciplinas(curso).then(function(response){
            $scope.disciplinas = response.data;     
        }, function(response){
            console.log("NÃO FOI POSSÍVEL LISTAR AS DISCIPLINAS DESSE CURSO");
        });
    }   

    $scope.buscar = function(curso,disciplina){
        horarioAPI.getHorarioCurso(curso,disciplina).then(function(response){
            $scope.horario = response.data;
        },function(response){
            console.log("NÃO FOI POSSÍVEL BUSCAR OS HORÁRIOS DO CURSO");
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