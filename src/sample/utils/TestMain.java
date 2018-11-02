package sample.utils;

import javafx.collections.ObservableList;
import sample.entity.UserEntity;
import sample.service.UserService;

public class TestMain {
    public static void main(String[] args) {
        UserService service = new UserService();
//        service.insert(new UserEntity("ym","管理员"));
        ObservableList<UserEntity> userEntities = service.findAll();
        for (UserEntity userEntity : userEntities) {
            System.out.println(userEntity);
        }
    }
}
