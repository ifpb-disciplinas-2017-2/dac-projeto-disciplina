angular.module("app").factory("cursoAPI", function($http, constants){
    var _list = function(){
        return $http.get(constants.baseUrl + "/curso");
    };
    // var _get = function(id){
    //     return $http.get(config.baseUrl + "/notas/get/" + id);
    // };
    var _listDisciplinas = function(nome){
        return $http.get(constants.baseUrl + "/curso/"+nome+"/disciplinas");
    };
    
    return {
        list : _list,
        listDisciplinas : _listDisciplinas
        // get : _get,
    };
});