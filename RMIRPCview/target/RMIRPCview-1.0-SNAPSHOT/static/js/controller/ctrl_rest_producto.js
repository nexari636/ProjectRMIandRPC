angular.module("app")
.controller("rest_controller_producto", function ($scope, $http) {

    $scope.productos=undefined;
    $scope.url='http://localhost:8081';

    $scope.nombre="";
    $scope.cantidad="";
    $scope.preciounitario="";

    $(document).ready(function ()
    {
        consumer_rest();
    });


    function consumer_rest(){
        $.ajax({
            method:"POST",
            url:$scope.url+"/api/producto/listar",
            processData:false,
            contentType: false,
            beforeSend: function (xhr) {
                console.log("cargando...");
            },
            success: function (data) {
                $scope.$apply(function() 
                {
                    console.log(JSON.parse(data))
                    $scope.productos=JSON.parse(data);
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
        formData.append("nombre",form.nombre.$viewValue);
        formData.append("cantidad",form.cantidad.$viewValue);
        formData.append("precioUnitario",form.preciounitario.$viewValue);
        console.log(form);
        $.ajax({
            method:"POST",
            url:$scope.url+"/api/producto/insertar",
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