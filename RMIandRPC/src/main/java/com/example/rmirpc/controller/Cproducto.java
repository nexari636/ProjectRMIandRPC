/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.rmirpc.controller;

import com.example.rmirpc.models.Cliente;
import com.example.rmirpc.models.Productos;
import com.example.rmirpc.rmi.IbaseData;
import com.example.rmirpc.rmi.Variables;
import com.google.gson.Gson;
import java.rmi.Naming;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author USUARIO
 */
@RestController
@RequestMapping("/api/producto")
@CrossOrigin("*")
public class Cproducto 
{
    
    @PostMapping("/listar")
    public ResponseEntity<?> listar()
    {
        Map<String,Object> response=new HashMap();
        try
        {
            System.out.println("holaaaaaaaaaaaaaaaa");
            IbaseData dd2=(IbaseData) Naming.lookup("//localhost:3331/serviceInfo1");
            
            String json=dd2.select(Variables.PRODUCTO);
            System.out.println(json);
            return new ResponseEntity<String>(json,HttpStatus.OK);
        }
        catch(Exception ex)
        {
            response.put("Error", ex.getMessage());
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
        }
    }
    
    
    
    @PostMapping("/insertar")
    public ResponseEntity<?> insertar(Productos producto)
    {
        Map<String,Object> response=new HashMap();
        try
        {
            Gson gson = new Gson();
            System.out.println("holaaaaaaaaaaaaaaaa "+producto.getPrecioUnitario());
            IbaseData dd2=(IbaseData) Naming.lookup("//localhost:3331/serviceInfo1");
            String jsonProducto=gson.toJson(producto);
            int res=dd2.guardar(jsonProducto,Variables.PRODUCTO);
            System.out.println(jsonProducto);
            return new ResponseEntity<Integer>(res,HttpStatus.OK);
        }
        catch(Exception ex)
        {
            response.put("Error", ex.getMessage());
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
        }
    }
    
    
    
    
    
    
}
