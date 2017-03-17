/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author USER
 */
public class Address {
    private int addressNo;
    private String addressName;
    private String district;
    private String subDistrict;
    private String road;
    private String province;
    private String zip;

    public int getAddressNo() {
        return addressNo;
    }

    public void setAddressNo(int addressNo) {
        this.addressNo = addressNo;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getSubDistrict() {
        return subDistrict;
    }

    public void setSubDistrict(String subDistrict) {
        this.subDistrict = subDistrict;
    }

    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
    
    public static Address getAddress(int addressNo){
        Address a = null;
        try{
            Connection con = ConnectionBuilder.getConnection();
            String sql = "SELECT * FROM Address WHERE addressNo = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,addressNo);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                a = new Address();
                orm(rs,a);
            }
            con.close();
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return a;
    }
    
    private static void orm(ResultSet rs,Address a) throws SQLException{
        a.setAddressName(rs.getString("addressName"));
        a.setAddressNo(rs.getInt("addressNo"));
        a.setDistrict(rs.getString("district"));
        a.setProvince(rs.getString("province"));
        a.setRoad(rs.getString("road"));
        a.setSubDistrict(rs.getString("subDistrict"));
        a.setZip(rs.getString("zip"));
    }
}
