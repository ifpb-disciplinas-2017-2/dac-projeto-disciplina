angular.module("app").factory("alunoAPI", function($http, constants){
    var _save = function(aluno){
        return $http.post(constants.baseUrl + "/aluno", aluno);
    };
    var _login = function(email, senha){
        return $http.post(constants.baseUrl + "/aluno/login", "email="+email+"&senha="+senha,
        {
            headers:{
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        });
    };    
    return {        
        save : _save,
        login : _login,
    };
});