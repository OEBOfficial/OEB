package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MenuType {
    private int menuTypeNo;
    private String menuTypeName;

    public int getMenuTypeNo() {
        return menuTypeNo;
    }

    public void setMenuTypeNo(int menuTypeNo) {
        this.menuTypeNo = menuTypeNo;
    }

    public String getMenuTypeName() {
        return menuTypeName;
    }

    public void setMenuTypeName(String menuTypeName) {
        this.menuTypeName = menuTypeName;
    }
    
    public static ArrayList<MenuType> getAllMenuType(){
        ArrayList<MenuType> mt = null;
        try {
            Connection con = ConnectionBuilder.getConnection();
            String sql = "SELECT * FROM MenuType";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            mt = new ArrayList<MenuType>();
            while(rs.next()){
                MenuType m = new MenuType();
                orm(rs, m);
                mt.add(m);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return mt;
    }
    
    public void addMenuType(){
        try {
            Connection con = ConnectionBuilder.getConnection();
            String sql = "INSERT INTO MenuType(menuTypeName) "
                    + "VALUES(?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, menuTypeName);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void editMenuType(int menuTypeNo){
        try {
            Connection con = ConnectionBuilder.getConnection();
            String sql = "UPDATE MenuType SET menuTypeName = ? WHERE menuTypeNo = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, menuTypeName);
            ps.setInt(2, menuTypeNo);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public static void delMenuType(int menuTypeNo){
        try {
            Connection con = ConnectionBuilder.getConnection();
            String sql = "DELETE FROM MenuType WHERE menuTypeNo = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, menuTypeNo);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    private static void orm(ResultSet rs, MenuType m) throws SQLException {
        m.setMenuTypeNo(rs.getInt("menuTypeNo"));
        m.setMenuTypeName(rs.getString("menuTypeName"));
    }
        
}
