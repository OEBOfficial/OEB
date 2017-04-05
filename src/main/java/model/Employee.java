package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public class Employee {
    private int empNo;
    private String empName;
    private String gender;
    private String telNo;
    private Double specPay;
    private int empTypeNo;
    private String empTypeName;
    private int positionNo;
    private String positionName;
    private int payTypeNo;
    private String payTypeName;
    private int branchNo;
    private int constraintNo;
    private Double pay;
    private Double SUMPAY;

    public Double getPay() {
        return pay;
    }

    public void setPay(Double pay) {
        this.pay = pay;
    }

    public int getPayTypeNo() {
        return payTypeNo;
    }

    public void setPayTypeNo(int payTypeNo) {
        this.payTypeNo = payTypeNo;
    }

    public String getPayTypeName() {
        return payTypeName;
    }

    public void setPayTypeName(String payTypeName) {
        this.payTypeName = payTypeName;
    }
    
    public int getConstraintNo() {
        return constraintNo;
    }

    public void setConstraintNo(int constraintNo) {
        this.constraintNo = constraintNo;
    }

    public Double getSUMPAY() {
        return SUMPAY;
    }

    public void setSUMPAY(Double SUMPAY) {
        this.SUMPAY = SUMPAY;
    }
    
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

    
    // is used
    public static Employee getEmployee(int empNo){
        Employee e = null;
        try{
            Connection con = ConnectionBuilder.getConnection();
            String sql = "SELECT * FROM Employee e JOIN `Constraint` c ON e.constraintNo = c.constraintNo "
                    + " JOIN EmployeePosition ep ON c.positionNo = ep.positionNo "
                    + " JOIN PayType pt ON c.payTypeNo = pt.payTypeNo "
                    + " JOIN EmploymentType et ON c.empTypeNo = et.empTypeNo "
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
    
    //is used
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
                .add("payTypeNo",e.getPayTypeNo())
                .add("payTypeName",e.getPayTypeName())
                .add("pay",e.getPay())
                .build();
        }
        return empJO;
    }
    
    //is used
    public static List<Employee> getAllEmp(int branchNo){
        List<Employee> employees = null;
        try{
            Connection con = ConnectionBuilder.getConnection();
            String sql = "SELECT * FROM Employee e JOIN `Constraint` c ON e.constraintNo = c.constraintNo "
                    + " JOIN EmployeePosition ep ON c.positionNo = ep.positionNo "
                    + " JOIN PayType pt ON c.payTypeNo = pt.payTypeNo "
                    + " JOIN EmploymentType et ON c.empTypeNo = et.empTypeNo "
                    + " WHERE e.branchNo = ? ORDER BY e.empName";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, branchNo);
            ResultSet rs = ps.executeQuery();
            employees = new LinkedList<Employee>();
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
    
    //is used
    public boolean addEmp(){
        boolean success = false;
        try{
            Connection con = ConnectionBuilder.getConnection();
            String sql = "INSERT INTO Employee(empName,gender,telNo,specPay,constraintNo,branchNo) "
                    + " VALUES(?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, empName);
            ps.setString(2, gender);
            ps.setString(3, telNo);
            if(specPay != null){
                ps.setDouble(4, specPay);
            }else{
                ps.setObject(4, null);
            }
            ps.setInt(5, constraintNo);
            ps.setInt(6, branchNo);
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
            String sql = "UPDATE Employee SET empName = ?,gender = ?,telNo = ?,specPay = ?,constraintNo= ? WHERE empNo = ? and branchNo = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, empName);
            ps.setString(2, gender);
            ps.setString(3, telNo);
            if(specPay != null){
                ps.setDouble(4, specPay);
            }else{
                ps.setObject(4, null);
            }
            ps.setInt(5, constraintNo);
            ps.setInt(6, empNo);
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
    
    public static List<Employee> getEmpPaid(int branchNo){
        List<Employee> employees = null;
        try{
            Connection con = ConnectionBuilder.getConnection();
            String sql = "SELECT SUM(wh.workingPay) AS SUMPAY ,e.*,et.*,ep.* FROM Employee e "
                    + " JOIN EmployeePosition ep ON e.positionNo = ep.positionNo "
                    + " JOIN EmploymentType et ON e.empTypeNo = et.empTypeNo "
                    + " JOIN WorkingHistory wh ON e.empNo = wh.empNo "
                    + " WHERE e.branchNo = ? "
                    + " GROUP BY wh.empNo"
                    + " ORDER BY e.empName ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, branchNo);
            ResultSet rs = ps.executeQuery();
            employees = new LinkedList<Employee>();
            while(rs.next()){
                Employee e = new Employee();
                orm(rs,e);
                e.setSUMPAY(rs.getDouble("SUMPAY"));
                employees.add(e);
            }
            con.close();
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return employees;
    }
    
    private static void orm(ResultSet rs,Employee e) throws SQLException{
        e.setEmpTypeNo(rs.getInt("empTypeNo"));
        e.setEmpName(rs.getString("empName"));
        e.setEmpNo(rs.getInt("empNo"));
        e.setPositionNo(rs.getInt("positionNo"));
        e.setBranchNo(rs.getInt("branchNo"));
        e.setGender(rs.getString("gender"));
        e.setConstraintNo(rs.getInt("constraintNo"));
        if(rs.getObject("specPay") == null){
            e.setSpecPay(null);
        }else{
            e.setSpecPay(rs.getDouble("specPay"));
        }
        e.setTelNo(rs.getString("telNo"));
        e.setEmpTypeName(rs.getString("empTypeName"));
        e.setPositionName(rs.getString("positionName"));
        e.setPayTypeNo(rs.getInt("payTypeNo"));
        e.setPayTypeName(rs.getString("payTypeName"));
        e.setPay(rs.getDouble("pay"));
    }
    
    

    @Override
    public String toString() {
        return "Employee{" + "empNo=" + empNo + ", empName=" + empName + ", gender=" + gender + ", telNo=" + telNo + ", specPay=" + specPay + ", empTypeNo=" + empTypeNo + ", positionNo=" + positionNo + ", branchNo=" + branchNo + ", empTypeName=" + empTypeName + ", positionName=" + positionName + '}';
    }
}
