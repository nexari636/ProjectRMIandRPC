angular.module("app",["ngRoute"])
        .config(function($routeProvider)
        {
           $routeProvider
                .when("/cliente",{
                   controller: "rest_controller_cliente",
                   templateUrl: "cliente.html"
               })
               .when("/producto",{
                    controller: "rest_controller_producto",
                    templateUrl: "producto.html"
                })
                .when("/compras",{
                    controller: "rest_controller_compras",
                    templateUrl: "compras.html"
                })
                .otherwise({
                   redirectTo:"/"
               });
        });

//http://localhost:8080/RMIRPCview/app.html#!/rest