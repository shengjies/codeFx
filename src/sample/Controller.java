package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sample.home.HomeController;

import java.util.Optional;


public class Controller {

    @FXML
    private GridPane loginPane;

    @FXML
    public void onBtnClick(ActionEvent actionEvent) throws Exception {
        Stage stage = (Stage) loginPane.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("home/home.fxml"));
        Parent home = fxmlLoader.load();
        Stage homeStage = new Stage();
        homeStage.setTitle("xx 系统");
        homeStage.setResizable(true);
        homeStage.setScene(new Scene(home, 1200, 700));
        homeStage.setOnCloseRequest(event -> {
            Alert _alert = new Alert(Alert.AlertType.CONFIRMATION, "确认退出吗？", new ButtonType("取消", ButtonBar.ButtonData.NO),
                    new ButtonType("确定", ButtonBar.ButtonData.YES));
            _alert.setTitle("确认");
            _alert.setHeaderText("提示");
            Optional<ButtonType> _buttonType = _alert.showAndWait();
            if (_buttonType.get().getButtonData().equals(ButtonBar.ButtonData.YES)) {
                homeStage.close();
            }else{
                event.consume();
            }
        });
        HomeController homeController =fxmlLoader.getController();
        homeController.initData();
        homeStage.show();

    }
}
