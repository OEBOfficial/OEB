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
import java.util.ArrayList;
import java.util.List;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 *
 * @author USER
 */
public class Employee {
    private int empNo;
    private String empName;
    private String gender;
    private String telNo;
    private Double specPay;
    private int empTypeNo;
    private int positionNo;
    private int branchNo;
    private String empTypeName;
    private String positionName;

    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public Double getSpecPay() {
        return specPay;
    }

    public void setSpecPay(Double specPay) {
        this.specPay = specPay;
    }

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

    public String getEmpTypeName() {
        return empTypeName;
    }

    public void setEmpTypeName(String empTypeName) {
        this.empTypeName = empTypeName;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    

    public int getBranchNo() {
        return branchNo;
    }

    public void setBranchNo(int branchNo) {
        this.branchNo = branchNo;
    }

    
    
    public static Employee getEmployee(int empNo){
        Employee e = null;
        try{
            Connection con = ConnectionBuilder.getConnection();
            String sql = "SELECT * FROM Employee e JOIN EmployeePosition ep ON e.positionNo = ep.positionNo "
                    + " JOIN EmploymentType et ON e.empTypeNo = et.empTypeNo "
                    + " WHERE e.empNo = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,empNo);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                e = new Employee();
                orm(rs,e);
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return e;
    }
    
    public static JsonObject getEmployeeJson(int empNo){
        JsonObjectBuilder empBuilder = Json.createObjectBuilder();
        Employee e = getEmployee(empNo);
        JsonObject empJO = null;
        if(e != null){
            empJO = empBuilder
                .add("empNo",e.getEmpNo())
                .add("empName", e.getEmpName())
                .add("branchNo", e.getBranchNo())
                .add("positionName", e.getPositionName())
                .add("positionNo", e.getPositionNo())
                .add("empTypeName", e.getEmpTypeName())
                .add("empTypeNo", e.getEmpTypeNo())
                .add("gender", e.getGender())
                .add("specPay", ""+e.getSpecPay())
                .add("telNo", e.getTelNo())
                .build();
        }
        return empJO;
    }
    
    public static List<Employee> getAllEmp(int branchNo){
        List<Employee> employees = null;
        try{
            Connection con = ConnectionBuilder.getConnection();
            String sql = "SELECT * FROM Employee e JOIN EmployeePosition ep ON e.positionNo = ep.positionNo "
                    + " JOIN EmploymentType et ON e.empTypeNo = et.empTypeNo "
                    + " WHERE e.branchNo = ? ORDER BY e.empName";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, branchNo);
            ResultSet rs = ps.executeQuery();
            employees = new ArrayList<Employee>();
            while(rs.next()){
                Employee e = new Employee();
                orm(rs,e);
                employees.add(e);
            }
            con.close();
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return employees;
    }
    
    public boolean addEmp(){
        boolean success = false;
        try{
            Connection con = ConnectionBuilder.getConnection();
            String sql = "INSERT INTO Employee(empName,gender,telNo,specPay,empTypeNO,positionNo,branchNo) "
                    + " VALUES(?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, empName);
            ps.setString(2, gender);
            ps.setString(3, telNo);
            if(specPay != null){
                ps.setDouble(4, specPay);
            }else{
                ps.setObject(4, null);
            }
            ps.setInt(5, empTypeNo);
            ps.setInt(6, positionNo);
            ps.setInt(7, branchNo);
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
    
    public boolean editEmp(){
        boolean success = false;
        try{
            Connection con = ConnectionBuilder.getConnection();
            String sql = "UPDATE Employee SET empName = ?,gender = ?,telNo = ?,specPay = ?,empTypeNo = ?,positionNo = ? WHERE empNo = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, empName);
            ps.setString(2, gender);
            ps.setString(3, telNo);
            if(specPay != null){
                ps.setDouble(4, specPay);
            }else{
                ps.setObject(4, null);
            }
            ps.setInt(5, empTypeNo);
            ps.setInt(6, positionNo);
            ps.setInt(7, empNo);
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
    
    public static boolean delEmp(int empNo){
        boolean success = false;
        try{
            Connection con = ConnectionBuilder.getConnection();
            String sql = "DELETE FROM Employee WHERE empNo = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, empNo);
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
    
    private static void orm(ResultSet rs,Employee e) throws SQLException{
        e.setEmpTypeNo(rs.getInt("empTypeNo"));
        e.setEmpName(rs.getString("empName"));
        e.setEmpNo(rs.getInt("empNo"));
        e.setPositionNo(rs.getInt("positionNo"));
        e.setBranchNo(rs.getInt("branchNo"));
        e.setGender(rs.getString("gender"));
        if(rs.getObject("specPay") == null){
            e.setSpecPay(null);
        }else{
            e.setSpecPay(rs.getDouble("specPay"));
        }
        e.setTelNo(rs.getString("telNo"));
        e.setEmpTypeName(rs.getString("empTypeName"));
        e.setPositionName(rs.getString("positionName"));
    }

    @Override
    public String toString() {
        return "Employee{" + "empNo=" + empNo + ", empName=" + empName + ", gender=" + gender + ", telNo=" + telNo + ", specPay=" + specPay + ", empTypeNo=" + empTypeNo + ", positionNo=" + positionNo + ", branchNo=" + branchNo + ", empTypeName=" + empTypeName + ", positionName=" + positionName + '}';
    }
}
