angular.module("app").factory("professorAPI", function($http, constants){
    var _list = function(){
        return $http.get(constants.baseUrl + "/professor");
    };
    var _listProfessoresDisciplina = function(disciplina){
        return $http.get(constants.baseUrl + "/professor/"+disciplina);
    };
    
    return {
        list : _list,
        listProfessoresDisciplina : _listProfessoresDisciplina        
    };
});