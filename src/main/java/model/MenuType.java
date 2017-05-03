package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    
    public static ArrayList<MenuType> getAllMenuType(int branchNo){
        ArrayList<MenuType> mt = null;
        try {
            Connection con = ConnectionBuilder.getConnection();
            String sql = "SELECT * FROM MenuType mt "
                    + " JOIN Branch_MenuType bmt ON mt.menuTypeNo = bmt.menuTypeNo "
                    + " WHERE branchNo = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,branchNo);
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
    
    public void addMenuType(int branchNo){
        try {
            Connection con = ConnectionBuilder.getConnection();
            String sql = "INSERT INTO MenuType(menuTypeName) "
                    + "VALUES(?)";
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, menuTypeName);
            int success = ps.executeUpdate();
            if(success == 1){
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                menuTypeNo = rs.getInt(1);
                addMenuTypeIntoBranch(menuTypeNo,branchNo);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public static void addMenuTypeIntoBranch(int menuTypeNo,int branchNo){
        try {
            Connection con = ConnectionBuilder.getConnection();
            String sql = "INSERT INTO Branch_MenuType(menuTypeNo,branchNo) "
                    + "VALUES(?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, menuTypeNo);
            ps.setInt(2, branchNo);
            int success = ps.executeUpdate();
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
    
    public static void editMenuType(int menuTypeNo,String menuTypeName){
        try{
            Connection con = ConnectionBuilder.getConnection();
            String sql = "UPDATE MenuType SET menuTypeName = ? WHERE menuTypeNo = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,menuTypeName);
            ps.setInt(2,menuTypeNo);
            int success = ps.executeUpdate();
        }catch(SQLException ex){
            System.out.println(ex);
        }
    }
    
    private static void orm(ResultSet rs, MenuType m) throws SQLException {
        m.setMenuTypeNo(rs.getInt("menuTypeNo"));
        m.setMenuTypeName(rs.getString("menuTypeName"));
    }
        
}
