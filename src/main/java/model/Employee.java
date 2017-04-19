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
    private int branchNo;
    private Double specPay;
    private Double SUMPAY; //sum of working history
    private String empName;
    private String gender;
    private String telNo;
    private Constraint constraint;

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

    public Double getSpecPay() {
        return specPay;
    }

    public void setSpecPay(Double specPay) {
        this.specPay = specPay;
    }

    public Double getSUMPAY() {
        return SUMPAY;
    }

    public void setSUMPAY(Double SUMPAY) {
        this.SUMPAY = SUMPAY;
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

    public Constraint getConstraint() {
        return constraint;
    }

    public void setConstraint(Constraint constraint) {
        this.constraint = constraint;
    }

    public static Employee getEmpByEmpNo(int empNo) {
        Employee e = null;
        try {
            Connection con = ConnectionBuilder.getConnection();
            String sql = "SELECT * FROM Employee e "
                    + " JOIN `Constraint` c ON e.constraintNo = c.constraintNo "
                    + " JOIN EmployeePosition ep ON c.positionNo = ep.positionNo "
                    + " JOIN EmploymentType et ON c.empTypeNo = et.empTypeNo "
                    + " JOIN PayType p ON c.payTypeNo = p.payTypeNo "
                    + " WHERE e.empNo = ? ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, empNo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                e = new Employee();
                orm(rs, e);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return e;
    }

    public static JsonObject getEmployeeJson(int empNo) {
        JsonObjectBuilder empBuilder = Json.createObjectBuilder();
        Employee e = getEmpByEmpNo(empNo);
        JsonObject empJO = null;
        if (e != null) {
            empJO = empBuilder
                    .add("empNo", e.getEmpNo())
                    .add("empName", e.getEmpName())
                    .add("branchNo", e.getBranchNo())
                    .add("positionNo", e.getConstraint().getEmployeePosition().getPositionNo())
                    .add("positionName", e.getConstraint().getEmployeePosition().getPositionName())
                    .add("empTypeNo", e.getConstraint().getEmploymentType().getEmpTypeNo())
                    .add("empTypeName", e.getConstraint().getEmploymentType().getEmpTypeName())
                    .add("payTypeNo", e.getConstraint().getPayType().getPayTypeNo())
                    .add("payTypeName", e.getConstraint().getPayType().getPayTypeName())
                    .add("pay", e.getConstraint().getPay())
                    .add("gender", e.getGender())
                    .add("specPay", ""+e.getSpecPay())
                    .add("telNo", e.getTelNo())
                    .build();
        }
        return empJO;
    }

    public static List<Employee> getEmpByBranch(int branchNo) {
        List<Employee> employees = null;
        try {
            Connection con = ConnectionBuilder.getConnection();
            String sql = "SELECT * FROM Employee e "
                    + " JOIN `Constraint` c ON e.constraintNo = c.constraintNo "
                    + " JOIN EmployeePosition ep ON c.positionNo = ep.positionNo "
                    + " JOIN EmploymentType et ON c.empTypeNo = et.empTypeNo "
                    + " JOIN PayType p ON c.payTypeNo = p.payTypeNo "
                    + " WHERE e.branchNo = ? "
                    + " ORDER BY e.empName ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, branchNo);
            ResultSet rs = ps.executeQuery();
            employees = new LinkedList<Employee>();
            while (rs.next()) {
                Employee e = new Employee();
                orm(rs, e);
                employees.add(e);
            }
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return employees;
    }
    
    public static List<Employee> getEmpByBranchWithSUMPAY(int branchNo) {
        List<Employee> employees = null;
        try {
            Connection con = ConnectionBuilder.getConnection();
            String sql = "SELECT SUM(wh.workingPay) AS SUMPAY,e.*,c.*,ep.*,et.*,p.*,wh.* FROM Employee e "
                    + " JOIN `Constraint` c ON e.constraintNo = c.constraintNo "
                    + " JOIN EmployeePosition ep ON c.positionNo = ep.positionNo "
                    + " JOIN EmploymentType et ON c.empTypeNo = et.empTypeNo "
                    + " JOIN PayType p ON c.payTypeNo = p.payTypeNo "
                    + " JOIN WorkingHistory wh ON e.empNo = wh.empNo "
                    + " WHERE e.branchNo = ? "
                    + " GROUP BY wh.empNo "
                    + " ORDER BY e.empName ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, branchNo);
            ResultSet rs = ps.executeQuery();
            employees = new LinkedList<Employee>();
            while (rs.next()) {
                Employee e = new Employee();
                orm(rs, e);
                e.setSUMPAY(rs.getDouble("SUMPAY"));
                employees.add(e);
            }
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return employees;
    }

    public boolean addEmp() {
        boolean success = false;
        try {
            Connection con = ConnectionBuilder.getConnection();
            String sql = "INSERT INTO Employee(empName,gender,telNo,specPay,constraintNo,branchNo) "
                    + " VALUES(?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, empName);
            ps.setString(2, gender);
            ps.setString(3, telNo);
            if (specPay != null) {
                ps.setDouble(4, specPay);
            } else {
                ps.setObject(4, null);
            }
            ps.setInt(5, constraint.getConstraintNo());
            ps.setInt(6, branchNo);
            int i = ps.executeUpdate();
            if (i > 0) {
                success = true;
            }
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return success;
    }

    public boolean editEmp() {
        boolean success = false;
        try {
            Connection con = ConnectionBuilder.getConnection();
            String sql = "UPDATE Employee SET empName = ?,gender = ?,telNo = ?,specPay = ?,constraintNo= ? WHERE empNo = ? and branchNo = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, empName);
            ps.setString(2, gender);
            ps.setString(3, telNo);
            if (specPay != null) {
                ps.setDouble(4, specPay);
            } else {
                ps.setObject(4, null);
            }
            ps.setInt(5, constraint.getConstraintNo());
            ps.setInt(6, empNo);
            ps.setInt(7, branchNo);
            int i = ps.executeUpdate();
            if (i > 0) {
                success = true;
            }
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return success;
    }

    public static boolean delEmp(int empNo) {
        boolean success = false;
        try {
            Connection con = ConnectionBuilder.getConnection();
            String sql = "DELETE FROM Employee WHERE empNo = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, empNo);
            int i = ps.executeUpdate();
            if (i > 0) {
                success = true;
            }
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return success;
    }

    private static void orm(ResultSet rs, Employee e) throws SQLException {
        e.setEmpNo(rs.getInt("empNo"));
        e.setBranchNo(rs.getInt("branchNo"));
        if (rs.getObject("specPay") != null) {
            e.setSpecPay(rs.getDouble("specPay"));
        }
        e.setEmpName(rs.getString("empName"));
        e.setGender(rs.getString("gender"));
        e.setTelNo(rs.getString("telNo"));
        EmployeePosition ep = new EmployeePosition();
        EmployeePosition.orm(rs, ep);
        EmploymentType et = new EmploymentType();
        EmploymentType.orm(rs, et);
        PayType pt = new PayType();
        PayType.orm(rs, pt);
        Constraint c = new Constraint();
        Constraint.orm(rs, c);
        e.setConstraint(c);
    }

    @Override
    public String toString() {
        return "Employee{" + "empNo=" + empNo + ", branchNo=" + branchNo + ", specPay=" + specPay + ", SUMPAY=" + SUMPAY + ", empName=" + empName + ", gender=" + gender + ", telNo=" + telNo + ", constraint=" + constraint + '}';
    }

}
