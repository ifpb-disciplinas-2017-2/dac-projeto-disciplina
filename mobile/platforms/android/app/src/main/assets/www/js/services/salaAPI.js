angular.module("app").factory("salaAPI", function($http, constants){
    var _list = function(){
        return $http.get(constants.baseUrl + "/sala");
    };
    
    return {
        list : _list,
        // get : _get,
    };
});