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
        url: '/horarioProf/:disciplina/:professor',
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
    .state('home.horarioTurma', {
        url: '/horarioTurma/:disciplina/:professor',
        views : {
            'conteudo':{
                templateUrl: 'views/horarioTurma.html'
            }
        }
    })
    .state('home.turmas', {
        url: '/turmas',
        views : {
            'conteudo':{
                templateUrl: 'views/turmas.html'
            }
        }
    })
    .state('home.cadastroTurma', {
        url: '/cadastroTurma',
        views : {
            'conteudo':{
                templateUrl: 'views/cadastroTurma.html'
            }
        }
    })
    .state('home.duvidas', {
        url: '/duvidas',
        views : {
            'conteudo':{
                templateUrl: 'views/duvidas.html'
            }
        }
    })
    .state('home.novaDuvida', {
        url: '/novaDuvida',
        views : {
            'conteudo':{
                templateUrl: 'views/novaDuvida.html'
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