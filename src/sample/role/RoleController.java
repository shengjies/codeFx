package sample.role;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sample.entity.RoleEntity;
import sample.service.RoleService;

import java.util.Optional;

public class RoleController {
    @FXML
    private TableView<RoleEntity> roleTable;

    public void initRoleData() {
        RoleService roleService = new RoleService();
        roleTable.setItems(roleService.findAll());
    }

    public void findRole(ActionEvent actionEvent) {
        this.initRoleData();
    }

    public void openAddRole(ActionEvent actionEvent) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("add.fxml"));
        GridPane gridPane = fxmlLoader.load();
        AddController addController = fxmlLoader.getController();
        addController.initRoleTable(roleTable);
        Stage stage = new Stage();
        stage.setTitle("添加角色");
        stage.setScene(new Scene(gridPane, 300, 120));
        stage.showAndWait();
    }

    public void openEditRole(ActionEvent actionEvent) throws Exception {
        RoleEntity entity = roleTable.getSelectionModel().getSelectedItem();
        if (entity == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("提示");
            alert.setContentText("请选择一行");
            alert.showAndWait();
            return;
        }
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("edit.fxml"));
        GridPane edit = fxmlLoader.load();
        EditController editController = fxmlLoader.getController();
        editController.initEditRole(entity, roleTable);
        Stage stage = new Stage();
        stage.setTitle("编辑角色");
        stage.setScene(new Scene(edit, 300, 120));
        stage.showAndWait();
    }

    public void delRole(ActionEvent actionEvent) {
        RoleEntity entity = roleTable.getSelectionModel().getSelectedItem();
        if (entity == null) {
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
            RoleService service = new RoleService();
            int result = service.delete(entity.getId());
            if (result > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("提示");
                alert.setContentText("操作成功");
                alert.showAndWait();
                initRoleData();
            }
        }
    }
}
