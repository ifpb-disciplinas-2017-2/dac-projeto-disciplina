angular.module("app").factory("duvidaAPI", function($http, constants){
    var _cadastrar = function(duvida){
        return $http.post(constants.baseUrl + "/duvida", duvida);
    };

    var _responderDuvida = function(idAluno,idDuvida,resposta){
        return $http.post(constants.baseUrl + 
            "/duvida/responder", "aluno="+idAluno+"&duvida="+idDuvida+"&resposta="+resposta,
        {
            headers:{
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        });
    };  
    
    var _listDuvidasAluno = function(idAluno){
        return $http.get(constants.baseUrl + "/duvida/aluno/"+idAluno);
    };

    var _listDuvidasColegas = function(idAluno){
        return $http.get(constants.baseUrl + "/duvida/aluno/"+idAluno+"/turmas");
    };

    return {
        cadastrar : _cadastrar,
        responderDuvida : _responderDuvida,
        listDuvidasAluno : _listDuvidasAluno,
        listDuvidasColegas : _listDuvidasColegas
    };
});