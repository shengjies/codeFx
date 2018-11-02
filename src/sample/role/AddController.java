package sample.role;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.entity.RoleEntity;
import sample.service.RoleService;

public class AddController {
    @FXML
    private TextField addRoleName;

    private TableView<RoleEntity> tableView;

    public void initRoleTable(TableView<RoleEntity> tableView) {
        this.tableView = tableView;
    }

    public void addRole(ActionEvent actionEvent) {
        RoleEntity roleEntity = new RoleEntity(addRoleName.getText());
        RoleService roleService = new RoleService();
        int result = roleService.insert(roleEntity);
        Stage stage = (Stage) addRoleName.getScene().getWindow();
        if (result > 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("提示");
            alert.setContentText("添加成功");
            alert.showAndWait();
            tableView.setItems(roleService.findAll());
        }
        stage.close();
    }
}
