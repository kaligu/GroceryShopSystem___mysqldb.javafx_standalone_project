package lk.ijse.groceryshop.controller;


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
import lk.ijse.groceryshop.dto.ItemDTO;
import lk.ijse.groceryshop.dto.OrdersDTO;
import lk.ijse.groceryshop.dto.PlaceOrderDTO;
import lk.ijse.groceryshop.entity.Customer;
import lk.ijse.groceryshop.entity.Item;
import lk.ijse.groceryshop.entity.Order;
import lk.ijse.groceryshop.modal.ItemDetails;
import lk.ijse.groceryshop.modal.OrderDetails;
import lk.ijse.groceryshop.service.ServiceFactory;
import lk.ijse.groceryshop.service.ServiceTypes;
import lk.ijse.groceryshop.service.custom.CustomerService;
import lk.ijse.groceryshop.service.custom.ItemService;
import lk.ijse.groceryshop.service.custom.OrderService;
import lk.ijse.groceryshop.service.custom.PlaceOrderService;
import lk.ijse.groceryshop.view.tm.CartTm;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

public class PlaceOrderFormController {
    public TextField txtDate;
    public ComboBox<String> cmbCustomerIds;
    public ComboBox<String> cmbItemCodes;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtSalary;
    public TextField txtDescription;
    public TextField txtUnitPrice;
    public TextField txtQtyOnHand;
    public TextField txtQty;
    public TableView<CartTm> tblCart;
    public TableColumn colCode;
    public TableColumn colDescription;
    public TableColumn colUnitPrice;
    public TableColumn colQty;
    public TableColumn colTotal;
    public TableColumn colOption;
    public Label lblTotal;
    public TextField txtOrderId;
    public AnchorPane placeOrderFormContext;
    public TextField txtLName;
    public TextField txtMName;
    public TextField txtFName;
    private CustomerService customerService= ServiceFactory.getInstance().getService(ServiceTypes.CUSTOMER);
    private ItemService itemService= ServiceFactory.getInstance().getService(ServiceTypes.ITEM);
    private PlaceOrderService placeOrderService= ServiceFactory.getInstance().getService(ServiceTypes.PLACEORDER);
    private OrderService orderService= ServiceFactory.getInstance().getService(ServiceTypes.ORDER);

    public void initialize(){

        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));

        setDateAndOrderId();
        loadAllCustomerIds();
        loadAllItemCodes();
         setOrderId();

        cmbCustomerIds.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if(newValue!=null){
                        setCustomerDetails();
                    }
        });

        cmbItemCodes.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if(newValue!=null){
                        setItemDetails();
                    }
                });


    }

    private void setOrderId() {
        if (orderService.getLastorderID().equals("null")){
            txtOrderId.setText("O-1");
        }else {
            String tempOrderId=orderService.getLastorderID();
            String[] array = tempOrderId.split("-");
            int tempNumber=Integer.parseInt(array[1]);
            int finalizeOrderId=tempNumber+1;
            txtOrderId.setText("O-"+finalizeOrderId);
        }
    }

    private void setItemDetails() {
        ItemDTO i = itemService.findItemByPk(cmbItemCodes.getValue());
        txtDescription.setText(i.getDescription());
        txtUnitPrice.setText(String.valueOf(i.getUnitPrice()));
        txtQtyOnHand.setText(String.valueOf(i.getQtyOnHand()));
    }

    private void setCustomerDetails() {
        CustomerDTO c =customerService.findCustomerByPk(cmbCustomerIds.getValue());
        txtFName.setText(c.getName().getFName());
        txtMName.setText(c.getName().getMName());
        txtLName.setText(c.getName().getLName());
        txtAddress.setText(c.getAddress());
        txtSalary.setText(String.valueOf(c.getSalary()));
    }

    private void loadAllItemCodes() {
        ObservableList<String> obList=FXCollections.observableArrayList(itemService.SearchItemAllIds());
        cmbItemCodes.setItems(obList);
    }

/*

 */
    private void loadAllCustomerIds() {
        ObservableList<String> oblist = FXCollections.observableArrayList();
        oblist.setAll(customerService.SearchCustomerAllIds());
        cmbCustomerIds.setItems(oblist);
    }
    


    private void setDateAndOrderId() {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String d =df.format(date);
        txtDate.setText(d);
        txtDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
    }

    private boolean checkQty(String code, int qty){
        int q;
        q=itemService.findItemByPk(code).getQtyOnHand();
        if (q>=qty){
            return true;
        }else{
            return false;
        }
    }

    ObservableList<CartTm> obList = FXCollections.observableArrayList();
    public void addToCartOnAction(ActionEvent actionEvent) {


        if (!checkQty(cmbItemCodes.getValue(),Integer.parseInt(txtQty.getText()))){
            new Alert(Alert.AlertType.WARNING, "Invalid Qty").show();
            return;
        }

        double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        int qty=Integer.parseInt(txtQty.getText());
        double total = unitPrice*qty;
        Button btn = new Button("Delete");
        int row = isAlreadyExists(cmbItemCodes.getValue());

        if (row==-1){
            CartTm tm = new CartTm(cmbItemCodes.getValue(),txtDescription.getText(),unitPrice,qty,total,btn);
            obList.add(tm);
            tblCart.setItems(obList);
        }else{
            int tempQty=obList.get(row).getQty()+qty;
            double tempTotal = unitPrice* tempQty;

            if (!checkQty(cmbItemCodes.getValue(),tempQty)){
                new Alert(Alert.AlertType.WARNING, "Invalid Qty").show();
                return;
            }

            obList.get(row).setQty(tempQty);
            obList.get(row).setTotal(tempTotal);
            tblCart.refresh();
        }

        calculateTotal();
        clearFields();
        cmbItemCodes.requestFocus();


        btn.setOnAction(event -> {
            Alert alert= new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES,ButtonType.NO);
            Optional<ButtonType> buttonType = alert.showAndWait();

            if (buttonType.get()==ButtonType.YES){
                for (CartTm tm: obList
                     ) {
                        obList.remove(tm);
                        calculateTotal();
                        tblCart.refresh();
                        return;
                }
            }

        });

    }

    private void clearFields() {
        txtDescription.clear();
        txtUnitPrice.clear();
        txtQtyOnHand.clear();
        txtQty.clear();
    }

    private int isAlreadyExists(String code){
        for (int i = 0; i < obList.size(); i++) {
            if (obList.get(i).getCode().equals(code)){
                return i;
            }
        }
        return -1;
    }

    private void calculateTotal(){
        double total=0.00;
        for (CartTm tm: obList
             ) {
            total+=tm.getTotal();
        }
        lblTotal.setText(String.valueOf(total));
    }
    


    public void placeOrderOnAction(ActionEvent actionEvent) throws SQLException {
        if (obList.isEmpty()) return;
        ArrayList<lk.ijse.groceryshop.modal.OrderDetails> details= new ArrayList<>();
        for (CartTm tm:obList
             ) {
            details.add(new OrderDetails(
                    tm.getQty(),
                    tm.getCode()
            ));
        }
        PlaceOrderDTO order= new PlaceOrderDTO(
                txtOrderId.getText(),  //order id
                txtDate.getText(),
                Double.parseDouble(lblTotal.getText()),
                cmbCustomerIds.getValue(),
                details
        );

        boolean isplaced = placeOrderService.PlaceAOrder(order);
        if(isplaced){
            new Alert(Alert.AlertType.CONFIRMATION, "Order Placed!").show();
            clearAll();
        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again!").show();
        }
    }

    private void clearAll() {
        obList.clear();
        calculateTotal();

        txtName.clear();
        txtAddress.clear();
        txtSalary.clear();

        cmbCustomerIds.setValue(null);
        cmbItemCodes.setValue(null);

        clearFields();
        cmbCustomerIds.requestFocus();
        setOrderId();
    }

    public void backToHomeOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage= (Stage) placeOrderFormContext.getScene().getWindow();
        stage.setScene(new Scene
                (FXMLLoader.load(getClass().
                        getResource("../resources/forms/DashboardForm.fxml"))));
    }

}
