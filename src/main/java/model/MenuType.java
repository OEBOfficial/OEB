//check code I
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.Statement;
import java.util.LinkedList;

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
    
    public static LinkedList<MenuType> getAllMenuType(int branchNo){
        LinkedList<MenuType> mt = null;
        try {
            Connection con = ConnectionBuilder.getConnection();
            String sql = "SELECT * FROM MenuType mt "
                    + " JOIN Branch_MenuType bmt ON mt.menuTypeNo = bmt.menuTypeNo "
                    + " WHERE branchNo = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,branchNo);
            ResultSet rs = ps.executeQuery();
            mt = new LinkedList<MenuType>();
            while(rs.next()){
                MenuType m = new MenuType();
                orm(rs, m);
                mt.add(m);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return mt;
    }
    
    public boolean addMenuType(int branchNo){
        boolean success = false;
        try {
            Connection con = ConnectionBuilder.getConnection();
            con.setAutoCommit(false);
            String sql = "INSERT INTO MenuType(menuTypeName) "
                    + "VALUES(?)";
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, menuTypeName);
            boolean successInSQL = ps.executeUpdate() == 1;
            if(successInSQL){
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                menuTypeNo = rs.getInt(1);
                success = addMenuTypeIntoBranch(menuTypeNo,branchNo);
            }
            if(success){
                con.commit();
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return success;
    }
    
    public static boolean addMenuTypeIntoBranch(int menuTypeNo,int branchNo){
        boolean success = false;
        try {
            Connection con = ConnectionBuilder.getConnection();
            String sql = "INSERT INTO Branch_MenuType(menuTypeNo,branchNo) "
                    + "VALUES(?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, menuTypeNo);
            ps.setInt(2, branchNo);
            success = ps.executeUpdate() > 0;
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return success;
    }
    
    public boolean editMenuType(int menuTypeNo){
        boolean success = false;
        try {
            Connection con = ConnectionBuilder.getConnection();
            String sql = "UPDATE MenuType SET menuTypeName = ? WHERE menuTypeNo = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, menuTypeName);
            ps.setInt(2, menuTypeNo);
            success = ps.executeUpdate() > 0;
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return success;
    }
    
    public static boolean delMenuType(int menuTypeNo){
        boolean success = false;
        try {
            Connection con = ConnectionBuilder.getConnection();
            String sql = "DELETE FROM MenuType WHERE menuTypeNo = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, menuTypeNo);
            success = ps.executeUpdate() > 0;
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return success;
    }
    
    public static boolean editMenuType(int menuTypeNo,String menuTypeName){
        boolean success = false;
        try{
            Connection con = ConnectionBuilder.getConnection();
            String sql = "UPDATE MenuType SET menuTypeName = ? WHERE menuTypeNo = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,menuTypeName);
            ps.setInt(2,menuTypeNo);
            success = ps.executeUpdate() > 0;
            con.close();
        }catch(Exception ex){
            System.out.println(ex);
        }
        return success;
    }
    
    private static void orm(ResultSet rs, MenuType m) throws Exception {
        m.setMenuTypeNo(rs.getInt("menuTypeNo"));
        m.setMenuTypeName(rs.getString("menuTypeName"));
    }
}