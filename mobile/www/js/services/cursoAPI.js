angular.module("app").factory("cursoAPI", function($http, constants){
    var _list = function(){
        return $http.get(constants.baseUrl + "/curso");
    };
    // var _get = function(id){
    //     return $http.get(config.baseUrl + "/notas/get/" + id);
    // };
    
    return {
        list : _list,
        // get : _get,
    };
});