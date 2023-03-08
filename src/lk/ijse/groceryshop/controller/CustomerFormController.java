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
import lk.ijse.groceryshop.embeded.CustMobile;
import lk.ijse.groceryshop.embeded.CustName;
import lk.ijse.groceryshop.service.ServiceFactory;
import lk.ijse.groceryshop.service.ServiceTypes;
import lk.ijse.groceryshop.service.custom.CustomerService;
import lk.ijse.groceryshop.view.tm.CustomerTm;


import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

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
    public TextField txtFName;
    public TextField txtMName;
    public TextField txtLName;
    public TextField txtPhoneNum;
    public ComboBox cmboxPhoneNum;
    public TableColumn colFName;
    public TableColumn colMName;
    public TableColumn colLName;
    public TableColumn colPhoneNos;
    public JFXButton removebtncmbox;
    ArrayList<CustMobile> phoneNumArray= new ArrayList<>();
    List<String> StrinPhoneNolist;
    private CustomerService customerService= ServiceFactory.getInstance().getService(ServiceTypes.CUSTOMER);

    private String searchText="";

    public void initialize() {
        phoneNumArray.clear();


        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colFName.setCellValueFactory(new PropertyValueFactory<>("fname"));
        colMName.setCellValueFactory(new PropertyValueFactory<>("mname"));
        colLName.setCellValueFactory(new PropertyValueFactory<>("lname"));
        colPhoneNos.setCellValueFactory(new PropertyValueFactory<>("phonenos"));
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
        txtFName.setText(tm.getFname());
        txtMName.setText(tm.getMname());
        txtLName.setText(tm.getLname());
        txtAddress.setText(tm.getAddress());
        txtSalary.setText(String.valueOf(tm.getSalary()));

        cmboxPhoneNum.getItems().clear();
        StrinPhoneNolist = new ArrayList<String>(Arrays.asList(tm.getPhonenos().split("\\s+")));
        ObservableList<String> oblPhoneNums = FXCollections.observableArrayList();
        oblPhoneNums.setAll(StrinPhoneNolist);
        cmboxPhoneNum.setItems(oblPhoneNums);


        btnSaveCustomer.setText("Update Customer");
    }

    private void searchCustomers(String text) {
        ObservableList<CustomerTm> tmList = FXCollections.observableArrayList();

        List<CustomerDTO> customerList= customerService.searchCustomerByText(searchText);

        for (CustomerDTO c: customerList){
            String phoneNo =null;
            StringBuffer bfNo = new StringBuffer();
            for(CustMobile p:c.getPhoneNosArray()){
                bfNo.append(p.getPhoneNums()+"  ");
            }
            phoneNo=bfNo.toString();
            Button btn = new Button("Delete");
            CustomerTm tm = new CustomerTm(
                    c.getId(),
                    c.getName().getFName(),
                    c.getName().getMName(),
                    c.getName().getLName(),
                    c.getAddress(),
                    c.getSalary(),
                    btn,
                    phoneNo
            );
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
        if (btnSaveCustomer.getText().equalsIgnoreCase("Save Customer")) {
            CustomerDTO cd = new CustomerDTO();
            cd.setId(txtId.getText());
            cd.setName(new CustName(txtFName.getText(),txtMName.getText(),txtLName.getText()));
            cd.setPhoneNosArray(phoneNumArray);
            cd.setAddress(txtAddress.getText());
            cd.setSalary(Double.parseDouble(txtSalary.getText()));
            boolean isCustomerSaved = customerService.saveCustomer(cd);
            if (isCustomerSaved) {
                searchCustomers(searchText);
                clearFields();
                new Alert(Alert.AlertType.INFORMATION, "Customer Saved!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again!").show();
            }

        } else {
            for (int l=0;l<StrinPhoneNolist.size();l++){
                phoneNumArray.add(new CustMobile(StrinPhoneNolist.get(l)));
            }
            CustomerDTO ucd = new CustomerDTO();
            ucd.setId(txtId.getText());
            ucd.setName(new CustName(txtFName.getText(),txtMName.getText(),txtLName.getText()));
            ucd.setAddress(txtAddress.getText());
            ucd.setSalary(Double.parseDouble(txtSalary.getText()));
            ucd.setPhoneNosArray(phoneNumArray);
            boolean isCustomerUpdated = customerService.updateCustomer(ucd);
            if (isCustomerUpdated) {
                searchCustomers(searchText);
                clearFields();
                new Alert(Alert.AlertType.INFORMATION, "Customer Updated!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again!").show();
            }
        }
    }

    private void clearFields() {
        cmboxPhoneNum.getItems().clear();
        txtId.clear();
        txtFName.clear();
        txtMName.clear();
        txtLName.clear();
        txtAddress.clear();
        txtSalary.clear();
        txtPhoneNum.clear();
        phoneNumArray.clear();
        loadCoomboBox();
    }

    public void backToHomeOnAction(ActionEvent actionEvent) throws IOException {
       Stage stage= (Stage)customerFormContext.getScene().getWindow();
       stage.setScene(new Scene
               (FXMLLoader.load(getClass().
                       getResource("../resources/forms/DashboardForm.fxml"))));
    }

    public void newCustomerOnAction(ActionEvent actionEvent) {
        clearFields();
        btnSaveCustomer.setText("Save Customer");
    }

    public void AddPhoneNumToCmbox(ActionEvent actionEvent) {
        if (btnSaveCustomer.getText().equalsIgnoreCase("Save Customer")) {
            phoneNumArray.add(new CustMobile(txtPhoneNum.getText()));
            loadCoomboBox();
        }else{
            StrinPhoneNolist.add(txtPhoneNum.getText());
            ObservableList<String> oblPhoneNums = FXCollections.observableArrayList();
            oblPhoneNums.setAll(StrinPhoneNolist);
            cmboxPhoneNum.setItems(oblPhoneNums);
        }
    }

    public void AdRemovePhoneNumFromCmbox(ActionEvent actionEvent) {
            if (btnSaveCustomer.getText().equalsIgnoreCase("Save Customer")) {
                if(phoneNumArray.size()>=2) {
                    phoneNumArray.remove(cmboxPhoneNum.getSelectionModel().getSelectedIndex());
                    loadCoomboBox();
                }
            } else {
                if(StrinPhoneNolist.size()>=2) {
                    StrinPhoneNolist.remove(cmboxPhoneNum.getSelectionModel().getSelectedIndex());
                    cmboxPhoneNum.getItems().clear();
                    ObservableList<String> oblPhoneNums = FXCollections.observableArrayList();
                    oblPhoneNums.setAll(StrinPhoneNolist);
                    cmboxPhoneNum.setItems(oblPhoneNums);
                }
            }

    }

    private void loadCoomboBox(){
        txtPhoneNum.clear();
        cmboxPhoneNum.getItems().clear();
        ObservableList<String> oblPhoneNums = FXCollections.observableArrayList();
        for(CustMobile c:phoneNumArray){
            oblPhoneNums.add(c.getPhoneNums());
        }
        cmboxPhoneNum.setItems(oblPhoneNums);
    }
}
