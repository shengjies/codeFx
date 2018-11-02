package sample.user;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.entity.RoleEntity;
import sample.entity.UserEntity;
import sample.service.RoleService;
import sample.service.UserService;


public class EditController {

    @FXML
    private TextField editName;
    @FXML
    private ChoiceBox<String> editRole;
    @FXML
    private Button editUserBtn;

    private TableView<UserEntity> tableView;

    private int id;

    public void initEdit(UserEntity entity, TableView<UserEntity> tableView) {
        ObservableList<String> role = FXCollections.observableArrayList();
        RoleService service = new RoleService();
        for (RoleEntity roleEntity : service.findAll()) {
            role.add(roleEntity.getName());
        }
        editRole.setItems(role);
        editName.setText(entity.getName());
        editRole.setValue(entity.getRole());
        id = entity.getId();
        this.tableView = tableView;
    }

    public void editUser(ActionEvent actionEvent) {
        UserService service = new UserService();
        int result = service.update(new UserEntity(id, editName.getText().trim(), editRole.getValue().trim()));
        if (result > 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("提示");
            alert.setContentText("修改成功");
            alert.showAndWait();
            tableView.setItems(service.findAll());
        }
        Stage stage = (Stage) editUserBtn.getScene().getWindow();
        stage.close();
    }
}
