angular.module('app').config(function($stateProvider,$urlRouterProvider){
    $stateProvider
    .state('index', {
        url: '/index',
        templateUrl: 'views/login.html'
    })
    .state('cadastro', {
        url: '/cadastro',
        templateUrl: 'views/cadastro.html'
    })
    .state('home', {
        url: '/home',
        abstract : true,
        templateUrl: 'views/home.html'
    })
    .state('home.horarios', {
        url: '/horarios',
        views : {
            'conteudo':{
                templateUrl: 'views/horarios.html'
            }
        }
    })
    .state('home.principal', {
        url: '/principal',
        views : {
            'conteudo':{
                templateUrl: 'views/principal.html'
            }
        }
    });
    $urlRouterProvider.otherwise('/index');
});