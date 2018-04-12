angular.module("app").factory("authorizationInterceptor", function($location,$q){
    return {
        request : function(config){
            var usuario = angular.fromJson(localStorage.getItem("usuario"));
            if(usuario != null){
                config.headers.AUTHORIZATION = 'Bearer ' + usuario.token;
            }else{
                config.headers.AUTHORIZATION = 'Bearer ';
            }
            return config;
        },
        responseError : function(rejection){
            if(rejection.status === 401){
                $location.path("/index");
            }
            return $q.reject(rejection);
        }
    };
});