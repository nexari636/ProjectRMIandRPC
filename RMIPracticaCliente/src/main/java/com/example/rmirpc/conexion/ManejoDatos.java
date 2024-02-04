/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.rmirpc.conexion;

import com.example.rmirpc.models.Cliente;
import com.example.rmirpc.models.Compras;
import com.example.rmirpc.models.Productos;
import com.example.rmirpc.rmi.Variables;
import java.sql.CallableStatement;
import java.sql.ResultSet;

/**
 *
 * @author USUARIO
 */
public class ManejoDatos 
{
    private static String selectCliente="select COALESCE(array_to_json(array_agg(row_to_json(client.*))),'[]') as cjson from ( select * from cliente ) as client";
    private static String insertarCliente="INSERT INTO cliente (nombre, apellidos, correo, edad) VALUES (?,?,?,?) returning 1 as num";
    private static String selectProductos="select COALESCE(array_to_json(array_agg(row_to_json(prod.*))),'[]') as cjson from ( select * from productos) as prod";
    private static String insertarProductos="INSERT INTO productos (nombre,cantidad, preciounitario) VALUES (?,?,?) returning 1 as num";
    private static String selectCompras="select COALESCE(array_to_json(array_agg(row_to_json(comp.*))),'[]') as cjson from ( select * from compras ) as comp";
    private static String insertarCompras="INSERT INTO compras (idcliente, idproducto,cantidad) VALUES (?,?,?) returning 1 as num";
    private static String jsonCompraporCliente="select COALESCE(array_to_json(array_agg(row_to_json(comp.*))),'[]') as cjson from ( select * from compras where idcliente=?) as comp";
    private static String updateTotal="update cliente set total=? where idcliente=? returning 1";
    private static String compraClienteproducto="select COALESCE(array_to_json(array_agg(row_to_json(comp.*))),'[]') as cjson from ( select c.idcompra as idc,c.idcliente as idclienteC,c.idproducto as idproductoC,c.cantidad as cantidadC,p.idproducto as idproductoP, p.nombre as nombreP,p.cantidad as cantidadP,p.preciounitario as preciounitario,cc.idcliente as idclientecl,cc.nombre as nombrecl,cc.apellidos as apellidoscl, cc.correo as correocl,cc.edad as edadcl,cc.total as totalcl from compras c inner join cliente cc on c.idcliente =cc.idcliente inner join productos p on c.idproducto=p.idproducto where cc.idcliente=? ) as comp";
    
    /*Insertar*/
    
    public int InsertarCliente(Cliente cliente)
    {
        try
        {
           Conexion cxp = new Conexion();
           CallableStatement cs = cxp.getConexion().prepareCall(insertarCliente);
           cs.setString(1, cliente.getNombres());
           cs.setString(2, cliente.getApellidos());
           cs.setString(3, cliente.getCorreo());
           cs.setInt(4, cliente.getEdad());
           cs.execute();
           ResultSet rs=cs.getResultSet();
           rs.next();
           return rs.getInt("num");
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return 0;
    }
    
    
    public int InsertarProductos(Productos produc)
    {
        try
        {
           Conexion cxp = new Conexion();
           CallableStatement cs = cxp.getConexion().prepareCall(insertarProductos);
           cs.setString(1, produc.getNombre());
           cs.setFloat(3, produc.getPrecioUnitario());
           cs.setInt(2,produc.getCantidad());
           cs.execute();
           ResultSet rs=cs.getResultSet();
           rs.next();
           return rs.getInt("num");
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return 0;
    }

    
    
    public int InsertarCompras(Compras compras)
    {
        try
        {
           Conexion cxp = new Conexion();
           CallableStatement cs = cxp.getConexion().prepareCall(insertarCompras);
           cs.setInt(1,compras.getIdcliente());
           cs.setInt(2,compras.getIdproducto());
           cs.setInt(3,compras.getCantidad());
           cs.execute();
           ResultSet rs=cs.getResultSet();
           rs.next();
           return rs.getInt("num");
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return 0;
    }
    
    
    public int actualizarTotal(Long idcliente, Float total)
    {
        try
        {
           Conexion cxp = new Conexion();
           CallableStatement cs = cxp.getConexion().prepareCall(updateTotal);
           cs.setFloat(1,total);
           cs.setLong(2,idcliente);
           cs.execute();
//           ResultSet rs=cs.getResultSet();
//           rs.next();
           return 1;
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return 0;
    }
    
    
    
    /* Seleccionar */
    public String select(String tipo)
    {
        try
        {
           Conexion cxp = new Conexion();
           CallableStatement cs = cxp.getConexion().prepareCall(tipo.equals(Variables.CLIENTE)?selectCliente:tipo.equals(Variables.PRODUCTO)?selectProductos:selectCompras);
           cs.execute();
           ResultSet rs=cs.getResultSet();
           rs.next();
           return rs.getString("cjson");
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
    
    public String CompraPorCliente(Long id)
    {
        try
        {
           Conexion cxp = new Conexion();
           CallableStatement cs = cxp.getConexion().prepareCall(jsonCompraporCliente);
           cs.setLong(1,id);
           cs.execute();
           ResultSet rs=cs.getResultSet();
           rs.next();
           return rs.getString("cjson");
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
    
    public String CompraPorClienteProducto(Long id)
    {
        try
        {
           Conexion cxp = new Conexion();
           CallableStatement cs = cxp.getConexion().prepareCall(compraClienteproducto);
           cs.setLong(1,id);
           cs.execute();
           ResultSet rs=cs.getResultSet();
           rs.next();
           return rs.getString("cjson");
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
    
    
    
    
    
    public int prueba()
    {
        try
        {
           Conexion cxp = new Conexion();
           CallableStatement cs = cxp.getConexion().prepareCall("call procedure_name(?)");
           cs.setInt(1,100);
           cs.execute();
          
           return 1;
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return 0;
    }
    
    
}
