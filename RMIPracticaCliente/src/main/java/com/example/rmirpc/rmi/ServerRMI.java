/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.example.rmirpc.rmi;

import com.example.rmirpc.conexion.ManejoDatos;
import java.net.InetAddress;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author USUARIO
 */
public class ServerRMI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        try
        {
            Registry rs=LocateRegistry.createRegistry(3331);
            rs.rebind("serviceInfo1", new Data());
            System.out.println("Servidor uno activo= "+InetAddress.getLocalHost().getHostName());
        }catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    
}
