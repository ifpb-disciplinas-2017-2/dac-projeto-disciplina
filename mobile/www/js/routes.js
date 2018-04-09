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
    .state('home.horarioSala', {
        url: '/horarioSala',
        views : {
            'conteudo':{
                templateUrl: 'views/horarioSala.html'
            }
        }
    })
    .state('home.horarioLab', {
        url: '/horarioLab',
        views : {
            'conteudo':{
                templateUrl: 'views/horarioLab.html'
            }
        }
    })
    .state('home.horarioProf', {
        url: '/horarioProf',
        views : {
            'conteudo':{
                templateUrl: 'views/horarioProf.html'
            }
        }
    })
    .state('home.horarioCurso', {
        url: '/horarioCurso',
        views : {
            'conteudo':{
                templateUrl: 'views/horarioCurso.html'
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