package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Menu {
    private int menuNo;
    private String menuNameTH;
    private String menuNameEN;
    private String menuDesc;
    private double menuPrice;
    private int isOfficialMenu;

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

    public double getMenuPrice() {
        return menuPrice;
    }

    public void setMenuPrice(double menuPrice) {
        this.menuPrice = menuPrice;
    }

    public int getIsOfficialMenu() {
        return isOfficialMenu;
    }

    public void setIsOfficialMenu(int isOfficialMenu) {
        this.isOfficialMenu = isOfficialMenu;
    }
    
    public static ArrayList<Menu> getAllMenu(){
        ArrayList<Menu> menus = null;
        try {
            Connection con = ConnectionBuilder.getConnection();
            String sql = "SELECT * FROM Menu";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            menus = new ArrayList<Menu>();
            while(rs.next()){
                Menu m = new Menu();
                orm(rs,m);
                menus.add(m);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return menus;
    }
    
    
    public static Menu getMenu(int menuNo){
        Menu m = null;
        try {
            Connection con = ConnectionBuilder.getConnection();
            String sql = "SELECT * FROM Menu WHERE menuNo = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, menuNo);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                m = new Menu();
                orm(rs,m);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return m;
    }
    
    public static void delMenu(int menuNo){
        try {
            Connection con = ConnectionBuilder.getConnection();
            String sql = "DELETE FROM Menu WHERE menuNo = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, menuNo);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void addMenu(int isOfficialMenu){
        try {
            Connection con = ConnectionBuilder.getConnection();
            String sql = "INSERT INTO Menu(menuNameTH,menuNameEN,menuDesc,menuPrice,isOfficialMenu) "
                    + "VALUES(?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, menuNameTH);
            ps.setString(2, menuNameEN);
            ps.setString(3, menuDesc);
            ps.setDouble(4, menuPrice);
            ps.setInt(5, isOfficialMenu);
            ps.executeUpdate();           
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void editMenu(int menuNo){
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
    
    private static void orm(ResultSet rs,Menu m) throws SQLException {
        m.setMenuNo(rs.getInt("menuNo"));
        m.setMenuNameTH(rs.getString("menuNameTH"));
        m.setMenuNameEN(rs.getString("menuNameEN"));
        m.setMenuDesc(rs.getString("menuDesc"));
        m.setMenuPrice(rs.getDouble("menuPrice"));
        m.setIsOfficialMenu(rs.getInt("isOfficialMenu"));
    }
    
    public static void main(String[] args) {
        Menu m = new Menu();
        m.setMenuNameTH("ผัดไทยกุ้งสด");
        m.setMenuNameEN("Pud Thai");
        m.setMenuDesc("เส้นใหญ่อร่อยมากกกกก");
        m.setMenuPrice(35);
        m.editMenu(6);
    }
    
    
    @Override
    public String toString() {
        return "Menu{" + "menuNo=" + menuNo + ", menuNameTH=" + menuNameTH + ", menuNameEN=" + menuNameEN + ", menuDesc=" + menuDesc + ", menuPrice=" + menuPrice + ", isOfficialMenu=" + isOfficialMenu + '}';
    }
}
