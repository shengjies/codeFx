package sample.utils;

import com.mysql.cj.jdbc.PreparedStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection getConnection(){
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/test?serverTimezone=CTT&useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true";
        String username ="root";
        String password ="root";
        Connection connection =null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url,username,password);
        }catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnect(Connection connection){
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closePstmt(PreparedStatement pstmt){
        if(pstmt != null){
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
