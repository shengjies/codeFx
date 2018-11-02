package sample.service;

import com.mysql.cj.jdbc.PreparedStatement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.entity.RoleEntity;
import sample.utils.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;


public class RoleService {
    /**
     * 查询所有角色
     *
     * @return
     */
    public ObservableList<RoleEntity> findAll() {
        ObservableList<RoleEntity> roleEntities = FXCollections.observableArrayList();
        Connection connection = DBConnection.getConnection();
        String sql = "SELECT * FROM test.role;";
        PreparedStatement pstmt = null;
        try {
            pstmt = (PreparedStatement) connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                roleEntities.add(new RoleEntity(rs.getInt("id"), rs.getString("name"), rs.getString("c_date")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closePstmt(pstmt);
            DBConnection.closeConnect(connection);
        }
        return roleEntities;
    }

    /**
     * 添加
     *
     * @param entity
     * @return
     */
    public int insert(RoleEntity entity) {
        int i = 0;
        Connection connection = DBConnection.getConnection();
        String sql = "INSERT INTO role (name,c_date) VALUES(?,now())";
        PreparedStatement pstmt = null;
        try {
            pstmt = (PreparedStatement) connection.prepareStatement(sql);
            pstmt.setString(1, entity.getName());
            i = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closePstmt(pstmt);
            DBConnection.closeConnect(connection);
        }
        return i;
    }

    public int update(RoleEntity entity) {
        int i = 0;
        Connection connection = DBConnection.getConnection();
        String sql = "UPDATE role SET name = '"+entity.getName()+"' WHERE id = "+entity.getId();
        PreparedStatement pstms = null;
        try {
            pstms = (PreparedStatement) connection.prepareStatement(sql);
            i = pstms.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closePstmt(pstms);
            DBConnection.closeConnect(connection);
        }
        return i;
    }

    public int delete (int id){
        int i =0;
        Connection connection = DBConnection.getConnection();
        String sql = "DELETE FROM role  WHERE id="+id;
        PreparedStatement pstmt =null;
        try {
            pstmt = (PreparedStatement)connection.prepareStatement(sql);
            i = pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBConnection.closePstmt(pstmt);
            DBConnection.closeConnect(connection);
        }
        return  i;
    }
}
