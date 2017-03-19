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
public class Branch {
    private int branchNo;
    private String branchName;
    private String branchDesc;
    private int restNo;
    private int addressNo;
    private int restOwnerNo;

    public int getRestNo() {
        return restNo;
    }

    public void setRestNo(int restNo) {
        this.restNo = restNo;
    }

    public int getAddressNo() {
        return addressNo;
    }

    public void setAddressNo(int addressNo) {
        this.addressNo = addressNo;
    }

    public int getRestOwnerNo() {
        return restOwnerNo;
    }

    public void setRestOwnerNo(int restOwnerNo) {
        this.restOwnerNo = restOwnerNo;
    }

    public int getBranchNo() {
        return branchNo;
    }

    public void setBranchNo(int branchNo) {
        this.branchNo = branchNo;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchDesc() {
        return branchDesc;
    }

    public void setBranchDesc(String branchDesc) {
        this.branchDesc = branchDesc;
    }
    
    public static Branch getBranch(int branchNo){
        Branch b = null;
        try{
            Connection con = ConnectionBuilder.getConnection();
            String sql = "SELECT * FROM Branch WHERE branchNo = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,branchNo);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                b = new Branch();
                orm(rs,b);
            }
            con.close();
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return b;
    }
    
    public static Branch getBranchByOwner(int restOwnerNo){
        Branch b = null;
        try{
            Connection con = ConnectionBuilder.getConnection();
            String sql = "SELECT * FROM Branch WHERE restOwnerNo = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,restOwnerNo);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                b = new Branch();
                orm(rs,b);
            }
            con.close();
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return b;
    }
    
    private static void orm(ResultSet rs,Branch b) throws SQLException{
        b.setBranchDesc(rs.getString("branchDesc"));
        b.setBranchName(rs.getString("branchName"));
        b.setBranchNo(rs.getInt("branchNo"));
        b.setRestOwnerNo(rs.getInt("restOwnerNo"));
        b.setBranchNo(rs.getInt("branchNo"));
        b.setAddressNo(rs.getInt("addressNo"));
    }
}
