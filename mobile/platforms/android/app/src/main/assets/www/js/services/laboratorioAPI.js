angular.module("app").factory("laboratorioAPI", function($http, constants){
    var _list = function(){
        return $http.get(constants.baseUrl + "/laboratorio");
    };
    
    return {
        list : _list,
        // get : _get,
    };
});