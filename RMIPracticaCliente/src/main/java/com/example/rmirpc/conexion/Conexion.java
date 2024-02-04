/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.rmirpc.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import lombok.Getter;

/**
 *
 * @author USUARIO
 */
@Getter
public class Conexion 
{
    static String login = "postgres";
    static String password = "123";
    static String url = "jdbc:postgresql://localhost/rmirpc";

    private Connection conexion;

    public Conexion() throws Exception {
        conexion = null;
        Class.forName("org.postgresql.Driver");
        conexion = DriverManager.getConnection(url, login, password);
        if (conexion != null) {
            System.out.println("Conexion a la base de datos " + url + " ......OK");
        }
    }
}
