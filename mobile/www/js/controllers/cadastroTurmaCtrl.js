angular.module('app').controller('cadastroTurmaCtrl',function($scope,$ionicPopup,$state,alunoAPI,cursoAPI,professorAPI){
    $scope.disciplinas = [];
    $scope.professores = [];
    
    var usuario = localStorage.getItem("usuario");
    var jsonUsuario = angular.fromJson(usuario);
    var nomeCurso = jsonUsuario.curso.info.descricao;
    
    cursoAPI.listDisciplinas(nomeCurso).then(function(response){
        $scope.disciplinas = response.data;
    },function(response){
        console.log("NÃO FOI POSSÍVEL LISTAR AS DISCIPLINAS DO CURSO");
    });

    $scope.buscarProfessores = function(nomeDisciplina){
        professorAPI.listProfessoresDisciplina(nomeDisciplina).then(function(response){
            $scope.professores = response.data;
        },function(response){
            console.log("NÃO FOI POSSÍVEL BUSCAR OS PROFESSORES DESSA DISCIPLINA");
        });
    }   
    $scope.cadastrar = function(disciplina,professor){
        alunoAPI.cadastroTurma(jsonUsuario.email,disciplina,professor).then(function(response){
            $state.go("home.turmas");
        },function(response){
            if(response.status === 400){
                $ionicPopup.alert({
                    title: 'Erro',
                    template: 'O aluno já está cadastrado nessa turma'
                });
            }else{
                $ionicPopup.alert({
                    title: 'Erro',
                    template: 'NÃO FOI POSSÍVEL CADASTRAR O ALUNO NA TURMA'
                });
            }
        });
    }
});