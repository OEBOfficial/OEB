/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author USER
 */
public class ConnectionBuilder {
    public static Connection getConnection(){
        Connection con = null;
        String url = "jdbc:mysql://iambighead.com:3306/OEB";
        String username = "OrderEatBill";
        String password = "oebproject2017";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url,username,password);
        }catch(Exception ex){
            System.out.println(ex);
        }
        System.gc();
        return con;
    }
}