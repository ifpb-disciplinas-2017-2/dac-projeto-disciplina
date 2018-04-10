angular.module("app").factory("authorizationInterceptor", function($location,$q){
    return {
        request : function(config){
            var authorization = angular.fromJson(localStorage.getItem("authorization"));
            if(authorization != null){
                config.headers.AUTHORIZATION = 'Bearer ' + authorization.token;
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