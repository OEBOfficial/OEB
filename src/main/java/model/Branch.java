//check code I
package model;

import java.sql.ResultSet;

public class Branch {
    private int branchNo;
    private String branchName;
    private String branchDesc;
    private int restNo;
    private int addressNo;
    private int restOwnerNo;

    public int getRestNo() {
        return restNo;
    }

    public void setRestNo(int restNo) {
        this.restNo = restNo;
    }

    public int getAddressNo() {
        return addressNo;
    }

    public void setAddressNo(int addressNo) {
        this.addressNo = addressNo;
    }

    public int getRestOwnerNo() {
        return restOwnerNo;
    }

    public void setRestOwnerNo(int restOwnerNo) {
        this.restOwnerNo = restOwnerNo;
    }

    public int getBranchNo() {
        return branchNo;
    }

    public void setBranchNo(int branchNo) {
        this.branchNo = branchNo;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchDesc() {
        return branchDesc;
    }

    public void setBranchDesc(String branchDesc) {
        this.branchDesc = branchDesc;
    }
    
    private static void orm(ResultSet rs,Branch b) throws Exception{
        b.setBranchDesc(rs.getString("branchDesc"));
        b.setBranchName(rs.getString("branchName"));
        b.setBranchNo(rs.getInt("branchNo"));
        b.setRestOwnerNo(rs.getInt("restOwnerNo"));
        b.setBranchNo(rs.getInt("branchNo"));
    }
}