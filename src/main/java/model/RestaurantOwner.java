/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author USER
 */
public class RestaurantOwner {
    private int restOwnerNo;
    private String restUserName;
    private String restPassword;
    private int branchNo;

    public int getBranchNo() {
        return branchNo;
    }

    public void setBranchNo(int branchNo) {
        this.branchNo = branchNo;
    }

    public int getRestOwnerNo() {
        return restOwnerNo;
    }

    public void setRestOwnerNo(int restOwnerNo) {
        this.restOwnerNo = restOwnerNo;
    }

    public String getRestUserName() {
        return restUserName;
    }

    public void setRestUserName(String restUserName) {
        this.restUserName = restUserName;
    }

    public String getRestPassword() {
        return restPassword;
    }

    public void setRestPassword(String restPassword) {
        this.restPassword = restPassword;
    }
    
    public static RestaurantOwner getRestaurantOwner(int restOwnerNo){
        RestaurantOwner ro = null;
        try{
            Connection con = ConnectionBuilder.getConnection();
            String sql = "SELECT * FROM RestaurantOwner WHERE restOwnerNo = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,restOwnerNo);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                ro = new RestaurantOwner();
                orm(rs,ro);
            }
            con.close();
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return ro;
    }
    
    public static RestaurantOwner logIn(String username,String password){
        RestaurantOwner ro = null;
        try{
            Connection con = ConnectionBuilder.getConnection();
            String sql = "SELECT * FROM RestaurantOwner ro "
                    + " JOIN Branch b ON ro.restOwnerNo = b.restOwnerNo "
                    + " WHERE LOWER(restUserName) = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username.toLowerCase());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                if(ActorManagement.decryptPassword(rs.getString("restPassword")).equals(password)){
                    ro = new RestaurantOwner();
                    orm(rs,ro);
                }
            }
            con.close();
        }catch(SQLException ex){
            System.out.println(ex);
        }        
        return ro;
    }
    
    private static void orm(ResultSet rs,RestaurantOwner ro) throws SQLException, SQLException, SQLException, SQLException, SQLException {
        ro.setRestOwnerNo(rs.getInt("restOwnerNo"));
        ro.setRestUserName(rs.getString("restUserName"));
        ro.setBranchNo(rs.getInt("branchNo"));
    }
    
    public static RestaurantOwner signInForCookie(String cookieVal){
        RestaurantOwner ro = null;
        try{
            Connection con = ConnectionBuilder.getConnection();
            String sql = "SELECT * FROM RestaurantOwner ro"
                    + " JOIN Branch b ON ro.restOwnerNo = b.restOwnerNo "
                    + " WHERE LOWER(restUserName) = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cookieVal.toLowerCase());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                ro = new RestaurantOwner();
                orm(rs,ro);
            }
            con.close();
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return ro;
    }
}
