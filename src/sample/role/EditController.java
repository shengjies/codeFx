package sample.role;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.entity.RoleEntity;
import sample.service.RoleService;

public class EditController {

    @FXML
    private TextField editRoleName;

    private int id;

    private TableView<RoleEntity> tableView;

    public void initEditRole(RoleEntity entity, TableView<RoleEntity> tableView) {
        this.tableView = tableView;
        editRoleName.setText(entity.getName());
        id = entity.getId();
    }

    public void editRole(ActionEvent actionEvent) {
        RoleService service = new RoleService();
        int result = service.update(new RoleEntity(id, editRoleName.getText()));
        if (result > 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("提示");
            alert.setContentText("修改成功");
            alert.showAndWait();
            tableView.setItems(service.findAll());
        }
        Stage stage = (Stage)editRoleName.getScene().getWindow();
        stage.close();
    }
}
