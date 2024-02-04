/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.rmirpc.rpc;

import com.example.rmirpc.conexion.ManejoDatos;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

/**
 *
 * @author USUARIO
 */
public class RPCConeccion 
{
    public RPCConeccion(){}
    
    public void RunRPC(String jsoncompras,String jsonproductos,Long id)
    {
        try {
            XmlRpcClient client = new XmlRpcClient();
            XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
            config.setServerURL(new URL("http://localhost:9000/RPC2"));
            client.setConfig(config);
            Object[] params = new Object[2];
            params[0]=jsoncompras;
            params[1]=jsonproductos;
            Object result = client.execute("json_calculate",params);
            System.out.println(result.toString()+" respuesta de python");
            ActualizarRegistros(id,Float.parseFloat(result.toString()));
        } 
        catch (MalformedURLException ex) {
            Logger.getLogger(RPCConeccion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (XmlRpcException ex) {
            Logger.getLogger(RPCConeccion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void ActualizarRegistros(Long idcliente,float total)
    {
        ManejoDatos md=new ManejoDatos();
        md.actualizarTotal(idcliente, total);
        System.out.println("dsfsdffsdfsd "+total);
    }
}
