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
import lk.ijse.groceryshop.dto.CustomerDTO;
import lk.ijse.groceryshop.service.ServiceFactory;
import lk.ijse.groceryshop.service.ServiceTypes;
import lk.ijse.groceryshop.service.custom.CustomerService;
import lk.ijse.groceryshop.view.tm.CustomerTm;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerFormController {
    public TextField txtId;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtSalary;
    public TableView<CustomerTm> tblCustomer;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colSalary;
    public TableColumn colOption;
    public JFXButton btnSaveCustomer;
    public AnchorPane customerFormContext;
    public TextField txtSearch;

    private CustomerService customerService= ServiceFactory.getInstance().getService(ServiceTypes.CUSTOMER);

    private String searchText="";

    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));

        searchCustomers(searchText);

        tblCustomer.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (null!=newValue){// newValue!=null
                        setData(newValue);
                    }
                });
        txtSearch.textProperty()
                .addListener((observable, oldValue, newValue) -> {
                    searchText=newValue;
                    searchCustomers(searchText);
                });


    }

    private void setData(CustomerTm tm) {
        txtId.setText(tm.getId());
        txtName.setText(tm.getName());
        txtAddress.setText(tm.getAddress());
        txtSalary.setText(String.valueOf(tm.getSalary()));
        btnSaveCustomer.setText("Update Customer");
    }

    private void searchCustomers(String text) {

        ObservableList<CustomerTm> tmList = FXCollections.observableArrayList();

        List<CustomerDTO> customerList= customerService.searchCustomerByText(searchText);

        for (CustomerDTO c: customerList){
            Button btn = new Button("Delete");
            CustomerTm tm = new CustomerTm(
                    c.getId(),
                    c.getName(),
                    c.getAddress(),
                    c.getSalary(),
                    btn);
            tmList.add(tm);
            btn.setOnAction(event -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                        "are you sure whether do you want to delete this customer?",
                        ButtonType.YES, ButtonType.NO);
                Optional<ButtonType> buttonType = alert.showAndWait();
                if (buttonType.get() == ButtonType.YES) {
                    if (customerService.deleteCustomer(tm.getId())) {
                        searchCustomers(searchText);
                        new Alert(Alert.AlertType.INFORMATION, "Customer Deleted!").show();
                    } else {
                        new Alert(Alert.AlertType.WARNING, "Try Again!").show();
                    }
                }
            });
        }
        tblCustomer.setItems(tmList);

    }

    public void saveCustomerOnAction(ActionEvent actionEvent) {
        /*
        if (btnSaveCustomer.getText().equalsIgnoreCase("Save Customer")) {
            boolean isCustomerSaved = customerService.saveCustomer(
                    new CustomerDTO(
                            txtId.getText(),
                            txtName.getText(), txtAddress.getText(),
                            Double.parseDouble(txtSalary.getText())
                    )
            );
            if (isCustomerSaved) {
                searchCustomers(searchText);
                clearFields();
                new Alert(Alert.AlertType.INFORMATION, "Customer Saved!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again!").show();
            }

        } else {
            boolean isCustomerUpdated = customerService.updateCustomer(
                    new CustomerDTO(
                            txtId.getText(),
                            txtName.getText(), txtAddress.getText(),
                            Double.parseDouble(txtSalary.getText())
                    )
            );
            if (isCustomerUpdated) {
                searchCustomers(searchText);
                clearFields();
                new Alert(Alert.AlertType.INFORMATION, "Customer Updated!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again!").show();
            }
        }

         */
    }

    private void clearFields() {
        txtId.clear();
        txtName.clear();
        txtAddress.clear();
        txtSalary.clear();
    }

    public void backToHomeOnAction(ActionEvent actionEvent) throws IOException {
       Stage stage= (Stage)customerFormContext.getScene().getWindow();
       stage.setScene(new Scene
               (FXMLLoader.load(getClass().
                       getResource("../resources/forms/DashboardForm.fxml"))));
    }

    public void newCustomerOnAction(ActionEvent actionEvent) {
        btnSaveCustomer.setText("Save Customer");
    }
}
