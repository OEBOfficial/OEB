package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public class WorkingHistory {

    private int workNo;
    private int fromTime;
    private int toTime;
    private int branchNo;
    private int empNo;
    private double workingPay;
    private Date fromDate;
    private Date toDate;
    private double minHoursPerDay;
    private double maxHoursPerDay;
    private String empTypeName;
    private String positionName;
    private String empName;
    private String payTypeName;

    public double getMinHoursPerDay() {
        return minHoursPerDay;
    }

    public void setMinHoursPerDay(double minHoursPerDay) {
        this.minHoursPerDay = minHoursPerDay;
    }

    public double getMaxHoursPerDay() {
        return maxHoursPerDay;
    }

    public void setMaxHoursPerDay(double maxHoursPerDay) {
        this.maxHoursPerDay = maxHoursPerDay;
    }
    
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

    public double getWorkingPay() {
        return workingPay;
    }

    public void setWorkingPay(double workingPay) {
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

    public String getPayTypeName() {
        return payTypeName;
    }

    public void setPayTypeName(String payTypeName) {
        this.payTypeName = payTypeName;
    }
    
    

    public static List<WorkingHistory> getAllWorkHist(int branchNo) {
        List<WorkingHistory> workhist = null;
        try {
            Connection con = ConnectionBuilder.getConnection();
            String sql = "SELECT * FROM WorkingHistory wh "
                    + " JOIN Employee e ON e.empNo = wh.empNo "
                    + " WHERE wh.branchNo = ? AND wh.toTime IS NOT NULL AND empTypeName <> 'withdraw' "
                    + " ORDER BY wh.fromDate DESC,wh.toTime DESC";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, branchNo);
            ResultSet rs = ps.executeQuery();
            workhist = new LinkedList<WorkingHistory>();
            while (rs.next()) {
                WorkingHistory wh = new WorkingHistory();
                orm(rs, wh);
                workhist.add(wh);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return workhist;
    }

    // is used
    public static List<WorkingHistory> getAllWorkHistWithCheck(int branchNo) {
        List<WorkingHistory> workhist = null;
        try {
            Connection con = ConnectionBuilder.getConnection();
            String sql = "SELECT * FROM Employee e "
                    + " LEFT JOIN (SELECT * FROM WorkingHistory WHERE (fromDate = ? OR (fromDate = ? AND toDate IS NULL)) AND empTypeName <> 'withdraw') as wh ON e.empNo = wh.empNo "
                    + " WHERE e.branchNo = ? "
                    + " GROUP BY e.empNo "
                    + " ORDER BY wh.fromDate DESC,wh.toTime DESC";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, new Date(System.currentTimeMillis()));
            ps.setDate(2, new Date(System.currentTimeMillis() - 1000L * 60L * 60L * 24L));
            ps.setInt(3, branchNo);
            ResultSet rs = ps.executeQuery();
            workhist = new LinkedList<WorkingHistory>();
            while (rs.next()) {
                WorkingHistory wh = new WorkingHistory();
                orm(rs, wh);
                workhist.add(wh);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return workhist;
    }

    public static List<WorkingHistory> filterWorkByDate(String fromDate, String toDate) {
        List<WorkingHistory> workhist = null;
        try {
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
            while (rs.next()) {
                WorkingHistory wh = new WorkingHistory();
                orm(rs, wh);
                workhist.add(wh);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return workhist;
    }

    public static List<WorkingHistory> getAllWorkHistByEmp(int empNo) {
        List<WorkingHistory> workHist = null;
        try {
            Connection con = ConnectionBuilder.getConnection();
            String sql = "SELECT * FROM WorkingHistory wh "
                    + " JOIN Employee e ON e.empNo = wh.empNo "
                    + " WHERE wh.empNo = ? AND wh.toTime IS NOT NULL "
                    + " ORDER BY wh.fromDate DESC,wh.toTime DESC";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, empNo);
            ResultSet rs = ps.executeQuery();
            workHist = new LinkedList<WorkingHistory>();
            while (rs.next()) {
                WorkingHistory wh = new WorkingHistory();
                orm(rs, wh);
                workHist.add(wh);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return workHist;
    }

    public static JsonArray getAllWorkHistByEmpJson(int empNo) {
        List<WorkingHistory> workHist = getAllWorkHistByEmp(empNo);
        JsonObjectBuilder workBuilder = Json.createObjectBuilder();
        JsonArrayBuilder JAB = Json.createArrayBuilder();
        JsonArray JA = null;
        if (workHist != null) {
            JA = JAB.build();
            for (int i = 0; i < workHist.size(); i++) {
                JsonObject jo = workBuilder
                        .add("workNo", workHist.get(i).getWorkNo())
                        .add("fromTime", workHist.get(i).getFromTime())
                        .add("toTime", workHist.get(i).getToTime())
                        .add("fromDate", "" + workHist.get(i).getFromDate())
                        .add("toDate", "" + workHist.get(i).getToDate())
                        .add("empTypeName", workHist.get(i).getEmpTypeName())
                        .add("positionName", workHist.get(i).getPositionName())
                        .add("workingPay", workHist.get(i).getWorkingPay())
                        .add("empNo", workHist.get(i).getEmpNo())
                        .add("empName", workHist.get(i).getEmpName())
                        .add("branchNo", workHist.get(i).getBranchNo())
                        .add("maxHoursPerDay", workHist.get(i).getMaxHoursPerDay())
                        .add("minHoursPerDay", workHist.get(i).getMinHoursPerDay())
                        .add("payTypeName", workHist.get(i).getPayTypeName())
                        .build();
                JAB.add(jo);
            }
            JA = JAB.build();
        }
        return JA;
    }

    public static void clockIn(int empNo) {
        try {
            Employee e = Employee.getEmpByEmpNo(empNo);
            if (e != null) {
                Connection con = ConnectionBuilder.getConnection();
                String sql = "INSERT INTO WorkingHistory(fromTime,fromDate,empNo,branchNo,minHoursPerDay,maxHoursPerDay,empTypeName,positionName,payTypeName) "
                        + " VALUES(?,?,?,?,?,?,?,?,?)";
                PreparedStatement ps = con.prepareStatement(sql);
                Date now = new Date(System.currentTimeMillis());
                ps.setInt(1, getIntTime(now));
                ps.setDate(2, now);
                ps.setInt(3, e.getEmpNo());
                ps.setInt(4, e.getBranchNo());
                ps.setDouble(5, e.getConstraint().getMinHoursPerDay());
                ps.setDouble(6, e.getConstraint().getMaxHoursPerDay());
                ps.setString(7, e.getConstraint().getEmploymentType().getEmpTypeName());
                ps.setString(8, e.getConstraint().getEmployeePosition().getPositionName());
                ps.setString(9, e.getConstraint().getPayType().getPayTypeName());
                ps.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public static WorkingHistory getWorkHist(int workNo) {
        WorkingHistory wh = null;
        try {
            Connection con = ConnectionBuilder.getConnection();
            String sql = "SELECT * FROM WorkingHistory wh "
                    + " JOIN Employee e ON wh.empNo = wh.empNo "
                    + " JOIN `Constraint` c ON e.constraintNo = c.constraintNo "
                    + " JOIN EmployeePosition ep ON c.positionNo = ep.positionNo "
                    + " JOIN EmploymentType et ON c.empTypeNo = et.empTypeNo "
                    + " JOIN PayType pt ON c.payTypeNo = pt.payTypeNo "
                    + " WHERE workNo = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, workNo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                wh = new WorkingHistory();
                orm(rs, wh);
                wh.setEmpTypeName(rs.getString("empTypeName"));
                wh.setPositionName(rs.getString("positionName"));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return wh;
    }

    public static void clockOut(int workNo,int empNo) {
        try {
            WorkingHistory wh = getWorkHist(workNo);
            Connection con = ConnectionBuilder.getConnection();
            String sql = "UPDATE WorkingHistory SET toTime=?,toDate=?,workingPay=? WHERE workNo = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            Date now = new Date(System.currentTimeMillis());
            int intTimeNow = getIntTime(now);
            ps.setInt(1, intTimeNow);
            ps.setDate(2, now);
            wh.setToDate(now);
            wh.setToTime(intTimeNow);
            wh.setEmpNo(empNo);
            ps.setDouble(3, wh.calWorkingPay());
            ps.setInt(4, workNo);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    private double calWorkingPay() {
        //fromDate toDate fromTime toTime
        double workingPay = 0; //initial
        Employee e = Employee.getEmpByEmpNo(empNo);
        Constraint c = Constraint.getConstraint(e.getConstraint().getEmployeePosition().getPositionNo(), e.getConstraint().getEmploymentType().getEmpTypeNo(),e.getConstraint().getPayType().getPayTypeNo());
        double payPerTime = 0; //initial
        if (e.getSpecPay() != null) {
            payPerTime = e.getSpecPay();
        } else {
            payPerTime = c.getPay();
        }
        
        if (e.getConstraint().getPayType().getPayTypeName().indexOf("วัน") != -1) {
            workingPay = payPerTime;
        } else{
            long diff = toDate.getTime() - fromDate.getTime();
            long diffDay = diff / (24 * 60 * 60 * 1000);
            double calTime = 0; //initial
            if (diffDay == 0) {
                int intTime = toTime - fromTime;
                calTime = (intTime / 60) + (intTime % 60) / 60.0;
            } else {
                if (diffDay == 1) {
                    int intTime = (1440 - fromTime) + toTime;
                    calTime = (intTime / 60) + (intTime % 60) / 60.0;
                } else {
                    calTime = c.getMaxHoursPerDay();
                }
            }
            if (calTime > c.getMaxHoursPerDay()) {
                calTime = c.getMaxHoursPerDay();
            }
            workingPay = calTime * payPerTime;
        }
        
        return workingPay;
    }

    private static Integer getIntTime(Date d) {
        Integer intTime = null;
        if (d != null) {
            DateFormat df = new SimpleDateFormat("HHmm");
            String strIntTime = df.format(d);
            int intHour = Integer.parseInt(strIntTime.substring(0, 2));
            int intMin = Integer.parseInt(strIntTime.substring(2, 4));
            intTime = (intHour * 60) + intMin;
        }
        return intTime;
    }
    
    public static void payEmp(int empNo,double inputWithdraw,int branchNo){
        try{
            Connection con = ConnectionBuilder.getConnection();
            String sql = "INSERT INTO WorkingHistory(fromTime,toTime,fromDate,toDate,workingPay,empNo,branchNo,minHoursPerDay,maxHoursPerDay,positionName,payTypeName,empTypeName) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            Date now = new Date(System.currentTimeMillis());
            ps.setInt(1,getIntTime(now));
            ps.setInt(2,getIntTime(now));
            ps.setDate(3,now);
            ps.setDate(4,now);
            ps.setDouble(5,-inputWithdraw);
            ps.setInt(6,empNo);
            ps.setInt(7,branchNo);
            ps.setDouble(8,0);
            ps.setDouble(9,0);
            ps.setString(10,"withdraw");
            ps.setString(11,"withdraw");
            ps.setString(12,"withdraw");
            ps.executeUpdate();
        }catch(SQLException ex){
            System.out.println(ex);
        }
    }

    private static void orm(ResultSet rs, WorkingHistory wh) throws SQLException {
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
        wh.setWorkingPay(rs.getDouble("workingPay"));
        wh.setMinHoursPerDay(rs.getDouble("minHoursPerDay"));
        wh.setMaxHoursPerDay(rs.getDouble("maxHoursPerDay"));
        wh.setPayTypeName(rs.getString("payTypeName"));
    }
}
