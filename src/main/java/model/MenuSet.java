package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MenuSet {

    private int menuSetNo;
    private String menuSetNameTH;
    private String menuSetNameEN;
    private String menuSetDesc;
    private Double menuSetPrice;
    private String menuSetPicPath;
    private Integer isOfficialMenuSet;
    private boolean isThisBranchMenu;
    private int branchNo;

    public int getBranchNo() {
        return branchNo;
    }

    public void setBranchNo(int branchNo) {
        this.branchNo = branchNo;
    }

    public boolean isIsThisBranchMenu() {
        return isThisBranchMenu;
    }

    public void setIsThisBranchMenu(boolean isThisBranchMenu) {
        this.isThisBranchMenu = isThisBranchMenu;
    }

    public int getMenuSetNo() {
        return menuSetNo;
    }

    public void setMenuSetNo(int menuSetNo) {
        this.menuSetNo = menuSetNo;
    }

    public String getMenuSetNameTH() {
        return menuSetNameTH;
    }

    public void setMenuSetNameTH(String menuSetNameTH) {
        this.menuSetNameTH = menuSetNameTH;
    }

    public String getMenuSetNameEN() {
        return menuSetNameEN;
    }

    public void setMenuSetNameEN(String menuSetNameEN) {
        this.menuSetNameEN = menuSetNameEN;
    }

    public String getMenuSetDesc() {
        return menuSetDesc;
    }

    public void setMenuSetDesc(String menuSetDesc) {
        this.menuSetDesc = menuSetDesc;
    }

    public Double getMenuSetPrice() {
        return menuSetPrice;
    }

    public void setMenuSetPrice(Double menuSetPrice) {
        this.menuSetPrice = menuSetPrice;
    }

    public String getMenuSetPicPath() {
        return menuSetPicPath;
    }

    public void setMenuSetPicPath(String menuSetPicPath) {
        this.menuSetPicPath = menuSetPicPath;
    }

    public Integer getIsOfficialMenuSet() {
        return isOfficialMenuSet;
    }

    public void setIsOfficialMenuSet(Integer isOfficialMenuSet) {
        this.isOfficialMenuSet = isOfficialMenuSet;
    }

    public static List<MenuSet> getAllMenuSet(int branchNo) {
        ArrayList<MenuSet> menuSet = null;
        try {
            Connection con = ConnectionBuilder.getConnection();
            String sql = "SELECT *,bms.branchNo AS bms_branchNo FROM MenuSet ms "
                    + " LEFT JOIN Branch_MenuSet bms ON ms.menuSetNo = bms.menuSetNo AND bms.branchNo = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, branchNo);
            ResultSet rs = ps.executeQuery();
            menuSet = new ArrayList<MenuSet>();
            while (rs.next()) {
                MenuSet ms = new MenuSet();
                orm(rs, ms);
                if (rs.getInt("bms_branchNo") == branchNo) {
                    ms.setIsThisBranchMenu(true);
                }
                menuSet.add(ms);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return menuSet;
    }

    public Integer addMenuSet(Map<Integer, Integer> menuNo, int isAvailable, double price) {
        Integer menuSetNo = null;
        try {
            Connection con = ConnectionBuilder.getConnection();
            String sql = "INSERT INTO MenuSet(menuSetNameTH,menuSetNameEN,menuSetDesc,menuSetPrice,menuSetPicPath,isOfficialMenuSet,branchNo) "
                    + " VALUES(?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, menuSetNameTH);
            ps.setString(2, menuSetNameEN);
            ps.setString(3, menuSetDesc);
            ps.setDouble(4, menuSetPrice);
            ps.setString(5, menuSetPicPath);
            ps.setInt(6, isOfficialMenuSet);
            ps.setInt(7, branchNo);
            int success = ps.executeUpdate();
            if (success == 1) {
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                menuSetNo = rs.getInt(1);
                con.setAutoCommit(false);
                sql = "INSERT INTO Menu_MenuSet(menuSetNo,menuNo,amount) VALUES(?,?,?)";
                ps = con.prepareStatement(sql);
                for (Map.Entry<Integer, Integer> entry : menuNo.entrySet()) {
                    ps.setInt(1, menuSetNo);
                    ps.setInt(2, entry.getKey()); // menuNo
                    ps.setInt(3, entry.getValue()); // amount
                    ps.addBatch();
                }

                ps.executeBatch();
                con.commit();
                if (success > 0) {
                    addMenuSetToBranch(menuSetNo, branchNo, isAvailable, price);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return menuSetNo;
    }

    public static void addMenuSetToBranch(int menuSetNo, int branchNo, int isAvailable, double price) {
        try {
            Connection con = ConnectionBuilder.getConnection();
            String sql = "INSERT INTO Branch_MenuSet(branchNo,menuSetNo,isAvailable,price) VALUES(?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, branchNo);
            ps.setInt(2, menuSetNo);
            ps.setInt(3, isAvailable);
            ps.setDouble(4, price);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void editMenuSet(Map<Integer, Integer> menuNo, int isAvailable) {
        try {
            Connection con = ConnectionBuilder.getConnection();
            // #ลบรูปด้วย อนาคตมาทำด้วยนะ
            String sql = "UPDATE MenuSet SET menuSetNameTH = ?,menuSetNameEN = ?,menuSetDesc = ?,menuSetPrice = ?,menuSetPicPath = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, menuSetNameTH);
            ps.setString(2, menuSetNameEN);
            ps.setString(3, menuSetDesc);
            ps.setDouble(4, menuSetPrice);
            ps.setString(5, menuSetPicPath);
            int success = ps.executeUpdate();
            if (success == 1) {
                success = editMenuSetInBranch(isAvailable, menuSetPrice, branchNo, menuSetNo);
                if (success == 1) {
                    con.setAutoCommit(false);
                    sql = "INSERT INTO Menu_MenuSet(menuSetNo,menuNo,amount) VALUES(?,?,?) ON DUPLICATE KEY UPDATE amount = ?)";
                    for (Map.Entry<Integer, Integer> entry : menuNo.entrySet()) {
                        ps.setInt(1, menuSetNo);
                        ps.setInt(2, entry.getKey()); // menuNo
                        ps.setInt(3, entry.getValue()); // amount
                        ps.setInt(4, entry.getValue()); // amount
                        ps.addBatch();
                    }
                    ps.executeBatch();
                    con.commit();
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public static int editMenuSetInBranch(int isAvailable, double price, int branchNo, int menuSetNo) {
        int success = 0;
        try {
            Connection con = ConnectionBuilder.getConnection();
            String sql = "UPDATE Branch_MenuSet SET isAvailable = ?,price = ? WHERE branchNo = ? AND menuSetNo = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, isAvailable);
            ps.setDouble(2, price);
            ps.setInt(3, branchNo);
            ps.setInt(4, menuSetNo);
            success = ps.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return success;
    }

    public static void delMenuSet(int menuSetNo, int branchNo) {
        try {
            Connection con = ConnectionBuilder.getConnection();
            String sql = "SELECT * FROM MenuSet WHERE menuSetNo = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, menuSetNo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                if (rs.getInt("isOfficialMenuSet") == 0) {  // Menu of this branch only
                    sql = "DELETE FROM MenuSet WHERE menuSetNo = ?";
                    ps = con.prepareStatement(sql);
                    ps.setInt(1, menuSetNo);
                    ps.executeUpdate();
                } else {
                    sql = "DELETE FROM Branch_MenuSet WHERE menuSetNo = ? AND branchNo = ?";  // Delete Branch_MenuSet
                    ps = con.prepareStatement(sql);
                    ps.setInt(1, menuSetNo);
                    ps.setInt(2, branchNo);
                    int success = ps.executeUpdate();
                    if (success == 1) {
                        sql = "SELECT * FROM Branch_MenuSet WHERE menuSetNo = ?";
                        ps = con.prepareStatement(sql);
                        ps.setInt(1, menuSetNo);
                        rs = ps.executeQuery();
                        if (rs.next()) {
                            sql = "UPDATE MenuSet SET branchNo = ? WHERE menuSetNo = ?";
                            ps = con.prepareStatement(sql);
                            ps.setInt(1, rs.getInt("branchNo"));
                            ps.setInt(2, menuSetNo);
                            ps.executeUpdate();
                        }
                    }
                }
            }
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    private static void orm(ResultSet rs, MenuSet ms) throws SQLException {
        ms.setIsOfficialMenuSet(rs.getInt("isOfficialMenuSet"));
        ms.setMenuSetDesc(rs.getString("menuSetDesc"));
        ms.setMenuSetNameEN(rs.getString("menuSetNameEN"));
        ms.setMenuSetNameTH(rs.getString("menuSetNameTH"));
        ms.setMenuSetNo(rs.getInt("menuSetNo"));
        ms.setMenuSetPicPath(rs.getString("menuSetPicPath"));
        ms.setMenuSetPrice(rs.getDouble("menuSetPrice"));
        ms.setBranchNo(rs.getInt("branchNo"));
    }

    public static void main(String[] args) {
        MenuSet ms = new MenuSet();
        ms.setBranchNo(1);
        ms.setIsOfficialMenuSet(0);
        ms.setMenuSetDesc("อร่อยมั้ง");
        ms.setMenuSetNameEN("OMG SET");
        ms.setMenuSetNameTH("คุณพระช่วย");
        ms.setMenuSetPrice(525.25);
        Map<Integer, Integer> menuNo = new LinkedHashMap<Integer, Integer>();
        menuNo.put(6, 2);
        menuNo.put(8, 3);
        menuNo.put(7, 1);
        ms.addMenuSet(menuNo, 0, ms.getMenuSetPrice());
    }
}
