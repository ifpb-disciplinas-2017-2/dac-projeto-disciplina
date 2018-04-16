angular.module('app').controller('cadastroCtrl',function($scope,cursoAPI,alunoAPI,$ionicPopup,$state){
    $scope.cursos=[];
    $scope.aluno={};
    cursoAPI.list().then(function(response){
        $scope.cursos = response.data;
    },function(response){
        console.log("aconteceu um erro");
    });
    
    $scope.cadastrar = function(){
        alunoAPI.save($scope.aluno).then(function(response){
            showAlert('Sucesso','Seu cadastro foi realizado com sucesso!');
        },function(response){
            if(response.status === 400){
                showAlert('Erro','Já existe um usuário com esse email!');    
            }else{
                showAlert('Erro','Não foi possível realizar o seu cadastro!');
            }
        });
    };

    function showAlert(titulo,subtitulo){
        alertPopup = $ionicPopup.alert({
            title: titulo,
            subTitle: subtitulo
        }).then(function(res) {
              $state.go('index');
        });
    };
    
        
    
});