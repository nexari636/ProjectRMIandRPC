/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.rmirpc.rmi;

import com.example.rmirpc.conexion.ManejoDatos;
import com.example.rmirpc.models.Cliente;
import com.example.rmirpc.models.Compras;
import com.example.rmirpc.models.Productos;
import com.example.rmirpc.rpc.RPCConeccion;
import com.google.gson.Gson;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author USUARIO
 */
public class Data extends UnicastRemoteObject implements IbaseData
{
    public Data() throws RemoteException{};
    
    @Override
    public String select(String index) throws RemoteException {
        ManejoDatos md=new ManejoDatos();
        String result=md.select(index);
        return result;
    }

    @Override
    public int guardar(String  json,String type) throws RemoteException 
    {
        try
        {
            Gson gson = new Gson();
            ManejoDatos md=new ManejoDatos();
            if(type.equals(Variables.CLIENTE))
            {
                Cliente c=gson.fromJson(json, Cliente.class);
                md.InsertarCliente(c);
                return 1;
            }
            else if(type.equals(Variables.PRODUCTO))
            {
                Productos p=gson.fromJson(json, Productos.class);
                md.InsertarProductos(p);
                return 1;
            }
            else
            {
                Compras c=gson.fromJson(json, Compras.class);
                md.InsertarCompras(c);
                return 1;
            }
        }catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return 0;
    }

    @Override
    public int conexionRPC(Long idcliente) 
    {
        if(idcliente!=null)
        {
            ManejoDatos md=new ManejoDatos();
            String jsoncompras=md.CompraPorCliente(idcliente);
            String jsonproductos=md.select(Variables.PRODUCTO);
            if(!jsoncompras.isEmpty() && jsoncompras!=null && !jsonproductos.isEmpty() && jsonproductos!=null)
            {
                RPCConeccion rpc=new RPCConeccion();
                rpc.RunRPC(jsoncompras,jsonproductos,idcliente);
                return 1;
            }
            else
                return 0;
        }else return 0;
    }

    @Override
    public String ListaClienteProductoHowCompra(Long idcliente) throws RemoteException {
        try
        {
            ManejoDatos md=new ManejoDatos();
            String jsonproductosHowCompra=md.CompraPorClienteProducto(idcliente);
            return jsonproductosHowCompra;
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return "0";
    }
}
