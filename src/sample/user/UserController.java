package sample.user;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sample.entity.UserEntity;
import sample.service.UserService;

import java.util.Optional;

public class UserController {

    @FXML
    private TableView<UserEntity> userTable;
    @FXML
    private Pagination userPage;

    public void initUserTableData() {
        UserService service = new UserService();
        userTable.setItems(service.findAll());
    }

    public void openAddUserClick(ActionEvent actionEvent) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("adduser.fxml"));
        GridPane gridPane = fxmlLoader.load();
        AddController addController = fxmlLoader.getController();
        addController.initAdd(this.userTable);
        Stage stage = new Stage();
        stage.setTitle("添加用户");
        stage.setResizable(false);
        stage.setScene(new Scene(gridPane, 300, 160));
        stage.showAndWait();
    }

    public void findUser(ActionEvent actionEvent) {
        initUserTableData();
    }

    public void openEditUser(ActionEvent actionEvent) throws Exception {
        UserEntity userEntity = userTable.getSelectionModel().getSelectedItem();
        if (userEntity == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("提示");
            alert.setContentText("请选择一行");
            alert.showAndWait();
            return;
        }
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("edituser.fxml"));
        GridPane gridPane = fxmlLoader.load();
        EditController editController = fxmlLoader.getController();
        editController.initEdit(userEntity,userTable);
        Stage stage = new Stage();
        stage.setTitle("编辑用户");
        stage.setScene(new Scene(gridPane, 300, 160));
        stage.showAndWait();

    }

    public void delUser(ActionEvent actionEvent) {
        UserEntity userEntity = userTable.getSelectionModel().getSelectedItem();
        if (userEntity == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("提示");
            alert.setContentText("请选择一行");
            alert.showAndWait();
            return;
        }
        Alert _alert = new Alert(Alert.AlertType.CONFIRMATION, "确认删除吗？", new ButtonType("取消", ButtonBar.ButtonData.NO),
                new ButtonType("确定", ButtonBar.ButtonData.YES));
        _alert.setTitle("确认");
        _alert.setHeaderText("提示");
        Optional<ButtonType> _buttonType = _alert.showAndWait();
        if (_buttonType.get().getButtonData().equals(ButtonBar.ButtonData.YES)) {
            UserService service = new UserService();
            int result = service.delete(userEntity.getId());
            if (result > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("提示");
                alert.setContentText("操作成功");
                alert.showAndWait();
                initUserTableData();
            }
        }
    }
}
