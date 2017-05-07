package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class MenuConfigLevel {

    private int menuConfigNo;
    private String menuConfigName;
    private int menuConfigLevelNo;
    private String menuConfigLevelName;

    public int getMenuConfigNo() {
        return menuConfigNo;
    }

    public void setMenuConfigNo(int menuConfigNo) {
        this.menuConfigNo = menuConfigNo;
    }

    public String getMenuConfigName() {
        return menuConfigName;
    }

    public void setMenuConfigName(String menuConfigName) {
        this.menuConfigName = menuConfigName;
    }

    public int getMenuConfigLevelNo() {
        return menuConfigLevelNo;
    }

    public void setMenuConfigLevelNo(int menuConfigLevelNo) {
        this.menuConfigLevelNo = menuConfigLevelNo;
    }

    public String getMenuConfigLevelName() {
        return menuConfigLevelName;
    }

    public void setMenuConfigLevelName(String menuConfigLevelName) {
        this.menuConfigLevelName = menuConfigLevelName;
    }

    public static List<MenuConfigLevel> getAllMenuConfig() {
        List<MenuConfigLevel> mcl = null;
        try {
            Connection con = ConnectionBuilder.getConnection();
            String sql = "SELECT * FROM MenuConfig";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            mcl = new LinkedList<MenuConfigLevel>();
            while (rs.next()) {
                MenuConfigLevel mc = new MenuConfigLevel();
                mc.setMenuConfigNo(rs.getInt("menuConfigNo"));
                mc.setMenuConfigName(rs.getString("menuConfigName"));
                mcl.add(mc);
            }
            con.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return mcl;
    }

    public static boolean addMenuConfig(String menuConfigName, List<String> menuConLV) {
        boolean success = false;
        try {
            Connection con = ConnectionBuilder.getConnection();
            con.setAutoCommit(false);
            String sql = "INSERT INTO MenuConfig(menuConfigName) VALUES(?)";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, menuConfigName);
            boolean successInSQL = ps.executeUpdate() == 1;
            if (successInSQL) {
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                int menuConfigNo = rs.getInt(1);

                success = addMenuConfigLevel(menuConfigNo, menuConLV, con);
                con.commit();
            }
            con.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return success;
    }

    public static boolean editMenuConfig(int menuConfigNo, List<String> menuConLV) {
        boolean success = false;
        try {
            Connection con = ConnectionBuilder.getConnection();
            con.setAutoCommit(false);
            String sql = "DELETE FROM MenuConfigLevel WHERE menuConfigNo = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, menuConfigNo);
            ps.executeUpdate();

            success = addMenuConfigLevel(menuConfigNo, menuConLV, con);
            con.commit();
            con.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return success;
    }

    private static boolean addMenuConfigLevel(int menuConfigNo, List<String> menuConLV, Connection con) {
        boolean success = false;
        try {
            String sql = "INSERT INTO MenuConfigLevel(menuConfigLevelNo,menuConfigLevelName,menuConfigNo) VALUES(?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            int i = 1;
            for (String mcl : menuConLV) {
                ps.setInt(1, i);
                ps.setString(2, mcl);
                ps.setInt(3, menuConfigNo);
                ps.addBatch();
                i++;
            }
            ps.executeBatch();
            success = true;
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return success;
    }

    public static boolean delMenuConfig(int menuConfigNo) {
        boolean success = false;
        try {
            Connection con = ConnectionBuilder.getConnection();
            String sql = "DELETE FROM MenuConfig WHERE menuConfigNo = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, menuConfigNo);
            success = ps.executeUpdate() == 1;
            con.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return success;
    }

    public static boolean delAllMenuConfig(String[] stMenuConfigNo) {
        boolean success = false;
        try {
            Connection con = ConnectionBuilder.getConnection();
            con.setAutoCommit(false);
            String sql = "DELETE FROM MenuConfig WHERE menuConfigNo = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            for (String mcn : stMenuConfigNo) {
                ps.setInt(1, Integer.parseInt(mcn));
                ps.addBatch();
            }
            ps.executeBatch();
            con.commit();
            success = true;
            con.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return success;
    }
}
