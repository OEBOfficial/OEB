package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public class Constraint {

    private int constraintNo;
    private int empTypeNo;
    private int positionNo;
    private double maxHoursPerDay;
    private double minHoursPerDay;
    private int payTypeNo;
    private String payTypeName;
    private double pay;

    public int getConstraintNo() {
        return constraintNo;
    }

    public void setConstraintNo(int constraintNo) {
        this.constraintNo = constraintNo;
    }
    
    public String getPayTypeName() {
        return payTypeName;
    }

    public void setPayTypeName(String payTypeName) {
        this.payTypeName = payTypeName;
    }

    public int getPayTypeNo() {
        return payTypeNo;
    }

    public void setPayTypeNo(int payTypeNo) {
        this.payTypeNo = payTypeNo;
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

    public double getMaxHoursPerDay() {
        return maxHoursPerDay;
    }

    public void setMaxHoursPerDay(double maxHoursPerDay) {
        this.maxHoursPerDay = maxHoursPerDay;
    }

    public double getMinHoursPerDay() {
        return minHoursPerDay;
    }

    public void setMinHoursPerDay(double minHoursPerDay) {
        this.minHoursPerDay = minHoursPerDay;
    }

    public double getPay() {
        return pay;
    }

    public void setPay(double pay) {
        this.pay = pay;
    }

    //is used
    public static JsonArray getAllConJson(int positionNo) {
        JsonArrayBuilder JAB = Json.createArrayBuilder();
        JsonObjectBuilder JOB = Json.createObjectBuilder();
        JsonArray JA = null;
        List<Constraint> constraints = getAllConstraint(positionNo);
        if (constraints != null) {
            JA = JAB.build();
            for (int i = 0; i < constraints.size(); i++) {
                JsonObject jo = JOB
                        .add("positionNo", constraints.get(i).getPositionNo())
                        .add("empTypeNo", constraints.get(i).getEmpTypeNo())
                        .add("maxHoursPerDay", constraints.get(i).getMaxHoursPerDay())
                        .add("minHoursPerDay", constraints.get(i).getMinHoursPerDay())
                        .add("pay", constraints.get(i).getPay())
                        .add("payTypeNo", constraints.get(i).getPayTypeNo())
                        .add("payTypeName", constraints.get(i).getPayTypeName())
                        .add("constraintNo",constraints.get(i).getConstraintNo())
                        .build();
                JAB.add(jo);
            }
            JA = JAB.build();
        }
        return JA;
    }
    
    //is used
    public static boolean addConstraints(int positionNo, String[] empTypes, String[] payTypes, String[] maxHours, String[] minHours, String[] pay, String proportion,int branchNo) {
        try {
            Connection con = ConnectionBuilder.getConnection();
            con.setAutoCommit(false);
            String sql = "INSERT INTO `Constraint`(positionNo,empTypeNo,minHoursPerDay,maxHoursPerDay,pay,payTypeNo,branchNo) VALUES(?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            int idx = 0;
            int proportionIdx = 0;
            for (String stEmpType : empTypes) {
                int empTypeNo = Integer.parseInt(stEmpType);
                int iPropotion = Integer.parseInt("" + proportion.charAt(proportionIdx));
                int roundInWhile = 0;
                while (idx < payTypes.length) {
                    int payTypeNo = Integer.parseInt(payTypes[idx]);
                    ps.setInt(1, positionNo);
                    ps.setInt(2, empTypeNo);
                    ps.setDouble(3, Double.parseDouble(minHours[idx]));
                    ps.setDouble(4, Double.parseDouble(maxHours[idx]));
                    ps.setDouble(5, Double.parseDouble(pay[idx]));
                    ps.setInt(6, payTypeNo);
                    ps.setInt(7,branchNo);
                    ps.addBatch();
                    idx++;
                    roundInWhile++;
                    if (idx >= payTypes.length || roundInWhile >= iPropotion) { // next emptype
                        break;
                    }
                }
                if (idx >= payTypes.length) {
                    break;
                }
                proportionIdx++;
            }
            ps.executeBatch();
            con.commit();
            System.out.println("commit !!!");
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return true;
    }

//    public boolean editConstraint() {
//        boolean success = false;
//        try {
//            Connection con = ConnectionBuilder.getConnection();
//            String sql = "UPDATE Constraint SET minHoursPerDay = ?,maxHoursPerDay = ?,pay = ? WHERE empTypeNo = ? AND positionNo = ?";
//            PreparedStatement ps = con.prepareStatement(sql);
//            ps.setDouble(1, minHoursPerDay);
//            ps.setDouble(2, maxHoursPerDay);
//            ps.setDouble(3, pay);
//            ps.setInt(4, empTypeNo);
//            ps.setInt(5, positionNo);
//            int i = ps.executeUpdate();
//            if (i > 0) {
//                success = true;
//            }
//            con.close();
//        } catch (SQLException ex) {
//            System.out.println(ex);
//        }
//        return success;
//    }

    //is used
    public static boolean delConstraints(int positionNo) {
        boolean success = false;
        try {
            Connection con = ConnectionBuilder.getConnection();
            String sql = "DELETE FROM `Constraint` WHERE positionNo = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, positionNo);
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

    // is used
    public static List<Constraint> getAllConstraint(int positionNo) {
        List<Constraint> constraints = null;
        try {
            Connection con = ConnectionBuilder.getConnection();
            String sql = "SELECT * FROM `Constraint` c JOIN PayType pt ON c.payTypeNo = pt.payTypeNo WHERE positionNo = ? ORDER BY c.positionNo,c.empTypeNo,c.payTypeNo";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, positionNo);
            ResultSet rs = ps.executeQuery();
            constraints = new LinkedList<Constraint>();
            while (rs.next()) {
                Constraint c = new Constraint();
                orm(rs, c);
                c.setPayTypeName(rs.getString("payTypeName"));
                constraints.add(c);
            }
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return constraints;
    }
    
    
    //is used
    public static Integer findConstraintNo(int positionNo,int empTypeNo,int payTypeNo){
        Integer constraintNo = null;
        try{
            Connection con = ConnectionBuilder.getConnection();
            String sql = "SELECT constraintNo FROM `Constraint` WHERE positionNo = ? AND empTypeNo = ? AND payTypeNo = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, positionNo);
            ps.setInt(2, empTypeNo);
            ps.setInt(3, payTypeNo);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                constraintNo = rs.getInt("constraintNo");
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return constraintNo;
    }

    
    public static Constraint getConstraint(int positionNo, int empTypeNo, int payTypeNo) {
        Constraint c = null;
        try {
            Connection con = ConnectionBuilder.getConnection();
            String sql = "SELECT * FROM `Constraint` c "
                    + " JOIN EmploymentType et ON c.empTypeNo = et.empTypeNo "
                    + " WHERE c.positionNo = ? AND c.empTypeNo = ? AND c.payTypeNo = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, positionNo);
            ps.setInt(2, empTypeNo);
            ps.setInt(3, payTypeNo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                c = new Constraint();
                orm(rs, c);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return c;
    }
    
    //is used
    private static List<Constraint> getConstraintByBranch(int branchNo){
        List<Constraint> constraints = null;
        try{
            Connection con = ConnectionBuilder.getConnection();
            String sql = "SELECT * FROM `Constraint` c JOIN PayType p ON c.payTypeNo = p.payTypeNo WHERE positionNo IN (SELECT positionNo FROM EmployeePosition WHERE branchNo = ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,branchNo);
            ResultSet rs = ps.executeQuery();
            constraints = new LinkedList<Constraint>();
            while(rs.next()){
                Constraint c = new Constraint();
                orm(rs,c);
                constraints.add(c);
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return constraints;
    }

    //is used
    public static Map<String, String> getMapConstraint(int branchNo) {
        List<Constraint> constraints = getConstraintByBranch(branchNo);
        System.out.println(constraints);
        Map<String, String> constraintMaps = null;
        if (constraints != null) {
            constraintMaps = new LinkedHashMap<String,String>();
            for (Constraint c : constraints) {
                String key = c.getPositionNo() + "|" + c.getEmpTypeNo();
                String value = c.getPayTypeNo()+"";
                if(constraintMaps.get(key) == null){
                    constraintMaps.put(key, value);
                }else{
                    String oldValue = constraintMaps.get(key);
                    constraintMaps.put(key, oldValue+value);
                }
            }
        }
        return constraintMaps;
    }

    private static void orm(ResultSet rs, Constraint c) throws SQLException {
        c.setEmpTypeNo(rs.getInt("empTypeNo"));
        c.setMinHoursPerDay(rs.getDouble("minHoursPerDay"));
        c.setMaxHoursPerDay(rs.getDouble("maxHoursPerDay"));
        c.setPay(rs.getDouble("pay"));
        c.setPositionNo(rs.getInt("positionNo"));
        c.setPayTypeNo(rs.getInt("payTypeNo"));
//        c.setPayTypeName(rs.getString("payTypeName"));
        c.setConstraintNo(rs.getInt("constraintNo"));
    }
    
    public static void main(String[] args) {
        System.out.println(getMapConstraint(1));
    }
}
