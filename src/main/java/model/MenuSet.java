package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
    
    public Integer addMenuSet(Map<Integer,Integer> menuNo,int isAvailable,double price){
        Integer menuSetNo = null;
        try{
            Connection con = ConnectionBuilder.getConnection();
            String sql = "INSERT INTO MenuSet(menuSetNameTH,menuSetNameEN,menuSetDesc,menuSetPrice,menuSetPicPath,isOfficialMenuSet,branchNo) "
                    + " VALUES(?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,menuSetNameTH);
            ps.setString(2,menuSetNameEN);
            ps.setString(3,menuSetDesc);
            ps.setDouble(4,menuSetPrice);
            ps.setString(5,menuSetPicPath);
            ps.setInt(6,isOfficialMenuSet);
            ps.setInt(7,branchNo);
            int success = ps.executeUpdate();
            if(success == 1){
                ResultSet rs = ps.executeQuery();
                rs.next();
                menuSetNo = rs.getInt(1);
                for(Map.Entry<Integer,Integer> entry : menuNo.entrySet()){
                    sql = "INSERT INTO Menu_MenuSet(menuSetNo,menuNo,amount) VALUES(?,?,?)";
                    ps = con.prepareStatement(sql);
                    ps.setInt(1,menuSetNo);
                    ps.setInt(2,entry.getKey()); // menuNo
                    ps.setInt(3,entry.getValue()); // amount
                    ps.addBatch();
                }
                success = ps.executeUpdate();
                if(success > 0){
                    addMenuSetToBranch(menuSetNo,branchNo,isAvailable,price);
                }
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return menuSetNo;
    }
    
    public static void addMenuSetToBranch(int menuSetNo,int branchNo,int isAvailable,double price){
        try{
            Connection con = ConnectionBuilder.getConnection();
            String sql = "INSERT INTO Branch_Menu(branchNo,menuSetNo,isAvailable,price) VALUES(?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,branchNo);
            ps.setInt(2,menuSetNo);
            ps.setInt(3,isAvailable);
            ps.setDouble(4,price);
            ps.executeUpdate();
        }catch(SQLException ex){
            System.out.println(ex);
        }
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
                    sql = "DELETE MenuSet WHERE menuSetNo = ?";
                    ps = con.prepareStatement(sql);
                    ps.setInt(1,menuSetNo);
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
}
