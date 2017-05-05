package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Material {
    private int matNo;
    private String matName;
    private int matTypeNo;

    public int getMatNo() {
        return matNo;
    }

    public void setMatNo(int matNo) {
        this.matNo = matNo;
    }

    public String getMatName() {
        return matName;
    }

    public void setMatName(String matName) {
        this.matName = matName;
    }

    public int getMatTypeNo() {
        return matTypeNo;
    }

    public void setMatTypeNo(int matTypeNo) {
        this.matTypeNo = matTypeNo;
    }
    
    public static List<Material> getAllMaterial(){
        List<Material> mats = null;
        try {
            Connection con = ConnectionBuilder.getConnection();
            String sql = "SELECT * FROM Material";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            mats = new ArrayList<Material>();
            while(rs.next()){
                Material m = new Material();
                orm(rs,m);
                mats.add(m);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return mats;
    }
    
    public void addMaterialType(){
        try {
            Connection con = ConnectionBuilder.getConnection();
            String sql = "INSERT INTO Material(matName,matTypeNo) VALUES(?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,matName);
            ps.setInt(2,matTypeNo);
            ps.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void editMaterialType(){
        try {
            Connection con = ConnectionBuilder.getConnection();
            String sql = "UPDATE Material SET matName = ?, matTypeNo = ? WHERE matNo = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,matName);
            ps.setInt(2,matTypeNo);
            ps.setInt(3,matNo);
            ps.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void delMaterialType(int matNo){
        try {
            Connection con = ConnectionBuilder.getConnection();
            String sql = "DELETE FROM Material WHERE matNo = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,matNo);
            ps.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    private static void orm(ResultSet rs,Material m) throws SQLException{
        m.setMatName(rs.getString("matName"));
        m.setMatNo(rs.getInt("matNo"));
        m.setMatTypeNo(rs.getInt("matTypeNo"));
    }
}
