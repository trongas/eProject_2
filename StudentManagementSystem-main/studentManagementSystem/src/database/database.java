package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class database {
    
    public static Connection connectDb(){
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/studentmanagement?useSSL=false", "root", "trong190724");
            return connect;
        }catch(Exception e){    
            e.printStackTrace();
        }
        return null;
    }
    
}