package lk.ijse.groceryshop.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.groceryshop.dto.ItemDTO;
import lk.ijse.groceryshop.service.ServiceFactory;
import lk.ijse.groceryshop.service.ServiceTypes;
import lk.ijse.groceryshop.service.custom.ItemService;
import lk.ijse.groceryshop.view.tm.ItemTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ItemFormController {
    public AnchorPane itemFormContext;
    public TextField txtCode;
    public TextField txtDescription;
    public TextField txtUnitPrice;
    public TextField txtQtyOnHand;
    public JFXButton btnSaveItem;
    public TextField txtSearch;
    public TableView<ItemTm> tblItem;
    public TableColumn colCode;
    public TableColumn colDescription;
    public TableColumn colUnitPrice;
    public TableColumn colQtyOnHand;
    public TableColumn colOption;

    private ItemService itemService= ServiceFactory.getInstance().getService(ServiceTypes.ITEM);


    private String searchText = "";

    public void initialize() {
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));

        searchItems(searchText);
        tblItem.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (null != newValue) {// newValue!=null
                        setData(newValue);
                    }
                });
        txtSearch.textProperty()
                .addListener((observable, oldValue, newValue) -> {
                    searchText = newValue;
                    searchItems(searchText);
                });

    }

    private void setData(ItemTm tm) {
        txtCode.setText(tm.getCode());
        txtDescription.setText(tm.getDescription());
        txtUnitPrice.setText(String.valueOf(tm.getUnitPrice()));
        txtQtyOnHand.setText(String.valueOf(tm.getQtyOnHand()));
        btnSaveItem.setText("Update Item");
    }


    private void searchItems(String text) {

        ObservableList<ItemTm> tmList = FXCollections.observableArrayList();

        List<ItemDTO> itemList=itemService.searchCustomerByText(searchText);

        for (ItemDTO i:itemList){
            Button btn = new Button("Delete");
            ItemTm tm = new ItemTm(
                    i.getCode(),
                    i.getDescription(),
                    i.getUnitPrice(),
                    i.getQtyOnHand(),
                    btn);
            tmList.add(tm);
            btn.setOnAction(event -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                        "are you sure whether do you want to delete this Item?",
                        ButtonType.YES, ButtonType.NO);
                Optional<ButtonType> buttonType = alert.showAndWait();
                if (buttonType.get() == ButtonType.YES) {
                    if (itemService.deleteItem(tm.getCode())) {
                        searchItems(searchText);
                        new Alert(Alert.AlertType.INFORMATION, "Item Deleted!").show();
                    } else {
                        new Alert(Alert.AlertType.WARNING, "Try Again!").show();
                    }


                }
            });
        }
        tblItem.setItems(tmList);

    }

    public void backToHomeOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage= (Stage) itemFormContext.getScene().getWindow();
        stage.setScene(new Scene
                (FXMLLoader.load(getClass().
                        getResource("../resources/forms/DashboardForm.fxml"))));
    }

    public void newItemOnAction(ActionEvent actionEvent) {
        btnSaveItem.setText("Save Item");
    }

    public void saveItemOnAction(ActionEvent actionEvent) {

        if (btnSaveItem.getText().equalsIgnoreCase("Save Item")) {

            boolean isItemSaved = itemService.saveItem(
                    new ItemDTO(txtCode.getText(),
                    txtDescription.getText(), Double.parseDouble(txtUnitPrice.getText()),
                    Integer.parseInt(txtQtyOnHand.getText())));
            if (isItemSaved) {
                searchItems(searchText);
                clearFields();
                new Alert(Alert.AlertType.INFORMATION, "Item Saved!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again!").show();
            }

        } else {

            boolean isItemUpdated = itemService.updateItem(
                    new ItemDTO(txtCode.getText(),
                            txtDescription.getText(), Double.parseDouble(txtUnitPrice.getText()),
                            Integer.parseInt(txtQtyOnHand.getText())));
            if (isItemUpdated) {
                searchItems(searchText);
                clearFields();
                new Alert(Alert.AlertType.INFORMATION, "Customer Updated!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again!").show();
            }
        }
    }

    private void clearFields() {
        txtCode.clear();
        txtDescription.clear();
        txtUnitPrice.clear();
        txtQtyOnHand.clear();
    }
}
