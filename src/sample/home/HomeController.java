package sample.home;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import sample.role.RoleController;
import sample.service.UserService;
import sample.user.UserController;

public class HomeController {
    @FXML
    private TreeView<String> homeTree;

    @FXML
    private TabPane homeTabPane;

    @FXML
    private PieChart pieChart;


    public void initData() {
        TreeItem<String> rootItem = new TreeItem<>("信息管理");
        rootItem.setExpanded(true);
        TreeItem<String> item = new TreeItem<>("用户信息");
        rootItem.getChildren().add(item);
        TreeItem<String> item1 = new TreeItem<>("角色信息");
        rootItem.getChildren().add(item1);
        homeTree.setRoot(rootItem);
        UserService service = new UserService();
        pieChart.setData(service.initPieData());
    }

    public void treeViewClick(MouseEvent event) throws Exception {
        String name = homeTree.getSelectionModel().getSelectedItem().getValue();
        if ("信息管理".equals(name)) return;
        int num = 0;
        boolean boo = true;
        for (int i = 0; i < homeTabPane.getTabs().size(); i++) {
            if (name.equals(homeTabPane.getTabs().get(i).getText())) {
                num = i;
                boo = false;
            }
        }
        if (boo) {
            Tab tab = new Tab(name);
            switch (name) {
                case "用户信息":
                    FXMLLoader userFxmlLoader = new FXMLLoader(getClass().getResource("../user/user.fxml"));
                    BorderPane user = userFxmlLoader.load();
                    tab.setContent(user);
                    UserController userController = userFxmlLoader.getController();
                    userController.initUserTableData();
                    break;
                case "角色信息":
                    FXMLLoader roleFxmlLoader = new FXMLLoader(getClass().getResource("../role/role.fxml"));
                    BorderPane role = roleFxmlLoader.load();
                    tab.setContent(role);
                    RoleController roleController = roleFxmlLoader.getController();
                    roleController.initRoleData();
                    break;
            }
            tab.setClosable(true);
            homeTabPane.getTabs().add(tab);
            homeTabPane.getSelectionModel().select(tab);
        } else {
            homeTabPane.getSelectionModel().select(num);
        }
    }
}
