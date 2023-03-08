package lk.ijse.groceryshop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lk.ijse.groceryshop.embeded.CustMobile;
import lk.ijse.groceryshop.embeded.CustName;
import lk.ijse.groceryshop.entity.Customer;
import lk.ijse.groceryshop.util.HbFactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.util.ArrayList;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        Session session= HbFactoryConfiguration.getInstance().getSession();
        Transaction transaction=session.beginTransaction();

        try {
            primaryStage.setScene(new Scene(
                    FXMLLoader.load(
                            getClass().getResource("resources/forms/DashboardForm.fxml")
                    )
            ));
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.show();







        //save customer object









        //System.out.println(session.get(Customer.class,"C001"));

        //save item object
/*
        Item i = new Item();
        i.setCode("i001");
        i.setDescription("sugar");
        i.setQtyOnHand(1);
        i.setUnitPrice(250);
        session.save(i);

        //System.out.println(session.get(Item.class,"i001"));

 */

      //  session.save(cd);

       // transaction.commit();
        //session.close();
    }
}
