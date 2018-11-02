package sample.service;

import com.mysql.cj.jdbc.PreparedStatement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import sample.entity.UserEntity;
import sample.utils.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {
    /**
     * 新增
     *
     * @param entity
     * @return
     */
    public int insert(UserEntity entity) {
        Connection connection = DBConnection.getConnection();
        int i = 0;
        String sql = "INSERT INTO `test`.`user` (`name`,`role`,c_date)VALUES(?,?,now());";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) connection.prepareStatement(sql);
            pstmt.setString(1, entity.getName());
            pstmt.setString(2, entity.getRole());
            i = pstmt.executeUpdate();
            if (pstmt != null) {
                pstmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    /**
     * 查询所有信息
     *
     * @return
     */
    public ObservableList<UserEntity> findAll() {
        ObservableList<UserEntity> userEntities = FXCollections.observableArrayList();
        Connection connection = DBConnection.getConnection();
        String sql = "SELECT * FROM test.user;";
        PreparedStatement pstmt = null;
        try {
            pstmt = (PreparedStatement) connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                userEntities.add(new UserEntity(rs.getInt("id"), rs.getString("name"), rs.getString("role"),rs.getString("c_date")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return userEntities;
    }

    public int update(UserEntity entity) {
        Connection connection = DBConnection.getConnection();
        int i = 0;
        String sql = "UPDATE user SET name = '" + entity.getName() + "', role = '" + entity.getRole() + "'  WHERE id = " + entity.getId();
        PreparedStatement pstmt = null;
        try {
            pstmt = (PreparedStatement) connection.prepareStatement(sql);
            i = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closePstmt(pstmt);
            DBConnection.closeConnect(connection);
        }
        return i;
    }

    public int delete(int id) {
        Connection connection = DBConnection.getConnection();
        int i = 0;
        String sql = "DELETE FROM user WHERE id =" + id;
        PreparedStatement pstmt = null;
        try {
            pstmt = (PreparedStatement)connection.prepareStatement(sql);
            i = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closePstmt(pstmt);
            DBConnection.closeConnect(connection);
        }
        return i;
    }

    public ObservableList<PieChart.Data> initPieData(){
        ObservableList<PieChart.Data> pie = FXCollections.observableArrayList();
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstmt =null;
        String sql = "select role,count(*) as num from user group by role;";
        try {
            pstmt = (PreparedStatement)connection.prepareStatement(sql);
            ResultSet set = pstmt.executeQuery();
            while (set.next()){
                pie.addAll(new PieChart.Data(set.getString("role"),set.getInt("num")));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBConnection.closePstmt(pstmt);
            DBConnection.closeConnect(connection);
        }
        return pie;
    }
}
