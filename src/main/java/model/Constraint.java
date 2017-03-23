    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;

/**
 *
 * @author USER
 */
public class Constraint{
    private int empTypeNo;
    private int positionNo;
    private double hoursPerDay;
    private double pay;

    public int getEmpTypeNo() {
        return empTypeNo;
    }

    public void setEmpTypeNo(int empTypeNo) {
        this.empTypeNo = empTypeNo;
    }

    public int getPositionNo() {
        return positionNo;
    }

    public void setPositionNo(int positionNo) {
        this.positionNo = positionNo;
    }

    public double getHoursPerDay() {
        return hoursPerDay;
    }

    public void setHoursPerDay(double hourPerDay) {
        this.hoursPerDay = hourPerDay;
    }

    public double getPay() {
        return pay;
    }

    public void setPay(double pay) {
        this.pay = pay;
    }
    
    public static JsonArray getAllConJson(int positionNo){
        JsonArrayBuilder JAB = Json.createArrayBuilder();
        JsonObjectBuilder JOB = Json.createObjectBuilder();
        JsonArray JA = null;
        List<Constraint> constraints = getAllConstraint(positionNo);
        if(constraints != null){
            JA = JAB.build();
            for(int i = 0 ; i < constraints.size() ; i++){
                JsonObject jo = JOB
                        .add("empTypeNo", constraints.get(i).getEmpTypeNo())
                        .add("hoursPerDay", constraints.get(i).getHoursPerDay())
                        .add("pay", constraints.get(i).getPay())
                        .build();
                JAB.add(jo);
            }
            JA = JAB.build();
        }
        return JA;
    }
    
    public boolean addConstraint(){
        boolean success = false;
        try{
            Connection con = ConnectionBuilder.getConnection();
            String sql = "INSERT INTO Constraint(empTypeNo,positionNo,hoursPerDay,pay) VALUES(?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, empTypeNo);
            ps.setInt(2, positionNo);
            ps.setDouble(3, hoursPerDay);
            ps.setDouble(4, pay);
            int i = ps.executeUpdate();
            if(i > 0){
                success = true;
            }
            con.close();
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return success;
    }
    
    public boolean editConstraint(){
        boolean success = false;
        try{
            Connection con = ConnectionBuilder.getConnection();
            String sql = "UPDATE Constraint SET hoursPerDay = ?,pay = ? WHERE empTypeNo = ? AND positionNo = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDouble(1, hoursPerDay);
            ps.setDouble(2, pay);
            ps.setInt(3, empTypeNo);
            ps.setInt(4, positionNo);
            int i = ps.executeUpdate();
            if(i > 0){
                success = true;
            }
            con.close();
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return success;
    }
    
    public static boolean delConstraint(int empTypeNo,int positionNo){
        boolean success = false;
        try{
            Connection con = ConnectionBuilder.getConnection();
            String sql = "DELETE FROM Constraint WHERE empTypeNo = ? AND positionNo = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, empTypeNo);
            ps.setInt(2, positionNo);
            int i = ps.executeUpdate();
            if(i > 0){
                success = true;
            }
            con.close();
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return success;
    }
    
    public static List<Constraint> getAllConstraint(int positionNo){
        List<Constraint> constraints = null;
        try{
            Connection con = ConnectionBuilder.getConnection();
            String sql = "SELECT * FROM `Constraint`  WHERE positionNo = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, positionNo);
            ResultSet rs = ps.executeQuery();
            constraints = new LinkedList<Constraint>();
            while(rs.next()){
                Constraint c = new Constraint();
                orm(rs,c);
                constraints.add(c);
            }
            con.close();
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return constraints;
    }
    
    private static void orm(ResultSet rs,Constraint c) throws SQLException{
        c.setEmpTypeNo(rs.getInt("empTypeNo"));
        c.setHoursPerDay(rs.getDouble("hoursPerDay"));
        c.setPay(rs.getDouble("pay"));
        c.setPositionNo(rs.getInt("positionNo"));
    }
}
