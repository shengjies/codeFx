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

public class AddController {
    @FXML
    private TextField addName;
    @FXML
    private ChoiceBox<String> addRole;
    @FXML
    private Button addUserBtn;

    private TableView<UserEntity> tableView;

    public void initAdd(TableView<UserEntity> tableView){
        this.tableView = tableView;
        ObservableList<String> role = FXCollections.observableArrayList();
        RoleService service = new RoleService();
        for (RoleEntity roleEntity : service.findAll()) {
            role.add(roleEntity.getName());
        }
        this.addRole.setItems(role);
    }

    public void addUserClick(ActionEvent actionEvent) throws Exception {
        UserService service = new UserService();
        int result = service.insert(new UserEntity(addName.getText(), addRole.getValue()));
        Stage stage = (Stage) addUserBtn.getScene().getWindow();

        if (result > 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("提示");
            alert.setContentText("添加成功");
            alert.showAndWait();
            tableView.setItems(service.findAll());
        }
        stage.close();
    }
}
