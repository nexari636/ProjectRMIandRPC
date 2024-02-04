/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.rmirpc.rmi;

import com.example.rmirpc.models.Cliente;
import com.example.rmirpc.models.Compras;
import com.example.rmirpc.models.Productos;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author USUARIO
 */
public interface IbaseData extends Remote 
{
    public String select(String index)throws RemoteException;
    public int guardar(String  json,String type)throws RemoteException;
    public int conexionRPC(Long idcliente)throws RemoteException;
    public String ListaClienteProductoHowCompra(Long idcliente)throws RemoteException;
}
