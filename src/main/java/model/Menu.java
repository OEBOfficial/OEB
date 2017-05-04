package model;

import com.mysql.cj.api.jdbc.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public class Menu {

    private int menuNo;
    private String menuNameTH;
    private String menuNameEN;
    private String menuDesc;
    private Double menuPrice;
    private int isOfficialMenu;
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

    public int getMenuNo() {
        return menuNo;
    }

    public void setMenuNo(int menuNo) {
        this.menuNo = menuNo;
    }

    public String getMenuNameTH() {
        return menuNameTH;
    }

    public void setMenuNameTH(String menuNameTH) {
        this.menuNameTH = menuNameTH;
    }

    public String getMenuNameEN() {
        return menuNameEN;
    }

    public void setMenuNameEN(String menuNameEN) {
        this.menuNameEN = menuNameEN;
    }

    public String getMenuDesc() {
        return menuDesc;
    }

    public void setMenuDesc(String menuDesc) {
        this.menuDesc = menuDesc;
    }

    public Double getMenuPrice() {
        return menuPrice;
    }

    public void setMenuPrice(Double menuPrice) {
        this.menuPrice = menuPrice;
    }

    public int getIsOfficialMenu() {
        return isOfficialMenu;
    }

    public void setIsOfficialMenu(int isOfficialMenu) {
        this.isOfficialMenu = isOfficialMenu;
    }

    public static ArrayList<Menu> getAllMenu(int branchNo) {
        ArrayList<Menu> menus = null;
        try {
            Connection con = ConnectionBuilder.getConnection();
            String sql = "SELECT *,bm.branchNo AS bm_branchNo FROM Menu m "
                    + " LEFT JOIN Branch_Menu bm ON m.menuNo = bm.menuNo AND bm.branchNo = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, branchNo);
            ResultSet rs = ps.executeQuery();
            menus = new ArrayList<Menu>();
            while (rs.next()) {
                Menu m = new Menu();
                orm(rs, m);
                if (rs.getInt("bm_branchNo") == branchNo) {
                    m.setIsThisBranchMenu(true);
                }
                menus.add(m);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return menus;
    }

    public static Menu getMenu(int menuNo) {
        Menu m = null;
        try {
            Connection con = ConnectionBuilder.getConnection();
            String sql = "SELECT * FROM Menu WHERE menuNo = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, menuNo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                m = new Menu();
                orm(rs, m);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return m;
    }

    public static JsonObject getMenuJson(int menuNo) {
        JsonObjectBuilder empBuilder = Json.createObjectBuilder();
        Menu m = getMenu(menuNo);
        JsonObject menuJO = null;
        if (m != null) {
            menuJO = empBuilder
                    .add("menuNo", m.getMenuNo())
                    .add("menuNameTH", m.getMenuNameTH())
                    .add("menuNameEN", m.getMenuNameEN())
                    .add("menuDesc", m.getMenuDesc())
                    .add("menuPrice", m.getMenuPrice())
                    .add("isOfficialMenu", m.getIsOfficialMenu())
                    .add("isThisBranchMenu", m.isIsThisBranchMenu())
                    .add("branchNo", m.getBranchNo())
                    .build();
        }
        return menuJO;
    }

    public static List<Menu> getMenuByMenuSet(int menuSetNo, int branchNo) {
        ArrayList<Menu> menus = null;
        try {
            Connection con = ConnectionBuilder.getConnection();
            String sql = "SELECT *,bm.branchNo AS bm_branchNo FROM Menu m "
                    + " JOIN Menu_MenuSet mms ON m.menuNo = mms.menuNo "
                    + " LEFT JOIN Branch_Menu bm ON m.menuNo = bm.menuNo AND bm.branchNo = ? "
                    + " WHERE menuSetNo = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, branchNo);
            ps.setInt(2, menuSetNo);
            ResultSet rs = ps.executeQuery();
            menus = new ArrayList<Menu>();
            while (rs.next()) {
                Menu m = new Menu();
                orm(rs, m);
                if (rs.getInt("bm_branchNo") == branchNo) {
                    m.setIsThisBranchMenu(true);
                }
                menus.add(m);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return menus;
    }

    public static JsonArray getMenuByMenuSetJson(int menuSetNo, int branchNo) {
        JsonArrayBuilder JAB = Json.createArrayBuilder();
        JsonObjectBuilder JOB = Json.createObjectBuilder();
        List<Menu> menus = Menu.getMenuByMenuSet(menuSetNo, branchNo);
        JsonArray JA = null;
        if (menus != null) {
            JA = JAB.build();
            for (int i = 0; i < menus.size(); i++) {
                JsonObject jo = JOB
                        .add("menuNo", menus.get(i).getMenuNo())
                        .add("menuNameTH", menus.get(i).getMenuNameTH())
                        .add("menuNameEN", menus.get(i).getMenuNameEN())
                        .add("menuDesc", menus.get(i).getMenuDesc())
                        .add("menuPrice", menus.get(i).getMenuPrice())
                        .add("isOfficialMenu", menus.get(i).getIsOfficialMenu())
                        .add("isThisBranchMenu", menus.get(i).isIsThisBranchMenu())
                        .add("branchNo", menus.get(i).getBranchNo())
                        .build();
                JAB.add(jo);
            }
            JA = JAB.build();
        }
        return JA;
    }

    public static void delMenu(int menuNo,int branchNo) {
        try {
            Connection con = ConnectionBuilder.getConnection();
            String sql = "SELECT * FROM Menu WHERE menuNo = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, menuNo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                if (rs.getInt("isOfficialMenu") == 0) {  // Menu of this branch only
                    sql = "DELETE FROM Menu WHERE menuNo = ?";
                    ps = con.prepareStatement(sql);
                    ps.setInt(1,menuNo);
                    ps.executeUpdate();
                } else {
                    sql = "DELETE FROM Branch_Menu WHERE menuNo = ? AND branchNo = ?";  // Delete Branch_Menu
                    ps = con.prepareStatement(sql);
                    ps.setInt(1, menuNo);
                    ps.setInt(2, branchNo);
                    int success = ps.executeUpdate();
                    if (success == 1) {
                        sql = "SELECT * FROM Branch_Menu WHERE menuNo = ?"; // check if menu is in other branch
                        ps = con.prepareStatement(sql);
                        ps.setInt(1, menuNo);
                        rs = ps.executeQuery();
                        if (rs.next()) {
                            sql = "UPDATE Menu SET branchNo = ? WHERE menuNo = ?";
                            ps = con.prepareStatement(sql);
                            ps.setInt(1, rs.getInt("branchNo"));
                            ps.setInt(2, menuNo);
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

    public void addMenu(int isOfficialMenu) {
        try {
            Connection con = ConnectionBuilder.getConnection();
            String sql = "INSERT INTO Menu(menuNameTH,menuNameEN,menuDesc,menuPrice,isOfficialMenu,branchNo) "
                    + "VALUES(?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, menuNameTH);
            ps.setString(2, menuNameEN);
            ps.setString(3, menuDesc);
            ps.setDouble(4, menuPrice);
            ps.setInt(5, isOfficialMenu);
            ps.setInt(6,branchNo);
            int success = ps.executeUpdate();
            if(success == 1){
                ResultSet rs = ps.executeQuery();
                rs.next();
                int menuNo = rs.getInt(1);
                addMenuIntoBranch(menuNo,branchNo,0,null);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public static void addMenuIntoBranch(int menuNo,int branchNo,int isAvailable,Double price){
        try {
            Connection con = ConnectionBuilder.getConnection();
            String sql = "INSERT INTO Branch_Menu(menuNo,branchNo,isAvailable,price) "
                    + "VALUES(?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, menuNo);
            ps.setInt(2, branchNo);
            ps.setInt(3, isAvailable);
            if(price == null){
                ps.setNull(branchNo, java.sql.Types.DOUBLE);
            }else{
                ps.setDouble(4, price);
            }
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void editMenu(int menuNo) {
        try {
            Connection con = ConnectionBuilder.getConnection();
            String sql = "UPDATE Menu SET menuNameTH = ?,menuNameEN = ?,menuDesc = ?,"
                    + "menuPrice = ?,isOfficialMenu = ? WHERE menuNo = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, menuNameTH);
            ps.setString(2, menuNameEN);
            ps.setString(3, menuDesc);
            ps.setDouble(4, menuPrice);
            ps.setInt(5, isOfficialMenu);
            ps.setInt(6, menuNo);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void orm(ResultSet rs, Menu m) throws SQLException {
        m.setMenuNo(rs.getInt("menuNo"));
        m.setMenuNameTH(rs.getString("menuNameTH"));
        m.setMenuNameEN(rs.getString("menuNameEN"));
        m.setMenuDesc(rs.getString("menuDesc"));
        m.setMenuPrice(rs.getDouble("menuPrice"));
        m.setIsOfficialMenu(rs.getInt("isOfficialMenu"));
        m.setBranchNo(rs.getInt("branchNo"));
    }

    @Override
    public String toString() {
        return "Menu{" + "menuNo=" + menuNo + ", menuNameTH=" + menuNameTH + ", menuNameEN=" + menuNameEN + ", menuDesc=" + menuDesc + ", menuPrice=" + menuPrice + ", isOfficialMenu=" + isOfficialMenu + ", isThisBranchMenu=" + isThisBranchMenu + ", branchNo=" + branchNo + '}';
    }
}
