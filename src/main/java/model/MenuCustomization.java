package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class MenuCustomization {
    private int menuNo;
    private int menuConfigNo;
    private int menuConfigLevelNo;
    private int materialNo;
    private double quantity;
    private String unit;

    public int getMenuNo() {
        return menuNo;
    }

    public void setMenuNo(int menuNo) {
        this.menuNo = menuNo;
    }

    public int getMenuConfigNo() {
        return menuConfigNo;
    }

    public void setMenuConfigNo(int menuConfigNo) {
        this.menuConfigNo = menuConfigNo;
    }

    public int getMenuConfigLevelNo() {
        return menuConfigLevelNo;
    }

    public void setMenuConfigLevelNo(int menuConfigLevelNo) {
        this.menuConfigLevelNo = menuConfigLevelNo;
    }

    public int getMaterialNo() {
        return materialNo;
    }

    public void setMaterialNo(int materialNo) {
        this.materialNo = materialNo;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
    
    public static boolean editMenuCustomization(int menuNo,List<MenuCustomization> menuCust){
        boolean success = false;
        try {
            Connection con = ConnectionBuilder.getConnection();
            con.setAutoCommit(success);
            String sql = "DELETE FROM MenuCustomization WHERE menuNo = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, menuNo);
            ps.executeUpdate();
            
            sql = "INSERT INTO MenuCustomization(menuNo,menuConfigNo,menuConfigLevelNo,materialNo,quantity,unit) VALUES(?,?,?,?,?,?)";
            ps = con.prepareStatement(sql);
            for(MenuCustomization mc : menuCust){
                ps.setInt(1, mc.getMenuNo());
                ps.setInt(2, mc.getMenuConfigNo());
                ps.setInt(3, mc.getMenuConfigLevelNo());
                ps.setInt(4, mc.getMaterialNo());
                ps.setDouble(5, mc.getMaterialNo());
                ps.setString(6, mc.getUnit());
                ps.addBatch();
            }
            ps.executeBatch();
            success = true;
            con.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return success;
    }
}
