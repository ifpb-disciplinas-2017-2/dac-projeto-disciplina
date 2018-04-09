angular.module("app").factory("professorAPI", function($http, constants){
    var _list = function(){
        return $http.get(constants.baseUrl + "/professor");
    };
    
    return {
        list : _list,
        // get : _get,
    };
});