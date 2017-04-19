package model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PayType {
    private int payTypeNo;
    private String payTypeName;

    public PayType() {
    }
    
    public PayType(int payTypeNo, String payTypeName) {
        this.payTypeNo = payTypeNo;
        this.payTypeName = payTypeName;
    }
    
    public int getPayTypeNo() {
        return payTypeNo;
    }

    public void setPayTypeNo(int payTypeNo) {
        this.payTypeNo = payTypeNo;
    }

    public String getPayTypeName() {
        return payTypeName;
    }

    public void setPayTypeName(String payTypeName) {
        this.payTypeName = payTypeName;
    }
    
    public static void orm(ResultSet rs, PayType pt) throws SQLException{
        pt.setPayTypeNo(rs.getInt("payTypeNo"));
        pt.setPayTypeName(rs.getString("payTypeName"));
    }

    @Override
    public String toString() {
        return "PayType{" + "payTypeNo=" + payTypeNo + ", payTypeName=" + payTypeName + '}';
    }
    
}
