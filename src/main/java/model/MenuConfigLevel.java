package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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

    public static boolean addMenuConfig(String menuConfigName, List<MenuConfigLevel> menuConLV) {
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

    public static boolean editMenuConfig(int menuConfigNo, List<MenuConfigLevel> menuConLV) {
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

    private static boolean addMenuConfigLevel(int menuConfigNo, List<MenuConfigLevel> menuConLV, Connection con) {
        boolean success = false;
        try {
            String sql = "INSERT INTO MenuConfigLevel(menuConfigLevelNo,menuConfigLevelName,menuConfigNo) VALUES(?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            for (MenuConfigLevel mcl : menuConLV) {
                ps.setInt(1, mcl.getMenuConfigLevelNo());
                ps.setString(2, mcl.getMenuConfigLevelName());
                ps.setInt(3, menuConfigNo);
                ps.addBatch();
            }
            ps.executeBatch();
            success = true;
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return success;
    }
}