/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author USER
 */
public class WorkingHistory {
    private int workNo;
    private int fromTime;
    private int toTime;
    private Date fromDate;
    private Date toDate;
    private String empTypeName;
    private String positionName;
    private int workingPay;
    private int empNo;
    private String empName;
    private int branchNo;

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }
    
    public int getWorkNo() {
        return workNo;
    }

    public void setWorkNo(int workNo) {
        this.workNo = workNo;
    }

    public int getFromTime() {
        return fromTime;
    }

    public void setFromTime(int fromTime) {
        this.fromTime = fromTime;
    }

    public int getToTime() {
        return toTime;
    }

    public void setToTime(int toTime) {
        this.toTime = toTime;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
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

    public int getWorkingPay() {
        return workingPay;
    }

    public void setWorkingPay(int workingPay) {
        this.workingPay = workingPay;
    }

    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    public int getBranchNo() {
        return branchNo;
    }

    public void setBranchNo(int branchNo) {
        this.branchNo = branchNo;
    }
    
    public static List<WorkingHistory> getAllWorkHist(int branchNo){
        List<WorkingHistory> workhist = null;
        try{
            Connection con = ConnectionBuilder.getConnection();
            String sql = "SELECT * FROM WorkingHistory wh "
                    + " JOIN Employee e ON e.empNo = wh.empNo "
                    + " WHERE wh.branchNo = ? AND wh.toTime IS NOT NULL "
                    + " ORDER BY wh.fromDate DESC,wh.toTime DESC";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, branchNo);
            ResultSet rs = ps.executeQuery();
            workhist = new LinkedList<WorkingHistory>();
            while(rs.next()){
                WorkingHistory wh = new WorkingHistory();
                orm(rs,wh);
                workhist.add(wh);                
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return workhist;
    }
    
    public static List<WorkingHistory> filterWorkByDate(String fromDate,String toDate){
        List<WorkingHistory> workhist = null;
        try{
            Connection con = ConnectionBuilder.getConnection();
            String sql = "SELECT * FROM WorkingHistory wh "
                    + " JOIN Employee e ON e.empNo = wh.empNo "
                    + " WHERE wh.fromDate BETWEEN ? AND ? AND wh.toTime IS NOT NULL "
                    + " ORDER BY wh.fromDate DESC,wh.toTime DESC";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, fromDate);
            ps.setString(2, toDate);
            ResultSet rs = ps.executeQuery();
            workhist = new LinkedList<WorkingHistory>();
            while(rs.next()){
                WorkingHistory wh = new WorkingHistory();
                orm(rs,wh);
                workhist.add(wh);                
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return workhist;
    }
    
    private static void orm(ResultSet rs,WorkingHistory wh) throws SQLException{
        wh.setBranchNo(rs.getInt("workNo"));
        wh.setEmpName(rs.getString("empName"));
        wh.setEmpNo(rs.getInt("empNo"));
        wh.setEmpTypeName(rs.getString("empTypeName"));
        wh.setFromDate(rs.getDate("fromDate"));
        wh.setFromTime(rs.getInt("fromTime"));
        wh.setPositionName(rs.getString("positionName"));
        wh.setToDate(rs.getDate("toDate"));
        wh.setToTime(rs.getInt("toTime"));
        wh.setWorkNo(rs.getInt("workNo"));
        wh.setWorkingPay(rs.getInt("workingPay"));
    }
}
