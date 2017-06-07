//check code I
package model;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionBuilder {
    public static Connection getConnection(){
        Connection con = null;
        String url = "jdbc:mysql://52.43.248.108:3306/OEB?characterEncoding=UTF-8";
        String username = "OrderEatBill";
        String password = "OEBProject";
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