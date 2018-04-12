angular.module('app').controller('loginCtrl',function($scope,alunoAPI,$ionicPopup,$state){
    $scope.usuario = {};
    $scope.logar = function(usuario){
        alunoAPI.login(usuario.email,usuario.senha).then(function(response){
            //SALVANDO USUÁRIO E TOKEN NO LOCALSTORAGE PARA USAR NO HEADERS DE OUTRAS REQUISIÇÕES
            localStorage.setItem("usuario",angular.toJson(response.data));
            $state.go('home.principal');
        },function(response){
            if(response.status === 403){
                showAlert('Erro','Usuário não cadastrado!');    
            }else if(response.status === 400){
                showAlert('Erro','Dados incorretos!');    
            }else if(response.status === 401){
                showAlert('Pedido enviado','Aguarde o coordenador liberar seu acesso');    
            }else{
                showAlert('Erro','Não foi possível realizar login!');    
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