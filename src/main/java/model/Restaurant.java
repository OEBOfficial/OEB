//check code I
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Restaurant {
    private int restNo;
    private String restName;

    public int getRestNo() {
        return restNo;
    }

    public void setRestNo(int restNo) {
        this.restNo = restNo;
    }

    public String getRestName() {
        return restName;
    }

    public void setRestName(String restName) {
        this.restName = restName;
    }
    
    public static Restaurant getRestaurant(int restNo){
        Restaurant r = null;
        try{
            Connection con = ConnectionBuilder.getConnection();
            String sql = "SELECT * FROM Restaurant WHERE restNo = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,restNo);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                r = new Restaurant();
                orm(rs,r);
            }
            con.close();
        }catch(Exception ex){
            System.out.println(ex);
        }
        return r;
    }
    
    private static void orm(ResultSet rs,Restaurant r) throws Exception{
        r.setRestName(rs.getString("restName"));
        r.setRestNo(rs.getInt("restNo"));
    }
}
