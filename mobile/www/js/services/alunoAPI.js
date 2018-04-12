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
    var _cadastroTurma = function(email, disciplina, professor){
        return $http.post(constants.baseUrl + 
            "/aluno/turma", "aluno="+email+"&disciplina="+disciplina+"&professor="+professor,
        {
            headers:{
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        });
    };  
    var _listTurmasAluno = function(idAluno){
        return $http.get(constants.baseUrl + "/aluno/" + idAluno + "/turmas");
    };
    return {        
        save : _save,
        login : _login,
        cadastroTurma : _cadastroTurma,
        listTurmasAluno : _listTurmasAluno
    };
});