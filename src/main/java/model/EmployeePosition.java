package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public class EmployeePosition {
    private int positionNo;
    private int branchNo;
    private String positionName;

    public EmployeePosition() {
    }
    
    public EmployeePosition(int positionNo, int branchNo, String positionName) {
        this.positionNo = positionNo;
        this.branchNo = branchNo;
        this.positionName = positionName;
    }
    
    public int getPositionNo() {
        return positionNo;
    }

    public void setPositionNo(int positionNo) {
        this.positionNo = positionNo;
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
    
    public static EmployeePosition getEmpPos(int posNo){
        EmployeePosition ep = null;
        try{
            Connection con = ConnectionBuilder.getConnection();
            String sql = "SELECT * FROM EmployeePosition WHERE positionNo = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,posNo);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                ep = new EmployeePosition();
                orm(rs,ep);
            }
            con.close();
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return ep;
    }
    
    public static JsonObject getEmpPosJson(int positionNo){
        JsonObjectBuilder JOB = Json.createObjectBuilder();
        EmployeePosition ep = getEmpPos(positionNo);
        JsonObject empJO = null;
        if(ep != null){
            empJO = JOB
                .add("positionName",ep.getPositionName())
                .add("positionNo", ep.getPositionNo())
                .add("branchNo", ep.getBranchNo())
                .build();
        }
        return empJO;
    }
    
    //is used
    public static List<EmployeePosition> getAllEmpPos(int branchNo){
        List<EmployeePosition> empPos = null;
        try{
            Connection con = ConnectionBuilder.getConnection();
            String sql = "SELECT * FROM EmployeePosition WHERE branchNo = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,branchNo);
            ResultSet rs = ps.executeQuery();
            empPos = new LinkedList<EmployeePosition>();
            while(rs.next()){
                EmployeePosition ep = new EmployeePosition();
                orm(rs,ep);
                empPos.add(ep);
            }
            con.close();
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return empPos;
    }
    
    //is used
    public Integer addEmpPosName(){
        Integer posNo = null;
        try{
            Connection con = ConnectionBuilder.getConnection();
            String sql = "INSERT INTO EmployeePosition(positionName,branchNo) "
                    + " VALUES(?,?) ";
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, positionName);
            ps.setInt(2, branchNo);
            int i = ps.executeUpdate();
            if(i > 0){
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                posNo = rs.getInt(1);
            }
            con.close();
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return posNo;
    }
    
    public boolean editEmpPosName(){
        boolean success = false;
        try{
            Connection con = ConnectionBuilder.getConnection();
            String sql = "UPDATE EmployeePosition SET positionName = ? WHERE positionNo = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, positionName);
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
        
    public static boolean delEmpPos(int positionNo){
        boolean success = false;
        try{
            Connection con = ConnectionBuilder.getConnection();
            String sql = "DELETE FROM EmployeePosition WHERE positionNo = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, positionNo);
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
    
    public static void orm(ResultSet rs,EmployeePosition ep) throws SQLException{
        ep.setBranchNo(rs.getInt("branchNo"));
        ep.setPositionNo(rs.getInt("positionNo"));
        ep.setPositionName(rs.getString("positionName"));
    }

    @Override
    public String toString() {
        return "EmployeePosition{" + "positionNo=" + positionNo + ", branchNo=" + branchNo + ", positionName=" + positionName + '}';
    }
    
}
