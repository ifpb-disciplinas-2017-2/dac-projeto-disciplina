angular.module("app").factory("horarioAPI", function($http, constants){
    
    var _getHorarioSala = function(nome){
        return $http.get(constants.baseUrl + "/horarios/sala/"+nome);
    };
    var _getHorarioLaboratorio = function(nome){
        return $http.get(constants.baseUrl + "/horarios/laboratorio/"+nome);
    };
    var _getHorarioProfessor = function(nome){
        return $http.get(constants.baseUrl + "/horarios/professor/"+nome);
    };
    var _getHorarioCurso = function(curso,disciplina){
        return $http.get(constants.baseUrl + "/horarios/"+curso+"/"+disciplina);
    };
    var _getHorarioTurma = function(disciplina, professor){
        return $http.get(constants.baseUrl + "/horarios/turma/"+disciplina+"/"+professor);
    };
    
    return {
        getHorarioSala : _getHorarioSala,
        getHorarioLaboratorio : _getHorarioLaboratorio,
        getHorarioProfessor : _getHorarioProfessor,
        getHorarioCurso : _getHorarioCurso,
        getHorarioTurma : _getHorarioTurma
    };
});