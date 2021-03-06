//check code I
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.LinkedList;
import java.util.List;

public class MaterialType {
    private int matTypeNo;
    private String matTypeName;

    public int getMatTypeNo() {
        return matTypeNo;
    }

    public void setMatTypeNo(int matTypeNo) {
        this.matTypeNo = matTypeNo;
    }

    public String getMatTypeName() {
        return matTypeName;
    }

    public void setMatTypeName(String matTypeName) {
        this.matTypeName = matTypeName;
    }
    
    public static List<MaterialType> getAllMaterialType(){
        List<MaterialType> matTypes = null;
        try {
            Connection con = ConnectionBuilder.getConnection();
            String sql = "SELECT * FROM MaterialType";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            matTypes = new LinkedList<MaterialType>();
            while(rs.next()){
                MaterialType mt = new MaterialType();
                orm(rs,mt);
                matTypes.add(mt);
            }
            con.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return matTypes;
    }
    
    public boolean addMaterialType(){
        boolean success = false;
        try {
            Connection con = ConnectionBuilder.getConnection();
            String sql = "INSERT INTO MaterialType(matTypeName) VALUES(?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,matTypeName);
            success = ps.executeUpdate() > 0;
            con.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return success;
    }
    
    public boolean editMaterialType(){
        boolean success = false;
        try {
            Connection con = ConnectionBuilder.getConnection();
            String sql = "UPDATE MaterialType SET matTypeName = ? WHERE matTypeNo = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,matTypeName);
            ps.setInt(2,matTypeNo);
            success = ps.executeUpdate() > 0;
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return success;
    }
    
    public boolean delMaterialType(int matTypeno){
        boolean success = false;
        try {
            Connection con = ConnectionBuilder.getConnection();
            String sql = "DELETE FROM MaterialType WHERE matTypeNo = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,matTypeNo);
            success = ps.executeUpdate() > 0;
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return success;
    }
    
    private static void orm(ResultSet rs,MaterialType mt) throws Exception{
        mt.setMatTypeName(rs.getString("matTypeName"));
        mt.setMatTypeNo(rs.getInt("matTypeNo"));
    }
}
