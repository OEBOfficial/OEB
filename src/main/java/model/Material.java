//check code I
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.LinkedList;
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

    public static List<Material> getAllMaterial() {
        List<Material> mats = null;
        try {
            Connection con = ConnectionBuilder.getConnection();
            String sql = "SELECT * FROM Material";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            mats = new LinkedList<Material>();
            while (rs.next()) {
                Material m = new Material();
                orm(rs, m);
                mats.add(m);
            }
            con.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return mats;
    }

    public boolean addMaterial() {
        boolean success = false;
        try {
            Connection con = ConnectionBuilder.getConnection();
            String sql = "INSERT INTO Material(matName,matTypeNo) VALUES(?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, matName);
            ps.setInt(2, matTypeNo);
            success = ps.executeUpdate() > 0;
            con.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return success;
    }

    public boolean editMaterial() {
        boolean success = false;
        try {
            Connection con = ConnectionBuilder.getConnection();
            String sql = "UPDATE Material SET matName = ?, matTypeNo = ? WHERE matNo = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, matName);
            ps.setInt(2, matTypeNo);
            ps.setInt(3, matNo);
            success = ps.executeUpdate() > 0;
            con.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return success;
    }

    public static boolean delMaterial(int matNo) {
        boolean success = false;
        try {
            Connection con = ConnectionBuilder.getConnection();
            String sql = "DELETE FROM Material WHERE matNo = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, matNo);
            success = ps.executeUpdate() > 0;
            con.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return success;
    }

    public static boolean delAllMaterial(String[] stMatNo) {
        boolean success = false;
        try {
            Connection con = ConnectionBuilder.getConnection();
            con.setAutoCommit(false);
            String sql = "DELETE FROM Material WHERE matNo = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            for (String mn : stMatNo) {
                ps.setInt(1, Integer.parseInt(mn));
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

    private static void orm(ResultSet rs, Material m) throws Exception {
        m.setMatName(rs.getString("matName"));
        m.setMatNo(rs.getInt("matNo"));
        m.setMatTypeNo(rs.getInt("matTypeNo"));
    }
}
