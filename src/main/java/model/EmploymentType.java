//check code I
package model;

import java.sql.ResultSet;


public class EmploymentType {
    private int empTypeNo;
    private String empTypeName;

    public EmploymentType() {
    }  

    public EmploymentType(int empTypeNo, String empTypeName) {
        this.empTypeNo = empTypeNo;
        this.empTypeName = empTypeName;
    }
    
    public int getEmpTypeNo() {
        return empTypeNo;
    }

    public void setEmpTypeNo(int empTypeNo) {
        this.empTypeNo = empTypeNo;
    }

    public String getEmpTypeName() {
        return empTypeName;
    }

    public void setEmpTypeName(String empTypeName) {
        this.empTypeName = empTypeName;
    }
    
    public static void orm(ResultSet rs,EmploymentType et) throws Exception{
        et.setEmpTypeName(rs.getString("empTypeName"));
        et.setEmpTypeNo(rs.getInt("empTypeNo"));
    }

    @Override
    public String toString() {
        return "EmploymentType{" + "empTypeNo=" + empTypeNo + ", empTypeName=" + empTypeName + '}';
    }
}