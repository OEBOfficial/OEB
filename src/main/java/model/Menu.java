//check code I
package model;

import com.mysql.cj.api.jdbc.Statement;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.LinkedList;
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
    private String menuPicPath;
    private Double menuPrice;
    private int isOfficialMenu;
    private boolean isThisBranchMenu;
    private boolean isAvailable;
    private int branchNo;
    private int amount;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getMenuPicPath() {
        return menuPicPath;
    }

    public void setMenuPicPath(String menuPicPath) {
        this.menuPicPath = menuPicPath;
    }

    public boolean isIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

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

    public static LinkedList<Menu> getAllMenu(int branchNo) {
        LinkedList<Menu> menus = null;
        try {
            Connection con = ConnectionBuilder.getConnection();
            String sql = "SELECT *,bm.branchNo AS bm_branchNo FROM Menu m "
                    + " LEFT JOIN Branch_Menu bm ON m.menuNo = bm.menuNo AND bm.branchNo = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, branchNo);
            ResultSet rs = ps.executeQuery();
            menus = new LinkedList<Menu>();
            while (rs.next()) {
                Menu m = new Menu();
                orm(rs, m);
                if (rs.getInt("bm_branchNo") == branchNo) {
                    m.setIsThisBranchMenu(true);
                }
                menus.add(m);
            }
            con.close();
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
            con.close();
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
                    .add("menuPicPath", m.getMenuPicPath())
                    .build();
        }
        return menuJO;
    }

    public static List<Menu> getMenuByMenuSet(int menuSetNo, int branchNo) {
        LinkedList<Menu> menus = null;
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
            menus = new LinkedList<Menu>();
            while (rs.next()) {
                Menu m = new Menu();
                orm(rs, m);
                if (rs.getInt("bm_branchNo") == branchNo) {
                    m.setIsThisBranchMenu(true);
                }
                m.setAmount(rs.getInt("amount"));
                menus.add(m);
            }
            con.close();
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
                        .add("menuPicPath", "" + menus.get(i).getMenuPicPath())
                        .add("amount", menus.get(i).getAmount())
                        .build();
                JAB.add(jo);
            }
            JA = JAB.build();
        }
        return JA;
    }

    public static boolean delMenu(int menuNo, int branchNo) {
        boolean success = false;
        try {
            Connection con = ConnectionBuilder.getConnection();
            con.setAutoCommit(false);
            String sql = "SELECT * FROM Menu WHERE menuNo = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, menuNo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                if (rs.getInt("isOfficialMenu") == 0) {  // Menu of this branch only
                    sql = "DELETE FROM Menu WHERE menuNo = ?";
                    ps = con.prepareStatement(sql);
                    ps.setInt(1, menuNo);
                    ps.executeUpdate();
                } else {
                    sql = "DELETE FROM Branch_Menu WHERE menuNo = ? AND branchNo = ?";  // Delete Branch_Menu
                    ps = con.prepareStatement(sql);
                    ps.setInt(1, menuNo);
                    ps.setInt(2, branchNo);
                    int successInSQL = ps.executeUpdate();
                    if (successInSQL == 1) {
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
                        } else {
                            sql = "DELETE FROM Menu WHERE menuNo = ?";
                            ps = con.prepareStatement(sql);
                            ps.setInt(1, menuNo);
                            ps.executeUpdate();
                        }
                    }
                }
            }
            con.commit();
            con.close();
            success = true;
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return success;
    }

    public static boolean delAllMenu(String[] stMenuNo, int branchNo) {
        boolean success = false;
        try {
            Connection con = ConnectionBuilder.getConnection();
            con.setAutoCommit(false);
            for (String menuNo : stMenuNo) {
                int iMenuNo = Integer.parseInt(menuNo);
                String sql = "SELECT * FROM Menu WHERE menuNo = ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, iMenuNo);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    if (rs.getInt("isOfficialMenu") == 0) {  // Menu of this branch only
                        sql = "DELETE FROM Menu WHERE menuNo = ?";
                        ps = con.prepareStatement(sql);
                        ps.setInt(1, iMenuNo);
                        ps.executeUpdate();
                    } else {
                        sql = "DELETE FROM Branch_Menu WHERE menuNo = ? AND branchNo = ?";  // Delete Branch_Menu
                        ps = con.prepareStatement(sql);
                        ps.setInt(1, iMenuNo);
                        ps.setInt(2, branchNo);
                        int successInSQL = ps.executeUpdate();
                        if (successInSQL == 1) {
                            sql = "SELECT * FROM Branch_Menu WHERE menuNo = ?"; // check if menu is in other branch
                            ps = con.prepareStatement(sql);
                            ps.setInt(1, iMenuNo);
                            rs = ps.executeQuery();
                            if (rs.next()) {
                                sql = "UPDATE Menu SET branchNo = ? WHERE menuNo = ?";
                                ps = con.prepareStatement(sql);
                                ps.setInt(1, rs.getInt("branchNo"));
                                ps.setInt(2, iMenuNo);
                                ps.executeUpdate();
                            } else {
                                sql = "DELETE FROM Menu WHERE menuNo = ?";
                                ps = con.prepareStatement(sql);
                                ps.setInt(1, iMenuNo);
                                ps.executeUpdate();
                            }
                        }
                    }
                }
            }
            con.commit();
            con.close();
            success = true;
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return success;
    }

    public static boolean manageAvailableAllMenu(String[] stMenuNo, int branchNo, int isAvailable) {
        boolean success = false;
        try {
            Connection con = ConnectionBuilder.getConnection();
            con.setAutoCommit(false);
            String sql = "UPDATE Branch_Menu SET isAvailable = ? WHERE menuNo = ? AND branchNo = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            for (String mn : stMenuNo) {
                ps.setInt(1, isAvailable);
                ps.setInt(2, Integer.parseInt(mn));
                ps.setInt(3, branchNo);
                ps.addBatch();
            }
            ps.executeBatch();
            con.commit();
            con.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return success;
    }

    public boolean addMenu(List<Integer> menuType, List<MenuMaterial> menuMat) {
        boolean success = false;
        try {
            Connection con = ConnectionBuilder.getConnection();
            con.setAutoCommit(false);
            String sql = "INSERT INTO Menu(menuNameTH,menuNameEN,menuDesc,menuPrice,isOfficialMenu,branchNo) "
                    + "VALUES(?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, menuNameTH);
            ps.setString(2, menuNameEN);
            ps.setString(3, menuDesc);
            ps.setDouble(4, menuPrice);
            ps.setInt(5, isOfficialMenu);
            ps.setInt(6, branchNo);
            boolean successInSQL = ps.executeUpdate() == 1;
            if (successInSQL) {
                ResultSet rs = ps.executeQuery();
                rs.next();
                int menuNo = rs.getInt(1);
                sql = "INSERT INTO Menu_MenuType(menuNo,menuTypeNo) VALUES(?,?)";
                ps = con.prepareStatement(sql);
                ps.setInt(1, menuNo);
                for (int mt : menuType) {
                    ps.setInt(2, mt);
                    ps.addBatch();
                }
                ps.executeBatch();
                sql = "INSERT INTO Menu_Material(menuNo,materialNo,quantity,unit) VALUES(?,?,?,?)";
                ps = con.prepareStatement(sql);
                for (MenuMaterial mm : menuMat) {
                    ps.setInt(1, menuNo);
                    ps.setInt(2, mm.getMaterialNo());
                    ps.setDouble(3, mm.getQuantity());
                    ps.setString(4, mm.getUnit());
                    ps.addBatch();
                }
                ps.executeBatch();
                success = addMenuIntoBranch(menuNo, branchNo, 0);
            }
            if (success) {
                con.commit();
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return success;
    }

    public static boolean addMenuIntoBranch(int menuNo, int branchNo, int isAvailable) {
        boolean success = false;
        try {
            Connection con = ConnectionBuilder.getConnection();
            String sql = "INSERT INTO Branch_Menu(menuNo,branchNo,isAvailable) "
                    + "VALUES(?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, menuNo);
            ps.setInt(2, branchNo);
            ps.setInt(3, isAvailable);
            success = ps.executeUpdate() > 0;
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return success;
    }

    public static boolean addAllMenuIntoBranch(String[] stMenuNo, int branchNo) {
        boolean success = false;
        try {
            Connection con = ConnectionBuilder.getConnection();
            con.setAutoCommit(false);
            String sql = "INSERT INTO Branch_Menu(menuNo,branchNo,isAvailable) "
                    + "VALUES(?,?,0)";
            PreparedStatement ps = con.prepareStatement(sql);
            for (String mn : stMenuNo) {
                ps.setInt(1, Integer.parseInt(mn));
                ps.setInt(2, branchNo);
                ps.addBatch();
            }
            ps.executeBatch();
            
            con.commit();
            success = true;
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return success;
    }

    public boolean editMenu(List<Integer> menuTypeNo, List<MenuMaterial> menuMat, int isAvailable) {
        boolean success = false;
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
            boolean successInSQL = ps.executeUpdate() > 0;
            if (successInSQL) {
                successInSQL = editMenuInBranch(isAvailable, menuPrice, branchNo, menuNo);
                if (successInSQL) {
                    sql = "DELETE FROM Menu_MenuType WHERE menuNo = ?";
                    ps = con.prepareStatement(sql);
                    ps.setInt(1, menuNo);
                    ps.executeUpdate();

                    sql = "INSERT INTO Menu_MenuType(menuNo,menuTypeNo) VALUES(?,?)";
                    ps = con.prepareStatement(sql);
                    for (int mtn : menuTypeNo) {
                        ps.setInt(1, menuNo);
                        ps.setInt(2, mtn);
                        ps.addBatch();
                    }
                    ps.executeBatch();

                    sql = "DELETE FROM Menu_Material WHERE menuNo = ?";
                    ps = con.prepareStatement(sql);
                    ps.setInt(1, menuNo);
                    ps.executeUpdate();

                    sql = "INSERT INTO Menu_Material(menuNo,materialNo,quantity,unit) VALUES(?,?,?,?)";
                    ps = con.prepareStatement(sql);
                    for (MenuMaterial mm : menuMat) {
                        ps.setInt(1, menuNo);
                        ps.setInt(2, mm.getMaterialNo());
                        ps.setDouble(3, mm.getQuantity());
                        ps.setString(4, mm.getUnit());
                        ps.addBatch();
                    }
                    ps.executeBatch();

                    con.commit();
                    success = true;
                }
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return success;
    }

    public static boolean editMenuInBranch(int isAvailable, double price, int branchNo, int menuNo) {
        boolean success = false;
        try {
            Connection con = ConnectionBuilder.getConnection();
            String sql = "UPDATE Branch_Menu SET isAvailable = ?,price = ? WHERE branchNo = ? AND menuNo = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, isAvailable);
            ps.setDouble(2, price);
            ps.setInt(3, branchNo);
            ps.setInt(4, menuNo);
            success = ps.executeUpdate() > 0;
            con.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return success;
    }

    private static void orm(ResultSet rs, Menu m) throws Exception {
        m.setMenuNo(rs.getInt("menuNo"));
        m.setMenuNameTH(rs.getString("menuNameTH"));
        m.setMenuNameEN(rs.getString("menuNameEN"));
        m.setMenuDesc(rs.getString("menuDesc"));
        m.setMenuPrice(rs.getDouble("menuPrice"));
        m.setIsOfficialMenu(rs.getInt("isOfficialMenu"));
        m.setBranchNo(rs.getInt("branchNo"));
        m.setIsAvailable(rs.getInt("isAvailable") == 1);
        m.setMenuPicPath(rs.getString("menuPicPath"));
    }

    @Override
    public String toString() {
        return "Menu{" + "menuNo=" + menuNo + ", menuNameTH=" + menuNameTH + ", menuNameEN=" + menuNameEN + ", menuDesc=" + menuDesc + ", menuPicPath=" + menuPicPath + ", menuPrice=" + menuPrice + ", isOfficialMenu=" + isOfficialMenu + ", isThisBranchMenu=" + isThisBranchMenu + ", isAvailable=" + isAvailable + ", branchNo=" + branchNo + '}';
    }
}
