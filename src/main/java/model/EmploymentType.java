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
public class EmploymentType {
    private int empTypeNo;
    private String empTypeName;

    public int getEmpTypeNo() {
        return empTypeNo;
    }

    public void setEmpTypeNo(int empTypeNo) {
        this.empTypeNo = empTypeNo;
    }

    public String getEmpTypeName() {
        return empTypeName;
    }

    public void setEmpTypeName(String empTypeName) {
        this.empTypeName = empTypeName;
    }
    
    public static EmploymentType getEmployementType(int empTypeNo){
        EmploymentType et = null;
        try{
            Connection con = ConnectionBuilder.getConnection();
            String sql = "SELECT * FROM EmploymentType WHERE empTypeNo = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,empTypeNo);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                et = new EmploymentType();
                orm(rs,et);
            }
            con.close();
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return et;
    }
    
    private static void orm(ResultSet rs,EmploymentType et) throws SQLException{
        et.setEmpTypeName(rs.getString("empTypeName"));
        et.setEmpTypeNo(rs.getInt("empTypeNo"));
    }
}
