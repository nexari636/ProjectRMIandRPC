angular.module("app")
.controller("rest_controller_compras", function ($scope, $http) {

    $scope.productos=undefined;
    $scope.url='http://localhost:8081';

    $scope.cantidad="";
    $scope.productosC=undefined;

    $scope.productoslst=undefined;

    $(document).ready(function ()
    {
        consumer_rest();
    });


    function consumer_rest(){
        let id=0;
        if(localStorage.getItem("idcliente")!=null)
            id=localStorage.getItem("idcliente");
        else console.log("Error no tiene id guardado");
        $.ajax({
            method:"POST",
            url:$scope.url+"/api/compras/listar?id="+id,
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
                    $scope.productoslst=JSON.parse(data);
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
        let id=0;
        if(localStorage.getItem("idcliente")!=null)
            id=localStorage.getItem("idcliente");
        let formData=new FormData();
        formData.append("idcliente",id);
        formData.append("idproducto",form.productosC.$viewValue);
        formData.append("cantidad",form.cantidad.$viewValue);
        console.log(form);
        $.ajax({
            method:"POST",
            url:$scope.url+"/api/compras/insertar",
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