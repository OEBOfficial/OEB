//check code I
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedHashMap;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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

    public static List<MaterialType> getAllMaterialType() {
        List<MaterialType> matTypes = null;
        try {
            Connection con = ConnectionBuilder.getConnection();
            String sql = "SELECT * FROM MaterialType WHERE MatTypeNo <> 0";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            matTypes = new LinkedList<MaterialType>();
            while (rs.next()) {
                MaterialType mt = new MaterialType();
                orm(rs, mt);
                matTypes.add(mt);
            }
            con.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return matTypes;
    }

    public static Map<Integer, MaterialType> getAllMaterialTypeMap() {
        Map<Integer, MaterialType> matTypes = null;
        try {
            Connection con = ConnectionBuilder.getConnection();
            String sql = "SELECT * FROM MaterialType ORDER BY matTypeNo";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            matTypes = new LinkedHashMap<Integer, MaterialType>();
            while (rs.next()) {
                MaterialType mt = new MaterialType();
                orm(rs, mt);
                matTypes.put(mt.getMatTypeNo(), mt);
            }
            con.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return matTypes;
    }

    public boolean addMaterialType() {
        boolean success = false;
        try {
            Connection con = ConnectionBuilder.getConnection();
            String sql = "INSERT INTO MaterialType(matTypeName) VALUES(?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, matTypeName);
            success = ps.executeUpdate() > 0;
            con.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return success;
    }

    public boolean editMaterialType() {
        boolean success = false;
        try {
            Connection con = ConnectionBuilder.getConnection();
            String sql = "UPDATE MaterialType SET matTypeName = ? WHERE matTypeNo = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, matTypeName);
            ps.setInt(2, matTypeNo);
            success = ps.executeUpdate() > 0;
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return success;
    }

    public static boolean delMaterialType(int matTypeNo) {
        boolean success = false;
        try {
            Connection con = ConnectionBuilder.getConnection();
            String sql = "UPDATE Material SET matTypeNo = 0 WHERE matTypeNo = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, matTypeNo);
            ps.executeUpdate();
            sql = "DELETE FROM MaterialType WHERE matTypeNo = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, matTypeNo);
            success = ps.executeUpdate() > 0;
            con.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return success;
    }

    public static boolean delAllMaterialType(String[] stMatTypeNo) {
        boolean success = false;
        try {
            Connection con = ConnectionBuilder.getConnection();
            con.setAutoCommit(false);
            String sql = "UPDATE Material SET matTypeNo = 0 WHERE matTypeNo = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            for (String mtn : stMatTypeNo) {
                ps.setInt(1, Integer.parseInt(mtn));
                ps.addBatch();
            }
            ps.executeBatch();
            sql = "DELETE FROM MaterialType WHERE matTypeNo = ?";
            ps = con.prepareStatement(sql);
            for (String mtn : stMatTypeNo) {
                ps.setInt(1, Integer.parseInt(mtn));
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

    private static void orm(ResultSet rs, MaterialType mt) throws Exception {
        mt.setMatTypeName(rs.getString("matTypeName"));
        mt.setMatTypeNo(rs.getInt("matTypeNo"));
    }
}
