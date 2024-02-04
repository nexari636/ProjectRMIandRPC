angular.module("app")
.controller("rest_controller_cliente", function ($scope, $http) {

    $scope.personas=undefined;
    $scope.url='http://localhost:8081';

    $scope.nombre="";
    $scope.apellido="";
    $scope.edad="";
    $scope.correo="";

    $(document).ready(function ()
    {
        consumer_rest();
    });


    function consumer_rest(){
        $.ajax({
            method:"POST",
            url:$scope.url+"/api/cliente/listar",
            processData:false,
            contentType: false,
            beforeSend: function (xhr) {
                console.log("cargando...");
            },
            success: function (data) {
                $scope.$apply(function() 
                {
                    console.log(JSON.parse(data))
                    $scope.personas=JSON.parse(data);
                });
                //console.log(data);
            },
            error: function (objXMLHttpRequest) 
            {
                console.log("error: ", objXMLHttpRequest);
            }
        });
    }



    $scope.guardar=(form)=> {
        console.log(form);
        
        let formData=new FormData();
        formData.append("nombres",form.nombre.$viewValue);
        formData.append("apellidos",form.apellido.$viewValue);
        formData.append("edad",form.edad.$viewValue);
        formData.append("correo",form.correo.$viewValue);
        console.log(form);
        $.ajax({
            method:"POST",
            url:$scope.url+"/api/cliente/insertar",
            processData:false,
            contentType: false,
            data:formData,
            beforeSend: function (xhr) {
                console.log("cargando...");
            },
            success: function (data) {
                $scope.$apply(function() 
                {
                    consumer_rest();
                });
                console.log(data);
            },
            error: function (objXMLHttpRequest) 
            {
                console.log("error: ", objXMLHttpRequest);
            }
        });
    }

    $scope.change_link=(id)=> {
        console.log(localStorage.getItem("idcliente"));
        if(localStorage.getItem("idcliente")!=null)
            localStorage.removeItem("idcliente")
        localStorage.setItem("idcliente",id);
        console.log(id);
        console.log(location.href);
        console.log(location.href.replace("cliente","compras"));
        location.href=location.href.replace("cliente","compras")
    }

    $scope.recalcularTotal=(id)=>
    {
        console.log(id);
        let formData=new FormData();
        formData.append("id",id);
        $.ajax({
            method:"POST",
            url:$scope.url+"/api/compras/calculate",
            processData:false,
            contentType: false,
            data:formData,
            beforeSend: function (xhr) {
                console.log("cargando...");
            },
            success: function (data) {
                $scope.$apply(function() 
                {
                    consumer_rest();
                });
                console.log(data);
            },
            error: function (objXMLHttpRequest) 
            {
                console.log("error: ", objXMLHttpRequest);
            }
        });
    }




    
});