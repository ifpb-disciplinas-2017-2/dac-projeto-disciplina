angular.module('app').controller('duvidasCtrl',function($scope,$ionicPopup,$ionicModal,$state,horarioAPI,alunoAPI,duvidaAPI){
    $scope.filtroDuvidas = "usuario";
    $scope.duvidas = [];

    var usuario = localStorage.getItem("usuario");
    var jsonUsuario = angular.fromJson(usuario);
    var idAluno = jsonUsuario.id;

    duvidaAPI.listDuvidasAluno(idAluno).then(function(response){
        $scope.duvidas = response.data;
    }, function(response){
        alertPopup = $ionicPopup.alert({
            title: "Erro",
            subTitle: "Não foi possível listar as dúvidas do aluno"
        });
    });

    $scope.carregarDuvidasAluno = function(){
        $scope.filtroDuvidas = "usuario";
        duvidaAPI.listDuvidasAluno(idAluno).then(function(response){
            $scope.duvidas = response.data;
        }, function(response){
            alertPopup = $ionicPopup.alert({
                title: "Erro",
                subTitle: "Não foi possível listar as dúvidas do aluno"
            });
        });
    };
    $scope.carregarDuvidasColegas = function(){
        $scope.filtroDuvidas = "colegas";
        duvidaAPI.listDuvidasColegas(idAluno).then(function(response){
            $scope.duvidas = response.data;
        }, function(response){
            alertPopup = $ionicPopup.alert({
                title: "Erro",
                subTitle: "Não foi possível listar as dúvidas de colegas"
            });
        });
    };
    


    $ionicModal.fromTemplateUrl('verDuvida.html', {
        scope: $scope,
        animation: 'slide-in-up'
      }).then(function(modal) {
        $scope.modalVerDuvida = modal;
      });
      $scope.abrirVerDuvida = function(duvida) {
        $scope.duvidaVer = duvida;
        $scope.modalVerDuvida.show();
      };
      $scope.fecharVerDuvida = function() {
        $scope.modalVerDuvida.hide();
      };

        $ionicModal.fromTemplateUrl('responderDuvida.html', {
            scope: $scope,
            animation: 'slide-in-up'
        }).then(function(modal) {
            $scope.modalResponderDuvida = modal;
        });
        $scope.abrirResponderDuvida = function(duvida) {
            $scope.duvidaResponder = duvida;
            $scope.modalResponderDuvida.show();
        };
        $scope.fecharResponderDuvida = function() {
            $scope.modalResponderDuvida.hide();
        };
        $scope.responder = function(duvida){
            duvidaAPI.responderDuvida(idAluno,duvida.id,duvida.resposta).then(function(response){
                $scope.fecharResponderDuvida();
                $state.go("home.duvidas",{},{reload:true});
            }, function(response){
                alertPopup = $ionicPopup.alert({
                    title: "Erro",
                    subTitle: "Não foi possível responder a dúvida"
                }); 
            });
        };
    
});