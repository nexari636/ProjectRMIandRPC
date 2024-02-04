/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.rmirpc.controller;


import com.example.rmirpc.models.Cliente;
import com.example.rmirpc.rmi.IbaseData;
import com.example.rmirpc.rmi.Variables;
import java.rmi.Naming;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.CrossOrigin;
/**
 *
 * @author USUARIO
 */
@RestController
@RequestMapping("/api/cliente")
@CrossOrigin("*")
public class Ccliente
{
    @PostMapping("/listar")
    public ResponseEntity<?> listar()
    {
        Map<String,Object> response=new HashMap();
        try
        {
            IbaseData dd2=(IbaseData) Naming.lookup("//localhost:3331/serviceInfo1");
            String json=dd2.select(Variables.CLIENTE);
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
    public ResponseEntity<?> insertar(Cliente cliente)
    {
        Map<String,Object> response=new HashMap();
        try
        {
            Gson gson = new Gson();
            IbaseData dd2=(IbaseData) Naming.lookup("//localhost:3331/serviceInfo1");
            String jsonCliente=gson.toJson(cliente);
            int res=dd2.guardar(jsonCliente,Variables.CLIENTE);
            return new ResponseEntity<Integer>(res,HttpStatus.OK);
        }
        catch(Exception ex)
        {
            response.put("Error", ex.getMessage());
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
        }
    }
}
